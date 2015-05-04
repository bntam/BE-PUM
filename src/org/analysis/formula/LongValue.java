package org.analysis.formula;

import org.analysis.complement.OldBitVector;

import java.util.Map;

public class LongValue implements Value {
	private long value;

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Constant";
	}

	public LongValue(long value) {
		super();
		this.value = value;
	}

	@Override
	public boolean equal(Value e) {
		// TODO Auto-generated method stub
		if (e instanceof LongValue)
			return value == ((LongValue) e).getValue();
		return false;
	}

	public long getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value + "";
	}

	@Override
	public LongValue clone() {
		return new LongValue(value);
	}

	@Override
	public Value movFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue)
			result = new LongValue(((LongValue) exp).getValue());
		else if (exp instanceof SymbolExp)
			result = new SymbolExp(((SymbolExp) exp).getVarName());
		else if (exp instanceof HybridExp)
			result = new HybridExp(((HybridExp) exp).getLeft(),
					((HybridExp) exp).getConnector(),
					((HybridExp) exp).getRight());
		return result;
	}

	@Override
	public Value addFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue)
			result = new LongValue(value + ((LongValue) exp).getValue());
		else if (exp instanceof SymbolExp)
			result = new HybridExp(exp, "+", new LongValue(this.value));
		else if (exp instanceof HybridExp)
			result = new HybridExp(exp, "+", new LongValue(this.value));
		return result;
	}

	@Override
	public Value subFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue)
			result = new LongValue(value - ((LongValue) exp).getValue());
		else if (exp instanceof SymbolExp)
			result = new HybridExp(new LongValue(this.value), "-", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(new LongValue(this.value), "-", exp);
		return result;
	}

	@Override
	public Value mulFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue)
			result = new LongValue(value * ((LongValue) exp).getValue());
		else if (exp instanceof SymbolExp)
			result = new HybridExp(new LongValue(this.value), "*", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(new LongValue(this.value), "*", exp);
		return result;
	}

	@Override
	public Value divFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue) {
			if (((LongValue) exp).getValue() == 0)
				return null;

			result = new LongValue(value / ((LongValue) exp).getValue());
		} else if (exp instanceof SymbolExp)
			result = new HybridExp(new LongValue(this.value), "/", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(new LongValue(this.value), "/", exp);
		return result;
	}

	@Override
	public Value andFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue)
			result = new LongValue(value & ((LongValue) exp).getValue());
		else if (exp instanceof SymbolExp)
			result = new HybridExp(new LongValue(this.value), "and", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(new LongValue(this.value), "and", exp);
		return result;
	}

	@Override
	public Value orFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue)
			result = new LongValue(value | ((LongValue) exp).getValue());
		else if (exp instanceof SymbolExp)
			result = new HybridExp(new LongValue(this.value), "or", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(new LongValue(this.value), "or", exp);
		return result;
	}

	@Override
	public Value notFunc() {
		// TODO Auto-generated method stub
		return new LongValue(value);
	}

	@Override
	public Value xorFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue) {
			result = new LongValue(value ^ ((LongValue) exp).getValue());
		} else if (exp instanceof SymbolExp)
			result = new HybridExp(new LongValue(this.value), "xor", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(new LongValue(this.value), "xor", exp);
		return result;
	}

	@Override
	public Value rrFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue) {
			long l1 = ((LongValue) exp).getValue();
			result = new LongValue(OldBitVector.rr(value, l1));
		} else if (exp instanceof SymbolExp)
			result = new HybridExp(new LongValue(this.value), "rr", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(new LongValue(this.value), "rr", exp);
		return result;
	}

	@Override
	public Value rlFunc(Value exp) {
		// TODO Auto-generated method stub
		Value result = null;
		if (exp instanceof LongValue) {
			long l1 = ((LongValue) exp).getValue();
			result = new LongValue(OldBitVector.rl(value, l1));
		} else if (exp instanceof SymbolExp)
			result = new HybridExp(new LongValue(this.value), "rl", exp);
		else if (exp instanceof HybridExp)
			result = new HybridExp(new LongValue(this.value), "rl", exp);
		return result;
	}

	@Override
	public String toStringPreFix() {
		// TODO Auto-generated method stub
		String result = "bv";
		if (value >= 0)
			result += value;
		else
			result += signConvert(this.value);

		return result + "[32]";
	}

	private long signConvert(long value2) {
		// TODO Auto-generated method stub
		return (long) (Math.pow(2, 32) + value2);
	}

	@Override
	public Value evaluate() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Map<String, Long> getValueMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValueMap(Map<String, Long> valueMap) {
		// TODO Auto-generated method stub

	}

}
