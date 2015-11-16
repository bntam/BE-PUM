package v2.org.analysis.transition_rule.x86instruction;

import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.complement.Convert;
import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.Value;

public class fisub extends X86InstructionStub {

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub
		 System.out.println("Instruction: " + inst.getName());

		Value suntract = null;
		Value subtrahend = null;
		String str_suntract = "";
		// 1 operand, ST0 is suntract, dest is subtrahend
		if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
			if (!rule.checkAddressValid(env, (X86MemoryOperand) dest)) {
				// SEH Exploit
				System.out.println("SEH:" + path.getCurrentState().getLocation().toString());
				return rule.processSEH(path.getCurrentState());
			}
			suntract = env.getFPUregister().getFPURegisterValue("st0");
			str_suntract = "st0";
			subtrahend = env.getMemory().getMemoryValue((X86MemoryOperand) dest, inst);
		}
		env.getFPUregister().FSUB(suntract, subtrahend, str_suntract, env);

		System.out.println("Value FST: " + Convert.longToHex(env.getFST().getValueFST()));
		return null;
	}

}
