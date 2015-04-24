/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetFileAttributes.java
 * Created date: Jan 31, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.platform.win32.Kernel32;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.system.Storage;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Retrieves file system attributes for a specified file or directory.
 * 
 * @param lpFileName
 *            The name of the file or directory. Prepend \\?\ to the path
 *            for names up to 32,767 wide characters
 *            
 * @return INVALID_FILE_ATTRIBUTES if the function fails, otherwise the file
 *         attributes WinNT.FILE_ATTRIBUTE_*
 *         
 * @author Yen Nguyen
 *
 */
public class GetFileAttributes extends Kernel32API {

	public GetFileAttributes() {
	}


	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();
		
		Value x1 = stack.pop();
		System.out.println("Argument:" + x1);

		if (x1 instanceof LongValue) {
			String fName = memory.getText(new X86MemoryOperand(
					DataType.INT32, ((LongValue) x1).getValue()));
			fName = Storage.getMappingPath(fName);
			
			long attr = Kernel32.INSTANCE.GetFileAttributes(fName);
			register.mov("eax", new LongValue(attr));

			System.out.println("Return Value:" + attr);
		}
		return false;
	}

}
