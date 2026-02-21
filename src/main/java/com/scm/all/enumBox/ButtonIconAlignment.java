package com.scm.all.enumBox;

/**
 * 按钮图标对齐方式枚举，对应C语言中的button_icon_align_枚举
 * 定义按钮中图标的四种对齐方向
 */
public enum ButtonIconAlignment {
    /**
     * 图标在左边（对应C枚举的button_icon_align_left）
     */
    LEFT(0),

    /**
     * 图标在顶部（对应C枚举的button_icon_align_top）
     */
    TOP(1),

    /**
     * 图标在右边（对应C枚举的button_icon_align_right）
     */
    RIGHT(2),

    /**
     * 图标在底部（对应C枚举的button_icon_align_bottom）
     */
    BOTTOM(3);

    /**
     * 枚举值对应的整数（与C枚举保持一致）
     */
    private final int value;

    /**
     * 构造方法，初始化枚举值
     * @param value 对应的整数
     */
    ButtonIconAlignment(int value) {
        this.value = value;
    }

    /**
     * 获取枚举对应的整数值
     * @return 对应的整数
     */
    public int getValue() {
        return value;
    }

    /**
     * 根据整数值获取对应的枚举实例
     * @param value 枚举对应的整数
     * @return 对应的枚举实例，未匹配则返回null
     */
    public static ButtonIconAlignment fromValue(int value) {
        for (ButtonIconAlignment alignment : ButtonIconAlignment.values()) {
            if (alignment.value == value) {
                return alignment;
            }
        }
        return null;
    }
}
