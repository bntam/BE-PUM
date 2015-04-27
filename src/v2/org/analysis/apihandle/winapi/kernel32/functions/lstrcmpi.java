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
 * Compares two character strings. The comparison is not case-sensitive.
 * 
 * @param lpString1
 *            The first null-terminated string to be compared.
 * 
 * @param lpString2
 *            The second null-terminated string to be compared.
 * 
 * @return If the string pointed to by lpString1 is less than the string pointed
 *         to by lpString2, the return value is negative. If the string pointed
 *         to by lpString1 is greater than the string pointed to by lpString2,
 *         the return value is positive. If the strings are equal, the return
 *         value is zero.
 * 
 * @author Yen Nguyen
 *
 */
public class lstrcmpi extends Kernel32API {

	public lstrcmpi() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName,
			BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		Value x2 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2 + " ");

		if (x1 instanceof LongValue && x2 instanceof LongValue) {
			long destAddr = ((LongValue) x1).getValue();
			long scrAddr = ((LongValue) x2).getValue();

			String dest = destAddr == 0 ? null : memory
					.getText(new X86MemoryOperand(DataType.INT32, destAddr));
			String src = scrAddr == 0 ? null : memory
					.getText(new X86MemoryOperand(DataType.INT32, scrAddr));

			System.out.println("Destination String:" + dest
					+ ", Source String:" + src);

			int ret = Kernel32DLL.INSTANCE.lstrcmpi(new WString(dest),
					new WString(src));

			register.mov("eax", new LongValue(ret));
		}
		return false;
	}

}
