#include <windows.h>
#include <tlhelp32.h>
#include <iostream>
#include <iomanip>
#include <string>
#include <array>
#include <functional>
#include <vector>
#include <sstream>
#include <regex>
#include <cctype>
#include <stdexcept>
#include <sstream>
#include <locale>
#include <codecvt>

#pragma comment(lib, "kernel32.lib")
#pragma comment(lib, "ntdll.lib")

// 命名空间隔离，避免符号冲突
namespace Debugger {

// 架构类型枚举（仅保留64位）
    enum class ArchType {
        X64     // 64位
    };

// 寄存器状态结构体（仅64位）
    struct RegisterState {
        // 64位基础寄存器: RAX, RBX, RCX, RDX, RSI, RDI, RBP, RSP, RIP
        std::array<DWORD64, 9> baseRegs64{};
        // 64位扩展寄存器: R8-R15
        std::array<DWORD64, 8> extRegs64{};
        // 架构类型（固定为X64）
        ArchType arch{ ArchType::X64 };
        // 指针基址（64位RBX）
        DWORD64 pointerBase{ 0 };
        // 指令指针偏移（RIP - 目标地址）
        DWORD64 ipOffset{ 0 };
        // 断点目标地址
        DWORD64 targetAddress{ 0 };
    };

// 断点回调函数类型
    using BreakpointCallback = std::function<void(const RegisterState&)>;

// 寄存器枚举（仅64位）
    enum class Register {
        RAX, RBX, RCX, RDX, RSI, RDI, RBP, RSP, RIP,
        R8, R9, R10, R11, R12, R13, R14, R15,
        INVALID
    };

// 硬件断点类型枚举
    enum class BreakpointType {
        ReadWrite,  // 读/写触发
        Execute,    // 执行触发
        Write       // 仅写触发
    };

// 硬件断点调试器核心类（仅支持64位）
    class HardwareBreakpointDebugger {
    public:
        // 构造函数
        HardwareBreakpointDebugger() = default;

        // 析构函数（确保资源释放）
        ~HardwareBreakpointDebugger() {
            try {
                Detach();
            }
            catch (...) { /* 忽略析构中的异常 */ }
        }

        // 禁止拷贝（避免资源冲突）
        HardwareBreakpointDebugger(const HardwareBreakpointDebugger&) = delete;
        HardwareBreakpointDebugger& operator=(const HardwareBreakpointDebugger&) = delete;

        // 移动构造
        HardwareBreakpointDebugger(HardwareBreakpointDebugger&& other) noexcept {
            *this = std::move(other);
        }

        // 移动赋值
        HardwareBreakpointDebugger& operator=(HardwareBreakpointDebugger&& other) noexcept {
            if (this != &other) {
                m_pid = other.m_pid;
                m_hProcess = other.m_hProcess;
                m_targetAddress = other.m_targetAddress;
                m_callback = std::move(other.m_callback);

                // 置空源对象
                other.m_pid = 0;
                other.m_hProcess = nullptr;
                other.m_targetAddress = 0;
            }
            return *this;
        }

        /**
         * @brief 启动硬件断点调试（字符串地址版本，支持十进制、十六进制、寄存器相对寻址）
         * @param pid 目标进程ID
         * @param addrStr 目标地址字符串
         * @param type 断点类型（默认读/写触发）
         * @param byteLen 监控字节长度（1/2/4/8字节，默认1字节）
         * @return 成功返回true，失败返回false
         */
        bool Start(DWORD pid, const std::string& addrStr,
                   BreakpointType type = BreakpointType::ReadWrite,
                   size_t byteLen = 1) {
            try {
                // 1. 提升调试权限
                if (!EnableDebugPrivilege()) {
                    throw std::runtime_error("Failed to enable debug privilege (run as administrator)");
                }

                // 2. 附加进程并验证64位架构
                if (!Attach(pid)) {
                    throw std::runtime_error("Failed to attach to process or process is not x64");
                }

                // 3. 解析目标地址
                if (!ParseAddress(addrStr, m_targetAddress)) {
                    throw std::runtime_error("Invalid address format: " + addrStr);
                }

                // 4. 设置硬件断点
                if (!SetHardwareBreakpoint(m_targetAddress, type, byteLen)) {
                    throw std::runtime_error("Failed to set hardware breakpoint");
                }

                // 5. 启动调试循环
                RunDebugLoop();

                return true;
            }
            catch (const std::exception& e) {
                std::cerr << "[ERROR] " << e.what() << std::endl;
                Detach();
                return false;
            }
        }

