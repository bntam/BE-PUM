package v2.org.analysis.algorithm;

public class PackerHeader {

	private String packerName;
	private String[] packerSignature;
	
	public PackerHeader (String pName, String[] pSignature)
	{
		packerName = pName;
		packerSignature = pSignature;
	}	
	
	public String getPackerName ()
	{
		return packerName;
	}
	
	public String[] getPackerSignature ()
	{
		return packerSignature;
	}
}
