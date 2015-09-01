/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi.kernel32
 * File name: User32DLL.java
 * Created date: Jan 30, 2015
 * Decription:
 * 
 */
package v2.org.analysis.apihandle.winapi.user32;

import java.nio.Buffer;
import java.nio.ByteBuffer;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef.*;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.WinUser.MSG;
import com.sun.jna.platform.win32.WinUser.WNDCLASSEX;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

import v2.org.analysis.apihandle.winapi.structures.WinUser.DLGPROC;
import v2.org.analysis.apihandle.winapi.structures.WinUser.DLGTEMPLATE;
import v2.org.analysis.apihandle.winapi.structures.WinUser.MENUITEMINFO;
import v2.org.analysis.apihandle.winapi.structures.WinUser.WNDCLASS;

/**
 * @author Yen Nguyen
 *
 */

public interface User32DLL extends StdCallLibrary {
	User32DLL INSTANCE = (User32DLL) Native.loadLibrary("user32", User32DLL.class, W32APIOptions.DEFAULT_OPTIONS);
	User32DLL SYNC_INSTANCE = (User32DLL) Native.synchronizedLibrary(INSTANCE);

	/**
	 * This function places a message in the message queue associated with the
	 * thread that created the specified window and then returns without waiting
	 * for the thread to process the message. Messages in a message queue are
	 * retrieved by calls to the GetMessage or PeekMessage function.
	 * 
	 * @param hWnd
	 *            : Handle to the window whose window procedure is to receive
	 *            the message.
	 * @param msg
	 *            : Specifies the message to be posted.
	 * @param wParam
	 *            : Specifies additional message-specific information.
	 * @param lParam
	 *            : Specifies additional message-specific information.
	 *
	 * @return If the function succeeds, the return value is nonzero.
	 */
	BOOL PostMessage(HWND hWnd, int Msg, WPARAM wParam, LPARAM lParam);

	/**
	 * Sends the specified message to a window or windows. The SendMessage
	 * function calls the window procedure for the specified window and does not
	 * return until the window procedure has processed the message.
	 * 
	 * @param hWnd
	 *            : A handle to the window whose window procedure will receive
	 *            the message. If this parameter is HWND_BROADCAST
	 *            ((HWND)0xffff), the message is sent to all top-level windows
	 *            in the system, including disabled or invisible unowned
	 *            windows, overlapped windows, and pop-up windows; but the
	 *            message is not sent to child windows.
	 * 
	 * @param Msg
	 *            : The message to be sent.
	 * 
	 * @param wParam
	 *            : Additional message-specific information.
	 * 
	 * @param lParam
	 *            : Additional message-specific information.
	 * 
	 * @return The return value specifies the result of the message processing;
	 *         it depends on the message sent.
	 */
	LRESULT SendMessage(HWND hWnd, int Msg, WPARAM wParam, LPARAM lParam);

	/**
	 * Displays a modal dialog box that contains a system icon, a set of
	 * buttons, and a brief application-specific message, such as status or
	 * error information. The message box returns an integer value that
	 * indicates which button the user clicked.
	 * 
	 * @param hWnd
	 *            : A handle to the owner window of the message box to be
	 *            created. If this parameter is NULL, the message box has no
	 *            owner window.
	 * 
	 * @param lpText
	 *            : The message to be displayed. If the string consists of more
	 *            than one line, you can separate the lines using a carriage
	 *            return and/or linefeed character between each line.
	 * 
	 * @param lpCaption
	 *            : The dialog box title. If this parameter is NULL, the default
	 *            title is Error.
	 * 
	 * @param uType
	 *            : The contents and behavior of the dialog box. This parameter
	 *            can be a combination of flags from the following groups of
	 *            flags.
	 * 
	 * @return If a message box has a Cancel button, the function returns the
	 *         IDCANCEL value if either the ESC key is pressed or the Cancel
	 *         button is selected. If the message box has no Cancel button,
	 *         pressing ESC has no effect.
	 */
	int MessageBox(HWND hWnd, String lpText, String lpCaption, UINT uType);

	/**
	 * Converts a character string or a single character to lowercase. If the
	 * operand is a character string, the function converts the characters in
	 * place.
	 * 
	 * @param lpsz
	 *            A null-terminated string, or specifies a single character. If
	 *            the high-order word of this parameter is zero, the low-order
	 *            word must contain a single character to be converted.
	 * 
	 * @return If the operand is a character string, the function returns a
	 *         pointer to the converted string. Because the string is converted
	 *         in place, the return value is equal to lpsz.
	 * 
	 *         If the operand is a single character, the return value is a
	 *         32-bit value whose high-order word is zero, and low-order word
	 *         contains the converted character.
	 */
	Pointer CharLower(/* _Inout_ */char[] lpsz);

	/**
	 * Retrieves a pointer to the next character in a string. This function can
	 * handle strings consisting of either single- or multi-byte characters.
	 * 
	 * @param lpsz
	 *            A character in a null-terminated string.
	 * 
	 * @return The return value is a pointer to the next character in the
	 *         string, or to the terminating null character if at the end of the
	 *         string. If lpsz points to the terminating null character, the
	 *         return value is equal to lpsz.
	 */
	long CharNext(/* _In_ */char[] lpsz);

