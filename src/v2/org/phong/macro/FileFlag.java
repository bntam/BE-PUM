package v2.org.phong.macro;

public class FileFlag {
	int FILE_FLAG_BACKUP_SEMANTICS=0x02000000;
	int FILE_FLAG_DELETE_ON_CLOSE=0x04000000;
	int FILE_FLAG_NO_BUFFERING=0x20000000;
	int FILE_FLAG_OPEN_NO_RECALL=0x00100000;
	int FILE_FLAG_OPEN_REPARSE_POINT=0x00200000;
	int FILE_FLAG_OVERLAPPED=0x40000000;
	int FILE_FLAG_POSIX_SEMANTICS=0x00100000;
	int FILE_FLAG_RANDOM_ACCESS=0x10000000;
	int FILE_FLAG_SESSION_AWARE=0x08000000;
	int FILE_FLAG_WRITE_THROUGH=0x80000000;
	
	private int fileFlag;

	public int getFileFlag() {
		return fileFlag;
	}

	public void setFileFlagBackupSematics() {
		this.fileFlag = FILE_FLAG_BACKUP_SEMANTICS;
	}

	public void setFileFlagDeleteOnClose() {
		this.fileFlag = FILE_FLAG_DELETE_ON_CLOSE;
	}
	
	public void setFileFlagNoBuffering() {
		this.fileFlag = FILE_FLAG_NO_BUFFERING;
	}
	
	public void setFileFlagOpenNoRecall() {
		this.fileFlag = FILE_FLAG_OPEN_NO_RECALL;
	}
	
	public void setFileFlagOpenReparsePoint() {
		this.fileFlag = FILE_FLAG_OPEN_REPARSE_POINT;
	}
	
	public void setFileFlagOverlapped() {
		this.fileFlag = FILE_FLAG_OVERLAPPED;
	}
	
	public void setFileFlagPosixSematics() {
		this.fileFlag = FILE_FLAG_POSIX_SEMANTICS;
	}
	
	public void setFileFlagRandomAccess() {
		this.fileFlag = FILE_FLAG_RANDOM_ACCESS;
	}
	
	public void setFileFlagSessionAware() {
		this.fileFlag = FILE_FLAG_SESSION_AWARE;
	}
	
	public void setFileFlagWriteThrough() {
		this.fileFlag = FILE_FLAG_WRITE_THROUGH;
	}
}
