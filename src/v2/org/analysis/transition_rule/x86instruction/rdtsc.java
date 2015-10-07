/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.transition_rule.x86instruction
 * File name: rdtsc.java
 * Created date: Sep 23, 2015
 * Description:
 */
package v2.org.analysis.transition_rule.x86instruction;

import v2.org.analysis.path.BPState;
import v2.org.analysis.transition_rule.AnalysisBit;
import v2.org.analysis.transition_rule.X86InstructionStub;
import v2.org.analysis.value.LongValue;

/**
 * @author Yen Nguyen
 *
 */
public class rdtsc extends X86InstructionStub {

	public rdtsc() {
	}

	@Override
	public BPState execute() {
		long time_stamp = System.nanoTime();
		AnalysisBit analysisBit = new AnalysisBit();
		long rdtsc_eax = analysisBit.RDTSC_EAX(time_stamp);
		long rdtsc_edx = analysisBit.RDTSC_EDX(time_stamp);
		env.getRegister().setRegisterValue("eax", new LongValue(rdtsc_eax));
		env.getRegister().setRegisterValue("edx", new LongValue(rdtsc_edx));
		
		return null;
	}

}
