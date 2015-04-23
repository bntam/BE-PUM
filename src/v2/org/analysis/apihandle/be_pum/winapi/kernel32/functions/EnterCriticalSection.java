/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: CloseHandle.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions;

import v2.org.analysis.apihandle.be_pum.winapi.kernel32.Kernel32API;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Waits for ownership of the specified critical section object. The function
 * returns when the calling thread is granted ownership.
 * 
 * @param hObject
 *            A pointer to the critical section object.
 * 
 * @author Yen Nguyen
 *
 */
public class EnterCriticalSection extends Kernel32API {

	public EnterCriticalSection() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Register register = env.getRegister();

		Value x1 = stack.pop();

		System.out.println("Argument:" + x1);
		if (x1 instanceof LongValue) {
//			long x = ((LongValue) x1).getValue();
//			System.out.println("Object Handle:" + x);
//
//			boolean ret = Kernel32.INSTANCE.CloseHandle(new HANDLE(x != 0 ? new Pointer(x) : Pointer.NULL));
			register.mov("eax", new LongValue(0));

		}
		return false;
	}

}
