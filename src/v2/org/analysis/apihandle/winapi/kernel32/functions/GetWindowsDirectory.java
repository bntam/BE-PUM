/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetWindowsDirectory.java
 * Created date: Mar 1, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Retrieves the path of the Windows directory.
 * 
 * @param lpBuffer
 *            A pointer to a buffer that receives the path. This path does not
 *            end with a backslash unless the Windows directory is the root
 *            directory. For example, if the Windows directory is named Windows
 *            on drive C, the path of the Windows directory retrieved by this
 *            function is C:\Windows. If the system was installed in the root
 *            directory of drive C, the path retrieved is C:\.
 * 
 * @param uSize
 *            The maximum size of the buffer specified by the lpBuffer
 *            parameter, in TCHARs. This value should be set to MAX_PATH.
 * 
 * @return If the function succeeds, the return value is the length of the
 *         string copied to the buffer, in TCHARs, not including the terminating
 *         null character.
 * 
 * @author Yen Nguyen
 *
 */
public class GetWindowsDirectory extends Kernel32API {

	/**
	 * 
	 */
	public GetWindowsDirectory() {
		
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		Value x2 = stack.pop();

		System.out.println("Argument: Length:" + x2 + ", Memory Operand:" + x1);
		if (x1 instanceof LongValue && x2 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();

			char[] lpBuffer = new char[(int) t2];
			UINT uSize = new UINT(t2);
			UINT ret = Kernel32DLL.INSTANCE.GetWindowsDirectory(lpBuffer, uSize);
			String curDir = new String(lpBuffer);
			curDir = curDir.substring(0, ret.intValue());

			memory.setText(new X86MemoryOperand(DataType.INT32, t1), curDir, ret.longValue());
			System.out.println("Windows Directory:" + curDir);
			register.mov("eax", new LongValue(ret.longValue()));
		}
		return false;
	}

}
