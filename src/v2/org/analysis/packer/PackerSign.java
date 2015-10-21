package v2.org.analysis.packer;

public class PackerSign {

	private String packerTechSign;
	private String packerName;
	
	public PackerSign (String pName, String pTechSign)
	{
		packerName 		= pName;
		packerTechSign	= pTechSign;
	}	
	
	public String getPackerName ()
	{
		return packerName;
	}
	
	public String getPackerTechSign()
	{
		return packerTechSign;
	}
	
}
