/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetCurrentDirectory.java
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

import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Retrieves the current directory for the current process.
 * 
 * @param nBufferLength
 *            : The length of the buffer for the current directory string, in
 *            TCHARs. The buffer length must include room for a terminating null
 *            character.
 * 
 * @param lpBuffer
 *            : A pointer to the buffer that receives the current directory
 *            string. This null-terminated string specifies the absolute path to
 *            the current directory. To determine the required buffer size, set
 *            this parameter to NULL and the nBufferLength parameter to 0.
 * 
 * @return If the function succeeds, the return value specifies the number of
 *         characters that are written to the buffer, not including the
 *         terminating null character.
 * 
 * @author Yen Nguyen
 *
 */
public class GetCurrentDirectory extends Kernel32API {

	/**
	 * 
	 */
	public GetCurrentDirectory() {
		
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		Value x2 = stack.pop();
		System.out.println("Argument: Length:" + x1 + ", Memory Operand:" + x2);
		if (x1 instanceof LongValue && x2 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			char[] lpBuffer = new char[(int) t1];
			DWORD ret = Kernel32DLL.INSTANCE.GetCurrentDirectory(new DWORD(t1), lpBuffer);

			String curDir = new String(lpBuffer);
			curDir = curDir.substring(0, ret.intValue());
			memory.setText(new X86MemoryOperand(DataType.INT32, ((LongValue) x2).getValue()), curDir, curDir.length());
			System.out.println("Current Directory:" + curDir);
			register.mov("eax", new LongValue(ret.longValue()));
		}
		return false;
	}

}
