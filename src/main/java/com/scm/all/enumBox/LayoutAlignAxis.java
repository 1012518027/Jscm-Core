package com.scm.all.enumBox;

/**
 * 布局轴对齐枚举（对应 C++ 枚举 layout_align_axis_）
 * groupLayoutAlignAxis 布局轴对齐(layout_align_axis_)
 * group_enum
 */
public enum LayoutAlignAxis {
    /**
     * 别名：无
     * 无
     */
    layout_align_axis_auto(0),

    /**
     * 别名：开始
     * 水平布局(顶部), 垂直布局(左侧)
     */
    layout_align_axis_start(1),

    /**
     * 别名：居中
     * 居中
     */
    layout_align_axis_center(2),

    /**
     * 别名：末尾
     * 水平布局(底部), 垂直布局(右侧)
     */
    layout_align_axis_end(3);

    private final int value;

    LayoutAlignAxis(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static LayoutAlignAxis fromValue(int value) {
        for (LayoutAlignAxis alignAxis : values()) {
            if (alignAxis.value == value) {
                return alignAxis;
            }
        }
        return null;
    }
}
