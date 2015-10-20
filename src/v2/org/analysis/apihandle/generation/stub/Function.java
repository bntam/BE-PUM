package v2.org.analysis.apihandle.generation.stub;

import java.util.List;
import java.util.Vector;

public class Function {
	public String funcName;
	public String libName;
	public String description;
	public List<String> importList;
	public List<Variable> paramsList;
	public Variable ret = null;
	
	public String extendClass;

	public Function() {
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String getLibName() {
		return libName;
	}

	public void setLibName(String libName) {
		this.libName = libName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getImportList() {
		return importList;
	}

	public void setImportList(Vector<String> importList) {
		this.importList = importList;
	}

	public List<Variable> getParamsList() {
		return paramsList;
	}

	public void setParamsList(Vector<Variable> paramsList) {
		this.paramsList = paramsList;
	}

	public Variable getRet() {
		return ret;
	}

	public void setRet(Variable ret) {
		this.ret = ret;
	}

	public void setImportList(List<String> importList) {
		this.importList = importList;
	}

	public void setParamsList(List<Variable> paramsList) {
		this.paramsList = paramsList;
	}
	
}
