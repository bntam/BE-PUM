/**
 * 
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Tlhelp32.PROCESSENTRY32;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Retrieves information about the next process recorded in a system snapshot.
 * 
 * @param hSnapshot
 *            A handle to the snapshot returned from a previous call to the
 *            CreateToolhelp32Snapshot function.
 * 
 * @param lppe
 *            A pointer to a PROCESSENTRY32 structure. It contains process
 *            information such as the name of the executable file, the process
 *            identifier, and the process identifier of the parent process.
 * 
 * @return Returns TRUE if the first entry of the process list has been copied
 *         to the buffer or FALSE otherwise. The ERROR_NO_MORE_FILES error value
 *         is returned by the GetLastError function if no processes exist or the
 *         snapshot does not contain process information.
 * 
 * @author Yen Nguyen
 *
 */
public class Process32Next extends Kernel32API {

	public Process32Next() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName,
			BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		Value x2 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2);

		if (x1 instanceof LongValue && x2 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();

			HANDLE hSnapshot = new HANDLE(new Pointer(t1));
			PROCESSENTRY32 lppe = new PROCESSENTRY32();

			BOOL ret = Kernel32DLL.INSTANCE.Process32First(hSnapshot, lppe);

			register.mov("eax", new LongValue(ret.longValue()));

			// DWORD dwSize;
			// DWORD cntUsage;
			// DWORD th32ProcessID;
			// ULONG_PTR th32DefaultHeapID;
			// DWORD th32ModuleID;
			// DWORD cntThreads;
			// DWORD th32ParentProcessID;
			// LONG pcPriClassBase;
			// DWORD dwFlags;
			// TCHAR szExeFile[MAX_PATH];
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(
					DataType.INT32, t2), new LongValue(lppe.dwSize.longValue()));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(
					DataType.INT32, t2 += 4),
					new LongValue(lppe.cntUsage.longValue()));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(
					DataType.INT32, t2 += 4),
					new LongValue(lppe.th32ProcessID.longValue()));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(
					DataType.INT32, t2 += 4), new LongValue(
					lppe.th32DefaultHeapID.longValue()));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(
					DataType.INT32, t2 += 4),
					new LongValue(lppe.th32ModuleID.longValue()));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(
					DataType.INT32, t2 += 4),
					new LongValue(lppe.cntThreads.longValue()));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(
					DataType.INT32, t2 += 4), new LongValue(
					lppe.th32ParentProcessID.longValue()));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(
					DataType.INT32, t2 += 4),
					new LongValue(lppe.pcPriClassBase.longValue()));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(
					DataType.INT32, t2 += 4),
					new LongValue(lppe.dwFlags.longValue()));
			memory.setText(new X86MemoryOperand(DataType.INT32, t2 += 4),
					new String(lppe.szExeFile));
		}
		return false;
	}
}
