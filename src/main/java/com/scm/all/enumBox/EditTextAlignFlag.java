package com.scm.all.enumBox;

/**
 * 编辑框文本对齐枚举（对应 C++ 枚举 edit_textAlign_flag_）
 * Group_edit_textAlign_flag_ 编辑框文本对齐(edit_textAlign_flag_)
 * group_enum
 */
public enum EditTextAlignFlag {
    /**
     * 别名：左侧
     * 左侧
     */
    edit_textAlign_flag_left(0x0),

    /**
     * 别名：右侧
     * 右侧
     */
    edit_textAlign_flag_right(0x1),

    /**
     * 别名：水平居中
     * 水平居中
     */
    edit_textAlign_flag_center(0x2),

    /**
     * 别名：顶部
     * 顶部
     */
    edit_textAlign_flag_top(0x0),

    /**
     * 别名：底部
     * 底部
     */
    edit_textAlign_flag_bottom(0x4),

    /**
     * 别名：垂直居中
     * 垂直居中
     */
    edit_textAlign_flag_center_v(0x8);

    private final int value;

    EditTextAlignFlag(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static EditTextAlignFlag fromValue(int value) {
        for (EditTextAlignFlag flag : values()) {
            if (flag.value == value) {
                return flag;
            }
        }
        return null;
    }
}
