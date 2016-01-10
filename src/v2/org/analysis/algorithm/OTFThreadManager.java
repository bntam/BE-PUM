/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.algorithm
 * File name: OTFThreadManager.java
 * Created date: Dec 3, 2015
 * Description:
 */
package v2.org.analysis.algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import v2.org.analysis.algorithm.OTFModelGeneration.OTFThread;
import v2.org.analysis.log.BPLogger;
//import v2.org.analysis.algorithm.OTFModelGeneration.OTFThread;
import v2.org.analysis.path.BPPath;
import v2.org.analysis.path.BPState;

/**
 * @author Yen Nguyen
 *
 */
public class OTFThreadManager {

	private static final boolean IS_MULTI_THREAD = false;
	private static final boolean IS_CHECKSUM = false;
	private static final int NUMBER_OF_CORE = (IS_MULTI_THREAD) ? (Runtime.getRuntime().availableProcessors()) : 1;

	/**
	 * Singleton instance of {@link OTFThreadManager} class
	 */
	private static volatile OTFThreadManager mInstance = null;

	private Set<String> mProcessedStateSet = null;

	/**
	 * The constructor of {@link OTFThreadManager} class. This method just be
	 * called if only the caller is getIntance.
	 * 
	 * @throws Exception
	 *             will be thrown if the caller is not getInstance
	 * 
	 */
	public OTFThreadManager() throws Exception {
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

		boolean isRightAccess = false;
		if (stackTraceElements.length >= 3) {
			String className = OTFThreadManager.class.getName();
			if (stackTraceElements[1].getClassName().equals(className)
					&& stackTraceElements[1].getMethodName().equals("<init>")
					&& stackTraceElements[2].getClassName().equals(className)
					&& stackTraceElements[2].getMethodName().equals("getInstance")) {
				isRightAccess = true;
			}
		}

		/**
		 * Check if user violates singleton design pattern
		 */
		if (!isRightAccess) {
			throw new Exception(
					"Violate Singleton Design Pattern! Please call getInstance static method of this class to get the instance!");
		}

		if (IS_CHECKSUM) {
			mProcessedStateSet = new HashSet<String>();
		}
	}

