/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: LoadLibrary.java
 * Created date: Feb 2, 2015
 * QC: 2015-02-02 Passed
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HMODULE;

import v2.org.analysis.apihandle.winapi.APIHandle;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

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
 * Loads the specified module into the address space of the calling process. The
 * specified module may cause other modules to be loaded.
 * 
 * @param lpFileName
 *            The name of the module. This can be either a library module (a
 *            .dll file) or an executable module (an .exe file). The name
 *            specified is the file name of the module and is not related to the
 *            name stored in the library module itself, as specified by the
 *            LIBRARY keyword in the module-definition (.def) file.
 * @return If the function succeeds, the return value is a handle to the module.
 * 
 * @author Yen Nguyen
 *
 */
public class LoadLibrary extends Kernel32API {

	/**
	 * 
	 */
	public LoadLibrary() {

	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		// LPCTSTR lpLibFileName address of filename of executable module
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();
		Value x1 = stack.pop();
		System.out.print("Argument:" + x1);
		if (x1 instanceof LongValue) {
			/*
			 * returnValue = APIHandler.loadLibraryA( ((ValueLongExp)
			 * x1).getValue(), program);
			 */
			String libraryName = memory.getText(new X86MemoryOperand(DataType.INT32, ((LongValue) x1).getValue()));
			System.out.println(" Library Name:" + libraryName);

			HMODULE ret = Kernel32DLL.INSTANCE.LoadLibrary(libraryName);

			long value = (ret == null) ? 0 : Pointer.nativeValue(ret.getPointer());
			register.mov("eax", new LongValue(value));
			//register.mov("edx", new LongValue(0x140608));
			//register.mov("ecx", new LongValue(0x7c801bfa));
			System.out.println("Return Value: " + value);

			value = ((LongValue) register.getRegisterValue("eax")).getValue();
			APIHandle.libraryHandle.put(value, libraryName);
		}
		return false;
	}

}
