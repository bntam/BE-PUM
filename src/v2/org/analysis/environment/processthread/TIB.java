package v2.org.analysis.environment.processthread;

import java.io.File;
import java.util.Map.Entry;

import org.jakstab.Program;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.Operand;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.apihandle.winapi.APIHandle;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.apihandle.winapi.kernel32.functions.LoadLibrary;
import v2.org.analysis.environment.ExternalMemory;
import v2.org.analysis.environment.ExternalMemory.ExternalMemoryReturnData;
import v2.org.analysis.path.BPState;
import v2.org.analysis.util.PairEntry;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HMODULE;

public class TIB {
	private static boolean beUpdated;
	private static final long TIB_Base_Address = 0x7EFDD000;
	private static long FS_0; // SEH frame *
	private static long FS_4; // EBP *
	private static long FS_8; // ESP *
	private static long FS_C; // SubSystemTib
	private static long FS_10; // FiberData
	private static long FS_14; // Arbitrary data slot
	private static final long FS_18 = TIB_Base_Address; // Address of TIB *
	private static long FS_1C; // Environment Pointer *
	private static long FS_20; // Process ID *
	private static long FS_24; // Current Thread ID *
	private static long FS_28; // Active RPC Handle
	private static long FS_2C; // Address of the TLS
	private static final long FS_30 = PEB.getPEB_Base_Address(); // Address of
																	// the PEB *
	private static long FS_34; // Last error number
	private static long FS_38; // Count of owned critical sections
	private static long FS_3C; // Address of CSR Client Thread
	private static long FS_40; // Win32 Thread Information
	private static long FS_44[]; // Win32 client information, user32 private
									// data
	private static long FS_CO; // Pointer to FastSysCall in Wow64
	private static long FS_C4; // Current locale
	private static long FS_C8; // FP Software Status Register
	private static long FS_CC[]; // Reserved for OS, kernel32 private data
	private static long FS_1A4; // Exception code
	private static long FS_1A8; // Activation context stack
	private static long FS_1BC[]; // Spare bytes, ntdll private data
	private static long FS_1D4[]; // Reserved for OS, ntdll private data
	private static long FS_1FC[]; // GDI TEB Batch, vm86 private data
	private static long FS_6DC; // GDI Region
	private static long FS_6E0; // GDI Pen
	private static long FS_6E4; // GDI Brush
	private static long FS_6E8; // Real Process ID
	private static long FS_6EC; // Real Thread ID
	private static long FS_6F4; // GDI client process ID (PID)
	private static long FS_6F8; // GDI client thread ID (TID)
	private static long FS_6FC; // GDI thread locale information
	private static long FS_700[]; // Reserved for user application
	private static long FS_714[]; // Reserved for GL
	private static long FS_BF4; // Last Status Value
	private static long FS_BF8[]; // Static UNICODE_STRING buffer
	private static long FS_E0C; // Pointer to de-allocation stack
	private static long FS_E10[]; // TLS slots, 4 byte per slot
	private static long FS_F10[]; // LIST_ENTRY structure
	private static long FS_F18; // VDM
	private static long FS_F1C; // Reserved for RPC
	private static long FS_F28; // RtlSetThreadErrorMode

	// Initialization
	static {
		beUpdated = false;
	}

	public static void setBeUpdated(boolean updated){
		beUpdated = updated;
	}

	public static void updateChecking(BPState curState) {
		Instruction ins = curState.getInstruction();
		if (ins == null) {
			return;
		}
		int i = 0;
		while (i < ins.getOperandCount()) {
			Operand op = ins.getOperand(i);
			if (op.getClass().getSimpleName().equals("X86MemoryOperand")) {
				X86MemoryOperand mop = (X86MemoryOperand) op;
				if (mop.getSegmentRegister() != null
						&& mop.getSegmentRegister().toString() == "%fs") {
					beUpdated = true;
					break;
				}
			}
			i++;
		}
	}

