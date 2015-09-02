package v2.org.analysis.algorithm;

import org.jakstab.Program;

import v2.org.analysis.path.BPState;

public class PackerTechniques {
	
	private PackerPatterns pPattern;
	
	private boolean packing_unpacking;	
	private boolean overwriting;	
	private boolean indirect_jump; 	
	private boolean obfuscated_const;	
	private boolean overlapping;	
	private boolean code_chunking;	
	private boolean stolen_bytes;	
	private boolean checksumming;	
	private boolean SEHs; 
	private boolean two_APIs;
	private boolean anti_debugging;	

	public PackerTechniques ()
	{
		pPattern = new PackerPatterns();
		
		packing_unpacking 	= false; 
		overwriting 		= false; 
		indirect_jump 		= false; 
		obfuscated_const	= false; 
		overlapping			= false; 
		code_chunking		= false; 
		stolen_bytes		= false; 
		checksumming		= false; 
		SEHs				= false; 
		two_APIs			= false; 
		anti_debugging		= false; 
	}
	
	public void updateChecking (BPState curState, Program program)
	{
		if (curState != null)
		{
			pPattern.setCheckingState(curState, program);
			this.checkingState();
		}
	}
	
	private void checkingState ()
	{
		// Check packing/unpacking
		if (!this.packing_unpacking)
		{
			if (pPattern.PackAndUnpack()) 	
				isPackingUnpacking();
		}
		
		// Check SMC
		if (!this.packing_unpacking)
		{
			if (pPattern.Overwriting()) 	
				isOverwriting();
		}
		
		if (pPattern.IndirectJump())	isIndirectJump();
		if (pPattern.ObfuscatedConst())	isObfuscatedConst();
		if (pPattern.Overlapping())		isOverlapping();
		if (pPattern.CodeChunking())	isCodeChunking();
		if (pPattern.StolenBytes())		isStolenBytes();
		if (pPattern.Checksumming())	isChecksumming();
		if (pPattern.SEHs())			isSEH();
		if (pPattern.TwoAPIs())			isTwoAPIs();
		if (pPattern.AntiDebugging())	isAntiDebugging();
	}
	
	public void isPackingUnpacking ()
	{
		this.packing_unpacking = true;
	}
	
	public void isOverwriting ()
	{
		this.overwriting = true;
	}
	
	public void isIndirectJump ()
	{
		this.indirect_jump = true;
	}
	
	public void isObfuscatedConst ()
	{
		this.obfuscated_const = true;
	}
	
	public void isOverlapping ()
	{
		this.overlapping = true;
	}
	
	public void isCodeChunking ()
	{
		this.code_chunking = true;
	}
	
	public void isStolenBytes ()
	{
		this.stolen_bytes = true;
	}
	
	public void isChecksumming ()
	{
		this.checksumming = true;
	}
	
	public void isSEH ()
	{
		this.SEHs = true;
	}
	
	public void isTwoAPIs ()
	{
		this.two_APIs = true;
	}
	
	public void isAntiDebugging ()
	{
		this.anti_debugging = true;
	}
	
	public String getDetailTechniques ()
	{
		String techniques = "";
		techniques += (packing_unpacking) 	? "1" : "0";
		techniques += (overwriting) 		? "1" : "0";
		techniques += (indirect_jump) 		? "1" : "0";
		techniques += (obfuscated_const) 	? "1" : "0";
		techniques += (overlapping) 		? "1" : "0";
		techniques += (code_chunking) 		? "1" : "0";
		techniques += (stolen_bytes) 		? "1" : "0";
		techniques += (checksumming) 		? "1" : "0";
		techniques += (SEHs) 				? "1" : "0";
		techniques += (two_APIs) 			? "1" : "0";
		techniques += (anti_debugging) 		? "1" : "0";
		return techniques;
	}
}
