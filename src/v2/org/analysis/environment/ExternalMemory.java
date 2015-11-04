package v2.org.analysis.environment;

import org.jakstab.Program;

import v2.org.analysis.complement.BitVector;
import v2.org.analysis.environment.ExternalMemory.ExternalMemoryReturnData;
import v2.org.analysis.log.BPLogger;
import v2.org.analysis.transition_rule.X86TransitionRule;
import v2.org.analysis.value.LongValue;

import com.sun.jna.Pointer;

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

		@Override
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
			BPLogger.reportLogger.warn(String.format("INVALID - Inst:%s - Address:%d", X86TransitionRule.currentState
					.getLocation().toString(), ret.address), new Exception());
		} else {
			BPLogger.reportLogger.info(String.format("VALID - Inst:%s - Address:%d, VALUE:%d",
					X86TransitionRule.currentState.getLocation().toString(), ret.address, ret.value.getValue()));
		}

		return ret;

		// return null;
	}

	private static long calculateDoubleWordValue(long r1, long r2, long r3, long r4) {
		/*
		 * int ret = 0; ret = (int) r1; ret |= r2 << 8; ret |= r3 << 16; ret |=
		 * r4 << 24; return ret;
		 */
		return BitVector.bytesToLong((int) r1, (int) r2, (int) r3, (int) r4);
	}

	private static long calculateWordValue(long r1, long r2) {
		/*
		 * int ret = 0; ret = (int) r1; ret |= r2 << 8; return ret;
		 */
		return BitVector.bytesToLong((int) r1, (int) r2);
	}

	public synchronized static ExternalMemoryReturnData getDoubleWord(long address) {
		// TODO Auto-generated method stub
		ExternalMemoryReturnData ret = getByte(address);
		ExternalMemoryReturnData ret1 = getByte(address + 1);
		ExternalMemoryReturnData ret2 = getByte(address + 2);
		ExternalMemoryReturnData ret3 = getByte(address + 3);

		if (ret != null && ret.isValidAddress && ret1 != null && ret1.isValidAddress && ret2 != null
				&& ret2.isValidAddress && ret3 != null && ret3.isValidAddress) {
			ret.value = new LongValue(calculateDoubleWordValue(ret.value.getValue(), ret1.value.getValue(),
					ret2.value.getValue(), ret3.value.getValue()));
		}
		return ret;
		// return null;
	}

	public synchronized static ExternalMemoryReturnData getWord(long address) {
		// TODO Auto-generated method stub
		ExternalMemoryReturnData ret = getByte(address);
		ExternalMemoryReturnData ret1 = getByte(address + 1);

		if (ret != null && ret.isValidAddress && ret1 != null && ret1.isValidAddress) {
			ret.value = new LongValue(calculateWordValue(ret.value.getValue(), ret1.value.getValue()));
		}

		// System.out.println(ret);
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
			this.data.value = new LongValue(ret);
			this.data.isValidAddress = true;
		} catch (Exception e) {
			BPLogger.reportLogger.error(e.getMessage(), e);
		}
	}
}