	/**
	 * Retrieves a pointer to the preceding character in a string. This function
	 * can handle strings consisting of either single- or multi-byte characters.
	 * 
	 * @param lpszStart
	 *            The beginning of the string.
	 * 
	 * @param lpszCurrent
	 *            A character in a null-terminated string.
	 * 
	 * @return The return value is a pointer to the preceding character in the
	 *         string, or to the first character in the string if the
	 *         lpszCurrent parameter equals the lpszStart parameter.
	 */
	WString CharPrev(/* _In_ */WString lpszStart, /* _In_ */WString lpszCurrent);

	/**
	 * Converts lowercase characters in a buffer to uppercase characters. The
	 * function converts the characters in place.
	 * 
	 * @param lpsz
	 *            [in, out] A buffer containing one or more characters to be
	 *            processed.
	 * 
	 * @param cchLength
	 *            The size, in characters, of the buffer pointed to by lpsz.
	 * 
	 * @return The return value is the number of characters processed.
	 */
	DWORD CharUpperBuff(/* _Inout_ */char[] lpsz, /* _In_ */DWORD cchLength);

	HWND CreateDialogParamW(
	/* _In_opt_ */HINSTANCE hInstance,
	/* _In_ */String lpTemplateName,
	/* _In_opt_ */HWND hWndParent,
	/* _In_opt_ *//* DLGPROC */int lpDialogFunc,
	/* _In_ */LPARAM dwInitParam);

	/**
	 * Destroys the specified menu and frees any memory that the menu occupies.
	 * 
	 * @param hMenu
	 *            [in] A handle to the menu to be destroyed.
	 * 
	 * @return If the function succeeds, the return value is nonzero.
	 */
	BOOL DestroyMenu(/* _In_ */HMENU hMenu);

	/**
	 * Retrieves information about a window class, including a handle to the
	 * small icon associated with the window class. The GetClassInfo function
	 * does not retrieve a handle to the small icon.
	 * 
	 * @param hinst
	 *            A handle to the instance of the application that created the
	 *            class. To retrieve information about classes defined by the
	 *            system (such as buttons or list boxes), set this parameter to
	 *            NULL.
	 * 
	 * @param lpszClass
	 *            The class name. The name must be that of a preregistered class
	 *            or a class registered by a previous call to the RegisterClass
	 *            or RegisterClassEx function. Alternatively, this parameter can
	 *            be a class atom created by a previous call to RegisterClass or
	 *            RegisterClassEx. The atom must be in the low-order word of
	 *            lpszClass; the high-order word must be zero.
	 * 
	 * @param lpwcx
	 *            A pointer to a WNDCLASSEX structure that receives the
	 *            information about the class.
	 * 
	 * @return If the function finds a matching class and successfully copies
	 *         the data, the return value is nonzero.
	 */
	BOOL GetClassInfoEx(/* _In_opt_ */HINSTANCE hinst, /* _In_ */
			WString lpszClass, /* _Out_ */WNDCLASSEX lpwcx);

	/**
	 * Retrieves the handle to the window that has the keyboard focus, if the
	 * window is attached to the calling thread's message queue.
	 * 
	 * @return The return value is the handle to the window with the keyboard
	 *         focus. If the calling thread's message queue does not have an
	 *         associated window with the keyboard focus, the return value is
	 *         NULL.
	 */
	HWND GetFocus();

	/**
	 * Retrieves information about the current keyboard.
	 * 
	 * @param nTypeFlag
	 *            The type of keyboard information to be retrieved.
	 * 
	 * @return If the function succeeds, the return value specifies the
	 *         requested information.
	 */
	int GetKeyboardType(/* _In_ */int nTypeFlag);

	/**
	 * Retrieves a message from the calling thread's message queue. The function
	 * dispatches incoming sent messages until a posted message is available for
	 * retrieval.
	 * 
	 * @param lpMsg
	 *            A pointer to an MSG structure that receives message
	 *            information from the thread's message queue.
	 * 
	 * @param hWnd
	 *            A handle to the window whose messages are to be retrieved. The
	 *            window must belong to the current thread.
	 * 
	 * @param wMsgFilterMin
	 *            The integer value of the lowest message value to be retrieved.
	 *            Use WM_KEYFIRST (0x0100) to specify the first keyboard message
	 *            or WM_MOUSEFIRST (0x0200) to specify the first mouse message.
	 * 
	 * @param wMsgFilterMax
	 *            The integer value of the highest message value to be
	 *            retrieved. Use WM_KEYLAST to specify the last keyboard message
	 *            or WM_MOUSELAST to specify the last mouse message.
	 * 
	 * @return If the function retrieves a message other than WM_QUIT, the
	 *         return value is nonzero. If the function retrieves the WM_QUIT
	 *         message, the return value is zero.
	 */
	BOOL GetMessage(/* _Out_ */MSG lpMsg, /* _In_opt_ */HWND hWnd, /* _In_ */
			UINT wMsgFilterMin, /* _In_ */UINT wMsgFilterMax);

