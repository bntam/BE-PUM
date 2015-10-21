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
	private String backupDetectionCountState = "";
	
	public PackerDetection ()
	{
		isPacked = false;
		techniques = new PackerTechniques();
	}
	
	public void packedBy ()
	{
		String packedby = "File is packed by ";
		String pTech = techniques.getDetailTechniques();
		for (PackerSign pSign: PackerConstants.pTechSign)
		{
			packedby += this.isPackedWith(pSign.getPackerTechSign(), pTech) ? pSign.getPackerName(): "";
			packedby += " ";
		}
		
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
		
		ArrayList<PackerHeader> pHeader = PackerConstants.GetData();
		
		for (PackerHeader hP: pHeader)
		{
			if (detectWithPacker(dataString, hP, byteEP))
			{
				isPackedBy(hP.getPackerName() + " - version: " + hP.getPackerVersion());
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
	
	private boolean detectWithPacker (String[] dataString, PackerHeader hPacker, String byteEP)
	{
		if (dataString == null) {
			return false;
		}
		
		int beginTracing = 0;
		if (hPacker.isEntryPoint())
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
		String[] sArr = hPacker.getPackerSignature();
		// Begin tracing
		for (int i = beginTracing; i < dataString.length && trace; i++)
		{	
			if (dataString[i].equals(sArr[0].toLowerCase()) 
				&& (dataString[i+1].equals(sArr[1].toLowerCase())
				|| sArr[1].equals("??")))
			{
				for (int j = 0; j < sArr.length; j++)
				{
					if (i + j < dataString.length)
					{
						if (!sArr[j].toLowerCase().equals(dataString[i + j]) 
							&& !sArr[j].equals("??"))
						{
							break;
						}
					}
					if (j == sArr.length - 1)
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
		String packerResultFileName = Program.getPackerResultFileName();
		String content = this.backupDetectionState;
		this.writeFileUpdate(packerResultFile, packerResultFileName, content);
		
		FileProcess packerResultCountFile = prog.getPackerResultCountFile();
		String packerResultCountFileName = Program.getPackerResultCountFileName();
		String contentC = this.backupDetectionCountState;
		this.writeFileUpdate(packerResultCountFile, packerResultCountFileName, contentC);
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
			if (viaBEPUM.split("-")[0].charAt(i) == '1') {
				result += ("x" + "\t");
			} else {
				result += (" " + "\t");
			}
		}
		result += (viaBEPUM.split("-")[1] + "\t"
				+ nodes + "\t"
				+ edges + "\t"
				+ times + "\t"
				+ convergence + "\t");
		
		this.curDetectionFile = fileName;
		this.backupDetectionState = result;
		
		// Update packer count
		String resultC = fileName + "\t" + viaHeader + "\t";
		resultC += this.getTechniques().getTechniquesStatiscial();
		resultC += viaBEPUM.split("-")[1] + "\t";
		resultC += nodes + "\t" + edges + "\t" + times + "\t" + convergence + "\t";
		this.backupDetectionCountState = resultC;	
	}
	
	private void writeFileUpdate (FileProcess file, String fileName, String content)
	{		
		ArrayList<String> resultString = new ArrayList<String>();
		
		try {
			BufferedReader input = new BufferedReader(new FileReader(fileName));

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
					file.appendFile(content);
				}
				else 
				{
					resultString.remove(resultString.size() - 1);
					resultString.add(content);
					PrintWriter pResFileTemp = new PrintWriter(fileName);
					pResFileTemp.close();
					
					PrintWriter pResFile = new PrintWriter(fileName);
					for (String line : resultString) {
						pResFile.println(line);
					}
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
}
