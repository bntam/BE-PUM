package v2.org.analysis.system.dll.abstracts;

import org.jakstab.asm.AbsoluteAddress;

import v2.org.analysis.complement.Convert;

import java.io.File;
import java.io.FileInputStream;

public class LibImage {
	// YenNguyen
	public static final String ADVAPI32PATH = "data/data/advapi32.dll";
	public static final String USER32PATH = "data/data/user32.dll";
	public static final String KERNEL32PATH = "data/data/kernel32.dll";

	private long baseAddress; // 7C800000
	private LibPEHandler peHandler;
	// Map <Integer, ByteClass> image;
	private int length;
	private String libName = "kernel32.dll";
	private byte[] value;

	public long getBaseAddress() {
		return baseAddress;
	}

	public void setBaseAddress(long baseAddress) {
		this.baseAddress = baseAddress;
	}

	private String path = null; // YenNguyen

	public String getPath() {
		return path;
	}

	public LibImage() {
		createImage();
	}

	public LibImage(String path) {
		this.path = path;
		createImage();
	}

	public long getReturnRandomValue() {
		return baseAddress + Convert.hexToLong("300") + (int) Math.random() * 30;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public static void main(String[] args) {
		LibImage k = new LibImage();

		System.out.println("Base address: " + k.baseAddress + " Hex:" + Convert.longToHex(k.baseAddress));
		System.out.println("Length: " + k.getLength());
		String index = "3c";
		int offset = (int) (k.baseAddress + Convert.hexToLong(index));
		System.out.println("Offset:" + index + " Value:" + offset);
		System.out.println("Read byte: " + k.readByte(offset) + " Hex:" + Convert.longToHex(k.readByte(offset), 8));
		System.out.println("Read word: " + k.readWord(offset) + " Hex:" + Convert.longToHex(k.readWord(offset), 16));
		System.out.println("Read double word: " + k.readDoubleWord(offset) + " Hex:"
				+ Convert.longToHex(k.readDoubleWord(offset), 32));

		index = "168";
		offset = (int) (k.baseAddress + Convert.hexToLong(index));
		System.out.println("Offset:" + index);
		System.out.println("Read byte: " + k.readByte(offset) + " Hex:" + Convert.longToHex(k.readByte(offset), 8));
		System.out.println("Read word: " + k.readWord(offset) + " Hex:" + Convert.longToHex(k.readWord(offset), 16));
		System.out.println("Read double word: " + k.readDoubleWord(offset) + " Hex:"
				+ Convert.longToHex(k.readDoubleWord(offset), 32));

		index = "262c";
		offset = (int) (k.baseAddress + Convert.hexToLong(index));
		System.out.println("Offset:" + index);
		System.out.println("Read byte: " + k.readByte(offset) + " Hex:" + Convert.longToHex(k.readByte(offset), 8));
		System.out.println("Read word: " + k.readWord(offset) + " Hex:" + Convert.longToHex(k.readWord(offset), 16));
		System.out.println("Read double word: " + k.readDoubleWord(offset) + " Hex:"
				+ Convert.longToHex(k.readDoubleWord(offset), 32));

		index = "2648";
		offset = (int) (k.baseAddress + Convert.hexToLong(index));
		System.out.println("Offset:" + index);
		System.out.println("Read byte: " + k.readByte(offset) + " Hex:" + Convert.longToHex(k.readByte(offset), 8));
		System.out.println("Read byte: " + k.readWord(offset) + " Hex:" + Convert.longToHex(k.readWord(offset), 16));
		System.out.println("Read byte: " + k.readDoubleWord(offset) + " Hex:"
				+ Convert.longToHex(k.readDoubleWord(offset), 32));

		index = "264c";
		offset = (int) (k.baseAddress + Convert.hexToLong(index));
		System.out.println("Offset:" + index);
		System.out.println("Read byte: " + k.readByte(offset) + " Hex:" + Convert.longToHex(k.readByte(offset), 8));
		System.out.println("Read byte: " + k.readWord(offset) + " Hex:" + Convert.longToHex(k.readWord(offset), 16));
		System.out.println("Read byte: " + k.readDoubleWord(offset) + " Hex:"
				+ Convert.longToHex(k.readDoubleWord(offset), 32));

		index = "2650";
		offset = (int) (k.baseAddress + Convert.hexToLong(index));
		System.out.println("Offset:" + index);
		System.out.println("Read byte: " + k.readByte(offset) + " Hex:" + Convert.longToHex(k.readByte(offset), 8));
		System.out.println("Read byte: " + k.readWord(offset) + " Hex:" + Convert.longToHex(k.readWord(offset), 16));
		System.out.println("Read byte: " + k.readDoubleWord(offset) + " Hex:"
				+ Convert.longToHex(k.readDoubleWord(offset), 32));
	}

	private void createImage() {
		// TODO Auto-generated method stub
		// image = new HashMap <Integer, ByteClass>();
		FileInputStream fileInputStream = null;

		File file = new File(this.path);

		length = (int) file.length();
		value = new byte[this.length];

		try {
			// Create PE Handler
			peHandler = new LibPEHandler(file);
			this.baseAddress = peHandler.getBaseAddress();
			// convert file into array of bytes
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(value);
			fileInputStream.close();

			// for (int i = 0; i < value.length; i++) {
			// System.out.print((char)value[i]);
			// }

			// System.out.println("Done");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// offset = new byte[(int)f.length()];
	}

	private long getRVAAddress(long address) {
		long t = peHandler.getRVA(address);
		if (t < 0)
			return address;
		else
			return t;
		// return in.getValueOperand();
	}

	public int readByte(int offset) {
		int off = (int) getRVAAddress(offset - this.baseAddress);
		// int off = (int) (offset - this.baseAddress);
		if (off < 0 || off >= value.length)
			return Integer.MIN_VALUE;
		return value[off];
	}

	public long readWord(int index) {
		/*
		 * int ret = 0; ret = readByte(index + 1); ret |= readByte(index + 1) <<
		 * 8; return ret;
		 */
		// int off = (int) getRVAAddress(index - this.baseAddress);
		String temp = Convert.longToHex(readByte(index + 1), 8);
		temp += Convert.longToHex(readByte(index), 8);
		return Convert.hexToLong(temp);
	}

	public long readDoubleWord(int index) {
		// int ret = 0;
		/*
		 * ret = readByte(index); ret |= readByte(index + 1) << 8; ret |=
		 * readByte(index + 2) << 16; ret |= readByte(index + 3) << 24;
		 */
		// int off = (int) getRVAAddress(index - this.baseAddress);

		String temp = Convert.longToHex(readByte(index + 3), 8);
		temp += Convert.longToHex(readByte(index + 2), 8);
		temp += Convert.longToHex(readByte(index + 1), 8);
		temp += Convert.longToHex(readByte(index), 8);
		// int t = readByte(index + 3);
		return Convert.hexToLong(temp);
	}

	public long getProcAddress(String functionName) {
		// TODO Auto-generated method stub
		/*
		 * for (Entry<AbsoluteAddress, String> entry: exportTable.entrySet()) {
		 * String t[] = entry.getValueOperand().split("@"); if
		 * (t[0].equals(functionName)) return entry.getKey().getValueOperand();
		 * }
		 * 
		 * return 0;
		 */

		return peHandler.getExportAddress(functionName);
	}

	public boolean isInside(long addr) {
		return addr >= this.baseAddress && addr < (this.baseAddress + getLength());
	}

	public boolean isInside(AbsoluteAddress addr) {
		return this.isInside(addr.getValue());
	}

	public String getProcName(long v) {
		// TODO Auto-generated method stub
		/*
		 * String s=""; for (Entry<AbsoluteAddress, String> entry :
		 * exportTable.entrySet()) { if (entry.getKey().getValueOperand() == v)
		 * s = entry.getValueOperand(); } String d = peHandler.getExportName(v);
		 * if (!s.equals(d)) System.out.println("Debug");
		 */
		return peHandler.getExportName(v);
	}

	public String getLibraryName() {
		return libName;
	}

	public void setLibraryName(String libName) {
		this.libName = libName;
	}
}
