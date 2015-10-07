package v2.org.analysis.transition_rule;

import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Immediate;
import org.jakstab.asm.Operand;
import org.jakstab.asm.x86.X86Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;
import v2.org.analysis.complement.BitVector;
import v2.org.analysis.complement.Convert;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.loop.LoopAlgorithm;
import v2.org.analysis.path.BPPath;
import v2.org.analysis.path.BPState;
import v2.org.analysis.system.SEHHandle;
import v2.org.analysis.value.*;

import java.util.Calendar;
import java.util.List;

public class X86InstructionInterpreter {
	public BPState execute(X86Instruction inst, BPPath path, List<BPPath> pathList, X86TransitionRule rule) {
		// TODO Auto-generated method stub
		BPState curState = path.getCurrentState();
		Operand dest = inst.getOperand1();
		Operand src = inst.getOperand2();
		Environment env = curState.getEnvironement();
		
		// PHONG - 20150921 - Check SEH before going ///////////////
		AbsoluteAddress curAddr = curState.getLocation();
		if (rule.getSEHHandle().causeException(curAddr))
		{
			SEHHandle sehHandle = curState.getEnvironement().getSystem().getSEHHandler();
			sehHandle.setSEHType(SEHHandle.SINGLE_STEP);
			return rule.processSEH(curState);
		}
		///////////////////////////////////////////////////////////
		if (inst.getName().startsWith("int")) {
			if (dest != null && dest.getClass().getSimpleName().equals("Immediate")) {
				long x = (long) Convert.convetUnsignedValue(((Immediate) dest).getNumber().intValue(),
						rule.getBitCount(inst));

				// Process interrupt 68h
				if (x == 104) {
					SEHHandle sehHandle = env.getSystem().getSEHHandler();
					sehHandle.setSEHType(SEHHandle.INTERUPT);
					return rule.processSEH(curState);
				}
				// Process interrupt 80h
				else if (x == 80) {

				} else
				// Process interrupt 21h
				if (x == 33) {
					// Check if AH = 2A
					// Return: CX = year (1980-2099) DH = month DL = day AL =
					// day of week (00h=Sunday)
					if (env.getRegister().getRegisterValue("%ah").equal(new LongValue(42))) {
						env.getRegister().setRegisterValue("%cx",
								new LongValue(Calendar.getInstance().get(Calendar.YEAR)));
						// int z = Calendar.getInstance().get(Calendar.MONTH);
						env.getRegister().setRegisterValue("%dh",
								new LongValue(Calendar.getInstance().get(Calendar.MONTH) + 1));
						env.getRegister().setRegisterValue("%dl",
								new LongValue(Calendar.getInstance().get(Calendar.DATE)));
						env.getRegister().setRegisterValue("%al",
								new LongValue(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)));
					}
				}

			}
		}
		else {
			Program.getProgram().getLog()
					.error("Instruction not supported" + inst.getName() + " at " + curState.getLocation());
		}

		rule.generateNextInstruction(inst, path, pathList, true);
		return curState;
	}
	/*
	private void movString(Environment env, X86Instruction inst, int opSize) {
		int s = opSize;
		switch (opSize) {
		case 8:
			s = 1;
			break;
		case 16:
			s = 2;
			break;
		case 32:
			s = 4;
			break;
		}
		Value esi = env.getRegister().getRegisterValue("esi");
		Value edi = env.getRegister().getRegisterValue("edi");
		Operand dest = inst.getOperand1();
		Operand src = inst.getOperand2();

		if (dest != null && src != null && dest instanceof X86MemoryOperand && src instanceof X86MemoryOperand) {
			Value temp = env.getMemory().getMemoryValue((X86MemoryOperand) src, inst);
			env.getMemory().setMemoryValue((X86MemoryOperand) dest, temp, inst);
			Value df = env.getFlag().getDFlag();

			if (df != null && df instanceof BooleanValue && ((BooleanValue) df).getValue() == true) {
				env.getRegister().sub("%esi", s);
				env.getRegister().sub("%edi", s);
			}
			// DF = 0
			else {
				env.getRegister().add("%esi", s);
				env.getRegister().add("%edi", s);
			}
		}
	}

	private void loadString(Environment env, X86Instruction inst, int opSize) {
		Operand source = inst.getOperand2();
		Value load = null;
		String base = "edi";
		if (source instanceof X86MemoryOperand) {
			load = env.getMemory().getMemoryValue((X86MemoryOperand) source, inst);
			if (((X86MemoryOperand) source).getBase() != null)
				base = ((X86MemoryOperand) source).getBase().toString();
		}

		if (load == null)
			return;
		Value df = env.getFlag().getDFlag();
		switch (opSize) {
		case 8:
			env.getRegister().setRegisterValue("al", load);

			if (df != null && df instanceof BooleanValue && ((BooleanValue) df).getValue()) {
				env.getRegister().sub(base, new LongValue(1));
			} else
				env.getRegister().add(base, new LongValue(1));

			break;
		case 16:
			env.getRegister().setRegisterValue("ax", load);

			if (df != null && df instanceof BooleanValue && ((BooleanValue) df).getValue()) {
				env.getRegister().sub(base, new LongValue(2));
			} else
				env.getRegister().add(base, new LongValue(2));

			break;
		case 32:
			env.getRegister().setRegisterValue("eax", load);

			if (df != null && df instanceof BooleanValue && ((BooleanValue) df).getValue()) {
				env.getRegister().sub(base, new LongValue(4));
			} else
				env.getRegister().add(base, new LongValue(4));

			break;
		}
	}

	private void storeString(Environment env, X86Instruction inst, int opSize) {
		Operand dest = inst.getOperand1();
		Value store = null;

		String base = "edi";
		if (dest instanceof X86MemoryOperand) {
			if (((X86MemoryOperand) dest).getBase() != null)
				base = ((X86MemoryOperand) dest).getBase().toString();
		}

		Value df = env.getFlag().getDFlag();
		switch (opSize) {
		case 8:
			store = env.getRegister().getRegisterValue("al");
			if (dest instanceof X86MemoryOperand) {
				env.getMemory().setMemoryValue((X86MemoryOperand) dest, store, inst);

				if (df != null && df instanceof BooleanValue && ((BooleanValue) df).getValue()) {
					env.getRegister().sub(base, new LongValue(1));
				} else
					env.getRegister().add(base, new LongValue(1));
			}
			break;
		case 16:
			store = env.getRegister().getRegisterValue("ax");
			if (dest instanceof X86MemoryOperand) {
				env.getMemory().setMemoryValue((X86MemoryOperand) dest, store, inst);

				if (df != null && df instanceof BooleanValue && ((BooleanValue) df).getValue()) {
					env.getRegister().sub(base, new LongValue(2));
				} else
					env.getRegister().add(base, new LongValue(2));
			}
			break;
		case 32:
			store = env.getRegister().getRegisterValue("eax");
			if (dest instanceof X86MemoryOperand) {
				env.getMemory().setMemoryValue((X86MemoryOperand) dest, store, inst);

				if (df != null && df instanceof BooleanValue && ((BooleanValue) df).getValue()) {
					env.getRegister().sub(base, new LongValue(4));
				} else
					env.getRegister().add(base, new LongValue(4));
			}
			break;
		}
	}
	*/
}
