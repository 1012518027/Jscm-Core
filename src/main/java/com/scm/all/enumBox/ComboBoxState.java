package com.scm.all.enumBox;

/**
 * 组合框状态枚举，对应C语言中的comboBox_state_枚举
 * 定义组合框可能的状态：鼠标离开、鼠标停留、按下
 */
public enum ComboBoxState {
    /**
     * 鼠标离开状态（对应C枚举的comboBox_state_leave）
     */
    COMBO_BOX_STATE_LEAVE(0),

    /**
     * 鼠标停留状态（对应C枚举的comboBox_state_stay）
     */
    COMBO_BOX_STATE_STAY(1),

    /**
     * 按下状态（对应C枚举的comboBox_state_down）
     */
    COMBO_BOX_STATE_DOWN(2);

    /**
     * 枚举值对应的整数（与C枚举的数值保持一致）
     */
    private final int value;

    /**
     * 构造方法，初始化枚举值对应的整数
     * @param value 枚举值对应的整数
     */
    ComboBoxState(int value) {
        this.value = value;
    }

    /**
     * 获取枚举值对应的整数（用于JNI交互或状态判断）
     * @return 枚举值对应的整数
     */
    public int getValue() {
        return value;
    }

    /**
     * 根据整数获取对应的枚举实例（用于解析C层返回的状态值）
     * @param value 枚举值对应的整数
     * @return 对应的枚举实例，若未匹配则返回null
     */
    public static ComboBoxState fromValue(int value) {
        for (ComboBoxState state : ComboBoxState.values()) {
            if (state.value == value) {
                return state;
            }
        }
        return null;
    }
}
