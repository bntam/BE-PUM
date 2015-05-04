/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: CreateFile.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

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
 * Creates a new directory. If the underlying file system supports security on
 * files and directories, the function applies a specified security descriptor
 * to the new directory.
 * 
 * @param lpPathName
 *            The path of the directory to be created.
 * 
 * @param lpSecurityAttributes
 *            A pointer to a SECURITY_ATTRIBUTES structure. The
 *            lpSecurityDescriptor member of the structure specifies a security
 *            descriptor for the new directory. If lpSecurityAttributes is NULL,
 *            the directory gets a default security descriptor. The ACLs in the
 *            default security descriptor for a directory are inherited from its
 *            parent directory.
 * 
 *            The target file system must support security on files and
 *            directories for this parameter to have an effect. (This is
 *            indicated when GetVolumeInformation returns FS_PERSISTENT_ACLS.)
 * 
 * @return If the function succeeds, the return value is nonzero.
 * 
 *         If the function fails, the return value is zero. To get extended
 *         error information, call GetLastError. Possible errors include the
 *         following.
 * 
 * @author Yen Nguyen
 *
 */
public class CreateDirectory extends Kernel32API {

	public CreateDirectory() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		Value x2 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2);

		if (x1 instanceof LongValue && x2 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();

			String lpPathName = memory.getText(new X86MemoryOperand(DataType.INT32, t1));
			lpPathName = Storage.getMappingPath(lpPathName);
			System.out.println("PathName:" + ", pSecurity:" + t2);

			BOOL ret = Kernel32DLL.INSTANCE.CreateDirectory(lpPathName, null);
			register.mov("eax", new LongValue(ret.longValue()));
			System.out.println("Return value:" + ret.longValue());
		}

		return false;
	}
}
