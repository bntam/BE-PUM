package v2.org.analysis.transition_rule.x86instruction;

import java.math.BigDecimal;
import java.math.RoundingMode;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.stub.X86InstructionStub;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.DoubleValue;
import v2.org.analysis.value.Value;

public class fprem extends X86InstructionStub {

	@Override
	public BPState execute() {
		// TODO Auto-generated method stub
		//System.out.println("Instruction: " + inst.getName());
		Value st0 = null;
		Value st1 = null;
		st0 = env.getFPUregister().getFPURegisterValue("st0");
		st1 = env.getFPUregister().getFPURegisterValue("st1");
		// ST0 == null || ST1 == null
		if (st0 == null || st1 == null) {
			env.getFPUregister().setFPURegisterValue("st0", new DoubleValue(Double.NaN));						
			env.getFST().changeUnderflow();
		}
		else {
			double temp_st0 = ((DoubleValue) st0).getValue();
			double temp_st1 = ((DoubleValue) st1).getValue();
			// ST0 == NaN || ST1 == NaN			
			if ( Double.isNaN(temp_st0) || Double.isNaN(temp_st1)){
				env.getFPUregister().setFPURegisterValue("st0", new DoubleValue(Double.NaN));				
				env.getFST().setC1(new BooleanValue(false));							
			}
			// ST0 = Infinity
			// ST1 = 0
			// => ST0 = NaN, IE = true
			else if ((Double.isInfinite(temp_st0)) || (temp_st1 == 0)){
				env.getFPUregister().setFPURegisterValue("st0", new DoubleValue(Double.NaN));				
				env.getFST().setC1(new BooleanValue(false));			
				env.getFST().setIE(new BooleanValue(true));
			}
			// ST1 = Infnity && ST0 = Finity
			// not change
			else if (Double.isInfinite(temp_st1) && Double.isFinite(temp_st0)){
				env.getFST().setC1(new BooleanValue(false));		
			}
			// ST0 == 0 && ST1 = +-F
			// ST0 = 0
			else  if (temp_st0 == 0 && Double.isFinite(temp_st1)){
				env.getFPUregister().setFPURegisterValue("st0", new DoubleValue(0.0));				
				env.getFST().setC1(new BooleanValue(false));	
			}
			// ST0 = Finity && ST1 = Finity
			else if (Double.isFinite(temp_st0) && Double.isFinite(temp_st1)){
				FPREM(temp_st0, temp_st1, env);
			}
		}
		//System.out.println("Value FST: " + Convert.longToHex(env.getFST().getValueFST()));
		return null;
	}
	
	public static void FPREM(double st0, double st1, Environment env){		
		int D = Math.getExponent(st0) - Math.getExponent(st1);		
		if (D < 64) {
			double temp = st0 / st1;
			int Q = round_toword_zero(temp);		
			double temp_st0 = st0 - (st1*Q);
			env.getFPUregister().setFPURegisterValue("st0", new DoubleValue(temp_st0));
			env.getFST().changeUFlag(temp_st0);
			Q = Math.abs(Q);				
			env.getFST().setC0(new BooleanValue(true_false(getQ2(Q))));
			env.getFST().setC1(new BooleanValue(true_false(getQ0(Q))));
			env.getFST().setC2(new BooleanValue(false));
			env.getFST().setC3(new BooleanValue(true_false(getQ1(Q))));			
		}
		else {
//			C2 = 1;
//			N = Constant; //This is an implementation-dependent number between 32 and 63.
//			QQ = Integer(TruncateTowardZero((ST(0) / ST(1)) / 2 * (D - N)));
//			ST(0) = ST(0) - (ST(1) * QQ * 2(D - N));
			env.getFST().setC2(new BooleanValue(true));
			int N = 63;
			double temp = (st0 / st1) / 2 *(D - N);
			int QQ = round_toword_zero(temp);
			double temp_st0 = st0 - (st1 * QQ * 2 * (D-N) );
			env.getFPUregister().setFPURegisterValue("st0", new DoubleValue(temp_st0));
		}
	}
	
	public static boolean true_false(byte x){
		if (x == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public static byte getQ0(int val) {		
		return (byte) (val & 1);
	}
	
	public static byte getQ1(int val){
		int temp = val>>1;		
		return (byte) (temp & 1);
	}
	
	public static byte getQ2(int val){
		int temp = val>>>2;		
		return (byte) (temp & 1);
	}
	
	public static int round_toword_zero(double x){
		BigDecimal value = new BigDecimal(x);
		value = value.setScale(0, RoundingMode.DOWN);
		int result = value.intValue();
		return result;
	}
	
}