	/**
	 * Retrieves the current color of the specified display element. Display
	 * elements are the parts of a window and the display that appear on the
	 * system display screen.
	 * 
	 * @param nIndex
	 *            The display element whose color is to be retrieved. This
	 *            parameter can be one of the following values.
	 * 
	 * @return The function returns the red, green, blue (RGB) color value of
	 *         the given element. If the nIndex parameter is out of range, the
	 *         return value is zero. Because zero is also a valid RGB value, you
	 *         cannot use GetSysColor to determine whether a system color is
	 *         supported by the current platform. Instead, use the
	 *         GetSysColorBrush function, which returns NULL if the color is not
	 *         supported.
	 */
	DWORD GetSysColor(/* _In_ */int nIndex);

	/**
	 * The GetSysColorBrush function retrieves a handle identifying a logical
	 * brush that corresponds to the specified color index.
	 * 
	 * @param nIndex
	 *            A color index. This value corresponds to the color used to
	 *            paint one of the window elements. See GetSysColor for system
	 *            color index values.
	 * 
	 * @return The return value identifies a logical brush if the nIndex
	 *         parameter is supported by the current platform. Otherwise, it
	 *         returns NULL.
	 */
	HBRUSH GetSysColorBrush(/* _In_ */int nIndex);

	/**
	 * Enables the application to access the window menu (also known as the
	 * system menu or the control menu) for copying and modifying.
	 * 
	 * @param hWnd
	 *            A handle to the window that will own a copy of the window
	 *            menu.
	 * 
	 * @param bRevert
	 *            The action to be taken. If this parameter is FALSE,
	 *            GetSystemMenu returns a handle to the copy of the window menu
	 *            currently in use. The copy is initially identical to the
	 *            window menu, but it can be modified. If this parameter is
	 *            TRUE, GetSystemMenu resets the window menu back to the default
	 *            state. The previous window menu, if any, is destroyed.
	 * 
	 * @return If the bRevert parameter is FALSE, the return value is a handle
	 *         to a copy of the window menu. If the bRevert parameter is TRUE,
	 *         the return value is NULL.
	 */
	HMENU GetSystemMenu(/* _In_ */HWND hWnd, /* _In_ */BOOL bRevert);

	/**
	 * Retrieves the identifier of the thread that created the specified window
	 * and, optionally, the identifier of the process that created the window..
	 * 
	 * @param hWnd
	 *            A handle to the window.
	 * 
	 * @param lpdwProcessId
	 *            A pointer to a variable that receives the process identifier.
	 *            If this parameter is not NULL, GetWindowThreadProcessId copies
	 *            the identifier of the process to the variable; otherwise, it
	 *            does not.
	 * 
	 * @return The return value is the identifier of the thread that created the
	 *         window.
	 */
	DWORD GetWindowThreadProcessId(/* _In_ */HWND hWnd, /* _Out_opt_ */
			DWORDByReference lpdwProcessId);

	/**
	 * Inserts a new menu item at the specified position in a menu.
	 * 
	 * @param hMenu
	 *            A handle to the menu in which the new menu item is inserted.
	 * 
	 * @param uItem
	 *            The identifier or position of the menu item before which to
	 *            insert the new item. The meaning of this parameter depends on
	 *            the value of fByPosition.
	 * 
	 * @param fByPosition
	 *            Controls the meaning of uItem. If this parameter is FALSE,
	 *            uItem is a menu item identifier. Otherwise, it is a menu item
	 *            position. See Accessing Menu Items Programmatically for more
	 *            information.
	 * 
	 * @param lpmii
	 *            A pointer to a MENUITEMINFO structure that contains
	 *            information about the new menu item.
	 * 
	 * @return If the function succeeds, the return value is nonzero.
	 */
	BOOL /* WINAPI */InsertMenuItem(/* _In_ */HMENU hMenu, /* _In_ */
			UINT uItem, /* _In_ */BOOL fByPosition, /* _In_ */MENUITEMINFO lpmii);

	/**
	 * Determines whether a message is intended for the specified dialog box
	 * and, if it is, processes the message.
	 * 
	 * @param hDlg
	 *            A handle to the dialog box.
	 * 
	 * @param lpMsg
	 *            A pointer to an MSG structure that contains the message to be
	 *            checked.
	 * 
	 * @return If the message has been processed, the return value is nonzero.
	 *         If the message has not been processed, the return value is zero.
	 */
	BOOL IsDialogMessage(/* _In_ */HWND hDlg, /* _In_ */MSG lpMsg);

	/**
	 * [LoadBitmap is available for use in the operating systems specified in
	 * the Requirements section. It may be altered or unavailable in subsequent
	 * versions. Instead, use LoadImage and DrawFrameControl.] The LoadBitmap
	 * function loads the specified bitmap resource from a module's executable
	 * file.
	 * 
	 * @param hInstance
	 *            A handle to the instance of the module whose executable file
	 *            contains the bitmap to be loaded.
	 * 
	 * @param lpBitmapName
	 *            A pointer to a null-terminated string that contains the name
	 *            of the bitmap resource to be loaded. Alternatively, this
	 *            parameter can consist of the resource identifier in the
	 *            low-order word and zero in the high-order word. The
	 *            MAKEINTRESOURCE macro can be used to create this value.
	 * 
	 * @return If the function succeeds, the return value is the handle to the
	 *         specified bitmap. If the function fails, the return value is
	 *         NULL.
	 */
	HBITMAP LoadBitmap(/* _In_ */HINSTANCE hInstance, /* _In_ */
			WString lpBitmapName);

