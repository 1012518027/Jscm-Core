package com.scm.all.enumBox;

/**
 * 窗口位置枚举（对应 C++ 枚举 window_position_）
 * groupWindow_position 窗口位置(window_position_)
 * group_enum
 */
public enum WindowPosition {
    /**
     * 别名：错误
     * 错误
     */
    window_position_error(-1),

    /**
     * 别名：顶部
     * top
     */
    window_position_top(0),

    /**
     * 别名：底部
     * bottom
     */
    window_position_bottom(1),

    /**
     * 别名：左边
     * left
     */
    window_position_left(2),

    /**
     * 别名：右边
     * right
     */
    window_position_right(3),

    /**
     * 别名：内容区
     * body
     */
    window_position_body(4),

    /**
     * 别名：整个窗口
     * window 整个窗口
     */
    window_position_window(5);

    private final int value;

    WindowPosition(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static WindowPosition fromValue(int value) {
        for (WindowPosition position : values()) {
            if (position.value == value) {
                return position;
            }
        }
        return null;
    }
}
