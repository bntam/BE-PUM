/**
 * 
 */
package v2.org.analysis.algorithm;

/**
 * @author NMHai
 * 
 * The main algorithm of On-the-fly Model Generation
 * 
 */

import org.jakstab.Algorithm;
import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86CondJmpInstruction;

import v2.org.analysis.cfg.BPCFG;
import v2.org.analysis.cfg.BPVertex;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.processthread.TIB;
import v2.org.analysis.olly.OllyCompare;
import v2.org.analysis.path.BPPath;
import v2.org.analysis.path.BPState;
import v2.org.analysis.path.PathList;
import v2.org.analysis.environment.StackV2;
import v2.org.analysis.statistics.FileProcess;
import v2.org.analysis.transition_rule.X86TransitionRule;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.Formulas;
import v2.org.analysis.value.LongValue;

import java.util.ArrayList;
import java.util.List;

public class OTFModelGeneration implements Algorithm {

	// private static final Logger logger =
	// Logger.getLogger(CPAAlgorithm.class);
	// private static long maxTimeProgam = 2500000;
	// private static long maxTimePath = 1500000;
	private static long bkTime = 108000000;
	// For Debug
	private int num = 1, loopCount = 1;;
	private boolean isCompareOlly = false, isChecked = false, isRestored = true;
	private long count = 1;
	private AbsoluteAddress checkedAddr = new AbsoluteAddress(0);
	private AbsoluteAddress endAddr = new AbsoluteAddress(0);
	private String fileName = "";
	private FileProcess compareOllyResult = null;
	private OllyCompare ollyCompare = null;

	private final Program program;

	public OTFModelGeneration(Program program) {
		super();
		this.program = program;
	}

	@Override
	public void run() {
		// --------------------------
		FileProcess fileState = new FileProcess("data/stateValue.txt");
		FileProcess bkFile = new FileProcess("data/restore.txt");
		fileName = "out_" + Program.getProgram().getFileName() + "_";

		fileState.clearContentFile();
		long overallStartTime = System.currentTimeMillis();
		long overallStartTemp = overallStartTime;
		// BE-PUM algorithm
		System.out.println("Starting On-the-fly Model Generation algorithm.");
		program.getResultFileTemp().appendInLine('\n' + program.getFileName() + '\t');
		// Set up initial context
		X86TransitionRule rule = new X86TransitionRule();
		BPCFG cfg = Program.getProgram().getBPCFG();
		Environment env = new Environment();
		env.getMemory().resetImportTable(program);
		AbsoluteAddress location = Program.getProgram().getEntryPoint();
		Instruction inst = Program.getProgram().getInstruction(location, env);
		List<BPPath> pathList = new ArrayList<BPPath>();
		BPVertex startNode = null;
		BPState curState = null;
		BPPath path = null;
		startNode = new BPVertex(location, inst);
		startNode.setType(0);
		cfg.insertVertex(startNode);
		curState = new BPState(env, location, inst);
		path = new BPPath(curState, new PathList(), new Formulas());
		path.setCurrentState(curState);
		pathList.add(path);

		if (Program.getProgram().getFileName().equals("api_test_v2.3_lvl1.exe") 
				&& isRestored) {
			System.out.println("Restore State from File.");
			bkFile = new FileProcess("data/restore.txt");
			pathList = restoreState(bkFile);
			// bkFile.clearContentFile();
			System.out.println("Finished!");
		}

		while (!pathList.isEmpty()) {
			/*
			 * if (System.currentTimeMillis() - overallStartTime >
			 * maxTimeProgram) { System.out.println("Stop Program after " +
			 * maxTimeProgram); overallStartTime = System.currentTimeMillis();
			 * // break; }
			 */

			path = pathList.remove(pathList.size() - 1);
			curState = path.getCurrentState();
			// long overallStartTimePath = System.currentTimeMillis();
			while (true) {
				long overallEndTimeTemp = System.currentTimeMillis();
				// Output file each 60s
				if (overallEndTimeTemp - overallStartTemp > 120000) {

					// Stop running one paths after maxTimePath
					/*
					 * if (overallEndTimeTemp - overallStartTimePath >
					 * maxTimePath) { Program.getProgram().getLog()
					 * .info("Stop Path after " + maxTimePath + " at " +
					 * curState.getLocation()); // break; }
					 */

					backupState(curState, fileState);
					overallStartTemp = overallEndTimeTemp;
				}

				if (overallEndTimeTemp - overallStartTime > bkTime) {

					// Stop running one paths after maxTimePath
					/*
					 * if (overallEndTimeTemp - overallStartTimePath >
					 * maxTimePath) { Program.getProgram().getLog()
					 * .info("Stop Path after " + maxTimePath + " at " +
					 * curState.getLocation()); // break; }
					 */

					backupStateAll(curState, bkFile);
					overallStartTime = overallEndTimeTemp;
				}

				if (path.isStop()) {
					break;
				}

				inst = curState.getInstruction();
				location = curState.getLocation();
				
				//if (location == null || location.toString().contains("4202d0"))
				//	System.out.println("Debug " + location);
								
				//debugProgram(location, curState, fileState, bkFile);
				compareOlly(curState);
				
				// PHONG: 20150506 - Update TIB
				// --------------------------------------
				TIB.updateTIB(curState);
				TIB.updateChecking(curState);
				// --------------------------------------

				if (inst == null || location == null)
					break;
				path.addTrace(curState.getLocation());

				if (inst instanceof X86CondJmpInstruction) {
					rule.getNewState((X86CondJmpInstruction) inst, path, pathList);
					if (!curState.checkFeasiblePath()) {
						path.destroy();
						break;
					}
				} else {
					rule.getNewState(path, pathList, true);
				}
			}
		}
	}

