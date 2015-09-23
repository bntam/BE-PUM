/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.kernel32.functions
 * File name: GetAtomName.java
 * Created date: Sep 23, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;

/**
 * Retrieves a copy of the character string associated with the specified local
 * atom.
 * 
 * @param nAtom
 *            The local atom that identifies the character string to be
 *            retrieved.
 * 
 * @param lpBuffer
 *            The character string.
 * 
 * @param nSize
 *            The size, in characters, of the buffer.
 * 
 * @return If the function succeeds, the return value is the length of the
 *         string copied to the buffer, in characters, not including the
 *         terminating null character. If the function fails, the return value
 *         is zero. To get extended error information, call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class GetAtomName extends Kernel32API {

	public GetAtomName() {
		super();
		NUM_OF_PARMS = 3;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		
		
	}

}
