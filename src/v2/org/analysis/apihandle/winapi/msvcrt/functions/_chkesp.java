/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: _chkesp.java
 * Created date: Nov 10, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.value.LongValue;

/**
 * @author Yen Nguyen
 *
 */
public class _chkesp extends MSVCRTAPI {

	/**
	 * 
	 */
	public _chkesp() {
		super();
		NUM_OF_PARMS = 0;
	}

	@Override
	public void execute() {
		// Do nothing
		register.mov("eax", new LongValue(0));
	}

}
