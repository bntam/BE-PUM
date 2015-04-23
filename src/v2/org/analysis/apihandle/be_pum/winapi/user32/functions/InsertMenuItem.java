/**
 * 
 */
package v2.org.analysis.apihandle.be_pum.winapi.user32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.HBITMAP;
import com.sun.jna.platform.win32.WinDef.HMENU;
import com.sun.jna.platform.win32.WinDef.UINT;
import v2.org.analysis.apihandle.be_pum.winapi.structures.WinUser.MENUITEMINFO;
import v2.org.analysis.apihandle.be_pum.winapi.user32.User32API;
import v2.org.analysis.apihandle.be_pum.winapi.user32.User32DLL;
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
 * Inserts a new menu item at the specified position in a menu.
 * 
 * @param hMenu
 *            A handle to the menu in which the new menu item is inserted.
 * 
 * @param uItem
 *            The identifier or position of the menu item before which to insert
 *            the new item. The meaning of this parameter depends on the value
 *            of fByPosition.
 * 
 * @param fByPosition
 *            Controls the meaning of uItem. If this parameter is FALSE, uItem
 *            is a menu item identifier. Otherwise, it is a menu item position.
 *            See Accessing Menu Items Programmatically for more information.
 * 
 * @param lpmii
 *            A pointer to a MENUITEMINFO structure that contains information
 *            about the new menu item.
 * 
 * @return If the function succeeds, the return value is nonzero.
 * 
 * @author Yen Nguyen
 *
 */
public class InsertMenuItem extends User32API {

	public InsertMenuItem() {
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
		Value x4 = stack.pop();

		System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4);

		if (x1 instanceof LongValue && x2 instanceof LongValue
				&& x3 instanceof LongValue && x4 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			long t3 = ((LongValue) x3).getValue();
			long t4 = ((LongValue) x4).getValue();

			HMENU hMenu = new HMENU(new Pointer(t1));
			UINT uItem = new UINT(t2);
			BOOL fByPosition = new BOOL(t3);
			MENUITEMINFO lpmii = new MENUITEMINFO();
			// public UINT cbSize;
			// public UINT fMask;
			// public UINT fType; // used if MIIM_TYPE (4.0) or MIIM_FTYPE
			// (>4.0)
			// public UINT fState; // used if MIIM_STATE
			// public UINT wID; // used if MIIM_ID
			// public HMENU hSubMenu; // used if MIIM_SUBMENU
			// public HBITMAP hbmpChecked; // used if MIIM_CHECKMARKS
			// public HBITMAP hbmpUnchecked; // used if MIIM_CHECKMARKS
			// public ULONG_PTR dwItemData; // used if MIIM_DATA
			// public WString dwTypeData;
			// public UINT cch; // used if MIIM_TYPE (4.0) or MIIM_STRING (>4.0)
			// public HBITMAP hbmpItem; // used if MIIM_BITMAP
			lpmii.cbSize = new UINT(
					((LongValue) memory
							.getDoubleWordMemoryValue(new X86MemoryOperand(
									DataType.INT32, t4))).getValue());
			lpmii.fMask = new UINT(
					((LongValue) memory
							.getDoubleWordMemoryValue(new X86MemoryOperand(
									DataType.INT32, t4 += 4))).getValue());
			lpmii.fType = new UINT(
					((LongValue) memory
							.getDoubleWordMemoryValue(new X86MemoryOperand(
									DataType.INT32, t4 += 4))).getValue());
			lpmii.fState = new UINT(
					((LongValue) memory
							.getDoubleWordMemoryValue(new X86MemoryOperand(
									DataType.INT32, t4 += 4))).getValue());
			lpmii.wID = new UINT(
					((LongValue) memory
							.getDoubleWordMemoryValue(new X86MemoryOperand(
									DataType.INT32, t4 += 4))).getValue());
			lpmii.hSubMenu = new HMENU(new Pointer(
					((LongValue) memory
							.getDoubleWordMemoryValue(new X86MemoryOperand(
									DataType.INT32, t4 += 4))).getValue()));
			lpmii.hbmpChecked = new HBITMAP(new Pointer(
					((LongValue) memory
							.getDoubleWordMemoryValue(new X86MemoryOperand(
									DataType.INT32, t4 += 4))).getValue()));
			lpmii.hbmpUnchecked = new HBITMAP(new Pointer(
					((LongValue) memory
							.getDoubleWordMemoryValue(new X86MemoryOperand(
									DataType.INT32, t4 += 4))).getValue()));
			lpmii.dwItemData = new ULONG_PTR(
					((LongValue) memory
							.getDoubleWordMemoryValue(new X86MemoryOperand(
									DataType.INT32, t4 += 4))).getValue());
			long strPtr = ((LongValue) memory
					.getDoubleWordMemoryValue(new X86MemoryOperand(
							DataType.INT32, t4 += 40))).getValue();
			lpmii.dwTypeData = new WString(
					(strPtr != 0) ? memory.getText(new X86MemoryOperand(
							DataType.INT32, strPtr)) : null);
			lpmii.cch = new UINT(
					((LongValue) memory
							.getDoubleWordMemoryValue(new X86MemoryOperand(
									DataType.INT32, t4 += 4))).getValue());
			lpmii.hbmpItem = new HBITMAP(new Pointer(
					((LongValue) memory
							.getDoubleWordMemoryValue(new X86MemoryOperand(
									DataType.INT32, t4 += 4))).getValue()));

			BOOL ret = User32DLL.INSTANCE.InsertMenuItem(hMenu, uItem,
					fByPosition, lpmii);

			register.mov("eax", new LongValue(ret.longValue()));
		}
		return false;
	}

}
