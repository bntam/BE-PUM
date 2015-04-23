/**
 * 
 */
package v2.org.analysis.environment;

import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;
import v2.org.analysis.complement.Convert;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * @author NMHai New stack with treating as Memory
 * 
 */
public class StackV2 extends Stack {
	private long baseAddr, topAddr;
	private Environment env;

	@Override
	public Stack clone() {
		// TODO Auto-generated method stub
		StackV2 s = new StackV2();
		s.setBaseAddress(baseAddr);
		s.setTopAddress(topAddr);

		return s;
	}

	@Override
	public void push(Value pushedElement) {
		// TODO Auto-generated method stub
		Value top = env.getRegister().getRegisterValue("esp");

		if (top instanceof LongValue) {
			topAddr = ((LongValue) top).getValue();
			topAddr -= 4;

			env.getMemory().setDoubleWordMemoryValue(topAddr, pushedElement);
			env.getRegister().sub("esp", new LongValue(4));
		} else
			System.out.println("Cannot pushed to stack!!!");
	}

	@Override
	public boolean equals(Stack s) {
		// TODO Auto-generated method stub
		if (s instanceof StackV2)
			return baseAddr == ((StackV2) s).getBaseAddress()
					&& topAddr == ((StackV2) s).getTopAddress();
		return false;
	}

	@Override
	public Value pop() {
		// TODO Auto-generated method stub
		Value top = env.getRegister().getRegisterValue("esp");

		if (top instanceof LongValue) {
			topAddr = ((LongValue) top).getValue();
			Value p = env.getMemory().getDoubleWordMemoryValue(topAddr);

			if (p != null) {
				topAddr += 4;
				env.getRegister().add("esp", new LongValue(4));

				return p;
			}
		}

		System.out.println("Cannot pop out of stack!!!");
		return null;
	}

	@Override
	public Value getValueStackFromIndex(long desp) {
		// TODO Auto-generated method stub
		Value esp = env.getRegister().getRegisterValue("esp");
		if (esp instanceof LongValue)
			return env.getMemory().getDoubleWordMemoryValue(
					desp + ((LongValue) esp).getValue());

		return null;
	}

	public void setValueStackFromIndex(long desp, Value v) {
		// TODO Auto-generated method stub
		Value esp = env.getRegister().getRegisterValue("esp");
		if (esp instanceof LongValue)
			env.getMemory().setDoubleWordMemoryValue(
					desp + ((LongValue) esp).getValue(), v);
	}

