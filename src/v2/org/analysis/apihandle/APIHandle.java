package v2.org.analysis.apihandle;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;
import v2.org.analysis.path.BPPath;

import java.util.ArrayList;
import java.util.List;

public class APIHandle {
	private List<APIStub> apis;
	private List<String> processedAPI;
	private List<String> unprocessedAPI;

	public APIHandle() {
		apis = new ArrayList<APIStub>();
		processedAPI = new ArrayList<String>();
		unprocessedAPI = new ArrayList<String>();
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		apis.add(new Kernel32Stub());
		apis.add(new User32Stub());
		apis.add(new MAPIStub());
		apis.add(new DefaultStub());

		unprocessedAPI.add("ExitProcess@kernel32.dll");
		//unprocessedAPI.add("GetProcAddress@kernel32.dll");
		//unprocessedAPI.add("LoadLibraryA@kernel32.dll");
		unprocessedAPI.add("GetWindowsDirectoryA@kernel32.dll");
		unprocessedAPI.add("GetSystemDirectoryA@kernel32.dll");
		unprocessedAPI.add("GetCurrentDirectoryA@kernel32.dll");
		unprocessedAPI.add("SetCurrentDirectoryA@kernel32.dll");
		unprocessedAPI.add("SetFileAttributesA@kernel32.dll");
		unprocessedAPI.add("MessageBoxA@user32.dll");
		unprocessedAPI.add("wsprintfA@user32.dll");
		unprocessedAPI.add("fixmapi@mapistub.dll");
		unprocessedAPI.add("exit@msvcrt.dll");
		unprocessedAPI.add("__set_app_type@msvcrt.dll");
		unprocessedAPI.add("__getmainargs@msvcrt.dll");
		unprocessedAPI.add("_controlfp@msvcrt.dll");
		unprocessedAPI.add("__p__fmode@msvcrt.dll");
		unprocessedAPI.add("__p__commode@msvcrt.dll");		
	}

	public void executeAPI(AbsoluteAddress addr, String api, Instruction inst,
			BPPath path, List<BPPath> pathList) {
		// TODO Auto-generated method stub
		String t[] = api.split("@");

		// if (t[1] != null && t[1].toLowerCase().equals("kernel32.dll"))
		for (APIStub s : apis) {
			if (s.equalsLibraryName(t[1])) {
				// Handling API stub, we seperate two path
				// First path is normal case processed by API stub
				// Second path is special case when return value is 0
				
				//if (t[0].equals("LocalReAlloc") || t[0].equals("LoadStringW"))
				//	System.out.println("Debug " + t[0]);	

				/*if (specialCase(api)) {
					BPPath newP = path.clone();
					s.executeAPI(addr, t[0], newP.getCurrentState(), inst,
							2);
					pathList.add(newP);
					processedAPI.add(api);
				}*/

				s.executeAPI(addr, t[0], path.getCurrentState(), inst, 1);

				return;
			}
		}
	}

	private boolean specialCase(String api) {
		// TODO Auto-generated method stub
		if (!api.contains("kernel32.dll"))
			return false;
		
		if (unprocessedAPI.contains(api))
			return false;

		return !processedAPI.contains(api);
	}

}
