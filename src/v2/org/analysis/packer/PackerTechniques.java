package v2.org.analysis.packer;

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
	private boolean timing_check;

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
		timing_check		= false;
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
		
		// Check indirect - jump
		if (!this.indirect_jump)
		{
			if (pPattern.IndirectJump())	
				isIndirectJump();
		}
		
		// Check obfuscated constant
		if (!this.obfuscated_const)
		{
			if (pPattern.ObfuscatedConst())	
				isObfuscatedConst();
		}
		
		// Check overlapping
		if (!this.overlapping)
		{
			if (pPattern.Overlapping())		
				isOverlapping();
		}
		
		// Check code chunk
		if (!this.code_chunking)
		{
			if (pPattern.CodeChunking())	
				isCodeChunking();
		}
		
		// Check stolen bytes
		if (!this.stolen_bytes)
		{
			if (pPattern.StolenBytes())		
				isStolenBytes();
		}
		
		// Check checksum
		if (!this.checksumming)
		{
			if (pPattern.Checksumming())	
				isChecksumming();
		}
		
		// Check SEH
		if (!this.SEHs)
		{
			if (pPattern.SEHs())			
				isSEH();
		}
		
		// Check 2 special APIs
		if (!this.two_APIs)
		{
			if (pPattern.TwoAPIs())			
				isTwoAPIs();
		}
		
		// Check anti-debugging
		if (!this.anti_debugging)
		{
			if (pPattern.AntiDebugging())	
				isAntiDebugging();
		}
		
		// Check timing-check
		if (!this.timing_check)
		{
			if (pPattern.TimingCheck())
				isTimingCheck();
		}
		
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
	
	public void isTimingCheck ()
	{
		this.timing_check = true;
	}
	
	public String getDetailTechniques ()
	{
		String techniques = "";
		techniques += (packing_unpacking) 	? "1" : "0"; // byte0
		techniques += (overwriting) 		? "1" : "0"; // byte1
		techniques += (indirect_jump) 		? "1" : "0"; // byte2
		techniques += (obfuscated_const) 	? "1" : "0"; // byte3
		techniques += (overlapping) 		? "1" : "0"; // byte4
		techniques += (code_chunking) 		? "1" : "0"; // byte5
		techniques += (stolen_bytes) 		? "1" : "0"; // byte6
		techniques += (checksumming) 		? "1" : "0"; // byte7
		techniques += (SEHs) 				? "1" : "0"; // byte8
		techniques += (two_APIs) 			? "1" : "0"; // byte9
		techniques += (anti_debugging) 		? "1" : "0"; // byte10
		techniques += (timing_check)		? "1" : "0"; // byte11
		return techniques;
	}
}
