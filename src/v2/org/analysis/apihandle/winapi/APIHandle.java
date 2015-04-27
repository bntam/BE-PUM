package v2.org.analysis.apihandle.winapi;

import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import v2.org.analysis.cfg.BPCFG;
import v2.org.analysis.cfg.BPEdge;
import v2.org.analysis.cfg.BPVertex;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPPath;
import v2.org.analysis.path.BPState;
import v2.org.analysis.system.SystemHandle;
import v2.org.analysis.system.VirtualMemory;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.SymbolValue;
import v2.org.analysis.value.Value;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;

//import v2.org.analysis.apihandle.be_pum.winapi.kernel32.Kernel32Stub;

public class APIHandle {
	private static HashMap<String, HashMap<String, String>> libMapping = new HashMap<String, HashMap<String, String>>();
	private static boolean init = false;

	private static void init() {
		if (init)
			return;

		try {
			String dir = "src\\"
					+ Test.class.getPackage().getName().replace(".", "\\");
			File fXmlFile = new File(dir + "\\" + "APIMap.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			if (doc.hasChildNodes()) {
				NodeList DLLList = doc.getElementsByTagName("DLL");

				for (int count = 0; count < DLLList.getLength(); count++) {
					Node DLLNode = DLLList.item(count);

					// make sure it's element node.
					if (DLLNode.getNodeType() == Node.ELEMENT_NODE) {
						HashMap<String, String> apiHashMap = new HashMap<String, String>();
						libMapping.put(DLLNode.getAttributes().item(0)
								.getNodeValue(), apiHashMap);
						// get attributes names and values
						NodeList APIList = DLLNode.getChildNodes();

						for (int i = 0; i < APIList.getLength(); i++) {
							Node apiNode = APIList.item(i);
							// make sure it's element node.
							if (apiNode.getNodeType() == Node.ELEMENT_NODE
									&& apiNode.hasAttributes()) {
								// get attributes names and values
								NamedNodeMap apiMap = apiNode.getAttributes();
								apiHashMap.put(apiMap.getNamedItem("funcName")
										.getNodeValue(),
										apiMap.getNamedItem("className")
												.getNodeValue());
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
		}

		init = true;
	}

	private static String findClassName(String funcName, String libName) {
		init();
		String fullClassName = null;
		HashMap<String, String> apiMap = libMapping.get(libName);
		fullClassName = apiMap.get(funcName);
		return fullClassName;
	}

	public static void executeAPI(AbsoluteAddress address, String api,
			Instruction inst, BPPath path, List<BPPath> pathList) {
		System.out.println("\tCall api: " + api);
		String t[] = api.split("@");

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

		// if (funcName.contains("ExitProcess"))
		// System.out.println("Debug");

		if (inst.getName().toString().equals("jmp")) {
			System.out.println("JMP API:" + funcName);

			// if (funcName.equals("ExitProcess") || funcName.equals("exit")) {
			// System.out.println("Debgug");
			// }

			BPVertex v1 = cfg.getVertex(curState.getLocation(),
					curState.getInstruction());
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
			if (returnAddr instanceof LongValue) {
				r = ((LongValue) returnAddr).getValue();
			} else
				r = curState.getLocation().getValue()
						+ curState.getInstruction().getSize();
			AbsoluteAddress addr = new AbsoluteAddress(r);
			Instruction newInst = program.getInstruction(addr, env);
			v1 = cfg.insertVertex(new BPVertex(addr, newInst));
			cfg.insertEdge(new BPEdge(v2, v1));

			curState.setLocation(addr);
			curState.setInstruction(newInst);
		} else if (inst.getName().toString().equals("call")) {
			System.out.println("Call API:" + funcName);

			BPVertex v1 = cfg.getVertex(curState.getLocation(),
					curState.getInstruction());
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

			long r = curState.getLocation().getValue()
					+ curState.getInstruction().getSize();
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

		Memory memory = env.getMemory();
		Register register = env.getRegister();
		SystemHandle system = env.getSystem();
		if (funcName.startsWith("LoadLibrary")) {
			// LPCTSTR lpLibFileName // address of filename of executable
			// module
			Value x1 = stack.pop();
			System.out.print("Argument:" + x1);
			if (x1 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.loadLibraryA( ((ValueLongExp)
				 * x1).getValueOperand(), program);
				 */
				String libraryName = memory.getText(new X86MemoryOperand(
						DataType.INT32, ((LongValue) x1).getValue()));
				System.out.println(" Library Name:" + libraryName);
				long temp = system.getLibraryHandle(libraryName);
				register.mov("eax", new LongValue(temp));
				System.out.println("Return value: " + temp);
			}
			return;
		} else if (funcName.startsWith("GetProcAddress")) {
			// HMODULE hModule, handle to DLL module
			// LPCSTR lpProcName name of function
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " ");

			if (x1 instanceof LongValue && x2 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(),
				 * program);
				 */
				String functionName = memory.getText(new X86MemoryOperand(
						DataType.INT32, ((LongValue) x2).getValue()));
				System.out.println("Function Name:" + functionName
						+ ", Library Handle:" + x1);
				long t1 = 0;
				if (functionName != "")
					t1 = system.getProcAddress(((LongValue) x1).getValue(),
							functionName);
				else
					t1 = system.getProcAddress(((LongValue) x1).getValue(),
							((LongValue) x2).getValue());
				register.mov("eax", new LongValue(t1));
				System.out.println("Return Value: " + t1);
			}
			return;
		}

		String className = findClassName(t[0].toLowerCase(), t[1].toLowerCase()
				.replace(".dll", ""));
		if (className != null) {
			try {
				Class<?> clazz = Class.forName(className);
				Constructor<?> ctor = clazz.getConstructor();
				API apiObject = (API) ctor.newInstance();

				apiObject.execute(address, t[0], path.getCurrentState(), inst);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Unsupported API: " + api);
			if (funcName.startsWith("ShellExecute")) {
				Value x1 = stack.pop();
				Value x2 = stack.pop();
				Value x3 = stack.pop();
				Value x4 = stack.pop();
				Value x5 = stack.pop();
				Value x6 = stack.pop();
				System.out.println("Argument: " + x1 + ", " + x2 + ", " + x3
						+ ", " + x4 + ", " + x5 + ", " + x6);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName ));
			} else	if (funcName.startsWith("InternetGetConnectedState")) {
				Value x1 = stack.pop();
				Value x2 = stack.pop();
				System.out.println("Argument: " + x1 + ", " + x2);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName ));
			} else if (funcName.startsWith("s_perror")) {
				Value x1 = stack.pop();
				Value x2 = stack.pop();
				System.out.println("Argument: " + x1 + ", " + x2);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName ));
			} else if (funcName.startsWith("setusermatherr")) {
				Value x1 = stack.pop();
				System.out.println("Argument: " + x1);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName ));
			} else
			if (funcName.startsWith("puts")) {
				Value x1 = stack.pop();
				System.out.println("Argument: " + x1);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName ));
			} else
			if (funcName.startsWith("_write")) {
				Value x1 = stack.pop();
				Value x2 = stack.pop();
				Value x3 = stack.pop();
				System.out.println("Argument= " + x1 + ", " + x2 + ", " + x3);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName ));
			} else if (funcName.startsWith("ord(115)")) {
				Value x1 = stack.pop();
				Value x2 = stack.pop();
				System.out.println("WSAStarup API: Argument= " + x1 + ", " + x2);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName ));
			} else if (funcName.startsWith("_initterm")) {
				Value x1 = stack.pop();
				Value x2 = stack.pop();
				System.out.println("Argument: " + x1 + ", " + x2);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName ));
			} else if (funcName.startsWith("__getmainargs")) {
				Value x1 = stack.pop();
				Value x2 = stack.pop();
				Value x3 = stack.pop();
				Value x4 = stack.pop();
				Value x5 = stack.pop();
				System.out.println("Argument: " + x1 + ", " + x2 + ", " + x3 + ", " + x4 + ", " + x5);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName ));
			} else			
			if (funcName.startsWith("_controlfp")) {
				Value x1 = stack.pop();
				Value x2 = stack.pop();
				stack.push(x2);
				stack.push(x1);
				System.out.println("Argument: " + x1 + ", " + x2);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName ));
			} else if (funcName.startsWith("__set_app_type")) {
				Value x1 = stack.pop();
				System.out.println("Argument: " + x1);
				env.getRegister().setRegisterValue("eax", new LongValue(1));
			} else if (funcName.startsWith("__p__fmode")) {
				System.out.println("Argument: None");
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName ));
			} else if (funcName.startsWith("__p__commode")) {
				System.out.println("Argument: None");
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName ));
			} else if (funcName.startsWith("WinExec")) {
				// HMODULE hModule, handle to DLL module
				// LPCSTR lpProcName name of function
				Value x1 = stack.pop();
				Value x2 = stack.pop();
				System.out.println("Argument:" + x1 + " " + x2 + " ");

				if (x1 instanceof LongValue && x2 instanceof LongValue) {
					/*
					 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
					 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
					 */
					String commandLine = memory.getText(new X86MemoryOperand(
							DataType.INT32, ((LongValue) x1).getValue()));
					System.out.println("Command Line:" + commandLine
							+ ", Window Style:" + ((LongValue) x2).getValue());
					register.mov(
							"eax",
							new LongValue(system.getWindowHandle().createWindow(
									commandLine, ((LongValue) x2).getValue())));
				}
			} else if (funcName.startsWith("GetTopWindow")) {
				Value x1 = stack.pop();
				//Value x2 = stack.pop();
				System.out.println("Argument: " + x1);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName ));
			} else if (funcName.startsWith("GetPriorityClass")) {
				Value x1 = stack.pop();
				//Value x2 = stack.pop();
				System.out.println("Argument: " + x1);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName ));
			} else if (funcName.startsWith("SetPriorityClass")) {
				Value x1 = stack.pop();
				Value x2 = stack.pop();
				System.out.println("Argument: " + x1 + ", " + x2);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName ));
			} else if (funcName.startsWith("BlockInput")) {
				Value x1 = stack.pop();
				//Value x2 = stack.pop();
				System.out.println("Argument: " + x1);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName ));
			} else if (funcName.startsWith("CryptAcquireContextA")) {
				Value x1 = stack.pop();
				Value x2 = stack.pop();
				Value x3 = stack.pop();
				Value x4 = stack.pop();
				Value x5 = stack.pop();
				System.out.println("Argument: " + x1 + ", " + x2 + ", " + x3 + ", " + x4 + ", " + x5);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName ));
			} else if (funcName.startsWith("CryptHashData")) {
				Value x1 = stack.pop();
				Value x2 = stack.pop();
				Value x3 = stack.pop();
				Value x4 = stack.pop();
				//Value x5 = stack.pop();
				System.out.println("Argument: " + x1 + ", " + x2 + ", " + x3 + ", " + x4);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName ));
			} else if (funcName.startsWith("CryptDeriveKey")) {
				Value x1 = stack.pop();
				Value x2 = stack.pop();
				Value x3 = stack.pop();
				Value x4 = stack.pop();
				Value x5 = stack.pop();
				System.out.println("Argument: " + x1 + ", " + x2 + ", " + x3 + ", " + x4 + ", " + x5);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName ));
			} else if (funcName.startsWith("CryptDestroyHash")) {
				Value x1 = stack.pop();
				//Value x2 = stack.pop();
				System.out.println("Argument: " + x1);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName ));
			} else if (funcName.startsWith("Thread32First")) {
				Value x1 = stack.pop();
				Value x2 = stack.pop();
				System.out.println("Argument: " + x1 + ", " + x2);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName ));
			} else if (funcName.startsWith("Thread32Next")) {
				Value x1 = stack.pop();
				Value x2 = stack.pop();
				System.out.println("Argument: " + x1 + ", " + x2);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName ));
			} else if (funcName.startsWith("OpenThread")) {
				Value x1 = stack.pop();
				Value x2 = stack.pop();
				Value x3 = stack.pop();
				System.out.println("Argument: " + x1 + ", " + x2 + ", " + x3);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName ));
			} else if (funcName.startsWith("SuspendThread")) {
				Value x1 = stack.pop();
				//Value x2 = stack.pop();
				System.out.println("Argument: " + x1);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName ));
			} else if (funcName.startsWith("ExitThread")) {
				Value x1 = stack.pop();
				//Value x2 = stack.pop();
				System.out.println("Argument: " + x1);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName ));
			} else if (funcName.startsWith("CryptCreateHash")) {
				Value x1 = stack.pop();
				Value x2 = stack.pop();
				Value x3 = stack.pop();
				Value x4 = stack.pop();
				Value x5 = stack.pop();
				System.out.println("Argument: " + x1 + ", " + x2 + ", " + x3 + ", " + x4 + ", " + x5);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName ));
			} else if (funcName.startsWith("GetWindowLong")) {
				Value x1 = stack.pop();
				Value x2 = stack.pop();
				//Value x3 = stack.pop();
				System.out.println("Argument: " + x1 + ", " + x2);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName ));
			} else if (funcName.startsWith("SetWindowLong")) {
				Value x1 = stack.pop();
				Value x2 = stack.pop();
				Value x3 = stack.pop();
				System.out.println("Argument: " + x1 + ", " + x2 + ", " + x3);
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax_" + funcName ));
			} else {
				System.out.println("No Handling of this API");
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax"));
			}
		}
	}

	// PHONG: insert here
	// Need to improve here, process the opcodes[] for best performance
	public static byte[] getOpcodesArray(BPState curState, long address) {
		VirtualMemory vM = curState.getEnvironement().getSystem()
				.getVirtualHandle().getCurrentVirtualMemory();
		vM.setAddress(address);
		// long offset = address - vM.getBaseAddress();
		byte[] opcodes = new byte[(int) vM.getSize()];
		// can modify here for best result, i < 10, because one asm statement
		// needs 10 bytes or less
		for (int i = 0; i < /* vM.getSize() - offset */10; i++) {
			long virtualAdrr = vM.getAddress() + (long) i;
			opcodes[i] = (byte) ((LongValue) curState.getEnvironement()
					.getMemory().getByteMemoryValue(virtualAdrr)).getValue();
		}
		return opcodes;
	}
}
