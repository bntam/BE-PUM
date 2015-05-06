/**
 * 
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinDef.HRSRC;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Retrieves a handle that can be used to obtain a pointer to the first byte of
 * the specified resource in memory.
 * 
 * @param hModule
 *            A handle to the module whose executable file contains the
 *            resource. If hModule is NULL, the system loads the resource from
 *            the module that was used to create the current process.
 * 
 * @param hResInfo
 *            A handle to the resource to be loaded. This handle is returned by
 *            the FindResource or FindResourceEx function.
 * 
 * @return If the function succeeds, the return value is a handle to the data
 *         associated with the resource. If the function fails, the return value
 *         is NULL. To get extended error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class LoadResource extends Kernel32API {

	public LoadResource() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName,
			BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		//Memory memory = env.getMemory();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		Value x2 = stack.pop();
		System.out.print("Argument:" + x1 + " " + x2);
		if (x1 instanceof LongValue && x2 instanceof LongValue) {

			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();

			HMODULE hModule = null;
			if (t1 != 0) {
				hModule = new HMODULE();
				hModule.setPointer(new Pointer(t1));
			}
			HRSRC hResInfo = new HRSRC(new Pointer(t2));
			HANDLE ret = Kernel32DLL.INSTANCE.LoadResource(hModule, hResInfo);

			long value = (ret == null) ? 0 : Pointer.nativeValue(ret.getPointer());
			register.mov("eax", new LongValue(value));
			System.out.println("Return Value: " + value);
		}
		return false;
	}

}
