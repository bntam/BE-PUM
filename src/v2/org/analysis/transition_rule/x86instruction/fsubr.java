package v2.org.analysis.transition_rule.x86instruction;

import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.complement.Convert;
import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.Value;

public class fsubr extends X86InstructionStub {

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub
		System.out.println("Instruction: " + inst.getName());
		Value suntract = null; 
		Value subtrahend = null;
		String str_subtrahend = "";		
		// 1 operand, ST0 is suntract, dest is subtrahend				
		if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
			if (!rule.checkAddressValid(env, (X86MemoryOperand) dest)) {
				// SEH Exploit
				System.out.println("SEH:" + path.getCurrentState().getLocation().toString());
				return rule.processSEH(path.getCurrentState());
			}
			subtrahend = env.getFPUregister().getFPURegisterValue("st0");
			str_subtrahend = "st0";
			suntract = env.getMemory().getMemoryValue((X86MemoryOperand) dest, inst); 									
		}		
		
		// 2 operand
		if (dest.getClass().getSimpleName().equals("X86FloatRegister")) {
			// d = env.getRegister().getRegisterValue(dest.toString());
			subtrahend = env.getFPUregister().getFPURegisterValue(dest.toString());
			str_subtrahend = dest.toString();
			suntract = env.getFPUregister().getFPURegisterValue(src.toString());
		}
		
		env.getFPUregister().FSUB(suntract, subtrahend, str_subtrahend, env);
		
		System.out.println("Value FST: " + Convert.longToHex(env.getFST().getValueFST()));
		return null;
	}

}
