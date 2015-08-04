/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: CopyFile.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.system.Storage;
import v2.org.analysis.value.LongValue;

/**
 * 
 * @author Yen Nguyen
 *
 */
public class _lopen extends Kernel32API {

	public _lopen() {
		NUM_OF_PARMS = 2;
	}


	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);

		String lpPathName = memory.getText(new X86MemoryOperand(DataType.INT32, t1));
		lpPathName = Storage.getMappingPath(lpPathName);

		int ret = Kernel32DLL.INSTANCE._lopen(lpPathName, (int) t2);
		register.mov("eax", new LongValue(ret));
	}
}
