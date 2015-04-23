/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: DuplicateHandle.java
 * Created date: Mar 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions;

import v2.org.analysis.apihandle.be_pum.winapi.kernel32.Kernel32API;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.WinNT.HANDLEByReference;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * The DuplicateHandle function duplicates an object handle.
 * 
 * @param hSourceProcessHandle
 *            Handle to the process with the handle to duplicate. The handle
 *            must have the PROCESS_DUP_HANDLE access right.
 * 
 * @param hSourceHandle
 *            Handle to duplicate. This is an open object handle that is valid
 *            in the context of the source process.
 * 
 * @param hTargetProcessHandle
 *            Handle to the process that is to receive the duplicated handle.
 *            The handle must have the PROCESS_DUP_HANDLE access right.
 * 
 * @param lpTargetHandle
 *            Pointer to a variable that receives the duplicate handle. This
 *            handle value is valid in the context of the target process. If
 *            hSourceHandle is a pseudo handle returned by GetCurrentProcess or
 *            GetCurrentThread, DuplicateHandle converts it to a real handle to
 *            a process or thread, respectively.
 * 
 * @param dwDesiredAccess
 *            Access requested for the new handle.
 * 
 * @param bInheritHandle
 *            Indicates whether the handle is inheritable.
 * 
 * @param dwOptions
 *            Optional actions.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero. To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class DuplicateHandle extends Kernel32API {

	public DuplicateHandle() {
		
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
		Value x7 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5 + " " + x6 + " " + x7);

		if (x1 instanceof LongValue && x2 instanceof LongValue && x3 instanceof LongValue && x4 instanceof LongValue
				&& x5 instanceof LongValue && x6 instanceof LongValue && x7 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			long t3 = ((LongValue) x3).getValue();
			long t4 = ((LongValue) x4).getValue();
			long t5 = ((LongValue) x5).getValue();
			long t6 = ((LongValue) x6).getValue();
			long t7 = ((LongValue) x7).getValue();

			HANDLE hSourceProcessHandle = new HANDLE(new Pointer(t1));
			HANDLE hSourceHandle = new HANDLE(new Pointer(t2));
			HANDLE hTargetProcessHandle = new HANDLE(new Pointer(t3));
			HANDLEByReference lpTargetHandle = new HANDLEByReference();
			int dwDesiredAccess = (int) t5;
			boolean bInheritHandle = (t6 == 0 ? false : true);
			int dwOptions = (int) t7;
			boolean ret = Kernel32.INSTANCE.DuplicateHandle(hSourceProcessHandle, hSourceHandle, hTargetProcessHandle,
					lpTargetHandle, dwDesiredAccess, bInheritHandle, dwOptions);

			register.mov("eax", new LongValue(ret ? 1 : 0));

			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t4),
					new LongValue(Pointer.nativeValue(lpTargetHandle.getValue().getPointer())));
		}
		return false;
	}

}
