package v2.org.analysis.value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SymbolValue implements Value {
	private String varName;
	private Map<String, Long> valueMap = new HashMap<String, Long>();

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Symbol Value";
	}

	public SymbolValue(String varName) {
		super();
		this.varName = varName;
	}

	@Override
	public boolean equal(Value e) {
		// TODO Auto-generated method stub
		if (e instanceof SymbolValue)
			return varName.equals(((SymbolValue) e).getVarName());
		return false;
	}

	public String getVarName() {
		return varName;
	}

	@Override
	public String toString() {
		return varName;
	}

	@Override
	public Value movFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue)
			result = new LongValue(((LongValue) exp).getValue());
		else if (exp instanceof SymbolValue)
			result = new SymbolValue(((SymbolValue) exp).getVarName());
		else if (exp instanceof HybridValue)
			result = new HybridValue(((HybridValue) exp).getLeft(),
					((HybridValue) exp).getConnector(),
					((HybridValue) exp).getRight());
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value addFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue)
			result = new HybridValue(new SymbolValue(varName), "+",
					new LongValue(((LongValue) exp).getValue()));
		else if (exp instanceof SymbolValue)
			result = new HybridValue(new SymbolValue(varName), "+", exp);
		else if (exp instanceof HybridValue)
			result = new HybridValue(new SymbolValue(varName), "+", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value subFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue)
			result = new HybridValue(new SymbolValue(varName), "-",
					new LongValue(((LongValue) exp).getValue()));
		else if (exp instanceof SymbolValue)
			result = new HybridValue(new SymbolValue(varName), "-", exp);
		else if (exp instanceof HybridValue)
			result = new HybridValue(new SymbolValue(varName), "-", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value unsignedMulFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue)
			result = new HybridValue(new SymbolValue(varName), "*",
					new LongValue(((LongValue) exp).getValue()));
		else if (exp instanceof SymbolValue)
			result = new HybridValue(new SymbolValue(varName), "*", exp);
		else if (exp instanceof HybridValue)
			result = new HybridValue(new SymbolValue(varName), "*", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value signedMulFunction(Value exp, int size) {
		Value result = null;
		if (exp instanceof LongValue)
			result = new HybridValue(new SymbolValue(varName), "*",
					new LongValue(((LongValue) exp).getValue()));
		else if (exp instanceof SymbolValue)
			result = new HybridValue(new SymbolValue(varName), "*", exp);
		else if (exp instanceof HybridValue)
			result = new HybridValue(new SymbolValue(varName), "*", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value signedMulFunction(Value exp) {
		Value result = null;
		if (exp instanceof LongValue)
			result = new HybridValue(new SymbolValue(varName), "*",
					new LongValue(((LongValue) exp).getValue()));
		else if (exp instanceof SymbolValue)
			result = new HybridValue(new SymbolValue(varName), "*", exp);
		else if (exp instanceof HybridValue)
			result = new HybridValue(new SymbolValue(varName), "*", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public SymbolValue clone() {
		return new SymbolValue(varName);
	}

	@Override
	public Value unsignedDivFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue)
			result = new HybridValue(new SymbolValue(varName), "/",
					new LongValue(((LongValue) exp).getValue()));
		else if (exp instanceof SymbolValue)
			result = new HybridValue(new SymbolValue(varName), "/", exp);
		else if (exp instanceof HybridValue)
			result = new HybridValue(new SymbolValue(varName), "/", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value signedDivFunction(Value exp) {
		Value result = null;
		if (exp instanceof LongValue)
			result = new HybridValue(new SymbolValue(varName), "/",
					new LongValue(((LongValue) exp).getValue()));
		else if (exp instanceof SymbolValue)
			result = new HybridValue(new SymbolValue(varName), "/", exp);
		else if (exp instanceof HybridValue)
			result = new HybridValue(new SymbolValue(varName), "/", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value andFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue)
			result = new HybridValue(new SymbolValue(varName), "and",
					new LongValue(((LongValue) exp).getValue()));
		else if (exp instanceof SymbolValue)
			result = new HybridValue(new SymbolValue(varName), "and", exp);
		else if (exp instanceof HybridValue)
			result = new HybridValue(new SymbolValue(varName), "and", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value orFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue)
			result = new HybridValue(new SymbolValue(varName), "or",
					new LongValue(((LongValue) exp).getValue()));
		else if (exp instanceof SymbolValue)
			result = new HybridValue(new SymbolValue(varName), "or", exp);
		else if (exp instanceof HybridValue)
			result = new HybridValue(new SymbolValue(varName), "or", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value notFunction() {
		// TODO Auto-generated method stub
		return new SymbolValue(varName);
	}

	@Override
	public Value xorFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue)
			result = new HybridValue(new SymbolValue(varName), "xor",
					new LongValue(((LongValue) exp).getValue()));
		else if (exp instanceof SymbolValue)
			result = new HybridValue(new SymbolValue(varName), "xor", exp);
		else if (exp instanceof HybridValue)
			result = new HybridValue(new SymbolValue(varName), "xor", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value rrFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue)
			result = new HybridValue(new SymbolValue(varName), "xor",
					new LongValue(((LongValue) exp).getValue()));
		else if (exp instanceof SymbolValue)
			result = new HybridValue(new SymbolValue(varName), "xor", exp);
		else if (exp instanceof HybridValue)
			result = new HybridValue(new SymbolValue(varName), "xor", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value rlFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue)
			result = new HybridValue(new SymbolValue(varName), "xor",
					new LongValue(((LongValue) exp).getValue()));
		else if (exp instanceof SymbolValue)
			result = new HybridValue(new SymbolValue(varName), "xor", exp);
		else if (exp instanceof HybridValue)
			result = new HybridValue(new SymbolValue(varName), "xor", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public String toStringPreFix() {
		// TODO Auto-generated method stub
		String var = new String(varName);
		/*
		 * if (var.startsWith("%")) var = var.substring(1); else if
		 * (var.startsWith("0x")) var = "op_addr_" + var; else if
		 * (var.startsWith("(%")) var = "op_addr_base_" + var.substring(2,
		 * var.length() - 1);
		 */

		if (var.startsWith("%") && !var.contains(":(%"))
			var = var.substring(1);
		else if (var.startsWith("0x"))
			if (var.contains(",")) {
				int pos1 = var.lastIndexOf("(");
				int pos2 = var.lastIndexOf(",");
				var = "op_addr_base2_disp_" + var.substring(0, pos1) + "_"
						+ var.substring(pos1 + 2, pos2) + "_"
						+ var.substring(pos2 + 2, var.length() - 1);
			} else if (var.contains("(%")) {
				// String t[] = var.split("(");
				int pos = var.lastIndexOf("(");
				var = "op_addr_base_disp_" + var.substring(0, pos) + "_"
						+ var.substring(pos + 2, var.length() - 1);
			} else
				var = "op_addr_disp_" + var;
		else if (var.startsWith("(%")) {
			if (var.contains(",")) {
				int pos = var.lastIndexOf(",");
				var = "op_addr_base_index_" + var.substring(2, pos - 1) + "_"
						+ var.substring(pos + 2, var.length() - 1);
			} else
				var = "op_addr_base_" + var.substring(2, var.length() - 1);
		} else if (var.startsWith("%") && var.contains(":(%")) {
			int pos = var.lastIndexOf(":");
			var = "op_addr_base_index_disp_" + var.substring(1, pos) + "_"
					+ var.substring(pos + 3, var.length() - 1);
		} else if (var.contains(",") && var.contains("(%")) {
			int pos1 = var.lastIndexOf("(");
			int pos2 = var.lastIndexOf(",");
			var = "op_addr_base_index_base_" + var.substring(0, pos1 - 1) + "_"
					+ var.substring(pos1 + 2, pos2) + "_"
					+ var.substring(pos2 + 2, var.length() - 1);
		}

		return var;
	}

	@Override
	public Value evaluate(Map<String, Long> z3Value) {
		// TODO Auto-generated method stub
		for (Entry<String, Long> entry : z3Value.entrySet())
			if (entry.getKey().contains(varName))
				return new LongValue(entry.getValue());
		return this;
	}

	public Map<String, Long> getValueMap() {
		return valueMap;
	}

	public void setValueMap(Map<String, Long> valueMap) {
		this.valueMap = valueMap;
	}

	@Override
	public Value rl8Function(Value s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value rl16Function(Value s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value rr16Function(Value s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value rr8Function(Value s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value powFunction(int i) {
		// TODO Auto-generated method stub
		return new HybridValue(new SymbolValue(varName), "pow",	new LongValue(i));
	}

	@Override
	public List<String> getVariable() {
		// TODO Auto-generated method stub
		ArrayList<String> r = new ArrayList<String>();
		r.add(varName);
		return r;
	}

	@Override
	public Value modFunction(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue)
			result = new HybridValue(new SymbolValue(varName), "mod",
					new LongValue(((LongValue) exp).getValue()));
		else if (exp instanceof SymbolValue)
			result = new HybridValue(new SymbolValue(varName), "mod", exp);
		else if (exp instanceof HybridValue)
			result = new HybridValue(new SymbolValue(varName), "mod", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

	@Override
	public Value powFunction(Value exp) {
		Value result = null;
		if (exp instanceof LongValue)
			result = new HybridValue(new SymbolValue(varName), "^",
					new LongValue(((LongValue) exp).getValue()));
		else if (exp instanceof SymbolValue)
			result = new HybridValue(new SymbolValue(varName), "^", exp);
		else if (exp instanceof HybridValue)
			result = new HybridValue(new SymbolValue(varName), "^", exp);
		else if (exp instanceof TopValue)
			return new TopValue();
		return result;
	}

}
