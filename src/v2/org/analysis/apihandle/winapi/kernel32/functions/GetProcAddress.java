/**
 * Project: BE_PUM_V1
 * Package name: org.be_pum.winapi.kernel32.functions
 * File name: GetProcAddress.java
 * Created date: Feb 2, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HMODULE;

import v2.org.analysis.apihandle.winapi.APIHandle;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLLwithoutOption;
import v2.org.analysis.value.LongValue;

/**
 * @author Yen Nguyen
 *
 */
public class GetProcAddress extends Kernel32API {

	public GetProcAddress() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		/*
		 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
		 * x1).getValue(), ((ValueLongExp) x2).getValue(), program);
		 */
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		String lpProcName = memory.getText(new X86MemoryOperand(DataType.INT32, t2));

		long ret = this.execute(t1, lpProcName);

		register.mov("eax", new LongValue(ret));
	}

	public long execute(long libHandle, String procName) {
		System.out.println("Function Name:" + procName + ", Library Handle:0x" + Long.toHexString(libHandle));
		
		HMODULE hModule = new HMODULE();
		hModule.setPointer(new Pointer(libHandle));
		
		int ret = Kernel32DLLwithoutOption.INSTANCE.GetProcAddress(hModule, procName);

		if (ret != 0) {
			String libName = APIHandle.libraryHandleMap.get(libHandle);
			if (libName == null) {
				// Library temp =
				// curState.getEnvironement().getSystem().getLibraryHandle().getLibrary(t1);
				// if (temp != null)
				libName = curState.getEnvironement().getSystem().getLibraryName(libHandle);
			}

			APIHandle.procAddressHandleMap.put((long) ret, procName + '@' + libName);
		}

		return ret;
	}
}
