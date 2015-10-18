/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: GetFileType.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.kernel32.functions;

import java.util.ArrayList;
import java.util.List;

import v2.org.analysis.apihandle.winapi.kernel32.Kernel32API;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinNT.HANDLE;

/**
 * Retrieves the file type of the specified file.
 * 
 * @param hFile
 *            A handle to the file.
 * 
 * @return FILE_TYPE_UNKNOWN if the function fails, or if the type is unknown.
 *         You can distinguish between a "valid" return of FILE_TYPE_UNKNOWN and
 *         its return due to a calling error (for example, passing an invalid
 *         handle to GetFileType) by calling GetLastError. If the function
 *         worked properly and FILE_TYPE_UNKNOWN was returned, a call to
 *         GetLastError will return NO_ERROR.
 * 
 * @author Yen Nguyen
 *
 */
public class GetFileType extends Kernel32API {
	public static List<Integer> stdHandleList = new ArrayList<Integer>();
	
	public GetFileType() {
		super();
		NUM_OF_PARMS = 1;
	}

	@Override
	public void execute() {
		long t = this.params.get(0);
		
		// Std Input/Output handle will cause the return value is 3
		int ret = (stdHandleList.contains((int) t)) ? 3 : Kernel32.INSTANCE.GetFileType(new HANDLE(new Pointer(t)));
		
		System.out.println("Return value:" + ret);
		register.mov("eax", new LongValue(ret));
	}

}