	/**
	 * Loads the specified cursor resource from the executable (.EXE) file
	 * associated with an application instance.
	 * 
	 * @param hInstance
	 *            A handle to an instance of the module whose executable file
	 *            contains the cursor to be loaded.
	 * 
	 * @param lpCursorName
	 *            The name of the cursor resource to be loaded. Alternatively,
	 *            this parameter can consist of the resource identifier in the
	 *            low-order word and zero in the high-order word. The
	 *            MAKEINTRESOURCE macro can also be used to create this value.
	 *            To use one of the predefined cursors, the application must set
	 *            the hInstance parameter to NULL and the lpCursorName parameter
	 *            to one the following values.
	 * 
	 * @return If the function succeeds, the return value is the handle to the
	 *         newly loaded cursor. If the function fails, the return value is
	 *         NULL. To get extended error information, call GetLastError.
	 */
	HCURSOR LoadCursor(/* _In_opt_ */HINSTANCE hInstance, /* _In_ */
			WString lpCursorName);

	/**
	 * Loads the specified icon resource from the executable (.exe) file
	 * associated with an application instance.
	 * 
	 * @param hInstance
	 *            A handle to an instance of the module whose executable file
	 *            contains the icon to be loaded. This parameter must be NULL
	 *            when a standard icon is being loaded.
	 * 
	 * @param lpIconName
	 *            The name of the icon resource to be loaded. Alternatively,
	 *            this parameter can contain the resource identifier in the
	 *            low-order word and zero in the high-order word. Use the
	 *            MAKEINTRESOURCE macro to create this value.
	 * 
	 * @return If the function succeeds, the return value is a handle to the
	 *         newly loaded icon. If the function fails, the return value is
	 *         NULL. To get extended error information, call GetLastError.
	 */
	HICON LoadIcon(/* _In_opt_ */HINSTANCE hInstance, /* _In_ */
			WString lpIconName);

	/**
	 * Loads the specified menu resource from the executable (.exe) file
	 * associated with an application instance.
	 * 
	 * @param hInstance
	 *            A handle to the module containing the menu resource to be
	 *            loaded.
	 * 
	 * @param lpMenuName
	 *            The name of the menu resource. Alternatively, this parameter
	 *            can consist of the resource identifier in the low-order word
	 *            and zero in the high-order word. To create this value, use the
	 *            MAKEINTRESOURCE macro.
	 * 
	 * @return If the function succeeds, the return value is a handle to the
	 *         menu resource. If the function fails, the return value is NULL.
	 *         To get extended error information, call GetLastError.
	 */
	HMENU LoadMenu(/* _In_opt_ */HINSTANCE hInstance, /* _In_ */
			WString lpMenuName);

	/**
	 * Loads a string resource from the executable file associated with a
	 * specified module, copies the string into a buffer, and appends a
	 * terminating null character.
	 * 
	 * @param hInstance
	 *            A handle to an instance of the module whose executable file
	 *            contains the string resource. To get the handle to the
	 *            application itself, call the GetModuleHandle function with
	 *            NULL.
	 * 
	 * @param uID
	 *            The identifier of the string to be loaded.
	 * 
	 * @param lpBuffer
	 *            The buffer is to receive the string. Must be of sufficient
	 *            length to hold a pointer (8 bytes).
	 * 
	 * @param nBufferMax
	 *            The size of the buffer, in characters. The string is truncated
	 *            and null-terminated if it is longer than the number of
	 *            characters specified. If this parameter is 0, then lpBuffer
	 *            receives a read-only pointer to the resource itself.
	 * 
	 * @return If the function succeeds, the return value is the number of
	 *         characters copied into the buffer, not including the terminating
	 *         null character, or zero if the string resource does not exist. To
	 *         get extended error information, call GetLastError.
	 */
	int LoadString(/* _In_opt_ */HINSTANCE hInstance, /* _In_ */UINT uID, /* _Out_ */
			char[] lpBuffer, /* _In_ */int nBufferMax);

	/**
	 * Opens the clipboard for examination and prevents other applications from
	 * modifying the clipboard content.
	 * 
	 * @param hWndNewOwner
	 *            A handle to the window to be associated with the open
	 *            clipboard. If this parameter is NULL, the open clipboard is
	 *            associated with the current task.
	 * 
	 * @return If the function succeeds, the return value is nonzero.
	 */
	BOOL OpenClipboard(/* _In_opt_ */HWND hWndNewOwner);

	/**
	 * Registers a window class for subsequent use in calls to the CreateWindow
	 * or CreateWindowEx function.
	 * 
	 * @param lpWndClass
	 *            A pointer to a WNDCLASS structure. You must fill the structure
	 *            with the appropriate class attributes before passing it to the
	 *            function.
	 * 
	 * @return If the function succeeds, the return value is a class atom that
	 *         uniquely identifies the class being registered. This atom can
	 *         only be used by the CreateWindow, CreateWindowEx, GetClassInfo,
	 *         GetClassInfoEx, FindWindow, FindWindowEx, and UnregisterClass
	 *         functions and the IActiveIMMap::FilterClientWindows method. If
	 *         the function fails, the return value is zero. To get extended
	 *         error information, call GetLastError.
	 */
	ATOM RegisterClass(/* _In_ */WNDCLASS lpWndClass);

