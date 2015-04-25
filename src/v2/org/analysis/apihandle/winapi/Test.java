/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi
 * File name: Test.java
 * Created date: Jan 30, 2015
 */
package v2.org.analysis.apihandle.winapi;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.LONG;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32DLL;
import v2.org.analysis.apihandle.winapi.structures.WinNT.RTL_CRITICAL_SECTION;
import v2.org.analysis.apihandle.winapi.user32.User32DLL;
import v2.org.analysis.path.BPState;

//import com.sun.jna.platform.win32.WinBase.STARTUPINFO;

/**
 * @author SEGFRY
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// int x = Kernel32DLL.INSTANCE.WinExec("Cpp.exe", 1);
		BPState curState = null;

		// Environment env = curState.getEnvironement();
		// Stack stack = env.getStack();
		// Memory memory = env.getMemory();
		// Register register = env.getRegister();
		// Program program = Program.getProgram();

		long x = 10;
		// Long x = (long) Kernel32.INSTANCE.GetFileAttributes("Cpp.exe");
		// x =
		// Kernel32DLL.INSTANCE.GetProcAddress(Kernel32DLL.INSTANCE.LoadLibraryA("kernel32.dll"),
		// "GetLastError");
		// x = (long) Kernel32.INSTANCE.GetLastError();
		// Kernel32.INSTANCE.DeleteFile("C:\\Users\\SEGFRY\\Desktop\\SQL.sql");
		// SYSTEMTIME lpSystemTime = new SYSTEMTIME();
		// Kernel32.INSTANCE.GetSystemTime(lpSystemTime);

		// OSVERSIONINFO lpVersionInfo = new OSVERSIONINFO();
		// Kernel32.INSTANCE.GetVersionEx(lpVersionInfo);

		/** ReadFile & GetFileType API **/
		// HANDLE hFile = Kernel32.INSTANCE.CreateFile("E:\\text.txt",
		// -2147483648, 1, null, 3, 128, null); // for read
		// System.out.println(Kernel32.INSTANCE.GetFileType(hFile));
		// ByteBuffer lpBuffer = ByteBuffer.allocate(1024);
		// OVERLAPPED lpOverlapped = new OVERLAPPED();
		//
		// boolean ret = Kernel32.INSTANCE.ReadFile(hFile, lpBuffer, 9, new
		// IntByReference(0), lpOverlapped);
		// try {
		// String str = new String(lpBuffer.array(), "UTF-8");
		// System.out.println("String: " + str);
		// } catch (UnsupportedEncodingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		/** Write API **/
		// HANDLE hFile = Kernel32.INSTANCE.CreateFile("E:\\text2.txt",
		// 1073741824, 0, null, 1, 128, null); //for write
		// String str = "Anh khong thuoc ve...";
		// boolean ret = Kernel32.INSTANCE.WriteFile(hFile, str.getBytes(),
		// str.getBytes().length, new IntByReference(0),
		// new OVERLAPPED());
		// Kernel32DLL.INSTANCE.SetEndOfFile(hFile);
		// Kernel32.INSTANCE.CloseHandle(hFile);

		/** OPEN REG **/
		// HKEYByReference phkResult = new HKEYByReference();
		// Advapi32.INSTANCE.RegOpenKeyEx(WinReg.HKEY_CURRENT_USER, "Console",
		// 0, WinNT.KEY_ALL_ACCESS, phkResult);

		/** POSTMESSEAGE **/
		// User32DLL.INSTANCE.PostMessage(new HWND(new Pointer(10)), 0, new
		// WPARAM(0), new LPARAM(10));
		// System.out.println(User32DLL.INSTANCE.SendMessage(new HWND(new
		// Pointer(10)), 0, new WPARAM(0), new LPARAM(10)).longValue());

		/** STRING **/
		// System.out.println(Kernel32DLL.INSTANCE.lstrcat(null, "World!!! "));
		// System.out.println(Kernel32DLL.INSTANCE.lstrcmp("abc", "aad"));
		// System.out.println(Kernel32DLL.INSTANCE.lstrlen(null));

		/** FILE **/
		// Kernel32.INSTANCE.CopyFile("E:\\text.txt", "E:\\text3.txt", false);
		// Kernel32.INSTANCE.MoveFile("E:\\text3.txt", "E:\\text1.txt");

		// Kernel32DLL.INSTANCE.ExitProcess(10000);

		// x =
		// Pointer.nativeValue(Kernel32DLL.INSTANCE.GetEnvironmentStrings());
		// x = Kernel32DLL.INSTANCE.FreeEnvironmentStrings(new
		// Pointer(x)).longValue();

		// System.out.println(Kernel32DLL.INSTANCE.GetCommandLine());
		// System.out.println(Kernel32DLL.INSTANCE.IsDebuggerPresent());

		// STARTUPINFO lpStartupInfo = new STARTUPINFO();
		// Kernel32DLL.INSTANCE.GetStartupInfo(lpStartupInfo);
		// System.out.println("Address: " +
		// Pointer.nativeValue(lpStartupInfo.lpTitle) + "lpTitle: " + (new
		// Pointer(Pointer.nativeValue(lpStartupInfo.lpTitle))).getWideString(0));
		// x = Pointer.nativeValue(hD.getPointer());

		// HANDLE hD = Kernel32DLL.INSTANCE.GetStdHandle(new DWORD(-12));

		// x = Kernel32.INSTANCE.GetVersion().longValue();

		// User32DLL.INSTANCE.MessageBox(null, "Click any button!", "Hello", new
		// UINT(310));

		// Kernel32DLL.INSTANCE.SetHandleCount(new UINT(1000000000));

		// WIN32_FIND_DATA FindFileData = new WIN32_FIND_DATA();
		// HANDLE hFind;
		// hFind = Kernel32DLL.INSTANCE.FindFirstFile(new WString("E:\\*"),
		// FindFileData);
		// BOOL ret = Kernel32DLL.INSTANCE.FindClose(hFind);

		// char[] lpBuffer = new char[100];
		// Kernel32DLL.INSTANCE.GetCurrentDirectory(new DWORD(100), lpBuffer);
		// System.out.println("GetCurrentDirectory: " + new String(lpBuffer));

		// char[] lpBuffer = new char[100];
		// Kernel32DLL.INSTANCE.GetSystemDirectory(lpBuffer, new UINT(100));
		// System.out.println("GetSystemDirectory: " + new String(lpBuffer));

		// char[] lpBuffer = new char[100];
		// Kernel32DLL.INSTANCE.GetWindowsDirectory(lpBuffer, new UINT(100));
		// System.out.println("GetWindowsDirectory: " + new String(lpBuffer));

		// char[] lpBuffer = new char[100];
		// IntByReference lpnSize = new IntByReference(100);
		// Kernel32.INSTANCE.GetComputerName(lpBuffer, lpnSize);
		// System.out.println("GetComputerName: " + new String(lpBuffer));

		// String lpDirectoryName = "C:\\";
		// LongByReference lpFreeBytesAvailable = new LongByReference();
		// LongByReference lpTotalNumberOfBytes = new LongByReference();
		// LongByReference lpTotalNumberOfFreeBytes = new LongByReference();
		// Kernel32.INSTANCE.GetDiskFreeSpaceEx(lpDirectoryName,
		// lpFreeBytesAvailable, lpTotalNumberOfBytes,
		// lpTotalNumberOfFreeBytes);
		// System.out.println("GetDiskFreeSpaceEx: " + lpDirectoryName +
		// " lpFreeBytesAvailable "
		// + lpFreeBytesAvailable.getValue() + " lpTotalNumberOfBytes " +
		// lpTotalNumberOfBytes.getValue()
		// + " lpTotalNumberOfFreeBytes " +
		// lpTotalNumberOfFreeBytes.getValue());

		// HANDLE hProcess = Kernel32.INSTANCE.GetCurrentProcess();
		// IntByReference Wow64Process = new IntByReference(1);
		// Kernel32.INSTANCE.IsWow64Process(hProcess, Wow64Process);
		// System.out.println("Wow64Process: " + Wow64Process.getValue());

		// char[] abc = ("abcd").toCharArray();
		// String str = "abcd";
		// System.out.println(User32DLL.INSTANCE.CharUpperBuff(abc, new
		// DWORD(2)));
		// System.out.println(abc);

		// RTL_CRITICAL_SECTION lpCriticalSection = new RTL_CRITICAL_SECTION();
		// //lpCriticalSection.DebugInfo = new LPVOID(0);
		// // lpCriticalSection.LockCount = new LONG(10);
		// // lpCriticalSection.LockSemaphore = new HANDLE();
		// // lpCriticalSection.OwningThread = new HANDLE();
		// // lpCriticalSection.RecursionCount = new LONG(100);
		// lpCriticalSection.SpinCount = new ULONG_PTR(10);
		// Kernel32DLL.INSTANCE.EnterCriticalSection(lpCriticalSection );

		// WString lpRootPathName = new WString("");
		// DWORDByReference lpSectorsPerCluster = new DWORDByReference(new
		// DWORD());
		// DWORDByReference lpBytesPerSector = new DWORDByReference(new
		// DWORD());
		// DWORDByReference lpNumberOfFreeClusters = new DWORDByReference(new
		// DWORD());
		// DWORDByReference lpTotalNumberOfClusters = new DWORDByReference(new
		// DWORD());
		// Kernel32DLL.INSTANCE.GetDiskFreeSpace(lpRootPathName,
		// lpSectorsPerCluster, lpBytesPerSector,
		// lpNumberOfFreeClusters, lpTotalNumberOfClusters);

		// String className = findClassName("kernel32", "DeleteFile");
		// if (className != null) {
		// try {
		// Class<?> clazz = Class.forName(className);
		// Constructor<?> ctor = clazz.getConstructor();
		// API api = (API) ctor.newInstance();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		

		char[] lpBuffer = new char[127];
		DWORD retzzz = Kernel32DLL.INSTANCE.GetCurrentDirectory(new DWORD(127), lpBuffer);
		String retttt = new String(lpBuffer);
		System.out.println("Char: " + retttt);
		
