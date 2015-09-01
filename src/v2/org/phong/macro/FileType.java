package v2.org.phong.macro;

public class FileType {

	int FILE_TYPE_UNKNOWN = 0;
	int FILE_TYPE_DISK = 1;
	int FILE_TYPE_CHAR = 2;
	int FILE_TYPE_PIPE = 3;

	private int fileType;

	public int getFileType() {
		return fileType;
	}

	public void setFileTypeUnknown() {
		this.fileType = FILE_TYPE_UNKNOWN;
	}

	public void setFileTypeDisk() {
		this.fileType = FILE_TYPE_DISK;
	}

	public void setFileTypeChar() {
		this.fileType = FILE_TYPE_CHAR;
	}

	public void setFileTypePipe() {
		this.fileType = FILE_TYPE_PIPE;
	}
}
