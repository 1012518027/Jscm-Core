package com.scm.all.enumBox;

/**
 * 位置标识枚举，对应C语言中的position_flag_枚举
 * 定义元素在容器中的各种位置标识
 */
public enum PositionFlag {
    /**
     * 左侧（对应C枚举的position_flag_left）
     */
    POSITION_FLAG_LEFT,

    /**
     * 顶部（对应C枚举的position_flag_top）
     */
    POSITION_FLAG_TOP,

    /**
     * 右侧（对应C枚举的position_flag_right）
     */
    POSITION_FLAG_RIGHT,

    /**
     * 底部（对应C枚举的position_flag_bottom）
     */
    POSITION_FLAG_BOTTOM,

    /**
     * 左上角（对应C枚举的position_flag_leftTop）
     */
    POSITION_FLAG_LEFT_TOP,

    /**
     * 左下角（对应C枚举的position_flag_leftBottom）
     */
    POSITION_FLAG_LEFT_BOTTOM,

    /**
     * 右上角（对应C枚举的position_flag_rightTop）
     */
    POSITION_FLAG_RIGHT_TOP,

    /**
     * 右下角（对应C枚举的position_flag_rightBottom）
     */
    POSITION_FLAG_RIGHT_BOTTOM,

    /**
     * 中心（对应C枚举的position_flag_center）
     */
    POSITION_FLAG_CENTER;

    /**
     * 获取枚举值对应的整数（与C枚举的默认序号保持一致）
     * C枚举默认从0开始编号，与Java枚举的ordinal()一致
     * @return 对应的整数序号
     */
    public int getValue() {
        return this.ordinal();
    }

    /**
     * 根据整数序号获取对应的枚举实例
     * @param value 枚举对应的整数序号（0开始）
     * @return 对应的枚举实例，若序号无效则返回null
     */
    public static PositionFlag fromValue(int value) {
        PositionFlag[] values = PositionFlag.values();
        if (value >= 0 && value < values.length) {
            return values[value];
        }
        return null;
    }
}
