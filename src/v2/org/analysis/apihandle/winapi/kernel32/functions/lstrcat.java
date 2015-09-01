/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: lstrcat.java
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
 * Appends one string to another.
 * 
 * @param lpString1
 *            : The first null-terminated string. This buffer must be large
 *            enough to contain both strings.
 * 
 * @param lpString2
 *            : The null-terminated string to be appended to the string
 *            specified in the lpString1 parameter.
 * 
 * @return If the function succeeds, the return value is a pointer to the
 *         buffer.
 * 
 * @author Yen Nguyen
 *
 */
public class lstrcat extends Kernel32API {

	/**
	 * 
	 */
	public lstrcat() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long destAddr = this.params.get(0);
		long scrAddr = this.params.get(1);

		String dest = destAddr == 0 ? null : memory.getText(new X86MemoryOperand(DataType.INT32, destAddr));
		String src = scrAddr == 0 ? null : memory.getText(new X86MemoryOperand(DataType.INT32, scrAddr));
		System.out.println("Destination Address:" + destAddr + ", Source String:" + src);

		dest = Kernel32DLL.INSTANCE.lstrcat(dest, src); // =
														// dest.concat(src);

		memory.setText(new X86MemoryOperand(DataType.INT32, destAddr), dest);
		register.mov("eax", new LongValue(1));
	}
}
