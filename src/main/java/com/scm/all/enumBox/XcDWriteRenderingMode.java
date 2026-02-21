package com.scm.all.enumBox;

/**
 * D2D文本渲染模式枚举（对应XC_DWRITE_RENDERING_MODE）
 */
public enum XcDWriteRenderingMode {
    /**
     * 指定根据字体和大小自动确定呈现模式（默认）
     */
    XC_DWRITE_RENDERING_MODE_DEFAULT(0),

    /**
     * 指定不执行抗锯齿。每个像素要么设置为文本的前景色，要么保留背景的颜色（不抗锯齿）
     */
    XC_DWRITE_RENDERING_MODE_ALIASED,

    /**
     * 使用与别名文本相同的度量指定 ClearType 呈现。字形只能定位在整个像素的边界上（CLEARTYPE_GDI_CLASSIC）
     */
    XC_DWRITE_RENDERING_MODE_CLEARTYPE_GDI_CLASSIC,

    /**
     * 使用使用 CLEARTYPE_NATURAL_QUALITY 创建的字体，使用与使用 GDI 的文本呈现相同的指标指定 ClearType 呈现。
     * 与使用别名文本相比，字形度量更接近其理想值，但字形仍然位于整个像素的边界上（CLEARTYPE_GDI_NATURAL）
     */
    XC_DWRITE_RENDERING_MODE_CLEARTYPE_GDI_NATURAL,

    /**
     * 仅在水平维度中指定具有抗锯齿功能的 ClearType 渲染。这通常用于中小字体大小（最多 16 ppem）（CLEARTYPE_NATURAL）
     */
    XC_DWRITE_RENDERING_MODE_CLEARTYPE_NATURAL,

    /**
     * 指定在水平和垂直维度上具有抗锯齿的 ClearType 渲染。这通常用于较大的尺寸，以使曲线和对角线看起来更平滑，但会牺牲一些柔和度（CLEARTYPE_NATURAL_SYMMETRIC）
     */
    XC_DWRITE_RENDERING_MODE_CLEARTYPE_NATURAL_SYMMETRIC,

    /**
     * 指定渲染应绕过光栅化器并直接使用轮廓。这通常用于非常大的尺寸（OUTLINE）
     */
    XC_DWRITE_RENDERING_MODE_OUTLINE;

    // 枚举值对应的整数
    private final int value;

    /**
     * 带参数的构造函数
     * @param value 枚举对应的整数值
     */
    XcDWriteRenderingMode(int value) {
        this.value = value;
    }

    /**
     * 默认构造函数，自动分配后续整数值
     */
    XcDWriteRenderingMode() {
        // 自动为后续枚举值分配递增的整数（从1开始）
        this.value = ordinal();
    }

    /**
     * 获取枚举对应的整数值
     * @return 枚举值对应的整数
     */
    public int getValue() {
        return value;
    }
}
