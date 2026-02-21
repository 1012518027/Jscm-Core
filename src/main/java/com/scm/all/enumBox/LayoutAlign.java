package com.scm.all.enumBox;

/**
 * 布局对齐枚举（对应 C++ 枚举 layout_align_）
 * Group_layout_align_ 布局对齐(layout_align_)
 * group_enum
 */
public enum LayoutAlign {
    /**
     * 别名：左侧
     * 左侧
     */
    layout_align_left(0),

    /**
     * 别名：顶部
     * 顶部
     */
    layout_align_top(1),

    /**
     * 别名：右侧
     * 右侧
     */
    layout_align_right(2),

    /**
     * 别名：底部
     * 底部
     */
    layout_align_bottom(3),

    /**
     * 别名：居中
     * 居中
     */
    layout_align_center(4),

    /**
     * 别名：等距
     * 等距
     */
    layout_align_equidistant(5);

    private final int value;

    LayoutAlign(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static LayoutAlign fromValue(int value) {
        for (LayoutAlign align : values()) {
            if (align.value == value) {
                return align;
            }
        }
        return null;
    }
}
