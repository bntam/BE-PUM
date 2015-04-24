/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: GetAsyncKeyState.java
 * Created date: Mar 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.platform.win32.User32;

import v2.org.analysis.apihandle.winapi.user32.User32API;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * This function determines whether a key is up or down at the time the function
 * is called, and whether the key was pressed after a previous call to
 * GetAsyncKeyState.
 * 
 * @param vKey
 *            Specifies one of 256 possible virtual-key codes.
 * 
 * @return If the function succeeds, the return value specifies whether the key
 *         was pressed since the last call to GetAsyncKeyState, and whether the
 *         key is currently up or down. If the most significant bit is set, the
 *         key is down.
 * 
 * @author Yen Nguyen
 *
 */
public class GetAsyncKeyState extends User32API {

	public GetAsyncKeyState() {
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

			short ret = User32.INSTANCE.GetAsyncKeyState((int) t1);

			register.mov("eax", new LongValue(ret));
		}
		return false;
	}

}
