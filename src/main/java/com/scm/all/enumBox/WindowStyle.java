package com.scm.all.enumBox;

/**
 * 炫彩窗口样式枚举（对应 C++ 枚举 window_style_）
 * group_WindowStyle 窗口样式(window_style_)
 */
public enum WindowStyle {
    /**
     * 别名：空
     * 什么也没有
     */
    window_style_nothing(0x0000),

    /**
     * 别名：标题栏
     * 标题栏
     */
    window_style_caption(0x0001),

    /**
     * 别名：边框
     * 边框，如果没有指定，那么边框大小为0
     */
    window_style_border(0x0002),

    /**
     * 别名：居中
     * 窗口居中
     */
    window_style_center(0x0004),

    /**
     * 别名：拖动边框
     * 拖动窗口边框
     */
    window_style_drag_border(0x0008),

    /**
     * 别名：拖动窗口
     * 拖动窗口
     */
    window_style_drag_window(0x0010),

    /**
     * 别名：允许最大化
     * 允许窗口最大化
     */
    window_style_allow_maxWindow(0x0020),

    /**
     * 别名：图标
     * 图标
     */
    window_style_icon(0x0040),

    /**
     * 别名：标题
     * 标题
     */
    window_style_title(0x0080),

    /**
     * 别名：控制按钮最小化
     * 控制按钮-最小化
     */
    window_style_btn_min(0x0100),

    /**
     * 别名：控制按钮最大化
     * 控制按钮-最大化
     */
    window_style_btn_max(0x0200),

    /**
     * 别名：控制按钮关闭
     * 控制按钮-关闭
     */
    window_style_btn_close(0x0400),

    /**
     * 别名：默认
     * 窗口样式-控制按钮: 居中 图标, 标题, 关闭按钮, 最大化按钮, 最小化按钮
     */
    window_style_default(
            window_style_caption.getValue() | window_style_border.getValue() | window_style_center.getValue() |
                    window_style_drag_border.getValue() | window_style_allow_maxWindow.getValue() | window_style_icon.getValue() |
                    window_style_title.getValue() | window_style_btn_min.getValue() | window_style_btn_max.getValue() | window_style_btn_close.getValue()
    ),

    /**
     * 别名：简单
     * 窗口样式-简单: 居中
     */
    window_style_simple(
            window_style_caption.getValue() | window_style_border.getValue() | window_style_center.getValue() |
                    window_style_drag_border.getValue() | window_style_allow_maxWindow.getValue()
    ),

    /**
     * 别名：弹出
     * 窗口样式-弹出窗口: 图标, 标题, 关闭按钮
     */
    window_style_pop(
            window_style_caption.getValue() | window_style_border.getValue() | window_style_center.getValue() |
                    window_style_drag_border.getValue() | window_style_allow_maxWindow.getValue() | window_style_icon.getValue() |
                    window_style_title.getValue() | window_style_btn_close.getValue()
    ),

    /**
     * 别名：模态
     * 模态窗口样式-控制按钮: 居中, 图标, 标题, 关闭按钮
     */
    window_style_modal(
            window_style_caption.getValue() | window_style_border.getValue() | window_style_center.getValue() |
                    window_style_icon.getValue() | window_style_title.getValue() | window_style_btn_close.getValue()
    ),

    /**
     * 别名：模态简单
     * 模态窗口样式-简单: 居中
     */
    window_style_modal_simple(
            window_style_caption.getValue() | window_style_border.getValue() | window_style_center.getValue()
    );

    private final int value;

    WindowStyle(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static WindowStyle fromValue(int value) {
        for (WindowStyle style : values()) {
            if (style.value == value) {
                return style;
            }
        }
        return null;
    }
}
