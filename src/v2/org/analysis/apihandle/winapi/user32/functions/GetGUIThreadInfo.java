/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: GetGUIThreadInfo.java
 * Created date: Mar 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.RECT;
import com.sun.jna.platform.win32.WinUser.GUITHREADINFO;

import v2.org.analysis.apihandle.winapi.user32.User32API;

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
 * Retrieves information about the active window or a specified graphical user
 * interface (GUI) thread.
 * 
 * @param idThread
 *            Identifies the thread for which information is to be retrieved. To
 *            retrieve this value, use the GetWindowThreadProcessId function. If
 *            this parameter is NULL, the function returns information for the
 *            foreground thread.
 * 
 * @param lpgui
 *            Pointer to a GUITHREADINFO structure that receives information
 *            describing the thread. Note that you must set GUITHREADINFO.cbSize
 *            to sizeof(GUITHREADINFO) before calling this function.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetGUIThreadInfo extends User32API {

	public GetGUIThreadInfo() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		Value x2 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2 + " ");

		if (x1 instanceof LongValue && x2 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();

			int idThread = (int) t1;
			GUITHREADINFO lpgui = new GUITHREADINFO();

			long value = ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2)))
					.getValue();
			lpgui.cbSize = (int) value;

			value = ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 += 4)))
					.getValue();
			lpgui.flags = (int) value;

			value = ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 += 4)))
					.getValue();
			lpgui.hwndActive = new HWND(new Pointer(value));

			value = ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 += 4)))
					.getValue();
			lpgui.hwndFocus = new HWND(new Pointer(value));

			value = ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 += 4)))
					.getValue();
			lpgui.hwndCapture = new HWND(new Pointer(value));

			value = ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 += 4)))
					.getValue();
			lpgui.hwndMenuOwner = new HWND(new Pointer(value));

			value = ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 += 4)))
					.getValue();
			lpgui.hwndMoveSize = new HWND(new Pointer(value));

			value = ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 += 4)))
					.getValue();
			lpgui.hwndCaret = new HWND(new Pointer(value));

			lpgui.rcCaret = new RECT();
			lpgui.rcCaret.left = (int) ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(
					DataType.INT32, t2 += 4))).getValue();
			lpgui.rcCaret.top = (int) ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32,
					t2 += 4))).getValue();
			lpgui.rcCaret.right = (int) ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(
					DataType.INT32, t2 += 4))).getValue();
			lpgui.rcCaret.bottom = (int) ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(
					DataType.INT32, t2 += 4))).getValue();

			boolean ret = User32.INSTANCE.GetGUIThreadInfo(idThread, lpgui);

			register.mov("eax", new LongValue(ret ? 1 : 0));

		}
		return false;
	}

}
