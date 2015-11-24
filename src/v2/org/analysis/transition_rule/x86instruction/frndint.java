package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.DoubleValue;
import v2.org.analysis.value.Value;

public class frndint extends X86InstructionStub {

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub
		//System.out.println("Instruction: " + inst.getName());
		Value st0 = env.getFPUregister().getFPURegisterValue("st0");	
		// ST0 == Null
		if (st0 == null){
			env.getFPUregister().setFPURegisterValue("st0", new DoubleValue(Double.NaN));
			env.getFST().changeUnderflow();
		}
		else {
			double temp_st0 = ((DoubleValue) st0).getValue();
			// ST0 == NaN || Infinity
			if (Double.isNaN(temp_st0) || Double.isInfinite(temp_st0)) {				
				env.getFST().setC1(new BooleanValue(false));
			}
			// ST0 == finity
			else {
				Long temp = Math.round(temp_st0);
				long result = Long.valueOf(temp.intValue());
				double temp_result = result;
				env.getFPUregister().setFPURegisterValue("st0", new DoubleValue(result));
				if (Math.abs(temp_result) < Math.abs(temp_st0)) {
					env.getFST().setPE(new BooleanValue(true));
				} else if (Math.abs(temp_result) > Math.abs(temp_st0)) {
					env.getFST().setPE(new BooleanValue(true));
					env.getFST().setC1(new BooleanValue(true));
				}
			}
		}
		//System.out.println("Value FST: " + Convert.longToHex(env.getFST().getValueFST()));
		return null;
	}

}
