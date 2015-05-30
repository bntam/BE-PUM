/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: RegOpenKey.java
 * Created date: May 30, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.advapi32.functions;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.LONG;
import com.sun.jna.platform.win32.WinReg.HKEY;
import com.sun.jna.platform.win32.WinReg.HKEYByReference;

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
 * Opens the specified registry key.
 * 
 * @param hKey
 *            A handle to an open registry key. This handle is returned by
 *            the RegCreateKeyEx or RegOpenKeyEx function, or it can be one
 *            of the following predefined keys: HKEY_CLASSES_ROOT
 *            HKEY_CURRENT_CONFIG HKEY_CURRENT_USER HKEY_LOCAL_MACHINE
 *            HKEY_USERS
 * 
 * @param lpSubKey
 *            The name of the registry key to be opened. This key must be a
 *            subkey of the key identified by the hKey parameter. Key names
 *            are not case sensitive. If this parameter is NULL or a pointer
 *            to an empty string, the function returns the same handle that
 *            was passed in.
 * 
 * @param phkResult
 *            A pointer to a variable that receives a handle to the opened
 *            key. If the key is not one of the predefined registry keys,
 *            call the RegCloseKey function after you have finished using
 *            the handle.
 * 
 * @return If the function succeeds, the return value is ERROR_SUCCESS. If
 *         the function fails, the return value is a nonzero error code
 *         defined in Winerror.h. You can use the FormatMessage function
 *         with the FORMAT_MESSAGE_FROM_SYSTEM flag to get a generic
 *         description of the error.
 *         
 * @author Yen Nguyen
 *
 */
public class RegOpenKey extends Advapi32API {

	public RegOpenKey() {
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
		System.out.println("Argument:" + x1 + " " + x2 + " " + x3);
		
		if (x1 instanceof LongValue && x2 instanceof LongValue && x3 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			long t3 = ((LongValue) x3).getValue();
			
			HKEY hKey = new HKEY((int)t1);
			String lpSubKey = (t2 == 0L) ? null : memory.getText(new X86MemoryOperand(DataType.INT32, t2));
			HKEYByReference phkResult = new HKEYByReference();
			LONG ret = Advapi32DLL.INSTANCE.RegOpenKey(hKey, lpSubKey, phkResult);
			
			long result = Pointer.nativeValue(phkResult.getValue().getPointer());
			System.out.println("Return value: " + ret.longValue() + ", result: " + result);
			
			register.mov("eax", new LongValue(ret.longValue()));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t3), new LongValue(result));
		}
		return false;
	}

}
