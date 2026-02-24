package com.scm.all.enumBox;

/**
 * Direct2D 的 D2D1_ALPHA_MODE 枚举 Java 映射版
 * 对应原生定义：typedef enum D2D1_ALPHA_MODE
 * 核心：数值与原生严格一致，补充 Alpha 模式的业务说明，支持 JNI 双向交互
 */
public enum D2D1AlphaMode {
    /**
     * Alpha 模式由系统隐式确定（若目标表面无相关信息，需手动指定）
     * 原生值：0
     */
    D2D1_ALPHA_MODE_UNKNOWN(0),

    /**
     * 预乘 Alpha 模式（Direct2D 绘图默认模式，推荐使用）
     * 原理：RGB 通道值已乘以 Alpha 值（如红色不透明为 (255,0,0,255)，半透明为 (128,0,0,128)）
     * 原生值：1
     */
    D2D1_ALPHA_MODE_PREMULTIPLIED(1),

    /**
     * 直 Alpha 模式（非预乘）
     * 原理：RGB 通道值独立，Alpha 仅存于 A 通道（如红色半透明为 (255,0,0,128)）
     * 原生值：2
     */
    D2D1_ALPHA_MODE_STRAIGHT(2),

    /**
     * 忽略 Alpha 通道（完全不透明）
     * 适用场景：无需透明度的纯彩色绘图
     * 原生值：3
     */
    D2D1_ALPHA_MODE_IGNORE(3),

    /**
     * 强制枚举为 DWORD 类型（仅原生占位，Java 业务代码无需使用）
     * 原生值：0xffffffff
     */
    D2D1_ALPHA_MODE_FORCE_DWORD(0xFFFFFFFF);

    // 存储原生枚举数值（32位int覆盖所有值，适配JNI传递）
    private final int value;

    /**
     * 构造方法：绑定枚举与原生数值
     * @param value 原生 D2D1_ALPHA_MODE 的整数值
     */
    D2D1AlphaMode(int value) {
        this.value = value;
    }

    /**
     * 获取原生数值（JNI 交互核心：传递给 C/C++ 设置像素格式）
     * @return 枚举对应的原生整数值
     */
    public int getValue() {
        return value;
    }

    /**
     * 从原生数值反向解析枚举（JNI 回调时解析返回值）
     * @param value 原生 D2D1_ALPHA_MODE 数值
     * @return 匹配的枚举，无匹配则返回 null
     */
    public static D2D1AlphaMode fromValue(int value) {
        for (D2D1AlphaMode alphaMode : values()) {
            if (alphaMode.value == value) {
                return alphaMode;
            }
        }
        return null;
    }

    /**
     * 快捷方法：获取 Direct2D 绘图默认 Alpha 模式（预乘 Alpha）
     * 对应原生：D2D1_ALPHA_MODE_PREMULTIPLIED = 1
     * @return 默认 Alpha 模式枚举
     */
    public static D2D1AlphaMode getDefaultD2DAlphaMode() {
        return D2D1_ALPHA_MODE_PREMULTIPLIED;
    }
}
