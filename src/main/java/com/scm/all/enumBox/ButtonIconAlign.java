package com.scm.all.enumBox;

/**
 * 按钮图标对齐方式枚举（对应 C++ 枚举 button_icon_align_）
 * groupButtonIconAlign 按钮图标对齐方式(button_icon_align_)
 * group_enum
 */
public enum ButtonIconAlign {
    /**
     * 别名：左边
     * 图标在左边
     */
    button_icon_align_left(0),

    /**
     * 别名：顶部
     * 图标在顶部
     */
    button_icon_align_top(1),

    /**
     * 别名：右边
     * 图标在右边
     */
    button_icon_align_right(2),

    /**
     * 别名：底部
     * 图标在底部
     */
    button_icon_align_bottom(3);

    private final int value;

    ButtonIconAlign(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ButtonIconAlign fromValue(int value) {
        for (ButtonIconAlign align : values()) {
            if (align.value == value) {
                return align;
            }
        }
        return null;
    }
}
