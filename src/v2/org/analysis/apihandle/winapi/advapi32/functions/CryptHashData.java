/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: CryptHashData.java
 * Created date: Apr 24, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.advapi32.functions;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.ptr.ByteByReference;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * The CryptHashData function adds data to a specified hash object. This
 * function and CryptHashSessionKey can be called multiple times to compute the
 * hash of long or discontinuous data streams.
 * 
 * @param hHash
 *            Handle of the hash object.
 * 
 * @param pbData
 *            A pointer to a buffer that contains the data to be added to the
 *            hash object.
 * 
 * @param dwDataLen
 *            Number of bytes of data to be added. This must be zero if the
 *            CRYPT_USERDATA flag is set.
 * 
 * @param dwFlags
 *            The following flag values are defined.
 * 
 * @return If the function succeeds, the return value is TRUE. If the function
 *         fails, the return value is FALSE. For extended error information,
 *         call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class CryptHashData extends Advapi32API {
	public CryptHashData() {
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

			ULONG_PTR hHash = new ULONG_PTR(t1);
			ByteByReference pbData = new ByteByReference();
			DWORD dwDataLen = new DWORD(t3);
			DWORD dwFlags = new DWORD(t4);
			BOOL ret = Advapi32DLL.INSTANCE.CryptHashData(hHash, pbData, dwDataLen, dwFlags);

			long value = ret.longValue();
			register.mov("eax", new LongValue(value));
			System.out.println("Return Value: " + value);

			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2),
					new LongValue((long) pbData.getValue()));
		}
		return false;
	}

}
