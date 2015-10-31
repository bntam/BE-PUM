package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class fcompp extends X86InstructionStub {

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub
		//System.out.println("Instruction: " + inst.getName());
		long temp_top;
		Value top = env.getFST().getTOP();
		top = top.evaluate(top.getValueMap());
		temp_top = ((LongValue) top).getValue();
		env.getFST().changePOP(temp_top);		
		top = env.getFST().getTOP().evaluate(top.getValueMap());
		temp_top = ((LongValue) top).getValue();
		env.getFST().changePOP(temp_top);	
		Value st0 = env.getFPUregister().getFPURegisterValue("st0");
		Value st1 = env.getFPUregister().getFPURegisterValue("st1");			
		env.getFST().changeFCOM(st0,st1);	
		env.getFPUregister().POP();	
		env.getFPUregister().POP();	
		//System.out.println("Value FST: " + Convert.longToHex(env.getFST().getValueFST()));
		return null;
	}

}
