package v2.org.analysis.packer.techniques;

import org.jakstab.Program;

import v2.org.analysis.path.BPState;

public class HardwareBPX implements PackerTechnique {

	/** 
	 * Using for record hardware breakpoints-
	 */
	
	private static int numOfBPXs;
	
	public HardwareBPX ()
	{
		numOfBPXs				= 0;
	}
	
	@Override
	public void Count (BPState curState, Program prog)
	{
		if (curState == null || curState.getInstruction() == null) {
			return;
		}
		
		numOfBPXs = 0;
		
	}
	
	@Override
	public int GetInfo ()
	{
		return numOfBPXs;
	}
	
}
