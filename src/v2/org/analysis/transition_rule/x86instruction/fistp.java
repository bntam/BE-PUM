package v2.org.analysis.transition_rule.x86instruction;

import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class fistp extends X86InstructionStub {

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub
		System.out.println("Instruction: " + inst.getName());
		Value st0 = null; 
		st0 = env.getFPUregister().getFPURegisterValue("st0");
		if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
			// X86MemoryOperand t =
			// env.getMemory().evaluateAddress((X86MemoryOperand) dest,
			// env);
			if (!rule.checkAddressValid(env, (X86MemoryOperand) dest)) {
				// SEH Exploit
				System.out.println("SEH:" + path.getCurrentState().getLocation().toString());
				return rule.processSEH(path.getCurrentState());
			}
			d = env.getMemory().getMemoryValue((X86MemoryOperand) dest, inst);
		}
		
		env.getFPUregister().FIST(st0, dest, env, inst);
		
		env.getFPUregister().POP();		
		Value top = env.getFST().getTOP();		
		env.getFST().changePOP(((LongValue) top).getValue());
		
//		d = env.getMemory().getMemoryValue((X86MemoryOperand) dest, inst);
//		System.out.println(d.toString());
//		System.out.println("Value FST: " + Convert.longToHex(env.getFST().getValueFST()));
//		System.out.println("Value FCW: " + Convert.longToHex(env.getFCW().getValueFCW()));
		return null;
	}

}
