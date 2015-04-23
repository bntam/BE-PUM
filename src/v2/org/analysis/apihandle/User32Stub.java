package v2.org.analysis.apihandle;

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
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.SymbolValue;
import v2.org.analysis.value.Value;

public class User32Stub extends APIStub {
	private String libraryName = "user32.dll";
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
		// Register register = env.getRegister();
		Program program = Program.getProgram();
		// SystemHandle system = env.getSystem();
		BPCFG cfg = program.getBPCFG();

		// if (funcName.equals("FindFirstFileA"))
		// System.out.println("Debug");
		if (inst.getName().toString().equals("jmp")) {
			System.out.println("JMP of API User32.dll:" + funcName);
			BPVertex v1 = cfg.getVertex(curState.getLocation(),
					curState.getInstruction());
			v1.setProperty(getFullName(funcName));
			BPVertex v2 = new BPVertex();
			// v2.setAddress(address);
			v2.setProperty(getFullName(funcName));			

			if (funcName.equals("ExitProcess") || funcName.equals("exit")) {
				v2.setType(BPVertex.ExitNode);
				v2 = cfg.insertVertex(v2);
				cfg.insertEdge(new BPEdge(v1, v2));
				// v2.setProperty(getFullName(funcName));
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
			System.out.println("Call of API User32.dll:" + funcName);

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

		if (funcName.startsWith("MessageBoxA")) {
			/*
			 * HWND hWnd, // handle of owner window LPCTSTR lpText, // address
			 * of text in message box LPCTSTR lpCaption, // address of title of
			 * message box UINT uType // style of message box
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4);
			System.out.print("Handle:" + x1.toString());
			if (x2 instanceof LongValue) {
				System.out.print(", Address of Text:"
						+ x2.toString()
						+ ", Text:"
						+ memory.getText(new X86MemoryOperand(DataType.INT32,
								((LongValue) x2).getValue())));
			}

			if (x3 instanceof LongValue) {
				System.out.print(", Address of Title Text:"
						+ x3.toString()
						+ ", Title Text:"
						+ memory.getText(new X86MemoryOperand(DataType.INT32,
								((LongValue) x3).getValue())));
			}

			System.out.println(", Style:" + x4.toString());
		} else if (funcName.startsWith("CharToOemBuff")) {
			/*
			 * HWND hWnd, // handle of owner window LPCTSTR lpText, // address
			 * of text in message box LPCTSTR lpCaption, // address of title of
			 * message box UINT uType // style of message box
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3);
			//System.out.print("Handle:" + x1.toString());
			if (x1 instanceof LongValue && x2 instanceof LongValue
					&& x3 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				long t1 = ((LongValue) x1).getValue();
				long t2 = ((LongValue) x2).getValue();
				long t3 = ((LongValue) x3).getValue();
								
				// String str = symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32, t2));
				System.out.println("Pointer:" + t1 + ", Pointer:"
						+ t2 + ", Length:" + t3);
				
				env.getRegister().mov("eax", new LongValue(1));
			}
		} else {
/*			if (!this.processedAPI.contains(funcName)) {
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax"));
				processedAPI.add(funcName);
			} else {
				env.getRegister().setRegisterValue("eax", new LongValue(1));
			}*/
			env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax"));
		}

		return ret;
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

	@Override
	public boolean equalsLibraryName(String l) {
		// TODO Auto-generated method stub
		return this.libraryName.equals(l.toLowerCase());
	}

	private String getFullName(String funcName) {
		// TODO Auto-generated method stub
		return funcName + "@" + libraryName;
	}
}
