/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.advapi32.functions
 * File name: CryptHashData.java
 * Created date: Apr 24, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.advapi32.functions;

import org.jakstab.asm.DataType;
import org.jakstab.asm.x86.X86MemoryOperand;

import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.ptr.ByteByReference;

import v2.org.analysis.apihandle.winapi.advapi32.Advapi32API;
import v2.org.analysis.apihandle.winapi.advapi32.Advapi32DLL;
import v2.org.analysis.value.LongValue;

/**
 * The CryptHashData function adds data to a specified hash object. This
 * function and CryptHashSessionKey can be called multiple times to compute the
 * hash of long or discontinuous data streams.
 * 
 * @param hHash
 *            Handle of the hash object.
 * 
 * @param pbData
 *            A pointer to a buffer that contains the data to be added to the
 *            hash object.
 * 
 * @param dwDataLen
 *            Number of bytes of data to be added. This must be zero if the
 *            CRYPT_USERDATA flag is set.
 * 
 * @param dwFlags
 *            The following flag values are defined.
 * 
 * @return If the function succeeds, the return value is TRUE. If the function
 *         fails, the return value is FALSE. For extended error information,
 *         call GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class CryptHashData extends Advapi32API {
	public CryptHashData() {
		super();
		NUM_OF_PARMS = 4;
	}


	@Override
	public void execute() {
		long t1 = this.params.get(0);
		long t2 = this.params.get(1);
		long t3 = this.params.get(2);
		long t4 = this.params.get(3);

		ULONG_PTR hHash = new ULONG_PTR(t1);
		ByteByReference pbData = new ByteByReference();
		DWORD dwDataLen = new DWORD(t3);
		DWORD dwFlags = new DWORD(t4);
		BOOL ret = Advapi32DLL.INSTANCE.CryptHashData(hHash, pbData, dwDataLen, dwFlags);

		long value = ret.longValue();
		register.mov("eax", new LongValue(value));
		System.out.println("Return Value: " + value);

		memory.setDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, t2),
				new LongValue((long) pbData.getValue()));
	}

}
