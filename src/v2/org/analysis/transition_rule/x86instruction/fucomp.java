package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class fucomp extends X86InstructionStub {

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub
		long temp_top;
		Value top = env.getFST().getTOP();
		top = top.evaluate(top.getValueMap());
		temp_top = ((LongValue) top).getValue();
		env.getFST().changePOP(temp_top);
		Value st0 = env.getFPUregister().getFPURegisterValue("st0");
		// Opreand is ST(1)
		if (dest.getClass().getSimpleName().equals("X86FloatRegister")) {				
			d = env.getFPUregister().getFPURegisterValue(dest.toString());				
		}			
		env.getFST().changeFCOM(st0,d);	
		env.getFPUregister().POP();		
		return null;
	}

}
