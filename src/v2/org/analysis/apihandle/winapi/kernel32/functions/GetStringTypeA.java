/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetStringTypeA.java
 * Created date: Mar 16, 2015
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
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.LCID;
import com.sun.jna.platform.win32.WinDef.WORDByReference;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Deprecated. Retrieves character type information for the characters in the
 * specified source string. For each character in the string, the function sets
 * one or more bits in the corresponding 16-bit element of the output array.
 * Each bit identifies a given character type, for example, letter, digit, or
 * neither.
 * 
 * @param Locale
 *            Locale identifier that specifies the locale. You can use the
 *            MAKELCID macro to create a locale identifier or use one of the
 *            following predefined values.
 * 
 * @param dwInfoType
 *            Flags specifying the character type information to retrieve. For
 *            possible flag values, see the dwInfoType parameter of
 *            GetStringTypeW. For detailed information about the character type
 *            bits, see Remarks for GetStringTypeW.
 * 
 * @param lpSrcStr
 *            Pointer to the ANSI string for which to retrieve the character
 *            types. The string can be a double-byte character set (DBCS) string
 *            if the supplied locale is appropriate for DBCS. The string is
 *            assumed to be null-terminated if cchSrc is set to any negative
 *            value.
 * 
 * @param cchSrc
 *            Size, in characters, of the string indicated by lpSrcStr. If the
 *            size includes a terminating null character, the function retrieves
 *            character type information for that character. If the application
 *            sets the size to any negative integer, the source string is
 *            assumed to be null-terminated and the function calculates the size
 *            automatically with an additional character for the null
 *            termination.
 * 
 * @param lpCharType
 *            Pointer to an array of 16-bit values. The length of this array
 *            must be large enough to receive one 16-bit value for each
 *            character in the source string. If cchSrc is not a negative
 *            number, lpCharType should be an array of words with cchSrc
 *            elements. If cchSrc is set to a negative number, lpCharType is an
 *            array of words with lpSrcStr + 1 elements. When the function
 *            returns, this array contains one word corresponding to each
 *            character in the source string.
 * 
 * @return Returns a nonzero value if successful, or 0 otherwise. To get
 *         extended error information, the application can call GetLastError,
 *         which can return one of the following error codes:
 *         ERROR_INVALID_FLAGS. The values supplied for flags were not valid.
 *         ERROR_INVALID_PARAMETER. Any of the parameter values was invalid.
 * 
 * @author Yen Nguyen
 *
 */
public class GetStringTypeA extends Kernel32API {

	public GetStringTypeA() {
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
		System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5);

		if (x1 instanceof LongValue && x2 instanceof LongValue && x3 instanceof LongValue && x4 instanceof LongValue
				&& x5 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			long t3 = ((LongValue) x3).getValue();
			long t4 = ((LongValue) x4).getValue();
			long t5 = ((LongValue) x5).getValue();
			
			LCID Locale = new LCID(t1);
			DWORD dwInfoType = new DWORD(t2);
			String lpSrcStr = memory.getText(new X86MemoryOperand(DataType.INT32, t3));
			int cchSrc = (int) t4;
			WORDByReference lpCharType = new WORDByReference();
			BOOL ret = Kernel32DLL.INSTANCE.GetStringTypeA(Locale, dwInfoType, lpSrcStr, cchSrc, lpCharType);

			register.mov("eax", new LongValue(ret.longValue()));
			
			memory.setWordMemoryValue(new X86MemoryOperand(DataType.INT32, t5), new LongValue(lpCharType.getValue().longValue()));
		}
		return false;
	}

}
