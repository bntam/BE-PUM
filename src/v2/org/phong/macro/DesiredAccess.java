package v2.org.phong.macro;

public class DesiredAccess {
	int ZERO = 0;
	int GENERIC_READ = 1;
	int GENERIC_WRITE = 2;
	
	private int desiredAccess;

	public int getDesiredAccess() {
		return desiredAccess;
	}

	public void setDesiredAccessWithZero() {
		this.desiredAccess = ZERO;
	}

	public void setDesiredAccessWithGenericRead() {
		this.desiredAccess = GENERIC_READ;
	}
	
	public void setDesiredAccessWithGenericWrite() {
		this.desiredAccess = GENERIC_WRITE;
	}
}
