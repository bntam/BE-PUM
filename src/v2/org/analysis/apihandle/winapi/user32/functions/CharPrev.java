/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: CharLower.java
 * Created date: Mar 8, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

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
 * Retrieves a pointer to the next character in a string. This function can
 * handle strings consisting of either single- or multi-byte characters.
 * 
 * @param lpszStart
 *            [in] The beginning of the string.
 * 
 * @param lpszCurrent
 *            [in] A character in a null-terminated string.
 * 
 * @return The return value is a pointer to the preceding character in the
 *         string, or to the first character in the string if the lpszCurrent
 *         parameter equals the lpszStart parameter.
 * 
 * @author Yen Nguyen
 *
 */
public class CharPrev extends User32API {

	public CharPrev() {
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

		if (x1 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();

			String lpszStart = memory.getText(new X86MemoryOperand(DataType.INT32, t1));
			
			long ret;
			
			if (t1 == t2) {
				ret = t1;
			} else if ((t1 + 2) <= t2 && t2 <= (t1 + lpszStart.length() * 2)) {
				ret = t2 - 2;
			} else {
				ret = t2 - 2;
			}
			
			register.mov("eax", new LongValue(ret));
		}
		return false;
	}
}
