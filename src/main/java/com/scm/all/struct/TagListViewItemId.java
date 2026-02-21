package com.scm.all.struct;

/**
 * 列表视图项ID类，对应C语言中的listView_item_id_结构体
 * 用于标识列表视图中项的位置（组索引+项索引）
 */
public class TagListViewItemId {
    /**
     * 组索引（对应C结构体中的iGroup）
     * 表示项所在的组编号，从0开始
     */
    private int groupIndex;

    /**
     * 项索引（对应C结构体中的iItem）
     * 表示组内的项编号，从0开始
     */
    private int itemIndex;

    /**
     * 重写toString方法，方便打印调试信息
     * @return 包含组索引和项索引的字符串
     */
    @Override
    public String toString() {
        return "ListViewItemId{" +
                "groupIndex=" + groupIndex +
                ", itemIndex=" + itemIndex +
                '}';
    }
}
