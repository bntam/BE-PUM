/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.user32.functions
 * File name: GetTopWindow.java
 * Created date: Apr 24, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Examines the Z order of the child windows associated with the specified
 * parent window and retrieves a handle to the child window at the top of the Z
 * order.
 * 
 * @param hWnd
 *            A handle to the parent window whose child windows are to be
 *            examined. If this parameter is NULL, the function returns a handle
 *            to the window at the top of the Z order.
 * 
 * @return If the function succeeds, the return value is a handle to the child
 *         window at the top of the Z order. If the specified window has no
 *         child windows, the return value is NULL. To get extended error
 *         information, use the GetLastError function.
 * 
 * @author Yen Nguyen
 *
 */
public class GetTopWindow extends User32API {

	public GetTopWindow() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		// Memory memory = env.getMemory();
		Register register = env.getRegister();

		Value x1 = stack.pop();

		System.out.println("Argument:" + x1);

		if (x1 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();

			HWND hWnd = (t1 == 0) ? null : new HWND(new Pointer(t1));
			HWND ret = User32DLL.INSTANCE.GetTopWindow(hWnd);

			long value = (ret == null) ? 0 : Pointer.nativeValue(ret.getPointer());
			register.mov("eax", new LongValue(value));
			System.out.println("Return Value: " + value);
		}
		return false;
	}

}
