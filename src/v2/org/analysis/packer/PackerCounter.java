package v2.org.analysis.packer;

import java.util.ArrayList;

import org.jakstab.Program;

import v2.org.analysis.packer.techniques.AntiDebugging;
import v2.org.analysis.packer.techniques.Checksumming;
import v2.org.analysis.packer.techniques.CodeChunking;
import v2.org.analysis.packer.techniques.IndirectJump;
import v2.org.analysis.packer.techniques.ObfuscatedConst;
import v2.org.analysis.packer.techniques.OverlappingBlock;
import v2.org.analysis.packer.techniques.OverlappingFunction;
import v2.org.analysis.packer.techniques.Overwriting;
import v2.org.analysis.packer.techniques.PackerTechnique;
import v2.org.analysis.packer.techniques.PackingUnpacking;
import v2.org.analysis.packer.techniques.SEH;
import v2.org.analysis.packer.techniques.StolenBytes;
import v2.org.analysis.packer.techniques.TimingCheck;
import v2.org.analysis.packer.techniques.TwoSpecialAPIs;
import v2.org.analysis.path.BPState;

public class PackerCounter {
	
	private BPState m_state;
	private Program m_prog;
	
	private ArrayList<PackerTechnique> pTechs = new ArrayList<>();
	
	private PackerTechnique antiDebuggingC;
	private PackerTechnique checksummingC;
	private PackerTechnique codeChunkingC;
	private PackerTechnique indirectJumpC;
	private PackerTechnique obfuscatedConstC;
	private PackerTechnique overlappingBlockC;
	private PackerTechnique overlappingFuncC;
	private PackerTechnique overwrittingC;
	private PackerTechnique packingUnpackingC;
	private PackerTechnique sehC;
	private PackerTechnique stolenBytesC;
	private PackerTechnique timingCheckC;
	private PackerTechnique twoAPIsC;
	
	public PackerCounter ()
	{	
		antiDebuggingC		= new AntiDebugging();
		checksummingC		= new Checksumming();
		codeChunkingC		= new CodeChunking();
		indirectJumpC 		= new IndirectJump();
		obfuscatedConstC	= new ObfuscatedConst();
		overlappingBlockC	= new OverlappingBlock();
		overlappingFuncC	= new OverlappingFunction();
		overwrittingC		= new Overwriting();
		packingUnpackingC	= new PackingUnpacking();
		sehC				= new SEH();
		stolenBytesC		= new StolenBytes();
		timingCheckC		= new TimingCheck();
		twoAPIsC			= new TwoSpecialAPIs();
		
		pTechs.add(antiDebuggingC);
		pTechs.add(checksummingC);
		pTechs.add(codeChunkingC);
		pTechs.add(indirectJumpC);
		pTechs.add(obfuscatedConstC);
		pTechs.add(overlappingBlockC);
		pTechs.add(overlappingFuncC);
		pTechs.add(overwrittingC);
		pTechs.add(packingUnpackingC);
		pTechs.add(sehC);
		pTechs.add(stolenBytesC);
		pTechs.add(timingCheckC);
		pTechs.add(twoAPIsC);
	}
	
	public void Execute (boolean run)
	{
		if (run)
		{
			for (PackerTechnique pTech : this.pTechs)
			{
				pTech.Count(this.m_state, this.m_prog);
			}
		}
	}
	
	public void setCountingState (BPState curState, Program prog)
	{
		this.m_state 	= curState;
		this.m_prog		= prog;
	}
	
	public String getInfo ()
	{
		String result = "";
		
		for (PackerTechnique pTech: pTechs)
		{
			result += String.valueOf(pTech.GetInfo());
			result += "\t";
		}
		
		return result;
	}
}
