package v2.org.analysis.apihandle.winapi.structures;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.BaseTSD;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.win32.StdCallLibrary;

import java.util.Arrays;
import java.util.List;

/**
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
		public LPVOID /* PRTL_CRITICAL_SECTION_DEBUG */DebugInfo = null;
		public LONG LockCount;
		public LONG RecursionCount;
		public HANDLE OwningThread; // from the thread's ClientId->UniqueThread
		public HANDLE LockSemaphore;
		public ULONG_PTR SpinCount; // force size on 64-bit systems when packed

		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "DebugInfo", "LockCount", "RecursionCount", "OwningThread",
					"LockSemaphore", "SpinCount" });
		}

		public RTL_CRITICAL_SECTION() {
		}
		
		public static class ByReference extends RTL_CRITICAL_SECTION implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		public RTL_CRITICAL_SECTION(Pointer memory) {
			super(memory);
			read();
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
	
	public class PROCESS_BASIC_INFORMATION extends Structure {
		public LONG ExitStatus;
		public PEB PebBaseAddress;
		public ULONG_PTR AffinityMask;
		public LONG BasePriority;
		public ULONG_PTR UniqueProcessId;
		public ULONG_PTR InheritedFromUniqueProcessId;

		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "ExitStatus", "PebBaseAddress", "AffinityMask", "BasePriority", "UniqueProcessId", "InheritedFromUniqueProcessId" });
		}

		public static class ByReference extends PROCESS_BASIC_INFORMATION implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		public PROCESS_BASIC_INFORMATION() {

		}

		public PROCESS_BASIC_INFORMATION(Pointer memory) {
			super(memory);
			read();
		}
	}
	
	public class PEB extends Structure{
		public byte Reserved1[] = new byte[2];
		public byte BeingDebugged;
		public byte Reserved2[] = new byte[1];
		public PVOID Reserved3[] = new PVOID[2];
		public /*smPPEB_LDR_DATA*/ Pointer Ldr;
		public /*smPRTL_USER_PROCESS_PARAMETERS*/ Pointer ProcessParameters;
		public byte Reserved4[] = new byte[104];
		public PVOID Reserved5[] = new PVOID[52];
		public /*smPPS_POST_PROCESS_INIT_ROUTINE*/ ULONG PostProcessInitRoutine;
		public byte Reserved6[] = new byte[128];
		public PVOID Reserved7[] = new PVOID[1];
		public ULONG SessionId;
		
		protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "Reserved1", "BeingDebugged", "Reserved2", 
					"Reserved3", "Ldr", "ProcessParameters",
					"Reserved4", "Reserved5", "PostProcessInitRoutine",
					"Reserved6", "Reserved7", "SessionId"});
		}

		public static class ByReference extends PEB implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		public PEB() {

		}

		public PEB(Pointer memory) {
			super(memory);
			read();
		}
	}
	
	public class UNICODE_STRING extends Structure {
		public USHORT Length;
		public USHORT MaximumLength;
		public WString  Buffer;
	    
	    protected List<String> getFieldOrder() {
			return Arrays.asList(new String[] { "Length", "MaximumLength", "Buffer"});
		}

		public static class ByReference extends UNICODE_STRING implements Structure.ByReference {
			public ByReference() {
			}

			public ByReference(Pointer memory) {
				super(memory);
			}
		}

		public UNICODE_STRING() {

		}

		public UNICODE_STRING(Pointer memory) {
			super(memory);
			read();
		}
	}
}
