/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: CreateFile.java
 * Created date: Feb 6, 2015
 * Description:
 */
package v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import v2.org.analysis.apihandle.be_pum.winapi.kernel32.Kernel32API;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86MemoryOperand;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.system.Storage;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * The CreateFile function creates or opens a file, file stream, directory,
 * physical disk, volume, console buffer, tape drive, communications resource,
 * mailslot, or named pipe. The function returns a handle that can be used to
 * access an object.
 * 
 * @param lpFileName
 *            A pointer to a null-terminated string that specifies the name of
 *            an object to create or open.
 * 
 * @param dwDesiredAccess
 *            The access to the object, which can be read, write, or both.
 * 
 * @param dwShareMode
 *            The sharing mode of an object, which can be read, write, both, or
 *            none.
 * 
 * @param lpSecurityAttributes
 *            A pointer to a SECURITY_ATTRIBUTES structure that determines
 *            whether or not the returned handle can be inherited by child
 *            processes. If lpSecurityAttributes is NULL, the handle cannot be
 *            inherited.
 * 
 * @param dwCreationDisposition
 *            An action to take on files that exist and do not exist.
 * 
 * @param dwFlagsAndAttributes
 *            The file attributes and flags.
 * 
 * @param hTemplateFile
 *            Handle to a template file with the GENERIC_READ access right. The
 *            template file supplies file attributes and extended attributes for
 *            the file that is being created. This parameter can be NULL.
 * 
 * @return If the function succeeds, the return value is an open handle to a
 *         specified file. If a specified file exists before the function call
 *         and dwCreationDisposition is CREATE_ALWAYS or OPEN_ALWAYS, a call to
 *         GetLastError returns ERROR_ALREADY_EXISTS, even when the function
 *         succeeds. If a file does not exist before the call, GetLastError
 *         returns 0 (zero). If the function fails, the return value is
 *         INVALID_HANDLE_VALUE. To get extended error information, call
 *         GetLastError.
 * 
 * @author Yen Nguyen
 *
 */
public class CreateFile extends Kernel32API {

	public CreateFile() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();
		Register register2 = env.getRegister();

		Value x1 = stack.pop();
		Value x2 = stack.pop();
		Value x3 = stack.pop();
		Value x4 = stack.pop();
		Value x5 = stack.pop();
		Value x6 = stack.pop();
		Value x7 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5 + " " + x6 + " " + x7);

		if (x1 instanceof LongValue && x2 instanceof LongValue && x3 instanceof LongValue && x4 instanceof LongValue
				&& x5 instanceof LongValue && x6 instanceof LongValue && x7 instanceof LongValue) {
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			long t3 = ((LongValue) x3).getValue();
			long t4 = ((LongValue) x4).getValue();
			long t5 = ((LongValue) x5).getValue();
			long t6 = ((LongValue) x6).getValue();
			long t7 = ((LongValue) x7).getValue();
			String fileName = memory.getText(new X86MemoryOperand(DataType.INT32, t1));
			fileName = Storage.getMappingPath(fileName);
			System.out.println("FileName:" + fileName + ", Access:" + t2 + ", ShareMode:" + t3 + ", pSecurity:" + t4
					+ ", Mode:" + t5 + ", Attributes:" + t6 + ", hTemplate:" + t7);

			// SECURITY_ATTRIBUTES lpSecurityAttributes = null;
			// if (t4 != 0) {
			// lpSecurityAttributes = new SECURITY_ATTRIBUTES();
			//
			// LongValue nLength = (LongValue)
			// memory.getDoubleWordMemoryValue(t4);
			// LongValue lpSecurityDescriptor = (LongValue)
			// memory.getDoubleWordMemoryValue(t4 + 4);
			// LongValue bInheritHandle = (LongValue)
			// memory.getDoubleWordMemoryValue(t4 + 8);
			//
			// // lpSecurityDescriptor is the address of SECURITY_DESCRIPTOR
			// // structure pointer
			// SECURITY_DESCRIPTOR lpSecurityDescriptor = Pointer.NULL;
			// if (lpSecurityDescriptor.getValue() != 0) {
			//
			// long pSecurity = t4;
			//
			// LongValue Revision = (LongValue)
			// memory.getByteMemoryValue(pSecurity);
			// LongValue Sbz1 = (LongValue) memory.getByteMemoryValue(pSecurity
			// += 1);
			// LongValue Control = (LongValue)
			// memory.getWordMemoryValue(pSecurity += 1);
			// LongValue Owner = (LongValue)
			// memory.getDoubleWordMemoryValue(pSecurity += 2);
			// LongValue Group = (LongValue)
			// memory.getDoubleWordMemoryValue(pSecurity += 4);
			// // typedef struct _ACL {
			// // BYTE AclRevision;
			// // BYTE Sbz1;
			// // WORD AclSize;
			// // WORD AceCount;
			// // WORD Sbz2;
			// // } ACL;
			// LongValue Sacl = (LongValue)
			// memory.getDoubleWordMemoryValue(pSecurity += 4);
			// LongValue Dacl = (LongValue)
			// memory.getDoubleWordMemoryValue(pSecurity += 4);
			//
			// ACL vSacl = null;
			// if (Sacl.getValue() != 0) {
			// long pSacl = Sacl.getValue();
			//
			// }
			//
			// lpSecurityAttributes.
			// }

			HANDLE ret = Kernel32.INSTANCE.CreateFile(fileName, (int) t2, (int) t3, null, (int) t5, (int) t6,
					new HANDLE(new Pointer(t7)));
			register.mov("eax", new LongValue(Pointer.nativeValue(ret.getPointer())));
			System.out.println("Return value:" + Pointer.nativeValue(ret.getPointer()));
		}

		return false;
	}
}
