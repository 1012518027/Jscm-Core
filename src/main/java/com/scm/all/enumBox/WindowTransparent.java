package com.scm.all.enumBox;

/**
 * 窗口透明标识枚举，对应C语言的window_transparent_枚举
 * 枚举值表示窗口的不同透明模式
 */
public enum WindowTransparent {
    /**
     * 默认窗口，不透明
     */
    WINDOW_TRANSPARENT_FALSE(0),

    /**
     * 透明窗口，带透明通道，异型
     */
    WINDOW_TRANSPARENT_SHAPED(1),

    /**
     * 阴影窗口，带透明通道，边框阴影，窗口透明或半透明
     */
    WINDOW_TRANSPARENT_SHADOW(2),

    /**
     * 透明窗口，不带透明通道，指定半透明度，指定透明色
     */
    WINDOW_TRANSPARENT_SIMPLE(3),

    /**
     * WIN7玻璃窗口，需要WIN7开启特效，当前未启用
     */
    WINDOW_TRANSPARENT_WIN7(4);

    // 枚举对应的数值
    private final int value;

    /**
     * 构造函数，初始化枚举值
     * @param value 枚举对应的整数数值
     */
    WindowTransparent(int value) {
        this.value = value;
    }

    /**
     * 获取枚举对应的数值
     * @return 枚举对应的整数
     */
    public int getValue() {
        return value;
    }

    /**
     * 根据数值获取对应的枚举实例
     * @param value 枚举对应的数值
     * @return 对应的枚举实例，如果没有匹配的则返回null
     */
    public static WindowTransparent fromValue(int value) {
        for (WindowTransparent transparent : values()) {
            if (transparent.value == value) {
                return transparent;
            }
        }
        return null;
    }
}
