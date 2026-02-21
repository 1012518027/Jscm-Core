package com.scm.all.enumBox;

/**
 * 字体样式枚举（对应 C++ 枚举 fontStyle_）
 * group_fontStyle_ 字体样式(fontStyle_)
 * group_enum
 */
public enum FontStyle {
    /**
     * 别名：正常
     * 正常
     */
    fontStyle_regular(0),

    /**
     * 别名：粗体
     * 粗体
     */
    fontStyle_bold(1),

    /**
     * 别名：斜体
     * 斜体
     */
    fontStyle_italic(2),

    /**
     * 别名：粗斜体
     * 粗斜体
     */
    fontStyle_boldItalic(3),

    /**
     * 别名：下划线
     * 下划线
     */
    fontStyle_underline(4),

    /**
     * 别名：删除线
     * 删除线
     */
    fontStyle_strikeout(8);

    private final int value;

    FontStyle(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static FontStyle fromValue(int value) {
        for (FontStyle style : values()) {
            if (style.value == value) {
                return style;
            }
        }
        return null;
    }
}
