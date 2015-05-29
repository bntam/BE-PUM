/**
 * 
 */
package v2.org.analysis.transition_rule;

import v2.org.analysis.apihandle.winapi.APIHandle;

//import v2.org.analysis.apihandle.APIHandle;
import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Immediate;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.Operand;
import org.jakstab.asm.x86.*;

import v2.org.analysis.cfg.BPCFG;
import v2.org.analysis.cfg.BPEdge;
import v2.org.analysis.cfg.BPVertex;
import v2.org.analysis.complement.Convert;
import v2.org.analysis.environment.ContextRecord;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.ExceptionRecord;
import v2.org.analysis.loop.LoopAlgorithm;
import v2.org.analysis.path.BPPath;
import v2.org.analysis.path.BPState;
import v2.org.analysis.system.VirtualMemory;
import v2.org.analysis.value.*;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author NMHai
 * 
 */
public class X86TransitionRule extends TransitionRule {
	static long MAX_LOOP_MULTI = 20;
	private APIHandle apiHandle = new APIHandle();
	private long loop_mullti = 0;
	private boolean setCFG = false;
	private List<String> checkedFormulasTrue = new ArrayList<String>();
	private List<String> checkedFormulasFalse = new ArrayList<String>();

	// PHONG: 20150502 --------------------------------------------------------
	boolean checkAddressValidJump(Environment env, long t) {
		// TODO Auto-generated method stub
		if (!env.getSystem().getSEHHandler().isSet())
			return true;
		
		if (env.getSystem().getLibraryHandle().getAPIName(t) != "")
			return true;				
		
		AbsoluteAddress addr = new AbsoluteAddress(t);

		if (addr.getValue() == 0)
			return false;
		boolean c1 = Program.getProgram().checkAddress(addr);
		boolean c2 = env.getStack().isInsideStack(addr);
		boolean c3 = env.getMemory().contains(addr);
		boolean c4 = env.getSystem().getKernel().isInside(addr);
		boolean c5 = env.getSystem().getUser32().isInsideKernel32(addr);
		boolean c6 = env.getSystem().getFileHandle().isInsideFIle(addr);
		boolean c7 = env.getSystem().getLibraryHandle().isInside(addr);

		return (c1 || c2 || c3 || c4 || c5 || c6 || c7);
	}

	// ------------------------------------------------------------------------

	boolean checkAddressValid(Environment env, X86MemoryOperand d) {
		// TODO Auto-generated method stub
		/*
		 * if (Program.getProgram().getFileName().equals("hostname.exe") ||
		 * Program.getProgram().getFileName().equals("diskcopy.com")) return
		 * true;
		 */
		if (d != null && d.getBase() != null && d.getBase() instanceof X86Register
				&& d.getBase().toString().contains("esp"))
			return true;

		X86MemoryOperand t = env.getMemory().evaluateAddress(d, env);

		if (t == null || t.getBase() != null || t.getSegmentRegister() != null || t.getIndex() != null)
			return true;

		if (!env.getSystem().getSEHHandler().isSet())
			return true;

		AbsoluteAddress addr = new AbsoluteAddress(t.getDisplacement());

		if (addr.getValue() == 0)
			return false;

		boolean c1 = Program.getProgram().checkAddress(addr);
		boolean c2 = env.getStack().isInsideStack(addr);
		boolean c3 = env.getMemory().contains(addr);
		boolean c4 = env.getSystem().getKernel().isInside(addr);
		boolean c5 = env.getSystem().getUser32().isInsideKernel32(addr);
		boolean c6 = env.getSystem().getFileHandle().isInsideFIle(addr);
		boolean c7 = env.getSystem().getLibraryHandle().isInside(addr);

		return (c1 || c2 || c3 || c4 || c5 || c6 || c7);
	}

	public String checkAPICall(Value r, BPState curState) {
		// YenNguyen: Check address in JNA memory
		String api = APIHandle.checkAPI(((LongValue) r).getValue());
		
		if (api == null || api == "") {
			api = curState.getEnvironement().getSystem().getLibraryHandle().getAPIName(((LongValue) r).getValue());
		}
		
		if (api == null || api == "") {
			api = Program.getProgram().checkAPI(((LongValue) r).getValue(), curState.getEnvironement());
			if (api != null && api.equals(""))
				api = null;
		}	
		
		return api;
	}

