/**
 * 
 */
package v2.org.analysis.environment;

import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;
import org.jakstab.disasm.x86.X86Disassembler;
import org.jakstab.loader.ExecutableImage;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;

import v2.org.analysis.complement.BitVector;
import v2.org.analysis.complement.Convert;
import v2.org.analysis.environment.ExternalMemory.ExternalMemoryReturnData;
import v2.org.analysis.statistics.FileProcess;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.SymbolValue;
import v2.org.analysis.value.Value;

import java.util.Map;
import java.util.Map.Entry;

/**
 * @author NMHai
 * 
 */
public class Memory {
	private Environment env;
	private Map<X86MemoryOperand, Value> memory;
	private Program program;
	private Map<Long, Value> reset;
	final int UNKNOWN = Integer.MAX_VALUE;

	public Memory() {
		memory = new NewHashMap<X86MemoryOperand, Value>();
		reset = new NewHashMap<Long, Value>();
		program = Program.getProgram();
	}

	public Memory(Environment env) {
		memory = new NewHashMap<X86MemoryOperand, Value>();
		program = Program.getProgram();
		this.env = env;
	}

	public String outputMemory() {
		FileProcess fp = new FileProcess(System.getProperty("user.dir")
				+ "/data/memory.txt");
		String ret = "";
		for (Map.Entry<X86MemoryOperand, Value> entry : memory.entrySet()) {
			ret += entry.getKey() + "\t" + entry.getValue() + "\n";
		}

		fp.clearContentFile();
		fp.appendFile(ret);
		return ret;
	}

	@Override
	public String toString() {
		// FileProcess fp = new FileProcess("data/memory.txt");
		String ret = "";
		for (Map.Entry<X86MemoryOperand, Value> entry : memory.entrySet()) {
			ret += entry.getKey() + "=" + entry.getValue() + ",";
		}

		// fp.clearContentFile();
		// fp.appendFile(ret);
		outputMemory();
		return ret;
	}

	public void addMemoryValue(long address, Value v, Instruction inst) {
		Value o = getMemoryValue(address, inst);
		o = o.addFunction(v);
		setMemoryValue(address, o, inst);
	}

	public void addMemoryValue(X86MemoryOperand dest, Value v, Instruction inst) {
		if (dest.getBase() != null && dest.getBase().toString().contains("esp")) {
			env.getStack().add(dest.getDisplacement(), v, inst);
			return;
		}

		long d = evaluateAddress(dest);

		if (d == UNKNOWN)
			return;

		this.addMemoryValue(d, v, inst);
	}

	public void andMemoryValue(long address, Value v, Instruction inst) {
		Value o = getMemoryValue(address, inst);
		o = o.andFunction(v);
		setMemoryValue(address, o, inst);
	}

	public void andMemoryValue(X86MemoryOperand dest, Value v, Instruction inst) {
		if (dest.getBase() != null && dest.getBase().toString().contains("esp")) {
			env.getStack().and(dest.getDisplacement(), v, inst);
			return;
		}

		long d = evaluateAddress(dest);

		if (d == UNKNOWN)
			return;

		this.andMemoryValue(d, v, inst);
	}

	private long calculateDoubleWordValue(long r1, long r2, long r3, long r4) {
		/*
		 * int ret = 0; ret = (int) r1; ret |= r2 << 8; ret |= r3 << 16; ret |=
		 * r4 << 24; return ret;
		 */
		return BitVector.bytesToLong((int) r1, (int) r2, (int) r3, (int) r4);
	}

	private long calculateWordValue(long r1, long r2) {
		/*
		 * int ret = 0; ret = (int) r1; ret |= r2 << 8; return ret;
		 */
		return BitVector.bytesToLong((int) r1, (int) r2);
	}

	public boolean changeValue(AbsoluteAddress address) {
		// TODO Auto-generated method stub
		ExecutableImage module = Program.getProgram().getModule(address);

		for (Map.Entry<X86MemoryOperand, Value> entry : memory.entrySet()) {
			X86MemoryOperand m = (X86MemoryOperand) entry.getKey();
			long addr = evaluateAddress(m);
			if (Math.abs(addr - address.getValue()) < 20) {
				Value v = entry.getValue();
				if (v instanceof LongValue) {
					long fp1 = module.getFilePointer(new AbsoluteAddress(addr));

					if (fp1 < 0 || (int) fp1 < 0) {
						Program.getProgram()
								.getLog()
								.debugString(
										"Requested instruction outside of file area: "
												+ address);
						return false;
					}

					// PHONG: debug here
					// Value old = new LongValue(Program.getProgram()
					// .getByteValueMemory(new AbsoluteAddress(addr)));
					// long old_v =
					// module.getDisassembler().getMemoryByteValue((int) fp1);
					if (module.getDisassembler() instanceof X86Disassembler) {
						X86Disassembler x86Dis = (X86Disassembler) module
								.getDisassembler();
						long oldValue = x86Dis.getMemoryByteValue((int) fp1);

						if (oldValue != ((LongValue) v).getValue()) {
							// Program.getProgram().setTechnique("SMC");
							// Program.getProgram().setDetailTechnique("SMC:" +
							// new AbsoluteAddress(addr) + " ");
							module.getDisassembler().setMemoryByteValue(
									(int) fp1, ((LongValue) v).getValue());
							reset.put(addr, new LongValue(oldValue));
						}
					}
				}
			}
		}

		return true;
	}

