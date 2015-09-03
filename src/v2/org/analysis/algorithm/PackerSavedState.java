package v2.org.analysis.algorithm;

public class PackerSavedState {

	private long insLocation;
	private String insName;
	
	public PackerSavedState (long insLocation, String insName)
	{
		this.insLocation 	= insLocation;
		this.insName 		= insName;
	}	
	
	public long getInsLoc ()
	{
		return this.insLocation;
	}
	
	public String getInsName ()
	{
		return this.insName;
	}
	
}