	/**
	 * Get singleton instance of this class
	 * 
	 * @return {@link OTFThreadManager} instance
	 */
	public static synchronized OTFThreadManager getInstance() {
		if (mInstance == null) {
			try {
				mInstance = new OTFThreadManager();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mInstance;
	}

	private int mNumberOfCurrentThreads = 0;

	/**
	 * Inform the manager that you want to start a new OTFThread. This method
	 * will return a number of current threads (after having started) if this
	 * number is smaller than a number of cores of processor; otherwise it will
	 * throw an exception.
	 * 
	 * @return Number of current threads after having started
	 * 
	 * @throws Exception
	 *             If number of current thread is larger than number of cores.
	 */
	private synchronized int startNewThread() throws Exception {
		synchronized (this) {
			if (this.isCanStart()) {
				this.mNumberOfCurrentThreads++;
			} else {
				throw new Exception("Reached to a maximum of cores!");
			}
		}
		return this.mNumberOfCurrentThreads;
	}

	/**
	 * Inform the manager that you want to finish an OTFThread. This method will
	 * return a number of current threads (after having finished). If this
	 * number is equal to zero, it will let the main thread (running algorithm)
	 * resume.
	 * 
	 * @return Number of current threads after having finished
	 * 
	 * @throws Exception
	 *             If number of current thread is smaller than zero.
	 */
	private synchronized int finishThread() throws Exception {
		synchronized (this) {
			if (this.mNumberOfCurrentThreads > 0) {
				this.mNumberOfCurrentThreads--;

				// Notify waiting main thread to resume
				if (this.mNumberOfCurrentThreads == 0) {
					synchronized (this) {
						this.notifyAll();
						BPLogger.debugLogger.info("************************ END ***************************");
					}
				}
			} else {
				throw new Exception("There is NO thread to finish!");
			}
		}
		return this.mNumberOfCurrentThreads;
	}

	public synchronized boolean isCanStart() {
		// System.out.println(String.format("%d-%d",
		// this.mNumberOfCurrentThreads, this.mNumberOfCore));
		if (this.mNumberOfCurrentThreads < OTFThreadManager.NUMBER_OF_CORE) {
			return true;
		} else {
			return false;
		}
	}

	public synchronized void check(OTFModelGeneration otfModelGeneration, List<BPPath> pathList) throws Exception {
		if (otfModelGeneration == null || pathList == null) {
			BPLogger.debugLogger.error(String.format("[OTFThreadManager] NULL INPUT - otf:%s,pathList:%s",
					otfModelGeneration, pathList));
		}
		if (pathList != null && pathList.size() > 0) {
			BPLogger.debugLogger.info(String.format("[OTFThreadManager] check pathListSize:%d", pathList.size()));

			if (this.isCanStart()) {
				BPPath path = pathList.remove(pathList.size() - 1);

				OTFThread thread = otfModelGeneration.new OTFThread(path);
				thread.start();
				BPLogger.debugLogger.info(String
						.format("[OTFThreadManager] START location:%s,numOfCurrentThreads:%d/%d", path
								.getCurrentState().getLocation().toString(), this.mNumberOfCurrentThreads,
								OTFThreadManager.NUMBER_OF_CORE));
			}
		}
	}

	/**
	 * Add all checksum string elements in processed state buffer set of child
	 * thread to this manager.
	 * 
	 * @param pProcessedSet
	 *            The temporary set holding a part processed states of child
	 *            thread.
	 */
	protected synchronized void addProcessedStates(Collection<? extends String> pProcessedSet) {
		mProcessedStateSet.addAll(pProcessedSet);
		pProcessedSet.clear();
	}

	protected synchronized boolean isProcessed(String pChecksum) {
		if (mProcessedStateSet != null) {
			if (mProcessedStateSet.contains(pChecksum)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @author Yen Nguyen
	 *
	 */
	static public abstract class OTFThreadBase extends Thread {
		private final static int MAX_SIZE_OF_BUFER = 4;

		protected String mMessage = null;
		private OTFThreadManager mOtfThreadManager = OTFThreadManager.getInstance();
		private List<String> mProcessedStateBuffer = null;

		/**
		 * Check if the state input was processed by another thread.
		 * 
		 * @param pBPState
		 *            Processed stated of current thread
		 * 
		 * @return {@code TRUE} if processed, {@code FALSE} otherwise.
		 */
		public boolean isStopCurrentPath(BPState pBPState) {
			if (IS_CHECKSUM && pBPState != null && pBPState.getLocation() != null) {
				if (mProcessedStateBuffer == null) {
					mProcessedStateBuffer = new ArrayList<String>(MAX_SIZE_OF_BUFER);
				}

				String location = pBPState.getLocation().toString();
				String checksum = pBPState.getEnvironement().hash();
				String combinedChecksum = String.format("%s%s", location, checksum);
				mProcessedStateBuffer.add(combinedChecksum);

				boolean isProcessed = mOtfThreadManager.isProcessed(combinedChecksum);

				if (isProcessed || mProcessedStateBuffer.size() == MAX_SIZE_OF_BUFER) {
					mOtfThreadManager.addProcessedStates(mProcessedStateBuffer);
				}
				
				if (isProcessed) {
					System.out.println("_____________________________________________________________________________");
					System.out.println(String.format("_____________________ INTERRUPT OTF THREAD %s _______________________", location));
					System.out.println("_____________________________________________________________________________");
				}

				return isProcessed;
			}
			return false;
		}

		public abstract void execute();

		private void beforeExecute() {
			try {
				mOtfThreadManager.startNewThread();
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("_____________________________________________________________________________");
			System.out.println(String.format("_____________________ START NEW OTF THREAD %s _______________________", mMessage));
			System.out.println("_____________________________________________________________________________");
		}

		private void afterExecute() {
			try {
				mOtfThreadManager.finishThread();
				
				if (mProcessedStateBuffer != null && mProcessedStateBuffer.size() > 0) {
					mOtfThreadManager.addProcessedStates(mProcessedStateBuffer);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void afterLoop(OTFModelGeneration otfModelGeneration, List<BPPath> pathList) {
			try {
				mOtfThreadManager.check(otfModelGeneration, pathList);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			this.beforeExecute();
			this.execute();
			this.afterExecute();
		}
	}

}