package v2.org.analysis.transition_rule;

import java.util.ArrayList;
import java.util.List;

import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.cfg.BPCFG;
import v2.org.analysis.cfg.BPEdge;
import v2.org.analysis.cfg.BPVertex;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.system.SystemHandle;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.SymbolValue;
import v2.org.analysis.value.Value;

public class DefaultStub extends APIStub {
	private String libraryName = "";
	//private List<String> processedAPI = new ArrayList<String>();

	@Override
	public boolean executeAPI(AbsoluteAddress address, String funcName,
			BPState curState, Instruction inst, int cond) {
		// TODO Auto-generated method stub
		// long returnValue;
		boolean ret = true;
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();
		Program program = Program.getProgram();
		SystemHandle system = env.getSystem();
		BPCFG cfg = program.getBPCFG();

		System.out.println("Default stub for handling " + funcName);
		//if (funcName.equals("_except_handler3"))
		//	System.out.println("Debug");
		if (inst.getName().toString().equals("jmp")) {
			System.out.println("JMP of API:" + funcName);
			BPVertex v1 = cfg.getVertex(curState.getLocation(),
					curState.getInstruction());
			v1.setProperty(getFullName(funcName));
			BPVertex v2 = new BPVertex();
			// v2.setAddress(address);
			//
			v2.setProperty(getFullName(funcName));			

			if (funcName.equals("ExitProcess") || funcName.equals("exit")) {
				v2.setType(BPVertex.ExitNode);
				v2 = cfg.insertVertex(v2);
				// v2.setProperty(getFullName(funcName));
				cfg.insertEdge(new BPEdge(v1, v2));
				curState.setLocation(null);
				curState.setInstruction(null);
				return true;
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
			System.out.println("Call of API:" + funcName);

			BPVertex v1 = cfg.getVertex(curState.getLocation(),
					curState.getInstruction());
			v1.setProperty(getFullName(funcName));
			BPVertex v2 = new BPVertex();
			// v2.setAddress(address);			
			v2.setProperty(getFullName(funcName));
			
			if (funcName.equals("ExitProcess") || funcName.equals("exit")) {
				v2.setType(BPVertex.ExitNode);
				v2 = cfg.insertVertex(v2);
				// v2.setProperty(getFullName(funcName));
				cfg.insertEdge(new BPEdge(v1, v2));
				curState.setLocation(null);
				curState.setInstruction(null);
				return true;
			}
			
			v2.setType(BPVertex.APINode);
			v2 = cfg.insertVertex(v2);
			cfg.insertEdge(new BPEdge(v1, v2));
			long r = curState.getLocation().getValue()
					+ curState.getInstruction().getSize();
			AbsoluteAddress addr = new AbsoluteAddress(r);
			Instruction newInst = program.getInstruction(addr, env);
			v1 = cfg.insertVertex(new BPVertex(addr, newInst));
			cfg.insertEdge(new BPEdge(v2, v1));

			curState.setLocation(addr);
			curState.setInstruction(newInst);
		}

		if (funcName.startsWith("ShellExecute")) {
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			Value x5 = stack.pop();
			Value x6 = stack.pop();
			System.out.println("Argument: " + x1 + ", " + x2 + ", " + x3
					+ ", " + x4 + ", " + x5 + ", " + x6);
			env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax"));
		} else	if (funcName.startsWith("InternetGetConnectedState")) {
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument: " + x1 + ", " + x2);
			env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax"));
		} else if (funcName.startsWith("s_perror")) {
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument: " + x1 + ", " + x2);
			env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax"));
		} else
		if (funcName.startsWith("setusermatherr")) {
			Value x1 = stack.pop();
			System.out.println("Argument: " + x1);
			env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax"));
		} else
		if (funcName.startsWith("puts")) {
			Value x1 = stack.pop();
			System.out.println("Argument: " + x1);
			env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax"));
		} else
		if (funcName.startsWith("_write")) {
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			System.out.println("Argument= " + x1 + ", " + x2 + ", " + x3);
			env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax"));
		} else if (funcName.startsWith("ord(115)")) {
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("WSAStarup API: Argument= " + x1 + ", " + x2);
			env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax"));
		} else if (funcName.startsWith("_initterm")) {
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument: " + x1 + ", " + x2);
			env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax"));
		} else if (funcName.startsWith("__getmainargs")) {
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			Value x5 = stack.pop();
			System.out.println("Argument: " + x1 + ", " + x2 + ", " + x3 + ", " + x4 + ", " + x5);
			env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax"));
		} else			
		if (funcName.startsWith("_controlfp")) {
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			stack.push(x2);
			stack.push(x1);
			System.out.println("Argument: " + x1 + ", " + x2);
			env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax"));
		} else if (funcName.startsWith("__set_app_type")) {
			Value x1 = stack.pop();
			System.out.println("Argument: " + x1);
			env.getRegister().setRegisterValue("eax", new LongValue(1));
		} else if (funcName.startsWith("__p__fmode")) {
			System.out.println("Argument: None");
			env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax"));
		} else if (funcName.startsWith("__p__commode")) {
			System.out.println("Argument: None");
			env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax"));
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
		} else {		
			//Default Stub
			/*if (!this.processedAPI.contains(funcName)) {
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax"));
				processedAPI.add(funcName);
			} else {
				env.getRegister().setRegisterValue("eax", new LongValue(1));
			}*/
			System.out.println("No Handling of this API");
			env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax"));
		}

		return ret;
	}

	private String getFullName(String funcName) {
		// TODO Auto-generated method stub
		return funcName + "@" + libraryName;
	}

	@Override
	public boolean equalsLibraryName(String libraryName) {
		// TODO Auto-generated method stub
		return true;
	}

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
}