	/*
	 * // PHONG: debug here add some function to test private void
	 * changeValuePhong(AbsoluteAddress address, Environment env) { // TODO
	 * Auto-generated method stub ExecutableImage module =
	 * Program.getProgram().getModule(address);
	 * 
	 * for (Map.Entry<X86MemoryOperand, Value> entry : memory.entrySet()) {
	 * X86MemoryOperand m = (X86MemoryOperand) entry.getKey(); long addr =
	 * evaluateAddress(m); if (Math.abs(addr - address.getValue()) < 5) { Value
	 * v = entry.getValue(); if (v instanceof LongValue) { long fp1 =
	 * module.getFilePointer(new AbsoluteAddress(addr));
	 * 
	 * // PHONG: debug here Value old = new LongValue(Program.getProgram()
	 * .getByteValueMemoryPhong(new AbsoluteAddress(addr), env));
	 * 
	 * module.getDisassembler().setMemoryByteValue((int) fp1, ((LongValue)
	 * v).getValue()); reset.put(addr, old); } } } }
	 */

	public Memory clone() {
		Memory ret = new Memory();

		for (Entry<X86MemoryOperand, Value> entry : memory.entrySet()) {
			if (entry.getValue() != null)
				ret.setByteMemoryValue(entry.getKey(), entry.getValue().clone());
		}

		return ret;
	}

	public boolean contains(AbsoluteAddress addr) {
		// TODO Auto-generated method stub
		for (Map.Entry<X86MemoryOperand, Value> entry : memory.entrySet()) {
			X86MemoryOperand m = (X86MemoryOperand) entry.getKey();

			if (evaluateAddress(m) == addr.getValue())
				return true;
		}

		return false;
	}

	public void divMemoryValue(long address, Value v, Instruction inst) {
		Value o = getMemoryValue(address, inst);
		o = o.unsignedDivFunction(v);
		setMemoryValue(address, o, inst);
	}

	public void divMemoryValue(X86MemoryOperand dest, Value v, Instruction inst) {
		if (dest.getBase() != null && dest.getBase().toString().contains("esp")) {
			env.getStack().div(dest.getDisplacement(), v, inst);
			return;
		}

		long d = evaluateAddress(dest);

		if (d == UNKNOWN)
			return;

		this.divMemoryValue(d, v, inst);
	}

	public boolean equals(Memory m) {
		for (Entry<X86MemoryOperand, Value> entry : memory.entrySet()) {
			Value t = m.getByteMemoryValue(entry.getKey());
			if (t == null || t.equal(entry.getValue()))
				return false;
		}

		return true;
	}

	private long evaluateAddress(X86MemoryOperand m) {
		// TODO Auto-generated method stub
		long val = (int) m.getDisplacement();
		if (m.getBase() != null) {
			Value r = env.getRegister()
					.getRegisterValue(m.getBase().toString());
			if (r != null && r instanceof LongValue) {
				// PHONG: change long address here to int address
				val += (int) ((LongValue) r).getValue();
				// return ret + m.getDisplacement() + ((LongValue)
				// r).getValueOperand();
			} else
				return UNKNOWN;
		}

		if (m.getIndex() != null) {
			Value index = env.getRegister().getRegisterValue(
					m.getIndex().toString());

			if (index != null && index instanceof LongValue) {
				if (m.getScale() != 0)
					val += (int) (((LongValue) index).getValue() * m.getScale());
				else
					val += (int) (((LongValue) index).getValue());
			} else
				return UNKNOWN;
		}

		return val;
	}

	public Value calculateAddress(X86MemoryOperand m) {
		// TODO Auto-generated method stub
		if (m.getBase() != null) {
			Value r = env.getRegister()
					.getRegisterValue(m.getBase().toString());
			Value index = null;
			if (m.getIndex() != null)
				index = env.getRegister().getRegisterValue(
						m.getIndex().toString());
			// long ret = 0;

			if (index != null) {
				if (m.getScale() != 0)
					r = r.addFunction(index.unsignedMulFunction(new LongValue(m
							.getScale())));
			}

			r = r.addFunction(new LongValue(m.getDisplacement()));

			return r;
		}

		return new LongValue(m.getDisplacement());
	}

