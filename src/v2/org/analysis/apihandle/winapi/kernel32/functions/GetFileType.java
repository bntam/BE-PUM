/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetFileType.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Retrieves the file type of the specified file.
 * 
 * @param hFile
 *            A handle to the file.
 * 
 * @return FILE_TYPE_UNKNOWN if the function fails, or if the type is unknown.
 *         You can distinguish between a "valid" return of FILE_TYPE_UNKNOWN and
 *         its return due to a calling error (for example, passing an invalid
 *         handle to GetFileType) by calling GetLastError. If the function
 *         worked properly and FILE_TYPE_UNKNOWN was returned, a call to
 *         GetLastError will return NO_ERROR.
 * 
 * @author Yen Nguyen
 *
 */
public class GetFileType extends Kernel32API {

	/**
	 * 
	 */
	public GetFileType() {

	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Register register = env.getRegister();

		// HANDLE hFile file handle
		Value x1 = stack.pop();
		System.out.println("Argument:" + x1);

		if (x1 instanceof LongValue) {
			long t = ((LongValue) x1).getValue();
			int ret = Kernel32.INSTANCE.GetFileType(new HANDLE(new Pointer(t)));
			register.mov("eax", new LongValue(ret));
		}
		return false;
	}

}