	/**
	 * Sets the cursor shape.
	 * 
	 * @param hCursor
	 *            A handle to the cursor. The cursor must have been created by
	 *            the CreateCursor function or loaded by the LoadCursor or
	 *            LoadImage function. If this parameter is NULL, the cursor is
	 *            removed from the screen.
	 * 
	 * @return The return value is the handle to the previous cursor, if there
	 *         was one. If there was no previous cursor, the return value is
	 *         NULL.
	 */
	HCURSOR SetCursor(/* _In_opt_ */HCURSOR hCursor);

	/**
	 * Sets the specified window's show state.
	 * 
	 * @param hWnd
	 *            A handle to the window.
	 * 
	 * @param nCmdShow
	 *            Controls how the window is to be shown. This parameter is
	 *            ignored the first time an application calls ShowWindow, if the
	 *            program that launched the application provides a STARTUPINFO
	 *            structure. Otherwise, the first time ShowWindow is called, the
	 *            value should be the value obtained by the WinMain function in
	 *            its nCmdShow parameter.
	 * 
	 * @return If the window was previously visible, the return value is
	 *         nonzero. If the window was previously hidden, the return value is
	 *         zero.
	 */
	BOOL ShowWindow(/* _In_ */HWND hWnd, /* _In_ */int nCmdShow);

	/**
	 * Translates virtual-key messages into character messages. The character
	 * messages are posted to the calling thread's message queue, to be read the
	 * next time the thread calls the GetMessage or PeekMessage function.
	 * 
	 * @param lpMsg
	 *            A pointer to an MSG structure that contains message
	 *            information retrieved from the calling thread's message queue
	 *            by using the GetMessage or PeekMessage function.
	 * 
	 * @return If the message is translated (that is, a character message is
	 *         posted to the thread's message queue), the return value is
	 *         nonzero. If the message is WM_KEYDOWN, WM_KEYUP, WM_SYSKEYDOWN,
	 *         or WM_SYSKEYUP, the return value is nonzero, regardless of the
	 *         translation. If the message is not translated (that is, a
	 *         character message is not posted to the thread's message queue),
	 *         the return value is zero.
	 */
	BOOL TranslateMessage(/* _In_ */MSG lpMsg);

	/**
	 * Unregisters a window class, freeing the memory required for the class.
	 * 
	 * @param lpClassName
	 *            A null-terminated string or a class atom. If lpClassName is a
	 *            string, it specifies the window class name. This class name
	 *            must have been registered by a previous call to the
	 *            RegisterClass or RegisterClassEx function. System classes,
	 *            such as dialog box controls, cannot be unregistered. If this
	 *            parameter is an atom, it must be a class atom created by a
	 *            previous call to the RegisterClass or RegisterClassEx
	 *            function. The atom must be in the low-order word of
	 *            lpClassName; the high-order word must be zero.
	 * 
	 * @param hInstance
	 *            A handle to the instance of the module that created the class.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         class could not be found or if a window still exists that was
	 *         created with the class, the return value is zero. To get extended
	 *         error information, call GetLastError.
	 */
	BOOL UnregisterClass(/* _In_ */String lpClassName, /* _In_opt_ */HINSTANCE hInstance);

	/**
	 * Examines the Z order of the child windows associated with the specified
	 * parent window and retrieves a handle to the child window at the top of
	 * the Z order.
	 * 
	 * @param hWnd
	 *            A handle to the parent window whose child windows are to be
	 *            examined. If this parameter is NULL, the function returns a
	 *            handle to the window at the top of the Z order.
	 * 
	 * @return If the function succeeds, the return value is a handle to the
	 *         child window at the top of the Z order. If the specified window
	 *         has no child windows, the return value is NULL. To get extended
	 *         error information, use the GetLastError function.
	 */
	HWND GetTopWindow(/* _In_opt_ */HWND hWnd);

	/**
	 * Blocks keyboard and mouse input events from reaching applications.
	 * 
	 * @param fBlockIt
	 *            The function's purpose. If this parameter is TRUE, keyboard
	 *            and mouse input events are blocked. If this parameter is
	 *            FALSE, keyboard and mouse events are unblocked. Note that only
	 *            the thread that blocked input can successfully unblock input.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If input
	 *         is already blocked, the return value is zero. To get extended
	 *         error information, call GetLastError.
	 */
	BOOL BlockInput(/* _In_ */BOOL fBlockIt);

	/**
	 * Retrieves information about the specified window. The function also
	 * retrieves the 32-bit (DWORD) value at the specified offset into the extra
	 * window memory.
	 * 
	 * @param hWnd
	 *            A handle to the window and, indirectly, the class to which the
	 *            window belongs.
	 * 
	 * @param nIndex
	 *            The zero-based offset to the value to be retrieved. Valid
	 *            values are in the range zero through the number of bytes of
	 *            extra window memory, minus four; for example, if you specified
	 *            12 or more bytes of extra memory, a value of 8 would be an
	 *            index to the third 32-bit integer. To retrieve any other
	 *            value, specify one of the following values.
	 * 
	 * @return If the function succeeds, the return value is the requested
	 *         value. If the function fails, the return value is zero. To get
	 *         extended error information, call GetLastError.
	 */
	LONG GetWindowLong(/* _In_ */HWND hWnd, /* _In_ */int nIndex);

