/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: GetWindowLong.java
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
 * Retrieves information about the specified window. The function also retrieves
 * the 32-bit (DWORD) value at the specified offset into the extra window
 * memory.
 * 
 * @param hWnd
 *            A handle to the window and, indirectly, the class to which the
 *            window belongs.
 * 
 * @param nIndex
 *            The zero-based offset to the value to be retrieved. Valid values
 *            are in the range zero through the number of bytes of extra window
 *            memory, minus four; for example, if you specified 12 or more bytes
 *            of extra memory, a value of 8 would be an index to the third
 *            32-bit integer. To retrieve any other value, specify one of the
 *            following values.
 * 
 * @return If the function succeeds, the return value is the requested value. If
 *         the function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetWindowLong extends User32API {

	public GetWindowLong() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
//		Memory memory = env.getMemory();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		Value x2 = stack.pop();
		System.out.print("Argument:" + x1 + " " + x2);
		if (x1 instanceof LongValue && x2 instanceof LongValue) {

			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();

			HWND hWnd = new HWND(new Pointer(t1));
			int nIndex = (int) t2;
			LONG ret = User32DLL.INSTANCE.GetWindowLong(hWnd, nIndex);

			long value = ret.longValue();
			register.mov("eax", new LongValue(value));
			System.out.println("Return Value: " + value);
		}
		return false;
	}

}
