/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: SetHandleCount.java
 * Created date: Feb 9, 2015
 * Description:
 */
package v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions;

import v2.org.analysis.apihandle.be_pum.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.be_pum.winapi.kernel32.Kernel32DLL;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * The SetHandleCount function sets the number of file handles available to
 * a process. This function has no effect under Windows NT and Windows 95,
 * because there is no explicit file handle limit for applications on these
 * platforms. Under Win32s, there are only 20 file handles available to a
 * process by default; however you could use SetHandleCount to allow a
 * process to use up to 255 file handles.
 * 
 * @param uNumber
 *            : Specifies the number of file handles needed by the
 *            application.
 *            
 * @return Under Windows NT and Windows 95, this function simply returns the
 *         value specified in the uNumber parameter. Under Win32s, the
 *         return value specifies the number of file handles actually
 *         available to the application. It may be fewer than the number
 *         specified by the uNumber parameter.
 *         
 * @author Yen Nguyen
 *
 */
public class SetHandleCount extends Kernel32API {

	/**
	 * 
	 */
	public SetHandleCount() {
		
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Register register = env.getRegister();

		// UINT uNumber number of file handles needed
		Value x1 = stack.pop();

		System.out.println("Argument:" + x1 + " ");

		if (x1 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();

			UINT ret = Kernel32DLL.INSTANCE.SetHandleCount(new UINT(t1));

			register.mov("eax", new LongValue(ret.longValue()));
		}
		return false;
	}

}
