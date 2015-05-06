/**
 * 
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.*;
import com.sun.jna.platform.win32.WinUser.MSG;

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
 * Determines whether a message is intended for the specified dialog box and, if
 * it is, processes the message.
 * 
 * @param hDlg
 *            A handle to the dialog box.
 * 
 * @param lpMsg
 *            A pointer to an MSG structure that contains the message to be
 *            checked.
 * 
 * @return If the message has been processed, the return value is nonzero. If
 *         the message has not been processed, the return value is zero.
 * 
 * @author Yen Nguyen
 *
 */
public class IsDialogMessage extends User32API {
	public IsDialogMessage() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName,
			BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		Value x2 = stack.pop();
		System.out.println("Argument: " + x1 + ", " + x2);
		if (x1 instanceof LongValue && x2 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();

			HWND hDlg = new HWND(new Pointer(t1));
			MSG lpMsg = new MSG();
			lpMsg.hWnd = new HWND(new Pointer(((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(
					DataType.INT32, t2))).getValue()));
			lpMsg.message = (int) ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32,
					t2 += 4))).getValue();
			lpMsg.wParam = new WPARAM(((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32,
					t2 += 4))).getValue());
			lpMsg.lParam = new LPARAM(((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32,
					t2 += 4))).getValue());
			lpMsg.time = (int) ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32,
					t2 += 4))).getValue();
			lpMsg.pt = new POINT((int) ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(
					DataType.INT32, t2 += 4))).getValue(),
					(int) ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 += 4)))
							.getValue());
			BOOL ret = User32DLL.INSTANCE.IsDialogMessage(hDlg, lpMsg);

			System.out.println("Return: " + ret.longValue());
			register.mov("eax", new LongValue(ret.longValue()));
		}
		return false;
	}

}
