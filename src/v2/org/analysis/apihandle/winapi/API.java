/**
 * Project: BE_PUM
 * Package name: v2.org.analysis.apihandle.be_pum.winapi
 * File name: APIClass.java
 * Created date: Jan 30, 2015
 */
package v2.org.analysis.apihandle.winapi;

import java.util.ArrayList;
import java.util.List;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * 
 * The stub abstract class is used for describe some primary attributes &
 * methods
 * 
 * @author Yen Nguyen
 * @version 0.2
 */
public abstract class API {
	protected int NUM_OF_PARMS = 0;

	protected String libraryName;

	protected BPState curState = null;
	protected Stack stack = null;
	protected Memory memory = null;
	protected Register register = null;

	protected List<Long> params = null;

	public void run(AbsoluteAddress address, BPState curState, Instruction inst) throws APIException {
		this.curState = curState;
		this.initAttributes();
		this.execute();
	}

	public String getLibraryName() {
		return this.libraryName;
	}

	public String getAPIName() {
		return getClass().getSimpleName();
	}

	public String getFullName() {
		return String.format("%s@%s.DLL", this.getAPIName(), this.getLibraryName());
	}

	protected void initAttributes() throws APIException {
		Environment env = curState.getEnvironement();
		this.stack = env.getStack();
		this.memory = env.getMemory();
		this.register = env.getRegister();

		if (NUM_OF_PARMS > 0) {
			this.params = new ArrayList<Long>();
			
			System.out.print("Argument:");
			
			for (int i = 0; i < NUM_OF_PARMS; i++) {
				Value value = stack.pop();

				if (value instanceof LongValue) {
					this.params.add(((LongValue) value).getValue());
				} else {
					throw new APIException(this);
				}
				
				System.out.print(String.format("\tx%d: %s\t", i + 1, value.toString()));
			}
			System.out.println();
		}
	}

	public abstract void execute();
}
