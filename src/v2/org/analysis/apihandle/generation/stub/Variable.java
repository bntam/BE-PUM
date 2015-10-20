package v2.org.analysis.apihandle.generation.stub;

public class Variable {
	public String type;
	public String name;
	public String desc;
	
	public int order;
	public String initStr;

	public Variable(String type, String name, String desc) {
		this.type = type;
		this.name = name;
		this.desc = desc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getInitStr() {
		return initStr;
	}

	public void setInitStr(String initStr) {
		this.initStr = initStr;
	}
}
