package v2.org.analysis.transition_rule.x86instruction;

import java.math.BigDecimal;

import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.DoubleValue;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class fiadd extends X86InstructionStub {

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub
		//System.out.println("Instruction: " + inst.getName());
		if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
			if (!rule.checkAddressValid(env, (X86MemoryOperand) dest)) {
				// SEH Exploit
				System.out.println("SEH:" + path.getCurrentState().getLocation().toString());
				return rule.processSEH(path.getCurrentState());
			}
			d = env.getMemory().getMemoryValue((X86MemoryOperand) dest, inst);
			long temp_d = 0;
			temp_d = ((LongValue) d).getValue();						
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
		//System.out.println("Value FST: " + Convert.longToHex(env.getFST().getValueFST()));
		return null;
	}

}
