/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: CreateThread.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.Value;

/**
 * NOT WORKING
 * 
 * @author Yen Nguyen
 *
 */
public class CreateThread extends Kernel32API {

	public CreateThread() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();
		Program program = Program.getProgram();

		/*
		 * LPSECURITY_ATTRIBUTES lpThreadAttributes, // pointer to thread
		 * security attributes DWORD dwStackSize, // initial thread stack size,
		 * in bytes LPTHREAD_START_ROUTINE lpStartAddress, // pointer to thread
		 * function LPVOID lpParameter, // argument for new thread DWORD
		 * dwCreationFlags, // creation flags LPDWORD lpThreadId // pointer to
		 * returned thread identifier
		 */
		Value x1 = stack.pop();
		Value x2 = stack.pop();
		Value x3 = stack.pop();
		Value x4 = stack.pop();
		Value x5 = stack.pop();
		Value x6 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5 + " " + x6);

		// Kernel32.INSTANCE.creat
		return false;
	}

}
