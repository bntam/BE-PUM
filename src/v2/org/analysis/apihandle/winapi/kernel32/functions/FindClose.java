/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: FindClose.java
 * Created date: Feb 2, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Closes a file search handle opened by the FindFirstFile, FindFirstFileEx,
 * FindFirstFileNameW, FindFirstFileNameTransactedW, FindFirstFileTransacted,
 * FindFirstStreamTransactedW, or FindFirstStreamW functions.
 * 
 * @param hFindFile
 *            : The file search handle.
 * @return If the function succeeds, the return value is nonzero.
 * 
 * @author Yen Nguyen
 *
 */
public class FindClose extends Kernel32API {

	/**
	 * 
	 */
	public FindClose() {
		
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {

		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Register register = env.getRegister();
		//Memory memory = env.getMemory();

		// HANDLE hFindFile file search handle
		Value x1 = stack.pop();
		System.out.println("Argument:" + x1);

		if (x1 instanceof LongValue) {
			long t = ((LongValue) x1).getValue();
			HANDLE in_output = new HANDLE((t != 0) ? new Pointer(t) : Pointer.NULL);
			BOOL ret = Kernel32DLL.INSTANCE.FindClose(in_output);

			//memory.setDoubleWordMemoryValue(t, new LongValue(Pointer.nativeValue(in_output.getPointer())));

			register.mov("eax", new LongValue((ret.booleanValue()) ? 1 : 0));
		}
		return false;
	}

}
