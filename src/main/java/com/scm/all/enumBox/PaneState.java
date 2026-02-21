package com.scm.all.enumBox;

/**
 * 窗格状态枚举（对应 C++ 枚举 pane_state_）
 * Group_pane_state_ 窗格状态(pane_state_)
 * group_enum
 */
public enum PaneState {
    /**
     * 别名：错误
     * 错误
     */
    pane_state_error(-1),

    /**
     * 别名：任意
     * 任意
     */
    pane_state_any(0),

    /**
     * 别名：锁定
     * 锁定
     */
    pane_state_lock(1),

    /**
     * 别名：停靠码头
     * 停靠码头
     */
    pane_state_dock(2),

    /**
     * 别名：浮动窗格
     * 浮动窗格
     */
    pane_state_float(3);

    private final int value;

    PaneState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PaneState fromValue(int value) {
        for (PaneState state : values()) {
            if (state.value == value) {
                return state;
            }
        }
        return null;
    }
}
