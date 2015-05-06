/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: VirtualAlloc.java
 * Created date: Feb 9, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.SIZE_T;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.LPVOID;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Reserves or commits a region of pages in the virtual address space of the
 * calling process. Memory allocated by this function is automatically
 * initialized to zero, unless MEM_RESET is specified.
 * 
 * @param lpAddress
 *            : The starting address of the region to allocate. If the memory is
 *            being reserved, the specified address is rounded down to the
 *            nearest multiple of the allocation granularity. If the memory is
 *            already reserved and is being committed, the address is rounded
 *            down to the next page boundary. To determine the size of a page
 *            and the allocation granularity on the host computer, use the
 *            GetSystemInfo function. If this parameter is NULL, the system
 *            determines where to allocate the region.
 * 
 * @param dwSize
 *            : The size of the region, in bytes. If the lpAddress parameter is
 *            NULL, this value is rounded up to the next page boundary.
 *            Otherwise, the allocated pages include all pages containing one or
 *            more bytes in the range from lpAddress to lpAddress+dwSize. This
 *            means that a 2-byte range straddling a page boundary causes both
 *            pages to be included in the allocated region.
 * 
 * @param flAllocationType
 *            : The type of memory allocation. This parameter must contain one
 *            of the following values.
 * 
 * @param flProtect
 *            : The memory protection for the region of pages to be allocated.
 *            If the pages are being committed, you can specify any one of the
 *            memory protection constants.
 * 
 * @return If the function succeeds, the return value is the base address of the
 *         allocated region of pages.
 * 
 * @author Yen Nguyen
 *
 */
public class VirtualAlloc extends Kernel32API {

	public VirtualAlloc() {
		
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Register register = env.getRegister();

		/*
		 * LPVOID lpAddress, address of region to reserve or commit DWORD
		 * dwSize, size of region DWORD flAllocationType, type of allocation
		 * DWORD flProtect type of access protection
		 */
		Value x1 = stack.pop();
		Value x2 = stack.pop();
		Value x3 = stack.pop();
		Value x4 = stack.pop();
		// Exp x5 = symbolStack.pop();
		System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4);
		if (x1 instanceof LongValue && x2 instanceof LongValue && x3 instanceof LongValue && x4 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			long t3 = ((LongValue) x3).getValue();
			long t4 = ((LongValue) x4).getValue();

			System.out.println("Address:" + t1 + ", Size:" + t2 + ", Allocation Type:" + t3 + ", Protection Type:" + t4);

			LPVOID lpAddress = (t1 != 0) ? new LPVOID(t1) : null;
			SIZE_T dwSize = new SIZE_T(t2);
			DWORD flAllocationType = new DWORD(t3);
			DWORD flProtect = new DWORD(t4);
			LPVOID ret = Kernel32DLL.INSTANCE.VirtualAlloc(lpAddress, dwSize, flAllocationType, flProtect);
			
			register.mov("eax", new LongValue(Pointer.nativeValue(ret.toPointer())));
		}
		return false;
	}

}
