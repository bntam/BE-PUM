/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetModuleHandle.java
 * Created date: Mar 1, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinDef.HMODULE;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * The GetModuleHandle function retrieves a module handle for the specified
 * module if the file has been mapped into the address space of the calling
 * process.
 * 
 * @param name
 *            Pointer to a null-terminated string that contains the name of the
 *            module (either a .dll or .exe file).
 * 
 * @return If the function succeeds, the return value is a handle to the
 *         specified module. If the function fails, the return value is NULL. To
 *         get extended error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetModuleHandle extends Kernel32API {

	public GetModuleHandle() {

	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Register register = env.getRegister();

		Value lpModuleName = stack.pop();
		System.out.println("lpModuleName:" + lpModuleName);

		if (lpModuleName instanceof LongValue) {

			HMODULE ret;
			if (((LongValue) lpModuleName).getValue() == 0) {
				ret = new HMODULE();
				ret.setPointer(new Pointer(Program.getProgram().getImageBase()));
				// returnValue = Kernel32.INSTANCE.GetModuleHandle(null);
			} else {
				String libraryName = env.getMemory().getText(((LongValue) lpModuleName).getValue());
				System.out.println("Library Name: " + libraryName);

				ret = Kernel32.INSTANCE.GetModuleHandle(libraryName);
			}

			long value = (ret == null) ? 0 : Pointer.nativeValue(ret.getPointer());
			register.mov("eax", new LongValue(value));
			System.out.println("Return Value:" + value);
		}
		return false;
	}

}