	public X86MemoryOperand evaluateAddress(X86MemoryOperand m, Environment env) {
		// TODO Auto-generated method stub
		/*
		 * Value base = null; long disp = 0;
		 * 
		 * if (t.getBase() != null) { base =
		 * env.getRegister().getRegisterValue(t.getBase().toString()); } if
		 * (t.getDisplacement() != 0) { disp = t.getDisplacement(); if (disp ==
		 * -1254086899) disp = disp & 0xFFFFFF; }
		 * 
		 * if (base != null) { if (base instanceof LongValue) { // PHONG:
		 * something weird here. Change to INT long val = (int)disp +
		 * (int)((LongValue) base).getValue();
		 * 
		 * Value index = null; if (t.getIndex() != null) index =
		 * env.getRegister().getRegisterValue(t.getIndex().toString());
		 * 
		 * if (index != null && index instanceof LongValue) val +=
		 * (int)t.getScale() * (int) ((LongValue)index).getValue();
		 * 
		 * return new X86MemoryOperand(t.getDataType(), val); } } return t;
		 */
		long val = (int) m.getDisplacement();
		if (m.getBase() != null) {
			Value r = env.getRegister()
					.getRegisterValue(m.getBase().toString());
			if (r != null && r instanceof LongValue) {
				// PHONG: change long address here to int address
				val += (int) ((LongValue) r).getValue();
				// return ret + m.getDisplacement() + ((LongValue)
				// r).getValueOperand();
			} else
				return m;
		}

		if (m.getIndex() != null) {
			Value index = env.getRegister().getRegisterValue(
					m.getIndex().toString());

			if (index != null && index instanceof LongValue) {
				if (m.getScale() != 0)
					val += (int) (((LongValue) index).getValue() * m.getScale());
				else
					val += (int) (((LongValue) index).getValue());
			} else
				return m;
		}

		return new X86MemoryOperand(m.getDataType(), val);
	}

	public Value getByteMemoryValue(long address) {
		if (env.getSystem().getKernel().isInside(new AbsoluteAddress(address))) {
			return new LongValue(env.getSystem().getKernel()
					.readByte((int) address));
		}

		if (env.getSystem().getFileHandle()
				.isInsideFIle(new AbsoluteAddress(address))) {
			return new LongValue(env.getSystem().getFileHandle()
					.readByte((int) address));
		}

		for (Map.Entry<X86MemoryOperand, Value> entry : memory.entrySet()) {
			X86MemoryOperand m = (X86MemoryOperand) entry.getKey();

			if (evaluateAddress(m) == address)
				return entry.getValue();
		}

		try {
			// PHONG: change here: something has called getbytememoryvalue
			if (program.isInside(new AbsoluteAddress(address)))
				return new LongValue(program.getByteValueMemoryPhong(
						new AbsoluteAddress(address), env));
			else {
				//YenNguyen: Access jna's memory here
				if (address != 0) {
					ExternalMemoryReturnData ret = ExternalMemory.getByte(address);
					if (ret != null && ret.isValidAddress) 
						return ret.value;
				}
				return new SymbolValue(Convert.generateString(
							new X86MemoryOperand(DataType.INT8, address))
							.toString());				
			}
		} catch (Exception e) {
			return new SymbolValue(Convert.generateString(
					new X86MemoryOperand(DataType.INT8, address)).toString());
		}
	}

	public Value getByteMemoryValue(X86MemoryOperand dest) {
		long d = evaluateAddress(dest);

		if (d == UNKNOWN)
			return new SymbolValue(Convert.generateString(dest));

		return this.getByteMemoryValue(d);
	}

