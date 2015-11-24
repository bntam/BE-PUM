package v2.org.analysis.environment;

import java.math.BigDecimal;
import java.math.MathContext;

import org.jakstab.asm.DataType;
import org.jakstab.asm.Operand;
import org.jakstab.asm.x86.X86Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.DoubleValue;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.SymbolValue;
import v2.org.analysis.value.Value;

public class FPURegister {
	private Value st0, st1, st2, st3, st4, st5, st6, st7;
	
	// FPU tag word
	private Value tag0, tag1, tag2, tag3,tag4, tag5, tag6, tag7;
	
	@Override
	public FPURegister clone(){
		FPURegister ret = new FPURegister();
		ret.setFPURegisterValue("st0", st0.clone());
		ret.setFPURegisterValue("st1", st1.clone());
		ret.setFPURegisterValue("st2", st2.clone());
		ret.setFPURegisterValue("st3", st3.clone());
		ret.setFPURegisterValue("st4", st4.clone());
		ret.setFPURegisterValue("st5", st5.clone());
		ret.setFPURegisterValue("st6", st6.clone());
		ret.setFPURegisterValue("st7", st7.clone());
		return ret;
	}
	
	public FPURegister() {
		st0 = new SymbolValue("st0");
		st1 = new SymbolValue("st1");
		st2 = new SymbolValue("st2");
		st3 = new SymbolValue("st3");
		st4 = new SymbolValue("st4");
		st5 = new SymbolValue("st5");
		st6 = new SymbolValue("st6");
		st7 = new SymbolValue("st7");		
	}
	
	public Value getFPURegisterValue(String registerName){
		String reg = checkRegisterName(registerName);
		
		if (reg.equals("st0")) {
			return st0;
		}
		if (reg.equals("st1")) {
			return st1;
		}
		if (reg.equals("st2")) {
			return st2;
		}
		if (reg.equals("st3")) {
			return st3;
		}
		if (reg.equals("st4")) {
			return st4;
		}
		if (reg.equals("st5")) {
			return st5;
		}
		if (reg.equals("st6")) {
			return st6;
		}
		if (reg.equals("st7")) {
			return st7;
		}		
		return null;
	}
	
	public void setFPURegisterValue(String registerName, Value value) {
		String reg = checkRegisterName(registerName);
		if (reg.equals("st0")) {
			st0 = value;
		} else if(reg.equals("st1")) {
			st1 = value;
		} else if(reg.equals("st2")) {
			st2 = value;
		} else if(reg.equals("st3")) {
			st3 = value;
		} else if(reg.equals("st4")) {
			st4 = value;
		} else if(reg.equals("st5")) {
			st5 = value;
		} else if(reg.equals("st6")) {
			st6 = value;
		} else if(reg.equals("st7")) {
			st7 = value;
		}
		FPUTagWord();
	}
	
	private String checkRegisterName(String registerName) {
		// TODO Auto-generated method stub
		String ret = "";
		if (registerName.startsWith("%")) {
			ret = registerName.substring(1).toLowerCase();
		} else {
			ret = registerName.toLowerCase();
		}
		return ret;
	}
	
	public void printInfor() {
		System.out.println("Information of FPU Register:");
		System.out.println("st0:" + st0.toString());
		System.out.println("st1:" + st1.toString());
		System.out.println("st2:" + st2.toString());
		System.out.println("st3:" + st3.toString());
		System.out.println("st4:" + st4.toString());
		System.out.println("st5:" + st5.toString());
		System.out.println("st6:" + st6.toString());
		System.out.println("st7:" + st7.toString());	
		System.out.println("tag0:" + tag7.toString());	
		System.out.println("tag1:" + tag7.toString());	
		System.out.println("tag2:" + tag7.toString());	
		System.out.println("tag3:" + tag7.toString());	
		System.out.println("tag4:" + tag7.toString());	
		System.out.println("tag5:" + tag7.toString());	
		System.out.println("tag6:" + tag7.toString());	
		System.out.println("tag7:" + tag7.toString());	
	}
	
