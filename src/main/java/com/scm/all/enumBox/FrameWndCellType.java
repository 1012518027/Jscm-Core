package com.scm.all.enumBox;

/**
 * 框架窗口单元格类型枚举（对应 C++ 枚举 frameWnd_cell_type_）
 * group_frameWnd_cell_type_ 框架窗口单元格类型(frameWnd_cell_type_)
 * group_enum
 */
public enum FrameWndCellType {
    /**
     * 别名：无
     * 无
     */
    frameWnd_cell_type_no(0),

    /**
     * 别名：窗格
     * 窗格
     */
    frameWnd_cell_type_pane(1),

    /**
     * 别名：窗格组
     * 窗格组
     */
    frameWnd_cell_type_group(2),

    /**
     * 别名：主视图区
     * 主视图区
     */
    frameWnd_cell_type_bodyView(3),

    /**
     * 别名：上下布局
     * 上下布局
     */
    frameWnd_cell_type_top_bottom(4),

    /**
     * 别名：左右布局
     * 左右布局
     */
    frameWnd_cell_type_left_right(5);

    private final int value;

    FrameWndCellType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static FrameWndCellType fromValue(int value) {
        for (FrameWndCellType type : values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }
}
