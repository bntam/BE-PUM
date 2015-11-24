package v2.org.analysis.transition_rule.x86instruction;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.DoubleValue;
import v2.org.analysis.value.Value;

public class fscale extends X86InstructionStub {

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub
		//System.out.println("Instruction: " + inst.getName());
		Value st0 = null;
		Value st1 = null;
		st0 = env.getFPUregister().getFPURegisterValue("st0");
		st1 = env.getFPUregister().getFPURegisterValue("st1");
		// ST0 == null || ST1 == null
		if (st0 == null || st1 == null) {
			env.getFPUregister().setFPURegisterValue("st0", new DoubleValue(Double.NaN));						
			env.getFST().changeUnderflow();
		}
		else {
			double temp_st0 = ((DoubleValue) st0).getValue();
			double temp_st1 = ((DoubleValue) st1).getValue();
			// ST0 == NaN || ST1 == NaN			
			if ( Double.isNaN(temp_st0) || Double.isNaN(temp_st1)){
				env.getFPUregister().setFPURegisterValue("st0", new DoubleValue(Double.NaN));				
				env.getFST().setC1(new BooleanValue(false));							
			}
			// ST1 == -Infinity, ST0 = Infinity
			// ST1 = + Infinity, ST0 = 0
			// =>IE
			else if ((Double.isInfinite(temp_st0) && Double.isInfinite(temp_st1) && temp_st1 < 0)
					 || (temp_st0 == 0 && Double.isInfinite(temp_st1) && temp_st1 > 0)) {
				env.getFPUregister().setFPURegisterValue("st0", new DoubleValue(Double.NaN));				
				env.getFST().setC1(new BooleanValue(false));		
				env.getFST().setIE(new BooleanValue(true));
			}
			// ST1 != -Infinity, ST0 = +Infinity
			// ST1 = +Infinity, ST0 = Finity > 0
			// => ST0 = +Infinity
			else if ((Double.isInfinite(temp_st0) && temp_st0 > 0) 
					|| (Double.isFinite(temp_st0) && temp_st0 > 0 && Double.isInfinite(temp_st1) && temp_st1 > 0)){
				env.getFPUregister().setFPURegisterValue("st0", new DoubleValue(Double.POSITIVE_INFINITY));				
			}
			// ST1 != -Infinity, ST0 = -Infinity
			// ST1 = +Infinity, ST0 = Finity < 0
			// => ST0 = -Infinity
			else if ((Double.isInfinite(temp_st0) && temp_st0 < 0) 
					|| (Double.isFinite(temp_st0) && temp_st0 < 0 && Double.isInfinite(temp_st1) && temp_st1 > 0)){
				env.getFPUregister().setFPURegisterValue("st0", new DoubleValue(Double.NEGATIVE_INFINITY));				
			}
			
			// ST0 = 0
			// ST1 = -Infinity && ST0 = finity
			// => ST0 = 0
			else if ((temp_st0 == 0) || 
					(Double.isFinite(temp_st0) && Double.isInfinite(temp_st1)) && temp_st1 < 0){
				env.getFPUregister().setFPURegisterValue("st0", new DoubleValue(0.0));		
			}
			// ST0 = Finity, ST1 = Finity
			// => ST0 = 2*roudtozero(ST1)
			else if (Double.isFinite(temp_st0) && Double.isFinite(temp_st1)){
				double result = temp_st0 * 2 * round_toword_zero(temp_st1);
				env.getFPUregister().setFPURegisterValue("st0", new DoubleValue(result));		
				BigDecimal big_2_st0 = new BigDecimal(temp_st0 * 2);
				BigDecimal big_round_st1 = new BigDecimal(round_toword_zero(temp_st1));
				BigDecimal exact_result = big_2_st0.multiply(big_round_st1, MathContext.DECIMAL128);
				env.getFST().changeResult(result, exact_result);
			}			
		}
		//System.out.println("Value FST: " + Convert.longToHex(env.getFST().getValueFST()));
		return null;
	}
	
	public double round_toword_zero(double x) {
		BigDecimal value = new BigDecimal(x);
		value = value.setScale(0, RoundingMode.DOWN);
		double result = value.doubleValue();
		return result;
	}

}
