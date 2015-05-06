/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: TerminateThread.java
 * Created date: Mar 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Terminates a thread.
 * 
 * @param hThread
 *            A handle to the thread to be terminated. The handle must have the
 *            THREAD_TERMINATE access right. For more information, see Thread
 *            Security and Access Rights.
 * 
 * @param dwExitCode
 *            The exit code for the thread. Use the GetExitCodeThread function
 *            to retrieve a thread's exit value.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class TerminateThread extends Kernel32API {

	public TerminateThread() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		// Memory memory = env.getMemory();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		Value x2 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2);

		if (x1 instanceof LongValue && x2 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();

			HANDLE hThread = new HANDLE(new Pointer(t1));
			DWORD dwExitCode = new DWORD(t2);

			BOOL ret = Kernel32DLL.INSTANCE.TerminateThread(hThread, dwExitCode);

			register.mov("eax", new LongValue(ret.longValue()));
		}
		return false;
	}

}
