/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.shell32
 * File name: Shel32DLL.java
 * Created date: Aug 5, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.shell32;

import v2.org.analysis.apihandle.winapi.structures.ShellApi.SHFILEINFO;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.BaseTSD.DWORD_PTR;
import com.sun.jna.platform.win32.Shell32;
import com.sun.jna.platform.win32.ShellAPI;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

/**
 * @author Yen Nguyen
 *
 */
public interface Shel32DLL extends ShellAPI, StdCallLibrary {
	Shel32DLL INSTANCE = (Shel32DLL) Native.loadLibrary("shell32", Shell32.class, W32APIOptions.DEFAULT_OPTIONS);

	/**
	 * 
	 * @param pszPath
	 * @param dwFileAttributes
	 * @param psfi
	 * @param cbFileInfo
	 * @param uFlags
	 * @return
	 */
	DWORD_PTR SHGetFileInfo(
	/* _In_ */String pszPath, DWORD dwFileAttributes,
	/* _Inout_ */SHFILEINFO psfi, UINT cbFileInfo, UINT uFlags);

}
