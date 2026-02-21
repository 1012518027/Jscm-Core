package com.scm.all.struct;

/**
 * 列表框项信息类，对应C语言中的listBox_item_info_结构体
 * 用于存储列表框中项的用户数据和高度信息
 */
public class TagListBoxItemInfo {
    /**
     * 用户绑定数据（对应C结构体中的nUserData）
     * vint类型通常表示可变长度整数，在Java中用long兼容32/64位系统
     */
    public byte[] nUserData;

    /**
     * 项默认高度（对应C结构体中的nHeight）
     * -1表示使用默认高度
     */
    public int nHeight;

    /**
     * 项选中时的高度（对应C结构体中的nSelHeight）
     * -1表示使用默认高度
     */
    public int nSelHeight;
    /**
     * 重写toString方法，方便调试时查看信息
     * @return 包含项信息的字符串
     */
    @Override
    public String toString() {
        return "ListBoxItemInfo{" +
                "userData=" + nUserData +
                ", height=" + (nHeight == -1 ? "默认" : nHeight) +
                ", selectedHeight=" + (nSelHeight == -1 ? "默认" : nSelHeight) +
                '}';
    }
}
