package com.scm.all.enumBox;

/**
 * 动画移动标识枚举（对应 C++ 枚举 animation_move_）
 * group_animation_move_ 动画移动标识(animation_move_)
 * group_enum
 */
public enum AnimationMove {
    /**
     * 别名：X轴移动
     * X轴移动
     */
    animation_move_x(0x01),

    /**
     * 别名：Y轴移动
     * Y轴移动
     */
    animation_move_y(0x02);

    private final int value;

    AnimationMove(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static AnimationMove fromValue(int value) {
        for (AnimationMove move : values()) {
            if (move.value == value) {
                return move;
            }
        }
        return null;
    }
}
