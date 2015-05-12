/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: WriteFile.java
 * Created date: Feb 4, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinBase.OVERLAPPED;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

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
 * Writes data to the specified file or input/output (I/O) device.
 * 
 * @param hFile
 *            A handle to the file or I/O device (for example, a file, file
 *            stream, physical disk, volume, console buffer, tape drive, socket,
 *            communications resource, mailslot, or pipe).
 * 
 * @param lpBuffer
 *            A pointer to the buffer containing the data to be written to the
 *            file or device.
 * 
 * @param nNumberOfBytesToWrite
 *            The number of bytes to be written to the file or device.
 * 
 * @param lpNumberOfBytesWritten
 *            A pointer to the variable that receives the number of bytes
 *            written when using a synchronous hFile parameter.
 * 
 * @param lpOverlapped
 *            A pointer to an OVERLAPPED structure is required if the hFile
 *            parameter was opened with FILE_FLAG_OVERLAPPED, otherwise this
 *            parameter can be NULL.
 * 
 * @return If the function succeeds, the return value is nonzero (TRUE). If the
 *         function fails, or is completing asynchronously, the return value is
 *         zero (FALSE). To get extended error information, call the
 *         GetLastError function.
 * 
 * @author Yen Nguyen
 *
 */
public class WriteFile extends Kernel32API {

	/**
	 * 
	 */
	public WriteFile() {
		
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();

		/*
		 * HANDLE hFile, // handle to file to write to LPCVOID lpBuffer, //
		 * pointer to data to write to file DWORD nNumberOfBytesToWrite, //
		 * number of bytes to write LPDWORD lpNumberOfBytesWritten, // pointer
		 * to number of bytes written LPOVERLAPPED lpOverlapped // pointer to
		 * structure needed for overlapped I/O
		 */

		Value x1 = stack.pop();
		Value x2 = stack.pop();
		Value x3 = stack.pop();
		Value x4 = stack.pop();
		Value x5 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5);

		if (x1 instanceof LongValue && x2 instanceof LongValue && x3 instanceof LongValue && x4 instanceof LongValue
				&& x5 instanceof LongValue) {
			/*
			 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
			 * x1).getValue(), ((ValueLongExp) x2).getValue(), program);
			 */
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			long t3 = ((LongValue) x3).getValue();
			long t4 = ((LongValue) x4).getValue();
			long t5 = ((LongValue) x5).getValue();

			String str = memory.getText(new X86MemoryOperand(DataType.INT32, t2));

			System.out.println("Handle File:" + t1 + ", String written:" + str + ", Number of Byte:" + t3
					+ ", Pointer:" + t4 + ", Overlapped:" + t5);

			HANDLE hFile = new HANDLE(t1 != 0L ? new Pointer(t1) : Pointer.NULL);
			byte[] lpBuffer = str.getBytes();
			int nNumberOfBytesToWrite = (int) t3;
			IntByReference lpNumberOfBytesWritten = new IntByReference((int) t4);
			
			OVERLAPPED lpOverlapped = new OVERLAPPED();
			if (t5 != 0L) {
				lpOverlapped.Internal = new ULONG_PTR(((LongValue) memory.getDoubleWordMemoryValue(t5)).getValue());
				lpOverlapped.InternalHigh = new ULONG_PTR(
						((LongValue) memory.getDoubleWordMemoryValue(t5 + 4)).getValue());
				lpOverlapped.Offset = (int) (((LongValue) memory.getDoubleWordMemoryValue(t5 + 8)).getValue());
				lpOverlapped.OffsetHigh = (int) (((LongValue) memory.getDoubleWordMemoryValue(t5 + 12)).getValue());
				lpOverlapped.hEvent = new HANDLE(new Pointer(
						((LongValue) memory.getDoubleWordMemoryValue(t5 + 16)).getValue()));
			}

			boolean ret = Kernel32.INSTANCE.WriteFile(hFile, lpBuffer, nNumberOfBytesToWrite, lpNumberOfBytesWritten,
					lpOverlapped);

			register.mov("eax", new LongValue(ret ? 1 : 0));
		}

		return false;
	}
}
