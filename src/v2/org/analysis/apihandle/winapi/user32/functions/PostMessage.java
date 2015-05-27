/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: PostMessage.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LPARAM;
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
 * This function places a message in the message queue associated with the
 * thread that created the specified window and then returns without waiting
 * for the thread to process the message. Messages in a message queue are
 * retrieved by calls to the GetMessage or PeekMessage function.
 * 
 * @param hWnd
 *            : Handle to the window whose window procedure is to receive
 *            the message.
 * @param msg
 *            : Specifies the message to be posted.
 * @param wParam
 *            : Specifies additional message-specific information.
 * @param lParam
 *            : Specifies additional message-specific information.
 *
 * @return If the function succeeds, the return value is nonzero.
 * 
 * @author Yen Nguyen
 *
 */
public class PostMessage extends User32API {

	public PostMessage() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();

		/*
		 * HWND hWnd, // handle of destination window UINT Msg, // message to
		 * post WPARAM wParam, // first message parameter LPARAM lParam //
		 * second message parameter
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

			String msg = memory.getText(new X86MemoryOperand(DataType.INT32, t2));
			String str1 = memory.getText(new X86MemoryOperand(DataType.INT32, t1));
			System.out.println("Handle Window:" + t1 + " " + str1 + ", Post Message:" + msg + ", First Param:" + t3
					+ ", Second Param:" + t4);

			BOOL ret = User32DLL.INSTANCE.PostMessage(new HWND(new Pointer(t1)), (int)t2, new WPARAM(t3), new LPARAM(t4));
			
			register.mov("eax", new LongValue(ret.longValue()));
		}
		return false;
	}

}
