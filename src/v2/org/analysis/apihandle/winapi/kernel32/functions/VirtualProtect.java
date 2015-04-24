/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: VirtualProtect.java
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

import com.sun.jna.platform.win32.BaseTSD.SIZE_T;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.DWORDByReference;
import com.sun.jna.platform.win32.WinDef.LPVOID;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Changes the protection on a region of committed pages in the virtual address
 * space of the calling process.
 * 
 * @param lpAddress
 *            A pointer an address that describes the starting page of the
 *            region of pages whose access protection attributes are to be
 *            changed.
 * 
 * @param dwSize
 *            The size of the region whose access protection attributes are to
 *            be changed, in bytes. The region of affected pages includes all
 *            pages containing one or more bytes in the range from the lpAddress
 *            parameter to (lpAddress+dwSize). This means that a 2-byte range
 *            straddling a page boundary causes the protection attributes of
 *            both pages to be changed.
 * 
 * @param flNewProtect
 *            The memory protection option. This parameter can be one of the
 *            memory protection constants.
 * 
 * @param lpflOldProtect
 *            A pointer to a variable that receives the previous access
 *            protection value of the first page in the specified region of
 *            pages. If this parameter is NULL or does not point to a valid
 *            variable, the function fails.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class VirtualProtect extends Kernel32API {

	public VirtualProtect() {
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

			LPVOID lpAddress = new LPVOID(t1);
			SIZE_T dwSize = new SIZE_T(t2);
			DWORD flNewProtect = new DWORD(t3);
			DWORDByReference lpflOldProtect = new DWORDByReference();
			BOOL ret = Kernel32DLL.INSTANCE.VirtualProtect(lpAddress, dwSize, flNewProtect, lpflOldProtect);

			register.mov("eax", new LongValue(ret.longValue()));

			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t4), new LongValue(lpflOldProtect
					.getValue().longValue()));
		}
		return false;
	}

}