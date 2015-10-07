package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.X86InstructionStub;

public class out extends X86InstructionStub {

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub
		System.out.println("Output I/O port address into AL/AX/EAX.");
		
		return null;
	}

}
