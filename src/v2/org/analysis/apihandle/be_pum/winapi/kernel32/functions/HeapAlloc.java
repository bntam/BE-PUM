/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: HeapAlloc.java
 * Created date: Feb 7, 2015
 * Description:
 */
package v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.SIZE_T;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.LPVOID;
import com.sun.jna.platform.win32.WinNT.HANDLE;

import v2.org.analysis.apihandle.be_pum.winapi.kernel32.Kernel32API;
import v2.org.analysis.apihandle.be_pum.winapi.kernel32.Kernel32DLL;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * Allocates a block of memory from a heap. The allocated memory is not movable.
 * 
 * @param hHeap
 *            : A handle to the heap from which the memory will be allocated.
 *            This handle is returned by the HeapCreate or GetProcessHeap
 *            function.
 * 
 * @param dwFlags
 *            : The heap allocation options. Specifying any of these values will
 *            override the corresponding value specified when the heap was
 *            created with HeapCreate. This parameter can be one or more of the
 *            following values.
 * 
 * @param dwBytes
 *            : The number of bytes to be allocated.
 * 
 * @return <p>
 *         If the function succeeds, the return value is a pointer to the
 *         allocated memory block.
 *         </p>
 *         <p>
 *         If the function fails and you have not specified
 *         <strong>HEAP_GENERATE_EXCEPTIONS</strong>, the return value is
 *         <strong>NULL</strong>.
 *         </p>
 *         <p>
 *         If the function fails and you have specified
 *         <strong>HEAP_GENERATE_EXCEPTIONS</strong>, the function may generate
 *         either of the exceptions listed in the following table. The
 *         particular exception depends upon the nature of the heap corruption.
 *         For more information, see <a href=
 *         "https://msdn.microsoft.com/en-us/library/windows/desktop/ms679356(v=vs.85).aspx"
 *         ><strong
 *         xmlns="http://www.w3.org/1999/xhtml">GetExceptionCode</strong ></a>.
 *         </p>
 * 
 * @author Yen Nguyen
 *
 */
public class HeapAlloc extends Kernel32API {
	public HeapAlloc() {

	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName,
			BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Register register = env.getRegister();

		/*
		 * HANDLE hHeap, // handle to the private heap block DWORD dwFlags, //
		 * heap allocation control flags DWORD dwBytes // number of bytes to
		 * allocate
		 */
		Value x1 = stack.pop();
		Value x2 = stack.pop();
		Value x3 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2 + " " + x3);

		if (x1 instanceof LongValue && x2 instanceof LongValue
				&& x3 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			long t3 = ((LongValue) x3).getValue();

			LPVOID ret = Kernel32DLL.INSTANCE.HeapAlloc(new HANDLE(new Pointer(
					t1)), new DWORD(t2), new SIZE_T(t3));

			long value = (ret == null) ? 0 : Pointer.nativeValue(ret.toPointer());
			register.mov("eax", new LongValue(value));
			System.out.println("Return Value: " + value);
		}

		return false;
	}

}