	public Value getDoubleWordMemoryValue(long address) {
		/*
		 * if (address==2420113468l) System.out.println("Debug");
		 */

		if (env.getSystem().getKernel().isInside(new AbsoluteAddress(address))) {
			return new LongValue(env.getSystem().getKernel()
					.readDoubleWord((int) address));
		}

		if (env.getSystem().getFileHandle()
				.isInsideFIle(new AbsoluteAddress(address))) {
			return new LongValue(env.getSystem().getFileHandle()
					.readDoubleWord((int) address));
		}

		long ret1 = UNKNOWN, ret2 = UNKNOWN, ret3 = UNKNOWN, ret4 = UNKNOWN;
		boolean p = false;
		for (Map.Entry<X86MemoryOperand, Value> entry : memory.entrySet()) {
			X86MemoryOperand m = (X86MemoryOperand) entry.getKey();

			if (evaluateAddress(m) == address) {
				Value v = entry.getValue();
				if (v instanceof LongValue) {
					ret1 = ((LongValue) v).getValue();
					p = true;
				} else
					return new SymbolValue(
							Convert.generateString(new X86MemoryOperand(
									DataType.INT32, address)));
			} else if (evaluateAddress(m) == address + 1) {
				Value v = entry.getValue();
				if (v instanceof LongValue) {
					ret2 = ((LongValue) v).getValue();
					p = true;
				} else
					return new SymbolValue(
							Convert.generateString(new X86MemoryOperand(
									DataType.INT32, address)));
			} else if (evaluateAddress(m) == address + 2) {
				Value v = entry.getValue();
				if (v instanceof LongValue) {
					ret3 = ((LongValue) v).getValue();
					p = true;
				} else
					return new SymbolValue(
							Convert.generateString(new X86MemoryOperand(
									DataType.INT32, address)));
			} else if (evaluateAddress(m) == address + 3) {
				Value v = entry.getValue();
				if (v instanceof LongValue) {
					ret4 = ((LongValue) v).getValue();
					p = true;
				} else
					return new SymbolValue(
							Convert.generateString(new X86MemoryOperand(
									DataType.INT32, address)));
			}
		}

		if (p) {
			if (ret1 == UNKNOWN)
				ret1 = program.getByteValueMemory(new AbsoluteAddress(address));

			if (ret2 == UNKNOWN)
				ret2 = program.getByteValueMemory(new AbsoluteAddress(
						address + 1));

			if (ret3 == UNKNOWN)
				ret3 = program.getByteValueMemory(new AbsoluteAddress(
						address + 2));

			if (ret4 == UNKNOWN)
				ret4 = program.getByteValueMemory(new AbsoluteAddress(
						address + 3));
		}

		if (ret1 != UNKNOWN && ret2 != UNKNOWN && ret3 != UNKNOWN
				&& ret4 != UNKNOWN)
			return new LongValue(calculateDoubleWordValue(ret1, ret2, ret3,
					ret4));

		/*
		 * if (ret1 != UNKNOWN) { try { return new
		 * LongValue(calculateWordValue(ret1, program.getWordValueMemory(new
		 * AbsoluteAddress(address + 1)))); } catch (Exception e) { return new
		 * TopValue(); } } else if (ret2 != UNKNOWN) { try { return new
		 * LongValue(calculateWordValue(program.getWordValueMemory(new
		 * AbsoluteAddress(address)), ret2)); } catch (Exception e) { return new
		 * TopValue(); } }
		 */
		// Chinh sua sau van de nay

		try {
			if (program.isInside(new AbsoluteAddress(address)))
				return new LongValue(
						program.getDoubleWordValueMemory(new AbsoluteAddress(
								address)));
			else {
				if (address != 0) {
					ExternalMemoryReturnData ret = ExternalMemory.getDoubleWord(address);
					if (ret != null && ret.isValidAddress) {
						return ret.value;
					} 
				} 
				return new SymbolValue(
					Convert.generateString(new X86MemoryOperand(
							DataType.INT32, address)));
			}
		} catch (Exception e) {
			return new SymbolValue(Convert.generateString(new X86MemoryOperand(
					DataType.INT32, address)));
		}
	}

	public Value getDoubleWordMemoryValue(X86MemoryOperand dest) {
		long d = evaluateAddress(dest);

		if (d == UNKNOWN) {

			return new SymbolValue(Convert.generateString(dest));
		}

		return this.getDoubleWordMemoryValue(d);
	}

	public Value getMemoryValue(long dest, Instruction inst) {
		// TODO Auto-generated method stub
		if (inst.getName().endsWith("l"))
			return getDoubleWordMemoryValue(dest);
		else if (inst.getName().endsWith("b"))
			return getByteMemoryValue(dest);
		else if (inst.getName().endsWith("s") || inst.getName().endsWith("w"))
			return getWordMemoryValue(dest);

		return getDoubleWordMemoryValue(dest);
	}

	public Value getMemoryValue(X86MemoryOperand dest, Instruction inst) {
		// TODO Auto-generated method stub
		/*
		 * if (dest.getBase() != null &&
		 * dest.getBase().toString().contains("esp")) { //
		 * System.out.println("Access the value of Stack"); return
		 * env.getStack().getValueStackFromIndex(dest.getDisplacement()); }
		 */
		if (dest.getSegmentRegister() != null
				&& dest.getSegmentRegister().toString() == "%fs") {
			return new LongValue(env.getSystem().getSEHHandler().getStart()
					.getNextSEHRecord());
		}

		/*
		 * if ((dest.getDataType() != DataType.INT32 &&
		 * inst.getName().endsWith("l")) || (dest.getDataType() != DataType.INT8
		 * && inst.getName().endsWith("b")) || (dest.getDataType() !=
		 * DataType.INT16 && inst.getName().endsWith("s")) ||
		 * (dest.getDataType() != DataType.INT16 &&
		 * inst.getName().endsWith("w")) ) System.out.println("Debug");
		 */

		if (dest.getDataType() == DataType.INT32)
			return getDoubleWordMemoryValue(dest);
		else if (dest.getDataType() == DataType.INT8)
			return getByteMemoryValue(dest);
		else if (dest.getDataType() == DataType.INT16)
			return getWordMemoryValue(dest);

		/*
		 * if (inst.getName().endsWith("l")) return
		 * getDoubleWordMemoryValue(dest); else if
		 * (inst.getName().endsWith("b")) return getByteMemoryValue(dest); else
		 * if (inst.getName().endsWith("s") || inst.getName().endsWith("w"))
		 * return getWordMemoryValue(dest);
		 */

		return getDoubleWordMemoryValue(dest);
	}

	public Register getRegister() {
		return env.getRegister();
	}

	/**
	 * @return the stack
	 */
	public Stack getStack() {
		return env.getStack();
	}

	public String getText(int addr, long l) {
		// TODO Auto-generated method stub
		String ret = "";
		// long addr = evaluateAddress(x86MemoryOperand);

		for (int i = 0; i < l; i++) {
			Value t = getByteMemoryValue(addr + i);

			if (t != null && t instanceof LongValue) {
				byte t1 = (byte) (((LongValue) t).getValue() & 0xFF);
				ret += (char) t1;
			}
		}

		return ret;

	}

