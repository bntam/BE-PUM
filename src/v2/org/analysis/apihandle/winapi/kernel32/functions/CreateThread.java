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
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * NOT WORKING
 * 
 * @author Yen Nguyen
 *
 */
public class CreateThread extends Kernel32API {

	public CreateThread() {
		super();
		NUM_OF_PARMS = 6;
	}


	@Override
	public void execute() {
		/*
		 * LPSECURITY_ATTRIBUTES lpThreadAttributes, // pointer to thread
		 * security attributes DWORD dwStackSize, // initial thread stack size,
		 * in bytes LPTHREAD_START_ROUTINE lpStartAddress, // pointer to thread
		 * function LPVOID lpParameter, // argument for new thread DWORD
		 * dwCreationFlags, // creation flags LPDWORD lpThreadId // pointer to
		 * returned thread identifier
		 */

		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);
		long t5 = this.params.get(4);
		long t6 = this.params.get(5);
		
		LongValue threadAddress = (LongValue) memory.getDoubleWordMemoryValue(t3);
		
		System.out.println("ThreadFunctionAddress: " + threadAddress.getValue());

		// Kernel32.INSTANCE.creat
	}

}
