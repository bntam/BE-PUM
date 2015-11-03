package v2.org.analysis.transition_rule.x86instruction;

import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.Value;

public class fdiv extends X86InstructionStub {

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub
		//System.out.println("Instruction: " + inst.getName());	
		
		// Xet truong hop so chia la Null, NaN, Infinity
		Value divisor = null; 
		Value dividend = null;
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
			dividend = env.getMemory().getMemoryValue((X86MemoryOperand) dest, inst); 									
		}		
		
		// 2 operand
		if (dest.getClass().getSimpleName().equals("X86FloatRegister")) {			
			//d = env.getRegister().getRegisterValue(dest.toString());
			divisor = env.getFPUregister().getFPURegisterValue(dest.toString());
			str_divisor = dest.toString();
			dividend = env.getFPUregister().getFPURegisterValue(src.toString());			
		}		
		env.getFPUregister().FDIV(divisor, dividend, str_divisor, env);
		
		//System.out.println("Value FST: " + Convert.longToHex(env.getFST().getValueFST()));
		return null;
	}

}
