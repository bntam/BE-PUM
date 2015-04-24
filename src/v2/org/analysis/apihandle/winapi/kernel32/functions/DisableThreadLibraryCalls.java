/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: CloseHandle.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.HMODULE;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Disables the DLL_THREAD_ATTACH and DLL_THREAD_DETACH notifications for the
 * specified dynamic-link library (DLL). This can reduce the size of the working
 * set for some applications.
 * 
 * @param hModule
 *            [in] A handle to the DLL module for which the DLL_THREAD_ATTACH
 *            and DLL_THREAD_DETACH notifications are to be disabled. The
 *            LoadLibrary, LoadLibraryEx, or GetModuleHandle function returns
 *            this handle. Note that you cannot call GetModuleHandle with NULL
 *            because this returns the base address of the executable image, not
 *            the DLL image.
 * 
 * @return If the function succeeds, the return value is nonzero.
 * 
 * @author Yen Nguyen
 *
 */
public class DisableThreadLibraryCalls extends Kernel32API {

	public DisableThreadLibraryCalls() {
		
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Register register = env.getRegister();

		Value x1 = stack.pop();

		System.out.println("Argument:" + x1);
		if (x1 instanceof LongValue) {
			long x = ((LongValue) x1).getValue();
			System.out.println("hModule:" + x);

			HMODULE hModule = new HMODULE();
			hModule.setPointer(new Pointer(x));
			BOOL ret = Kernel32DLL.INSTANCE.DisableThreadLibraryCalls(hModule);
			register.mov("eax", new LongValue(ret.longValue()));

			System.out.println("Return Value:" + ret);
		}
		return false;
	}

}
