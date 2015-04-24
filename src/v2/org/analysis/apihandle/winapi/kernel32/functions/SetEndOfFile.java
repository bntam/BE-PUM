/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: SetEndOfFile.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
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
 * Sets the physical file size for the specified file to the current
 * position of the file pointer. The physical file size is also referred to
 * as the end of the file. The SetEndOfFile function can be used to truncate
 * or extend a file. To set the logical end of a file, use the
 * SetFileValidData function.
 * 
 * @param hFile
 *            : A handle to the file to be extended or truncated.
 *            
 * @author Yen Nguyen
 *
 */
public class SetEndOfFile extends Kernel32API {

	/**
	 * 
	 */
	public SetEndOfFile() {
		
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Register register = env.getRegister();
		
		// HANDLE hFile handle of file whose EOF is to be set
		Value x1 = stack.pop();

		System.out.println("Argument:" + x1);

		if (x1 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();

			System.out.println("Handle of file:" + t1);
			BOOL ret = Kernel32DLL.INSTANCE.SetEndOfFile(new HANDLE(new Pointer(t1)));
			register.mov("eax", new LongValue(ret.longValue()));
		}
		return false;
	}

}