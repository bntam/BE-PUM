/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetShortPathName.java
 * Created date: Mar 3, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

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
 * Retrieves the short path form of the specified path.
 * 
 * @param lpszLongPath
 *            The path string.
 * 
 * @param lpdzShortPath
 *            A pointer to a buffer to receive the null-terminated short form of
 *            the path that lpszLongPath specifies.
 * 
 * @param cchBuffer
 *            The size of the buffer that lpszShortPath points to, in TCHARs.
 * 
 * @return If the function succeeds, the return value is the length, in TCHARs,
 *         of the string that is copied to lpszShortPath, not including the
 *         terminating null character. If the lpszShortPath buffer is too small
 *         to contain the path, the return value is the size of the buffer, in
 *         TCHARs, that is required to hold the path and the terminating null
 *         character. If the function fails for any other reason, the return
 *         value is zero. To get extended error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetShortPathName extends Kernel32API {

	public GetShortPathName() {
		
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
		System.out.println("Argument:" + x1 + " " + x2 + " " + x3);

		if (x1 instanceof LongValue && x2 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			long t3 = ((LongValue) x3).getValue();

			String lpszLongPath = memory.getText(new X86MemoryOperand(DataType.INT32, t1));
			char[] lpdzShortPath = new char[(int) t3];
			int cchBuffer = (int) t3;
			int ret = Kernel32.INSTANCE.GetShortPathName(lpszLongPath, lpdzShortPath, cchBuffer);

			String shortPathName = new String(lpdzShortPath);
			memory.setText(new X86MemoryOperand(DataType.INT32, t2), shortPathName, ret);

			register.mov("eax", new LongValue(ret));
		}
		return false;
	}

}