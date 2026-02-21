package com.scm.all.export.capstone;

import com.scm.all.export.ModuleOperationUtilsJNI;
import com.scm.all.pfunc.Tag_cs_insnCallBack;

/**
 * CapStone 反汇编引擎   不适合循环使用！
 */
public class CapStone {
    /**
     * 置在运行时拆卸引擎的选项
     * @param cs_opt_type 要设置的选项类型
     * @param lens 与@type对应的选项值
     * @return 成功时返回CS_ERR_OK，失败时返回其他值。 0
     */
    public static int CapStone_cs_option(int cs_opt_type,int lens){
        return ModuleOperationUtilsJNI.CapStone_cs_option(cs_opt_type, lens);
    }

    /**
     * 初始化CS句柄:这必须在使用CS之前完成。
     * @param arch 架构类型(CS_ARCH_*)
     * @param mode 硬件模式。这是CS_MODE_* 的组合
     * @return 成功时返回CS_ERR_OK，失败时返回其他值(参考cs_err enum)
     * 	详细错误)。
     */
    public static int CapStone_cs_open(int arch,int mode){
        return ModuleOperationUtilsJNI.CapStone_cs_open(arch,mode);
    }

    /** 反汇编引擎
     * @param pOpCode 字节
     * @param pAddr 内存地址  没有就给 0
     * @param Tag_cs_insn  Tag_cs_insn 类
     * @return 返回汇编长度
     */
    public static int CapStone_cs_disasm(byte[] pOpCode, int pAddr, Tag_cs_insnCallBack Tag_cs_insn){
        return ModuleOperationUtilsJNI.CapStone_cs_disasm(pOpCode,pAddr,Tag_cs_insn);
    }
    /** 反汇编引擎
     * @param pOpCode 字节
     * @param pAddr 内存地址  没有就给 0
     * @param Tag_cs_insn  Tag_cs_insn 类
     * @return 返回汇编长度
     */
    public static int CapStone_cs_disasm(byte[] pOpCode, long pAddr, Tag_cs_insnCallBack Tag_cs_insn){
        return ModuleOperationUtilsJNI.CapStone_cs_disasm(pOpCode,pAddr,Tag_cs_insn);
    }

    /**
     * 释放引擎
     */
    public static void CapStone_cs_free(){
        ModuleOperationUtilsJNI.CapStone_cs_free();
    }
}
