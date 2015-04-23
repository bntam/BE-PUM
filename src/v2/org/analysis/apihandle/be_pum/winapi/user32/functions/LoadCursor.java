/**
 * 
 */
package v2.org.analysis.apihandle.be_pum.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef.HCURSOR;
import com.sun.jna.platform.win32.WinDef.HINSTANCE;

import v2.org.analysis.apihandle.be_pum.winapi.user32.User32API;
import v2.org.analysis.apihandle.be_pum.winapi.user32.User32DLL;

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
 * Loads the specified cursor resource from the executable (.EXE) file
 * associated with an application instance.
 * 
 * @param hInstance
 *            A handle to an instance of the module whose executable file
 *            contains the cursor to be loaded.
 * 
 * @param lpCursorName
 *            The name of the cursor resource to be loaded. Alternatively, this
 *            parameter can consist of the resource identifier in the low-order
 *            word and zero in the high-order word. The MAKEINTRESOURCE macro
 *            can also be used to create this value. To use one of the
 *            predefined cursors, the application must set the hInstance
 *            parameter to NULL and the lpCursorName parameter to one the
 *            following values.
 * 
 * @return If the function succeeds, the return value is the handle to the newly
 *         loaded cursor. If the function fails, the return value is NULL. To
 *         get extended error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class LoadCursor extends User32API {
	public LoadCursor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		Value x2 = stack.pop();
		System.out.print("Argument:" + x1 + " " + x2);
		if (x1 instanceof LongValue && x2 instanceof LongValue) {

			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();

			HINSTANCE hInstance = null;
			if (t1 != 0) {
				hInstance = new HINSTANCE();
				hInstance.setPointer(new Pointer(t1));
			}
			WString lpCursorName = new WString(memory.getText(new X86MemoryOperand(DataType.INT32, t2)));
			HCURSOR ret = User32DLL.INSTANCE.LoadCursor(hInstance, lpCursorName);

			long value = (ret == null) ? 0 : Pointer.nativeValue(ret.getPointer());
			register.mov("eax", new LongValue(value));
			System.out.println("Return Value: " + value);
		}
		return false;
	}

}
