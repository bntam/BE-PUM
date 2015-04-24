/**
 * 
 */
package v2.org.analysis.path;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;
import v2.org.analysis.environment.Environment;

/**
 * @author NMHai
 * 
 */
public class BPState {
	private Environment env;
	private AbsoluteAddress location;
	private Instruction inst;
	private boolean feasible = true;

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

}