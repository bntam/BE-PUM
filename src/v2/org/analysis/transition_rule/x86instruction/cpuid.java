package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.InfoCPU;
import v2.org.analysis.transition_rule.X86InstructionStub;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class cpuid extends X86InstructionStub {

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub
		Value EAX = env.getRegister().getRegisterValue("eax");
		long temp_eax = ((LongValue) EAX).getValue();
		InfoCPU infoCPU = new InfoCPU();
		infoCPU.CPUID(temp_eax);
		long ebx = infoCPU.EBX;
		long ecx = infoCPU.ECX;
		long edx = infoCPU.EDX;

		env.getRegister().setRegisterValue("ebx", new LongValue(ebx));
		env.getRegister().setRegisterValue("ecx", new LongValue(ecx));
		env.getRegister().setRegisterValue("edx", new LongValue(edx));
		
		return null;
	}

}
