/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: WideCharToMultiByte.java
 * Created date: Mar 28, 2015
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
import com.sun.jna.platform.win32.WinDef.BOOLByReference;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Maps a UTF-16 (wide character) string to a new character string. The new
 * character string is not necessarily from a multibyte character set.
 * 
 * @param CodePage
 *            Code page to use in performing the conversion. This parameter can
 *            be set to the value of any code page that is installed or
 *            available in the operating system. For a list of code pages, see
 *            Code Page Identifiers. Your application can also specify one of
 *            the values shown in the following table.
 * 
 * @param dwFlags
 *            Flags indicating the conversion type. The application can specify
 *            a combination of the following values. The function performs more
 *            quickly when none of these flags is set. The application should
 *            specify WC_NO_BEST_FIT_CHARS and WC_COMPOSITECHECK with the
 *            specific value WC_DEFAULTCHAR to retrieve all possible conversion
 *            results. If all three values are not provided, some results will
 *            be missing.
 * 
 * @param lpWideCharStr
 *            Pointer to the Unicode string to convert.
 * 
 * @param cchWideChar
 *            Size, in characters, of the string indicated by lpWideCharStr.
 *            Alternatively, this parameter can be set set to -1 if the string
 *            is null-terminated. If cchWideChar is set to 0, the function
 *            fails.
 * 
 * @param lpMultiByteStr
 *            Pointer to a buffer that receives the converted string.
 * 
 * @param cbMultiByte
 *            Size, in bytes, of the buffer indicated by lpMultiByteStr. If this
 *            parameter is set to 0, the function returns the required buffer
 *            size for lpMultiByteStr and makes no use of the output parameter
 *            itself.
 * 
 * @param lpDefaultChar
 *            Pointer to the character to use if a character cannot be
 *            represented in the specified code page. The application sets this
 *            parameter to NULL if the function is to use a system default
 *            value. To obtain the system default character, the application can
 *            call the GetCPInfo or GetCPInfoEx function.
 * 
 * @param lpUsedDefaultChar
 *            Pointer to a flag that indicates if the function has used a
 *            default character in the conversion. The flag is set to TRUE if
 *            one or more characters in the source string cannot be represented
 *            in the specified code page. Otherwise, the flag is set to FALSE.
 *            This parameter can be set to NULL.
 * 
 * @return Returns the number of bytes written to the buffer pointed to by
 *         lpMultiByteStr if successful. If the function succeeds and
 *         cbMultiByte is 0, the return value is the required size, in bytes,
 *         for the buffer indicated by lpMultiByteStr. If the input byte/char
 *         sequences are invalid, returns U+FFFD for UTF encodings. The function
 *         returns 0 if it does not succeed.
 * 
 * @author Yen Nguyen
 *
 */
public class WideCharToMultiByte extends Kernel32API {

	public WideCharToMultiByte() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		Value x2 = stack.pop();
		Value x3 = stack.pop();
		Value x4 = stack.pop();
		Value x5 = stack.pop();
		Value x6 = stack.pop();
		Value x7 = stack.pop();
		Value x8 = stack.pop();

		System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5 + " " + x6 + " " + x7 + " "
				+ x8);

		if (x1 instanceof LongValue && x2 instanceof LongValue && x3 instanceof LongValue && x4 instanceof LongValue
				&& x5 instanceof LongValue && x6 instanceof LongValue && x7 instanceof LongValue
				&& x8 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			long t3 = ((LongValue) x3).getValue();
			long t4 = ((LongValue) x4).getValue();
			long t5 = ((LongValue) x5).getValue();
			long t6 = ((LongValue) x6).getValue();
			long t7 = ((LongValue) x7).getValue();
			long t8 = ((LongValue) x8).getValue();

			UINT CodePage = new UINT(t1);
			DWORD dwFlags = new DWORD(t2);
			WString lpWideCharStr = new WString(memory.getText(new X86MemoryOperand(DataType.INT32, t3)));
			int cchWideChar = (int) t4;
			char[] lpMultiByteStr = (t6 > 0) ? new char[(int) t6] : null;
			int cbMultiByte = (int) t6;
			String lpDefaultChar = (t7 != 0) ? memory.getText(new X86MemoryOperand(DataType.INT32, t7)) : null;
			BOOLByReference lpUsedDefaultChar = (t8 != 0) ? new BOOLByReference() : null;
			int ret = Kernel32DLL.INSTANCE.WideCharToMultiByte(CodePage, dwFlags, lpWideCharStr, cchWideChar,
					lpMultiByteStr, cbMultiByte, lpDefaultChar, lpUsedDefaultChar);

			register.mov("eax", new LongValue(ret));

			if (lpMultiByteStr != null)
				memory.setText(new X86MemoryOperand(DataType.INT32, t5), new String(lpMultiByteStr));
			if (lpUsedDefaultChar != null)
				memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t8), new LongValue(
						lpUsedDefaultChar.getValue().longValue()));
		}
		return false;
	}

}