        /**
         * @brief 启动硬件断点调试（整数地址版本，直接传入十进制地址）
         * @param pid 目标进程ID
         * @param addr 目标地址（十进制整数）
         * @param type 断点类型（默认读/写触发）
         * @param byteLen 监控字节长度（1/2/4/8字节，默认1字节）
         * @return 成功返回true，失败返回false
         */
        bool Start(DWORD pid, DWORD64 addr,
                   BreakpointType type = BreakpointType::ReadWrite,
                   size_t byteLen = 1) {
            try {
                // 1. 提升调试权限
                if (!EnableDebugPrivilege()) {
                    throw std::runtime_error("Failed to enable debug privilege (run as administrator)");
                }

                // 2. 附加进程并验证64位架构
                if (!Attach(pid)) {
                    throw std::runtime_error("Failed to attach to process or process is not x64");
                }

                // 3. 直接使用整数地址
                m_targetAddress = addr;
                std::cout << "[+] Using target address: 0x" << std::hex << m_targetAddress << " (decimal: " << std::dec << m_targetAddress << ")" << std::endl;

                // 4. 设置硬件断点
                if (!SetHardwareBreakpoint(m_targetAddress, type, byteLen)) {
                    throw std::runtime_error("Failed to set hardware breakpoint");
                }

                // 5. 启动调试循环
                RunDebugLoop();

                return true;
            }
            catch (const std::exception& e) {
                std::cerr << "[ERROR] " << e.what() << std::endl;
                Detach();
                return false;
            }
        }

        /**
         * @brief 设置断点回调函数
         * @param cb 回调函数
         */
        void SetCallback(BreakpointCallback cb) {
            m_callback = std::move(cb);
        }

        /**
         * @brief 手动脱离调试进程
         */
        void Detach() {
            if (m_hProcess) {
                // 清除所有断点
                ClearAllBreakpoints();
                // 停止调试
                DebugActiveProcessStop(m_pid);
                // 关闭进程句柄
                CloseHandle(m_hProcess);
                m_hProcess = nullptr;
            }
            m_pid = 0;
            m_targetAddress = 0;
        }

        /**
         * @brief 获取当前调试的进程ID
         * @return 进程ID
         */
        DWORD GetPid() const { return m_pid; }

        /**
         * @brief 获取目标架构类型（固定返回X64）
         * @return 架构类型
         */
        ArchType GetArchitecture() const { return ArchType::X64; }

        /**
             * @brief 格式化64位寄存器状态到字符串流（辅助函数）
             * @param state 寄存器状态
             * @param oss 输出字符串流
             */
        static void FormatRegisterState(const RegisterState& state, std::wostringstream& oss) {
            oss << std::hex << std::uppercase << std::setfill(L'0');
            // 打印地址信息
            oss << L"\n 断点命中 \n";
            oss << L"[+] 目标地址: 0x" << std::setw(16) << state.targetAddress << L"\n";
            oss << L"[+] RIP 偏移量：0x" << std::setw(8) << state.ipOffset << L"\n";
            oss << L"[+] RBX 基址偏移量：0x" << std::setw(8) << (state.pointerBase - state.targetAddress) << L"\n";

            // 打印64位寄存器
            oss << L"\n 64 位寄存器 \n";
            oss << L"RAX = 0x" << std::setw(16) << state.baseRegs64[0] << L"  RBX = 0x" << std::setw(16) << state.baseRegs64[1] << L"\n";
            oss << L"RCX = 0x" << std::setw(16) << state.baseRegs64[2] << L"  RDX = 0x" << std::setw(16) << state.baseRegs64[3] << L"\n";
            oss << L"RSI = 0x" << std::setw(16) << state.baseRegs64[4] << L"  RDI = 0x" << std::setw(16) << state.baseRegs64[5] << L"\n";
            oss << L"RBP = 0x" << std::setw(16) << state.baseRegs64[6] << L"  RSP = 0x" << std::setw(16) << state.baseRegs64[7] << L"\n";
            oss << L"RIP = 0x" << std::setw(16) << state.baseRegs64[8] << L"\n";
            oss << L"\n 段寄存器 \n";
            oss << L"\nR8  = 0x" << std::setw(16) << state.extRegs64[0] << L"  R9  = 0x" << std::setw(16) << state.extRegs64[1] << L"\n";
            oss << L"R10 = 0x" << std::setw(16) << state.extRegs64[2] << L"  R11 = 0x" << std::setw(16) << state.extRegs64[3] << L"\n";
            oss << L"R12 = 0x" << std::setw(16) << state.extRegs64[4] << L"  R13 = 0x" << std::setw(16) << state.extRegs64[5] << L"\n";
            oss << L"R14 = 0x" << std::setw(16) << state.extRegs64[6] << L"  R15 = 0x" << std::setw(16) << state.extRegs64[7] << L"\n";

            oss << std::dec << std::nouppercase << std::setfill(L' ');
        }

