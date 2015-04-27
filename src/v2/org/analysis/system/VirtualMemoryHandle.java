/**
 * 
 */
package v2.org.analysis.system;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MinhHai
 * 
 */
public class VirtualMemoryHandle {
	List<VirtualMemory> vList;
	VirtualMemory currentVirtualMemory;
	
	public VirtualMemoryHandle() {
		vList = new ArrayList<VirtualMemory>();
	}

	public long virtualAllocate(long address, long size, long type,
			long accessProtection) {
		VirtualMemory v = new VirtualMemory(address, size, type,
				accessProtection);
		long base = Math.round(Math.random() * Math.pow(10, 7));
		v.setBaseAddress(base);
		vList.add(v);

		return base;
	}

	public long freeVirtual(long base, long size, long type) {
		for (VirtualMemory v : vList) {
			if (v.getBaseAddress() == base) {
				return v.free(size, type);
				// return 1;
			}
		}

		return 0;
	}
	
	// PHONG: change here
	public void setCurrentVirtualMemory(VirtualMemory curVM){
		this.currentVirtualMemory = curVM;
	}
	
	public VirtualMemory getCurrentVirtualMemory(){
		return this.currentVirtualMemory;
	}
}
