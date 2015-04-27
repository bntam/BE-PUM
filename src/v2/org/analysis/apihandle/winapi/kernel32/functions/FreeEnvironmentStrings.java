/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: FreeEnvironmentStrings.java
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

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Frees a block of environment strings.
 * 
 * @param lpszEnvironmentBlock
 *            : A pointer to a block of environment strings. The pointer to
 *            the block must be obtained by calling the
 *            GetEnvironmentStrings function.
 * 
 * @return If the function succeeds, the return value is nonzero.
 * 
 * @author Yen Nguyen
 *
 */
public class FreeEnvironmentStrings extends Kernel32API {

	/**
	 * 
	 */
	public FreeEnvironmentStrings() {
		
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Register register = env.getRegister();
		
		// LPTSTR lpszEnvironmentBlock pointer to a block of environment strings
		Value x1 = stack.pop();
		System.out.println("Argument:" + x1);
		
		if (x1 instanceof LongValue) {
			long t = ((LongValue) x1).getValue();
			BOOL ret = Kernel32DLL.INSTANCE.FreeEnvironmentStrings(new Pointer(t));
			
			register.mov("eax", new LongValue(ret.longValue()));
		}
		return false;
	}

}