//		String pMessage = new String("%1!*.*s! %4 %5!*s!");
//		String pArgs[] = { "4", "2", "Bill",  // %1!*.*s! refers back to the first insertion string in pMessage
//			"Bob",                                                // %4 refers back to the second insertion string in pMessage
//			"6", "Bill" };                               // %5!*s! refers back to the third insertion string in pMessage
//		char buffer[] = new char[101];
//
//
//		Kernel32DLL.INSTANCE.FormatMessageW(new DWORD(9216),
//			pMessage, 
//			null,
//			null,
//			buffer, 
//			new DWORD(101), 
//			pArgs);
//
//		System.out.println(new String(buffer));
//		
//		char[] buf = new char[100];
//		long[] ar = {10, 20};
//		Kernel32DLL.INSTANCE.wsprintf(buf, "%s", ar);

		char[] input = new char[] { 'A', '\0' };
		Pointer abc = User32DLL.INSTANCE.CharLower(input);
		x = abc.getShort(0);
		byte t1 = (byte) (x & 0xFF);
		char ret = (char) t1;
		System.out.println("Char: " + ret);

		System.out.println("Code: " + x);
		System.out.println("Error: " + Kernel32.INSTANCE.GetLastError());
		x = (long) 1;
	}
}

class A {
	public A() {
	}