	private void backupStateAll(BPState curState, FileProcess bkFile) {
		// TODO Auto-generated method stub
		String result = "Address=" + curState.getLocation().toString() + "\n";
		result += "Register:" + curState.getEnvironement().getRegister() + "\n";
		result += "Flag:" + curState.getEnvironement().getFlag() + "\n";
		result += "Memory:" + curState.getEnvironement().getMemory() + "\n";
		result += "SEH:" + curState.getEnvironement().getSystem().getSEHHandler().toString() + "\n";
		result += "*************************************************************";

		bkFile.appendFile(result);
	}

	private List<BPPath> restoreState(FileProcess bkFile) {
		// TODO Auto-generated method stub
		Environment env = new Environment();
		getEnvironment(env, bkFile);
		AbsoluteAddress location = getContinuosPoint(bkFile);
		Instruction inst = Program.getProgram().getInstruction(location, env);
		List<BPPath> pathList = new ArrayList<BPPath>();
		// BPVertex startNode = null;
		// BPState curState = null;
		// BPPath path = null;
		// startNode = new BPVertex(location, inst);
		// startNode.setType(0);
		// cfg.insertVertex(startNode);
		BPState curState = new BPState(env, location, inst);
		BPPath path = new BPPath(curState, new PathList(), new Formulas());
		path.setCurrentState(curState);

		BPCFG cfg = Program.getProgram().getBPCFG();
		BPVertex startNode = null;
		startNode = new BPVertex(location, inst);
		startNode.setType(0);
		cfg.removeVertex(0);
		cfg.insertVertex(startNode);
		pathList.add(path);

		return pathList;
	}

	private AbsoluteAddress getContinuosPoint(FileProcess file) {
		// TODO Auto-generated method stub
		int t = 0;
		while (true) {
			String temp = file.getLineAt(t);

			if (temp == null || temp == "")
				return null;

			if (temp.contains("Address")) {
				temp = temp.substring(temp.indexOf("x") + 1, temp.length());
				return new AbsoluteAddress(Long.parseLong(temp, 16));
			}
			t++;
		}
	}

