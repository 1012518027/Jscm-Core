package com.scm.all.enumBox;

/**
 * 属性网格状态枚举（对应 C++ 枚举 propertyGrid_state_flag_）
 * group_propertyGrid_state_flag_ 属性网格状态(propertyGrid_state_flag_)
 * 注：枚举值为独立位标志，支持项/组相关状态组合与判断，与前序（列表、月历、按钮等）枚举用法完全统一
 */
public enum PropertyGridStateFlag {
    /**
     * 别名：项鼠标离开
     * 鼠标离开属性网格的某一项区域
     */
    PROPERTY_GRID_STATE_FLAG_ITEM_LEAVE(0x0080),

    /**
     * 别名：项鼠标停留
     * 鼠标停留于属性网格的某一项区域
     */
    PROPERTY_GRID_STATE_FLAG_ITEM_STAY(0x0100),

    /**
     * 别名：项选择
     * 属性网格的某一项处于选中状态
     */
    PROPERTY_GRID_STATE_FLAG_ITEM_SELECT(0x0200),

    /**
     * 别名：项未选择
     * 属性网格的某一项处于未选中状态
     */
    PROPERTY_GRID_STATE_FLAG_ITEM_SELECT_NO(0x0400),

    /**
     * 别名：组鼠标离开
     * 鼠标离开属性网格的某一组区域
     */
    PROPERTY_GRID_STATE_FLAG_GROUP_LEAVE(0x0800),

    /**
     * 别名：组展开
     * 属性网格的某一组处于展开状态（显示子项）
     */
    PROPERTY_GRID_STATE_FLAG_GROUP_EXPAND(0x1000),

    /**
     * 别名：组未展开
     * 属性网格的某一组处于未展开状态（隐藏子项）
     */
    PROPERTY_GRID_STATE_FLAG_GROUP_EXPAND_NO(0x2000);

    // 属性网格状态对应的位标志原始值（与 C++ 枚举完全一致，无任何修改）
    private final int value;

    /**
     * 构造函数：绑定枚举与对应的位标志值
     * @param value C++ 枚举对应的十六进制值
     */
    PropertyGridStateFlag(int value) {
        this.value = value;
    }

    /**
     * 获取枚举对应的原始数值（用于调用 WINAPI 时传入）
     * @return 位标志的 int 类型值
     */
    public int getValue() {
        return value;
    }

    // ------------------- 位运算辅助方法（与前序枚举用法完全统一，降低使用成本）-------------------
    /**
     * 组合当前状态与目标属性网格状态（按位或运算）
     * 例：项停留 + 项选中 → PROPERTY_GRID_STATE_FLAG_ITEM_STAY.or(PROPERTY_GRID_STATE_FLAG_ITEM_SELECT)
     * 例：组展开 + 组鼠标离开 → PROPERTY_GRID_STATE_FLAG_GROUP_EXPAND.or(PROPERTY_GRID_STATE_FLAG_GROUP_LEAVE)
     * @param other 要组合的属性网格状态
     * @return 组合后的状态值（int 类型，可直接传入 WINAPI）
     */
    public int or(PropertyGridStateFlag other) {
        return this.value | other.value;
    }

    /**
     * 组合多个属性网格状态（支持可变参数，适配多状态组合场景）
     * @param flags 要组合的多个状态（可混合项/组相关状态）
     * @return 组合后的状态值（int 类型）
     */
    public static int combine(PropertyGridStateFlag... flags) {
        int combined = 0;
        for (PropertyGridStateFlag flag : flags) {
            combined |= flag.value;
        }
        return combined;
    }

    /**
     * 判断目标状态值中是否包含当前属性网格状态（按位与运算）
     * @param state 要判断的状态值（通常是组合后的 int 值）
     * @return 是否包含当前状态（true=包含，false=不包含）
     */
    public boolean has(int state) {
        return (state & this.value) != 0;
    }
}
