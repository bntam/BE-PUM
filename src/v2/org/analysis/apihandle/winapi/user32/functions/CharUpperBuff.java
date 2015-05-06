/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetSystemDirectory.java
 * Created date: Feb 10, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;

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
 * Converts lowercase characters in a buffer to uppercase characters. The
 * function converts the characters in place.
 * 
 * @param lpsz
 *            [in, out] A buffer containing one or more characters to be
 *            processed.
 * 
 * @param cchLength
 *            The size, in characters, of the buffer pointed to by lpsz.
 * 
 * @return The return value is the number of characters processed.
 * 
 * @author Yen Nguyen
 *
 */
public class CharUpperBuff extends User32API {

	public CharUpperBuff() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		Value x2 = stack.pop();

		System.out.println("Argument: Length:" + x2 + ", Memory Operand:" + x1);
		if (x1 instanceof LongValue && x2 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();

			String str = memory.getText(new X86MemoryOperand(DataType.INT32, t1));

			char[] lpsz = str.toCharArray();
			DWORD cchLength = new DWORD(t2);
			DWORD ret = User32DLL.INSTANCE.CharUpperBuff(lpsz, cchLength);

			register.mov("eax", new LongValue(ret.longValue()));

			memory.setText(new X86MemoryOperand(DataType.INT32, t1), new String(lpsz));
		}
		return false;
	}

}
