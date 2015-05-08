/**
 * Project: BE_PUM
 * Package name: v2.org.analysis.apihandle.be_pum.winapi
 * File name: APIClass.java
 * Created date: Jan 30, 2015
 */
package v2.org.analysis.apihandle.winapi;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;
import v2.org.analysis.path.BPState;

/**
 * 
 * The stub abstract class is used for describe some primary attributes &
 * methods
 * 
 * @author Yen Nguyen
 * @version 0.1
 */
public abstract class API {
	protected String libraryName;

	public abstract boolean execute(AbsoluteAddress address, String funcName,
			BPState curState, Instruction inst);

	public String getLibraryName() {
		return this.libraryName;
	}
	
	public String getAPIName() {
		return getClass().getSimpleName();
	}
}
