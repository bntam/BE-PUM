/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetModuleFileName.java
 * Created date: Mar 1, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HMODULE;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Retrieves the fully qualified path for the file that contains the specified
 * module. The module must have been loaded by the current process.
 * 
 * @param hModule
 *            A handle to the loaded module whose path is being requested. If
 *            this parameter is NULL, GetModuleFileName retrieves the path of
 *            the executable file of the current process.
 * 
 * @param lpFilename
 *            A pointer to a buffer that receives the fully qualified path of
 *            the module. If the length of the path is less than the size that
 *            the nSize parameter specifies, the function succeeds and the path
 *            is returned as a null-terminated string.
 * 
 * @param nSize
 *            The size of the lpFilename buffer, in TCHARs.
 * 
 * @return If the function succeeds, the return value is the length of the
 *         string that is copied to the buffer, in characters, not including the
 *         terminating null character. If the buffer is too small to hold the
 *         module name, the string is truncated to nSize characters including
 *         the terminating null character, the function returns nSize, and the
 *         function sets the last error to ERROR_INSUFFICIENT_BUFFER.
 * 
 * @author Yen Nguyen
 * 
 */
public class GetModuleFileName extends Kernel32API {

	public GetModuleFileName() {

	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();

		Value hModule = stack.pop();
		Value lpFilename = stack.pop();
		Value nSize = stack.pop();
		System.out.println("Argument: Module: " + hModule.toString() + " lpFilename Address: " + lpFilename.toString()
				+ " Array Size:" + nSize.toString());

		if (hModule instanceof LongValue && lpFilename instanceof LongValue && nSize instanceof LongValue) {

			char Filename[] = new char[(int) ((LongValue) nSize).getValue()];
			HMODULE module = new HMODULE();
			module.setPointer(new Pointer(((LongValue) hModule).getValue()));

			String output = null;
			DWORD ret = null;

			if (((LongValue) hModule).getValue() == 0) {
				output = Program.getProgram().getAbsolutePathFile();
				ret = new DWORD(output.length());
			} else {
				ret = Kernel32DLL.INSTANCE.GetModuleFileName(module, Filename,
						new DWORD(((LongValue) nSize).getValue()));
				output = new String(Filename, 0, ret.intValue());
			}
			System.out.println("Filename:" + output + " \r\nReturn: " + ret);

			memory.setText(new X86MemoryOperand(DataType.INT32, ((LongValue) lpFilename).getValue()), output);
			register.mov("eax", new LongValue(ret.longValue()));

		}
		return false;
	}

}
