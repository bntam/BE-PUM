package v2.org.phong.structure.ex;

public class SECURITY_ATTRIBUTES {

	private int nLength;
	private String securityDesciptor;
	private boolean inheritHandle;

	public SECURITY_ATTRIBUTES() {
		// TODO Auto-generated constructor stub
	}

	public SECURITY_ATTRIBUTES(int nLength, String securityDescriptor, boolean inheritHandle) {
		this.setnLength(nLength);
		this.setSecurityDesciptor(securityDescriptor);
		this.setInheritHandle(inheritHandle);
	}

	public int getnLength() {
		return nLength;
	}

	public void setnLength(int nLength) {
		this.nLength = nLength;
	}

	public String getSecurityDesciptor() {
		return securityDesciptor;
	}

	public void setSecurityDesciptor(String securityDesciptor) {
		this.securityDesciptor = securityDesciptor;
	}

	public boolean isInheritHandle() {
		return inheritHandle;
	}

	public void setInheritHandle(boolean inheritHandle) {
		this.inheritHandle = inheritHandle;
	}

}
