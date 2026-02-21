package com.scm.all.enumBox;

/**
 * 调整布局标识枚举（对应 C++ 枚举 adjustLayout_）
 * Group_adjustLayout 调整布局标识位(adjustLayout_)
 * group_enum
 */
public enum AdjustLayout {
    /**
     * 别名：不调整
     * 不调整布局
     */
    adjustLayout_no(0x00),

    /**
     * 别名：强制调整全部
     * 强制调整自身和子对象布局.
     */
    adjustLayout_all(0x01),

    /**
     * 别名：只调整自身
     * 只调整自身布局,不调整子对象布局.
     */
    adjustLayout_self(0x02);

    private final int value;

    AdjustLayout(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static AdjustLayout fromValue(int value) {
        for (AdjustLayout layout : values()) {
            if (layout.value == value) {
                return layout;
            }
        }
        return null;
    }
}
