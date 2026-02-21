package com.scm.all.enumBox;

/**
 * 项背景绘制标识枚举（对应 C++ 枚举 list_drawItemBk_flag_）
 * 适用于(List,ListBox,ListView,Tree)
 * groupListDrawItemBkFlag 项背景绘制标识(List,ListBox,ListView,Tree)
 * group_enum
 */
public enum ListDrawItemBkFlag {
    /**
     * 别名：不绘制
     * 不绘制
     */
    list_drawItemBk_flag_nothing(0x000),

    /**
     * 别名：鼠标离开
     * 绘制鼠标离开状态项背景
     */
    list_drawItemBk_flag_leave(0x001),

    /**
     * 别名：鼠标停留
     * 绘制鼠标停留状态项背景
     */
    list_drawItemBk_flag_stay(0x002),

    /**
     * 别名：项选择
     * 绘制鼠标选择状态项背景
     */
    list_drawItemBk_flag_select(0x004),

    /**
     * 别名：组鼠标离开
     * 绘制鼠标离开状态组背景,当项为组时
     */
    list_drawItemBk_flag_group_leave(0x008),

    /**
     * 别名：组鼠标停留
     * 绘制鼠标停留状态组背景,当项为组时
     */
    list_drawItemBk_flag_group_stay(0x010),

    /**
     * 别名：水平分割线
     * 列表绘制水平分割线
     */
    list_drawItemBk_flag_line(0x020),

    /**
     * 别名：垂直分割线
     * 列表绘制垂直分割线
     */
    list_drawItemBk_flag_lineV(0x040);

    private final int value;

    ListDrawItemBkFlag(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ListDrawItemBkFlag fromValue(int value) {
        for (ListDrawItemBkFlag flag : values()) {
            if (flag.value == value) {
                return flag;
            }
        }
        return null;
    }
}
