/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: CryptReleaseContext.java
 * Created date: May 11, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.advapi32.functions;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * The CryptReleaseContext function releases the handle of a cryptographic
 * service provider (CSP) and a key container. At each call to this function,
 * the reference count on the CSP is reduced by one. When the reference count
 * reaches zero, the context is fully released and it can no longer be used by
 * any function in the application.
 * 
 * @param hProv
 *            Handle of a cryptographic service provider (CSP) created by a call
 *            to CryptAcquireContext.
 * 
 * @param dwFlags
 *            Reserved for future use and must be zero. If dwFlags is not set to
 *            zero, this function returns FALSE but the CSP is released.
 * 
 * @return If the function succeeds, the return value is nonzero (TRUE). If the
 *         function fails, the return value is zero (FALSE). For extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class CryptReleaseContext extends Advapi32API {

	public CryptReleaseContext() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
//		Memory memory = env.getMemory();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		Value x2 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2);
		if (x1 instanceof LongValue && x2 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();

			ULONG_PTR hProv = new ULONG_PTR(t1);
			DWORD dwFlags = new DWORD(t2);
			BOOL ret = Advapi32DLL.INSTANCE.CryptReleaseContext(hProv, dwFlags);

			long value = ret.longValue();
			register.mov("eax", new LongValue(value));
			System.out.println("Return Value: " + value);
		}
		return false;
	}

}