	public int foo1() {
		Kernel32.INSTANCE.GetModuleHandle("");
		int rett = Kernel32.INSTANCE.GetLastError();
		System.out.println("ID : " + Thread.currentThread().getId());
		return rett;
	}

	public int foo2() {
		int ret = Kernel32.INSTANCE.GetLastError();
		System.out.println("ID : " + Thread.currentThread().getId());
		return ret;
	}
}

class NewThread1 extends Thread {
	private RTL_CRITICAL_SECTION _lpCriticalSection;
	
	public NewThread1(RTL_CRITICAL_SECTION lpCriticalSection) {
		this._lpCriticalSection = lpCriticalSection;
		start(); // bắt đầu thread
	}

	// This is the entry point for the Thread.
	public void run() {
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("1: " + (new A()).foo1());
		this._lpCriticalSection.LockCount = new LONG(100);
	}
}

class NewThread2 extends Thread {
	private RTL_CRITICAL_SECTION _lpCriticalSection;
	
	public NewThread2(RTL_CRITICAL_SECTION lpCriticalSection) {
		this._lpCriticalSection = lpCriticalSection;
		start(); // bắt đầu thread
	}

	// This is the entry point for the Thread.
	public void run() {
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("2: " + (new A()).foo2());
		this._lpCriticalSection.LockCount = new LONG(200);
	}
}
