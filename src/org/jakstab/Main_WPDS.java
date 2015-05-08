/*
 * Main.java - This file is part of the Jakstab project.
 * Copyright 2007-2012 Johannes Kinder <jk@jakstab.org>
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, see <http://www.gnu.org/licenses/>.
 */

package org.jakstab;

import antlr.ANTLRException;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import org.analysis.cfg.IntraCFG;
import org.analysis.concolic_testing.TestCaseValue;
import org.analysis.symbolic_execution.SymbolicExecution;
import org.analysis.wpds.MinPathMOPV;
import org.analysis.wpds.State;
import org.analysis.wpds.WConf;
import org.analysis.wpds.semiring.MinPathSemiRing;
import org.analysis.wpds.weight.IntegerWeight;
import org.jakstab.analysis.*;
import org.jakstab.analysis.composite.CompositeState;
import org.jakstab.analysis.explicit.BasedNumberValuation;
import org.jakstab.analysis.explicit.BoundedAddressTracking;
import org.jakstab.analysis.procedures.ProcedureAnalysis;
import org.jakstab.analysis.procedures.ProcedureState;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.cfa.Location;
import org.jakstab.loader.BinaryParseException;
import org.jakstab.loader.DefaultHarness;
import org.jakstab.loader.HeuristicHarness;
import org.jakstab.rtl.expressions.ExpressionFactory;
import org.jakstab.ssl.Architecture;
import org.jakstab.transformation.DeadCodeElimination;
import org.jakstab.transformation.ExpressionSubstitution;
import org.jakstab.util.*;
import v2.org.analysis.cfg.AddressList;
import v2.org.analysis.system.SystemHandle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main_WPDS {

	private static Logger logger = Logger.getLogger(Main_WPDS.class);

	private final static String version = "0.8.3";

	private static volatile Algorithm activeAlgorithm;
	private static volatile Thread mainThread;

	public static String[] SplitUsingTokenizer(String Subject, String Delimiters) {
		StringTokenizer StrTkn = new StringTokenizer(Subject, Delimiters);
		ArrayList<String> ArrLis = new ArrayList<String>(Subject.length());
		while (StrTkn.hasMoreTokens()) {
			ArrLis.add(StrTkn.nextToken());
		}
		return ArrLis.toArray(new String[0]);
	}

	/*
	 * public static void main(String[] args) { //Main m = new Main(); String
	 * fileName = "asm/sefm14/pony1.exe"; Main.process("-m " + fileName); }
	 */

	public static void main(String[] args) {

		mainThread = Thread.currentThread();

		StatsTracker stats = StatsTracker.getInstance();

		// Parse command line
		// System.out.print("Enter file name:");
		System.out.print("Enter file name:");
		String in = "";

		// String pathVirus = "asm/sefm14/";
		// String pathVirus = "asm/api/";
		// String pathVirus = "asm/APLAS14/";
		String pathVirus = "asm/wpds/";
		// String pathVirus = "asm/vx.netlux.org/";
		// String pathVirus = "C:/Software/Virus/API/";

		// Scanner in = new Scanner(System.in);
		// in = "Virus.DOS.Holiday.3000";
		// in = "Virus.DOS.Hysterya.2209";
		// in = "Virus.DOS.Nono.1510";
		// in = "Virus.DOS.Ku.334.b";
		// in = "Virus.DOS.Dead.1362";
		// in = "Virus.DOS.Demon3b.4313";
		// in = "Virus.DOS.HLLP.RedArc.Twix" ; //Not finished
		// in = "Trojan-PSW.Win32.QQRob.16.s";

		// in = "PROTO-2.EXE";
		// in = "ambu1.exe";
		// in = "demo11.exe";
		// in = "Virus.DOS.Boys.500";
		// in = "Virus.DOS.Aman.10716";
		// in = "Trojan-Downloader.Win32.Dyfuca.di";
		// in = "Trojan-PSW.Win32.QQRob.16.s";
		// in ="tit.exe";
		// in = "helloMovsb.exe";

		// in = "iod.exe";
		// in = "caza.exe";
		// in = "dkennedy.exe";
		// in = "insert.exe";
		// in = "demo11.exe";
		// in = "demo1.exe";
		// in = "prodigy3.exe";
		// in = "triv_216.exe";
		// in = "ninja-1.exe";
		// in = "pony.exe";
		// in = "pony1.exe";

		/*
		 * in = "Flooder.Win32.FloodBots.20"; in = "Flooder.Win32.FloodBots.21";
		 * in = "Rootkit.Win32.Vanti.bv"; // 1 Co the dung sau in =
		 * "Rootkit.Win32.Vanti.df"; in = "Trojan-Dropper.Win32.Troman.a"; in =
		 * "Trojan-Dropper.Win32.Troman.b"; in = "Trojan-PSW.Win32.QQRob.16.d";
		 * // 1 in = "Trojan-PSW.Win32.QQRob.16.s"; // 1 in =
		 * "Trojan-PSW.Win32.QQRob.16.x"; // 3 Nghien cuu them in =
		 * "Trojan-PSW.Win32.VB.aq"; // 1 Co the dung in =
		 * "Trojan-PSW.Win32.VB.bi"; in = "Trojan-PSW.Win32.VB.cl"; in =
		 * "Trojan-PSW.Win32.VB.dw"; // 1 Co the dung in =
		 * "Virus.Boot.Stoned.Arcv.b"; // 3 in = "Virus.DOS.Aman.10716"; // 1 in
		 * = "Virus.DOS.BadBoy.1000.a"; // 1 in = "Virus.DOS.BadBoy.1000.b"; //
		 * 1 in = "Virus.DOS.Boys.500"; // 2 in = "Virus.DOS.Dead.1362"; // 1 in
		 * = "Virus.DOS.Demon3b.4313"; in = "Virus.DOS.Eliza.1194"; // 3 in =
		 * "Virus.DOS.Flat.1000.b"; // 4 in = "Virus.DOS.Flat.1000.c"; // 4 in =
		 * "Virus.DOS.HLLO.12272"; // 2.5 in = "Virus.DOS.HLLO.187Killa"; // 1
		 * in = "Virus.DOS.HLLP.RedArc.Twix"; // 2 in =
		 * "Virus.DOS.Holiday.3000"; // 5 Dung duoc ngay in =
		 * "Virus.DOS.Hysterya.2209"; // 4.5 in = "Virus.DOS.Ku.334.b";// 3.5 in
		 * = "Virus.DOS.Nono.1510"; // 3.5 in = "Virus.DOS.Shish.1002";// 4 in =
		 * "Virus.DOS.Shish.1142"; // 4.5 in = "Virus.DOS.SillyC.331.a"; // 1 in
		 * = "Virus.DOS.Slips.1475"; // 2.5 in = "Virus.DOS.Slowly.1114"; // 3.5
		 * in = "Virus.DOS.Slowly.1116"; // 3.5 in = "Virus.DOS.Slowly.1249"; //
		 * 3.5 in = "Virus.DOS.Slowly.1853"; // 3.5 in =
		 * "Virus.DOS.Slowly.1917"; // 3.5 in = "Virus.DOS.Spooky.571.b"; // 3
		 * in = "Virus.DOS.Tetris.552"; // 0.5 in = "Virus.DOS.Tetris.633"; //
		 * 0.5 in = "Virus.DOS.Tile.748"; // 2 in = "Virus.DOS.Yong-Ga-Ri.1867";
		 * // 3.5 in = "Virus.Win32.Cabanas.e"; // 0 in =
		 * "Virus.Win32.Cabanas.Release"; // 1 in = "Virus.Win32.Enumiacs.6656";
		 * // 1 in = "Virus.Win32.Enumiacs.8192.b"; // 1 in =
		 * "Virus.Win32.Eva.a"; // 2.5 in = "Virus.Win32.Eva.b"; // 1.5 in =
		 * "Virus.Win32.Eva.c"; // 1.5 in = "Virus.Win32.Htrip.a"; // 1.5 in =
		 * "Virus.Win32.Sankei.1983"; // 4.5 // Virus.Win32.Sankei.3001 va cac
		 * ver ke tiep in = "Virus.Win32.Savior.1740"; // 3.5 // Cac ver ke tiep
		 * in = "Virus.Win32.Seppuku.1606"; // 2.5 // Cac ver ke tiep in =
		 * "Virus.Win32.Wit.a";// 1.5 // Cac ver ke tiep in =
		 * "Virus.Win9x.I13.a"; // 3.5 // Nghien cuu them cac van de jmp
		 * OpenClipboard@user32.dll // Cac ver ke tiep in =
		 * "Virus.Win9x.MMort.1340"; // 1.5 // Nghien cuu them + cac Ver ke tiep
		 * in = "Virus.Win9x.Nathan.3412"; // 4 // Cac ver ke tiep in =
		 * "Virus.Win9x.Roma.1256"; // 4.5 // Ver ke tiep in =
		 * "Virus.Win9x.ZRam.a";// 3.5 // Xu ly Ret Ver ke tiep in =
		 * "Worm.Win32.Banwor.a"; // 3 // Giong nhu tren Worm.Win32.Chainsaw.a
		 * 
		 * in = "Virus.DOS.Holiday.3000"; // 5 Dung duoc ngay in =
		 * "Virus.DOS.Hysterya.2209"; // 4.5 in = "Virus.Win32.Sankei.1983"; //
		 * 4.5 in = "Virus.Win32.Sankei.3464"; // 4.5 in =
		 * "Virus.Win32.Sankei.4085"; // 4.5 // Gap van de voi Memory Operand //
		 * in = "Virus.DOS.Shish.1002";//4 // in = "Virus.DOS.Shish.1142"; //4.5
		 * 
		 * in = "Virus.Win9x.Roma.1256"; // 4.5 // in =
		 * "Virus.Win9x.Nathan.3412"; // 4 in = "Trojan-PSW.Win32.QQRob.16.x";
		 * // 3 Nghien cuu them in = "Virus.Boot.Stoned.Arcv.b"; // 3 // in =
		 * "Virus.DOS.Flat.1000.b"; // 4 in = "Virus.DOS.Flat.1000.c"; // 4 in =
		 * "Virus.DOS.Slowly.1114"; // 3.5
		 * 
		 * // in = "pony1.exe"; // in = "Virus.Win32.Aspade.1811";
		 * 
		 * // in = "iod.exe"; // in = "caza.exe"; // in = "dkennedy.exe"; // in
		 * = "insert.exe"; in = "demo17.exe"; in = "Constructor.Win32.Agent.q";
		 * in = "Virus.DOS.Dead.1373"; in = "Virus.DOS.DeadByte.250"; in =
		 * "Virus.Boot-DOS.Shimmer.b"; in = "Virus.Boot.PG.a"; in =
		 * "Virus.DOS.Beer.2X2.3441"; in = "Virus.DOS.HLLO.Nova"; in =
		 * "demo15.exe"; // in = "demoS.exe"; // in = "self-modifyCode.exe"; //
		 * in = "prodigy3.exe"; // in = "triv_216.exe"; // in = "ninja-1.exe";
		 * // in = "pony.exe"; // in = "pony1.exe"; in = "FirstProgram.exe"; //
		 * in = "pe-02.exe"; // in = "demo2.exe"; // in = "demo3.exe";
		 */
		// in = "Virus.DOS.Trivial.58.d";
		// in = "Net-Worm.Win32.Sasser.b";
		// in = "Virus.DOS.Aztech.1200";
		in = "Virus.Win32.Aztec.01";
		// in = "demo4.exe";
		// in = "Net-Worm.Win32.Sasser.a";
		// in = "sysCall.exe";
		// in = "seh.exe";
		in = "Virus.Win32.Eva.g";
		// in = "Virus.Win32.Cabanas.Release";
		// in = "loop.exe";
		// in = "loop1.exe";
		// in = "loop2.exe";
		// in = "condJump.exe";
		// in = "api_test.exe";
		// in = "Virus.DOS.1stVir.3032";
		in = "Virus.Win32.Seppuku.4827";
		in = "Virus.Win32.Seppuku.1606";
		in = "Virus.Win32.Enumiacs.8192.b";
		in = "Virus.Win32.Belial.a";
		in = "Email-Worm.Win32.Coronex.a";
		in = "Rootkit.Win32.Agent.az";
		in = "wpds1.exe";

		System.out.println(in);

		String[] arg = SplitUsingTokenizer("-m " + pathVirus + in, " ");
		// String[] arg = SplitUsingTokenizer(pathFile, " ");
		// String[] arg = SplitUsingTokenizer("-s -m asm/chosenFile/processing/"
		// + in, " ");
		Options.parseOptions(arg);
		// Options.parseOptions(in.next().split(" "));

		logger.info(Characters.DOUBLE_LINE_FULL_WIDTH);
		logger.info("                         Jakstab " + version);
		logger.info(Characters.DOUBLE_LINE_FULL_WIDTH);

		logger.error("Start analyzing with Jakstab!");

		// ///////////////////////
		// Parse SSL file

		Architecture arch;
		try {
			arch = new Architecture(Options.sslFilename.getValue());
			// arch = new Architecture(fName.next());
		} catch (IOException e) {
			logger.fatal("Unable to open SSL file!", e);
			return;
		} catch (ANTLRException e) {
			logger.fatal("Error parsing SSL file!", e);
			return;
		}

		long overallStartTime = System.currentTimeMillis();

		// ///////////////////////
		// Parse executable

		Program program = Program.createProgram(arch);

		File mainFile = new File(Options.mainFilename).getAbsoluteFile();

		String baseFileName = null;

		try {
			// Load additional modules
			for (String moduleName : Options.moduleFilenames) {
				logger.fatal("Parsing " + moduleName + "...");
				File moduleFile = new File(moduleName).getAbsoluteFile();
				program.loadModule(moduleFile);

				// If we are processing drivers, use the driver's name as base
				// name
				if (Options.wdm.getValue()
						&& moduleFile.getName().toLowerCase().endsWith(".sys")) {
					baseFileName = getBaseFileName(moduleFile);
				}
			}
			// Load main module last
			logger.error("Parsing main module " + Options.mainFilename + "...");
			program.loadMainModule(mainFile);

			// Use main module as base name if we have none yet
			// reserved for drivers, ignore
			if (baseFileName == null)
				baseFileName = getBaseFileName(mainFile);

		} catch (FileNotFoundException e) {
			logger.fatal("File not found: " + e.getMessage());
			return;
		} catch (IOException e) {
			logger.fatal("IOException while parsing executable!", e);
			// e.printStackTrace();
			return;
		} catch (BinaryParseException e) {
			logger.fatal("Error during parsing!", e);
			// e.printStackTrace();
			return;
		}
		logger.info("Finished parsing executable.");

		// Change entry point if requested
		if (Options.startAddress.getValue() > 0) {
			logger.verbose("Setting start address to 0x"
					+ Long.toHexString(Options.startAddress.getValue()));
			program.setEntryAddress(new AbsoluteAddress(Options.startAddress
					.getValue()));
		}

		// Add surrounding "%DF := 1; call entrypoint; halt;"
		program.installHarness(Options.heuristicEntryPoints.getValue() ? new HeuristicHarness()
				: new DefaultHarness());

		int slashIdx = baseFileName.lastIndexOf('\\');
		if (slashIdx < 0)
			slashIdx = baseFileName.lastIndexOf('/');
		if (slashIdx < 0)
			slashIdx = -1;
		slashIdx++;
		stats.record(baseFileName.substring(slashIdx));
		stats.record(version);

		// StatsPlotter.create(baseFileName + "_states.dat");
		// StatsPlotter.plot("#Time(ms)\tStates\tInstructions\tGC Time\tSpeed(st/s)");

		// Catches control-c and System.exit
		Thread shutdownThread = new Thread() {
			@Override
			public void run() {
				if (mainThread.isAlive() && activeAlgorithm != null) {
					// stop = true; // Used for CFI checks
					activeAlgorithm.stop();
					try {
						mainThread.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		Runtime.getRuntime().addShutdownHook(shutdownThread);

		// Add shutdown on return pressed for eclipse
		if (!Options.background.getValue() && System.console() == null) {
			logger.info("No console detected (eclipse?). Press return to terminate analysis and print statistics.");
			Thread eclipseShutdownThread = new Thread() {
				public void run() {
					try {
						System.in.read();
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.exit(1);
				}
			};
			// yices.dll blocks for input on load for some reason, so load it
			// before we start reading from System.in
			// If you are having problems with that, uncomment the next line
			// org.jakstab.solver.yices.YicesWrapper.getVersion();
			eclipseShutdownThread.start();
		}

		// Necessary to stop shutdown thread on exceptions being thrown
		try {

			// ///////////////////////
			// Reconstruct Control Flow
			ControlFlowReconstruction cfr = new ControlFlowReconstruction(
					program);
			long customTime = System.currentTimeMillis();
			// Execute the algorithm
			try {
				runAlgorithm(cfr);
			} catch (RuntimeException r) {
				logger.error("!! Runtime exception during Control Flow Reconstruction! Trying to shut down gracefully.");
				r.printStackTrace();
			}
			long overallEndTime = System.currentTimeMillis();

			ReachedSet reached = cfr.getReachedStates();
			if (Options.dumpStates.getValue()) {
				// output
				logger.fatal("=================");
				logger.fatal(" Reached states:");
				logger.fatal("=================");
				AbstractState[] stateArray = reached
						.toArray(new AbstractState[reached.size()]);
				Arrays.sort(stateArray, new Comparator<AbstractState>() {
					@Override
					public int compare(AbstractState o1, AbstractState o2) {
						return ((CompositeState) o1).getLocation().compareTo(
								((CompositeState) o2).getLocation());
					}
				});
				Location lastLoc = null;
				for (AbstractState s : stateArray) {
					if (!s.getLocation().equals(lastLoc)) {
						lastLoc = s.getLocation();
						logger.fatal("");
					}
					logger.fatal(s);
				}
			}

			int stateCount = reached.size();

			if (Options.outputLocationsWithMostStates.getValue())
				reached.logHighestStateCounts(10);

			if (!cfr.isCompleted()) {
				logger.error(Characters
						.starredBox("WARNING: Analysis interrupted, CFG might be incomplete!"));
			}

			if (!cfr.isSound()) {
				logger.error(Characters
						.starredBox("WARNING: Analysis was unsound!"));
			}

			/*
			 * logger.verbose("Unresolved locations: " +
			 * program.getUnresolvedBranches()); for (Location l :
			 * program.getUnresolvedBranches()) { AbsoluteAddress a =
			 * ((Location)l).getAddress(); if (program.getInstruction(a) ==
			 * null) { logger.verbose(l + ": " +
			 * program.getStatement((Location)l)); } else { logger.verbose(a +
			 * "\t" + program.getInstructionString(a)); } }
			 */

			int indirectBranches = program.countIndirectBranches();

			logger.info(Characters.DOUBLE_LINE_FULL_WIDTH);
			logger.info("   Statistics for Control Flow Reconstruction");
			logger.info(Characters.DOUBLE_LINE_FULL_WIDTH);
			logger.info("   Runtime:                     "
					+ String.format("%8dms",
							(overallEndTime - overallStartTime)));
			logger.info("   Instructions:                        "
					+ String.format("%8d", program.getInstructionCount()));
			logger.info("   RTL Statements:                      "
					+ String.format("%8d", program.getStatementCount()));
			logger.info("   CFA Edges:                           "
					+ String.format("%8d", program.getCFA().size()));
			logger.info("   States visited:                      "
					+ String.format("%8d", cfr.getNumberOfStatesVisited()));
			logger.info("   Final state space:                   "
					+ String.format("%8d", stateCount));
			logger.info("   Finished normally:                   "
					+ String.format("%8b", cfr.isCompleted()));
			logger.info("   Analysis result:                     "
					+ cfr.getStatus());
			// logger.error( "   Sound:                               " +
			// String.format("%8b", cfr.isSound()));
			logger.info("   Indirect Branches (no import calls): "
					+ String.format("%8d", indirectBranches));
			logger.info("   Unresolved Branches:                 "
					+ String.format("%8d", program.getUnresolvedBranches()
							.size()));
			logger.debug("   FastSet conversions:                 "
					+ String.format("%8d", FastSet.getConversionCount()));
			logger.debug("   Variable count:                      "
					+ String.format("%8d", ExpressionFactory.getVariableCount()));
			logger.info(Characters.DOUBLE_LINE_FULL_WIDTH);

			stats.record(program.getInstructionCount());
			stats.record(program.getStatementCount());
			stats.record(program.getCFA().size());
			stats.record(indirectBranches);
			stats.record(program.getUnresolvedBranches().size());
			stats.record(cfr.getNumberOfStatesVisited());
			stats.record(stateCount);
			stats.record(Math
					.round((overallEndTime - overallStartTime) / 1000.0));
			stats.record(cfr.getStatus());
			stats.record(Options.cpas.getValue());
			stats.record(BoundedAddressTracking.varThreshold.getValue());
			stats.record(BoundedAddressTracking.heapThreshold.getValue());
			stats.record(Options.basicBlocks.getValue() ? "y" : "n");
			stats.record(Options.summarizeRep.getValue() ? "y" : "n");
			stats.record(BasedNumberValuation.ExplicitPrintfArgs);
			stats.record(BasedNumberValuation.OverAppPrintfArgs);

			stats.print();

			ProgramGraphWriter graphWriter = new ProgramGraphWriter(program);

			String baseFileName1 = getExtractBaseFileName(baseFileName);

			graphWriter.writeDisassembly(program, baseFileName1 + "_jak.asm");

			if (!(cfr.isCompleted() && Options.secondaryCPAs.getValue()
					.length() > 0)) {
				if (!Options.noGraphs.getValue()) {
					graphWriter.writeControlFlowAutomaton(baseFileName1
							+ "_cfa");
					graphWriter.writeAssemblyCFG(baseFileName1 + "_asmcfg");
				}
				// if (Options.errorTrace) graphWriter.writeART(baseFileName +
				// "_art", cfr.getART());
			} else {
				// If control flow reconstruction finished normally and other
				// analyses are configured, start them now

				// Simplify CFA
				logger.info("=== Simplifying CFA ===");
				DeadCodeElimination dce;
				long totalRemoved = 0;
				runAlgorithm(new ExpressionSubstitution(program));
				do {
					dce = new DeadCodeElimination(program);
					runAlgorithm(dce);
					totalRemoved += dce.getRemovalCount();
				} while (dce.getRemovalCount() > 0);
				logger.info("=== Finished CFA simplification, removed "
						+ totalRemoved + " edges. ===");

				AnalysisManager mgr = AnalysisManager.getInstance();
				List<ConfigurableProgramAnalysis> secondaryCPAs = new LinkedList<ConfigurableProgramAnalysis>();
				for (int i = 0; i < Options.secondaryCPAs.getValue().length(); i++) {
					ConfigurableProgramAnalysis cpa = mgr
							.createAnalysis(Options.secondaryCPAs.getValue()
									.charAt(i));
					if (cpa != null) {
						AnalysisProperties p = mgr.getProperties(cpa);
						logger.info("--- Using " + p.getName());
						secondaryCPAs.add(cpa);
					} else {
						logger.fatal("No analysis corresponds to letter \""
								+ Options.secondaryCPAs.getValue().charAt(i)
								+ "\"!");
						System.exit(1);
					}
				}
				// Do custom analysis
				long customAnalysisStartTime = System.currentTimeMillis();
				CPAAlgorithm cpaAlg;
				ConfigurableProgramAnalysis[] cpaArray = secondaryCPAs
						.toArray(new ConfigurableProgramAnalysis[secondaryCPAs
								.size()]);
				if (Options.backward.getValue()) {
					cpaAlg = CPAAlgorithm.createBackwardAlgorithm(program,
							cpaArray);
				} else {
					cpaAlg = CPAAlgorithm.createForwardAlgorithm(program,
							cpaArray);
				}
				activeAlgorithm = cpaAlg;
				cpaAlg.run();
				long customAnalysisEndTime = System.currentTimeMillis();

				if (!Options.noGraphs.getValue())
					graphWriter.writeControlFlowAutomaton(
							baseFileName + "_cfa", cpaAlg.getReachedStates()
									.select(1));

				logger.error(Characters.DOUBLE_LINE_FULL_WIDTH);
				logger.error("   Statistics for "
						+ Options.secondaryCPAs.getValue());
				logger.error(Characters.DOUBLE_LINE_FULL_WIDTH);
				logger.error("   Runtime:                "
						+ String.format(
								"%8dms",
								(customAnalysisEndTime - customAnalysisStartTime)));
				logger.error("   States:                   "
						+ String.format("%8d", cpaAlg.getReachedStates().size()));
				logger.error(Characters.DOUBLE_LINE_FULL_WIDTH);

			}

			// If procedure abstraction is active, detect procedures now
			if (cfr.isCompleted()
					&& Options.procedureAbstraction.getValue() == 2) {
				cfr = null;
				reached = null;
				ProcedureAnalysis procedureAnalysis = new ProcedureAnalysis();
				CPAAlgorithm cpaAlg = CPAAlgorithm.createForwardAlgorithm(
						program, procedureAnalysis);
				runAlgorithm(cpaAlg);
				reached = cpaAlg.getReachedStates().select(1);
				Set<Location> procedures = procedureAnalysis.getCallees();

				SetMultimap<Location, Location> callGraph = HashMultimap
						.create();

				// Procedure analysis and thus this callgraph only works with
				// --procedures 2
				// A broken callgraph does not affect the safety checks, though,
				// as all
				// procedures are checked without any interprocedural
				// abstraction anyway
				for (Pair<Location, Location> callSite : procedureAnalysis
						.getCallSites()) {
					ProcedureState procedureState = (ProcedureState) Lattices
							.joinAll(reached.where(callSite.getLeft()));
					for (Location procedure : procedureState
							.getProcedureEntries()) {
						callGraph.put(procedure, callSite.getRight());
					}
				}
				logger.info("Found " + procedures.size()
						+ " function entry points from procedure analysis.");

				if (!Options.noGraphs.getValue())
					graphWriter.writeCallGraph(baseFileName + "_callgraph",
							callGraph);
			}
			// System.out.println("Number of nodes " + program.);
			System.out.println(Characters.DOUBLE_LINE_FULL_WIDTH);
			System.out.println("   Statistics for Control Flow Reconstruction");
			System.out.println(Characters.DOUBLE_LINE_FULL_WIDTH);
			System.out.println("   Filename:                     " + in);

			System.out.println("   Runtime:                     "
					+ String.format("%8dms",
							(overallEndTime - overallStartTime)));
			System.out.println("   Instructions:                        "
					+ String.format("%8d", program.getInstructionCount()));
			System.out.println("   Nodes:                        "
					+ String.format("%8d", graphWriter.getNodesCount()));
			System.out.println("   Edges:                        "
					+ String.format("%8d", graphWriter.getEdgesCount()));
			System.out.println("   RTL Statements:                      "
					+ String.format("%8d", program.getStatementCount()));
			System.out.println("   CFA Edges:                           "
					+ String.format("%8d", program.getCFA().size()));
			System.out.println("   States visited:                      "
					+ String.format("%8d", cfr.getNumberOfStatesVisited()));
			System.out.println("   Final state space:                   "
					+ String.format("%8d", stateCount));
			System.out.println("   Finished normally:                   "
					+ String.format("%8b", cfr.isCompleted()));
			System.out.println("   Analysis result:                     "
					+ cfr.getStatus());
			System.out.println("Finished parsing binary codes!");
			// Processing Time
			System.out.println("Processing Time: "
					+ String.format("%8dms",
							(System.currentTimeMillis() - customTime)));

			SystemHandle system = new SystemHandle();
			// if (this.indirectTarget.toString().equals("0x00401142"))
			// System.out.println("Debug");
			IntraCFG cfg = new IntraCFG(program, system);
			// cfg.prinCFG();
			// EnvSemiRing sr = new EnvSemiRing();
			List<WConf> conf = new ArrayList<WConf>();

			// AbsoluteAddress targetAddr = new AbsoluteAddress(4198425);
			AbsoluteAddress targetAddr = new AbsoluteAddress(4198423);
			// sr.setAddr(indirectTarget);
			// sr.setInst(program.getAssemblyMap().get(indirectTarget));
			List<AddressList> traceList = cfg.getTraceList(targetAddr);
			MinPathMOPV mopv = new MinPathMOPV(new IntegerWeight(0),
					new MinPathSemiRing());
			mopv.setTrace(traceList);
			if (traceList == null)
				return;

			for (int i = 0; i < traceList.size(); i++) {
				AddressList trace = traceList.get(i);
				// AddressList concreteTrace = trace.clone();
				// System.out.print("Original Trace:");
				trace.printInfo();
				TestCaseValue sv = new TestCaseValue();
				// BackwardSlicingMethod.slicingAlgorithm(trace,
				// this.indirectTarget,
				// program.getAssemblyMap(), sv);

				// SymbolicExecution se = new
				// SymbolicExecution(this.indirectTarget,
				// trace, cfg.getNegConditionList(), sv, program, system);
				SymbolicExecution se = new SymbolicExecution(targetAddr,
						trace.clone(), cfg, sv, program, system);
				se.setWeightConf(conf, new IntegerWeight(0));
				// se.printInfor();
				se.execute();
			}

			// System.out.println(sr.toString());
			System.out.println();
			System.out.println("WPDS information:");

			mopv.setConfigurationList(conf);
			mopv.setTarget(new State(program.getAssemblyMap().get(targetAddr),
					targetAddr));
			mopv.computeMOPV();
			// sr.printOutput();
			// Log.d("","");

			// logger.error("Applying symbolic execution to find statement constraints...");

			// InterPCFG cfg = new InterPCFG(program);
			// if (!cfg.constructCFG())
			// System.out.println("Construct CFG error");
			// else cfg.prinCFG();
			// cfg.test();
			// cfg.constructCFG();
			// cfg.prinCFG();

			// System.out.println("Value of Memory Operand: " +
			// program.get32Value(new AbsoluteAddress(4202496)));

			// SymbolicExecution se = new SymbolicExecution(program);
			// se.execute();

			// Kills the keypress-monitor-thread.
			try {
				Runtime.getRuntime().removeShutdownHook(shutdownThread);
				System.exit(0);
			} catch (IllegalStateException e) {
				// Happens when shutdown has already been initiated by Ctrl-C or
				// Return
			}
		} catch (Throwable e) {
			System.out.flush();
			e.printStackTrace();
			Runtime.getRuntime().removeShutdownHook(shutdownThread);
			// Kills eclipse shutdown thread
			System.exit(1);
		}

	}

	private static void runAlgorithm(Algorithm a) {
		activeAlgorithm = a;
		a.run();
		activeAlgorithm = null;
	}

	@SuppressWarnings("unused")
	private static final void appendToFile(String filename, String text) {
		try {
			FileWriter statsFile = new FileWriter(filename, true);
			statsFile.append(text);
			statsFile.close();
		} catch (Exception e) {
			logger.error("Cannot write to outputfile!", e);
		}
	}

	/*
	 * private static String getBaseFileName1(File file) { String baseFileName =
	 * file.getAbsolutePath(); // Get name of the analyzed file without file
	 * extension if it has one if (file.getName().contains(".")) { int dotIndex
	 * = file.getPath().lastIndexOf('.'); if (dotIndex > 0) { baseFileName =
	 * file.getPath().substring(0, dotIndex); } } return baseFileName; }
	 */

	private static String getBaseFileName(File file) {
		String baseFileName = file.getAbsolutePath();
		// Get name of the analyzed file without file extension if it has one
		if (file.getName().contains(".")) {
			int dotIndex = file.getPath().lastIndexOf('.');
			if (dotIndex > 0) {
				// baseFileName = file.getPath().substring(0, dotIndex);
			}
		}
		return baseFileName;
	}

	private static String getExtractBaseFileName(String baseFileName) {
		// TODO Auto-generated method stub

		String r = baseFileName.replace("vx.netlux.org", "ProcessResults_Temp");

		return r;
	}
}
