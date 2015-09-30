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

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.value.LongValue;

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
	private HMODULE apiCallReturn = null;

	public LoadLibrary() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		/*
		 * returnValue = APIHandler.loadLibraryA( ((ValueLongExp)
		 * x1).getValue(), program);
		 */
		String libraryName = memory.getText(new X86MemoryOperand(DataType.INT32, this.params.get(0)));
		long value = execute(libraryName);

		register.mov("eax", new LongValue(value));
		// register.mov("edx", new LongValue(0x140608));
		// register.mov("ecx", new LongValue(0x7c801bfa));

		value = ((LongValue) register.getRegisterValue("eax")).getValue();
	}

	public long execute(String libraryName) {
		System.out.println(" Library Name:" + libraryName);

		LoadLibThread thread = new LoadLibThread(libraryName);
		try {
			thread.start();
			Thread.sleep(100);
			if (this.apiCallReturn == null) {
				Thread.sleep(1000);
			}
			thread.interrupt();
		} catch (Exception e) {
			e.printStackTrace();
		}

		long value = (apiCallReturn == null) ? 0 : Pointer.nativeValue(apiCallReturn.getPointer());

		// Store all the handle value for each library
		if (value != 0) {
			APIHandle.libraryHandleMap.put(value, libraryName);
		}

		return value;
	}

	class LoadLibThread extends Thread {
		private String libName = null;

		public LoadLibThread(String lib) {
			this.libName = lib;
		}

		@Override
		public void run() {
			apiCallReturn = Kernel32DLL.INSTANCE.LoadLibrary(this.libName);
		}
	}
}
