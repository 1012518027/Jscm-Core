package com.scm.all.enumBox;

/**
 * Direct2D 的 D2D1_RENDER_TARGET_TYPE 枚举 Java 映射版
 * 对应原生定义：typedef enum D2D1_RENDER_TARGET_TYPE
 * 核心：枚举值与原生数值严格一致，支持 JNI 双向交互
 */
public enum D2D1RenderTargetType {
    /**
     * Direct2D 自动选择渲染模式（优先硬件加速，不支持则降级为软件）
     * 原生值：0
     */
    D2D1_RENDER_TARGET_TYPE_DEFAULT(0),

    /**
     * 渲染目标使用 CPU 进行软件渲染（无硬件加速，兼容性最好）
     * 原生值：1
     */
    D2D1_RENDER_TARGET_TYPE_SOFTWARE(1),

    /**
     * 渲染目标使用 GPU 进行硬件加速渲染（性能更高，需显卡支持）
     * 原生值：2
     */
    D2D1_RENDER_TARGET_TYPE_HARDWARE(2),

    /**
     * 强制枚举为 DWORD 类型（仅原生枚举占位用，Java 业务代码无需使用）
     * 原生值：0xffffffff
     */
    D2D1_RENDER_TARGET_TYPE_FORCE_DWORD(0xFFFFFFFF);

    // 存储原生枚举的数值（JNI 交互时传递给 C/C++ 的核心）
    private final int value;

    /**
     * 构造方法：绑定枚举与原生数值
     * @param value 原生 D2D1_RENDER_TARGET_TYPE 的整数值
     */
    D2D1RenderTargetType(int value) {
        this.value = value;
    }

    /**
     * 获取枚举对应的原生数值（JNI 调用时传递参数用）
     * @return 原生枚举的整数值
     */
    public int getValue() {
        return value;
    }

    /**
     * 从原生数值反向解析枚举（JNI 回调时解析返回值用）
     * @param value 原生 D2D1_RENDER_TARGET_TYPE 数值
     * @return 匹配的枚举，无匹配则返回 null
     */
    public static D2D1RenderTargetType fromValue(int value) {
        for (D2D1RenderTargetType type : values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }
}
