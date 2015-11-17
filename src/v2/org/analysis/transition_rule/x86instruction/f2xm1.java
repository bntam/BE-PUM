package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.DoubleValue;
import v2.org.analysis.value.Value;

public class f2xm1 extends X86InstructionStub {

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub
		//System.out.println("Instruction: " + inst.getName());
		Value st0 = env.getFPUregister().getFPURegisterValue("st0");
		if (st0 == null) {
			env.getFPUregister().FLD(Double.NaN);
		} else {
			double t_st0 = ((DoubleValue) st0).getValue();
			if (t_st0 >= -1.0 && t_st0 < 1.0) {
				double result = Math.pow(2, t_st0) - 1;
				env.getFPUregister().setFPURegisterValue("st0", new DoubleValue(result));

				if (t_st0 != result) {					
					env.getFST().changeF2XM1(result, t_st0);
				}
			} else {
				boolean c1 = ((BooleanValue) env.getFST().getC1()).getValue();
				if (c1 == true) {
					env.getFST().setC1(new BooleanValue(false));
				}
			}
		}
		//System.out.println("Value FST: " + Convert.longToHex(env.getFST().getValueFST()));
		return null;
	}

}
