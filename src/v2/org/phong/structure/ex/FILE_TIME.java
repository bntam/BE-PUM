package v2.org.phong.structure.ex;

public class FILE_TIME {

	private int lowDateTime;
	private int highDateTime;
	
	public FILE_TIME() {
		// TODO Auto-generated constructor stub
	}
	
	public FILE_TIME(int lowTime, int highTime) {
		this.setLowDateTime(lowTime);
		this.setHighDateTime(highTime);
	}

	public int getLowDateTime() {
		return lowDateTime;
	}

	public void setLowDateTime(int lowDateTime) {
		this.lowDateTime = lowDateTime;
	}

	public int getHighDateTime() {
		return highDateTime;
	}

	public void setHighDateTime(int highDateTime) {
		this.highDateTime = highDateTime;
	}
	
}
