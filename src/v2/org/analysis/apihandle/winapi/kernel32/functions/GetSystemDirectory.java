/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetSystemDirectory.java
 * Created date: Feb 10, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Retrieves the path of the system directory. The system directory contains
 * system files such as dynamic-link libraries and drivers.
 * 
 * @param lpBuffer
 *            : A pointer to the buffer to receive the path. This path does not
 *            end with a backslash unless the system directory is the root
 *            directory. For example, if the system directory is named
 *            Windows\System32 on drive C, the path of the system directory
 *            retrieved by this function is C:\Windows\System32.
 * 
 * @param uSize
 *            : The maximum size of the buffer, in TCHARs.
 * 
 * @return If the function succeeds, the return value is the length, in TCHARs,
 *         of the string copied to the buffer, not including the terminating
 *         null character. If the length is greater than the size of the buffer,
 *         the return value is the size of the buffer required to hold the path,
 *         including the terminating null character.
 * 
 * @author Yen Nguyen
 *
 */
public class GetSystemDirectory extends Kernel32API {

	/**
	 * 
	 */
	public GetSystemDirectory() {

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

			char[] lpBuffer = new char[(int) t2];
			UINT uSize = new UINT(t2);
			UINT ret = Kernel32DLL.INSTANCE.GetSystemDirectory(lpBuffer, uSize);
			String curDir = new String(lpBuffer);
			curDir = curDir.substring(0, ret.intValue());

			memory.setText(new X86MemoryOperand(DataType.INT32, t1), curDir, ret.longValue());
			System.out.println("System Directory:" + curDir);
			register.mov("eax", new LongValue(ret.longValue()));
		}
		return false;
	}

}
