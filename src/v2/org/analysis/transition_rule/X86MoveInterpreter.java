package v2.org.analysis.transition_rule;

import org.jakstab.Program;
import org.jakstab.asm.Operand;
import org.jakstab.asm.x86.X86MemoryOperand;
import org.jakstab.asm.x86.X86MoveInstruction;
import org.jakstab.asm.x86.X86Register;
//import v2.org.analysis.apihandle.APIHandle;

import v2.org.analysis.apihandle.winapi.APIHandle;
import v2.org.analysis.complement.BitVector;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.path.BPPath;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

import java.util.List;

public class X86MoveInterpreter {
	private APIHandle apiHandle = null;

	public BPState execute(X86MoveInstruction inst, BPPath path,
			List<BPPath> pathList, X86TransitionRule rule) {
		// TODO Auto-generated method stub
		this.apiHandle = rule.getAPIHandle();
		BPState curState = path.getCurrentState();
		Environment env = curState.getEnvironement();

		Operand dest = inst.getOperand1();
		Operand src = inst.getOperand2();
		int opSize = rule.getBitCount(inst);
		if (inst.getName().startsWith("movz")) {
			Value source = rule.getValueOperand(src, env, inst);
			if (source != null && source instanceof LongValue) {
				long t = ((LongValue)source).getValue();
				rule.setValueOperand(dest, new LongValue(BitVector.extend(t, 0, opSize)), env, inst);
			} else
				rule.setValueOperand(dest, source, env, inst);
		} else if (inst.getName().startsWith("movsx")) {
			Value source = rule.getValueOperand(src, env, inst);
			if (source != null && source instanceof LongValue) {
				long t = ((LongValue)source).getValue();
				int sign = BitVector.getMSB(t, opSize / 2);
				rule.setValueOperand(dest, new LongValue(BitVector.extend(t, sign, opSize)), env, inst);
			} else
				rule.setValueOperand(dest, source, env, inst);
		} else if (inst.getName().startsWith("mov")) {
			// normal move
			Value source = rule.getValueOperand(src, env, inst);
			//System.out.println();
			if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
				// prevState.getMemoryValues().put((MemoryOperand) dest,
				// toMoveVal);
				X86MemoryOperand y = (X86MemoryOperand) dest;
				// Xu li truong hop mov fs:0, esp
				// Khi do se tac dong den SEH
				boolean b = false;
				if (y.getDisplacement() == 0 && y.getBase() == null)
					b = true;
				else {
					if (y.getBase() != null) {
						Value v = env.getRegister().getRegisterValue(
								y.getBase().toString());
						if (v instanceof LongValue)
							b = (((LongValue) v).getValue() == 0);
					}
				}

				if (y.getSegmentRegister() != null
						&& y.getSegmentRegister().toString() == "%fs" && b) {
					System.out.println("SEH Exploit:"
							+ curState.getLocation().toString());
					if (src.getClass().getSimpleName().equals("X86Register")) {
						if (((X86Register) src).toString().equals("%esp")) {
							rule.setSEH(curState);
						}
					}
				} else {
					X86MemoryOperand t = env.getMemory().evaluateAddress(
							(X86MemoryOperand) dest, env);

					if (!rule.checkAddressValid(env, t)) {
						// SEH Exploit
						return rule.processSEH(curState);
					}
					
					rule.setValueOperand(dest, source, env, inst);
				}
			} else {
				// PHONG - 20150422
				if (source instanceof LongValue)
					rule.setValueOperand(dest, source, env, inst);
				else
					rule.setValueOperand(dest, new LongValue(0), env, inst);
			}

		} else if (inst.getName().startsWith("xchg")) {
			// normal move
			Value temp = rule.getValueOperand(dest, env, inst);
			Value source = rule.getValueOperand(src, env, inst);
			if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
				X86MemoryOperand t = env.getMemory().evaluateAddress(
						(X86MemoryOperand) dest, env);

				if (!rule.checkAddressValid(env, t)) {
					// SEH Exploit
					return rule.processSEH(curState);
				}
			}
			rule.setValueOperand(dest, source, env, inst);
			rule.setValueOperand(src, temp, env, inst);
		} else {
			Program.getProgram().getLog().error("Instruction not supported" + inst + " at "
				+ curState.getLocation());
		} 

		rule.generateNextInstruction(inst, path, pathList, true);
		return curState;
	}

	

	
	

}
