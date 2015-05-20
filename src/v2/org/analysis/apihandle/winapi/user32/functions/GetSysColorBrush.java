/**
 * 
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HBRUSH;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * The GetSysColorBrush function retrieves a handle identifying a logical brush
 * that corresponds to the specified color index.
 * 
 * @param nIndex
 *            A color index. This value corresponds to the color used to paint
 *            one of the window elements. See GetSysColor for system color index
 *            values.
 * 
 * @return The return value identifies a logical brush if the nIndex parameter
 *         is supported by the current platform. Otherwise, it returns NULL.
 * 
 * @author Yen Nguyen
 *
 */
public class GetSysColorBrush extends User32API {

	public GetSysColorBrush() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Register register = env.getRegister();

		Value x1 = stack.pop();

		System.out.println("Argument:" + x1);

		if (x1 instanceof LongValue) {
			long x = ((LongValue) x1).getValue();

			int nIndex = (int) x;
			HBRUSH ret = User32DLL.INSTANCE.GetSysColorBrush(nIndex);

			long value = (ret == null) ? 0 : Pointer.nativeValue(ret.getPointer());
			register.mov("eax", new LongValue(value));
			System.out.println("Return Value: " + value);
		}
		return false;
	}

}
