/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetDiskFreeSpaceEx.java
 * Created date: Mar 2, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.ptr.LongByReference;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * The GetDiskFreeSpaceEx function retrieves information about the amount of
 * space that is available on a disk volume, which is the total amount of space,
 * the total amount of free space, and the total amount of free space available
 * to the user that is associated with the calling thread.
 * 
 * @param lpDirectoryName
 *            A pointer to a null-terminated string that specifies a directory
 *            on a disk. If this parameter is NULL, the function uses the root
 *            of the current disk. If this parameter is a UNC name, it must
 *            include a trailing backslash, for example, \\MyServer\MyShare\.
 *            This parameter does not have to specify the root directory on a
 *            disk. The function accepts any directory on a disk.
 * 
 * @param lpFreeBytesAvailable
 *            A pointer to a variable that receives the total number of free
 *            bytes on a disk that are available to the user who is associated
 *            with the calling thread. This parameter can be NULL.
 * 
 * @param lpTotalNumberOfBytes
 *            A pointer to a variable that receives the total number of bytes on
 *            a disk that are available to the user who is associated with the
 *            calling thread. This parameter can be NULL.
 * 
 * @param lpTotalNumberOfFreeBytes
 *            A pointer to a variable that receives the total number of free
 *            bytes on a disk. This parameter can be NULL.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is 0 (zero). To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetDiskFreeSpaceEx extends Kernel32API {

	public GetDiskFreeSpaceEx() {

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
		Value x4 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4);

		if (x1 instanceof LongValue && x2 instanceof LongValue && x3 instanceof LongValue && x4 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			long t3 = ((LongValue) x3).getValue();
			long t4 = ((LongValue) x4).getValue();

			String lpDirectoryName = (t1 == 0) ? null : memory.getText(new X86MemoryOperand(DataType.INT32, t1));
			LongByReference lpFreeBytesAvailable = (t2 == 0) ? null : new LongByReference(
					((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2))).getValue());
			LongByReference lpTotalNumberOfBytes = (t3 == 0) ? null : new LongByReference(
					((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t3))).getValue());
			LongByReference lpTotalNumberOfFreeBytes = (t4 == 0) ? null : new LongByReference(
					((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t4))).getValue());

			boolean ret = Kernel32.INSTANCE.GetDiskFreeSpaceEx(lpDirectoryName, lpFreeBytesAvailable,
					lpTotalNumberOfBytes, lpTotalNumberOfFreeBytes);

			register.mov("eax", new LongValue((ret) ? 1 : 0));

			if (t2 != 0)
				memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2), new LongValue(
						lpFreeBytesAvailable.getValue()));
			if (t3 != 0)
				memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t3), new LongValue(
						lpTotalNumberOfBytes.getValue()));
			if (t4 != 0)
				memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t4), new LongValue(
						lpTotalNumberOfFreeBytes.getValue()));
		}
		return false;
	}

}
