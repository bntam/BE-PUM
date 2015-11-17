package v2.org.analysis.environment;

import java.math.BigDecimal;
import java.util.Map;

import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.DoubleValue;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.SymbolValue;
import v2.org.analysis.value.Value;

public class FPUStatusWord {
	private Value B, C3, TOP, C2, C1, C0, ES, SF, PE, UE, OE, ZE, DE, IE;
	private static int overflow = 0;
	// FST: FPU startus word
	// B: FPU busy
	// TOP: top of stack pointer
	// C3, C2, C1, C0: FPU condition code interpretion
	// ES: Error summary status
	// SF: Stack Fault
	// Exception Flags
	// PE: Precision, UE: uderflow, OE: Overflow, ZE: Zero Divide, DE: Denormalized Operand, IE: Invalid Operation
	
	public FPUStatusWord(){
		//setAFlag(new SymbolValue("aFlag"));
		setB(new SymbolValue("B"));
		setC3(new SymbolValue("C3"));
		setTOP(new SymbolValue("TOP"));
		setC2(new SymbolValue("C2"));
		setC1(new SymbolValue("C1"));
		setC0(new SymbolValue("C0"));
		setES(new SymbolValue("ES"));
		setSF(new SymbolValue("SF"));
		setPE(new SymbolValue("PE"));
		setUE(new SymbolValue("UE"));
		setOE(new SymbolValue("OE"));
		setZE(new SymbolValue("ZE"));
		setDE(new SymbolValue("DE"));
		setIE(new SymbolValue("IE"));
	}
	
	@Override
	public FPUStatusWord clone(){
		FPUStatusWord c = new FPUStatusWord();
		c.setB(this.B.clone());
		c.setC0(this.C0.clone());
		c.setC1(this.C1.clone());
		c.setC2(this.C2.clone());
		c.setC3(this.C3.clone());
		c.setTOP(this.TOP.clone());
		c.setES(this.ES.clone());
		c.setSF(this.SF.clone());
		c.setPE(this.PE.clone());
		c.setUE(this.UE.clone());
		c.setOE(this.OE.clone());
		c.setZE(this.ZE.clone());
		c.setDE(this.DE.clone());
		c.setIE(this.IE.clone());
		return c;
	}
	
	public void inti(){		
		setB(new BooleanValue(false));
		setC3(new BooleanValue(false));
		setTOP(new LongValue(0));
		setC2(new BooleanValue(false));
		setC1(new BooleanValue(false));
		setC0(new BooleanValue(false));
		setES(new BooleanValue(false));
		setSF(new BooleanValue(false));
		setPE(new BooleanValue(false));
		setUE(new BooleanValue(false));
		setOE(new BooleanValue(false));
		setZE(new BooleanValue(false));
		setDE(new BooleanValue(false));
		setIE(new BooleanValue(false));			
	}
	
	public void reset() {
		overflow = 0;
		setB(new BooleanValue(false));
		setC3(new BooleanValue(false));
		setTOP(new LongValue(0));
		setC2(new BooleanValue(false));
		setC1(new BooleanValue(false));
		setC0(new BooleanValue(false));
		setES(new BooleanValue(false));
		setSF(new BooleanValue(false));
		setPE(new BooleanValue(false));
		setUE(new BooleanValue(false));
		setOE(new BooleanValue(false));
		setZE(new BooleanValue(false));
		setDE(new BooleanValue(false));
		setIE(new BooleanValue(false));	
	}
	
	@Override
	public String toString(){
		return "B=" + B.toString() + "," + "C3=" + C3.toString() + "," + "TOP=" + TOP.toString() + "," +
			   "C2=" + C2.toString() + "," + "C1=" + C1.toString() + "," + "C0=" + C0.toString() + "," +
			   "ES=" + ES.toString() + "," + "SF=" + SF.toString() + "," + "PE=" + PE.toString() + "," +
			   "UE=" + UE.toString() + "," + "OE=" + OE.toString() + "," + "ZE=" + ZE.toString() + "," +
			   "DE=" + DE.toString() + "," + "IE=" + IE.toString() + ",";
	}
	
	//get value of FPU startus word
	public Value getB(){
		return B;
	}
	