	/**
	 * Changes an attribute of the specified window. The function also sets the
	 * 32-bit (long) value at the specified offset into the extra window memory.
	 * 
	 * @param hWnd
	 *            A handle to the window and, indirectly, the class to which the
	 *            window belongs.
	 * 
	 * @param nIndex
	 *            The zero-based offset to the value to be set. Valid values are
	 *            in the range zero through the number of bytes of extra window
	 *            memory, minus the size of an integer. To set any other value,
	 *            specify one of the following values.
	 * 
	 * @param dwNewLong
	 *            The replacement value.
	 * 
	 * @return If the function succeeds, the return value is the previous value
	 *         of the specified 32-bit integer. If the function fails, the
	 *         return value is zero. To get extended error information, call
	 *         GetLastError.
	 */
	LONG SetWindowLong(/* _In_ */HWND hWnd,/* _In_ */int nIndex,/* _In_ */LONG dwNewLong);

	/**
	 * Retrieves a handle to the Shell's desktop window.
	 * 
	 * @return The return value is the handle of the Shell's desktop window. If
	 *         no Shell process is present, the return value is NULL.
	 */
	HWND GetShellWindow();

	/**
	 * Creates a modal dialog box from a dialog box template resource. Before
	 * displaying the dialog box, the function passes an application-defined
	 * value to the dialog box procedure as the lParam parameter of the
	 * WM_INITDIALOG message. An application can use this value to initialize
	 * dialog box controls.
	 * 
	 * @param hInstance
	 *            A handle to the module which contains the dialog box template.
	 *            If this parameter is NULL, then the current executable is
	 *            used.
	 * 
	 * @param lpTemplateName
	 *            The dialog box template. This parameter is either the pointer
	 *            to a null-terminated character string that specifies the name
	 *            of the dialog box template or an integer value that specifies
	 *            the resource identifier of the dialog box template. If the
	 *            parameter specifies a resource identifier, its high-order word
	 *            must be zero and its low-order word must contain the
	 *            identifier. You can use the MAKEINTRESOURCE macro to create
	 *            this value.
	 * 
	 * @param hWndParent
	 *            A handle to the window that owns the dialog box.
	 * 
	 * @param lpDialogFunc
	 *            A pointer to the dialog box procedure. For more information
	 *            about the dialog box procedure, see DialogProc.
	 * 
	 * @param dwInitParam
	 *            The value to pass to the dialog box in the lParam parameter of
	 *            the WM_INITDIALOG message.
	 * 
	 * @return If the function succeeds, the return value is the value of the
	 *         nResult parameter specified in the call to the EndDialog function
	 *         used to terminate the dialog box. If the function fails because
	 *         the hWndParent parameter is invalid, the return value is zero.
	 *         The function returns zero in this case for compatibility with
	 *         previous versions of Windows. If the function fails for any other
	 *         reason, the return value is –1. To get extended error
	 *         information, call GetLastError.
	 */
	INT_PTR DialogBoxParam(/* _In_opt_ */HINSTANCE hInstance, /* _In_ */String lpTemplateName, /* _In_opt_ */
			HWND hWndParent, /* _In_opt_ */DLGPROC lpDialogFunc, /* _In_ */LPARAM dwInitParam);

	/**
	 * The OffsetRect function moves the specified rectangle by the specified
	 * offsets.
	 * 
	 * @param lprc
	 *            Pointer to a RECT structure that contains the logical
	 *            coordinates of the rectangle to be moved.
	 * 
	 * @param dx
	 *            Specifies the amount to move the rectangle left or right. This
	 *            parameter must be a negative value to move the rectangle to
	 *            the left.
	 * 
	 * @param dy
	 *            Specifies the amount to move the rectangle up or down. This
	 *            parameter must be a negative value to move the rectangle up.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is zero.
	 */
	BOOL OffsetRect(/* _Inout_ */RECT lprc, /* _In_ */int dx, /* _In_ */int dy);

	/**
	 * Provides default processing for any window messages that the window
	 * procedure of a multiple-document interface (MDI) frame window does not
	 * process. All window messages that are not explicitly processed by the
	 * window procedure must be passed to the DefFrameProc function, not the
	 * DefWindowProc function.
	 * 
	 * @param hWnd
	 *            A handle to the MDI frame window.
	 * 
	 * @param hWndMDIClient
	 *            A handle to the MDI client window.
	 * 
	 * @param uMsg
	 *            The message to be processed.
	 * 
	 * @param wParam
	 *            Additional message-specific information.
	 * 
	 * @param lParam
	 *            Additional message-specific information.
	 * 
	 * @return The return value specifies the result of the message processing
	 *         and depends on the message. If the hWndMDIClient parameter is
	 *         NULL, the return value is the same as for the DefWindowProc
	 *         function.
	 */
	LRESULT DefFrameProc(
	/* _In_ */HWND hWnd,
	/* _In_ */HWND hWndMDIClient,
	/* _In_ */UINT uMsg,
	/* _In_ */WPARAM wParam,
	/* _In_ */LPARAM lParam);

