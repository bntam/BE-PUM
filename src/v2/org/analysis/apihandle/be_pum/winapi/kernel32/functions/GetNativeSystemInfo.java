/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetNativeSystemInfo.java
 * Created date: Mar 3, 2015
 * Description:
 */
package v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions;

import v2.org.analysis.apihandle.be_pum.winapi.kernel32.Kernel32API;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinBase.SYSTEM_INFO;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * The GetNativeSystemInfo function retrieves information about the current
 * system to an application running under WOW64. If the function is called from
 * a 64-bit application, it is equivalent to the GetSystemInfo function.
 * 
 * @param lpSystemInfo
 *            Pointer to a SYSTEM_INFO structure that receives the information.
 * 
 * @author Yen Nguyen
 *
 */
public class GetNativeSystemInfo extends Kernel32API {

	public GetNativeSystemInfo() {
		
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();

		Value x1 = stack.pop();

		System.out.println("Argument:" + x1);

		if (x1 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();

			SYSTEM_INFO lpSystemInfo = new SYSTEM_INFO();
			Kernel32.INSTANCE.GetNativeSystemInfo(lpSystemInfo);
		}
		return false;
	}

}
