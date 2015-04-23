/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: CreateFile.java
 * Created date: Feb 6, 2015
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
import com.sun.jna.platform.win32.WinBase.SECURITY_ATTRIBUTES;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Creates or opens a named or unnamed mutex object.
 * 
 * @param lpMutexAttributes
 *            [in, optional] A pointer to a SECURITY_ATTRIBUTES structure. If
 *            this parameter is NULL, the handle cannot be inherited by child
 *            processes.
 * 
 * @param bInitialOwner
 *            [in] If this value is TRUE and the caller created the mutex, the
 *            calling thread obtains initial ownership of the mutex object.
 *            Otherwise, the calling thread does not obtain ownership of the
 *            mutex. To determine if the caller created the mutex, see the
 *            Return Values section.
 * 
 * @param lpName
 *            [in, optional] The name of the mutex object. The name is limited
 *            to MAX_PATH characters. Name comparison is case sensitive.
 * 
 * @return If the function succeeds, the return value is a handle to the newly
 *         created mutex object.
 * 
 *         If the function fails, the return value is NULL. To get extended
 *         error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class CreateMutex extends Kernel32API {

	public CreateMutex() {
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
		System.out.println("Argument:" + x1 + " " + x2 + " " + x3);

		if (x1 instanceof LongValue && x2 instanceof LongValue && x3 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			long t3 = ((LongValue) x3).getValue();

			SECURITY_ATTRIBUTES lpMutexAttributes = null;
			BOOL bInitialOwner = new BOOL(t2);
			String lpName = (t3 != 0) ? memory.getText(new X86MemoryOperand(DataType.INT32, t3)) : null;
			HANDLE ret = Kernel32DLL.INSTANCE.CreateMutex(lpMutexAttributes, bInitialOwner, lpName);

			long value = (ret == null) ? 0 : Pointer.nativeValue(ret.getPointer());
			register.mov("eax", new LongValue(value));
			System.out.println("Return value:" + value);
		}

		return false;
	}
}
