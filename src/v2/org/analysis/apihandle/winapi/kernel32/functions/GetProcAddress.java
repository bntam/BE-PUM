/**
 * Project: BE_PUM_V1
 * Package name: org.be_pum.winapi.kernel32.functions
 * File name: GetProcAddress.java
 * Created date: Feb 2, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HMODULE;

import v2.org.analysis.apihandle.winapi.APIHandle;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLLwithoutOption;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * @author SEGFRY
 *
 */
public class GetProcAddress extends Kernel32API {

	public GetProcAddress() {
		
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();
		// HMODULE hModule, handle to DLL module
		// LPCSTR lpProcName name of function
		Value x1 = stack.pop();
		Value x2 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2 + " ");

		if (x1 instanceof LongValue && x2 instanceof LongValue) {
			/*
			 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
			 * x1).getValue(), ((ValueLongExp) x2).getValue(), program);
			 */
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			String lpProcName = memory.getText(new X86MemoryOperand(DataType.INT32, t2));
			System.out.println("Function Name:" + lpProcName + ", Library Handle:" + x1);
			
			HMODULE hModule = new HMODULE();
			hModule.setPointer(new Pointer(t1));
			
			long ret = Kernel32DLLwithoutOption.INSTANCE.GetProcAddress(hModule, lpProcName);
			register.mov("eax", new LongValue(ret));
			System.out.println("Return Value: " + ret);
			
			String libName = APIHandle.libraryHandle.get(t1);
			ret = ((LongValue) register.getRegisterValue("eax")).getValue();
			APIHandle.processAddressHandle.put(ret, lpProcName + '@' + libName);
		}
		return false;
	}

}
