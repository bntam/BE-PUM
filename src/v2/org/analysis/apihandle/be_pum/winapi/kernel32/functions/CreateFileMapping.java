/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: CreateFileMapping.java
 * Created date: Feb 10, 2015
 * Description:
 */
package v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions;

import v2.org.analysis.apihandle.be_pum.winapi.kernel32.Kernel32API;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinBase.SECURITY_ATTRIBUTES;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Creates or opens a named or unnamed file mapping object for a specified file.
 * 
 * @param hFile
 *            A handle to the file from which to create a file mapping object.
 * 
 * @param lpAttributes
 *            A pointer to a SECURITY_ATTRIBUTES structure that determines
 *            whether a returned handle can be inherited by child processes. The
 *            lpSecurityDescriptor member of the SECURITY_ATTRIBUTES structure
 *            specifies a security descriptor for a new file mapping object.
 * 
 * @param flProtect
 *            Specifies the page protection of the file mapping object. All
 *            mapped views of the object must be compatible with this
 *            protection.
 * 
 * @param dwMaximumSizeHigh
 *            The high-order DWORD of the maximum size of the file mapping
 *            object.
 * 
 * @param dwMaximumSizeLow
 *            The low-order DWORD of the maximum size of the file mapping
 *            object.
 * 
 * @param lpName
 *            The name of the file mapping object.
 * 
 * @return If the function succeeds, the return value is a handle to the newly
 *         created file mapping object. If the object exists before the function
 *         call, the function returns a handle to the existing object (with its
 *         current size, not the specified size), and GetLastError returns
 *         ERROR_ALREADY_EXISTS. If the function fails, the return value is
 *         NULL. To get extended error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class CreateFileMapping extends Kernel32API {

	/**
	 * 
	 */
	public CreateFileMapping() {
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
		Value x6 = stack.pop();

		System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5 + " " + x6);

		if (x1 instanceof LongValue && x2 instanceof LongValue && x3 instanceof LongValue && x4 instanceof LongValue
				&& x5 instanceof LongValue && x6 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			long t3 = ((LongValue) x3).getValue();
			long t4 = ((LongValue) x4).getValue();
			long t5 = ((LongValue) x5).getValue();
			long t6 = ((LongValue) x6).getValue();

			HANDLE hFile = new HANDLE(new Pointer(t1));
			// TODO: Implement this
			SECURITY_ATTRIBUTES lpAttributes = null;
			int flProtect = (int) t3;
			int dwMaximumSizeHigh = (int) t4;
			int dwMaximumSizeLow = (int) t5;
			String lpName = (t6 != 0) ? memory.getText(new X86MemoryOperand(DataType.INT32, t6)) : null;

			System.out.println("Handle File:" + t1 + ", Security Attribute:" + t2 + ", Object Protection:" + t3
					+ ", Maximum Size High:" + t4 + ", Maximum Size Low:" + t5 + ", File Mapping Name Address:" + lpName);

			HANDLE ret = Kernel32.INSTANCE.CreateFileMapping(hFile, lpAttributes, flProtect, dwMaximumSizeHigh,
					dwMaximumSizeLow, lpName);

			long value = (ret == null) ? 0 : Pointer.nativeValue(ret.getPointer());
			register.mov("eax", new LongValue(value));
			System.out.println("Return Value: " + value);
		}
		return false;
	}

}
