/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: SendMessage.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;

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
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Sends the specified message to a window or windows. The SendMessage
 * function calls the window procedure for the specified window and does not
 * return until the window procedure has processed the message.
 * 
 * @param hWnd
 *            : A handle to the window whose window procedure will receive
 *            the message. If this parameter is HWND_BROADCAST
 *            ((HWND)0xffff), the message is sent to all top-level windows
 *            in the system, including disabled or invisible unowned
 *            windows, overlapped windows, and pop-up windows; but the
 *            message is not sent to child windows.
 * 
 * @param Msg
 *            : The message to be sent.
 * 
 * @param wParam
 *            : Additional message-specific information.
 * 
 * @param lParam
 *            : Additional message-specific information.
 * 
 * @return The return value specifies the result of the message processing;
 *         it depends on the message sent.
 *         
 * @author Yen Nguyen
 *
 */
public class SendMessage extends User32API {

	public SendMessage() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();

		/*
		 * HWND hWnd, handle of destination window UINT Msg, message to send
		 * WPARAM wParam, first message parameter LPARAM lParam second message
		 * parameter
		 */

		Value x1 = stack.pop();
		Value x2 = stack.pop();
		Value x3 = stack.pop();
		Value x4 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4);

		if (x1 instanceof LongValue && x2 instanceof LongValue && x3 instanceof LongValue && x4 instanceof LongValue) {
			/*
			 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
			 * x1).getValue(), ((ValueLongExp) x2).getValue(), program);
			 */
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			long t3 = ((LongValue) x3).getValue();
			long t4 = ((LongValue) x4).getValue();

			// String str = symbolValueMemoryOperand.getText(new
			// X86MemoryOperand(DataType.INT32, t2));
			String msg = memory.getText(new X86MemoryOperand(DataType.INT32, t2));

			System.out.println("Window Handle:" + t1 + ", Message Sent:" + msg + ", First Param:" + t3
					+ ", Second Param:" + t4);
			
			LRESULT ret = User32DLL.INSTANCE.SendMessage(new HWND(new Pointer(t1)), (int)t2, new WPARAM(t3), new LPARAM(t4));
			
			register.mov("eax", new LongValue(ret.longValue()));
		}
		return false;
	}

}
