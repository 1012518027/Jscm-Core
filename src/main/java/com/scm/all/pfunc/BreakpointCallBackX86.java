package com.scm.all.pfunc;

/**
 * X86调试器回调
 */
public class BreakpointCallBackX86 {
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
        return;
    }
}
