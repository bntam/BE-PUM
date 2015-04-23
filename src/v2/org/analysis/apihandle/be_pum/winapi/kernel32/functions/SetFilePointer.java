/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: SetFilePointer.java
 * Created date: Feb 2, 2015
 * Description:
 */
package v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions;

import v2.org.analysis.apihandle.be_pum.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.be_pum.winapi.kernel32.Kernel32DLL;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.LONG;
import com.sun.jna.platform.win32.WinDef.LONGByReference;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Moves the file pointer of the specified file.
 * 
 * This function stores the file pointer in two LONG values. To work with file
 * pointers that are larger than a single LONG value, it is easier to use the
 * SetFilePointerEx function.
 * 
 * @param hFile
 *            : A handle to the file.
 * @param lDistanceToMove
 *            : The low order 32-bits of a signed value that specifies the
 *            number of bytes to move the file pointer.
 * @param lpDistanceToMoveHigh
 *            : A pointer to the high order 32-bits of the signed 64-bit
 *            distance to move.
 * @param dwMoveMethod
 *            : The starting point for the file pointer move.
 * @return <p>
 *         If the function succeeds and lpDistanceToMoveHigh is NULL, the return
 *         value is the low-order DWORD of the new file pointer.
 *         </p>
 * 
 *         <p>
 *         If function succeeds and lpDistanceToMoveHigh is not NULL, the return
 *         value is the low-order DWORD of the new file pointer and
 *         lpDistanceToMoveHigh contains the high order DWORD of the new file
 *         pointer.
 *         </p>
 * 
 * @author Yen Nguyen
 *
 */
public class SetFilePointer extends Kernel32API {

	public SetFilePointer() {

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
		System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " ");

		if (x1 instanceof LongValue && x2 instanceof LongValue && x3 instanceof LongValue && x4 instanceof LongValue) {
			/*
			 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
			 * x1).getValue(), ((ValueLongExp) x2).getValue(), program);
			 */
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			long t3 = ((LongValue) x3).getValue();
			long t4 = ((LongValue) x4).getValue();

			// String str = symbolValueMemoryOperand.getText(new
			// X86MemoryOperand(DataType.INT32, t2));
			System.out.println("Handle File:" + t1 + ", Number of Bytes:" + t2 + ", Address of High-Order:" + t3
					+ ", Move Type:" + t4);

			HANDLE hFile = new HANDLE(new Pointer(t1));
			LONG lDistanceToMove = new LONG(t2);
			LONGByReference lpDistanceToMoveHigh = (t3 == 0) ? null : new LONGByReference(new LONG(t3));
			DWORD dwMoveMethod = new DWORD(t4);
			DWORD ret = Kernel32DLL.INSTANCE.SetFilePointer(hFile, lDistanceToMove, lpDistanceToMoveHigh, dwMoveMethod);

			register.mov("eax", new LongValue(ret.longValue()));

			if (lpDistanceToMoveHigh != null) {
				memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t3), new LongValue(
						lpDistanceToMoveHigh.getValue().longValue()));
			}
		}
		return false;
	}

}
