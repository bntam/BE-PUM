package v2.org.analysis.algorithm;

import java.util.ArrayList;

import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Immediate;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.Operand;
import org.jakstab.asm.x86.X86CondJmpInstruction;
import org.jakstab.asm.x86.X86JmpInstruction;
import org.jakstab.asm.x86.X86MemoryOperand;
import org.jakstab.asm.x86.X86Register;

import v2.org.analysis.apihandle.winapi.APIHandle;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

public class PackerPatterns {
	
	private BPState curState;
	private Program m_program;
	
	private final int IS_PACKING = 50;
	private int smcCount;
	
	private boolean setupSEH;
	
	private boolean useLoadLibrary;
	private boolean useGetProcAddress;
	
	private ArrayList<PackerSavedState> chunkCodeStates;
	private final long CHUNK_THRESHOLD = 10;
	
	public PackerPatterns()
	{
		this.curState 			= null;
		this.m_program 			= null;
		
		// Use for detecting SMC (Overwriting)
		this.smcCount  			= 0;
		
		// Use for detecting SEH
		this.setupSEH 			= false;
		
		// Use for detecting 2 special APIs
		this.useLoadLibrary 	= false;
		this.useGetProcAddress 	= false;
		
		// Use for detecting code chunk
		this.chunkCodeStates 	= new ArrayList<PackerSavedState>();
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
		if (opCount >= 2)
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
	
	/*
	 * Check if it is indirect jump
	 */
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
		return false;
	}
	
	/*
	 * Check if it is obfuscated constant.
	 */
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
	
	/*
	 * Check if it is overlapping
	 */
	public boolean Overlapping()
	{
		return false;
	}
	
	/*
	 * Check if it is chunk code
	 */
	public boolean CodeChunking()
	{
		if (curState == null || curState.getInstruction() == null) return false;
		
		Instruction ins = curState.getInstruction();
		if (ins instanceof X86JmpInstruction || ins instanceof X86CondJmpInstruction)
		{
			long insLoc = curState.getLocation().getValue();
			PackerSavedState jmpState = new PackerSavedState(insLoc, ins.getName());
			boolean isExisted = false;
			for (PackerSavedState jState: this.chunkCodeStates)
			{
				if (jState.getInsLoc() == jmpState.getInsLoc())
				{
					isExisted = true;
					break;
				}
			}
			if (!isExisted)
			{
				this.chunkCodeStates.add(jmpState);
			}
		}
		int size = this.chunkCodeStates.size();
		if (this.chunkCodeStates.size() > 2)
		{
			PackerSavedState jmpStateA = this.chunkCodeStates.get(size - 1);
			PackerSavedState jmpStateB = this.chunkCodeStates.get(size - 2);
			PackerSavedState jmpStateC = this.chunkCodeStates.get(size - 3);
			if (Math.abs(jmpStateA.getInsLoc() - jmpStateB.getInsLoc()) <= this.CHUNK_THRESHOLD
				&& Math.abs(jmpStateB.getInsLoc() - jmpStateC.getInsLoc()) <= this.CHUNK_THRESHOLD)
			{
				return true;
			}
		}
		
		return false;
	}
	
	/*
	 * Check if it is VirtualAlloc
	 */
	public boolean StolenBytes()
	{
		if (curState == null || curState.getInstruction() == null) return false;
		
		Environment env = curState.getEnvironement();
		String insName = this.curState.getInstruction().getName();
		if (insName.contains("call"))
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
				String apiName = this.GetAPIName(aVal);
				if (apiName != null)
				{
					if (apiName.contains("VirtualAlloc"))
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/*
	 * Check if it is checksum calculate
	 */
	public boolean Checksumming()
	{
		if (curState == null || curState.getInstruction() == null) return false;

		Instruction ins = curState.getInstruction();
		if (ins.getName().contains("cmp"))
		{
			long nextInsLoc = curState.getLocation().getValue() + ins.getSize();
			Instruction nextIns = this.m_program.getInstruction(new AbsoluteAddress(nextInsLoc), curState.getEnvironement());
			if (nextIns instanceof X86JmpInstruction || nextIns instanceof X86CondJmpInstruction)
			{
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Check if it is structure exception handling
	 */
	public boolean SEHs()
	{
		// Part1: Detect setup SEH
		if (curState == null || curState.getInstruction() == null) return false;
		
		Environment env = curState.getEnvironement();
		if (!this.setupSEH)
		{
			int opCount = this.curState.getInstruction().getOperandCount();
			if (opCount >= 1)
			{
				Operand dest = this.curState.getInstruction().getOperand(0);
				if (dest instanceof X86MemoryOperand)
				{
					X86MemoryOperand memAddr = (X86MemoryOperand) dest;
					boolean base = false;
					if (memAddr.getDisplacement() == 0 && memAddr.getBase() == null)
						base = true;
					else {
						if (memAddr.getBase() != null) {
							Value memVal = env.getRegister().getRegisterValue(memAddr.getBase().toString());
							if (memVal instanceof LongValue)
								base = (((LongValue) memVal).getValue() == 0);
						}
					}

					if (memAddr.getSegmentRegister() != null 
							&& memAddr.getSegmentRegister().toString() == "%fs" && base) 
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/*
	 * Check if it is LoadLibrary and GetProcAddress
	 */
	public boolean TwoAPIs()
	{
		if (curState == null || curState.getInstruction() == null) return false;
			
		Environment env = curState.getEnvironement();
		String insName = this.curState.getInstruction().getName();
		if (insName.contains("call"))
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
				String apiName = this.GetAPIName(aVal);
				if (apiName != null)
				{
					if (apiName.contains("LoadLibrary"))
					{
						this.useLoadLibrary = true;
					}
					else if (apiName.contains("GetProcAddress"))
					{
						this.useGetProcAddress = true;
					}
				}
			}	
		}
		if (this.useLoadLibrary && this.useGetProcAddress)
		{
			return true;
		}
		
		return false;
	}
	
	/*
	 * Check if it is IsDebuggerPresent 
	 */
	public boolean AntiDebugging()
	{
		if (curState == null || curState.getInstruction() == null) return false;
		
		Environment env = curState.getEnvironement();
		String insName = this.curState.getInstruction().getName();
		if (insName.contains("call"))
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
				String apiName = this.GetAPIName(aVal);
				if (apiName != null)
				{
					if (apiName.contains("IsDebuggerPresent"))
					{
						this.useLoadLibrary = true;
					}
				}
			}	
		}
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
	
	private String GetAPIName (Value apiAddr)
	{
		String api = APIHandle.checkAPI(((LongValue) apiAddr).getValue());
		if (api == null || api == "") {
			api = curState.getEnvironement().getSystem().getLibraryHandle().getAPIName(((LongValue) apiAddr).getValue());
		}
		
		if (api == null || api == "") {
			api = Program.getProgram().checkAPI(((LongValue) apiAddr).getValue(), curState.getEnvironement());
			if (api != null && api.equals(""))
				api = null;
		}	
		return api;
	}
	
}
