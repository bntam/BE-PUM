/**
 * 
 */
package v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions;

import v2.org.analysis.apihandle.be_pum.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.be_pum.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.apihandle.be_pum.winapi.structures.WinBase.MEMORYSTATUS;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * [GlobalMemoryStatus can return incorrect information. Use the
 * GlobalMemoryStatusEx function instead. ] Retrieves information about the
 * system's current usage of both physical and virtual memory.
 * 
 * @param lpBuffer
 *            A pointer to a MEMORYSTATUS structure. The GlobalMemoryStatus
 *            function stores information about current memory availability into
 *            this structure.
 * 
 * @author Yen Nguyen
 *
 */
public class GlobalMemoryStatus extends Kernel32API {

	public GlobalMemoryStatus() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName,
			BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();

		Value x1 = stack.pop();

		System.out.println("Argument:" + x1);

		if (x1 != null && x1 instanceof LongValue) {
			long t = ((LongValue) x1).getValue();

			MEMORYSTATUS lpBuffer = new MEMORYSTATUS();
			Kernel32DLL.INSTANCE.GlobalMemoryStatus(lpBuffer);

			// DWORD dwLength;
			// DWORD dwMemoryLoad;
			// SIZE_T dwTotalPhys;
			// SIZE_T dwAvailPhys;
			// SIZE_T dwTotalPageFile;
			// SIZE_T dwAvailPageFile;
			// SIZE_T dwTotalVirtual;
			// SIZE_T dwAvailVirtual;

			memory.setDoubleWordMemoryValue(t, new LongValue(lpBuffer.dwLength.longValue()));
			memory.setDoubleWordMemoryValue(t += 4, new LongValue(lpBuffer.dwMemoryLoad.longValue()));
			memory.setDoubleWordMemoryValue(t += 4, new LongValue(lpBuffer.dwTotalPhys.longValue()));
			memory.setDoubleWordMemoryValue(t += 4, new LongValue(lpBuffer.dwAvailPhys.longValue()));
			memory.setDoubleWordMemoryValue(t += 4, new LongValue(lpBuffer.dwTotalPageFile.longValue()));
			memory.setDoubleWordMemoryValue(t += 4, new LongValue(lpBuffer.dwAvailPageFile.longValue()));
			memory.setDoubleWordMemoryValue(t += 4, new LongValue(lpBuffer.dwTotalVirtual.longValue()));
			memory.setDoubleWordMemoryValue(t += 4, new LongValue(lpBuffer.dwAvailVirtual.longValue()));
		}
		return false;
	}

}
