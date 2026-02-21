package com.scm.all.enumBox;

/**
 * 列表视图状态枚举（对应 C++ 枚举 listView_state_flag_）
 * group_listView_state_flag_ 列表视图状态(listView_state_flag_)
 * 注：枚举值为独立位标志，支持项/组相关状态组合与判断，与前序（列表、列表头、按钮等）枚举用法完全统一
 */
public enum ListViewStateFlag {
    /**
     * 别名：项鼠标离开
     * 鼠标离开列表视图的某一项区域
     */
    LIST_VIEW_STATE_FLAG_ITEM_LEAVE(0x0080),

    /**
     * 别名：项鼠标停留
     * 鼠标停留于列表视图的某一项区域
     */
    LIST_VIEW_STATE_FLAG_ITEM_STAY(0x0100),

    /**
     * 别名：项选择
     * 列表视图的某一项处于选中状态
     */
    LIST_VIEW_STATE_FLAG_ITEM_SELECT(0x0200),

    /**
     * 别名：项未选择
     * 列表视图的某一项处于未选中状态
     */
    LIST_VIEW_STATE_FLAG_ITEM_SELECT_NO(0x0400),

    /**
     * 别名：组鼠标离开
     * 鼠标离开列表视图的某一组区域
     */
    LIST_VIEW_STATE_FLAG_GROUP_LEAVE(0x0800),

    /**
     * 别名：组鼠标停留
     * 鼠标停留于列表视图的某一组区域
     */
    LIST_VIEW_STATE_FLAG_GROUP_STAY(0x1000),

    /**
     * 别名：组选择
     * 列表视图的某一组处于选中状态
     */
    LIST_VIEW_STATE_FLAG_GROUP_SELECT(0x2000),

    /**
     * 别名：组未选择
     * 列表视图的某一组处于未选中状态
     */
    LIST_VIEW_STATE_FLAG_GROUP_SELECT_NO(0x4000);

    // 列表视图状态对应的位标志原始值（与 C++ 枚举完全一致，无任何修改）
    private final int value;

    /**
     * 构造函数：绑定枚举与对应的位标志值
     * @param value C++ 枚举对应的十六进制值
     */
    ListViewStateFlag(int value) {
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
     * 组合当前状态与目标列表视图状态（按位或运算）
     * 例：项停留 + 项选中 → LIST_VIEW_STATE_FLAG_ITEM_STAY.or(LIST_VIEW_STATE_FLAG_ITEM_SELECT)
     * 例：组停留 + 组选中 → LIST_VIEW_STATE_FLAG_GROUP_STAY.or(LIST_VIEW_STATE_FLAG_GROUP_SELECT)
     * @param other 要组合的列表视图状态
     * @return 组合后的状态值（int 类型，可直接传入 WINAPI）
     */
    public int or(ListViewStateFlag other) {
        return this.value | other.value;
    }

    /**
     * 组合多个列表视图状态（支持可变参数，适配多状态组合场景）
     * @param flags 要组合的多个状态（可混合项/组相关状态）
     * @return 组合后的状态值（int 类型）
     */
    public static int combine(ListViewStateFlag... flags) {
        int combined = 0;
        for (ListViewStateFlag flag : flags) {
            combined |= flag.value;
        }
        return combined;
    }

    /**
     * 判断目标状态值中是否包含当前列表视图状态（按位与运算）
     * @param state 要判断的状态值（通常是组合后的 int 值）
     * @return 是否包含当前状态（true=包含，false=不包含）
     */
    public boolean has(int state) {
        return (state & this.value) != 0;
    }
}
