package com.scm.all.export.capstone.constant;

/**
 * 架构类型
 */
public class TAG_CS_ARCH {
    /**
     * ARM架构(包括Thumb、Thumb-2)
     */
    public static int CS_ARCH_ARM = 0;    // ARM architecture (including Thumb, Thumb-2)
    /**
     *  ARM-64，也叫AArch64
     */
    public static int CS_ARCH_ARM64 = 1;      // ARM-64, also called AArch64
    /**
     * Mips架构
     */
    public static int CS_ARCH_MIPS = 2;       // Mips architecture
    /**
     * X86架构(包括X86 或 X86 -64)
     */
    public static int CS_ARCH_X86 = 3;        // X86 architecture (including x86 & x86-64)
    /**
     * PowerPC架构
     */
    public static int CS_ARCH_PPC = 4;        // PowerPC architecture
    /**
     * Sparc体系结构
     */
    public static int CS_ARCH_SPARC = 5;      // Sparc architecture
    /**
     * SystemZ架构
     */
    public static int CS_ARCH_SYSZ = 6;       // SystemZ architecture
    /**
     * XCore架构
     */
    public static int CS_ARCH_XCORE = 7;      // XCore architecture
    /**
     * 68 k架构
     */
    public static int CS_ARCH_M68K = 8;       // 68K architecture
    /**
     * 未知
     */
    public static int CS_ARCH_MAX = 9;
    /**
     * 所有体系结构-适用于cs_support()
     */
    public static int CS_ARCH_ALL = 0xFFFF; // All architectures - for cs_support()

    @Override
    public String toString() {
        return "Tag_CS_Arch{" +
                "CS_ARCH_ARM=" + CS_ARCH_ARM +
                ", CS_ARCH_ARM64=" + CS_ARCH_ARM64 +
                ", CS_ARCH_MIPS=" + CS_ARCH_MIPS +
                ", CS_ARCH_X86=" + CS_ARCH_X86 +
                ", CS_ARCH_PPC=" + CS_ARCH_PPC +
                ", CS_ARCH_SPARC=" + CS_ARCH_SPARC +
                ", CS_ARCH_SYSZ=" + CS_ARCH_SYSZ +
                ", CS_ARCH_XCORE=" + CS_ARCH_XCORE +
                ", CS_ARCH_M68K=" + CS_ARCH_M68K +
                ", CS_ARCH_MAX=" + CS_ARCH_MAX +
                ", CS_ARCH_ALL=" + CS_ARCH_ALL +
                '}';
    }
}
