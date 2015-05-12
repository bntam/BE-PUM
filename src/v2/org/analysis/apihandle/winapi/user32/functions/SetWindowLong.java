/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: SetWindowLong.java
 * Created date: Apr 25, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LONG;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Changes an attribute of the specified window. The function also sets the
 * 32-bit (long) value at the specified offset into the extra window memory.
 * 
 * @param hWnd
 *            A handle to the window and, indirectly, the class to which the
 *            window belongs.
 * 
 * @param nIndex
 *            The zero-based offset to the value to be set. Valid values are in
 *            the range zero through the number of bytes of extra window memory,
 *            minus the size of an integer. To set any other value, specify one
 *            of the following values.
 * 
 * @param dwNewLong
 *            The replacement value.
 * 
 * @return If the function succeeds, the return value is the previous value of
 *         the specified 32-bit integer. If the function fails, the return value
 *         is zero. To get extended error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class SetWindowLong extends User32API {

	public SetWindowLong() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		Value x2 = stack.pop();
		Value x3 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2 + " " + x3);

		if (x1 instanceof LongValue && x2 instanceof LongValue && x3 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			long t3 = ((LongValue) x3).getValue();

			HWND hWnd = (t1 == 0L) ? null : new HWND(new Pointer(t1));
			int nIndex = (int) t2;
			LONG dwNewLong = new LONG(t3);
			LONG ret = User32DLL.INSTANCE.SetWindowLong(hWnd, nIndex, dwNewLong);

			long value = ret.longValue();
			register.mov("eax", new LongValue(value));
			System.out.println("Return Value: " + value);
		}
		return false;
	}

}
