package com.scm.all.enumBox;


/**
 * 窗格状态标识枚举（对应 C++ 枚举 pane_state_flag_）
 * group_pane_state_flag_ 窗格状态标识(pane_state_flag_)
 * 注：枚举值为独立位标志，支持鼠标交互状态与区域标识组合，复用 {@link ElementStateFlag} 常量确保一致性
 */
public enum PaneStateFlag {
    /**
     * 别名：鼠标离开
     * 鼠标离开窗格整体区域（复用元素状态的“鼠标离开”常量）
     */
    PANE_STATE_FLAG_LEAVE(ElementStateFlag.ELEMENT_STATE_FLAG_LEAVE.getValue()),

    /**
     * 别名：鼠标停留
     * 鼠标停留于窗格整体区域（复用元素状态的“鼠标停留”常量）
     */
    PANE_STATE_FLAG_STAY(ElementStateFlag.ELEMENT_STATE_FLAG_STAY.getValue()),

    /**
     * 别名：标题栏
     * 窗格的标题区域（标识窗格的标题栏部分）
     */
    PANE_STATE_FLAG_CAPTION(0x0080),

    /**
     * 别名：内容区
     * 窗格的内容区域（标识窗格的主体内容部分）
     */
    PANE_STATE_FLAG_BODY(0x0100);

    // 窗格状态对应的位标志原始值（与 C++ 枚举完全一致）
    private final int value;

    /**
     * 构造函数：绑定枚举与对应的位标志值
     * @param value C++ 枚举对应的数值（或复用 ElementStateFlag 的常量值）
     */
    PaneStateFlag(int value) {
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
     * 组合当前状态与目标窗格状态（按位或运算）
     * 例：鼠标停留 + 标题栏 → PANE_STATE_FLAG_STAY.or(PANE_STATE_FLAG_CAPTION)
     * @param other 要组合的窗格状态
     * @return 组合后的状态值（int 类型，可直接传入 WINAPI）
     */
    public int or(PaneStateFlag other) {
        return this.value | other.value;
    }

    /**
     * 组合多个窗格状态（支持可变参数，适配多状态组合场景）
     * @param flags 要组合的多个状态（可混合鼠标状态与区域标识）
     * @return 组合后的状态值（int 类型）
     */
    public static int combine(PaneStateFlag... flags) {
        int combined = 0;
        for (PaneStateFlag flag : flags) {
            combined |= flag.value;
        }
        return combined;
    }

    /**
     * 判断目标状态值中是否包含当前窗格状态（按位与运算）
     * @param state 要判断的状态值（通常是组合后的 int 值）
     * @return 是否包含当前状态（true=包含，false=不包含）
     */
    public boolean has(int state) {
        return (state & this.value) != 0;
    }

    /**
     * 扩展：组合当前窗格状态与元素状态（如标题栏 + 元素启用）
     * @param elementState 元素状态枚举
     * @return 组合后的状态值（int 类型）
     */
    public int or(ElementStateFlag elementState) {
        return this.value | elementState.getValue();
    }
}
