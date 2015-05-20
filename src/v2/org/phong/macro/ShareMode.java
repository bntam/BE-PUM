package v2.org.phong.macro;

public class ShareMode {
	int ZERO = 0x00000000;
	int FILE_SHARE_DELETE = 0x00000004;
	int FILE_SHARE_READ = 0x00000001;
	int FILE_SHARE_WRITE = 0x00000002;

	private int shareMode;

	public int getShareMode() {
		return shareMode;
	}

	public void setShareModeWithZero() {
		this.shareMode = ZERO;
	}

	public void setShareModeWithFileShareDelete() {
		this.shareMode = FILE_SHARE_DELETE;
	}

	public void setShareModeWithFileShareRead() {
		this.shareMode = FILE_SHARE_READ;
	}

	public void setShareModeWithFileShareWrite() {
		this.shareMode = FILE_SHARE_WRITE;
	}
}
