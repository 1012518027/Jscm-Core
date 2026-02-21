package com.scm.all.enumBox;

/**
 * 列表树状态枚举（对应 C++ 枚举 tree_state_flag_）
 * group_tree_state_flag_ 列表树状态(tree_state_flag_)
 * 注：枚举值为独立位标志，支持项选择、组类型判断，与前序（列表、列表视图、按钮等）枚举用法完全统一
 */
public enum TreeStateFlag {
    /**
     * 别名：项鼠标离开
     * 鼠标离开列表树的某一项区域
     */
    TREE_STATE_FLAG_ITEM_LEAVE(0x0080),

    /**
     * 别名：项鼠标停留
     * 鼠标停留于列表树的某一项区域（保留值，暂未使用）
     */
    TREE_STATE_FLAG_ITEM_STAY(0x0100),

    /**
     * 别名：项选择
     * 列表树的某一项处于选中状态
     */
    TREE_STATE_FLAG_ITEM_SELECT(0x0200),

    /**
     * 别名：项未选择
     * 列表树的某一项处于未选中状态
     */
    TREE_STATE_FLAG_ITEM_SELECT_NO(0x0400),

    /**
     * 别名：项为组
     * 列表树的某一项是组节点（包含子项的节点）
     */
    TREE_STATE_FLAG_GROUP(0x0800),

    /**
     * 别名：项不为组
     * 列表树的某一项是普通节点（不包含子项）
     */
    TREE_STATE_FLAG_GROUP_NO(0x1000);

    // 列表树状态对应的位标志原始值（与 C++ 枚举完全一致，无任何修改）
    private final int value;

    /**
     * 构造函数：绑定枚举与对应的位标志值
     * @param value C++ 枚举对应的十六进制值
     */
    TreeStateFlag(int value) {
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
     * 组合当前状态与目标列表树状态（按位或运算）
     * 例：项为组 + 项选中 → TREE_STATE_FLAG_GROUP.or(TREE_STATE_FLAG_ITEM_SELECT)
     * @param other 要组合的列表树状态
     * @return 组合后的状态值（int 类型，可直接传入 WINAPI）
     */
    public int or(TreeStateFlag other) {
        return this.value | other.value;
    }

    /**
     * 组合多个列表树状态（支持可变参数，适配多状态组合场景）
     * @param flags 要组合的多个状态（可混合选择状态与组类型状态）
     * @return 组合后的状态值（int 类型）
     */
    public static int combine(TreeStateFlag... flags) {
        int combined = 0;
        for (TreeStateFlag flag : flags) {
            combined |= flag.value;
        }
        return combined;
    }

    /**
     * 判断目标状态值中是否包含当前列表树状态（按位与运算）
     * @param state 要判断的状态值（通常是组合后的 int 值）
     * @return 是否包含当前状态（true=包含，false=不包含）
     */
    public boolean has(int state) {
        return (state & this.value) != 0;
    }
}
