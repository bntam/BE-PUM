package v2.org.analysis.transition_rule.x86instruction;

import java.math.BigDecimal;
import java.math.MathContext;

import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.complement.Convert;
import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.DoubleValue;
import v2.org.analysis.value.Value;

public class fdivr extends X86InstructionStub {

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub
		System.out.println("Instruction: " + inst.getName());	
		
		// Xet truong hop so chia la Null, NaN, Infinity
		Value divisor = null; 
		Value divided = null;
		String str_divisor = "";		
		// 1 operand, ST0 is divisor, dest is divided				
		if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
			if (!rule.checkAddressValid(env, (X86MemoryOperand) dest)) {
				// SEH Exploit
				System.out.println("SEH:" + path.getCurrentState().getLocation().toString());
				return rule.processSEH(path.getCurrentState());
			}
			divisor = env.getFPUregister().getFPURegisterValue("st0");
			str_divisor = "st0";
			divided = env.getMemory().getMemoryValue((X86MemoryOperand) dest, inst); 									
		}		
		
		// 2 operand
		if (dest.getClass().getSimpleName().equals("X86FloatRegister")) {			
			//d = env.getRegister().getRegisterValue(dest.toString());
			divisor = env.getFPUregister().getFPURegisterValue(dest.toString());
			str_divisor = dest.toString();
			divided = env.getFPUregister().getFPURegisterValue(src.toString());			
		}
		
		// ST0 || divisor == null, divided with NULL
		if (divisor == null || divided == null) {
			env.getFPUregister().setFPURegisterValue(str_divisor, new DoubleValue(Double.NaN));
			env.getFST().changeUnderflow();
		}
		else {
			double temp_divisor = ((DoubleValue) divisor).getValue();
			double temp_divided = ((DoubleValue) divided).getValue();
			// ST0 || divisor == NaN, divided == NaN
			if (Double.isNaN(temp_divisor) || Double.isNaN(temp_divided) ) {
				env.getFPUregister().setFPURegisterValue(str_divisor, new DoubleValue(Double.NaN));
				env.getFST().setC1(new BooleanValue(false));
			}	
			// xu ly divisor == infinity va divided == infinity
			// ST0 || divisor = infinity
			// // ST0/divisor == 0 and dest == 0
			else if((Double.isInfinite(temp_divisor) && Double.isInfinite(temp_divided)) ||
					  (temp_divisor == 0 && temp_divided == 0 )){				
				env.getFPUregister().setFPURegisterValue(str_divisor, new DoubleValue(Double.NaN));
				env.getFST().setIE(new BooleanValue(true));
			}
			// ST0 || divisor = infinity
			else if ((Double.isInfinite(temp_divisor) && !Double.isInfinite(temp_divided ))){
				// not change FST
				// st0 = infinity (neg or positive) belong st0				
			}
			// divided == infinity
			else if ( Double.isInfinite(temp_divided ) && !Double.isInfinite(temp_divisor ) ){
				// st0/divisor = 0
				env.getFPUregister().setFPURegisterValue(str_divisor, new DoubleValue(0.0));
			}			
			
			// divided == 0, ST0 != 0
			else if (temp_divisor > 0 && temp_divided == 0) {
				env.getFPUregister().setFPURegisterValue("st0", new DoubleValue(Double.POSITIVE_INFINITY));
				env.getFST().setZE(new BooleanValue(true));
			}
			else if (temp_divisor < 0 && temp_divided == 0){
				env.getFPUregister().setFPURegisterValue("st0", new DoubleValue(Double.NEGATIVE_INFINITY));
				env.getFST().setZE(new BooleanValue(true));
			}				
			// ST0 = finity
			else {
				double result = temp_divisor/temp_divided;
				BigDecimal y = new BigDecimal(temp_divided);
				BigDecimal x = new BigDecimal(temp_divisor);
				BigDecimal exact_result = x.divide(y,MathContext.DECIMAL128);
				env.getFPUregister().setFPURegisterValue("st0", new DoubleValue(result));
				env.getFST().changeFDIV(result, exact_result);			
			}							
		}			
		System.out.println("Value FST: " + Convert.longToHex(env.getFST().getValueFST()));
		return null;
	}

}
