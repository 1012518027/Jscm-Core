package com.scm.all.enumBox;

/**
 * 数据适配器数据类型枚举（对应 C++ 枚举 adapter_date_type_）
 * group_adapter_date_type_ 数据适配器数据类型(adapter_date_type_)
 * group_enum
 */
public enum AdapterDateType {
    /**
     * 别名：错误
     * 错误
     */
    adapter_date_type_error(-1),

    /**
     * 别名：整型
     * 整型
     */
    adapter_date_type_int(0),

    /**
     * 别名：浮点型
     * 浮点型
     */
    adapter_date_type_float(1),

    /**
     * 别名：字符串
     * 字符串
     */
    adapter_date_type_string(2),

    /**
     * 别名：图片
     * 图片
     */
    adapter_date_type_image(3);

    private final int value;

    AdapterDateType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static AdapterDateType fromValue(int value) {
        for (AdapterDateType type : values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }
}
