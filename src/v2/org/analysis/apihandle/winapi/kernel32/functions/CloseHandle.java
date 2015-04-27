/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: CloseHandle.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * The CloseHandle function closes an open object handle.
 * 
 * @param hObject
 *            Handle to an open object. This parameter can be a pseudo handle or
 *            INVALID_HANDLE_VALUE.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class CloseHandle extends Kernel32API {

	public CloseHandle() {
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
			System.out.println("Object Handle:" + x);

			boolean ret = Kernel32.INSTANCE.CloseHandle(new HANDLE(x != 0 ? new Pointer(x) : Pointer.NULL));
			register.mov("eax", new LongValue(ret ? 1 : 0));

			System.out.println("Return Value:" + ret);
		}
		return false;
	}

}
