package com.scm.all.enumBox;

/**
 * 列表头状态枚举（对应 C++ 枚举 listHeader_state_flag_）
 * group_listHeader_state_flag_ 列表头状态(listHeader_state_flag_)
 * 注：枚举值为独立位标志，支持状态组合与判断，与前序（列表、列表框、按钮等）枚举用法完全统一
 */
public enum ListHeaderStateFlag {
    /**
     * 别名：项鼠标离开
     * 鼠标离开列表头的某一项区域
     */
    LIST_HEADER_STATE_FLAG_ITEM_LEAVE(0x0080),

    /**
     * 别名：项鼠标停留
     * 鼠标停留于列表头的某一项区域
     */
    LIST_HEADER_STATE_FLAG_ITEM_STAY(0x0100),

    /**
     * 别名：项鼠标按下
     * 鼠标在列表头的某一项上按下（未释放）
     */
    LIST_HEADER_STATE_FLAG_ITEM_DOWN(0x0200);

    // 列表头状态对应的位标志原始值（与 C++ 枚举完全一致，无任何修改）
    private final int value;

    /**
     * 构造函数：绑定枚举与对应的位标志值
     * @param value C++ 枚举对应的十六进制值
     */
    ListHeaderStateFlag(int value) {
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
     * 组合当前状态与目标列表头状态（按位或运算）
     * 例：LIST_HEADER_STATE_FLAG_ITEM_STAY.or(LIST_HEADER_STATE_FLAG_ITEM_DOWN) → 项停留且按下
     * @param other 要组合的列表头状态
     * @return 组合后的状态值（int 类型，可直接传入 WINAPI）
     */
    public int or(ListHeaderStateFlag other) {
        return this.value | other.value;
    }

    /**
     * 组合多个列表头状态（支持可变参数，适配多状态组合场景）
     * @param flags 要组合的多个状态
     * @return 组合后的状态值（int 类型）
     */
    public static int combine(ListHeaderStateFlag... flags) {
        int combined = 0;
        for (ListHeaderStateFlag flag : flags) {
            combined |= flag.value;
        }
        return combined;
    }

    /**
     * 判断目标状态值中是否包含当前列表头状态（按位与运算）
     * @param state 要判断的状态值（通常是组合后的 int 值）
     * @return 是否包含当前状态（true=包含，false=不包含）
     */
    public boolean has(int state) {
        return (state & this.value) != 0;
    }
}
