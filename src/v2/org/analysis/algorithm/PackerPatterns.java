package v2.org.analysis.algorithm;

import v2.org.analysis.path.BPState;

public class PackerPatterns {
	
	private BPState curState;
	
	public boolean PackAndUnpack()
	{
		return true;
	}
	
	public boolean Overwriting()
	{
		return true;
	}
	
	public boolean IndirectJump()
	{
		return true;
	}
	
	public boolean ObfuscatedConst()
	{
		return true;
	}
	
	public boolean Overlapping()
	{
		return true;
	}
	
	public boolean CodeChunking()
	{
		return true;
	}
	
	public boolean StolenBytes()
	{
		return true;
	}
	
	public boolean Checksumming()
	{
		return true;
	}
	
	public boolean SEHs()
	{
		return true;
	}
	
	public boolean TwoAPIs()
	{
		return true;
	}
	
	public boolean AntiDebugging()
	{
		return true;
	}
	
	public void setCheckingState (BPState cState)
	{
		this.curState = cState;
	}
	
}