	public String getText(long addr) {
		// TODO Auto-generated method stub
		String ret = "";
		// long addr = evaluateAddress(x86MemoryOperand);
		while (true) {
			Value t = getByteMemoryValue(addr);

			if (t != null && t instanceof LongValue) {
				byte t1 = (byte) (((LongValue) t).getValue() & 0xFF);
				if (t1 == 0)
					break;

				ret += (char) t1;
				addr++;
			} else if (t instanceof SymbolValue)
				return ret;
		}

		return ret;
	}

	public String getText(X86MemoryOperand x86MemoryOperand) {
		// TODO Auto-generated method stub
		String ret = "";
		long addr = evaluateAddress(x86MemoryOperand);
		while (true) {
			Value t = getByteMemoryValue(addr);

			if (t != null && t instanceof LongValue) {
				byte t1 = (byte) (((LongValue) t).getValue() & 0xFF);
				if (t1 == 0)
					break;

				ret += (char) t1;
				addr++;
			} else if (t instanceof SymbolValue)
				return ret;
		}

		return ret;
	}

	public Value getWordMemoryValue(long address) {
		if (env.getSystem().getKernel().isInside(new AbsoluteAddress(address))) {
			return new LongValue(env.getSystem().getKernel()
					.readWord((int) address));
		}

		if (env.getSystem().getFileHandle()
				.isInsideFIle(new AbsoluteAddress(address))) {
			return new LongValue(env.getSystem().getFileHandle()
					.readWord((int) address));
		}

		long ret1 = UNKNOWN, ret2 = UNKNOWN;
		boolean p = false;
		for (Map.Entry<X86MemoryOperand, Value> entry : memory.entrySet()) {
			X86MemoryOperand m = (X86MemoryOperand) entry.getKey();

			if (evaluateAddress(m) == address) {
				Value v1 = entry.getValue();
				if (v1 instanceof LongValue) {
					ret1 = ((LongValue) v1).getValue();
					p = true;
				} else
					return new SymbolValue(Convert.generateString(
							new X86MemoryOperand(DataType.INT16, address))
							.toString());
				// break;
			} else if (evaluateAddress(m) == address + 1) {
				Value v2 = entry.getValue();
				if (v2 instanceof LongValue) {
					ret2 = ((LongValue) v2).getValue();
					p = true;
				} else
					return new SymbolValue(Convert.generateString(
							new X86MemoryOperand(DataType.INT16, address))
							.toString());
				// break;
			}
		}

		if (p) {
			if (ret1 == UNKNOWN)
				ret1 = program.getByteValueMemory(new AbsoluteAddress(address));
			else if (ret2 == UNKNOWN)
				ret2 = program.getByteValueMemory(new AbsoluteAddress(
						address + 1));
		}

		if (ret1 != UNKNOWN && ret2 != UNKNOWN)
			return new LongValue(calculateWordValue(ret1, ret2));

		if (ret1 != UNKNOWN) {
			try {
				return new LongValue(calculateWordValue(ret1,
						program.getWordValueMemory(new AbsoluteAddress(
								address + 1))));
			} catch (Exception e) {
				return new SymbolValue(Convert.generateString(
						new X86MemoryOperand(DataType.INT16, address))
						.toString());
			}
		} else if (ret2 != UNKNOWN) {
			try {
				return new LongValue(
						calculateWordValue(
								program.getWordValueMemory(new AbsoluteAddress(
										address)), ret2));
			} catch (Exception e) {
				return new SymbolValue(
						Convert.generateString(new X86MemoryOperand(
								DataType.INT16, address)));
			}
		}

		try {
			if (program.isInside(new AbsoluteAddress(address)))
				return new LongValue(
						program.getWordValueMemory(new AbsoluteAddress(address)));
			else {
				//YenNguyen: Access jna's memory here
				if (address != 0) {
					ExternalMemoryReturnData ret = ExternalMemory.getWord(address);
					if (ret != null && ret.isValidAddress) {
						return ret.value;
					}
				}
				return new SymbolValue(Convert.generateString(
					new X86MemoryOperand(DataType.INT8, address))
						.toString());				
			}			
		} catch (Exception e) {
			return new SymbolValue(Convert.generateString(new X86MemoryOperand(
					DataType.INT16, address)));
		}
	}

	public Value getWordMemoryValue(X86MemoryOperand dest) {
		long d = evaluateAddress(dest);

		if (d == UNKNOWN)
			return new SymbolValue(Convert.generateString(dest));

		return this.getWordMemoryValue(d);
	}

	public void mulMemoryValue(long address, Value v, Instruction inst) {
		Value o = getMemoryValue(address, inst);
		o = o.unsignedMulFunction(v);
		setMemoryValue(address, o, inst);
	}

