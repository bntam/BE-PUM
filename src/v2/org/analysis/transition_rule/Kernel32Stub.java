package v2.org.analysis.transition_rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;




//import org.analysis.api_stub.APIHandler;
import org.jakstab.Program;
import org.jakstab.asm.AbsoluteAddress;
import org.jakstab.asm.DataType;
import org.jakstab.asm.Instruction;
import org.jakstab.asm.x86.X86CondJmpInstruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.cfg.AddressList;
import v2.org.analysis.cfg.BPCFG;
import v2.org.analysis.cfg.BPEdge;
import v2.org.analysis.cfg.BPVertex;
import v2.org.analysis.environment.Environment;
import v2.org.analysis.environment.Memory;
import v2.org.analysis.environment.Register;
import v2.org.analysis.environment.Stack;
import v2.org.analysis.path.BPState;
import v2.org.analysis.system.SystemHandle;
import v2.org.analysis.system.VirtualMemory;
import v2.org.analysis.value.AnyValue;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.SymbolValue;
import v2.org.analysis.value.Value;

public class Kernel32Stub extends APIStub {
	private String libraryName = "kernel32.dll";
	private List<String> checkedAPI = new ArrayList<String>();

	public Kernel32Stub() {
		super();
	}
	
	private void simulateAPI(AbsoluteAddress address, String funcName,
			BPState curState, Instruction inst) {
		long returnValue = 0;
		//boolean ret = true;
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();
		Program program = Program.getProgram();
		SystemHandle system = env.getSystem();
		//BPCFG cfg = program.getBPCFG();
		
		if (funcName.startsWith("RtlUnwind")) {
			/*
			 * HANDLE hFileMappingObject, // file-mapping object to map into
			 * address space DWORD dwDesiredAccess, // access mode DWORD
			 * dwFileOffsetHigh, // high-order 32 bits of file offset DWORD
			 * dwFileOffsetLow, // low-order 32 bits of file offset DWORD
			 * dwNumberOfBytesToMap // number of bytes to map
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4);

			// symbolValueRegister.mov("eax", 1);			
		} else if (funcName.startsWith("UnhandledExceptionFilter")) {
			Value x1 = stack.pop();								
			System.out.println("Argument:" + x1);
			// symbolValueRegister.mov("eax", 1);
			register.mov("eax", new SymbolValue("api_eax"));
		} else if (funcName.startsWith("SetThreadLocal")) {
			Value x1 = stack.pop();								
			System.out.println("Argument:" + x1);
			// symbolValueRegister.mov("eax", 1);
			if (x1 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				long t1 = ((LongValue) x1).getValue();
												
				// String str = symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32, t2));
				System.out.println("Local Identifier:" + t1);
				
				System.out.println("Return Value: 1");
				register.mov("eax", 1);				
			}

		} else if (funcName.startsWith("LocalFree")) {
			Value x1 = stack.pop();								
			System.out.println("Argument:" + x1);
			// symbolValueRegister.mov("eax", 1);
			if (x1 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				long t1 = ((LongValue) x1).getValue();
												
				// String str = symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32, t2));
				System.out.println("Handle:" + t1);
				
				System.out.println("Return Value: 0");
				register.mov("eax", 0);				
			}

		} else 
		if (funcName.startsWith("FormatMessageA")) {
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			Value x5 = stack.pop();
			Value x6 = stack.pop();
			Value x7 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4 + " " + x5 + " " + x6 + " " + x7);

			if (x1 instanceof LongValue && x2 instanceof LongValue
					&& x3 instanceof LongValue && x4 instanceof LongValue
					&& x5 instanceof LongValue && x6 instanceof LongValue
					&& x7 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				long t1 = ((LongValue) x1).getValue();
				long t2 = ((LongValue) x2).getValue();
				long t3 = ((LongValue) x3).getValue();
				long t4 = ((LongValue) x4).getValue();
				long t5 = ((LongValue) x5).getValue();
				long t6 = ((LongValue) x6).getValue();
				long t7 = ((LongValue) x7).getValue();

				/*
				 * String fileMapName = symbolValueMemoryOperand
				 * .getText(new X86MemoryOperand(DataType.INT32, t6));
				 */
				// String str = symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32, t2));
				System.out.println("Flag:" + t1
						+ ", Source:" + t2
						+ ", Message Field:" + t3 + ", Language:"
						+ t4 + ", Buffer:" + t5
						+ ", Size:" + t6 + ", Argument" + t7);
				long t = 1;
				register.mov("eax", new LongValue(t));
				System.out.println("Return Value:" + t);
			} 

		} else if (funcName.startsWith("SetFileTime")) {
			/*
			 * HANDLE hFileMappingObject, // file-mapping object to map into
			 * address space DWORD dwDesiredAccess, // access mode DWORD
			 * dwFileOffsetHigh, // high-order 32 bits of file offset DWORD
			 * dwFileOffsetLow, // low-order 32 bits of file offset DWORD
			 * dwNumberOfBytesToMap // number of bytes to map
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4);

			// symbolValueRegister.mov("eax", 1);
			if (x1 instanceof LongValue && x2 instanceof LongValue
					&& x3 instanceof LongValue && x4 instanceof LongValue
					) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				long t1 = ((LongValue) x1).getValue();
				long t2 = ((LongValue) x2).getValue();
				long t3 = ((LongValue) x3).getValue();
				long t4 = ((LongValue) x4).getValue();
				
				// String str = symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32, t2));
				System.out.println("File Handle:" + t1 + ", Time Created:"
						+ t2 + ", Time Access:" + t3
						+ ", Time Written :" + t4);
				
				System.out.println("Return Value: " + 1);
				register.mov("eax", new LongValue(1));
			}
		} else if (funcName.startsWith("GetTickCount")) {
			long time = System.currentTimeMillis();
			System.out.println("Return Value: " + time);
			register.mov("eax", new LongValue(time));
		} else if (funcName.startsWith("OutputDebugString")) {
			Value x1 = stack.pop();
								
			System.out.println("Argument:" + x1);

			// symbolValueRegister.mov("eax", 1);
			if (x1 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				long t1 = ((LongValue) x1).getValue();
												
				// String str = symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32, t2));
				System.out.println("Pointer String:" + t1 + ", String:"
						+ memory.getText(t1));
				
				System.out.println("Return Value: Null");
				//register.mov("eax", new LongValue(1));				
			}

		} else if (funcName.startsWith("CheckRemoteDebuggerPresent")) {
			Value x1 = stack.pop();
			Value x2 = stack.pop();
						
			System.out.println("Argument:" + x1 + " " + x2);

			// symbolValueRegister.mov("eax", 1);
			if (x1 instanceof LongValue && x2 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				long t1 = ((LongValue) x1).getValue();
				long t2 = ((LongValue) x2).getValue();
								
				// String str = symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32, t2));
				System.out.println("Handle Process:" + t1 + ", Pointer to Debugger:"
						+ t2);
				
				System.out.println("Return Value: " + 1);
				register.mov("eax", new LongValue(1));
				memory.setMemoryValue(new X86MemoryOperand(DataType.INT32, t2), new LongValue(0), inst);
			}

		} else if (funcName.startsWith("GetLastError")) {
			System.out.println("Return Value: " + 87);
			register.mov("eax", new LongValue(87));
		} else if (funcName.startsWith("Beep")) {
			Value x1 = stack.pop();
			Value x2 = stack.pop();
						
			System.out.println("Argument:" + x1 + " " + x2);

			// symbolValueRegister.mov("eax", 1);
			if (x1 instanceof LongValue && x2 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				long t1 = ((LongValue) x1).getValue();
				long t2 = ((LongValue) x2).getValue();
								
				// String str = symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32, t2));
				System.out.println("Duration:" + t1 + ", Frequency:"
						+ t2);
				
				System.out.println("Return Value: " + 1);
				register.mov("eax", new LongValue(1));
			}

		} else if (funcName.startsWith("VirtualProtect")) {
			/*
			 * HANDLE hFileMappingObject, // file-mapping object to map into
			 * address space DWORD dwDesiredAccess, // access mode DWORD
			 * dwFileOffsetHigh, // high-order 32 bits of file offset DWORD
			 * dwFileOffsetLow, // low-order 32 bits of file offset DWORD
			 * dwNumberOfBytesToMap // number of bytes to map
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4);

			// symbolValueRegister.mov("eax", 1);
			if (x1 instanceof LongValue && x2 instanceof LongValue
					&& x3 instanceof LongValue && x4 instanceof LongValue
					) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				long t1 = ((LongValue) x1).getValue();
				long t2 = ((LongValue) x2).getValue();
				long t3 = ((LongValue) x3).getValue();
				long t4 = ((LongValue) x4).getValue();
				
				// String str = symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32, t2));
				System.out.println("Old Protect:" + t1 + ", New Protect:"
						+ t2 + ", Size:" + t3
						+ ", Address :" + t4);
				
				System.out.println("Return Value: " + 1);
				register.mov("eax", new LongValue(1));
			}

		} else if (funcName.startsWith("GetCurrentProcess")) {
			// HMODULE hModule, handle to DLL module
			// LPCSTR lpProcName name of function

			System.out.println("Argument: No");
			program.generageCFG(program.getAbsolutePathFile() + "_test");

			register.mov("eax", new LongValue(system.getCurrentProcess()));
		} else if (funcName.startsWith("WinExec")) {
			// HMODULE hModule, handle to DLL module
			// LPCSTR lpProcName name of function
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " ");

			if (x1 instanceof LongValue && x2 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				String commandLine = memory.getText(new X86MemoryOperand(
						DataType.INT32, ((LongValue) x1).getValue()));
				System.out.println("Command Line:" + commandLine
						+ ", Window Style:" + ((LongValue) x2).getValue());
				register.mov(
						"eax",
						new LongValue(system.getWindowHandle()
								.createWindow(commandLine,
										((LongValue) x2).getValue())));

			}
		} else if (funcName.startsWith("CreateProcess")) {
			/*
			 * LPCTSTR lpApplicationName, // pointer to name of executable
			 * module LPTSTR lpCommandLine, // pointer to command line
			 * string LPSECURITY_ATTRIBUTES lpProcessAttributes, // pointer
			 * to process security attributes LPSECURITY_ATTRIBUTES
			 * lpThreadAttributes, // pointer to thread security attributes
			 * BOOL bInheritHandles, // handle inheritance flag DWORD
			 * dwCreationFlags, // creation flags LPVOID lpEnvironment, //
			 * pointer to new environment block LPCTSTR lpCurrentDirectory,
			 * // pointer to current directory name LPSTARTUPINFO
			 * lpStartupInfo, // pointer to STARTUPINFO
			 * LPPROCESS_INFORMATION lpProcessInformation // pointer to
			 * PROCESS_INFORMATION
			 */
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
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4 + " " + x5 + " " + x6 + " " + x7 + " " + x8 + " "
					+ x9 + " " + x10);

