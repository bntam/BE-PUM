/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: DeleteCriticalSection.java
 * Created date: Mar 8, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.apihandle.winapi.structures.WinNT.PRTL_CRITICAL_SECTION;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.WinDef.LONG;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Releases all resources used by an unowned critical section object.
 * 
 * @param lpCriticalSection
 *            [in, out] A pointer to the critical section object. The object
 *            must have been previously initialized with the
 *            InitializeCriticalSection function.
 * 
 * @author Yen Nguyen
 *
 */
public class DeleteCriticalSection extends Kernel32API {

	public DeleteCriticalSection() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();

		Value x1 = stack.pop();

		System.out.println("Argument:" + x1);
		if (x1 instanceof LongValue) {
			long x = ((LongValue) x1).getValue();

			PRTL_CRITICAL_SECTION lpCriticalSection = new PRTL_CRITICAL_SECTION();
			lpCriticalSection.LockCount = new LONG(((LongValue) memory.getDoubleWordMemoryValue(x)).getValue());
			lpCriticalSection.RecursionCount = new LONG(
					((LongValue) memory.getDoubleWordMemoryValue(x += 4)).getValue());
			lpCriticalSection.OwningThread = new HANDLE(new Pointer(
					((LongValue) memory.getDoubleWordMemoryValue(x += 4)).getValue()));
			lpCriticalSection.LockSemaphore = new HANDLE(new Pointer(
					((LongValue) memory.getDoubleWordMemoryValue(x += 4)).getValue()));
			lpCriticalSection.SpinCount = new ULONG_PTR(
					((LongValue) memory.getDoubleWordMemoryValue(x += 4)).getValue());

			Kernel32DLL.INSTANCE.DeleteCriticalSection(lpCriticalSection);

			x = ((LongValue) x1).getValue();
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, x), new LongValue(
					lpCriticalSection.LockCount.longValue()));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, x += 4), new LongValue(
					lpCriticalSection.RecursionCount.longValue()));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, x += 4),
					new LongValue(Pointer.nativeValue(lpCriticalSection.OwningThread.getPointer())));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, x += 4),
					new LongValue(Pointer.nativeValue(lpCriticalSection.LockSemaphore.getPointer())));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, x += 4), new LongValue(
					lpCriticalSection.SpinCount.longValue()));
		}
		return false;
	}

}
