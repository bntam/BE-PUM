package v2.org.analysis.transition_rule.x86move;

import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.X86MoveStub;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.Value;

public class cmovna extends X86MoveStub {

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub
		boolean isSet_CMOVcc = false;
		if (env.getFlag().getCFlag().equal(new BooleanValue(1)) || env.getFlag().getZFlag().equal(new BooleanValue(1))) {
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
