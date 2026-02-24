package com.scm.all.enumBox;

/**
 * Direct2D 的 D2D1_RENDER_TARGET_USAGE 枚举 Java 映射版
 * 对应原生定义：typedef enum D2D1_RENDER_TARGET_USAGE
 * 核心特性：支持位运算组合（如 GDI_COMPATIBLE | FORCE_BITMAP_REMOTING）
 */
public enum D2D1RenderTargetUsage {
    /**
     * 无特殊使用要求（默认值）
     * 原生值：0x00000000
     */
    D2D1_RENDER_TARGET_USAGE_NONE(0x00000000),

    /**
     * 强制位图远程渲染：本地渲染，终端服务会话中发送位图更新到客户端
     * 原生值：0x00000001
     */
    D2D1_RENDER_TARGET_USAGE_FORCE_BITMAP_REMOTING(0x00000001),

    /**
     * GDI 兼容：允许调用 ID2D1GdiInteropRenderTarget::GetDC，且渲染在本地进行
     * 原生值：0x00000002
     */
    D2D1_RENDER_TARGET_USAGE_GDI_COMPATIBLE(0x00000002),

    /**
     * 强制枚举为 DWORD 类型（仅原生占位，Java 业务代码无需使用）
     * 原生值：0xffffffff
     */
    D2D1_RENDER_TARGET_USAGE_FORCE_DWORD(0xFFFFFFFF);

    // 存储原生枚举的数值（支持位运算，用 int 足够，32位覆盖所有值）
    private final int value;

    /**
     * 构造方法：绑定枚举与原生数值
     * @param value 原生 D2D1_RENDER_TARGET_USAGE 的整数值
     */
    D2D1RenderTargetUsage(int value) {
        this.value = value;
    }

    /**
     * 获取枚举对应的原生数值（JNI 传递/位运算核心）
     * @return 原生枚举的整数值
     */
    public int getValue() {
        return value;
    }

    /**
     * 从原生数值反向解析枚举（支持位组合值的解析）
     * 注意：位组合值（如 0x00000003 = 1|2）会返回 null，需自行处理位判断
     * @param value 原生 D2D1_RENDER_TARGET_USAGE 数值
     * @return 匹配的单一枚举，无匹配则返回 null
     */
    public static D2D1RenderTargetUsage fromValue(int value) {
        for (D2D1RenderTargetUsage usage : values()) {
            if (usage.value == value) {
                return usage;
            }
        }
        return null;
    }

    /**
     * 扩展方法：判断数值是否包含当前枚举的位标记（适配位组合场景）
     * @param combinedValue 位组合后的数值（如 0x00000003）
     * @return true = 包含当前枚举的位标记，false = 不包含
     */
    public boolean isSet(int combinedValue) {
        return (combinedValue & this.value) != 0;
    }
}
