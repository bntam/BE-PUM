/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: _initterm.java
 * Created date: Aug 22, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.value.SymbolValue;

/**
 * @author Yen Nguyen
 *
 */
public class _initterm extends MSVCRTAPI {
	
	public _initterm() {
		super();
		NUM_OF_PARMS = 2;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

		// VOID Func
		//register.setRegisterValue("eax", new SymbolValue("api_eax_" + getFullName()));
	}

}
