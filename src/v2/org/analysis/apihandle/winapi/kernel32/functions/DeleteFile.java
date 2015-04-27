/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: DeleteFileA.java
 * Created date: Feb 2, 2015
 * QC: Passed
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.Kernel32;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.system.Storage;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Deletes an existing file.
 * 
 * @param filename
 *            The name of the file to be deleted.
 * 
 * @return If the function succeeds, the return value is nonzero. If the
 *         function fails, the return value is zero (0). To get extended error
 *         information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class DeleteFile extends Kernel32API {

	/**
	 * 
	 */
	public DeleteFile() {
		
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {

		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();
		
		// LPCTSTR lpFileName // pointer to name of file to delete
		Value x1 = stack.pop();
		System.out.println("Argument:" + x1);

		if (x1 instanceof LongValue) {
			String fName = memory.getText(new X86MemoryOperand(
					DataType.INT32, ((LongValue) x1).getValue()));
			fName = Storage.getMappingPath(fName);

			System.out.println("Delete file: " + fName);
			boolean ret = Kernel32.INSTANCE.DeleteFile(fName);
			
			register.mov("eax", new LongValue((ret) ? 1 : 0));
		}
		
		return false;
	}
}
