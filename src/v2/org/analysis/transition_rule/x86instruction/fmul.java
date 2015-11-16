package v2.org.analysis.transition_rule.x86instruction;

import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.Value;

public class fmul extends X86InstructionStub {

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub
		//System.out.println("Instruction: " + inst.getName());
		Value multi_x = null; 
		Value multi_y = null;
		String str_dest = "";		
		// 1 operand, ST0 is divisor, dest is divided				
		if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
			if (!rule.checkAddressValid(env, (X86MemoryOperand) dest)) {
				// SEH Exploit
				System.out.println("SEH:" + path.getCurrentState().getLocation().toString());
				return rule.processSEH(path.getCurrentState());
			}
			multi_x = env.getFPUregister().getFPURegisterValue("st0");
			str_dest = "st0";
			multi_y = env.getMemory().getMemoryValue((X86MemoryOperand) dest, inst); 									
		}		
		
		// 2 operand
		if (dest.getClass().getSimpleName().equals("X86FloatRegister")) {			
			//d = env.getRegister().getRegisterValue(dest.toString());
			multi_x = env.getFPUregister().getFPURegisterValue(dest.toString());
			str_dest = dest.toString();
			multi_y = env.getFPUregister().getFPURegisterValue(src.toString());			
		}		
		env.getFPUregister().FMUL(multi_x, multi_y, str_dest, env);
		
		//System.out.println("Value FST: " + Convert.longToHex(env.getFST().getValueFST()));
		return null;
	}

}
