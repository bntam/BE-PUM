/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: SearchPath.java
 * Created date: Mar 27, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;

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
import v2.org.analysis.system.Storage;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Searches for a specified file in a specified path.
 * 
 * @param lpPath
 *            The path to be searched for the file. If this parameter is NULL,
 *            the function searches for a matching file using a
 *            registry-dependent system search path. For more information, see
 *            the Remarks section.
 * 
 * @param lpFileName
 *            The name of the file for which to search.
 * 
 * @param lpExtension
 *            The extension to be added to the file name when searching for the
 *            file. The first character of the file name extension must be a
 *            period (.). The extension is added only if the specified file name
 *            does not end with an extension. If a file name extension is not
 *            required or if the file name contains an extension, this parameter
 *            can be NULL.
 * 
 * @param nBufferLength
 *            The size of the buffer that receives the valid path and file name
 *            (including the terminating null character), in TCHARs.
 * 
 * @param lpBuffer
 *            A pointer to the buffer to receive the path and file name of the
 *            file found. The string is a null-terminated string.
 * 
 * @param lpFilePart
 *            A pointer to the variable to receive the address (within lpBuffer)
 *            of the last component of the valid path and file name, which is
 *            the address of the character immediately following the final
 *            backslash (\) in the path.
 * 
 * @return If the function succeeds, the value returned is the length, in
 *         TCHARs, of the string that is copied to the buffer, not including the
 *         terminating null character. If the return value is greater than
 *         nBufferLength, the value returned is the size of the buffer that is
 *         required to hold the path, including the terminating null character.
 *         If the function fails, the return value is zero. To get extended
 *         error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class SearchPath extends Kernel32API {

	public SearchPath() {
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
		Value x5 = stack.pop();
		Value x6 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5 + " " + x6);

		if (x1 instanceof LongValue && x2 instanceof LongValue && x3 instanceof LongValue && x4 instanceof LongValue
				&& x5 instanceof LongValue && x6 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			long t3 = ((LongValue) x3).getValue();
			long t4 = ((LongValue) x4).getValue();
			long t5 = ((LongValue) x5).getValue();
			long t6 = ((LongValue) x6).getValue();

			String lpPath = (t1 != 0) ? memory.getText(new X86MemoryOperand(DataType.INT32, t1)) : null;
			String lpFileName = (t2 != 0) ? memory.getText(new X86MemoryOperand(DataType.INT32, t2)) : null;
			String lpExtension = (t3 != 0) ? memory.getText(new X86MemoryOperand(DataType.INT32, t3)) : null;
			DWORD nBufferLength = new DWORD(t4);
			char[] lpBuffer = new char[(int) t4];
			Pointer lpFilePart = (t6 != 0) ? new Pointer(0) : null;

			DWORD ret = null;
			if (t1 != 0 && lpPath.length() > 0) {
				String lpMPath = Storage.getMappingPath(lpPath);
				ret = Kernel32DLL.INSTANCE.SearchPath(lpMPath, lpFileName, lpExtension, nBufferLength, lpBuffer,
						lpFilePart);
			}
			if (ret == null || (ret != null && ret.longValue() == 0)) {
				ret = Kernel32DLL.INSTANCE.SearchPath(lpPath, lpFileName, lpExtension, nBufferLength, lpBuffer,
						lpFilePart);
			}
			register.mov("eax", new LongValue(ret.longValue()));

			if (lpBuffer != null)
				memory.setText(new X86MemoryOperand(DataType.INT32, t5), new String(lpBuffer));
			if (lpFilePart != null)
				memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t6),
						new LongValue(Pointer.nativeValue(lpFilePart)));
		}
		return false;
	}

}
