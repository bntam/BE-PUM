package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class fld1 extends X86InstructionStub {

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub
		long temp_top;
		Value top = env.getFST().getTOP();
		top = top.evaluate(top.getValueMap());
		temp_top = ((LongValue) top).getValue();
		
		env.getFST().changFLD(temp_top);
		if (((BooleanValue) env.getFST().getC1()).getValue()) {
			env.getFPUregister().FLD(Double.NaN);
		} else {
			env.getFPUregister().FLD(1.0);
		}
		//System.out.println("Value FST: " +  Convert.longToHex(env.getFST().getValueFST()));
		return null;
	}

}
