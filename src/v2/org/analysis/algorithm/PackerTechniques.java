package v2.org.analysis.algorithm;

public class PackerTechniques {
	
	private boolean packing_unpacking; //x	
	private boolean overwriting;	
	private boolean indirect_jump; //x	
	private boolean obfuscated_const;	
	private boolean overlapping;	
	private boolean code_chunking;	
	private boolean stolen_bytes;	
	private boolean checksumming;	
	private boolean SEHs; //x
	private boolean two_APIs;
	private boolean anti_debugging;	

	public PackerTechniques ()
	{
		packing_unpacking 	= false; //x
		overwriting 		= false; //x
		indirect_jump 		= false; //x
		obfuscated_const	= true;  // Detect later
		overlapping			= false; // Detect later
		code_chunking		= false; // Detect later 
		stolen_bytes		= false; //x
		checksumming		= false; // Detect later
		SEHs				= false; //x
		two_APIs			= false; //x
		anti_debugging		= false; //x
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
