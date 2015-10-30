package v2.org.analysis.packer;

public class PackerPatterns {
	
	private PackerCounter pCounter;
	
	public PackerPatterns()
	{
		pCounter = new PackerCounter();
	}
	
	public void setCheckingState(PackerCounter pCounter)
	{
		this.pCounter = pCounter;
	}
	
	/*
	 * Check if it is encryption
	 */
	public boolean PackAndUnpack()
	{
		return pCounter.getTechniques(PackerConstants.PACKING_UNPACKING).GetInfo() > 0;
	}
	
	/*
	 * Check if it is SMC
	 */
	public boolean Overwriting()
	{
		return pCounter.getTechniques(PackerConstants.OVERWRITING).GetInfo() > 0;
	}
	
	/*
	 * Check if it is indirect jump
	 */
	public boolean IndirectJump()
	{
		return pCounter.getTechniques(PackerConstants.INDIRECT_JUMP).GetInfo() > 0;
	}
	
	/*
	 * Check if it is obfuscated constant.
	 */
	public boolean ObfuscatedConst()
	{
		return pCounter.getTechniques(PackerConstants.OBFUSCATED_CONST).GetInfo() > 0;
	}
	
	/*
	 * Check if it is overlapping
	 */
	public boolean OverlappingFunction()
	{
		return pCounter.getTechniques(PackerConstants.OVERLAPPING_FUNC).GetInfo() > 0;
	}
	
	public boolean OverlappingBlock()
	{
		return pCounter.getTechniques(PackerConstants.OVERLAPPING_BLOCK).GetInfo() > 0;
	}
	
	/*
	 * Check if it is chunk code
	 */
	public boolean CodeChunking()
	{
		return pCounter.getTechniques(PackerConstants.CODE_CHUNKING).GetInfo() > 0;
	}
	
	/*
	 * Check if it is VirtualAlloc
	 */
	public boolean StolenBytes()
	{
		return pCounter.getTechniques(PackerConstants.STOLEN_BYTES).GetInfo() > 0;
	}
	
	/*
	 * Check if it is checksum calculate
	 */
	public boolean Checksumming()
	{
		return pCounter.getTechniques(PackerConstants.CHECKSUMMING).GetInfo() > 0;
	}
	
	/*
	 * Check if it is structure exception handling
	 */
	public boolean SEHs()
	{
		return pCounter.getTechniques(PackerConstants.SEH).GetInfo() > 0;
	}
	
	/*
	 * Check if it is LoadLibrary and GetProcAddress
	 */
	public boolean TwoAPIs()
	{
		return pCounter.getTechniques(PackerConstants.TWO_APIS).GetInfo() > 0;
	}
	
	/*
	 * Check if it is IsDebuggerPresent 
	 */
	public boolean AntiDebugging()
	{
		return pCounter.getTechniques(PackerConstants.ANTI_DEBUGGING).GetInfo() > 0;
	}

	/*
	 * Check if it is Timing check
	 */
	public boolean TimingCheck()
	{
		return pCounter.getTechniques(PackerConstants.TIMING_CHECK).GetInfo() > 0;
	}
}
