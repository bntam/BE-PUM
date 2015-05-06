/**
 * 
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.LPVOID;
import com.sun.jna.platform.win32.WinDef.UINT_PTR;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Verifies that the calling process has write access to the specified range of
 * memory.
 * 
 * @param lp
 *            A pointer to the first byte of the memory block.
 * 
 * @param ucb
 *            The size of the memory block, in bytes. If this parameter is zero,
 *            the return value is zero.
 * @return If the calling process has write access to all bytes in the specified
 *         memory range, the return value is zero. If the calling process does
 *         not have write access to all bytes in the specified memory range, the
 *         return value is nonzero. If the application is run under a debugger
 *         and the process does not have write access to all bytes in the
 *         specified memory range, the function causes a first chance
 *         STATUS_ACCESS_VIOLATION exception. The debugger can be configured to
 *         break for this condition. After resuming process execution in the
 *         debugger, the function continues as usual and returns a nonzero value
 *         This behavior is by design and serves as a debugging aid.
 * 
 * @author Yen Nguyen
 *
 */
public class IsBadWritePtr extends Kernel32API {

	public IsBadWritePtr() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName,
			BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		//Memory memory = env.getMemory();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		Value x2 = stack.pop();
		System.out.println("Argument: " + x1 + ", " + x2);
		if (x1 instanceof LongValue && x2 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();

			LPVOID lp = new LPVOID(t1);
			UINT_PTR ucb = new UINT_PTR(t2);
			BOOL ret = Kernel32DLL.INSTANCE.IsBadWritePtr(lp, ucb);

			System.out.println("Return: " + ret.longValue());
			register.mov("eax", new LongValue(ret.longValue()));
		}
		return false;
	}

}
