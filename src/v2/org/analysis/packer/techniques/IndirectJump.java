package v2.org.analysis.packer.techniques;

import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.Operand;
import org.jakstab.asm.x86.X86CondJmpInstruction;
import org.jakstab.asm.x86.X86JmpInstruction;
import org.jakstab.asm.x86.X86MemoryOperand;
import org.jakstab.asm.x86.X86Register;
import org.jakstab.asm.x86.X86RetInstruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class IndirectJump {

	/** 
	 * Using for record indirect jump
	 */
	
	private static int numOfCall;
	private static int numOfIndirectCall;
	private static int numOfIndirectRetn;
	private static int numOfIndirectJump;
	
	public IndirectJump ()
	{
		numOfCall				= 0;
		numOfIndirectCall 		= 0;
		numOfIndirectJump		= 0;
		numOfIndirectRetn		= 0;
	}
	
	public void Count (BPState curState, Program prog)
	{
		if (curState == null || curState.getInstruction() == null) return;
		
		Instruction ins = curState.getInstruction();
		String insName = ins.getName();
		if (insName.contains("call"))
		{
			numOfCall++;
			Operand dest = curState.getInstruction().getOperand(0);
			if (dest instanceof X86Register || dest instanceof X86MemoryOperand)
			{
				numOfIndirectCall++;
			}
		}
		else if (ins instanceof X86JmpInstruction || ins instanceof X86CondJmpInstruction)
		{
			Operand dest = curState.getInstruction().getOperand(0);
			if (dest instanceof X86Register || dest instanceof X86MemoryOperand)
			{
				numOfIndirectJump++;
			}
		}
		else if (ins instanceof X86RetInstruction)
		{
			Value ret = curState.getEnvironement().getStack().getValueStackFromIndex(0);
			if (ret != null && ret instanceof LongValue)
			{
				AbsoluteAddress aAddr = new AbsoluteAddress(((LongValue) ret).getValue());
				if (prog.getMainModule().isCodeArea(aAddr))
				{
					numOfIndirectRetn++;
				}
			}
		}
	}
	
	public void GetInfo ()
	{
		System.out.println("Number of calls: " + numOfCall);
		System.out.println("Number of indirect calls: " 	+ numOfIndirectCall);
		System.out.println("Number of indirect return: " 	+ numOfIndirectRetn);
		System.out.println("Number of indirect jump: " 		+ numOfIndirectJump);
	}
	
}
