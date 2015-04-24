/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: CopyFile.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.SIZE_T;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.LPVOID;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Flushes the instruction cache for the specified process.
 * 
 * @param hProcess
 *            A handle to a process whose instruction cache is to be flushed.
 * 
 * @param lpBaseAddress
 *            A pointer to the base of the region to be flushed. This parameter
 *            can be NULL.
 * 
 * @param dwSize
 *            The size of the region to be flushed if the lpBaseAddress
 *            parameter is not NULL, in bytes.
 * 
 * @return If the function succeeds, the return value is nonzero.
 * 
 * @author Yen Nguyen
 *
 */
public class FlushInstructionCache extends Kernel32API {

	public FlushInstructionCache() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		Value x2 = stack.pop();
		Value x3 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2 + " " + x3);

		if (x1 instanceof LongValue && x2 instanceof LongValue && x3 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			long t3 = ((LongValue) x3).getValue();

			HANDLE hProcess = new HANDLE(new Pointer(t1));
			LPVOID lpBaseAddress = new LPVOID(t2);
			SIZE_T dwSize = new SIZE_T(t3);
			BOOL ret = Kernel32DLL.INSTANCE.FlushInstructionCache(hProcess, lpBaseAddress, dwSize);

			register.mov("eax", new LongValue(ret.longValue()));
		}
		return false;
	}
}
