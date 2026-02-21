package com.scm.all.enumBox;

/**
 * 组合框状态标识枚举（对应 C++ 枚举 comboBox_state_flag_）
 * group_comboBox_state_flag_ 组合框状态标识(comboBox_state_flag_)
 */
public enum ComboBoxStateFlag {
    /**
     * 别名：鼠标离开
     * 鼠标离开组合框区域（复用元素状态的“鼠标离开”常量）
     */
    COMBO_BOX_STATE_FLAG_LEAVE(ElementStateFlag.ELEMENT_STATE_FLAG_LEAVE.getValue()),

    /**
     * 别名：鼠标停留
     * 鼠标停留于组合框区域（复用元素状态的“鼠标停留”常量）
     */
    COMBO_BOX_STATE_FLAG_STAY(ElementStateFlag.ELEMENT_STATE_FLAG_STAY.getValue()),

    /**
     * 别名：鼠标按下
     * 鼠标在组合框上按下（未释放，复用元素状态的“鼠标按下”常量）
     */
    COMBO_BOX_STATE_FLAG_DOWN(ElementStateFlag.ELEMENT_STATE_FLAG_DOWN.getValue());

    // 组合框状态对应的位标志原始值（与 C++ 枚举完全一致，复用 ElementStateFlag 确保数值统一）
    private final int value;

    /**
     * 构造函数：绑定枚举与对应的位标志值
     * @param value C++ 枚举对应的数值（复用 ElementStateFlag 的常量值）
     */
    ComboBoxStateFlag(int value) {
        this.value = value;
    }

    /**
     * 获取枚举对应的原始数值（用于调用 WINAPI 时传入）
     * @return 位标志的 int 类型值
     */
    public int getValue() {
        return value;
    }

    // ------------------- 位运算辅助方法（与前序枚举用法完全统一）-------------------
    /**
     * 组合当前状态与目标组合框状态（按位或运算）
     * 例：COMBO_BOX_STATE_FLAG_STAY.or(COMBO_BOX_STATE_FLAG_DOWN) → 鼠标停留且按下
     * @param other 要组合的组合框状态
     * @return 组合后的状态值（int 类型，可直接传入 WINAPI）
     */
    public int or(ComboBoxStateFlag other) {
        return this.value | other.value;
    }

    /**
     * 组合多个组合框状态（支持可变参数）
     * @param flags 要组合的多个状态
     * @return 组合后的状态值（int 类型）
     */
    public static int combine(ComboBoxStateFlag... flags) {
        int combined = 0;
        for (ComboBoxStateFlag flag : flags) {
            combined |= flag.value;
        }
        return combined;
    }

    /**
     * 判断目标状态值中是否包含当前组合框状态（按位与运算）
     * @param state 要判断的状态值（通常是组合后的 int 值）
     * @return 是否包含当前状态（true=包含，false=不包含）
     */
    public boolean has(int state) {
        return (state & this.value) != 0;
    }

    /**
     * 扩展：组合当前组合框状态与元素状态（如组合框状态+启用/禁用）
     * 例：COMBO_BOX_STATE_FLAG_DOWN.or(ElementStateFlag.ELEMENT_STATE_FLAG_ENABLE)
     * @param elementState 元素状态枚举
     * @return 组合后的状态值（int 类型）
     */
    public int or(ElementStateFlag elementState) {
        return this.value | elementState.getValue();
    }
}