	/**
	 * Copies the text of the specified window's title bar (if it has one) into
	 * a buffer. If the specified window is a control, the text of the control
	 * is copied. However, GetWindowText cannot retrieve the text of a control
	 * in another application.
	 * 
	 * @param hWnd
	 *            A handle to the window or control containing the text.
	 * 
	 * @param lpString
	 *            The buffer that will receive the text. If the string is as
	 *            long or longer than the buffer, the string is truncated and
	 *            terminated with a null character.
	 * 
	 * @param nMaxCount
	 *            The maximum number of characters to copy to the buffer,
	 *            including the null character. If the text exceeds this limit,
	 *            it is truncated.
	 * 
	 * @return If the function succeeds, the return value is the length, in
	 *         characters, of the copied string, not including the terminating
	 *         null character. If the window has no title bar or text, if the
	 *         title bar is empty, or if the window or control handle is
	 *         invalid, the return value is zero. To get extended error
	 *         information, call GetLastError.
	 */
	int GetWindowText(
	/* _In_ */HWND hWnd,
	/* _Out_ */char[] lpString,
	/* _In_ */int nMaxCount);

	/**
	 * Sets the caret blink time to the specified number of milliseconds. The
	 * blink time is the elapsed time, in milliseconds, required to invert the
	 * caret's pixels.
	 * 
	 * @param uMSeconds
	 *            The new blink time, in milliseconds.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is zero. To get extended error
	 *         information, call GetLastError.
	 */
	BOOL SetCaretBlinkTime(/* _In_ */UINT uMSeconds);

	/**
	 * 
	 * @param hInstance
	 * @param hDialogTemplate
	 * @param hWndParent
	 * @param lpDialogFunc
	 * @param dwInitParam
	 * @return
	 */
	INT_PTR DialogBoxIndirectParam(
	/* _In_opt_ */HINSTANCE hInstance,
	/* _In_ */DLGTEMPLATE hDialogTemplate,
	/* _In_opt_ */HWND hWndParent,
	/* _In_opt_ */DLGPROC lpDialogFunc,
	/* _In_ */LPARAM dwInitParam);

	/**
	 * The InflateRect function increases or decreases the width and height of
	 * the specified rectangle. The InflateRect function adds dx units to the
	 * left and right ends of the rectangle and dy units to the top and bottom.
	 * The dx and dy parameters are signed values; positive values increase the
	 * width and height, and negative values decrease them.
	 * 
	 * @param lprc
	 *            A pointer to the RECT structure that increases or decreases in
	 *            size.
	 * 
	 * @param dx
	 *            The amount to increase or decrease the rectangle width. This
	 *            parameter must be negative to decrease the width.
	 * 
	 * @param dy
	 *            The amount to increase or decrease the rectangle height. This
	 *            parameter must be negative to decrease the height.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is zero.
	 */
	BOOL InflateRect(
	/* _Inout_ */RECT lprc,
	/* _In_ */int dx,
	/* _In_ */int dy);

	/**
	 * Determines the visibility state of the specified window.
	 * 
	 * @param hWnd
	 *            A handle to the window to be tested.
	 * 
	 * @return If the specified window, its parent window, its parent's parent
	 *         window, and so forth, have the WS_VISIBLE style, the return value
	 *         is nonzero. Otherwise, the return value is zero. Because the
	 *         return value specifies whether the window has the WS_VISIBLE
	 *         style, it may be nonzero even if the window is totally obscured
	 *         by other windows.
	 */
	BOOL IsWindowVisible(/* _In_ */HWND hWnd);

	/**
	 * Retrieves the dimensions of the bounding rectangle of the specified
	 * window. The dimensions are given in screen coordinates that are relative
	 * to the upper-left corner of the screen.
	 * 
	 * @param hWnd
	 *            A handle to the window.
	 * 
	 * @param lpRect
	 *            A pointer to a RECT structure that receives the screen
	 *            coordinates of the upper-left and lower-right corners of the
	 *            window.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is zero. To get extended error
	 *         information, call GetLastError.
	 */
	BOOL GetWindowRect(/* _In_ */HWND hWnd, /* _Out_ */RECT lpRect);

	/**
	 * The EqualRect function determines whether the two specified rectangles
	 * are equal by comparing the coordinates of their upper-left and
	 * lower-right corners.
	 * 
	 * @param lprc1
	 *            Pointer to a RECT structure that contains the logical
	 *            coordinates of the first rectangle.
	 * 
	 * @param lprc2
	 *            Pointer to a RECT structure that contains the logical
	 *            coordinates of the second rectangle.
	 * 
	 * @return If the two rectangles are identical, the return value is nonzero.
	 *         If the two rectangles are not identical, the return value is
	 *         zero.
	 */
	BOOL EqualRect(
	/* _In_ const */RECT lprc1,
	/* _In_ const */RECT lprc2);

