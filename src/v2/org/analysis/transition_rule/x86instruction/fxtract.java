package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.DoubleValue;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class fxtract extends X86InstructionStub {

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub
		//System.out.println("Instruction: " + inst.getName());
		Value st0 = env.getFPUregister().getFPURegisterValue("st0");	
		// ST0 == null || overflow
		if (checkOverflow() || st0 == null ){
			env.getFPUregister().setFPURegisterValue("st0", new DoubleValue(Double.NaN));	
			env.getFPUregister().FLD(Double.NaN);	
			env.getFST().changeUnderflow();
			changefixFLD();			
			if (checkOverflow()){
				env.getFST().setC1(new BooleanValue(true));
			}
		}
		else {
			double temp_st0 = ((DoubleValue) st0).getValue();
			// ST0 == NaN
			if (Double.isNaN(temp_st0)) {
				env.getFPUregister().FLD(Double.NaN);	
				env.getFST().setC1(new BooleanValue(false));
				changefixFLD();				
			}
			// ST0 = infinity > 0
			else if (Double.isInfinite(temp_st0) && (temp_st0 > 0)) {
				env.getFPUregister().setFPURegisterValue("st0", new DoubleValue(Double.POSITIVE_INFINITY));	
				env.getFPUregister().FLD(Double.POSITIVE_INFINITY);	
				env.getFST().setC1(new BooleanValue(false));
				changefixFLD();				
			}
			// ST0 = infinity < 0
			else if (Double.isInfinite(temp_st0) && (temp_st0 < 0)){
				env.getFPUregister().setFPURegisterValue("st0", new DoubleValue(Double.POSITIVE_INFINITY));	
				env.getFPUregister().FLD(temp_st0);					
				env.getFST().setC1(new BooleanValue(false));
				changefixFLD();
			}			
			// ST0 == 0
			else if (temp_st0 == 0){
				env.getFPUregister().setFPURegisterValue("st0", new DoubleValue(Double.POSITIVE_INFINITY));	
				env.getFPUregister().FLD(temp_st0);					
				env.getFST().setC1(new BooleanValue(false));
				env.getFST().setZE(new BooleanValue(true));
				changefixFLD();
			}
			// ST0 = finity
			else {
				double temp = getMantissa(temp_st0);
				env.getFPUregister().setFPURegisterValue("st0", new DoubleValue(Math.getExponent(temp_st0)));
				env.getFPUregister().FLD(temp);	
				changefixFLD();
			}
		}
		//System.out.println("Value FST: " + Convert.longToHex(env.getFST().getValueFST()));
		return null;
	}
	
	public static double getMantissa(double x) {
        int exponent = Math.getExponent(x);
        double result = x / Math.pow(2, exponent);
        return result;
    }
	
	public void changefixFLD(){
		long temp_top;
		Value top = env.getFST().getTOP();
		top = top.evaluate(top.getValueMap());
		temp_top = ((LongValue) top).getValue();		
		env.getFST().changeFLD(temp_top);
	}
	
	public boolean checkOverflow(){	
		int overflow = env.getFST().getOverflow();		
		if (overflow >= 7) {			
			return true;
		} else {
			return false;
		}
	}

}
