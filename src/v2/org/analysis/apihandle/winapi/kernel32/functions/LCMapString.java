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
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.LCID;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * For a locale specified by identifier, maps one input character string to
 * another using a specified transformation, or generates a sort key for the
 * input string.
 * 
 * @param Locale
 *            Locale identifier that specifies the locale. You can use the
 *            MAKELCID macro to create a locale identifier or use one of the
 *            following predefined values.
 * 
 * @param dwMapFlags
 *            Flags specifying the type of transformation to use during string
 *            mapping or the type of sort key to generate. For detailed
 *            definitions, see the dwMapFlags parameter of LCMapStringEx.
 * 
 * @param lpSrcStr
 *            Pointer to a source string that the function maps or uses for sort
 *            key generation. This string cannot have a size of 0.
 * 
 * @param cchSrc
 *            ize, in characters, of the source string indicated by lpSrcStr.
 *            The size of the source string can include the terminating null
 *            character, but does not have to. If the terminating null character
 *            is included, the mapping behavior of the function is not greatly
 *            affected because the terminating null character is considered to
 *            be unsortable and always maps to itself. The application can set
 *            the parameter to any negative value to specify that the source
 *            string is null-terminated. In this case, if LCMapString is being
 *            used in its string-mapping mode, the function calculates the
 *            string length itself, and null-terminates the mapped string
 *            indicated by lpDestStr. The application cannot set this parameter
 *            to 0.
 * 
 * @param lpDestStr
 *            Pointer to a buffer in which this function retrieves the mapped
 *            string or a sort key. When the application uses this function to
 *            generate a sort key, the destination string can contain an odd
 *            number of bytes. The LCMAP_BYTEREV flag only reverses an even
 *            number of bytes. The last byte (odd-positioned) in the sort key is
 *            not reversed.
 * 
 * @param cchDest
 *            Size, in characters, of the destination string indicated by
 *            lpDestStr. If the application is using the function for string
 *            mapping, it supplies a character count for this parameter. If
 *            space for a terminating null character is included in cchSrc,
 *            cchDest must also include space for a terminating null character.
 * 
 * @return Returns the number of characters or bytes in the translated string or
 *         sort key, including a terminating null character, if successful. If
 *         the function succeeds and the value of cchDest is 0, the return value
 *         is the size of the buffer required to hold the translated string or
 *         sort key, including a terminating null character. This function
 *         returns 0 if it does not succeed. To get extended error information,
 *         the application can call GetLastError, which can return one of the
 *         following error codes:
 * 
 * @author Yen Nguyen
 *
 */
public class LCMapString extends Kernel32API {

	public LCMapString() {
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

			LCID Locale = new LCID(t1);
			DWORD dwMapFlags = new DWORD(t2);
			WString lpSrcStr = new WString(memory.getText(new X86MemoryOperand(
					DataType.INT32, t3)));
			int cchSrc = (int) t4;
			char[] lpDestStr = (t5 != 0 && t6 != 0) ? new char[(int) t6] : null;
			int cchDest = (int) t6;
			int ret = Kernel32DLL.INSTANCE.LCMapString(Locale, dwMapFlags,
					lpSrcStr, cchSrc, lpDestStr, cchDest);

			register.mov("eax", new LongValue(ret));

			if (lpDestStr != null)
				memory.setText(new X86MemoryOperand(DataType.INT32, t5),
						new String(lpDestStr));
		}
		return false;
	}

}