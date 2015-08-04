/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: SetFileAttributes.java
 * Created date: Feb 10, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinDef.DWORD;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.system.Storage;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Sets the attributes for a file or directory.
 * 
 * @param lpFileName
 *            The name of the file whose attributes are to be set.
 * 
 *            The name is limited to MAX_PATH characters. To extend this limit
 *            to 32,767 wide characters, prepend "\\?\" to the path.
 * 
 * @param dwFileAttributes
 *            The file attributes to set for the file. This parameter can be one
 *            or more values, combined using the bitwise-OR operator. However,
 *            all other values override FILE_ATTRIBUTE_NORMAL.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class SetFileAttributes extends Kernel32API {

	/**
	 * 
	 */
	public SetFileAttributes() {
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		String fileName = memory.getText(new X86MemoryOperand(DataType.INT32, t1));
		fileName = Storage.getMappingPath(fileName);
		System.out.println("FileName:" + fileName + ", Attribute:" + t2);

		boolean ret = Kernel32.INSTANCE.SetFileAttributes(fileName, new DWORD(t2));

		register.mov("eax", new LongValue(ret ? 1 : 0));
		System.out.println("Return Value:" + (ret ? 1 : 0));
	}

}
