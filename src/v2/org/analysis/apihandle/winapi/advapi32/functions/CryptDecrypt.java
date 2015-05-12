/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: CryptDecrypt.java
 * Created date: May 11, 2015
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
import com.sun.jna.platform.win32.WinDef.DWORDByReference;
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
 * @author Yen Nguyen
 *
 */
public class CryptDecrypt extends Advapi32API {

	public CryptDecrypt() {
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
		Value x6 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5 + " " + x6);

		if (x1 instanceof LongValue && x2 instanceof LongValue && x3 instanceof LongValue && x4 instanceof LongValue
				&& x5 instanceof LongValue && x6 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			long t3 = ((LongValue) x3).getValue();
			long t4 = ((LongValue) x4).getValue();
			long t5 = ((LongValue) x5).getValue();
			long t6 = ((LongValue) x5).getValue();

			ULONG_PTR hKey = new ULONG_PTR(t1);
			ULONG_PTR hHash = new ULONG_PTR(t2);
			BOOL Final = new BOOL(t3);
			DWORD dwFlags = new DWORD(t4);
			ByteByReference pbData = new ByteByReference();
			DWORDByReference pdwDataLen = new DWORDByReference();

			BOOL ret = Advapi32DLL.INSTANCE.CryptDecrypt(hKey, hHash, Final, dwFlags, pbData, pdwDataLen);

			register.mov("eax", new LongValue(ret.longValue()));
			System.out.println("Return Value: " + ret.booleanValue());

			memory.setByteMemoryValue(new X86MemoryOperand(DataType.INT8, t5), new LongValue(pbData.getValue()));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t6), new LongValue(pdwDataLen
					.getValue().longValue()));
		}
		return false;
	}

}
