package com.scm.all.export.capstone.constant;


/**
 * option 类型
 */
public class TAG_CS_OPT_TYPE {
    /**
     * 未指定选项
     */
    public static int CS_OPT_INVALID = 0; // No option specified
    /**
     * 程序集输出语法
     */
    public static int CS_OPT_SYNTAX = 1;  // Assembly output syntax
    /**
     * 将指令结构分解为细节
     */
    public static int CS_OPT_DETAIL = 2;  // Break down instruction structure into details
    /**
     * 在运行时更改引擎的模式
     */
    public static int CS_OPT_MODE = 3;    // Change engine's mode at run-time
    /**
     * 用户定义的动态内存相关功能
     */
    public static int CS_OPT_MEM = 4; // User-defined dynamic memory related functions
    /**
     * 反汇编时跳过数据。然后引擎处于SKIPDATA模式。
     */
    public static int CS_OPT_SKIPDATA = 5; // Skip data when disassembling. Then engine is in SKIPDATA mode.
    /**
     * 为SKIPDATA选项设置用户定义函数
     */
    public static int CS_OPT_SKIPDATA_SETUP = 6; // Setup user-defined function for SKIPDATA option
    /**
     * 自定义指令助记符
     */
    public static int CS_OPT_MNEMONIC = 7; // Customize instruction mnemonic
    /**
     * 以无符号形式打印即时操作数
     */
    public static int CS_OPT_UNSIGNED = 8; // print immediate operands in unsigned form
}
