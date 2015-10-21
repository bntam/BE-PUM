package v2.org.analysis.transition_rule.x86instruction;

import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.DoubleValue;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class fld extends X86InstructionStub {

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
		}
		// char[] t = Convert.convertBinary(d, 32);
		if (d instanceof DoubleValue) {
			double temp_d = ((DoubleValue) d).getValue();
			long temp_top;
			Value top = env.getFST().getTOP();
			top = top.evaluate(top.getValueMap());
			temp_top = ((LongValue) top).getValue();
			// System.out.println("Value dest: " + temp_d);
			env.getFST().changFLD(temp_top);
			if (((BooleanValue) env.getFST().getC1()).getValue()) {
				env.getFPUregister().FLD(Double.NaN);
			} else {
				env.getFPUregister().FLD(temp_d);
			}
		} else {
			System.out.println("Operand is not double value");
		}
		//System.out.println("Value FST: " + Convert.longToHex(env.getFST().getValueFST()));
		// System.out.println(inst.toString());

		return null;
	}

}
