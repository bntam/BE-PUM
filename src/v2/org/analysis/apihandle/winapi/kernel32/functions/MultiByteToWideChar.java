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
 * Maps a character string to a UTF-16 (wide character) string. The character
 * string is not necessarily from a multibyte character set.
 * 
 * @param CodePage
 *            Code page to use in performing the conversion. This parameter can
 *            be set to the value of any code page that is installed or
 *            available in the operating system. For a list of code pages, see
 *            Code Page Identifiers.
 * 
 * @param dwFlags
 *            Flags indicating the conversion type. The application can specify
 *            a combination of the following values, with MB_PRECOMPOSED being
 *            the default. MB_PRECOMPOSED and MB_COMPOSITE are mutually
 *            exclusive. MB_USEGLYPHCHARS and MB_ERR_INVALID_CHARS can be set
 *            regardless of the state of the other flags.
 * 
 * @param lpMultiByteStr
 *            Pointer to the character string to convert.
 * 
 * @param cbMultiByte
 *            Size, in bytes, of the string indicated by the lpMultiByteStr
 *            parameter. Alternatively, this parameter can be set to -1 if the
 *            string is null-terminated. Note that, if cbMultiByte is 0, the
 *            function fails.
 * 
 * @param lpWideCharStr
 *            Pointer to a buffer that receives the converted string.
 * 
 * @param cchWideChar
 *            Size, in characters, of the buffer indicated by lpWideCharStr. If
 *            this value is 0, the function returns the required buffer size, in
 *            characters, including any terminating null character, and makes no
 *            use of the lpWideCharStr buffer.
 * 
 * @return Returns the number of characters written to the buffer indicated by
 *         lpWideCharStr if successful. If the function succeeds and cchWideChar
 *         is 0, the return value is the required size, in characters, for the
 *         buffer indicated by lpWideCharStr. If the input byte/char sequences
 *         are invalid, returns U+FFFD for UTF encodings. The function returns 0
 *         if it does not succeed.
 * 
 * @author Yen Nguyen
 *
 */
public class MultiByteToWideChar extends Kernel32API {

	public MultiByteToWideChar() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName,
			BPState curState, Instruction inst) {
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

		System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4
				+ " " + x5 + " " + x6);

		if (x1 instanceof LongValue && x2 instanceof LongValue
				&& x3 instanceof LongValue && x4 instanceof LongValue
				&& x5 instanceof LongValue && x6 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			long t3 = ((LongValue) x3).getValue();
			long t4 = ((LongValue) x4).getValue();
			long t5 = ((LongValue) x5).getValue();
			long t6 = ((LongValue) x6).getValue();

			UINT CodePage = new UINT(t1);
			DWORD dwFlags = new DWORD(t2);
			String lpMultiByteStr = memory.getText(new X86MemoryOperand(
					DataType.INT32, t3));
			int cbMultiByte = (int) t4;
			char[] lpWideCharStr = (t5 != 0L && t6 > 0) ? new char[(int) t6]
					: null;
			int cchWideChar = (int) t6;

			int ret = Kernel32DLL.INSTANCE.MultiByteToWideChar(CodePage,
					dwFlags, lpMultiByteStr, cbMultiByte, lpWideCharStr,
					cchWideChar);

			register.mov("eax", new LongValue(ret));

			if (lpWideCharStr != null)
				memory.setText(new X86MemoryOperand(DataType.INT32, t5),
						new String(lpWideCharStr));
		}
		return false;
	}

}
