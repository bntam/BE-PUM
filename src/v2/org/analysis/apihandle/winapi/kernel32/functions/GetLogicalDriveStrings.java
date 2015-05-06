/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetLogicalDriveStrings.java
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
import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * The GetLogicalDriveStrings function fills a buffer with strings that specify
 * valid drives in the system.
 * 
 * @param nBufferLength
 *            Maximum size of the buffer pointed to by lpBuffer, in TCHARs. This
 *            size does not include the terminating null character. If this
 *            parameter is zero, lpBuffer is not used.
 * 
 * @param lpBuffer
 *            Pointer to a buffer that receives a series of null-terminated
 *            strings, one for each valid drive in the system, plus with an
 *            additional null character. Each string is a device name.
 * 
 * @return If the function succeeds, the return value is the length, in
 *         characters, of the strings copied to the buffer, not including the
 *         terminating null character. Note that an ANSI-ASCII null character
 *         uses one byte, but a Unicode null character uses two bytes. If the
 *         buffer is not large enough, the return value is greater than
 *         nBufferLength. It is the size of the buffer required to hold the
 *         drive strings. If the function fails, the return value is zero. To
 *         get extended error information, use the GetLastError function.
 * 
 * @author Yen Nguyen
 *
 */
public class GetLogicalDriveStrings extends Kernel32API {

	public GetLogicalDriveStrings() {
		
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
			long t2 = ((LongValue) x2).getValue();

			DWORD nBufferLength = new DWORD(t1);
			char[] lpBuffer = new char[(int) t1];
			DWORD ret = Kernel32.INSTANCE.GetLogicalDriveStrings(nBufferLength, lpBuffer);

			String logicalDriveStrings = new String(lpBuffer);
			memory.setText(new X86MemoryOperand(DataType.INT32, t2), logicalDriveStrings, ret.longValue());
			System.out.println("Logical Drive Strings:" + logicalDriveStrings);
			register.mov("eax", new LongValue(ret.longValue()));
		}
		return false;
	}

}
