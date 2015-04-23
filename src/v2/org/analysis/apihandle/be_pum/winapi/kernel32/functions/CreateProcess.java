/**
 * Project: BE_PUM
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions
 * File name: CreateProcess.java
 * Created date: Jan 30, 2015
 * Description:
 */
package v2.org.analysis.apihandle.be_pum.winapi.kernel32.functions;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinBase.PROCESS_INFORMATION;
import com.sun.jna.platform.win32.WinBase.SECURITY_ATTRIBUTES;
import com.sun.jna.platform.win32.WinBase.STARTUPINFO;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.WORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.ByteByReference;
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
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.Value;

/**
 * 
 * Creates a new process and its primary thread. The new process runs in the
 * security context of the calling process.
 * 
 * @param lpApplicationName
 *            The name of the module to be executed.
 * @param lpCommandLine
 *            The command line to be executed.
 * @param lpProcessAttributes
 *            A pointer to a SECURITY_ATTRIBUTES structure that determines
 *            whether the returned handle to the new process object can be
 *            inherited by child processes. If lpProcessAttributes is NULL, the
 *            handle cannot be inherited.
 * 
 * @param lpThreadAttributes
 *            A pointer to a SECURITY_ATTRIBUTES structure that determines
 *            whether the returned handle to the new thread object can be
 *            inherited by child processes. If lpThreadAttributes is NULL, the
 *            handle cannot be inherited.
 * 
 * @param bInheritHandles
 *            If this parameter TRUE, each inheritable handle in the calling
 *            process is inherited by the new process. If the parameter is
 *            FALSE, the handles are not inherited. Note that inherited handles
 *            have the same value and access rights as the original handles.
 * 
 * @param dwCreationFlags
 *            The flags that control the priority class and the creation of the
 *            process.
 * @param lpEnvironment
 *            A pointer to the environment block for the new process. If this
 *            parameter is NULL, the new process uses the environment of the
 *            calling process.
 * 
 * @param lpCurrentDirectory
 *            The full path to the current directory for the process.
 * @param lpStartupInfo
 *            A pointer to a STARTUPINFO or STARTUPINFOEX structure.
 * @param lpProcessInformation
 *            A pointer to a PROCESS_INFORMATION structure that receives
 *            identification information about the new process.
 * @return If the function succeeds, the return value is nonzero.
 * 
 * @author Yen Nguyen
 *
 */
public class CreateProcess extends Kernel32API {

	public CreateProcess() {
	}

	@Override
	public boolean execute(AbsoluteAddress address, String funcName, BPState curState, Instruction inst) {

		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();

		Value x1 = stack.pop();
		Value x2 = stack.pop();
		Value x3 = stack.pop();
		Value x4 = stack.pop();
		Value x5 = stack.pop();
		Value x6 = stack.pop();
		Value x7 = stack.pop();
		Value x8 = stack.pop();
		Value x9 = stack.pop();
		Value x10 = stack.pop();
		System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " " + x4 + " " + x5 + " " + x6 + " " + x7 + " "
				+ x8 + " " + x9 + " " + x10);

