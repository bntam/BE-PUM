package v2.org.analysis.apihandle.winapi.ws2_32;

import v2.org.analysis.apihandle.winapi.structures.WinSock.SOCKADDR;
import v2.org.analysis.apihandle.winapi.structures.WinSock.WSADATA;
import v2.org.analysis.apihandle.winapi.structures.WinSock.hostent;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;

public interface Ws2_32DLL extends StdCallLibrary {
	Ws2_32DLL INSTANCE = (Ws2_32DLL) Native.loadLibrary("ws2_32", Ws2_32DLL.class);
	Ws2_32DLL SYNC_INSTANCE = (Ws2_32DLL) Native.synchronizedLibrary(INSTANCE);

	// Not working, use Kernel32.INSTANCE.GetLastError instead
	// int WSASetLastError();

	int WSAStartup(int wVersionRequested, WSADATA lpWSAData);

	int WSACleanup();

	int socket(int af, int type, int protocol);

	int closesocket(int s);

	int connect(int s, SOCKADDR.ByReference name, int namelen);

	int bind(int s, SOCKADDR.ByReference name, int namelen);

	int accept(int s, SOCKADDR.ByReference addr, IntByReference addrlen);

	int listen(int s, int backlog);

	int gethostname(byte[] name, int namelen);

	hostent gethostbyname(String name);

	int send(int s, String buf, int len, int flags);

	int recv(int s, Pointer buf, int len, int flags);

	int shutdown(int s, int how);
}