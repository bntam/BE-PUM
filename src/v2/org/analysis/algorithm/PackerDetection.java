package v2.org.analysis.algorithm;

public class PackerDetection {
	
	private static final String UPX 		= "10110000010";
	private static final String FSG 		= "10111000010";
	private static final String PECOMPACT 	= "11111111110";
	private static final String NPACK 		= "10111000010";
	private static final String PETITE 		= "00110000100";
	private static final String YODA 		= "10111101111";
	private static final String ASPACK 		= "10101000000";
	
	private PackerTechniques techniques;
	
	public PackerDetection ()
	{
		techniques = new PackerTechniques();
	}
	
	public void packedBy ()
	{
		String packedby = "File packed by ";
		String pTech = techniques.getDetailTechniques();
		packedby += (pTech.equals(UPX)) 		? "UPX ": "";
		packedby += (pTech.equals(FSG)) 		? "FSG ": "";
		packedby += (pTech.equals(PECOMPACT)) 	? "PECOMPACT ": "";
		packedby += (pTech.equals(NPACK)) 		? "NPACK ": "";
		packedby += (pTech.equals(PETITE)) 		? "PETITE ": "";
		packedby += (pTech.equals(YODA)) 		? "YODA ": "";
		packedby += (pTech.equals(ASPACK)) 		? "ASPACK ": "";
		System.out.println(packedby);
	}
	
	public PackerTechniques getTechniques ()
	{
		return techniques;
	}
	
}
