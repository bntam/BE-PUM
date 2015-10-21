package v2.org.analysis.packer.techniques;

import org.jakstab.Program;

import v2.org.analysis.path.BPState;

public interface PackerTechnique {

	public void Count(BPState curState, Program prog);
	
	public int GetInfo();
	
}
