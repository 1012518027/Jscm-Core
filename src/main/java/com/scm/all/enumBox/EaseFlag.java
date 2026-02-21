package com.scm.all.enumBox;

/**
 * 缓动标识枚举（对应 C++ 枚举 ease_flag_）
 * group_ease_flag_ 缓动标识(ease_flag_)
 * group_enum
 */
public enum EaseFlag {
    /**
     * 别名：线性
     * 线性, 直线
     */
    ease_flag_linear(0),

    /**
     * 别名：二次方曲线
     * 二次方曲线
     */
    ease_flag_quad(1),

    /**
     * 别名：三次方曲线
     * 三次方曲线, 圆弧
     */
    ease_flag_cubic(2),

    /**
     * 别名：四次方曲线
     * 四次方曲线
     */
    ease_flag_quart(3),

    /**
     * 别名：五次方曲线
     * 五次方曲线
     */
    ease_flag_quint(4),

    /**
     * 别名：正弦
     * 正弦, 在末端变化
     */
    ease_flag_sine(5),

    /**
     * 别名：突击
     * 突击, 突然一下
     */
    ease_flag_expo(6),

    /**
     * 别名：圆环
     * 圆环, 好比绕过一个圆环
     */
    ease_flag_circ(7),

    /**
     * 别名：强力回弹
     * 强力回弹
     */
    ease_flag_elastic(8),

    /**
     * 别名：回弹
     * 回弹, 比较缓慢
     */
    ease_flag_back(9),

    /**
     * 别名：弹跳
     * 弹跳, 模拟小球落地弹跳
     */
    ease_flag_bounce(10),

    /**
     * 别名：从慢到快
     * 从慢到快
     */
    ease_flag_in(0x010000),

    /**
     * 别名：从快到慢
     * 从快到慢
     */
    ease_flag_out(0x020000),

    /**
     * 别名：从慢到快再到慢
     * 从慢到快再到慢
     */
    ease_flag_inOut(0x030000);

    private final int value;

    EaseFlag(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static EaseFlag fromValue(int value) {
        for (EaseFlag flag : values()) {
            if (flag.value == value) {
                return flag;
            }
        }
        return null;
    }
}