	public void reset() {			
		this.st0 = null;
		this.st1 = null;
		this.st2 = null;
		this.st3 = null;
		this.st4 = null;
		this.st5 = null;
		this.st6 = null;
		this.st7 = null;
		FPUTagWord();
	}
	
	private int checkValue(Value number){
		// 0 : valid
		// 1 : Zero
		// 2 : invalid NaN, Infinite
		// 3 : null
		int result = 0;
		if (number  instanceof DoubleValue ) {
			double temp = ((DoubleValue) number).getValue();
			if (temp == 0){
				result = 1;
			}
			else if (!Double.isFinite(temp)){
				result = 2;
			}			
		}
		else if (number == null){
			result = 3;
		}		
		return result;
	}
		
	public void FPUTagWord(){
		tag0 = new LongValue(checkValue(this.st0));
		tag1 = new LongValue(checkValue(this.st1));
		tag2 = new LongValue(checkValue(this.st2));
		tag3 = new LongValue(checkValue(this.st3));
		tag4 = new LongValue(checkValue(this.st4));
		tag5 = new LongValue(checkValue(this.st5));
		tag6 = new LongValue(checkValue(this.st6));
		tag7 = new LongValue(checkValue(this.st7));		
	}
	
	public void FADDP(double number){		
		Value [] array =  new Value[8];		
		getArrayST(array);	
		for (int i = 1 ; i < 7 ; i++) {
			array[i] = array[i + 1];
		}
		array[0] = new DoubleValue (number);
		array[7] = null;
		setArrayST(array);
	}
	
	public void POP() {
		Value [] array =  new Value[8];		
		getArrayST(array);	
		for (int i = 0 ; i < 7 ; i++) {
			array[i] = array[i + 1];
		}		
		array[7] = null;
		setArrayST(array);
		
	}
	
	public void FLD(double number){
		Value [] array =  new Value[8];		
		getArrayST(array);		
		for (int i = 7 ; i > 0 ; i--) {
			array[i] = array[i - 1];
		}
		array[0] = new DoubleValue (number);
		setArrayST(array);			
	}
	
	public void FDECSTP(){
		Value [] array =  new Value[8];		
		getArrayST(array);
		Value temp = array[7];
		for (int i = 7 ; i > 0 ; i--) {
			array[i] = array[i - 1];
		}
		array[0] = temp;
		setArrayST(array);		
	}
	
	public void setArrayST(Value array[]){
		this.st0 = array[0];
		this.st1 = array[1];
		this.st2 = array[2];
		this.st3 = array[3];
		this.st4 = array[4];
		this.st5 = array[5];
		this.st6 = array[6];
		this.st7 = array[7];
		FPUTagWord();
	}
	
	public Value[] getArrayST(Value array[]){		
		array[0] = this.st0;
		array[1] = this.st1;
		array[2] = this.st2;
		array[3] = this.st3;
		array[4] = this.st4;
		array[5] = this.st5;
		array[6] = this.st6;
		array[7] = this.st7;		
		return array;
	}

	public void mov(String dest, DoubleValue val) {
		// TODO Auto-generated method stub
		Value d = getFPURegisterValue(dest);

		if (d == null) {
			System.out.println("Mov Function Fail:" + dest + " Value:" + val.toString());
			return;
		}

		d = d.movFunction(val);
		// d = normalizeValue(d, dest);
		setFPURegisterValue(dest, d);
	}

	public void FINCSTP() {
		// TODO Auto-generated method stub
		Value [] array =  new Value[8];		
		getArrayST(array);
		Value temp = array[0];
		for (int i = 0 ; i < 7 ; i++) {
			array[i] = array[i + 1];
		}
		array[7] = temp;
		setArrayST(array);		
	}	
	
