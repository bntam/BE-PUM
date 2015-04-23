/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: CloseWindow.java
 * Created date: Mar 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.be_pum.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import v2.org.analysis.apihandle.be_pum.winapi.user32.User32API;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Destroys the specified window. The function sends WM_DESTROY and WM_NCDESTROY
 * messages to the window to deactivate it and remove the keyboard focus from
 * it. The function also destroys the window's menu, flushes the thread message
 * queue, destroys timers, removes clipboard ownership, and breaks the clipboard
 * viewer chain (if the window is at the top of the viewer chain).
 * 
 * If the specified window is a parent or owner window, DestroyWindow
 * automatically destroys the associated child or owned windows when it destroys
 * the parent or owner window. The function first destroys child or owned
 * windows, and then it destroys the parent or owner window.
 * 
 * DestroyWindow also destroys modeless dialog boxes created by the CreateDialog
 * function.
 * 
 * @param hWnd
 *            [in] Type: HWND A handle to the window to be destroyed.
 * 
 * @return Type: BOOL If the function succeeds, the return value is nonzero.
 * 
 *         If the function fails, the return value is zero. To get extended
 *         error information, call {@link Kernel32#GetLastError}.
 * 
 * @author Yen Nguyen
 *
 */
public class DestroyWindow extends User32API {

	public DestroyWindow() {
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
			boolean ret = User32.INSTANCE.DestroyWindow(hWnd);

			register.mov("eax", new LongValue(ret ? 1 : 0));
		}
		return false;
	}

}
