package v2.org.analysis.packer;

import org.jakstab.Program;

import v2.org.analysis.path.BPState;

public class PackerTechniques {
	
	private PackerPatterns pPattern;
	
	private boolean packing_unpacking;	
	private boolean overwriting;	
	private boolean indirect_jump; 	
	private boolean obfuscated_const;	
	private boolean overlapping_func;
	private boolean overlapping_block;
	private boolean code_chunking;	
	private boolean stolen_bytes;	
	private boolean checksumming;	
	private boolean SEHs; 
	private boolean two_APIs;
	private boolean anti_debugging;	
	private boolean timing_check;
	
	private boolean dynamic_code;
	private boolean code_layout;
	private boolean anti_rewriting;
	
	private boolean USING_COUNT;
	private static PackerCounter pCounter;
	
	public PackerTechniques ()
	{
		USING_COUNT			= false;
		
		pPattern 			= new PackerPatterns();
		
		packing_unpacking 	= false; 
		overwriting 		= false; 
		indirect_jump 		= false; 
		obfuscated_const	= false; 
		code_chunking		= false; 
		stolen_bytes		= false; 
		checksumming		= false; 
		SEHs				= false; 
		two_APIs			= false; 
		anti_debugging		= false;
		timing_check		= false;
		
		dynamic_code		= false;
		code_layout			= false;
		anti_rewriting		= false;
		
		pCounter			= new PackerCounter();
	}
	
	public void updateChecking (BPState curState, Program program)
	{
		if (curState != null)
		{
			pPattern.setCheckingState(curState, program);
			this.checkingState();
			// BEGIN TO STATISTIC
			pCounter.setCountingState(curState, program);
			pCounter.Execute(this.USING_COUNT);
		}
	}
	
	private void checkingState ()
	{
		// Check packing/unpacking
		if (!this.packing_unpacking)
		{
			if (pPattern.PackAndUnpack()) {
				isPackingUnpacking();
			}
		}
		
		// Check SMC
		if (!this.packing_unpacking)
		{
			if (pPattern.Overwriting()) {
				isOverwriting();
			}
		}
		
		// Check indirect - jump
		if (!this.indirect_jump)
		{
			if (pPattern.IndirectJump()) {
				isIndirectJump();
			}
		}
		
		// Check obfuscated constant
		if (!this.obfuscated_const)
		{
			if (pPattern.ObfuscatedConst()) {
				isObfuscatedConst();
			}
		}
		
		// Check overlapping function
		if (!this.overlapping_func)
		{
			if (pPattern.OverlappingFunction()) {
				isOverlappingFunc();
			}
		}
		
		// Check overlapping block
		if (!this.overlapping_block)
		{
			if (pPattern.OverlappingBlock()) {
				isOverlappingBlock();
			}
		}
		
		// Check code chunk
		if (!this.code_chunking)
		{
			if (pPattern.CodeChunking()) {
				isCodeChunking();
			}
		}
		
		// Check stolen bytes
		if (!this.stolen_bytes)
		{
			if (pPattern.StolenBytes()) {
				isStolenBytes();
			}
		}
		
		// Check checksum
		if (!this.checksumming)
		{
			if (pPattern.Checksumming()) {
				isChecksumming();
			}
		}
		
		// Check SEH
		if (!this.SEHs)
		{
			if (pPattern.SEHs()) {
				isSEH();
			}
		}
		
		// Check 2 special APIs
		if (!this.two_APIs)
		{
			if (pPattern.TwoAPIs()) {
				isTwoAPIs();
			}
		}
		
		// Check anti-debugging
		if (!this.anti_debugging)
		{
			if (pPattern.AntiDebugging()) {
				isAntiDebugging();
			}
		}
		
		// Check timing-check
		if (!this.timing_check)
		{
			if (pPattern.TimingCheck()) {
				isTimingCheck();
			}
		}
		
		// Check dynamic code
		if (!this.dynamic_code)
		{
			if (this.packing_unpacking || this.overwriting) {
				isDynamicCode();
			}
		}
		
		// Check code layout
		if (!this.code_layout)
		{
			if (this.overlapping_func || this.overlapping_block || this.code_chunking) {
				isCodeLayout();
			}
		}
		
		// Check anti rewriting
		if (!this.anti_rewriting)
		{
			if (this.stolen_bytes || this.checksumming) {
				isAntiRewriting();
			}
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
	
	public void isOverlappingFunc ()
	{
		this.overlapping_func = true;
	}
	
	public void isOverlappingBlock ()
	{
		this.overlapping_block = true;
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
	
	public void isDynamicCode ()
	{
		this.dynamic_code = true;
	}
	
	public void isCodeLayout ()
	{
		this.code_layout = true;
	}
	
	public void isAntiRewriting ()
	{
		this.anti_rewriting = true;
	}
	
	public String getDetailTechniques ()
	{
		String techniques = "";
		techniques += (packing_unpacking) 	? "1" : "0"; 
		techniques += (overwriting) 		? "1" : "0"; 
		techniques += (indirect_jump) 		? "1" : "0"; 
		techniques += (obfuscated_const) 	? "1" : "0"; 
		techniques += (code_chunking) 		? "1" : "0"; 
		techniques += (stolen_bytes) 		? "1" : "0"; 
		techniques += (checksumming) 		? "1" : "0"; 
		techniques += (SEHs) 				? "1" : "0"; 
		techniques += (two_APIs) 			? "1" : "0"; 
		techniques += (anti_debugging) 		? "1" : "0";
		techniques += (dynamic_code)		? "1" : "0";
		techniques += (code_layout)			? "1" : "0";
		techniques += (overlapping_func)	? "1" : "0";
		techniques += (overlapping_block)	? "1" : "0";
		techniques += (anti_rewriting)		? "1" : "0";
		techniques += (timing_check)		? "1" : "0";
		
		return techniques;
	}
	
	public String getTechniquesStatiscial ()
	{
		return pCounter.getInfo();
	}
	
	public void setCount (boolean isCounting)
	{
		this.USING_COUNT = isCounting;
	}
}
