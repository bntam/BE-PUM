/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: RegOpenKeyEx.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.advapi32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Advapi32;
import com.sun.jna.platform.win32.WinReg.HKEY;
import com.sun.jna.platform.win32.WinReg.HKEYByReference;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;

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
 * The RegOpenKeyEx function opens the specified registry key. Note that key
 * names are not case sensitive.
 * 
 * @param hKey
 *            Handle to an open key.
 * 
 * @param lpSubKey
 *            Pointer to a null-terminated string containing the name of the
 *            subkey to open.
 * 
 * @param ulOptions
 *            Reserved; must be zero.
 * 
 * @param samDesired
 *            Access mask that specifies the desired access rights to the key.
 *            The function fails if the security descriptor of the key does not
 *            permit the requested access for the calling process.
 * 
 * @param phkResult
 *            Pointer to a variable that receives a handle to the opened key. If
 *            the key is not one of the predefined registry keys, call the
 *            RegCloseKey function after you have finished using the handle.
 * 
 * @return If the function succeeds, the return value is ERROR_SUCCESS. If the
 *         function fails, the return value is a nonzero error code defined in
 *         Winerror.h.
 * 
 * @author Yen Nguyen
 * 
 */
public class RegOpenKeyEx extends Advapi32API {

	public RegOpenKeyEx() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();

		/*
		 * HKEY hKey, // handle of key to set value for LPCTSTR lpValueName, //
		 * address of value to set DWORD Reserved, // reserved DWORD dwType, //
		 * flag for value type CONST BYTE *lpData, // address of value data
		 * DWORD cbData // size of value data
		 */

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

			String lpSubKey = (t2 == 0) ? null : memory.getText(new X86MemoryOperand(DataType.INT32, t2));
			HKEYByReference phkResult = new HKEYByReference();

			int t = (int) t1;
			int ret = Advapi32.INSTANCE.RegOpenKeyEx(new HKEY((int) t1), lpSubKey, (int) t3, (int) t4, phkResult);
			register.mov("eax", new LongValue(ret));

			HKEY result = phkResult.getValue();
			long value = (result == null) ? 0 : Pointer.nativeValue(result.getPointer());
			System.out.println("Return: " + value);

			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t5), new LongValue(value));
		}
		return false;
	}

}
