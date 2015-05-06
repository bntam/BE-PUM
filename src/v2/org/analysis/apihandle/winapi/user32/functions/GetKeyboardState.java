/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: GetKeyboardState.java
 * Created date: Mar 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.platform.win32.User32;

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
 * The GetKeyboardState function copies the status of the 256 virtual keys to
 * the specified buffer.
 * 
 * @param lpKeyState
 *            Pointer to the 256-byte array that receives the status data for
 *            each virtual key.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetKeyboardState extends User32API {

	public GetKeyboardState() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		System.out.println("Argument:" + x1);

		if (x1 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();

			byte[] lpKeyState = new byte[256];
			boolean ret = User32.INSTANCE.GetKeyboardState(lpKeyState);

			for (int i = 0; i < 256; i++) {
				memory.setByteMemoryValue(new X86MemoryOperand(DataType.INT32, t1 + i), new LongValue(lpKeyState[i]));
			}

			register.mov("eax", new LongValue(ret ? 1 : 0));
		}
		return false;
	}

}
