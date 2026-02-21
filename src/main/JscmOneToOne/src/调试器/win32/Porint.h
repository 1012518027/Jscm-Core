#include <windows.h>

// 手动定义 NT_SUCCESS
#ifndef NT_SUCCESS
#define NT_SUCCESS(Status) (((NTSTATUS)(Status)) >= 0)
#endif

// 新增：保存已设置断点的信息（用于后续删除/切换）
typedef struct {
    int drIndex;          // 占用的DR寄存器索引（0-3）
    DWORD address;        // 断点地址
    int triggerType;      // 触发类型（1=写，3=读写）
    int size;             // 长度（1/2/4）
    bool isActive;        // 是否激活
} BreakpointInfo;

// 全局变量：存储当前断点信息（单断点，如需多断点可改为数组）
static BreakpointInfo g_CurrentBreakpoint = {-1, 0, 0, 0, false};

// 设置硬件断点（支持读/写/读写触发）
static bool SetHardwareBreakpoint(HANDLE hThread, DWORD address, int triggerType, int size) {
     // 先检查是否已有激活的断点，避免重复设置
    if (g_CurrentBreakpoint.isActive) {
        return false;
    }

 // 1. 校验句柄有效性
    if (hThread == NULL || hThread == INVALID_HANDLE_VALUE) {
        return false;
    }
    // 2. 校验地址有效性（x64地址非0且对齐建议）
    if (address == 0) {
        return false;
    }
    
    CONTEXT context = { 0 };
    context.ContextFlags = CONTEXT_DEBUG_REGISTERS | CONTEXT_i386;

    if (!GetThreadContext(hThread, &context)) {
        return false;
    }

    // 查找空闲调试寄存器 DR0-DR3
    int regIndex = -1;
    for (int i = 0; i < 4; ++i) {
        if (!(context.Dr7 & (1ULL << (i * 2)))) {
            regIndex = i;
            break;
        }
    }
    if (regIndex == -1) {
        return false;
    }

    // 设置断点地址
    switch (regIndex) {
    case 0: context.Dr0 = address; break;
    case 1: context.Dr1 = address; break;
    case 2: context.Dr2 = address; break;
    case 3: context.Dr3 = address; break;
    }

    // 启用该断点（局部启用）
    context.Dr7 |= (1ULL << (regIndex * 2));    // 局部位 L0-L3
    context.Dr7 |= (1ULL << (regIndex * 2 + 1));// 全局位 G0-G3

    // 设置触发条件
    // triggerType: 1 = 写入, 3 = 读或写（用于“读取”和“读写”）
    int rwBits = (triggerType == 1) ? 1 : 3;  // 1=写, 3=读写
    context.Dr7 &= ~(0x3ULL << (16 + regIndex * 4));
    context.Dr7 |= ((ULONGLONG)rwBits << (16 + regIndex * 4));

    // 设置长度
    int lenCode = 0;
    if (size == 1) lenCode = 0;
    else if (size == 2) lenCode = 1;
    else if (size == 4) lenCode = 3;
    else {
        return false;
    }
    context.Dr7 &= ~(0x3ULL << (18 + regIndex * 4));
    context.Dr7 |= ((ULONGLONG)lenCode << (18 + regIndex * 4));

    if (!SetThreadContext(hThread, &context)) {
        return false;
    }

 // 新增：保存断点信息到全局变量
    g_CurrentBreakpoint.drIndex = regIndex;
    g_CurrentBreakpoint.address = address;
    g_CurrentBreakpoint.triggerType = triggerType;
    g_CurrentBreakpoint.size = size;
    g_CurrentBreakpoint.isActive = true;

    return true;
}
