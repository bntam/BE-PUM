/**
 * 
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.HWND;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Opens the clipboard for examination and prevents other applications from
 * modifying the clipboard content.
 * 
 * @param hWndNewOwner
 *            A handle to the window to be associated with the open clipboard.
 *            If this parameter is NULL, the open clipboard is associated with
 *            the current task.
 * 
 * @return If the function succeeds, the return value is nonzero.
 * 
 * @author Yen Nguyen
 *
 */
public class OpenClipboard extends User32API {

	public OpenClipboard() {
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

			HWND hWndNewOwner = (t1 != 0L) ? new HWND(new Pointer(t1)) : null;
			BOOL ret = User32DLL.INSTANCE.OpenClipboard(hWndNewOwner);

			register.mov("eax", new LongValue(ret.longValue()));
		}
		return false;
	}

}
