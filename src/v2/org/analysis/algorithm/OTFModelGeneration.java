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
import v2.org.analysis.environment.StackV2;
import v2.org.analysis.olly.OllyCompare;
import v2.org.analysis.path.BPPath;
import v2.org.analysis.path.BPState;
import v2.org.analysis.path.PathList;
import v2.org.analysis.statistics.FileProcess;
import v2.org.analysis.transition_rule.X86TransitionRule;
import v2.org.analysis.value.Formulas;

import java.util.ArrayList;
import java.util.List;

public class OTFModelGeneration implements Algorithm {

	// private static final Logger logger =
	// Logger.getLogger(CPAAlgorithm.class);
	private static long maxTimeProgram = 2500000;
	private static long maxTimePath = 1500000;
	private int num = 0;

	private final Program program;

	public OTFModelGeneration(Program program) {
		super();
		this.program = program;
	}

	@Override
	public void run() {
		// FOR DEBUGGING
		/*
		 * int number_loop = 100; long startAddr = 0x0018fe94; long endaddr =
		 * 0x0018ff90; OllyCompare ollyc = new OllyCompare("asm/packer/out.txt",
		 * startAddr, number_loop); OllyLoop[] ollyloop = ollyc.get_ollyloop();
		 * ;
		 */
		// FOR DEBUG ONLY
		/*boolean debug = false;
		FileProcess file = new FileProcess("data/compareWithOlly.txt");
		file.clearContentFile();
		long memoryStartAddr = 0x12F660;
		long memoryEndAddr = 0x403100;
		long stackIndex = 0x12F724;
		// int numChecked = ;
		AbsoluteAddress checkedAddr = new AbsoluteAddress(0x4B9DA5);
		AbsoluteAddress endAddr = new AbsoluteAddress(0x4C1843);
		boolean check = false;
		OllyCompare ollyCompare = new OllyCompare("asm/olly/out_vmprotect_3.txt",
				memoryStartAddr, memoryEndAddr, stackIndex);
		ollyCompare.read_olly();*/
		// --------------------------
		int count = 1;
		long overallStartTime = System.currentTimeMillis();
		long overallStartTemp = overallStartTime;
		// BE-PUM algorithm
		System.out.println("Starting On-the-fly Model Generation algorithm.");
		program.getResultFileTemp().appendInLine(
				'\n' + program.getFileName() + '\t');
		// Set up initial context
		X86TransitionRule rule = new X86TransitionRule();
		BPCFG cfg = Program.getProgram().getBPCFG();
		Environment env = new Environment();
		AbsoluteAddress location = Program.getProgram().getEntryPoint();
		Instruction inst = Program.getProgram().getInstruction(location, env);

		BPVertex startNode = new BPVertex(location, inst);
		startNode.setType(0);
		cfg.insertVertex(startNode);
		BPState curState = new BPState(env, location, inst);
		BPPath path = new BPPath(curState, new PathList(), new Formulas());
		path.setCurrentState(curState);
		// path.addTrace(curState.getLocation());
		// List<BPPath> processedPathList = new ArrayList<BPPath>();
		List<BPPath> pathList = new ArrayList<BPPath>();
		pathList.add(path);

		while (!pathList.isEmpty()) {
			if (System.currentTimeMillis() - overallStartTime > maxTimeProgram) {
				System.out.println("Stop Program after " + maxTimeProgram);
				overallStartTime = System.currentTimeMillis();
				// break;
			}

			path = pathList.remove(pathList.size() - 1);
			curState = path.getCurrentState();
			long overallStartTimePath = System.currentTimeMillis();
			while (true) {
				long overallEndTimeTemp = System.currentTimeMillis();
				// Output file each 60s
				if (overallEndTimeTemp - overallStartTemp > 30000) {

					// Stop running one paths after maxTimePath
					if (overallEndTimeTemp - overallStartTimePath > maxTimePath) {
						System.out.println("Stop Path after " + maxTimePath
								+ " at " + curState.getLocation());
						// break;
					}

					program.generageCFG("asm/cfg/" + program.getFileName()
							+ "_test");
					program.getResultFileTemp().appendInLine(
							program.getDetailTechnique() + " Nodes:"
									+ program.getBPCFG().getVertexCount()
									+ " Edges:"
									+ program.getBPCFG().getEdgeCount() + " ");
					overallStartTemp = overallEndTimeTemp;
				}

				if (path.isStop()) {
					break;
				}

				inst = curState.getInstruction();
				location = curState.getLocation();
				//debugProgram(location, curState);
				/*
				 * if (location.toString().contains("40100c")){ count = 2; }
				 * 
				 * if (location.toString().contains("40100e")){ count = 2; }
				 */

				// OLLY DEBUG HERE
				/*if (debug) {
					if (location != null
							&& location.getValue() == checkedAddr.getValue() && count == 0x67F1)
						check = true;
					
					if (count == 1697)
						System.out.println("Stop");

					if (check & location != null
							&& location.getValue() != endAddr.getValue()) {
						System.out.println("Loop = " + count + " , Address = "
								+ location.toString() + ":");
						file.appendFile("Loop = " + count + " , Address = "
								+ location.toString() + ":");
						// COMPARE HERE
						String result = ollyCompare.compareBEPUM(env, count,
								location);
						count = ollyCompare.getNextCheck();
						if (result.contains("Unequal")) {
							System.out.println("Bug");
						}
						file.appendFile(result);
						System.out
								.println("*************************************************************");
						file.appendFile("*************************************************************");
					}

					if (location != null
							&& location.getValue() == endAddr.getValue()
							&& check) {
						System.out.println("Stop Check");
						check = false;
					}
				}*/
				// ----------------------------------------------------------

				if (inst == null || location == null)
					break;
				path.addTrace(curState.getLocation());

				if (inst instanceof X86CondJmpInstruction) {
					rule.getNewState((X86CondJmpInstruction) inst, path,
							pathList);
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

	private void debugProgram(AbsoluteAddress location, BPState curState) {
		// TODO Auto-generated method stub
		String fileName = Program.getProgram().getFileName();
		if (location != null
				&& (location.toString().contains("FFFFFFFFF")
				// ******************************************
				// Virus.Win32.HLLO.Momac.a
						|| (fileName.equals("Virus.Win32.HLLO.Momac.a") && (location
								.toString().contains("40130c")))
						// ******************************************
						// Email-Worm.Win32.Atak.e
						|| (fileName.equals("Email-Worm.Win32.Atak.e") && (location
								.toString().contains("404cf0")))
						// ******************************************
						// Virus.Win32.ZMist
						|| (fileName.equals("Virus.Win32.ZMist") && (location
								.toString().contains("402d01")))
						// ******************************************
						// api_test_pespin.exe
						|| (fileName.equals("api_test_pespin.exe") && (location
								.toString().contains("40669e")))
						// ******************************************
						// api_test_yoda.exe
						|| (fileName.equals("api_test_yoda.exe") && (location
								.toString().contains("4045fb")))
						// ******************************************
						// api_test_vmprotect.exe
						|| (fileName.equals("api_test_vmprotect.exe") && (
						// location.toString().contains("4c11b0")
						location.toString().contains("4b9da5")))
						// ******************************************
						// api_test_yc1.2.exe
						|| (fileName.equals("api_test_yc1.2.exe") && (
						location.toString().contains("40473d") // done
						//|| location.toString().contains("404278") // done
						//|| location.toString().contains("404300")
						//|| location.toString().contains("404442")
						//|| location.toString().contains("40432e")
						//|| location.toString().contains("404325")
						//|| location.toString().contains("4042ef")
						//|| location.toString().contains("4042f5")
						//|| location.toString().contains("404318")
						//|| location.toString().contains("4042d6")// done
						//|| location.toString().contains("4042c9")// done
						//|| location.toString().contains("4042b5")// done
						//|| location.toString().contains("40429c")// done
						//|| location.toString().contains("40415b")//done
						))
				// ******************************************
				// Virus.Win32.Aztec.01
				|| (fileName.equals("Virus.Win32.Aztec.01") && (location
						.toString().contains("40118d")
						//|| location.toString().contains("401317")
						)))) {
			num++;
			System.out.println("Debug at:" + location.toString());
			// System.out.println("Loop: " + num + " Memory Operand Value: " +
			// curState.getEnvironement().getMemory().getDoubleWordMemoryValue(1638076)
			// );
			// System.out.println("Loop: " + num + " Memory Operand Value: " +
			// curState.getEnvironement().getMemory().getDoubleWordMemoryValue(1638268)
			// );
			System.out.println(" Register: "
					+ curState.getEnvironement().getRegister());
			System.out.println(" Flag: "
					+ curState.getEnvironement().getFlag());
			//System.out.println(" Stack: "
			//		+ ((StackV2) curState.getEnvironement().getStack())
			//				.getString());
			//System.out.println(" Memory: 0x4047E3 = "
			//		+ curState.getEnvironement().getMemory().getDoubleWordMemoryValue(0x4047e3));
			System.out
					.println("******************************************************************");
			program.generageCFG(program.getAbsolutePathFile() + "_test");
		}

		// if (location != null && location.toString().contains("40403a"))
		// System.out.println(curState.getEnvironement().getRegister().getRegisterValue("ecx"));

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

	/*
	 * private static String getExtractBaseFileName(String baseFileName) { //
	 * TODO Auto-generated method stub
	 * 
	 * String r = baseFileName.replace("vx.netlux.org", "cfg");
	 * 
	 * return r; }
	 */
}