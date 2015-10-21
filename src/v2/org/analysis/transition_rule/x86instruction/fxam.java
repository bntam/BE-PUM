package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.Value;

public class fxam extends X86InstructionStub {

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub		
		Value st0 = env.getFPUregister().getFPURegisterValue("ST0");			
		env.getFST().changeFXAM(st0);
		return null;
	}
}
