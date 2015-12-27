package v2.org.analysis.packer.techniques;

import java.util.ArrayList;

import org.jakstab.Program;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.Operand;
import org.jakstab.asm.x86.X86JmpInstruction;

import v2.org.analysis.packer.PackerConstants;
import v2.org.analysis.packer.PackerHelper;
import v2.org.analysis.packer.PackerRecord;
import v2.org.analysis.packer.PackerSavedBlock;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class OverlappingBlock implements PackerTechnique {

	/** 
	 * Using for record overlapping function
	 */
	
	private static int numOfOverlappingBlock;
	private static boolean firstCheck;
	private static long tracingBlockLoc;
	private static ArrayList<PackerSavedBlock> blocks;
	
	private static ArrayList<Long> savedListBlock;
	
	private static boolean isOverlap;
	
	public OverlappingBlock ()
	{
		numOfOverlappingBlock			= 0;
		
		firstCheck						= true;
		tracingBlockLoc					= 0x0;
		blocks 							= new ArrayList<PackerSavedBlock>();
		
		savedListBlock					= new ArrayList<Long>();
		
		isOverlap						= false;
	}
	
	@Override
	public void Count (BPState curState, Program prog)
	{
		if (curState == null || curState.getInstruction() == null) {
			return;
		}
		
		if (firstCheck)
		{
			tracingBlockLoc = curState.getLocation().getValue();
			firstCheck = false;
		}
		
		Instruction ins = curState.getInstruction();
		if (ins instanceof X86JmpInstruction)
		{
			Operand op = ins.getOperand(0); 
			Value aVal = PackerHelper.GetOperandValue(curState, op);
			if (aVal instanceof LongValue)
			{
				PackerSavedBlock savedBlock = new PackerSavedBlock(tracingBlockLoc
																 , curState.getLocation().getValue());
				int size = blocks.size();
				if (size > 1)
				{
					for (PackerSavedBlock block: blocks)
					{
						if (PackerHelper.CheckOverlapping(block, savedBlock)
								&& !PackerHelper.IsExisted(savedListBlock, new Long(block.getBeginBlock()))
								&& !PackerHelper.IsExisted(savedListBlock, new Long(savedBlock.getBeginBlock())))
						{
							numOfOverlappingBlock++;
							PackerRecord.getInstance().updatePackerTechniqueRecord(String.valueOf(PackerConstants.OVERLAPPING_BLOCK));
							isOverlap = true;
							savedListBlock.add(new Long(block.getBeginBlock()));
							savedListBlock.add(new Long(savedBlock.getBeginBlock()));
							break;
						}
					}
				}
				blocks.add(savedBlock);
				tracingBlockLoc = ((LongValue) aVal).getValue();
				if (isOverlap)
				{
					blocks = PackerHelper.ClearStates(blocks);
					isOverlap = false;
				}
			}
		}
	}
	
	@Override
	public int GetInfo ()
	{
		return numOfOverlappingBlock;
	}
	
}