	boolean checkZ3(Formulas formulas) {
		// TODO Auto-generated method stub
		// System.out.println("Check feasibility of path with Z3");
		if (formulas.isEmpty()) {
			// Program.getProgram().getLog().info("Check Path Condition: SAT");
			return true;
		}

		if (formulas.isBooleanValue())
			return formulas.evaluate();

		// Reuse the result of other running
		if (checkedFormulasTrue.contains(formulas.toStringPrefix()))
			return true;

		if (checkedFormulasFalse.contains(formulas.toStringPrefix()))
			return false;

		// if ()

		try {
			// System.out.println();
			this.writeZ3Input(System.getProperty("user.dir") + "/z3Input.smt", formulas);
			// Process p = Runtime.getRuntime().exec(
			// "cmd /c start /wait run_z3.bat");
			Process p = Runtime.getRuntime().exec(
					"cmd /c start /wait " + System.getProperty("user.dir") + "/run_z3.bat");
			/*
			 * Process p = Runtime.getRuntime().exec( "cmd /c start /wait " +
			 * System.getProperty("user.dir") + "/z3-4.3.0-x86/bin/z3.exe /smt "
			 * + System.getProperty("user.dir") + "/z3Input.smt > " +
			 * System.getProperty("user.dir") + "/z3Output.txt");
			 */
			Program.getProgram().getLog().info("Running script Z3...");
			int exitCode = p.waitFor();

			Program.getProgram().getLog().info("Done script. ExitCode:" + exitCode);
		} catch (Throwable e) {
			System.out.flush();
			e.printStackTrace();
			// Runtime.getRuntime().removeShutdownHook(shutdownThread);
			// Kills eclipse shutdown thread
			System.exit(1);
		}

		InputStream fis;
		BufferedReader br;

		try {
			String line = "";
			fis = new FileInputStream(System.getProperty("user.dir") + "/z3Output.txt");
			// fis = new FileInputStream("z3Output.txt");
			br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
			line = br.readLine();

			if (line != null && line.equals("sat")) {
				Program.getProgram().getLog().info("Check Path Condition: SAT");
				br.close();
				br = null;
				fis = null;
				checkedFormulasTrue.add(formulas.toStringPrefix());
				return true;
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		// Done with the file
		// System.out.println(result.substring(8));
		Program.getProgram().getLog().info("Check Path Condition: UNSAT");
		checkedFormulasFalse.add(formulas.toStringPrefix());
		br = null;
		fis = null;

		return false;
	}

	Map<String, Long> executeZ3(Formulas l) {
		// TODO Auto-generated method stub
		this.writeZ3Input(System.getProperty("user.dir") + "/z3Input.smt", l);
		try {
			// Process p = Runtime.getRuntime().exec(
			// "cmd /c start /wait run_z3.bat");
			Process p = Runtime.getRuntime().exec(
					"cmd /c start /wait " + System.getProperty("user.dir") + "/run_z3.bat");
			/*
			 * Process p = Runtime.getRuntime().exec( "cmd /c start /wait " +
			 * System.getProperty("user.dir") +
			 * "/z3-4.3.2-x86-win/bin/z3.exe /smt " +
			 * System.getProperty("user.dir") + "/z3Input.smt > " +
			 * System.getProperty("user.dir") + "/z3Output.txt");
			 */
			Program.getProgram().getLog().info("Running script Z3...");
			int exitCode = p.waitFor();

			Program.getProgram().getLog().info("Done script. ExitCode:" + exitCode);
		} catch (Throwable e) {
			System.out.flush();
			e.printStackTrace();
			// Runtime.getRuntime().removeShutdownHook(shutdownThread);
			// Kills eclipse shutdown thread
			System.exit(1);
		}
		// return this.readZ3Output(System.getProperty("user.dir") +
		// "/z3Output.txt");
		return this.readZ3Output(System.getProperty("user.dir") + "/z3Output.txt");
	}

	// Change the location and instruction for new state
	void generateNextInstruction(Instruction ins, BPPath path, List<BPPath> pathList, boolean cond) {
		// TODO Auto-generated method stub
		BPState curState = path.getCurrentState();
		BPCFG cfg = Program.getProgram().getBPCFG();
		BPVertex src = cfg.getVertex(curState.getLocation(), ins);

		if (ins instanceof X86ArithmeticInstruction) {
			AbsoluteAddress newLocation = new AbsoluteAddress(curState.getLocation().getValue() + ins.getSize());

			Instruction newIns;
			// FOR ARITHMETIC HERE
			if (curState.getEnvironement().getSystem().isInVirtualMemory() == true) {
				byte[] opcodes = this.getOpcodesArray(curState, newLocation.getValue());
				newIns = Program.getProgram().getInstruction(opcodes, curState.getEnvironement());
			} else
				newIns = Program.getProgram().getInstruction(newLocation, curState.getEnvironement());

			curState.setInstruction(newIns);
			curState.setLocation(newLocation);
		} else if (ins instanceof X86CallInstruction) {
			;
		} else if (ins instanceof X86CondJmpInstruction) {
			// Dieu kien dung
			if (cond) {
				Environment env = curState.getEnvironement();
				Operand dest = ((X86CondJmpInstruction) ins).getOperand1();
				Value r = null;
				AbsoluteAddress targetTemp = curState.getLocation();

				if (dest.getClass().getSimpleName().equals("X86AbsoluteAddress")) {
					r = new LongValue(((AbsoluteAddress) dest).getValue());
				} else if (dest.getClass().getSimpleName().equals("X86PCRelativeAddress")) {
					r = new LongValue(((X86PCRelativeAddress) dest).getEffectiveValue(targetTemp.getValue()));
				} else if (dest.getClass().getSimpleName().equals("X86Register")) {
					r = env.getRegister().getRegisterValue(dest.toString());
				} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					r = env.getMemory().getMemoryValue((X86MemoryOperand) dest, (X86Instruction) ins);
				}

				if (r != null && r instanceof LongValue) {
					AbsoluteAddress r1 = new AbsoluteAddress(((LongValue) r).getValue());
					Program.getProgram().getBPCFG().getVertex(curState.getLocation(), curState.getInstruction())
							.setProperty(r1.toString());

					String api = checkAPICall(r, curState);
					if (api != null/* !api.equals("") */) {
						apiHandle.executeAPI(new AbsoluteAddress(((LongValue) r).getValue()), api, ins, path, pathList);
						this.setCFG = true;
					} else {
						AbsoluteAddress nextAddr = new AbsoluteAddress(((LongValue) r).getValue());
						Instruction nextInst;
						// FOR CONDITIONAL JUMP HERE
						if (curState.getEnvironement().getSystem().isInVirtualMemory() == true) {
							byte[] opcodes = this.getOpcodesArray(curState, nextAddr.getValue());
							nextInst = Program.getProgram().getInstruction(opcodes, curState.getEnvironement());
						} else
							nextInst = Program.getProgram().getInstruction(nextAddr, env);

						curState.setInstruction(nextInst);
						curState.setLocation(nextAddr);
					}
				}
			} else {
				// Dieu kien sai
				AbsoluteAddress newLocation = new AbsoluteAddress(curState.getLocation().getValue() + ins.getSize());
				Instruction newIns;
				if (curState.getEnvironement().getSystem().isInVirtualMemory() == true) {
					byte[] opcodes = this.getOpcodesArray(curState, newLocation.getValue());
					newIns = Program.getProgram().getInstruction(opcodes, curState.getEnvironement());
				} else
					newIns = Program.getProgram().getInstruction(newLocation, curState.getEnvironement());

				curState.setInstruction(newIns);
				curState.setLocation(newLocation);
			}
		} else if (ins instanceof X86JmpInstruction)
			;
		else if (ins instanceof X86MoveInstruction) {
			AbsoluteAddress newLocation = new AbsoluteAddress(curState.getLocation().getValue() + ins.getSize());
			// FOR MOVE HERE
			Instruction newIns;
			if (curState.getEnvironement().getSystem().isInVirtualMemory() == true) {
				byte[] opcodes = this.getOpcodesArray(curState, newLocation.getValue());
				newIns = Program.getProgram().getInstruction(opcodes, curState.getEnvironement());
			} else
				newIns = Program.getProgram().getInstruction(newLocation, curState.getEnvironement());

			curState.setInstruction(newIns);
			curState.setLocation(newLocation);
		} else if (ins instanceof X86RetInstruction)
			;
		else if (ins instanceof X86Instruction) {
			AbsoluteAddress newLocation = new AbsoluteAddress(curState.getLocation().getValue() + ins.getSize());
			Instruction newIns;
			if (curState.getEnvironement().getSystem().isInVirtualMemory() == true) {
				byte[] opcodes = this.getOpcodesArray(curState, newLocation.getValue());
				newIns = Program.getProgram().getInstruction(opcodes, curState.getEnvironement());
			} else
				newIns = Program.getProgram().getInstruction(newLocation, curState.getEnvironement());

			curState.setInstruction(newIns);
			curState.setLocation(newLocation);
		}

		BPVertex dest = new BPVertex(curState.getLocation(), curState.getInstruction());
		dest = cfg.insertVertex(dest);
		BPEdge edge = new BPEdge(src, dest);
		cfg.insertEdge(edge);
	}

	public APIHandle getAPIHandle() {
		return this.apiHandle;
	}

	int getBitCount(Instruction ins) {
		// TODO Auto-generated method stub
		if (ins.getName().endsWith("b"))
			return 8;
		else if (ins.getName().endsWith("l"))
			return 32;
		else if (ins.getName().endsWith("s") || ins.getName().endsWith("w"))
			return 16;
		return 0;
	}

	private String getNameZ3Result(String line) {
		// TODO Auto-generated method stub
		String result[] = line.split(" ");
		return result[0];
	}

	@Override
	public void getNewState(BPPath path, List<BPPath> pathList, boolean cond) {
		// TODO Auto-generated method stub
		// Formulas l = path.getPathCondition();
		BPState curState = path.getCurrentState();
		BPCFG cfg = Program.getProgram().getBPCFG();
		Instruction ins = curState.getInstruction();
		BPVertex src = cfg.getVertex(curState.getLocation(), ins);

		if (ins instanceof X86ArithmeticInstruction)
			new X86ArithmeticInterpreter().execute((X86ArithmeticInstruction) ins, path, pathList, this);
		else if (ins instanceof X86CallInstruction)
			new X86CallInterpreter().execute((X86CallInstruction) ins, path, pathList, this);
		else if (ins instanceof X86CondJmpInstruction)
			new X86ConditionalJumpInterpreter().execute((X86CondJmpInstruction) ins, path, pathList, this);
		else if (ins instanceof X86JmpInstruction)
			new X86JumpInterpreter().execute((X86JmpInstruction) ins, path, pathList, this);
		else if (ins instanceof X86MoveInstruction)
			new X86MoveInterpreter().execute((X86MoveInstruction) ins, path, pathList, this);
		else if (ins instanceof X86RetInstruction)
			new X86ReturnInterpreter().execute((X86RetInstruction) ins, path, pathList, this);
		else if (ins instanceof X86Instruction)
			new X86InstructionInterpreter().execute((X86Instruction) ins, path, pathList, this);
		if (!setCFG) {
			BPVertex dest = new BPVertex(curState.getLocation(), curState.getInstruction());
			dest = cfg.insertVertex(dest);
			if (curState.getLocation() == null || curState.getInstruction() == null) {
				dest.setType(BPVertex.UnknownNode);
				dest.setProperty("Unknown Node");
			}

			BPEdge edge = new BPEdge(src, dest);
			cfg.insertEdge(edge);
		} else
			setCFG = false;
		// return curState;
		if (path.getCurrentState().checkFeasiblePath())
			LoopAlgorithm.getInstance().halt(path, this);
	}

	// Generate new Path Condition and check for feasibility with Z3
	public void getNewState(X86CondJmpInstruction inst, BPPath path, List<BPPath> pathList, boolean condition) {
		// TODO Auto-generated method stub
		new X86ConditionalJumpInterpreter().execute(inst, path, pathList, condition, this);
		if (path.getCurrentState().checkFeasiblePath())
			LoopAlgorithm.getInstance().halt(path, this);
	}

	// PHONG: insert here
	// Need to improve here, process the opcodes[] for best performance
	public byte[] getOpcodesArray(BPState curState, long address) {
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

	public boolean getSetCFG() {
		return this.setCFG;
	}

	private long getValueZ3(String line) {
		// TODO Auto-generated method stub
		long val = 0;
		String result[] = line.split(" ");

		if (result[0].contains("Flag")) {
			if (result[2].contains("false"))
				val = 0;
			else if (result[2].contains("true"))
				val = 1;
			return val;
		}

		if (result[2].startsWith("(")) {
			val = Long.parseLong(result[3].substring(0, result[3].length() - 1));
			// if (val > 100000) val = 0;
			if (result[2].substring(1).equals("-"))
				val = -val;
		} else {
			val = Long.parseLong(result[2].substring(2, result[2].length() - 4));
			if (val >= Math.pow(2, 31))
				val = (long) (val - Math.pow(2, 32));
		}
		return val;
	}

	private long getValueZ3Result(String line) {
		// TODO Auto-generated method stub
		long val = 0;
		String result[] = line.split(" ");
		if (result[0].contains("Flag")) {
			if (result[2].contains("false"))
				val = 0;
			else if (result[2].contains("true"))
				val = 1;
			return val;
		}

		if (result[2].startsWith("(")) {
			val = Long.parseLong(result[3].substring(0, result[3].length() - 1));
			// if (val > 100000) val = 0;
			if (result[2].substring(1).equals("-"))
				val = -val;
		} else {
			val = Long.parseLong(result[2].substring(2, result[2].length() - 4));
			// if (val >= Math.pow(2, 31))
			// val = (long) (val - Math.pow(2, 32));
		}
		return val;
	}

	// New version: Add (not dest1) in new Formula
	void multiDestination(Value r1, long value, Instruction inst, BPPath path, List<BPPath> pathList) {
		// TODO Auto-generated method stub
		if (loop_mullti > MAX_LOOP_MULTI)
			return;
		else
			loop_mullti++;

		BPPath p = path.clone();
		Formulas l = p.getPathCondition();

		Value l1 = new HybridBooleanValue(r1, new LongValue(value), "==");

		l.add(new Formula(l1, "not"));
		if (!this.checkZ3(l))
			return;

		pathList.add(p);
		Program.getProgram().generageCFG(Program.getProgram().getAbsolutePathFile() + "_test");
	}

	// Hai: Process the problem of multi destination of SAT Solver
	// Old implementation
	protected void multiDestination_Old(Map<String, Long> z3Value, X86JmpInstruction inst, BPPath path,
			List<BPPath> pathList) {
		// TODO Auto-generated method stub

		// Program.getProgram().generageCFG(Program.getProgram().getAbsolutePathFile()
		// + "_test");
		if (loop_mullti > MAX_LOOP_MULTI)
			return;
		else
			loop_mullti++;

		BPPath p = path.clone();
		Formulas l = p.getPathCondition();
		HybridBooleanValue l2 = null;

		for (Entry<String, Long> i : z3Value.entrySet()) {
			String var = i.getKey();
			long value = i.getValue();

			Value l1 = new HybridBooleanValue(new SymbolValue(var), new LongValue(value), "==");
			if (l2 != null)
				l2 = new HybridBooleanValue(l1, l2, "and");
			else
				l2 = (HybridBooleanValue) l1;
			// p.getPathCondition().add(new Formula());
		}

		l.add(new Formula(l2, "not"));
		if (!this.checkZ3(l))
			return;

		pathList.add(p);

		Program.getProgram().generageCFG(Program.getProgram().getAbsolutePathFile() + "_test");
	}

	/*
	 * public BPState processSEH(BPState curState) { // TODO Auto-generated
	 * method stub Program.getProgram().setTechnique("SEH");
	 * Program.getProgram().setDetailTechnique( "SEH:" + curState.getLocation()
	 * + " "); AbsoluteAddress addr = new
	 * AbsoluteAddress(curState.getEnvironement()
	 * .getSystem().getSEHHandler().getStart().getSehHandler()); Instruction
	 * inst = Program.getProgram().getInstruction(addr,
	 * curState.getEnvironement());
	 * 
	 * //ICFEM: Set SEH false
	 * curState.getEnvironement().getSystem().getSEHHandler
	 * ().setSEHReady(false);
	 * 
	 * // Set up Stack Configuration for SEH Value e = new
	 * LongValue(curState.getEnvironement().getSystem()
	 * .getSEHHandler().getStart().getAddrSEHRecord());
	 * curState.getEnvironement().getStack() .push(new LongValue((long)
	 * (Math.random() * Math.pow(10, 7))));
	 * curState.getEnvironement().getStack() .push(new LongValue((long)
	 * (Math.random() * Math.pow(10, 7))));
	 * 
	 * curState.getEnvironement().getStack() .push(new LongValue((long)
	 * (Math.random() * Math.pow(10, 7)))); // Address Code
	 * curState.getEnvironement().getStack() .push(new
	 * LongValue(curState.getLocation().getValue()));
	 * 
	 * curState.getEnvironement().getStack().push(new LongValue(0));
	 * curState.getEnvironement().getStack().push(new LongValue(0));
	 * curState.getEnvironement().getStack() .push(new LongValue((long)
	 * (Math.random() * Math.pow(10, 7)))); Value esp =
	 * curState.getEnvironement().getRegister() .getRegisterValue("esp");
	 * 
	 * curState.getEnvironement().getStack().push(e);
	 * curState.getEnvironement().getStack().push(esp);
	 * curState.getEnvironement().getStack().push(new LongValue(2089824936));
	 * 
	 * // Set up Registers for SEH curState.getEnvironement().getRegister()
	 * .setRegisterValue("eax", new LongValue(0));
	 * curState.getEnvironement().getRegister() .setRegisterValue("ebx", new
	 * LongValue(0)); curState.getEnvironement().getRegister()
	 * .setRegisterValue("ecx", new LongValue(addr.getValue()));
	 * 
	 * BPCFG cfg = Program.getProgram().getBPCFG(); BPVertex v1 =
	 * cfg.getVertex(curState.getLocation(), curState.getInstruction()); //
	 * v1.setProperty(getFullName(funcName)); BPVertex v2 = new BPVertex(addr,
	 * inst); // v2.setAddress(address); v2 = cfg.insertVertex(v2);
	 * cfg.insertEdge(new BPEdge(v1, v2)); curState.setLocation(addr);
	 * curState.setInstruction(inst); this.setCFG = true;
	 * 
	 * return curState; }
	 */
	// PHONG: 20150105 -------------------------------------------------------
	void setSystemSEH(BPState curState) {
		// TODO Auto-generated method stub
		// Program.getProgram().setTechnique("SetUpException");
		// Program.getProgram().setDetailTechnique(
		// "SetUpException:" + curState.getLocation() + " ");
		System.out.println("Set Up System Exception: " + curState.getLocation());
		Environment env = curState.getEnvironement();

		env.getSystem()
				.getSEHHandler()
				.getStart()
				.setNextSEHRecord(((LongValue) env.getStack().getValueStackFromIndex(0)).getValue(),
						((LongValue) env.getRegister().getRegisterValue("esp")).getValue());
		env.getSystem()
				.getSEHHandler()
				.getStart()
				.setSEHHandler(((LongValue) env.getStack().getValueStackFromIndex(4)).getValue(),
						((LongValue) env.getRegister().getRegisterValue("esp")).getValue() + 4);
		// env.getSystem().getSEHHandler().setSEHReady(true);
	}

	// ---------------------------------------------------------------------------

	// PHONG - 20150422
	public BPState processSEH(BPState curState) {
		Program.getProgram().setTechnique("SEH");
		Program.getProgram().setDetailTechnique("SEH:" + curState.getLocation() + " ");
		Program.getProgram().getLog().infoString("Process SEH at:" + curState.getLocation() + "\n");
		AbsoluteAddress addr = new AbsoluteAddress(curState.getEnvironement().getSystem().getSEHHandler().getStart()
				.getSehHandler());
		Instruction inst = Program.getProgram().getInstruction(addr, curState.getEnvironement());

		// Context changing
		// ---------------------------------------------------------------
		long err_ptr = curState.getEnvironement().getSystem().getSEHHandler().getStart().getAddrSEHRecord();

		// Step 1: Set up Context Record for SEH and Push it first to stack
		ContextRecord context_record = new ContextRecord();
		context_record.setContextRecord(curState);
		context_record.toStack(curState);
		long context_record_ptr = context_record.getContext_record_ptr();

		// Step 2: Set up Exception Record for SEH and push it to stack
		ExceptionRecord exception_record = new ExceptionRecord();
		exception_record.setExceptionRecord(curState);
		exception_record.toStack(curState);
		long exception_record_ptr = exception_record.getException_record_ptr();

		// Push the return value for system
		curState.getEnvironement().getStack().push(new LongValue(err_ptr));
		curState.getEnvironement().getStack().push(new LongValue(0x7C));
		curState.getEnvironement().getStack().push(new LongValue(err_ptr));
		this.setSystemSEH(curState);
		curState.getEnvironement().getStack().push(new LongValue(context_record_ptr));
		curState.getEnvironement().getStack().push(new LongValue(err_ptr));
		curState.getEnvironement().getStack().push(new LongValue(exception_record_ptr));
		curState.getEnvironement().getStack().push(new LongValue(0x7C9032A8));

		// Set up value for register
		// EAX = EBX = 0
		// ECX = address of next address
		curState.getEnvironement().getRegister().setRegisterValue("eax", new LongValue(0));
		curState.getEnvironement().getRegister().setRegisterValue("ebx", new LongValue(0));
		curState.getEnvironement().getRegister().setRegisterValue("ecx", new LongValue(addr.getValue()));
		// Set False for SEH
		curState.getEnvironement().getSystem().getSEHHandler().setSEHReady(false);
		// ---------------------------------------------------------------------------------

		BPCFG cfg = Program.getProgram().getBPCFG();
		BPVertex v1 = cfg.getVertex(curState.getLocation(), curState.getInstruction());
		BPVertex v2 = new BPVertex(addr, inst);
		v2 = cfg.insertVertex(v2);
		cfg.insertEdge(new BPEdge(v1, v2));
		curState.setLocation(addr);
		curState.setInstruction(inst);
		this.setCFG = true;

		return curState;
	}

	/*
	 * private String reverseName(String name) { // TODO Auto-generated method
	 * stub String var = name; // %eax if (name.startsWith("op_addr_base_") &&
	 * !name.contains("disp")) var = "(%" + var + ")"; // 0x441023 else if
	 * (var.startsWith("op_addr_disp_")) var = var.substring(13); else if
	 * (var.startsWith("op_addr_base_disp")) { String temp[] = var.split("_");
	 * var = temp[4] + "(%" + temp[5] + ")"; } else if
	 * (var.startsWith("op_addr_base2_disp")) { String temp[] = var.split("_");
	 * var = temp[4] + "(%" + temp[5] + "," + temp[6] + ")"; } else if
	 * (var.startsWith("op_addr_base_index")) { String temp[] = var.split("_");
	 * var = "(%" + temp[4] + ",%" + temp[5] + ")"; } else if
	 * (var.startsWith("op_addr_base_index_disp_")) { String temp[] =
	 * var.split("_"); var = "%" + temp[5] + ":(%" + temp[6] + ")"; } else if
	 * (var.startsWith("op_addr_base_index_base_")) { String temp[] =
	 * var.split("_"); var = temp[5] + "(%" + temp[6] + "," + temp[7] + ")"; }
	 * else var = "%" + var;
	 * 
	 * return var; }
	 */

	private Map<String, Long> readZ3Output(String fileName) {
		// TODO Auto-generated method stub
		InputStream fis;
		BufferedReader br;
		String line;
		try {
			fis = new FileInputStream(fileName);
			br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
			line = br.readLine();

			if (line != null && line.equals("sat")) {
				Program.getProgram().getLog().info("SAT Result:");
				Map<String, Long> result = new HashMap<String, Long>();
				while ((line = br.readLine()) != null) {
					String name = getNameZ3Result(line);
					long val = getValueZ3Result(line);
					// name = reverseName(name);
					result.put(name, val);
					Program.getProgram().getLog().info(name + " --> " + val + " = " + getValueZ3(line));
				}
				br.close();
				/*
				 * if (sr != null) { //EnvWeight ew = new EnvWeight();
				 * //ew.setAddress(targetIndirect);
				 * //ew.setInstruction(assemblyMap.get(targetIndirect));
				 * 
				 * Environment e = new Environment(symbolValueRegister.clone(),
				 * symbolValueRegisterPart.clone(),
				 * symbolValueMemoryOperand.clone(), symbolValueSegment.clone(),
				 * symbolFlag.clone(), symbolStack.clone(), program, system);
				 * 
				 * Environment e = new Environment(symbolValueRegister,
				 * symbolValueRegisterPart, symbolValueMemoryOperand,
				 * symbolValueSegment, symbolFlag, symbolStack, program,
				 * system);
				 * 
				 * //sr.getEnvWeight().addWeight(e); }
				 */
				return result;
			} else {
				System.out.println("UNSAT");
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		// Done with the file
		// System.out.println(result.substring(8));

		br = null;
		fis = null;
		return null;
	}

	public void setCFG(boolean b) {
		this.setCFG = b;
	}

	// PHONG: 20150501
	// ------------------------------------------------------------------
	void setSEHOther(BPState curState, String register) {
		// TODO Auto-generated method stub
		Program.getProgram().setTechnique("SetUpException");
		Program.getProgram().setDetailTechnique("SetUpException:" + curState.getLocation() + " ");
		System.out.println("Set Up Other Exception: " + curState.getLocation());
		Environment env = curState.getEnvironement();

		long register_value = ((LongValue) env.getRegister().getRegisterValue(register)).getValue();
		env.getSystem()
				.getSEHHandler()
				.getStart()
				.setNextSEHRecord(((LongValue) env.getMemory().getDoubleWordMemoryValue(register_value)).getValue(),
						register_value);
		env.getSystem()
				.getSEHHandler()
				.getStart()
				.setSEHHandler(((LongValue) env.getMemory().getDoubleWordMemoryValue(register_value + 4)).getValue(),
						register_value + 4);
		env.getSystem().getSEHHandler().setSEHReady(true);
	}

	// -----------------------------------------------------------------------------------------

	void setSEH(BPState curState) {
		// TODO Auto-generated method stub
		Program.getProgram().setTechnique("SetUpException");
		Program.getProgram().setDetailTechnique("SetUpException:" + curState.getLocation() + " ");
		System.out.println("Set Up Exception: " + curState.getLocation());
		Environment env = curState.getEnvironement();

		env.getSystem()
				.getSEHHandler()
				.getStart()
				.setNextSEHRecord(((LongValue) env.getStack().getValueStackFromIndex(0)).getValue(),
						((LongValue) env.getRegister().getRegisterValue("esp")).getValue());
		env.getSystem()
				.getSEHHandler()
				.getStart()
				.setSEHHandler(((LongValue) env.getStack().getValueStackFromIndex(4)).getValue(),
						((LongValue) env.getRegister().getRegisterValue("esp")).getValue() + 4);
		env.getSystem().getSEHHandler().setSEHReady(true);
	}

	/*
	 * private BPState specialCase(Instruction inst, BPState curState, Formulas
	 * l) { // TODO Auto-generated method stub if (inst instanceof
	 * X86RetInstruction) { if
	 * (Program.getProgram().getFileName().equals("hostname.exe") &&
	 * curState.getLocation().toString().contains("010013ac")) { AbsoluteAddress
	 * addr = new AbsoluteAddress(16781795); Instruction ins =
	 * Program.getProgram().getInstruction(addr, curState.getEnvironement());
	 * 
	 * curState.setInstruction(ins); curState.setLocation(addr);
	 * 
	 * return curState; } else if (Program.getProgram().getFileName()
	 * .equals("hostname.exe") &&
	 * curState.getLocation().toString().contains("01001472")) { AbsoluteAddress
	 * addr = new AbsoluteAddress(16781691); Instruction ins =
	 * Program.getProgram().getInstruction(addr, curState.getEnvironement());
	 * 
	 * curState.setInstruction(ins); curState.setLocation(addr);
	 * 
	 * return curState; } }
	 * 
	 * return null; }
	 */
	/*
	 * BPState specialProcessSEH(BPState curState) { // TODO Auto-generated
	 * method stub // PHONG Environment env = curState.getEnvironement();
	 * env.getRegister().mov("ebp", "esp"); env.getRegister().add("ebp", new
	 * LongValue(20)); long ebp_addr = ((LongValue)
	 * env.getRegister().getRegisterValue("ebp")) .getValue(); long
	 * exception_took_place = ((LongValue) env.getMemory()
	 * .getDoubleWordMemoryValue(ebp_addr)).getValue();
	 * 
	 * AbsoluteAddress nextAddr = new AbsoluteAddress(exception_took_place);
	 * Instruction nextIns = Program.getProgram() .getInstruction(nextAddr,
	 * env);
	 * 
	 * // Restore stack long seh_address =
	 * curState.getEnvironement().getSystem()
	 * .getSEHHandler().getStart().getSehHandler();
	 * 
	 * // Stack pointer while (true) { long top_stack_address = ((LongValue)
	 * env.getRegister() .getRegisterValue("esp")).getValue(); Value t =
	 * env.getMemory().getDoubleWordMemoryValue( top_stack_address);
	 * 
	 * if (t instanceof LongValue) { long top_stack_value = ((LongValue)
	 * t).getValue(); env.getStack().pop(); if (seh_address == top_stack_value
	 * || env.getStack().isEmpty()) break; }
	 * 
	 * if (env.getStack().isEmpty()) break; env.getRegister().add("esp", new
	 * LongValue(4)); }
	 * 
	 * curState.setLocation(nextAddr); curState.setInstruction(nextIns);
	 * 
	 * return curState; }
	 */
	// PHONG - 20150422
	public BPState specialProcessSEH(BPState curState) {
		// Restore
		long context_record_ptr = ((LongValue) curState.getEnvironement().getStack().getValueStackFromIndex(0x8))
				.getValue();
		curState.getEnvironement().getRegister().mov("esp", context_record_ptr);
		Value edi_value = curState.getEnvironement().getStack().getValueStackFromIndex(0x9c);
		Value esi_value = curState.getEnvironement().getStack().getValueStackFromIndex(0xa0);
		Value ebx_value = curState.getEnvironement().getStack().getValueStackFromIndex(0xa4);
		Value edx_value = curState.getEnvironement().getStack().getValueStackFromIndex(0xa8);
		Value ecx_value = curState.getEnvironement().getStack().getValueStackFromIndex(0xac);
		Value eax_value = curState.getEnvironement().getStack().getValueStackFromIndex(0xb0);
		Value ebp_value = curState.getEnvironement().getStack().getValueStackFromIndex(0xb4);
		// New location
		Value eip_value = curState.getEnvironement().getStack().getValueStackFromIndex(0xb8);
		Value efl_value = curState.getEnvironement().getStack().getValueStackFromIndex(0xc0);

		if (efl_value != null && efl_value instanceof LongValue)
			curState.getEnvironement().getFlag().setflags(((LongValue) efl_value).getValue());

		Value esp_value = curState.getEnvironement().getStack().getValueStackFromIndex(0xc4);

		curState.getEnvironement().getRegister().mov("edi", edi_value);
		curState.getEnvironement().getRegister().mov("esi", esi_value);
		curState.getEnvironement().getRegister().mov("ebx", ebx_value);
		curState.getEnvironement().getRegister().mov("edx", edx_value);
		curState.getEnvironement().getRegister().mov("ecx", ecx_value);
		curState.getEnvironement().getRegister().mov("eax", eax_value);
		curState.getEnvironement().getRegister().mov("ebp", ebp_value);
		curState.getEnvironement().getRegister().mov("esp", esp_value);

		AbsoluteAddress nextAddr = new AbsoluteAddress(0x00000000);
		if (eip_value != null && eip_value instanceof LongValue)
			nextAddr = new AbsoluteAddress(((LongValue) eip_value).getValue());
		Instruction nextIns = Program.getProgram().getInstruction(nextAddr, curState.getEnvironement());
		curState.setLocation(nextAddr);
		curState.setInstruction(nextIns);

		return curState;
	}

	private void writeZ3Input(String fileName, Formulas formulaList) {
		FileWriter writer;
		// System.out.println("Z3 Input File: ");
		try {
			// writer = new FileWriter(fileName, "UTF-8");
			/*
			 * File f = new File(fileName); if (!f.exists()) return;
			 */
			writer = new FileWriter(new File(fileName));

			try {
				writer.write("(benchmark program\n");
				writer.write(" :status sat\n");
				writer.write(" :logic QF_LIA\n");
				writer.write(":extrafuns (" + formulaList.getVariableZ3() + ")\n");
				Program.getProgram().getLog().info(":extrafuns (" + formulaList.getVariableZ3() + ")");
				// Program.getProgram().getLog().info(":formula" +
				// formulaList.toStringPrefix());
				if (formulaList.isEmpty()) {
					writer.write(":formula (true)\n");
					Program.getProgram().getLog().info(":formula (true)");
				} else {
					writer.write(":formula" + formulaList.toStringPrefix() + "\n");
					Program.getProgram().getLog().info(":formula" + formulaList.toStringPrefix());
				}
				writer.write(")\n");
				Program.getProgram().getLog().info(")");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// System.out.println("Close Stream");
				writer.close();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getNewState(X86CondJmpInstruction inst, BPPath path, List<BPPath> pathList) {
		// TODO Auto-generated method stub
		Formulas p1 = path.getPathCondition().clone();
		Formulas p2 = path.getPathCondition().clone();

		// Special Case
		if (inst.getName().startsWith("loop")) {
			path.getCurrentState().getEnvironement().getRegister().sub("%ecx", new LongValue(1));
		}

		if (new X86ConditionalJumpInterpreter().execute(inst, p1, path, true, this)) {
			if (new X86ConditionalJumpInterpreter().execute(inst, p2, path, false, this)) {
				BPPath newP = path.clone();
				newP.setPathCondition(p2);

				if (path.getLoopHandle().isCheck()
				// && path.getLoopHandle().isStop()
				)
					path.getLoopHandle().setStop(
							newP.getLoopHandle().checkFormulas(inst.getName(), newP.getCurrentState()));

				this.generateNextInstruction(inst, newP, pathList, false);

				pathList.add(newP);
				newP.getCurrentState().setFeasiblePath(true);

				LoopAlgorithm.getInstance().halt(newP, this);
			}

			if (path.getLoopHandle().isCheck())
				path.getLoopHandle()
						.setStop(path.getLoopHandle().checkFormulas(inst.getName(), path.getCurrentState()));

			this.generateNextInstruction(inst, path, pathList, true);
			path.setPathCondition(p1);
			path.getCurrentState().setFeasiblePath(true);

			LoopAlgorithm.getInstance().halt(path, this);
		} else {
			if (new X86ConditionalJumpInterpreter().execute(inst, p2, path, false, this)) {
				path.setPathCondition(p2);
				if (path.getLoopHandle().isCheck()
				// && path.getLoopHandle().isStop()
				)
					path.getLoopHandle().setStop(
							path.getLoopHandle().checkFormulas(inst.getName(), path.getCurrentState()));

				this.generateNextInstruction(inst, path, pathList, false);
				path.getCurrentState().setFeasiblePath(true);

				LoopAlgorithm.getInstance().halt(path, this);
			} else
				path.getCurrentState().setFeasiblePath(false);
		}
	}

	public Value getValueOperand(Operand src, Environment env, Instruction ins) {
		Value s = null;
		if (src == null) {
			s = new LongValue(1);
		} else if (src.getClass().getSimpleName().equals("Immediate")) {
			long y = (long) Convert.convetUnsignedValue(((Immediate) src).getNumber().intValue(), getBitCount(ins));
			s = new LongValue(y);
		} else if (src.getClass().getSimpleName().equals("X86Register")
				|| src.getClass().getSimpleName().equals("X86RegisterPart")
				|| src.getClass().getSimpleName().equals("X86SegmentRegister")) {
			s = env.getRegister().getRegisterValue(src.toString());
		} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
			s = env.getMemory().getMemoryValue((X86MemoryOperand) src, ins);
		}

		return s;
	}

	public void setValueOperand(Operand src, Value val, Environment env, Instruction inst) {
		if (src == null) {
			return;
		} else if (src.getClass().getSimpleName().equals("X86Register")
				|| src.getClass().getSimpleName().equals("X86RegisterPart")
				|| src.getClass().getSimpleName().equals("X86SegmentRegister")) {
			env.getRegister().setRegisterValue(src.toString(), val);
		} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
			env.getMemory().setMemoryValue((X86MemoryOperand) src, val, inst);
		}

	}

	public int getBitCountOprand(Operand op) {
		// TODO Auto-generated method stub
		if (op instanceof X86MemoryOperand) 
			return ((X86MemoryOperand)op).getDataType().bits();
		else if (op instanceof X86RegisterPart) {			
			return ((X86RegisterPart)op).getLength();
		} else if (op instanceof X86SegmentRegister) {			
			return 32;
		} else if (op instanceof X86Register) {			
			return 32;
		}
		
		return 0;
	}
}
