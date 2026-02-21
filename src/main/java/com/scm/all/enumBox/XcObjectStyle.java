package com.scm.all.enumBox;

/**
 * 对象样式枚举（对应 C++ 枚举 XC_OBJECT_STYLE，用于区分外观）
 * group_ObjectStyle 对象样式(XC_OBJECT_STYLE,用于区分外观)
 */
public enum XcObjectStyle {
    xc_style_default(0),

    /**
     * 别名：按钮样式_默认
     * 默认风格
     */
    button_style_default(xc_style_default.getValue()),

    /**
     * 别名：按钮样式_单选
     * 单选按钮
     */
    button_style_radio(1),

    /**
     * 别名：按钮样式_多选
     * 多选按钮
     */
    button_style_check(2),

    /**
     * 别名：按钮样式_图标
     * 图标按钮
     */
    button_style_icon(3),

    /**
     * 别名：按钮样式_展开
     * 展开按钮
     */
    button_style_expand(4),

    /**
     * 别名：按钮样式_关闭
     * 关闭按钮
     */
    button_style_close(5),

    /**
     * 别名：按钮样式_最大化
     * 最大化按钮
     */
    button_style_max(6),

    /**
     * 别名：按钮样式_最小化
     * 最小化按钮
     */
    button_style_min(7),

    /**
     * 水平滚动条-左按钮
     */
    button_style_scrollbar_left(8),

    /**
     * 水平滚动条-右按钮
     */
    button_style_scrollbar_right(9),

    /**
     * 垂直滚动条-上按钮
     */
    button_style_scrollbar_up(10),

    /**
     * 垂直滚动条-下按钮
     */
    button_style_scrollbar_down(11),

    /**
     * 水平滚动条-滑块
     */
    button_style_scrollbar_slider_h(12),

    /**
     * 垂直滚动条-滑块
     */
    button_style_scrollbar_slider_v(13),

    /**
     * Tab条-按钮
     */
    button_style_tabBar(14),

    /**
     * 滑动条-滑块
     */
    button_style_slider(15),

    /**
     * 工具条-按钮
     */
    button_style_toolBar(16),

    /**
     * 工具条-左滚动按钮
     */
    button_style_toolBar_left(17),

    /**
     * 工具条-右滚动按钮
     */
    button_style_toolBar_right(18),

    /**
     * 窗格-关闭按钮
     */
    button_style_pane_close(19),

    /**
     * 窗格-锁定按钮
     */
    button_style_pane_lock(20),

    /**
     * 窗格-菜单按钮
     */
    button_style_pane_menu(21),

    /**
     * 窗格-码头按钮左
     */
    button_style_pane_dock_left(22),

    /**
     * 窗格-码头按钮上
     */
    button_style_pane_dock_top(23),

    /**
     * 窗格-码头按钮右
     */
    button_style_pane_dock_right(24),

    /**
     * 窗格-码头按钮下
     */
    button_style_pane_dock_bottom(25),

    /**
     * 框架窗口-停靠码头左
     */
    element_style_frameWnd_dock_left(26),

    /**
     * 框架窗口-停靠码头上
     */
    element_style_frameWnd_dock_top(27),

    /**
     * 框架窗口-停靠码头右
     */
    element_style_frameWnd_dock_right(28),

    /**
     * 框架窗口-停靠码头下
     */
    element_style_frameWnd_dock_bottom(29),

    /**
     * 工具条-分割线
     */
    element_style_toolBar_separator(30),

    /**
     * 组合框-下拉列表框,下拉组合框弹出的ListBox
     */
    listBox_style_comboBox(31);

    private final int value;

    XcObjectStyle(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static XcObjectStyle fromValue(int value) {
        for (XcObjectStyle style : values()) {
            if (style.value == value) {
                return style;
            }
        }
        return null;
    }
}