	public void FDIV(Value divisor , Value dividend, String str_dest, Environment env){
		
		// ST0 || divisor == null, divided with NULL		
		if (divisor == null || dividend == null) {
			setFPURegisterValue(str_dest, new DoubleValue(Double.NaN));
			env.getFST().changeUnderflow();
		} else {
			double temp_dividend = 0.0;
			double temp_divisor = 0.0;		
			if(dividend instanceof LongValue) {
				temp_dividend = ((LongValue)dividend).getValue();
			}
			else {
				temp_dividend = ((DoubleValue) dividend).getValue();
			}	
			if (divisor instanceof LongValue){
				temp_divisor = ((LongValue)divisor).getValue();
			}
			else {
				temp_divisor = ((DoubleValue) divisor).getValue();
			}
				
			// ST0 || divisor == NaN, divided == NaN
			if (Double.isNaN(temp_divisor) || Double.isNaN(temp_dividend)) {
				setFPURegisterValue(str_dest, new DoubleValue(Double.NaN));
				env.getFST().setC1(new BooleanValue(false));
			}
			// xu ly divisor == infinity va divided == infinity
			// ST0 || divisor = infinity
			// // ST0/divisor == 0 and dest == 0
			else if ((Double.isInfinite(temp_divisor) && Double.isInfinite(temp_dividend))
					|| (temp_divisor == 0 && temp_dividend == 0)) {
				setFPURegisterValue(str_dest, new DoubleValue(Double.NaN));
				env.getFST().setIE(new BooleanValue(true));
			}
			// ST0 || divisor = infinity
			else if ((Double.isInfinite(temp_divisor) && !Double.isInfinite(temp_dividend))) {
				// not change FST
				// st0 = infinity (neg or positive) belong st0
			}
			// divided == infinity
			else if (Double.isInfinite(temp_dividend) && !Double.isInfinite(temp_divisor)) {
				// st0/divisor = 0
				setFPURegisterValue(str_dest, new DoubleValue(0.0));
			}

			// divided == 0, ST0 != 0
			else if (temp_divisor > 0 && temp_dividend == 0) {
				setFPURegisterValue(str_dest, new DoubleValue(Double.POSITIVE_INFINITY));
				env.getFST().setZE(new BooleanValue(true));
			} else if (temp_divisor < 0 && temp_dividend == 0) {
				setFPURegisterValue(str_dest, new DoubleValue(Double.NEGATIVE_INFINITY));
				env.getFST().setZE(new BooleanValue(true));
			}
			// ST0 = finity
			else {
				double result = temp_divisor / temp_dividend;
				BigDecimal y = new BigDecimal(temp_dividend);
				BigDecimal x = new BigDecimal(temp_divisor);
				BigDecimal exact_result = x.divide(y, MathContext.DECIMAL128);
				setFPURegisterValue(str_dest, new DoubleValue(result));
				env.getFST().changeFDIV(result, exact_result);
			}
		}
	}
	
	public void FSUB(Value suntract , Value subtrahend, String str_suntract, Environment env){	
		// ST0 || suntract == null, subtrahend with NULL
		if (suntract == null || subtrahend == null) {
			env.getFPUregister().setFPURegisterValue(str_suntract, new DoubleValue(Double.NaN));
			env.getFST().changeUnderflow();
		} else {
			double temp_subtrahend = 0.0;
			double temp_suntract = 0.0;
			if (subtrahend instanceof LongValue) {
				temp_subtrahend = ((LongValue) subtrahend).getValue();
			} else {
				temp_subtrahend = ((DoubleValue) subtrahend).getValue();
			}
			if (suntract instanceof LongValue) {
				temp_suntract = ((LongValue) suntract).getValue();
			} else {
				temp_suntract = ((DoubleValue) suntract).getValue();
			}

			// ST0 || suntract == NaN, subtrahend == NaN
			if (Double.isNaN(temp_suntract) || Double.isNaN(temp_subtrahend)) {
				env.getFPUregister().setFPURegisterValue(str_suntract, new DoubleValue(Double.NaN));
				env.getFST().setC1(new BooleanValue(false));
			}

			// xu ly suntract == infinity va subtrahend == infinity
			// ST0 || suntract = infinity
			else if ((Double.isInfinite(temp_suntract) && Double.isInfinite(temp_subtrahend))) {
				env.getFPUregister().setFPURegisterValue(str_suntract, new DoubleValue(Double.NaN));
				env.getFST().setIE(new BooleanValue(true));
			}

			// ST0 || suntract = infinity || subtrahend == infinity
			else if ((Double.isInfinite(temp_suntract) || Double.isInfinite(temp_subtrahend))) {
				double result = temp_suntract - temp_subtrahend;
				env.getFPUregister().setFPURegisterValue(str_suntract, new DoubleValue(result));
			}

			// ST0 = finity
			else {
				double result = temp_suntract - temp_subtrahend;
				BigDecimal y = new BigDecimal(temp_subtrahend);
				BigDecimal x = new BigDecimal(temp_suntract);
				BigDecimal exact_result = x.subtract(y, MathContext.DECIMAL128);
				env.getFPUregister().setFPURegisterValue(str_suntract, new DoubleValue(result));
				env.getFST().changeResult(result, exact_result);
			}
		}
	}
	
