/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: WritePrivateProfileString.java
 * Created date: Mar 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef.BOOL;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.system.Storage;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Copies a string into the specified section of an initialization file.
 * 
 * @param lpAppName
 *            The name of the section to which the string will be copied. If the
 *            section does not exist, it is created. The name of the section is
 *            case-independent; the string can be any combination of uppercase
 *            and lowercase letters.
 * 
 * @param lpKeyName
 *            The name of the key to be associated with a string. If the key
 *            does not exist in the specified section, it is created. If this
 *            parameter is NULL, the entire section, including all entries
 *            within the section, is deleted.
 * 
 * @param lpString
 *            A null-terminated string to be written to the file. If this
 *            parameter is NULL, the key pointed to by the lpKeyName parameter
 *            is deleted.
 * 
 * @param lpFileName
 *            The name of the initialization file.
 * 
 * @return If the function successfully copies the string to the initialization
 *         file, the return value is nonzero. If the function fails, or if it
 *         flushes the cached version of the most recently accessed
 *         initialization file, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class WritePrivateProfileString extends Kernel32API {

	public WritePrivateProfileString() {
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

			WString lpAppName = new WString(memory.getText(new X86MemoryOperand(DataType.INT32, t1)));
			WString lpKeyName = new WString(memory.getText(new X86MemoryOperand(DataType.INT32, t2)));
			WString lpString = new WString(memory.getText(new X86MemoryOperand(DataType.INT32, t3)));
			WString lpFileName = new WString(Storage.getMappingPath(memory.getText(new X86MemoryOperand(DataType.INT32,
					t4))));
			BOOL ret = Kernel32DLL.INSTANCE.WritePrivateProfileString(lpAppName, lpKeyName, lpString, lpFileName);

			register.mov("eax", new LongValue(ret.longValue()));
		}
		return false;
	}

}
