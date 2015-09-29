package v2.org.analysis.environment;

import org.jakstab.Program;

import com.sun.jna.Pointer;

import v2.org.analysis.environment.ExternalMemory.ExternalMemoryReturnData;
import v2.org.analysis.log.BPLogger;
import v2.org.analysis.value.LongValue;

/**
 * This class is used to get the memory value that is not handled by Memory
 * class. This memory value maybe created by JNA's call or available in
 * dynamic-link library.
 * 
 * @author Yen Nguyen
 *
 */
public class ExternalMemory {
	private static boolean isFirst = true;

	public static class ExternalMemoryReturnData {

		public long address;
		public boolean isValidAddress = false;
		public LongValue value;

		public String toString() {
			return "JNA's Address: " + this.address + " is "
					+ ((this.isValidAddress) ? ("valid, value = " + this.value) : "invalid ");
		}
	}

	public ExternalMemory() {
	}

	public synchronized static ExternalMemoryReturnData getByte(long address) {
		if (isFirst) {
			isFirst = false;
			BPLogger.reportLogger.info(Program.getProgram().getAbsolutePathFile());
		}

		ExternalMemoryReturnData ret = new ExternalMemoryReturnData();
		ret.address = address;

		if (address == 0) {
			ret.isValidAddress = false;
			ret.value = new LongValue(0);
			return ret;
		}

		// logger.info("ACCESS - Address:" + address, new Exception("Trace"));
		Thread pointer = new Thread(new AccessPointerThread(ret));
		pointer.start();
		try {
			pointer.join();
		} catch (InterruptedException e) {
			BPLogger.reportLogger.error(String.format("JNA Pointer InterruptedException:%d", address), e);
		}

		if (!ret.isValidAddress) {
			BPLogger.reportLogger.warn(String.format("INVALID - Address:%d", ret.address), new Exception());
		} else {
			BPLogger.reportLogger.info(String.format("VALID - Address:%d, VALUE:%d", ret.address, ret.value));
		}

		return ret;

		// return null;
	}
}

class AccessPointerThread implements Runnable {

	// private static boolean isFirstTime = true;
	private ExternalMemoryReturnData data;

	public AccessPointerThread(ExternalMemoryReturnData buffer) {
		this.data = buffer;
	}

	@Override
	public void run() {
		Pointer ptr = new Pointer(this.data.address);
		try {
			// if (isFirstTime) {
			// Thread.sleep(1000);
			// }
			byte ret = ptr.getByte(0);
			this.data.value = new LongValue((long) ret);
			this.data.isValidAddress = true;
		} catch (Exception e) {
			BPLogger.reportLogger.error(e.getMessage(), e);
		}
	}
}