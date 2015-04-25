/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: CryptCreateHash.java
 * Created date: Apr 25, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.advapi32.functions;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTRByReference;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.UINT;

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
 * The CryptCreateHash function initiates the hashing of a stream of data. It
 * creates and returns to the calling application a handle to a cryptographic
 * service provider (CSP) hash object. This handle is used in subsequent calls
 * to CryptHashData and CryptHashSessionKey to hash session keys and other
 * streams of data.
 * 
 * @param hProv
 *            A handle to a CSP created by a call to CryptAcquireContext.
 * 
 * @param Algid
 *            An ALG_ID value that identifies the hash algorithm to use.
 * 
 * @param hKey
 *            If the type of hash algorithm is a keyed hash, such as the
 *            Hash-Based Message Authentication Code (HMAC) or Message
 *            Authentication Code (MAC) algorithm, the key for the hash is
 *            passed in this parameter. For nonkeyed algorithms, this parameter
 *            must be set to zero.
 * 
 * @param dwFlags
 * 
 * @param phHash
 *            The address to which the function copies a handle to the new hash
 *            object. When you have finished using the hash object, release the
 *            handle by calling the CryptDestroyHash function.
 * 
 * @return If the function succeeds, the function returns TRUE. If the function
 *         fails, it returns FALSE. For extended error information, call
 *         GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class CryptCreateHash extends Advapi32API {

	public CryptCreateHash() {
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
		System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5);

		if (x1 instanceof LongValue && x2 instanceof LongValue && x3 instanceof LongValue && x4 instanceof LongValue
				&& x5 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			long t3 = ((LongValue) x3).getValue();
			long t4 = ((LongValue) x4).getValue();
			long t5 = ((LongValue) x5).getValue();

			ULONG_PTR hProv = new ULONG_PTR(t1);
			UINT Algid = new UINT(t2);
			ULONG_PTR hKey = new ULONG_PTR(t3);
			DWORD dwFlags = new DWORD(t4);
			ULONG_PTRByReference phHash = new ULONG_PTRByReference();
			BOOL ret = Advapi32DLL.INSTANCE.CryptCreateHash(hProv, Algid, hKey, dwFlags, phHash);

			long value = ret.longValue();
			register.mov("eax", new LongValue(value));
			System.out.println("Return Value: " + value);

			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t5), new LongValue(phHash.getValue()
					.longValue()));
		}
		return false;
	}

}
