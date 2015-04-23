/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: SetCurrentDirectory.java
 * Created date: Feb 10, 2015
 * Description:
 */
package v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions;

import v2.org.analysis.apihandle.be_pum.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.be_pum.winapi.kernel32.Kernel32DLL;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef.BOOL;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.system.Storage;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Changes the current directory for the current process.
 * 
 * @param lpPathName
 *            : The path to the new current directory. This parameter may
 *            specify a relative path or a full path. In either case, the
 *            full path of the specified directory is calculated and stored
 *            as the current directory. For more information, see File
 *            Names, Paths, and Namespaces.
 *            
 * @return If the function succeeds, the return value is nonzero.
 * 
 * @author Yen Nguyen
 *
 */
public class SetCurrentDirectory extends Kernel32API {

	/**
	 * 
	 */
	public SetCurrentDirectory() {
		
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();
		
		Value x1 = stack.pop();

		System.out.println("Argument:" + x1);
		if (x1 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();

			String path = memory.getText(new X86MemoryOperand(DataType.INT32, t1));
			path = path.replace('/', '\\');
			Storage.CurrentDirectory = path;
			System.out.println("Path File:" + path);

			BOOL ret = Kernel32DLL.INSTANCE.SetCurrentDirectory(new WString(path));

			System.out.println("Return: " + ret);
			register.mov("eax", new LongValue(ret.longValue()));
		}
		return false;
	}

}
