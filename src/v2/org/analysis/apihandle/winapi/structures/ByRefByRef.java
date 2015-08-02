package v2.org.analysis.apihandle.winapi.structures;

import com.sun.jna.Memory;
import com.sun.jna.PointerType;

/** Provides generic "pointer to type" functionality, often used in C
 * code to return values to the caller in addition to a function result.
 * <p>
 * Derived classes should define <code>setValue(&lt;T&gt;)</code>
 * and <code>&lt;T&gt; getValue()</code> methods which write to/read from
 * memory.
 * <p>This class derives from PointerType instead of Memory in order to
 * restrict the API to only <code>getValue/setValue</code>.
 * <p>NOTE: this class would ideally be replaced by a generic.
 */
public abstract class ByRefByRef extends PointerType {
    
    protected ByRefByRef(int dataSize) {
    	Memory memory = new Memory(dataSize);
    	memory.setPointer(0, new Memory(dataSize));
        setPointer(memory);
    }
}

