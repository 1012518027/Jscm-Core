package com.scm.all.enumBox;

/**
 * 硬件断点类型枚举（对应C++的BreakpointType）
 * @author Debugger
 */
public enum BreakpointType {
    /** 读/写触发 */
    ReadWrite(0),
    /** 执行触发 */
    Execute(1),
    /** 仅写触发 */
    Write(2);
    private final int value;

    BreakpointType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static BreakpointType fromValue(int value) {
        for (BreakpointType flag : values()) {
            if (flag.value == value) {
                return flag;
            }
        }
        return null;
    }
}
