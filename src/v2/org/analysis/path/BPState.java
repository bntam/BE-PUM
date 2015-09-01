/**
 * 
 */
package v2.org.analysis.path;

import java.util.Map;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.value.Formulas;

/**
 * @author NMHai
 * 
 */
public class BPState {
	private Environment env;
	private AbsoluteAddress location;
	private Instruction inst;
	private Formulas pathCondition;
	private boolean feasible = true;
	private int index = 0; // Binh usage

	/**
	 * @param env
	 * @param location
	 * @param inst
	 */
	public BPState(Environment env, AbsoluteAddress location, Instruction inst) {
		super();
		this.env = env;
		this.location = location;
		this.inst = inst;
	}

	public BPState() {
		super();
		this.env = null;
		this.location = null;
		this.inst = null;
	}

	public Environment getEnvironement() {
		return env;
	}

	public void setEnvironment(Environment env) {
		this.env = env;
	}

	public AbsoluteAddress getLocation() {
		return location;
	}

	public void setLocation(AbsoluteAddress location) {
		this.location = location;
	}

	public Instruction getInstruction() {
		return inst;
	}

	public void setInstruction(Instruction inst) {
		this.inst = inst;
	}

	public BPState clone() {
		BPState ret = new BPState();
		ret.setEnvironment(env.clone());
		ret.setFeasiblePath(feasible);
		ret.setInstruction(inst);
		if (location != null)
			ret.setLocation(new AbsoluteAddress(location.getValue()));
		else
			ret.setLocation(null);
		return ret;
	}

	public boolean checkFeasiblePath() {
		// TODO Auto-generated method stub
		return feasible;
	}

	/**
	 * @param fesible
	 *            the fesible to set
	 */
	public void setFeasiblePath(boolean fesible) {
		this.feasible = fesible;
	}

	public Formulas getPathCondition() {
		return pathCondition;
	}

	public void setPathCondition(Formulas pathCondition) {
		this.pathCondition = pathCondition;
	}

	// Binh usage
	public int getIndex() {
		return index;
	}
	
	// Binh usage
	public void setIndex(int index) {
		this.index = index;
	}

	public void setValue(Map<String, Long> z3Value) {
		// TODO Auto-generated method stub
		env.getRegister().setValue(z3Value);
		//env.getMemory().setValue(z3Value);
		env.getFlag().setValue(z3Value);
	}

}
