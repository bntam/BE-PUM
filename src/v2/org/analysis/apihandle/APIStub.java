package v2.org.analysis.apihandle;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.path.BPState;

public abstract class APIStub {
	public abstract boolean executeAPI(AbsoluteAddress address,
			String funcName, BPState curState, Instruction inst, int cond);

	public abstract boolean equalsLibraryName(String libraryName);
}
