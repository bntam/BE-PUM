/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: WaitForSingleObject.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions;

import v2.org.analysis.apihandle.be_pum.winapi.kernel32.Kernel32API;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Waits until the specified object is in the signaled state or the time-out
 * interval elapses. To enter an alertable wait state, use the
 * WaitForSingleObjectEx function. To wait for multiple objects, use the
 * WaitForMultipleObjects.
 * 
 * @param hHandle
 *            A handle to the object. For a list of the object types whose
 *            handles can be specified, see the following Remarks section. If
 *            this handle is closed while the wait is still pending, the
 *            function's behavior is undefined. The handle must have the
 *            SYNCHRONIZE access right. For more information, see Standard
 *            Access Rights.
 * 
 * @param dwMilliseconds
 *            The time-out interval, in milliseconds. If a nonzero value is
 *            specified, the function waits until the object is signaled or the
 *            interval elapses. If dwMilliseconds is zero, the function does not
 *            enter a wait state if the object is not signaled; it always
 *            returns immediately. If dwMilliseconds is INFINITE, the function
 *            will return only when the object is signaled.
 * 
 * @return If the function succeeds, the return value indicates the event that
 *         caused the function to return.
 * 
 * @author Yen Nguyen
 *
 */
public class WaitForSingleObject extends Kernel32API {

	/**
	 * 
	 */
	public WaitForSingleObject() {
		
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Register register = env.getRegister();

		/*
		 * HANDLE hHandle, // handle of object to wait for DWORD dwMilliseconds
		 * // time-out interval in milliseconds
		 */
		Value x1 = stack.pop();
		Value x2 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2 + " ");

		if (x1 instanceof LongValue && x2 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			int ret = Kernel32.INSTANCE.WaitForSingleObject(new HANDLE(new Pointer(t1)), (int) t2);
			register.mov("eax", new LongValue(ret));
		}
		return false;
	}
}
