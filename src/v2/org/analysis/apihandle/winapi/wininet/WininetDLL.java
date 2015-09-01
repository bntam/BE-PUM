/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.wininet
 * File name: WininetDLL.java
 * Created date: Aug 28, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.wininet;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.LPVOID;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.win32.StdCallLibrary;

/**
 * @author Yen Nguyen
 *
 */
public interface WininetDLL extends StdCallLibrary {
	WininetDLL INSTANCE = (WininetDLL) Native.loadLibrary("wininet", WininetDLL.class);
	WininetDLL SYNC_INSTANCE = (WininetDLL) Native.synchronizedLibrary(INSTANCE);

	/**
	 * Sets an Internet option.
	 * 
	 * @param hInternet
	 *            Handle on which to set information.
	 * 
	 * @param dwOption
	 *            Internet option to be set. This can be one of the Option Flags
	 *            values.
	 * 
	 * @param lpBuffer
	 *            Pointer to a buffer that contains the option setting.
	 * 
	 * @param dwBufferLength
	 *            Size of the lpBuffer buffer. If lpBuffer contains a string,
	 *            the size is in TCHARs. If lpBuffer contains anything other
	 *            than a string, the size is in bytes.
	 * 
	 * @return Returns TRUE if successful, or FALSE otherwise. To get a specific
	 *         error message, call GetLastError.
	 */
	BOOL InternetSetOption(
	/* _In_ *//* HINTERNET */HANDLE hInternet,
	/* _In_ */DWORD dwOption,
	/* _In_ */LPVOID lpBuffer,
	/* _In_ */DWORD dwBufferLength);
}
