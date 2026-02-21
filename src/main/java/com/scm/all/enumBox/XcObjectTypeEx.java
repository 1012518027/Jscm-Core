package com.scm.all.enumBox;

/**
 * 炫彩对象扩展类型枚举，对应C语言中的XC_OBJECT_TYPE_EX枚举
 * 定义了各种扩展对象类型，包括按钮类型和布局元素类型
 */
public enum XcObjectTypeEx {
    /**
     * 错误类型（对应C枚举的xc_ex_error）
     */
    XC_EX_ERROR(-1),

    /**
     * 默认按钮类型（对应C枚举的button_type_default）
     */
    BUTTON_TYPE_DEFAULT(0),

    /**
     * 单选按钮类型（对应C枚举的button_type_radio）
     */
    BUTTON_TYPE_RADIO(1),

    /**
     * 多选按钮类型（对应C枚举的button_type_check）
     */
    BUTTON_TYPE_CHECK(2),

    /**
     * 窗口关闭按钮类型（对应C枚举的button_type_close）
     */
    BUTTON_TYPE_CLOSE(3),

    /**
     * 窗口最小化按钮类型（对应C枚举的button_type_min）
     */
    BUTTON_TYPE_MIN(4),

    /**
     * 窗口最大化还原按钮类型（对应C枚举的button_type_max）
     */
    BUTTON_TYPE_MAX(5),

    /**
     * 布局元素类型（对应C枚举的element_type_layout）
     */
    ELEMENT_TYPE_LAYOUT(6);

    /**
     * 枚举值对应的整数（与C枚举保持一致）
     */
    private final int value;

    /**
     * 构造方法，初始化枚举值对应的整数
     * @param value 对应的整数
     */
    XcObjectTypeEx(int value) {
        this.value = value;
    }

    /**
     * 获取枚举值对应的整数
     * @return 对应的整数
     */
    public int getValue() {
        return value;
    }

    /**
     * 根据整数获取对应的枚举实例
     * @param value 枚举对应的整数
     * @return 对应的枚举实例，未匹配则返回null
     */
    public static XcObjectTypeEx fromValue(int value) {
        for (XcObjectTypeEx type : XcObjectTypeEx.values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }
}