	public void mulMemoryValue(X86MemoryOperand dest, Value v, Instruction inst) {
		if (dest.getBase() != null && dest.getBase().toString().contains("esp")) {
			env.getStack().mul(dest.getDisplacement(), v, inst);
			return;
		}

		long d = evaluateAddress(dest);

		if (d == UNKNOWN)
			return;

		this.mulMemoryValue(d, v, inst);
	}

	private Value normalizeValue(Value v, Instruction inst) {
		if (v instanceof LongValue) {
			long t = ((LongValue) v).getValue();
			return new LongValue(Convert.convetUnsignedValue(t,
					Convert.getBitCount(inst)));
		}

		return v;
	}

	public void orMemoryValue(long address, Value v, Instruction inst) {
		Value o = getMemoryValue(address, inst);
		o = o.orFunction(v);
		setMemoryValue(address, o, inst);
	}

	public void orMemoryValue(X86MemoryOperand dest, Value v, Instruction inst) {
		if (dest.getBase() != null && dest.getBase().toString().contains("esp")) {
			env.getStack().or(dest.getDisplacement(), v, inst);
			return;
		}

		long d = evaluateAddress(dest);

		if (d == UNKNOWN)
			return;

		this.orMemoryValue(d, v, inst);
	}

	public void resetValue(AbsoluteAddress address, Instruction instr) {
		// TODO Auto-generated method stub
		if (instr == null)
			return;

		ExecutableImage module = Program.getProgram().getModule(address);

		for (Map.Entry<Long, Value> entry : reset.entrySet()) {
			long m = entry.getKey();
			// long addr = evaluateAddress(m);
			long fp1 = module.getFilePointer(new AbsoluteAddress(m));
			module.getDisassembler().setMemoryByteValue((int) fp1,
					((LongValue) entry.getValue()).getValue());
			if (m - address.getValue() < instr.getSize()
					&& m - address.getValue() >= 0) {
				Program.getProgram().setTechnique("SMC");
				Program.getProgram().setDetailTechnique("SMC:" + address + " ");
			}
			// reset.put(addr, v);
		}
	}

	public void rlMemoryValue(long address, Value v, Instruction inst) {
		Value o = getMemoryValue(address, inst);
		o = o.rlFunction(v);
		setMemoryValue(address, o, inst);
	}

	public void rlMemoryValue(X86MemoryOperand dest, Value v, Instruction inst) {
		if (dest.getBase() != null && dest.getBase().toString().contains("esp")) {
			env.getStack().rl(dest.getDisplacement(), v, inst);
			return;
		}

		long d = evaluateAddress(dest);

		if (d == UNKNOWN)
			return;

		this.rlMemoryValue(d, v, inst);
	}

	public void rrMemoryValue(long address, Value v, Instruction inst) {
		Value o = getMemoryValue(address, inst);
		o = o.rrFunction(v);
		setMemoryValue(address, o, inst);
	}

	public void rrMemoryValue(X86MemoryOperand dest, Value v, Instruction inst) {
		if (dest.getBase() != null && dest.getBase().toString().contains("esp")) {
			env.getStack().rr(dest.getDisplacement(), v, inst);
			return;
		}

		long d = evaluateAddress(dest);

		if (d == UNKNOWN)
			return;

		this.rrMemoryValue(d, v, inst);
	}

	public void setByteMemoryValue(long address, Value v) {
		for (Map.Entry<X86MemoryOperand, Value> entry : memory.entrySet()) {
			X86MemoryOperand m = (X86MemoryOperand) entry.getKey();

			if (evaluateAddress(m) == address) {
				// if (address == 4198435)
				// System.out.println("Debug");
				entry.setValue(v);
				return;
			}
		}

		memory.put(new X86MemoryOperand(DataType.INT32, address), v);
	}

	public void setByteMemoryValue(X86MemoryOperand dest, Value v) {
		long d = evaluateAddress(dest);

		if (d == UNKNOWN)
			return;

		this.setByteMemoryValue(d, v);
	}

