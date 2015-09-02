package v2.org.analysis.algorithm;

import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Immediate;
import org.jakstab.asm.Operand;
import org.jakstab.asm.x86.X86AbsoluteAddress;
import org.jakstab.asm.x86.X86MemoryOperand;
import org.jakstab.asm.x86.X86Register;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class PackerPatterns {
	
	private BPState curState;
	private Program m_program;
	
	private final int IS_PACKING = 50;
	private int smcCount;
	
	public PackerPatterns()
	{
		this.curState 	= null;
		this.m_program 	= null;
		
		this.smcCount  	= 0;
	}
	
	/*
	 * Check if it is encryption
	 */
	public boolean PackAndUnpack()
	{
		if (this.smcCount >= this.IS_PACKING)
		{
			return true;
		}
		return false;
	}
	
	/*
	 * Check if it is SMC
	 */
	public boolean Overwriting()
	{
		if (curState == null || curState.getInstruction() == null) return false;
		
		Environment env = curState.getEnvironement();
		int opCount = this.curState.getInstruction().getOperandCount();
		if (opCount >= 1)
		{
			Operand dest = this.curState.getInstruction().getOperand(0);
			Value aVal = null;
			if (dest instanceof X86Register)
			{
				aVal = env.getRegister().getRegisterValue(dest.toString());
			}
			else if (dest instanceof X86MemoryOperand)
			{
				aVal = env.getMemory().getDoubleWordMemoryValue((X86MemoryOperand)dest);
			}
			if (aVal instanceof LongValue)
			{
				AbsoluteAddress aAddr = new AbsoluteAddress(((LongValue) aVal).getValue());
				if (this.IsInCodeSection(aAddr))
				{
					this.smcCount++;
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean IndirectJump()
	{
		if (curState == null || curState.getInstruction() == null) return false;
		
		String insName = this.curState.getInstruction().getName();
		if (insName.contains("call"))
		{
			Operand dest = this.curState.getInstruction().getOperand(0);
			if (dest instanceof X86Register)
			{
				return true;
			}
		}
		// Added return indirect jump
		return false;
	}
	
	public boolean ObfuscatedConst()
	{
		if (curState == null || curState.getInstruction() == null) return false;
		
		int opCount = this.curState.getInstruction().getOperandCount();
		if (opCount >= 1)
		{
			Operand op1 = this.curState.getInstruction().getOperand(0);
			Operand op2 = null;
			if (opCount >= 2)
			{
				op2 = this.curState.getInstruction().getOperand(1);
			}
			if (op1 instanceof Immediate)
			{
				return true;
			}
			if (op2 instanceof Immediate)
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean Overlapping()
	{
		return false;
	}
	
	public boolean CodeChunking()
	{
		return false;
	}
	
	public boolean StolenBytes()
	{
		return false;
	}
	
	public boolean Checksumming()
	{
		return false;
	}
	
	public boolean SEHs()
	{
		return false;
	}
	
	public boolean TwoAPIs()
	{
		return false;
	}
	
	public boolean AntiDebugging()
	{
		return false;
	}
	
	public void setCheckingState (BPState cState, Program prog)
	{
		this.curState = cState;
		this.m_program = prog;
	}
	
	private boolean IsInCodeSection (AbsoluteAddress address)
	{
		return m_program.getMainModule().isCodeArea(address);
	}
	
}
