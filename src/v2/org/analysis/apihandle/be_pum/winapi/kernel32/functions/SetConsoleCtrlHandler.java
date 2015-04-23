/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: SetConsoleCtrlHandler.java
 * Created date: Mar 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions;

import v2.org.analysis.apihandle.be_pum.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.be_pum.winapi.kernel32.Kernel32DLL;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import com.sun.jna.Callback;
import com.sun.jna.platform.win32.WinDef.BOOL;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Adds or removes an application-defined HandlerRoutine function from the list
 * of handler functions for the calling process.
 * 
 * @param HandlerRoutine
 *            A pointer to the application-defined HandlerRoutine function to be
 *            added or removed. This parameter can be NULL.
 * 
 * @param Add
 *            If this parameter is TRUE, the handler is added; if it is FALSE,
 *            the handler is removed. If the HandlerRoutine parameter is NULL, a
 *            TRUE value causes the calling process to ignore CTRL+C input, and
 *            a FALSE value restores normal processing of CTRL+C input. This
 *            attribute of ignoring or processing CTRL+C is inherited by child
 *            processes.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class SetConsoleCtrlHandler extends Kernel32API {

	public SetConsoleCtrlHandler() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		Value x2 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2 + " ");

		if (x1 instanceof LongValue && x2 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			
			Callback HandlerRoutine = null;
			BOOL Add = new BOOL(t2);
			BOOL ret = Kernel32DLL.INSTANCE.SetConsoleCtrlHandler(HandlerRoutine, Add);
			
			register.mov("eax", new LongValue(ret.longValue()));
		}
		return false;
	}

}
