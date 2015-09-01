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
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

import java.util.List;

public class X86MoveInterpreter {
	private APIHandle apiHandle = null;

	public BPState execute(X86MoveInstruction inst, BPPath path, List<BPPath> pathList, X86TransitionRule rule) {
		// TODO Auto-generated method stub
		this.apiHandle = rule.getAPIHandle();
		BPState curState = path.getCurrentState();
		Environment env = curState.getEnvironement();
		
		// Bien dung de xu ly CMOVcc
		boolean isSet_CMOVcc = false; // Xet dieu kien cua CMOVcc
		
		Operand dest = inst.getOperand1();
		Operand src = inst.getOperand2();
		int opSize = rule.getBitCount(inst);
		if (inst.getName().startsWith("movz")) {
			Value source = rule.getValueOperand(src, env, inst);
			if (source != null && source instanceof LongValue) {
				long t = ((LongValue) source).getValue();
				int opSize1 = rule.getBitCountOprand(src);
				LongValue temp = new LongValue(BitVector.extend(t, 0, opSize1, opSize));
				//System.out.println();
				rule.setValueOperand(dest, temp , env, inst);
			} else
				rule.setValueOperand(dest, source, env, inst);
		} else if (inst.getName().startsWith("movsx")) {
			Value source = rule.getValueOperand(src, env, inst);
			if (source != null && source instanceof LongValue) {
				long t = ((LongValue) source).getValue();
				int sign = BitVector.getMSB(t, opSize / 2);
				rule.setValueOperand(dest, new LongValue(BitVector.extend(t, sign, ((X86MemoryOperand)src).getDataType().bits(), opSize)), env, inst);
			} else
				rule.setValueOperand(dest, source, env, inst);
		} else if (inst.getName().startsWith("mov")) {
			// normal move
			Value source = rule.getValueOperand(src, env, inst);
			// System.out.println();
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
						Value v = env.getRegister().getRegisterValue(y.getBase().toString());
						if (v instanceof LongValue)
							b = (((LongValue) v).getValue() == 0);
					}
				}

