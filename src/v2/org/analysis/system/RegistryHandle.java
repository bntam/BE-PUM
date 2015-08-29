package v2.org.analysis.system;

import java.util.HashMap;
import java.util.Map;

import v2.org.analysis.util.PairEntry;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinReg.HKEY;

/**
 * This class handle all the registry that not belong to the real registry of
 * system. In other words, this class contain all the registry (handle, key,
 * value) created by input binary file.
 * 
 * @author Yen Nguyen
 *
 */
public class RegistryHandle {
	/**
	 * This map store all the handle created by API
	 */
	private static Map<Long, PairEntry<PredefinedKeys, String>> handleMap = new HashMap<Long, PairEntry<PredefinedKeys, String>>();

	/**
	 * This map store all the registry value that not belong to the real
	 * registry of system
	 */
	private static Map<PredefinedKeys, Map<String, PairEntry<KeyValueType, char[]>>> registryMap = new HashMap<PredefinedKeys, Map<String, PairEntry<KeyValueType, char[]>>>();

	/**
	 * Add a new key handle
	 * 
	 * @param newKey
	 *            A pointer to a variable that receives a handle to the opened
	 *            key.
	 * 
	 * @param hKey
	 *            A handle to an open registry key. This handle is returned by
	 *            the RegCreateKeyEx or RegOpenKeyEx function, or it can be one
	 *            of the following predefined keys: HKEY_CLASSES_ROOT
	 *            HKEY_CURRENT_CONFIG HKEY_CURRENT_USER HKEY_LOCAL_MACHINE
	 *            HKEY_USERS
	 * 
	 * @param subKey
	 *            The name of the registry key to be opened. This key must be a
	 *            subkey of the key identified by the hKey parameter. Key names
	 *            are not case sensitive. If this parameter is NULL or a pointer
	 *            to an empty string, the function returns the same handle that
	 *            was passed in.
	 */
	public static void addHandle(HKEY newKey, long hKey, String subKey) {
		if (newKey != null) {
			handleMap.put(Pointer.nativeValue(newKey.getPointer()), new PairEntry<PredefinedKeys, String>(
					PredefinedKeys.fromLong(hKey), subKey));
		}
	}

	/**
	 * Remove the handle from map (after it had been closed)
	 * 
	 * @param hKey
	 *            A pointer to a variable that handle to the opened key.
	 */
	public static void removeHandle(HKEY hKey) {
		if (hKey != null) {
			handleMap.remove(Pointer.nativeValue(hKey.getPointer()));
		}
	}

	/**
	 * Get key entry (contains PredefinedKeys and value) by the variable that
	 * handle to the opened key. (undefined object)
	 * 
	 * @param hKey
	 *            The variable that handle to the opened key.
	 * 
	 * @return KeyPairEntry if exists, otherwise NULL.
	 */
	private static PairEntry<PredefinedKeys, String> getKeyPairEntry(Object hKey) {
		if (hKey == null)
			return null;

		if (hKey instanceof HKEY) {
			return getKeyPairEntry(Pointer.nativeValue(((HKEY) hKey).getPointer()));
		} else if (hKey instanceof Long) {
			return getKeyPairEntry(((Long) hKey).longValue());
		} else {
			return null;
		}
	}

	/**
	 * Get key entry (contains PredefinedKeys and value) by the variable that
	 * handle to the opened key. (defined object)
	 * 
	 * @param hKey
	 *            The variable that handle to the opened key.
	 * 
	 * @return KeyPairEntry if exists, otherwise NULL.
	 */
	private static PairEntry<PredefinedKeys, String> getKeyPairEntry(long hKey) {
		return handleMap.get(hKey);
	}

	/**
	 * Set existed registry value or put a new registry value.
	 * 
	 * @param hKey
	 *            The variable that handle to the opened key.
	 * 
	 * @param name
	 *            The name of registry want to affect.
	 * 
	 * @param value
	 *            The new value of affected registry.
	 */
	public static void setRegValue(Object hKey, String name, long type, char[] value) {
		PairEntry<PredefinedKeys, String> entryKey = getKeyPairEntry(hKey);

		if (entryKey == null)
			return;

		PredefinedKeys key = entryKey.getKey();
		String path = String.format("%s\\%s", entryKey.getValue(), name);

		Map<String, PairEntry<KeyValueType, char[]>> regDataMap = registryMap.get(key);

		if (regDataMap == null) {
			regDataMap = new HashMap<String, PairEntry<KeyValueType, char[]>>();
			registryMap.put(key, regDataMap);
		}

		if (regDataMap != null)
			regDataMap.put(path, new PairEntry<KeyValueType, char[]>(KeyValueType.fromInt((int) type), value));
	}

