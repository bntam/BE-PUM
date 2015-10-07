package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.X86InstructionStub;

public class in extends X86InstructionStub {

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub
		System.out.println("Input I/O port address into AL/AX/EAX.");
		
		return null;
	}

}
