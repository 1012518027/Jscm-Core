#include <windows.h>
#include <cstdint>  // 用于uintptr_t类型
// 手动定义 NT_SUCCESS（避免依赖 winternl.h）
#ifndef NT_SUCCESS
#define NT_SUCCESS(Status) (((NTSTATUS)(Status)) >= 0)
#endif

// 新增：保存已设置断点的信息（用于后续删除/切换）
typedef struct {
    int drIndex;          // 占用的DR寄存器索引（0-3）
    uintptr_t address;    // 断点地址（x64用uintptr_t适配64位地址）
    int triggerType;      // 触发类型（1=写，3=读写）
    int size;             // 长度（1/2/4/8字节，适配x64）
    bool isActive;        // 是否激活
} BreakpointInfo;

// 全局变量：存储当前断点信息（单断点，如需多断点可改为数组）
static BreakpointInfo g_CurrentBreakpoint = {-1, 0, 0, 0, false};

// 设置硬件断点
static bool SetHardwareBreakpoint(HANDLE hThread, uintptr_t address, int triggerType, int size) {
     // 先检查是否已有激活的断点，避免重复设置
    if (g_CurrentBreakpoint.isActive) {
        return false;
    }

    CONTEXT context = { 0 };
    context.ContextFlags = CONTEXT_DEBUG_REGISTERS | CONTEXT_AMD64; // 显式指定x64

    if (!GetThreadContext(hThread, &context)) {
        return false;
    }

    // 查找空闲调试寄存器 (DR0-DR3)
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

    // 启用断点
    context.Dr7 |= (1ULL << (regIndex * 2));
    context.Dr7 |= (1ULL << (regIndex * 2 + 1));// 全局位 G0-G3

    // 设置触发条件（读/写/读写）
    context.Dr7 &= ~(0x3ULL << (16 + regIndex * 4)); // 清除旧条件
    context.Dr7 |= ((ULONGLONG)triggerType  << (16 + regIndex * 4));

    // 设置长度（0=1字节, 1=2字节, 3=4字节, 2=8字节 — 注意 x64 下 8 字节用 2）
    int lenCode = 0;
    if (size == 1) lenCode = 0;
    else if (size == 2) lenCode = 1;
    else if (size == 4) lenCode = 3;
    else if (size == 8) lenCode = 2; // x64: 8字节 = 10b = 2
    else {
        return false;
    }
    context.Dr7 &= ~(0x3ULL << (18 + regIndex * 4)); // 清除旧长度
    context.Dr7 |= ((ULONGLONG)lenCode << (18 + regIndex * 4));

    if (!SetThreadContext(hThread, &context)) {
        return false;
    }

  // 保存断点信息到全局变量（修复原代码triggerType变量名错误）
    g_CurrentBreakpoint.drIndex = regIndex;
    g_CurrentBreakpoint.address = address;
    g_CurrentBreakpoint.triggerType = triggerType;
    g_CurrentBreakpoint.size = size;
    g_CurrentBreakpoint.isActive = true;

    return true;
}
