package v2.org.analysis.apihandle.winapi.msvcrt;

import v2.org.analysis.apihandle.winapi.structures.Internal._startupinfo;
import v2.org.analysis.apihandle.winapi.structures.WinNT.CONTEXT;
import v2.org.analysis.apihandle.winapi.structures.WinNT.EXCEPTION_RECORD;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.BaseTSD.SIZE_T;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

/**
 * 
 * @author Yen Nguyen
 *
 */
public interface MSVCRTDLL extends StdCallLibrary {
	MSVCRTDLL INSTANCE = (MSVCRTDLL) Native.loadLibrary("msvcrt", MSVCRTDLL.class, W32APIOptions.DEFAULT_OPTIONS);
	MSVCRTDLL SYNC_INSTANCE = (MSVCRTDLL) Native.synchronizedLibrary(INSTANCE);

	/**
	 * Resets the floating-point package.
	 */
	void _fpreset();

	/**
	 * Invokes command-line parsing and copies the arguments to main() back
	 * through the passed pointers.
	 * 
	 * @param _Argc
	 *            An integer that contains the number of arguments that follow
	 *            in argv. The argc parameter is always greater than or equal to
	 *            1.
	 * 
	 * @param _Argv
	 *            An array of null-terminated strings representing command-line
	 *            arguments entered by the user of the program. By convention,
	 *            argv[0] is the command with which the program is invoked,
	 *            argv[1] is the first command-line argument, and so on, until
	 *            argv[argc], which is always NULL. The first command-line
	 *            argument is always argv[1] and the last one is argv[argc – 1].
	 * 
	 * @param _Env
	 *            An array of strings that represent the variables set in the
	 *            user's environment. This array is terminated by a NULL entry.
	 * 
	 * @param _DoWildCard
	 *            An integer that if set to 1 expands the wildcards in the
	 *            command line arguments, or if set to 0 does nothing.
	 * 
	 * @param _StartInfo
	 *            Other information to be passed to the CRT DLL.
	 * 
	 * @return 0 if successful; a negative value if unsuccessful.
	 */
	int __getmainargs(IntByReference _Argc, Pointer _Argv, Memory _Env, int _DoWildCard, _startupinfo _StartInfo);

	int __p__environ();

	/**
	 * Set function to be executed on exit The function pointed by func is
	 * automatically called without arguments when the program terminates
	 * normally.
	 * 
	 * If more than one atexit function has been specified by different calls to
	 * this function, they are all executed in reverse order as a stack (i.e.
	 * the last function specified is the first to be executed at exit).
	 * 
	 * A single function can be registered to be executed at exit more than
	 * once.
	 * 
	 * If atexit is called after exit, the call may or may not succeed depending
	 * on the particular system and library implementation (unspecified
	 * behavior).
	 * 
	 * If a function registered with atexit throws an exception for which it
	 * does not provide a handler when called on termination, terminate is
	 * automatically called (C++).
	 * 
	 * Particular library implementations may impose a limit on the number of
	 * functions call that can be registered with atexit, but this cannot be
	 * less than 32 function calls.
	 * 
	 * @param func
	 *            Function to be called. The function shall return no value and
	 *            take no arguments.
	 * 
	 * @return A zero value is returned if the function was successfully
	 *         registered. If it failed, a non-zero value is returned.
	 */
	int atexit(/* void (*func)(void) */Pointer func);

	/**
	 * Fill block of memory
	 * 
	 * Sets the first num bytes of the block of memory pointed by ptr to the
	 * specified value (interpreted as an unsigned char).
	 * 
	 * @param ptr
	 *            Pointer to the block of memory to fill.
	 * 
	 * @param value
	 *            Value to be set. The value is passed as an int, but the
	 *            function fills the block of memory using the unsigned char
	 *            conversion of this value.
	 * 
	 * @param num
	 *            Number of bytes to be set to the value. size_t is an unsigned
	 *            integral type.
	 * 
	 * @return ptr is returned.
	 */
	Pointer memset(byte[] ptr, byte value, SIZE_T num);

