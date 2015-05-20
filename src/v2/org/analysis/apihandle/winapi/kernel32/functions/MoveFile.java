/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: MoveFile.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.Kernel32;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.system.Storage;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Moves an existing file or a directory, including its children.
 * 
 * @param lpExistingFileName
 *            The current name of the file or directory on the local computer.
 * 
 *            The name is limited to MAX_PATH characters. To extend this limit
 *            to 32,767 wide characters, prepend "\\?\" to the path. For more
 *            information, see Naming a File.
 * 
 * @param lpNewFileName
 *            The new name for the file or directory. The new name must not
 *            already exist. A new file may be on a different file system or
 *            drive. A new directory must be on the same drive.
 * 
 *            The name is limited to MAX_PATH characters. To extend this limit
 *            to 32,767 wide characters, prepend "\\?\" to the path. For more
 *            information, see Naming a File.
 * 
 * @return true, if successful If the function succeeds, the return value is
 *         nonzero.
 * 
 *         If the function fails, the return value is zero. To get extended
 *         error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class MoveFile extends Kernel32API {

	/**
	 * 
	 */
	public MoveFile() {

	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();

		// LPCTSTR lpExistingFileName, address of name of the existing file
		// LPCTSTR lpNewFileName address of new name for the file
		Value x1 = stack.pop();
		Value x2 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2 + " ");

		if (x1 instanceof LongValue && x2 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();

			String fileNameOld = memory.getText(new X86MemoryOperand(DataType.INT32, t1));
			fileNameOld = Storage.getMappingPath(fileNameOld);
			String fileNameNew = memory.getText(new X86MemoryOperand(DataType.INT32, t2));
			fileNameNew = Storage.getMappingPath(fileNameNew);
			System.out.println("Old File:" + fileNameOld + ", New File:" + fileNameNew);

			boolean ret = Kernel32.INSTANCE.MoveFile(fileNameOld, fileNameNew);
			register.mov("eax", new LongValue(ret ? 1 : 0));

		}
		return false;
	}

}
