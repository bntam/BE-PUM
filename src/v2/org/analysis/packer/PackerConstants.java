package v2.org.analysis.packer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public final class PackerConstants {

	public static final int ANTI_DEBUGGING 		= 0;
	public static final int CHECKSUMMING		= ANTI_DEBUGGING 	+ 1;
	public static final int CODE_CHUNKING		= CHECKSUMMING 		+ 1;
	public static final int INDIRECT_JUMP		= CODE_CHUNKING 	+ 1;
	public static final int OBFUSCATED_CONST	= INDIRECT_JUMP		+ 1;
	public static final int OVERLAPPING_BLOCK	= OBFUSCATED_CONST 	+ 1;
	public static final int OVERLAPPING_FUNC	= OVERLAPPING_BLOCK	+ 1;
	public static final int OVERWRITING			= OVERLAPPING_FUNC	+ 1;
	public static final int PACKING_UNPACKING	= OVERWRITING		+ 1;
	public static final int SEH					= PACKING_UNPACKING + 1;
	public static final int STOLEN_BYTES		= SEH 				+ 1;
	public static final int TIMING_CHECK		= STOLEN_BYTES		+ 1;
	public static final int TWO_APIS			= TIMING_CHECK		+ 1;
	public static final int HARDWARE_BPX		= TWO_APIS			+ 1;
	
	private static final String ASPACK 			= "01111111101000";
	private static final String FSG 			= "01011111100010";
	private static final String MPRESS 			= "01011101100000";
	private static final String NPACK 			= "01011111101010";
	private static final String PECOMPACT 		= "01011111111010";
	private static final String PETITE 			= "01011111110000";
	private static final String UPX 			= "01011101100010";
	private static final String YODA 			= "11111111110010";
	private static final String TELOCK			= "01111111111001";
	
	public static final PackerSign[] pTechSign 		= {new PackerSign("ASPACK", 	ASPACK),
													   new PackerSign("FSG", 		FSG),
													   new PackerSign("MPRESS", 	MPRESS),
													   new PackerSign("NPACK", 		NPACK),
													   new PackerSign("PECOMPACT", 	PECOMPACT),
													   new PackerSign("PETITE", 	PETITE),
													   new PackerSign("UPX", 		UPX),
													   new PackerSign("YODA", 		YODA),
													   new PackerSign("TELOCK",     TELOCK)};
												
	
	private static final String cASPACK				= "0:1:1:12:63:5:3:45:1:0:4:0:0:0";
	private static final String cFSG				= "0:1:0:12:2:1:1:4:1:0:0:0:2:0";
	private static final String cMPRESS				= "0:1:0:4:34:6:0:14:1:0:0:0:0:0";
	private static final String cNPACK				= "0:1:0:9:24:3:2:11:1:0:4:0:3:0";
	private static final String cPECOMPACT			= "0:1:0:16:60:7:4:28:1:3:2:0:2:0";
	private static final String cPETITE				= "0:1:0:8:115:12:1:51:1:4:0:0:0:0";
	private static final String cUPX				= "0:1:0:4:25:1:0:7:1:0:0:0:2:0";
	private static final String cYODA				= "1:1:1:15:141:6:4:120:1:9:0:0:3:0";
	private static final String cTELOCK				= "0:1:1:22:167:20:2:136:12:35:4:0:0:1";
	
	public static final PackerSign[] pTechCountSign		= {new PackerSign("ASPACK", 	cASPACK),
														   new PackerSign("FSG", 		cFSG),
														   new PackerSign("MPRESS", 	cMPRESS),
			   											   new PackerSign("NPACK", 		cNPACK),
			   											   new PackerSign("PECOMPACT", 	cPECOMPACT),
			   											   new PackerSign("PETITE", 	cPETITE),
			   											   new PackerSign("UPX", 		cUPX),
			   											   new PackerSign("YODA", 		cYODA),
			   											   new PackerSign("TELOCK", 	cTELOCK)};
	
	public static final String signatureFile 		= "data/data/PackerSignature/packer_signature.json";
	public static final String PACKER_NAME_TAG 		= "PACKER_NAME";
	public static final String VERSION_TAG 			= "VERSION";
	public static final String ENTRY_POINT_TAG 		= "IS_ENTRY_POINT";
	public static final String SIGNATURE_TAG 		= "SIGNATURE";
	
	public static ArrayList<PackerHeader> GetData ()
	{
		ArrayList<PackerHeader> hPacker = new ArrayList<PackerHeader>();
		
		JSONParser jParser = new JSONParser();
		try {
			JSONArray jArray  =(JSONArray) jParser.parse(new FileReader(signatureFile));
			for (Object o : jArray) {
	        	
	        	JSONObject pSignature = (JSONObject) o;

	            String packerName = (String) pSignature.get(PACKER_NAME_TAG);
	            String packerVersion = (String) pSignature.get(VERSION_TAG);
	            boolean isEntryPoint = ((String) pSignature.get(ENTRY_POINT_TAG)).contains("YES");  
	            String[] sArr = parseSignature((String) pSignature.get(SIGNATURE_TAG));
	            
	            PackerHeader pHeader = new PackerHeader(packerName,
	            						   				packerVersion,
	            						   				isEntryPoint,
	            						   				sArr);
	            hPacker.add(pHeader);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return hPacker;
	}
	
	private static String[] parseSignature(String signature)
	{
		String[] sArr = {};
		ArrayList<String> sList = new ArrayList<String>();
		if (signature.length() % 2 != 0)
		{
			signature += "?";
		}
		for (int i = 0; i < signature.length(); i++)
		{
			if (i % 2 == 0)
			{
				sList.add(signature.substring(i, i + 2));
			}
		}
		return sList.toArray(sArr);
	}
}
