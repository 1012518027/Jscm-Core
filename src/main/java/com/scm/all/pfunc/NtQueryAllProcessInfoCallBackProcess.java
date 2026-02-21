package com.scm.all.pfunc;

/**
// * 全局回调
 */
public class NtQueryAllProcessInfoCallBackProcess {
    /**
     * @param ProcessName 进程名
     * @param NumberOfThreads 进程中的线程数
     * @param BasePriority 进程的基优先级，这是在关联进程中创建的线程的起始优先级
     * @param UniqueProcessId 进程唯一的ID
     * @param HandleCount 当前进程占用的句柄数
     * @param SessionId 会话ID
     * @param PeakVirtualSize  进程使用的虚拟内存的峰值大小（以字节为单位）
     * @param VirtualSize 虚拟内存的当前大小（以字节为单位）
     * @param QuotaPagedPoolUsage 分页池使用情况的进程收费的当前配额
     * @param PagefileUsage 进程正在使用的页面文件存储的字节数
     * @param PeakPagefileUsage 进程使用的页面文件存储的最大字节数
     * @param PrivatePageCount 使用此过程分配的内存页数
     */
    public void callBack(String ProcessName,
                         int NumberOfThreads,
                         int BasePriority,
                         int UniqueProcessId,
                         int HandleCount,
                         int SessionId,
                         long PeakVirtualSize,
                         long VirtualSize,
                         long QuotaPagedPoolUsage,
                         long PagefileUsage,
                         long PeakPagefileUsage,
                         long PrivatePageCount){};


}