	public Value getC3(){
		return C3;
	}
	
	public Value getTOP(){
		return TOP;
	}
	
	public Value getC2(){
		return C2;
	}
	
	public Value getC1(){
		return C1;
	}
	
	public Value getC0(){
		return C0;
	}
	
	public Value getES(){
		return ES;
	}
	
	public Value getSF(){
		return SF;
	}
	
	public Value getPE(){
		return PE;
	}
	
	public Value getUE(){
		return UE;
	}
	
	public Value getOE(){
		return OE;
	}
	
	public Value getZE() {
		return ZE;
	}
	
	public Value getDE(){
		return IE;
	}
		
	//Set value of FPU startus word
	public void setB(Value B){
		this.B = B;
	}
	
	public void setC3(Value C3){
		this.C3 = C3;
	}
	
	public void setTOP(Value TOP){
		this.TOP = TOP;
	}
	
	public void setC2(Value C2){
		this.C2 = C2;
	}
	
	public void setC1(Value C1){
		this.C1 = C1;
	}
	
	public void setC0(Value C0){
		this.C0 = C0;
	}
	
	public void setES(Value ES){
		this.ES = ES;
	}
	
	public void setSF(Value SF){
		this.SF = SF;
	}
	
	public void setPE(Value PE){
		this.PE = PE;
	}
	
	public void setUE(Value UE){
		this.UE = UE;
	}
	
	public void setOE(Value OE){
		this.OE = OE;
	}
	
	public void setZE(Value ZE){
		this.ZE = ZE;
	}
	
	public void setDE(Value DE){
		this.DE = DE;
	}
	
	public void setIE(Value IE){
		this.IE = IE;
	}
	
	public int getoverflow(){
		return  overflow;
	}
	
	public long getValueFST(){
		long temp = 0;
		long bit_0, bit_1, bit_2, bit_3, bit_4, bit_5,
			bit_6, bit_7, bit_8, bit_9, bit_10, bit_top, 
			bit_11, bit_12, bit_13, bit_14, bit_15;
		bit_0 = Boolean2Long(((BooleanValue) IE).getValue());
		bit_1 = Boolean2Long(((BooleanValue) DE).getValue());
		bit_2 = Boolean2Long(((BooleanValue) ZE).getValue());
		bit_3 = Boolean2Long(((BooleanValue) OE).getValue());
		bit_4 = Boolean2Long(((BooleanValue) UE).getValue());
		bit_5 = Boolean2Long(((BooleanValue) PE).getValue());
		bit_6 = Boolean2Long(((BooleanValue) SF).getValue());
		bit_7 = Boolean2Long(((BooleanValue) ES).getValue());
		bit_8 = Boolean2Long(((BooleanValue) C0).getValue());
		bit_9 = Boolean2Long(((BooleanValue) C1).getValue());
		bit_10 = Boolean2Long(((BooleanValue) C2).getValue());
		bit_14 = Boolean2Long(((BooleanValue) C3).getValue());
		bit_15 =Boolean2Long(((BooleanValue) B).getValue());
		bit_top = ((LongValue) TOP).getValue();
		
		bit_11 = bit_top % 2;
		bit_12 = (bit_top / 2 ) % 2;
		bit_13 = ((bit_top / 2 ) / 2 ) % 2;
	
		temp = (bit_0 * 1) + (bit_1 * 2) + (bit_2 * 4) + (bit_3 * 8) + (bit_4 * 16)
				+ (bit_5 * 32) + (bit_6 * 64) + (bit_7 * 128) + (bit_8 * 256) + (bit_9 * 512)
				+ (bit_10 * 1024) + (bit_11 * 2048) + (bit_12 * 4096) + (bit_13 * 8192) 
				+ (bit_14 * 16384) + (bit_15 * 32768);
		//xu ly gia tri
//		this.FST = new LongValue(temp);
		//setFSTValue("FST", new LongValue(temp));	
		return temp;
	}
	
	private long Boolean2Long(boolean bool){
		long temp = 0;
		if(bool == true) {
			temp = 1;
		}
		return temp;
	}
	
