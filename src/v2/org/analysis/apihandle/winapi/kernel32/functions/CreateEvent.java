/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: CreateFile.java
 * Created date: Feb 6, 2015
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
 * Creates or opens a named or unnamed event object.
 * 
 * @param lpEventAttributes
 *            [in, optional] A pointer to a SECURITY_ATTRIBUTES structure. If
 *            this parameter is NULL, the handle cannot be inherited by child
 *            processes.
 * 
 * @param bManualReset
 *            [in] If this parameter is TRUE, the function creates a
 *            manual-reset event object, which requires the use of the
 *            ResetEvent function to set the event state to nonsignaled. If this
 *            parameter is FALSE, the function creates an auto-reset event
 *            object, and system automatically resets the event state to
 *            nonsignaled after a single waiting thread has been released.
 * 
 * @param bInitialState
 *            [in] If this parameter is TRUE, the initial state of the event
 *            object is signaled; otherwise, it is nonsignaled.
 * 
 * @param lpName
 *            [in, optional] The name of the event object. The name is limited
 *            to MAX_PATH characters. Name comparison is case sensitive.
 * 
 * @return If the function succeeds, the return value is a handle to the event
 *         object. If the named event object existed before the function call,
 *         the function returns a handle to the existing object and GetLastError
 *         returns ERROR_ALREADY_EXISTS.
 * 
 * @author Yen Nguyen
 *
 */
public class CreateEvent extends Kernel32API {

	public CreateEvent() {
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

			SECURITY_ATTRIBUTES lpEventAttributes = null;
			BOOL bManualReset = new BOOL(t2);
			BOOL bInitialState = new BOOL(t3);
			String lpName = (t4 != 0L) ? memory.getText(new X86MemoryOperand(DataType.INT32, t4)) : null;
			HANDLE ret = Kernel32DLL.INSTANCE.CreateEvent(null, bManualReset, bInitialState, lpName);

			register.mov("eax", new LongValue(Pointer.nativeValue(ret.getPointer())));
			System.out.println("Return value:" + Pointer.nativeValue(ret.getPointer()));
		}

		return false;
	}
}
