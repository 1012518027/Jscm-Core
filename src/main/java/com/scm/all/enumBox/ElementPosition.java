package com.scm.all.enumBox;

/**
 * UI元素位置枚举（对应 C++ 枚举 element_position_）
 * groupElement_position UI元素位置(element_position_)
 * group_enum
 */
public enum ElementPosition {
    /**
     * 别名：无
     * 无效
     */
    element_position_no(0x00),

    /**
     * 别名：左边
     * 左边
     */
    element_position_left(0x01),

    /**
     * 别名：上边
     * 上边
     */
    element_position_top(0x02),

    /**
     * 别名：右边
     * 右边
     */
    element_position_right(0x04),

    /**
     * 别名：下边
     * 下边
     */
    element_position_bottom(0x08);

    private final int value;

    ElementPosition(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ElementPosition fromValue(int value) {
        for (ElementPosition position : values()) {
            if (position.value == value) {
                return position;
            }
        }
        return null;
    }
}
