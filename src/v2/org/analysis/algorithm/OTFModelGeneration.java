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
import v2.org.analysis.value.Formulas;
import java.util.ArrayList;
import java.util.List;

public class OTFModelGeneration implements Algorithm {

	// private static final Logger logger =
	// Logger.getLogger(CPAAlgorithm.class);
	private static long maxTimeProgam = 2500000;
	private static long maxTimePath = 1500000;
	// For Debug
	private int num = 1, loopCount = 1;;
	private boolean isCompareOlly = true, isChecked = false, isRestored = false;
	private long count = 1;
	private AbsoluteAddress checkedAddr = new AbsoluteAddress(0);
	private AbsoluteAddress endAddr = new AbsoluteAddress(0);
	private String fileName = "out_Email-Worm.Win32.Apbost.c_";
	private FileProcess compareOllyResult = null;
	private OllyCompare ollyCompare = null;
	private FileProcess fileState = new FileProcess("data/stateValue.txt");

	private final Program program;

	public OTFModelGeneration(Program program) {
		super();
		this.program = program;
	}

	@Override
	public void run() {
		// --------------------------
		FileProcess bkFile = new FileProcess("data/restore.txt");;
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
		AbsoluteAddress location = Program.getProgram().getEntryPoint();
		Instruction inst = Program.getProgram().getInstruction(location, env);
		List<BPPath> pathList = new ArrayList<BPPath>();
		BPVertex startNode = null;
		BPState curState = null;
		BPPath path = null;

		if (isRestored) {
			bkFile = new FileProcess("data/restore.txt");
			pathList = restoreState();
		} else {
			startNode = new BPVertex(location, inst);
			startNode.setType(0);
			cfg.insertVertex(startNode);
			curState = new BPState(env, location, inst);
			path = new BPPath(curState, new PathList(), new Formulas());
			path.setCurrentState(curState);
			pathList.add(path);
		}

		while (!pathList.isEmpty()) {
			/*if (System.currentTimeMillis() - overallStartTime > maxTimeProgram) {
				System.out.println("Stop Program after " + maxTimeProgram);
				overallStartTime = System.currentTimeMillis();
				// break;
			}*/

			path = pathList.remove(pathList.size() - 1);
			curState = path.getCurrentState();
			// PHONG: 20150506 - Update TIB
			// --------------------------------------
			TIB.updateTIB(curState);
			// --------------------------------------------------------------------
			//long overallStartTimePath = System.currentTimeMillis();
			while (true) {
				long overallEndTimeTemp = System.currentTimeMillis();
				// Output file each 60s
				if (overallEndTimeTemp - overallStartTemp > 120000) {

					// Stop running one paths after maxTimePath
					/*if (overallEndTimeTemp - overallStartTimePath > maxTimePath) {
						Program.getProgram().getLog()
								.info("Stop Path after " + maxTimePath + " at " + curState.getLocation());
						// break;
					}*/

					backupState(curState, fileState, bkFile);					
					overallStartTemp = overallEndTimeTemp;
				}

				if (path.isStop()) {
					break;
				}

				inst = curState.getInstruction();
				location = curState.getLocation();
				//debugProgram(location, curState);
				//compareOlly(curState);

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

	private List<BPPath> restoreState() {
		// TODO Auto-generated method stub
		return null;
	}

	private void backupState(BPState curState, FileProcess fileState, FileProcess bkFile) {
		// TODO Auto-generated method stub
		bkFile.clearContentFile();
		
		String result = "Address=" + curState.getLocation().toString() + "\n";
		result += "Register: " + curState.getEnvironement().getRegister() + "\n";
		result += "Flag: " + curState.getEnvironement().getFlag() + "\n";
		result += "Stack: " + ((StackV2)curState.getEnvironement().getStack()).toString() + "\n";
		result += "SEH: " + curState.getEnvironement().getSystem().getSEHHandler().toString() + "\n";
		result += "*************************************************************";

		//bkFile.appendFile(result);
		fileState.appendFile(result);

		program.generageCFG("asm/cfg/" + program.getFileName() + "_test");
		program.getResultFileTemp().appendInLine(
				program.getDetailTechnique() + " Nodes:" + program.getBPCFG().getVertexCount() + " Edges:"
						+ program.getBPCFG().getEdgeCount() + " ");
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
				long memoryStartAddr = 0x429000;
				long memoryEndAddr = 0x429028;
				long stackIndex = 0x8c;
				
				ollyCompare = new OllyCompare("asm/olly/" + fileName + "" + num + ".txt", memoryStartAddr,
						memoryEndAddr, stackIndex);
				//ollyCompare = new OllyCompare("asm/olly/" + fileName + ".txt", memoryStartAddr,
				//		memoryEndAddr, stackIndex);
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

	private void debugProgram(AbsoluteAddress location, BPState curState) {
		// TODO Auto-generated method stub
		String fileName = Program.getProgram().getFileName();
		if (location != null
				&& (location.toString().contains("FFFFFFFFF")
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
						|| (fileName.equals("api_test_v2.3_lvl1.exe") && (
						location.toString().contains("01")
						//|| location.toString().contains("4329b0")	
						//|| location.toString().contains("407ac6") // RDTSC
						//|| location.toString().contains("40797e") // RDTSC
						//|| location.toString().contains("4360e9") // RDTSC
						//|| location.toString().contains("436107") // RDTSC
						//|| location.toString().contains("43610f") // RDTSC
						//|| location.toString().contains("432220") // RDTSC
						//|| location.toString().contains("4094da") // RDTSC
						//|| location.toString().contains("4094df") // RDTSC
						|| location.toString().contains("41c896") // SEH
						|| location.toString().contains("4199c3") // Target
						|| location.toString().contains("4197c7") // Wrong 
						))
						// ******************************************
						// api_test_aspack.exe
						|| (fileName.equals("api_test_aspack.exe") && (location.toString().contains("4043c2")
						// || location.toString().contains("408184")
						))
						// ******************************************
						// api_test_aspack.exe
						|| (fileName.equals("Virus.Win32.Cabanas.2999") 
						&& (location.toString().contains("40497b")
						// || location.toString().contains("408184")
						))
						// ******************************************
						// Email-Worm.Win32.Apbost.c
						|| (fileName.equals("Email-Worm.Win32.Apbost.c") 
						&& (location.toString().contains("4046e8")
						// || location.toString().contains("408184")
						))
				// ******************************************
				// Virus.Win32.Aztec.01
				|| (fileName.equals("Virus.Win32.Aztec.01") && (location.toString().contains("40118d")
				// || location.toString().contains("401317")
				)))) {
			System.out.println("Debug at:" + location.toString());		
			String result = "Address=" + curState.getLocation().toString() + "\n";
			result += "Register: " + curState.getEnvironement().getRegister() + "\n";
			result += "Flag: " + curState.getEnvironement().getFlag() + "\n";
			result += "Memory: " + curState.getEnvironement().getMemory() + "\n";
			result += "SEH: " + curState.getEnvironement().getSystem().getSEHHandler().toString() + "\n";
			result += "*************************************************************";
			fileState.appendFile(result);
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
