package com.scm.all.enumBox;

/**
 * 窗格对齐枚举（对应 C++ 枚举 pane_align_）
 * Group_pane_align_ 窗格对齐(pane_align_)
 * group_enum
 */
public enum PaneAlign {
    /**
     * 别名：错误
     * 错误
     */
    pane_align_error(-1),

    /**
     * 别名：左侧
     * 左侧
     */
    pane_align_left(0),

    /**
     * 别名：顶部
     * 顶部
     */
    pane_align_top(1),

    /**
     * 别名：右侧
     * 右侧
     */
    pane_align_right(2),

    /**
     * 别名：底部
     * 底部
     */
    pane_align_bottom(3),

    /**
     * 别名：居中
     * 居中
     */
    pane_align_center(4);

    private final int value;

    PaneAlign(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PaneAlign fromValue(int value) {
        for (PaneAlign align : values()) {
            if (align.value == value) {
                return align;
            }
        }
        return null;
    }
}
