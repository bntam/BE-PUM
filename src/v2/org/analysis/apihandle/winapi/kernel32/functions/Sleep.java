/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: Sleep.java
 * Created date: Mar 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Suspends the execution of the current thread until the time-out interval
 * elapses.
 * 
 * @param dwMilliseconds
 *            The time interval for which execution is to be suspended, in
 *            milliseconds. A value of zero causes the thread to relinquish the
 *            remainder of its time slice to any other thread that is ready to
 *            run. If there are no other threads ready to run, the function
 *            returns immediately, and the thread continues execution. Windows
 *            XP: A value of zero causes the thread to relinquish the remainder
 *            of its time slice to any other thread of equal priority that is
 *            ready to run. If there are no other threads of equal priority
 *            ready to run, the function returns immediately, and the thread
 *            continues execution. This behavior changed starting with Windows
 *            Server 2003. A value of INFINITE indicates that the suspension
 *            should not time out.
 * 
 * @author Yen Nguyen
 *
 */
public class Sleep extends Kernel32API {
	public static int offset = 0;

	public Sleep() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();

		Value x1 = stack.pop();

		System.out.println("Argument:" + x1);

		if (x1 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			
			if (t1 > 1000L) {
				offset += (int) (t1 - 1000L);
				t1 = 1000L;
			}

			DWORD dwMilliseconds = new DWORD(t1);
			Kernel32DLL.INSTANCE.Sleep(dwMilliseconds);
		}
		return false;
	}

}
