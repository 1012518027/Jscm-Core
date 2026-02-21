package com.scm.all.struct;

/**
 * Edit 样式信息类，对应C语言中的 edit_style_info_ 结构体
 * 用于存储编辑框的样式信息，包括样式类型、引用计数、资源句柄、颜色等
 */
public class TagEditStyleInfo {
    /**
     * 样式类型（对应C中的 USHORT，Java中用short表示16位无符号整数）
     * 具体取值需参考相关定义（如：0=字体样式，1=图片样式等）
     */
    public short type;

    /**
     * 引用计数（对应C中的 USHORT）
     * 用于跟踪该样式被引用的次数，辅助内存管理
     */
    public short nRef;

    /**
     * 句柄(字体,图片,UI对象)（对应C中的 HXCGUI）
     * 通常是一个资源标识符，指向字体、图片或其他UI对象
     */
    public long hFont_image_obj;  // 使用long兼容64位系统的句柄

    /**
     * 颜色值（对应C中的 COLORREF）
     * Windows中COLORREF通常是32位整数，格式为0x00BBGGRR
     * 例如：红色=0x000000FF，绿色=0x0000FF00，蓝色=0x00FF0000
     */
    public long color;

    /**
     * 是否使用颜色（对应C中的 BOOL）
     * true=使用上述color字段的颜色值，false=不使用（使用默认颜色）
     */
    public int bColor;

    @Override
    public String toString() {
        return "TagEditStyleInfo{" +
                "type=" + type +
                ", nRef=" + nRef +
                ", hFont_image_obj=" + hFont_image_obj +
                ", color=" + color +
                ", bColor=" + bColor +
                '}';
    }
}
