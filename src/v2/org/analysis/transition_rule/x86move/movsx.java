package v2.org.analysis.transition_rule.x86move;

import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.complement.BitVector;
import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.X86MoveStub;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class movsx extends X86MoveStub {

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub
		Value source = rule.getValueOperand(src, env, inst);
		if (source != null && source instanceof LongValue) {
			long t = ((LongValue) source).getValue();
			int sign = BitVector.getMSB(t, opSize / 2);
			rule.setValueOperand(dest, new LongValue(BitVector.extend(t, sign, ((X86MemoryOperand)src).getDataType().bits(), opSize)), env, inst);
		} else
			rule.setValueOperand(dest, source, env, inst);
		
		return null;
	}

}