	public void setFSTValue(String stFST, Value value) {
		// TODO Auto-generated method stub
		String temp = stFST.toLowerCase();
		
		if (temp.contains("B")) {
			B = value;
		} else if (temp.contains("C3")) {
			C3 = value;
		} else if (temp.contains("TOP")) {
			TOP = value;
		} else if (temp.contains("C2")) {
			C2 = value;
		} else if (temp.contains("C1")) {
			C1 = value;
		} else if (temp.contains("C0")) {
			C0 = value;
		} else if (temp.contains("ES")) {
			ES = value;
		} else if (temp.contains("SF")) {
			SF = value;
		} else if (temp.contains("PE")) {
			PE = value;
		} else if (temp.contains("UE")) {
			UE = value;
		} else if (temp.contains("OE")) {
			OE = value;
		} else if (temp.contains("ZE")) {
			ZE = value;
		} else if (temp.contains("DE")) {
			DE = value;
		} else if (temp.contains("IE")) {
			IE = value;
		}
	}
	
	public void setValue(Map<String, Long> z3Value) {
		// TODO Auto-generated method stub		
		setFSTValue("B", B.evaluate(z3Value));
		setFSTValue("C3", C3.evaluate(z3Value));
		setFSTValue("TOP", TOP.evaluate(z3Value));
		setFSTValue("C2", C2.evaluate(z3Value));
		setFSTValue("C1", C1.evaluate(z3Value));
		setFSTValue("C0", C0.evaluate(z3Value));
		setFSTValue("ES", ES.evaluate(z3Value));
		setFSTValue("SF", SF.evaluate(z3Value));
		setFSTValue("PE", PE.evaluate(z3Value));
		setFSTValue("UE", UE.evaluate(z3Value));
		setFSTValue("OE", OE.evaluate(z3Value));
		setFSTValue("ZE", ZE.evaluate(z3Value));
		setFSTValue("DE", DE.evaluate(z3Value));
		setFSTValue("IE", IE.evaluate(z3Value));
	}
	
	public void changeUnderflow(){
		this.C1 = new BooleanValue(false);
		this.SF = new BooleanValue(true);
		this.IE = new BooleanValue(true);
		
	}
	
	public void changeInvaid(){
		this.SF = new BooleanValue(true);
		this.IE = new BooleanValue(true);
	}
	
	public void changFLD(long top){		
		if (top == 0) {
			this.TOP = new LongValue(7);
			overflow++;
		} else {			
			this.TOP = new LongValue(top - 1);
			overflow++;
		}
		if (overflow > 8){
			this.C1 = new BooleanValue(true);
			this.SF = new BooleanValue(true);
			this.IE = new BooleanValue(true);
		}
		else {
			this.C1 = new BooleanValue(false);
		}
	}
	
	public void changFCLEX(){
		this.IE = new BooleanValue(false);
		this.DE = new BooleanValue(false);
		this.ZE = new BooleanValue(false);
		this.OE = new BooleanValue(false);
		this.UE = new BooleanValue(false);
		this.PE = new BooleanValue(false);
		this.ES = new BooleanValue(false);
		this.ES = new BooleanValue(false);
		this.B = new BooleanValue(false);
		
		
	}

	public void changeFDECSTP() {
		// TODO Auto-generated method stub
		this.C1 = new BooleanValue(false);
		overflow = 8;
		long t_top = ((LongValue) this.TOP).getValue();
		if (t_top == 0){
			this.TOP = new LongValue(7);			
		}
		else {
			this.TOP = new LongValue(t_top - 1);			
		}
		
	}

	public void changeFINCSTP() {
		// TODO Auto-generated method stub
		this.C1 = new BooleanValue(false);
		overflow = 8;
		long t_top = ((LongValue) this.TOP).getValue();
		if (t_top == 7){
			this.TOP = new LongValue(0);			
		}
		else {
			this.TOP = new LongValue(t_top + 1);			
		}
		
	}

