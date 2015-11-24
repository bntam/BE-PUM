package v2.org.analysis.transition_rule.x86instruction;

import java.math.BigDecimal;
import java.math.MathContext;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.BigDecimalCalculations;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.DoubleValue;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class fpatan extends X86InstructionStub {

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub
		//System.out.println("Instruction: " + inst.getName());
		Value st0 = null;
		Value st1 = null;
		double result = Math.PI;
		double temp = 2.0;
		st0 = env.getFPUregister().getFPURegisterValue("st0");
		st1 = env.getFPUregister().getFPURegisterValue("st1");
		// ST0 == null || ST1 == null
		if (st0 == null || st1 == null) {
			env.getFPUregister().setFPURegisterValue("st1", new DoubleValue(Double.NaN));						
			env.getFST().changeUnderflow();
		}
		else {
			double temp_st0 = ((DoubleValue) st0).getValue();
			double temp_st1 = ((DoubleValue) st1).getValue();			
			// ST0 == NaN || ST1 == NaN
			if (Double.isNaN(temp_st0) || Double.isNaN(temp_st1)){
				env.getFPUregister().setFPURegisterValue("st1", new DoubleValue(Double.NaN));				
				env.getFST().setC1(new BooleanValue(false));							
			}
			// ST0 == Infinity, ST1 == Infinity
			else if (Double.isInfinite(temp_st0) && Double.isInfinite(temp_st1)){				
				// ST0 = -Infinity, ST1 = -Infinity
				// => -3/4 pi
				if (temp_st0 < 0 && temp_st1 < 0) {					
					temp = -0.75;
				}
				// ST0 = -Infinity, ST1 = +Infinity
				// => 3/4 pi
				else if (temp_st0 < 0 && temp_st1 > 0){
					temp = 0.75;
				}
				// ST0 = +Infinity, ST1 = -Infinity
				// => -1/4 pi
				else if (temp_st0 > 0 && temp_st1 < 0){
					temp = -0.25;
				}
				// ST0 = +Infinity, ST1 = +Infinity
				// => 1/4 pi
				else {
					temp =0.25;
				}				
			}
			// ST1 == -Infinity && ST0 = Finity
			// ST1 < 0 && ST0 >= 0
			// => -1/2 pi
			else if((Double.isInfinite(temp_st1) && temp_st1 < 0 && Double.isFinite(temp_st0)) 
					|| (Double.isFinite(temp_st1) && temp_st1 < 0 && temp_st0 >= 0) ){
				temp = -0.5;				
			}	
			// ST1 == +Infinity && ST0 = Finity
			// ST1 > 0 && ST0 >=0
			// => 1/2 pi
			else if ((Double.isInfinite(temp_st1) && temp_st1 > 0 && Double.isFinite(temp_st0)) 
					|| (Double.isFinite(temp_st1) && temp_st1 > 0 && temp_st0 >= 0)){
				temp = 0.5;
			}
			// ST1 == 0, ST0 >=0
			// => 0pi
			else if (temp_st1 == 0 && temp_st0 >= 0){
				temp = 0;
			}
			// ST1 < 0 (-F) && ST0 <0
			// => -pi
			else if (Double.isFinite(temp_st1) &&  temp_st1 < 0 && temp_st0 <0){
				temp = -1.0;
			}
			// ST1 >= 0 (F) && ST0 <0
			// => +pi
			else if (Double.isFinite(temp_st1) &&  temp_st1 >= 0 && temp_st0 <0) {
				temp = 1.0;
			}
		}
		// Tinh toan arctan
		if (temp != 2.0) {
			result = temp * Math.PI;
			env.getFPUregister().setFPURegisterValue("st1", new DoubleValue(result));
			BigDecimalCalculations BigDec = new BigDecimalCalculations();
			BigDecimal exact_result = new BigDecimal(temp);
			exact_result = exact_result.multiply(BigDec.getPI(), MathContext.DECIMAL128);
			env.getFST().changeResult(result, exact_result);			
		}
		env.getFPUregister().POP();		
		Value top = env.getFST().getTOP();		
		env.getFST().changePOP(((LongValue) top).getValue());
		//System.out.println("Value FST: " + Convert.longToHex(env.getFST().getValueFST()));
		return null;
	}

}
