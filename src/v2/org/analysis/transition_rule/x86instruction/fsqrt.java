package v2.org.analysis.transition_rule.x86instruction;

import java.math.BigDecimal;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.BigDecimalCalculations;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.DoubleValue;
import v2.org.analysis.value.Value;

public class fsqrt extends X86InstructionStub {

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub
		//System.out.println("Instruction: " + inst.getName());
		Value st0 = null;
		st0 = env.getFPUregister().getFPURegisterValue("st0");
		// ST0 == null
		if (st0 == null) {
			env.getFPUregister().setFPURegisterValue("st0", new DoubleValue(Double.NaN));
			env.getFST().changeUnderflow();
		} else {
			double temp_st0 = ((DoubleValue) st0).getValue();;
			// ST0 == NaN
			if (Double.isNaN(temp_st0)) {
				env.getFPUregister().setFPURegisterValue("st0", new DoubleValue(Double.NaN));
				env.getFST().setC1(new BooleanValue(false));
			}
			// ST0  = -infinity || ST0 < 0
			else if ( (Double.isInfinite(temp_st0) && (temp_st0 < 0)) || (temp_st0 < 0) ) {
				env.getFPUregister().setFPURegisterValue("st0", new DoubleValue(Double.NaN));
				env.getFST().setIE(new BooleanValue(true));
			}
			// ST0 = + infinity
			else if (Double.isInfinite(temp_st0) && (temp_st0 > 0)){
				env.getFPUregister().setFPURegisterValue("st0", new DoubleValue(Double.POSITIVE_INFINITY));
			}
			// ST0 = finity 
			else {
				double sqrt = Math.sqrt(temp_st0);
				env.getFPUregister().setFPURegisterValue("st0", new DoubleValue(sqrt));				
				BigDecimalCalculations BigDecCalcu = new BigDecimalCalculations();
				BigDecimal temp_sqrt = new BigDecimal(temp_st0);
				BigDecimal exact_sqrt = BigDecCalcu.sqrt(temp_sqrt, 20);
				env.getFST().changeResult(sqrt,exact_sqrt);
				System.out.println(sqrt);
				System.out.println(exact_sqrt);
			}
		}
		//System.out.println("Value FST: " + Convert.longToHex(env.getFST().getValueFST()));
		return null;
	}

}
