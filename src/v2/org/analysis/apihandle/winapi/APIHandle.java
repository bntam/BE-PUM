package v2.org.analysis.apihandle.winapi;

import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.jna.platform.win32.Kernel32;

import v2.org.analysis.cfg.BPCFG;
import v2.org.analysis.cfg.BPEdge;
import v2.org.analysis.cfg.BPVertex;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPPath;
import v2.org.analysis.path.BPState;
import v2.org.analysis.system.VirtualMemory;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.SymbolValue;
import v2.org.analysis.value.Value;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import v2.org.analysis.apihandle.be_pum.winapi.kernel32.Kernel32Stub;

public class APIHandle {
	private static HashMap<String, String> apiMapping = new HashMap<String, String>();
	private static boolean init = false;
	public static Map<Long, String> libraryHandle = new HashMap<Long, String>();
	public static Map<Long, String> processAddressHandle = new HashMap<Long, String>();
	public static boolean isDebug = true;

	private static void init() {
		if (init)
			return;
		
		String directory = APIHandle.class.getPackage().getName().replace(".", "/");
		InputStream fXmlFile = null;
		try {
//			String dir = "src\\" + Test.class.getPackage().getName().replace(".", "\\");
//			File fXmlFile = new File(dir + "\\" + "APIMap.xml");
			
			fXmlFile = APIHandle.class.getResourceAsStream("/" + directory + "/APIMap.xml");
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			if (doc.hasChildNodes()) {
				NodeList DLLList = doc.getElementsByTagName("DLL");

				for (int count = 0; count < DLLList.getLength(); count++) {
					Node DLLNode = DLLList.item(count);

					// make sure it's element node.
					if (DLLNode.getNodeType() == Node.ELEMENT_NODE) {
//						HashMap<String, String> apiHashMap = new HashMap<String, String>();
//						apiMapping.put(DLLNode.getAttributes().item(0).getNodeValue(), apiHashMap);
						// get attributes names and values
						NodeList APIList = DLLNode.getChildNodes();

						for (int i = 0; i < APIList.getLength(); i++) {
							Node apiNode = APIList.item(i);
							// make sure it's element node.
							if (apiNode.getNodeType() == Node.ELEMENT_NODE && apiNode.hasAttributes()) {
								// get attributes names and values
								NamedNodeMap apiMap = apiNode.getAttributes();
								apiMapping.put(apiMap.getNamedItem("funcName").getNodeValue(),
										apiMap.getNamedItem("className").getNodeValue());
								// if
								// (apiMap.getNamedItem("funcName").getNodeValue().equals(funcName))
								// {
								// fullClassName =
								// apiMap.getNamedItem("className").getNodeValue();
								// }
							}

						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fXmlFile != null)
					fXmlFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		init = true;
	}

	private static String findClassName(String funcName, String libName) {
		init();
		String fullClassName = null;
//		String apiMap = apiMapping.get(libName);
//		if (apiMap == null)
//			return null;
		fullClassName = apiMapping.get(funcName);
		return fullClassName;
	}

	public static void executeAPI(AbsoluteAddress address, String api, Instruction inst, BPPath path,
			List<BPPath> pathList) {
		System.out.println("\n\tCall api: " + api);
		String t[] = api.split("@");

		//if (api.contains("HeapAlloc"))
		//	System.out.println("Debug");

		// long returnValue = 0;
		String funcName = t[0];
		BPState curState = path.getCurrentState();
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		// Memory memory = env.getMemory();
		// Register register = env.getRegister();
		Program program = Program.getProgram();
		// SystemHandle system = env.getSystem();
		BPCFG cfg = program.getBPCFG();

		System.out.println("\tIns address of api: " + curState.getLocation());

		if (inst.getName().toString().equals("jmp")) {
			System.out.println("JMP API:" + funcName);

			BPVertex v1 = cfg.getVertex(curState.getLocation(), curState.getInstruction());
			v1.setProperty(api);
			BPVertex v2 = new BPVertex();
			// v2.setAddress(address);
			v2.setProperty(api);

			if (funcName.equals("ExitProcess") || funcName.equals("exit")) {
				v2.setType(BPVertex.ExitNode);
				v2 = cfg.insertVertex(v2);
				// v2.setProperty(api);
				cfg.insertEdge(new BPEdge(v1, v2));
				curState.setLocation(null);
				curState.setInstruction(null);
				return;
			}

			v2.setType(BPVertex.APINode);
			v2 = cfg.insertVertex(v2);
			cfg.insertEdge(new BPEdge(v1, v2));
			Value returnAddr = stack.pop();
			long r = 0;
			if (//	path.getPreviousInst() instanceof X86CallInstruction && 
					returnAddr != null && returnAddr instanceof LongValue) {
				r = ((LongValue) returnAddr).getValue();
			} else {
				r = curState.getLocation().getValue() + curState.getInstruction().getSize();
				stack.push(returnAddr);
			}
			AbsoluteAddress addr = new AbsoluteAddress(r);
			Instruction newInst = program.getInstruction(addr, env);
			v1 = cfg.insertVertex(new BPVertex(addr, newInst));
			cfg.insertEdge(new BPEdge(v2, v1));

			curState.setLocation(addr);
			curState.setInstruction(newInst);
		} else if (inst.getName().toString().equals("call")) {
			System.out.println("Call API:" + funcName);

			BPVertex v1 = cfg.getVertex(curState.getLocation(), curState.getInstruction());
			v1.setProperty(api);
			BPVertex v2 = new BPVertex();
			// v2.setAddress(address);
			v2.setProperty(api);

			if (funcName.equals("ExitProcess") || funcName.equals("exit")) {
				v2.setType(BPVertex.ExitNode);
				v2 = cfg.insertVertex(v2);
				// v2.setProperty(api);
				cfg.insertEdge(new BPEdge(v1, v2));
				curState.setLocation(null);
				curState.setInstruction(null);
				return;
			}

			v2.setType(BPVertex.APINode);
			v2 = cfg.insertVertex(v2);
			cfg.insertEdge(new BPEdge(v1, v2));

			long r = curState.getLocation().getValue() + curState.getInstruction().getSize();
			AbsoluteAddress addr = new AbsoluteAddress(r);
			// PHONG: change here for virtual memory
			Instruction newInst;
			if (curState.getEnvironement().getSystem().isInVirtualMemory() == true) {
				byte[] opcodes = getOpcodesArray(curState, addr.getValue());
				// NEXT INSTRUCTION FOR CALL
				newInst = Program.getProgram().getInstruction(opcodes, env);
			} else
				newInst = program.getInstruction(addr, env);

			v1 = cfg.insertVertex(new BPVertex(addr, newInst));
			cfg.insertEdge(new BPEdge(v2, v1));

			curState.setLocation(addr);
			curState.setInstruction(newInst);
		} else if (inst.getName().toString().contains("ret")) {
			System.out.println("RET API:" + funcName);

			BPVertex v1 = cfg.getVertex(curState.getLocation(), curState.getInstruction());
			v1.setProperty(api);
			BPVertex v2 = new BPVertex();
			// v2.setAddress(address);
			v2.setProperty(api);

			if (funcName.equals("ExitProcess") || funcName.equals("exit")) {
				v2.setType(BPVertex.ExitNode);
				v2 = cfg.insertVertex(v2);
				// v2.setProperty(api);
				cfg.insertEdge(new BPEdge(v1, v2));
				curState.setLocation(null);
				curState.setInstruction(null);
				return;
			}

			v2.setType(BPVertex.APINode);
			v2 = cfg.insertVertex(v2);
			cfg.insertEdge(new BPEdge(v1, v2));

			Value ret = env.getStack().pop();
			long r = 0;
			if (ret != null && ret instanceof LongValue)
				r = ((LongValue) ret).getValue();
			AbsoluteAddress addr = new AbsoluteAddress(r);
			// PHONG: change here for virtual memory
			Instruction newInst;
			if (curState.getEnvironement().getSystem().isInVirtualMemory() == true) {
				byte[] opcodes = getOpcodesArray(curState, addr.getValue());
				// NEXT INSTRUCTION FOR CALL
				newInst = Program.getProgram().getInstruction(opcodes, env);
			} else
				newInst = program.getInstruction(addr, env);

			v1 = cfg.insertVertex(new BPVertex(addr, newInst));
			cfg.insertEdge(new BPEdge(v2, v1));

			curState.setLocation(addr);
			curState.setInstruction(newInst);
		}

		//Memory memory = env.getMemory();
		//Register register = env.getRegister();
		//SystemHandle system = env.getSystem();

		String className = findClassName(t[0].toLowerCase(), t[1].toLowerCase().replace(".dll", ""));
		
		// PHONG - 20150728 ///////////////////////////////////////
		if (t[0].contains("LoadLibraryA"))
			Program.getProgram().setTechnique("UseAPI: LoadLibrary");
		if (t[0].contains("GetProcAddress"))
			Program.getProgram().setTechnique("UseAPI: GetProcAddress");
		if (t[0].contains("VirtualAlloc"))
			Program.getProgram().setTechnique("UseAPI: VirtualAlloc");
		if (t[0].contains("IsDebuggerPresent"))
			Program.getProgram().setTechnique("UseAPI: IsDebuggerPresent");
		//////////////////////////////////////////////////////////
		
		if (className != null) {
			try {
				Class<?> clazz = Class.forName(className);
				Constructor<?> ctor = clazz.getConstructor();
				API apiObject = (API) ctor.newInstance();

				apiObject.run(address, path.getCurrentState(), inst);

				System.out.println("\tLast Error: " + Kernel32.INSTANCE.GetLastError());
			} catch (APIException ex) {
				ex.writeLog();
				ex.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Unsupported API: " + api);
			if (funcName.startsWith("InternetGetConnectedState")) {
				Value x1 = stack.pop();
				Value x2 = stack.pop();
				System.out.println("Argument: " + x1 + ", " + x2);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName));
			} else if (funcName.startsWith("s_perror")) { // Can not find
															// definition
				Value x1 = stack.pop();
				Value x2 = stack.pop();
				System.out.println("Argument: " + x1 + ", " + x2);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName));
			} else if (funcName.startsWith("setusermatherr")) { // Can not find
																// definition
				Value x1 = stack.pop();
				System.out.println("Argument: " + x1);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName));
			} else if (funcName.startsWith("puts")) { // Just for print in
														// console, what DLL
														// file is this API
														// belong to?
				Value x1 = stack.pop();
				System.out.println("Argument: " + x1);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName));
			} else if (funcName.startsWith("_write")) { // DLL???
				Value x1 = stack.pop();
				Value x2 = stack.pop();
				Value x3 = stack.pop();
				System.out.println("Argument= " + x1 + ", " + x2 + ", " + x3);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName));
			} else if (funcName.startsWith("ord(115)")) { // ?????????
				Value x1 = stack.pop();
				Value x2 = stack.pop();
				System.out.println("WSAStarup API: Argument= " + x1 + ", " + x2);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName));
			} else if (funcName.startsWith("_initterm")) {
				Value x1 = stack.pop();
				Value x2 = stack.pop();
				System.out.println("Argument: " + x1 + ", " + x2);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName));
			} else if (funcName.startsWith("__getmainargs")) {
				Value x1 = stack.pop();
				Value x2 = stack.pop();
				Value x3 = stack.pop();
				Value x4 = stack.pop();
				Value x5 = stack.pop();
				System.out.println("Argument: " + x1 + ", " + x2 + ", " + x3 + ", " + x4 + ", " + x5);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName));
			} else if (funcName.startsWith("_controlfp")) {
				Value x1 = stack.pop();
				Value x2 = stack.pop();
				stack.push(x2);
				stack.push(x1);
				System.out.println("Argument: " + x1 + ", " + x2);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName));
			} else if (funcName.startsWith("__set_app_type")) {
				Value x1 = stack.pop();
				System.out.println("Argument: " + x1);
				env.getRegister().setRegisterValue("eax", new LongValue(1));
			} else if (funcName.startsWith("__p__fmode")) {
				System.out.println("Argument: None");
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName));
			} else if (funcName.startsWith("__p__commode")) {
				System.out.println("Argument: None");
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName));
			} else if (funcName.startsWith("__p___initenv")) {
				System.out.println("Argument: No");
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName));
			} else if (funcName.startsWith("_chkesp")) {
				System.out.println("Argument: No");
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName));
			} else if (funcName.startsWith("strncmp")) {
				Value x1 = stack.pop();
				Value x2 = stack.pop();
				Value x3 = stack.pop();

				System.out.println("Argument: " + x1 + ", " + x2 + ", " + x3);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName));
			} else if (funcName.startsWith("strncat")) {
				Value x1 = stack.pop();
				Value x2 = stack.pop();
				Value x3 = stack.pop();

				System.out.println("Argument: " + x1 + ", " + x2 + ", " + x3);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName));
			} else if (funcName.startsWith("fopen")) {
				Value x1 = stack.pop();
				Value x2 = stack.pop();
				Value x3 = stack.pop();

				System.out.println("Argument: " + x1 + ", " + x2 + ", " + x3);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName));
			} else if (funcName.startsWith("fprintf")) {
				Value x1 = stack.pop();
				Value x2 = stack.pop();
				Value x3 = stack.pop();
				Value x4 = stack.pop();
				System.out.println("Argument: " + x1 + ", " + x2 + ", " + x3 + ", " + x4);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName));
			} else if (funcName.startsWith("strncat")) {
				Value x1 = stack.pop();
				Value x2 = stack.pop();
				Value x3 = stack.pop();

				System.out.println("Argument: " + x1 + ", " + x2 + ", " + x3);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName));
			} else if (funcName.startsWith("printf")) {
				Value x1 = stack.pop();
				System.out.println("Argument: " + x1);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName));
			} else if (funcName.startsWith("fclose")) {
				Value x1 = stack.pop();
				System.out.println("Argument: " + x1);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName));
			} else if (funcName.startsWith("strlen")) {
				Value x1 = stack.pop();
				System.out.println("Argument: " + x1);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName));
			} else if (funcName.startsWith("strncat")) {
				Value x1 = stack.pop();
				Value x2 = stack.pop();
				Value x3 = stack.pop();

				System.out.println("Argument: " + x1 + ", " + x2 + ", " + x3);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName));
			} else if (funcName.startsWith("strncat")) {
				Value x1 = stack.pop();
				Value x2 = stack.pop();
				Value x3 = stack.pop();

				System.out.println("Argument: " + x1 + ", " + x2 + ", " + x3);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName));
			} else if (funcName.startsWith("_hread")) {
				Value x1 = stack.pop();
				Value x2 = stack.pop();
				Value x3 = stack.pop();

				System.out.println("Argument: " + x1 + ", " + x2 + ", " + x3);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName));
			} else if (funcName.startsWith("_hwrite")) {
				Value x1 = stack.pop();
				Value x2 = stack.pop();
				Value x3 = stack.pop();

				System.out.println("Argument: " + x1 + ", " + x2 + ", " + x3);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName));
			} else {
				System.out.println("No Handling of this API");
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax"));
			}
		}

		System.out.println("EAX=" + env.getRegister().getRegisterValue("eax"));
	}

	// PHONG: insert here
	// Need to improve here, process the opcodes[] for best performance
	public static byte[] getOpcodesArray(BPState curState, long address) {
		VirtualMemory vM = curState.getEnvironement().getSystem().getVirtualHandle().getCurrentVirtualMemory();
		vM.setAddress(address);
		// long offset = address - vM.getBaseAddress();
		byte[] opcodes = new byte[(int) vM.getSize()];
		// can modify here for best result, i < 10, because one asm statement
		// needs 10 bytes or less
		for (int i = 0; i < /* vM.getSize() - offset */10; i++) {
			long virtualAdrr = vM.getAddress() + (long) i;
			opcodes[i] = (byte) ((LongValue) curState.getEnvironement().getMemory().getByteMemoryValue(virtualAdrr))
					.getValue();
		}
		return opcodes;
	}

	public static String checkAPI(long address) {
		String api = APIHandle.processAddressHandle.get(address);
		return api;
	}
}
