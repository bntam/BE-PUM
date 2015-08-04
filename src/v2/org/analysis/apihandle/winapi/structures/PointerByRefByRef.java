package v2.org.analysis.apihandle.winapi.structures;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.ByReference;

/**
 * Represents a reference to a reference to a pointer to native data. In C notation,
 * <code>void***</code>.
 * 
 * @author Yen Nguyen
 */
public class PointerByRefByRef extends ByRefByRef {

	public PointerByRefByRef() {
		this(null);
	}

	public PointerByRefByRef(Pointer value) {
		super(Pointer.SIZE);
		setValue(value);
	}

	public void setValue(Pointer value) {
		getPointer().getPointer(0).setPointer(0, value);
	}

	public Pointer getValue() {
		return getPointer().getPointer(0).getPointer(0);
	}
}
