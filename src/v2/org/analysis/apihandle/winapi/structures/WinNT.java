package v2.org.analysis.apihandle.winapi.structures;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.BaseTSD;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.win32.StdCallLibrary;

import java.util.Arrays;
import java.util.List;

/**
 * Ported from WinNT.h (kernel32.dll/kernel services).
 * 
 * @author Yen Nguyen
 */
public interface WinNT extends StdCallLibrary, WinDef, BaseTSD {
	public static class PRTL_CRITICAL_SECTION extends Structure {
		public LONG LockCount;
		public LONG RecursionCount;
		public HANDLE OwningThread; // from the thread's ClientId->UniqueThread
		public HANDLE LockSemaphore;
		public ULONG_PTR SpinCount; // force size on 64-bit systems when packed

		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "LockCount", "RecursionCount", "OwningThread", "LockSemaphore",
					"SpinCount" });
		}
	}

	public class LIST_ENTRY extends Structure {
		public LPVOID Flink;
		public LPVOID Blink;

		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "Flink", "Blink" });
		}
	}

	public class RTL_CRITICAL_SECTION_DEBUG extends Structure {
		public WORD Type;
		public WORD CreatorBackTraceIndex;
		public LPVOID CriticalSection; // struct _RTL_CRITICAL_SECTION *
		public LIST_ENTRY ProcessLocksList;
		public DWORD EntryCount;
		public DWORD ContentionCount;
		public DWORD Flags;
		public WORD CreatorBackTraceIndexHigh;
		public WORD SpareWORD;

		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "Type", "CreatorBackTraceIndex", "CriticalSection", "ProcessLocksList",
					"EntryCount", "ContentionCount", "Flags", "CreatorBackTraceIndexHigh", "SpareWORD" });
		}
	}

	public class RTL_CRITICAL_SECTION extends Structure {
		public LPVOID /* PRTL_CRITICAL_SECTION_DEBUG */DebugInfo;
		public LONG LockCount;
		public LONG RecursionCount;
		public HANDLE OwningThread; // from the thread's ClientId->UniqueThread
		public HANDLE LockSemaphore;
		public ULONG_PTR SpinCount; // force size on 64-bit systems when packed

		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "DebugInfo", "LockCount", "RecursionCount", "OwningThread",
					"LockSemaphore", "SpinCount" });
		}
	}

	public class CPINFO extends Structure {
		public UINT MaxCharSize = new UINT(); // max length (in bytes) of a char
		public char DefaultChar[] = new char[2]; // default character
		public char LeadByte[] = new char[12]; // lead byte ranges

		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "MaxCharSize", "DefaultChar", "LeadByte" });
		}

		public static class ByReference extends CPINFO implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		public CPINFO() {

		}

		public CPINFO(Pointer memory) {
			super(memory);
			read();
		}
	}

	public class MEMORY_BASIC_INFORMATION extends Structure {
		public PVOID BaseAddress;
		public PVOID AllocationBase;
		public DWORD AllocationProtect;
		public SIZE_T RegionSize;
		public DWORD State;
		public DWORD Protect;
		public DWORD Type;

		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "BaseAddress", "AllocationBase", "AllocationProtect", "RegionSize",
					"State", "Protect", "Type" });
		}

		public static class ByReference extends MEMORY_BASIC_INFORMATION implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		public MEMORY_BASIC_INFORMATION() {

		}

		public MEMORY_BASIC_INFORMATION(Pointer memory) {
			super(memory);
			read();
		}
	}
}
