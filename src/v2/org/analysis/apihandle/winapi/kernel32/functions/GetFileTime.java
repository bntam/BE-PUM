/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetFileTime.java
 * Created date: Mar 3, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Retrieves the date and time that a file or directory was created, last
 * accessed, and last modified.
 * 
 * @param hFile
 *            A handle to the file or directory for which dates and times are to
 *            be retrieved. The handle must have been created using the
 *            CreateFile function with the GENERIC_READ access right.
 * 
 * @param lpCreationTime
 *            A pointer to a FILETIME structure to receive the date and time the
 *            file or directory was created. This parameter can be NULL if the
 *            application does not require this information.
 * 
 * @param lpLastAccessTime
 *            A pointer to a FILETIME structure to receive the date and time the
 *            file or directory was last accessed. The last access time includes
 *            the last time the file or directory was written to, read from, or,
 *            in the case of executable files, run. This parameter can be NULL
 *            if the application does not require this information.
 * 
 * @param lpLastWriteTime
 *            A pointer to a FILETIME structure to receive the date and time the
 *            file or directory was last written to, truncated, or overwritten
 *            (for example, with WriteFile or SetEndOfFile). This date and time
 *            is not updated when file attributes or security descriptors are
 *            changed. This parameter can be NULL if the application does not
 *            require this information.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetFileTime extends Kernel32API {

	public GetFileTime() {
		
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		Value x2 = stack.pop();
		Value x3 = stack.pop();
		Value x4 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4);

		if (x1 instanceof LongValue && x2 instanceof LongValue && x3 instanceof LongValue && x4 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			long t3 = ((LongValue) x3).getValue();
			long t4 = ((LongValue) x4).getValue();
			
			HANDLE hFile = new HANDLE(new Pointer(t1));
			FILETIME lpCreationTime = new FILETIME();
			FILETIME lpLastAccessTime = new FILETIME();
			FILETIME lpLastWriteTime = new FILETIME();
			boolean ret = Kernel32.INSTANCE.GetFileTime(hFile, lpCreationTime, lpLastAccessTime, lpLastWriteTime);

			register.mov("eax", new LongValue(ret ? 1 : 0));
			
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2), new LongValue(lpCreationTime.dwLowDateTime));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 + 4), new LongValue(lpCreationTime.dwHighDateTime));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t3), new LongValue(lpLastAccessTime.dwLowDateTime));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t3 + 4), new LongValue(lpLastAccessTime.dwHighDateTime));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t4), new LongValue(lpLastWriteTime.dwLowDateTime));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t4 + 4), new LongValue(lpLastWriteTime.dwHighDateTime));
		}
		return false;
	}

}
