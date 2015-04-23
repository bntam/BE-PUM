package v2.org.analysis.apihandle.be_pum.winapi;

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
import v2.org.analysis.value.Value;

public class DefaultStub extends APIStub {
	private String libraryName = "";

	@Override
	public boolean executeAPI(AbsoluteAddress address, String funcName,
			BPState curState, Instruction inst, boolean cond) {
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
			System.out.println("JMP Symbolic Execution of API:" + funcName);
			BPVertex v1 = cfg.getVertex(curState.getLocation(),
					curState.getInstruction());
			v1.setProperty(getFullName(funcName));
			BPVertex v2 = new BPVertex();
			// v2.setAddress(address);
			v2.setType(BPVertex.APINode);
			v2.setProperty(getFullName(funcName));
			v2 = cfg.insertVertex(v2);

			if (funcName.equals("ExitProcess") || funcName.equals("exit")) {
				v2.setType(BPVertex.ExitNode);
				// v2.setProperty(getFullName(funcName));
				cfg.insertEdge(new BPEdge(v1, v2));
				curState.setLocation(null);
				curState.setInstruction(null);
				return true;
			}

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

			if (funcName.equals("ExitProcess") || funcName.equals("exit")) {
				v2.setType(BPVertex.ExitNode);
				// v2.setProperty(getFullName(funcName));
				cfg.insertEdge(new BPEdge(v1, v2));
				curState.setLocation(null);
				curState.setInstruction(null);
				return true;
			}
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

		if (funcName.startsWith("WinExec")) {
			// HMODULE hModule, handle to DLL module
			// LPCSTR lpProcName name of function
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " ");

			if (x1 instanceof LongValue && x2 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValue(), ((ValueLongExp) x2).getValue(), program);
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
