/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32
 * File name: Gdi32DLL.java
 * Created date: Aug 7, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.gdi32;

import v2.org.analysis.apihandle.winapi.structures.Wingdi.PALETTEENTRY;

import com.sun.jna.Native;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HDC;
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

	/**
	 * The AddFontResource function adds the font resource from the specified
	 * file to the system font table. The font can subsequently be used for text
	 * output by any application.
	 * 
	 * @param lpszFilename
	 *            A pointer to a null-terminated character string that contains
	 *            a valid font file name. This parameter can specify any of the
	 *            following files.
	 * 
	 * @return If the function succeeds, the return value specifies the number
	 *         of fonts added. If the function fails, the return value is zero.
	 *         No extended error information is available.
	 */
	int AddFontResource(/* _In_ */WString lpszFilename);

	/**
	 * The GetStockObject function retrieves a handle to one of the stock pens,
	 * brushes, fonts, or palettes.
	 * 
	 * @param fnObject
	 *            The type of stock object. This parameter can be one of the
	 *            following values.
	 * 
	 * @return If the function succeeds, the return value is a handle to the
	 *         requested logical object. If the function fails, the return value
	 *         is NULL.
	 */
	HANDLE GetStockObject(/* _In_ */int fnObject);

	/**
	 * The GdiGetBatchLimit function returns the maximum number of function
	 * calls that can be accumulated in the calling thread's current batch. The
	 * system flushes the current batch whenever this limit is exceeded.
	 * 
	 * @return If the function succeeds, the return value is the batch limit. If
	 *         the function fails, the return value is zero.
	 */
	DWORD GdiGetBatchLimit();

	/**
	 * The GetBkColor function returns the current background color for the
	 * specified device context.
	 * 
	 * @param hdc
	 *            Handle to the device context whose background color is to be
	 *            returned.
	 * 
	 * @return If the function succeeds, the return value is a COLORREF value
	 *         for the current background color. If the function fails, the
	 *         return value is CLR_INVALID.
	 */
	DWORD GetBkColor(HDC hdc);
}
