package v2.org.phong.k32;

import v2.org.phong.macro.*;
import v2.org.phong.structure.FileHandle;
import v2.org.phong.structure.Handle;
import v2.org.phong.structure.SearchHandle;
import v2.org.phong.structure.ex.SECURITY_ATTRIBUTES;
import v2.org.phong.structure.ex.SYSTEM_TIME;
import v2.org.phong.structure.ex.WIN32_FIND_DATA;

import java.util.concurrent.atomic.AtomicReference;

public interface APIK32 {
	
	boolean CopyFileA(String existingFileName, String newFileName, boolean failIfExists);
	
	String GetCommandLine();

	double GetTickCount();
	
	int GetWindowsDirectory(AtomicReference<String> dirBuffer, int bufferSize);
	
	int GetSystemDirectory(AtomicReference<String> dirBuffer, int bufferSize);
	
	int GetCurrentDirectory(int bufferLength, AtomicReference<String> dirBuffer);
	
	SearchHandle FindFirstFile(String fileName, AtomicReference<WIN32_FIND_DATA> findFileData);
	
	boolean FindNextFile(SearchHandle searchHandle, AtomicReference<WIN32_FIND_DATA> findFileData);
	
	boolean FindClose(SearchHandle searchHandle);
	
	boolean CloseHandle(Handle handle);
	
	String lstrcat(String s1, String s2);
	
	int lstrcmp(String s1, String s2);
	
	int lstrlen(String s1);
	
	String lstrcpy(String s1, String s2);
	
	boolean DeleteFile(String fileName);
	
	boolean MoveFile(String existingFileName, String newFileName);
	
	void GetSystemTime(AtomicReference<SYSTEM_TIME> systemTime);
	
	FileHandle CreateFile(String fileName, DesiredAccess desiredAccess, ShareMode shareMode,
			SECURITY_ATTRIBUTES securityAttributes, CreationDistribution creationDistribution
			,FlagsAndAttributes flagAndAttributes, FileHandle templateFile);
	
	int _lwrite(FileHandle file, String buffer, int nBytes);
	
	long GetFileSize(FileHandle file, int fileSizeHigh);

	void GetLocalTime(AtomicReference<SYSTEM_TIME> localTime);
	
	FileAttribute GetFileAttributes(String fileName);
	
	FileType GetFileType(FileHandle fileHandle);

	boolean SetCurrentDirectory(String PathName);
}