				if (y.getSegmentRegister() != null && y.getSegmentRegister().toString() == "%fs" && b) {
					// System.out.println("SEH Exploit:"
					// + curState.getLocation().toString());
					if (src.getClass().getSimpleName().equals("X86Register")) {
						if (((X86Register) src).toString().equals("%esp")) {
							rule.setSEH(curState);
						}
						// PHONG:
						// 20150501:-------------------------------------------------
						else {
							rule.setSEHOther(curState, ((X86Register) src).toString());
						}
						// -----------------------------------------------------------------
					}
				} else {
					// X86MemoryOperand t = env.getMemory().evaluateAddress(
					// (X86MemoryOperand) dest, env);

					if (!rule.checkAddressValid(env, (X86MemoryOperand) dest)) {
						// SEH Exploit
						return rule.processSEH(curState);
					}

					rule.setValueOperand(dest, source, env, inst);
				}
			} else {
				// PHONG - 20150422
				rule.setValueOperand(dest, source, env, inst);
			}

		} else if (inst.getName().startsWith("xchg")) {
			// normal move
			Value temp = rule.getValueOperand(dest, env, inst);
			Value source = rule.getValueOperand(src, env, inst);			
			
			if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
				// X86MemoryOperand t = env.getMemory().evaluateAddress(
				// (X86MemoryOperand) dest, env);

				if (!rule.checkAddressValid(env, (X86MemoryOperand) dest)) {
					// SEH Exploit
					return rule.processSEH(curState);
				}
			}
			
			rule.setValueOperand(dest, source, env, inst);
			rule.setValueOperand(src, temp, env, inst);
		} 
		
		else if (inst.getName().startsWith("cmov")) {

			if (inst.getName().startsWith("cmovne") || inst.getName().equals("cmovnz")) {

				if (env.getFlag().getZFlag().equal(new BooleanValue(0))) {
					isSet_CMOVcc = true;
				}
			}
			// dieu kien ZF = 1 or CF = 1
			else if (inst.getName().startsWith("cmovbe") || inst.getName().equals("cmovna")) {

				if (env.getFlag().getCFlag().equal(new BooleanValue(1))
						|| env.getFlag().getZFlag().equal(new BooleanValue(1))) {
					isSet_CMOVcc = true;
				}
			}

			// dieu kien ZF = 0 and CF = 0
			else if (inst.getName().startsWith("cmova") || inst.getName().equals("cmovnbe")) {

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
			else if (inst.getName().startsWith("cmovnp") || inst.getName().equals("cmovpo")) {

				if (env.getFlag().getPFlag().equal(new BooleanValue(0))) {
					isSet_CMOVcc = true;
				}
			}

			// dieu kien: ZF = 1;
			else if (inst.getName().startsWith("cmove") || inst.getName().equals("cmovz")) {

				if (env.getFlag().getZFlag().equal(new BooleanValue(1))) {
					isSet_CMOVcc = true;
				}
			}

			// dieu kien: CF = 1;
			else if (inst.getName().startsWith("cmovb") || inst.getName().equals("cmovc")
					|| inst.getName().equals("cmovnae")) {

				if (env.getFlag().getCFlag().equal(new BooleanValue(1))) {
					isSet_CMOVcc = true;
				}
			}
			// dieu kien: PF = 1;
			else if (inst.getName().startsWith("cmovp") || inst.getName().equals("cmovpe")) {

				if (env.getFlag().getPFlag().equal(new BooleanValue(1))) {
					isSet_CMOVcc = true;
				}
			}

			// dieu kien: SF = OF;
			else if (inst.getName().startsWith("cmovge") || inst.getName().equals("cmovnl")) {

				//boolean SF, OF;

				Value sFlag = env.getFlag().getSFlag();
				sFlag = sFlag.evaluate(sFlag.getValueMap());
				//SF = ((BooleanValue) sFlag).getValue();

				Value oFlag = env.getFlag().getOFlag();
				oFlag = oFlag.evaluate(oFlag.getValueMap());
				//OF = ((BooleanValue) oFlag).getValue();

				if (sFlag.equal(oFlag)) {
					isSet_CMOVcc = true;
				}
			}

			// dieu kien: ZF = 0 and SF = OF;
			else if (inst.getName().startsWith("cmovg") || inst.getName().equals("cmovnle")) {

				//boolean ZF, SF, OF;

				Value zFlag = env.getFlag().getZFlag();
				zFlag = zFlag.evaluate(zFlag.getValueMap());
				//ZF = ((BooleanValue) zFlag).getValue();

				Value sFlag = env.getFlag().getSFlag();
				sFlag = sFlag.evaluate(sFlag.getValueMap());
				//SF = ((BooleanValue) sFlag).getValue();

				Value oFlag = env.getFlag().getOFlag();
				oFlag = oFlag.evaluate(oFlag.getValueMap());
				//OF = ((BooleanValue) oFlag).getValue();

				if (zFlag.equal(new BooleanValue(false)) && (sFlag.equal(oFlag))) {
					isSet_CMOVcc = true;
				}
			}

			// dieu kien: ZF = 1 or SF != OF;
			else if (inst.getName().startsWith("cmovle") || inst.getName().equals("cmovng")) {

				//boolean ZF, SF, OF;

				Value zFlag = env.getFlag().getZFlag();
				zFlag = zFlag.evaluate(zFlag.getValueMap());
				//ZF = ((BooleanValue) zFlag).getValue();

				Value sFlag = env.getFlag().getSFlag();
				sFlag = sFlag.evaluate(sFlag.getValueMap());
				//SF = ((BooleanValue) sFlag).getValue();

				Value oFlag = env.getFlag().getOFlag();
				oFlag = oFlag.evaluate(oFlag.getValueMap());
				//OF = ((BooleanValue) oFlag).getValue();

				if (zFlag.equal(new BooleanValue(true)) || !(sFlag.equal(oFlag))) {
					isSet_CMOVcc = true;
				}
			}

			// dieu kien: SF != OF;
			else if (inst.getName().startsWith("cmovl") || inst.getName().equals("cmovnge")) {

				//boolean SF, OF;

				Value sFlag = env.getFlag().getSFlag();
				sFlag = sFlag.evaluate(sFlag.getValueMap());
				//SF = ((BooleanValue) sFlag).getValue();

				Value oFlag = env.getFlag().getOFlag();
				oFlag = oFlag.evaluate(oFlag.getValueMap());
				//OF = ((BooleanValue) oFlag).getValue();

				if (!(sFlag.equal(oFlag))) {
					isSet_CMOVcc = true;
				}
			}

			// dieu kien: OF = 1;
			else if (inst.getName().startsWith("cmovo")) {

				if (env.getFlag().getOFlag().equal(new BooleanValue(true))) {
					isSet_CMOVcc = true;
				}
			}

			// dieu kien: SF = 1;
			else if (inst.getName().startsWith("cmovs")) {

				if (env.getFlag().getSFlag().equal(new BooleanValue(true))) {
					isSet_CMOVcc = true;
				}
			}

			if (isSet_CMOVcc == true) {
				Value s = null;
				//long temp_s;
				
				if (src.getClass().getSimpleName().equals("X86Register")
						|| src.getClass().getSimpleName().equals("X86RegisterPart")
						|| src.getClass().getSimpleName().equals("X86SegmentRegister")) {
					s = env.getRegister().getRegisterValue(src.toString());
				} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
					// X86MemoryOperand t = env.getMemory().evaluateAddress(
					// (X86MemoryOperand) src, env);

					if (!rule.checkAddressValid(env, (X86MemoryOperand) dest)) {
						// SEH Exploit
						// System.out.println("SEH:"
						// + path.getCurrentState().getLocation().toString());
						return rule.processSEH(path.getCurrentState());
					}

					s = env.getMemory().getMemoryValue((X86MemoryOperand) src, inst);
				}
				
				env.getRegister().setRegisterValue(dest.toString(), s);
				isSet_CMOVcc = false;
				// Modified by Khanh: MSSV 51101594
				/*if (s instanceof LongValue) {
					temp_s = ((LongValue) s).getValue();
					env.getRegister().setRegisterValue(dest.toString(), new LongValue(temp_s));
					isSet_CMOVcc = false;
				}
				// Modified by Khanh: MSSV 51101594
				else{
					Program.getProgram().setLog("CMOVcc with Symbol Value at " + curState.getLocation());
					rule.generateNextInstruction(inst, path, pathList, true);
					return curState;
				}*/
			}

		}
		else {
			Program.getProgram().getLog().error("Instruction not supported" + inst + " at " + curState.getLocation());
		}

		rule.generateNextInstruction(inst, path, pathList, true);
		return curState;
	}

}
