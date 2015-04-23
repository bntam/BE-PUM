/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: CloseWindow.java
 * Created date: Mar 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.be_pum.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.HMENU;
import v2.org.analysis.apihandle.be_pum.winapi.user32.User32API;
import v2.org.analysis.apihandle.be_pum.winapi.user32.User32DLL;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Destroys the specified menu and frees any memory that the menu occupies.
 * 
 * @param hMenu
 *            [in] A handle to the menu to be destroyed.
 * 
 * @return If the function succeeds, the return value is nonzero.
 * 
 * @author Yen Nguyen
 *
 */
public class DestroyMenu extends User32API {

	public DestroyMenu() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		System.out.println("Argument: " + x1);

		if (x1 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();

			HMENU hMenu = new HMENU(new Pointer(t1));
			BOOL ret = User32DLL.INSTANCE.DestroyMenu(hMenu);

			register.mov("eax", new LongValue(ret.longValue()));
		}
		return false;
	}

}
