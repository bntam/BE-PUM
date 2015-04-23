/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetDriveType.java
 * Created date: Mar 2, 2015
 * Description:
 */
package v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions;

import com.sun.jna.platform.win32.Kernel32;
import v2.org.analysis.apihandle.be_pum.winapi.kernel32.Kernel32API;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * The GetDriveType function determines whether a disk drive is a removable,
 * fixed, CD-ROM, RAM disk, or network drive.
 * 
 * @param lpRootPathName
 *            Pointer to a null-terminated string that specifies the root
 *            directory of the disk to return information about. A trailing
 *            backslash is required. If this parameter is NULL, the function
 *            uses the root of the current directory.
 * 
 * @return The return value specifies the type of drive.
 * 
 * @author Yen Nguyen
 *
 */
public class GetDriveType extends Kernel32API {

	public GetDriveType() {
		
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

			String lpRootPathName = memory.getText(new X86MemoryOperand(DataType.INT32, t1));
			int ret = Kernel32.INSTANCE.GetDriveType(lpRootPathName);
			register.mov("eax", new LongValue(ret));
		}
		return false;
	}

}
