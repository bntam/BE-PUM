/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: RegSetValueEx.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.advapi32.functions;

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
 * The RegSetValueEx function sets the data and type of a specified value under
 * a registry key.
 *
 * @param hKey
 *            Handle to an open key. The key must have been opened with the
 *            KEY_SET_VALUE access right.
 * 
 * @param lpValueName
 *            Pointer to a string containing the name of the value to set. If a
 *            value with this name is not already present in the key, the
 *            function adds it to the key. If lpValueName is NULL or an empty
 *            string, "", the function sets the type and data for the key's
 *            unnamed or default value.
 * 
 * @param Reserved
 *            Reserved; must be zero.
 * 
 * @param dwType
 *            Type of data pointed to by the lpData parameter.
 * 
 * @param lpData
 *            Pointer to a buffer containing the data to be stored with the
 *            specified value name.
 * 
 * @param cbData
 *            Size of the information pointed to by the lpData parameter, in
 *            bytes. If the data is of type REG_SZ, REG_EXPAND_SZ, or
 *            REG_MULTI_SZ, cbData must include the size of the terminating null
 *            character or characters.
 * 
 * @return If the function succeeds, the return value is ERROR_SUCCESS. If the
 *         function fails, the return value is a nonzero error code defined in
 *         Winerror.h.
 * 
 * @author Yen Nguyen
 *
 */
public class RegSetValueEx extends Advapi32API {

	public RegSetValueEx() {
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
		Value x6 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5 + " " + x6);

		if (x1 instanceof LongValue && x2 instanceof LongValue && x3 instanceof LongValue && x4 instanceof LongValue
				&& x5 instanceof LongValue && x6 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			long t3 = ((LongValue) x3).getValue();
			long t4 = ((LongValue) x4).getValue();
			long t5 = ((LongValue) x5).getValue();
			long t6 = ((LongValue) x6).getValue();

			String lpValueName = memory.getText(new X86MemoryOperand(DataType.INT32, t2));
			String lpData = memory.getText(new X86MemoryOperand(DataType.INT32, t5));

			int ret = 0;// Advapi32.INSTANCE.RegSetValueEx(new HKEY((int) t1),
						// lpValueName, (int) t3, (int) t4, lpData.getBytes(),
						// (int) t6);
			register.mov("eax", new LongValue(ret));
		}
		return false;
	}

}