	public static void updateTIB(BPState curState) {
		if (!beUpdated){
			return;
		}
		// Update SEH frame
		FS_0 = curState.getEnvironement().getSystem().getSEHHandler().getStart().getAddrSEHRecord();
		curState.getEnvironement().getMemory().setDoubleWordMemoryValue(TIB_Base_Address, new LongValue(FS_0));
		// Update EBP
		Value ebp = curState.getEnvironement().getRegister().getRegisterValue("ebp");
		if (ebp != null && ebp instanceof LongValue) {
			FS_4 = ((LongValue) ebp).getValue();
		}
		curState.getEnvironement().getMemory().setDoubleWordMemoryValue(TIB_Base_Address + 0x4, new LongValue(FS_4));
		// Update ESP
		Value esp = curState.getEnvironement().getRegister().getRegisterValue("esp");
		if (esp != null && esp instanceof LongValue) {
			FS_8 = ((LongValue) esp).getValue();
		}
		//FS_8 = ((LongValue) curState.getEnvironement().getRegister().getRegisterValue("esp")).getValue();
		curState.getEnvironement().getMemory().setDoubleWordMemoryValue(TIB_Base_Address + 0x8, new LongValue(FS_8));
		// More: Update Environment pointer FS_1C
		FS_1C = 0;
		curState.getEnvironement().getMemory().setDoubleWordMemoryValue(TIB_Base_Address + 0x1C, new LongValue(FS_1C));
		// Update Address of TIB
		curState.getEnvironement().getMemory().setDoubleWordMemoryValue(TIB_Base_Address + 0x18, new LongValue(FS_18));
		// Update Process ID
		FS_20 = Kernel32.INSTANCE.GetCurrentProcessId();
		curState.getEnvironement().getMemory().setDoubleWordMemoryValue(TIB_Base_Address + 0x20, new LongValue(FS_20));
		// Update Thread ID
		FS_24 = Kernel32.INSTANCE.GetCurrentThreadId();
		curState.getEnvironement().getMemory().setDoubleWordMemoryValue(TIB_Base_Address + 0x24, new LongValue(FS_24));
		// Update Address of PEB
		curState.getEnvironement().getMemory().setDoubleWordMemoryValue(TIB_Base_Address + 0x30, new LongValue(FS_30));
		// //////////////////////////////////////////////////////////
		// Update PEB
		// /////////////////////////////////////////////////////////
		PEB.updatePEB(curState);
		beUpdated = false;
	}

	public static long getTIB_Base_Address() {
		return TIB_Base_Address;
	}
	
	public void getLoaderData(String libraryName) {
		// Get DLL's Base Address /////////////////////
		long libHandle = 0;
		// Try to find the handle of current library
		if (APIHandle.libraryHandleMap.containsValue(libraryName)) {
			for (Entry<Long, PairEntry<String, Integer>> handle : APIHandle.libraryHandleMap.entrySet()) {
				if (handle.getValue().getKey().equals(libraryName)) {
					libHandle = handle.getKey(); // DLL's Base Address
					break;
				}
			}
		} else {
			// If can not find, execute LoadLibrary API
			libHandle = (new LoadLibrary()).execute(libraryName); // DLL's Base
																	// Address
		}
		// ///////////////////////////////////////////////

		// Get DLL's Entry Point/////////////////////////
		long x = getWordExternalMemory(libHandle);
		if (x != 0x5A4D) {
			return; // fail to reach the MZ in PE File
		}
		
		x = getDoubleWordExternalMemory(libHandle + 0x3c);
		if (x == Long.MIN_VALUE) {
			return; // fail to reach the MZ in PE File
		}
		
		long entryPoint = getDoubleWordExternalMemory(libHandle + x + 0x28) + libHandle;
		/////////////////////////////////////////////////
		
		// Get Full DLL's name/////////////////////////
		long t3 = 100;
		
		char Filename[] = new char[(int) t3];
		HMODULE module = new HMODULE();
		module.setPointer(new Pointer(libHandle));

		String output = null;
		DWORD ret = null;

		if (libHandle == 0L) {
			output = Program.getProgram().getAbsolutePathFile();
			ret = new DWORD(output.length());
			Kernel32.INSTANCE.SetLastError(0);
		} else {
			ret = Kernel32DLL.INSTANCE.GetModuleFileName(module, Filename, new DWORD(t3));
			output = new String(Filename, 0, ret.intValue());
		}
		
		String jre_location = System.getProperties().getProperty("java.home") + File.separator + "bin" + File.separator
				+ "java";
		String jdk_location = System.getProperties().getProperty("java.home") + File.separator + "java";
		if (jdk_location.contains("jre")) {
			jdk_location = jdk_location.replace("jre", "bin");
		}
		
		if (output.startsWith(jre_location) || output.startsWith(jdk_location)) {
			output = Program.getProgram().getAbsolutePathFile();
			ret = new DWORD(output.length());
			Kernel32.INSTANCE.SetLastError(0);
		}
		
		System.out.println("DLL's Base Address=0x" +Long.toHexString(libHandle) + ", Module's Entry Point=0x" + Long.toHexString(entryPoint) + 
				", Full DLL's name= " + output+ ", Base DLL's name=" + libraryName);
		System.out.println();
		/////////////////////////////////////////////////
	}

	private long getWordExternalMemory(long address) {
		// TODO Auto-generated method stub
		if (address != 0) {
			ExternalMemoryReturnData ret = ExternalMemory.getWord(address);
			if (ret != null && ret.isValidAddress) {
				return ret.value.getValue();
			}
		}
		return Long.MIN_VALUE;
	}

	private long getDoubleWordExternalMemory(long address) {
		if (address != 0) {
			ExternalMemoryReturnData ret = ExternalMemory.getDoubleWord(address);
			if (ret != null && ret.isValidAddress) {
				return ret.value.getValue();
			}
		}
		return Long.MIN_VALUE;
	}
}
