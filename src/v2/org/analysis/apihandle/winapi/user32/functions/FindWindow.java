/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: FindWindow.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;

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
 * This function retrieves the handle to the top-level window whose class name
 * and window name match the specified strings. This function does not search
 * child windows.
 * 
 * @param lpClassName
 *            Long pointer to a null-terminated string that specifies the class
 *            name or is an atom that identifies the class-name string. If this
 *            parameter is an atom, it must be a global atom created by a
 *            previous call to the GlobalAddAtom function. The atom, a 16-bit
 *            value, must be placed in the low-order word of lpClassName; the
 *            high-order word must be zero.
 * 
 * @param lpWindowName
 *            Long pointer to a null-terminated string that specifies the window
 *            name (the window's title). If this parameter is NULL, all window
 *            names match.
 * 
 * @return A handle to the window that has the specified class name and window
 *         name indicates success. NULL indicates failure. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class FindWindow extends User32API {

	/**
	 * 
	 */
	public FindWindow() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();

		// LPCTSTR lpClassName, pointer to class name
		// LPCTSTR lpWindowName, pointer to window name
		Value x1 = stack.pop();
		Value x2 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2 + " ");

		if (x1 instanceof LongValue && x2 instanceof LongValue) {
			/*
			 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
			 * x1).getValue(), ((ValueLongExp) x2).getValue(), program);
			 */
			String className = memory.getText(new X86MemoryOperand(DataType.INT32, ((LongValue) x1).getValue()));
			String windowName = memory.getText(new X86MemoryOperand(DataType.INT32, ((LongValue) x2).getValue()));

			System.out.println("Class Name:" + className + ", Window Name Address:" + ((LongValue) x2).getValue()
					+ ", Window Name:" + windowName);

			HWND ret = User32.INSTANCE.FindWindow(className, windowName);

			long value = (ret == null) ? 0 : Pointer.nativeValue(ret.getPointer());
			register.mov("eax", new LongValue(value));
			System.out.println("Return Value: " + value);

		}
		return false;
	}
}
