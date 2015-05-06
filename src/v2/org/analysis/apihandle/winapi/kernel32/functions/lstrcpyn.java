/**
 * 
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.WString;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

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
 * Copies a specified number of characters from a source string into a buffer.
 * 
 * @param lpString1
 *            The destination buffer, which receives the copied characters. The
 *            buffer must be large enough to contain the number of TCHAR values
 *            specified by iMaxLength, including room for a terminating null
 *            character.
 * 
 * @param lpString2
 *            The source string from which the function is to copy characters.
 * 
 * @param iMaxLength
 *            The number of TCHAR values to be copied from the string pointed to
 *            by lpString2 into the buffer pointed to by lpString1, including a
 *            terminating null character.
 * 
 * @return If the function succeeds, the return value is a pointer to the
 *         buffer. The function can succeed even if the source string is greater
 *         than iMaxLength characters. If the function fails, the
 * 
 * @author Yen Nguyen
 *
 */
public class lstrcpyn extends Kernel32API {
	
	public lstrcpyn() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName,
			BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();

		// LPTSTR lpString1, address of buffer
		// LPCTSTR lpString2 address of string to copy
		Value x1 = stack.pop();
		Value x2 = stack.pop();
		Value x3 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2 + " " + x3);

		if (x1 instanceof LongValue && x2 instanceof LongValue && x3 instanceof LongValue) {
			long destAddr = ((LongValue) x1).getValue();
			long scrAddr = ((LongValue) x2).getValue();
			int n = (int) ((LongValue) x3).getValue();

			String dest = memory.getText(new X86MemoryOperand(DataType.INT32, ((LongValue) x1).getValue()));
			String src = memory.getText(new X86MemoryOperand(DataType.INT32, ((LongValue) x2).getValue()));
			
			System.out.println("Destination Address:" + destAddr + ", Source String:" + src);
			
			WString ret = Kernel32DLL.INSTANCE.lstrcpyn(new WString(dest), new WString(src), n);
			memory.setText(new X86MemoryOperand(DataType.INT32, destAddr), ret.toString());
			register.mov("eax", new LongValue(1));
		}
		return false;
	}

}
