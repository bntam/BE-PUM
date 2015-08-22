/**
 * 
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Creates a name for a temporary file. If a unique file name is generated, an
 * empty file is created and the handle to it is released; otherwise, only a
 * file name is generated.
 * 
 * @param lpPathName
 *            The directory path for the file name. Applications typically
 *            specify a period (.) for the current directory or the result of
 *            the GetTempPath function. The string cannot be longer than
 *            MAX_PATH�14 characters or GetTempFileName will fail. If this
 *            parameter is NULL, the function fails.
 * 
 * @param lpPrefixString
 *            The null-terminated prefix string. The function uses up to the
 *            first three characters of this string as the prefix of the file
 *            name. This string must consist of characters in the OEM-defined
 *            character set.
 * 
 * @param uUnique
 *            An unsigned integer to be used in creating the temporary file
 *            name. For more information, see Remarks. If uUnique is zero, the
 *            function attempts to form a unique file name using the current
 *            system time. If the file already exists, the number is increased
 *            by one and the functions tests if this file already exists. This
 *            continues until a unique filename is found; the function creates a
 *            file by that name and closes it. Note that the function does not
 *            attempt to verify the uniqueness of the file name when uUnique is
 *            nonzero.
 * 
 * @param lpTempFileName
 *            A pointer to the buffer that receives the temporary file name.
 *            This buffer should be MAX_PATH characters to accommodate the path
 *            plus the terminating null character.
 * 
 * @return If the function succeeds, the return value specifies the unique
 *         numeric value used in the temporary file name. If the uUnique
 *         parameter is nonzero, the return value specifies that same number.
 * 
 * @author Yen Nguyen
 *
 */
public class GetTempFileName extends Kernel32API {

	public GetTempFileName() {
		super();
		NUM_OF_PARMS = 4;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);

		WString lpPathName = new WString(memory.getText(new X86MemoryOperand(DataType.INT32, t1)));
		WString lpPrefixString = new WString(memory.getText(new X86MemoryOperand(DataType.INT32, t2)));
		UINT uUnique = new UINT(t3);
		char[] lpTempFileName = new char[260]; // #define MAX_PATH 260
		UINT ret = Kernel32DLL.INSTANCE.GetTempFileName(lpPathName, lpPrefixString, uUnique, lpTempFileName);

		register.mov("eax", new LongValue(ret.longValue()));

		memory.setText(new X86MemoryOperand(DataType.INT32, t4), new String(lpTempFileName));
	}

}
