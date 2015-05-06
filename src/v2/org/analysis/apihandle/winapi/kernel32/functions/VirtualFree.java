/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: VirtualFree.java
 * Created date: Feb 9, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.BaseTSD.SIZE_T;
import com.sun.jna.platform.win32.WinDef.BOOL;
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
 * Releases, decommits, or releases and decommits a region of pages within the
 * virtual address space of the calling process.
 * 
 * @param lpAddress
 *            : A pointer to the base address of the region of pages to be
 *            freed.
 * 
 * @param dwSize
 *            : The size of the region of memory to be freed, in bytes.
 * 
 * @param dwFreeType
 *            : The type of free operation. This parameter can be one of the
 *            following values.
 * 
 * @return If the function succeeds, the return value is nonzero.
 * 
 * @author Yen Nguyen
 *
 */
public class VirtualFree extends Kernel32API {

	/**
	 * 
	 */
	public VirtualFree() {
		
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Register register = env.getRegister();

		/*
		 * LPVOID lpAddress, // address of region of committed pages DWORD
		 * dwSize, // size of region DWORD dwFreeType // type of free operation
		 */
		Value x1 = stack.pop();
		Value x2 = stack.pop();
		Value x3 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2 + " " + x3);

		if (x1 instanceof LongValue && x2 instanceof LongValue && x3 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			long t3 = ((LongValue) x3).getValue();

			// String fileName = symbolValueMemoryOperand.getText(new
			// X86MemoryOperand(DataType.INT32, t1));
			System.out.println("Base Address:" + t1 + ", Size:" + t2 + ", Free Type:" + t3);
			
			BOOL ret = Kernel32DLL.INSTANCE.VirtualFree(new LPVOID(t1), new SIZE_T(t2), new DWORD(t3));

			register.mov("eax", new LongValue(ret.longValue()));
		}
		return false;
	}

}