	public void setDoubleWordMemoryValue(long address, Value v) {
		if (v instanceof LongValue) {
			long x = ((LongValue) v).getValue();
			int[] t = BitVector.longToBytes(x, 4);
			/*
			 * x = x & 0xFFFFFFFF; long x1 = (long) (x % Math.pow(2, 8)); long y
			 * = (long) (x / Math.pow(2, 8)); long x2 = (long) (y % Math.pow(2,
			 * 8)); long z = (long) (x / Math.pow(2, 16)); long x3 = (long) (z %
			 * Math.pow(2, 8)); long x4 = (long) (x / Math.pow(2, 24));
			 */

			// if (t[0] != x4 || t[1] != x3 || t[2] != x2 || t[3] != x1)
			// System.out.println("Debug");
			// else
			// System.out.println("Debug");
			long x4 = t[0];
			long x3 = t[1];
			long x2 = t[2];
			long x1 = t[3];
			boolean s1 = false, s2 = false, s3 = false, s4 = false;
			;
			for (Map.Entry<X86MemoryOperand, Value> entry : memory.entrySet()) {
				X86MemoryOperand m = (X86MemoryOperand) entry.getKey();

				if (evaluateAddress(m) == address) {
					entry.setValue(new LongValue(x1));
					s1 = true;
				}
				if (evaluateAddress(m) == address + 1) {
					entry.setValue(new LongValue(x2));
					s2 = true;
				}
				if (evaluateAddress(m) == address + 2) {
					entry.setValue(new LongValue(x3));
					s3 = true;
				}
				if (evaluateAddress(m) == address + 3) {
					entry.setValue(new LongValue(x4));
					s4 = true;
				}

				if (s1 && s2 && s3 && s4)
					return;
			}

			if (!s1)
				memory.put(new X86MemoryOperand(DataType.INT32, address),
						new LongValue(x1));

			if (!s2)
				memory.put(new X86MemoryOperand(DataType.INT32, address + 1),
						new LongValue(x2));

			if (!s3)
				memory.put(new X86MemoryOperand(DataType.INT32, address + 2),
						new LongValue(x3));

			if (!s4)
				memory.put(new X86MemoryOperand(DataType.INT32, address + 3),
						new LongValue(x4));
		} else {
			for (Map.Entry<X86MemoryOperand, Value> entry : memory.entrySet()) {
				X86MemoryOperand m = (X86MemoryOperand) entry.getKey();

				if (evaluateAddress(m) == address) {
					entry.setValue(v);
					return;
				}
			}
			memory.put(new X86MemoryOperand(DataType.INT32, address), v);
		}
	}

	public void setDoubleWordMemoryValue(X86MemoryOperand dest, Value v) {
		long d = evaluateAddress(dest);

		if (d == UNKNOWN)
			return;

		this.setDoubleWordMemoryValue(d, v);
	}

	public void setEnvironment(Environment environment) {
		// TODO Auto-generated method stub
		env = environment;
	}

	private void setMemoryValue(long dest, Value x, Instruction inst) {
		if (x == null)
			return;

		x = this.normalizeValue(x, inst);
		// TODO Auto-generated method stub
		if (inst.getName().endsWith("l"))
			setDoubleWordMemoryValue(dest, x);
		else if (inst.getName().endsWith("b"))
			setByteMemoryValue(dest, x);
		else if (inst.getName().endsWith("s") || inst.getName().endsWith("w"))
			setWordMemoryValue(dest, x);
		else
			setDoubleWordMemoryValue(dest, x);
	}

	public void setMemoryValue(X86MemoryOperand dest, Value x, Instruction inst) {
		// TODO Auto-generated method stub
		if (x == null)
			return;

		x = this.normalizeValue(x, inst);

		/*
		 * if ((dest.getDataType() != DataType.INT32 &&
		 * inst.getName().endsWith("l")) || (dest.getDataType() != DataType.INT8
		 * && inst.getName().endsWith("b")) || (dest.getDataType() !=
		 * DataType.INT16 && inst.getName().endsWith("s")) ||
		 * (dest.getDataType() != DataType.INT16 &&
		 * inst.getName().endsWith("w")) ) System.out.println("Debug");
		 */

		/*
		 * if (inst.getName().endsWith("l")) setDoubleWordMemoryValue(dest, x);
		 * else if (inst.getName().endsWith("b")) setByteMemoryValue(dest, x);
		 * else if (inst.getName().endsWith("s") ||
		 * inst.getName().endsWith("w")) setWordMemoryValue(dest, x);
		 */
		if (dest.getDataType() == DataType.INT32)
			setDoubleWordMemoryValue(dest, x);
		else if (dest.getDataType() == DataType.INT8)
			setByteMemoryValue(dest, x);
		else if (dest.getDataType() == DataType.INT16)
			setWordMemoryValue(dest, x);
	}

	public void setText(X86MemoryOperand m, String str) {
		// TODO Auto-generated method stub
		char[] t = str.toCharArray();
		long disp = evaluateAddress(m);

		for (int i = 0; i < t.length; i++) {
			int x = (int) t[i];
			if (x == 47)
				x = 92;

			this.setByteMemoryValue(
					new X86MemoryOperand(m.getDataType(), disp), new LongValue(
							x));
			disp++;
		}
	}

	public long setText(X86MemoryOperand m, String str, long value) {
		// TODO Auto-generated method stub
		char[] t = str.toCharArray();
		long disp = evaluateAddress(m);

		for (int i = 0; i < value && i < t.length; i++) {
			int x = (int) t[i];
			if (x == 47)
				x = 92;

			this.setByteMemoryValue(
					new X86MemoryOperand(m.getDataType(), disp), new LongValue(
							x));
			disp++;
		}
		return t.length;
	}

