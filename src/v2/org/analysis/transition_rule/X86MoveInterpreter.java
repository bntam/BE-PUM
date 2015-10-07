package v2.org.analysis.transition_rule;

import org.jakstab.Program;
import org.jakstab.asm.Operand;
import org.jakstab.asm.x86.X86MemoryOperand;
import org.jakstab.asm.x86.X86MoveInstruction;
import org.jakstab.asm.x86.X86Register;
//import v2.org.analysis.apihandle.APIHandle;


import v2.org.analysis.apihandle.winapi.APIHandle;
import v2.org.analysis.complement.BitVector;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.path.BPPath;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

import java.util.List;

public class X86MoveInterpreter {
	private APIHandle apiHandle = null;

	public BPState execute(X86MoveInstruction inst, BPPath path, List<BPPath> pathList, X86TransitionRule rule) {
		// TODO Auto-generated method stub
		this.apiHandle = rule.getAPIHandle();
		BPState curState = path.getCurrentState();
//		Environment env = curState.getEnvironement();
//		
//		// Bien dung de xu ly CMOVcc
//		boolean isSet_CMOVcc = false; // Xet dieu kien cua CMOVcc
//		
//		Operand dest = inst.getOperand1();
//		Operand src = inst.getOperand2();
//		int opSize = rule.getBitCount(inst);
		
		Program.getProgram().getLog().error("Instruction not supported" + inst + " at " + curState.getLocation());
		

		rule.generateNextInstruction(inst, path, pathList, true);
		return curState;
	}

}
