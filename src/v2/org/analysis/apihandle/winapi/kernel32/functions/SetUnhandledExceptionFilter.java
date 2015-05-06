/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: SetUnhandledExceptionFilter.java
 * Created date: Mar 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

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
 * Enables an application to supersede the top-level exception handler of each
 * thread of a process. After calling this function, if an exception occurs in a
 * process that is not being debugged, and the exception makes it to the
 * unhandled exception filter, that filter will call the exception filter
 * function specified by the lpTopLevelExceptionFilter parameter.
 * 
 * @param lpTopLevelExceptionFilter
 *            A pointer to a top-level exception filter function that will be
 *            called whenever the UnhandledExceptionFilter function gets
 *            control, and the process is not being debugged. A value of NULL
 *            for this parameter specifies default handling within
 *            UnhandledExceptionFilter.
 * 
 * @return The SetUnhandledExceptionFilter function returns the address of the
 *         previous exception filter established with the function. A NULL
 *         return value means that there is no current top-level exception
 *         handler.
 * 
 * @author Yen Nguyen
 *
 */
public class SetUnhandledExceptionFilter extends Kernel32API {

	public SetUnhandledExceptionFilter() {
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

			register.mov("eax", new LongValue(0));
		}
		return false;
	}

}
