/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32
 * File name: Gdi32DLL.java
 * Created date: Aug 7, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.gdi32;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.apihandle.winapi.structures.Wingdi.PALETTEENTRY;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.GDI32;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

/**
 * @author Yen Nguyen
 *
 */
public interface Gdi32DLL extends StdCallLibrary {

	Gdi32DLL INSTANCE = (Gdi32DLL) Native.loadLibrary("gdi32", Gdi32DLL.class, W32APIOptions.DEFAULT_OPTIONS);
	Gdi32DLL SYNC_INSTANCE = (Gdi32DLL) Native.synchronizedLibrary(INSTANCE);

	/**
	 * The AnimatePalette function replaces entries in the specified logical
	 * palette.
	 * 
	 * @param hpal
	 *            A handle to the logical palette.
	 * 
	 * @param iStartIndex
	 *            The first logical palette entry to be replaced.
	 * 
	 * @param cEntries
	 *            The number of entries to be replaced.
	 * 
	 * @param pe
	 *            A pointer to the first member in an array of PALETTEENTRY
	 *            structures used to replace the current entries.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is zero.
	 */
	int AnimatePalette(
	/* _In_ */HANDLE hpal,
	/* _In_ */UINT iStartIndex,
	/* _In_ */UINT cEntries,
	/* _In_ */PALETTEENTRY pe);
}
