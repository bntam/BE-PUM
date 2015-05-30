package v2.org.analysis;
import capstone.Capstone;

public class CapstoneMain {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Path filePath = Paths.get("virus/Virus.Win32.Aztec.01");
		//byte[] opCode = {(byte)0x0f, (byte)0x6f, (byte)0xe0, (byte)0x90, (byte)0x90, (byte)0x90};
		byte[] opCode = {(byte)0x64, (byte)0x67, (byte)0xff, (byte)0x36, (byte)0x00, (byte)0x00};
//		try {
//			opCode = Files.readAllBytes(filePath);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Capstone cs = new Capstone(Capstone.CS_ARCH_X86, Capstone.CS_MODE_32);
		Capstone.CsInsn[] allInsn = cs.disasm(opCode, 0x00400000);
		for (int i = 0; i < allInsn.length; i++) {
			System.out.printf("%d\t0x%x:\t%s\t%s\n",i+1, allInsn[i].address,
					allInsn[i].mnemonic, allInsn[i].opStr);
		}
	}
}
