/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: ShowWindow.java
 * Created date: Mar 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
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
 * Sets the specified window's show state.
 * 
 * @param hWnd
 *            A handle to the window.
 * 
 * @param nCmdShow
 *            Controls how the window is to be shown. This parameter is ignored
 *            the first time an application calls ShowWindow, if the program
 *            that launched the application provides a STARTUPINFO structure.
 *            Otherwise, the first time ShowWindow is called, the value should
 *            be the value obtained by the WinMain function in its nCmdShow
 *            parameter.
 * 
 * @return If the window was previously visible, the return value is nonzero. If
 *         the window was previously hidden, the return value is zero..
 * @author Yen Nguyen
 *
 */
public class ShowWindow extends User32API {

	public ShowWindow() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		//Memory memory = env.getMemory();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		Value x2 = stack.pop();
		System.out.print("Argument:" + x1 + " " + x2);
		if (x1 instanceof LongValue && x2 instanceof LongValue) {

			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();

			HWND hWnd = new HWND(new Pointer(t1));
			int nCmdShow = (int) t2;
			BOOL ret = User32DLL.INSTANCE.ShowWindow(hWnd, nCmdShow);

			register.mov("eax", new LongValue(ret.longValue()));
		}
		return false;
	}

}
