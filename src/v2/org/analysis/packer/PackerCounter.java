package v2.org.analysis.packer;

import org.jakstab.Program;

import v2.org.analysis.packer.techniques.IndirectJump;
import v2.org.analysis.path.BPState;

public class PackerCounter {
	
	private BPState m_state;
	private Program m_prog;
	
	private IndirectJump indirectJumpC;
	
	public PackerCounter ()
	{	
		indirectJumpC 	= new IndirectJump();
	}
	
	public void Execute (boolean run)
	{
		if (run)
		{
			indirectJumpC.Count(this.m_state, this.m_prog);
		}
	}
	
	public void setCountingState (BPState curState, Program prog)
	{
		this.m_state 	= curState;
		this.m_prog		= prog;
	}
	
	public void getInfo ()
	{
		System.out.println("============== PACKER TECHNIQUES STATISCIAL ==================");
		indirectJumpC.GetInfo();
		System.out.println("==============================================================");
	}
}
