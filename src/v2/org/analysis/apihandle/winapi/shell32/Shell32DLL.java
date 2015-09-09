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
import com.sun.jna.platform.win32.WinDef.HICON;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

/**
 * @author Yen Nguyen
 *
 */
public interface Shell32DLL extends ShellAPI, StdCallLibrary {
	Shell32DLL INSTANCE = (Shell32DLL) Native.loadLibrary("shell32", Shell32.class, W32APIOptions.DEFAULT_OPTIONS);

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

	/**
	 * Displays a ShellAbout dialog box.
	 * 
	 * @param hWnd
	 *            A window handle to a parent window. This parameter can be
	 *            NULL.
	 * 
	 * @param szApp
	 *            A pointer to a null-terminated string that contains text to be
	 *            displayed in the title bar of the ShellAbout dialog box and on
	 *            the first line of the dialog box after the text "Microsoft".
	 *            If the text contains a separator (#) that divides it into two
	 *            parts, the function displays the first part in the title bar
	 *            and the second part on the first line after the text
	 *            "Microsoft".
	 * 
	 * @param szOtherStuff
	 *            A pointer to a null-terminated string that contains text to be
	 *            displayed in the dialog box after the version and copyright
	 *            information. This parameter can be NULL.
	 * 
	 * @param hIcon
	 *            The handle of an icon that the function displays in the dialog
	 *            box. This parameter can be NULL, in which case the function
	 *            displays the Windows icon.
	 * 
	 * @return TRUE if successful; otherwise, FALSE.
	 */
	int ShellAbout(
	/* _In_opt_ */HWND hWnd,
	/* _In_ */String szApp,
	/* _In_opt_ */String szOtherStuff,
	/* _In_opt_ */HICON hIcon);
}