	private void getEnvironment(Environment env, FileProcess file) {
		// TODO Auto-generated method stub
		int t = 0;
		while (true) {
			String temp = file.getLineAt(t);

			if (temp == null || temp == "")
				return;
			else if (temp.contains("Register")) {
				temp = temp.substring(temp.indexOf(":") + 1, temp.length());
				String[] reg = temp.split(",");
				for (int i = 0; i < reg.length; i++) {
					reg[i] = reg[i].replace(" ", "");
					String r[] = reg[i].split("=");

					env.getRegister().setRegisterValue(r[0], new LongValue(Long.parseLong(r[1], 16)));
				}

			} else if (temp.contains("Flag")) {
				temp = temp.substring(temp.indexOf(":") + 1, temp.length());
				String[] reg = temp.split(",");
				for (int i = 0; i < reg.length; i++) {
					reg[i] = reg[i].replace(" ", "");
					String r[] = reg[i].split("=");

					if (r[1].toLowerCase().contains("true"))
						env.getFlag().setFlagValue(r[0], new BooleanValue(true));
					else
						env.getFlag().setFlagValue(r[0], new BooleanValue(false));
				}
			} else if (temp.contains("Memory")) {
				temp = temp.substring(temp.indexOf(":") + 1, temp.length());
				String[] reg = temp.split(",");
				for (int i = 0; i < reg.length; i++) {
					reg[i] = reg[i].replace(" ", "");
					String r[] = reg[i].split("=");
					String addr = r[0].replace("0x", "");
					long x = Long.parseLong(addr, 16);
					byte y = (byte) Long.parseLong(reduce(r[1], 8), 16);
					env.getMemory().setByteMemoryValue(x, new LongValue(y));
				}
			}
			t++;
		}
	}

	private String reduce(String str, int i) {
		// TODO Auto-generated method stub
		String ret = str;
		if (i == 8)
			if (str.length() > 2)
				ret = str.substring(str.length()-2);
		
		return ret;
	}

	private void backupState(BPState curState, FileProcess fileState) {
		// TODO Auto-generated method stub
		String result = "Address=" + curState.getLocation().toString() + "\n";
		result += "Register: " + curState.getEnvironement().getRegister() + "\n";
		result += "Flag: " + curState.getEnvironement().getFlag() + "\n";
		result += "Stack: " + ((StackV2) curState.getEnvironement().getStack()).toString() + "\n";
		result += "SEH: " + curState.getEnvironement().getSystem().getSEHHandler().toString() + "\n";
		result += "*************************************************************";

		// bkFile.appendFile(result);
		fileState.appendFile(result);

		program.generageCFG("/asm/cfg/" + program.getFileName() + "_test");
		program.getResultFileTemp().appendInLine(
				program.getDetailTechnique() + " Nodes:" + program.getBPCFG().getVertexCount() + " Edges:"
						+ program.getBPCFG().getEdgeCount() + " ");
		//System.out.println();
	}

	private void compareOlly(BPState state) {
		// TODO Auto-generated method stub
		// -------------------------------------------------------------------
		// OLLY DEBUG HERE
		// checkedAddr = new AbsoluteAddress(0x40818C);
		// endAddr = new AbsoluteAddress(0x4085B2);
		if (isCompareOlly) {
			AbsoluteAddress location = state.getLocation();
			Environment env = state.getEnvironement();
			if (ollyCompare == null) {
				long memoryStartAddr = 0x404098;
				long memoryEndAddr = 0x4040A0;
				long stackIndex = 0x8c;

				ollyCompare = new OllyCompare("asm/olly/" + fileName + "" + num + ".txt", memoryStartAddr,
						memoryEndAddr, stackIndex);
				// ollyCompare = new OllyCompare("asm/olly/" + fileName +
				// ".txt", memoryStartAddr,
				// memoryEndAddr, stackIndex);
				ollyCompare.importOllyData(checkedAddr, endAddr);
			}

			if (compareOllyResult == null) {
				compareOllyResult = new FileProcess("data/compareWithOlly_" + fileName + "" + num + ".txt");
				compareOllyResult.clearContentFile();
			}

			if (location != null && location.getValue() == checkedAddr.getValue()) {
				isChecked = true;
			}

			if (isChecked & location != null
					&& (location.getValue() != endAddr.getValue() || loopCount != ollyCompare.getLoopCount())) {
				// System.out.println("Loop = " + count + " , Address = " +
				// location.toString() + ":");
				compareOllyResult.appendFile("Loop = " + count + " , Address = " + location.toString() + ":");
				// COMPARE HERE
				String result = ollyCompare.compareBEPUM(env, count, location);
				count = ollyCompare.getNextCheck();
				// if (result.contains("Unequal")) {
				// System.out.println("Bug");
				// }
				compareOllyResult.appendFile(result);
				// System.out.println("*************************************************************");
				compareOllyResult.appendFile("*************************************************************");
				loopCount++;
			}

			if (isChecked && location != null && location.getValue() == endAddr.getValue()
					&& loopCount == ollyCompare.getLoopCount()) {
				System.out.println("Stop Check: " + fileName + "" + num + ".txt");
				ollyCompare = null;
				loopCount = 1;
				compareOllyResult = null;
				num++;
				isChecked = false;
				count = 1;

				if (num >= 3) {
					isCompareOlly = false;
					System.out.println("Finish Checking");
				}
			}
		}
	}

