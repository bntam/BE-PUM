package v2.org.analysis.transition_rule.x86instruction;

import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class fcomp extends X86InstructionStub {

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub
		//System.out.println("Instruction: " + inst.getName());
		long temp_top;
		Value top = env.getFST().getTOP();
		top = top.evaluate(top.getValueMap());
		temp_top = ((LongValue) top).getValue();
		env.getFST().changePOP(temp_top);
		Value st0 = env.getFPUregister().getFPURegisterValue("st0");
		// Opreand is ST(1)
		if (dest.getClass().getSimpleName().equals("X86FloatRegister")) {				
			d = env.getFPUregister().getFPURegisterValue(dest.toString());				
		}
		if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
			if (!rule.checkAddressValid(env, (X86MemoryOperand) dest)) {
				// SEH Exploit
				System.out.println("SEH:" + path.getCurrentState().getLocation().toString());
				return rule.processSEH(path.getCurrentState());
			}
			d = env.getMemory().getMemoryValue((X86MemoryOperand) dest, inst);
		}
		
		env.getFST().changeFCOM(st0,d);	
		env.getFPUregister().POP();		
		//System.out.println("Value FST: " + Convert.longToHex(env.getFST().getValueFST()));
		return null;
	}

}
