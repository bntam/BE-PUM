/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.comctl32
 * File name: Comctl32DLL.java
 * Created date: Aug 5, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.comctl32;

import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

/**
 * @author Yen Nguyen
 *
 */
public interface Comctl32DLL extends StdCallLibrary {
	Comctl32DLL INSTANCE = (Comctl32DLL) Native.loadLibrary("comctl32", Comctl32DLL.class);
	Comctl32DLL SYNC_INSTANCE = (Comctl32DLL) Native.synchronizedLibrary(INSTANCE);

	/**
	 * Registers and initializes certain common control window classes. This
	 * function is obsolete. New applications should use the
	 * InitCommonControlsEx function.
	 */
	void InitCommonControls();
}