		if (x1 instanceof LongValue && x2 instanceof LongValue && x3 instanceof LongValue && x4 instanceof LongValue
				&& x5 instanceof LongValue && x6 instanceof LongValue && x7 instanceof LongValue
				&& x8 instanceof LongValue && x9 instanceof LongValue && x10 instanceof LongValue) {
			/*
			 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
			 * x1).getValue(), ((ValueLongExp) x2).getValue(), program);
			 */
			long t1 = ((LongValue) x1).getValue();
			long t2 = ((LongValue) x2).getValue();
			long t3 = ((LongValue) x3).getValue();
			long t4 = ((LongValue) x4).getValue();
			long t5 = ((LongValue) x5).getValue();
			long t6 = ((LongValue) x6).getValue();
			long t7 = ((LongValue) x7).getValue();
			long t8 = ((LongValue) x8).getValue();
			long t9 = ((LongValue) x9).getValue();
			long t10 = ((LongValue) x10).getValue();

			String moduleFileName = memory.getText(new X86MemoryOperand(DataType.INT32, t1));
			String commandLine = memory.getText(new X86MemoryOperand(DataType.INT32, t2));
			String curDir = memory.getText(new X86MemoryOperand(DataType.INT32, t8));
			String pStarupInfo = memory.getText(new X86MemoryOperand(DataType.INT32, t9));
			System.out.println("Module File Name:" + moduleFileName + ", Command Line:" + commandLine
					+ ", Process Attribute Security:" + t3 + ", Thread Attribute Security:" + t4 + ", Handle Flag:"
					+ t5 + ", Creation Flag:" + t6 + ", Environemnt Block:" + t7 + ", Current Directory:" + curDir
					+ ", Process Starup Info:" + pStarupInfo + ", Process Information:" + t10);

			SECURITY_ATTRIBUTES lpProcessAttributes = null;
			SECURITY_ATTRIBUTES lpThreadAttributes = null;
			boolean bInheritHandles = (t5 != 0) ? true : false;
			DWORD dwCreationFlags = new DWORD(t6);
			Pointer lpEnvironment = Pointer.NULL;
			STARTUPINFO lpStartupInfo = null;
			PROCESS_INFORMATION lpProcessInformation = null;

			// TODO: t3 is the address of lpProcessAttributes structure pointer
			if (t3 != 0) {
				lpProcessAttributes = new SECURITY_ATTRIBUTES();

				LongValue nLength = (LongValue) memory.getDoubleWordMemoryValue(t3);
				LongValue lpSecurityDescriptor = (LongValue) memory.getDoubleWordMemoryValue(t3 + 4);
				LongValue bInheritHandle = (LongValue) memory.getDoubleWordMemoryValue(t3 + 8);

				// lpSecurityDescriptor is the address of SECURITY_DESCRIPTOR
				// structure pointer
				// SECURITY_DESCRIPTOR lpSecurityDescriptor = Pointer.NULL;
				// if (lpSecurityDescriptor.getValue() != 0) {
				// long pSecurityDescriptor = lpSecurityDescriptor.getValue();
				//
				// LongValue Revision = (LongValue)
				// memory.getByteMemoryValue(pSecurityDescriptor);
				// LongValue Sbz1 = (LongValue)
				// memory.getByteMemoryValue(pSecurityDescriptor += 1);
				// LongValue Control = (LongValue)
				// memory.getWordMemoryValue(pSecurityDescriptor += 1);
				// LongValue Owner = (LongValue)
				// memory.getDoubleWordMemoryValue(pSecurityDescriptor += 2);
				// LongValue Group = (LongValue)
				// memory.getDoubleWordMemoryValue(pSecurityDescriptor += 4);
				// // typedef struct _ACL {
				// // BYTE AclRevision;
				// // BYTE Sbz1;
				// // WORD AclSize;
				// // WORD AceCount;
				// // WORD Sbz2;
				// // } ACL;
				// LongValue Sacl = (LongValue)
				// memory.getDoubleWordMemoryValue(pSecurityDescriptor += 4);
				// LongValue Dacl = (LongValue)
				// memory.getDoubleWordMemoryValue(pSecurityDescriptor += 4);
				//
				// if (Sacl.getValue() != 0) {
				// long pSacl = Sacl.getValue();
				//
				// }
				// }

				lpProcessAttributes.bInheritHandle = (bInheritHandle.getValue() != 0) ? true : false;
				lpProcessAttributes.lpSecurityDescriptor = Pointer.NULL;
				lpProcessAttributes.dwLength = new DWORD(nLength.getValue());
			}

			// TODO: t4 is the address of lpThreadAttributes structure pointer
			if (t4 != 0) {
				lpThreadAttributes = new SECURITY_ATTRIBUTES();

				LongValue bInheritHandle = (LongValue) memory.getWordMemoryValue(t4);
				LongValue lpSecurityDescriptor = (LongValue) memory.getWordMemoryValue(t4 + 4);
				LongValue nLength = (LongValue) memory.getWordMemoryValue(t4 + 8);

				// TODO: lpSecurityDescriptor is the address of
				// SECURITY_DESCRIPTOR structure pointer

				lpThreadAttributes.bInheritHandle = (bInheritHandle.getValue() != 0) ? true : false;
				lpThreadAttributes.lpSecurityDescriptor = (lpSecurityDescriptor.getValue() != 0) ? new Pointer(
						lpSecurityDescriptor.getValue()) : Pointer.NULL;
				lpThreadAttributes.dwLength = new DWORD(nLength.getValue());
			}

			// t9 is the address of lpStartupInfo structure pointer
			if (t9 != 0) {
				lpStartupInfo = new STARTUPINFO();

				DWORD cb = new DWORD(((LongValue) memory.getDoubleWordMemoryValue(t9)).getValue());
				String lpReserved = memory.getText(new X86MemoryOperand(DataType.INT32, t9 = t9 + 4));
				String lpDesktop = memory.getText(new X86MemoryOperand(DataType.INT32, t9 = t9 + 4));
				String lpTitle = memory.getText(new X86MemoryOperand(DataType.INT32, t9 = t9 + 4));
				DWORD dwX = new DWORD(((LongValue) memory.getDoubleWordMemoryValue(t9 = t9 + 4)).getValue());
				DWORD dwY = new DWORD(((LongValue) memory.getDoubleWordMemoryValue(t9 = t9 + 4)).getValue());
				DWORD dwXSize = new DWORD(((LongValue) memory.getDoubleWordMemoryValue(t9 = t9 + 4)).getValue());
				DWORD dwYSize = new DWORD(((LongValue) memory.getDoubleWordMemoryValue(t9 = t9 + 4)).getValue());
				DWORD dwXCountChars = new DWORD(((LongValue) memory.getDoubleWordMemoryValue(t9 = t9 + 4)).getValue());
				DWORD dwYCountChars = new DWORD(((LongValue) memory.getDoubleWordMemoryValue(t9 = t9 + 4)).getValue());
				DWORD dwFillAttribute = new DWORD(((LongValue) memory.getDoubleWordMemoryValue(t9 = t9 + 4)).getValue());
				int dwFlags = (int) ((LongValue) memory.getDoubleWordMemoryValue(t9 = t9 + 4)).getValue();
				WORD wShowWindow = new WORD(((LongValue) memory.getWordMemoryValue(t9 = t9 + 4)).getValue());
				WORD cbReserved2 = new WORD(((LongValue) memory.getWordMemoryValue(t9 = t9 + 2)).getValue());
				ByteByReference lpReserved2 = new ByteByReference(
						(byte) ((LongValue) memory.getByteMemoryValue(t9 = t9 + 2)).getValue());
				HANDLE hStdInput = new HANDLE(new Pointer(
						((LongValue) memory.getWordMemoryValue(t9 = t9 + 1)).getValue()));
				HANDLE hStdOutput = new HANDLE(new Pointer(
						((LongValue) memory.getWordMemoryValue(t9 = t9 + 2)).getValue()));
				HANDLE hStdError = new HANDLE(new Pointer(
						((LongValue) memory.getWordMemoryValue(t9 = t9 + 2)).getValue()));

				lpStartupInfo.cb = cb;
				lpStartupInfo.lpReserved = lpReserved;
				lpStartupInfo.lpDesktop = lpDesktop;
				lpStartupInfo.lpTitle = lpTitle;
				lpStartupInfo.dwX = dwX;
				lpStartupInfo.dwY = dwY;
				lpStartupInfo.dwXSize = dwXSize;
				lpStartupInfo.dwYSize = dwYSize;
				lpStartupInfo.dwXCountChars = dwXCountChars;
				lpStartupInfo.dwYCountChars = dwYCountChars;
				lpStartupInfo.dwFillAttribute = dwFillAttribute;
				lpStartupInfo.dwFlags = dwFlags;
				lpStartupInfo.wShowWindow = wShowWindow;
				lpStartupInfo.cbReserved2 = cbReserved2;
				lpStartupInfo.lpReserved2 = lpReserved2;
				lpStartupInfo.hStdInput = hStdInput;
				lpStartupInfo.hStdOutput = hStdOutput;
				lpStartupInfo.hStdError = hStdError;
			}

			// t10 is the address of lpProcessInformation structure pointer
			if (t10 != 0) {
				lpProcessInformation = new PROCESS_INFORMATION();

				HANDLE hProcess = new HANDLE(new Pointer(((LongValue) memory.getDoubleWordMemoryValue(t10)).getValue()));
				HANDLE hThread = new HANDLE(new Pointer(
						((LongValue) memory.getDoubleWordMemoryValue(t10 = t10 + 4)).getValue()));
				DWORD dwProcessId = new DWORD(((LongValue) memory.getDoubleWordMemoryValue(t10 = t10 + 4)).getValue());
				DWORD dwThreadId = new DWORD(((LongValue) memory.getDoubleWordMemoryValue(t10 = t10 + 4)).getValue());

				lpProcessInformation.hProcess = hProcess;
				lpProcessInformation.hThread = hThread;
				lpProcessInformation.dwProcessId = dwProcessId;
				lpProcessInformation.dwThreadId = dwThreadId;
			}

			boolean ret = Kernel32.INSTANCE.CreateProcess(moduleFileName, commandLine, lpProcessAttributes,
					lpThreadAttributes, bInheritHandles, dwCreationFlags, lpEnvironment, curDir, lpStartupInfo,
					lpProcessInformation);

			register.mov("eax", new LongValue((ret) ? 1 : 0));
		}
		return false;
	}
}
