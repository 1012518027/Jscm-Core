package com.scm.all.export.capstone.constant;

/**
 * Capstone API遇到的所有类型的错误
 * 这些是cs_errno()返回的值
 */
public class TAG_CS_ERR {
    /**
     * 没有错误:一切都很好
     */
    public static int CS_ERR_OK = 0;   // No error: everything was fine
    /**
     * 内存不足错误:cs_open()， cs_disasm()， cs_disasm_iter()
     */
    public static int CS_ERR_MEM = 1;      // Out-Of-Memory error: cs_open(), cs_disasm(), cs_disasm_iter()
    /**
     * 不支持的架构:cs_open()
     */
    public static int CS_ERR_ARCH = 2;     // Unsupported architecture: cs_open()
    /**
     * 无效句柄:cs_op_count()， cs_op_index()
     */
    public static int CS_ERR_HANDLE = 3;   // Invalid handle: cs_op_count(), cs_op_index()
    /**
     * 无效的csh参数:cs_close()， cs_errno()， cs_option()
     */
    public static int CS_ERR_CSH = 4;    // Invalid csh argument: cs_close(), cs_errno(), cs_option()
    /**
     * 无效/不支持的模式:cs_open()
     */
    public static int CS_ERR_MODE = 5;   // Invalid/unsupported mode: cs_open()
    /**
     * 无效/不支持的选项:cs_option()
     */
    public static int CS_ERR_OPTION = 6;  // Invalid/unsupported option: cs_option()
    /**
     * 信息不可用，因为细节选项为OFF
     */
    public static int CS_ERR_DETAIL = 7;  // Information is unavailable because detail option is OFF
    /**
     * 未初始化的动态内存管理(参见CS_OPT_MEM)
     */
    public static int CS_ERR_MEMSETUP = 8; // Dynamic memory management uninitialized (see CS_OPT_MEM)
    /**
     * 不支持的版本(绑定)
     */
    public static int CS_ERR_VERSION = 9;  // Unsupported version (bindings)
    /**
     * 在“节食”引擎中访问无关数据
     */
    public static int CS_ERR_DIET = 10;     // Access irrelevant data in "diet" engine
    /**
     * 以SKIPDATA模式访问“data”指令的无关数据
     */
    public static int CS_ERR_SKIPDATA = 11; // Access irrelevant data for "data" instruction in SKIPDATA mode
    /**
     * 不支持X86 AT and T语法(在编译时选择退出)
     */
    public static int CS_ERR_X86_ATT = 12; // X86 AT&T syntax is unsupported (opt-out at compile time)
    /**
     * 不支持X86 Intel语法(在编译时选择退出)
     */
    public static int CS_ERR_X86_INTEL = 13; // X86 Intel syntax is unsupported (opt-out at compile time)
    /**
     * 不支持X86 Intel语法(在编译时选择退出)
     */
    public static int CS_ERR_X86_MASM = 14;// X86 Intel syntax is unsupported (opt-out at compile time)

    @Override
    public String toString() {
        return "Tag_CS_Err{" +
                "CS_ERR_OK=" + CS_ERR_OK +
                ", CS_ERR_MEM=" + CS_ERR_MEM +
                ", CS_ERR_ARCH=" + CS_ERR_ARCH +
                ", CS_ERR_HANDLE=" + CS_ERR_HANDLE +
                ", CS_ERR_CSH=" + CS_ERR_CSH +
                ", CS_ERR_MODE=" + CS_ERR_MODE +
                ", CS_ERR_OPTION=" + CS_ERR_OPTION +
                ", CS_ERR_DETAIL=" + CS_ERR_DETAIL +
                ", CS_ERR_MEMSETUP=" + CS_ERR_MEMSETUP +
                ", CS_ERR_VERSION=" + CS_ERR_VERSION +
                ", CS_ERR_DIET=" + CS_ERR_DIET +
                ", CS_ERR_SKIPDATA=" + CS_ERR_SKIPDATA +
                ", CS_ERR_X86_ATT=" + CS_ERR_X86_ATT +
                ", CS_ERR_X86_INTEL=" + CS_ERR_X86_INTEL +
                ", CS_ERR_X86_MASM=" + CS_ERR_X86_MASM +
                '}';
    }
}
