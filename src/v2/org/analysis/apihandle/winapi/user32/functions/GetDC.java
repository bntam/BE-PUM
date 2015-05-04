/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: CloseWindow.java
 * Created date: Mar 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinDef.HWND;

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
 * This function retrieves a handle to a display device context (DC) for the
 * client area of the specified window. The display device context can be used
 * in subsequent graphics display interface (GDI) functions to draw in the
 * client area of the window.
 * 
 * @param hWnd
 *            Handle to the window whose device context is to be retrieved. If
 *            this value is NULL, GetDC retrieves the device context for the
 *            entire screen.
 * 
 * @return The handle the device context for the specified window's client area
 *         indicates success. NULL indicates failure. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetDC extends User32API {

	public GetDC() {
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

			HWND hWnd = new HWND(new Pointer(t1));
			HDC ret = User32.INSTANCE.GetDC(hWnd);

			register.mov("eax", new LongValue(Pointer.nativeValue(ret.getPointer())));
		}
		return false;
	}

}
