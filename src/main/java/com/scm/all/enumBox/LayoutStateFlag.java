package com.scm.all.enumBox;

/**
 * 布局状态枚举（对应 C++ 枚举 layout_state_flag_）
 * group_layout_state_flag_ 布局状态(layout_state_flag_)
 * 注：枚举值为独立位标志，支持布局背景与区域标识组合，复用 {@link WindowStateFlag} 常量确保一致性
 */
public enum LayoutStateFlag {
    /**
     * 别名：无
     * 无特殊布局状态（复用窗口状态的“无”常量）
     */
    LAYOUT_STATE_FLAG_NOTHING(WindowStateFlag.WINDOW_STATE_FLAG_NOTHING.getValue()),

    /**
     * 别名：完整背景
     * 布局显示完整背景（包含边框区域）
     */
    LAYOUT_STATE_FLAG_FULL(0x0001),

    /**
     * 别名：内容区
     * 布局仅显示内容区域（不包含边大小）
     */
    LAYOUT_STATE_FLAG_BODY(0x0002);

    // 布局状态对应的位标志原始值（与 C++ 枚举完全一致）
    private final int value;

    /**
     * 构造函数：绑定枚举与对应的位标志值
     * @param value C++ 枚举对应的数值（或复用 WindowStateFlag 的常量值）
     */
    LayoutStateFlag(int value) {
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
     * 组合当前状态与目标布局状态（按位或运算）
     * 例：完整背景 + 内容区 → LAYOUT_STATE_FLAG_FULL.or(LAYOUT_STATE_FLAG_BODY)
     * @param other 要组合的布局状态
     * @return 组合后的状态值（int 类型，可直接传入 WINAPI）
     */
    public int or(LayoutStateFlag other) {
        return this.value | other.value;
    }

    /**
     * 组合多个布局状态（支持可变参数，适配多状态组合场景）
     * @param flags 要组合的多个状态
     * @return 组合后的状态值（int 类型）
     */
    public static int combine(LayoutStateFlag... flags) {
        int combined = 0;
        for (LayoutStateFlag flag : flags) {
            combined |= flag.value;
        }
        return combined;
    }

    /**
     * 判断目标状态值中是否包含当前布局状态（按位与运算）
     * @param state 要判断的状态值（通常是组合后的 int 值）
     * @return 是否包含当前状态（true=包含，false=不包含）
     */
    public boolean has(int state) {
        return (state & this.value) != 0;
    }

    /**
     * 扩展：组合当前布局状态与窗口状态（如完整背景 + 窗口置顶）
     * @param windowState 窗口状态枚举
     * @return 组合后的状态值（int 类型）
     */
    public int or(WindowStateFlag windowState) {
        return this.value | windowState.getValue();
    }
}
