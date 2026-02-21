package com.scm.all.struct;

public class TagPmemory_Basic_InfoRmationX64 {
    /**
     * 区域基地址
     */
    public long BaseAddress;
    /**
     * 分配基地址
     */
    public long AllocationBase;
    /**
     * 原始保护
     */
    public int AllocationProtect;
    /**
     * 正常 0
     */
    public long RegionSize;
    /**
     * 状态
     */
    public int State;
    /**
     * 保护
     */
    public int Protect;
    /**
     * 类型
     */
    public int lType;
    @Override
    public String toString() {
        return "TagPmemory_Basic_InfoRmationX64{" +
                "BaseAddress=" + BaseAddress +
                ", AllocationBase=" + AllocationBase +
                ", AllocationProtect=" + AllocationProtect +
                ", RegionSize=" + RegionSize +
                ", State=" + State +
                ", Protect=" + Protect +
                ", lType=" + lType +
                '}';
    }
}
