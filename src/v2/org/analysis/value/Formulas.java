package v2.org.analysis.value;

import java.util.ArrayList;
import java.util.List;

public class Formulas {
	private List<Formula> listFormula;
	private List<String> variableName;
	private int num = 0;

	public Formulas clone() {
		Formulas f = new Formulas();
		for (Formula f1 : listFormula) {
			f.add(f1.clone());
		}
		for (String s : variableName)
			f.addVariableName(s);

		return f;
	}

	public Formulas() {
		listFormula = new ArrayList<Formula>();
		variableName = new ArrayList<String>();
	}

	public List<Formula> getListFormula() {
		return listFormula;
	}

	public Formula getTop() {
		return this.listFormula.get(num - 1);
	}

	public void setListFormula(List<Formula> listFormula) {
		this.listFormula = listFormula;
	}

	public boolean contain(Formula sf) {
		for (Formula x : this.listFormula)
			if (x.equal(sf) || x.toStringPrefix().equals(sf.toStringPrefix()))
				return true;
		return false;
	}

	public boolean add(Formula sf) {
		if (this.contain(sf))
			return false;
		this.listFormula.add(sf);
		this.num++;
		return true;
	}

	public boolean add(Formulas fs) {
		List<Formula> l = fs.getListFormula();
		if (l.size() == 0)
			return false;

		for (Formula i : l)
			add(i);
		return true;
	}

	public void printInfo() {
		System.out.println("Expression:");
		System.out.println(toStringPrefix());
		System.out.println();
	}

	public String toStringPrefix() {
		String result = "";

		if (listFormula.size() > 1) {
			result = "(and ";
			for (Formula f : listFormula)
				result += f.toStringPrefix();
			// System.out.println();
			result += " )";
		} else if (listFormula.size() > 0)
			result = listFormula.get(0).toStringPrefix();
		return result;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return listFormula.isEmpty();
	}

	/*
	 * public String getVariable() { // TODO Auto-generated method stub return
	 * "(" + "ebx" + " BitVec[32])"; }
	 */

	private void insertVariableName() {
		for (Formula x : this.listFormula)
			addVariableNames(x.getVariable());
	}

	private void addVariableNames(List<String> variables) {
		// TODO Auto-generated method stub
		if (variables == null)
			return;

		for (String t : variables)
			addVariableName(t);
	}

	public String getVariableZ3() {
		insertVariableName();
		String result = "";
		for (String var : variableName) {
			result += "(" + var + " BitVec[32])";
		}

		result += "(TOP BitVec[32])";

		return result;
	}

	public void addVariableName(String s) {
		if (!this.variableName.contains(s))
			this.variableName.add(s);
	}

	public boolean isBooleanValue() {
		// TODO Auto-generated method stub
		for (Formula f : listFormula) {
			if (!f.evaluate().isBoolean())
				return false;
		}

		return true;
	}

	public boolean evaluate() {
		// TODO Auto-generated method stub
		boolean ret = true;
		for (Formula f : listFormula) {
			ret &= f.evaluate().getBooleanValue();
		}

		return ret;
	}

	public void clear() {
		// TODO Auto-generated method stub
		listFormula.clear();
		variableName.clear();
		num = 0;
	}
}
