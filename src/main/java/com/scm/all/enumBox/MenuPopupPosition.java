package com.scm.all.enumBox;

/**
 * 菜单弹出方向枚举（对应 C++ 枚举 menu_popup_position_）
 * menu_popup_position_ 菜单弹出方向(menu_popup_position_)
 * group_enum
 */
public enum MenuPopupPosition {
    /**
     * 别名：左上角
     * 左上角
     */
    menu_popup_position_left_top(0),

    /**
     * 别名：左下角
     * 左下角
     */
    menu_popup_position_left_bottom(1),

    /**
     * 别名：右上角
     * 右上角
     */
    menu_popup_position_right_top(2),

    /**
     * 别名：右下角
     * 右下角
     */
    menu_popup_position_right_bottom(3),

    /**
     * 别名：左居中
     * 左居中
     */
    menu_popup_position_center_left(4),

    /**
     * 别名：上居中
     * 上居中
     */
    menu_popup_position_center_top(5),

    /**
     * 别名：右居中
     * 右居中
     */
    menu_popup_position_center_right(6),

    /**
     * 别名：下居中
     * 下居中
     */
    menu_popup_position_center_bottom(7);

    private final int value;

    MenuPopupPosition(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MenuPopupPosition fromValue(int value) {
        for (MenuPopupPosition position : values()) {
            if (position.value == value) {
                return position;
            }
        }
        return null;
    }
}
