package v2.org.analysis.system;

public class SEHHandle {
	private SEHLinkedList start;
	private boolean isSet;

	public boolean isSet() {
		return isSet;
	}

	public void setSEHReady(boolean ready) {
		this.isSet = ready;
	}

	public SEHHandle() {
		start = new SEHLinkedList(1638372, 2002594307);
		isSet = false;
	}

	public SEHLinkedList getStart() {
		// this.isSet = true;
		return start;
	}

	public void setStart(SEHLinkedList start) {
		this.start = start;
		this.isSet = true;
	}

	public void removeStart() {
		this.start = null;
		this.isSet = false;
	}
}
