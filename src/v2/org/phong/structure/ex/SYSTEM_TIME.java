package v2.org.phong.structure.ex;

public class SYSTEM_TIME {
	private int year;
	private int month;
	private int dayOfMonth;
	private int dayOfWeek;
	private int hour;
	private int minute;
	private int second;
	private int milisecond;

	public SYSTEM_TIME() {
		// TODO Auto-generated constructor stub
	}

	public SYSTEM_TIME(int year, int month, int dayOfMonth, int dayOfWeek, int hour, int minute, int second,
			int milisecond) {
		this.setYear(year);
		this.setMonth(month);
		this.setDayOfWeek(dayOfWeek);
		this.setDayOfMonth(dayOfMonth);
		this.setHour(hour);
		this.setMinute(minute);
		this.setSecond(second);
		this.setMilisecond(milisecond);
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(int dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public int getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public int getMilisecond() {
		return milisecond;
	}

	public void setMilisecond(int milisecond) {
		this.milisecond = milisecond;
	}
}