package com.scm.all.enumBox;

/**
 * Direct2D 的 D2D1_FEATURE_LEVEL 枚举 Java 映射版
 * 对应原生定义：typedef enum D2D1_FEATURE_LEVEL
 * 核心：枚举值与原生数值完全一致，保证 JNI 交互时的正确性
 */
public enum D2D1FeatureLevel {
    /**
     * 调用者不要求特定的底层 D3D 设备级别（默认值）
     * 原生值：0
     */
    D2D1_FEATURE_LEVEL_DEFAULT(0),

    /**
     * D3D 设备级别兼容 DX9（对应 D3D_FEATURE_LEVEL_9_1）
     * 原生值：D3D_FEATURE_LEVEL_9_1 = 0x9100
     */
    D2D1_FEATURE_LEVEL_9(0x9100),

    /**
     * D3D 设备级别兼容 DX10（对应 D3D_FEATURE_LEVEL_10_0）
     * 原生值：D3D_FEATURE_LEVEL_10_0 = 0xA000
     */
    D2D1_FEATURE_LEVEL_10(0xA000),

    /**
     * 强制枚举为 DWORD 类型（仅原生枚举占位用，Java 中无需实际使用）
     * 原生值：0xffffffff
     */
    D2D1_FEATURE_LEVEL_FORCE_DWORD(0xFFFFFFFF);

    // 存储原生枚举的数值（用于 JNI 交互时传递）
    private final int value;

    /**
     * 构造方法：绑定枚举值与原生数值
     * @param value 原生 D2D1_FEATURE_LEVEL 的数值
     */
    D2D1FeatureLevel(int value) {
        this.value = value;
    }

    /**
     * 获取原生数值（核心：JNI 调用时传递给 C/C++ 的关键）
     * @return 枚举对应的原生整数值
     */
    public int getValue() {
        return value;
    }

    /**
     * 从原生数值反向查找对应的枚举（JNI 回调时解析返回值用）
     * @param value 原生 D2D1_FEATURE_LEVEL 数值
     * @return 对应的枚举，无匹配则返回 null
     */
    public static D2D1FeatureLevel fromValue(int value) {
        for (D2D1FeatureLevel level : values()) {
            if (level.value == value) {
                return level;
            }
        }
        return null;
    }
}
