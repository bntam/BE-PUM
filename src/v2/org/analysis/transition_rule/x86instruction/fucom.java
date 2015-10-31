package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.complement.Convert;
import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.Value;

public class fucom extends X86InstructionStub {

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub
		System.out.println("Instruction: " + inst.getName());
		Value st0 = env.getFPUregister().getFPURegisterValue("st0");
		// Opreand is ST(1)
		if (dest.getClass().getSimpleName().equals("X86FloatRegister")) {				
			d = env.getFPUregister().getFPURegisterValue(dest.toString());				
		}			
		env.getFST().changeFCOM(st0,d);	
		System.out.println("Value FST: " + Convert.longToHex(env.getFST().getValueFST()));
		return null;
	}

}
