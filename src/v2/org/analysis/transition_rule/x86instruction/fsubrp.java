package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class fsubrp extends X86InstructionStub {

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub
		//System.out.println("Instruction: " + inst.getName());
		Value suntract = null; 
		Value subtrahend = null;
		String str_subtrahend = "";		
		
		// 2 operand
		if (dest.getClass().getSimpleName().equals("X86FloatRegister")) {
			// d = env.getRegister().getRegisterValue(dest.toString());
			subtrahend = env.getFPUregister().getFPURegisterValue(dest.toString());
			str_subtrahend = dest.toString();
			suntract = env.getFPUregister().getFPURegisterValue(src.toString());
		}
		
		env.getFPUregister().FSUB(suntract, subtrahend, str_subtrahend, env);
		env.getFPUregister().POP();
		Value top = env.getFST().getTOP();
		env.getFST().changePOP(((LongValue) top).getValue());
		
		//System.out.println("Value FST: " + Convert.longToHex(env.getFST().getValueFST()));
		return null;
	}

}