			if (x1 instanceof LongValue && x2 instanceof LongValue
					&& x3 instanceof LongValue && x4 instanceof LongValue
					&& x5 instanceof LongValue && x6 instanceof LongValue
					&& x7 instanceof LongValue && x8 instanceof LongValue
					&& x9 instanceof LongValue && x10 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				long t1 = ((LongValue) x1).getValue();
				long t2 = ((LongValue) x2).getValue();
				long t3 = ((LongValue) x3).getValue();
				long t4 = ((LongValue) x4).getValue();
				long t5 = ((LongValue) x5).getValue();
				long t6 = ((LongValue) x1).getValue();
				long t7 = ((LongValue) x2).getValue();
				long t8 = ((LongValue) x3).getValue();
				long t9 = ((LongValue) x4).getValue();
				long t10 = ((LongValue) x5).getValue();

				String moduleFileName = memory
						.getText(new X86MemoryOperand(DataType.INT32, t1));
				String commandLine = memory.getText(new X86MemoryOperand(
						DataType.INT32, t2));
				String curDir = memory.getText(new X86MemoryOperand(
						DataType.INT32, t8));
				String pStarupInfo = memory.getText(new X86MemoryOperand(
						DataType.INT32, t9));
				System.out.println("Module File Name:" + moduleFileName
						+ ", Command Line:" + commandLine
						+ ", Process Attribute Security:" + t3
						+ ", Thread Attribute Security:" + t4
						+ ", Handle Flag:" + t5 + ", Creation Flag:" + t6
						+ ", Environemnt Block:" + t7
						+ ", Current Directory:" + curDir
						+ ", Process Starup Info:" + pStarupInfo
						+ ", Process Information:" + t10);
				register.mov(
						"eax",
						new LongValue(system.getProcessHandle()
								.createProcess(moduleFileName, commandLine,
										t3, t4, t5, t6, t7, curDir,
										pStarupInfo, t10)));
			}
		} else if (funcName.startsWith("GetFileAttributes")) {
			// HANDLE hFindFile // file search handle
			Value x1 = stack.pop();
			System.out.println("Argument:" + x1);

			if (x1 instanceof LongValue) {
				String fName = memory.getText(new X86MemoryOperand(
						DataType.INT32, ((LongValue) x1).getValue()));
				long attr = system.getFileAttribute(fName);
				register.mov("eax", new LongValue(attr));

				System.out.println("Return Value:" + attr);
			}

		} else if (funcName.startsWith("GetProcAddress")) {
			// HMODULE hModule, handle to DLL module
			// LPCSTR lpProcName name of function
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " ");

			if (x1 instanceof LongValue && x2 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				String functionName = memory.getText(new X86MemoryOperand(
						DataType.INT32, ((LongValue) x2).getValue()));
				System.out.println("Function Name:" + functionName
						+ ", Library Handle:" + x1);
				long t = 0;
				if (functionName != "")
					t = system.getProcAddress(((LongValue) x1).getValue(),
						functionName);
				else
					t = system.getProcAddress(((LongValue) x1).getValue(),
							((LongValue) x2).getValue());
				register.mov("eax", new LongValue(t));
				System.out.println("Return Value: " + t);

			}

		} else if (funcName.startsWith("LoadLibraryA")) {
			// LPCTSTR lpLibFileName // address of filename of executable
			// module
			Value x1 = stack.pop();
			System.out.print("Argument:" + x1);
			if (x1 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.loadLibraryA( ((ValueLongExp)
				 * x1).getValueOperand(), program);
				 */
				String libraryName = memory.getText(new X86MemoryOperand(
						DataType.INT32, ((LongValue) x1).getValue()));
				System.out.println(" Library Name:" + libraryName);
				long t = system.getLibraryHandle(libraryName);
				register.mov("eax", new LongValue(t));
				System.out.println("Return value: " + t);
			}
		} else if (funcName.startsWith("SetFilePointer")) {
			/*
			 * HANDLE hFile, // handle of file LONG lDistanceToMove, //
			 * number of bytes to move file pointer PLONG
			 * lpDistanceToMoveHigh, // address of high-order word of
			 * distance to move DWORD dwMoveMethod // how to move
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4 + " ");

			if (x1 instanceof LongValue && x2 instanceof LongValue
					&& x3 instanceof LongValue && x4 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				long t1 = ((LongValue) x1).getValue();
				long t2 = ((LongValue) x2).getValue();
				long t3 = ((LongValue) x3).getValue();
				long t4 = ((LongValue) x4).getValue();

				// String str = symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32, t2));
				System.out.println("Handle File:" + t1
						+ ", Number of Bytes:" + t2
						+ ", Address of High-Order:" + t3 + ", Move Type:"
						+ t4);
				register.mov("eax", new LongValue(system.getFileHandle()
						.setFilePointer(t1, t2, t3, t4)));
			}

		} else if (funcName.startsWith("FindClose")) {
			// HANDLE hFindFile // file search handle
			Value x1 = stack.pop();
			System.out.println("Argument:" + x1);

			if (x1 instanceof LongValue) {
				long t = ((LongValue) x1).getValue();

				register.mov("eax", new LongValue(system.getFileHandle()
						.closeFind(t)));
			}

		} else if (funcName.startsWith("DeleteFileA")) {
			// LPCTSTR lpFileName // pointer to name of file to delete
			Value x1 = stack.pop();
			System.out.println("Argument:" + x1);

			if (x1 instanceof LongValue) {
				String fName = memory.getText(new X86MemoryOperand(
						DataType.INT32, ((LongValue) x1).getValue()));
				register.mov("eax", new LongValue(system.getFileHandle()
						.deleteFile(fName)));
			}

		} else if (funcName.startsWith("GetSystemTime")) {
			// LPSYSTEMTIME lpSystemTime // address of system time structure
			Value x1 = stack.pop();
			System.out.println("Memory Address:" + x1.toString());

			if (x1 instanceof LongValue) {
				LongValue x = (LongValue) x1;

				memory.setText(
						new X86MemoryOperand(DataType.INT32, x.getValue()),
						new AnyValue().toString());
				// symbolValueMemoryOperand.setText(new
				// X86MemoryOperand(DataType.INT32, x.getValueOperand()),
				// String.valueOf(System.currentTimeMillis()));
			}

		} else if (funcName.startsWith("GetVersionExA")) {
			// This function has no parameters.

			long verNum = 498139398;
			System.out.println("Version Number:" + verNum);

			register.mov("eax", new LongValue(verNum));

		} else if (funcName.startsWith("MapViewOfFile")) {
			/*
			 * HANDLE hFileMappingObject, // file-mapping object to map into
			 * address space DWORD dwDesiredAccess, // access mode DWORD
			 * dwFileOffsetHigh, // high-order 32 bits of file offset DWORD
			 * dwFileOffsetLow, // low-order 32 bits of file offset DWORD
			 * dwNumberOfBytesToMap // number of bytes to map
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			Value x5 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4 + " " + x5);

			// symbolValueRegister.mov("eax", 1);
			if (x1 instanceof LongValue && x2 instanceof LongValue
					&& x3 instanceof LongValue && x4 instanceof LongValue
					&& x5 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				long t1 = ((LongValue) x1).getValue();
				long t2 = ((LongValue) x2).getValue();
				long t3 = ((LongValue) x3).getValue();
				long t4 = ((LongValue) x4).getValue();
				long t5 = ((LongValue) x5).getValue();

				// String str = symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32, t2));
				System.out.println("Handle File:" + t1 + ", Access Mode:"
						+ t2 + ", High-order 32 bits of file offset:" + t3
						+ ", Low-order 32 bits of file offset :" + t4
						+ ", Number of bytes to map:" + t5);
				long v = system.getFileHandle().mapViewOfFile(t1, t2, t3,
						t4, t5);
				System.out.println("Base Address: " + v);
				register.mov("eax", new LongValue(v));
			}

		} else if (funcName.startsWith("ReadFile")) {
			/*
			 * HANDLE hFile, // handle of file to read LPVOID lpBuffer, //
			 * address of buffer that receives data DWORD
			 * nNumberOfBytesToRead, // number of bytes to read LPDWORD
			 * lpNumberOfBytesRead, // address of number of bytes read
			 * LPOVERLAPPED lpOverlapped // address of structure for data
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			Value x5 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4 + " " + x5);

			if (x1 instanceof LongValue && x2 instanceof LongValue
					&& x3 instanceof LongValue && x4 instanceof LongValue
					&& x5 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				long t1 = ((LongValue) x1).getValue();
				long t2 = ((LongValue) x2).getValue();
				long t3 = ((LongValue) x3).getValue();
				long t4 = ((LongValue) x4).getValue();
				long t5 = ((LongValue) x5).getValue();

				// String str = symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32, t2));
				System.out.println("Handle File:" + t1
						+ ", Address of Buffer:" + t2 + ", Number of Byte:"
						+ t3 + ", Number of Actual Read Bytes:" + t4
						+ ", Overlapped:" + t5);
				String str = system.getFileHandle().readFile(t1, t2, t3,
						t4, t5);
				if (str != null) {
					memory.setText(
							new X86MemoryOperand(DataType.INT32, t2), str);
					register.mov("eax", new LongValue(1));
				} else
					register.mov("eax", new LongValue(0));
			}
		} else if (funcName.startsWith("UnmapViewOfFile")) {
			// LPCVOID lpBaseAddress // address where mapped view begins
			Value x1 = stack.pop();
			System.out.println("Argument:" + x1);
			register.mov("eax", new LongValue(1));
		} else if (funcName.startsWith("WriteFile")) {
			/*
			 * HANDLE hFile, // handle to file to write to LPCVOID lpBuffer,
			 * // pointer to data to write to file DWORD
			 * nNumberOfBytesToWrite, // number of bytes to write LPDWORD
			 * lpNumberOfBytesWritten, // pointer to number of bytes written
			 * LPOVERLAPPED lpOverlapped // pointer to structure needed for
			 * overlapped I/O
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			Value x5 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4 + " " + x5);

			if (x1 instanceof LongValue && x2 instanceof LongValue
					&& x3 instanceof LongValue && x4 instanceof LongValue
					&& x5 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				long t1 = ((LongValue) x1).getValue();
				long t2 = ((LongValue) x2).getValue();
				long t3 = ((LongValue) x3).getValue();
				long t4 = ((LongValue) x4).getValue();
				long t5 = ((LongValue) x5).getValue();

				String str = memory.getText(new X86MemoryOperand(
						DataType.INT32, t2));
				System.out.println("Handle File:" + t1
						+ ", String written:" + str + ", Number of Byte:"
						+ t3 + ", Pointer:" + t4 + ", Overlapped:" + t5);
				register.mov("eax", new LongValue(system.getFileHandle()
						.writeFile(t1, str, t3, t4, t5)));
			}
		} else if (funcName.startsWith("RegSetValueExA")) {
			/*
			 * HKEY hKey, // handle of key to set value for LPCTSTR
			 * lpValueName, // address of value to set DWORD Reserved, //
			 * reserved DWORD dwType, // flag for value type CONST BYTE
			 * *lpData, // address of value data DWORD cbData // size of
			 * value data
			 */

			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			Value x5 = stack.pop();
			Value x6 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4 + " " + x5 + " " + x6);

		} else if (funcName.startsWith("SetEndOfFile")) {
			// HANDLE hFile // handle of file whose EOF is to be set
			Value x1 = stack.pop();

			System.out.println("Argument:" + x1);

			if (x1 instanceof LongValue) {
				long t1 = ((LongValue) x1).getValue();

				System.out.println("Handle of file:" + t1);
				register.mov("eax", new LongValue(system.getFileHandle()
						.setEndOfFile(t1)));
			}

		} else if (funcName.startsWith("WaitForSingleObject")) {
			/*
			 * HANDLE hHandle, // handle of object to wait for DWORD
			 * dwMilliseconds // time-out interval in milliseconds
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " ");

		} else if (funcName.startsWith("PostMessageA")) {
			/*
			 * HWND hWnd, // handle of destination window UINT Msg, //
			 * message to post WPARAM wParam, // first message parameter
			 * LPARAM lParam // second message parameter
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4);

			if (x1 instanceof LongValue && x2 instanceof LongValue
					&& x3 instanceof LongValue && x4 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				long t1 = ((LongValue) x1).getValue();
				long t2 = ((LongValue) x2).getValue();
				long t3 = ((LongValue) x3).getValue();
				long t4 = ((LongValue) x4).getValue();

				String str = memory.getText(new X86MemoryOperand(
						DataType.INT32, t2));
				String str1 = memory.getText(new X86MemoryOperand(
						DataType.INT32, t1));
				System.out.println("Handle Window:" + t1 + " " + str1
						+ ", Post Message:" + str + ", First Param:" + t3
						+ ", Second Param:" + t4);
				register.mov("eax", new LongValue(system.getWindowHandle()
						.postMessage(t1, str, t3, t4)));
			}

		} else if (funcName.startsWith("lstrcatA")) {
			// LPTSTR lpString1, // address of buffer for concatenated
			// strings
			// LPCTSTR lpString2 // address of string to add to string1
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " ");

			if (x1 instanceof LongValue && x2 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				long destAddr = ((LongValue) x1).getValue();
				String dest = memory.getText(new X86MemoryOperand(
						DataType.INT32, ((LongValue) x1).getValue()));
				String src = memory.getText(new X86MemoryOperand(
						DataType.INT32, ((LongValue) x2).getValue()));
				dest = dest.concat(src);
				System.out.println("Destination Address:" + destAddr
						+ ", Source String:" + src);
				memory.setText(new X86MemoryOperand(DataType.INT32,
						destAddr), dest);
				register.mov("eax", new LongValue(1));
			}

		} else if (funcName.startsWith("lstrcmpA")) {
			// LPCTSTR lpString1, // address of first string
			// LPCTSTR lpString2 // address of second string
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " ");

			if (x1 instanceof LongValue && x2 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				// long destAddr = ((ValueLongExp) x1).getValueOperand();
				String dest = memory.getText(new X86MemoryOperand(
						DataType.INT32, ((LongValue) x1).getValue()));
				String src = memory.getText(new X86MemoryOperand(
						DataType.INT32, ((LongValue) x2).getValue()));
				// dest += src;
				System.out.println("Destination String:" + dest
						+ ", Source String:" + src);
				// symbolValueMemoryOperand.setText(new
				// X86MemoryOperand(DataType.INT32, destAddr), dest);
				register.mov("eax", new LongValue(dest.compareTo(src)));
			}

		} else if (funcName.startsWith("lstrlenA")) {
			// LPCTSTR lpString // address of string to count
			Value x1 = stack.pop();
			// Exp x2 = symbolStack.pop();
			System.out.println("Argument:" + x1);

			if (x1 instanceof LongValue) {
				String dest = memory.getText(new X86MemoryOperand(
						DataType.INT32, ((LongValue) x1).getValue()));
				System.out.println("Destination String:" + dest);
				register.mov("eax", new LongValue(dest.length()));
			}

		} else if (funcName.startsWith("MoveFileA")) {
			// LPCTSTR lpExistingFileName, // address of name of the
			// existing file
			// LPCTSTR lpNewFileName // address of new name for the file
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " ");

			if (x1 instanceof LongValue && x2 instanceof LongValue) {
				long t1 = ((LongValue) x1).getValue();
				long t2 = ((LongValue) x2).getValue();

				String fileNameOld = memory.getText(new X86MemoryOperand(
						DataType.INT32, t1));
				String fileNameNew = memory.getText(new X86MemoryOperand(
						DataType.INT32, t2));
				System.out.println("Old File:" + fileNameOld
						+ ", New File:" + fileNameNew);

				register.mov("eax", new LongValue(system.getFileHandle()
						.moveFile(fileNameOld, fileNameNew)));

			}

		} else if (funcName.startsWith("RegCloseKey")) {
			// HKEY hKey // handle of key to close
			Value x1 = stack.pop();
			System.out.println("Argument:" + x1 + " ");

		} else if (funcName.startsWith("RegOpenKeyExA")) {
			/*
			 * HKEY hKey, // handle of open key LPCTSTR lpSubKey, // address
			 * of name of subkey to open DWORD ulOptions, // reserved REGSAM
			 * samDesired, // security access mask PHKEY phkResult //
			 * address of handle of open key
			 */

			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			Value x5 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4 + " " + x5);

		} else if (funcName.startsWith("SendMessageA")) {
			/*
			 * HWND hWnd, // handle of destination window UINT Msg, //
			 * message to send WPARAM wParam, // first message parameter
			 * LPARAM lParam // second message parameter
			 */

			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4);

			if (x1 instanceof LongValue && x2 instanceof LongValue
					&& x3 instanceof LongValue && x4 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				long t1 = ((LongValue) x1).getValue();
				long t2 = ((LongValue) x2).getValue();
				long t3 = ((LongValue) x3).getValue();
				long t4 = ((LongValue) x4).getValue();

				// String str = symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32, t2));
				String msg = memory.getText(new X86MemoryOperand(
						DataType.INT32, t2));

				System.out.println("Window Handle:" + t1
						+ ", Message Sent:" + msg + ", First Param:" + t3
						+ ", Second Param:" + t4);
				register.mov("eax", new LongValue(system.getWindowHandle()
						.sendMessage(t1, msg, t3, t4)));
			}

		} else
		// insert API them
		if (funcName.startsWith("CopyFileA")) {
			/*
			 * LPCTSTR lpExistingFileName, // pointer to name of an existing
			 * file LPCTSTR lpNewFileName, // pointer to filename to copy to
			 * BOOL bFailIfExists // flag for operation if file exists
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3);

			if (x1 instanceof LongValue && x2 instanceof LongValue
					&& x3 instanceof LongValue) {
				long t1 = ((LongValue) x1).getValue();
				long t2 = ((LongValue) x2).getValue();
				long t3 = ((LongValue) x3).getValue();

				String fileNameOld = memory.getText(new X86MemoryOperand(
						DataType.INT32, t1));
				String fileNameNew = memory.getText(new X86MemoryOperand(
						DataType.INT32, t2));
				System.out.println("Old File:" + fileNameOld
						+ ", New File:" + fileNameNew + ", Flag:" + t3);

				register.mov("eax", new LongValue(system.getFileHandle()
						.copyFile(fileNameOld, fileNameNew, t3)));

			}

		} else if (funcName.startsWith("CreateThread")) {
			/*
			 * LPSECURITY_ATTRIBUTES lpThreadAttributes, // pointer to
			 * thread security attributes DWORD dwStackSize, // initial
			 * thread stack size, in bytes LPTHREAD_START_ROUTINE
			 * lpStartAddress, // pointer to thread function LPVOID
			 * lpParameter, // argument for new thread DWORD
			 * dwCreationFlags, // creation flags LPDWORD lpThreadId //
			 * pointer to returned thread identifier
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			Value x5 = stack.pop();
			Value x6 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4 + " " + x5 + " " + x6);

		} else if (funcName.startsWith("ExitProcess")) {
			// UINT uExitCode // exit code for all threads
			Value x1 = stack.pop();

			System.out.println("Argument:" + x1);

		} else if (funcName.startsWith("FindWindowA")) {
			// LPCTSTR lpClassName, // pointer to class name
			// LPCTSTR lpWindowName // pointer to window name
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " ");

			if (x1 instanceof LongValue && x2 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				String className = memory.getText(new X86MemoryOperand(
						DataType.INT32, ((LongValue) x1).getValue()));

				String windowName = memory.getText(new X86MemoryOperand(
						DataType.INT32, ((LongValue) x2).getValue()));
				System.out.println("Class Name:" + className
						+ ", Window Name Address:"
						+ ((LongValue) x2).getValue() + ", Window Name:"
						+ windowName);
				register.mov(
						"eax",
						new LongValue(system.getWindowHandle().findWindow(
								className, ((LongValue) x2).getValue())));

			}

		} else if (funcName.startsWith("FreeEnvironmentStringsA")) {
			// LPTSTR lpszEnvironmentBlock // pointer to a block of
			// environment strings
			Value x1 = stack.pop();
			System.out.println("Argument:" + x1);

		} else if (funcName.startsWith("FreeEnvironmentStringsW")) {
			// LPTSTR lpszEnvironmentBlock // pointer to a block of
			// environment strings
			Value x1 = stack.pop();

			System.out.println("Argument:" + x1);

		} else if (funcName.startsWith("GetCommandLineA")) {
			// This function has no parameters.

			long disp = 4796200;
			String commandLine = "\"C:/Windows/" + program.getFileName() + "\"";
			//String commandLine = "C:/Windows/" + program.getFileName();
			System.out.println("Argument MemoryOperand:" + disp
					+ ", Command Line:" + commandLine);
			memory.setText(new X86MemoryOperand(DataType.INT32, disp),
					commandLine);
			env.getRegister().setRegisterValue("eax", new LongValue(disp));

		} else if (funcName.startsWith("IsDebuggerPresent")) {
			// This function has no parameters.
			int retV = 0;
			System.out.println("Return Value:" + retV);
			register.mov("eax", new LongValue(retV));

		} else if (funcName.startsWith("GetEnvironmentStrings")) {
			// This function has no parameters.

			System.out.println("Argument:" + "null");

		} else if (funcName.startsWith("GetEnvironmentStringsW")) {
			// This function has no parameters.

			System.out.println("Argument:" + "null");

		} else if (funcName.startsWith("GetFileType")) {
			// HANDLE hFile // file handle
			Value x1 = stack.pop();
			System.out.println("Argument:" + x1);

		} else if (funcName.startsWith("GetLastError")) {
			// This function has no parameters.
			long verNum = 14007;
			System.out.println("Last Error:" + verNum);

			register.mov("eax", new LongValue(verNum));

		} else if (funcName.startsWith("GetStartupInfoA")) {
			// LPSTARTUPINFO lpStartupInfo // address of STARTUPINFO
			// structure
			Value x1 = stack.pop();

			System.out.println("Argument:" + x1);

			if (x1 != null && x1 instanceof LongValue) {
				// String sInfo = "Info";
				String sInfo = "D...Â¨Â¡^.ÃˆÂ¡^.Ã°Â¡^.l).*.dll.Any file (*.*).*.*.Â�...........Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿";
				memory.setText(new X86MemoryOperand(DataType.INT32,
						((LongValue) x1).getValue()), sInfo);
			}

		} else if (funcName.startsWith("GetStdHandle")) {
			// DWORD nStdHandle // input, output, or error device
			Value x1 = stack.pop();

			System.out.println("Argument:" + x1 + " ");
			
			if (x1 instanceof LongValue)
				env.getRegister().setRegisterValue("eax", new LongValue(env.getSystem().getSTDHandle(((LongValue)x1).getValue())));

		} else if (funcName.startsWith("GetVersion")) {
			// This function has no parameters.

			System.out.println("Argument:" + "null");
			long verNum = 170393861; // Version of Windows XP
			System.out.println("Version Number:" + verNum);

			register.mov("eax", new LongValue(verNum));

		} else if (funcName.startsWith("HeapAlloc")) {
			/*
			 * HANDLE hHeap, // handle to the private heap block DWORD
			 * dwFlags, // heap allocation control flags DWORD dwBytes //
			 * number of bytes to allocate
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3);
		} else if (funcName.startsWith("HeapCreate")) {
			/*
			 * DWORD flOptions, // heap allocation flag DWORD dwInitialSize,
			 * // initial heap size DWORD dwMaximumSize // maximum heap size
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3);

			if (x1 instanceof LongValue && x2 instanceof LongValue
					&& x3 instanceof LongValue) {
				long t1 = ((LongValue) x1).getValue();
				long t2 = ((LongValue) x2).getValue();
				long t3 = ((LongValue) x3).getValue();

				// String fileNameOld = symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32, t1));
				// String fileNameNew = symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32, t2));
				System.out.println("fOption:" + t1 + ", Initial Size:" + t2
						+ ", Maximum Size:" + t3);

				register.mov("eax", new LongValue(system.getHeapHandle()
						.creatHeap(t2, t3, t1)));

			}
		} else if (funcName.startsWith("HeapDestroy")) {
			// HANDLE hHeap // handle to the heap
			Value x1 = stack.pop();

			System.out.println("Argument:" + x1);

		} else if (funcName.startsWith("HeapFree")) {
			/*
			 * HANDLE hHeap, // handle to the heap DWORD dwFlags, // heap
			 * freeing flags LPVOID lpMem // pointer to the memory to free
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3);

		} else if (funcName.startsWith("HeapReAlloc")) {
			/*
			 * HANDLE hHeap, // handle to a heap block DWORD dwFlags, //
			 * heap reallocation flags LPVOID lpMem, // pointer to the
			 * memory to reallocate DWORD dwBytes // number of bytes to
			 * reallocate
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4);
		} else if (funcName.startsWith("lstrcpyA")) {
			// LPTSTR lpString1, // address of buffer
			// LPCTSTR lpString2 // address of string to copy
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " ");

			if (x1 instanceof LongValue && x2 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				long dest = ((LongValue) x1).getValue();
				String src = memory.getText(new X86MemoryOperand(
						DataType.INT32, ((LongValue) x2).getValue()));
				System.out.println("Destination Address:" + dest
						+ ", Source String:" + src);
				memory.setText(new X86MemoryOperand(DataType.INT32, dest),
						src);
				register.mov("eax", new LongValue(1));
			}

		} else if (funcName.startsWith("MessageBoxA")) {
			/*
			 * HWND hWnd, // handle of owner window LPCTSTR lpText, //
			 * address of text in message box LPCTSTR lpCaption, // address
			 * of title of message box UINT uType // style of message box
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4);
			System.out.print("Handle:" + x1.toString());
			if (x2 instanceof LongValue) {
				System.out
						.print(", Address of Text:"
								+ x2.toString()
								+ ", Text:"
								+ memory.getText(new X86MemoryOperand(
										DataType.INT32, ((LongValue) x2)
												.getValue())));
			}

			if (x3 instanceof LongValue) {
				System.out
						.print(", Address of Title Text:"
								+ x3.toString()
								+ ", Title Text:"
								+ memory.getText(new X86MemoryOperand(
										DataType.INT32, ((LongValue) x3)
												.getValue())));
			}

			System.out.println(", Style:" + x4.toString());
		} else if (funcName.startsWith("PeekMessageA")) {
			/*
			 * LPMSG lpMsg, // pointer to structure for message HWND hWnd,
			 * // handle to window UINT wMsgFilterMin, // first message UINT
			 * wMsgFilterMax, // last message UINT wRemoveMsg // removal
			 * flags
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			Value x5 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4 + " " + x5);

		} else if (funcName.startsWith("SetHandleCount")) {
			// UINT uNumber // number of file handles needed
			Value x1 = stack.pop();

			System.out.println("Argument:" + x1 + " ");

		} else if (funcName.startsWith("VirtualAlloc")) {
			/*
			 * LPVOID lpAddress, // address of region to reserve or commit
			 * DWORD dwSize, // size of region DWORD flAllocationType, //
			 * type of allocation DWORD flProtect // type of access
			 * protection
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			// Exp x5 = symbolStack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4);
			if (x1 instanceof LongValue && x2 instanceof LongValue
					&& x3 instanceof LongValue && x4 instanceof LongValue) {
				long t1 = ((LongValue) x1).getValue();
				long t2 = ((LongValue) x2).getValue();
				long t3 = ((LongValue) x3).getValue();
				long t4 = ((LongValue) x4).getValue();

				// String fileName = symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32, t1));
				System.out.println("Address:" + t1 + ", Size:" + t2
						+ ", Allocation Type:" + t3 + ", Protection Type:"
						+ t4);

				//long t = system.getVirtualHandle().virtualAllocate(t1, t2,
				//		t3, t4);
				
				// PHONG: change to debug here
				//VirtualMemory vM = new VirtualMemory(0x00330000, 0x00330000, t2, t3, t4);
				VirtualMemory vM;
				if (!contain(funcName)) {
					vM = new VirtualMemory(0x00020000, 0x00020000, t2, t3, t4);
					register.mov("eax", new LongValue(0x00020000));
				} else { 
					vM = new VirtualMemory(0x00240000, 0x00240000, t2, t3, t4);
					register.mov("eax", new LongValue(0x00240000));
				}
				
				system.getVirtualHandle().setCurrentVirtualMemory(vM);
				//register.mov("eax", new LongValue(0x00020000));
			}
		} else if (funcName.startsWith("VirtualFree")) {
			/*
			 * LPVOID lpAddress, // address of region of committed pages
			 * DWORD dwSize, // size of region DWORD dwFreeType // type of
			 * free operation
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3);

			if (x1 instanceof LongValue && x2 instanceof LongValue
					&& x3 instanceof LongValue) {
				long t1 = ((LongValue) x1).getValue();
				long t2 = ((LongValue) x2).getValue();
				long t3 = ((LongValue) x3).getValue();

				// String fileName = symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32, t1));
				System.out.println("Base Address:" + t1 + ", Size:" + t2
						+ ", Free Type:" + t3);

				long t = system.getVirtualHandle().freeVirtual(t1, t2, t3);
				register.mov("eax", new LongValue(t));
			}

		} else // Insert new API
		if (funcName.startsWith("FindFirstFileA")) {
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " ");

			if (x1 instanceof LongValue && x2 instanceof LongValue) {
				long t1 = ((LongValue) x1).getValue();
				long t2 = ((LongValue) x2).getValue();

				String searchName = memory.getText(new X86MemoryOperand(
						DataType.INT32, t1));
				System.out.println("Search File:" + searchName
						+ ", Memory Operand:" + t2);

				/*
				 * symbolValueRegister.mov( "eax", new
				 * ValueLongExp(system.getFileHandle()
				 * .findFirstFile(searchName, symbolValueMemoryOperand,
				 * t2)));
				 */
				long t = system.getFileHandle().findFirstFile(searchName,
						memory, t2);
				System.out.println("Search Handle:" + t);
				register.mov("eax", new LongValue(t));
			}

		} else if (funcName.startsWith("SetCurrentDirectoryA")) {
			Value x1 = stack.pop();
			// long x2 = concreteStack.pop();

			System.out.println("Argument:" + x1);
			if (x1 instanceof LongValue) {
				long t1 = ((LongValue) x1).getValue();

				String path = memory.getText(new X86MemoryOperand(
						DataType.INT32, t1));
				System.out.println("Path File:" + path);
				// env.getSystem().setPath(path);
				register.mov("eax",
						new LongValue(env.getSystem().setPath(path)));
			}

		} else if (funcName.startsWith("FindNextFileA")) {
			Value x1 = stack.pop();
			Value x2 = stack.pop();

			System.out.println("Argument:" + x1 + " " + x2);

			if (x1 instanceof LongValue && x2 instanceof LongValue) {
				long t1 = ((LongValue) x1).getValue();
				long t2 = ((LongValue) x2).getValue();
				long ret = system.getFileHandle()
						.findNextFile(t1, memory, t2);
				if (ret == 0 && !checkedAPI.contains(funcName)) {
					ret = (long) (Math.pow(10, 5) * Math.random());
					checkedAPI.add(funcName);
				}
				
				Value t = new LongValue(ret);
				register.mov("eax", t);
				System.out.println("Return Value:"
						+ ((LongValue) t).getValue());
			}

		} else if (funcName.startsWith("CloseHandle")) {
			Value x1 = stack.pop();

			System.out.println("Argument:" + x1);
			if (x1 instanceof LongValue) {
				long x = ((LongValue) x1).getValue();
				System.out.println("Object Handle:" + x1);

				long t = system.closeHandle(x);
				register.mov("eax", new LongValue(t));

				System.out.println("Return Value:" + t);
			}

		} else if (funcName.startsWith("CreateFileMapping")) {
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			Value x5 = stack.pop();
			Value x6 = stack.pop();
			// Exp x7 = symbolStack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4 + " " + x5 + " " + x6);

			if (x1 instanceof LongValue && x2 instanceof LongValue
					&& x3 instanceof LongValue && x4 instanceof LongValue
					&& x5 instanceof LongValue && x6 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				long t1 = ((LongValue) x1).getValue();
				long t2 = ((LongValue) x2).getValue();
				long t3 = ((LongValue) x3).getValue();
				long t4 = ((LongValue) x4).getValue();
				long t5 = ((LongValue) x5).getValue();
				long t6 = ((LongValue) x6).getValue();

				/*
				 * String fileMapName = symbolValueMemoryOperand
				 * .getText(new X86MemoryOperand(DataType.INT32, t6));
				 */
				// String str = symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32, t2));
				System.out.println("Handle File:" + t1
						+ ", Security Attribute:" + t2
						+ ", Object Protection:" + t3 + ", High-Order:"
						+ t4 + ", High-Order:" + t5
						+ ", File Mapping Name Address:" + t6);
				long t = system.getFileHandle().createFileMapping(t1, t2,
						t3, t4, t5, t6);
				register.mov("eax", new LongValue(t));
				System.out.println("Return Value:" + t);
			}

		} else if (funcName.startsWith("CreateFile")) {
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			Value x5 = stack.pop();
			Value x6 = stack.pop();
			Value x7 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4 + " " + x5 + " " + x6 + " " + x7);

			if (x1 instanceof LongValue && x2 instanceof LongValue
					&& x3 instanceof LongValue && x4 instanceof LongValue
					&& x5 instanceof LongValue && x6 instanceof LongValue
					&& x7 instanceof LongValue) {
				long t1 = ((LongValue) x1).getValue();
				long t2 = ((LongValue) x2).getValue();
				long t3 = ((LongValue) x3).getValue();
				long t4 = ((LongValue) x4).getValue();
				long t5 = ((LongValue) x5).getValue();
				long t6 = ((LongValue) x6).getValue();
				long t7 = ((LongValue) x7).getValue();
				String fileName = memory.getText(new X86MemoryOperand(
						DataType.INT32, t1));
				System.out.println("FileName:" + fileName + ", Access:"
						+ t2 + ", ShareMode:" + t3 + ", pSecurity:" + t4
						+ ", Mode:" + t5 + ", Attributes:" + t6
						+ ", hTemplate:" + t7);

				long t = system
						.createFile(fileName, t2, t3, t4, t5, t6, t7);
				register.mov("eax", new LongValue(t));
				System.out.println("Return value:" + t);
			}

		} else if (funcName.startsWith("SetFileAttributesA")) {
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " ");
			if (x1 instanceof LongValue && x2 instanceof LongValue) {
				long t1 = ((LongValue) x1).getValue();
				long t2 = ((LongValue) x2).getValue();

				String fileName = memory.getText(new X86MemoryOperand(
						DataType.INT32, t1));
				System.out.println("FileName:" + fileName + ", Attribute:"
						+ t2);
				long t = system.getFileHandle().setFileAttribute(fileName,
						t2);
				register.mov("eax", new LongValue(t));

				System.out.println("Return Value:" + t);
			}

		} else if (funcName.startsWith("GetCurrentDirectoryA")) {
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument: Length:" + x1
					+ ", Memory Operand:" + x2);
			if (x1 instanceof LongValue && x2 instanceof LongValue) {
				String curDir = "C:/Test";
				long size = memory.setText(new X86MemoryOperand(
						DataType.INT32, ((LongValue) x2).getValue()),
						curDir, ((LongValue) x1).getValue());
				System.out.println("Current Directory:" + curDir);
				register.mov("eax", new LongValue(size));

				// System.out.println("Result " + funcName + ":" +
				// symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32,
				// ((ValueLongExp)x2).getValueOperand()), size) + "!");
			}

		} else if (funcName.startsWith("GetSystemDirectoryA")) {
			Value x2 = stack.pop();
			Value x1 = stack.pop();

			System.out.println("Argument: Length:" + x1
					+ ", Memory Operand:" + x2);
			if (x1 instanceof LongValue && x2 instanceof LongValue) {
				String curDir = "C:/Windows/system32";
				long size = memory.setText(new X86MemoryOperand(
						DataType.INT32, ((LongValue) x2).getValue()),
						curDir, ((LongValue) x1).getValue());
				System.out.println("System Directory:" + curDir);
				register.mov("eax", new LongValue(size));

				// System.out.println("Result GetSystemDirectory:" +
				// symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32,
				// ((ValueLongExp)x2).getValueOperand()), size) + "!");
			}

		} else if (funcName.startsWith("GetWindowsDirectoryA")) {
			Value x2 = stack.pop();
			Value x1 = stack.pop();
			System.out.println("Argument: Length:" + x1
					+ ", Memory Operand:" + x2);
			if (x1 instanceof LongValue && x2 instanceof LongValue) {
				String curDir = "C:/Windows";
				System.out.println("Window Directory:" + curDir);
				long size = memory.setText(new X86MemoryOperand(
						DataType.INT32, ((LongValue) x2).getValue()),
						curDir, ((LongValue) x1).getValue());
				register.mov("eax", new LongValue(size));
				register.mov("ecx", new LongValue(size));
				register.mov("edx", new LongValue(0));

				// System.out.println("Result " + funcName + ":" +
				// symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32,
				// ((ValueLongExp)x2).getValueOperand()), size) + "!");
			}

		} else if (funcName.startsWith("GetModuleHandleA")) {
			Value lpModuleName = stack.pop();

			if (lpModuleName instanceof LongValue) {
				if (((LongValue) lpModuleName).getValue() == 0) {
					returnValue = Program.getProgram().getImageBase();
					System.out.println("Argument: " + 0);
					System.out.println("Return Value: " + returnValue);
					register.mov("eax", new LongValue(returnValue));
					// symbolStack.pop();
				} else {
					String libraryName = env.getMemory().getText(
							((LongValue) lpModuleName).getValue());
					System.out.println("Library Name: " + libraryName);

					returnValue = getModuleHandleA(libraryName, env);
					System.out.println("Return Value: " + returnValue);
					register.mov("eax", new LongValue(returnValue));
				}
			}
		} else if (funcName.startsWith("GetModuleFileName")) {

			Value hModule = stack.pop();
			Value lpFilename = stack.pop();
			Value nSize = stack.pop();
			System.out.println("Argument:" + hModule.toString() + " "
					+ lpFilename.toString() + " " + nSize.toString());

			if (hModule instanceof LongValue
					&& lpFilename instanceof LongValue
					&& nSize instanceof LongValue) {
				/*
				 * long returnValue = APIHandler.getModuleFileNameA(
				 * ((ValueLongExp) hModule).getValueOperand(), ((ValueLongExp)
				 * lpFilename).getValueOperand(), ((ValueLongExp)
				 * nSize).getValueOperand(), program);
				 */
				String s = "C:/Windows/" + program.getFileName();
				memory.setText(new X86MemoryOperand(DataType.INT32,
						((LongValue) lpFilename).getValue()), s);
				register.mov("eax", new LongValue(s.length()));

				// System.out.println("Result " + funcName + ":" +
				// symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32,
				// ((ValueLongExp)lpFilename).getValueOperand()), s.length()) +
				// "!");
				// symbolStack.pop();
			}
		}
		//#############################################################################################################
		//Phong: insert here
		else if (funcName.startsWith("_lclose")) {
			Value hFile = stack.pop();
			System.out.println("Argument:" + hFile.toString());
			if (hFile instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("_lcreat")){
			Value pathName = stack.pop();
			Value iAttr = stack.pop();
			System.out.println("Argument:" + pathName.toString() + " "
					+ iAttr.toString());
			if (pathName instanceof LongValue && iAttr instanceof LongValue){
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("_llseek")){
			Value hFile = stack.pop();
			Value lOffset = stack.pop();
			Value iOrigin = stack.pop();
			System.out.println("Argument:" + hFile.toString() + " "
					+ lOffset.toString() + " " + iOrigin.toString());
			if (hFile instanceof LongValue && lOffset instanceof LongValue && iOrigin instanceof LongValue){
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("_lopen")){
			Value pathName = stack.pop();
			Value iReadWrite = stack.pop();
			System.out.println("Argument:" + pathName.toString() + " " + iReadWrite.toString());
			if (pathName instanceof LongValue && iReadWrite instanceof LongValue){
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("CharLowerA")){
			Value lstr = stack.pop();
			System.out.println("Argument:" + lstr.toString());
			if (lstr instanceof LongValue){
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("CharNextA")){
			Value lstr = stack.pop();
			System.out.println("Argument:" + lstr.toString());
			if (lstr instanceof LongValue){
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("CharPrevA")){
			Value lstrStart = stack.pop();
			Value lstrCur = stack.pop();
			System.out.println("Argument:" + lstrStart.toString() + " " + lstrCur.toString());
			if (lstrStart instanceof LongValue && lstrCur instanceof  LongValue){
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("CharUpperBuffA")){
			Value lstr = stack.pop();
			Value slen = stack.pop();
			System.out.println("Argument:" + lstr.toString() + " " + slen.toString());
			if (lstr instanceof LongValue && slen instanceof LongValue){
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("CompareFileTime")){
			Value fileTime1 = stack.pop();
			Value fileTime2 = stack.pop();
			System.out.println("Argument:" + fileTime1.toString() + " " + fileTime2.toString());
			if (fileTime1 instanceof LongValue && fileTime2 instanceof LongValue){
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("CompareStringA")){
			Value locale = stack.pop();
			Value cmpFlags = stack.pop();
			Value string1 = stack.pop();
			Value count1 = stack.pop();
			Value string2 = stack.pop();
			Value count2 = stack.pop();
			System.out.println("Argument:" + locale.toString() + " " + cmpFlags.toString()
					+ " " + string1.toString() + " " + count1.toString() + string2.toString()
					+ " " + count2.toString());
			if (locale instanceof LongValue && cmpFlags instanceof LongValue && string1 instanceof  LongValue
					&& count1 instanceof LongValue && string2 instanceof LongValue && count2 instanceof LongValue){
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("CreateDialogParamA")){
			Value hInstance = stack.pop();
			Value tName = stack.pop();
			Value wParent = stack.pop();
			Value dialogFunc = stack.pop();
			Value initPar = stack.pop();
			System.out.println("Argument:" + hInstance.toString() + " " + tName.toString()
					+ " " + wParent.toString() + " " + dialogFunc.toString() + initPar.toString());
			if (hInstance instanceof LongValue && tName instanceof LongValue && wParent instanceof  LongValue
					&& dialogFunc instanceof LongValue && initPar instanceof LongValue){
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("CreateDirectoryA")) {
			Value pathName = stack.pop();
			Value securityAttr = stack.pop();
			System.out.println("Argument:" + pathName.toString() + " " + securityAttr.toString());
			if (pathName instanceof LongValue && securityAttr instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("CreateEventA")){
			Value eventAttr = stack.pop();
			Value manualReset = stack.pop();
			Value initialState = stack.pop();
			Value pName = stack.pop();
			System.out.println("Argument:" + eventAttr.toString() + " " + manualReset.toString()
					+ " " + initialState.toString() + " " + pName.toString());
			if (eventAttr instanceof LongValue && manualReset instanceof LongValue
					&& initialState instanceof LongValue && pName instanceof LongValue){
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("CreateProcessA")){
			Value appName = stack.pop();
			Value cmd = stack.pop();
			Value pAttr = stack.pop();
			Value tAttr = stack.pop();
			Value inherritHandle = stack.pop();
			Value cFlags = stack.pop();
			Value envi = stack.pop();
			Value curDir = stack.pop();
			Value startupInfo = stack.pop();
			Value pInfo = stack.pop();
			System.out.println("Argument:" + appName.toString() + " " + cmd.toString()
					+ " " + pAttr.toString() + " " + tAttr.toString() + inherritHandle.toString()
					+ " " + cFlags.toString() + " " + envi.toString() + curDir.toString() + startupInfo.toString()
					+ " " + pInfo.toString());
			if (appName instanceof LongValue && cmd instanceof LongValue && pAttr instanceof LongValue
					&& tAttr instanceof LongValue && inherritHandle instanceof LongValue && cFlags instanceof LongValue
					&& envi instanceof LongValue && curDir instanceof LongValue && startupInfo instanceof LongValue
					&& pInfo instanceof LongValue){
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("CreateRemoteThread")){
			Value hProcess = stack.pop();
			Value tAttr = stack.pop();
			Value stackSize = stack.pop();
			Value sAddr = stack.pop();
			Value parameter = stack.pop();
			Value cFlags = stack.pop();
			Value tID = stack.pop();
			System.out.println("Argument:" + hProcess.toString() + " " + tAttr.toString()
					+ " " + stackSize.toString() + " " + sAddr.toString() + parameter.toString()
					+ " " + cFlags.toString() + " " + tID.toString());
			if (hProcess instanceof LongValue && tAttr instanceof LongValue && stackSize instanceof LongValue
					&& sAddr instanceof LongValue && parameter instanceof LongValue && cFlags instanceof LongValue
					&& tID instanceof LongValue){
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("CreateWindowExA")){
			Value exStyle = stack.pop();
			Value className = stack.pop();
			Value winName = stack.pop();
			Value style = stack.pop();
			Value x = stack.pop();
			Value y = stack.pop();
			Value width = stack.pop();
			Value height = stack.pop();
			Value wParent = stack.pop();
			Value menu = stack.pop();
			Value hinstance = stack.pop();
			Value param = stack.pop();
			System.out.println("Argument:" + exStyle.toString() + " " + className.toString()
					+ " " + winName.toString() + " " + style.toString() + x.toString()
					+ " " + y.toString() + " " + width.toString() + " " + height.toString()
					+ " " + wParent.toString() + " " + menu.toString() + " " + hinstance.toString()
					+ " " + param.toString());
			if (exStyle instanceof LongValue && className instanceof LongValue && winName instanceof LongValue
					&& style instanceof LongValue && x instanceof LongValue && y instanceof LongValue
					&& width instanceof LongValue && height instanceof LongValue && wParent instanceof LongValue
					&& menu instanceof LongValue && hinstance instanceof LongValue && param instanceof LongValue){
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("DeleteCriticalSection")){
			Value cSection = stack.pop();
			System.out.println("Argument:" + " " + cSection.toString());
		}
		else if (funcName.startsWith("DestroyMenu")) {
			Value hmenu = stack.pop();
			System.out.println("Argument:" + " " + hmenu.toString());
			if (hmenu instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("DeviceIoControl")){
			Value hDevice = stack.pop();
			Value controlCode = stack.pop();
			Value inBuffer = stack.pop();
			Value inBufferSize = stack.pop();
			Value outBuffer = stack.pop();
			Value outBufferSize = stack.pop();
			Value bytesReturned = stack.pop();
			Value overlapped = stack.pop();
			System.out.println("Argument:" + " " + hDevice.toString()+ " " + controlCode.toString()+ " " + inBuffer.toString()
					+ " " + inBufferSize.toString()+ " " + outBuffer.toString()+ " " + outBufferSize.toString()
					+ " " + bytesReturned.toString() + " " + overlapped.toString());
			if (hDevice instanceof LongValue && controlCode instanceof LongValue && inBuffer instanceof LongValue
					&& inBufferSize instanceof LongValue && outBuffer instanceof LongValue && outBufferSize instanceof LongValue
					&& bytesReturned instanceof LongValue && overlapped instanceof LongValue){
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("DialogBoxParamA")) {
			Value hinstance = stack.pop();
			Value tName = stack.pop();
			Value wParent = stack.pop();
			Value dFunc = stack.pop();
			Value initPar = stack.pop();
			System.out.println("Argument:" + " " + hinstance.toString() + " " + tName.toString()
					+ " " + wParent.toString() + " " + dFunc.toString() + " " + initPar.toString());
			if (hinstance instanceof LongValue && tName instanceof LongValue && wParent instanceof LongValue
					&& dFunc instanceof LongValue && initPar instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("DisableThreadLibraryCalls")){
			Value libModule = stack.pop();
			System.out.println("Argument:" + " " + libModule.toString());
			if (libModule instanceof LongValue){
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("DispatchMessageA")){
			Value msg = stack.pop();
			System.out.println("Argument:" + " " + msg.toString());
			if (msg instanceof LongValue){
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("EnterCriticalSection")){
			Value cSection = stack.pop();
			System.out.println("Argument:" + " " + cSection.toString());
		}
		else if (funcName.startsWith("EnumDateFormatsA")){
			Value dateFormat = stack.pop();
			Value locale = stack.pop();
			Value flags = stack.pop();
			System.out.println("Argument:" + " " + dateFormat.toString() + " " + locale.toString()
					+ " " + flags.toString());
			if (dateFormat instanceof LongValue && locale instanceof LongValue && flags instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("FileTimeToSystemTime")){
			Value fTime = stack.pop();
			Value sTime = stack.pop();
			System.out.println("Argument:" + " " + fTime.toString() + " " + sTime.toString());
			if (fTime instanceof LongValue && sTime instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("FindResourceA")){
			Value hModule = stack.pop();
			Value name = stack.pop();
			Value type = stack.pop();
			System.out.println("Argument:" + " " + hModule.toString() + " " + name.toString() + " " + type.toString());
			if (hModule instanceof LongValue && name instanceof LongValue && type instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("FlushInstructionCache")){
			Value hProcess = stack.pop();
			Value baseAddr = stack.pop();
			Value size = stack.pop();
			System.out.println("Argument:" + " " + hProcess.toString() + " " + baseAddr.toString() + " " + size.toString());
			if (hProcess instanceof LongValue && baseAddr instanceof LongValue && size instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("FreeLibrary")){
			Value libModule = stack.pop();
			System.out.println("Argument:" + " " + libModule.toString());
			if (libModule instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("GetACP")){
			register.mov("eax", new SymbolValue("api_eax"));
		}
		else if (funcName.startsWith("GetClassInfoExA")){
			Value hinst = stack.pop();
			Value strClass = stack.pop();
			Value wcx = stack.pop();
			System.out.println("Argument:" + " " + hinst.toString() + " " + strClass.toString() + " " + wcx.toString());
			if (hinst instanceof LongValue && strClass instanceof LongValue && wcx instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("GetComputerNameA")){
			Value buffer = stack.pop();
			Value size = stack.pop();
			System.out.println("Argument:" + " " + buffer.toString() + " " + size.toString());
			if (buffer instanceof LongValue && size instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("GetComputerNameA")){
			Value buffer = stack.pop();
			Value size = stack.pop();
			System.out.println("Argument:" + " " + buffer.toString() + " " + size.toString());
			if (buffer instanceof LongValue && size instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("GetCurrentProcessId")){
			register.mov("eax", new SymbolValue("api_eax"));
		}
		else if (funcName.startsWith("GetCurrentThread")){
			register.mov("eax", new SymbolValue("api_eax"));
		}
		else if (funcName.startsWith("GetCurrentThreadId")){
			register.mov("eax", new SymbolValue("api_eax"));
		}
		else if (funcName.startsWith("GetDateFormatA")){
			Value locale = stack.pop();
			Value flags = stack.pop();
			Value date = stack.pop();
			Value format = stack.pop();
			Value datestr = stack.pop();
			Value chdate = stack.pop();
			System.out.println("Argument:" + " " + locale.toString() + " " + flags.toString()
					+ " " + date.toString() + " " + format.toString() + " " + datestr.toString() + " " + chdate.toString());
			if (locale instanceof LongValue && flags instanceof LongValue && date instanceof LongValue
					&& format instanceof LongValue && datestr instanceof LongValue && chdate instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("GetDiskFreeSpaceA")){
			Value rootpathname = stack.pop();
			Value sectorpcluster = stack.pop();
			Value bytespsector = stack.pop();
			Value nooffreecluster = stack.pop();
			Value totalnoofcluster = stack.pop();
			System.out.println("Argument:" + " " + rootpathname.toString() + " " + sectorpcluster.toString()
					+ " " + bytespsector.toString() + " " + nooffreecluster.toString() + " " + totalnoofcluster.toString());
			if (rootpathname instanceof LongValue && sectorpcluster instanceof LongValue && bytespsector instanceof LongValue
					&& nooffreecluster instanceof LongValue && totalnoofcluster instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("GetDriveTypeA")){
			Value rootpathname = stack.pop();
			System.out.println("Argument:" + " " + rootpathname.toString());
			if (rootpathname instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("GetEnvironmentVariableA")){
			Value name = stack.pop();
			Value buffer = stack.pop();
			Value size = stack.pop();
			System.out.println("Argument:" + " " + name.toString()+ " " + buffer.toString()+ " " + size.toString());
			if (name instanceof LongValue && buffer instanceof LongValue && size instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("GetFileSize")){
			Value hfile = stack.pop();
			Value filesizehigh = stack.pop();
			System.out.println("Argument:" + " " + hfile.toString()+ " " + filesizehigh.toString());
			if (hfile instanceof LongValue && filesizehigh instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("GetFileTime")){
			Value hfile = stack.pop();
			Value cTime = stack.pop();
			Value laTime = stack.pop();
			Value lwTime = stack.pop();
			System.out.println("Argument:" + " " + hfile.toString()+ " " + cTime.toString()
					+ " " + laTime.toString() + " " + lwTime.toString());
			if (hfile instanceof LongValue && cTime instanceof LongValue
					&& laTime instanceof LongValue && lwTime instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("GetFocus")){
			register.mov("eax", new SymbolValue("api_eax"));
		}
		else if (funcName.startsWith("GetKeyboardType")){
			Value typeFlag = stack.pop();
			System.out.println("Argument:" + " " + typeFlag.toString());
			if (typeFlag instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("GetLocaleInfoA")){
			Value locale = stack.pop();
			Value ctype = stack.pop();
			Value cdata = stack.pop();
			Value chdata = stack.pop();
			System.out.println("Argument:" + " " + locale.toString() + " " + ctype.toString()
					+ " " + cdata.toString() + " " + chdata.toString());
			if (locale instanceof LongValue && ctype instanceof LongValue && cdata instanceof LongValue
					&& chdata instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("GetLocalTime")){
			Value systemtime = stack.pop();
			System.out.println("Argument:" + " " + systemtime.toString());
			if (systemtime instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("GetLogicalDrives")){
			register.mov("eax", new SymbolValue("api_eax"));
		}
		else if (funcName.startsWith("GetMessageA")){
			Value msg = stack.pop();
			Value wnd = stack.pop();
			Value mFilterMin = stack.pop();
			Value mFilterMax = stack.pop();
			System.out.println("Argument:" + " " + msg.toString() + wnd.toString()
					+ " " + mFilterMin.toString() + " " + mFilterMax.toString());
			if (msg instanceof LongValue && wnd instanceof LongValue
					&& mFilterMin instanceof LongValue && mFilterMax instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("GetProcessHeap")){
			register.mov("eax", new SymbolValue("api_eax"));
		}
		else if (funcName.startsWith("GetShortPathNameA")){
			Value slongpath = stack.pop();
			Value sshortpath = stack.pop();
			Value chbuffer = stack.pop();
			System.out.println("Argument:" + " " + slongpath.toString() + sshortpath.toString()
					+ " " + chbuffer.toString());
			if (slongpath instanceof LongValue && sshortpath instanceof LongValue
					&& chbuffer instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("GetStringTypeA")){
			Value locale = stack.pop();
			Value infotype = stack.pop();
			Value srcstr = stack.pop();
			Value chsrc = stack.pop();
			Value chartype = stack.pop();
			System.out.println("Argument:" + " " + locale.toString() + infotype.toString()
					+ " " + srcstr.toString() + " " + chsrc.toString() + " " + chartype.toString());
			if (locale instanceof LongValue && infotype instanceof LongValue
					&& srcstr instanceof LongValue && chsrc instanceof LongValue && chartype instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("GetStringTypeW")){
			Value infotype = stack.pop();
			Value srcstr = stack.pop();
			Value chsrc = stack.pop();
			Value chartype = stack.pop();
			System.out.println("Argument:" + " " + infotype.toString()
					+ " " + srcstr.toString() + " " + chsrc.toString() + " " + chartype.toString());
			if (infotype instanceof LongValue
					&& srcstr instanceof LongValue && chsrc instanceof LongValue && chartype instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("GetSysColor")){
			Value index = stack.pop();
			System.out.println("Argument:" + " " + index.toString());
			if (index instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("GetSysColorBrush")){
			Value index = stack.pop();
			System.out.println("Argument:" + " " + index.toString());
			if (index instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("GetSystemMenu")){
			Value wnd = stack.pop();
			Value revert = stack.pop();
			System.out.println("Argument:" + " " + wnd.toString() + " " + revert.toString());
			if (wnd instanceof LongValue && revert instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("GetTempFileNameA")){
			Value pathname = stack.pop();
			Value prefixstr = stack.pop();
			Value unique = stack.pop();
			Value tfilename = stack.pop();
			System.out.println("Argument:" + " " + pathname.toString() + " " + prefixstr.toString()
					+ " " + unique.toString() + " " + tfilename.toString());
			if (pathname instanceof LongValue && prefixstr instanceof LongValue
					&& unique instanceof LongValue && tfilename instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("GetWindowThreadProcessId")){
			Value wnd = stack.pop();
			Value proId = stack.pop();
			System.out.println("Argument:" + " " + wnd.toString() + " " + proId.toString());
			if (wnd instanceof LongValue && proId instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("GlobalAlloc")){
			Value flg = stack.pop();
			Value bytes = stack.pop();
			System.out.println("Argument:" + " " + flg.toString() + " " + bytes.toString());
			if (flg instanceof LongValue && bytes instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("GlobalFree")){
			Value mem = stack.pop();
			System.out.println("Argument:" + " " + mem.toString());
			if (mem instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("GlobalLock")){
			Value mem = stack.pop();
			System.out.println("Argument:" + " " + mem.toString());
			if (mem instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("GlobalMemoryStatus")){
			Value buffer = stack.pop();
			System.out.println("Argument:" + " " + buffer.toString());
		}
		else if (funcName.startsWith("InitializeCriticalSection")){
			Value csec = stack.pop();
			System.out.println("Argument:" + " " + csec.toString());
			//stack.push(csec);
		}
		else if (funcName.startsWith("InsertMenuItemA")){
			Value menu = stack.pop();
			Value item = stack.pop();
			Value byPos = stack.pop();
			Value mii = stack.pop();
			System.out.println("Argument:" + " " + menu.toString() + " " + item.toString()
					+ " " + byPos.toString() + " " + mii.toString());
			if (menu instanceof LongValue && item instanceof LongValue
					&& byPos instanceof LongValue && mii instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("InterlockedDecrement")){
			Value addend = stack.pop();
			System.out.println("Argument:" + " " + addend.toString());
			if (addend instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("InterlockedIncrement")){
			Value addend = stack.pop();
			System.out.println("Argument:" + " " + addend.toString());
			if (addend instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("IsBadWritePtr")){
			Value lp = stack.pop();
			Value ucb = stack.pop();
			System.out.println("Argument:" + " " + lp.toString() + " " + ucb.toString());
			if (lp instanceof LongValue && ucb instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("IsDialogMessage")){
			Value dlg = stack.pop();
			Value msg = stack.pop();
			System.out.println("Argument:" + " " + dlg.toString() + " " + msg.toString());
			if (dlg instanceof LongValue && msg instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("LCMapString")){
			Value locale = stack.pop();
			Value flg = stack.pop();
			Value srcstr = stack.pop();
			Value chsrc = stack.pop();
			Value desrstr = stack.pop();
			Value chdest = stack.pop();
			System.out.println("Argument:" + " " + locale.toString() + " " + flg.toString()
					+ " " + srcstr.toString() + " " + chsrc.toString() + " " + desrstr.toString() + " " + chdest.toString());
			if (locale instanceof LongValue && flg instanceof LongValue && srcstr instanceof LongValue
					&& chsrc instanceof LongValue && desrstr instanceof LongValue && chdest instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("LeaveCriticalSection")){
			Value csec = stack.pop();
			System.out.println("Argument:" + " " + csec.toString());
		}
		else if (funcName.startsWith("LoadBitmapA")){
			Value hinstance = stack.pop();
			Value bitmapname = stack.pop();
			System.out.println("Argument:" + " " + hinstance.toString() + " " + bitmapname.toString());
			if (hinstance instanceof LongValue && bitmapname instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("LoadCursorA")){
			Value hinstance = stack.pop();
			Value cursorname = stack.pop();
			System.out.println("Argument:" + " " + hinstance.toString() + " " + cursorname.toString());
			if (hinstance instanceof LongValue && cursorname instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("LoadIconA")){
			Value hinstance = stack.pop();
			Value iconname = stack.pop();
			System.out.println("Argument:" + " " + hinstance.toString() + " " + iconname.toString());
			if (hinstance instanceof LongValue && iconname instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("LoadMenuA")){
			Value hinstance = stack.pop();
			Value menuname = stack.pop();
			System.out.println("Argument:" + " " + hinstance.toString() + " " + menuname.toString());
			if (hinstance instanceof LongValue && menuname instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("LoadResource")){
			Value hmodule = stack.pop();
			Value resinfo = stack.pop();
			System.out.println("Argument:" + " " + hmodule.toString() + " " + resinfo.toString());
			if (hmodule instanceof LongValue && resinfo instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("LoadStringA")){
			Value hinstance = stack.pop();
			Value uid = stack.pop();
			Value buffer = stack.pop();
			Value bufferMax = stack.pop();
			System.out.println("Argument:" + " " + hinstance.toString() + " " + uid.toString()
					+ " " + buffer.toString() + " " + bufferMax.toString());
			if (hinstance instanceof LongValue && uid instanceof LongValue
					&& buffer instanceof LongValue && bufferMax instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("LocalAlloc")){
			Value flg = stack.pop();
			Value bytes = stack.pop();
			System.out.println("Argument:" + " " + flg.toString() + " " + bytes.toString());
			if (flg instanceof LongValue && bytes instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("LocalLock")){
			Value mem = stack.pop();
			System.out.println("Argument:" + " " + mem.toString());
			if (mem instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("LocalReAlloc")){
			Value mem = stack.pop();
			Value bytes = stack.pop();
			Value flg = stack.pop();
			System.out.println("Argument:" + " " + mem.toString() + " " + bytes.toString() + " " + flg.toString());
			if (mem instanceof LongValue && bytes instanceof  LongValue && flg instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("LockResource")){
			Value resdata = stack.pop();
			System.out.println("Argument:" + " " + resdata.toString());
			if (resdata instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("lstrcmpi")){
			Value str1 = stack.pop();
			Value str2 = stack.pop();
			System.out.println("Argument:" + " " + str1.toString() + " " + str2.toString());
			if (str1 instanceof LongValue && str2 instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("lstrcpyn")){
			Value str1 = stack.pop();
			Value str2 = stack.pop();
			Value maxlen = stack.pop();
			System.out.println("Argument:" + " " + str1.toString() + " " + str2.toString() + " " + maxlen.toString());
			if (str1 instanceof LongValue && str2 instanceof LongValue && maxlen instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("MultiByteToWideChar")){
			Value cpage = stack.pop();
			Value flg = stack.pop();
			Value multibstr = stack.pop();
			Value cmultibyte = stack.pop();
			Value widecharstr = stack.pop();
			Value widechar = stack.pop();
			System.out.println("Argument:" + " " + cpage.toString() + " " + flg.toString() + " " + multibstr.toString()
					+ " " + cmultibyte.toString() + " " + widecharstr.toString() + " " + widechar.toString());
			if (cpage instanceof LongValue && flg instanceof LongValue && multibstr instanceof LongValue
					&& cmultibyte instanceof LongValue && widecharstr instanceof LongValue && widechar instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("OpenClipboard")){
			Value wndNewOwner = stack.pop();
			System.out.println("Argument:" + " " + wndNewOwner.toString());
			if (wndNewOwner instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("OpenEventA")){
			Value dAccess = stack.pop();
			Value iHandle = stack.pop();
			Value name = stack.pop();
			System.out.println("Argument:" + " " + dAccess.toString() + " " + iHandle.toString() + " " + name.toString());
			if (dAccess instanceof LongValue && iHandle instanceof LongValue && name instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("OpenFile")){
			Value fName = stack.pop();
			Value reOpenBuff = stack.pop();
			Value style = stack.pop();
			System.out.println("Argument:" + " " + fName.toString() + " " + reOpenBuff.toString() + " " + style.toString());
			if (fName instanceof LongValue && reOpenBuff instanceof LongValue && style instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("OpenEventA")){
			Value dAccess = stack.pop();
			Value iHandle = stack.pop();
			Value name = stack.pop();
			System.out.println("Argument:" + " " + dAccess.toString() + " " + iHandle.toString() + " " + name.toString());
			if (dAccess instanceof LongValue && iHandle instanceof LongValue && name instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("OpenProcess")){
			Value dAccess = stack.pop();
			Value iHandle = stack.pop();
			Value pId = stack.pop();
			System.out.println("Argument:" + " " + dAccess.toString() + " " + iHandle.toString() + " " + pId.toString());
			if (dAccess instanceof LongValue && iHandle instanceof LongValue && pId instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("OutputDebugStringA")){
			Value oStr = stack.pop();
			System.out.println("Argument:" + " " + oStr.toString());
		}
		else if (funcName.startsWith("ReadProcessMemory")){
			Value hProc = stack.pop();
			Value bAddr = stack.pop();
			Value buffer = stack.pop();
			Value size = stack.pop();
			Value noofbyteread = stack.pop();
			System.out.println("Argument:" + " " + hProc.toString() + " " + bAddr.toString() + " " + buffer.toString()
					+ " " + size.toString() + " " + noofbyteread.toString());
			if (hProc instanceof LongValue && bAddr instanceof LongValue && buffer instanceof LongValue
					&& size instanceof LongValue && noofbyteread instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("RegisterClassA")){
			Value wndClass = stack.pop();
			System.out.println("Argument:" + " " + wndClass.toString());
			if (wndClass instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("RegisterClassExA")){
			Value wndClass = stack.pop();
			System.out.println("Argument:" + " " + wndClass.toString());
			if (wndClass instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("ReleaseMutex")){
			Value mutex = stack.pop();
			System.out.println("Argument:" + " " + mutex.toString());
			if (mutex instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("SearchPathA")){
			Value path = stack.pop();
			Value fName = stack.pop();
			Value ext = stack.pop();
			Value bufferlen = stack.pop();
			Value buffer = stack.pop();
			Value fPart = stack.pop();
			System.out.println("Argument:" + " " + path.toString() + " " + fName.toString() + " " + ext.toString()
					+ " " + bufferlen.toString() + " " + buffer.toString() + " " + fPart.toString());
			if (path instanceof LongValue && fName instanceof LongValue && ext instanceof LongValue
					&& bufferlen instanceof LongValue && buffer instanceof LongValue && fPart instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("SetConsoleCtrlHandler")){
			Value hRoutine = stack.pop();
			Value add = stack.pop();
			System.out.println("Argument:" + " " + hRoutine.toString() + " " + add.toString());
			if (hRoutine instanceof LongValue && add instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("SetCursor")){
			Value hCursor = stack.pop();
			System.out.println("Argument:" + " " + hCursor.toString());
			if (hCursor instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("SetErrorMode")){
			Value mode = stack.pop();
			System.out.println("Argument:" + " " + mode.toString());
			if (mode instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("SetEvent")){
			Value event = stack.pop();
			System.out.println("Argument:" + " " + event.toString());
			if (event instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("SetLastError")){
			Value errcode = stack.pop();
			System.out.println("Argument:" + " " + errcode.toString());
		}
		else if (funcName.startsWith("SetThreadPriority")){
			Value hthread = stack.pop();
			Value prior = stack.pop();
			System.out.println("Argument:" + " " + hthread.toString() + " " + prior.toString());
			if (hthread instanceof LongValue && prior instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("SetTimer")){
			Value wnd = stack.pop();
			Value idevent = stack.pop();
			Value elapse = stack.pop();
			Value timerfunc = stack.pop();
			System.out.println("Argument:" + " " + wnd.toString() + " " + idevent.toString()
					+ " " + elapse.toString() + " " + timerfunc.toString());
			if (wnd instanceof LongValue && idevent instanceof LongValue && elapse instanceof LongValue
					&& timerfunc instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("SetUnhandledExceptionFilter")){
			Value toplvlExpFilter = stack.pop();
			System.out.println("Argument:" + " " + toplvlExpFilter.toString());
			if (toplvlExpFilter instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("ShowWindow")){
			Value wnd = stack.pop();
			Value cmdshow = stack.pop();
			System.out.println("Argument:" + " " + wnd.toString() + " " + cmdshow.toString());
			if (wnd instanceof LongValue && cmdshow instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("SizeofResource")){
			Value module = stack.pop();
			Value resinfo = stack.pop();
			System.out.println("Argument:" + " " + module.toString() + " " + resinfo.toString());
			if (module instanceof LongValue && resinfo instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("Sleep")){
			Value millisec = stack.pop();
			System.out.println("Argument:" + " " + millisec.toString());
		}
		else if (funcName.startsWith("SystemTimeToFileTime")){
			Value systime = stack.pop();
			Value filetime = stack.pop();
			System.out.println("Argument:" + " " + systime.toString() + " " + filetime.toString());
			if (systime instanceof LongValue && filetime instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("TerminateProcess")){
			Value proc = stack.pop();
			Value exitcode = stack.pop();
			System.out.println("Argument:" + " " + proc.toString() + " " + exitcode.toString());
			if (proc instanceof LongValue && exitcode instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("TerminateThread")){
			Value hthread = stack.pop();
			Value exitcode = stack.pop();
			System.out.println("Argument:" + " " + hthread.toString() + " " + exitcode.toString());
			if (hthread instanceof LongValue && exitcode instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("TlsAlloc")){
			register.mov("eax", new SymbolValue("api_eax"));
		}
		else if (funcName.startsWith("TlsFree")){
			Value tlsindex = stack.pop();
			System.out.println("Argument:" + " " + tlsindex.toString());
			if (tlsindex instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("TlsGetValue")){
			Value tlsindex = stack.pop();
			System.out.println("Argument:" + " " + tlsindex.toString());
		}
		else if (funcName.startsWith("TlsSetValue")){
			Value tlsindex = stack.pop();
			Value tlsval = stack.pop();
			System.out.println("Argument:" + " " + tlsindex.toString() + " " + tlsval.toString());
			if (tlsindex instanceof LongValue && tlsval instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("TranslateMessage")){
			Value msg = stack.pop();
			System.out.println("Argument:" + " " + msg.toString());
			if (msg instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("UnregisterClassA")){
			Value classname = stack.pop();
			Value hinstance = stack.pop();
			System.out.println("Argument:" + " " + classname.toString() + " " + hinstance.toString());
			if (classname instanceof LongValue && hinstance instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("UpdateWindow")){
			Value wnd = stack.pop();
			System.out.println("Argument:" + " " + wnd.toString());
			if (wnd instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("VirtualAllocEx")){
			Value proc = stack.pop();
			Value addr = stack.pop();
			Value size = stack.pop();
			Value alloctype = stack.pop();
			Value protect = stack.pop();
			System.out.println("Argument:" + " " + proc.toString()+ " " + addr.toString() + " " + size.toString()
					+ " " + alloctype.toString() + " " + protect.toString());
			if (proc instanceof LongValue && addr instanceof LongValue && size instanceof LongValue
					&& alloctype instanceof LongValue && protect instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("VirtualQuery")){
			Value addr = stack.pop();
			Value buffer = stack.pop();
			Value len = stack.pop();
			System.out.println("Argument:" + " " + addr.toString()+ " " + buffer.toString() + " " + len.toString());
			if (addr instanceof LongValue && buffer instanceof LongValue && len instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("WideCharToMultiByte")){
			Value cpage = stack.pop();
			Value flg = stack.pop();
			Value widecharstr = stack.pop();
			Value widechar = stack.pop();
			Value multibstr = stack.pop();
			Value cmultibyte = stack.pop();
			Value defaultchar = stack.pop();
			Value useddefaultchar = stack.pop();
			System.out.println("Argument:" + " " + cpage.toString() + " " + flg.toString()
					+ " " + widecharstr.toString() + " " + widechar.toString()
					+ " " + multibstr.toString() + " " + cmultibyte.toString()
					+ " " + defaultchar.toString() + " " + useddefaultchar.toString());
			if (cpage instanceof LongValue && flg instanceof LongValue  && widecharstr instanceof LongValue
					&& widechar instanceof LongValue && multibstr instanceof LongValue
					&& cmultibyte instanceof LongValue && defaultchar instanceof LongValue
					&& useddefaultchar instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("WritePrivateProfileStringA")){
			Value appname = stack.pop();
			Value keyname = stack.pop();
			Value str = stack.pop();
			Value filename = stack.pop();
			System.out.println("Argument:" + " " + appname.toString()+ " " + keyname.toString()
					+ " " + str.toString() + " " + filename.toString());
			if (appname instanceof LongValue && keyname instanceof LongValue && str instanceof LongValue
					&& filename instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else if (funcName.startsWith("WriteProcessMemory")){
			Value proc = stack.pop();
			Value baddr = stack.pop();
			Value buffer = stack.pop();
			Value size = stack.pop();
			Value noofbytewritten = stack.pop();
			System.out.println("Argument:" + " " + proc.toString()+ " " + baddr.toString()
					+ " " + buffer.toString() + " " + size.toString() + " " + noofbytewritten.toString());
			if (proc instanceof LongValue && baddr instanceof LongValue && buffer instanceof LongValue
					&& size instanceof LongValue && noofbytewritten instanceof LongValue) {
				register.mov("eax", new SymbolValue("api_eax"));
			}
		}
		else {
			//Default way
			Program.getProgram().getLog().debugString("API does not handle in Kernel32.dll");
			register.mov("eax", new SymbolValue("api_eax"));
		}
		
		if (!contain(funcName))		
			checkedAPI.add(funcName);
	}	
	
	private boolean contain(String funcName) {
		// TODO Auto-generated method stub
		for (String t: checkedAPI)
			if (t.equals(funcName))
				return true;
		
		return false;
	}

	public void simulateAPISE(AbsoluteAddress address, String funcName,
			BPState curState, Instruction inst) {
		//long returnValue = 0;
		//boolean ret = true;
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();
		Program program = Program.getProgram();
		//SystemHandle system = env.getSystem();
		//BPCFG cfg = program.getBPCFG();
		
		// Condition is false
		// The return value is always 0
		if (funcName.startsWith("SetThreadLocal")) {
			Value x1 = stack.pop();								
			System.out.println("Argument:" + x1);
			// symbolValueRegister.mov("eax", 1);
			if (x1 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				long t1 = ((LongValue) x1).getValue();
												
				// String str = symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32, t2));
				System.out.println("Local Identifier:" + t1);
				
				System.out.println("Return Value: 1");								
			}
			Value v = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + v);
			register.mov("eax", v);
		} else 
		if (funcName.startsWith("LocalFree")) {
			Value x1 = stack.pop();								
			System.out.println("Argument:" + x1);
			// symbolValueRegister.mov("eax", 1);
			if (x1 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				long t1 = ((LongValue) x1).getValue();
												
				// String str = symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32, t2));
				System.out.println("Handle:" + t1);								
			}
			Value v = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + v);
			register.mov("eax", v);
		} else 
		if (funcName.startsWith("FormatMessageA")) {
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			Value x5 = stack.pop();
			Value x6 = stack.pop();
			Value x7 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4 + " " + x5 + " " + x6 + " " + x7);

			if (x1 instanceof LongValue && x2 instanceof LongValue
					&& x3 instanceof LongValue && x4 instanceof LongValue
					&& x5 instanceof LongValue && x6 instanceof LongValue
					&& x7 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				long t1 = ((LongValue) x1).getValue();
				long t2 = ((LongValue) x2).getValue();
				long t3 = ((LongValue) x3).getValue();
				long t4 = ((LongValue) x4).getValue();
				long t5 = ((LongValue) x5).getValue();
				long t6 = ((LongValue) x6).getValue();
				long t7 = ((LongValue) x7).getValue();

				/*
				 * String fileMapName = symbolValueMemoryOperand
				 * .getText(new X86MemoryOperand(DataType.INT32, t6));
				 */
				// String str = symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32, t2));
				System.out.println("Flag:" + t1
						+ ", Source:" + t2
						+ ", Message Field:" + t3 + ", Language:"
						+ t4 + ", Buffer:" + t5
						+ ", Size:" + t6 + ", Argument" + t7);			
			} 
			Value v = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + v);
			register.mov("eax", v);

		} else if (funcName.startsWith("SetFileTime")) {
			/*
			 * HANDLE hFileMappingObject, // file-mapping object to map into
			 * address space DWORD dwDesiredAccess, // access mode DWORD
			 * dwFileOffsetHigh, // high-order 32 bits of file offset DWORD
			 * dwFileOffsetLow, // low-order 32 bits of file offset DWORD
			 * dwNumberOfBytesToMap // number of bytes to map
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4);

			// symbolValueRegister.mov("eax", 1);
			if (x1 instanceof LongValue && x2 instanceof LongValue
					&& x3 instanceof LongValue && x4 instanceof LongValue
					) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				long t1 = ((LongValue) x1).getValue();
				long t2 = ((LongValue) x2).getValue();
				long t3 = ((LongValue) x3).getValue();
				long t4 = ((LongValue) x4).getValue();
				
				// String str = symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32, t2));
				System.out.println("File Handle:" + t1 + ", Time Created:"
						+ t2 + ", Time Access:" + t3
						+ ", Time Written :" + t4);				
			}
			Value v = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + v);
			register.mov("eax", v);
		} else if (funcName.startsWith("GetTickCount")) {
			long time = System.currentTimeMillis();
			Value v = new SymbolValue("api_" + funcName + "_" + time);
			System.out.println("Return Value: " + v);
			register.mov("eax", v);
		} else if (funcName.startsWith("OutputDebugString")) {
			Value x1 = stack.pop();								
			System.out.println("Argument:" + x1);
			// symbolValueRegister.mov("eax", 1);
			if (x1 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				long t1 = ((LongValue) x1).getValue();
												
				// String str = symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32, t2));
				System.out.println("Pointer String:" + t1 + ", String:"
						+ memory.getText(t1));
				
				System.out.println("Return Value: Null");
				//register.mov("eax", new LongValue(1));				
			}

		} else if (funcName.startsWith("CheckRemoteDebuggerPresent")) {
			Value x1 = stack.pop();
			Value x2 = stack.pop();
						
			System.out.println("Argument:" + x1 + " " + x2);

			// symbolValueRegister.mov("eax", 1);
			if (x1 instanceof LongValue && x2 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				long t1 = ((LongValue) x1).getValue();
				long t2 = ((LongValue) x2).getValue();
								
				// String str = symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32, t2));
				System.out.println("Handle Process:" + t1 + ", Pointer to Debugger:"
						+ t2);
				
				System.out.println("Return Value: " + 1);
				Value eax = new SymbolValue("eax_" + funcName);
				register.mov("eax", new LongValue(1));
				//register.mov("eax", eax);
				//memory.setMemoryValue(t2, new LongValue(1), inst);
				memory.setMemoryValue(new X86MemoryOperand(DataType.INT32, t2), eax, inst);
			}

		} else if (funcName.startsWith("GetLastError")) {
			System.out.println("Return Value: " + 50);
			register.mov("eax", new LongValue(50));
		} else if (funcName.startsWith("Beep")) {
			Value x1 = stack.pop();
			Value x2 = stack.pop();
						
			System.out.println("Argument:" + x1 + " " + x2);

			// symbolValueRegister.mov("eax", 1);
			if (x1 instanceof LongValue && x2 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				long t1 = ((LongValue) x1).getValue();
				long t2 = ((LongValue) x2).getValue();
								
				// String str = symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32, t2));
				System.out.println("Duration:" + t1 + ", Frequency:"
						+ t2);				
			}
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);

		} else if (funcName.startsWith("VirtualProtect")) {
			/*
			 * HANDLE hFileMappingObject, // file-mapping object to map into
			 * address space DWORD dwDesiredAccess, // access mode DWORD
			 * dwFileOffsetHigh, // high-order 32 bits of file offset DWORD
			 * dwFileOffsetLow, // low-order 32 bits of file offset DWORD
			 * dwNumberOfBytesToMap // number of bytes to map
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4);

			// symbolValueRegister.mov("eax", 1);
			if (x1 instanceof LongValue && x2 instanceof LongValue
					&& x3 instanceof LongValue && x4 instanceof LongValue
					) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				long t1 = ((LongValue) x1).getValue();
				long t2 = ((LongValue) x2).getValue();
				long t3 = ((LongValue) x3).getValue();
				long t4 = ((LongValue) x4).getValue();
				
				// String str = symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32, t2));
				System.out.println("Old Protect:" + t1 + ", New Protect:"
						+ t2 + ", Size:" + t3
						+ ", Address :" + t4);			
			}
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);

		} else if (funcName.startsWith("GetCurrentProcess")) {
			// HMODULE hModule, handle to DLL module
			// LPCSTR lpProcName name of function

			System.out.println("Argument: No");
			// program.generageCFG(program.getAbsolutePathFile() + "_test");

			// register.mov("eax", new
			// LongValue(system.getCurrentProcess()));
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("WinExec")) {
			// HMODULE hModule, handle to DLL module
			// LPCSTR lpProcName name of function
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " ");
			//register.mov("eax", 0);
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("CreateProcess")) {
			/*
			 * LPCTSTR lpApplicationName, // pointer to name of executable
			 * module LPTSTR lpCommandLine, // pointer to command line
			 * string LPSECURITY_ATTRIBUTES lpProcessAttributes, // pointer
			 * to process security attributes LPSECURITY_ATTRIBUTES
			 * lpThreadAttributes, // pointer to thread security attributes
			 * BOOL bInheritHandles, // handle inheritance flag DWORD
			 * dwCreationFlags, // creation flags LPVOID lpEnvironment, //
			 * pointer to new environment block LPCTSTR lpCurrentDirectory,
			 * // pointer to current directory name LPSTARTUPINFO
			 * lpStartupInfo, // pointer to STARTUPINFO
			 * LPPROCESS_INFORMATION lpProcessInformation // pointer to
			 * PROCESS_INFORMATION
			 */
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
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4 + " " + x5 + " " + x6 + " " + x7 + " " + x8 + " "
					+ x9 + " " + x10);
			//register.mov("eax", 0);
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("GetFileAttributes")) {
			// HANDLE hFindFile // file search handle
			Value x1 = stack.pop();
			System.out.println("Argument:" + x1);
			//register.mov("eax", 0);
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("GetProcAddress")) {
			// HMODULE hModule, handle to DLL module
			// LPCSTR lpProcName name of function
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " ");
			//register.mov("eax", 0);
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("LoadLibraryA")) {
			// LPCTSTR lpLibFileName // address of filename of executable
			// module
			Value x1 = stack.pop();
			System.out.print("Argument:" + x1);
			//register.mov("eax", 0);
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("SetFilePointer")) {
			/*
			 * HANDLE hFile, // handle of file LONG lDistanceToMove, //
			 * number of bytes to move file pointer PLONG
			 * lpDistanceToMoveHigh, // address of high-order word of
			 * distance to move DWORD dwMoveMethod // how to move
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4 + " ");
			//register.mov("eax", 0);
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("FindClose")) {
			// HANDLE hFindFile // file search handle
			Value x1 = stack.pop();
			System.out.println("Argument:" + x1);
			//register.mov("eax", 0);
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("DeleteFileA")) {
			// LPCTSTR lpFileName // pointer to name of file to delete
			Value x1 = stack.pop();
			System.out.println("Argument:" + x1);
			//register.mov("eax", 0);
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("GetSystemTime")) {
			// LPSYSTEMTIME lpSystemTime // address of system time structure
			Value x1 = stack.pop();
			System.out.println("Memory Address:" + x1.toString());

			if (x1 instanceof LongValue) {
				LongValue x = (LongValue) x1;
				memory.setText(
						new X86MemoryOperand(DataType.INT32, x.getValue()),
						"");
			}

		} else if (funcName.startsWith("GetVersionExA")) {
			// This function has no parameters.

			// long verNum = 498139398;
			long verNum = 0;
			System.out.println("Version Number:" + verNum);

			//register.mov("eax", new LongValue(verNum));
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("MapViewOfFile")) {
			/*
			 * HANDLE hFileMappingObject, // file-mapping object to map into
			 * address space DWORD dwDesiredAccess, // access mode DWORD
			 * dwFileOffsetHigh, // high-order 32 bits of file offset DWORD
			 * dwFileOffsetLow, // low-order 32 bits of file offset DWORD
			 * dwNumberOfBytesToMap // number of bytes to map
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			Value x5 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4 + " " + x5);
			//register.mov("eax", 0);
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("ReadFile")) {
			/*
			 * HANDLE hFile, // handle of file to read LPVOID lpBuffer, //
			 * address of buffer that receives data DWORD
			 * nNumberOfBytesToRead, // number of bytes to read LPDWORD
			 * lpNumberOfBytesRead, // address of number of bytes read
			 * LPOVERLAPPED lpOverlapped // address of structure for data
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			Value x5 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4 + " " + x5);
			//register.mov("eax", 0);
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("UnmapViewOfFile")) {
			// LPCVOID lpBaseAddress // address where mapped view begins
			Value x1 = stack.pop();
			System.out.println("Argument:" + x1);
			//register.mov("eax", new LongValue(0));
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("WriteFile")) {
			/*
			 * HANDLE hFile, // handle to file to write to LPCVOID lpBuffer,
			 * // pointer to data to write to file DWORD
			 * nNumberOfBytesToWrite, // number of bytes to write LPDWORD
			 * lpNumberOfBytesWritten, // pointer to number of bytes written
			 * LPOVERLAPPED lpOverlapped // pointer to structure needed for
			 * overlapped I/O
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			Value x5 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4 + " " + x5);
			//register.mov("eax", 0);
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("RegSetValueExA")) {
			/*
			 * HKEY hKey, // handle of key to set value for LPCTSTR
			 * lpValueName, // address of value to set DWORD Reserved, //
			 * reserved DWORD dwType, // flag for value type CONST BYTE
			 * *lpData, // address of value data DWORD cbData // size of
			 * value data
			 */

			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			Value x5 = stack.pop();
			Value x6 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4 + " " + x5 + " " + x6);
			//register.mov("eax", 0);
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("SetEndOfFile")) {
			// HANDLE hFile // handle of file whose EOF is to be set
			Value x1 = stack.pop();
			System.out.println("Argument:" + x1);
			//register.mov("eax", 0);
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("WaitForSingleObject")) {
			/*
			 * HANDLE hHandle, // handle of object to wait for DWORD
			 * dwMilliseconds // time-out interval in milliseconds
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " ");
			//register.mov("eax", new LongValue(0));
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("PostMessageA")) {
			/*
			 * HWND hWnd, // handle of destination window UINT Msg, //
			 * message to post WPARAM wParam, // first message parameter
			 * LPARAM lParam // second message parameter
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4);
			//register.mov("eax", 0);
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("lstrcatA")) {
			// LPTSTR lpString1, // address of buffer for concatenated
			// strings
			// LPCTSTR lpString2 // address of string to add to string1
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " ");
			//register.mov("eax", 0);
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("lstrcmpA")) {
			// LPCTSTR lpString1, // address of first string
			// LPCTSTR lpString2 // address of second string
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " ");
			register.mov("eax", 0);

		} else if (funcName.startsWith("lstrlenA")) {
			// LPCTSTR lpString // address of string to count
			Value x1 = stack.pop();
			// Exp x2 = symbolStack.pop();
			System.out.println("Argument:" + x1);
			//register.mov("eax", 0);
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("MoveFileA")) {
			// LPCTSTR lpExistingFileName, // address of name of the
			// existing file
			// LPCTSTR lpNewFileName // address of new name for the file
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " ");

			//register.mov("eax", 0);
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("RegCloseKey")) {
			// HKEY hKey // handle of key to close
			Value x1 = stack.pop();
			System.out.println("Argument:" + x1 + " ");
			//register.mov("eax", new LongValue(0));
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("RegOpenKeyExA")) {
			/*
			 * HKEY hKey, // handle of open key LPCTSTR lpSubKey, // address
			 * of name of subkey to open DWORD ulOptions, // reserved REGSAM
			 * samDesired, // security access mask PHKEY phkResult //
			 * address of handle of open key
			 */

			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			Value x5 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4 + " " + x5);
			//register.mov("eax", 0);
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("SendMessageA")) {
			/*
			 * HWND hWnd, // handle of destination window UINT Msg, //
			 * message to send WPARAM wParam, // first message parameter
			 * LPARAM lParam // second message parameter
			 */

			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4);

			//register.mov("eax", 0);
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("CopyFileA")) {
			/*
			 * LPCTSTR lpExistingFileName, // pointer to name of an existing
			 * file LPCTSTR lpNewFileName, // pointer to filename to copy to
			 * BOOL bFailIfExists // flag for operation if file exists
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3);

			//register.mov("eax", 0);
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("CreateThread")) {
			/*
			 * LPSECURITY_ATTRIBUTES lpThreadAttributes, // pointer to
			 * thread security attributes DWORD dwStackSize, // initial
			 * thread stack size, in bytes LPTHREAD_START_ROUTINE
			 * lpStartAddress, // pointer to thread function LPVOID
			 * lpParameter, // argument for new thread DWORD
			 * dwCreationFlags, // creation flags LPDWORD lpThreadId //
			 * pointer to returned thread identifier
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			Value x5 = stack.pop();
			Value x6 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4 + " " + x5 + " " + x6);
			//register.mov("eax", 0);
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("ExitProcess")) {
			// UINT uExitCode // exit code for all threads
			Value x1 = stack.pop();

			System.out.println("Argument:" + x1);

		} else if (funcName.startsWith("FindWindowA")) {
			// LPCTSTR lpClassName, // pointer to class name
			// LPCTSTR lpWindowName // pointer to window name
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " ");

			//register.mov("eax", 0);
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("FreeEnvironmentStringsA")) {
			// LPTSTR lpszEnvironmentBlock // pointer to a block of
			// environment strings
			Value x1 = stack.pop();
			System.out.println("Argument:" + x1);
			//register.mov("eax", 0);
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("FreeEnvironmentStringsW")) {
			// LPTSTR lpszEnvironmentBlock // pointer to a block of
			// environment strings
			Value x1 = stack.pop();

			System.out.println("Argument:" + x1);
			//register.mov("eax", 0);
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("GetCommandLineA")) {
			// This function has no parameters.

			long disp = 4796200;
			String commandLine = "C:/Windows/" + program.getFileName();
			System.out.println("Argument MemoryOperand:" + disp
					+ ", Command Line:" + commandLine);
			// memory.setText(new X86MemoryOperand(DataType.INT32, disp),
			// commandLine);
			memory.setText(new X86MemoryOperand(DataType.INT32, disp), "");
			env.getRegister().setRegisterValue("eax", new SymbolValue("eax"));
		} else if (funcName.startsWith("IsDebuggerPresent")) {
			// This function has no parameters.
			//int retV = 1;
			//System.out.println("Return Value:" + retV);
			//register.mov("eax", new LongValue(retV));
			Value ret = new SymbolValue("api_" + funcName);
			System.out.println("Return Value:" + ret);
			register.mov("eax", ret);
			// register.mov("eax", new LongValue(0));
		} else if (funcName.startsWith("GetEnvironmentStrings")) {
			// This function has no parameters.

			System.out.println("Argument:" + "null");

		} else if (funcName.startsWith("GetEnvironmentStringsW")) {
			// This function has no parameters.

			System.out.println("Argument:" + "null");

		} else if (funcName.startsWith("GetFileType")) {
			// HANDLE hFile // file handle
			Value x1 = stack.pop();
			System.out.println("Argument:" + x1);

		} else if (funcName.startsWith("GetLastError")) {
			// This function has no parameters.
			// long verNum = 14007;
			long verNum = 0;
			System.out.println("Last Error:" + verNum);

			//register.mov("eax", new LongValue(verNum));
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("GetStartupInfoA")) {
			// LPSTARTUPINFO lpStartupInfo // address of STARTUPINFO
			// structure
			Value x1 = stack.pop();

			System.out.println("Argument:" + x1);

			if (x1 != null && x1 instanceof LongValue) {
				String sInfo = "";
				// String sInfo =
				// "D...Â¨Â¡^.ÃˆÂ¡^.Ã°Â¡^.l).*.dll.Any file (*.*).*.*.Â�...........Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿";
				memory.setText(new X86MemoryOperand(DataType.INT32,
						((LongValue) x1).getValue()), sInfo);
			}

		} else if (funcName.startsWith("GetStdHandle")) {
			// DWORD nStdHandle // input, output, or error device
			Value x1 = stack.pop();
			System.out.println("Argument:" + x1 + " ");			
			//env.getRegister().setRegisterValue("eax", new LongValue(0));
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("GetVersion")) {
			// This function has no parameters.

			System.out.println("Argument:" + "null");
			// long verNum = 170393861; // Version of Windows XP
			long verNum = 0;
			System.out.println("Version Number:" + verNum);

			//register.mov("eax", new LongValue(verNum));
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("HeapAlloc")) {
			/*
			 * HANDLE hHeap, // handle to the private heap block DWORD
			 * dwFlags, // heap allocation control flags DWORD dwBytes //
			 * number of bytes to allocate
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3);
		} else if (funcName.startsWith("HeapCreate")) {
			/*
			 * DWORD flOptions, // heap allocation flag DWORD dwInitialSize,
			 * // initial heap size DWORD dwMaximumSize // maximum heap size
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3);

			//register.mov("eax", 0);
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("HeapDestroy")) {
			// HANDLE hHeap // handle to the heap
			Value x1 = stack.pop();

			System.out.println("Argument:" + x1);
			//register.mov("eax", 0);
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("HeapFree")) {
			/*
			 * HANDLE hHeap, // handle to the heap DWORD dwFlags, // heap
			 * freeing flags LPVOID lpMem // pointer to the memory to free
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3);
			//register.mov("eax", 0);
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("HeapReAlloc")) {
			/*
			 * HANDLE hHeap, // handle to a heap block DWORD dwFlags, //
			 * heap reallocation flags LPVOID lpMem, // pointer to the
			 * memory to reallocate DWORD dwBytes // number of bytes to
			 * reallocate
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4);
			//register.mov("eax", 0);
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("lstrcpyA")) {
			// LPTSTR lpString1, // address of buffer
			// LPCTSTR lpString2 // address of string to copy
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " ");

			//register.mov("eax", 0);
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("MessageBoxA")) {
			/*
			 * HWND hWnd, // handle of owner window LPCTSTR lpText, //
			 * address of text in message box LPCTSTR lpCaption, // address
			 * of title of message box UINT uType // style of message box
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4);
			System.out.print("Handle:" + x1.toString());
			if (x2 instanceof LongValue) {
				System.out
						.print(", Address of Text:"
								+ x2.toString()
								+ ", Text:"
								+ memory.getText(new X86MemoryOperand(
										DataType.INT32, ((LongValue) x2)
												.getValue())));
			}

			if (x3 instanceof LongValue) {
				System.out
						.print(", Address of Title Text:"
								+ x3.toString()
								+ ", Title Text:"
								+ memory.getText(new X86MemoryOperand(
										DataType.INT32, ((LongValue) x3)
												.getValue())));
			}

			System.out.println(", Style:" + x4.toString());

		} else if (funcName.startsWith("PeekMessageA")) {
			/*
			 * LPMSG lpMsg, // pointer to structure for message HWND hWnd,
			 * // handle to window UINT wMsgFilterMin, // first message UINT
			 * wMsgFilterMax, // last message UINT wRemoveMsg // removal
			 * flags
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			Value x5 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4 + " " + x5);

		} else if (funcName.startsWith("SetHandleCount")) {
			// UINT uNumber // number of file handles needed
			Value x1 = stack.pop();

			System.out.println("Argument:" + x1 + " ");

		} else if (funcName.startsWith("VirtualAlloc")) {
			/*
			 * LPVOID lpAddress, // address of region to reserve or commit
			 * DWORD dwSize, // size of region DWORD flAllocationType, //
			 * type of allocation DWORD flProtect // type of access
			 * protection
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			// Exp x5 = symbolStack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4);
			//register.mov("eax", 0);
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("VirtualFree")) {
			/*
			 * LPVOID lpAddress, // address of region of committed pages
			 * DWORD dwSize, // size of region DWORD dwFreeType // type of
			 * free operation
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3);

			//register.mov("eax", 0);
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else // Insert new API
		if (funcName.startsWith("FindFirstFileA")) {
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " ");

			if (x1 instanceof LongValue && x2 instanceof LongValue) {
				long t1 = ((LongValue) x1).getValue();
				long t2 = ((LongValue) x2).getValue();

				String searchName = memory.getText(new X86MemoryOperand(
						DataType.INT32, t1));
				System.out.println("Search File:" + searchName
						+ ", Memory Operand:" + t2);

				/*
				 * symbolValueRegister.mov( "eax", new
				 * ValueLongExp(system.getFileHandle()
				 * .findFirstFile(searchName, symbolValueMemoryOperand,
				 * t2)));
				 */
				// long t = system.getFileHandle().findFirstFile(searchName,
				// memory, t2);
				//long t = -1;
				//System.out.println("Search Handle:" + t);
				//register.mov("eax", new LongValue(t));				
			}
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("SetCurrentDirectoryA")) {
			Value x1 = stack.pop();
			// long x2 = concreteStack.pop();

			System.out.println("Argument:" + x1);
			if (x1 instanceof LongValue) {
				long t1 = ((LongValue) x1).getValue();

				String path = memory.getText(new X86MemoryOperand(
						DataType.INT32, t1));
				System.out.println("Path File:" + path);
				// env.getSystem().setPath(path);
				// register.mov("eax",
				// new LongValue(env.getSystem().setPath(path)));
				//register.mov("eax", new LongValue(0));
			}
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("FindNextFileA")) {
			Value x1 = stack.pop();
			Value x2 = stack.pop();

			System.out.println("Argument:" + x1 + " " + x2);
			//register.mov("eax", 0);
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("CloseHandle")) {
			Value x1 = stack.pop();

			System.out.println("Argument:" + x1);
			if (x1 instanceof LongValue) {
				long x = ((LongValue) x1).getValue();
				System.out.println("Object Handle:" + x);

				// long t = system.closeHandle(x);
				//long t = 0;
				//register.mov("eax", new LongValue(t));
				//System.out.println("Return Value:" + t);
			}
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);

		} else if (funcName.startsWith("CreateFileMappingA")) {
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			Value x5 = stack.pop();
			Value x6 = stack.pop();
			// Exp x7 = symbolStack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4 + " " + x5 + " " + x6);

			//register.mov("eax", 0);
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("CreateFileA")) {
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			Value x5 = stack.pop();
			Value x6 = stack.pop();
			Value x7 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4 + " " + x5 + " " + x6 + " " + x7);

			//register.mov("eax", -1);
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("SetFileAttributesA")) {
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " ");
			//register.mov("eax", 0);
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("GetCurrentDirectoryA")) {
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument: Length:" + x1
					+ ", Memory Operand:" + x2);
			if (x1 instanceof LongValue && x2 instanceof LongValue) {
				String curDir = "C:/Test";
				long size = memory.setText(new X86MemoryOperand(
						DataType.INT32, ((LongValue) x2).getValue()),
						curDir, ((LongValue) x1).getValue());
				System.out.println("Current Directory:" + curDir);
				register.mov("eax", new LongValue(size));

				// System.out.println("Result " + funcName + ":" +
				// symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32,
				// ((ValueLongExp)x2).getValueOperand()), size) + "!");
			}
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("GetSystemDirectoryA")) {
			Value x2 = stack.pop();
			Value x1 = stack.pop();

			System.out.println("Argument: Length:" + x1
					+ ", Memory Operand:" + x2);
			if (x1 instanceof LongValue && x2 instanceof LongValue) {
				String curDir = "C:/Windows/system32";
				long size = memory.setText(new X86MemoryOperand(
						DataType.INT32, ((LongValue) x2).getValue()),
						curDir, ((LongValue) x1).getValue());
				System.out.println("System Directory:" + curDir);
				register.mov("eax", new LongValue(size));

				// System.out.println("Result GetSystemDirectory:" +
				// symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32,
				// ((ValueLongExp)x2).getValueOperand()), size) + "!");
			}
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("GetWindowsDirectoryA")) {
			Value x2 = stack.pop();
			Value x1 = stack.pop();
			System.out.println("Argument: Length:" + x1
					+ ", Memory Operand:" + x2);
			if (x1 instanceof LongValue && x2 instanceof LongValue) {
				String curDir = "C:/Windows";
				System.out.println("Window Directory:" + curDir);
				long size = memory.setText(new X86MemoryOperand(
						DataType.INT32, ((LongValue) x2).getValue()),
						curDir, ((LongValue) x1).getValue());
				register.mov("eax", new LongValue(size));

				// System.out.println("Result " + funcName + ":" +
				// symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32,
				// ((ValueLongExp)x2).getValueOperand()), size) + "!");
			}
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("GetModuleHandleA")) {
			Value lpModuleName = stack.pop();

			if (lpModuleName instanceof LongValue) {
				if (((LongValue) lpModuleName).getValue() != 0) {
					String libraryName = env.getMemory().getText(
							((LongValue) lpModuleName).getValue());
					System.out.println("Library Name: " + libraryName);
				}
			}
			// returnValue = getModuleHandleA(libraryName, env);
			//returnValue = 0;
			//System.out.println("Return Value: " + returnValue);
			//register.mov("eax", new LongValue(returnValue));
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("GetModuleFileNameA")) {

			Value hModule = stack.pop();
			Value lpFilename = stack.pop();
			Value nSize = stack.pop();
			System.out.println("Argument:" + hModule.toString() + " "
					+ lpFilename.toString() + " " + nSize.toString());

			if (hModule instanceof LongValue
					&& lpFilename instanceof LongValue
					&& nSize instanceof LongValue) {
				/*
				 * long returnValue = APIHandler.getModuleFileNameA(
				 * ((ValueLongExp) hModule).getValueOperand(), ((ValueLongExp)
				 * lpFilename).getValueOperand(), ((ValueLongExp)
				 * nSize).getValueOperand(), program);
				 */
				String s = "C:/Windows/" + program.getFileName();
				memory.setText(new X86MemoryOperand(DataType.INT32,
						((LongValue) lpFilename).getValue()), s);
				register.mov("eax", new LongValue(s.length()));

				// System.out.println("Result " + funcName + ":" +
				// symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32,
				// ((ValueLongExp)lpFilename).getValueOperand()), s.length()) +
				// "!");
				// symbolStack.pop();
			}
			Value eax = new SymbolValue("eax_" + funcName);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else {
			register.mov("eax", new SymbolValue("api_eax"));
		}
	}
	
	private void simulateAPIFalse(AbsoluteAddress address, String funcName,
			BPState curState, Instruction inst) {
		//long returnValue = 0;
		//boolean ret = true;
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		Memory memory = env.getMemory();
		Register register = env.getRegister();
		Program program = Program.getProgram();
		//SystemHandle system = env.getSystem();
		//BPCFG cfg = program.getBPCFG();
		
		// Condition is false
		// The return value is always 0
		if (funcName.startsWith("SetThreadLocal")) {
			Value x1 = stack.pop();								
			System.out.println("Argument:" + x1);
			// symbolValueRegister.mov("eax", 1);
			if (x1 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				long t1 = ((LongValue) x1).getValue();
												
				// String str = symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32, t2));
				System.out.println("Local Identifier:" + t1);
				
				System.out.println("Return Value: 0");
				register.mov("eax", 0);				
			}

		} else if (funcName.startsWith("LocalFree")) {
			Value x1 = stack.pop();								
			System.out.println("Argument:" + x1);
			// symbolValueRegister.mov("eax", 1);
			if (x1 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				long t1 = ((LongValue) x1).getValue();
												
				// String str = symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32, t2));
				System.out.println("Handle:" + t1);
				
				System.out.println("Return Value: Null");
				register.mov("eax", x1);				
			}

		} else if (funcName.startsWith("FormatMessageA")) {
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			Value x5 = stack.pop();
			Value x6 = stack.pop();
			Value x7 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4 + " " + x5 + " " + x6 + " " + x7);

			if (x1 instanceof LongValue && x2 instanceof LongValue
					&& x3 instanceof LongValue && x4 instanceof LongValue
					&& x5 instanceof LongValue && x6 instanceof LongValue
					&& x7 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				long t1 = ((LongValue) x1).getValue();
				long t2 = ((LongValue) x2).getValue();
				long t3 = ((LongValue) x3).getValue();
				long t4 = ((LongValue) x4).getValue();
				long t5 = ((LongValue) x5).getValue();
				long t6 = ((LongValue) x6).getValue();
				long t7 = ((LongValue) x7).getValue();

				/*
				 * String fileMapName = symbolValueMemoryOperand
				 * .getText(new X86MemoryOperand(DataType.INT32, t6));
				 */
				// String str = symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32, t2));
				System.out.println("Flag:" + t1
						+ ", Source:" + t2
						+ ", Message Field:" + t3 + ", Language:"
						+ t4 + ", Buffer:" + t5
						+ ", Size:" + t6 + ", Argument" + t7);
				long t = 0;
				register.mov("eax", new LongValue(t));
				System.out.println("Return Value:" + t);
			} 

		} else 
		if (funcName.startsWith("SetFileTime")) {
			/*
			 * HANDLE hFileMappingObject, // file-mapping object to map into
			 * address space DWORD dwDesiredAccess, // access mode DWORD
			 * dwFileOffsetHigh, // high-order 32 bits of file offset DWORD
			 * dwFileOffsetLow, // low-order 32 bits of file offset DWORD
			 * dwNumberOfBytesToMap // number of bytes to map
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4);

			// symbolValueRegister.mov("eax", 1);
			if (x1 instanceof LongValue && x2 instanceof LongValue
					&& x3 instanceof LongValue && x4 instanceof LongValue
					) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				long t1 = ((LongValue) x1).getValue();
				long t2 = ((LongValue) x2).getValue();
				long t3 = ((LongValue) x3).getValue();
				long t4 = ((LongValue) x4).getValue();
				
				// String str = symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32, t2));
				System.out.println("File Handle:" + t1 + ", Time Created:"
						+ t2 + ", Time Access:" + t3
						+ ", Time Written :" + t4);
				
				System.out.println("Return Value: " + 0);
				register.mov("eax", new LongValue(0));
			}
		} else if (funcName.startsWith("GetTickCount")) {
			long time = System.currentTimeMillis();
			//Value v = new SymbolValue("api_" + funcName + "_" + time);
			System.out.println("Return Value: " + time);
//			register.mov("eax", new LongValue(time));
		} else if (funcName.startsWith("OutputDebugString")) {
			Value x1 = stack.pop();								
			System.out.println("Argument:" + x1);
			// symbolValueRegister.mov("eax", 1);
			if (x1 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				long t1 = ((LongValue) x1).getValue();
												
				// String str = symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32, t2));
				System.out.println("Pointer String:" + t1 + ", String:"
						+ memory.getText(t1));
				
				System.out.println("Return Value: Null");
				//register.mov("eax", new LongValue(1));				
			}

		} else if (funcName.startsWith("CheckRemoteDebuggerPresent")) {
			Value x1 = stack.pop();
			Value x2 = stack.pop();
						
			System.out.println("Argument:" + x1 + " " + x2);

			// symbolValueRegister.mov("eax", 1);
			if (x1 instanceof LongValue && x2 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				long t1 = ((LongValue) x1).getValue();
				long t2 = ((LongValue) x2).getValue();
								
				// String str = symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32, t2));
				System.out.println("Handle Process:" + t1 + ", Pointer to Debugger:"
						+ t2);
				
				System.out.println("Return Value: " + 0);
				Value eax = new LongValue(0);				
				//register.mov("eax", eax);
				//memory.setMemoryValue(t2, new LongValue(1), inst);
				memory.setMemoryValue(new X86MemoryOperand(DataType.INT32, t2), eax, inst);
			}
			register.mov("eax", new LongValue(0));
		} else if (funcName.startsWith("GetLastError")) {
			System.out.println("Return Value: " + 50);
			register.mov("eax", new LongValue(50));
		} else if (funcName.startsWith("Beep")) {
			Value x1 = stack.pop();
			Value x2 = stack.pop();
						
			System.out.println("Argument:" + x1 + " " + x2);

			// symbolValueRegister.mov("eax", 1);
			if (x1 instanceof LongValue && x2 instanceof LongValue) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				long t1 = ((LongValue) x1).getValue();
				long t2 = ((LongValue) x2).getValue();
								
				// String str = symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32, t2));
				System.out.println("Duration:" + t1 + ", Frequency:"
						+ t2);				
			}
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);

		} else if (funcName.startsWith("VirtualProtect")) {
			/*
			 * HANDLE hFileMappingObject, // file-mapping object to map into
			 * address space DWORD dwDesiredAccess, // access mode DWORD
			 * dwFileOffsetHigh, // high-order 32 bits of file offset DWORD
			 * dwFileOffsetLow, // low-order 32 bits of file offset DWORD
			 * dwNumberOfBytesToMap // number of bytes to map
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4);

			// symbolValueRegister.mov("eax", 1);
			if (x1 instanceof LongValue && x2 instanceof LongValue
					&& x3 instanceof LongValue && x4 instanceof LongValue
					) {
				/*
				 * returnValue = APIHandler.getProcAddress( ((ValueLongExp)
				 * x1).getValueOperand(), ((ValueLongExp) x2).getValueOperand(), program);
				 */
				long t1 = ((LongValue) x1).getValue();
				long t2 = ((LongValue) x2).getValue();
				long t3 = ((LongValue) x3).getValue();
				long t4 = ((LongValue) x4).getValue();
				
				// String str = symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32, t2));
				System.out.println("Old Protect:" + t1 + ", New Protect:"
						+ t2 + ", Size:" + t3
						+ ", Address :" + t4);			
			}
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);

		} else if (funcName.startsWith("GetCurrentProcess")) {
			// HMODULE hModule, handle to DLL module
			// LPCSTR lpProcName name of function

			System.out.println("Argument: No");
			// program.generageCFG(program.getAbsolutePathFile() + "_test");

			// register.mov("eax", new
			// LongValue(system.getCurrentProcess()));
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("WinExec")) {
			// HMODULE hModule, handle to DLL module
			// LPCSTR lpProcName name of function
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " ");
			//register.mov("eax", 0);
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("CreateProcess")) {
			/*
			 * LPCTSTR lpApplicationName, // pointer to name of executable
			 * module LPTSTR lpCommandLine, // pointer to command line
			 * string LPSECURITY_ATTRIBUTES lpProcessAttributes, // pointer
			 * to process security attributes LPSECURITY_ATTRIBUTES
			 * lpThreadAttributes, // pointer to thread security attributes
			 * BOOL bInheritHandles, // handle inheritance flag DWORD
			 * dwCreationFlags, // creation flags LPVOID lpEnvironment, //
			 * pointer to new environment block LPCTSTR lpCurrentDirectory,
			 * // pointer to current directory name LPSTARTUPINFO
			 * lpStartupInfo, // pointer to STARTUPINFO
			 * LPPROCESS_INFORMATION lpProcessInformation // pointer to
			 * PROCESS_INFORMATION
			 */
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
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4 + " " + x5 + " " + x6 + " " + x7 + " " + x8 + " "
					+ x9 + " " + x10);
			//register.mov("eax", 0);
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("GetFileAttributes")) {
			// HANDLE hFindFile // file search handle
			Value x1 = stack.pop();
			System.out.println("Argument:" + x1);
			//register.mov("eax", 0);
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("GetProcAddress")) {
			// HMODULE hModule, handle to DLL module
			// LPCSTR lpProcName name of function
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " ");
			//register.mov("eax", 0);
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("LoadLibraryA")) {
			// LPCTSTR lpLibFileName // address of filename of executable
			// module
			Value x1 = stack.pop();
			System.out.print("Argument:" + x1);
			//register.mov("eax", 0);
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("SetFilePointer")) {
			/*
			 * HANDLE hFile, // handle of file LONG lDistanceToMove, //
			 * number of bytes to move file pointer PLONG
			 * lpDistanceToMoveHigh, // address of high-order word of
			 * distance to move DWORD dwMoveMethod // how to move
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4 + " ");
			//register.mov("eax", 0);
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("FindClose")) {
			// HANDLE hFindFile // file search handle
			Value x1 = stack.pop();
			System.out.println("Argument:" + x1);
			//register.mov("eax", 0);
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("DeleteFileA")) {
			// LPCTSTR lpFileName // pointer to name of file to delete
			Value x1 = stack.pop();
			System.out.println("Argument:" + x1);
			//register.mov("eax", 0);
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("GetSystemTime")) {
			// LPSYSTEMTIME lpSystemTime // address of system time structure
			Value x1 = stack.pop();
			System.out.println("Memory Address:" + x1.toString());

			if (x1 instanceof LongValue) {
				LongValue x = (LongValue) x1;
				memory.setText(
						new X86MemoryOperand(DataType.INT32, x.getValue()),
						"");
			}

		} else if (funcName.startsWith("GetVersionExA")) {
			// This function has no parameters.

			// long verNum = 498139398;
			long verNum = 0;
			System.out.println("Version Number:" + verNum);

			//register.mov("eax", new LongValue(verNum));
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("MapViewOfFile")) {
			/*
			 * HANDLE hFileMappingObject, // file-mapping object to map into
			 * address space DWORD dwDesiredAccess, // access mode DWORD
			 * dwFileOffsetHigh, // high-order 32 bits of file offset DWORD
			 * dwFileOffsetLow, // low-order 32 bits of file offset DWORD
			 * dwNumberOfBytesToMap // number of bytes to map
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			Value x5 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4 + " " + x5);
			//register.mov("eax", 0);
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("ReadFile")) {
			/*
			 * HANDLE hFile, // handle of file to read LPVOID lpBuffer, //
			 * address of buffer that receives data DWORD
			 * nNumberOfBytesToRead, // number of bytes to read LPDWORD
			 * lpNumberOfBytesRead, // address of number of bytes read
			 * LPOVERLAPPED lpOverlapped // address of structure for data
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			Value x5 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4 + " " + x5);
			//register.mov("eax", 0);
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("UnmapViewOfFile")) {
			// LPCVOID lpBaseAddress // address where mapped view begins
			Value x1 = stack.pop();
			System.out.println("Argument:" + x1);
			//register.mov("eax", new LongValue(0));
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("WriteFile")) {
			/*
			 * HANDLE hFile, // handle to file to write to LPCVOID lpBuffer,
			 * // pointer to data to write to file DWORD
			 * nNumberOfBytesToWrite, // number of bytes to write LPDWORD
			 * lpNumberOfBytesWritten, // pointer to number of bytes written
			 * LPOVERLAPPED lpOverlapped // pointer to structure needed for
			 * overlapped I/O
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			Value x5 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4 + " " + x5);
			//register.mov("eax", 0);
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("RegSetValueExA")) {
			/*
			 * HKEY hKey, // handle of key to set value for LPCTSTR
			 * lpValueName, // address of value to set DWORD Reserved, //
			 * reserved DWORD dwType, // flag for value type CONST BYTE
			 * *lpData, // address of value data DWORD cbData // size of
			 * value data
			 */

			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			Value x5 = stack.pop();
			Value x6 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4 + " " + x5 + " " + x6);
			//register.mov("eax", 0);
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("SetEndOfFile")) {
			// HANDLE hFile // handle of file whose EOF is to be set
			Value x1 = stack.pop();
			System.out.println("Argument:" + x1);
			//register.mov("eax", 0);
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("WaitForSingleObject")) {
			/*
			 * HANDLE hHandle, // handle of object to wait for DWORD
			 * dwMilliseconds // time-out interval in milliseconds
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " ");
			//register.mov("eax", new LongValue(0));
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("PostMessageA")) {
			/*
			 * HWND hWnd, // handle of destination window UINT Msg, //
			 * message to post WPARAM wParam, // first message parameter
			 * LPARAM lParam // second message parameter
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4);
			//register.mov("eax", 0);
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("lstrcatA")) {
			// LPTSTR lpString1, // address of buffer for concatenated
			// strings
			// LPCTSTR lpString2 // address of string to add to string1
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " ");
			//register.mov("eax", 0);
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("lstrcmpA")) {
			// LPCTSTR lpString1, // address of first string
			// LPCTSTR lpString2 // address of second string
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " ");
			register.mov("eax", 0);

		} else if (funcName.startsWith("lstrlenA")) {
			// LPCTSTR lpString // address of string to count
			Value x1 = stack.pop();
			// Exp x2 = symbolStack.pop();
			System.out.println("Argument:" + x1);
			//register.mov("eax", 0);
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("MoveFileA")) {
			// LPCTSTR lpExistingFileName, // address of name of the
			// existing file
			// LPCTSTR lpNewFileName // address of new name for the file
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " ");

			//register.mov("eax", 0);
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("RegCloseKey")) {
			// HKEY hKey // handle of key to close
			Value x1 = stack.pop();
			System.out.println("Argument:" + x1 + " ");
			//register.mov("eax", new LongValue(0));
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("RegOpenKeyExA")) {
			/*
			 * HKEY hKey, // handle of open key LPCTSTR lpSubKey, // address
			 * of name of subkey to open DWORD ulOptions, // reserved REGSAM
			 * samDesired, // security access mask PHKEY phkResult //
			 * address of handle of open key
			 */

			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			Value x5 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4 + " " + x5);
			//register.mov("eax", 0);
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("SendMessageA")) {
			/*
			 * HWND hWnd, // handle of destination window UINT Msg, //
			 * message to send WPARAM wParam, // first message parameter
			 * LPARAM lParam // second message parameter
			 */

			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4);

			//register.mov("eax", 0);
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("CopyFileA")) {
			/*
			 * LPCTSTR lpExistingFileName, // pointer to name of an existing
			 * file LPCTSTR lpNewFileName, // pointer to filename to copy to
			 * BOOL bFailIfExists // flag for operation if file exists
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3);

			//register.mov("eax", 0);
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("CreateThread")) {
			/*
			 * LPSECURITY_ATTRIBUTES lpThreadAttributes, // pointer to
			 * thread security attributes DWORD dwStackSize, // initial
			 * thread stack size, in bytes LPTHREAD_START_ROUTINE
			 * lpStartAddress, // pointer to thread function LPVOID
			 * lpParameter, // argument for new thread DWORD
			 * dwCreationFlags, // creation flags LPDWORD lpThreadId //
			 * pointer to returned thread identifier
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			Value x5 = stack.pop();
			Value x6 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4 + " " + x5 + " " + x6);
			//register.mov("eax", 0);
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("ExitProcess")) {
			// UINT uExitCode // exit code for all threads
			Value x1 = stack.pop();

			System.out.println("Argument:" + x1);

		} else if (funcName.startsWith("FindWindowA")) {
			// LPCTSTR lpClassName, // pointer to class name
			// LPCTSTR lpWindowName // pointer to window name
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " ");

			//register.mov("eax", 0);
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("FreeEnvironmentStringsA")) {
			// LPTSTR lpszEnvironmentBlock // pointer to a block of
			// environment strings
			Value x1 = stack.pop();
			System.out.println("Argument:" + x1);
			//register.mov("eax", 0);
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("FreeEnvironmentStringsW")) {
			// LPTSTR lpszEnvironmentBlock // pointer to a block of
			// environment strings
			Value x1 = stack.pop();

			System.out.println("Argument:" + x1);
			//register.mov("eax", 0);
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("GetCommandLineA")) {
			// This function has no parameters.

			long disp = 4796200;
			String commandLine = "C:/Windows/" + program.getFileName();
			System.out.println("Argument MemoryOperand:" + disp
					+ ", Command Line:" + commandLine);
			// memory.setText(new X86MemoryOperand(DataType.INT32, disp),
			// commandLine);
			memory.setText(new X86MemoryOperand(DataType.INT32, disp), "");
			env.getRegister().setRegisterValue("eax", new SymbolValue("eax"));
		} else if (funcName.startsWith("IsDebuggerPresent")) {
			// This function has no parameters.
			int retV = 0;
			//System.out.println("Return Value:" + retV);
			//register.mov("eax", new LongValue(retV));
			Value ret = new LongValue(retV);
			System.out.println("Return Value:" + ret);
			register.mov("eax", ret);
			// register.mov("eax", new LongValue(0));
		} else if (funcName.startsWith("GetEnvironmentStrings")) {
			// This function has no parameters.

			System.out.println("Argument:" + "null");

		} else if (funcName.startsWith("GetEnvironmentStringsW")) {
			// This function has no parameters.

			System.out.println("Argument:" + "null");

		} else if (funcName.startsWith("GetFileType")) {
			// HANDLE hFile // file handle
			Value x1 = stack.pop();
			System.out.println("Argument:" + x1);

		} else if (funcName.startsWith("GetLastError")) {
			// This function has no parameters.
			// long verNum = 14007;
			long verNum = 0;
			System.out.println("Last Error:" + verNum);

			//register.mov("eax", new LongValue(verNum));
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("GetStartupInfoA")) {
			// LPSTARTUPINFO lpStartupInfo // address of STARTUPINFO
			// structure
			Value x1 = stack.pop();

			System.out.println("Argument:" + x1);

			if (x1 != null && x1 instanceof LongValue) {
				String sInfo = "";
				// String sInfo =
				// "D...Â¨Â¡^.ÃˆÂ¡^.Ã°Â¡^.l).*.dll.Any file (*.*).*.*.Â�...........Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿Ã¿";
				memory.setText(new X86MemoryOperand(DataType.INT32,
						((LongValue) x1).getValue()), sInfo);
			}

		} else if (funcName.startsWith("GetStdHandle")) {
			// DWORD nStdHandle // input, output, or error device
			Value x1 = stack.pop();
			System.out.println("Argument:" + x1 + " ");			
			//env.getRegister().setRegisterValue("eax", new LongValue(0));
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("GetVersion")) {
			// This function has no parameters.

			System.out.println("Argument:" + "null");
			// long verNum = 170393861; // Version of Windows XP
			long verNum = 0;
			System.out.println("Version Number:" + verNum);

			//register.mov("eax", new LongValue(verNum));
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("HeapAlloc")) {
			/*
			 * HANDLE hHeap, // handle to the private heap block DWORD
			 * dwFlags, // heap allocation control flags DWORD dwBytes //
			 * number of bytes to allocate
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3);
		} else if (funcName.startsWith("HeapCreate")) {
			/*
			 * DWORD flOptions, // heap allocation flag DWORD dwInitialSize,
			 * // initial heap size DWORD dwMaximumSize // maximum heap size
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3);

			//register.mov("eax", 0);
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("HeapDestroy")) {
			// HANDLE hHeap // handle to the heap
			Value x1 = stack.pop();

			System.out.println("Argument:" + x1);
			//register.mov("eax", 0);
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("HeapFree")) {
			/*
			 * HANDLE hHeap, // handle to the heap DWORD dwFlags, // heap
			 * freeing flags LPVOID lpMem // pointer to the memory to free
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3);
			//register.mov("eax", 0);
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("HeapReAlloc")) {
			/*
			 * HANDLE hHeap, // handle to a heap block DWORD dwFlags, //
			 * heap reallocation flags LPVOID lpMem, // pointer to the
			 * memory to reallocate DWORD dwBytes // number of bytes to
			 * reallocate
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4);
			//register.mov("eax", 0);
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("lstrcpyA")) {
			// LPTSTR lpString1, // address of buffer
			// LPCTSTR lpString2 // address of string to copy
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " ");

			//register.mov("eax", 0);
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("MessageBoxA")) {
			/*
			 * HWND hWnd, // handle of owner window LPCTSTR lpText, //
			 * address of text in message box LPCTSTR lpCaption, // address
			 * of title of message box UINT uType // style of message box
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4);
			System.out.print("Handle:" + x1.toString());
			if (x2 instanceof LongValue) {
				System.out
						.print(", Address of Text:"
								+ x2.toString()
								+ ", Text:"
								+ memory.getText(new X86MemoryOperand(
										DataType.INT32, ((LongValue) x2)
												.getValue())));
			}

			if (x3 instanceof LongValue) {
				System.out
						.print(", Address of Title Text:"
								+ x3.toString()
								+ ", Title Text:"
								+ memory.getText(new X86MemoryOperand(
										DataType.INT32, ((LongValue) x3)
												.getValue())));
			}

			System.out.println(", Style:" + x4.toString());

		} else if (funcName.startsWith("PeekMessageA")) {
			/*
			 * LPMSG lpMsg, // pointer to structure for message HWND hWnd,
			 * // handle to window UINT wMsgFilterMin, // first message UINT
			 * wMsgFilterMax, // last message UINT wRemoveMsg // removal
			 * flags
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			Value x5 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4 + " " + x5);

		} else if (funcName.startsWith("SetHandleCount")) {
			// UINT uNumber // number of file handles needed
			Value x1 = stack.pop();

			System.out.println("Argument:" + x1 + " ");

		} else if (funcName.startsWith("VirtualAlloc")) {
			/*
			 * LPVOID lpAddress, // address of region to reserve or commit
			 * DWORD dwSize, // size of region DWORD flAllocationType, //
			 * type of allocation DWORD flProtect // type of access
			 * protection
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			// Exp x5 = symbolStack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4);
			//register.mov("eax", 0);
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("VirtualFree")) {
			/*
			 * LPVOID lpAddress, // address of region of committed pages
			 * DWORD dwSize, // size of region DWORD dwFreeType // type of
			 * free operation
			 */
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3);

			//register.mov("eax", 0);
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else // Insert new API
		if (funcName.startsWith("FindFirstFileA")) {
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " ");

			if (x1 instanceof LongValue && x2 instanceof LongValue) {
				long t1 = ((LongValue) x1).getValue();
				long t2 = ((LongValue) x2).getValue();

				String searchName = memory.getText(new X86MemoryOperand(
						DataType.INT32, t1));
				System.out.println("Search File:" + searchName
						+ ", Memory Operand:" + t2);

				/*
				 * symbolValueRegister.mov( "eax", new
				 * ValueLongExp(system.getFileHandle()
				 * .findFirstFile(searchName, symbolValueMemoryOperand,
				 * t2)));
				 */
				// long t = system.getFileHandle().findFirstFile(searchName,
				// memory, t2);
				//long t = -1;
				//System.out.println("Search Handle:" + t);
				//register.mov("eax", new LongValue(t));				
			}
			Value eax = new LongValue(-1);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("SetCurrentDirectoryA")) {
			Value x1 = stack.pop();
			// long x2 = concreteStack.pop();

			System.out.println("Argument:" + x1);
			if (x1 instanceof LongValue) {
				long t1 = ((LongValue) x1).getValue();

				String path = memory.getText(new X86MemoryOperand(
						DataType.INT32, t1));
				System.out.println("Path File:" + path);
				// env.getSystem().setPath(path);
				// register.mov("eax",
				// new LongValue(env.getSystem().setPath(path)));
				//register.mov("eax", new LongValue(0));
			}
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("FindNextFileA")) {
			Value x1 = stack.pop();
			Value x2 = stack.pop();

			System.out.println("Argument:" + x1 + " " + x2);
			//register.mov("eax", 0);
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("CloseHandle")) {
			Value x1 = stack.pop();

			System.out.println("Argument:" + x1);
			if (x1 instanceof LongValue) {
				long x = ((LongValue) x1).getValue();
				System.out.println("Object Handle:" + x);

				// long t = system.closeHandle(x);
				//long t = 0;
				//register.mov("eax", new LongValue(t));
				//System.out.println("Return Value:" + t);
			}
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);

		} else if (funcName.startsWith("CreateFileMappingA")) {
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			Value x5 = stack.pop();
			Value x6 = stack.pop();
			// Exp x7 = symbolStack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4 + " " + x5 + " " + x6);

			//register.mov("eax", 0);
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("CreateFileA")) {
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			Value x3 = stack.pop();
			Value x4 = stack.pop();
			Value x5 = stack.pop();
			Value x6 = stack.pop();
			Value x7 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " " + x3 + " "
					+ x4 + " " + x5 + " " + x6 + " " + x7);

			//register.mov("eax", -1);
			Value eax = new LongValue(-1);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("SetFileAttributesA")) {
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument:" + x1 + " " + x2 + " ");
			//register.mov("eax", 0);
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("GetCurrentDirectoryA")) {
			Value x1 = stack.pop();
			Value x2 = stack.pop();
			System.out.println("Argument: Length:" + x1
					+ ", Memory Operand:" + x2);
			if (x1 instanceof LongValue && x2 instanceof LongValue) {
				String curDir = "C:/Test";
				long size = memory.setText(new X86MemoryOperand(
						DataType.INT32, ((LongValue) x2).getValue()),
						curDir, ((LongValue) x1).getValue());
				System.out.println("Current Directory:" + curDir);
				register.mov("eax", new LongValue(size));

				// System.out.println("Result " + funcName + ":" +
				// symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32,
				// ((ValueLongExp)x2).getValueOperand()), size) + "!");
			}
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("GetSystemDirectoryA")) {
			Value x2 = stack.pop();
			Value x1 = stack.pop();

			System.out.println("Argument: Length:" + x1
					+ ", Memory Operand:" + x2);
			if (x1 instanceof LongValue && x2 instanceof LongValue) {
				String curDir = "C:/Windows/system32";
				long size = memory.setText(new X86MemoryOperand(
						DataType.INT32, ((LongValue) x2).getValue()),
						curDir, ((LongValue) x1).getValue());
				System.out.println("System Directory:" + curDir);
				register.mov("eax", new LongValue(size));

				// System.out.println("Result GetSystemDirectory:" +
				// symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32,
				// ((ValueLongExp)x2).getValueOperand()), size) + "!");
			}
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("GetWindowsDirectoryA")) {
			Value x2 = stack.pop();
			Value x1 = stack.pop();
			System.out.println("Argument: Length:" + x1
					+ ", Memory Operand:" + x2);
			if (x1 instanceof LongValue && x2 instanceof LongValue) {
				String curDir = "C:/Windows";
				System.out.println("Window Directory:" + curDir);
				long size = memory.setText(new X86MemoryOperand(
						DataType.INT32, ((LongValue) x2).getValue()),
						curDir, ((LongValue) x1).getValue());
				register.mov("eax", new LongValue(size));

				// System.out.println("Result " + funcName + ":" +
				// symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32,
				// ((ValueLongExp)x2).getValueOperand()), size) + "!");
			}
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("GetModuleHandleA")) {
			Value lpModuleName = stack.pop();

			if (lpModuleName instanceof LongValue) {
				if (((LongValue) lpModuleName).getValue() != 0) {
					String libraryName = env.getMemory().getText(
							((LongValue) lpModuleName).getValue());
					System.out.println("Library Name: " + libraryName);
				}
			}
			// returnValue = getModuleHandleA(libraryName, env);
			//returnValue = 0;
			//System.out.println("Return Value: " + returnValue);
			//register.mov("eax", new LongValue(returnValue));
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else if (funcName.startsWith("GetModuleFileNameA")) {

			Value hModule = stack.pop();
			Value lpFilename = stack.pop();
			Value nSize = stack.pop();
			System.out.println("Argument:" + hModule.toString() + " "
					+ lpFilename.toString() + " " + nSize.toString());

			if (hModule instanceof LongValue
					&& lpFilename instanceof LongValue
					&& nSize instanceof LongValue) {
				/*
				 * long returnValue = APIHandler.getModuleFileNameA(
				 * ((ValueLongExp) hModule).getValueOperand(), ((ValueLongExp)
				 * lpFilename).getValueOperand(), ((ValueLongExp)
				 * nSize).getValueOperand(), program);
				 */
				String s = "C:/Windows/" + program.getFileName();
				memory.setText(new X86MemoryOperand(DataType.INT32,
						((LongValue) lpFilename).getValue()), s);
				register.mov("eax", new LongValue(s.length()));

				// System.out.println("Result " + funcName + ":" +
				// symbolValueMemoryOperand.getText(new
				// X86MemoryOperand(DataType.INT32,
				// ((ValueLongExp)lpFilename).getValueOperand()), s.length()) +
				// "!");
				// symbolStack.pop();
			}
			Value eax = new LongValue(0);
			System.out.println("Return Value: " + eax);
			register.mov("eax", eax);
		} else {
			register.mov("eax", new SymbolValue("api_eax"));
		}
	}


	@Override
	public boolean executeAPI(AbsoluteAddress address, String funcName,
			BPState curState, Instruction inst, int cond) {
		// TODO Auto-generated method stub
		//long returnValue = 0;
		boolean ret = true;
		Environment env = curState.getEnvironement();
		Stack stack = env.getStack();
		//Memory memory = env.getMemory();
		//Register register = env.getRegister();
		Program program = Program.getProgram();
		//SystemHandle system = env.getSystem();
		BPCFG cfg = program.getBPCFG();

		// if (funcName.contains("ExitProcess"))
		// System.out.println("Debug");

		if (inst.getName().toString().equals("jmp")) {
			System.out.println("JMP API kernel32.dll:" + funcName);

			// if (funcName.equals("ExitProcess") || funcName.equals("exit")) {
			// System.out.println("Debgug");
			// }

			BPVertex v1 = cfg.getVertex(curState.getLocation(),
					curState.getInstruction());
			v1.setProperty(getFullName(funcName));
			BPVertex v2 = new BPVertex();
			// v2.setAddress(address);
			v2.setProperty(getFullName(funcName));			

			if (funcName.equals("ExitProcess") || funcName.equals("exit")) {
				v2.setType(BPVertex.ExitNode);
				v2 = cfg.insertVertex(v2);
				// v2.setProperty(getFullName(funcName));
				cfg.insertEdge(new BPEdge(v1, v2));
				curState.setLocation(null);
				curState.setInstruction(null);
				return true;
			}
			
			v2.setType(BPVertex.APINode);			
			v2 = cfg.insertVertex(v2);
			cfg.insertEdge(new BPEdge(v1, v2));
			Value returnAddr = stack.pop();
			long r = 0;
			if (returnAddr instanceof LongValue) {
				r = ((LongValue) returnAddr).getValue();
			} else
				r = curState.getLocation().getValue()
						+ curState.getInstruction().getSize();
			AbsoluteAddress addr = new AbsoluteAddress(r);
			Instruction newInst = program.getInstruction(addr, env);
			v1 = cfg.insertVertex(new BPVertex(addr, newInst));
			cfg.insertEdge(new BPEdge(v2, v1));

			curState.setLocation(addr);
			curState.setInstruction(newInst);
		} else if (inst.getName().toString().equals("call")) {
			System.out.println("Call API kernel32.dll:" + funcName);

			BPVertex v1 = cfg.getVertex(curState.getLocation(),
					curState.getInstruction());
			v1.setProperty(getFullName(funcName));
			BPVertex v2 = new BPVertex();
			// v2.setAddress(address);
			v2.setProperty(getFullName(funcName));
			
			if (funcName.equals("ExitProcess") || funcName.equals("exit")) {
				v2.setType(BPVertex.ExitNode);
				v2 = cfg.insertVertex(v2);
				// v2.setProperty(getFullName(funcName));
				cfg.insertEdge(new BPEdge(v1, v2));
				curState.setLocation(null);
				curState.setInstruction(null);
				return true;
			}			

			v2.setType(BPVertex.APINode);			
			v2 = cfg.insertVertex(v2);
			cfg.insertEdge(new BPEdge(v1, v2));

			long r = curState.getLocation().getValue()
					+ curState.getInstruction().getSize();
			AbsoluteAddress addr = new AbsoluteAddress(r);
			// PHONG: change here for virtual memory
			Instruction newInst;
			if (curState.getEnvironement().getSystem().isInVirtualMemory() == true){
				byte[] opcodes = this.getOpcodesArray(curState, addr.getValue());
				// NEXT INSTRUCTION FOR CALL 
				newInst = Program.getProgram().getInstruction(opcodes, env);
			}
			else newInst = program.getInstruction(addr, env);
			
			v1 = cfg.insertVertex(new BPVertex(addr, newInst));
			cfg.insertEdge(new BPEdge(v2, v1));

			curState.setLocation(addr);
			curState.setInstruction(newInst);
		}
		
		switch (cond) { 
		case 1:	
			Program.getProgram().getLog().info("Simulate API with True Condition");
			simulateAPI(address, funcName, curState, inst);
			break;
		case 2: 
			Program.getProgram().getLog().info("Simulate API with False Condition");
			simulateAPIFalse(address, funcName, curState, inst);
			break;
		case 3: 
			Program.getProgram().getLog().info("Simulate API with Symbolic Execution");
			simulateAPISE(address, funcName, curState, inst);
			break;
		default: 
			break;
		}

		return ret;
	}

	private long getModuleHandleA(String library, Environment env) {
		// TODO Auto-generated method stub
		if (library.toLowerCase().contains("kernel32"))
			return env.getSystem().getKernel().getBaseAddress();
		return 0;
	}

	private String getFullName(String funcName) {
		// TODO Auto-generated method stub
		return funcName + "@" + libraryName;
	}

	public static int checkCondLookAhead(AddressList l,
			Map<AbsoluteAddress, Instruction> map,
			Map<AbsoluteAddress, AbsoluteAddress> neg) {
		if (l.length() == 2) {
			AbsoluteAddress t1 = l.pop();
			AbsoluteAddress t2 = l.pop();
			// AbsoluteAddress t3 = l.pop();
			// AbsoluteAddress t4 = l.pop();
			Instruction i1 = map.get(t1);
			// Instruction i2 = map.get(t2);

			if (i1 instanceof X86CondJmpInstruction) {
				boolean reCond = false;

				if (neg != null && !neg.isEmpty() && neg.containsKey(t1)
						&& neg.get(t1).getValue() == t2.getValue()) {
					// instName = reverseConditionJump(instName);
					reCond = true;
					// System.out.println("Reverse Conditional Jump:" +
					// instName);
				}

				if (i1.getName().equals("je")) {
					if (reCond)
						return 0;
					else
						return 1;
				}

				if (i1.getName().equals("jne")) {
					if (reCond)
						return 1;
					else
						return 0;
				}
			}
		} else if (l.length() >= 2) {
			AbsoluteAddress t1 = l.pop();
			AbsoluteAddress t2 = l.pop();
			AbsoluteAddress t3 = l.pop();
			// AbsoluteAddress t4 = l.pop();
			Instruction i1 = map.get(t1);
			Instruction i2 = map.get(t2);

			if (i2 instanceof X86CondJmpInstruction) {
				boolean reCond = false;

				if (neg != null && !neg.isEmpty() && neg.containsKey(t1)
						&& neg.get(t2).getValue() == t3.getValue()) {
					// instName = reverseConditionJump(instName);
					reCond = true;
					// System.out.println("Reverse Conditional Jump:" +
					// instName);
				}

				if (i1.getName().equals("je")) {
					if (reCond)
						return 0;
					else
						return 1;
				}

				if (i1.getName().equals("jne")) {
					if (reCond)
						return 1;
					else
						return 0;
				}
			} else if (i1 instanceof X86CondJmpInstruction) {
				boolean reCond = false;

				if (neg != null && !neg.isEmpty() && neg.containsKey(t1)
						&& neg.get(t1).getValue() == t2.getValue()) {
					// instName = reverseConditionJump(instName);
					reCond = true;
					// System.out.println("Reverse Conditional Jump:" +
					// instName);
				}

				if (i1.getName().equals("je")) {
					if (reCond)
						return 0;
					else
						return 1;
				}

				if (i1.getName().equals("jne")) {
					if (reCond)
						return 1;
					else
						return 0;
				}
			}
		}

		// TODO Auto-generated method stub
		return 3;
	}

	/**
	 * @return the libraryName
	 */
	public String getLibraryName() {
		return libraryName;
	}

	/**
	 * @param libraryName
	 *            the libraryName to set
	 */
	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}

	@Override
	public boolean equalsLibraryName(String l) {
		// TODO Auto-generated method stub
		return libraryName.equals(l.toLowerCase());
	}
	
	// PHONG: insert here
	// Need to improve here, process the opcodes[] for best performance
	public byte[] getOpcodesArray(BPState curState, long address){
		VirtualMemory vM = curState.getEnvironement().getSystem()
				.getVirtualHandle().getCurrentVirtualMemory();
		vM.setAddress(address);
//			long offset = address - vM.getBaseAddress();
		byte[] opcodes = new byte[(int)vM.getSize()];
		// can modify here for best result, i < 10, because one asm statement needs 10 bytes or less
		for (int i = 0; i < /*vM.getSize() - offset*/10; i++){
			long virtualAdrr = vM.getAddress() + (long)i;
			opcodes[i] = (byte)((LongValue)curState.getEnvironement().getMemory()
					.getByteMemoryValue(virtualAdrr)).getValue();
		}
		return opcodes;
	}	
	
}
