package com.scm.all.enumBox;

/**
 * 按钮状态标识枚举（对应 C++ 枚举 button_state_flag_）
 * group_button_state_flag_ 按钮状态标识(button_state_flag_)

 */
public enum ButtonStateFlag {
    /**
     * 别名：鼠标离开
     * 鼠标离开按钮区域（复用元素状态的“鼠标离开”常量）
     */
    BUTTON_STATE_FLAG_LEAVE(ElementStateFlag.ELEMENT_STATE_FLAG_LEAVE.getValue()),

    /**
     * 别名：鼠标停留
     * 鼠标停留于按钮区域（复用元素状态的“鼠标停留”常量）
     */
    BUTTON_STATE_FLAG_STAY(ElementStateFlag.ELEMENT_STATE_FLAG_STAY.getValue()),

    /**
     * 别名：鼠标按下
     * 鼠标在按钮上按下（未释放，复用元素状态的“鼠标按下”常量）
     */
    BUTTON_STATE_FLAG_DOWN(ElementStateFlag.ELEMENT_STATE_FLAG_DOWN.getValue()),

    /**
     * 别名：选中
     * 按钮处于选中状态
     */
    BUTTON_STATE_FLAG_CHECK(0x0080),

    /**
     * 别名：未选中
     * 按钮处于未选中状态
     */
    BUTTON_STATE_FLAG_CHECK_NO(0x0100),

    /**
     * 别名：窗口还原
     * 按钮功能为窗口还原（如窗口控制按钮中的“还原”按钮状态）
     */
    BUTTON_STATE_FLAG_WINDOW_RESTORE(0x0200), // 原命名补充下划线，符合Java规范

    /**
     * 别名：窗口最大化
     * 按钮功能为窗口最大化（如窗口控制按钮中的“最大化”按钮状态）
     */
    BUTTON_STATE_FLAG_WINDOW_MAXIMIZE(0x0400); // 原命名补充下划线，符合Java规范

    // 按钮状态对应的位标志原始值（与C++枚举完全一致）
    private final int value;

    /**
     * 构造函数：绑定枚举与对应的位标志值
     * @param value C++枚举对应的十六进制值（或复用ElementStateFlag的常量值）
     */
    ButtonStateFlag(int value) {
        this.value = value;
    }

    /**
     * 获取枚举对应的原始数值（用于调用WINAPI时传入）
     * @return 位标志的int类型值
     */
    public int getValue() {
        return value;
    }

    // ------------------- 位运算辅助方法（与之前枚举用法统一）-------------------
    /**
     * 组合当前状态与目标状态（按位或运算）
     * 例：BUTTON_STATE_FLAG_STAY.or(BUTTON_STATE_FLAG_CHECK) → 鼠标停留且选中
     * @param other 要组合的按钮状态
     * @return 组合后的状态值（int类型，可直接传入WINAPI）
     */
    public int or(ButtonStateFlag other) {
        return this.value | other.value;
    }

    /**
     * 组合多个按钮状态（支持可变参数）
     * 例：ButtonStateFlag.combine(BUTTON_STATE_FLAG_DOWN, BUTTON_STATE_FLAG_CHECK) → 按下且选中
     * @param flags 要组合的多个状态
     * @return 组合后的状态值（int类型）
     */
    public static int combine(ButtonStateFlag... flags) {
        int combined = 0;
        for (ButtonStateFlag flag : flags) {
            combined |= flag.value;
        }
        return combined;
    }

    /**
     * 判断目标状态值中是否包含当前状态（按位与运算）
     * 例：int state = ButtonStateFlag.combine(...) → BUTTON_STATE_FLAG_CHECK.has(state)
     * @param state 要判断的状态值（通常是组合后的int值）
     * @return 是否包含当前状态（true=包含，false=不包含）
     */
    public boolean has(int state) {
        return (state & this.value) != 0;
    }

    // ------------------- 扩展：跨枚举组合（支持与元素状态组合，可选）-------------------
    /**
     * 组合当前按钮状态与元素状态（如按钮状态+启用/禁用状态）
     * 例：BUTTON_STATE_FLAG_CHECK.or(ElementStateFlag.ELEMENT_STATE_FLAG_ENABLE)
     * @param elementState 元素状态枚举
     * @return 组合后的状态值（int类型）
     */
    public int or(ElementStateFlag elementState) {
        return this.value | elementState.getValue();
    }
}
