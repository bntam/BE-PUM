package v2.org.analysis.apihandle.winapi.advapi32;

import com.sun.jna.Native;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTRByReference;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

/**
 * @author Yen Nguyen
 *
 */
public interface Advapi32DLL extends StdCallLibrary {
	Advapi32DLL INSTANCE = (Advapi32DLL) Native.loadLibrary("advapi32", Advapi32DLL.class,
			W32APIOptions.DEFAULT_OPTIONS);
	Advapi32DLL SYNC_INSTANCE = (Advapi32DLL) Native.synchronizedLibrary(INSTANCE);

	/**
	 * The CryptAcquireContext function is used to acquire a handle to a
	 * particular key container within a particular cryptographic service
	 * provider (CSP). This returned handle is used in calls to CryptoAPI
	 * functions that use the selected CSP..
	 * 
	 * @param phProv
	 *            A pointer to a handle of a CSP. When you have finished using
	 *            the CSP, release the handle by calling the CryptReleaseContext
	 *            function.
	 * 
	 * @param pszContainer
	 *            The key container name. This is a null-terminated string that
	 *            identifies the key container to the CSP. This name is
	 *            independent of the method used to store the keys. Some CSPs
	 *            store their key containers internally (in hardware), some use
	 *            the system registry, and others use the file system. In most
	 *            cases, when dwFlags is set to CRYPT_VERIFYCONTEXT,
	 *            pszContainer must be set to NULL. However, for hardware-based
	 *            CSPs, such as a smart card CSP, can be access publically
	 *            available information in the specfied container.
	 * 
	 * @param pszProvider
	 *            A null-terminated string that contains the name of the CSP to
	 *            be used.
	 * 
	 * @param dwProvType
	 *            Specifies the type of provider to acquire. Defined provider
	 *            types are discussed in Cryptographic Provider Types.
	 * 
	 * @param dwFlags
	 *            Flag values. This parameter is usually set to zero, but some
	 *            applications set one or more of the following flags.
	 * 
	 * @return If the function succeeds, the function returns nonzero (TRUE). If
	 *         the function fails, it returns zero (FALSE). For extended error
	 *         information, call GetLastError.
	 */
	BOOL CryptAcquireContext(/* _Out_ *//* HCRYPTPROV * */ULONG_PTRByReference phProv, /* _In_ */WString pszContainer, /* _In_ */
			WString pszProvider, /* _In_ */DWORD dwProvType, /* _In_ */DWORD dwFlags);

	/**
	 * The CryptHashData function adds data to a specified hash object. This
	 * function and CryptHashSessionKey can be called multiple times to compute
	 * the hash of long or discontinuous data streams.
	 * 
	 * @param hHash
	 *            Handle of the hash object.
	 * 
	 * @param pbData
	 *            A pointer to a buffer that contains the data to be added to
	 *            the hash object.
	 * 
	 * @param dwDataLen
	 *            Number of bytes of data to be added. This must be zero if the
	 *            CRYPT_USERDATA flag is set.
	 * 
	 * @param dwFlags
	 *            The following flag values are defined.
	 * 
	 * @return If the function succeeds, the return value is TRUE. If the
	 *         function fails, the return value is FALSE. For extended error
	 *         information, call GetLastError.
	 */
	BOOL CryptHashData(/* _In_ *//* HCRYPTHASH */ULONG_PTR hHash, /* _In_ */ByteByReference pbData, /* _In_ */
			DWORD dwDataLen, /* _In_ */DWORD dwFlags);
}
