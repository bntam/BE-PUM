package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class fldl2e extends X86InstructionStub {

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub
		//System.out.println("Instruction: " + inst.getName());
		//System.out.println("Instruction: " +  log2(Math.E));
		//System.out.println("Instruction: " +  Math.l);
		
		long temp_top;
		Value top = env.getFST().getTOP();
		top = top.evaluate(top.getValueMap());
		temp_top = ((LongValue) top).getValue();
		
		env.getFST().changFLD(temp_top);
		if (((BooleanValue) env.getFST().getC1()).getValue()) {
			env.getFPUregister().FLD(Double.NaN);
		} else {
			env.getFPUregister().FLD(log2(Math.E));
		}
		
		return null;
	}
	
	public static double log2(double n)
	{
	    return (Math.log(n) / Math.log(2));
	}


}
