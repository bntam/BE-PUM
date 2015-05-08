package v2.org.analysis.system;

import org.jakstab.asm.AbsoluteAddress;
import java.util.ArrayList;
import java.util.List;

public class LibraryHandle {
	private List<Library> libraryName;

	public Library getLibrary(String libraryName2) {
		// TODO Auto-generated method stub
		for (Library l : libraryName)
			if (l.getLibraryName().equals(libraryName2))
				return l;
		Library t = new Library(libraryName2.toLowerCase());
		libraryName.add(t);

		return t;
	}

	public LibraryHandle() {
		libraryName = new ArrayList<Library>();

		libraryName.add(new Library("mapistub.dll"));
		libraryName.add(new Library("msvcrt.dll"));
		//libraryName.add(new Library("user32.dll"));
		//libraryName.add(new Library("kernel32.dll"));
	}

	public Library getLibrary(long libraryHandle) {
		// TODO Auto-generated method stub
		for (Library l : libraryName)
			if (l.getBaseAddress() == libraryHandle)
				return l;

		return null;
	}

	public String getAPIName(long value) {
		// TODO Auto-generated method stub
		for (Library l : libraryName) {
			String t = l.getAPIName(value);
			if (t != "")
				return t + "@" + l.getLibraryName();
		}

		return "";
	}

	public boolean isInside(AbsoluteAddress addr) {
		//Hai: Special Case when this address is API Address
		//Change later
		for (Library l : libraryName)
			if (!l.getLibraryName().equals("kernel32.dll") && !l.getLibraryName().equals("user32.dll")
				&& l.getExportTable().containsKey(addr))
				return true;

		return false;
	}
}
