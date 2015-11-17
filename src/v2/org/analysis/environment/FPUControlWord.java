package v2.org.analysis.environment;

import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.SymbolValue;
import v2.org.analysis.value.Value;

public class FPUControlWord {
	private Value IM, DM, ZM, OM, UM, PM, PC, RC, X;
	// IM: Invalid operation
	// DM: Denormalized operand
	// ZM: Zero Divide
	// OM: Overflow
	// UM: Underflow
	// PM: Precision
	// PC: Precision Control
	// RC: Rounding Control
	// X: Infinity Control 
	
	public FPUControlWord(){
		//setAFlag(new SymbolValue("aFlag"));
		setIM(new SymbolValue("IM"));
		setDM(new SymbolValue("DM"));
		setZM(new SymbolValue("ZM"));
		setOM(new SymbolValue("OM"));
		setUM(new SymbolValue("UM"));
		setPM(new SymbolValue("PM"));
		setPC(new SymbolValue("PC"));
		setRC(new SymbolValue("RC"));
		setX(new SymbolValue("X"));	
	}
	
	@Override
	public FPUControlWord clone(){
		FPUControlWord c = new FPUControlWord();
		c.setIM(this.IM.clone());
		c.setDM(this.DM.clone());
		c.setZM(this.ZM.clone());
		c.setOM(this.OM.clone());
		c.setUM(this.UM.clone());
		c.setPM(this.PM.clone());
		c.setPC(this.PC.clone());
		c.setRC(this.RC.clone());
		c.setX(this.X.clone());		
		return c;
	}
	
	public void inti(){		
		setIM(new BooleanValue(true));
		setDM(new BooleanValue(true));		
		setZM(new BooleanValue(true));
		setOM(new BooleanValue(true));
		setUM(new BooleanValue(true));
		setPM(new BooleanValue(true));
		
		setPC(new LongValue(2));
		setRC(new LongValue(0));
		setX(new BooleanValue(false));			
	}
	
	public void reset() {
		
		setIM(new BooleanValue(true));
		setDM(new BooleanValue(true));		
		setZM(new BooleanValue(true));
		setOM(new BooleanValue(true));
		setUM(new BooleanValue(true));
		setPM(new BooleanValue(true));
		
		setPC(new LongValue(2));
		setRC(new LongValue(0));
		setX(new BooleanValue(false));
	}
	
	@Override
	public String toString(){
		return "IM=" + IM.toString() + "," + "DM=" + DM.toString() + "," + "ZM=" + ZM.toString() + "," +
			   "OM=" + OM.toString() + "," + "UM=" + UM.toString() + "," + "PM=" + PM.toString() + "," +
			   "PC=" + PC.toString() + "," + "RC=" + RC.toString() + "," + "X=" + X.toString();			   
	}
	
	// get value of FPU startus word
	public Value getIM() {
		return IM;
	}

	public Value getDM() {
		return DM;
	}

	public Value getZM() {
		return ZM;
	}

	public Value getOM() {
		return OM;
	}

	public Value getUM() {
		return UM;
	}

	public Value getPM() {
		return PM;
	}

	public Value getPC() {
		return PC;
	}

	public Value getRC() {
		return RC;
	}

	public Value getX() {
		return X;
	}	

	// Set value of FPU startus word
	public void setIM(Value IM) {
		this.IM = IM;
	}

	public void setDM(Value DM) {
		this.DM = DM;
	}

	public void setZM(Value ZM) {
		this.ZM = ZM;
	}

	public void setOM(Value OM) {
		this.OM = OM;
	}

	public void setUM(Value UM) {
		this.UM = UM;
	}

	public void setPM(Value PM) {
		this.PM = PM;
	}

	public void setPC(Value PC) {
		this.PC = PC;
	}

	public void setRC(Value RC) {
		this.RC = RC;
	}

	public void setX(Value X) {
		this.X = X;
	}	
	
	public long getValueFCW(){
		long temp = 0;
		long bit_0, bit_1, bit_2, bit_3, bit_4, bit_5,
			bit_6, bit_7, bit_8, bit_9, bit_10, bit_PC, bit_RC, 
			bit_11, bit_12;
		bit_0 = Boolean2Long(((BooleanValue) IM).getValue());
		bit_1 = Boolean2Long(((BooleanValue) DM).getValue());
		bit_2 = Boolean2Long(((BooleanValue) ZM).getValue());
		bit_3 = Boolean2Long(((BooleanValue) OM).getValue());
		bit_4 = Boolean2Long(((BooleanValue) UM).getValue());
		bit_5 = Boolean2Long(((BooleanValue) PM).getValue());
		bit_6 = Boolean2Long(true);
		bit_7 = Boolean2Long(false);
		
		bit_PC = ((LongValue) PC).getValue();
		bit_8 = bit_PC % 2;
		bit_9 = (bit_PC / 2 ) % 2;		
		
		bit_RC =  ((LongValue) RC).getValue();
		bit_10 = bit_RC % 2;
		bit_11 = (bit_RC / 2 ) % 2;		
		
		bit_12 =Boolean2Long(((BooleanValue) X).getValue());				
	
		temp = (bit_0 * 1) + (bit_1 * 2) + (bit_2 * 4) + (bit_3 * 8) + (bit_4 * 16)
				+ (bit_5 * 32) + (bit_6 * 64) + (bit_7 * 128) + (bit_8 * 256) + (bit_9 * 512)
				+ (bit_10 * 1024) + (bit_11 * 2048) + (bit_12 * 4096) + 0;
		return temp;
	}
	
	private long Boolean2Long(boolean bool){
		long temp = 0;
		if(bool == true) {
			temp = 1;
		}
		return temp;
	}
	
	public void setFCWValue(String stFCW, Value value) {
		// TODO Auto-generated method stub
		//String temp = stFCW.toLowerCase();
		  String temp = stFCW;
		
		if (temp.contains("IM")) {
			IM = value;
		} else if (temp.contains("DM")) {
			DM = value;
		} else if (temp.contains("ZM")) {
			ZM = value;
		} else if (temp.contains("OM")) {
			OM = value;
		} else if (temp.contains("UM")) {
			UM = value;
		} else if (temp.contains("PM")) {
			PM = value;
		} else if (temp.contains("PC")) {
			PC = value;
		} else if (temp.contains("RC")) {
			RC = value;
		} else if (temp.contains("X")) {
			X = value;
		}
	}

}