	public void changeFXAM(Value st0) {
		// TODO Auto-generated method stub
		int choose = 0; //Unsupported (0)
		double t_st0;
		if (st0 instanceof DoubleValue) {
			t_st0 = ((DoubleValue) st0).getValue();
			int exponent = Math.getExponent(t_st0);			
			if (t_st0 == Double.NaN) {
				//NaN
				this.C1 = new BooleanValue(true);				
				choose = 1;
			} else if (t_st0 == Double.NEGATIVE_INFINITY || t_st0 == Double.POSITIVE_INFINITY) {
				//Infinity
				if (t_st0 > 0) {
					this.C1 = new BooleanValue(false);
				} else {
					this.C1 = new BooleanValue(true);
				}				
				choose = 3;
			} else if (t_st0 == 0.0) {
				//Zero
				this.C1 = new BooleanValue(false);				
				choose = 4;						
			} else if (exponent == -1022 || exponent == -1023) {
				//Denormal
				if (t_st0 > 0) {
					this.C1 = new BooleanValue(false);
				} else {
					this.C1 = new BooleanValue(true);
				}
				choose = 6;
			} else {
				//Normal
				if (t_st0 > 0) {
					this.C1 = new BooleanValue(false);
				} else {
					this.C1 = new BooleanValue(true);
				}
				
				choose = 2;
			}			
		}
		else if (st0 == null){
			//Null
			this.C1 = new BooleanValue(false);	
			choose = 5;
		}
		switch (choose) {
		case 1: //NaN
			this.C3 = new BooleanValue(false);
			this.C2 = new BooleanValue(false);
			this.C0 = new BooleanValue(true);
			break;
		case 2: //Normal
			this.C3 = new BooleanValue(false);
			this.C2 = new BooleanValue(true);
			this.C0 = new BooleanValue(false);
			break;
		case 3: //INFINITY
			this.C3 = new BooleanValue(false);
			this.C2 = new BooleanValue(true);
			this.C0 = new BooleanValue(true);
			break;
		case 4: //Zero
			this.C3 = new BooleanValue(true);
			this.C2 = new BooleanValue(false);
			this.C0 = new BooleanValue(false);
			break;
		case 5: //Null
			this.C3 = new BooleanValue(true);
			this.C2 = new BooleanValue(false);
			this.C0 = new BooleanValue(true);
			break;
		case 6: //Denormal
			this.C3 = new BooleanValue(true);
			this.C2 = new BooleanValue(true);
			this.C0 = new BooleanValue(false);
			break;

		default: //Unsupported
			this.C3 = new BooleanValue(false);
			this.C2 = new BooleanValue(false);
			this.C0 = new BooleanValue(false);
			break;
		}
	}
	
	public void changeF2XM1(double number, double st0){
		changeDFlag(number);
		changeUFlag(number);
		BigDecimal x = new BigDecimal(Math.pow(2, st0) - 1);
		int index = Double.toString(number).length();			
		changePFlag(x, number, index);
				
	}
	
	public void changeFADD(double number, BigDecimal exact_number){
		boolean temp_C1 = ((BooleanValue)this.C1).getValue();
		if (Double.isNaN(number) && temp_C1) {
			this.C1 = new BooleanValue(false);
		}
		else if (Double.isInfinite(number)){
			this.IE = new BooleanValue(true);
		}
		else {
			changeDFlag(number);
			changeUFlag(number);
			changeOFlag(number);
			int index = Double.toString(number).length();			
			changePFlag(exact_number, number, index);
			
		}		
	}
	
	public void changeFDIV(double number, BigDecimal exact_number){
		boolean temp_C1 = ((BooleanValue)this.C1).getValue();
		if (Double.isNaN(number) && temp_C1) {
			this.C1 = new BooleanValue(false);
		}
		else if (Double.isInfinite(number)){
			this.IE = new BooleanValue(true);
		}
		else {
			changeDFlag(number);
			changeUFlag(number);
			changeOFlag(number);
			int index = Double.toString(number).length();			
			changePFlag(exact_number, number, index);		
		}		
	}
	
	public void changeResult(double number, BigDecimal exact_number){
		boolean temp_C1 = ((BooleanValue)this.C1).getValue();
		if (Double.isNaN(number) && temp_C1) {
			this.C1 = new BooleanValue(false);
		}
		else if (Double.isInfinite(number)){
			this.IE = new BooleanValue(true);
		}
		else {
			changeDFlag(number);
			changeUFlag(number);
			changeOFlag(number);
			int index = Double.toString(number).length();			
			changePFlag(exact_number, number, index);		
		}		
	}
	
