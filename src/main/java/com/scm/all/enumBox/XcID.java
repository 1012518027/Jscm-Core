package com.scm.all.enumBox;

/**
 * 特殊ID
 */
public enum XcID {
    /**
     *  根节点
     */
    XC_ID_ROOT(0),
    /**
     * ID错误
     */
    XC_ID_ERROR(-1),   ///
    /**
     * 插入开始位置(当前层)
     */
    XC_ID_FIRST(-2),   ///
    /**
     *  插入末尾位置(当前层)
     */
    XC_ID_LAST(-3);   ///
    // 枚举值对应的整数
    private final int value;

    /**
     * 带参数的构造函数
     * @param value 枚举对应的整数值
     */
    XcID(int value) {
        this.value = value;
    }

    /**
     * 默认构造函数，自动分配后续整数值
     */
    XcID() {
        // 自动为后续枚举值分配递增的整数（从1开始）
        this.value = ordinal();
    }

    /**
     * 获取枚举对应的整数值
     * @return 枚举值对应的整数
     */
    public int getValue() {
        return value;
    }
}
