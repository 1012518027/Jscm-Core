package com.scm.all.enumBox;

/**
 * 布局大小类型枚举，对应C语言中的layout_size_枚举
 * 定义布局中元素的大小计算方式
 */
public enum LayoutSize {
    /**
     * 固定大小（对应C枚举的layout_size_fixed）
     */
    LAYOUT_SIZE_FIXED(0),

    /**
     * 填充父容器（对应C枚举的layout_size_fill）
     */
    LAYOUT_SIZE_FILL(1),

    /**
     * 自动大小（根据内容计算，对应C枚举的layout_size_auto）
     */
    LAYOUT_SIZE_AUTO(2),

    /**
     * 比例分配（按比例分配剩余空间，对应C枚举的layout_size_weight）
     */
    LAYOUT_SIZE_WEIGHT(3),

    /**
     * 百分比（对应C枚举的layout_size_percent）
     */
    LAYOUT_SIZE_PERCENT(4),

    /**
     * 禁用（不使用布局大小设置，对应C枚举的layout_size_disable）
     */
    LAYOUT_SIZE_DISABLE(5);

    /**
     * 枚举值对应的整数（与C枚举保持一致）
     */
    private final int value;

    /**
     * 构造方法，初始化枚举值对应的整数
     * @param value 对应的整数
     */
    LayoutSize(int value) {
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
    public static LayoutSize fromValue(int value) {
        for (LayoutSize size : LayoutSize.values()) {
            if (size.value == value) {
                return size;
            }
        }
        return null;
    }
}
