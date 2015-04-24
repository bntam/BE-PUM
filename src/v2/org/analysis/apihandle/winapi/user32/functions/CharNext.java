/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: CharLower.java
 * Created date: Mar 8, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

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
 * Retrieves a pointer to the next character in a string. This function can
 * handle strings consisting of either single- or multi-byte characters.
 * 
 * @param lpsz
 *            A character in a null-terminated string.
 * 
 * @return The return value is a pointer to the next character in the string, or
 *         to the terminating null character if at the end of the string. If
 *         lpsz points to the terminating null character, the return value is
 *         equal to lpsz.
 * 
 * @author Yen Nguyen
 *
 */
public class CharNext extends User32API {

	public CharNext() {
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
			long x = ((LongValue) x1).getValue();

			char[] lpsz = memory.getText(new X86MemoryOperand(DataType.INT32, x)).toCharArray();
			User32DLL.INSTANCE.CharNext(lpsz);
			
			register.mov("eax", new LongValue((lpsz.length == 0) ? x : (x + 2)));
		}
		return false;
	}

}
