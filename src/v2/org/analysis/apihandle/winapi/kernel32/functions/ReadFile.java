/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: ReadFile.java
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

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * Reads data from the specified file or input/output (I/O) device. Reads occur
 * at the position specified by the file pointer if supported by the device.
 * 
 * This function is designed for both synchronous and asynchronous operations.
 * For a similar function designed solely for asynchronous operation, see
 * ReadFileEx
 * 
 * @param hFile
 *            A handle to the device (for example, a file, file stream, physical
 *            disk, volume, console buffer, tape drive, socket, communications
 *            resource, mailslot, or pipe).
 * 
 * @param lpBuffer
 *            A pointer to the buffer that receives the data read from a file or
 *            device.
 * 
 * @param nNumberOfBytesToRead
 *            The maximum number of bytes to be read.
 * 
 * @param lpNumberOfBytesRead
 *            A pointer to the variable that receives the number of bytes read
 *            when using a synchronous hFile parameter
 * 
 * @param lpOverlapped
 *            A pointer to an OVERLAPPED structure is required if the hFile
 *            parameter was opened with FILE_FLAG_OVERLAPPED, otherwise it can
 *            be NULL.
 * 
 * @return If the function succeeds, the return value is nonzero (TRUE). If the
 *         function fails, or is completing asynchronously, the return value is
 *         zero (FALSE). To get extended error information, call the
 *         GetLastError function.
 * 
 *         Note The GetLastError code ERROR_IO_PENDING is not a failure; it
 *         designates the read operation is pending completion asynchronously.
 *         For more information, see Remarks.
 * 
 * @author Yen Nguyen
 *
 */
public class ReadFile extends Kernel32API {

	/**
	 * 
	 */
	public ReadFile() {
		
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();

		/*
		 * HANDLE hFile, // handle of file to read LPVOID lpBuffer, // address
		 * of buffer that receives data DWORD nNumberOfBytesToRead, // number of
		 * bytes to read LPDWORD lpNumberOfBytesRead, // address of number of
		 * bytes read LPOVERLAPPED lpOverlapped // address of structure for data
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

			System.out.println("Handle File:" + t1 + ", Address of Buffer:" + t2 + ", Number of Byte:" + t3
					+ ", Number of Actual Read Bytes:" + t4 + ", Overlapped:" + t5);

			HANDLE hFile = new HANDLE(t1 != 0 ? new Pointer(t1) : Pointer.NULL);
			// Pointer lpBuffer = new Pointer(t2);
			int nNumberOfBytesToRead = (int) t3;
			ByteBuffer lpBuffer = ByteBuffer.allocate(nNumberOfBytesToRead + 1);
			IntByReference lpNumberOfBytesRead = new IntByReference((int) t4);

			OVERLAPPED lpOverlapped = null;

			if (t5 != 0) {
				lpOverlapped = new OVERLAPPED();
				lpOverlapped.Internal = new ULONG_PTR(((LongValue) memory.getDoubleWordMemoryValue(t5)).getValue());
				lpOverlapped.InternalHigh = new ULONG_PTR(
						((LongValue) memory.getDoubleWordMemoryValue(t5 += 4)).getValue());
				lpOverlapped.Offset = (int) (((LongValue) memory.getDoubleWordMemoryValue(t5 += 4)).getValue());
				lpOverlapped.OffsetHigh = (int) (((LongValue) memory.getDoubleWordMemoryValue(t5 += 4)).getValue());
				lpOverlapped.hEvent = new HANDLE(new Pointer(
						((LongValue) memory.getDoubleWordMemoryValue(t5 += 4)).getValue()));
				t5 = ((LongValue) x5).getValue();
			}

			boolean ret = Kernel32.INSTANCE.ReadFile(hFile, lpBuffer, nNumberOfBytesToRead, lpNumberOfBytesRead,
					lpOverlapped);
			
			register.mov("eax", new LongValue(ret ? 1 : 0));


			try {
				byte[] bufferArray = lpBuffer.array();
				for (int i = 0; i < bufferArray.length; i++) {
					memory.setByteMemoryValue(new X86MemoryOperand(DataType.INT8, t2 + i), new LongValue((long)bufferArray[i]));
				}
				//System.out.println("Data: " + str);
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("ReadFile is done");
			
			if (t4 != 0) {
				memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t4), new LongValue(
						lpNumberOfBytesRead.getValue()));
			}

			if (t5 != 0) {
				memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t5), new LongValue(
						lpOverlapped.Internal.longValue()));
				memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t5 += 4), new LongValue(
						lpOverlapped.InternalHigh.longValue()));
				memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t5 += 4), new LongValue(
						lpOverlapped.Offset));
				memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t5 += 4), new LongValue(
						lpOverlapped.OffsetHigh));
				memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t5 += 4),
						new LongValue(Pointer.nativeValue(lpOverlapped.hEvent.getPointer())));
			}
		}

		return false;
	}
}
