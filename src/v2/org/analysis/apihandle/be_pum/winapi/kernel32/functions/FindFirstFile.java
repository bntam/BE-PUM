/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: FindFirstFile.java
 * Created date: Feb 9, 2015
 * Description:
 */
package v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import v2.org.analysis.apihandle.be_pum.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.be_pum.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.apihandle.be_pum.winapi.structures.WinBase.WIN32_FIND_DATA;
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
 * Searches a directory for a file or subdirectory with a name that matches a
 * specific name (or partial name if wildcards are used).
 * 
 * @param lpFileName
 *            : The directory or path, and the file name, which can include
 *            wildcard characters, for example, an asterisk (*) or a question
 *            mark (?).
 * 
 * @param lpFindFileData
 *            : A pointer to the WIN32_FIND_DATA structure that receives
 *            information about a found file or directory.
 * 
 * @return If the function succeeds, the return value is a search handle used in
 *         a subsequent call to FindNextFile or FindClose, and the
 *         lpFindFileData parameter contains information about the first file or
 *         directory found.
 * 
 * @author Yen Nguyen
 *
 */
public class FindFirstFile extends Kernel32API {

	/**
	 * 
	 */
	public FindFirstFile() {
		
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
			HANDLE ret = Kernel32DLL.INSTANCE.FindFirstFile(new WString(searchName), lpFindFileData);

			System.out.println("Search Handle:" + Pointer.nativeValue(ret.getPointer()));
			register.mov("eax", new LongValue(Pointer.nativeValue(ret.getPointer())));

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
