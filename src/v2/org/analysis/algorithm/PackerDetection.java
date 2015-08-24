package v2.org.analysis.algorithm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.jakstab.Program;
import org.jakstab.util.Characters;

import com.sun.jna.WString;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.cfg.BPCFG;
import v2.org.analysis.statistics.FileProcess;

public class PackerDetection {

	private PackerTechniques techniques;
	
	private String detectViaHeader;
	private String detectViaBEPUM;
	
	public PackerDetection ()
	{
		techniques = new PackerTechniques();
	}
	
	public void packedBy ()
	{
		String packedby = "File is packed by ";
		String pTech = techniques.getDetailTechniques();
		packedby += (pTech.equals(PackerConstants.UPX)) 		? "UPX ": "";
		packedby += (pTech.equals(PackerConstants.FSG)) 		? "FSG ": "";
		packedby += (pTech.equals(PackerConstants.PECOMPACT)) 	? "PECOMPACT ": "";
		packedby += (pTech.equals(PackerConstants.NPACK)) 		? "NPACK ": "";
		packedby += (pTech.equals(PackerConstants.PETITE)) 		? "PETITE ": "";
		packedby += (pTech.equals(PackerConstants.YODA)) 		? "YODA ": "";
		packedby += (pTech.equals(PackerConstants.ASPACK)) 		? "ASPACK ": "";
		System.out.println(packedby);
		Program.getProgram().setLog("Via OTF, " + packedby);
		
		if (packedby.equals("File is packed by "))
		{
			this.detectViaBEPUM = pTech + "-" + "NONE";
		}
		else 
		{
			this.detectViaBEPUM = pTech + "-" 
					+ packedby.substring(new String("File is packed by ").length());
		}
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
		
		// UPX
		if (detectWithPacker(dataString, PackerConstants.hUPX, byteEP))
		{
			isPackedBy("UPX");
			return;
		}
		// FSG
		if (detectWithPacker(dataString, PackerConstants.hFSG, byteEP))
		{
			isPackedBy("FSG");
			return;
		}
		// PECOMPACT
		if (detectWithPacker(dataString, PackerConstants.hPECOMPACT, byteEP))
		{
			isPackedBy("PECOMPACT");
			return;
		}
		// PETITE
		if (detectWithPacker(dataString, PackerConstants.hPETITE, byteEP))
		{
			isPackedBy("PETITE");
			return;
		}
		// YODA
		if (detectWithPacker(dataString, PackerConstants.hYODA, byteEP))
		{
			isPackedBy("YODA");
			return;
		}
		// ASPACK
		if (detectWithPacker(dataString, PackerConstants.hASPACK, byteEP))
		{
			isPackedBy("ASPACK");
			return;
		}
		//NPACK
		if (detectWithPacker(dataString, PackerConstants.hNPACK, byteEP))
		{
			isPackedBy("NPACK");
			return;
		}
		else
		{
			this.detectViaHeader = "NONE";
		}
	}
	
	private void isPackedBy(String packerName)
	{
		String packedby = "File is packed by " + packerName;
		System.out.println(packedby);
		Program.getProgram().setLog("Detect via Header, " + packedby);
		this.detectViaHeader = packedby.substring(new String("File is packed by ").length());
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
	
	public void setToLog (Program prog, OTFModelGeneration otfMG)
	{
		BPCFG cfg = prog.getBPCFG();

		FileProcess packerResultFile = prog.getPackerResultFile();

		Kernel32DLL.INSTANCE.SetCurrentDirectory(new WString(System.getProperty("user.dir")));
		
		String fileName = prog.getFileName();
		String viaHeader = this.detectViaHeader;
		String viaBEPUM = this.detectViaBEPUM;
		String nodes = String.format("%8d", cfg.getVertexCount());
		String edges = String.format("%8d", cfg.getEdgeCount());
		String times = Long.toString(prog.GetAnalyzingTime());
		String convergence = otfMG.isCompleted() ? "x": " ";
		
		String result = fileName + "\t" + viaHeader + "\t";
		for(int i = 0; i < viaBEPUM.split("-")[0].length(); i++)
		{
			if (viaBEPUM.split("-")[0].charAt(i) == '1')
				result += ("x" + "\t");
			else result += (" " + "\t");
		}
		result += (viaBEPUM.split("-")[1] + "\t"
				+ nodes + "\t"
				+ edges + "\t"
				+ times + "\t"
				+ convergence + "\t");
		
		packerResultFile.appendFile(result);
	}
	
}
