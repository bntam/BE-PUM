/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.advapi32.functions
 * File name: RegCloseKey.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.platform.win32.Advapi32;
import com.sun.jna.platform.win32.WinReg.HKEY;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * The RegCloseKey function releases a handle to the specified registry key.
 *
 * @param hKey
 *            Handle to the open key to be closed. The handle must have been
 *            opened by the RegCreateKeyEx, RegOpenKeyEx, or RegConnectRegistry
 *            function.
 * 
 * @return If the function succeeds, the return value is ERROR_SUCCESS. If the
 *         function fails, the return value is a nonzero error code defined in
 *         Winerror.h.
 * 
 * @author Yen Nguyen
 *
 */
public class RegCloseKey extends Advapi32API {

	public RegCloseKey() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Register register = env.getRegister();

		// HKEY hKey // handle of key to close
		Value x1 = stack.pop();
		System.out.println("Argument:" + x1 + " ");

		if (x1 instanceof LongValue) {
			int ret = Advapi32.INSTANCE.RegCloseKey(new HKEY((int) ((LongValue) x1).getValue()));
			register.mov("eax", new LongValue(ret));
		}
		return false;
	}

}
