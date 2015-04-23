/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetEnvironmentVariable.java
 * Created date: Mar 2, 2015
 * Description:
 */
package v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions;

import v2.org.analysis.apihandle.be_pum.winapi.kernel32.Kernel32API;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.platform.win32.Kernel32;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Retrieves the contents of the specified variable from the environment block
 * of the calling process.
 * 
 * @param lpName
 *            The name of the environment variable.
 * 
 * @param lpBuffer
 *            A pointer to a buffer that receives the contents of the specified
 *            environment variable as a null-terminated string. An environment
 *            variable has a maximum size limit of 32,767 characters, including
 *            the null-terminating character.
 * 
 * @param nSize
 *            The size of the buffer pointed to by the lpBuffer parameter,
 *            including the null-terminating character, in characters.
 * 
 * @return If the function succeeds, the return value is the number of
 *         characters stored in the buffer pointed to by lpBuffer, not including
 *         the terminating null character. If lpBuffer is not large enough to
 *         hold the data, the return value is the buffer size, in characters,
 *         required to hold the string and its terminating null character and
 *         the contents of lpBuffer are undefined. If the function fails, the
 *         return value is zero. To get extended error information, call
 *         GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetEnvironmentVariable extends Kernel32API {

	public GetEnvironmentVariable() {
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
		System.out.println("Argument: lpName:" + x1 + ", lpBuffer:" + x2 + ", nSize" + x3);
		if (x1 instanceof LongValue && x2 instanceof LongValue && x3 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			long t3 = ((LongValue) x3).getValue();

			String lpName = memory.getText(new X86MemoryOperand(DataType.INT32, t1));
			char[] lpBuffer = new char[(int) t3];
			int ret = Kernel32.INSTANCE.GetEnvironmentVariable(lpName, lpBuffer, (int) t3);

			String variable = new String(lpBuffer);
			memory.setText(new X86MemoryOperand(DataType.INT32, t2), variable, variable.length());

			register.mov("eax", new LongValue(ret));
		}
		return false;
	}

}
