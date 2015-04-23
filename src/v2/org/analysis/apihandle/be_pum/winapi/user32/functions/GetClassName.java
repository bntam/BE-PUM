/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: GetClassName.java
 * Created date: Mar 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.be_pum.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import v2.org.analysis.apihandle.be_pum.winapi.user32.User32API;
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
 * This function retrieves the name of the class to which the specified window
 * belongs.
 * 
 * @param hWnd
 *            Handle to the window and, indirectly, the class to which the
 *            window belongs.
 * 
 * @param lpClassName
 *            Long pointer to the buffer that is to receive the class name
 *            string.
 * 
 * @param nMaxCount
 *            Specifies the length, in characters, of the buffer pointed to by
 *            the lpClassName parameter. The class name string is truncated if
 *            it is longer than the buffer.
 * 
 * @return The number of characters copied to the specified buffer indicates
 *         success. Zero indicates failure. To get extended error information,
 *         call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetClassName extends User32API {

	public GetClassName() {
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
		System.out.println("Argument:" + x1 + " " + x2 + " " + x3);

		if (x1 instanceof LongValue && x2 instanceof LongValue && x3 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			long t3 = ((LongValue) x3).getValue();

			HWND hWnd = new HWND(new Pointer(t1));
			char[] lpClassName = new char[(int) t3];
			int nMaxCount = (int) t3;
			int ret = User32.INSTANCE.GetClassName(hWnd, lpClassName, nMaxCount);

			String className = new String(lpClassName);
			memory.setText(new X86MemoryOperand(DataType.INT32, t2), className, ret);

			register.mov("eax", new LongValue(ret));
		}
		return false;
	}

}
