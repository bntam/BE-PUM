/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: FindNextFile.java
 * Created date: Feb 10, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinDef.HRSRC;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Determines the location of a resource with the specified type and name in
 * the specified module.
 * 
 * @param hModule
 *            A handle to the module whose portable executable file or an
 *            accompanying MUI file contains the resource. If this parameter
 *            is NULL, the function searches the module used to create the
 *            current process.
 * 
 * @param lpName
 *            The name of the resource. Alternately, rather than a pointer,
 *            this parameter can be MAKEINTRESOURCE(ID), where ID is the
 *            integer identifier of the resource. For more information, see
 *            the Remarks section below.
 * 
 * @param lpType
 *            The resource type. Alternately, rather than a pointer, this
 *            parameter can be MAKEINTRESOURCE(ID), where ID is the integer
 *            identifier of the given resource type. For standard resource
 *            types, see Resource Types. For more information, see the
 *            Remarks section below.
 * 
 * @return If the function succeeds, the return value is a handle to the
 *         specified resource's information block. To obtain a handle to the
 *         resource, pass this handle to the LoadResource function.
 * 
 * @author Yen Nguyen
 *
 */
public class FindResource extends Kernel32API {

	public FindResource() {
		
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		Value x2 = stack.pop();
		Value x3 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2 + " " + x3);

		if (x1 instanceof LongValue && x2 instanceof LongValue && x3 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			long t3 = ((LongValue) x3).getValue();

			HMODULE hModule = new HMODULE();
			hModule.setPointer(new Pointer(t1));
			String lpName = memory.getText(new X86MemoryOperand(DataType.INT32, t2));
			String lpType = memory.getText(new X86MemoryOperand(DataType.INT32, t3));
			
			HRSRC ret = Kernel32DLL.INSTANCE.FindResource(hModule, lpName, lpType);
			
			register.mov("eax", new LongValue(Pointer.nativeValue(ret.getPointer())));
		}
		return false;
	}

}
