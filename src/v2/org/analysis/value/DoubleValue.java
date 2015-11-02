package v2.org.analysis.value;

import v2.org.analysis.complement.BitVector;
import v2.org.analysis.complement.Convert;

import java.util.List;
import java.util.Map;

public class DoubleValue implements Value {
	private double value;
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Concrete Value";
	}
	
	public DoubleValue(double value) {
		super();
		this.value = value;
	}
	
	public DoubleValue(long value) {
		super();
		this.value = (double) value;
	}
	
	@Override
	public String toStringPreFix() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		return Convert.doubleToHex(value) + "";
		// return value + "";
	}
	
	@Override
	public boolean equal(Value e) {
		// TODO Auto-generated method stub
		if (e instanceof DoubleValue)
			return value == ((DoubleValue) e).getValue();		
		return false;
	}
	
	public double getValue() {
		return value;
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
			result = new HybridValue(((HybridValue) exp).getLeft(), ((HybridValue) exp).getConnector(),
					((HybridValue) exp).getRight());
		else if (exp instanceof TopValue)
			return new TopValue();
		else if (exp instanceof DoubleValue)
			result = new DoubleValue(((DoubleValue) exp).getValue());
		return result;		
	}

	@Override
	public Value addFunction(Value exp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value subFunction(Value exp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value unsignedMulFunction(Value exp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value signedMulFunction(Value exp, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value signedMulFunction(Value exp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value unsignedDivFunction(Value exp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value signedDivFunction(Value exp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value andFunction(Value exp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value orFunction(Value exp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value modFunction(Value v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value powFunction(Value v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value notFunction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value xorFunction(Value exp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value rrFunction(Value exp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value rlFunction(Value exp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleValue clone() {
		// TODO Auto-generated method stub
		return new DoubleValue(value);
	}

	@Override
	public Value evaluate(Map<String, Long> z3Value) {
		// TODO Auto-generated method stub
		return null;
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
		return null;
	}

	@Override
	public List<String> getVariable() {
		// TODO Auto-generated method stub
		return null;
	}

}
