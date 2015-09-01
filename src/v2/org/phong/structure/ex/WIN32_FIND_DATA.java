package v2.org.phong.structure.ex;

public class WIN32_FIND_DATA {

	private String fileAttributes;
	private FILE_TIME creationTime;
	private FILE_TIME lastAccessTime;
	private FILE_TIME lastWriteTime;
	private int fileSizeHigh;
	private int fileSizeLow;
	private int reserved0;
	private int reserved1;
	private String fileName;
	private String altFileName;

	public WIN32_FIND_DATA() {

	}

	public WIN32_FIND_DATA(String fAttr, FILE_TIME cTime, FILE_TIME lAccTime, FILE_TIME lWriTime, int fSizeH,
			int fSizeL, int r0, int r1, String fName, String altfName) {
		this.setFileAttributes(fAttr);
		this.setCreationTime(cTime);
		this.setLastAccessTime(lAccTime);
		this.setLastWriteTime(lWriTime);
		this.setFileSizeHigh(fSizeH);
		this.setFileSizeLow(fSizeL);
		this.setReserved0(r0);
		this.setReserved1(r1);
		this.setFileName(fName);
		this.setAltFileName(altfName);
	}

	public String getFileAttributes() {
		return fileAttributes;
	}

	public void setFileAttributes(String fileAttributes) {
		this.fileAttributes = fileAttributes;
	}

	public FILE_TIME getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(FILE_TIME creationTime) {
		this.creationTime = creationTime;
	}

	public FILE_TIME getLastAccessTime() {
		return lastAccessTime;
	}

	public void setLastAccessTime(FILE_TIME lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	public FILE_TIME getLastWriteTime() {
		return lastWriteTime;
	}

	public void setLastWriteTime(FILE_TIME lastWriteTime) {
		this.lastWriteTime = lastWriteTime;
	}

	public int getFileSizeHigh() {
		return fileSizeHigh;
	}

	public void setFileSizeHigh(int fileSizeHigh) {
		this.fileSizeHigh = fileSizeHigh;
	}

	public int getFileSizeLow() {
		return fileSizeLow;
	}

	public void setFileSizeLow(int fileSizeLow) {
		this.fileSizeLow = fileSizeLow;
	}

	public int getReserved0() {
		return reserved0;
	}

	public void setReserved0(int reserved0) {
		this.reserved0 = reserved0;
	}

	public int getReserved1() {
		return reserved1;
	}

	public void setReserved1(int reserved1) {
		this.reserved1 = reserved1;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getAltFileName() {
		return altFileName;
	}

	public void setAltFileName(String altFileName) {
		this.altFileName = altFileName;
	}

}
