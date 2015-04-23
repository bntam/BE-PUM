/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: FindFirstFile.java
 * Created date: Feb 9, 2015
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
import com.sun.jna.platform.win32.WinDef.LONG;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Compares two file times.
 * 
 * @param lpFileTime1
 *            [in] A pointer to a FILETIME structure that specifies the first
 *            file time.
 * 
 * @param lpFileTime2
 *            [in] A pointer to a FILETIME structure that specifies the second
 *            file time.
 * 
 * @return The return value is one of the following values.
 * 
 * @author Yen Nguyen
 *
 */
public class CompareFileTime extends Kernel32API {

	public CompareFileTime() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		Value x2 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2 + " ");

		if (x1 instanceof LongValue && x2 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();

			FILETIME lpFileTime1 = new FILETIME();
			FILETIME lpFileTime2 = new FILETIME();

			lpFileTime1.dwLowDateTime = (int) ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(
					DataType.INT32, t1))).getValue();
			lpFileTime1.dwHighDateTime = (int) ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(
					DataType.INT32, t1 + 4))).getValue();
			lpFileTime2.dwLowDateTime = (int) ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(
					DataType.INT32, t2))).getValue();
			lpFileTime2.dwHighDateTime = (int) ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(
					DataType.INT32, t2 + 4))).getValue();

			LONG ret = Kernel32DLL.INSTANCE.CompareFileTime(lpFileTime1, lpFileTime2);

			register.mov("eax", new LongValue(ret.longValue()));
		}

		return false;
	}

}
