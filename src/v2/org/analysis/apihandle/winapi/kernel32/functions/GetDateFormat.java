/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: VirtualAlloc.java
 * Created date: Feb 9, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinBase.SYSTEMTIME;
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
 * Formats a date as a date string for a locale specified by the locale
 * identifier. The function formats either a specified date or the local system
 * date.
 * 
 * @param Locale
 *            Locale identifier that specifies the locale this function formats
 *            the date string for. You can use the MAKELCID macro to create a
 *            locale identifier or use one of the following predefined values.
 * 
 * @param dwFlags
 *            Flags specifying date format options. For detailed definitions,
 *            see the dwFlags parameter of GetDateFormatEx.
 * 
 * @param lpDate
 *            Pointer to a SYSTEMTIME structure that contains the date
 *            information to format. The application sets this parameter to NULL
 *            if the function is to use the current local system date.
 * 
 * @param lpFormat
 *            Pointer to a format picture string that is used to form the date.
 *            Possible values for the format picture string are defined in Day,
 *            Month, Year, and Era Format Pictures.
 * 
 * @param lpDateStr
 *            Pointer to a buffer in which this function retrieves the formatted
 *            date string.
 * 
 * @param cchDate
 *            Size, in characters, of the lpDateStr buffer. The application can
 *            set this parameter to 0 to return the buffer size required to hold
 *            the formatted date string. In this case, the buffer indicated by
 *            lpDateStr is not used.
 * 
 * @return Returns the number of characters written to the lpDateStr buffer if
 *         successful. If the cchDate parameter is set to 0, the function
 *         returns the number of characters required to hold the formatted date
 *         string, including the terminating null character.
 * 
 * @author Yen Nguyen
 *
 */
public class GetDateFormat extends Kernel32API {

	public GetDateFormat() {

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
		System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5 + " " + x6);

		if (x1 instanceof LongValue && x2 instanceof LongValue && x3 instanceof LongValue && x4 instanceof LongValue
				&& x5 instanceof LongValue && x6 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			long t3 = ((LongValue) x3).getValue();
			long t4 = ((LongValue) x4).getValue();
			long t5 = ((LongValue) x4).getValue();
			long t6 = ((LongValue) x6).getValue();

			LCID Locale = new LCID(t1);
			DWORD dwFlags = new DWORD(t2);
			SYSTEMTIME lpDate = null;
			if (t3 != 0) {
				lpDate = new SYSTEMTIME();
				lpDate.wYear = (short) ((LongValue) memory.getWordMemoryValue(new X86MemoryOperand(DataType.INT32, t3)))
						.getValue();
				lpDate.wMonth = (short) ((LongValue) memory.getWordMemoryValue(new X86MemoryOperand(DataType.INT32,
						t3 += 2))).getValue();
				lpDate.wDayOfWeek = (short) ((LongValue) memory.getWordMemoryValue(new X86MemoryOperand(DataType.INT32,
						t3 += 2))).getValue();
				lpDate.wDay = (short) ((LongValue) memory.getWordMemoryValue(new X86MemoryOperand(DataType.INT32,
						t3 += 2))).getValue();
				lpDate.wHour = (short) ((LongValue) memory.getWordMemoryValue(new X86MemoryOperand(DataType.INT32,
						t3 += 2))).getValue();
				lpDate.wMinute = (short) ((LongValue) memory.getWordMemoryValue(new X86MemoryOperand(DataType.INT32,
						t3 += 2))).getValue();
				lpDate.wSecond = (short) ((LongValue) memory.getWordMemoryValue(new X86MemoryOperand(DataType.INT32,
						t3 += 2))).getValue();
				lpDate.wMilliseconds = (short) ((LongValue) memory.getWordMemoryValue(new X86MemoryOperand(
						DataType.INT32, t3 += 2))).getValue();
			}
			WString lpFormat = (t4 == 0) ? null : new WString(memory.getText(new X86MemoryOperand(DataType.INT32, t4)));
			char[] lpDateStr = (t5 == 0) ? null : new char[(int) t6 + 1];
			int cchDate = (int) t6;
			
			int ret = Kernel32DLL.INSTANCE.GetDateFormatW(Locale, dwFlags, lpDate, lpFormat, lpDateStr, cchDate);

			register.mov("eax", new LongValue(ret));
			
			if (t5 != 0 && cchDate != 0) {
				memory.setText(new X86MemoryOperand(DataType.INT32, t5), new String(lpDateStr), ret);
			}
		}
		return false;
	}

}
