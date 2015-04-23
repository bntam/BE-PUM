/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetFileTime.java
 * Created date: Mar 3, 2015
 * Description:
 */
package v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions;

import v2.org.analysis.apihandle.be_pum.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.be_pum.winapi.kernel32.Kernel32DLL;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.platform.win32.WinBase.FILETIME;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Retrieves the current system date and time. The information is in Coordinated
 * Universal Time (UTC) format.
 * 
 * @param lpSystemTimeAsFileTime
 *            A pointer to a FILETIME structure to receive the current system
 *            date and time in UTC format.
 * 
 * @author Yen Nguyen
 *
 */
public class GetSystemTimeAsFileTime extends Kernel32API {

	public GetSystemTimeAsFileTime() {

	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();

		Value x1 = stack.pop();
		System.out.println("Argument:" + x1);

		if (x1 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();

			FILETIME lpSystemTimeAsFileTime = new FILETIME();
			Kernel32DLL.INSTANCE.GetSystemTimeAsFileTime(lpSystemTimeAsFileTime);

			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1), new LongValue(
					lpSystemTimeAsFileTime.dwLowDateTime));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1 + 4), new LongValue(
					lpSystemTimeAsFileTime.dwHighDateTime));
		}
		return false;
	}

}
