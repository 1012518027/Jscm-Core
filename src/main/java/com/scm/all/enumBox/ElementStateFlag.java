package com.scm.all.enumBox;

/**
 * 元素状态枚举（对应 C 语言 element_state_flag_）
 * group_element_state_flag_ 元素状态(element_state_flag_)
 * 注：枚举值为位标志，支持通过 {@link #or(ElementStateFlag)} 组合状态，{@link #has(int)} 判断状态
 */
public enum ElementStateFlag {
    /**
     * 别名：无
     * 无特殊状态（复用窗口状态的“无”常量）
     */
    ELEMENT_STATE_FLAG_NOTHING(WindowStateFlag.WINDOW_STATE_FLAG_NOTHING.getValue()),

    /**
     * 别名：启用
     * 元素处于启用状态
     */
    ELEMENT_STATE_FLAG_ENABLE(0x0001),

    /**
     * 别名：禁用
     * 元素处于禁用状态
     */
    ELEMENT_STATE_FLAG_DISABLE(0x0002),

    /**
     * 别名：焦点
     * 元素拥有焦点
     */
    ELEMENT_STATE_FLAG_FOCUS(0x0004),

    /**
     * 别名：无焦点
     * 元素无焦点
     */
    ELEMENT_STATE_FLAG_FOCUS_NO(0x0008),

    /**
     * 别名：焦点扩展
     * 该元素或该元素的子元素拥有焦点
     */
    ELEMENT_STATE_FLAG_FOCUS_EX(0x40000000),

    /**
     * 别名：无焦点扩展
     * 无焦点Ex（无扩展焦点）
     */
    ELEMENT_STATE_FLAG_FOCUS_EX_NO(0x80000000),

    /**
     * 别名：布局内容区
     * 布局对应的内容区域（复用窗口状态的“布局内容区”常量）
     */
    LAYOUT_STATE_FLAG_LAYOUT_BODY(WindowStateFlag.WINDOW_STATE_FLAG_LAYOUT_BODY.getValue()),

    /**
     * 别名：鼠标离开
     * 鼠标离开元素区域
     */
    ELEMENT_STATE_FLAG_LEAVE(0x0010),

    /**
     * 别名：鼠标停留
     * 鼠标停留于元素区域
     */
    ELEMENT_STATE_FLAG_STAY(0x0020),

    /**
     * 别名：鼠标按下
     * 鼠标在元素上按下（未释放）
     */
    ELEMENT_STATE_FLAG_DOWN(0x0040);

    // 枚举对应的位标志原始值（与 C 语言完全一致，交叉引用通过 WindowStateFlag 确保一致性）
    private final int value;

    /**
     * 构造函数：绑定枚举与对应的位标志值
     * @param value C 语言枚举对应的十六进制值（或复用 WindowStateFlag 的值）
     */
    ElementStateFlag(int value) {
        this.value = value;
    }

    /**
     * 获取枚举对应的原始数值（用于调用 WINAPI 时传入）
     * @return 位标志的 int 类型值
     */
    public int getValue() {
        return value;
    }

    // ------------------- 位运算辅助方法（与 WindowStateFlag 用法一致，降低学习成本）-------------------
    /**
     * 组合当前状态与目标状态（按位或运算）
     * 例：ELEMENT_STATE_FLAG_ENABLE.or(ELEMENT_STATE_FLAG_FOCUS) → 启用且拥有焦点
     * @param other 要组合的状态
     * @return 组合后的状态值（int 类型，可直接传入 WINAPI）
     */
    public int or(ElementStateFlag other) {
        return this.value | other.value;
    }

    /**
     * 组合多个状态（支持可变参数，适合多状态组合场景）
     * 例：ElementStateFlag.combine(ELEMENT_STATE_FLAG_STAY, ELEMENT_STATE_FLAG_DOWN) → 鼠标停留且按下
     * @param flags 要组合的多个状态
     * @return 组合后的状态值（int 类型）
     */
    public static int combine(ElementStateFlag... flags) {
        int combined = 0;
        for (ElementStateFlag flag : flags) {
            combined |= flag.value;
        }
        return combined;
    }

    /**
     * 判断目标状态值中是否包含当前状态（按位与运算）
     * 例：int state = ElementStateFlag.combine(...) → ELEMENT_STATE_FLAG_FOCUS.has(state)
     * @param state 要判断的状态值（通常是组合后的 int 值）
     * @return 是否包含当前状态（true=包含，false=不包含）
     */
    public boolean has(int state) {
        return (state & this.value) != 0;
    }
}
