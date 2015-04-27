/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: ExitProcess.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

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
 * Ends the calling process and all its threads.
 * 
 * @param uExitCode
 *            : The exit code for the process and all threads.
 *            
 * @author Yen Nguyen
 *
 */
public class ExitProcess extends Kernel32API {

	/**
	 * 
	 */
	public ExitProcess() {
		
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		
		// UINT uExitCode exit code for all threads
		Value x1 = stack.pop();

		System.out.println("Argument:" + x1);
		
		if (x1 instanceof LongValue) {
			LongValue x = (LongValue) x1;
			
			Kernel32DLL.INSTANCE.ExitProcess((int)x.getValue());
		}
		return false;
	}

}