	@Override
	public Value getIndex(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int length() {
		// TODO Auto-generated method stub
		return (int) ((topAddr - baseAddr) / 4);
	}

	@Override
	public void sub(long desp, Value v, Instruction inst) {
		// TODO Auto-generated method stub
		Value r = this.getValueStackFromIndex(desp);
		r = r.subFunction(v);
		r = normalizeValue(r, inst);
		setValueStackFromIndex(desp, r);
	}

	@Override
	public void add(long desp, Value v, Instruction inst) {
		// TODO Auto-generated method stub
		Value r = this.getValueStackFromIndex(desp);
		r = r.addFunction(v);
		r = normalizeValue(r, inst);
		setValueStackFromIndex(desp, r);
	}

	@Override
	public void mul(long desp, Value v, Instruction inst) {
		// TODO Auto-generated method stub
		Value r = this.getValueStackFromIndex(desp);
		r = r.unsignedMulFunction(v);
		r = normalizeValue(r, inst);
		setValueStackFromIndex(desp, r);
	}

	@Override
	public void xor(long desp, Value v, Instruction inst) {
		// TODO Auto-generated method stub
		Value r = this.getValueStackFromIndex(desp);
		r = r.xorFunction(v);
		r = normalizeValue(r, inst);
		setValueStackFromIndex(desp, r);
	}

	@Override
	public void or(long desp, Value v, Instruction inst) {
		// TODO Auto-generated method stub
		Value r = this.getValueStackFromIndex(desp);
		r = r.orFunction(v);
		r = normalizeValue(r, inst);
		setValueStackFromIndex(desp, r);
	}

	@Override
	public void and(long desp, Value v, Instruction inst) {
		// TODO Auto-generated method stub
		Value r = this.getValueStackFromIndex(desp);
		r = r.andFunction(v);
		r = normalizeValue(r, inst);
		setValueStackFromIndex(desp, r);
	}

	@Override
	public void div(long desp, Value v, Instruction inst) {
		// TODO Auto-generated method stub
		Value r = this.getValueStackFromIndex(desp);
		r = r.unsignedDivFunction(v);
		r = normalizeValue(r, inst);
		setValueStackFromIndex(desp, r);
	}

	@Override
	public void rr(long desp, Value v, Instruction inst) {
		// TODO Auto-generated method stub
		Value r = this.getValueStackFromIndex(desp);
		r = r.rrFunction(v);
		r = normalizeValue(r, inst);
		setValueStackFromIndex(desp, r);
	}

	@Override
	public void rl(long desp, Value v, Instruction inst) {
		// TODO Auto-generated method stub
		Value r = this.getValueStackFromIndex(desp);
		r = r.rlFunction(v);
		r = normalizeValue(r, inst);
		setValueStackFromIndex(desp, r);
	}

	@Override
	public void pop(long t) {
		// TODO Auto-generated method stub
		for (int i = 0; i < t / 4; i++)
			pop();
	}

	public void init(LongValue longValue) {
		// TODO Auto-generated method stub
		baseAddr = Convert.hexToLong("12FFF0");
		//baseAddr = Convert.hexToLong("18FF90");
		topAddr = Convert.hexToLong("12FFD0");
		//topAddr = Convert.hexToLong("18FF94");
		//oldBaseAddr = Convert.hexToLong("12FFD0");

		env.getRegister().mov("esp", new LongValue(getTopAddress()));
		env.getRegister().mov("ebp", new LongValue(getBaseAddress()));

		//push(new LongValue((long) (Math.random() * Math.pow(10, 5))));
		//push(new LongValue((long) (Math.random() * Math.pow(10, 5))));
		//push(new LongValue((long) (Math.random() * Math.pow(10, 5))));
		//push(new LongValue((long) (Math.random() * Math.pow(10, 5))));
		//push(new LongValue((long) (Math.random() * Math.pow(10, 5))));		
		
		//push(new LongValue((long) (Math.random() * Math.pow(10, 5))));
		push(new LongValue(1));
		push(new LongValue((long) (Math.random() * Math.pow(10, 7))));
		push(longValue);
	}

	/**
	 * @return the baseAddr
	 */
	public long getBaseAddress() {
		return baseAddr;
	}

	/**
	 * @param baseAddr
	 *            the baseAddr to set
	 */
	public void setBaseAddress(long baseAddr) {
		this.baseAddr = baseAddr;
	}

	/**
	 * @return the topAddr
	 */
	public long getTopAddress() {
		return topAddr;
	}

	/**
	 * @param topAddr
	 *            the topAddr to set
	 */
	public void setTopAddress(long topAddr) {
		this.topAddr = topAddr;
	}

	/**
	 * @return the env
	 */
	public Environment getEnvironment() {
		return env;
	}

	/**
	 * @param env
	 *            the env to set
	 */
	public void setEnvironment(Environment env) {
		this.env = env;
	}

	private Value normalizeValue(Value v, Instruction inst) {
		if (v instanceof LongValue) {
			long t = ((LongValue) v).getValue();
			return new LongValue((long)Convert.convetUnsignedValue(t,
					Convert.getBitCount(inst)));
		}

		return v;
	}
	
	@Override
	public String toString() {
		String result = "";
		long index = baseAddr;
		/*while (index >= topAddr) {
			result += env.getMemory().getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, index)) + ",";
			index -= 4;
		}*/
		
		Value esp = env.getRegister().getRegisterValue("esp");
		Value ebp = env.getRegister().getRegisterValue("ebp");
		if (esp != null && esp instanceof LongValue) {
			long top = ((LongValue)esp).getValue();
			while (index >= top) {
				result += new AbsoluteAddress(index) + " : " + env.getMemory().getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, index)) + ", ";
				index -= 4;
			}

			if (ebp != null && ebp instanceof LongValue)
				result += "EBP: " + env.getMemory().getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, ((LongValue)ebp).getValue()));
			//return result + " " + (topAddr == ((LongValue) esp).getValue());
		}
		if (result == "")
			return "Stack is undefined";
		
		return result;
	}

	public String getString() {
		String result = "";

		/*while (index >= topAddr) {
			result += env.getMemory().getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, index)) + ",";
			index -= 4;
		}*/

		Value esp = env.getRegister().getRegisterValue("esp");
		Value ebp = env.getRegister().getRegisterValue("ebp");
		if (esp != null && esp instanceof LongValue) {
			long index = ((LongValue)esp).getValue();
			while (index <= baseAddr) {
				result += new AbsoluteAddress(index) + " : " + env.getMemory().getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, index)) + "\n";
				index += 4;
			}

			if (ebp != null && ebp instanceof LongValue)
				result += "EBP: " + env.getMemory().getDoubleWordMemoryValue(new X86MemoryOperand(DataType.INT32, ((LongValue)ebp).getValue()));
			//return result + " " + (topAddr == ((LongValue) esp).getValue());
		}
		if (result == "")
			return "Stack is undefined";

		return result;
	}

	@Override
	public boolean isInsideStack(AbsoluteAddress addr) {
		// TODO Auto-generated method stub
		return addr.getValue() > baseAddr - 1048576 && addr.getValue() < topAddr + 1048576;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return topAddr > baseAddr;
	}
	
	@Override
	public void push16(Value pushedElement) {
		// TODO Auto-generated method stub
		Value top = env.getRegister().getRegisterValue("esp");

		if (top instanceof LongValue) {
			topAddr = ((LongValue) top).getValue();
			topAddr -= 2;

			env.getMemory().setWordMemoryValue(topAddr, pushedElement);
			env.getRegister().sub("esp", new LongValue(2));
		} else
			System.out.println("Cannot pushed to stack!!!");

	}
}
