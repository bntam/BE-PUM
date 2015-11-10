/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.msvcrt.functions
 * File name: fclose.java
 * Created date: Nov 11, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.msvcrt.functions;

import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTAPI;
import v2.org.analysis.apihandle.winapi.msvcrt.MSVCRTDLL;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;

/**
 * Close file
 * 
 * Closes the file associated with the stream and disassociates it.
 * 
 * All internal buffers associated with the stream are disassociated from it and
 * flushed: the content of any unwritten output buffer is written and the
 * content of any unread input buffer is discarded.
 * 
 * Even if the call fails, the stream passed as parameter will no longer be
 * associated with the file nor its buffers.
 * 
 * @param stream
 *            Pointer to a FILE object that specifies the stream to be closed.
 * 
 * @return If the stream is successfully closed, a zero value is returned. On
 *         failure, EOF is returned.
 * 
 * @author Yen Nguyen
 *
 */
public class fclose extends MSVCRTAPI {

	public fclose() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t1 = this.params.get(0);

		Pointer stream = new Pointer(t1);
		int ret = MSVCRTDLL.INSTANCE.fclose(stream);

		register.mov("eax", new LongValue(ret));
	}

}
