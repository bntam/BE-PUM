/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: SystemTimeToFileTime.java
 * Created date: Mar 28, 2015
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
 * Converts a system time to file time format. System time is based on
 * Coordinated Universal Time (UTC).
 * 
 * @param lpSystemTime
 *            A pointer to a SYSTEMTIME structure that contains the system time
 *            to be converted from UTC to file time format.
 * 
 * @param lpFileTime
 *            A pointer to a FILETIME structure to receive the converted system
 *            time.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class SystemTimeToFileTime extends Kernel32API {

	public SystemTimeToFileTime() {
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

			SYSTEMTIME lpSystemTime = null;
			if (t1 != 0L) {
				lpSystemTime = new SYSTEMTIME();
				lpSystemTime.wYear = (short) ((LongValue) memory.getWordMemoryValue(new X86MemoryOperand(
						DataType.INT32, t1))).getValue();
				lpSystemTime.wMonth = (short) ((LongValue) memory.getWordMemoryValue(new X86MemoryOperand(
						DataType.INT32, t1 += 2))).getValue();
				lpSystemTime.wDayOfWeek = (short) ((LongValue) memory.getWordMemoryValue(new X86MemoryOperand(
						DataType.INT32, t1 += 2))).getValue();
				lpSystemTime.wDay = (short) ((LongValue) memory.getWordMemoryValue(new X86MemoryOperand(DataType.INT32,
						t1 += 2))).getValue();
				lpSystemTime.wHour = (short) ((LongValue) memory.getWordMemoryValue(new X86MemoryOperand(
						DataType.INT32, t1 += 2))).getValue();
				lpSystemTime.wMinute = (short) ((LongValue) memory.getWordMemoryValue(new X86MemoryOperand(
						DataType.INT32, t1 += 2))).getValue();
				lpSystemTime.wSecond = (short) ((LongValue) memory.getWordMemoryValue(new X86MemoryOperand(
						DataType.INT32, t1 += 2))).getValue();
				lpSystemTime.wMilliseconds = (short) ((LongValue) memory.getWordMemoryValue(new X86MemoryOperand(
						DataType.INT32, t1 += 2))).getValue();
			}
			FILETIME lpFileTime = new FILETIME();

			BOOL ret = Kernel32DLL.INSTANCE.SystemTimeToFileTime(lpSystemTime, lpFileTime);
			register.mov("eax", new LongValue(ret.longValue()));

			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2), new LongValue(
					lpFileTime.dwLowDateTime));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 + 4), new LongValue(
					lpFileTime.dwHighDateTime));
		}
		return false;
	}

}
