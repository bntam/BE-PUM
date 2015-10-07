package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.AnalysisBit;
import v2.org.analysis.transition_rule.X86InstructionStub;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class cdq extends X86InstructionStub {

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub
		Value EAX = env.getRegister().getRegisterValue("eax");
		long temp_EAX = ((LongValue) EAX).getValue();
		long temp = 0;

		temp = new AnalysisBit().CWD_CDQ(temp_EAX);
		env.getRegister().setRegisterValue("edx", new LongValue(temp));
		
		return null;
	}

}
