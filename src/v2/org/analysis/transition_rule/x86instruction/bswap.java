package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.AnalysisBit;
import v2.org.analysis.transition_rule.X86InstructionStub;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.SymbolValue;

public class bswap extends X86InstructionStub{

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub
		long temp_d = 0;
		long temp = 0;

		if (dest.getClass().getSimpleName().equals("X86Register")) {
			d = env.getRegister().getRegisterValue(dest.toString());

			if (d != null && d instanceof LongValue) {
				temp_d = ((LongValue) d).getValue();
				temp = new AnalysisBit().SwapBit32(temp_d);
				env.getRegister().setRegisterValue(dest.toString(), new LongValue(temp));
			} else
				env.getRegister().setRegisterValue(dest.toString(), new SymbolValue(dest.toString()));
		}
		
		return null;
	}
	

}
