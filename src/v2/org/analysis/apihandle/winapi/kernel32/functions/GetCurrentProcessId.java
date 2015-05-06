/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetCurrentProcessId.java
 * Created date: Mar 1, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import com.sun.jna.platform.win32.Kernel32;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;

/**
 * This function returns the process identifier of the calling process.
 * 
 * @return The return value is the process identifier of the calling process.
 * 
 * @author Yen Nguyen
 *
 */
public class GetCurrentProcessId extends Kernel32API {

	public GetCurrentProcessId() {
		
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Register register = env.getRegister();
		int ret = Kernel32.INSTANCE.GetCurrentProcessId();
		register.mov("eax", new LongValue(ret));
		return false;
	}

}
