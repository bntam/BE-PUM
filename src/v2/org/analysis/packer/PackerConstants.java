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

	private static final String UPX 			= "1111001010110110";
	private static final String FSG 			= "1111001010111110";
	private static final String NPACK 			= "1111011010111110";
	private static final String PECOMPACT 		= "1111011110111110";
	private static final String PETITE 			= "1111001100111110";
	private static final String YODA 			= "1111101111111110";
	private static final String ASPACK 			= "1111111000111110";
	private static final String PEBUNDLE 		= "1111111111111111";
	private static final String MPRESS 			= "1111001000110110";
	private static final String TELOCK 			= "1111111111111111";
	
	public static final PackerSign[] pTechSign 		= {new PackerSign("UPX", 		UPX),
													   new PackerSign("FSG", 		FSG),
													   new PackerSign("NPACK", 		NPACK),
													   new PackerSign("PECOMPACT", 	PECOMPACT),
													   new PackerSign("PETITE", 	PETITE),
													   new PackerSign("YODA", 		YODA),
													   new PackerSign("ASPACK", 	ASPACK),
													   new PackerSign("TELOCK", 	TELOCK),
													   new PackerSign("PEBUNDLE", 	PEBUNDLE),
													   new PackerSign("MPRESS", 	MPRESS)};
												
	
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
