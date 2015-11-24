package v2.org.analysis.transition_rule.x86instruction;

import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.Value;

public class fst extends X86InstructionStub {

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub
		//System.out.println("Instruction: " + inst.getName());
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
		
		System.out.println("Chua ho tro");
		// Chua xu ly gan bo nho la 1 double value
		
//		// NULL || NaN || Infinity 
//		if (st0 == null) {			
//			env.getMemory().setMemoryValue((X86MemoryOperand) dest, new DoubleValue(Double.NaN), inst);			
//			env.getFST().changeUnderflow();
//		} else {
//			double temp_st0 = ((DoubleValue) st0).getValue();
//			// NaN
//			if (Double.isNaN(temp_st0)) {
//				env.getMemory().setMemoryValue((X86MemoryOperand) dest, new DoubleValue(Double.NaN), inst);			
//				env.getFST().setC1(new BooleanValue(false));
//			}
//			// Infinity
//			if (Double.isFinite(temp_st0)){
//				env.getMemory().setMemoryValue((X86MemoryOperand) dest, new DoubleValue(temp_st0), inst);	
//			}
//			// finity
//			else {
//				env.getMemory().setMemoryValue((X86MemoryOperand) dest, new DoubleValue(temp_st0), inst);
//			}
//		}
//		d = env.getMemory().getMemoryValue((X86MemoryOperand) dest, inst);
//		System.out.println(d.toString());
//		System.out.println("Value FST: " + Convert.longToHex(env.getFST().getValueFST()));
		return null;
	}

}
