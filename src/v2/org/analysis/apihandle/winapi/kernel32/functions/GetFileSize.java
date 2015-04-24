/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetFileTime.java
 * Created date: Mar 3, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.DWORDByReference;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Retrieves the size of the specified file, in bytes.
 * 
 * @param hFile
 *            A handle to the file.
 * 
 * @param lpFileSizeHigh
 *            A pointer to the variable where the high-order doubleword of the
 *            file size is returned. This parameter can be NULL if the
 *            application does not require the high-order doubleword.
 * 
 * @return If the function succeeds, the return value is the low-order
 *         doubleword of the file size, and, if lpFileSizeHigh is non-NULL, the
 *         function puts the high-order doubleword of the file size into the
 *         variable pointed to by that parameter.
 * 
 * @author Yen Nguyen
 *
 */
public class GetFileSize extends Kernel32API {

	public GetFileSize() {

	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		Value x2 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2);

		if (x1 instanceof LongValue && x2 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();

			HANDLE hFile = new HANDLE(new Pointer(t1));
			DWORDByReference lpFileSizeHigh = (t2 == 0) ? null : new DWORDByReference();

			DWORD ret = Kernel32DLL.INSTANCE.GetFileSize(hFile, lpFileSizeHigh);

			register.mov("eax", new LongValue(ret.longValue()));

			if (t2 != 0)
				memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2), new LongValue(lpFileSizeHigh
						.getValue().longValue()));
		}
		return false;
	}

}
