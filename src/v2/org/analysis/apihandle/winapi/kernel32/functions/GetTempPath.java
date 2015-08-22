/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetTempPath.java
 * Created date: Mar 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinDef.DWORD;

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
 * The GetTempPath function retrieves the path of the directory designated for
 * temporary files.
 * 
 * @param nBufferLength
 *            Size of the string buffer identified by lpBuffer, in TCHARs.
 * 
 * @param buffer
 *            Pointer to a string buffer that receives the null-terminated
 *            string specifying the temporary file path. The returned string
 *            ends with a backslash, for example, C:\TEMP\.
 * 
 * @return If the function succeeds, the return value is the length, in TCHARs,
 *         of the string copied to lpBuffer, not including the terminating null
 *         character. If the return value is greater than nBufferLength, the
 *         return value is the length, in TCHARs, of the buffer required to hold
 *         the path.
 * 
 *         If the function fails, the return value is zero. To get extended
 *         error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetTempPath extends Kernel32API {

	public GetTempPath() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		char[] lpBuffer = new char[(int) t1];
		DWORD ret = Kernel32.INSTANCE.GetTempPath(new DWORD(t1), lpBuffer);

		String tmpPath = new String(lpBuffer);
		memory.setText(new X86MemoryOperand(DataType.INT32, this.params.get(1)), tmpPath, tmpPath.length());
		System.out.println("Temp Path: " + tmpPath);
		register.mov("eax", new LongValue(ret.longValue()));
	}

}