	public void FMUL(Value multi_x , Value multi_y, String str_dest, Environment env){	
		// ST0 || multi_x == null, multi_y with NULL
		if (multi_x == null || multi_y == null) {
			env.getFPUregister().setFPURegisterValue(str_dest, new DoubleValue(Double.NaN));
			env.getFST().changeUnderflow();
		} else {
			double temp_multi_x = 0.0;
			double temp_multi_y = 0.0;
			if (multi_x instanceof LongValue) {
				temp_multi_x = ((LongValue) multi_x).getValue();
			} else {
				temp_multi_x = ((DoubleValue) multi_x).getValue();
			}
			if (multi_y instanceof LongValue) {
				temp_multi_y = ((LongValue) multi_y).getValue();
			} else {
				temp_multi_y = ((DoubleValue) multi_y).getValue();
			}

			// ST0 || multi_x == NaN, multi_y == NaN
			if (Double.isNaN(temp_multi_x) || Double.isNaN(temp_multi_y)) {
				env.getFPUregister().setFPURegisterValue(str_dest, new DoubleValue(Double.NaN));
				env.getFST().setC1(new BooleanValue(false));
			}
			// xu ly multi_x == infinity va multi_y == 0
			// ST0 || multi_x = infinity
			// multi_y == infinity va multi_x == 0
			else if ((Double.isInfinite(temp_multi_x) && temp_multi_y == 0)
					|| (temp_multi_x == 0 && (Double.isInfinite(temp_multi_y)))) {
				env.getFPUregister().setFPURegisterValue(str_dest, new DoubleValue(Double.NaN));
				env.getFST().setIE(new BooleanValue(true));
			}
			// ST0 || multi_x = infinity || multi_y = infinity
			else if ((Double.isInfinite(temp_multi_x) || Double.isInfinite(temp_multi_y))) {
				double result = temp_multi_x * temp_multi_y;
				env.getFPUregister().setFPURegisterValue(str_dest, new DoubleValue(result));
			}

			// ST0 = finity
			else {
				double result = temp_multi_x * temp_multi_y;
				BigDecimal y = new BigDecimal(temp_multi_y);
				BigDecimal x = new BigDecimal(temp_multi_x);
				BigDecimal exact_result = x.multiply(y, MathContext.DECIMAL128);
				env.getFPUregister().setFPURegisterValue(str_dest, new DoubleValue(result));
				env.getFST().changeResult(result, exact_result);
			}
		}
	}
	
