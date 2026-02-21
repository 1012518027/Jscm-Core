package com.scm.all.enumBox;

/**
 * 图片绘制类型枚举，对应C语言中的image_draw_type_枚举
 * 定义图片在UI元素中的各种绘制方式
 */
public enum ImageDrawType {
    /**
     * 默认绘制方式（对应C枚举的image_draw_type_default）
     */
    IMAGE_DRAW_TYPE_DEFAULT(0),

    /**
     * 拉伸绘制（对应C枚举的image_draw_type_stretch）
     */
    IMAGE_DRAW_TYPE_STRETCH(1),

    /**
     * 九宫格自适应绘制（对应C枚举的image_draw_type_adaptive）
     */
    IMAGE_DRAW_TYPE_ADAPTIVE(2),

    /**
     * 平铺绘制（对应C枚举的image_draw_type_tile）
     */
    IMAGE_DRAW_TYPE_TILE(3),

    /**
     * 固定比例绘制（对应C枚举的image_draw_type_fixed_ratio）
     */
    IMAGE_DRAW_TYPE_FIXED_RATIO(4),

    /**
     * 九宫格外围绘制（不绘制中间区域，对应C枚举的image_draw_type_adaptive_border）
     */
    IMAGE_DRAW_TYPE_ADAPTIVE_BORDER(5);

    /**
     * 枚举值对应的整数（与C枚举保持一致）
     */
    private final int value;

    /**
     * 构造方法，初始化枚举值对应的整数
     * @param value 对应的整数
     */
    ImageDrawType(int value) {
        this.value = value;
    }

    /**
     * 获取枚举值对应的整数
     * @return 对应的整数
     */
    public int getValue() {
        return value;
    }

    /**
     * 根据整数获取对应的枚举实例
     * @param value 枚举对应的整数
     * @return 对应的枚举实例，未匹配则返回null
     */
    public static ImageDrawType fromValue(int value) {
        for (ImageDrawType type : ImageDrawType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }
}
