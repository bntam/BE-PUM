/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: RtlZeroMemory.java
 * Created date: Mar 27, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.SIZE_T;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * The RtlZeroMemory routine fills a block of memory with zeros, given a pointer
 * to the block and the length, in bytes, to be filled.
 * 
 * @param Destination
 *            A pointer to the memory block to be filled with zeros.
 * 
 * @param Length
 *            The number of bytes to fill with zeros.
 * 
 * @author Yen Nguyen
 *
 */
public class RtlZeroMemory extends Kernel32API {

	public RtlZeroMemory() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		Value x2 = stack.pop();
		System.out.print("Argument:" + x1 + " " + x2);
		if (x1 instanceof LongValue && x2 instanceof LongValue) {

			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();

			Pointer Destination = new Pointer(t1);
			SIZE_T Length = new SIZE_T(t2);
			Kernel32DLL.INSTANCE.RtlZeroMemory(Destination, Length);

			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1),
					new LongValue(Pointer.nativeValue(Destination)));
		}
		return false;
	}

}