    private:
        // 成员变量
        DWORD m_pid{ 0 };                      // 目标进程ID
        HANDLE m_hProcess{ nullptr };          // 进程句柄
        DWORD64 m_targetAddress{ 0 };          // 断点目标地址
        BreakpointCallback m_callback{ nullptr };// 回调函数

        // 函数指针类型定义
        using pSetProcessDebugPort = BOOL(WINAPI*)(HANDLE hProcess, HANDLE hDebugPort);
        using pNtSetInformationProcess = NTSTATUS(WINAPI*)(HANDLE ProcessHandle, DWORD ProcessInformationClass,
                                                           PVOID ProcessInformation, DWORD ProcessInformationLength);

        /**
         * @brief 提升SeDebugPrivilege权限
         * @return 成功返回true，失败返回false
         */
        bool EnableDebugPrivilege() {
            HANDLE hToken = nullptr;
            bool result = false;

            try {
                if (!OpenProcessToken(GetCurrentProcess(), TOKEN_ADJUST_PRIVILEGES | TOKEN_QUERY, &hToken)) {
                    return false;
                }

                TOKEN_PRIVILEGES tp{};
                LUID luid{};
                if (!LookupPrivilegeValue(nullptr, SE_DEBUG_NAME, &luid)) {
                    return false;
                }

                tp.PrivilegeCount = 1;
                tp.Privileges[0].Luid = luid;
                tp.Privileges[0].Attributes = SE_PRIVILEGE_ENABLED;

                AdjustTokenPrivileges(hToken, FALSE, &tp, sizeof(tp), nullptr, nullptr);
                result = (GetLastError() == ERROR_SUCCESS);
            }
            catch (...) {
                result = false;
            }

            // 确保句柄关闭
            if (hToken) {
                CloseHandle(hToken);
            }

            return result;
        }

        /**
         * @brief 附加到目标进程并验证64位架构
         * @param pid 目标进程ID
         * @return 成功返回true，失败返回false
         */
        bool Attach(DWORD pid) {
            // 清理调试环境
            CleanupProcessDebugEnv(pid);

            // 验证进程是否为64位
            if (!IsProcessX64(pid)) {
                std::cerr << "[ERROR] Process is not x64 architecture" << std::endl;
                return false;
            }

            // 附加调试
            if (!DebugActiveProcess(pid)) {
                return false;
            }

            // 打开进程句柄（64位进程所需权限）
            m_hProcess = OpenProcess(PROCESS_QUERY_INFORMATION | PROCESS_VM_READ | PROCESS_SET_INFORMATION |
                                     PROCESS_SET_QUOTA | PROCESS_VM_OPERATION, FALSE, pid);
            if (!m_hProcess) {
                DebugActiveProcessStop(pid);
                return false;
            }

            m_pid = pid;
            std::cout << "[+] Attached to PID: " << pid << " (x64)" << std::endl;
            return true;
        }

        /**
         * @brief 验证进程是否为64位
         * @param pid 进程ID
         * @return 是返回true，否返回false
         */
        bool IsProcessX64(DWORD pid) {
            HANDLE hProcess = OpenProcess(PROCESS_QUERY_INFORMATION, FALSE, pid);
            if (!hProcess) return false;

            BOOL isWow64 = FALSE;
            bool isX64 = false;

            if (IsWow64Process(hProcess, &isWow64)) {
                // 64位系统上，非Wow64进程即为64位进程
                SYSTEM_INFO si{};
                GetNativeSystemInfo(&si);
                isX64 = (si.wProcessorArchitecture == PROCESSOR_ARCHITECTURE_AMD64 && !isWow64);
            }

            CloseHandle(hProcess);
            return isX64;
        }

