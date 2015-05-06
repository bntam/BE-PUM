/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: CryptDeriveKey.java
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
 * The CryptDeriveKey function generates cryptographic session keys derived from
 * a base data value. This function guarantees that when the same cryptographic
 * service provider (CSP) and algorithms are used, the keys generated from the
 * same base data are identical. The base data can be a password or any other
 * user data.
 * 
 * @param hProv
 *            A HCRYPTPROV handle of a CSP created by a call to
 *            CryptAcquireContext.
 * 
 * @param Algid
 *            An ALG_ID structure that identifies the symmetric encryption
 *            algorithm for which the key is to be generated. The algorithms
 *            available will most likely be different for each CSP. For more
 *            information about which algorithm identifier is used by the
 *            different providers for the key specs AT_KEYEXCHANGE and
 *            AT_SIGNATURE, see ALG_ID.
 * 
 * @param hBaseData
 *            A handle to a hash object that has been fed the exact base data.
 * 
 * @param dwFlags
 *            Specifies the type of key generated.
 * 
 * @param phKey
 *            A pointer to a HCRYPTKEY variable to receive the address of the
 *            handle of the newly generated key. When you have finished using
 *            the key, release the handle by calling the CryptDestroyKey
 *            function.
 * 
 * @return If the function succeeds, the function returns nonzero (TRUE). If the
 *         function fails, it returns zero (FALSE). For extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class CryptDeriveKey extends Advapi32API {

	public CryptDeriveKey() {
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
			ULONG_PTR hBaseData = new ULONG_PTR(t3);
			DWORD dwFlags = new DWORD(t4);
			ULONG_PTRByReference phKey = new ULONG_PTRByReference();
			BOOL ret = Advapi32DLL.INSTANCE.CryptDeriveKey(hProv, Algid, hBaseData, dwFlags, phKey);

			long value = ret.longValue();
			register.mov("eax", new LongValue(value));
			System.out.println("Return Value: " + value);

			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t5), new LongValue(phKey.getValue()
					.longValue()));
		}
		return false;
	}

}
