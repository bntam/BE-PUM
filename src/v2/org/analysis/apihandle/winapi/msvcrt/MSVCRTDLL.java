package v2.org.analysis.apihandle.winapi.msvcrt;

import v2.org.analysis.apihandle.winapi.structures.PointerByRefByRef;
import v2.org.analysis.apihandle.winapi.structures.Internal._startupinfo;
import v2.org.analysis.apihandle.winapi.structures.WinBase.STARTUPINFO;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.SIZE_T;
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
}
