package com.scm.all.export.capstone.constant;

/**
 * 模式类型
 */
public class TAG_CS_MODE {
    /**
     * 小端模式(默认模式)
     */
    public static int CS_MODE_LITTLE_ENDIAN = 0;  // little-endian mode (default mode)
    /**
     * 32位的手臂
     */
    public static int CS_MODE_ARM = 0;    // 32-bit ARM
    /**
     * 16位模式(X86)
     */
    public static int CS_MODE_16 = 1 << 1;    // 16-bit mode (X86)
    /**
     * 32位模式(X86)
     */
    public static int CS_MODE_32 = 1 << 2;    // 32-bit mode (X86)
    /**
     * 64位模式(X86, PPC)
     */
    public static int CS_MODE_64 = 1 << 3;    // 64-bit mode (X86, PPC)
    /**
     * ARM的Thumb模式，包括Thumb-2
     */
    public static int CS_MODE_THUMB = 1 << 4; // ARM's Thumb mode, including Thumb-2
    /**
     * ARM的Cortex-M系列
     */
    public static int CS_MODE_MCLASS = 1 << 5;    // ARM's Cortex-M series
    /**
     * ARM的ARMv8 A32编码
     */
    public static int CS_MODE_V8 = 1 << 6;    // ARMv8 A32 encodings for ARM
    /**
     * MicroMips mode (MIPS)
     */
    public static int CS_MODE_MICRO = 1 << 4; // MicroMips mode (MIPS)
    /**
     * Mips III ISA
     */
    public static int CS_MODE_MIPS3 = 1 << 5; // Mips III ISA
    /**
     * Mips32r6 ISA
     */
    public static int CS_MODE_MIPS32R6 = 1 << 6; // Mips32r6 ISA
    /**
     * SparcV9模式(Sparc)
     */
    public static int CS_MODE_V9 = 1 << 4; // SparcV9 mode (Sparc)
    /**
     * 四处理机扩展模式(PPC)
     */
    public static int CS_MODE_QPX = 1 << 4; // Quad Processing eXtensions mode (PPC)
    /**
     * M68K 68000 模式
     */
    public static int CS_MODE_M68K_000 = 1 << 1; // M68K 68000 mode
    /**
     * M68K 68010 模式
     */
    public static int CS_MODE_M68K_010 = 1 << 2; // M68K 68010 mode
    /**
     * M68K 68020模式
     */
    public static int CS_MODE_M68K_020 = 1 << 3; // M68K 68020 mode
    /**
     * M68K 68030模式
     */
    public static int CS_MODE_M68K_030 = 1 << 4; // M68K 68030 mode
    /**
     * M68K 68040模式
     */
    public static int CS_MODE_M68K_040 = 1 << 5; // M68K 68040 mode
    /**
     * M68K 68060模式
     */
    public static int CS_MODE_M68K_060 = 1 << 6; // M68K 68060 mode
    /**
     * 高位优先模式
     */
    public static int CS_MODE_BIG_ENDIAN = 1 << 31;   // big-endian mode
    /**
     * Mips32 ISA (Mips)
     */
    public static int CS_MODE_MIPS32 = CS_MODE_32;    // Mips32 ISA (Mips)
    /**
     * Mips64 ISA (Mips)
     */
    public static int CS_MODE_MIPS64 = CS_MODE_64;    // Mips64 ISA (Mips)

    @Override
    public String toString() {
        return "Tag_CS_Mode{" +
                "CS_MODE_LITTLE_ENDIAN=" + CS_MODE_LITTLE_ENDIAN +
                ", CS_MODE_ARM=" + CS_MODE_ARM +
                ", CS_MODE_16=" + CS_MODE_16 +
                ", CS_MODE_32=" + CS_MODE_32 +
                ", CS_MODE_64=" + CS_MODE_64 +
                ", CS_MODE_THUMB=" + CS_MODE_THUMB +
                ", CS_MODE_MCLASS=" + CS_MODE_MCLASS +
                ", CS_MODE_V8=" + CS_MODE_V8 +
                ", CS_MODE_MICRO=" + CS_MODE_MICRO +
                ", CS_MODE_MIPS3=" + CS_MODE_MIPS3 +
                ", CS_MODE_MIPS32R6=" + CS_MODE_MIPS32R6 +
                ", CS_MODE_V9=" + CS_MODE_V9 +
                ", CS_MODE_QPX=" + CS_MODE_QPX +
                ", CS_MODE_M68K_000=" + CS_MODE_M68K_000 +
                ", CS_MODE_M68K_010=" + CS_MODE_M68K_010 +
                ", CS_MODE_M68K_020=" + CS_MODE_M68K_020 +
                ", CS_MODE_M68K_030=" + CS_MODE_M68K_030 +
                ", CS_MODE_M68K_040=" + CS_MODE_M68K_040 +
                ", CS_MODE_M68K_060=" + CS_MODE_M68K_060 +
                ", CS_MODE_BIG_ENDIAN=" + CS_MODE_BIG_ENDIAN +
                ", CS_MODE_MIPS32=" + CS_MODE_MIPS32 +
                ", CS_MODE_MIPS64=" + CS_MODE_MIPS64 +
                '}';
    }
}