        /**
         * @brief 清理目标进程的调试环境
         * @param pid 目标进程ID
         */
        void CleanupProcessDebugEnv(DWORD pid) {
            HANDLE hProcess = OpenProcess(PROCESS_SET_INFORMATION | PROCESS_QUERY_INFORMATION, FALSE, pid);
            if (!hProcess) return;

            try {
                // 清除调试端口
                HMODULE hKernel32 = GetModuleHandleA("kernel32.dll");
                if (hKernel32) {
                    auto pSetDebugPort = (pSetProcessDebugPort)GetProcAddress(hKernel32, "SetProcessDebugPort");
                    if (pSetDebugPort) {
                        pSetDebugPort(hProcess, nullptr);
                    }
                }

                // 清除调试标志
                HMODULE hNtdll = GetModuleHandleA("ntdll.dll");
                if (hNtdll) {
                    auto pNtSetInfo = (pNtSetInformationProcess)GetProcAddress(hNtdll, "NtSetInformationProcess");
                    if (pNtSetInfo) {
                        DWORD debugFlags = 0;
                        pNtSetInfo(hProcess, 0x1F, &debugFlags, sizeof(debugFlags));
                    }
                }
            }
            catch (...) {
                // 忽略异常
            }

            CloseHandle(hProcess);
        }

        /**
         * @brief 检查字符串是否为合法的十六进制地址
         * @param str 输入字符串
         * @return 是返回true，否返回false
         */
        bool IsHexAddress(const std::string& str) {
            if (str.size() < 3 || (str.substr(0, 2) != "0x" && str.substr(0, 2) != "0X")) {
                return false;
            }
            for (size_t i = 2; i < str.size(); ++i) {
                if (!isxdigit(str[i])) return false;
            }
            return true;
        }

        /**
         * @brief 检查字符串是否为合法的十进制地址
         * @param str 输入字符串
         * @return 是返回true，否返回false
         */
        bool IsDecAddress(const std::string& str) {
            if (str.empty()) return false;
            for (char c : str) {
                if (!isdigit(c)) return false;
            }
            return true;
        }

        /**
         * @brief 解析地址字符串（支持十进制、十六进制、64位寄存器相对寻址）
         * @param addrStr 地址字符串
         * @param outAddress 输出解析后的地址
         * @return 成功返回true，失败返回false
         */
        bool ParseAddress(const std::string& addrStr, DWORD64& outAddress) {
            // 1. 十进制地址（纯数字）
            if (IsDecAddress(addrStr)) {
                outAddress = std::stoull(addrStr, nullptr, 10);
                std::cout << "[+] Resolved decimal address " << addrStr << " to 0x" << std::hex << outAddress << std::dec << std::endl;
                return true;
            }

            // 2. 十六进制地址（0x开头）
            if (IsHexAddress(addrStr)) {
                outAddress = std::stoull(addrStr, nullptr, 16);
                return true;
            }

            // 3. 64位寄存器相对寻址（如 RBX+100、RBP-0x8）
            std::regex regRegex(R"(([a-zA-Z0-9]+)\s*([\+\-])\s*(0x[a-fA-F0-9]+|\d+))");
            std::smatch match;
            if (std::regex_match(addrStr, match, regRegex)) {
                std::string regName = match[1].str();
                char op = match[2].str()[0];
                // 支持偏移为十进制或十六进制
                std::string offsetStr = match[3].str();
                DWORD64 offset = IsHexAddress(offsetStr) ? std::stoull(offsetStr, nullptr, 16) : std::stoull(offsetStr, nullptr, 10);

                DWORD64 regValue = 0;
                if (!GetRegisterValue(regName, regValue)) {
                    return false;
                }

                outAddress = (op == '+') ? regValue + offset : regValue - offset;
                std::cout << "[+] Resolved " << addrStr << " to 0x" << std::hex << outAddress << std::dec << std::endl;
                return true;
            }

            return false;
        }

