package v2.org.analysis.transition_rule.x86instruction;

import java.math.BigDecimal;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.DoubleValue;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class faddp extends X86InstructionStub {

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub
		//System.out.println("Instruction: " + inst.getName());
		Value st0 = env.getFPUregister().getFPURegisterValue("st0");
		Value st1 = env.getFPUregister().getFPURegisterValue("st1");
		long temp_top;
		Value top = env.getFST().getTOP();
		top = top.evaluate(top.getValueMap());
		temp_top = ((LongValue) top).getValue();
		env.getFST().changeFADDP(temp_top);
		if (st0 == null || st1 == null) {			
			env.getFPUregister().FADDP(Double.NaN);
			env.getFST().changeUnderflow();			
		}
		else{
			double temp_st0 = ((DoubleValue) st0).getValue();
			double temp_st1 = ((DoubleValue) st1).getValue();
			if (Double.isNaN(temp_st0) || Double.isNaN(temp_st1)){
				env.getFPUregister().FADDP(Double.NaN);
				env.getFST().changeUnderflow();	
			}
			else {
				double result = temp_st0 + temp_st1;
				BigDecimal x = new BigDecimal(temp_st0);
				BigDecimal y = new BigDecimal(temp_st1);
				BigDecimal exact_result = x.add(y);
				env.getFPUregister().FADDP(result);		
				env.getFST().changeFADD(result, exact_result);							
			}
		}
		//System.out.println("Value FST: " + Convert.longToHex(env.getFST().getValueFST()));
		return null;
	}

}
