/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: SendMessage.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinUser.MSG;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Retrieves a message from the calling thread's message queue. The function
 * dispatches incoming sent messages until a posted message is available for
 * retrieval.
 * 
 * @param lpMsg
 *            A pointer to an MSG structure that receives message information
 *            from the thread's message queue.
 * 
 * @param hWnd
 *            A handle to the window whose messages are to be retrieved. The
 *            window must belong to the current thread.
 * 
 * @param wMsgFilterMin
 *            The integer value of the lowest message value to be retrieved. Use
 *            WM_KEYFIRST (0x0100) to specify the first keyboard message or
 *            WM_MOUSEFIRST (0x0200) to specify the first mouse message.
 * 
 * @param wMsgFilterMax
 *            The integer value of the highest message value to be retrieved.
 *            Use WM_KEYLAST to specify the last keyboard message or
 *            WM_MOUSELAST to specify the last mouse message.
 * 
 * @return If the function retrieves a message other than WM_QUIT, the return
 *         value is nonzero. If the function retrieves the WM_QUIT message, the
 *         return value is zero.
 * 
 * @author Yen Nguyen
 *
 */
public class GetMessage extends User32API {

	public GetMessage() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		Value x2 = stack.pop();
		Value x3 = stack.pop();
		Value x4 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4);

		if (x1 instanceof LongValue && x2 instanceof LongValue && x3 instanceof LongValue && x4 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			long t3 = ((LongValue) x3).getValue();
			long t4 = ((LongValue) x4).getValue();

			MSG lpMsg = new MSG();
			HWND hWnd = (t2 == 0) ? null : new HWND(new Pointer(t2));
			UINT wMsgFilterMin = new UINT(t3);
			UINT wMsgFilterMax = new UINT(t4);
			BOOL ret = User32DLL.INSTANCE.GetMessage(lpMsg, hWnd, wMsgFilterMin, wMsgFilterMax);

			register.mov("eax", new LongValue(ret.longValue()));
			
			long value = Pointer.nativeValue(lpMsg.hWnd.getPointer());
			memory.setDoubleWordMemoryValue(t1, new LongValue(value));
			
			value = lpMsg.message;
			memory.setDoubleWordMemoryValue(t1 += 4, new LongValue(value));
			
			value = lpMsg.wParam.longValue();
			memory.setDoubleWordMemoryValue(t1 += 4, new LongValue(value));
			
			value = lpMsg.lParam.longValue();
			memory.setDoubleWordMemoryValue(t1 += 4, new LongValue(value));
			
			value = lpMsg.time;
			memory.setDoubleWordMemoryValue(t1 += 4, new LongValue(value));

			memory.setDoubleWordMemoryValue(t1 += 4, new LongValue(lpMsg.pt.x));
			memory.setDoubleWordMemoryValue(t1 += 4, new LongValue(lpMsg.pt.y));
		}
		return false;
	}

}
