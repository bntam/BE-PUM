package v2.org.analysis.transition_rule;

import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.Immediate;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.Operand;
import org.jakstab.asm.x86.X86RetInstruction;
import v2.org.analysis.cfg.BPCFG;
import v2.org.analysis.cfg.BPEdge;
import v2.org.analysis.cfg.BPVertex;
import v2.org.analysis.complement.Convert;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.path.BPPath;
import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

import java.util.List;

public class X86ReturnInterpreter {

	public BPState execute(X86RetInstruction inst, BPPath path,
			List<BPPath> pathList, X86TransitionRule rule) {
		// TODO Auto-generated method stub
		BPState curState = path.getCurrentState();
		// Formulas l = path.getPathCondition();
		Environment env = curState.getEnvironement();

		/*
		 * BPState s = specialCase(inst, curState, l); if (s != null) return s;
		 */

		if (inst.getName().startsWith("ret")) {

			// PHONG: CHECK FOR RETURN AT ADDRESS: 004097f0
			// if (curState.getLocation().toString().contains("4097f0")){
			// System.out.println();
			// }

			Value ret = env.getStack().pop();
			// env.getRegister().add("esp", new LongValue(4));

			if (ret != null && ret instanceof LongValue) {

				long r = ((LongValue) ret).getValue();
				if (r == 0x7c9032A8) {
					//if (Program.getProgram().getFileName().equals("api_test_yc1.2.exe")) 
					//		ret = new LongValue(0x401000) ;
					//else 
					return rule.specialProcessSEH(curState);
				}

				AbsoluteAddress nextAddr = new AbsoluteAddress(
						((LongValue) ret).getValue());
				Instruction nextIns = Program.getProgram().getInstruction(
						nextAddr, env);

				Program.getProgram().setTechnique("Ret-Indirect Jump");
				Program.getProgram().setDetailTechnique(
						"Ret-Indirect Jump:" + curState.getLocation() + " ");

				if (nextIns == null) {
					BPCFG cfg = Program.getProgram().getBPCFG();
					BPVertex s1 = cfg.getVertex(curState.getLocation(),
							curState.getInstruction());
					BPVertex s2 = new BPVertex();

					if (env.getSystem().getKernel().isInside(nextAddr))
						s2.setType(BPVertex.ExitNode);
					else
						s2.setType(BPVertex.UnknownNode);

					s2.setProperty(nextAddr.toString());
					cfg.insertVertex(s2);
					cfg.insertEdge(new BPEdge(s1, s2));

					nextAddr = null;
					rule.setCFG(true);
				}

				curState.setInstruction(nextIns);
				curState.setLocation(nextAddr);

				if (inst.getOperandCount() == 1) {
					Operand op = inst.getOperand(0);
					long t = Convert.convetUnsignedValue(((Immediate) op).getNumber()
							.intValue(), rule.getBitCount(inst));

					env.getStack().pop(t);
				}
			}
		} else {
			Program.getProgram().getLog().error("Instruction not supported" + inst + " at " 
					+ curState.getLocation());
		}

		return curState;
	}

	

	
}
