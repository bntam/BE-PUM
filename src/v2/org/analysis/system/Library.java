package v2.org.analysis.system;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Library {
	private String libraryName;
	private int baseAddress;

	public Map<String, Long> getExportTable() {
		return exportTable;
	}

	private Map<String, Long> exportTable;

	/**
	 * @return the libraryName
	 */
	public String getLibraryName() {
		return libraryName;
	}

	/**
	 * @param libraryName
	 *            the libraryName to set
	 */
	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}

	/**
	 * @return the baseAddress
	 */
	public int getBaseAddress() {
		return baseAddress;
	}

	/**
	 * @param baseAddress
	 *            the baseAddress to set
	 */
	public void setBaseAddress(int baseAddress) {
		this.baseAddress = baseAddress;
	}

	public Library(String name) {
		libraryName = name;
		baseAddress = (int) (Math.random() * Math.pow(10, 6));
		exportTable = new HashMap<String, Long>();
		initializeExportTable();
	}

	private void initializeExportTable() {
		// TODO Auto-generated method stub
		if (this.libraryName.equals("mapistub.dll")) {
			exportTable.put("fixmapi", (long) (baseAddress + Math.random() * Math.pow(10, 2)));
		}
	}

	public void insertAPI(String api, long address) {
		exportTable.put(api, address);
	}

	public long getAPIAddr(String api) {
		for (Entry<String, Long> entry : exportTable.entrySet()) {
			if (entry.getKey().equals(api.toLowerCase()))
				return entry.getValue();
		}
		long ret = (long) (getBaseAddress() + Math.random() * getBaseAddress());
		exportTable.put(api, ret);

		return ret;
	}

	public String getAPIName(long addr) {
		for (Entry<String, Long> entry : exportTable.entrySet()) {
			if (entry.getValue() == addr)
				return entry.getKey();
		}

		return "";
	}
}
