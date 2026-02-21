package com.scm.all.enumBox;

/**
 * 按钮状态枚举，对应C语言中的button_state_枚举
 * 定义了按钮的各种交互状态
 */
public enum ButtonState {
    /**
     * 离开状态（对应C枚举的button_state_leave）
     */
    BUTTON_STATE_LEAVE(0),

    /**
     * 停留状态（对应C枚举的button_state_stay）
     */
    BUTTON_STATE_STAY(1),

    /**
     * 按下状态（对应C枚举的button_state_down）
     */
    BUTTON_STATE_DOWN(2),

    /**
     * 选中状态（对应C枚举的button_state_check）
     */
    BUTTON_STATE_CHECK(3),

    /**
     * 禁用状态（对应C枚举的button_state_disable）
     */
    BUTTON_STATE_DISABLE(4);

    /**
     * 枚举值对应的整数（与C枚举保持一致）
     */
    private final int value;

    /**
     * 构造方法，初始化枚举值对应的整数
     * @param value 对应的整数
     */
    ButtonState(int value) {
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
    public static ButtonState fromValue(int value) {
        for (ButtonState state : ButtonState.values()) {
            if (state.value == value) {
                return state;
            }
        }
        return null;
    }
}
