package com.scm.all.struct;

/**
 * Edit 数据复制-样式
 */
public class Tag_edit_data_copy_style {
    /**
     * 句柄(字体,图片,UI对象), 使用UINT目的当64位时可以节约4字节内存
     */
    public long hFont_image_obj;
    /**
     * 颜色
     */
    public long color;
    public int bColor;


    @Override
    public String toString() {
        return "Tag_edit_data_copy_style_{" +
                "hFont_image_obj=" + hFont_image_obj +
                ", color=" + color +
                //", bColor=" + bColor +
                '}';
    }
}
