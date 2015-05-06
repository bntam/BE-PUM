/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: SuspendThread.java
 * Created date: Apr 25, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Suspends the specified thread.
 * 
 * @param hThread
 *            A handle to the thread that is to be suspended.
 * 
 * @return If the function succeeds, the return value is the thread's previous
 *         suspend count; otherwise, it is (DWORD) -1. To get extended error
 *         information, use the GetLastError function.
 * 
 * @author Yen Nguyen
 *
 */
public class SuspendThread extends Kernel32API {

	public SuspendThread() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		System.out.println("Argument:" + x1);

		if (x1 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();

			HANDLE hThread = new HANDLE(new Pointer(t1));
			DWORD ret = Kernel32DLL.INSTANCE.SuspendThread(hThread);

			long value = ret.longValue();
			register.mov("eax", new LongValue(value));
			System.out.println("Return Value: " + value);
		}
		return false;
	}

}
