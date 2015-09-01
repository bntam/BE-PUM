/**
 * 
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.WString;

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
 * Compares two character strings. The comparison is not case-sensitive.
 * 
 * @param lpString1
 *            The first null-terminated string to be compared.
 * 
 * @param lpString2
 *            The second null-terminated string to be compared.
 * 
 * @return If the string pointed to by lpString1 is less than the string pointed
 *         to by lpString2, the return value is negative. If the string pointed
 *         to by lpString1 is greater than the string pointed to by lpString2,
 *         the return value is positive. If the strings are equal, the return
 *         value is zero.
 * 
 * @author Yen Nguyen
 *
 */
public class lstrcmpi extends Kernel32API {

	public lstrcmpi() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		long destAddr = this.params.get(0);
		long scrAddr = this.params.get(1);

		String dest = destAddr == 0 ? null : memory.getText(new X86MemoryOperand(DataType.INT32, destAddr));
		String src = scrAddr == 0 ? null : memory.getText(new X86MemoryOperand(DataType.INT32, scrAddr));

		System.out.println("Destination String:" + dest + ", Source String:" + src);

		int ret = Kernel32DLL.INSTANCE.lstrcmpi(new WString(dest), new WString(src));

		register.mov("eax", new LongValue(ret));
	}

}
