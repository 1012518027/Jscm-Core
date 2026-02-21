package com.scm.all.enumBox;

import java.util.Arrays;

/**
 * 窗口状态枚举，对应C语言中的window_state_flag_枚举
 * 定义窗口各区域的状态标识，支持位运算组合使用
 */
public enum WindowStateFlag {
    /**
     * 无状态（对应C枚举的window_state_flag_nothing）
     */
    WINDOW_STATE_FLAG_NOTHING(0x0000),

    /**
     * 整个窗口（对应C枚举的window_state_flag_leave）
     */
    WINDOW_STATE_FLAG_LEAVE(0x0001),

    /**
     * 内容区（对应C枚举的window_state_flag_body_leave）
     */
    WINDOW_STATE_FLAG_BODY_LEAVE(0x0002),

    /**
     * 顶部（对应C枚举的window_state_flag_top_leave）
     */
    WINDOW_STATE_FLAG_TOP_LEAVE(0x0004),

    /**
     * 底部（对应C枚举的window_state_flag_bottom_leave）
     */
    WINDOW_STATE_FLAG_BOTTOM_LEAVE(0x0008),

    /**
     * 左侧（对应C枚举的window_state_flag_left_leave）
     */
    WINDOW_STATE_FLAG_LEFT_LEAVE(0x0010),

    /**
     * 右侧（对应C枚举的window_state_flag_right_leave）
     */
    WINDOW_STATE_FLAG_RIGHT_LEAVE(0x0020),

    /**
     * 布局内容区（对应C枚举的window_state_flag_layout_body）
     */
    WINDOW_STATE_FLAG_LAYOUT_BODY(0x20000000);

    /**
     * 枚举值对应的位标识（与C枚举保持一致）
     */
    private final int value;

    /**
     * 构造方法，初始化枚举值对应的位标识
     * @param value 对应的位标识值
     */
    WindowStateFlag(int value) {
        this.value = value;
    }

    /**
     * 获取枚举值对应的位标识
     * @return 位标识值
     */
    public int getValue() {
        return value;
    }

    /**
     * 检查当前状态是否包含指定的状态
     * @param flag 要检查的状态
     * @return 若包含则返回true，否则返回false
     */
    public boolean hasFlag(WindowStateFlag flag) {
        return (this.value & flag.value) != 0;
    }

    /**
     * 将多个状态组合为一个位标识值
     * @param flags 要组合的状态数组
     * @return 组合后的位标识值
     */
    public static int combine(WindowStateFlag... flags) {
        int combined = 0;
        for (WindowStateFlag flag : flags) {
            combined |= flag.value;
        }
        return combined;
    }

    /**
     * 根据位标识值获取对应的枚举实例集合
     * @param value 位标识值
     * @return 包含的所有枚举实例
     */
    public static WindowStateFlag[] fromValue(int value) {
        return Arrays.stream(WindowStateFlag.values())
                .filter(flag -> (flag.value & value) != 0)
                .toArray(WindowStateFlag[]::new);
    }
}
