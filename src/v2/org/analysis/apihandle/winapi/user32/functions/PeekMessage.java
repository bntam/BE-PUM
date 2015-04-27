/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: PeekMessage.java
 * Created date: Feb 8, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser.MSG;

import v2.org.analysis.apihandle.winapi.user32.User32API;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * This function checks a thread message queue for a message and places the
 * message (if any) in the specified structure.
 * 
 * @param lpMsg
 *            Pointer to an MSG structure that receives message information.
 * 
 * @param hWnd
 *            Handle to the window whose messages are to be examined.
 * 
 * @param wMsgFilterMin
 *            Specifies the value of the first message in the range of messages
 *            to be examined.
 * 
 * @param wMsgFilterMax
 *            Specifies the value of the last message in the range of messages
 *            to be examined.
 * 
 * @param wRemoveMsg
 *            Specifies how messages are handled. This parameter can be one of
 *            the following values.
 * 
 * @return Nonzero indicates success. Zero indicates failure.
 * 
 * @author Yen Nguyen
 *
 */
public class PeekMessage extends User32API {

	public PeekMessage() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();

		/*
		 * LPMSG lpMsg, // pointer to structure for message HWND hWnd, // handle
		 * to window UINT wMsgFilterMin, // first message UINT wMsgFilterMax, //
		 * last message UINT wRemoveMsg // removal flags
		 */
		Value x1 = stack.pop();
		Value x2 = stack.pop();
		Value x3 = stack.pop();
		Value x4 = stack.pop();
		Value x5 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5);

		if (x1 instanceof LongValue && x2 instanceof LongValue && x3 instanceof LongValue && x4 instanceof LongValue
				&& x5 instanceof LongValue) {
			/*
			 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
			 * x1).getValue(), ((ValueLongExp) x2).getValue(), program);
			 */
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			long t3 = ((LongValue) x3).getValue();
			long t4 = ((LongValue) x4).getValue();
			long t5 = ((LongValue) x5).getValue();

			MSG lpMsg = new MSG();
			HWND hWnd = new HWND(new Pointer(t2));
			int wMsgFilterMin = (int) t3;
			int wMsgFilterMax = (int) t4;
			int wRemoveMsg = (int) t5;
			
			boolean ret = User32.INSTANCE.PeekMessage(lpMsg, hWnd, wMsgFilterMin, wMsgFilterMax, wRemoveMsg);
			register.mov("eax", new LongValue((ret) ? 1 : 0));
			
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
