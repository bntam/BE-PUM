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
		long addrSEH = 0x12FFE0;
		long nextSEH = 0xFFFFFFFF;
		long sehHandler = 0x7C839AA8;
		start = new SEHLinkedList(addrSEH, nextSEH, sehHandler);
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