	public void FADD(Value add_x , Value add_y, String str_dest, Environment env){	
		// ST0 || add_x == null, add_y with NULL
		if (add_x == null || add_y == null) {
			env.getFPUregister().setFPURegisterValue(str_dest, new DoubleValue(Double.NaN));
			env.getFST().changeUnderflow();
		} else {
			double temp_add_x = 0.0;
			double temp_add_y = 0.0;
			if (add_x instanceof LongValue) {
				temp_add_x = ((LongValue) add_x).getValue();
			} else {
				temp_add_x = ((DoubleValue) add_x).getValue();
			}
			if (add_y instanceof LongValue) {
				temp_add_y = ((LongValue) add_y).getValue();
			} else {
				temp_add_y = ((DoubleValue) add_y).getValue();
			}

			// ST0 || add_x == NaN, add_y == NaN
			if (Double.isNaN(temp_add_x) || Double.isNaN(temp_add_y)) {
				env.getFPUregister().setFPURegisterValue(str_dest, new DoubleValue(Double.NaN));
				env.getFST().setC1(new BooleanValue(false));
			}
			// xu ly add_x == infinity va add_y == infinity
			// ST0 || add_x = infinity
			else if ((Double.isInfinite(temp_add_x) && (Double.isInfinite(temp_add_y)))) {
				env.getFPUregister().setFPURegisterValue(str_dest, new DoubleValue(Double.NaN));
				env.getFST().setIE(new BooleanValue(true));
			}
			// ST0 || add_x = infinity || add_y = infinity
			else if ((Double.isInfinite(temp_add_x) || Double.isInfinite(temp_add_y))) {
				double result = temp_add_x + temp_add_y;
				env.getFPUregister().setFPURegisterValue(str_dest, new DoubleValue(result));
			}

			// ST0 = finity
			else {
				double result = temp_add_x + temp_add_y;
				BigDecimal y = new BigDecimal(temp_add_y);
				BigDecimal x = new BigDecimal(temp_add_x);
				BigDecimal exact_result = x.add(y, MathContext.DECIMAL128);
				env.getFPUregister().setFPURegisterValue(str_dest, new DoubleValue(result));
				env.getFST().changeResult(result, exact_result);
			}
		}
	}
	

	public void FIST(Value st02, Operand dest, Environment env, X86Instruction inst) {
		// TODO Auto-generated method stub
		// NULL || NaN || Infinity => 8000 0000 (32), 8000 (16)
		if (st0 == null) {
			if (((X86MemoryOperand) dest).getDataType() == DataType.INT64) {
				// 8000 0000 0000 0000 
				// chua xu ly
			}
			if (((X86MemoryOperand) dest).getDataType() == DataType.INT32) {
				// 8000 0000
				env.getMemory().setMemoryValue((X86MemoryOperand) dest, new LongValue(-2147483648), inst);
			}
			if (((X86MemoryOperand) dest).getDataType() == DataType.INT16) {
				// 8000
				env.getMemory().setMemoryValue((X86MemoryOperand) dest, new LongValue(32768), inst);
			}
			env.getFST().changeUnderflow();
		} else {
			double temp_st0 = ((DoubleValue) st0).getValue();
			// NaN
			if (Double.isNaN(temp_st0) || Double.isInfinite(temp_st0)) {
				if (((X86MemoryOperand) dest).getDataType() == DataType.INT32) {
					// 8000 0000
					env.getMemory().setMemoryValue((X86MemoryOperand) dest, new LongValue(-2147483648), inst);
				}
				if (((X86MemoryOperand) dest).getDataType() == DataType.INT16) {
					// 8000
					env.getMemory().setMemoryValue((X86MemoryOperand) dest, new LongValue(32768), inst);
				}
				if (Double.isInfinite(temp_st0)) {
					env.getFST().setIE(new BooleanValue(true));
				}
				env.getFST().setC1(new BooleanValue(false));
			}
			// finity
			else {
				Long temp = Math.round(temp_st0);
				long result = Long.valueOf(temp.intValue());
				double temp_result = result;
				env.getMemory().setMemoryValue((X86MemoryOperand) dest, new LongValue(result), inst);
				if (Math.abs(temp_result) < Math.abs(temp_st0)) {
					env.getFST().setPE(new BooleanValue(true));
				} else if (Math.abs(temp_result) > Math.abs(temp_st0)) {
					env.getFST().setPE(new BooleanValue(true));
					env.getFST().setC1(new BooleanValue(true));
				}
			}
		}
	}	
}
