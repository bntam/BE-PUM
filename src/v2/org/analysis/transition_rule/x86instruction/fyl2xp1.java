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

public class fyl2xp1 extends X86InstructionStub {

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
			// ST0 <= -1.0
			// => PoP, PE = true
			else if (temp_st0 <= -1.0){
				env.getFPUregister().setFPURegisterValue("st1", new DoubleValue(temp_st0));
				env.getFST().setPE(new BooleanValue(true));
			}
			// ST0 == 0, ST1 = infinity
			// => IE
			else if (temp_st0 == 0 && Double.isInfinite(temp_st1)){
				env.getFST().setIE(new BooleanValue(true));
				env.getFST().setC1(new BooleanValue(false));
				env.getFPUregister().setFPURegisterValue("st1", new DoubleValue(Double.NaN));	
			}
			// ST0 == 0 || ST1 == 0 
			// => ST1 = 0
			else if (temp_st0 == 0 || temp_st1 == 0){
				env.getFPUregister().setFPURegisterValue("st1", new DoubleValue(0.0));
			}
			// ST0 = finity
			else {
				double result = temp_st1 * log2(temp_st0 + 1);
				env.getFPUregister().setFPURegisterValue("st1", new DoubleValue(result));
				BigDecimalCalculations BigDec = new BigDecimalCalculations();
				BigDecimal temp_0 = new BigDecimal(temp_st0 + 1);
				BigDecimal temp_1 = new BigDecimal(temp_st1);
				BigDecimal exact_result = temp_1.multiply(BigDec.log2(temp_0), MathContext.DECIMAL128);
				env.getFST().changeResult(result, exact_result);
				System.out.println(result);
				System.out.println(exact_result);
			}
		}
		env.getFPUregister().POP();		
		Value top = env.getFST().getTOP();		
		env.getFST().changePOP(((LongValue) top).getValue());
		//System.out.println("Value FST: " + Convert.longToHex(env.getFST().getValueFST()));
		return null;
	}
	public static double log2(double n)
	{
	    return (Math.log(n) / Math.log(2));
	}

}
