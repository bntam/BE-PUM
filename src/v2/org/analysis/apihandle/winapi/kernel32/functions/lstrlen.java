/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: lstrlen.java
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

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Determines the length of the specified string (not including the terminating
 * null character).
 * 
 * @param lpString
 *            : The null-terminated string to be checked.
 * 
 * @return The function returns the length of the string, in characters. If
 *         lpString is NULL, the function returns 0.
 * 
 * @author Yen Nguyen
 *
 */
public class lstrlen extends Kernel32API {

	/**
	 * 
	 */
	public lstrlen() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t = this.params.get(0);
		String dest = t == 0 ? null : memory.getText(new X86MemoryOperand(DataType.INT32, t));
		System.out.println("Destination String:" + dest);

		register.mov("eax", new LongValue(Kernel32DLL.INSTANCE.lstrlen(dest)));
	}

}
