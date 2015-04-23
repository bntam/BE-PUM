/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetDiskFreeSpaceEx.java
 * Created date: Mar 2, 2015
 * Description:
 */
package v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions;

import v2.org.analysis.apihandle.be_pum.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.be_pum.winapi.kernel32.Kernel32DLL;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.DWORDByReference;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Retrieves information about the specified disk, including the amount of free
 * space on the disk.
 * 
 * @param lpRootPathName
 *            The root directory of the disk for which information is to be
 *            returned. If this parameter is NULL, the function uses the root of
 *            the current disk. If this parameter is a UNC name, it must include
 *            a trailing backslash (for example, "\\MyServer\MyShare\").
 *            Furthermore, a drive specification must have a trailing backslash
 *            (for example, "C:\"). The calling application must have
 *            FILE_LIST_DIRECTORY access rights for this directory.
 * 
 * @param lpSectorsPerCluster
 *            A pointer to a variable that receives the number of sectors per
 *            cluster.
 * 
 * @param lpBytesPerSector
 *            A pointer to a variable that receives the number of bytes per
 *            sector.
 * 
 * @param lpNumberOfFreeClusters
 *            A pointer to a variable that receives the total number of free
 *            clusters on the disk that are available to the user who is
 *            associated with the calling thread. If per-user disk quotas are in
 *            use, this value may be less than the total number of free clusters
 *            on the disk.
 * 
 * @param lpTotalNumberOfClusters
 *            A pointer to a variable that receives the total number of clusters
 *            on the disk that are available to the user who is associated with
 *            the calling thread. If per-user disk quotas are in use, this value
 *            may be less than the total number of clusters on the disk.
 * 
 * @return If the function succeeds, the return value is nonzero.
 * 
 * @author Yen Nguyen
 *
 */
public class GetDiskFreeSpace extends Kernel32API {

	public GetDiskFreeSpace() {

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
		Value x4 = stack.pop();
		Value x5 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5);

		if (x1 instanceof LongValue && x2 instanceof LongValue && x3 instanceof LongValue && x4 instanceof LongValue
				&& x5 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			long t3 = ((LongValue) x3).getValue();
			long t4 = ((LongValue) x4).getValue();
			long t5 = ((LongValue) x5).getValue();

			WString lpRootPathName = new WString(memory.getText(new X86MemoryOperand(DataType.INT32, t1)));
			DWORDByReference lpSectorsPerCluster = new DWORDByReference(new DWORD(t2));
			DWORDByReference lpBytesPerSector = new DWORDByReference(new DWORD(t3));
			DWORDByReference lpNumberOfFreeClusters = new DWORDByReference(new DWORD(t4));
			DWORDByReference lpTotalNumberOfClusters = new DWORDByReference(new DWORD(t4));
			BOOL ret = Kernel32DLL.INSTANCE.GetDiskFreeSpace(lpRootPathName, lpSectorsPerCluster, lpBytesPerSector,
					lpNumberOfFreeClusters, lpTotalNumberOfClusters);

			register.mov("eax", new LongValue(ret.longValue()));

			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2), new LongValue(lpSectorsPerCluster
					.getValue().longValue()));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t3), new LongValue(lpBytesPerSector
					.getValue().longValue()));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t4), new LongValue(lpNumberOfFreeClusters
					.getValue().longValue()));
			memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t5), new LongValue(lpTotalNumberOfClusters
					.getValue().longValue()));
		}
		return false;
	}
}
