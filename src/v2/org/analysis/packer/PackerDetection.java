package v2.org.analysis.packer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.jakstab.Program;

import com.sun.jna.WString;

import v2.org.analysis.algorithm.OTFModelGeneration;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.cfg.BPCFG;
import v2.org.analysis.statistics.FileProcess;

public class PackerDetection {

	private boolean isPacked;
	
	private PackerTechniques techniques;
	
	private String detectViaHeader;
	private String detectViaBEPUM;
	
	private String curDetectionFile = "";
	private String backupDetectionState = "";
	
	public PackerDetection ()
	{
		isPacked = false;
		techniques = new PackerTechniques();
	}
	
	public void packedBy ()
	{
		String packedby = "File is packed by ";
		String pTech = techniques.getDetailTechniques();
		packedby += this.isPackedWith(PackerConstants.UPX, pTech)		? "UPX ": "";
		packedby += this.isPackedWith(PackerConstants.FSG, pTech)		? "FSG ": "";
		packedby += this.isPackedWith(PackerConstants.PECOMPACT, pTech) ? "PECOMPACT ": "";
		packedby += this.isPackedWith(PackerConstants.NPACK, pTech) 	? "NPACK ": "";
		packedby += this.isPackedWith(PackerConstants.PETITE, pTech) 	? "PETITE ": "";
		packedby += this.isPackedWith(PackerConstants.YODA, pTech)		? "YODA ": "";
		packedby += this.isPackedWith(PackerConstants.ASPACK, pTech) 	? "ASPACK ": "";
		
		if (packedby.equals("File is packed by "))
		{
			this.isPacked = false;
			this.detectViaBEPUM = pTech + "-" + "NONE";
		}
		else 
		{
			this.isPacked = true;
			this.detectViaBEPUM = pTech + "-" 
					+ packedby.substring(new String("File is packed by ").length());
		}
		System.out.println(detectViaBEPUM);
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
		
		for (PackerHeader hP: PackerConstants.hPacker)
		{
			if (detectWithPacker(dataString, hP.getPackerSignature(), byteEP))
			{
				isPackedBy(hP.getPackerName());
				return;
			}
		}
		this.detectViaHeader = "NONE";
	}
	
	private void isPackedBy(String packerName)
	{
		String packedby = "File is packed by " + packerName;
		System.out.println(packedby);
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
			for (int i = 0; i < dataString.length; i++)
			{
				if (dataString[i].toLowerCase().equals(byteEP))
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
	
	public void setToLogFirst (Program prog)
	{
		FileProcess packerResultFile = prog.getPackerResultFile();
	
		String fileName = prog.getFileName();
		String viaHeader = this.detectViaHeader;
		
		String resultFirst = fileName + "\t" + viaHeader + "\t";
		
		packerResultFile.appendFile(resultFirst);
	}
	
	public void setToLog (Program prog)
	{
		FileProcess packerResultFile = prog.getPackerResultFile();
		
		ArrayList<String> resultString = new ArrayList<String>();
		
		try {
			BufferedReader input = new BufferedReader(
					new FileReader(Program.getPackerResultFileName()));

		    try {
		    	String lastLine = "", curLine = "";
		    	
				while ((curLine = input.readLine()) != null) {
					resultString.add(curLine);
				    lastLine = curLine;
				}
				
				input.close();
				
				String curFile = this.curDetectionFile;
				
				if (!lastLine.contains(curFile))
				{
					packerResultFile.appendFile(this.backupDetectionState);
				}
				else 
				{
					resultString.remove(resultString.size() - 1);
					resultString.add(this.backupDetectionState);
					PrintWriter pResFileTemp = new PrintWriter(
							Program.getPackerResultFileName());
					pResFileTemp.close();
					
					PrintWriter pResFile = new PrintWriter(
							Program.getPackerResultFileName());
					for (String line : resultString)
				        pResFile.println(line);
				    pResFile.close();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isPackedWith(String packerStr, String techStr)
	{
		for (int i = 0; i < packerStr.length(); i++)
		{
			if (packerStr.charAt(i) == '1' && techStr.charAt(i) == '0')
			{
				return false;
			}
		}
		return true;
	}
	
	public boolean fileIsPacked ()
	{
		return this.isPacked;
	}

	public void updateBackupDetectionState (Program prog, OTFModelGeneration otfMG)
	{
		BPCFG cfg = prog.getBPCFG();

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
		
		this.curDetectionFile = fileName;
		this.backupDetectionState = result;
	}
}
