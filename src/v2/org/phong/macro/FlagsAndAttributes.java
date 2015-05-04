package v2.org.phong.macro;

public class FlagsAndAttributes {
	
	private FileAttribute fAttribute;
	private FileFlag fFlag;
	
	public FlagsAndAttributes() {
		// TODO Auto-generated constructor stub
	}
	
	public FlagsAndAttributes(FileAttribute fAttribute, FileFlag fFlag) {
		this.setfAttribute(fAttribute);
		this.setfFlag(fFlag);
	}

	public FileAttribute getfAttribute() {
		return fAttribute;
	}

	public void setfAttribute(FileAttribute fAttribute) {
		this.fAttribute = fAttribute;
	}

	public FileFlag getfFlag() {
		return fFlag;
	}

	public void setfFlag(FileFlag fFlag) {
		this.fFlag = fFlag;
	}
	
}
