/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetFileTime.java
 * Created date: Mar 3, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.LCID;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

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
 * Retrieves information about a locale specified by identifier.
 * 
 * @param Locale
 *            Locale identifier for which to retrieve information. You can use
 *            the MAKELCID macro to create a locale identifier or use one of the
 *            following predefined values.
 * 
 * @param LCType
 *            The locale information to retrieve. For detailed definitions, see
 *            the LCType parameter of GetLocaleInfoEx.
 * 
 * @param lpLCData
 *            Pointer to a buffer in which this function retrieves the requested
 *            locale information. This pointer is not used if cchData is set to
 *            0. For more information, see the Remarks section.
 * 
 * @param cchData
 *            Size, in TCHAR values, of the data buffer indicated by lpLCData.
 *            Alternatively, the application can set this parameter to 0. In
 *            this case, the function does not use the lpLCData parameter and
 *            returns the required buffer size, including the terminating null
 *            character.
 * 
 * @return Returns the number of characters retrieved in the locale data buffer
 *         if successful and cchData is a nonzero value. If the function
 *         succeeds, cchData is nonzero, and LOCALE_RETURN_NUMBER is specified,
 *         the return value is the size of the integer retrieved in the data
 *         buffer; that is, 2 for the Unicode version of the function or 4 for
 *         the ANSI version. If the function succeeds and the value of cchData
 *         is 0, the return value is the required size, in characters including
 *         a null character, for the locale data buffer.
 * 
 * @author Yen Nguyen
 *
 */
public class GetLocaleInfo extends Kernel32API {

	public GetLocaleInfo() {

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
		System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4);

		if (x1 instanceof LongValue && x2 instanceof LongValue && x3 instanceof LongValue && x4 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			long t3 = ((LongValue) x3).getValue();
			long t4 = ((LongValue) x4).getValue();

			LCID Locale = new LCID(t1);
			DWORD LCType = new DWORD(t2);
			char[] lpLCData = (t4 == 0) ? null : new char[(int) t4];
			int cchData = (int) t4;
			int ret = Kernel32DLL.INSTANCE.GetLocaleInfo(Locale, LCType, lpLCData, cchData);

			register.mov("eax", new LongValue(ret));

			if (lpLCData != null && t4 != 0)
				memory.setText(new X86MemoryOperand(DataType.INT32, t3), new String(lpLCData), ret);
		}
		return false;
	}

}
