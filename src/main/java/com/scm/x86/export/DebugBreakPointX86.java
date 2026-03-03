package com.scm.x86.export;

import com.scm.all.export.ModuleOperationUtilsJNI;
import com.scm.all.pfunc.BreakpointCallBackX86;

public class DebugBreakPointX86 {
    //    64位：1|2|4|8                   32位：1|2|4
//    64位：0=执行, 1=写入, 3=读写      32位： 1读    3写

    /**
     * @param pid 进程ID
     * @param address 断点地址
     * @param len 断点长度  32位：1|2|4
     * @param dr7Type 断点类型 1读 3写
     * @return 线程ID
     */
    public static int HardwareBreakpoint(int pid,int address,int len,int dr7Type){
        return ModuleOperationUtilsJNI.RunHardwareBreakpointX86(pid,address,len,dr7Type);
    }

    /**
     * @param pid 进程ID
     * @param callBack BreakpointCallBackX64 调试器回调地址
     * @return 0:正常结束 1:异常结束
     */
    public static int CallBack_Data(int pid, BreakpointCallBackX86 callBack){
        return ModuleOperationUtilsJNI.CallBack_DataX86(pid,callBack);
    }

}
