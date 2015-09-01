package v2.org.phong.macro;

public final class FileAttribute {
	int FILE_ATTRIBUTE_ARCHIVE = 0x00000020;
	int FILE_ATTRIBUTE_ENCRYPTED = 0x00004000;
	int FILE_ATTRIBUTE_HIDDEN = 0x00000002;
	int FILE_ATTRIBUTE_NORMAL = 0x00000080;
	int FILE_ATTRIBUTE_OFFLINE = 0x00001000;
	int FILE_ATTRIBUTE_READONLY = 0x00000001;
	int FILE_ATTRIBUTE_SYSTEM = 0x00000004;
	int FILE_ATTRIBUTE_TEMPORARY = 0x00000100;
	int FILE_ATTRIBUTE_UNDEFINED = 0xFFFFFFFF;

	private int fileAttribute;

	public FileAttribute() {
		// TODO Auto-generated constructor stub
	}

	public int getFileAttribute() {
		return fileAttribute;
	}

	public void setFileAttributeArchive() {
		this.fileAttribute = FILE_ATTRIBUTE_ARCHIVE;
	}

	public void setFileAttributeEncrypted() {
		this.fileAttribute = FILE_ATTRIBUTE_ENCRYPTED;
	}

	public void setFileAttributeHidden() {
		this.fileAttribute = FILE_ATTRIBUTE_HIDDEN;
	}

	public void setFileAttributeNormal() {
		this.fileAttribute = FILE_ATTRIBUTE_NORMAL;
	}

	public void setFileAttributeOffline() {
		this.fileAttribute = FILE_ATTRIBUTE_OFFLINE;
	}

	public void setFileAttributeReadonly() {
		this.fileAttribute = FILE_ATTRIBUTE_READONLY;
	}

	public void setFileAttributeSystem() {
		this.fileAttribute = FILE_ATTRIBUTE_SYSTEM;
	}

	public void setFileAttributeTemporary() {
		this.fileAttribute = FILE_ATTRIBUTE_TEMPORARY;
	}

	public void setFileAttributeUndefined() {
		this.fileAttribute = FILE_ATTRIBUTE_UNDEFINED;
	}
}
