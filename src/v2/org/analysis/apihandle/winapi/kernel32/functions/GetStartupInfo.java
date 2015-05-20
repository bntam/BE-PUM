/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetStartupInfo.java
 * Created date: Feb 7, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.apihandle.winapi.structures.WinBase.STARTUPINFO;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import com.sun.jna.Pointer;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Retrieves the contents of the STARTUPINFO structure that was specified when
 * the calling process was created.
 * 
 * @param lpStartupInfo
 *            : A pointer to a STARTUPINFO structure that receives the startup
 *            information.
 * 
 * @author Yen Nguyen
 *
 */
public class GetStartupInfo extends Kernel32API {

	/**
	 * 
	 */
	public GetStartupInfo() {

	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();

		// LPSTARTUPINFO lpStartupInfo address of STARTUPINFO structure
		Value x1 = stack.pop();

		System.out.println("Argument:" + x1);

		if (x1 != null && x1 instanceof LongValue) {
			long t = ((LongValue) x1).getValue();
			long pointer;

			STARTUPINFO lpStartupInfo = new STARTUPINFO();
			Kernel32DLL.INSTANCE.GetStartupInfo(lpStartupInfo);

			memory.setDoubleWordMemoryValue(t, new LongValue(lpStartupInfo.cb.longValue()));

			pointer = Pointer.nativeValue(lpStartupInfo.lpReserved);
			memory.setDoubleWordMemoryValue(t += 4, new LongValue(pointer));
			// memory.setText(new X86MemoryOperand(DataType.INT32, pointer),
			// lpStartupInfo.lpReserved.getWideString(0));

			pointer = Pointer.nativeValue(lpStartupInfo.lpDesktop);
			memory.setDoubleWordMemoryValue(t += 4, new LongValue(pointer));
			// memory.setText(new X86MemoryOperand(DataType.INT32, pointer),
			// lpStartupInfo.lpDesktop.toString());

			pointer = Pointer.nativeValue(lpStartupInfo.lpTitle);
			memory.setDoubleWordMemoryValue(t += 4, new LongValue(pointer));

			memory.setDoubleWordMemoryValue(t += 4, new LongValue(lpStartupInfo.dwX.longValue()));
			memory.setDoubleWordMemoryValue(t += 4, new LongValue(lpStartupInfo.dwY.longValue()));
			memory.setDoubleWordMemoryValue(t += 4, new LongValue(lpStartupInfo.dwXSize.longValue()));
			memory.setDoubleWordMemoryValue(t += 4, new LongValue(lpStartupInfo.dwYSize.longValue()));
			memory.setDoubleWordMemoryValue(t += 4, new LongValue(lpStartupInfo.dwXCountChars.longValue()));
			memory.setDoubleWordMemoryValue(t += 4, new LongValue(lpStartupInfo.dwYCountChars.longValue()));
			memory.setDoubleWordMemoryValue(t += 4, new LongValue(lpStartupInfo.dwFillAttribute.longValue()));
			memory.setDoubleWordMemoryValue(t += 4, new LongValue(lpStartupInfo.dwFlags.longValue()));
			memory.setWordMemoryValue(t += 4, new LongValue(lpStartupInfo.wShowWindow.longValue()));
			memory.setWordMemoryValue(t += 2, new LongValue(lpStartupInfo.cbReserved2.longValue()));
			memory.setDoubleWordMemoryValue(t += 2, new LongValue(0/*
																	 * Pointer.
																	 * nativeValue
																	 * (
																	 * lpStartupInfo
																	 * .
																	 * lpReserved2
																	 * .
																	 * getPointer
																	 * ())
																	 */));
			memory.setDoubleWordMemoryValue(t += 4,
					new LongValue(Pointer.nativeValue(lpStartupInfo.hStdInput.getPointer())));
			memory.setDoubleWordMemoryValue(t += 4,
					new LongValue(Pointer.nativeValue(lpStartupInfo.hStdOutput.getPointer())));
			memory.setDoubleWordMemoryValue(t += 4,
					new LongValue(Pointer.nativeValue(lpStartupInfo.hStdError.getPointer())));

			// String sInfo =
			// "D...Â¨Â¡^.ÃˆÂ¡^.Ã°Â¡^.l).*.dll.Any file (*.*).*.*.Â�...........Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿";
			// memory.setText(new X86MemoryOperand(DataType.INT32, ((LongValue)
			// x1).getValue()), sInfo);
		}

		return false;
	}

}
