package v2.org.analysis.system;

import v2.org.analysis.system.dll.Kernel32Image;
import v2.org.analysis.system.dll.User32Image;

public class SystemHandle {
	private Kernel32Image kernelHandle;
	private User32Image userHandle;
	private FileHandle fileHandle;
	private RegistryHandle registryHandle;
	private HeapHandle heapHandle;
	private SEHHandle seh;
	private VirtualMemoryHandle virtualHandle;
	private ProcessHandle processHandle;
	private WindowHandle windowHandle;
	private LibraryHandle libraryHandle;
	private String path = "C:/Windows";

	// PHONG: Check if it is being in process of Virtual Memory
	private boolean inVirtualMemory;

	public FileHandle getFileHandle() {
		return fileHandle;
	}

	public Kernel32Image getKernel() {
		return kernelHandle;
	}

	public void setKernel(Kernel32Image kernel) {
		this.kernelHandle = kernel;
	}

	public User32Image getUser32() {
		return userHandle;
	}

	public void setUser32(User32Image kernel) {
		userHandle = kernel;
	}

	public SEHHandle getSEHHandler() {
		return seh;
	}

	public void setSeh(SEHHandle seh) {
		this.seh = seh;
	}

	public SystemHandle() {
		kernelHandle = new Kernel32Image("data/kernel32.dll");
		userHandle = new User32Image("data/user32.dll");
		seh = new SEHHandle();
		fileHandle = new FileHandle();
		fileHandle.setPath(path);
		registryHandle = new RegistryHandle();
		heapHandle = new HeapHandle();
		virtualHandle = new VirtualMemoryHandle();
		processHandle = new ProcessHandle();
		windowHandle = new WindowHandle();
		libraryHandle = new LibraryHandle();
	}

	public boolean checkAddrInKernel32(long ad) {
		// TODO Auto-generated method stub
		return ad >= this.getKernel().getBaseAddress() && ad < this.getFileHandle().getBaseAddress();
	}

	public long getLibraryHandle(String libraryName) {
		// TODO Auto-generated method stub
		String libName = libraryName.toLowerCase();
		if (libName.equals("kernel32.dll") || libName.contains("kernel32"))
			return getKernel().getBaseAddress();

		if (libName.equals("user32.dll") || libName.contains("user32"))
			return getUser32().getBaseAddress();

		Library l = libraryHandle.getLibrary(libName);

		if (l != null)
			return l.getBaseAddress();
		else
			return 0;
	}

	public long getProcAddress(long libraryHandle, String functionName) {
		// TODO Auto-generated method stub
		Kernel32Image kernel = getKernel();
		if (kernel.getBaseAddress() == libraryHandle) {
			return kernel.getProcAddress(functionName);
		}

		User32Image user = getUser32();
		if (user.getBaseAddress() == libraryHandle) {
			return user.getProcAddress(functionName);
		}

		Library l = this.libraryHandle.getLibrary(libraryHandle);

		if (l != null)
			return l.getAPIAddr(functionName);

		return 0;
	}

	public long getProcAddress(long libraryHandle, long function) {
		// TODO Auto-generated method stub
		Library l = this.libraryHandle.getLibrary(libraryHandle);

		if (l.getLibraryName().equals("ws2_32.dll"))
			if (function == 57)
				return l.getAPIAddr("gethostname");
			else if (function == 115)
				return l.getAPIAddr("WSAStarup");

		return 0;
	}

	public long closeHandle(long x) {
		// TODO Auto-generated method stub
		if (fileHandle.containFile(x))
			return fileHandle.closeFile(x);

		if (fileHandle.containFileMappingHandle(x))
			return fileHandle.closeFileMappingHandle(x);

		return 0;
	}

	/**
	 * @return the registryHandle
	 */
	public RegistryHandle getRegistryHandle() {
		return registryHandle;
	}

	/**
	 * @param registryHandle
	 *            the registryHandle to set
	 */
	public void setRegistryHandle(RegistryHandle registryHandle) {
		this.registryHandle = registryHandle;
	}

	/**
	 * @return the heapHandle
	 */
	public HeapHandle getHeapHandle() {
		return heapHandle;
	}

	/**
	 * @param heapHandle
	 *            the heapHandle to set
	 */
	public void setHeapHandle(HeapHandle heapHandle) {
		this.heapHandle = heapHandle;
	}

	public long createFile(String fileName, long t2, long t3, long t4, long t5, long t6, long t7) {
		// TODO Auto-generated method stub
		return fileHandle.createFile(fileName, t2, t3, t4, t5, t6, t7);
	}

	public long getFileAttribute(String fName) {
		// TODO Auto-generated method stub
		return fileHandle.getFileAttribute(fName);
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 * @return
	 */
	public long setPath(String path) {
		this.path = path;
		fileHandle.setPath(path);

		return 1;
	}

	/**
	 * @return the virtualHandle
	 */
	public VirtualMemoryHandle getVirtualHandle() {
		return virtualHandle;
	}

	/**
	 * @param virtualHandle
	 *            the virtualHandle to set
	 */
	public void setVirtualHandle(VirtualMemoryHandle virtualHandle) {
		this.virtualHandle = virtualHandle;
	}

	public ProcessHandle getProcessHandle() {
		// TODO Auto-generated method stub
		return processHandle;
	}

	public void setProcessHandle(ProcessHandle h) {
		processHandle = h;
	}

	public WindowHandle getWindowHandle() {
		// TODO Auto-generated method stub
		return windowHandle;
	}

	public boolean checkAddrInFile(long ad) {
		// TODO Auto-generated method stub
		return ad >= this.getFileHandle().getBaseAddress();
	}

	/**
	 * @return the librarayHandle
	 */
	public LibraryHandle getLibraryHandle() {
		return libraryHandle;
	}

	/**
	 * @param librarayHandle
	 *            the librarayHandle to set
	 */
	public void setLibraryHandle(LibraryHandle librarayHandle) {
		this.libraryHandle = librarayHandle;
	}

	public long getCurrentProcess() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isInVirtualMemory() {
		return inVirtualMemory;
	}

	public void setInVirtualMemory(boolean inVirtualMemory) {
		this.inVirtualMemory = inVirtualMemory;
	}

	public long getSTDHandle(long value) {
		// TODO Auto-generated method stub
		if (value == -11)
			return 7;

		return 0;
	}

	public long getProcAddress(String libraryName, String functionName) {
		if (libraryName.toLowerCase().contains("kernel32.dll"))
			return getKernel().getProcAddress(functionName);

		if (libraryName.toLowerCase().contains("user32.dll"))
			return getUser32().getProcAddress(functionName);

		Library l = this.libraryHandle.getLibrary(libraryName);

		if (l != null)
			return l.getAPIAddr(functionName);

		return 0;
	}
}
