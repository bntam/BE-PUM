package v2.org.analysis.transition_rule.x86instruction;

import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.Value;

public class fdivr extends X86InstructionStub {

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub
		//System.out.println("Instruction: " + inst.getName());	
		
		// Xet truong hop so chia la Null, NaN, Infinity
		Value divisor = null; 
		Value dividend = null;
		String str_dividend = "";		
		// 1 operand, ST0 is divisor, dest is divided				
		if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
			if (!rule.checkAddressValid(env, (X86MemoryOperand) dest)) {
				// SEH Exploit
				System.out.println("SEH:" + path.getCurrentState().getLocation().toString());
				return rule.processSEH(path.getCurrentState());
			}
			dividend = env.getFPUregister().getFPURegisterValue("st0");
			str_dividend = "st0";
			divisor = env.getMemory().getMemoryValue((X86MemoryOperand) dest, inst); 									
		}		
		
		// 2 operand
		if (dest.getClass().getSimpleName().equals("X86FloatRegister")) {			
			//d = env.getRegister().getRegisterValue(dest.toString());
			dividend = env.getFPUregister().getFPURegisterValue(dest.toString());
			str_dividend = dest.toString();
			divisor = env.getFPUregister().getFPURegisterValue(src.toString());			
		}
		env.getFPUregister().FDIV(divisor, dividend, str_dividend, env);
		
		//System.out.println("Value FST: " + Convert.longToHex(env.getFST().getValueFST()));
		return null;
	}

}
