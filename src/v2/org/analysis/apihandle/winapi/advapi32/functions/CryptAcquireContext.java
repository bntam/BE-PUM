/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: CryptAcquireContext.java
 * Created date: Apr 24, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.advapi32.functions;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.WString;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTRByReference;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
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
 * The CryptAcquireContext function is used to acquire a handle to a particular
 * key container within a particular cryptographic service provider (CSP). This
 * returned handle is used in calls to CryptoAPI functions that use the selected
 * CSP..
 * 
 * @param phProv
 *            A pointer to a handle of a CSP. When you have finished using the
 *            CSP, release the handle by calling the CryptReleaseContext
 *            function.
 * 
 * @param pszContainer
 *            The key container name. This is a null-terminated string that
 *            identifies the key container to the CSP. This name is independent
 *            of the method used to store the keys. Some CSPs store their key
 *            containers internally (in hardware), some use the system registry,
 *            and others use the file system. In most cases, when dwFlags is set
 *            to CRYPT_VERIFYCONTEXT, pszContainer must be set to NULL. However,
 *            for hardware-based CSPs, such as a smart card CSP, can be access
 *            publically available information in the specfied container.
 * 
 * @param pszProvider
 *            A null-terminated string that contains the name of the CSP to be
 *            used.
 * 
 * @param dwProvType
 *            Specifies the type of provider to acquire. Defined provider types
 *            are discussed in Cryptographic Provider Types.
 * 
 * @param dwFlags
 *            Flag values. This parameter is usually set to zero, but some
 *            applications set one or more of the following flags.
 * 
 * @return If the function succeeds, the function returns nonzero (TRUE). If the
 *         function fails, it returns zero (FALSE). For extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class CryptAcquireContext extends Advapi32API {

	public CryptAcquireContext() {
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

			ULONG_PTRByReference phProv = new ULONG_PTRByReference();
			WString pszContainer = new WString(memory.getText(new X86MemoryOperand(DataType.INT32, t2)));
			WString pszProvider = new WString(memory.getText(new X86MemoryOperand(DataType.INT32, t3)));
			DWORD dwProvType = new DWORD(t4);
			DWORD dwFlags = new DWORD(t5);
			BOOL ret = Advapi32DLL.INSTANCE.CryptAcquireContext(phProv, pszContainer, pszProvider, dwProvType, dwFlags);

			long value = ret.longValue();
			register.mov("eax", new LongValue(value));
			System.out.println("Return Value: " + value);

			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t1), new LongValue(phProv.getValue()
					.longValue()));
		}
		return false;
	}

}
