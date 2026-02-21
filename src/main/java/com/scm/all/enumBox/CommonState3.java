package com.scm.all.enumBox;

/**
 * 普通三种状态枚举，对应C语言中的common_state3_枚举
 * 定义了通用的三种交互状态：离开、停留、按下
 */
public enum CommonState3 {
    /**
     * 离开状态（对应C枚举的common_state3_leave）
     */
    COMMON_STATE3_LEAVE(0),

    /**
     * 停留状态（对应C枚举的common_state3_stay）
     */
    COMMON_STATE3_STAY(1),

    /**
     * 按下状态（对应C枚举的common_state3_down）
     */
    COMMON_STATE3_DOWN(2);

    /**
     * 枚举值对应的整数（与C枚举保持一致）
     */
    private final int value;

    /**
     * 构造方法，初始化枚举值对应的整数
     * @param value 对应的整数
     */
    CommonState3(int value) {
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
    public static CommonState3 fromValue(int value) {
        for (CommonState3 state : CommonState3.values()) {
            if (state.value == value) {
                return state;
            }
        }
        return null;
    }
}
