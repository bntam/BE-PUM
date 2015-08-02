package v2.org.analysis.algorithm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.jakstab.Program;

public class PackerDetection {

	private PackerTechniques techniques;
	
	public PackerDetection ()
	{
		techniques = new PackerTechniques();
	}
	
	public void packedBy ()
	{
		String packedby = "File packed by ";
		String pTech = techniques.getDetailTechniques();
		packedby += (pTech.equals(PackerConstants.UPX)) 		? "UPX ": "";
		packedby += (pTech.equals(PackerConstants.FSG)) 		? "FSG ": "";
		packedby += (pTech.equals(PackerConstants.PECOMPACT)) 	? "PECOMPACT ": "";
		packedby += (pTech.equals(PackerConstants.NPACK)) 		? "NPACK ": "";
		packedby += (pTech.equals(PackerConstants.PETITE)) 		? "PETITE ": "";
		packedby += (pTech.equals(PackerConstants.YODA)) 		? "YODA ": "";
		packedby += (pTech.equals(PackerConstants.ASPACK)) 		? "ASPACK ": "";
		System.out.println(packedby);
	}
	
	public PackerTechniques getTechniques ()
	{
		return techniques;
	}
	
	public void detectViaHeader (Program prog)
	{
		// Entry point
		String byteEP = String.format("%02x", prog.getByteValueMemory(prog.getEntryPoint()));
		
		// Read file
		String file = prog.getAbsolutePathFile();
		Path path = Paths.get(file);
		String[] dataString = null;
		try {
			byte[] dataByte = Files.readAllBytes(path);
			dataString = new String[dataByte.length];
		    for(int i = 0; i < dataByte.length; i++) {
		        dataString[i] = String.format("%02x", dataByte[i]);
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String packedby = "File is packed by ";
		// UPX
		if (detectWithPacker(dataString, PackerConstants.hUPX, byteEP))
		{
			packedby += "UPX";
			System.out.println(packedby);
			return;
		}
		// FSG
		if (detectWithPacker(dataString, PackerConstants.hFSG, byteEP))
		{
			packedby += "FSG";
			System.out.println(packedby);
			return;
		}
		// PECOMPACT
		if (detectWithPacker(dataString, PackerConstants.hPECOMPACT, byteEP))
		{
			packedby += "PECOMPACT";
			System.out.println(packedby);
			return;
		}
		// PETITE
		if (detectWithPacker(dataString, PackerConstants.hPETITE, byteEP))
		{
			packedby += "PETITE";
			System.out.println(packedby);
			return;
		}
		// YODA
		if (detectWithPacker(dataString, PackerConstants.hYODA, byteEP))
		{
			packedby += "YODA";
			System.out.println(packedby);
			return;
		}
		// ASPACK
		if (detectWithPacker(dataString, PackerConstants.hASPACK, byteEP))
		{
			packedby += "ASPACK";
			System.out.println(packedby);
			return;
		}
		//NPACK
		// Add later
	}
	
	private boolean detectWithPacker (String[] dataString, String[] hPacker, String byteEP)
	{
		if (dataString == null)
			return false;
		
		boolean fromEntryPoint = Boolean.parseBoolean(hPacker[hPacker.length - 1]);
		int beginTracing = 0;
		if (fromEntryPoint)
		{
			for (int i = 0; i < hPacker.length; i++)
			{
				if (hPacker[i].toLowerCase().equals(byteEP))
				{
					beginTracing = i;
					break;
				}
			}
		}
		
		boolean trace = true;
		// Begin tracing
		for (int i = beginTracing; i < dataString.length && trace; i++)
		{
			if (dataString[i].equals(hPacker[0].toLowerCase()) 
				&& (dataString[i+1].equals(hPacker[1].toLowerCase())
				|| hPacker[1].equals("??")))
			{
				for (int j = 0; j < hPacker.length - 1; j++)
				{
					if (!hPacker[j].toLowerCase().equals(dataString[i + j]) 
						&& !hPacker[j].equals("??"))
					{
						break;
					}
					if (j == hPacker.length - 2)
					{
						trace = false;
					}
				}
			}
			else if (i == dataString.length - 2)
			{
				return false;
			}
		}
		return true;
	}
	
}