	private void debugProgram(AbsoluteAddress location, BPState curState, FileProcess fileState, FileProcess bkFile) {
		// TODO Auto-generated method stub
		String fileName = Program.getProgram().getFileName();
		if (location != null && (location.toString().contains("FFFFFFFFF")
		// ******************************************
		// Virus.Win32.HLLO.Momac.a
				|| (fileName.equals("Virus.Win32.HLLO.Momac.a") && (location.toString().contains("40130c")))
				// ******************************************
				// Email-Worm.Win32.Atak.e
				|| (fileName.equals("Email-Worm.Win32.Atak.e") && (location.toString().contains("404cf0")))
				// ******************************************
				// Virus.Win32.ZMist
				|| (fileName.equals("Virus.Win32.ZMist") && (location.toString().contains("402d01")))
				// ******************************************
				// api_test_pespin.exe
				|| (fileName.equals("api_test_pespin.exe") && (location.toString().contains("40669e")))
				// ******************************************
				// api_test_yoda.exe
				|| (fileName.equals("api_test_yoda.exe") && (location.toString().contains("4045fb")))
				// ******************************************
				// api_test_vmprotect.exe
				|| (fileName.equals("api_test_vmprotect.exe") && (
				// location.toString().contains("4c11b0")
				location.toString().contains("4b9da5")))
				// ******************************************
				// api_test_yc1.2.exe
				|| (fileName.equals("api_test_v2.3_lvl1.exe") && 
				(location.toString().contains("00000000001")
				|| location.toString().contains("424a41") // API GetLocalTime
				|| location.toString().contains("436daf") // API CreateFile
				|| location.toString().contains("436ef8") // API CreateFile				
				|| location.toString().contains("436bc7") // RET
				|| location.toString().contains("437b16") // After STI
				|| location.toString().contains("43ce7c") // After STI 
				|| location.toString().contains("43f722") // API GetVersionExA
				
				|| location.toString().contains("44228a") // Target
				))
				// ******************************************
				// api_test_aspack.exe
				|| (fileName.equals("api_test_aspack.exe") && (location.toString().contains("4043c2")
				// || location.toString().contains("408184")
				))
				// ******************************************
				// api_test_aspack.exe
				|| (fileName.equals("Virus.Win32.Cabanas.2999") && (location.toString().contains("40497b")
				// || location.toString().contains("408184")
				))
				// ******************************************
				// Email-Worm.Win32.Apbost.c
				|| (fileName.equals("Email-Worm.Win32.Apbost.c") && (location.toString().contains("4046e8")
				// || location.toString().contains("408184")
				))
		// ******************************************
		// Virus.Win32.Aztec.01
				|| (fileName.equals("Virus.Win32.Aztec.01") && 
				(location.toString().contains("40134e")
				|| location.toString().contains("401312")
				|| location.toString().contains("40106c")
				)))) {
			System.out.println("Debug at:" + location.toString());
			backupState(curState, fileState);
			backupStateAll(curState, bkFile);
			program.generageCFG(program.getAbsolutePathFile() + "_test");
		}
		/*
		 * if (location != null && location.toString().contains("0040481b") &&
		 * Program.getProgram().getFileName()
		 * .equals("Virus.Win32.Cabanas.2999")) { // Value ecx =
		 * env.getRegister().getRegisterValue("ecx"); String s1 =
		 * curState.getEnvironement().getMemory() .getText(4215362, 650);
		 * System.out.println("Decrypted String: " + s1); }
		 */
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
	}

	public boolean isCompleted() {
		// TODO Auto-generated method stub
		return program.getBPCFG().isCompleted();
	}

	public boolean isSound() {
		// TODO Auto-generated method stub
		return true;
	}
}
