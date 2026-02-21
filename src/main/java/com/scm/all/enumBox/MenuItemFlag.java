package com.scm.all.enumBox;

/**
 * 菜单项标识枚举（对应 C++ 枚举 menu_item_flag_）
 * menu_item_flag_ 菜单项标识(menu_item_flag_)
 * group_enum
 */
public enum MenuItemFlag {
    /**
     * 别名：正常
     * 正常
     */
    menu_item_flag_normal(0x00),

    /**
     * 别名：选择
     * 选择或鼠标停留
     */
    menu_item_flag_select(0x01),

    /**
     * 别名：停留
     * 选择或鼠标停留 等于 menu_item_flag_select
     */
    menu_item_flag_stay(0x01),

    /**
     * 别名：勾选
     * 勾选
     */
    menu_item_flag_check(0x02),

    /**
     * 别名：弹出
     * 弹出
     */
    menu_item_flag_popup(0x04),

    /**
     * 别名：分隔栏
     * 分隔栏 ID号任意,ID号被忽略
     */
    menu_item_flag_separator(0x08),

    /**
     * 别名：禁用
     * 禁用
     */
    menu_item_flag_disable(0x10);

    private final int value;

    MenuItemFlag(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MenuItemFlag fromValue(int value) {
        for (MenuItemFlag flag : values()) {
            if (flag.value == value) {
                return flag;
            }
        }
        return null;
    }
}
