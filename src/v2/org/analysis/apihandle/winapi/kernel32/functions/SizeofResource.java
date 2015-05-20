/**
 * Project: BE_PUM_V2
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: SizeofResource.java
 * Created date: Mar 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinDef.HRSRC;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Retrieves the size, in bytes, of the specified resource.
 * 
 * @param hModule
 *            A handle to the module whose executable file contains the
 *            resource.
 * 
 * @param hResInfo
 *            A handle to the resource. This handle must be created by using the
 *            FindResource or FindResourceEx function.
 * 
 * @return If the function succeeds, the return value is the number of bytes in
 *         the resource. If the function fails, the return value is zero. To get
 *         extended error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class SizeofResource extends Kernel32API {

	public SizeofResource() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		// Memory memory = env.getMemory();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		Value x2 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2 + " ");

		if (x1 instanceof LongValue && x2 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();

			HMODULE hModule = new HMODULE();
			hModule.setPointer(new Pointer(t1));
			HRSRC hResInfo = new HRSRC(new Pointer(t2));
			DWORD ret = Kernel32DLL.INSTANCE.SizeofResource(hModule, hResInfo);

			register.mov("eax", new LongValue(ret.longValue()));
		}
		return false;
	}

}