	/**
	 * Query registry value in virtual registry management.
	 * 
	 * @param hKey
	 *            The variable that handle to the opened key.
	 * 
	 * @param name
	 *            The name of registry want to take value.
	 * 
	 * @return char type array of existed value, or NULL if not exists.
	 */
	public static PairEntry<KeyValueType, char[]> queryRegValue(Object hKey, String name) {
		PairEntry<PredefinedKeys, String> entryKey = getKeyPairEntry(hKey);

		if (entryKey == null)
			return null;

		PredefinedKeys key = entryKey.getKey();
		String path = String.format("%s\\%s", entryKey.getValue(), name);

		Map<String, PairEntry<KeyValueType, char[]>> regMap = registryMap.get(key);

		if (regMap != null)
			return regMap.get(path);

		return null;
	}

	/**
	 * An application must open a key before it can add data to the registry. To
	 * open a key, an application must supply a handle to another key in the
	 * registry that is already open. The system defines predefined keys that
	 * are always open. Predefined keys help an application navigate in the
	 * registry and make it possible to develop tools that allow a system
	 * administrator to manipulate categories of data. Applications that add
	 * data to the registry should always work within the framework of
	 * predefined keys, so administrative tools can find and use the new data.
	 * 
	 * An application can use handles to these keys as entry points to the
	 * registry. These handles are valid for all implementations of the
	 * registry, although the use of the handles may vary from platform to
	 * platform. In addition, other predefined handles have been defined for
	 * specific platforms. The following are handles to the predefined keys.
	 * 
	 * @author Yen Nguyen
	 *
	 */
	public static enum PredefinedKeys {
		// @formatter:off
		NONE(0x0L), //
		HKEY_CLASSES_ROOT(0x80000000L), //
		HKEY_CURRENT_USER(0x80000001L), //
		HKEY_LOCAL_MACHINE(0x80000002L), //
		HKEY_USERS(0x80000003L), //
		HKEY_PERFORMANCE_DATA(0x80000004L), //
		HKEY_PERFORMANCE_TEXT(0x80000050L), //
		HKEY_PERFORMANCE_NLSTEXT(0x80000060L), //
		HKEY_CURRENT_CONFIG(0x80000005L), //
		HKEY_DYN_DATA(0x80000006L), //
		HKEY_CURRENT_USER_LOCAL_SETTINGS(0x80000007L);
		// @formatter:on

		private static final Map<Long, PredefinedKeys> longToTypeMap = new HashMap<Long, PredefinedKeys>();

		static {
			for (PredefinedKeys type : PredefinedKeys.values()) {
				longToTypeMap.put(type.value, type);
			}
		}

		private long value = 0;

		PredefinedKeys(long value) {
			this.value = value;
		}

		public long getValue() {
			return this.value;
		}

		public static PredefinedKeys fromLong(long i) {
			PredefinedKeys type = longToTypeMap.get(Long.valueOf(i));
			if (type == null)
				return PredefinedKeys.NONE;
			return type;
		}
	}

	/**
	 * A registry value can store data in various formats. When you store data
	 * under a registry value, for instance by calling the RegSetValueEx
	 * function, you can specify one of the following values to indicate the
	 * type of data being stored. When you retrieve a registry value, functions
	 * such as RegQueryValueEx use these values to indicate the type of data
	 * retrieved.
	 * 
	 * The following registry value types are defined in Winnt.h.
	 * 
	 * @author Yen Nguyen
	 *
	 */
	public static enum KeyValueType {
		// @formatter:off
		REG_NONE(0) // No value type
		, REG_SZ(1) // Unicode nul terminated string
		, REG_EXPAND_SZ(2) // Unicode nul terminated string (with environment variable references)
		, REG_BINARY(3) // Free form binary
		, REG_DWORD(4) // 32-bit number
		, REG_DWORD_LITTLE_ENDIAN(4) // 32-bit number (same as REG_DWORD)
		, REG_DWORD_BIG_ENDIAN(5) // 32-bit number
		, REG_LINK(6) // Symbolic Link (unicode)
		, REG_MULTI_SZ(7) // Multiple Unicode strings
		, REG_RESOURCE_LIST(8) // Resource list in the resource map
		, REG_FULL_RESOURCE_DESCRIPTOR(9) // Resource list in the hardware description
		, REG_RESOURCE_REQUIREMENTS_LIST(10) //
		, REG_QWORD(11) // 64-bit number
		, REG_QWORD_LITTLE_ENDIAN(11); // 64-bit number (same as REG_QWORD)
		// @formatter:on

		private static final Map<Integer, KeyValueType> intToTypeMap = new HashMap<Integer, KeyValueType>();

		static {
			for (KeyValueType type : KeyValueType.values()) {
				intToTypeMap.put(type.value, type);
			}
		}

		private int value = 0;

		KeyValueType(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}

		public static KeyValueType fromInt(int i) {
			KeyValueType type = intToTypeMap.get(Integer.valueOf(i));
			if (type == null)
				return KeyValueType.REG_NONE;
			return type;
		}
	}
}
