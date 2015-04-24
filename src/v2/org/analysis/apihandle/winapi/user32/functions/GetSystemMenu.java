/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: GetSystemMenu.java
 * Created date: Mar 21, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.HMENU;
import com.sun.jna.platform.win32.WinDef.HWND;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Enables the application to access the window menu (also known as the system
 * menu or the control menu) for copying and modifying.
 * 
 * @param hWnd
 *            A handle to the window that will own a copy of the window menu.
 * 
 * @param bRevert
 *            The action to be taken. If this parameter is FALSE, GetSystemMenu
 *            returns a handle to the copy of the window menu currently in use.
 *            The copy is initially identical to the window menu, but it can be
 *            modified. If this parameter is TRUE, GetSystemMenu resets the
 *            window menu back to the default state. The previous window menu,
 *            if any, is destroyed.
 * 
 * @return If the bRevert parameter is FALSE, the return value is a handle to a
 *         copy of the window menu. If the bRevert parameter is TRUE, the return
 *         value is NULL.
 * 
 * @author Yen Nguyen
 *
 */
public class GetSystemMenu extends User32API {

	public GetSystemMenu() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		// Memory memory = env.getMemory();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		Value x2 = stack.pop();

		System.out.println("Argument:" + x1 + " " + x2);

		if (x1 instanceof LongValue && x2 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();

			HWND hWnd = new HWND(new Pointer(t1));
			BOOL bRevert = new BOOL(t2);
			HMENU ret = User32DLL.INSTANCE.GetSystemMenu(hWnd, bRevert);

			long value = (ret == null) ? 0 : Pointer.nativeValue(ret.getPointer());
			register.mov("eax", new LongValue(value));
			System.out.println("Return Value: " + value);
		}
		return false;
	}
}
