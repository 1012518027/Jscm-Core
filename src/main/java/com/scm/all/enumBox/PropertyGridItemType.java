package com.scm.all.enumBox;

/**
 * 属性网格项类型枚举（对应 C++ 枚举 propertyGrid_item_type_）
 * GroupPropertyGrid_item_type 属性网格项类型(propertyGrid_item_type_)
 * group_enum
 */
public enum PropertyGridItemType {
    /**
     * 别名：文本
     * 默认,字符串类型
     */
    propertyGrid_item_type_text(0),

    /**
     * 别名：编辑框
     * 编辑框
     */
    propertyGrid_item_type_edit(1),

    /**
     * 别名：颜色选择
     * 颜色选择元素
     */
    propertyGrid_item_type_edit_color(2),

    /**
     * 别名：文件选择
     * 文件选择编辑框
     */
    propertyGrid_item_type_edit_file(3),

    /**
     * 别名：设置
     * 设置
     */
    propertyGrid_item_type_edit_set(4),

    /**
     * 别名：组合框
     * 组合框
     */
    propertyGrid_item_type_comboBox(5),

    /**
     * 别名：组
     * 组
     */
    propertyGrid_item_type_group(6),

    /**
     * 别名：面板
     * 面板
     */
    propertyGrid_item_type_panel(7);

    private final int value;

    PropertyGridItemType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PropertyGridItemType fromValue(int value) {
        for (PropertyGridItemType type : values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }
}
