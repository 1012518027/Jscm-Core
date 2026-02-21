package com.scm.x64.export;

import com.scm.all.export.ModuleOperationUtilsJNI;
import com.scm.all.pfunc.BreakpointCallBackX64;
/*
        ProcessAndThreadUtils.elevatePrivileges(ProcessAndThreadUtils.getCurrentProcess(),4);
                int pid = ProcessAndThreadUtils.processNameGetPid("Tutorial-i386.exe");
                System.out.println("11111进程ID：" + pid);

                int closeID = ModuleOperationUtilsJNI.RunHardwareBreakpointX86(pid,0x017478C0,4,3);
                System.out.println("22222线程ID：" + closeID);

                int close = 0;
                do {
                close = ModuleOperationUtilsJNI.CallBack_DataX86(pid, new BreakpointCallBackX86(){

public void callback(
        int ExceptionAddress,
        int ContextFlags,
        int Dr0,
        int Dr1,
        int Dr2,
        int Dr3,
        int Dr6,
        int Dr7,
        int SegGs,
        int SegFs,
        int SegEs,
        int SegDs,
        int Edi,
        int Esi,
        int Ebx,
        int Edx,
        int Ecx,
        int Eax,
        int Ebp,
        int Eip,
        int SegCs,              // MUST BE SANITIZED
        int EFlags,             // MUST BE SANITIZED
        int Esp,
        int SegSs
        ){
        System.out.println("ExceptionAddress: " + ExceptionAddress);
        System.out.println("ContextFlags: " + ContextFlags);
        System.out.println("Dr0: " + Dr0);
        System.out.println("Dr1: " + Dr1);
        System.out.println("Dr2: " + Dr2);
        System.out.println("Dr3: " + Dr3);
        System.out.println("Dr6: " + Dr6);
        System.out.println("Dr7: " + Dr7);
        System.out.println("SegGs: " + SegGs);
        System.out.println("SegFs: " + SegFs);
        System.out.println("SegEs: " + SegEs);
        System.out.println("SegDs: " + SegDs);
        System.out.println("Edi: " + Edi);
        System.out.println("Esi: " + Esi);
        System.out.println("Ebx: " + Ebx);
        System.out.println("Edx: " + Edx);
        System.out.println("Ecx: " + Ecx);
        System.out.println("Eax: " + Eax);
        System.out.println("Ebp: " + Ebp);
        System.out.println("Eip: " + Eip);
        System.out.println("SegCs: " + SegCs);
        System.out.println("EFlags: " + EFlags);
        System.out.println("Esp: " + Esp);
        System.out.println("SegSs: " + SegSs);
        return;
        }
        });

        }while (close==1);
 */
public class DebugBreakPointX64 {
    //    64位：1|2|4|8                   32位：1|2|4
//    64位：0=执行, 1=写入, 3=读写      32位： 1读    3写

    /**
     * @param pid 进程ID
     * @param address 断点地址
     * @param len 断点长度  64位：1|2|4|8
     * @param dr7Type 断点类型 0=执行, 1=写入, 3=读写
     * @return 线程ID
     */
    public static int HardwareBreakpoint(int pid,long address,int len,int dr7Type){
        return ModuleOperationUtilsJNI.RunHardwareBreakpointX64(pid,address,len,dr7Type);
    }

    /**
     * @param pid 进程ID
     * @param callBack BreakpointCallBackX64 调试器回调地址
     * @return 0:正常结束 1:异常结束
     */
    public static int CallBack_Data(int pid, BreakpointCallBackX64 callBack){
        return ModuleOperationUtilsJNI.CallBack_DataX64(pid,callBack);
    }
}
