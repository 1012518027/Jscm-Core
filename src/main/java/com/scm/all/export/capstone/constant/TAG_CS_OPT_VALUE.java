package com.scm.all.export.capstone.constant;

public class TAG_CS_OPT_VALUE {
    /**
     * 关闭一个选项 - CS_OPT_DETAIL、CS_OPT_SKIPDATA、CS_OPT_UNSIGNED的默认值。
     */
    public static int CS_OPT_OFF = 0;  // Turn OFF an option - default for CS_OPT_DETAIL, CS_OPT_SKIPDATA, CS_OPT_UNSIGNED.
    /**
     * 打开选项（CS_OPT_DETAIL、CS_OPT_SKIPDATA）。
     */
    public static int CS_OPT_ON = 3; // Turn ON an option (CS_OPT_DETAIL, CS_OPT_SKIPDATA).
    /**
     * 默认 asm 语法 （CS_OPT_SYNTAX）。
     */
    public static int CS_OPT_SYNTAX_DEFAULT = 0; // Default asm syntax (CS_OPT_SYNTAX).
    /**
     * X86 英特尔 asm 语法 - 默认为 X86 （CS_OPT_SYNTAX）。
     */
    public static int CS_OPT_SYNTAX_INTEL = 1; // X86 Intel asm syntax - default on X86 (CS_OPT_SYNTAX).
    /**
     * X86 ATT asm 语法 （CS_OPT_SYNTAX）
     */
    public static int CS_OPT_SYNTAX_ATT = 2;   // X86 ATT asm syntax (CS_OPT_SYNTAX).
    /**
     * 打印仅带数字的寄存器名称 （CS_OPT_SYNTAX）
     */
    public static int CS_OPT_SYNTAX_NOREGNAME = 3; // Prints register name with only number (CS_OPT_SYNTAX)
    /**
     * X86 英特尔 Masm 语法 （CS_OPT_SYNTAX）。
     */
    public static int CS_OPT_SYNTAX_MASM = 4; // X86 Intel Masm syntax (CS_OPT_SYNTAX).
}
