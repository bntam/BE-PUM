/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetStdHandle.java
 * Created date: Feb 7, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Retrieves a handle to the specified standard device (standard input, standard
 * output, or standard error).
 * 
 * @param nStdHandle
 *            : The standard device. This parameter can be one of the following
 *            values.
 * @return <p>
 *         If the function succeeds, the return value is a handle to the
 *         specified device, or a redirected handle set by a previous call to
 *         SetStdHandle. The handle has GENERIC_READ and GENERIC_WRITE access
 *         rights, unless the application has used SetStdHandle to set a
 *         standard handle with lesser access.
 *         </p>
 *         <p>
 *         If the function fails, the return value is INVALID_HANDLE_VALUE. To
 *         get extended error information, call GetLastError.
 *         </p>
 *         <p>
 *         If an application does not have associated standard handles, such as
 *         a service running on an interactive desktop, and has not redirected
 *         them, the return value is NULL.
 *         </p>
 * 
 * @author Yen Nguyen
 *
 */
public class GetStdHandle extends Kernel32API {

	/**
	 * 
	 */
	public GetStdHandle() {
		
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Register register = env.getRegister();

		// DWORD nStdHandle input, output, or error device
		Value x1 = stack.pop();

		System.out.println("Argument:" + x1 + " ");

		if (x1 instanceof LongValue) {
			long t = ((LongValue) x1).getValue();
			HANDLE ret = Kernel32DLL.INSTANCE.GetStdHandle(new DWORD(t));

			long value = (ret == null) ? 0 : Pointer.nativeValue(ret.getPointer());
			register.mov("eax", new LongValue(value));
			System.out.println("Return Value:" + value);
		}
		return false;
	}

}
