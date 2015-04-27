/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: SetFileTime.java
 * Created date: Mar 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

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
 * Sets the date and time that the specified file or directory was created, last
 * accessed, or last modified.
 * 
 * @param hFile
 *            A handle to the file or directory. The handle must have been
 *            created using the CreateFile function with the
 *            FILE_WRITE_ATTRIBUTES access right. For more information, see File
 *            Security and Access Rights.
 * 
 * @param lpCreationTime
 *            A pointer to a FILETIME structure that contains the new creation
 *            date and time for the file or directory. This parameter can be
 *            NULL if the application does not need to change this information.
 * 
 * @param lpLastAccessTime
 *            A pointer to a FILETIME structure that contains the new last
 *            access date and time for the file or directory. The last access
 *            time includes the last time the file or directory was written to,
 *            read from, or (in the case of executable files) run. This
 *            parameter can be NULL if the application does not need to change
 *            this information.
 * 
 * @param lpLastWriteTime
 *            A pointer to a FILETIME structure that contains the new last
 *            modified date and time for the file or directory. This parameter
 *            can be NULL if the application does not need to change this
 *            information.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class SetFileTime extends Kernel32API {

	public SetFileTime() {
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
			FILETIME lpCreationTime = (t2 != 0) ? new FILETIME() : null;
			if (lpCreationTime != null) {
				lpCreationTime.dwLowDateTime = (int) ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(
						DataType.INT32, t2))).getValue();
				lpCreationTime.dwHighDateTime = (int) ((LongValue) memory
						.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 + 4))).getValue();
			}

			FILETIME lpLastAccessTime = (t3 != 0) ? new FILETIME() : null;
			if (lpLastAccessTime != null) {
				lpLastAccessTime.dwLowDateTime = (int) ((LongValue) memory
						.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2))).getValue();
				lpLastAccessTime.dwHighDateTime = (int) ((LongValue) memory
						.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 + 4))).getValue();
			}

			FILETIME lpLastWriteTime = (t4 != 0) ? new FILETIME() : null;
			if (lpLastWriteTime != null) {
				lpLastWriteTime.dwLowDateTime = (int) ((LongValue) memory
						.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2))).getValue();
				lpLastWriteTime.dwHighDateTime = (int) ((LongValue) memory
						.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 + 4))).getValue();
			}

			BOOL ret = Kernel32DLL.INSTANCE.SetFileTime(hFile, lpCreationTime, lpLastAccessTime, lpLastWriteTime);

			register.mov("eax", new LongValue(ret.longValue()));
		}
		return false;
	}

}
