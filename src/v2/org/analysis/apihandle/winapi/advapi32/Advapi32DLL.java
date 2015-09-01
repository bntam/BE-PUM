package v2.org.analysis.apihandle.winapi.advapi32;

import java.nio.Buffer;

import com.sun.jna.Native;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTRByReference;
import com.sun.jna.platform.win32.WinBase.SECURITY_ATTRIBUTES;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.DWORDByReference;
import com.sun.jna.platform.win32.WinDef.LONG;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinDef.WORD;
import com.sun.jna.platform.win32.WinReg.HKEY;
import com.sun.jna.platform.win32.WinReg.HKEYByReference;
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

	/**
	 * Opens the specified registry key.
	 * 
	 * @param hKey
	 *            A handle to an open registry key. This handle is returned by
	 *            the RegCreateKeyEx or RegOpenKeyEx function, or it can be one
	 *            of the following predefined keys: HKEY_CLASSES_ROOT
	 *            HKEY_CURRENT_CONFIG HKEY_CURRENT_USER HKEY_LOCAL_MACHINE
	 *            HKEY_USERS
	 * 
	 * @param lpSubKey
	 *            The name of the registry key to be opened. This key must be a
	 *            subkey of the key identified by the hKey parameter. Key names
	 *            are not case sensitive. If this parameter is NULL or a pointer
	 *            to an empty string, the function returns the same handle that
	 *            was passed in.
	 * 
	 * @param phkResult
	 *            A pointer to a variable that receives a handle to the opened
	 *            key. If the key is not one of the predefined registry keys,
	 *            call the RegCloseKey function after you have finished using
	 *            the handle.
	 * 
	 * @return If the function succeeds, the return value is ERROR_SUCCESS. If
	 *         the function fails, the return value is a nonzero error code
	 *         defined in Winerror.h. You can use the FormatMessage function
	 *         with the FORMAT_MESSAGE_FROM_SYSTEM flag to get a generic
	 *         description of the error.
	 */
	LONG RegOpenKey(/* _In_ */HKEY hKey, /* _In_opt_ */String lpSubKey, /* _Out_ */HKEYByReference phkResult);

	/**
	 * Retrieves the type and data for the specified value name associated with
	 * an open registry key.
	 * 
	 * @param hKey
	 *            A handle to an open registry key. The key must have been
	 *            opened with the KEY_QUERY_VALUE access right. For more
	 *            information, see Registry Key Security and Access Rights.
	 * 
	 * @param lpValueName
	 *            The name of the registry value.
	 * 
	 * @param lpReserved
	 *            This parameter is reserved and must be NULL.
	 * 
	 * @param lpType
	 *            A pointer to a variable that receives a code indicating the
	 *            type of data stored in the specified value. For a list of the
	 *            possible type codes, see Registry Value Types. The lpType
	 *            parameter can be NULL if the type code is not required.
	 * 
	 * @param lpData
	 *            A pointer to a buffer that receives the value's data. This
	 *            parameter can be NULL if the data is not required.
	 * 
	 * @param lpcbData
	 *            A pointer to a variable that specifies the size of the buffer
	 *            pointed to by the lpData parameter, in bytes. When the
	 *            function returns, this variable contains the size of the data
	 *            copied to lpData. The lpcbData parameter can be NULL only if
	 *            lpData is NULL.
	 * 
	 * @return If the function succeeds, the return value is ERROR_SUCCESS. If
	 *         the function fails, the return value is a system error code. If
	 *         the lpData buffer is too small to receive the data, the function
	 *         returns ERROR_MORE_DATA. If the lpValueName registry value does
	 *         not exist, the function returns ERROR_FILE_NOT_FOUND.
	 */
	LONG RegQueryValueEx(
	/* _In_ */HKEY hKey,
	/* _In_opt_ */String lpValueName,
	/* _Reserved_ */DWORDByReference lpReserved,
	/* _Out_opt_ */DWORDByReference lpType,
	/* _Out_opt_ */Buffer lpData,
	/* _Inout_opt_ */DWORDByReference lpcbData);

	/**
	 * Creates the specified registry key. If the key already exists, the
	 * function opens it. Note that key names are not case sensitive.
	 * 
	 * @param hKey
	 *            A handle to an open registry key. The calling process must
	 *            have KEY_CREATE_SUB_KEY access to the key. For more
	 *            information, see Registry Key Security and Access Rights.
	 * 
	 * @param lpSubKey
	 *            The name of a subkey that this function opens or creates. The
	 *            subkey specified must be a subkey of the key identified by the
	 *            hKey parameter; it can be up to 32 levels deep in the registry
	 *            tree. For more information on key names, see Structure of the
	 *            Registry.
	 * 
	 * @param Reserved
	 *            This parameter is reserved and must be zero.
	 * 
	 * @param lpClass
	 *            The user-defined class type of this key. This parameter may be
	 *            ignored. This parameter can be NULL.
	 * 
	 * @param dwOptions
	 *            This parameter can be one of the following values.
	 * 
	 *            REG_OPTION_BACKUP_RESTORE 0x00000004L If this flag is set, the
	 *            function ignores the samDesired parameter and attempts to open
	 *            the key with the access required to backup or restore the key.
	 *            If the calling thread has the SE_BACKUP_NAME privilege
	 *            enabled, the key is opened with the ACCESS_SYSTEM_SECURITY and
	 *            KEY_READ access rights. If the calling thread has the
	 *            SE_RESTORE_NAME privilege enabled, beginning with Windows
	 *            Vista, the key is opened with the ACCESS_SYSTEM_SECURITY,
	 *            DELETE and KEY_WRITE access rights. If both privileges are
	 *            enabled, the key has the combined access rights for both
	 *            privileges. For more information, see Running with Special
	 *            Privileges.
	 * 
	 *            REG_OPTION_CREATE_LINK 0x00000002L Note Registry symbolic
	 *            links should only be used for for application compatibility
	 *            when absolutely necessary. This key is a symbolic link. The
	 *            target path is assigned to the L"SymbolicLinkValue" value of
	 *            the key. The target path must be an absolute registry path.
	 * 
	 *            REG_OPTION_NON_VOLATILE 0x00000000L This key is not volatile;
	 *            this is the default. The information is stored in a file and
	 *            is preserved when the system is restarted. The RegSaveKey
	 *            function saves keys that are not volatile.
	 * 
	 *            REG_OPTION_VOLATILE 0x00000001L All keys created by the
	 *            function are volatile. The information is stored in memory and
	 *            is not preserved when the corresponding registry hive is
	 *            unloaded. For HKEY_LOCAL_MACHINE, this occurs only when the
	 *            system initiates a full shutdown. For registry keys loaded by
	 *            the RegLoadKey function, this occurs when the corresponding
	 *            RegUnLoadKey is performed. The RegSaveKey function does not
	 *            save volatile keys. This flag is ignored for keys that already
	 *            exist. Note On a user selected shutdown, a fast startup
	 *            shutdown is the default behavior for the system.
	 * 
	 * @param samDesired
	 *            A mask that specifies the access rights for the key to be
	 *            created. For more information, see Registry Key Security and
	 *            Access Rights.
	 * 
	 * @param lpSecurityAttributes
	 *            A pointer to a SECURITY_ATTRIBUTES structure that determines
	 *            whether the returned handle can be inherited by child
	 *            processes. If lpSecurityAttributes is NULL, the handle cannot
	 *            be inherited.
	 * 
	 * @param phkResult
	 *            A pointer to a variable that receives a handle to the opened
	 *            or created key. If the key is not one of the predefined
	 *            registry keys, call the RegCloseKey function after you have
	 *            finished using the handle.
	 * 
	 * @param lpdwDisposition
	 *            A pointer to a variable that receives one of the following
	 *            disposition values.
	 * 
	 * @return If the function succeeds, the return value is ERROR_SUCCESS. If
	 *         the function fails, the return value is a nonzero error code
	 *         defined in Winerror.h. You can use the FormatMessage function
	 *         with the FORMAT_MESSAGE_FROM_SYSTEM flag to get a generic
	 *         description of the error.
	 */
	LONG RegCreateKeyEx(
	/* _In_ */HKEY hKey,
	/* _In_ */String lpSubKey,
	/* _Reserved_ */DWORD Reserved,
	/* _In_opt_ */String lpClass,
	/* _In_ */DWORD dwOptions,
	/* _In_ *//* REGSAM */WORD samDesired,
	/* _In_opt_ */SECURITY_ATTRIBUTES lpSecurityAttributes,
	/* _Out_ */HKEY phkResult,
	/* _Out_opt_ */DWORD lpdwDisposition);
}
