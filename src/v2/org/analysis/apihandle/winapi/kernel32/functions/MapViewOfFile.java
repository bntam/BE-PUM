/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: MapViewOfFile.java
 * Created date: Feb 4, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Maps a view of a file mapping into the address space of a calling process.
 * 
 * @param hFileMappingObject
 *            A handle to a file mapping object. The CreateFileMapping and
 *            OpenFileMapping functions return this handle.
 * 
 * @param dwDesiredAccess
 *            The type of access to a file mapping object, which determines the
 *            protection of the pages.
 * 
 * @param dwFileOffsetHigh
 *            A high-order DWORD of the file offset where the view begins.
 * 
 * @param dwFileOffsetLow
 *            A low-order DWORD of the file offset where the view is to begin.
 * 
 * @param dwNumberOfBytesToMap
 *            The number of bytes of a file mapping to map to the view.
 * 
 * @return If the function succeeds, the return value is the starting address of
 *         the mapped view. If the function fails, the return value is NULL. To
 *         get extended error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class MapViewOfFile extends Kernel32API {

	/**
	 * 
	 */
	public MapViewOfFile() {
		
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Register register = env.getRegister();

		/*
		 * HANDLE hFileMappingObject, // file-mapping object to map into address
		 * space DWORD dwDesiredAccess, // access mode DWORD dwFileOffsetHigh,
		 * // high-order 32 bits of file offset DWORD dwFileOffsetLow, //
		 * low-order 32 bits of file offset DWORD dwNumberOfBytesToMap // number
		 * of bytes to map
		 */
		Value x1 = stack.pop();
		Value x2 = stack.pop();
		Value x3 = stack.pop();
		Value x4 = stack.pop();
		Value x5 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5);

		// symbolValueRegister.mov("eax", 1);
		if (x1 instanceof LongValue && x2 instanceof LongValue && x3 instanceof LongValue && x4 instanceof LongValue
				&& x5 instanceof LongValue) {

			long t1 = ((LongValue) x1).getValue();
			HANDLE hFileMappingObject = new HANDLE(t1 != 0 ? new Pointer(t1) : Pointer.NULL);
			int dwDesiredAccess = (int) ((LongValue) x2).getValue();
			int dwFileOffsetHigh = (int) ((LongValue) x3).getValue();
			int dwFileOffsetLow = (int) ((LongValue) x4).getValue();
			int dwNumberOfBytesToMap = (int) ((LongValue) x5).getValue();

			System.out.println("Handle File:" + t1 + ", Access Mode:" + dwDesiredAccess
					+ ", High-order 32 bits of file offset:" + dwFileOffsetHigh
					+ ", Low-order 32 bits of file offset :" + dwFileOffsetLow + ", Number of bytes to map:"
					+ dwNumberOfBytesToMap);

			Pointer ret = Kernel32.INSTANCE.MapViewOfFile(hFileMappingObject, dwDesiredAccess, dwFileOffsetHigh,
					dwFileOffsetLow, dwNumberOfBytesToMap);

			System.out.println("Base Address: " + Pointer.nativeValue(ret));
			register.mov("eax", new LongValue(Pointer.nativeValue(ret)));
		}

		return false;
	}

}
