package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.X86InstructionStub;
import v2.org.analysis.value.BooleanValue;

public class cld extends X86InstructionStub {

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub
		env.getFlag().setDFlag(new BooleanValue(0));
		
		return null;
	}

}