	/**
	 * Locate substring
	 * 
	 * Returns a pointer to the first occurrence of str2 in str1, or a null
	 * pointer if str2 is not part of str1.
	 * 
	 * The matching process does not include the terminating null-characters,
	 * but it stops there.
	 * 
	 * @param str1
	 *            C string to be scanned.
	 * 
	 * @param str2
	 *            C string containing the sequence of characters to match.
	 * 
	 * @return A pointer to the first occurrence in str1 of the entire sequence
	 *         of characters specified in str2, or a null pointer if the
	 *         sequence is not present in str1.
	 */
	Pointer strstr(char[] str1, char[] str2);

	/**
	 * Get current time
	 * 
	 * Get the current calendar time as a value of type time_t.
	 * 
	 * The function returns this value, and if the argument is not a null
	 * pointer, it also sets this value to the object pointed by timer.
	 * 
	 * The value returned generally represents the number of seconds since 00:00
	 * hours, Jan 1, 1970 UTC (i.e., the current unix timestamp). Although
	 * libraries may use a different representation of time: Portable programs
	 * should not use the value returned by this function directly, but always
	 * rely on calls to other elements of the standard library to translate them
	 * to portable types (such as localtime, gmtime or difftime).
	 * 
	 * @param timer
	 *            Pointer to an object of type time_t, where the time value is
	 *            stored. Alternatively, this parameter can be a null pointer,
	 *            in which case the parameter is not used (the function still
	 *            returns a value of type time_t with the result).
	 * 
	 * @return The current calendar time as a time_t object.
	 * 
	 *         If the argument is not a null pointer, the return value is the
	 *         same as the one stored in the location pointed by argument timer.
	 * 
	 *         If the function could not retrieve the calendar time, it returns
	 *         a value of -1.
	 * 
	 *         time_t is an alias of a fundamental arithmetic type capable of
	 *         representing times.
	 */
	long time(LongByReference timer);

	/**
	 * Initialize random number generator
	 * 
	 * The pseudo-random number generator is initialized using the argument
	 * passed as seed.
	 * 
	 * For every different seed value used in a call to srand, the pseudo-random
	 * number generator can be expected to generate a different succession of
	 * results in the subsequent calls to rand.
	 * 
	 * Two different initializations with the same seed will generate the same
	 * succession of results in subsequent calls to rand.
	 * 
	 * If seed is set to 1, the generator is reinitialized to its initial value
	 * and produces the same values as before any call to rand or srand.
	 * 
	 * In order to generate random-like numbers, srand is usually initialized to
	 * some distinctive runtime value, like the value returned by function time
	 * (declared in header <ctime>). This is distinctive enough for most trivial
	 * randomization needs.
	 * 
	 * @param seed
	 *            An integer value to be used as seed by the pseudo-random
	 *            number generator algorithm.
	 */
	void srand(int seed);

	/**
	 * Sets the current application type.
	 * 
	 * @param at
	 *            A value that indicates the application type. The possible
	 *            values are:
	 * 
	 *            _UNKNOWN_APP Unknown application type.
	 * 
	 *            _CONSOLE_APP Console (command-line) application.
	 * 
	 *            _GUI_APP GUI (Windows) application.
	 */
	void __set_app_type(int at);

	/**
	 * Points to the _fmode global variable, which specifies the default file
	 * translation mode for file I/O operations.
	 * 
	 * @return Pointer to the _fmode global variable.
	 */
	IntByReference __p__fmode();

	/**
	 * Sets the file translation mode.
	 * 
	 * @param fd
	 *            File descriptor.
	 * 
	 * @param mode
	 *            New translation mode.
	 * 
	 * @return If successful, returns the previous translation mode. If invalid
	 *         parameters are passed to this function, the invalid-parameter
	 *         handler is invoked, as described in Parameter Validation. If
	 *         execution is allowed to continue, this function returns –1 and
	 *         sets errno to either EBADF, which indicates an invalid file
	 *         descriptor, or EINVAL, which indicates an invalid mode argument.
	 */
	int _setmode(int fd, int mode);

