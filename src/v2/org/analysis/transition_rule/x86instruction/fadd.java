package v2.org.analysis.transition_rule.x86instruction;

import java.math.BigDecimal;

import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.DoubleValue;
import v2.org.analysis.value.Value;

public class fadd extends X86InstructionStub {

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub
		//System.out.println("Instruction: " + inst.getName());		
		// 1 operand				
		if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
			if (!rule.checkAddressValid(env, (X86MemoryOperand) dest)) {
				// SEH Exploit
				System.out.println("SEH:" + path.getCurrentState().getLocation().toString());
				return rule.processSEH(path.getCurrentState());
			}
			d = env.getMemory().getMemoryValue((X86MemoryOperand) dest, inst);
			double temp_d = 0;
			temp_d = ((DoubleValue) d).getValue();						
			Value st0 = env.getFPUregister().getFPURegisterValue("st0");			
			if (st0 == null) {
				env.getFPUregister().FLD(Double.NaN);
				env.getFST().changeUnderflow();
			}
			else {
				double temp_st0 = ((DoubleValue) st0).getValue();
				if (Double.isNaN(temp_st0)) {
					env.getFST().setC1(new BooleanValue(false));
				}
				else {
					double result = temp_d + temp_st0;
					BigDecimal x = new BigDecimal(temp_d);
					BigDecimal y = new BigDecimal(temp_st0);
					BigDecimal exact_result = x.add(y);
					env.getFPUregister().setFPURegisterValue("st0", new DoubleValue(result));
					env.getFST().changeFADD(result, exact_result);			
				}							
			}					
		}
		
		// 2 operand
		if (dest.getClass().getSimpleName().equals("X86FloatRegister")) {			
			//d = env.getRegister().getRegisterValue(dest.toString());
			d = env.getFPUregister().getFPURegisterValue(dest.toString());
			s = env.getFPUregister().getFPURegisterValue(src.toString());
			if (d == null || s == null) {
				env.getFPUregister().FLD(Double.NaN);
				env.getFST().changeUnderflow();
			}
			else {
				double temp_d = ((DoubleValue) d).getValue();
				double temp_s = ((DoubleValue) s).getValue();
				if (Double.isNaN(temp_d) || Double.isNaN(temp_s)) {
					env.getFST().setC1(new BooleanValue(false));
				}
				else {
					double result = temp_d + temp_s;
					BigDecimal x = new BigDecimal(temp_d);
					BigDecimal y = new BigDecimal(temp_s);
					BigDecimal exact_result = x.add(y);
					env.getFPUregister().setFPURegisterValue(dest.toString(), new DoubleValue(result));
					env.getFST().changeFADD(result, exact_result);		
				}
			}				
		}
		//System.out.println("Value FST: " + Convert.longToHex(env.getFST().getValueFST()));
		return null;
	}

}
