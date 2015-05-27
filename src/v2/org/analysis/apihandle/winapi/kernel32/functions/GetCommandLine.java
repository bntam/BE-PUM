/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetCommandLine.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;

/**
 * Retrieves the command-line string for the current process.
 * 
 * @return The return value is a pointer to the command-line string for the
 *         current process.
 * 
 * @author Yen Nguyen
 *
 */
public class GetCommandLine extends Kernel32API {

	/**
	 * 
	 */
	public GetCommandLine() {

	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Memory memory = env.getMemory();
		Program program = Program.getProgram();

		// This function has no parameters.
		long disp = 4796200;
		// String commandLine = Kernel32DLL.INSTANCE.GetCommandLine();
		//String commandLine = "\"C:/Windows/" + program.getFileName()+"\"";
		 String commandLine = "\"" + program.getAbsolutePathFile() + "\"";

		System.out.println("Argument MemoryOperand:" + disp + ", Command Line:" + commandLine);

		memory.setText(new X86MemoryOperand(DataType.INT32, disp), commandLine);
		env.getRegister().setRegisterValue("eax", new LongValue(disp));
		return false;
	}

}
