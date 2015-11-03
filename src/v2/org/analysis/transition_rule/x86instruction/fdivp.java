package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class fdivp extends X86InstructionStub {

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub
		
		//System.out.println("Instruction: " + inst.getName());
		// Xet truong hop so chia la Null, NaN, Infinity
		Value divisor = null;
		Value dividend = null;	
		String str_divisor = "";
		// 2 operand
		if (dest.getClass().getSimpleName().equals("X86FloatRegister")) {
			// d = env.getRegister().getRegisterValue(dest.toString());
			divisor = env.getFPUregister().getFPURegisterValue(dest.toString());
			str_divisor = dest.toString();
			dividend = env.getFPUregister().getFPURegisterValue(src.toString());
		}
		
		env.getFPUregister().FDIV(divisor, dividend, str_divisor, env);
		env.getFPUregister().POP();		
		Value top = env.getFST().getTOP();		
		env.getFST().changePOP(((LongValue) top).getValue());
		//System.out.println("Value FST: " + Convert.longToHex(env.getFST().getValueFST()));
		return null;
	}

}