        /**
         * @brief 获取64位寄存器的值
         * @param regName 寄存器名
         * @param outValue 输出寄存器值
         * @return 成功返回true，失败返回false
         */
        bool GetRegisterValue(const std::string& regName, DWORD64& outValue) {
            auto threads = GetThreadIds();
            if (threads.empty()) return false;

            HANDLE hThread = OpenThread(THREAD_GET_CONTEXT | THREAD_SUSPEND_RESUME, FALSE, threads[0]);
            if (!hThread) return false;

            bool result = false;
            try {
                SuspendThread(hThread);

                CONTEXT ctx{};
                ctx.ContextFlags = CONTEXT_FULL | CONTEXT_AMD64;
                if (GetThreadContext(hThread, &ctx)) {
                    Register reg = GetRegisterEnum(regName);
                    outValue = GetRegisterValueFromContext(ctx, reg);
                    result = true;
                }
            }
            catch (...) {
                result = false;
            }

            // 确保线程恢复和句柄关闭
            ResumeThread(hThread);
            CloseHandle(hThread);

            return result;
        }

        /**
         * @brief 将64位寄存器名转换为枚举
         * @param regName 寄存器名
         * @return 寄存器枚举
         */
        Register GetRegisterEnum(const std::string& regName) {
            std::string upperReg;
            for (char c : regName) upperReg += toupper(c);

            if (upperReg == "RAX") return Register::RAX;
            if (upperReg == "RBX") return Register::RBX;
            if (upperReg == "RCX") return Register::RCX;
            if (upperReg == "RDX") return Register::RDX;
            if (upperReg == "RSI") return Register::RSI;
            if (upperReg == "RDI") return Register::RDI;
            if (upperReg == "RBP") return Register::RBP;
            if (upperReg == "RSP") return Register::RSP;
            if (upperReg == "RIP") return Register::RIP;

            if (upperReg == "R8") return Register::R8;
            if (upperReg == "R9") return Register::R9;
            if (upperReg == "R10") return Register::R10;
            if (upperReg == "R11") return Register::R11;
            if (upperReg == "R12") return Register::R12;
            if (upperReg == "R13") return Register::R13;
            if (upperReg == "R14") return Register::R14;
            if (upperReg == "R15") return Register::R15;

            return Register::INVALID;
        }

        /**
         * @brief 从64位上下文获取寄存器值
         * @param ctx 线程上下文
         * @param reg 寄存器枚举
         * @return 寄存器值
         */
        DWORD64 GetRegisterValueFromContext(const CONTEXT& ctx, Register reg) {
            switch (reg) {
                // 64位基础寄存器
                case Register::RAX: return ctx.Rax;
                case Register::RBX: return ctx.Rbx;
                case Register::RCX: return ctx.Rcx;
                case Register::RDX: return ctx.Rdx;
                case Register::RSI: return ctx.Rsi;
                case Register::RDI: return ctx.Rdi;
                case Register::RBP: return ctx.Rbp;
                case Register::RSP: return ctx.Rsp;
                case Register::RIP: return ctx.Rip;

                    // 64位扩展寄存器
                case Register::R8: return ctx.R8;
                case Register::R9: return ctx.R9;
                case Register::R10: return ctx.R10;
                case Register::R11: return ctx.R11;
                case Register::R12: return ctx.R12;
                case Register::R13: return ctx.R13;
                case Register::R14: return ctx.R14;
                case Register::R15: return ctx.R15;

                default: return 0;
            }
        }

        /**
         * @brief 获取进程的所有线程ID
         * @return 线程ID列表
         */
        std::vector<DWORD> GetThreadIds() const {
            std::vector<DWORD> threads;
            HANDLE hSnap = CreateToolhelp32Snapshot(TH32CS_SNAPTHREAD, 0);
            if (hSnap == INVALID_HANDLE_VALUE) return threads;

            try {
                THREADENTRY32 te{};
                te.dwSize = sizeof(te);
                if (Thread32First(hSnap, &te)) {
                    do {
                        if (te.th32OwnerProcessID == m_pid) {
                            threads.push_back(te.th32ThreadID);
                        }
                    } while (Thread32Next(hSnap, &te));
                }
            }
            catch (...) {
                // 忽略异常
            }

            CloseHandle(hSnap);
            return threads;
        }

