/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: FindFirstFile.java
 * Created date: Feb 9, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.apihandle.winapi.structures.WinNT.CPINFO;

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
 * Retrieves information about any valid installed or available code page.
 * 
 * @param CodePage
 *            Identifier for the code page for which to retrieve information.
 *            For details, see the CodePage parameter of GetCPInfoEx.
 * 
 * @param lpCPInfo
 *            Pointer to a CPINFO structure that receives information about the
 *            code page. See the Remarks section.
 * 
 * @return Returns 1 if successful, or 0 otherwise. To get extended error
 *         information, the application can call GetLastError, which can return
 *         one of the following error codes: ERROR_INVALID_PARAMETER. Any of the
 *         parameter values was invalid.
 * 
 * @author Yen Nguyen
 *
 */
public class GetCPInfo extends Kernel32API {

	public GetCPInfo() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		Value x2 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2 + " ");

		if (x1 instanceof LongValue && x2 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();

			UINT CodePage = new UINT(t1);
			CPINFO lpCPInfo = new CPINFO();
			BOOL ret = Kernel32DLL.INSTANCE.GetCPInfo(CodePage, lpCPInfo);

			register.mov("eax", new LongValue(ret.longValue()));

			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2), new LongValue(
					lpCPInfo.MaxCharSize.longValue()));
			memory.setByteMemoryValue(new X86MemoryOperand(DataType.INT32, t2 += 1), new LongValue(
					lpCPInfo.DefaultChar[0]));
			memory.setByteMemoryValue(new X86MemoryOperand(DataType.INT32, t2 += 1), new LongValue(
					lpCPInfo.DefaultChar[1]));

			for (int i = 0; i < 12; i++) {
				memory.setByteMemoryValue(new X86MemoryOperand(DataType.INT32, t2 + 1 + i), new LongValue(
						lpCPInfo.LeadByte[i]));
			}
		}

		return false;
	}

}