	private void changeUFlag(double number){
		double Max = 1 * Math.pow(2, -1022);
    	double Min = -1 * Math.pow(2, -1022);
    	if (number <= Max && number >= Min && number!= 0.0){
    		this.UE = new BooleanValue(true);
    	}
	}
	
	private void changeOFlag(double number){
		double Max = 1 * Math.pow(2, 1024);
		double Min = -1 * Math.pow(2, 1024);
		if (number >= Max || number <= Min ){
			this.OE = new BooleanValue(true);
		}
	}
	
	private void changeDFlag(double number){
		int exponent = Math.getExponent(number);
		if ( (exponent == -1022 || exponent == -1023) && number != 0.0  ) {
			this.DE = new BooleanValue(true);
		}			
	}
	
	private void changePFlag(BigDecimal unrounded, double number, int index){	
		if (index >= 18) { 
			String str_unrounded = unrounded.toPlainString();
			int intNumber_0 = Integer.parseInt(Character.toString(str_unrounded.charAt(index)));
			int intNumber_1 = Integer.parseInt(Character.toString(str_unrounded.charAt(index + 1)));
			if (intNumber_0 >= 5) {
				this.PE = new BooleanValue(true);
				this.C1 = new BooleanValue(true);
			}
			// number 0 1
			// if (number_1) >5 => round = (number 0) + 1
			// example 1.2345 => 1.235 => 1.24
			else if (intNumber_1 >= 5 && (intNumber_0 + 1) >= 5) {
				this.PE = new BooleanValue(true);
				this.C1 = new BooleanValue(true);
			} 
			// co xu khong chinh xac cua result, ko lam tron
			else {
				this.PE = new BooleanValue(true);
			}
		}
		else if (index >=1 ){
			String str = unrounded.toPlainString();
			double exact = 0.0;
			if (str.length() >= 17) {
				String str_exact = str.substring(0,17);
				exact = Double.parseDouble(str_exact);
			}
			else {
				exact = Double.parseDouble(str);
			}
			
			if (Math.abs(exact) < Math.abs(number)) {
				this.PE = new BooleanValue(true);
				this.C1 = new BooleanValue(true);
			}
		}
	}
	
	private void changeZFlag(double number){
		
	}

	public void changeFADDP(long top) {
		if (top == 7) {
			this.TOP = new LongValue(0);			
		} else {			
			this.TOP = new LongValue(top + 1);			
		}		
	}
	
	public void changePOP(long top){
		if (top == 7) {
			this.TOP = new LongValue(0);			
		} else {			
			this.TOP = new LongValue(top + 1);			
		}
	}
	
	public void changeFCOM(Value st0, Value src) {
		// TODO Auto-generated method stub
		if (st0 == null || src == null){
			this.C3 = new BooleanValue(true);
			this.C2 = new BooleanValue(true);
			this.C0 = new BooleanValue(true);
			changeInvaid();
		}
		else {
			double temp_st0 = ((DoubleValue) st0).getValue();
			double temp_src = 0.0;
			if (src instanceof LongValue){
				temp_src = ((LongValue)src).getValue();
			}
			else {
				temp_src = ((DoubleValue) src).getValue();
			}
			
			if (Double.isNaN(temp_src) || Double.isNaN(temp_st0)){
				this.C3 = new BooleanValue(true);
				this.C2 = new BooleanValue(true);
				this.C0 = new BooleanValue(true);
				changeUnderflow();
			}
			if (temp_st0 > temp_src){
				this.C3 = new BooleanValue(false);
				this.C2 = new BooleanValue(false);
				this.C0 = new BooleanValue(false);
			}
			if (temp_st0 < temp_src){
				this.C3 = new BooleanValue(false);
				this.C2 = new BooleanValue(false);
				this.C0 = new BooleanValue(true);
			}
			if (temp_st0 == temp_src){
				this.C3 = new BooleanValue(true);
				this.C2 = new BooleanValue(false);
				this.C0 = new BooleanValue(false);
			}
		}
	}
}
