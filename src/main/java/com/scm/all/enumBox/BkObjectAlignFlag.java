package com.scm.all.enumBox;

/**
 * 背景对象对齐方式枚举（对应 C++ 枚举 bkObject_align_flag_）
 * group_bkInfo_align_flag_ 背景对象对齐方式(bkObject_align_flag_)
 * group_enum
 */
public enum BkObjectAlignFlag {
    /**
     * 别名：无
     * 无
     */
    bkObject_align_flag_no(0x000),

    /**
     * 别名：左对齐
     * 左对齐, 当设置此标识时,外间距(margin.left)代表左侧间距; 当right未设置时,那么外间距(margin.right)代表宽度;
     */
    bkObject_align_flag_left(0x001),

    /**
     * 别名：顶对齐
     * 顶对齐, 当设置此标识时,外间距(margin.top)代表顶部间距; 当bottom未设置时,那么外间距(margin.bottom)代表高度;
     */
    bkObject_align_flag_top(0x002),

    /**
     * 别名：右对齐
     * 右对齐, 当设置此标识时,外间距(margin.right)代表右侧间距; 当left未设置时,那么外间距(margin.left)代表宽度;
     */
    bkObject_align_flag_right(0x004),

    /**
     * 别名：底对齐
     * 底对齐, 当设置此标识时,外间距(margin.bottom)代表底部间距; 当top未设置时,那么外间距(margin.top)代表高度;
     */
    bkObject_align_flag_bottom(0x008),

    /**
     * 别名：水平居中
     * 水平居中, 当设置此标识时,外间距(margin.left)代表宽度;
     */
    bkObject_align_flag_center(0x010),

    /**
     * 别名：垂直居中
     * 垂直居中, 当设置此标识时,外间距(margin.top)代表高度;
     */
    bkObject_align_flag_center_v(0x020);

    private final int value;

    BkObjectAlignFlag(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static BkObjectAlignFlag fromValue(int value) {
        for (BkObjectAlignFlag flag : values()) {
            if (flag.value == value) {
                return flag;
            }
        }
        return null;
    }
}