        /**
         * @brief 为单个64位线程设置硬件断点
         * @param hThread 线程句柄
         * @param addr 断点地址
         * @param type 断点类型
         * @param byteLen 监控字节长度
         * @return 成功返回true，失败返回false
         */
        bool SetHardwareBreakpoint(HANDLE hThread, DWORD64 addr, BreakpointType type, size_t byteLen) const {
            CONTEXT ctx{};
            ctx.ContextFlags = CONTEXT_DEBUG_REGISTERS | CONTEXT_AMD64;

            SuspendThread(hThread);
            bool result = false;

            try {
                if (GetThreadContext(hThread, &ctx)) {
                    // 配置DR0寄存器（断点地址）
                    ctx.Dr0 = addr;

                    // 清除DR7的原有配置
                    ctx.Dr7 &= ~(1ULL << 0);          // 禁用DR0
                    ctx.Dr7 &= ~(0xFULL << 16);       // 清空RW和LEN位

                    // 设置断点类型（RW位）
                    switch (type) {
                        case BreakpointType::Execute: ctx.Dr7 |= (0ULL << 16); break;  // 00: 执行触发
                        case BreakpointType::Write:    ctx.Dr7 |= (1ULL << 16); break;  // 01: 写触发
                        case BreakpointType::ReadWrite: ctx.Dr7 |= (3ULL << 16); break; // 11: 读/写触发
                    }

                    // 设置监控字节长度（LEN位）
                    switch (byteLen) {
                        case 1: ctx.Dr7 |= (0ULL << 18); break;  // 00: 1字节
                        case 2: ctx.Dr7 |= (1ULL << 18); break;  // 01: 2字节
                        case 4: ctx.Dr7 |= (3ULL << 18); break;  // 11: 4字节
                        case 8: ctx.Dr7 |= (2ULL << 18); break;  // 10: 8字节（64位支持）
                        default: ctx.Dr7 |= (0ULL << 18); break;
                    }

                    // 启用DR0
                    ctx.Dr7 |= (1ULL << 0);

                    // 应用上下文
                    result = SetThreadContext(hThread, &ctx);
                }
            }
            catch (...) {
                result = false;
            }

            ResumeThread(hThread);
            return result;
        }

        /**
         * @brief 为所有64位线程设置硬件断点
         * @param addr 断点地址
         * @param type 断点类型
         * @param byteLen 监控字节长度
         * @return 成功返回true，失败返回false
         */
        bool SetHardwareBreakpoint(DWORD64 addr, BreakpointType type, size_t byteLen) {
            auto threads = GetThreadIds();
            if (threads.empty()) return false;

            std::cout << "[+] Setting breakpoint at 0x" << std::hex << addr << std::dec
                      << " on " << threads.size() << " threads" << std::endl;

            int successCount = 0;
            for (DWORD tid : threads) {
                HANDLE hThread = OpenThread(THREAD_GET_CONTEXT | THREAD_SET_CONTEXT | THREAD_SUSPEND_RESUME,
                                            FALSE, tid);
                if (!hThread) continue;

                if (SetHardwareBreakpoint(hThread, addr, type, byteLen)) {
                    successCount++;
                }

                CloseHandle(hThread);
            }

            std::cout << "[+] Successfully set breakpoint on " << successCount << "/" << threads.size() << " threads" << std::endl;
            return successCount > 0;
        }

        /**
         * @brief 清除所有64位线程的硬件断点
         */
        void ClearAllBreakpoints() {
            auto threads = GetThreadIds();
            for (DWORD tid : threads) {
                HANDLE hThread = OpenThread(THREAD_GET_CONTEXT | THREAD_SET_CONTEXT | THREAD_SUSPEND_RESUME,
                                            FALSE, tid);
                if (!hThread) continue;

                CONTEXT ctx{};
                ctx.ContextFlags = CONTEXT_DEBUG_REGISTERS | CONTEXT_AMD64;

                SuspendThread(hThread);
                if (GetThreadContext(hThread, &ctx)) {
                    ctx.Dr0 = 0;
                    ctx.Dr7 &= ~(1ULL << 0);
                    ctx.Dr7 &= ~(0xFULL << 16);
                    SetThreadContext(hThread, &ctx);
                }
                ResumeThread(hThread);
                CloseHandle(hThread);
            }
        }

        /**
         * @brief 处理64位线程的断点命中事件
         * @param tid 触发断点的线程ID
         */
        void HandleBreakpointHit(DWORD tid) {
            HANDLE hThread = OpenThread(THREAD_GET_CONTEXT | THREAD_SUSPEND_RESUME, FALSE, tid);
            if (!hThread) return;

            try {
                SuspendThread(hThread);

                RegisterState state;
                state.targetAddress = m_targetAddress;

                // 读取64位寄存器状态
                ReadThreadContext(hThread, state);

                // 计算偏移
                state.ipOffset = state.baseRegs64[8] - m_targetAddress;
                state.pointerBase = state.baseRegs64[1];

                // 调用回调
                if (m_callback) m_callback(state);
            }
            catch (...) {
                // 忽略异常
            }

            // 确保线程恢复和句柄关闭
            ResumeThread(hThread);
            CloseHandle(hThread);
        }

