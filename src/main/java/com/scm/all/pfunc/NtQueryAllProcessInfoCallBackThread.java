package com.scm.all.pfunc;

public class NtQueryAllProcessInfoCallBackThread {

    /**
     * 线程信息操作类
     * @param ProcessName 进程名
     * @param UniqueProcessId 进程ID
     * @param StartAddressX86 线程入口地址 X86
     * @param StartAddressX64 线程入口地址 X64 SDK 64在用
     * @param UniqueProcess 线程内的进程ID
     * @param UniqueThread 线程ID
     * @param Priority 动态线程优先级
     * @param BasePriority 基线程优先级
     * @param ThreadStates 当前线程状态
     * @param WaitReason 线程正在等待的原因
     */
    public void callBack(String ProcessName,int UniqueProcessId,int StartAddressX86,long StartAddressX64,int UniqueProcess,int UniqueThread,int Priority,int BasePriority,int ThreadStates,int WaitReason){};
}
