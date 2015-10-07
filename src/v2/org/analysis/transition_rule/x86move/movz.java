package v2.org.analysis.transition_rule.x86move;

import v2.org.analysis.complement.BitVector;
import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.X86MoveStub;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class movz extends X86MoveStub {

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub
		Value source = rule.getValueOperand(src, env, inst);
		if (source != null && source instanceof LongValue) {
			long t = ((LongValue) source).getValue();
			int opSize1 = rule.getBitCountOprand(src);
			LongValue temp = new LongValue(BitVector.extend(t, 0, opSize1, opSize));
			//System.out.println();
			rule.setValueOperand(dest, temp , env, inst);
		} else
			rule.setValueOperand(dest, source, env, inst);
		
		return null;
	}

}
