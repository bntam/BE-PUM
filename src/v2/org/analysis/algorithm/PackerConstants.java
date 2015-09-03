package v2.org.analysis.algorithm;

public final class PackerConstants {

	// Techniques String
	public static final String UPX 			= "11110001010";
	public static final String FSG 			= "11010101010";
	public static final String NPACK 		= "11110111010";
	public static final String PECOMPACT 	= "11110011110";
	public static final String PETITE 		= "11110101100";
	public static final String YODA 		= "11110101110";
	public static final String ASPACK 		= "11010111000";
	
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
	
	public static final String[] hNPACK		= {"83","3D","??","??","??","00","00","75","05"
											  ,"E9","01","00","00","00","C3","E8","46","00"
											  ,"00","00","E8","73","00","00","00","B8","??"
											  ,"??","??","??","2B","05","08","??","??","??"
											  ,"A3","??","??","??","??","E8","9C","00","00"
											  ,"00","E8","??","02","00","00","E8","??","06"
											  ,"00","00","E8","??","06","00","00","A1","??"
											  ,"??","??","??","C7","05","??","??","??","00"
											  ,"01","00","00","00","01","05","00","??","??"
											  ,"??","FF","35","00", "true"};
	
	public static final String[] hARMADILLO	= {"60","E8","00","00","00","00","5D","50","51"
											  ,"0F","CA","F7","D2","9C","F7","D2","0F","CA"
											  ,"EB","0F","B9","EB","0F","B8","EB","07","B9"
											  ,"EB","0F","90","EB","08","FD","EB","0B","F2"
											  ,"EB","F5","EB","F6","F2","EB","08","FD","EB"
											  ,"E9","F3","EB","E4","FC","E9","9D","0F","C9"
											  ,"8B","CA","F7","D1","59","58","50","51","0F"
											  ,"CA","F7","D2","9C","F7","D2","0F","CA","EB"
											  ,"0F","B9","EB","0F","B8","EB","07","B9","EB"
											  ,"0F","90","EB","08","true"};
	
	public static final String[] hASPROTECT = {"68","01","??","??","??","E8","01","00","00"
											  ,"00","C3","C3","true"};
	
	public static final String[] hMEW		= {"E9","??","??","??","FF","0C","??","??","00"
											  ,"00","00","00","00","00","00","00","00","??"
											  ,"??","??","00","0C","??","??","00","true"};
	
	public static final String[] hMPRESS	= {"60","E8","00","00","00","00","58","05","??"
											  ,"??","??","??","8B","30","0?","?0","2B","C0"
											  ,"8B","FE","66","AD","C1","E0","0C","8B","C8"
											  ,"50","AD","2B","C8","0?","?1","8B","C8","57"
											  ,"51","49","8A","44","39","06","88","04","31"
											  ,"75","F6","false"};
	
	public static final String[] hPELOCK	= {"4C","6F","61","64","4C","69","62","72","61"
											  ,"72","79","41","00","00","56","69","72","74"
											  ,"75","61","6C","41","6C","6C","6F","63","00"
											  ,"4B","45","true"};
	
	public static final String[] hPESPIN	= {"EB","01","??","60","E8","00","00","00","00"
											  ,"8B","1C","24","83","C3","12","81","2B","E8"
											  ,"B1","06","00","FE","4B","FD","82","2C","24"
											  ,"??","??","46","00","0B","E4","74","9E","75"
											  ,"01","C7","81","73","04","D7","7A","F7","2F"
											  ,"81","73","19","77","00","43","B7","F6","C3"
											  ,"6B","B7","00","00","F9","FF","E3","C9","C2"
											  ,"08","00","A3","68","72","01","FF","5D","33"
											  ,"C9","41","E2","17","EB","07","??","EB","01"
											  ,"??","EB","0D","??","E8","01","00","00","00"
											  ,"??","5A","83","EA","0B","FF","E2","EB","04"
											  ,"??","EB","04","??","EB","FB","??","??","??"
											  ,"??","??","??","??","??","??","??","??","??"
											  ,"??","??","??","??","??","??","EB","02","??"
											  ,"??","F9","72","08","73","0E","F9","83","04"
											  ,"24","17","C3","E8","04","00","00","00","??"
											  ,"??","??","??","EB","06","??","??","??","??"
											  ,"??","??","F5","72","0E","F5","72","F8","68"
											  ,"EB","EC","83","04","24","07","F5","FF","34"
											  ,"24","C3","true"};
	
	public static final PackerHeader hPacker[] =
		{ new PackerHeader("UPX"		, PackerConstants.hUPX)
	     ,new PackerHeader("FSG"		, PackerConstants.hFSG)
	     ,new PackerHeader("PECOMPACT"	, PackerConstants.hPECOMPACT)
	     ,new PackerHeader("PETITE"		, PackerConstants.hPETITE)
	     ,new PackerHeader("YODA"		, PackerConstants.hYODA)
	     ,new PackerHeader("ASPACK"		, PackerConstants.hASPACK)
	     ,new PackerHeader("NPACK"		, PackerConstants.hNPACK)
	     ,new PackerHeader("ARMADILLO"	, PackerConstants.hARMADILLO)
	     ,new PackerHeader("ASPROTECT"	, PackerConstants.hASPROTECT)
	     ,new PackerHeader("MEW"		, PackerConstants.hMEW)
	     ,new PackerHeader("MPRESS"		, PackerConstants.hMPRESS)
	     ,new PackerHeader("ARMADILLO"	, PackerConstants.hPELOCK)
	     ,new PackerHeader("ARMADILLO"	, PackerConstants.hPESPIN)
		};
}
