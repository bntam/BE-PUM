/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.transition_rule.x86instruction
 * File name: int3.java
 * Created date: Sep 23, 2015
 * Description:
 */
package v2.org.analysis.transition_rule.x86instruction;

import org.jakstab.Program;

import v2.org.analysis.path.BPState;
import v2.org.analysis.system.SEHHandle;
import v2.org.analysis.transition_rule.X86InstructionStub;

/**
 * @author Yen Nguyen
 *
 */
public class int3 extends X86InstructionStub {

	public int3() {
	}

	@Override
	public BPState execute() {
		if (env.getSystem().getSEHHandler().isSet()) {
			SEHHandle sehHandle = curState.getEnvironement().getSystem().getSEHHandler();
			sehHandle.setSEHType(SEHHandle.INTERUPT);
			return rule.processSEH(curState);
		} else
			Program.getProgram().getLog().debugString("Not processed int3 at " + curState.getLocation());

		return null;
	}

}
