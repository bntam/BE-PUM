package v2.org.analysis.system;

import java.util.HashMap;
import java.util.Map;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinReg.HKEY;

public class RegistryHandle {
	private static Map<Long, Entry<PredefinedKeys, String>> handleMap = new HashMap<Long, Entry<PredefinedKeys, String>>();
	private static Map<PredefinedKeys, Map<String, char[]>> registryMap = new HashMap<RegistryHandle.PredefinedKeys, Map<String, char[]>>();

	public static void addHandle(HKEY newKey, long hKey, String subKey) {
		if (newKey != null) {
			handleMap.put(Pointer.nativeValue(newKey.getPointer()),
					new Entry<PredefinedKeys, String>(PredefinedKeys.fromLong(hKey), subKey));
		}
	}

	public static void removeHandle(HKEY hKey) {
		if (hKey != null) {
			handleMap.remove(Pointer.nativeValue(hKey.getPointer()));
		}
	}

	public static Entry<PredefinedKeys, String> getKeyEntry(Object hKey) {
		if (hKey == null)
			return null;

		if (hKey instanceof HKEY) {
			return getKeyEntry(Pointer.nativeValue(((HKEY) hKey).getPointer()));
		} else if (hKey instanceof Long) {
			return getKeyEntry(((Long) hKey).longValue());
		} else {
			return null;
		}
	}

	public static Entry<PredefinedKeys, String> getKeyEntry(long hKey) {
		return handleMap.get(hKey);
	}

	public static void setRegValue(Object hKey, String name, char[] value) {
		Entry<PredefinedKeys, String> entryKey = getKeyEntry(hKey);

		if (entryKey == null)
			return;

		PredefinedKeys key = entryKey.getKey();
		String path = String.format("%s\\%s", entryKey.getValue(), name);

		Map<String, char[]> regDataMap = registryMap.get(key);

		if (regDataMap == null) {
			regDataMap = new HashMap<String, char[]>();
			registryMap.put(key, regDataMap);
		}

		if (regDataMap != null)
			regDataMap.put(path, value);
	}

	public static char[] queryRegValue(Object hKey, String name) {
		Entry<PredefinedKeys, String> entryKey = getKeyEntry(hKey);

		if (entryKey == null)
			return null;

		PredefinedKeys key = entryKey.getKey();
		String path = String.format("%s\\%s", entryKey.getValue(), name);

		Map<String, char[]> regMap = registryMap.get(key);

		if (regMap != null)
			return regMap.get(path);

		return null;
	}

	public static enum PredefinedKeys {
		NONE(0x0L), HKEY_CLASSES_ROOT(0x80000000L), HKEY_CURRENT_USER(0x80000001L), HKEY_LOCAL_MACHINE(0x80000002L), HKEY_USERS(
				0x80000003L), HKEY_PERFORMANCE_DATA(0x80000004L), HKEY_PERFORMANCE_TEXT(0x80000050L), HKEY_PERFORMANCE_NLSTEXT(
				0x80000060L), HKEY_CURRENT_CONFIG(0x80000005L), HKEY_DYN_DATA(0x80000006L), HKEY_CURRENT_USER_LOCAL_SETTINGS(
				0x80000007L);

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

	static class Entry<K, V> {
		final K key;
		V value;

		Entry(K k, V v) {
			value = v;
			key = k;
		}

		public final K getKey() {
			return key;
		}

		public final V getValue() {
			return value;
		}

		public final V setValue(V newValue) {
			V oldValue = value;
			value = newValue;
			return oldValue;
		}

		public final boolean equals(Object o) {
			if (!(o instanceof Entry))
				return false;

			Entry e = (Entry) o;
			Object k1 = getKey();
			Object k2 = e.getKey();

			if (k1 == k2 || (k1 != null && k1.equals(k2))) {
				Object v1 = getValue();
				Object v2 = e.getValue();
				if (v1 == v2 || (v1 != null && v1.equals(v2)))
					return true;
			}
			return false;
		}
	}
}
