package com.scm.all.struct;

/**
 * 位置点类，对应C语言中的position_结构体
 * 用于表示行和列的索引位置
 */
public class TagPosition {
    /**
     * 行索引（对应C结构体中的iRow）
     */
    public int iRow;

    /**
     * 列索引（对应C结构体中的iColumn）
     */
    public int iColumn;

    /**
     * 重写toString方法，方便打印位置信息
     * @return 包含行和列索引的字符串
     */
    @Override
    public String toString() {
        return "Position{" +
                "row=" + iRow +
                ", column=" + iColumn +
                '}';
    }
}