        /**
         * @brief 读取64位线程上下文到寄存器状态
         * @param hThread 线程句柄
         * @param state 寄存器状态
         */
        void ReadThreadContext(HANDLE hThread, RegisterState& state) {
            CONTEXT ctx{};
            ctx.ContextFlags = CONTEXT_ALL | CONTEXT_AMD64;

            if (GetThreadContext(hThread, &ctx)) {
                // 64位基础寄存器
                state.baseRegs64 = { ctx.Rax, ctx.Rbx, ctx.Rcx, ctx.Rdx, ctx.Rsi, ctx.Rdi, ctx.Rbp, ctx.Rsp, ctx.Rip };
                // 64位扩展寄存器
                state.extRegs64 = { ctx.R8, ctx.R9, ctx.R10, ctx.R11, ctx.R12, ctx.R13, ctx.R14, ctx.R15 };
            }
        }

        /**
         * @brief 运行64位调试循环
         */
        void RunDebugLoop() {
            std::cout << "[+] Waiting for breakpoint hit (x64)..." << std::endl;

            DEBUG_EVENT de{};
            while (WaitForDebugEvent(&de, INFINITE)) {
                try {
                    if (de.dwDebugEventCode == EXCEPTION_DEBUG_EVENT) {
                        auto& ex = de.u.Exception.ExceptionRecord;
                        // 硬件断点触发的单步异常
                        if (ex.ExceptionCode == EXCEPTION_SINGLE_STEP) {
                            std::cout << "\n[+] Breakpoint hit on thread " << de.dwThreadId << std::endl;
                            HandleBreakpointHit(de.dwThreadId);
                            ClearAllBreakpoints();
                            ContinueDebugEvent(de.dwProcessId, de.dwThreadId, DBG_CONTINUE);
                            break;
                        }
                    }

                    // 继续调试事件
                    ContinueDebugEvent(de.dwProcessId, de.dwThreadId, DBG_CONTINUE);
                }
                catch (...) {
                    ContinueDebugEvent(de.dwProcessId, de.dwThreadId, DBG_CONTINUE);
                }
            }
        }
    };

/**
 * @brief 命名空间级别的 PrintRegisterState 函数（可直接引用）
 * @param state 寄存器状态
 * @return 宽字符指针（静态缓冲区，线程安全）
 */
    inline const wchar_t* PrintRegisterState(const RegisterState& state) {
        // 静态线程本地缓冲区，保证线程安全且多次调用不会覆盖其他线程的内容
        thread_local static std::wstring s_buffer;
        thread_local static wchar_t s_wbuffer[8192] = { 0 }; // 固定大小缓冲区，避免动态分配

        try {
            std::wostringstream oss;
            HardwareBreakpointDebugger::FormatRegisterState(state, oss);
            s_buffer = oss.str();

            // 复制到固定缓冲区（防止字符串析构）
            size_t len = s_buffer.length();
            if (len >= sizeof(s_wbuffer) / sizeof(wchar_t)) {
                len = sizeof(s_wbuffer) / sizeof(wchar_t) - 1;
            }
            wcsncpy_s(s_wbuffer, s_buffer.c_str(), len);
            s_wbuffer[len] = L'\0';
        }
        catch (...) {
            wcscpy_s(s_wbuffer, L"[ERROR] Failed to format register state");
        }

        return s_wbuffer;
    }

/**
 * @brief 便捷调用函数（整数地址版本，直接传入十进制地址）
 * @param pid 目标进程ID
 * @param addr 目标地址（十进制整数）
 * @param cb 回调函数
 * @param type 断点类型
 * @param byteLen 监控字节长度
 * @return 成功返回true，失败返回false
 */
    inline bool RunHardwareBreakpoint(DWORD pid, DWORD64 addr,
                                      BreakpointCallback cb = nullptr,
                                      BreakpointType type = BreakpointType::ReadWrite,
                                      size_t byteLen = 1) {
        HardwareBreakpointDebugger dbg;
        if (cb) dbg.SetCallback(cb);
        return dbg.Start(pid, addr, type, byteLen);
    }

} // namespace Debugger