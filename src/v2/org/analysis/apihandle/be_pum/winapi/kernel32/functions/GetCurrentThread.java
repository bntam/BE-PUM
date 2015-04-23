/**
 * Project: BE_PUM
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetCurrentProcess.java
 * Created date: Jan 30, 2015
 */
package v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions;

import v2.org.analysis.apihandle.be_pum.winapi.kernel32.Kernel32API;
import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;

/**
 * Retrieves a pseudo handle for the calling thread.
 * 
 * @return This function has no parameters.
 * 
 * @author Yen Nguyen
 *
 */
public class GetCurrentThread extends Kernel32API {

	/**
	 * Constructor
	 */
	public GetCurrentThread() {

	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		// Prepare environment
		Environment env = curState.getEnvironement();
		Register register = env.getRegister();
		Program program = Program.getProgram();
		int error;

		HANDLE handle = Kernel32.INSTANCE.GetCurrentThread();
		long value = Pointer.nativeValue(handle.getPointer());
		
		program.generageCFG(program.getAbsolutePathFile() + "_test");

		// Store
		register.mov("eax", new LongValue(value));

		return false;
	}

}