	/**
	 * Allocate memory block
	 * 
	 * Allocates a block of size bytes of memory, returning a pointer to the
	 * beginning of the block.
	 * 
	 * The content of the newly allocated block of memory is not initialized,
	 * remaining with indeterminate values.
	 * 
	 * If size is zero, the return value depends on the particular library
	 * implementation (it may or may not be a null pointer), but the returned
	 * pointer shall not be dereferenced.
	 * 
	 * @param size
	 *            Size of the memory block, in bytes. size_t is an unsigned
	 *            integral type.
	 * 
	 * @return On success, a pointer to the memory block allocated by the
	 *         function. The type of this pointer is always void*, which can be
	 *         cast to the desired type of data pointer in order to be
	 *         dereferenceable. If the function failed to allocate the requested
	 *         block of memory, a null pointer is returned.
	 */
	Pointer malloc(SIZE_T size);

	/**
	 * Performs cleanup operations and returns without terminating the process.
	 */
	void _cexit();

	/**
	 * Points to the _commode global variable, which specifies the default file
	 * commit mode for file I/O operations.
	 * 
	 * @return Pointer to the _commode global variable.
	 */
	IntByReference __p__commode();

	/**
	 * Gets and sets the floating-point control word. A more secure version of
	 * _controlfp is available; see _controlfp_s.
	 * 
	 * @param nnew
	 *            New control-word bit values.
	 * 
	 * @param mask
	 *            Mask for new control-word bits to set.
	 * 
	 * @return For _control87 and _controlfp, the bits in the value returned
	 *         indicate the floating-point control state. For a complete
	 *         definition of the bits that are returned by _control87, see
	 *         FLOAT.H.
	 */
	UINT _controlfp(UINT nnew, UINT mask);

	/**
	 * Converts an integer to a string. More secure versions of these functions
	 * are available
	 * 
	 * @param value
	 *            Number to be converted.
	 * 
	 * @param str
	 *            String result.
	 * 
	 * @param radix
	 *            Base of value; which must be in the range 2–36.
	 * 
	 * @return Each of these functions returns a pointer to str. There is no
	 *         error return.
	 */
	WString _itow(int value, WString str, int radix);

	/**
	 * Internal CRT function. Used by a framework to find the appropriate
	 * exception handler to process the current exception..
	 * 
	 * @param exception_record
	 *            [in] Information about the specific exception.
	 * 
	 * @param registration
	 *            [in] The record that indicates which scope table should be
	 *            used to find the exception handler.
	 * 
	 * @param context
	 *            [in] Reserved.
	 * 
	 * @param dispatcher
	 *            [in] Reserved.
	 * 
	 * @return If an exception should be dismissed, returns DISPOSITION_DISMISS.
	 *         If the exception should be passed up a level to the encapsulating
	 *         exception handlers, returns DISPOSITION_CONTINUE_SEARCH.
	 */
	int _except_handler3(EXCEPTION_RECORD exception_record,
	/* PEXCEPTION_REGISTRATION */Pointer registration, CONTEXT context,
	/* PEXCEPTION_REGISTRATION */Pointer dispatcher);

	/**
	 * Copy block of memory
	 * 
	 * Copies the values of num bytes from the location pointed to by source
	 * directly to the memory block pointed to by destination.
	 * 
	 * The underlying type of the objects pointed to by both the source and
	 * destination pointers are irrelevant for this function; The result is a
	 * binary copy of the data.
	 * 
	 * The function does not check for any terminating null character in source
	 * - it always copies exactly num bytes.
	 * 
	 * To avoid overflows, the size of the arrays pointed to by both the
	 * destination and source parameters, shall be at least num bytes, and
	 * should not overlap (for overlapping memory blocks, memmove is a safer
	 * approach).
	 * 
	 * @param destination
	 *            Pointer to the destination array where the content is to be
	 *            copied, type-casted to a pointer of type void*.
	 * 
	 * @param source
	 *            Pointer to the source of data to be copied, type-casted to a
	 *            pointer of type const void*.
	 * 
	 * @param num
	 *            Number of bytes to copy. size_t is an unsigned integral type.
	 *
	 * @return destination is returned.
	 */
	Pointer memcpy(Pointer destination, Pointer source, SIZE_T num);

	/**
	 * Converts a long integer to a string. More secure versions of these
	 * functions are available
	 * 
	 * @param value
	 *            Number to be converted.
	 * 
	 * @param str
	 *            String result.
	 * 
	 * @param radix
	 *            Base of value.
	 * 
	 * @return Each of these functions returns a pointer to str. There is no
	 *         error return.
	 */
	Pointer _ltoa(long value, char[] str, int radix);
}
