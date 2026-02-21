package com.scm.all.struct;

public class TagPmemory_Basic_InfoRmationX86 {
    /**
     * 区域基地址
     */
    public int BaseAddress;
    /**
     * 分配基地址
     */
    public int AllocationBase;
    /**
     * 原始保护
     */
    public int AllocationProtect;
    /**
     * 正常 0
     */
    public int RegionSize;
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
        return "TagPmemory_Basic_InfoRmationX86{" +
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
