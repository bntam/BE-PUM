/**
 * 
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef.HBITMAP;
import com.sun.jna.platform.win32.WinDef.HINSTANCE;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;

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
 * [LoadBitmap is available for use in the operating systems specified in the
 * Requirements section. It may be altered or unavailable in subsequent
 * versions. Instead, use LoadImage and DrawFrameControl.] The LoadBitmap
 * function loads the specified bitmap resource from a module's executable file.
 * 
 * @param hInstance
 *            A handle to the instance of the module whose executable file
 *            contains the bitmap to be loaded.
 * 
 * @param lpBitmapName
 *            A pointer to a null-terminated string that contains the name of
 *            the bitmap resource to be loaded. Alternatively, this parameter
 *            can consist of the resource identifier in the low-order word and
 *            zero in the high-order word. The MAKEINTRESOURCE macro can be used
 *            to create this value.
 * 
 * @return If the function succeeds, the return value is the handle to the
 *         specified bitmap. If the function fails, the return value is NULL.
 * 
 * @author Yen Nguyen
 *
 */
public class LoadBitmap extends User32API {

	public LoadBitmap() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		Value x2 = stack.pop();
		System.out.print("Argument:" + x1 + " " + x2);
		if (x1 instanceof LongValue && x2 instanceof LongValue) {

			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();

			HINSTANCE hInstance = new HINSTANCE();
			hInstance.setPointer(new Pointer(t1));
			WString lpBitmapName = new WString(memory.getText(new X86MemoryOperand(DataType.INT32, t2)));
			HBITMAP ret = User32DLL.INSTANCE.LoadBitmap(hInstance, lpBitmapName);

			long value = (ret == null) ? 0 : Pointer.nativeValue(ret.getPointer());
			register.mov("eax", new LongValue(value));
			System.out.println("Return Value: " + value);
		}
		return false;
	}

}
