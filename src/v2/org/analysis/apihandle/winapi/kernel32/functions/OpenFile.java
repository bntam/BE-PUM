/**
 * 
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.platform.win32.WinDef.UINT;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.apihandle.winapi.structures.WinBase.OFSTRUCT;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.system.Storage;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Creates, opens, reopens, or deletes a file.
 * 
 * @param lpFileName
 *            The name of the file.
 * 
 * @param lpReOpenBuff
 *            A pointer to the OFSTRUCT structure that receives information
 *            about a file when it is first opened.
 * 
 * @param uStyle
 *            The action to be taken.
 * 
 * @return If the function succeeds, the return value specifies a file handle to
 *         use when performing file I/O. To close the file, call the CloseHandle
 *         function using this handle.
 * 
 * @author Yen Nguyen
 *
 */
public class OpenFile extends Kernel32API {

	public OpenFile() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName,
			BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		Value x2 = stack.pop();
		Value x3 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2 + " " + x3);

		if (x1 instanceof LongValue && x2 instanceof LongValue
				&& x3 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			long t3 = ((LongValue) x3).getValue();

			String lpFileName = memory.getText(new X86MemoryOperand(
					DataType.INT32, t1));
			lpFileName = Storage.getMappingPath(lpFileName);
			OFSTRUCT lpReOpenBuff = new OFSTRUCT();
			UINT uStyle = new UINT(t3);
			int ret = Kernel32DLL.INSTANCE.OpenFile(lpFileName, lpReOpenBuff,
					uStyle);

			register.mov("eax", new LongValue(ret));

			// public BYTE cBytes;
			// public BYTE fFixedDisk;
			// public WORD nErrCode;
			// public WORD Reserved1;
			// public WORD Reserved2;
			// public char szPathName[] = new char[128];

			memory.setByteMemoryValue(new X86MemoryOperand(DataType.INT32, t2),
					new LongValue(lpReOpenBuff.cBytes.longValue()));
			memory.setByteMemoryValue(new X86MemoryOperand(DataType.INT32,
					t2 += 1),
					new LongValue(lpReOpenBuff.fFixedDisk.longValue()));
			memory.setWordMemoryValue(new X86MemoryOperand(DataType.INT32,
					t2 += 1), new LongValue(lpReOpenBuff.nErrCode.longValue()));
			memory.setWordMemoryValue(new X86MemoryOperand(DataType.INT32,
					t2 += 2), new LongValue(lpReOpenBuff.Reserved1 == null ? 0 : lpReOpenBuff.Reserved1.longValue()));
			memory.setWordMemoryValue(new X86MemoryOperand(DataType.INT32,
					t2 += 2), new LongValue(lpReOpenBuff.Reserved2 == null ? 0 : lpReOpenBuff.Reserved2.longValue()));
			memory.setText(new X86MemoryOperand(DataType.INT32, t2 += 2),
					new String(lpReOpenBuff.szPathName));
		}
		return false;
	}

}
