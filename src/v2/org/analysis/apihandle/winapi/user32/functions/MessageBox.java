/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: MessageBox.java
 * Created date: Feb 8, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.winapi.kernel32.functions.Sleep;
import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.environment.ExternalMemory.ExternalMemoryReturnData;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Displays a modal dialog box that contains a system icon, a set of buttons,
 * and a brief application-specific message, such as status or error
 * information. The message box returns an integer value that indicates which
 * button the user clicked.
 * 
 * @param hWnd
 *            : A handle to the owner window of the message box to be created.
 *            If this parameter is NULL, the message box has no owner window.
 * 
 * @param lpText
 *            : The message to be displayed. If the string consists of more than
 *            one line, you can separate the lines using a carriage return
 *            and/or linefeed character between each line.
 * 
 * @param lpCaption
 *            : The dialog box title. If this parameter is NULL, the default
 *            title is Error.
 * 
 * @param uType
 *            : The contents and behavior of the dialog box. This parameter can
 *            be a combination of flags from the following groups of flags.
 * 
 * @return If a message box has a Cancel button, the function returns the
 *         IDCANCEL value if either the ESC key is pressed or the Cancel button
 *         is selected. If the message box has no Cancel button, pressing ESC
 *         has no effect.
 * 
 * @author Yen Nguyen
 *
 */
public class MessageBox extends User32API {

	private Integer apiCallReturn = null;

	public MessageBox() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();

		/*
		 * HWND hWnd, // handle of owner window LPCTSTR lpText, // address of
		 * text in message box LPCTSTR lpCaption, // address of title of message
		 * box UINT uType // style of message box
		 */
		Value x1 = stack.pop();
		Value x2 = stack.pop();
		Value x3 = stack.pop();
		Value x4 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4);
		System.out.print("Handle:" + x1.toString());
		if (x1 instanceof LongValue && x2 instanceof LongValue && x3 instanceof LongValue && x4 instanceof LongValue) {
			HWND hWnd = new HWND(new Pointer(((LongValue) x1).getValue()));
			String lpText = memory.getText(new X86MemoryOperand(DataType.INT32, ((LongValue) x2).getValue()));
			String lpCaption = memory.getText(new X86MemoryOperand(DataType.INT32, ((LongValue) x3).getValue()));
			UINT uType = new UINT(((LongValue) x4).getValue());

			System.out.print(", Address of Text:" + x2.toString() + ", Text:" + lpText);
			System.out.print(", Address of Title Text:" + x3.toString() + ", Title Text:" + lpCaption);
			System.out.println(", Style:" + x4.toString());

			MBThread thread = new MBThread(hWnd, lpText, lpCaption, uType);
			try {
				thread.start();
				Thread.sleep(500);
				thread.interrupt();
			} catch (Exception e) {
				e.printStackTrace();
			}

			// Return code/value Description
			// IDABORT 3 The Abort button was selected.
			// IDCANCEL 2 The Cancel button was selected.
			// IDCONTINUE 11 The Continue button was selected.
			// IDIGNORE 5 The Ignore button was selected.
			// IDNO 7 The No button was selected.
			// IDOK 1 The OK button was selected.
			// IDRETRY 4 The Retry button was selected.
			// IDTRYAGAIN 10 The Try Again button was selected.
			// IDYES 6 The Yes button was selected.

			register.mov("eax", new LongValue(1));
		}
		return false;
	}

	class MBThread extends Thread {
		HWND _hWnd;
		String _lpText;
		String _lpCaption;
		UINT _uType;

		public MBThread(HWND hWnd, String lpText, String lpCaption, UINT uType) {
			this._hWnd = hWnd;
			this._lpText = lpText;
			this._lpCaption = lpCaption;
			this._uType = uType;
		}

		@Override
		public void run() {
			apiCallReturn = User32DLL.INSTANCE.MessageBox(this._hWnd, this._lpText, this._lpCaption, this._uType);
		}
	}
}
