package v2.org.analysis.apihandle.generation.stub;

/**
 * 
 * @author Yen Nguyen
 *
 */
public class Type {
	public String from;
	public String to;
	public String fullClassName = null;
	
	public Type() {
	}
	
	public Type(String type) {
		this(type, type, null);
	}
	
	public Type(String from, String to) {
		this(from, to, null);
	}
	
	public Type(String from, String to, String fullClassName) {
		this.from = from;
		this.to = to;
		this.fullClassName = fullClassName;
	}

	@Override
	public String toString() {
		return this.to;
	}
}
