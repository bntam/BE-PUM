package v2.org.analysis.apihandle.winapi.advapi32;

import com.sun.jna.Native;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTRByReference;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.DWORDByReference;
import com.sun.jna.platform.win32.WinDef.UINT;
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

	/**
	 * The CryptDeriveKey function generates cryptographic session keys derived
	 * from a base data value. This function guarantees that when the same
	 * cryptographic service provider (CSP) and algorithms are used, the keys
	 * generated from the same base data are identical. The base data can be a
	 * password or any other user data.
	 * 
	 * @param hProv
	 *            A HCRYPTPROV handle of a CSP created by a call to
	 *            CryptAcquireContext.
	 * 
	 * @param Algid
	 *            An ALG_ID structure that identifies the symmetric encryption
	 *            algorithm for which the key is to be generated. The algorithms
	 *            available will most likely be different for each CSP. For more
	 *            information about which algorithm identifier is used by the
	 *            different providers for the key specs AT_KEYEXCHANGE and
	 *            AT_SIGNATURE, see ALG_ID.
	 * 
	 * @param hBaseData
	 *            A handle to a hash object that has been fed the exact base
	 *            data.
	 * 
	 * @param dwFlags
	 *            Specifies the type of key generated.
	 * 
	 * @param phKey
	 *            A pointer to a HCRYPTKEY variable to receive the address of
	 *            the handle of the newly generated key. When you have finished
	 *            using the key, release the handle by calling the
	 *            CryptDestroyKey function.
	 * 
	 * @return If the function succeeds, the function returns nonzero (TRUE). If
	 *         the function fails, it returns zero (FALSE). For extended error
	 *         information, call GetLastError.
	 */
	BOOL CryptDeriveKey(/* _In_ *//* HCRYPTPROV */ULONG_PTR hProv, /* _In_ *//* ALG_ID */UINT Algid, /* _In_ *//* HCRYPTHASH */
			ULONG_PTR hBaseData, /* _In_ */DWORD dwFlags, /* _Inout_ *//* HCRYPTKEY */ULONG_PTRByReference phKey);

	/**
	 * The CryptDestroyHash function destroys the hash object referenced by the
	 * hHash parameter. After a hash object has been destroyed, it can no longer
	 * be used.
	 * 
	 * @param hHash
	 *            The handle of the hash object to be destroyed.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is zero. For extended error
	 *         information, call GetLastError.
	 */
	BOOL CryptDestroyHash(/* _In_ *//* HCRYPTHASH */ULONG_PTR hHash);

	/**
	 * The CryptCreateHash function initiates the hashing of a stream of data.
	 * It creates and returns to the calling application a handle to a
	 * cryptographic service provider (CSP) hash object. This handle is used in
	 * subsequent calls to CryptHashData and CryptHashSessionKey to hash session
	 * keys and other streams of data.
	 * 
	 * @param hProv
	 *            A handle to a CSP created by a call to CryptAcquireContext.
	 * 
	 * @param Algid
	 *            An ALG_ID value that identifies the hash algorithm to use.
	 * 
	 * @param hKey
	 *            If the type of hash algorithm is a keyed hash, such as the
	 *            Hash-Based Message Authentication Code (HMAC) or Message
	 *            Authentication Code (MAC) algorithm, the key for the hash is
	 *            passed in this parameter. For nonkeyed algorithms, this
	 *            parameter must be set to zero.
	 * 
	 * @param dwFlags
	 * 
	 * @param phHash
	 *            The address to which the function copies a handle to the new
	 *            hash object. When you have finished using the hash object,
	 *            release the handle by calling the CryptDestroyHash function.
	 * 
	 * @return If the function succeeds, the function returns TRUE. If the
	 *         function fails, it returns FALSE. For extended error information,
	 *         call GetLastError.
	 */
	BOOL CryptCreateHash(/* _In_ *//* HCRYPTPROV */ULONG_PTR hProv, /* _In_ *//* ALG_ID */UINT Algid, /* _In_ *//* HCRYPTKEY */
			ULONG_PTR hKey, /* _In_ */DWORD dwFlags, /* _Out_ *//* HCRYPTHASH */ULONG_PTRByReference phHash);

	/**
	 * The CryptDecrypt function decrypts data previously encrypted by using the
	 * CryptEncrypt function.
	 * 
	 * @param hKey
	 *            A handle to the key to use for the decryption. An application
	 *            obtains this handle by using either the CryptGenKey or
	 *            CryptImportKey function.
	 * 
	 * @param hHash
	 *            A handle to a hash object. If data is to be decrypted and
	 *            hashed simultaneously, a handle to a hash object is passed in
	 *            this parameter. The hash value is updated with the decrypted
	 *            plaintext. This option is useful when simultaneously
	 *            decrypting and verifying a signature.
	 * 
	 * @param Final
	 *            A Boolean value that specifies whether this is the last
	 *            section in a series being decrypted. This value is TRUE if
	 *            this is the last or only block. If this is not the last block,
	 *            this value is FALSE. For more information, see Remarks.
	 * 
	 * @param dwFlags
	 *            The following flag values are defined. CRYPT_OAEP 0x00000040
	 *            || CRYPT_DECRYPT_RSA_NO_PADDING_CHECK 0x00000020
	 * 
	 * @param pbData
	 *            A pointer to a buffer that contains the data to be decrypted.
	 *            After the decryption has been performed, the plaintext is
	 *            placed back into this same buffer.
	 * 
	 * @param pdwDataLen
	 *            A pointer to a DWORD value that indicates the length of the
	 *            pbData buffer. Before calling this function, the calling
	 *            application sets the DWORD value to the number of bytes to be
	 *            decrypted. Upon return, the DWORD value contains the number of
	 *            bytes of the decrypted plaintext.
	 * 
	 * @return If the function succeeds, the function returns nonzero (TRUE). If
	 *         the function fails, it returns zero (FALSE). For extended error
	 *         information, call GetLastError.
	 */
	BOOL CryptDecrypt(/* _In_ *//* HCRYPTKEY */ULONG_PTR hKey, /* _In_ *//* HCRYPTHASH */ULONG_PTR hHash, /* _In_ */
			BOOL Final, /* _In_ */DWORD dwFlags, /* _Inout_ */ByteByReference pbData, /* _Inout_ */
			DWORDByReference pdwDataLen);

	/**
	 * The CryptDestroyKey function releases the handle referenced by the hKey
	 * parameter. After a key handle has been released, it is no longer valid
	 * and cannot be used again.
	 * 
	 * @param hKey
	 *            The handle of the key to be destroyed.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is zero. For extended error
	 *         information, call GetLastError.
	 */
	BOOL CryptDestroyKey(/* _In_ *//* HCRYPTKEY */ULONG_PTR hKey);

	/**
	 * The CryptReleaseContext function releases the handle of a cryptographic
	 * service provider (CSP) and a key container. At each call to this
	 * function, the reference count on the CSP is reduced by one. When the
	 * reference count reaches zero, the context is fully released and it can no
	 * longer be used by any function in the application.
	 * 
	 * @param hProv
	 *            Handle of a cryptographic service provider (CSP) created by a
	 *            call to CryptAcquireContext.
	 * 
	 * @param dwFlags
	 *            Reserved for future use and must be zero. If dwFlags is not
	 *            set to zero, this function returns FALSE but the CSP is
	 *            released.
	 * 
	 * @return If the function succeeds, the return value is nonzero (TRUE). If
	 *         the function fails, the return value is zero (FALSE). For
	 *         extended error information, call GetLastError.
	 */
	BOOL CryptReleaseContext(/* _In_ *//* HCRYPTPROV */ULONG_PTR hProv, /* _In_ */DWORD dwFlags);

}
