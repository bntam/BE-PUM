/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: FindFirstFile.java
 * Created date: Feb 9, 2015
 * Description:
 */
package v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions;

import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.LCID;
import v2.org.analysis.apihandle.be_pum.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.be_pum.winapi.kernel32.Kernel32DLL;
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
 * Compares two character strings, for a locale specified by identifier.
 * 
 * @param Locale
 *            [in] Locale identifier of the locale used for the comparison. You
 *            can use the MAKELCID macro to create a locale identifier or use
 *            one of the following predefined values.
 *
 * @param dwCmpFlags
 *            [in] Flags that indicate how the function compares the two
 *            strings. For detailed definitions, see the dwCmpFlags parameter of
 *            CompareStringEx.
 * 
 * @param lpString1
 *            [in] Pointer to the first string to compare.
 * 
 * @param cchCount1
 *            [in] Length of the string indicated by lpString1, excluding the
 *            terminating null character. This value represents bytes for the
 *            ANSI version of the function and wide characters for the Unicode
 *            version. The application can supply a negative value if the string
 *            is null-terminated. In this case, the function determines the
 *            length automatically.
 * 
 * @param lpString2
 *            [in] Pointer to the second string to compare.
 * 
 * @param cchCount2
 *            [in] Length of the string indicated by lpString2, excluding the
 *            terminating null character. This value represents bytes for the
 *            ANSI version of the function and wide characters for the Unicode
 *            version. The application can supply a negative value if the string
 *            is null-terminated. In this case, the function determines the
 *            length automatically.
 * 
 * @author Yen Nguyen
 *
 */
public class CompareString extends Kernel32API {

	public CompareString() {
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

		if (x1 instanceof LongValue && x2 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			long t3 = ((LongValue) x3).getValue();
			long t4 = ((LongValue) x4).getValue();
			long t5 = ((LongValue) x5).getValue();
			long t6 = ((LongValue) x6).getValue();

			LCID Locale = new LCID(t1);
			DWORD dwCmpFlags = new DWORD(t2);
			WString lpString1 = new WString(memory.getText(new X86MemoryOperand(DataType.INT32, t3)));
			int cchCount1 = (int) t4;
			WString lpString2 = new WString(memory.getText(new X86MemoryOperand(DataType.INT32, t5)));
			int cchCount2 = (int) t6;

			int ret = Kernel32DLL.INSTANCE
					.CompareString(Locale, dwCmpFlags, lpString1, cchCount1, lpString2, cchCount2);

			register.mov("eax", new LongValue(ret));
		}

		return false;
	}

}
