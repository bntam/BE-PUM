package v2.org.analysis.environment.processthread;

import v2.org.analysis.path.BPState;
import v2.org.analysis.value.LongValue;

import java.util.LinkedList;

public class LDRData {

    public static class ListEntry {

        private long list_base_address;
        private long F_link;
        private long B_link;

    }

    private static final long LDR_Base_Address = 0x77D80200;
    private static final long Length = 0x30;
    private static long Initialized;
    private static long SsHandle;
    private static ListEntry InLoadOrderModuleList;

    static {
        InLoadOrderModuleList = new ListEntry();
    }

    static void updateLDR(BPState curState){
        // Update later
        // Update LDR_0
        curState.getEnvironement().getMemory().setDoubleWordMemoryValue(LDR_Base_Address, new LongValue(Length));
        // Update LDR_4
        Initialized = 0x1;
        curState.getEnvironement().getMemory().setDoubleWordMemoryValue(LDR_Base_Address + 4, new LongValue(Initialized));
        // Update LDR_8
        SsHandle = 0x0;
        curState.getEnvironement().getMemory().setDoubleWordMemoryValue(LDR_Base_Address + 8, new LongValue(SsHandle));
        // Update LDR_C
        // TODO: Check this value is
        InLoadOrderModuleList.list_base_address = 0x4A3BE8;
        curState.getEnvironement().getMemory().setDoubleWordMemoryValue(LDR_Base_Address + 0xC, new LongValue(InLoadOrderModuleList.list_base_address));
    }

    public static long getLDR_Base_Address(){
        return LDR_Base_Address;
    }

}
