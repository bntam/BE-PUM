package v2.org.phong.k32;

import v2.org.phong.macro.*;
import v2.org.phong.structure.FileHandle;
import v2.org.phong.structure.Handle;
import v2.org.phong.structure.SearchHandle;
import v2.org.phong.structure.ex.SECURITY_ATTRIBUTES;
import v2.org.phong.structure.ex.SYSTEM_TIME;
import v2.org.phong.structure.ex.WIN32_FIND_DATA;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class APIK32Implementation implements APIK32 {

	@Override
	public boolean CopyFileA(String existingFileName, String newFileName, boolean failIfExists) {
		// TODO Auto-generated method stub
		/**
		 * existingFileName: the path of existing file, example:
		 * D:/WORKSPACE/Eclipse/VirusAPI/old.txt newFileName: the path of new
		 * file, example: D:/WORKSPACE/Eclipse/VirusAPI/new.txt failIfExists =
		 * TRUE: file existed -> Failed to copy failIfExists = FALSE: file
		 * existed -> Replace old file
		 */
		if (newFileName.equalsIgnoreCase(existingFileName) && failIfExists) {
			return false;
		}
		InputStream inStream = null;
		OutputStream outStream = null;
		try {
			File oldFile = new File(existingFileName);
			File newFile = new File(newFileName);

			inStream = new FileInputStream(oldFile);
			outStream = new FileOutputStream(newFile);

			byte[] buffer = new byte[1024];
			int length;
			while ((length = inStream.read(buffer)) > 0) {
				outStream.write(buffer, 0, length);
			}

			if (inStream != null) {
				inStream.close();
			}
			if (outStream != null) {
				outStream.close();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public String GetCommandLine() {
		// TODO Auto-generated method stub
		/**
		 * cmdLine: get program name cmdArg: get input arguments that called by
		 * main
		 */
		// Get execute directory
		String cmdLine = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		cmdLine = cmdLine.concat(System.getProperty("sun.java.command"));
		// Get runtime of JVM to get input arguments
		RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
		List<String> cmdArg = runtimeMXBean.getInputArguments();
		int i = 0;
		while (cmdArg.isEmpty()) {
			cmdLine.concat(" " + cmdArg.get(i));
			i++;
		}
		return cmdLine;
	}

	@Override
	public double GetTickCount() {
		// TODO Auto-generated method stub
		/**
		 * Get time of system
		 */
		double time = System.currentTimeMillis();
		return time;
	}

	@Override
	public int GetWindowsDirectory(AtomicReference<String> dirBuffer, int bufferSize) {
		// TODO Auto-generated method stub
		/**
		 * dirBuffer: string buffer which contains directory bufferSize: size of
		 * buffer, it means length of string
		 */
		dirBuffer.set(System.getenv("SystemRoot"));
		int dirSize = dirBuffer.get().length();
		if (dirSize > bufferSize) {
			return dirSize;
		} else if (dirSize <= 0) {
			return 0;
		}
		return dirSize;
	}

	@Override
	public int GetSystemDirectory(AtomicReference<String> dirBuffer, int bufferSize) {
		// TODO Auto-generated method stub
		/**
		 * dirBuffer: string buffer which contains directory bufferSize: size of
		 * buffer, it means length of string
		 */
		dirBuffer.set(System.getenv("SystemRoot") + "\\System32");
		int dirSize = dirBuffer.get().length();
		if (dirSize > bufferSize) {
			return dirSize;
		} else if (dirSize <= 0) {
			return 0;
		}
		return dirSize;
	}

	@Override
	public int GetCurrentDirectory(int bufferLength, AtomicReference<String> dirBuffer) {
		// TODO Auto-generated method stub
		/**
		 * dirBuffer: string buffer which contains directory bufferLength:
		 * length of buffer, it means length of string
		 */
		dirBuffer.set(System.getProperty("user.dir"));
		int dirSize = dirBuffer.get().length();
		if (dirSize > bufferLength) {
			return dirSize;
		} else if (dirSize <= 0) {
			return 0;
		}
		return dirSize;
	}

	@Override
	public SearchHandle FindFirstFile(String fileName, AtomicReference<WIN32_FIND_DATA> findFileData) {
		// TODO Auto-generated method stub
		/**
		 * fileName: name of file which we want to find findFileData: find data
		 * dirSearch: directory where we want to find file searchContent:
		 * content which contains file name and file type
		 */
		String dirSearch = fileName.substring(0, fileName.lastIndexOf("/"));
		final String searchContent = fileName.substring(fileName.lastIndexOf("/") + 1);
		File searchFile = new File(dirSearch);
		File[] matchingFiles = searchFile.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				String searchFileName = searchContent.substring(0, searchContent.lastIndexOf("."));
				String searchFileType = searchContent.substring(searchContent.lastIndexOf(".") + 1);
				if (searchFileName.equalsIgnoreCase("*")) {
					// Search for all file
					return name.endsWith(searchFileType);
				} else {
					return name.startsWith(searchFileName) && name.endsWith(searchFileType);
				}
			}
		});
		SearchHandle handle;
		if (matchingFiles.length == 0) {
			// After searching, if we have nothing
			// TODO: return INVALID_HANDLE_VALUE
			handle = new SearchHandle();
		} else {
			// Get first file in matching file
			WIN32_FIND_DATA newfindFileData = new WIN32_FIND_DATA();
			newfindFileData.setFileName(dirSearch + "/" + matchingFiles[0].getName());
			findFileData.set(newfindFileData);

			// Create file handle for search handle
			// Convert file to FileHandle
			LinkedList<FileHandle> findFileList = new LinkedList<FileHandle>();
			for (int i = 0; i < matchingFiles.length; i++) {
				FileHandle fileH = new FileHandle();
				fileH.setFileName(dirSearch + "/" + matchingFiles[i].getName());
				findFileList.add(fileH);
			}
			handle = new SearchHandle();
			handle.setFindFile(matchingFiles);
			handle.setFindFileHandle(findFileList);
		}
		return handle;
	}

	@Override
	public boolean FindNextFile(SearchHandle searchHandle, AtomicReference<WIN32_FIND_DATA> findFileData) {
		// TODO Auto-generated method stub
		// Get search handle from find first file function
		if (searchHandle.getFindFile() == null || searchHandle.getFindFile().length == 1) {
			return false;
		}
		// Get second file in matching file
		WIN32_FIND_DATA newfindFileData = new WIN32_FIND_DATA();
		String firstFile = searchHandle.getFindFileHandle().getFirst().getFileName();
		newfindFileData.setFileName(firstFile.substring(0, firstFile.lastIndexOf("/")) + "/"
				+ searchHandle.getFindFile()[1].getName());
		findFileData.set(newfindFileData);
		return true;
	}

	@Override
	public boolean FindClose(SearchHandle searchHandle) {
		// TODO Auto-generated method stub
		searchHandle = null;
		return true;
	}

	@Override
	public boolean CloseHandle(Handle handle) {
		// TODO Auto-generated method stub
		handle = null;
		return true;
	}

	@Override
	public String lstrcat(String s1, String s2) {
		// TODO Auto-generated method stub
		return s1.concat(s2);
	}

	@Override
	public int lstrcmp(String s1, String s2) {
		// TODO Auto-generated method stub
		return s1.compareTo(s2);
	}

	@Override
	public int lstrlen(String s1) {
		// TODO Auto-generated method stub
		return s1.length();
	}

	@Override
	public String lstrcpy(String s1, String s2) {
		// TODO Auto-generated method stub
		s1 = String.copyValueOf(s2.toCharArray());
		return s1;
	}

	@Override
	public boolean DeleteFile(String fileName) {
		// TODO Auto-generated method stub
		try {
			Path filePath = Paths.get(fileName);
			Files.delete(filePath);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean MoveFile(String existingFileName, String newFileName) {
		// TODO Auto-generated method stub
		if (CopyFileA(existingFileName, newFileName, false)) {
			DeleteFile(existingFileName);
			return true;
		}
		return false;
	}

	@Override
	public void GetSystemTime(AtomicReference<SYSTEM_TIME> systemTime) {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		int milisecond = cal.get(Calendar.MILLISECOND);
		SYSTEM_TIME newSystemTime = new SYSTEM_TIME(year, month, dayOfMonth, dayOfWeek, hour, minute, second,
				milisecond);
		systemTime.set(newSystemTime);
	}

	@Override
	public FileHandle CreateFile(String fileName, DesiredAccess desiredAccess, ShareMode shareMode,
			SECURITY_ATTRIBUTES securityAttributes, CreationDistribution creationDistribution,
			FlagsAndAttributes flagAndAttributes, FileHandle templateFile) {
		// TODO Auto-generated method stub
		// Create new file Handle
		FileHandle newFileHandle = new FileHandle(fileName, desiredAccess, shareMode, securityAttributes,
				creationDistribution, flagAndAttributes, templateFile);
		// Create new file on specified directory,set attribute, share mode for
		// file
		// TODO:
		return newFileHandle;
	}

	@Override
	public int _lwrite(FileHandle file, String buffer, int nBytes) {
		// TODO Auto-generated method stub
		File newFile = new File(file.getFileName());
		OutputStream outStream;
		try {
			outStream = new FileOutputStream(newFile);
			byte[] tempBufferData = buffer.getBytes(Charset.forName("UTF-8"));
			byte[] bufferData = new byte[nBytes];
			for (int i = 0; i < nBytes; i++) {
				bufferData[i] = tempBufferData[i];
			}
			outStream.write(bufferData);
			if (outStream != null) {
				outStream.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return nBytes;
		// TODO: return HFILE_ERROR
	}

	@Override
	public long GetFileSize(FileHandle file, int fileSizeHigh) {
		// TODO Auto-generated method stub
		File newfile = new File(file.getFileName());
		// TODO: return low-order word
		return newfile.length();
	}

	@Override
	public void GetLocalTime(AtomicReference<SYSTEM_TIME> localTime) {
		// TODO Auto-generated method stub
		AtomicReference<SYSTEM_TIME> newLocalTime = new AtomicReference<SYSTEM_TIME>();
		GetSystemTime(newLocalTime);
		localTime.set(newLocalTime.get());
	}

	@Override
	public FileAttribute GetFileAttributes(String fileName) {
		// TODO Auto-generated method stub
		File file = new File(fileName);
		FileAttribute fAttr = new FileAttribute();
		fAttr.setFileAttributeUndefined();
		// TODO: another attribute for file
		if (file.isHidden()) {
			fAttr.setFileAttributeHidden();
		} else if (file.canRead() && !file.canWrite()) {
			fAttr.setFileAttributeReadonly();
		} else if (file.canRead() && file.canWrite()) {
			fAttr.setFileAttributeNormal();
		}
		return fAttr;
	}

	@Override
	public FileType GetFileType(FileHandle fileHandle) {
		// TODO Auto-generated method stub
		FileType fType = new FileType();
		fType.setFileTypeUnknown();
		File file = new File(fileHandle.getFileName());
		// TODO: other type for file
		if (file.getName().lastIndexOf(".") != -1)
			fType.setFileTypeDisk();
		return fType;
	}

	@Override
	public boolean SetCurrentDirectory(String PathName) {
		// TODO Auto-generated method stub
		System.setProperty("user.dir", PathName);
		return true;
	}

}
