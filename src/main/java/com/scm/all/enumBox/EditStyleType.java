package com.scm.all.enumBox;

public enum EditStyleType {
    /**
     * 别名：字体
     * 字体
     */
    edit_style_type_font_color(1),

    /**
     * 别名：图片
     * 图片
     */
    edit_style_type_image(2),

    /**
     * 别名：UI对象
     * UI对象
     */
    edit_style_type_obj(3);

    private final int value;

    EditStyleType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static EditStyleType fromValue(int value) {
        for (EditStyleType type : values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }
}
