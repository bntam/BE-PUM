/**
 * 
 */
package v2.org.analysis.transition_rule;

import java.util.ArrayList;
import java.util.List;

import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;
import v2.org.analysis.cfg.BPCFG;
import v2.org.analysis.cfg.BPEdge;
import v2.org.analysis.cfg.BPVertex;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.SymbolValue;
import v2.org.analysis.value.Value;

/**
 * @author NMHai
 * 
 */
public class MAPIStub extends APIStub {
	private String libraryName = "mapistub.dll";
	private List<String> processedAPI = new ArrayList<String>();
	/*
	 * (non-Javadoc)
	 * 
	 * @see v2.org.analysis.transition_rule.APIStub#executeAPI(org.jakstab.asm.
	 * AbsoluteAddress, java.lang.String, v2.org.analyis.path.BPState,
	 * org.jakstab.asm.Instruction)
	 */
	@Override
	public boolean executeAPI(AbsoluteAddress address, String funcName,
			BPState curState, Instruction inst, int cond) {
		// TODO Auto-generated method stub
		// long returnValue;
		boolean ret = true;
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		// Memory memory = env.getMemory();
		// Register register = env.getRegister();
		Program program = Program.getProgram();
		// SystemHandle system = env.getSystem();
		BPCFG cfg = program.getBPCFG();

		// if (funcName.equals("FindFirstFileA"))
		// System.out.println("Debug");
		if (inst.getName().toString().equals("jmp")) {
			System.out.println("JMP Symbolic Execution of API:" + funcName);
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
			System.out.println("Call Symbolic Execution of API:" + funcName);

			BPVertex v1 = cfg.getVertex(curState.getLocation(),
					curState.getInstruction());
			v1.setProperty(getFullName(funcName));
			BPVertex v2 = new BPVertex();
			// v2.setAddress(address);
			v2.setType(BPVertex.APINode);
			v2.setProperty(getFullName(funcName));
			v2 = cfg.insertVertex(v2);
			cfg.insertEdge(new BPEdge(v1, v2));

			if (funcName.equals("ExitProcess") || funcName.equals("exit")) {
				v2.setType(BPVertex.ExitNode);
				// v2.setProperty(getFullName(funcName));
				curState.setLocation(null);
				curState.setInstruction(null);
				return true;
			}

			long r = curState.getLocation().getValue()
					+ curState.getInstruction().getSize();
			AbsoluteAddress addr = new AbsoluteAddress(r);
			Instruction newInst = program.getInstruction(addr, env);
			v1 = cfg.insertVertex(new BPVertex(addr, newInst));
			cfg.insertEdge(new BPEdge(v2, v1));

			curState.setLocation(addr);
			curState.setInstruction(newInst);
		}

		if (funcName.startsWith("FixMAPI")) {
			// HMODULE hModule, handle to DLL module
			// LPCSTR lpProcName name of function
			// Value x1 = stack.pop();
			// Value x2 = stack.pop();
			// System.out.println("Argument:" + x1 + " " + x2 + " ");

			/*
			 * if (x1 instanceof LongValue && x2 instanceof LongValue) {
			 * 
			 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
			 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
			 * 
			 * String commandLine = memory.getText(new X86MemoryOperand(
			 * DataType.INT32, ((LongValue) x1).getValueOperand()));
			 * System.out.println("Command Line:" + commandLine +
			 * ", Window Style:" + ((LongValue) x2).getValueOperand()); register.mov(
			 * "eax", new LongValue(system.getWindowHandle().createWindow(
			 * commandLine, ((LongValue) x2).getValueOperand())));
			 * 
			 * }
			 */
		} else {
			if (!this.processedAPI.contains(funcName)) {
				env.getRegister().setRegisterValue("eax", new SymbolValue("api_eax"));
				processedAPI.add(funcName);
			} else {
				env.getRegister().setRegisterValue("eax", new LongValue(1));
			}
		}

		return ret;
	}

	private String getFullName(String funcName) {
		// TODO Auto-generated method stub
		return funcName + "@" + libraryName;
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

}
