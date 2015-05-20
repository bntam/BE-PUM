/**
 * 
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.DWORDByReference;
import com.sun.jna.platform.win32.WinDef.HWND;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Retrieves the identifier of the thread that created the specified window and,
 * optionally, the identifier of the process that created the window..
 * 
 * @param hWnd
 *            A handle to the window.
 * 
 * @param lpdwProcessId
 *            A pointer to a variable that receives the process identifier. If
 *            this parameter is not NULL, GetWindowThreadProcessId copies the
 *            identifier of the process to the variable; otherwise, it does not.
 * 
 * @return The return value is the identifier of the thread that created the
 *         window.
 * 
 * @author Yen Nguyen
 *
 */
public class GetWindowThreadProcessId extends User32API {

	public GetWindowThreadProcessId() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		Value x2 = stack.pop();

		System.out.println("Argument:" + x1 + " " + x2);

		if (x1 instanceof LongValue && x2 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();

			HWND hWnd = new HWND(new Pointer(t1));
			DWORDByReference lpdwProcessId = (t2 == 0L) ? null : new DWORDByReference(new DWORD(t2));
			DWORD ret = User32DLL.INSTANCE.GetWindowThreadProcessId(hWnd, lpdwProcessId);

			register.mov("eax", new LongValue(ret.longValue()));

			if (t2 != 0L)
				memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2), new LongValue(lpdwProcessId
						.getValue().longValue()));
		}
		return false;
	}
}
