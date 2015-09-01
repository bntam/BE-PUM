/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.user32.functions
 * File name: SendMessage.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.HINSTANCE;
import com.sun.jna.platform.win32.WinUser.WNDCLASSEX;

import v2.org.analysis.apihandle.winapi.user32.User32API;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.value.LongValue;

/**
 * Retrieves information about a window class, including a handle to the small
 * icon associated with the window class. The GetClassInfo function does not
 * retrieve a handle to the small icon.
 * 
 * @param hinst
 *            A handle to the instance of the application that created the
 *            class. To retrieve information about classes defined by the system
 *            (such as buttons or list boxes), set this parameter to NULL.
 * 
 * @param lpszClass
 *            The class name. The name must be that of a preregistered class or
 *            a class registered by a previous call to the RegisterClass or
 *            RegisterClassEx function. Alternatively, this parameter can be a
 *            class atom created by a previous call to RegisterClass or
 *            RegisterClassEx. The atom must be in the low-order word of
 *            lpszClass; the high-order word must be zero.
 * 
 * @param lpwcx
 *            A pointer to a WNDCLASSEX structure that receives the information
 *            about the class.
 * 
 * @return If the function finds a matching class and successfully copies the
 *         data, the return value is nonzero.
 * 
 * @author Yen Nguyen
 *
 */
public class GetClassInfoEx extends User32API {

	public GetClassInfoEx() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);

		HINSTANCE hinst = new HINSTANCE();
		hinst.setPointer(new Pointer(t1));
		WString lpszClass = new WString(memory.getText(new X86MemoryOperand(DataType.INT32, t2)));
		WNDCLASSEX lpwcx = new WNDCLASSEX();

		BOOL ret = User32DLL.INSTANCE.GetClassInfoEx(hinst, lpszClass, lpwcx);

		register.mov("eax", new LongValue(ret.longValue()));

		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t3), new LongValue(lpwcx.cbSize));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t3 += 4), new LongValue(lpwcx.style));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t3 += 4), new LongValue(0 /*
																										 * lpfnWndProc
																										 */));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t3 += 4), new LongValue(lpwcx.cbClsExtra));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t3 += 4), new LongValue(lpwcx.cbWndExtra));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t3 += 4),
				new LongValue(Pointer.nativeValue(lpwcx.hInstance.getPointer())));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t3 += 4),
				new LongValue(Pointer.nativeValue(lpwcx.hIcon.getPointer())));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t3 += 4),
				new LongValue(Pointer.nativeValue(lpwcx.hCursor.getPointer())));
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t3 += 4),
				new LongValue(Pointer.nativeValue(lpwcx.hbrBackground.getPointer())));
		long pointer = ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t3 += 4)))
				.getValue();
		memory.setText(new X86MemoryOperand(DataType.INT32, pointer), lpwcx.lpszMenuName);
		pointer = ((LongValue) memory.getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t3 += 4)))
				.getValue();
		memory.setText(new X86MemoryOperand(DataType.INT32, pointer), lpwcx.lpszClassName.toString());
		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t3 += 4),
				new LongValue(Pointer.nativeValue(lpwcx.hIconSm.getPointer())));
	}

}
