/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetModuleHandle.java
 * Created date: Mar 1, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.APIHandle;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

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
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {

		HMODULE ret;
		String libraryName = null;
		long t1 = this.params.get(0);
		if (t1 == 0L) {
			ret = new HMODULE();
			ret.setPointer(new Pointer(Program.getProgram().getImageBase()));
			// returnValue = Kernel32.INSTANCE.GetModuleHandle(null);
		} else {
			libraryName = memory.getText(new X86MemoryOperand(DataType.INT32, t1));
			System.out.println("Library Name: " + libraryName);

			ret = Kernel32.INSTANCE.GetModuleHandle(libraryName);
		}

		long value = (ret == null) ? 0 : Pointer.nativeValue(ret.getPointer());
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value:" + value);

		if (libraryName != null) {
			value = ((LongValue) register.getRegisterValue("eax")).getValue();
			APIHandle.libraryHandle.put(value, libraryName);
		}
	}

}
