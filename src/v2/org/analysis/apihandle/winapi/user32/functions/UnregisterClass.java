/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: UnregisterClass.java
 * Created date: Mar 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.HINSTANCE;

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
 * Unregisters a window class, freeing the memory required for the class.
 * 
 * @param lpClassName
 *            A null-terminated string or a class atom. If lpClassName is a
 *            string, it specifies the window class name. This class name must
 *            have been registered by a previous call to the RegisterClass or
 *            RegisterClassEx function. System classes, such as dialog box
 *            controls, cannot be unregistered. If this parameter is an atom, it
 *            must be a class atom created by a previous call to the
 *            RegisterClass or RegisterClassEx function. The atom must be in the
 *            low-order word of lpClassName; the high-order word must be zero.
 * 
 * @param hInstance
 *            A handle to the instance of the module that created the class.
 * 
 * @return If the function succeeds, the return value is nonzero. If the class
 *         could not be found or if a window still exists that was created with
 *         the class, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class UnregisterClass extends User32API {

	public UnregisterClass() {
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

			String lpClassName = memory.getText(new X86MemoryOperand(DataType.INT32, t1));
			HINSTANCE hInstance = null;
			if (t2 != 0) {
				hInstance = new HINSTANCE();
				hInstance.setPointer(new Pointer(t2));
			}
			BOOL ret = User32DLL.INSTANCE.UnregisterClass(lpClassName, hInstance);

			register.mov("eax", new LongValue(ret.longValue()));
		}
		return false;
	}

}
