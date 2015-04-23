package v2.org.analysis.transition_rule;

import org.jakstab.Program;
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
import v2.org.analysis.value.*;

import java.util.Calendar;
import java.util.List;

public class X86InstructionInterpreter {
	public BPState execute(X86Instruction inst, BPPath path,
			List<BPPath> pathList, X86TransitionRule rule) {
		// TODO Auto-generated method stub
		BPState curState = path.getCurrentState();
		Operand dest = inst.getOperand1();
		Operand src = inst.getOperand2();
		Environment env = curState.getEnvironement();
		// Bien dung de xu ly CMOVcc
		boolean isSet_CMOVcc = false; // Xet dieu kien cua CMOVcc
		long temp_s;
		Value d = null, s = null;
		int opSize = rule.getBitCount(inst);
		// System.out.println("Instruction: " + inst.getName());
		if (inst.getName().startsWith("int3")) {
			if (env.getSystem().getSEHHandler().isSet())
				return rule.processSEH(curState);
			else
				Program.getProgram().getLog().debugString("Not processed int3 at " + curState.getLocation());
		} else
		// Hai: Check later
		if (inst.getName().startsWith("cmovne")
				|| inst.getName().equals("cmovnz")) {

			if (env.getFlag().getZFlag().equal(new BooleanValue(0))) {
				isSet_CMOVcc = true;
			}
		}

		// dieu kien ZF = 1 or CF = 1
		else if (inst.getName().startsWith("cmovbe")
				|| inst.getName().equals("cmovna")) {

			if (env.getFlag().getCFlag().equal(new BooleanValue(1))
					|| env.getFlag().getZFlag().equal(new BooleanValue(1))) {
				isSet_CMOVcc = true;
			}
		}

		// dieu kien ZF = 0 and CF = 0
		else if (inst.getName().startsWith("cmova")
				|| inst.getName().equals("cmovnbe")) {

			if (env.getFlag().getCFlag().equal(new BooleanValue(0))
					&& env.getFlag().getZFlag().equal(new BooleanValue(0))) {
				isSet_CMOVcc = true;
			}
		}

		// dieu kien: OF = 0;
		else if (inst.getName().startsWith("cmovno")) {

			if (env.getFlag().getOFlag().equal(new BooleanValue(0))) {
				isSet_CMOVcc = true;
			}
		}

		// dieu kien: SF = 0;
		else if (inst.getName().startsWith("cmovns")) {

			if (env.getFlag().getSFlag().equal(new BooleanValue(0))) {
				isSet_CMOVcc = true;
			}
		}

		// dieu kien: PF = 0;
		else if (inst.getName().startsWith("cmovnp")
				|| inst.getName().equals("cmovpo")) {

			if (env.getFlag().getPFlag().equal(new BooleanValue(0))) {
				isSet_CMOVcc = true;
			}
		}

		// dieu kien: ZF = 1;
		else if (inst.getName().startsWith("cmove")
				|| inst.getName().equals("cmovz")) {

			if (env.getFlag().getZFlag().equal(new BooleanValue(1))) {
				isSet_CMOVcc = true;
			}
		}

		// dieu kien: CF = 1;
		else if (inst.getName().startsWith("cmovb")
				|| inst.getName().equals("cmovc")
				|| inst.getName().equals("cmovnae")) {

			if (env.getFlag().getCFlag().equal(new BooleanValue(1))) 
{
				isSet_CMOVcc = true;
			}
		}
		// dieu kien: PF = 1;
		else if (inst.getName().startsWith("cmovp")
				|| inst.getName().equals("cmovpe")) {

			if (env.getFlag().getPFlag().equal(new BooleanValue(1))) {
				isSet_CMOVcc = true;
			}
		}

		// dieu kien: SF = OF;
		else if (inst.getName().startsWith("cmovge")
				|| inst.getName().equals("cmovnl")) {

			boolean SF, OF;

			Value sFlag = env.getFlag().getSFlag();
			sFlag = sFlag.evaluate(sFlag.getValueMap());
			SF = ((BooleanValue) sFlag).getValue();

			Value oFlag = env.getFlag().getOFlag();
			oFlag = oFlag.evaluate(oFlag.getValueMap());
			OF = ((BooleanValue) oFlag).getValue();

			if (SF == OF) {
				isSet_CMOVcc = true;
			}
		}

		// dieu kien: ZF = 0 and SF = OF;
		else if (inst.getName().startsWith("cmovg")
				|| inst.getName().equals("cmovnle")) {

			boolean ZF, SF, OF;

			Value zFlag = env.getFlag().getZFlag();
			zFlag = zFlag.evaluate(zFlag.getValueMap());
			ZF = ((BooleanValue) zFlag).getValue();

			Value sFlag = env.getFlag().getSFlag();
			sFlag = sFlag.evaluate(sFlag.getValueMap());
			SF = ((BooleanValue) sFlag).getValue();

			Value oFlag = env.getFlag().getOFlag();
			oFlag = oFlag.evaluate(oFlag.getValueMap());
			OF = ((BooleanValue) oFlag).getValue();

			if ((ZF == false) && (SF == OF)) {
				isSet_CMOVcc = true;
			}
		}

		// dieu kien: ZF = 1 or SF != OF;
		else if (inst.getName().startsWith("cmovle")
				|| inst.getName().equals("cmovng")) {

			boolean ZF, SF, OF;

			Value zFlag = env.getFlag().getZFlag();
			zFlag = zFlag.evaluate(zFlag.getValueMap());
			ZF = ((BooleanValue) zFlag).getValue();

			Value sFlag = env.getFlag().getSFlag();
			sFlag = sFlag.evaluate(sFlag.getValueMap());
			SF = ((BooleanValue) sFlag).getValue();

			Value oFlag = env.getFlag().getOFlag();
			oFlag = oFlag.evaluate(oFlag.getValueMap());
			OF = ((BooleanValue) oFlag).getValue();

			if ((ZF == true) || (SF != OF)) {
				isSet_CMOVcc = true;
			}
		}

		// dieu kien: SF != OF;
		else if (inst.getName().startsWith("cmovl")
				|| inst.getName().equals("cmovnge")) {

			boolean SF, OF;

			Value sFlag = env.getFlag().getSFlag();
			sFlag = sFlag.evaluate(sFlag.getValueMap());
			SF = ((BooleanValue) sFlag).getValue();

			Value oFlag = env.getFlag().getOFlag();
			oFlag = oFlag.evaluate(oFlag.getValueMap());
			OF = ((BooleanValue) oFlag).getValue();

			if (SF != OF) {
				isSet_CMOVcc = true;
			}
		}

		// dieu kien: OF = 1;
		else if (inst.getName().startsWith("cmovo")) {

			if (env.getFlag().getOFlag().equal(new BooleanValue(1))) {
				isSet_CMOVcc = true;
			}
		}

		// dieu kien: SF = 1;
		else if (inst.getName().startsWith("cmovs")) {

			if (env.getFlag().getSFlag().equal(new BooleanValue(1))) {
				isSet_CMOVcc = true;
			}
		}

		if (isSet_CMOVcc == true) {
			if (src.getClass().getSimpleName().equals("X86Register")
					|| src.getClass().getSimpleName().equals("X86RegisterPart")
					|| src.getClass().getSimpleName()
							.equals("X86SegmentRegister")) {
				s = env.getRegister().getRegisterValue(src.toString());
			} else if (src.getClass().getSimpleName()
					.equals("X86MemoryOperand")) {
				X86MemoryOperand t = env.getMemory().evaluateAddress(
						(X86MemoryOperand) src, env);

				if (!rule.checkAddressValid(env, t)) {
					// SEH Exploit
					System.out.println("SEH:"
							+ path.getCurrentState().getLocation().toString());
					return rule.processSEH(path.getCurrentState());
				}

				s = env.getMemory()
						.getMemoryValue((X86MemoryOperand) src, inst);
			}

			temp_s = ((LongValue) s).getValue();
			env.getRegister().setRegisterValue(dest.toString(),
					new LongValue(temp_s));
			isSet_CMOVcc = false;
		}  	// SETE: ZF=1 => dest = 1 else dest = 0
		// SETZ: ZF=1 => dest = 1 else dest = 0
		else if ((inst.getName().startsWith("sete"))
				|| (inst.getName().startsWith("setz"))) {

			Value zFlag = env.getFlag().getZFlag();
			zFlag = zFlag.evaluate(zFlag.getValueMap());

			if (zFlag instanceof BooleanValue) {
				boolean isSet = ((BooleanValue) zFlag).getValue();
				if (dest.getClass().getSimpleName().equals("X86Register")
						|| dest.getClass().getSimpleName().equals("X86RegisterPart")
						|| dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					env.getRegister().setRegisterValue(dest.toString(),
							((isSet) ? new LongValue(1) : new LongValue(0)));
				} else if (dest.getClass().getSimpleName()
						.equals("X86MemoryOperand")) {
					X86MemoryOperand t = env.getMemory().evaluateAddress((X86MemoryOperand) dest, env);
					if (!rule.checkAddressValid(env, t)) {
						// SEH Exploit
						System.out.println("SEH:"
								+ path.getCurrentState().getLocation()
										.toString());
						return rule.processSEH(path.getCurrentState());
					}
					env.getMemory().setByteMemoryValue((X86MemoryOperand) dest,
							((isSet) ? new LongValue(1) : new LongValue(0)));
				}
			}
		}
		
		// SETAE: CF = 0 => dest = 1 else dest = 0
		// SETNB: CF = 0 => dest = 1 else dest = 0
		// SETNC: CF = 0 => dest = 1 else dest = 0
		else if ((inst.getName().startsWith("setae"))
				|| (inst.getName().startsWith("setnb"))
				|| (inst.getName().startsWith("setnc"))) {

			boolean isSet = false;
			if (env.getFlag().getCFlag().equal(new BooleanValue(0))) {
				isSet = true;
			}
			if (dest.getClass().getSimpleName().equals("X86Register")
					|| dest.getClass().getSimpleName()
							.equals("X86RegisterPart")
					|| dest.getClass().getSimpleName()
							.equals("X86SegmentRegister")) {
				env.getRegister().setRegisterValue(dest.toString(),
						((isSet) ? new LongValue(1) : new LongValue(0)));

			} else if (dest.getClass().getSimpleName()
					.equals("X86MemoryOperand")) {
				X86MemoryOperand t = env.getMemory().evaluateAddress(
						(X86MemoryOperand) dest, env);
				if (!rule.checkAddressValid(env, t)) {
					// SEH Exploit
					System.out.println("SEH:"
							+ path.getCurrentState().getLocation().toString());
					return rule.processSEH(path.getCurrentState());
				}
				env.getMemory().setByteMemoryValue((X86MemoryOperand) dest,
						((isSet) ? new LongValue(1) : new LongValue(0)));
			}
		}
		
		// SETA: (CF = 0 && ZF = 0) => dest = 1 else dest = 0
		// SETNBE: CF=0 and ZF=0 => dest = 1 else dest = 0
		else if ((inst.getName().startsWith("seta"))
				|| (inst.getName().startsWith("setnbe"))) {

			boolean isSet = false;
			if (env.getFlag().getZFlag().equal(new BooleanValue(0))
					&& env.getFlag().getCFlag().equal(new BooleanValue(0))) {
				isSet = true;
			}
			if (dest.getClass().getSimpleName().equals("X86Register")
					|| dest.getClass().getSimpleName()
							.equals("X86RegisterPart")
					|| dest.getClass().getSimpleName()
							.equals("X86SegmentRegister")) {
				env.getRegister().setRegisterValue(dest.toString(),
						((isSet) ? new LongValue(1) : new LongValue(0)));

			} else if (dest.getClass().getSimpleName()
					.equals("X86MemoryOperand")) {
				X86MemoryOperand t = env.getMemory().evaluateAddress(
						(X86MemoryOperand) dest, env);
				if (!rule.checkAddressValid(env, t)) {
					// SEH Exploit
					System.out.println("SEH:"
							+ path.getCurrentState().getLocation().toString());
					return rule.processSEH(path.getCurrentState());
				}
				env.getMemory().setByteMemoryValue((X86MemoryOperand) dest,
						((isSet) ? new LongValue(1) : new LongValue(0)));
			}
		}
		
		// SETBE: CF = 1 or ZF = 1 => dest = 1 else dest  = 0
		// SETNA: CF = 1 or Z F= 1 => dest = 1 else dest  = 0
		else if ((inst.getName().startsWith("setbe"))
				|| (inst.getName().startsWith("setna"))) {

			boolean isSet = false;
			if (env.getFlag().getCFlag().equal(new BooleanValue(1))
					|| env.getFlag().getZFlag().equal(new BooleanValue(1))) {
				isSet = true;
			}
			if (dest.getClass().getSimpleName().equals("X86Register")
					|| dest.getClass().getSimpleName()
							.equals("X86RegisterPart")
					|| dest.getClass().getSimpleName()
							.equals("X86SegmentRegister")) {
				env.getRegister().setRegisterValue(dest.toString(),
						((isSet) ? new LongValue(1) : new LongValue(0)));

			} else if (dest.getClass().getSimpleName()
					.equals("X86MemoryOperand")) {
				X86MemoryOperand t = env.getMemory().evaluateAddress(
						(X86MemoryOperand) dest, env);
				if (!rule.checkAddressValid(env, t)) {
					// SEH Exploit
					System.out.println("SEH:"
							+ path.getCurrentState().getLocation().toString());
					return rule.processSEH(path.getCurrentState());
				}
				env.getMemory().setByteMemoryValue((X86MemoryOperand) dest,
						((isSet) ? new LongValue(1) : new LongValue(0)));
			}
		}
		
		// SETB:   CF = 1 => dest = 1 else dest = 0
		// SETC:   CF = 1 => dest = 1 else dest = 0
		// SETNAE: CF = 1 => dest = 1 else dest = 0
		else if ((inst.getName().startsWith("setb"))
				|| (inst.getName().startsWith("setc"))
				|| (inst.getName().startsWith("setnae"))) {

			boolean isSet = false;
			if (env.getFlag().getCFlag().equal(new BooleanValue(1))) {
				isSet = true;
			}
			if (dest.getClass().getSimpleName().equals("X86Register")
					|| dest.getClass().getSimpleName()
							.equals("X86RegisterPart")
					|| dest.getClass().getSimpleName()
							.equals("X86SegmentRegister")) {
				env.getRegister().setRegisterValue(dest.toString(),
						((isSet) ? new LongValue(1) : new LongValue(0)));

			} else if (dest.getClass().getSimpleName()
					.equals("X86MemoryOperand")) {
				X86MemoryOperand t = env.getMemory().evaluateAddress(
						(X86MemoryOperand) dest, env);
				if (!rule.checkAddressValid(env, t)) {
					// SEH Exploit
					System.out.println("SEH:"
							+ path.getCurrentState().getLocation().toString());
					return rule.processSEH(path.getCurrentState());
				}
				env.getMemory().setByteMemoryValue((X86MemoryOperand) dest,
						((isSet) ? new LongValue(1) : new LongValue(0)));
			}
		}
		
		// SETGE: SF = OF => dest = 1 else dest = 0
		// SETNL: SF = OF => dest =1 else dest = 0
		else if ((inst.getName().startsWith("setge"))
				|| (inst.getName().startsWith("setnl"))) {
			boolean isSet = false;
			boolean SF, OF;

			Value sFlag = env.getFlag().getSFlag();
			sFlag = sFlag.evaluate(sFlag.getValueMap());
			SF = ((BooleanValue) sFlag).getValue();

			Value oFlag = env.getFlag().getOFlag();
			oFlag = oFlag.evaluate(oFlag.getValueMap());
			OF = ((BooleanValue) oFlag).getValue();

			if (SF == OF)
				isSet = true;

			if (dest.getClass().getSimpleName().equals("X86Register")
					|| dest.getClass().getSimpleName()
							.equals("X86RegisterPart")
					|| dest.getClass().getSimpleName()
							.equals("X86SegmentRegister")) {
				env.getRegister().setRegisterValue(dest.toString(),
						((isSet) ? new LongValue(1) : new LongValue(0)));

			} else if (dest.getClass().getSimpleName()
					.equals("X86MemoryOperand")) {
				X86MemoryOperand t = env.getMemory().evaluateAddress(
						(X86MemoryOperand) dest, env);
				if (!rule.checkAddressValid(env, t)) {
					// SEH Exploit
					System.out.println("SEH:"
							+ path.getCurrentState().getLocation().toString());
					return rule.processSEH(path.getCurrentState());
				}
				env.getMemory().setByteMemoryValue((X86MemoryOperand) dest,
						((isSet) ? new LongValue(1) : new LongValue(0)));
			}

		}
		
		// SETG:   ZF=0 and SF=OF => dest = 1 else dest = 0
		// SETNLE: ZF=0 and SF=OF => dest = 1 else dest = 0
		else if ((inst.getName().startsWith("setg"))
				|| (inst.getName().startsWith("setnle"))) {
			boolean isSet = false;
			boolean ZF, SF, OF;

			Value zFlag = env.getFlag().getZFlag();
			zFlag = zFlag.evaluate(zFlag.getValueMap());
			ZF = ((BooleanValue) zFlag).getValue();

			Value sFlag = env.getFlag().getSFlag();
			sFlag = sFlag.evaluate(sFlag.getValueMap());
			SF = ((BooleanValue) sFlag).getValue();

			Value oFlag = env.getFlag().getOFlag();
			oFlag = oFlag.evaluate(oFlag.getValueMap());
			OF = ((BooleanValue) oFlag).getValue();

			if ((ZF == false) && (SF == OF))
				isSet = true;

			if (dest.getClass().getSimpleName().equals("X86Register")
					|| dest.getClass().getSimpleName()
							.equals("X86RegisterPart")
					|| dest.getClass().getSimpleName()
							.equals("X86SegmentRegister")) {
				env.getRegister().setRegisterValue(dest.toString(),
						((isSet) ? new LongValue(1) : new LongValue(0)));

			} else if (dest.getClass().getSimpleName()
					.equals("X86MemoryOperand")) {
				X86MemoryOperand t = env.getMemory().evaluateAddress(
						(X86MemoryOperand) dest, env);
				if (!rule.checkAddressValid(env, t)) {
					// SEH Exploit
					System.out.println("SEH:"
							+ path.getCurrentState().getLocation().toString());
					return rule.processSEH(path.getCurrentState());
				}
				env.getMemory().setByteMemoryValue((X86MemoryOperand) dest,
						((isSet) ? new LongValue(1) : new LongValue(0)));
			}

		}
		
		// SETLE: ZF=1 or SF<>OF => dest = 1 else dest = 0
		// SETNG: ZF=1 or SF<>OF => dest = 1 else dest = 0
		else if ((inst.getName().startsWith("setle"))
				|| (inst.getName().startsWith("setng"))) {
			boolean isSet = false;
			boolean ZF, SF, OF;

			Value zFlag = env.getFlag().getZFlag();
			zFlag = zFlag.evaluate(zFlag.getValueMap());
			ZF = ((BooleanValue) zFlag).getValue();

			Value sFlag = env.getFlag().getSFlag();
			sFlag = sFlag.evaluate(sFlag.getValueMap());
			SF = ((BooleanValue) sFlag).getValue();

			Value oFlag = env.getFlag().getOFlag();
			oFlag = oFlag.evaluate(oFlag.getValueMap());
			OF = ((BooleanValue) oFlag).getValue();

			if ((ZF == true) || (SF != OF))
				isSet = true;

			if (dest.getClass().getSimpleName().equals("X86Register")
					|| dest.getClass().getSimpleName()
							.equals("X86RegisterPart")
					|| dest.getClass().getSimpleName()
							.equals("X86SegmentRegister")) {
				env.getRegister().setRegisterValue(dest.toString(),
						((isSet) ? new LongValue(1) : new LongValue(0)));

			} else if (dest.getClass().getSimpleName()
					.equals("X86MemoryOperand")) {
				X86MemoryOperand t = env.getMemory().evaluateAddress(
						(X86MemoryOperand) dest, env);
				if (!rule.checkAddressValid(env, t)) {
					// SEH Exploit
					System.out.println("SEH:"
							+ path.getCurrentState().getLocation().toString());
					return rule.processSEH(path.getCurrentState());
				}
				env.getMemory().setByteMemoryValue((X86MemoryOperand) dest,
						((isSet) ? new LongValue(1) : new LongValue(0)));
			}
		}
		
		// SETL:   SF<>OF => dest = 1 else dest = 0
		// SETNGE: SF<>OF => dest = 1 else dest = 0
		else if ((inst.getName().startsWith("setl"))
				|| (inst.getName().startsWith("setnge"))) {
			boolean isSet = false;
			boolean SF, OF;

			Value sFlag = env.getFlag().getSFlag();
			sFlag = sFlag.evaluate(sFlag.getValueMap());
			SF = ((BooleanValue) sFlag).getValue();

			Value oFlag = env.getFlag().getOFlag();
			oFlag = oFlag.evaluate(oFlag.getValueMap());
			OF = ((BooleanValue) oFlag).getValue();

			if (SF != OF)
				isSet = true;

			if (dest.getClass().getSimpleName().equals("X86Register")
					|| dest.getClass().getSimpleName()
							.equals("X86RegisterPart")
					|| dest.getClass().getSimpleName()
							.equals("X86SegmentRegister")) {
				env.getRegister().setRegisterValue(dest.toString(),
						((isSet) ? new LongValue(1) : new LongValue(0)));

			} else if (dest.getClass().getSimpleName()
					.equals("X86MemoryOperand")) {
				X86MemoryOperand t = env.getMemory().evaluateAddress(
						(X86MemoryOperand) dest, env);
				if (!rule.checkAddressValid(env, t)) {
					// SEH Exploit
					System.out.println("SEH:"
							+ path.getCurrentState().getLocation().toString());
					return rule.processSEH(path.getCurrentState());
				}
				env.getMemory().setByteMemoryValue((X86MemoryOperand) dest,
						((isSet) ? new LongValue(1) : new LongValue(0)));
			}
		}

		// SETNE: ZF=0 => dest = 1 else dest = 0
		// SETNZ ZF=0 => dest = 1 else dest = 0
		else if ((inst.getName().startsWith("setne"))
				|| (inst.getName().startsWith("setnz"))) {

			boolean isSet = false;
			if (env.getFlag().getZFlag().equal(new BooleanValue(0))) {
				isSet = true;
			}
			if (dest.getClass().getSimpleName().equals("X86Register")
					|| dest.getClass().getSimpleName()
							.equals("X86RegisterPart")
					|| dest.getClass().getSimpleName()
							.equals("X86SegmentRegister")) {
				env.getRegister().setRegisterValue(dest.toString(),
						((isSet) ? new LongValue(1) : new LongValue(0)));

			} else if (dest.getClass().getSimpleName()
					.equals("X86MemoryOperand")) {
				X86MemoryOperand t = env.getMemory().evaluateAddress(
						(X86MemoryOperand) dest, env);
				if (!rule.checkAddressValid(env, t)) {
					// SEH Exploit
					System.out.println("SEH:"
							+ path.getCurrentState().getLocation().toString());
					return rule.processSEH(path.getCurrentState());
				}
				env.getMemory().setByteMemoryValue((X86MemoryOperand) dest,
						((isSet) ? new LongValue(1) : new LongValue(0)));
			}
		}
		
		// SETNP : PF=0 => dest = 1 else dest = 0
		// SETPO : PE = 0 => dest = 1 else dest = 0
		else if (inst.getName().startsWith("setnp")
				|| inst.getName().startsWith("setpo")) {

			boolean isSet = false;
			if (env.getFlag().getPFlag().equal(new BooleanValue(0))) {
				isSet = true;
			}
			if (dest.getClass().getSimpleName().equals("X86Register")
					|| dest.getClass().getSimpleName()
							.equals("X86RegisterPart")
					|| dest.getClass().getSimpleName()
							.equals("X86SegmentRegister")) {
				env.getRegister().setRegisterValue(dest.toString(),
						((isSet) ? new LongValue(1) : new LongValue(0)));

			} else if (dest.getClass().getSimpleName()
					.equals("X86MemoryOperand")) {
				X86MemoryOperand t = env.getMemory().evaluateAddress(
						(X86MemoryOperand) dest, env);
				if (!rule.checkAddressValid(env, t)) {
					// SEH Exploit
					System.out.println("SEH:"
							+ path.getCurrentState().getLocation().toString());
					return rule.processSEH(path.getCurrentState());
				}
				env.getMemory().setByteMemoryValue((X86MemoryOperand) dest,
						((isSet) ? new LongValue(1) : new LongValue(0)));
			}
		}
		
		// SETPE PF=1 => dest = 1 else dest = 0
		// SETP PF=1 => dest = 1 else dest = 0
		else if ((inst.getName().startsWith("setp"))
				|| (inst.getName().startsWith("setpe"))) {

			boolean isSet = false;
			if (env.getFlag().getPFlag().equal(new BooleanValue(1))) {
				isSet = true;
			}
			if (dest.getClass().getSimpleName().equals("X86Register")
					|| dest.getClass().getSimpleName()
							.equals("X86RegisterPart")
					|| dest.getClass().getSimpleName()
							.equals("X86SegmentRegister")) {
				env.getRegister().setRegisterValue(dest.toString(),
						((isSet) ? new LongValue(1) : new LongValue(0)));

			} else if (dest.getClass().getSimpleName()
					.equals("X86MemoryOperand")) {
				X86MemoryOperand t = env.getMemory().evaluateAddress(
						(X86MemoryOperand) dest, env);
				if (!rule.checkAddressValid(env, t)) {
					// SEH Exploit
					System.out.println("SEH:"
							+ path.getCurrentState().getLocation().toString());
					return rule.processSEH(path.getCurrentState());
				}
				env.getMemory().setByteMemoryValue((X86MemoryOperand) dest,
						((isSet) ? new LongValue(1) : new LongValue(0)));
			}
				}
		
		// SETNO: OF=0 => dest = 1 else dest = 0
		else if (inst.getName().startsWith("setno")) {

			boolean isSet = false;
			if (env.getFlag().getOFlag().equal(new BooleanValue(0))) {
				isSet = true;
			}
			if (dest.getClass().getSimpleName().equals("X86Register")
					|| dest.getClass().getSimpleName()
							.equals("X86RegisterPart")
					|| dest.getClass().getSimpleName()
							.equals("X86SegmentRegister")) {
				env.getRegister().setRegisterValue(dest.toString(),
						((isSet) ? new LongValue(1) : new LongValue(0)));

			} else if (dest.getClass().getSimpleName()
					.equals("X86MemoryOperand")) {
				X86MemoryOperand t = env.getMemory().evaluateAddress(
						(X86MemoryOperand) dest, env);
				if (!rule.checkAddressValid(env, t)) {
					// SEH Exploit
					System.out.println("SEH:"
							+ path.getCurrentState().getLocation().toString());
					return rule.processSEH(path.getCurrentState());
				}
				env.getMemory().setByteMemoryValue((X86MemoryOperand) dest,
						((isSet) ? new LongValue(1) : new LongValue(0)));
			}
		}
				
		// SETNS SF=0 => dest = 1 else dest = 0
		else if (inst.getName().startsWith("setns")) {

			boolean isSet = false;
			if (env.getFlag().getSFlag().equal(new BooleanValue(0))) {
				isSet = true;
			}
			if (dest.getClass().getSimpleName().equals("X86Register")
					|| dest.getClass().getSimpleName()
							.equals("X86RegisterPart")
					|| dest.getClass().getSimpleName()
							.equals("X86SegmentRegister")) {
				env.getRegister().setRegisterValue(dest.toString(),
						((isSet) ? new LongValue(1) : new LongValue(0)));

			} else if (dest.getClass().getSimpleName()
					.equals("X86MemoryOperand")) {
				X86MemoryOperand t = env.getMemory().evaluateAddress(
						(X86MemoryOperand) dest, env);
				if (!rule.checkAddressValid(env, t)) {
					// SEH Exploit
					System.out.println("SEH:"
							+ path.getCurrentState().getLocation().toString());
					return rule.processSEH(path.getCurrentState());
				}
				env.getMemory().setByteMemoryValue((X86MemoryOperand) dest,
						((isSet) ? new LongValue(1) : new LongValue(0)));
			}
		}
		
		// SETO OF = 1 => dest = 1
		else if (inst.getName().startsWith("seto")) {

			boolean isSet = false;
			if (env.getFlag().getOFlag().equal(new BooleanValue(1))) {
				isSet = true;
			}
			if (dest.getClass().getSimpleName().equals("X86Register")
					|| dest.getClass().getSimpleName()
							.equals("X86RegisterPart")
					|| dest.getClass().getSimpleName()
							.equals("X86SegmentRegister")) {
				env.getRegister().setRegisterValue(dest.toString(),
						((isSet) ? new LongValue(1) : new LongValue(0)));

			} else if (dest.getClass().getSimpleName()
					.equals("X86MemoryOperand")) {
				X86MemoryOperand t = env.getMemory().evaluateAddress(
						(X86MemoryOperand) dest, env);
				if (!rule.checkAddressValid(env, t)) {
					// SEH Exploit
					System.out.println("SEH:"
							+ path.getCurrentState().getLocation().toString());
					return rule.processSEH(path.getCurrentState());
				}
				env.getMemory().setByteMemoryValue((X86MemoryOperand) dest,
						((isSet) ? new LongValue(1) : new LongValue(0)));
			}
		}
		
		// SETS: SF=1 => dest = 1
		else if (inst.getName().startsWith("sets")) {

			boolean isSet = false;
			if (env.getFlag().getSFlag().equal(new BooleanValue(1))) {
				isSet = true;
			}
			if (dest.getClass().getSimpleName().equals("X86Register")
					|| dest.getClass().getSimpleName()
							.equals("X86RegisterPart")
					|| dest.getClass().getSimpleName()
							.equals("X86SegmentRegister")) {
				env.getRegister().setRegisterValue(dest.toString(),
						((isSet) ? new LongValue(1) : new LongValue(0)));

			} else if (dest.getClass().getSimpleName()
					.equals("X86MemoryOperand")) {
				X86MemoryOperand t = env.getMemory().evaluateAddress(
						(X86MemoryOperand) dest, env);
				if (!rule.checkAddressValid(env, t)) {
					// SEH Exploit
					System.out.println("SEH:"
							+ path.getCurrentState().getLocation().toString());
					return rule.processSEH(path.getCurrentState());
				}
				env.getMemory().setByteMemoryValue((X86MemoryOperand) dest,
						((isSet) ? new LongValue(1) : new LongValue(0)));
			}
		}
		else if (inst.getName().startsWith("cwt")
				|| inst.getName().startsWith("cbw")
		   		||inst.getName().startsWith("cwde")) {
			if (opSize == 16) {
				Value al = env.getRegister().getRegisterValue("al");
				// Value ah = env.getRegister().getRegisterValue("ah");
				if (al instanceof LongValue) {
					long tempal = ((LongValue) al).getValue();
					// tempax = 21645;
					//int t = (int) (tempal / Math.pow(2, 7));
					long ax = BitVector.signExtend(tempal, opSize);
					//long ax = (long) (tempal + t * 4294901760l);
					// Change flag aad. flag SF, ZF, PF
					env.getRegister().setRegisterValue("ax", new LongValue(ax));
				}
			} else if (opSize == 32) {
				Value ax = env.getRegister().getRegisterValue("ax");
				// Value ah = env.getRegister().getRegisterValue("ah");
				if (ax instanceof LongValue) {
					long tempax = ((LongValue) ax).getValue();
					// tempax = 21645;
					long eax = BitVector.signExtend(tempax, opSize);
					// Change flag aad. flag SF, ZF, PF
					env.getRegister().setRegisterValue("eax", new LongValue(eax));
				}
			}
		} else
		// Khanh: Check later
		if (inst.getName().startsWith("aad")) {
			Value al = env.getRegister().getRegisterValue("al");
			Value ah = env.getRegister().getRegisterValue("ah");
			if (al instanceof LongValue && ah instanceof LongValue) {
				long tempal = ((LongValue) al).getValue();
				long tempah = ((LongValue) ah).getValue();
				long temp_al = tempal + (tempah * 10);
				long temp_ah = 0;
				env.getRegister()
						.setRegisterValue("al", new LongValue(temp_al));
				env.getRegister()
						.setRegisterValue("ah", new LongValue(temp_ah));
				// Change flag aad. flag SF, ZF, PF

				long t = Convert.convertSignedValue(temp_al, opSize);
				env.getFlag().setPFlag(new BooleanValue(BitVector.getParityBit(t)));
				env.getFlag().setSFlag(new BooleanValue(t < 0));
				env.getFlag().setZFlag(new BooleanValue(t == 0));
			}
		}
		// Khanh: Check later
		else if (inst.getName().startsWith("aas")) {
			Value AL = env.getRegister().getRegisterValue("al");
			Value AF = env.getFlag().getAFlag();
			boolean isCF = false;
			if (((LongValue) AL).getValue() > 9
					|| (AF instanceof BooleanValue && ((BooleanValue) AF)
							.getValue()))
				isCF = true;

			if (isCF) {
				Long t = ((LongValue) AL).getValue() - 6;
				env.getRegister()
						.setRegisterValue("al", new LongValue(t & 0xF));
				env.getRegister().sub("ah", new LongValue(1));
			}
			env.getFlag().setCFlag(new BooleanValue(isCF));
			env.getFlag().setAFlag(new BooleanValue(isCF));
		} else if (inst.getName().startsWith("clc")) {
			env.getFlag().setCFlag(new BooleanValue(false));
		} else if (inst.getName().startsWith("popa")) {
			//System.out.println("Process " + inst.getName());
			Value edi = env.getStack().pop();
			if (edi == null)
				edi = new SymbolValue("edi");
			
			Value esi = env.getStack().pop();
			if (esi == null)
				esi = new SymbolValue("esi");
			
			Value ebp = env.getStack().pop();
			if (ebp == null)
				ebp = new SymbolValue("ebp");
			
			Value esp = env.getStack().pop();
			if (esp == null)
				esp = new SymbolValue("esp");
			
			Value ebx = env.getStack().pop();
			if (ebx == null)
				ebx = new SymbolValue("ebx");
			
			Value edx = env.getStack().pop();
			if (edx == null)
				edx = new SymbolValue("edx");
			
			Value ecx = env.getStack().pop();
			if (ecx == null)
				ecx = new SymbolValue("ecx");
			
			Value eax = env.getStack().pop();
			if (eax == null)
				eax = new SymbolValue("eax");

			env.getRegister().setRegisterValue("edi", edi);
			env.getRegister().setRegisterValue("esi", esi);
			env.getRegister().setRegisterValue("ebp", ebp);
			// env.getRegister().setRegisterValue("esp", esp);
			env.getRegister().setRegisterValue("ebx", ebx);
			env.getRegister().setRegisterValue("edx", edx);
			env.getRegister().setRegisterValue("ecx", ecx);
			env.getRegister().setRegisterValue("eax", eax);

			Program.getProgram().getLog().debugString("Restore value of Register: EAX="
					+ eax.toString() + " EBX=" + ebx.toString() + " ECX="
					+ ecx.toString() + " EDX=" + edx.toString() + " ESI="
					+ esi.toString() + " EDI=" + edi.toString() + " ESP="
					+ esp.toString() + " EBP=" + ebp.toString());
			// Program.getProgram().generageCFG(
			// Program.getProgram().getAbsolutePathFile() + "_test");
		} else if (inst.getName().startsWith("popf")) {
			//System.out.println("Process " + inst.getName());
			Value v = env.getStack().pop();
			System.out.println("Restore value of Flags: " + v.toString());
		} else if (inst.getName().startsWith("cmps")) {
			opSize = rule.getBitCount(inst) / 8;
			if (inst.hasPrefixREPZ()) {
				// System.out.println("Debug Instruction REPZ:" +
				// inst.getName());

				if (dest.getClass().getSimpleName().equals("X86MemoryOperand")
						&& src.getClass().getSimpleName()
								.equals("X86MemoryOperand")) {
					X86MemoryOperand edi = env.getMemory().evaluateAddress(
							(X86MemoryOperand) dest, env);
					X86MemoryOperand esi = env.getMemory().evaluateAddress(
							(X86MemoryOperand) src, env);

					// Comapre between EDI and ESI, if not true, increase ESI
					// and
					// EDI by 1
					Value ecx = env.getRegister().getRegisterValue("%ecx");

					if (ecx instanceof LongValue) {
						int j = 0;
						boolean zf = true;
						int t = (int) ((LongValue) ecx).getValue();
						for (int i = 0; i < t; i++) {
							Value ediValue = env.getMemory().getMemoryValue(new X86MemoryOperand(edi.getDataType(),
									edi.getDisplacement() + i * opSize), inst);
							Value esiValue = env.getMemory().getMemoryValue(new X86MemoryOperand(esi.getDataType(),
									esi.getDisplacement() + i * opSize), inst);
							// PHONG: fix here
							env.getFlag().changeFlagWithSUB(esiValue, ediValue,
									env, rule.getBitCount(inst));
							j++;
							if (!ediValue.equal(esiValue)) {
								// env.getFlag().setZFlag(new
								// BooleanValue(false));
								zf = false;
								break;
							}
						}
						env.getFlag().setZFlag(new BooleanValue(zf));
						if (((BooleanValue) env.getFlag().getDFlag())
								.getValue() == false) {
							env.getRegister().add("esi", j * opSize);
							env.getRegister().add("edi", j * opSize);
						} else {
							env.getRegister().sub("esi", j * opSize);
							env.getRegister().sub("edi", j * opSize);
						}
						env.getRegister().sub("ecx", j);
					}
				}
			} else if (inst.hasPrefixREPNZ()) {
				//System.out.println("Debug Instruction REPNZ" + inst.getName());
				if (dest.getClass().getSimpleName().equals("X86MemoryOperand")
						&& src.getClass().getSimpleName()
						.equals("X86MemoryOperand")) {
					X86MemoryOperand edi = env.getMemory().evaluateAddress(
							(X86MemoryOperand) dest, env);
					X86MemoryOperand esi = env.getMemory().evaluateAddress(
							(X86MemoryOperand) src, env);

					// Comapre between EDI and ESI, if not true, increase ESI
					// and
					// EDI by 1
					Value ecx = env.getRegister().getRegisterValue("%ecx");

					if (ecx instanceof LongValue) {
						int j = 0;
						boolean zf = true;
						int t = (int) ((LongValue) ecx).getValue();
						for (int i = 0; i < t; i++) {
							Value ediValue = env.getMemory().getMemoryValue(new X86MemoryOperand(edi.getDataType(),
									edi.getDisplacement() + i * opSize), inst);
							Value esiValue = env.getMemory().getMemoryValue(new X86MemoryOperand(esi.getDataType(),
									esi.getDisplacement() + i * opSize), inst);
							// PHONG: fix here
							env.getFlag().changeFlagWithSUB(esiValue, ediValue,
									env, rule.getBitCount(inst));
							j++;
							if (ediValue.equal(esiValue)) {
								// env.getFlag().setZFlag(new
								// BooleanValue(false));
								zf = false;
								break;
							}
						}
						env.getFlag().setZFlag(new BooleanValue(zf));
						if (((BooleanValue) env.getFlag().getDFlag())
								.getValue() == false) {
							env.getRegister().add("esi", j * opSize);
							env.getRegister().add("edi", j * opSize);
						} else {
							env.getRegister().sub("esi", j * opSize);
							env.getRegister().sub("edi", j * opSize);
						}
						env.getRegister().sub("ecx", j);
					}
				}
			} else {
				// System.out.println("Debug Instruction " + inst.getName());

				if (dest.getClass().getSimpleName().equals("X86MemoryOperand")
						&& src.getClass().getSimpleName()
								.equals("X86MemoryOperand")) {
					X86MemoryOperand edi = env.getMemory().evaluateAddress(
							(X86MemoryOperand) dest, env);
					X86MemoryOperand esi = env.getMemory().evaluateAddress(
							(X86MemoryOperand) src, env);

					// Comapre between EDI and ESI, if not true, increase ESI
					// and
					// EDI by 1
					// Value ecx = env.getRegister().getRegisterValue("%ecx");

					// boolean zf = true;
					Value ediValue = env.getMemory().getMemoryValue(edi, inst);
					Value esiValue = env.getMemory().getMemoryValue(esi, inst);
					// PHONG: fix here
					env.getFlag().changeFlagWithSUB(esiValue, ediValue, env,
							rule.getBitCount(inst));

					env.getFlag().setZFlag(
							new BooleanValue(ediValue.equal(esiValue)));
					if (((BooleanValue) env.getFlag().getDFlag()).getValue() == false) {
						env.getRegister().add("esi", new LongValue(opSize));
						env.getRegister().add("edi", new LongValue(opSize));
					} else {
						env.getRegister().sub("esi", new LongValue(opSize));
						env.getRegister().sub("edi", new LongValue(opSize));
					}
					// env.getRegister().sub("ecx", j);
				}
			}

		} else if (inst.getName().startsWith("sti")) {
			env.getFlag().setIFlag(new BooleanValue(true));
		} else if (inst.getName().startsWith("std")) {
			env.getFlag().setDFlag(new BooleanValue(1));
		} else if (inst.getName().startsWith("cld")) {
			// Clear direction flag
			env.getFlag().setDFlag(new BooleanValue(0));
		} else if (inst.getName().startsWith("scas")) {
			/*
			 * @_1: scasb jnz @_1 sub edi,esi ; EDI = API Name size
			 */
			// Scas is followed by jump condition. This is a loop
			// So we run this loop by symbolic execution with while loop
			// System.out.println("Debug Instruction scas");
			opSize = rule.getBitCount(inst) / 8;
			if (inst.hasPrefixREPNZ()) {
				while (true) {
					Value x = env.getRegister().getRegisterValue(
							dest.toString());
					Value y = env.getRegister().getRegisterValue("%edi");

					if (x instanceof LongValue && y instanceof LongValue) {
						long x1 = ((LongValue) x).getValue();
						long y1 = ((LongValue) y).getValue();

						Value z = env.getMemory().getMemoryValue(
								new X86MemoryOperand(((X86MemoryOperand)inst.getOperand2()).getDataType(), y1), inst);
						// PHONG: fix here
						env.getFlag().changeFlagWithSUB(x, z, env,
								rule.getBitCount(inst));

						// PHONG: fix here
						if (((BooleanValue) env.getFlag().getDFlag())
								.getValue() == false) {
							env.getRegister().sub("ecx", new LongValue(1));
							env.getRegister().add("%edi", new LongValue(opSize));
						} else {
							env.getRegister().add("ecx", new LongValue(1));
							env.getRegister().sub("%edi", new LongValue(opSize));
						}

						// Dieu kien bang xay ra
						// Thoat khoi vong lap
						if (z != null && z.equal(x)) {
							env.getFlag().setZFlag(new BooleanValue(true));
							break;
						} else
							env.getFlag().setZFlag(new BooleanValue(false));

						// Thoat ra khi ECX = 0
						Value ecx = env.getRegister().getRegisterValue("%ecx");
						if (ecx instanceof LongValue
								&& ((LongValue) ecx).getValue() == 0)
							break;
					} else 
						break;
				}

			} else if (inst.hasPrefixREPZ()) {
				while (true) {
					Value x = env.getRegister().getRegisterValue(
							dest.toString());
					Value y = env.getRegister().getRegisterValue("%edi");

					if (x instanceof LongValue && y instanceof LongValue) {
						long x1 = ((LongValue) x).getValue();
						long y1 = ((LongValue) y).getValue();

						Value z = env.getMemory().getMemoryValue(y1, inst);
						// PHONG: fix here
						env.getFlag().changeFlagWithSUB(x, z, env,
								rule.getBitCount(inst));

						// PHONG: fix here
						if (((BooleanValue) env.getFlag().getDFlag())
								.getValue() == false) {
							env.getRegister().sub("ecx", new LongValue(1));
							env.getRegister().add("%edi", new LongValue(opSize));
						} else {
							env.getRegister().add("ecx", new LongValue(1));
							env.getRegister().sub("%edi", new LongValue(opSize));
						}

						// Dieu kien khong bang xay ra
						// Thoat khoi vong lap
						if (z != null && z instanceof LongValue
								&& x1 == ((LongValue) z).getValue()) {
							env.getFlag().setZFlag(new BooleanValue(true));
							//break;
						} else {
							env.getFlag().setZFlag(new BooleanValue(false));
							break;
						}

						// Thoat ra khi ECX = 0
						Value ecx = env.getRegister().getRegisterValue("%ecx");
						if (ecx instanceof LongValue
								&& ((LongValue) ecx).getValue() == 0)
							break;
					} else 
						break;
				}

			} else {
				Value x = env.getRegister().getRegisterValue(dest.toString());
				Value y = env.getRegister().getRegisterValue("%edi");

				if (x instanceof LongValue && y instanceof LongValue) {
					long x1 = ((LongValue) x).getValue();
					long y1 = ((LongValue) y).getValue();
					// while (true) {
					Value z = env.getMemory().getMemoryValue(y1, inst);
					// PHONG: fix here
					env.getFlag().changeFlagWithSUB(x, z, env,
							rule.getBitCount(inst));
					// Dieu kien bang xay ra
					if (z != null && z instanceof LongValue
							&& x1 == ((LongValue) z).getValue())
						// break;
						env.getFlag().setZFlag(new BooleanValue(true));
					else
						env.getFlag().setZFlag(new BooleanValue(false));

					// PHONG: fix here
					if (((BooleanValue) env.getFlag().getDFlag()).getValue() == false) {
						env.getRegister()
								.add("%edi", new LongValue(opSize));
					} else {
						env.getRegister()
								.sub("%edi", new LongValue(opSize));
					}
				}
			}
		} else if (inst.getName().startsWith("stos")) {
			// Load String
			if (inst.hasPrefixREPZ() || inst.hasPrefixREPNZ()) {
				// System.out.println("Debug Instruction REPZ STOS:" +
				// inst.toString());
				Value ecx = env.getRegister().getRegisterValue("ecx");
				Value cx = env.getRegister().getRegisterValue("cx");
				if (ecx != null && ecx instanceof LongValue) {
					long t = ((LongValue)ecx).getValue();
					t = LoopAlgorithm.getInstance().normalizeLoop(t);
					
					for (int i=0; i<t; i++) {
						storeString(env, inst, opSize);
						env.getRegister().sub("ecx", new LongValue(1));
					}
					//env.getRegister().setRegisterValue("ecx", new LongValue(0));
				} else if (cx != null && cx instanceof LongValue) {
					long t = ((LongValue)cx).getValue();
					t = LoopAlgorithm.getInstance().normalizeLoop(t);
					
					for (int i=0; i<t; i++) {
						storeString(env, inst, opSize);
						env.getRegister().sub("cx", new LongValue(1));
					}
					//env.getRegister().setRegisterValue("cx", new LongValue(0));
				} else {
					storeString(env, inst, opSize);
				}

			} else {
				// System.out.println("Debug Instruction stos:" + inst.toString());
				storeString(env, inst, opSize);
			}

		} else if (inst.getName().startsWith("lods")) {
			// Load String
			// System.out.println("Debug Instruction lods");
			if (inst.hasPrefixREPZ() || inst.hasPrefixREPNZ()) {
				// System.out.println("Debug Instruction REPZ STOS:" +
				// inst.toString());
				Value ecx = env.getRegister().getRegisterValue("ecx");
				Value cx = env.getRegister().getRegisterValue("cx");
				if (ecx != null && ecx instanceof LongValue) {
					long t = ((LongValue)ecx).getValue();
					t = LoopAlgorithm.getInstance().normalizeLoop(t);
					
					for (int i=0; i<t; i++) {
						loadString(env, inst, opSize);
						env.getRegister().sub("ecx", new LongValue(1));
					}
				} else if (cx != null && cx instanceof LongValue) {
					long t = ((LongValue)cx).getValue();
					t = LoopAlgorithm.getInstance().normalizeLoop(t);
					
					for (int i=0; i<t; i++) {
						loadString(env, inst, opSize);
						env.getRegister().sub("cx", new LongValue(1));
					}
				} else {
					loadString(env, inst, opSize);
				}

			} else {
				// System.out.println("Debug Instruction stos:" + inst.toString());
				loadString(env, inst, opSize);
			}
		} else if (inst.getName().startsWith("movs")) {
			if (inst.hasPrefixREPZ() || inst.hasPrefixREPNZ()) {
				Value ecx = env.getRegister().getRegisterValue("ecx");
				Value cx = env.getRegister().getRegisterValue("cx");
				if (ecx != null && ecx instanceof LongValue) {
					long t = ((LongValue)ecx).getValue();
					t = LoopAlgorithm.getInstance().normalizeLoop(t);
					
					for (int i=0; i<t; i++) {
						movString(env, inst, opSize);
						env.getRegister().sub("ecx", new LongValue(1));
					}
					//env.getRegister().setRegisterValue("ecx", new LongValue(0));
				} else if (cx != null && cx instanceof LongValue) {
					long t = ((LongValue)cx).getValue();
					t = LoopAlgorithm.getInstance().normalizeLoop(t);
					
					for (int i=0; i<t; i++) {
						movString(env, inst, opSize);
						env.getRegister().sub("cx", new LongValue(1));
					}
					//env.getRegister().setRegisterValue("cx", new LongValue(0));
				} else {
					movString(env, inst, opSize);
				}
			} else {
				movString(env, inst, opSize);
			}
		} else if (inst.getName().startsWith("cld")) {
			env.getFlag().setDFlag(new BooleanValue(0));
		} else if (inst.getName().startsWith("int")) {
			if (dest != null
					&& dest.getClass().getSimpleName().equals("Immediate")) {
				long x = (long)Convert.convetUnsignedValue(((Immediate) dest).getNumber()
						.intValue(), rule.getBitCount(inst));

				// Process interrupt 80h
				if (x == 80) {

				} else
				// Process interrupt 21h
				if (x == 33) {
					// Check if AH = 2A
					// Return: CX = year (1980-2099) DH = month DL = day AL =
					// day of week (00h=Sunday)
					if (env.getRegister().getRegisterValue("%ah")
							.equal(new LongValue(42))) {
						env.getRegister().setRegisterValue(
								"%cx",
								new LongValue(Calendar.getInstance().get(
										Calendar.YEAR)));
						// int z = Calendar.getInstance().get(Calendar.MONTH);
						env.getRegister().setRegisterValue(
								"%dh",
								new LongValue(Calendar.getInstance().get(
										Calendar.MONTH) + 1));
						env.getRegister().setRegisterValue(
								"%dl",
								new LongValue(Calendar.getInstance().get(
										Calendar.DATE)));
						env.getRegister().setRegisterValue(
								"%al",
								new LongValue(Calendar.getInstance().get(
										Calendar.DAY_OF_WEEK)));
					}
				}

			}
		} else if (inst.getName().startsWith("leave")) {
			env.getRegister().setRegisterValue("esp",
					env.getRegister().getRegisterValue("ebp"));
			env.getRegister().setRegisterValue("ebp", env.getStack().pop());
		} else if (inst.getName().startsWith("lea")) {
			// just like MOV but with minor difference - Come back later
			// http://stackoverflow.com/questions/1699748/what-is-the-difference-between-mov-and-lea
			if (dest.getClass().getSimpleName().equals("X86Register")) {
				if (src.getClass().getSimpleName().equals("X86Register")) {
					env.getRegister().mov(dest.toString(), src.toString());
				} else if (src.getClass().getSimpleName()
						.equals("X86RegisterPart")) {
					env.getRegister().mov(dest.toString(), src.toString());
				} else if (src.getClass().getSimpleName()
						.equals("X86SegmentRegister")) {
					env.getRegister().mov(dest.toString(), src.toString());
				} else if (src.getClass().getSimpleName().equals("Immediate")) {
					env.getRegister().mov(
							dest.toString(),
							Convert.convetUnsignedValue(((Immediate) src).getNumber()
									.intValue(), rule.getBitCount(inst)));
				} else if (src.getClass().getSimpleName()
						.equals("X86MemoryOperand")) {
					X86MemoryOperand t = (X86MemoryOperand) src;
					env.getRegister().setRegisterValue(dest.toString(),
							env.getMemory().calculateAddress(t));
				}
			} else if (dest.getClass().getSimpleName()
					.equals("X86RegisterPart")) {
				if (src.getClass().getSimpleName().equals("X86Register")) {
					env.getRegister().mov(dest.toString(), src.toString());
				} else if (src.getClass().getSimpleName()
						.equals("X86RegisterPart")) {
					env.getRegister().mov(dest.toString(), src.toString());
				} else if (src.getClass().getSimpleName()
						.equals("X86SegmentRegister")) {
					env.getRegister().mov(dest.toString(), src.toString());
				} else if (src.getClass().getSimpleName().equals("Immediate")) {
					env.getRegister().mov(
							dest.toString(),
							Convert.convetUnsignedValue(((Immediate) src).getNumber()
									.intValue(), rule.getBitCount(inst)));
				} else if (src.getClass().getSimpleName()
						.equals("X86MemoryOperand")) {
					X86MemoryOperand t = (X86MemoryOperand) src;
					env.getRegister().setRegisterValue(dest.toString(),
							env.getMemory().calculateAddress(t));
				}
			} else if (dest.getClass().getSimpleName()
					.equals("X86SegmentRegister")) {
				if (src.getClass().getSimpleName().equals("X86Register")) {
					env.getRegister().mov(dest.toString(), src.toString());
				} else if (src.getClass().getSimpleName()
						.equals("X86RegisterPart")) {
					env.getRegister().mov(dest.toString(), src.toString());
				} else if (src.getClass().getSimpleName()
						.equals("X86SegmentRegister")) {
					env.getRegister().mov(dest.toString(), src.toString());
				} else if (src.getClass().getSimpleName().equals("Immediate")) {
					env.getRegister().mov(
							dest.toString(),
							Convert.convetUnsignedValue(((Immediate) src).getNumber()
									.intValue(), rule.getBitCount(inst)));
				} else if (src.getClass().getSimpleName()
						.equals("X86MemoryOperand")) {
					X86MemoryOperand t = (X86MemoryOperand) src;
					env.getRegister().setRegisterValue(dest.toString(),
							env.getMemory().calculateAddress(t));
				}
			}

		} else if (inst.getName().startsWith("cmp")
				|| inst.getName().startsWith("test")) {
			if (dest.getClass().getSimpleName().equals("X86Register"))
				d = env.getRegister().getRegisterValue(dest.toString());
			else if (dest.getClass().getSimpleName().equals("X86RegisterPart"))
				d = env.getRegister().getRegisterValue(dest.toString());
			else if (dest.getClass().getSimpleName()
					.equals("X86SegmentRegister"))
				d = env.getRegister().getRegisterValue(dest.toString());
			else if (dest.getClass().getSimpleName().equals("Immediate"))
				d = new LongValue(Convert.convetUnsignedValue(((Immediate) dest)
						.getNumber().intValue(), rule.getBitCount(inst)));
			else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
				d = env.getMemory().getMemoryValue((X86MemoryOperand) dest,
						inst);
				// if (d instanceof LongValue &&
				// ((LongValue)d).getValueOperand()==23117)
				// System.out.println("Debug");

				X86MemoryOperand t = env.getMemory().evaluateAddress(
						(X86MemoryOperand) dest, env);

				//Cau lenh CMP khong lam thay doi Memory nen se khong the xay ra truong hop SEH
				/*if (!rule.checkAddressValid(env, t)) {
					System.out.println("Process SEH at: "
							+ path.getCurrentState().getLocation().toString());
					return rule.processSEH(path.getCurrentState());
				}*/

				// evaluateAddress((X86MemoryOperand) dest,
				// env).getDisplacement());
				// System.out.println(d.getName());
			}

			if (src.getClass().getSimpleName().equals("X86Register"))
				s = env.getRegister().getRegisterValue(src.toString());
			else if (src.getClass().getSimpleName().equals("X86RegisterPart"))
				s = env.getRegister().getRegisterValue(src.toString());
			else if (src.getClass().getSimpleName()
					.equals("X86SegmentRegister"))
				s = env.getRegister().getRegisterValue(src.toString());
			else if (src.getClass().getSimpleName().equals("Immediate")) {
				s = new LongValue(Convert.convetUnsignedValue(((Immediate) src)
						.getNumber().intValue(), rule.getBitCount(inst)));
			} else if (src.getClass().getSimpleName()
					.equals("X86MemoryOperand")) {
				s = env.getMemory()
						.getMemoryValue((X86MemoryOperand) src, inst);
			}

			// this.formulas.add(new Formula(d.clone(), s.clone()));
			if (inst.getName().startsWith("cmp"))
				// env.getFlag().changeFlagWithCMP(d, s, env,
				// rule.getBitCount(inst));
				// PHONG: fix here: changeFlagWithCMP = changeFlagWithSub
				env.getFlag().changeFlagWithSUB(d, s, env,
						rule.getBitCount(inst));
			else
				env.getFlag().changeFlagWithTEST(d, s, env,
						rule.getBitCount(inst));
			// set compare status

		} else if (inst.getName().startsWith("pop")) {
			if (dest == null)
				return curState;

			if (dest.getClass().getSimpleName().equals("X86Register")
					|| dest.getClass().getSimpleName()
							.equals("X86RegisterPart")
					|| dest.getClass().getSimpleName()
							.equals("X86SegmentRegister")) {
				/*
				 * if (!symbolValueRegister.setSymbolRegisterValue(
				 * dest.toString(), symbolStack.pop()))
				 * System.out.println("Error");
				 */
				env.getRegister().setRegisterValue(dest.toString(),
						env.getStack().pop());
			} else if (dest.getClass().getSimpleName().equals("Immediate"))
				// d = new ConstantExp(((Immediate)
				// dest).getNumber().intValue());
				;
			else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
				/*
				 * if (!symbolValueMemoryOperand.setSymbolMemoryOperandValue(
				 * (X86MemoryOperand) dest, symbolStack.pop()))
				 * System.out.println("Error");
				 */

				env.getMemory().setMemoryValue((X86MemoryOperand) dest,
						env.getStack().pop(), inst);
			}

		} else if (inst.getName().startsWith("pusha")) {
			// PHONG
			// Save ESP here
			Value esp_addr = env.getRegister().getRegisterValue("esp");

			env.getStack().push(env.getRegister().getRegisterValue("%eax"));
			env.getStack().push(env.getRegister().getRegisterValue("%ecx"));
			env.getStack().push(env.getRegister().getRegisterValue("%edx"));
			env.getStack().push(env.getRegister().getRegisterValue("%ebx"));

			// env.getStack().push(env.getRegister().getRegisterValue("%esp"));
			// PUSH original ESP
			env.getStack().push(esp_addr);

			env.getStack().push(env.getRegister().getRegisterValue("%ebp"));
			env.getStack().push(env.getRegister().getRegisterValue("%esi"));
			env.getStack().push(env.getRegister().getRegisterValue("%edi"));

		} else if (inst.getName().startsWith("pushf")) {
			if (opSize == 32)
				env.getStack().push(env.getFlag().getEFLAGS());
			else if (opSize == 16)
				env.getStack().push16(env.getFlag().getEFLAGS());
		} else if (inst.getName().startsWith("push")) {
			if (dest == null)
				return curState;

			if (dest.getClass().getSimpleName().equals("X86Register"))
				d = env.getRegister().getRegisterValue(dest.toString());
			else if (dest.getClass().getSimpleName().equals("X86RegisterPart"))
				d = env.getRegister().getRegisterValue(dest.toString());
			else if (dest.getClass().getSimpleName().equals("Immediate")) {
				// Immediate t = (Immediate) dest;
				// long x = t.getNumber().longValue();
				d = new LongValue(Convert.convetUnsignedValue(((Immediate) dest)
						.getNumber().longValue(), rule.getBitCount(inst)));
			} else if (dest.getClass().getSimpleName()
					.equals("X86MemoryOperand")) {
				// d = symbolValueMemoryOperand
				// .getMemoryOperandVal((X86MemoryOperand) dest);
				X86MemoryOperand t = (X86MemoryOperand) dest;
				if (t.getSegmentRegister() != null
						&& t.getSegmentRegister().toString() == "%fs"
						&& t.getDisplacement() == 0) {
					d = new LongValue(env.getSystem().getSEHHandler()
							.getStart().getNextSEHRecord());
				} else
					d = env.getMemory().getMemoryValue(t, inst);

			} else if (dest.getClass().getSimpleName()
					.equals("X86SegmentRegister"))
				d = env.getRegister().getRegisterValue(dest.toString());

			env.getStack().push(d);
		} else if (inst.getName().startsWith("nop")) {
			// do nothing
		} else if (inst.getName().startsWith("das"))
		// TODO: 51104402 - DAS - Decimal Adjust for Subtraction
		{
			Value firstCondition = new HybridValue(env.getRegister()
					.getRegisterValue("al").andFunction(new LongValue(0x0F)),
					">", new LongValue(9));
			firstCondition = firstCondition.evaluate(firstCondition
					.getValueMap());

			Value secondCondition = env.getFlag().getAFlag();
			secondCondition = secondCondition.evaluate(secondCondition
					.getValueMap());

			if ((firstCondition instanceof BooleanValue && ((BooleanValue) firstCondition)
					.getValue() == true)
					|| (secondCondition instanceof BooleanValue && ((BooleanValue) secondCondition)
							.getValue() == true)) {
				env.getRegister().setRegisterValue(
						"al",
						env.getRegister().getRegisterValue("al")
								.subFunction(new LongValue(6)));

				// CF = CF OR BorrowFromLastSubtraction; (* CF OR borrow from AL
				// = AL - 6 *)
				Value CFlagCondition = new HybridValue(env.getRegister()
						.getRegisterValue("al"), "<", new LongValue(6));
				CFlagCondition = CFlagCondition.evaluate(CFlagCondition
						.getValueMap());
				if (CFlagCondition instanceof BooleanValue
						&& ((BooleanValue) CFlagCondition).getValue() == true)
					env.getFlag().setCFlag(new BooleanValue(true));

				env.getFlag().setAFlag(new BooleanValue(true));
			} else {
				env.getFlag().setAFlag(new BooleanValue(false));
			}

			firstCondition = new HybridValue(env.getRegister()
					.getRegisterValue("al"), ">", new LongValue(0x9F));
			firstCondition = firstCondition.evaluate(firstCondition
					.getValueMap());

			secondCondition = env.getFlag().getCFlag();
			secondCondition = secondCondition.evaluate(secondCondition
					.getValueMap());

			if ((firstCondition instanceof BooleanValue && ((BooleanValue) firstCondition)
					.getValue() == true)
					|| (secondCondition instanceof BooleanValue && ((BooleanValue) secondCondition)
							.getValue() == true)) {
				env.getRegister().setRegisterValue(
						"al",
						env.getRegister().getRegisterValue("al")
								.subFunction(new LongValue(0x60)));
				env.getFlag().setCFlag(new BooleanValue(true));
			} else {
				env.getFlag().setCFlag(new BooleanValue(false));
			}
		} else if (inst.getName().startsWith("sete"))
		// TODO: 51104402 - SETcc - Set Byte on Condition
		{
			Value zFlag = env.getFlag().getZFlag();
			zFlag = zFlag.evaluate(zFlag.getValueMap());

			if (zFlag instanceof BooleanValue) {
				boolean isSet = ((BooleanValue) zFlag).getValue();
				if (dest.getClass().getSimpleName().equals("X86Register")
						|| dest.getClass().getSimpleName()
								.equals("X86RegisterPart")
						|| dest.getClass().getSimpleName()
								.equals("X86SegmentRegister")) {
					env.getRegister().setRegisterValue(dest.toString(),
							((isSet) ? new LongValue(1) : new LongValue(0)));
				} else if (dest.getClass().getSimpleName()
						.equals("X86MemoryOperand")) {
					X86MemoryOperand t = env.getMemory().evaluateAddress(
							(X86MemoryOperand) dest, env);
					if (!rule.checkAddressValid(env, t)) {
						// SEH Exploit
						/*
						 * System.out.println("SEH:" +
						 * path.getCurrentState().getLocation() .toString());
						 */
						return rule.processSEH(path.getCurrentState());
					}

					env.getMemory().setByteMemoryValue((X86MemoryOperand) dest,
							((isSet) ? new LongValue(1) : new LongValue(0)));
				}
			}
		} else if (inst.getName().startsWith("aam")) {
		// TODO: 51104402 - AAM - ASCII Adjust AX After Multiply
			Value AL = env.getRegister().getRegisterValue("al");
			AL = AL.evaluate(AL.getValueMap());
			long ALValue = Long.MIN_VALUE;
			if (inst.getOperandCount() == 0) {
				if (AL instanceof LongValue) {
					ALValue = ((LongValue) AL).getValue();
					env.getRegister().setRegisterValue("ah",
							new LongValue(ALValue / 10));
					env.getRegister().setRegisterValue("al",
							new LongValue(ALValue % 10));
				}
				//--------------------------------------------------------------------------------
				// PHONG: Change flag here
				//env.getFlag().changeFlagWithDIV(AL, new LongValue(10),env,inst.getOperandCount());
				//env.getFlag().changeFlagWithDIV(AL, new LongValue(10),env,inst.getOperandCount());
				//--------------------------------------------------------------------------------
			} else {
				dest = inst.getOperand1();

				if (dest.getClass().getSimpleName().equals("X86Register")
						|| dest.getClass().getSimpleName()
								.equals("X86RegisterPart")
						|| dest.getClass().getSimpleName()
								.equals("X86SegmentRegister")) {
					Value t = env.getRegister().getRegisterValue(
							dest.toString());

					if (AL instanceof LongValue && t instanceof LongValue) {
						ALValue = ((LongValue) AL).getValue();
						long tValue = ((LongValue) t).getValue();

						env.getRegister().setRegisterValue("ah",
								new LongValue(ALValue / tValue));
						env.getRegister().setRegisterValue("al",
								new LongValue(ALValue % tValue));
						//-------------------------------------------------------------------------------------
						// PHONG: Change flag here
						//env.getFlag().changeFlagWithDIV(AL, new LongValue(tValue),env,inst.getOperandCount());
						//env.getFlag().changeFlagWithDIV(AL, new LongValue(tValue),env,inst.getOperandCount());
						//-------------------------------------------------------------------------------------
					}
				} else if (dest.getClass().getSimpleName().equals("Immediate")) {
					long tValue = Convert.convetUnsignedValue(((Immediate) dest)
							.getNumber().longValue(), rule.getBitCount(inst));

					if (AL instanceof LongValue) {
						ALValue = ((LongValue) AL).getValue();

						env.getRegister().setRegisterValue("ah",
								new LongValue(ALValue / tValue));
						env.getRegister().setRegisterValue("al",
								new LongValue(ALValue % tValue));
						//-------------------------------------------------------------------------------------
						// PHONG: Change flag here
						//env.getFlag().changeFlagWithDIV(AL, new LongValue(tValue),env,inst.getOperandCount());
						//env.getFlag().changeFlagWithDIV(AL, new LongValue(tValue),env,inst.getOperandCount());
						//-------------------------------------------------------------------------------------
					}
				}
			}
			//PHONG: undefined != false
			env.getFlag().setCFlag(new BooleanValue(false));
			env.getFlag().setOFlag(new BooleanValue(false));
			env.getFlag().setAFlag(new BooleanValue(false));
			if (ALValue != Long.MIN_VALUE) {
				long t = Convert.convertSignedValue(ALValue, opSize);
				env.getFlag().setPFlag(new BooleanValue(BitVector.getParityBit(t)));
				env.getFlag().setSFlag(new BooleanValue(t < 0));
				env.getFlag().setZFlag(new BooleanValue(t == 0));
			}
		} else if (inst.getName().startsWith("sahf"))
		// TODO: 51104402 - SAHF - Store AH into Flags
		{
			Value AH = env.getRegister().getRegisterValue("ah");
			AH = AH.evaluate(AH.getValueMap());
			// EFLAGS(SF:ZF:0:AF:0:PF:1:CF) = AH;
			if (AH instanceof LongValue) {
				long AHValue = ((LongValue) AH).getValue();
				boolean[] AHBits = new boolean[8];
				for (int i = 0; i < 8; i++)
					AHBits[i] = (((AHValue & (1 << i)) >> i) == 1) ? true
							: false;
				// Loads the SF, ZF, AF, PF, and CF flags of the EFLAGS register
				// with values
				// from the corresponding bits in the AH register (bits 7, 6, 4,
				// 2, and 0, respectively)
				env.getFlag().setSFlag(new BooleanValue(AHBits[7]));
				env.getFlag().setZFlag(new BooleanValue(AHBits[6]));
				env.getFlag().setAFlag(new BooleanValue(AHBits[4]));
				env.getFlag().setPFlag(new BooleanValue(AHBits[2]));
				env.getFlag().setCFlag(new BooleanValue(AHBits[0]));
			}
		} else if (inst.getName().startsWith("cltd"))
		// TODO: 51104402 - CWD/CDQ - Convert Word to Doubleword/Convert
		// Doubleword to Quadword
		{
			// PHONG: fix here
			Value EAX = env.getRegister().getRegisterValue("eax");
			EAX = EAX.evaluate(EAX.getValueMap());

			// The CWD instruction copies the sign (bit 15) of the value in the
			// AX register into every bit position in the DX register
			if (EAX instanceof LongValue) {
				long EAXValue = ((LongValue) EAX).getValue();
				boolean signBit = (((EAXValue & (1 << 31)) >> 31) == 1) ? true
						: false;

				if (signBit)
					env.getRegister().setRegisterValue("edx",
							new LongValue(0xFFFFFFFF));
				else
					env.getRegister().setRegisterValue("edx",
							new LongValue(0x00000000));
			}
		}

		/*****************
		 ***** Khanh*******
		 *****************/

		// NEG : bu 2 cua bit
		else if (inst.getName().startsWith("neg")) {
			Value temp = env.getRegister().getRegisterValue(dest.toString());

			if (temp instanceof LongValue) {
				// long t = ((LongValue) temp).getValueOperand();
				Value temp2 = ((LongValue) temp).notFunction(); // ~ value of
																// register
				Value t2 = temp2.addFunction(new LongValue(1)); // add 1
				env.getRegister().setRegisterValue(dest.toString(), t2);

				// NOTICE: changeflagwithNEG is undefined
				// env.getFlag().changeFlagWithNEG(temp2, temp, env,
				// rule.getBitCount(inst));

				// PHONG: fix here
				Value zeroval = new LongValue(0x0);
				env.getFlag().changeFlagWithNeg(zeroval, temp, env,
						rule.getBitCount(inst));
			} else // bo sung sau : truong hop khong thoa man
			{
			}

		}

		// STC cau lenh set CF = 1 bat ky truong hop nao
		else if (inst.getName().startsWith("stc")) {
			env.getFlag().setCFlag(new BooleanValue(1));
		}

		// CMC cau lenh set CF = ~ CF
		else if (inst.getName().startsWith("cmc")) {
			// temp = true
			if (env.getFlag().getCFlag().equal(new BooleanValue(1))) {
				env.getFlag().setCFlag(new BooleanValue(0));
			}

			// temp = false
			else if (env.getFlag().getCFlag().equal(new BooleanValue(0))) {
				env.getFlag().setCFlag(new BooleanValue(1));
			}
		}
		else if (inst.getName().startsWith("bsf")){
			long temp_d = 0;
			temp_s = 0;
			long result = 0;
			int get_bit = rule.getBitCount(inst);

			if (dest.getClass().getSimpleName().equals("X86Register")
					|| dest.getClass().getSimpleName().equals("X86RegisterPart")
					|| dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
				d = env.getRegister().getRegisterValue(dest.toString());
			}

			if (src.getClass().getSimpleName().equals("X86Register")
					|| src.getClass().getSimpleName().equals("X86RegisterPart")
					|| src.getClass().getSimpleName().equals("X86SegmentRegister")) {
				s = env.getRegister().getRegisterValue(src.toString());
			}
			else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
				X86MemoryOperand t = env.getMemory().evaluateAddress((X86MemoryOperand) src, env);
				if (!rule.checkAddressValid(env, t)) {
					// SEH Exploit
					System.out.println("SEH:" + path.getCurrentState().getLocation().toString());
					return rule.processSEH(path.getCurrentState());
				}
				s = env.getMemory().getMemoryValue((X86MemoryOperand) src, inst);
			}

			temp_d = ((LongValue) d).getValue();
			temp_s = ((LongValue) s).getValue();
			if( (temp_d == 0) && (temp_s == 0)){
				env.getFlag().setZFlag(new BooleanValue(true));
			}
			else{
				env.getFlag().setZFlag(new BooleanValue(false));
				result = new AnalysisBit().BSF(temp_s, get_bit);
				env.getRegister().setRegisterValue(dest.toString(),new LongValue(result));
				// PHONG: change flag here
				// As documentation: flag will change when temp + 1. Dest will be number of loops
				// --------------------------------------------------------------------------------------
				for (int i = 0; i < result; i++){
					LongValue temp_value = new LongValue(i);
					env.getFlag().changeFlagWithINC(temp_value,env,get_bit);
				}
				// --------------------------------------------------------------------------------------
			}
			/*
			PHONG: change here
			env.getFlag().setCFlag(new BooleanValue(false));
			env.getFlag().setOFlag(new BooleanValue(false));
			env.getFlag().setSFlag(new BooleanValue(false));
			env.getFlag().setAFlag(new BooleanValue(false));
			env.getFlag().setPFlag(new BooleanValue(false));
			*/
		}
		// SETA:
		// (ZF = 0 && CF = 0) => dest = 1
		else if (inst.getName().startsWith("seta")) {

			// PHONG: fix here
			if ((env.getFlag().getZFlag().equal(new BooleanValue(0)) && (env
					.getFlag().getCFlag().equal(new BooleanValue(0))))) {
				env.getRegister().setRegisterValue(dest.toString(),
						new LongValue(1));
			} else // bo sung truong hop khong thoa man
			{

			}
		}

		// lahf: set AL= EFLAGS bit low (8 bit dau)
		// 7 6 5 4 3 2 1 0
		// SF ZF 0 AF 0 PF 1 CF

		else if (inst.getName().startsWith("lahf")) {
			long bit0, bit1, bit2, bit3, bit4, bit5, bit6, bit7;

			if (env.getFlag().getCFlag().equal(new BooleanValue(1)))
				bit0 = 1;
			else
				bit0 = 0;

			bit1 = 2;

			if (env.getFlag().getPFlag().equal(new BooleanValue(1)))
				bit2 = 4;
			else
				bit2 = 0;

			bit3 = 0;

			if (env.getFlag().getAFlag().equal(new BooleanValue(1)))
				bit4 = 16;
			else
				bit4 = 0;

			bit5 = 0;

			if (env.getFlag().getZFlag().equal(new BooleanValue(1)))
				bit6 = 64;
			else
				bit6 = 0;

			if (env.getFlag().getSFlag().equal(new BooleanValue(1)))
				bit7 = 128;
			else
				bit7 = 0;

			long result = 0;
			result = bit0 + bit1 + bit2 + bit3 + bit4 + bit5 + bit6 + bit7;

			// env.getRegister().setRegisterValue("al", new LongValue(result));
			// PHONG: fix here
			env.getRegister().setRegisterValue("ah", new LongValue(result));
		}

		// truong hop xlat
		// truong hop 1: khi thanh ghi ebx co gia tri
		// truong hop 2: khi thanh ghi ebx ko co gia tri, thanh al co gia tri
		// truong hop 3: ebx, al ko co gia tri
		else if (inst.getName().startsWith("xlat")) {
			Value ebx = env.getRegister().getRegisterValue("ebx");
			if (ebx instanceof LongValue) {
				long e = ((LongValue) ebx).getValue();
				// Value m = env.getMemory().getWordMemoryValue(e);
				// env.getRegister().setRegisterValue("al", m);
				// PHONG: fix here
				Value m = env.getMemory().getByteMemoryValue(
						e
								+ ((LongValue) env.getRegister()
										.getRegisterValue("al")).getValue());
				env.getRegister().setRegisterValue("al", m);
			} else {
				Value al = env.getRegister().getRegisterValue("al");
				if (al instanceof LongValue) {
					long e = ((LongValue) al).getValue();
					Value m = env.getMemory().getWordMemoryValue(e);
					env.getRegister().setRegisterValue("al", m);
				}
			}
			// long print = ((LongValue) temp).getValueOperand();
			// System.out.println(print);

		}

		/*****************
		 ***** Khanh*******
		 *****************/

		else if (inst.getName().startsWith("cli")) {
			env.getFlag().setIFlag(new BooleanValue(false));
		}

		else if (inst.getName().startsWith("setb")) {

			Value CF = env.getFlag().getCFlag();
			if (dest.getClass().getSimpleName().equals("X86RegisterPart")) {
				if (CF instanceof BooleanValue
						&& ((BooleanValue) CF).getValue()) {
					env.getRegister().setRegisterValue(dest.toString(),
							new LongValue(1));

				} else {
					env.getRegister().setRegisterValue(dest.toString(),
							new LongValue(0));
					int a = 0;
					a++;
				}
			}

		}

		else if (inst.getName().startsWith("aaa")) {
			Value AL = env.getRegister().getRegisterValue("al");
			Value AF = env.getFlag().getAFlag();
			boolean isCF = false;
			if ((AL instanceof LongValue)
					&& (((LongValue) AL).getValue() > 9 || (AF instanceof BooleanValue && ((BooleanValue) AF)
							.getValue())))
				isCF = true;

			if (isCF) {
				Long t = ((LongValue) AL).getValue() + 6;
				env.getRegister()
						.setRegisterValue("al", new LongValue(t & 0xF));
				env.getRegister().add("ah", new LongValue(1));
			}
			env.getFlag().setCFlag(new BooleanValue(isCF));
			env.getFlag().setAFlag(new BooleanValue(isCF));
		} else if (inst.getName().startsWith("enter")) {
			long ebp = 0;
			long esp = 0;
			long op1 = Convert.convetUnsignedValue(((Immediate) dest).getNumber()
					.intValue(), 16);
			long op2 = Convert.convetUnsignedValue(((Immediate) src).getNumber()
					.intValue(), 8);
			long _size = op1 + 4 * op2;
			// xoa.
			if (env.getRegister().getRegisterValue("ebp") instanceof LongValue)
				ebp = ((LongValue) env.getRegister().getRegisterValue("ebp"))
						.getValue();
			if (env.getRegister().getRegisterValue("esp") instanceof LongValue)
				esp = ((LongValue) env.getRegister().getRegisterValue("esp"))
						.getValue();

			env.getStack().push(env.getRegister().getRegisterValue("ebp"));
			Value tempreg = env.getRegister().getRegisterValue("esp");
			if (op2 > 0) {
				Long botAddr;
				Value bot = env.getRegister().getRegisterValue("ebp");

				if (bot instanceof LongValue) {
					botAddr = ((LongValue) bot).getValue();
					for (int i = 1; i < op2; i++) {
						botAddr -= 4;
						Value p = env.getMemory().getDoubleWordMemoryValue(
								botAddr);
						env.getStack().push(p);
					}
				}// end if

				env.getStack().push(tempreg);

			}// end if(op2 >0 )
				// new base(test);
			Long oldEBP = (long) 0;
			oldEBP = ebp;

			env.getRegister().setRegisterValue("ebp", tempreg);
			Long esp_value = ((LongValue) tempreg).getValue() - _size;
			env.getRegister().setRegisterValue("esp", new LongValue(esp_value));
			// xoa.
			ebp = ((LongValue) env.getRegister().getRegisterValue("ebp"))
					.getValue();
			esp = ((LongValue) env.getRegister().getRegisterValue("esp"))
					.getValue();

		} else if (inst.getName().startsWith("daa")) {
			Value AL = env.getRegister().getRegisterValue("al");
			Value AF = env.getFlag().getAFlag();
			Value CF = env.getFlag().getCFlag();
			boolean isCF = false;
			if ((AL instanceof LongValue && ((LongValue) AL).getValue() > 9)
					|| (AF instanceof BooleanValue && ((BooleanValue) AF)
							.getValue()))
				isCF = true;

			if (isCF) {
				env.getRegister().add("al", new LongValue(6));
				env.getFlag().setAFlag(new BooleanValue(true));
				// change Flag cF
				// Tu: change flag with DAA ?
				// env.getFlag().changCFlagWithDAA(env.getRegister().getRegisterValue("al"),
				// new LongValue(6), env);
			}

			if ((AL instanceof LongValue && ((LongValue) AL).getValue() > 0x9F)
					|| (CF instanceof BooleanValue && ((BooleanValue) CF)
							.getValue())) {
				env.getRegister().add("al", new LongValue(0x60));
				env.getFlag().setCFlag(new BooleanValue(true));
			}
		}
		//Khanh
		else if (inst.getName().startsWith("cmpxch8b")){
			Value EAX = env.getRegister().getRegisterValue("eax");
			Value EBX = env.getRegister().getRegisterValue("ebx");
			Value ECX = env.getRegister().getRegisterValue("ecx");
			Value EDX = env.getRegister().getRegisterValue("edx");
			long temp_EAX = ((LongValue) EAX).getValue();
			long temp_EBX = ((LongValue) EBX).getValue();
			long temp_ECX = ((LongValue) ECX).getValue();
			long temp_EDX = ((LongValue) EDX).getValue();

			long temp_d = 0;
			long temp_1 = 0;
			long temp_2 = 0;
			long EDX_EAX = 0;
			long ECX_EBX = 0;


			if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
				X86MemoryOperand t = env.getMemory().evaluateAddress((X86MemoryOperand) dest, env);
				if (!rule.checkAddressValid(env, t)) {
					// SEH Exploit
					System.out.println("SEH:"
							+ path.getCurrentState().getLocation()
							.toString());
					return rule.processSEH(path.getCurrentState());
				}
				d = env.getMemory().getMemoryValue((X86MemoryOperand) dest, inst);
			}

			temp_d = ((LongValue) d).getValue();
			EDX_EAX = new AnalysisBit().Bit64toDec(temp_EAX,temp_EDX);
			ECX_EBX = new AnalysisBit().Bit64toDec(temp_EBX,temp_ECX);

			if(temp_d == EDX_EAX){
				env.getFlag().setZFlag(new BooleanValue(true));
				env.getMemory().setMemoryValue((X86MemoryOperand) dest, new LongValue(ECX_EBX), inst);
			}else {
				env.getFlag().setZFlag(new BooleanValue(false));
				temp_1 = new AnalysisBit().Bit0FromIm(temp_d);
				temp_2 = new AnalysisBit().Bit32FromIm(temp_d);
				env.getRegister().setRegisterValue("eax",new LongValue(temp_1));
				env.getRegister().setRegisterValue("edx",new LongValue(temp_2));
			}
		}


		/******** CMPXCHG **********/
		// accumulator = AL, AX, or EAX, depending on whether
		//a byte, word, or doubleword comparison is being performed
		//	if(accumulator == Destination) {
		//		ZF = 1;
		//		Destination = Source;
		//	}
		//	else {
		//		ZF = 0;
		//		accumulator = Destination;
		//	}
		else if (inst.getName().startsWith("cmpxchg")){
			int bit = rule.getBitCount(inst);
			Value AL = env.getRegister().getRegisterValue("al");
			Value AX = env.getRegister().getRegisterValue("ax");
			Value EAX = env.getRegister().getRegisterValue("eax");

			boolean isSet = false;
			long temp_AL = 0;
			long temp_AX = 0;
			long temp_EAX = 0;
			long temp_d = 0;
			temp_s = 0;

			if (dest.getClass().getSimpleName().equals("X86Register")
					|| dest.getClass().getSimpleName()
					.equals("X86RegisterPart")
					|| dest.getClass().getSimpleName()
					.equals("X86SegmentRegister")) {
				d = env.getRegister().getRegisterValue(dest.toString());
			}
			else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
				X86MemoryOperand t = env.getMemory().evaluateAddress((X86MemoryOperand) dest, env);

				if (!rule.checkAddressValid(env, t)) {
					// SEH Exploit
					System.out.println("SEH:"
							+ path.getCurrentState().getLocation()
							.toString());
					return rule.processSEH(path.getCurrentState());
				}

				d = env.getMemory().getMemoryValue((X86MemoryOperand) dest, inst);
			}

			if (src.getClass().getSimpleName().equals("X86Register")
					|| src.getClass().getSimpleName()
					.equals("X86RegisterPart")
					|| src.getClass().getSimpleName()
					.equals("X86SegmentRegister")) {
				s = env.getRegister().getRegisterValue(src.toString());
			}

			temp_d = ((LongValue) d).getValue();
			temp_s = ((LongValue) s).getValue();

			if ( bit == 8 ) {
				temp_AL = ((LongValue) AL).getValue();
				if ( temp_AL == temp_d)
					isSet = true;
			}
			else if ( bit == 16 ) {
				temp_AX = ((LongValue) AX).getValue();
				if ( temp_AX == temp_d )
					isSet = true;
			}
			else if ( bit == 32 ) {
				temp_EAX = ((LongValue) EAX).getValue();
				if ((temp_EAX == temp_d))
					isSet = true;
			}

			if (isSet == true){
				env.getRegister().setRegisterValue(dest.toString(),new LongValue(temp_s));
				env.getFlag().setZFlag(new BooleanValue(true));
				//change flag CF, PF, AF, SF, and OF
			}
			else {
				if (bit == 8){
					env.getRegister().setRegisterValue("al",new LongValue(temp_d));
				}
				else if(bit == 16){
					env.getRegister().setRegisterValue("ax",new LongValue(temp_d));
				}
				else {
					env.getRegister().setRegisterValue("eax",new LongValue(temp_d));
				}
				env.getFlag().setZFlag(new BooleanValue(false));
				//change flag CF, PF, AF, SF, and OF
			}
		}
		//swap bit thanh ghi 32 [0...7]   [8...15]  [16...23] [24...31]
		// ket qua 				[24...31] [8....15] [16...23] [0....7]
		else if (inst.getName().startsWith("bswap") ){
			long temp_d = 0;
			long temp = 0;

			if ( dest.getClass().getSimpleName().equals("X86Register")) {
				d = env.getRegister().getRegisterValue(dest.toString());

				if (d != null && d instanceof LongValue) {
					temp_d = ((LongValue) d).getValue();
					temp = new AnalysisBit().SwapBit32(temp_d);
					env.getRegister().setRegisterValue(dest.toString(), new LongValue(temp));
				} else
					env.getRegister().setRegisterValue(dest.toString(), new SymbolValue(dest.toString()));
			}
		}

		/*** CBW ***/
		// AX =  AL (xet dau)
		else if (inst.getName().startsWith("cbw")){
			Value AL = env.getRegister().getRegisterValue("al");
			long temp_AL = ((LongValue)AL).getValue();
			long temp =0;

			temp = new AnalysisBit().MOVS(16, temp_AL);
			env.getRegister().setRegisterValue("ax", new LongValue(temp));
		}

		/*** CWDE ***/
		// EAX =  AX (xet dau)
		else if (inst.getName().startsWith("cwde")){
			Value AX = env.getRegister().getRegisterValue("ax");
			long temp_AX = ((LongValue) AX).getValue();
			long temp =0;

			temp = new AnalysisBit().MOVS(32, temp_AX);
			env.getRegister().setRegisterValue("eax", new LongValue(temp));
		}


		/*** CWD ***/
		//DX = bit dau cua AX
		else if (inst.getName().startsWith("cwd")){
			Value AX = env.getRegister().getRegisterValue("ax");
			long temp_AX = ((LongValue) AX).getValue();
			long temp = 0;

			temp = new AnalysisBit().CWD_CDQ(temp_AX);
			env.getRegister().setRegisterValue("dx", new LongValue(temp) );
		}

		/*** CDQ ***/
		//EDX = bit dau cua EAX
		else if (inst.getName().startsWith("cdq")){
			Value EAX = env.getRegister().getRegisterValue("eax");
			long temp_EAX = ((LongValue) EAX).getValue();
			long temp = 0;

			temp = new AnalysisBit().CWD_CDQ(temp_EAX);
			env.getRegister().setRegisterValue("edx", new LongValue(temp) );
		}

		/*** SHLD ***/
		//dich bit sang trai vs so bit la count
		else if (inst.getName().startsWith("shld")) {
			Operand count = inst.getOperand3();
			Value c = null;

			long temp_d = 0;
			temp_s = 0;
			long temp_c = 0;
			long result = 0;
			int get_bit = rule.getBitCount(inst);

			if (dest.getClass().getSimpleName().equals("X86Register")
					|| dest.getClass().getSimpleName().equals("X86RegisterPart")
					|| dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
				d = env.getRegister().getRegisterValue(dest.toString());
			}
			else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
				X86MemoryOperand t = env.getMemory().evaluateAddress((X86MemoryOperand) dest, env);
				if (!rule.checkAddressValid(env, t)) {
					// SEH Exploit
					System.out.println("SEH:" + path.getCurrentState().getLocation().toString());
					return rule.processSEH(path.getCurrentState());
				}
				d = env.getMemory().getMemoryValue((X86MemoryOperand) src, inst);
			}

			if (src.getClass().getSimpleName().equals("X86Register")
					|| src.getClass().getSimpleName().equals("X86RegisterPart")
					|| src.getClass().getSimpleName().equals("X86SegmentRegister")) {
				s = env.getRegister().getRegisterValue(src.toString());
			}

			if (count.getClass().getSimpleName().equals("X86Register")
					|| count.getClass().getSimpleName().equals("X86RegisterPart")
					|| count.getClass().getSimpleName().equals("X86SegmentRegister")) {
				c = env.getRegister().getRegisterValue(count.toString());
			}
			else if ( count.getClass().getSimpleName().equals("Immediate") )
			{
				c = new LongValue(Convert.convetUnsignedValue(((Immediate) count).getNumber().intValue(), rule.getBitCount(inst)));
			}

			temp_d = ((LongValue) d).getValue();
			temp_s = ((LongValue) s).getValue();
			temp_c = ((LongValue) c).getValue();

			if(temp_c > 0) {
				result = new AnalysisBit().SHLD(temp_d, temp_s, temp_c, get_bit);
				// PHONG: change here. ChangeFlag here
				// ------------------------------------------------------------------------------------
				if (temp_c < get_bit){
					long array_dest[] = new long[get_bit];
					long temp = temp_d;
					for (int i = 0; i < get_bit; i++)
						array_dest[i]=0;
					int i = 0;
					while(temp != 0) {
						array_dest[i] = temp % 2;
						temp = temp / 2;
						i++;
					}
					// change another flag
					LongValue destflag = new LongValue(result);
					LongValue srcflag = new LongValue(0);
					env.getFlag().changeFlagWithADD(destflag,srcflag,env,get_bit);
					// change OFlag here
					// change CFlag here
					BooleanValue cflag = new BooleanValue(array_dest[(int)(get_bit-temp_c)]!=0);
					env.getFlag().setCFlag(cflag);
				}
				//---------------------------------------------------------------------------------------
				if (dest.getClass().getSimpleName().equals("X86MemoryOperand"))
					env.getMemory().setMemoryValue((X86MemoryOperand)dest, new LongValue(result) , inst);
				else
					env.getRegister().setRegisterValue(dest.toString(),new LongValue(result));
			}
		}

		/*** SHRD ***/
		//dich bit sang trai vs so bit la count
		else if (inst.getName().startsWith("shrd")) {
			Operand count = inst.getOperand3();
			Value c = null;

			long temp_d = 0;
			temp_s = 0;
			long temp_c = 0;
			long result = 0;
			int get_bit = rule.getBitCount(inst);

			if (dest.getClass().getSimpleName().equals("X86Register")
					|| dest.getClass().getSimpleName().equals("X86RegisterPart")
					|| dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
				d = env.getRegister().getRegisterValue(dest.toString());
			}
			else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
				X86MemoryOperand t = env.getMemory().evaluateAddress((X86MemoryOperand) dest, env);
				if (!rule.checkAddressValid(env, t)) {
					// SEH Exploit
					System.out.println("SEH:" + path.getCurrentState().getLocation().toString());
					return rule.processSEH(path.getCurrentState());
				}
				d = env.getMemory().getMemoryValue((X86MemoryOperand) src, inst);
			}

			if (src.getClass().getSimpleName().equals("X86Register")
					|| src.getClass().getSimpleName().equals("X86RegisterPart")
					|| src.getClass().getSimpleName().equals("X86SegmentRegister")) {
				s = env.getRegister().getRegisterValue(src.toString());
			}

			if (count.getClass().getSimpleName().equals("X86Register")
					|| count.getClass().getSimpleName().equals("X86RegisterPart")
					|| count.getClass().getSimpleName().equals("X86SegmentRegister")) {
				c = env.getRegister().getRegisterValue(count.toString());
			}
			else if ( count.getClass().getSimpleName().equals("Immediate") )
			{
				c = new LongValue(Convert.convetUnsignedValue(((Immediate) count).getNumber().intValue(), rule.getBitCount(inst)));
			}


			if (d != null && d instanceof LongValue && s != null && s instanceof LongValue
					&& c != null && c instanceof LongValue) {
				temp_d = ((LongValue) d).getValue();
				temp_s = ((LongValue) s).getValue();
				temp_c = ((LongValue) c).getValue();

				if (temp_c > 0) {
					result = new AnalysisBit().SHRD(temp_d, temp_s, temp_c, get_bit);
					// PHONG: change here. ChangeFlag here
					// ------------------------------------------------------------------------------------
					if (temp_c < get_bit){
						long array_dest[] = new long[get_bit];
						long temp = temp_d;
						for (int i = 0; i < get_bit; i++)
							array_dest[i]=0;
						int i = 0;
						while(temp != 0) {
							array_dest[i] = temp % 2;
							temp = temp / 2;
							i++;
						}
						// change another flag
						LongValue destflag = new LongValue(result);
						LongValue srcflag = new LongValue(0);
						env.getFlag().changeFlagWithADD(destflag,srcflag,env,get_bit);
						// change OFlag here
						// change CFlag here
						BooleanValue cflag = new BooleanValue(array_dest[(int)(get_bit-1)]!=0);
						env.getFlag().setCFlag(cflag);
					}
					//---------------------------------------------------------------------------------------
					if (dest.getClass().getSimpleName().equals("X86MemoryOperand"))
						env.getMemory().setMemoryValue((X86MemoryOperand) dest, new LongValue(result), inst);
					else
						env.getRegister().setRegisterValue(dest.toString(), new LongValue(result));
				}
			}
		}

		else if (inst.getName().startsWith("in")) {
			//env.AddressPort(d,s);
			System.out.println("Input I/O port address into AL/AX/EAX.");
		}
		else if (inst.getName().startsWith("out")){
			//env.AddressPort(d,s);
			System.out.println("Output I/O port address into AL/AX/EAX.");
		}	else if (inst.getName().startsWith("btc")){
			long temp_d = 0;
			temp_s = 0;
			long result = 0;
			long CF = 0;
			int get_bit = rule.getBitCount(inst);

			if (dest.getClass().getSimpleName().equals("X86Register")
					|| dest.getClass().getSimpleName().equals("X86RegisterPart")
					|| dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
				d = env.getRegister().getRegisterValue(dest.toString());
			}
			else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
				X86MemoryOperand t = env.getMemory().evaluateAddress((X86MemoryOperand) dest, env);
				if (!rule.checkAddressValid(env, t)) {
					// SEH Exploit
					System.out.println("SEH:" + path.getCurrentState().getLocation().toString());
					return rule.processSEH(path.getCurrentState());
				}
				d = env.getMemory().getMemoryValue((X86MemoryOperand) dest, inst);
			}

			if (src.getClass().getSimpleName().equals("X86Register")
					|| src.getClass().getSimpleName().equals("X86RegisterPart")
					|| src.getClass().getSimpleName().equals("X86SegmentRegister")) {
				s = env.getRegister().getRegisterValue(src.toString());
			} else if (src.getClass().getSimpleName().equals("Immediate") )
			{
				s = new LongValue(Convert.convetUnsignedValue(((Immediate) src).getNumber().intValue(), opSize));
			}

			temp_d = ((LongValue) d).getValue();
			temp_s = ((LongValue) s).getValue();
			CF = new AnalysisBit().BT(temp_d, temp_s, get_bit);
			result = new AnalysisBit().BTC(temp_d, temp_s, get_bit);
			if (dest.getClass().getSimpleName().equals("X86MemoryOperand"))
				env.getMemory().setMemoryValue((X86MemoryOperand)dest, new LongValue(result) , inst);
			else
				env.getRegister().setRegisterValue(dest.toString(),new LongValue(result));
			if (CF == 1)
				env.getFlag().setCFlag(new BooleanValue(true));
			else
				env.getFlag().setCFlag(new BooleanValue(false));

		}

		/**BTR**/
		else if (inst.getName().startsWith("btr")){
			long temp_d = 0;
			temp_s = 0;
			long result = 0;
			long CF = 0;
			int get_bit = rule.getBitCount(inst);

			if (dest.getClass().getSimpleName().equals("X86Register")
					|| dest.getClass().getSimpleName().equals("X86RegisterPart")
					|| dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
				d = env.getRegister().getRegisterValue(dest.toString());
			}
			else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
				X86MemoryOperand t = env.getMemory().evaluateAddress((X86MemoryOperand) dest, env);
				if (!rule.checkAddressValid(env, t)) {
					// SEH Exploit
					System.out.println("SEH:" + path.getCurrentState().getLocation().toString());
					return rule.processSEH(path.getCurrentState());
				}
				d = env.getMemory().getMemoryValue((X86MemoryOperand) dest, inst);
			}

			if (src.getClass().getSimpleName().equals("X86Register")
					|| src.getClass().getSimpleName().equals("X86RegisterPart")
					|| src.getClass().getSimpleName().equals("X86SegmentRegister")) {
				s = env.getRegister().getRegisterValue(src.toString());
			}
			else if ( src.getClass().getSimpleName().equals("Immediate") )
			{
				s = new LongValue(Convert.convetUnsignedValue(((Immediate) src).getNumber().intValue(), rule.getBitCount(inst)));
			}

			temp_d = ((LongValue) d).getValue();
			temp_s = ((LongValue) s).getValue();
			CF = new AnalysisBit().BT(temp_d, temp_s, get_bit);
			result = new AnalysisBit().BTR(temp_d, temp_s, get_bit);
			if (dest.getClass().getSimpleName().equals("X86MemoryOperand"))
				env.getMemory().setMemoryValue((X86MemoryOperand) dest, new LongValue(result), inst);
			else
				env.getRegister().setRegisterValue(dest.toString(),new LongValue(result));
			if (CF == 1)
				env.getFlag().setCFlag(new BooleanValue(true));
			else
				env.getFlag().setCFlag(new BooleanValue(false));

		}

		/**BTS**/
		else if (inst.getName().startsWith("bts")){
			long temp_d = 0;
			temp_s = 0;
			long result = 0;
			long CF = 0;
			int get_bit = rule.getBitCount(inst);

			if (dest.getClass().getSimpleName().equals("X86Register")
					|| dest.getClass().getSimpleName().equals("X86RegisterPart")
					|| dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
				d = env.getRegister().getRegisterValue(dest.toString());
			}
			else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
				X86MemoryOperand t = env.getMemory().evaluateAddress((X86MemoryOperand) dest, env);
				if (!rule.checkAddressValid(env, t)) {
					// SEH Exploit
					System.out.println("SEH:" + path.getCurrentState().getLocation().toString());
					return rule.processSEH(path.getCurrentState());
				}
				d = env.getMemory().getMemoryValue((X86MemoryOperand) dest, inst);
			}

			if (src.getClass().getSimpleName().equals("X86Register")
					|| src.getClass().getSimpleName().equals("X86RegisterPart")
					|| src.getClass().getSimpleName().equals("X86SegmentRegister")) {
				s = env.getRegister().getRegisterValue(src.toString());
			}
			else if ( src.getClass().getSimpleName().equals("Immediate") )
			{
				s = new LongValue(Convert.convetUnsignedValue(((Immediate) src).getNumber().intValue(), rule.getBitCount(inst)));
			}

			temp_d = ((LongValue) d).getValue();
			temp_s = ((LongValue) s).getValue();
			CF = new AnalysisBit().BT(temp_d, temp_s, get_bit);
			result = new AnalysisBit().BTS(temp_d, temp_s, get_bit);
			if (dest.getClass().getSimpleName().equals("X86MemoryOperand"))
				env.getMemory().setMemoryValue((X86MemoryOperand)dest, new LongValue(result), inst);
			else
				env.getRegister().setRegisterValue(dest.toString(),new LongValue(result));
			if (CF == 1)
				env.getFlag().setCFlag(new BooleanValue(true));
			else
				env.getFlag().setCFlag(new BooleanValue(false));

		}

		/**BT**/
		//CF = (bit, bit of set)
		else if (inst.getName().startsWith("bt")){
			long temp_d = 0;
			temp_s = 0;
			long result = 0;
			int get_bit = rule.getBitCount(inst);

			if (dest.getClass().getSimpleName().equals("X86Register")
					|| dest.getClass().getSimpleName().equals("X86RegisterPart")
					|| dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
				d = env.getRegister().getRegisterValue(dest.toString());
			}
			else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
				X86MemoryOperand t = env.getMemory().evaluateAddress((X86MemoryOperand) dest, env);
				if (!rule.checkAddressValid(env, t)) {
					// SEH Exploit
					System.out.println("SEH:" + path.getCurrentState().getLocation().toString());
					return rule.processSEH(path.getCurrentState());
				}
				d = env.getMemory().getMemoryValue((X86MemoryOperand) dest, inst);
			}

			if (src.getClass().getSimpleName().equals("X86Register")
					|| src.getClass().getSimpleName().equals("X86RegisterPart")
					|| src.getClass().getSimpleName().equals("X86SegmentRegister")) {
				s = env.getRegister().getRegisterValue(src.toString());
			}
			else if ( src.getClass().getSimpleName().equals("Immediate") )
			{
				s = new LongValue(Convert.convetUnsignedValue(((Immediate) src).getNumber().intValue(), rule.getBitCount(inst)));
			}

			if (d != null && d instanceof LongValue && s != null && s instanceof LongValue) {
				temp_d = ((LongValue) d).getValue();
				temp_s = ((LongValue) s).getValue();

				result = new AnalysisBit().BT(temp_d, temp_s, get_bit);
				if (result == 1)
					env.getFlag().setCFlag(new BooleanValue(true));
				else
					env.getFlag().setCFlag(new BooleanValue(false));
			}
		} else if (inst.getName().startsWith("bsr")){
			long temp_d = 0;
			temp_s = 0;
			long result = 0;
			int get_bit = rule.getBitCount(inst);

			d = rule.getValueOperand(dest, env, inst);
			s = rule.getValueOperand(src, env, inst);	
			
			if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
				X86MemoryOperand t = env.getMemory().evaluateAddress((X86MemoryOperand) src, env);
				if (!rule.checkAddressValid(env, t)) {
					// SEH Exploit
					System.out.println("SEH:" + path.getCurrentState().getLocation().toString());
					return rule.processSEH(path.getCurrentState());
				}				
			}
			
			if (s == null || !(s instanceof LongValue))
				return curState;

			temp_d = ((LongValue) d).getValue();
			temp_s = ((LongValue) s).getValue();
			if(temp_s == 0){
				env.getFlag().setZFlag(new BooleanValue(true));
				//rule.setValueOperand(dest, new SymbolValue(dest.toString()), env, inst);
			} else{
				env.getFlag().setZFlag(new BooleanValue(false));
				result = new AnalysisBit().BSR(temp_s, get_bit);
				// Phong: change flag here
				// -------------------------------------------------------------------------------------
				for (int i = get_bit-1; i > result; i--){
					LongValue temp_value = new LongValue(i);
					env.getFlag().changeFlagWithDEC(temp_value,env,get_bit);
				}
				env.getRegister().setRegisterValue(dest.toString(),new LongValue(result));
				// -------------------------------------------------------------------------------------
			}
		} else if (inst.getName().startsWith("cpuid")){

			Value EAX = env.getRegister().getRegisterValue("eax");
			long temp_eax = ((LongValue) EAX).getValue();
			InfoCPU infoCPU = new InfoCPU();
			infoCPU.CPUID(temp_eax);
			long ebx = infoCPU.EBX;
			long ecx = infoCPU.ECX;
			long edx = infoCPU.EDX;

			env.getRegister().setRegisterValue("ebx",new LongValue(ebx));
			env.getRegister().setRegisterValue("ecx",new LongValue(ecx));
			env.getRegister().setRegisterValue("edx",new LongValue(edx));
		}
		else {
			Program.getProgram().getLog().error("Instruction not supported" + inst.getName() + " at " 
					+ curState.getLocation());
		}

		rule.generateNextInstruction(inst, path, pathList, true);
		return curState;
	}

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
			env.getMemory().setMemoryValue((X86MemoryOperand)dest, env.getMemory().getMemoryValue((X86MemoryOperand)src, inst), inst);
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
			if (((X86MemoryOperand)source).getBase() != null)
				base = ((X86MemoryOperand)source).getBase().toString();
		}

		if (load == null)
			return;
		Value df = env.getFlag().getDFlag();
		switch (opSize) {
			case 8:
				env.getRegister().setRegisterValue("al", load);

				if (df != null && df instanceof BooleanValue && ((BooleanValue)df).getValue()) {
					env.getRegister().sub(base, new LongValue(1));
				} else
					env.getRegister().add(base, new LongValue(1));

				break;
			case 16:
				env.getRegister().setRegisterValue("ax", load);

				if (df != null && df instanceof BooleanValue && ((BooleanValue)df).getValue()) {
					env.getRegister().sub(base, new LongValue(2));
				} else
					env.getRegister().add(base, new LongValue(2));

				break;
			case 32:
				env.getRegister().setRegisterValue("eax", load);

				if (df != null && df instanceof BooleanValue && ((BooleanValue)df).getValue()) {
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
			if (((X86MemoryOperand)dest).getBase() != null)
				base = ((X86MemoryOperand)dest).getBase().toString();
		}

		Value df = env.getFlag().getDFlag();
		switch (opSize) {
			case 8:
				store = env.getRegister().getRegisterValue("al");
				if (dest instanceof X86MemoryOperand) {
					env.getMemory().setMemoryValue((X86MemoryOperand)dest, store, inst);

					if (df != null && df instanceof BooleanValue && ((BooleanValue)df).getValue()) {
						env.getRegister().sub(base, new LongValue(1));
					} else
						env.getRegister().add(base, new LongValue(1));
				}
				break;
			case 16:
				store = env.getRegister().getRegisterValue("ax");
				if (dest instanceof X86MemoryOperand) {
					env.getMemory().setMemoryValue((X86MemoryOperand)dest, store, inst);

					if (df != null && df instanceof BooleanValue && ((BooleanValue)df).getValue()) {
						env.getRegister().sub(base, new LongValue(2));
					} else
						env.getRegister().add(base, new LongValue(2));
				}
				break;
			case 32:
				store = env.getRegister().getRegisterValue("eax");
				if (dest instanceof X86MemoryOperand) {
					env.getMemory().setMemoryValue((X86MemoryOperand)dest, store, inst);

					if (df != null && df instanceof BooleanValue && ((BooleanValue)df).getValue()) {
						env.getRegister().sub(base, new LongValue(4));
					} else
						env.getRegister().add(base, new LongValue(4));
				}
				break;
		}
	}
}
