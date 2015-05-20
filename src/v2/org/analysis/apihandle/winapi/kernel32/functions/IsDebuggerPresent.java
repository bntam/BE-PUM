/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: IsDebuggerPresent.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;

/**
 * @author Yen Nguyen
 *
 */
public class IsDebuggerPresent extends Kernel32API {

	/**
	 * 
	 */
	public IsDebuggerPresent() {

	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Register register = env.getRegister();

		// This function has no parameters.
		boolean ret = Kernel32DLL.INSTANCE.IsDebuggerPresent();
		System.out.println("Return Value:" + ret);
		register.mov("eax", new LongValue(ret ? 1 : 0));
		return false;
	}

}