	public void setWordMemoryValue(long address, Value v) {
		if (v == null)
			return;

		if (v instanceof LongValue) {
			long x = ((LongValue) v).getValue();

			// long x2 = (long) ((x & 0xFFFF) / Math.pow(2, 8));
			// long x1 = (long) ((x & 0xFFFF) % Math.pow(2, 8));
			int[] t = BitVector.longToBytes(x, 2);
			long x2 = t[0];
			long x1 = t[1];

			boolean s1 = false, s2 = false;
			for (Map.Entry<X86MemoryOperand, Value> entry : memory.entrySet()) {
				X86MemoryOperand m = (X86MemoryOperand) entry.getKey();

				if (evaluateAddress(m) == address) {
					entry.setValue(new LongValue(x1));
					s1 = true;
				}
				if (evaluateAddress(m) == address + 1) {
					entry.setValue(new LongValue(x2));
					s2 = true;
				}

				if (s1 && s2)
					return;
			}

			if (!s1)
				memory.put(new X86MemoryOperand(DataType.INT32, address),
						new LongValue(x1));

			if (!s2)
				memory.put(new X86MemoryOperand(DataType.INT32, address + 1),
						new LongValue(x2));
		} else {
			for (Map.Entry<X86MemoryOperand, Value> entry : memory.entrySet()) {
				X86MemoryOperand m = (X86MemoryOperand) entry.getKey();

				if (evaluateAddress(m) == address) {
					entry.setValue(v);
					return;
				}
			}
			memory.put(new X86MemoryOperand(DataType.INT32, address), v);
		}
	}

	public void setWordMemoryValue(X86MemoryOperand dest, Value v) {
		if (v == null)
			return;

		long d = evaluateAddress(dest);

		if (d == UNKNOWN)
			return;

		this.setWordMemoryValue(d, v);
	}

	public void subMemoryValue(long address, Value v, Instruction inst) {
		Value o = getMemoryValue(address, inst);
		o = o.subFunction(v);
		setMemoryValue(address, o, inst);
	}

	public void subMemoryValue(X86MemoryOperand dest, Value v, Instruction inst) {
		// Process with Stack
		if (dest.getBase() != null && dest.getBase().toString().contains("esp")) {
			env.getStack().sub(dest.getDisplacement(), v, inst);
			return;
		}

		long d = evaluateAddress(dest);

		if (d == UNKNOWN)
			return;

		this.subMemoryValue(d, v, inst);
	}

	/*
	 * private int getBitCount(Instruction ins) { // TODO Auto-generated method
	 * stub if (ins.getName().endsWith("b")) return 8; else if
	 * (ins.getName().endsWith("l")) return 32; else if
	 * (ins.getName().endsWith("s") || ins.toString().endsWith("w")) return 16;
	 * return 0; }
	 */

	public void xorMemoryValue(long address, Value v, Instruction inst) {
		Value o = getMemoryValue(address, inst);
		o = o.xorFunction(v);
		setMemoryValue(address, o, inst);
	}

	public void xorMemoryValue(X86MemoryOperand dest, Value v, Instruction inst) {
		if (dest.getBase() != null && dest.getBase().toString().contains("esp")) {
			env.getStack().xor(dest.getDisplacement(), v, inst);
			return;
		}

		long d = evaluateAddress(dest);

		if (d == UNKNOWN)
			return;

		this.xorMemoryValue(d, v, inst);
	}

	public void divSignMemoryValue(long address, Value v, Instruction inst) {
		Value o = getMemoryValue(address, inst);
		if (o instanceof LongValue && v instanceof LongValue) {
			long dest = ((LongValue) o).getValue();
			long src = ((LongValue) v).getValue();
			int b = Convert.getBitCount(inst);
			int signBit = Convert.getSignBit(dest, b);

			for (int j = 1; j <= src; j++)
				dest = dest / 2;

			for (int j = 1; j <= src; j++)
				dest += signBit * Math.pow(2, b - j);

			setMemoryValue(address, new LongValue(dest), inst);
			return;
		}

		o = o.unsignedDivFunction(v);
		setMemoryValue(address, o, inst);
	}

	public void divSignMemoryValue(X86MemoryOperand dest, Value v,
			Instruction inst) {
		if (dest.getBase() != null && dest.getBase().toString().contains("esp")) {
			env.getStack().div(dest.getDisplacement(), v, inst);
			return;
		}

		long d = evaluateAddress(dest);

		if (d == UNKNOWN)
			return;

		this.divSignMemoryValue(d, v, inst);
	}

	public void getMemoryAddress(X86MemoryOperand t, X86Instruction inst) {
		// TODO Auto-generated method stub

	}

	public long[] getBytesArray(AbsoluteAddress address, int n) {
		// TODO Auto-generated method stub
		if (address == null)
			return null;

		long[] ret = new long[n];
		for (int i = 0; i < n; i++)
			ret[i] = Long.MIN_VALUE;

		for (Map.Entry<X86MemoryOperand, Value> entry : memory.entrySet()) {
			X86MemoryOperand m = (X86MemoryOperand) entry.getKey();
			long addr = evaluateAddress(m);

			int index = (int) (addr - address.getValue());
			if (index < n && index >= 0) {
				Value t = entry.getValue();
				if (t != null && t instanceof LongValue)
					ret[index] = (byte) ((LongValue) t).getValue();
			}
		}
		return ret;
	}

	/*
	 * private long normalizeValue(long v, Instruction inst) { // TODO
	 * Auto-generated method stub return Convert.convetUnsignedValue(v,
	 * Convert.getBitCount(inst)); }
	 */
}