	/**
	 * Retrieves the position of the mouse cursor, in screen coordinates.
	 * 
	 * @param lpPoint
	 *            A pointer to a POINT structure that receives the screen
	 *            coordinates of the cursor.
	 * 
	 * @return Returns nonzero if successful or zero otherwise. To get extended
	 *         error information, call GetLastError.
	 */
	BOOL GetCursorPos(/* _Out_ */POINT lpPoint);

	/**
	 * The ClientToScreen function converts the client-area coordinates of a
	 * specified point to screen coordinates.
	 * 
	 * @param hWnd
	 *            A handle to the window whose client area is used for the
	 *            conversion.
	 * 
	 * @param lpPoint
	 *            A pointer to a POINT structure that contains the client
	 *            coordinates to be converted. The new screen coordinates are
	 *            copied into this structure if the function succeeds.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is zero.
	 */
	BOOL ClientToScreen(/* _In_ */HWND hWnd, /* _Inout_ */POINT lpPoint);

	/**
	 * Retrieves the window handle to the active window attached to the calling
	 * thread's message queue.
	 * 
	 * @return The return value is the handle to the active window attached to
	 *         the calling thread's message queue. Otherwise, the return value
	 *         is NULL.
	 */
	HWND GetActiveWindow();

	/**
	 * Retrieves a handle to the current window station for the calling process.
	 * 
	 * @return If the function succeeds, the return value is a handle to the
	 *         window station. If the function fails, the return value is NULL.
	 *         To get extended error information, call GetLastError.
	 */
	HANDLE GetProcessWindowStation();

	/**
	 * Retrieves information about the specified window station or desktop
	 * object.
	 * 
	 * @param hObj
	 *            A handle to the window station or desktop object. This handle
	 *            is returned by the CreateWindowStation, OpenWindowStation,
	 *            CreateDesktop, or OpenDesktop function.
	 * 
	 * @param nIndex
	 *            The information to be retrieved.
	 * 
	 * @param pvInfo
	 *            A pointer to a buffer to receive the object information.
	 * 
	 * @param nLength
	 *            The size of the buffer pointed to by the pvInfo parameter, in
	 *            bytes.
	 * 
	 * @param lpnLengthNeeded
	 *            A pointer to a variable receiving the number of bytes required
	 *            to store the requested information. If this variable's value
	 *            is greater than the value of the nLength parameter when the
	 *            function returns, the function returns FALSE, and none of the
	 *            information is copied to the pvInfo buffer. If the value of
	 *            the variable pointed to by lpnLengthNeeded is less than or
	 *            equal to the value of nLength, the entire information block is
	 *            copied.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is zero. To get extended error
	 *         information, call GetLastError.
	 */
	BOOL GetUserObjectInformation(
	/* _In_ */HANDLE hObj,
	/* _In_ */int nIndex,
	/* _Out_opt_ *//* PVOID */Buffer pvInfo,
	/* _In_ */DWORD nLength,
	/* _Out_opt_ */DWORDByReference lpnLengthNeeded);

	/**
	 * Copies the caret's position to the specified POINT structure.
	 * 
	 * @param lpPoint
	 *            A pointer to the POINT structure that is to receive the client
	 *            coordinates of the caret.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is zero. To get extended error
	 *         information, call GetLastError.
	 */
	BOOL GetCaretPos(/* _Out_ */POINT lpPoint);

	/**
	 * Creates a new shape for the system caret and assigns ownership of the
	 * caret to the specified window. The caret shape can be a line, a block, or
	 * a bitmap.
	 * 
	 * @param hWnd
	 *            A handle to the window that owns the caret.
	 * 
	 * @param hBitmap
	 *            A handle to the bitmap that defines the caret shape. If this
	 *            parameter is NULL, the caret is solid. If this parameter is
	 *            (HBITMAP) 1, the caret is gray. If this parameter is a bitmap
	 *            handle, the caret is the specified bitmap. The bitmap handle
	 *            must have been created by the CreateBitmap, CreateDIBitmap, or
	 *            LoadBitmap function. If hBitmap is a bitmap handle,
	 *            CreateCaret ignores the nWidth and nHeight parameters; the
	 *            bitmap defines its own width and height.
	 * 
	 * @param nWidth
	 *            The width of the caret, in logical units. If this parameter is
	 *            zero, the width is set to the system-defined window border
	 *            width. If hBitmap is a bitmap handle, CreateCaret ignores this
	 *            parameter.
	 * 
	 * @param nHeight
	 *            The height of the caret, in logical units. If this parameter
	 *            is zero, the height is set to the system-defined window border
	 *            height. If hBitmap is a bitmap handle, CreateCaret ignores
	 *            this parameter.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is zero. To get extended error
	 *         information, call GetLastError.
	 */
	BOOL CreateCaret(
	/* _In_ */HWND hWnd,
	/* _In_opt_ */HBITMAP hBitmap,
	/* _In_ */int nWidth,
	/* _In_ */int nHeight);

	/**
	 * Determines whether a window is maximized.
	 * 
	 * @param hWnd
	 *            A handle to the window to be tested.
	 * 
	 * @return If the window is zoomed, the return value is nonzero. If the
	 *         window is not zoomed, the return value is zero.
	 */
	BOOL IsZoomed(/* _In_ */HWND hWnd);
}
