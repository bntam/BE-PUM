/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetSystemTime.java
 * Created date: Feb 2, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.platform.win32.WinBase.FILETIME;
import com.sun.jna.platform.win32.WinBase.SYSTEMTIME;
import com.sun.jna.platform.win32.WinDef.BOOL;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Converts a file time to system time format. System time is based on
 * Coordinated Universal Time (UTC).
 * 
 * @param lpFileTime
 *            A pointer to a FILETIME structure containing the file time to be
 *            converted to system (UTC) date and time format.
 * 
 * @param lpSystemTime
 *            A pointer to a SYSTEMTIME structure to receive the converted file
 *            time.
 * 
 * @return If the function succeeds, the return value is nonzero.
 * 
 * @author Yen Nguyen
 *
 */
public class FileTimeToSystemTime extends Kernel32API {

	public FileTimeToSystemTime() {
		
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

			FILETIME lpFileTime = new FILETIME();
			SYSTEMTIME lpSystemTime = new SYSTEMTIME();

			lpFileTime.dwLowDateTime = (int) ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(
					DataType.INT32, t1))).getValue();
			lpFileTime.dwHighDateTime = (int) ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(
					DataType.INT32, t1 + 4))).getValue();

			BOOL ret = Kernel32DLL.INSTANCE.FileTimeToSystemTime(lpFileTime, lpSystemTime);
			register.mov("eax", new LongValue(ret.longValue()));

			memory.setWordMemoryValue(t2, new LongValue(lpSystemTime.wYear));
			memory.setWordMemoryValue(t2 + 2, new LongValue(lpSystemTime.wMonth));
			memory.setWordMemoryValue(t2 + 4, new LongValue(lpSystemTime.wDayOfWeek));
			memory.setWordMemoryValue(t2 + 6, new LongValue(lpSystemTime.wDay));
			memory.setWordMemoryValue(t2 + 8, new LongValue(lpSystemTime.wHour));
			memory.setWordMemoryValue(t2 + 10, new LongValue(lpSystemTime.wMinute));
			memory.setWordMemoryValue(t2 + 12, new LongValue(lpSystemTime.wSecond));
			memory.setWordMemoryValue(t2 + 14, new LongValue(lpSystemTime.wMilliseconds));
		}
		return false;
	}

}
