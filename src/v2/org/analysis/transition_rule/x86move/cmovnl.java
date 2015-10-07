package v2.org.analysis.transition_rule.x86move;

import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.X86MoveStub;
import v2.org.analysis.value.Value;

public class cmovnl extends X86MoveStub {

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub
		//boolean SF, OF;

		Value sFlag = env.getFlag().getSFlag();
		sFlag = sFlag.evaluate(sFlag.getValueMap());
		//SF = ((BooleanValue) sFlag).getValue();

		Value oFlag = env.getFlag().getOFlag();
		oFlag = oFlag.evaluate(oFlag.getValueMap());
		//OF = ((BooleanValue) oFlag).getValue();

		boolean isSet_CMOVcc = false;
		if (sFlag.equal(oFlag)) {
			isSet_CMOVcc  = true;
		}
		if (isSet_CMOVcc == true) {
			Value s = null;
			// long temp_s;

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
		}
		return null;
	}

}
