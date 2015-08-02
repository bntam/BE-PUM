package v2.org.analysis.algorithm;

public final class PackerConstants {

	// Techniques String
	public static final String UPX = "10110000010";
	public static final String FSG = "10111000010";
	public static final String PECOMPACT = "11111111110";
	public static final String NPACK = "10111000010";
	public static final String PETITE = "00110000100";
	public static final String YODA = "10111101111";
	public static final String ASPACK = "10101000000";

	// Packer Header
	public static final String[] hUPX 		= {"5E","89","F7","B9","??","??","??","??","8A"
											  ,"07","47","2C","E8","3C","01","77","F7","80"
											  ,"3F","??","75","F2","8B","07","8A","5F","04"
											  ,"66","C1","E8","08","C1","C0","10","86","C4"
											  ,"29","F8","80","EB","E8","01","F0","89","07"
											  ,"83","C7","05","88","D8","E2","D9","8D","??"
											  ,"??","??","??","??","8B","07","09","C0","74"
											  ,"3C","8B","5F","04","8D","??","??","??","??"
											  ,"??","??","01","F3","50","83","C7","08","FF"
											  ,"false"};
	
	public static final String[] hFSG		= {"87","25","??","??","??","00","61","94","55"
											  ,"A4","B6","80","FF","13","true"};
	
	public static final String[] hPECOMPACT = {"B8","??","??","??","??","50","64","FF","35"
											  ,"00","00","00","00","64","89","25","00","00"
											  ,"00","00","33","C0","89","08","50","45","43"
											  ,"false"};
	
	public static final String[] hPETITE	= {"B8","??","??","??","??","6A","00","68","??"
											  ,"??","??","??","64","??","??","??","??","??"
											  ,"??","64","??","??","??","??","??","??","66"
											  ,"9C","60","50","true"};
	
	public static final String[] hYODA		= {"55","8B","EC","53","56","57","60","E8","00"
											  ,"00","00","00","5D","81","ED","6C","28","40"
											  ,"00","B9","5D","34","40","00","81","E9","C6"
											  ,"28","40","00","8B","D5","81","C2","C6","28"
											  ,"40","00","8D","3A","8B","F7","33","C0","EB"
											  ,"04","90","EB","01","C2","AC","true"};

	public static final String[] hASPACK	= {"60","E8","03","00","00","00","E9","EB","04"
											  ,"5D","45","55","C3","E8","01","00","00","00"
											  ,"EB","5D","BB","ED","FF","FF","FF","03","DD"
											  ,"81","EB","true"};
}
