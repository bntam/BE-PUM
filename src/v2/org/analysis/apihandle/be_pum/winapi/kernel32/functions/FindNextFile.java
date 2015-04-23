/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: FindNextFile.java
 * Created date: Feb 10, 2015
 * Description:
 */
package v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions;

import v2.org.analysis.apihandle.be_pum.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.be_pum.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.apihandle.be_pum.winapi.structures.WinBase.WIN32_FIND_DATA;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
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
 * Continues a file search from a previous call to the FindFirstFile,
 * FindFirstFileEx, or FindFirstFileTransacted functions.
 * 
 * @param hFindFile
 *            : The search handle returned by a previous call to the
 *            FindFirstFile or FindFirstFileEx function.
 * 
 * @param lpFindFileData
 *            : A pointer to the WIN32_FIND_DATA structure that receives
 *            information about the found file or subdirectory.
 * 
 * @return If the function succeeds, the return value is nonzero and the
 *         lpFindFileData parameter contains information about the next file or
 *         directory found.
 * 
 * @author Yen Nguyen
 *
 */
public class FindNextFile extends Kernel32API {

	/**
	 * 
	 */
	public FindNextFile() {
		
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		Value x2 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2 + " ");

		if (x1 instanceof LongValue && x2 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long pFind = ((LongValue) x2).getValue();

			String searchName = memory.getText(new X86MemoryOperand(DataType.INT32, t1));
			System.out.println("Search File:" + searchName + ", Memory Operand:" + pFind);

			WIN32_FIND_DATA lpFindFileData = new WIN32_FIND_DATA();
			HANDLE hFindFile = new HANDLE(new Pointer(t1));
			BOOL ret = Kernel32DLL.INSTANCE.FindNextFile(hFindFile, lpFindFileData);

			System.out.println("Return value:" + ret.longValue());
			register.mov("eax", new LongValue(ret.longValue()));

			memory.setDoubleWordMemoryValue(pFind, new LongValue(lpFindFileData.dwFileAttributes.longValue()));
			memory.setDoubleWordMemoryValue(pFind += 4, new LongValue(lpFindFileData.ftCreationTime.dwLowDateTime));
			memory.setDoubleWordMemoryValue(pFind += 4, new LongValue(lpFindFileData.ftCreationTime.dwHighDateTime));

			memory.setDoubleWordMemoryValue(pFind += 4, new LongValue(lpFindFileData.ftLastAccessTime.dwLowDateTime));
			memory.setDoubleWordMemoryValue(pFind += 4, new LongValue(lpFindFileData.ftLastAccessTime.dwHighDateTime));

			memory.setDoubleWordMemoryValue(pFind += 4, new LongValue(lpFindFileData.ftLastWriteTime.dwLowDateTime));
			memory.setDoubleWordMemoryValue(pFind += 4, new LongValue(lpFindFileData.ftLastWriteTime.dwHighDateTime));

			memory.setDoubleWordMemoryValue(pFind += 4, new LongValue(lpFindFileData.nFileSizeHigh.longValue()));
			memory.setDoubleWordMemoryValue(pFind += 4, new LongValue(lpFindFileData.nFileSizeLow.longValue()));
			memory.setDoubleWordMemoryValue(pFind += 4, new LongValue(lpFindFileData.dwReserved0.longValue()));
			memory.setDoubleWordMemoryValue(pFind += 4, new LongValue(lpFindFileData.dwReserved1.longValue()));

			memory.setText(new X86MemoryOperand(DataType.INT32, pFind += 4), new String(lpFindFileData.cFileName));
			memory.setText(new X86MemoryOperand(DataType.INT32, pFind += (2 * lpFindFileData.cFileName.length)),
					new String(lpFindFileData.cAlternateFileName));
		}
		return false;
	}

}
