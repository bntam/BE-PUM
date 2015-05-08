/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: VirtualQuery.java
 * Created date: Mar 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.SIZE_T;
import com.sun.jna.platform.win32.WinDef.LPVOID;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.apihandle.winapi.structures.WinNT.MEMORY_BASIC_INFORMATION;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Retrieves information about a range of pages in the virtual address space of
 * the calling process.
 * 
 * @param lpAddress
 *            A pointer to the base address of the region of pages to be
 *            queried. This value is rounded down to the next page boundary. To
 *            determine the size of a page on the host computer, use the
 *            GetSystemInfo function.
 * 
 * @param lpBuffer
 *            A pointer to a MEMORY_BASIC_INFORMATION structure in which
 *            information about the specified page range is returned.
 * 
 * @param dwLength
 *            The size of the buffer pointed to by the lpBuffer parameter, in
 *            bytes.
 * 
 * @return The return value is the actual number of bytes returned in the
 *         information buffer. If the function fails, the return value is zero.
 *         To get extended error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class VirtualQuery extends Kernel32API {

	public VirtualQuery() {
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

		if (x1 instanceof LongValue && x2 instanceof LongValue && x3 instanceof LongValue) {

			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			long t3 = ((LongValue) x3).getValue();

			LPVOID lpAddress = (t1 != 0) ? new LPVOID(t1) : null;
			MEMORY_BASIC_INFORMATION lpBuffer = new MEMORY_BASIC_INFORMATION();
			SIZE_T dwLength = new SIZE_T(t3);
			SIZE_T ret = Kernel32DLL.INSTANCE.VirtualQuery(lpAddress, lpBuffer, dwLength);

			register.mov("eax", new LongValue(ret.longValue()));

			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2),
					new LongValue(Pointer.nativeValue(lpBuffer.BaseAddress.getPointer())));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 += 4),
					new LongValue(Pointer.nativeValue(lpBuffer.AllocationBase.getPointer())));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 += 4), new LongValue(
					lpBuffer.AllocationProtect.longValue()));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 += 4), new LongValue(
					lpBuffer.RegionSize.longValue()));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 += 4),
					new LongValue(lpBuffer.State.longValue()));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 += 4), new LongValue(
					lpBuffer.Protect.longValue()));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2 += 4),
					new LongValue(lpBuffer.Type.longValue()));
		}
		return false;
	}

}
