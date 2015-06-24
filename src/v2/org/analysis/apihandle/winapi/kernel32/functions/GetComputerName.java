/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetComputerName.java
 * Created date: Mar 1, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.complement.Convert;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Retrieves only the NetBIOS name of the local computer.
 * 
 * @param buffer
 *            A pointer to a buffer that receives the computer name or the
 *            cluster virtual server name. The buffer size should be large
 *            enough to contain MAX_COMPUTERNAME_LENGTH + 1 characters.
 * 
 * @param lpnSize
 *            On input, specifies the size of the buffer, in TCHARs. On output,
 *            the number of TCHARs copied to the destination buffer, not
 *            including the terminating null character. If the buffer is too
 *            small, the function fails and GetLastError returns
 *            ERROR_BUFFER_OVERFLOW. The lpnSize parameter specifies the size of
 *            the buffer required, including the terminating null character.
 * 
 * @return If the function succeeds, the return value is a nonzero value. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetComputerName extends Kernel32API {

	public GetComputerName() {

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

		if (x1 instanceof LongValue && x2 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();

			int buffSize = (int) t2;
			if (buffSize > 256)
				buffSize = 256;
			char[] lpBuffer = new char[buffSize];
			IntByReference lpnSize = new IntByReference((int) t2);

			boolean ret = Kernel32.INSTANCE.GetComputerName(lpBuffer, lpnSize);

			StringBuilder compName = new StringBuilder();
			for (int i = 0; i < lpBuffer.length; i++) {
				if (lpBuffer[i] != 0) {
					memory.setByteMemoryValue(new X86MemoryOperand(DataType.INT8, t1 + i), new LongValue(lpBuffer[i]));
					compName.append(lpBuffer[i]);
				}
			}
			System.out.println("Computer Name:" + compName.toString());
			register.mov("eax", new LongValue(ret ? 1 : 0));
		}
		return false;
	}

}
