/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: TlsSetValue.java
 * Created date: Mar 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.LPVOID;
import v2.org.analysis.apihandle.be_pum.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.be_pum.winapi.kernel32.Kernel32DLL;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Stores a value in the calling thread's thread local storage (TLS) slot for
 * the specified TLS index. Each thread of a process has its own slot for each
 * TLS index.
 * 
 * @param dwTlsIndex
 *            The TLS index that was allocated by the TlsAlloc function.
 * 
 * @param lpTlsValue
 *            The value to be stored in the calling thread's TLS slot for the
 *            index.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class TlsSetValue extends Kernel32API {

	public TlsSetValue() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		// Memory memory = env.getMemory();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		Value x2 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2);

		if (x1 instanceof LongValue && x2 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();

			DWORD dwTlsIndex = new DWORD(t1);
			LPVOID lpTlsValue = (t2 != 0) ? new LPVOID(t2) : null;
			BOOL ret = Kernel32DLL.INSTANCE.TlsSetValue(dwTlsIndex, lpTlsValue);

			register.mov("eax", new LongValue(ret.longValue()));
		}
		return false;
	}

}
