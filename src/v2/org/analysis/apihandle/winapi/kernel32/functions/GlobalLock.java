/**
 * 
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.LPVOID;
import com.sun.jna.platform.win32.WinNT.HANDLE;

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
 * Locks a global memory object and returns a pointer to the first byte of the
 * object's memory block.
 * 
 * @param hMem
 *            A handle to the global memory object. This handle is returned by
 *            either the GlobalAlloc or GlobalReAlloc function.
 * 
 * @return If the function succeeds, the return value is a pointer to the first
 *         byte of the memory block.
 * 
 * @author Yen Nguyen
 *
 */
public class GlobalLock extends Kernel32API {

	public GlobalLock() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName,
			BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		// Memory memory = env.getMemory();
		Register register = env.getRegister();

		Value x1 = stack.pop();

		System.out.println("Argument:" + x1);

		if (x1 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();

			HANDLE hMem = new HANDLE(new Pointer(t1));
			LPVOID ret = Kernel32DLL.INSTANCE.GlobalLock(hMem);

			register.mov(
					"eax",
					new LongValue(ret == null ? 0 : Pointer.nativeValue(ret
							.toPointer())));
		}
		return false;
	}

}
