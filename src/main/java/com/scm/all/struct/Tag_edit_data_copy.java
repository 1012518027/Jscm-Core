package com.scm.all.struct;

import java.util.Arrays;

/**
 * Edit 数据复制
 */
public class Tag_edit_data_copy {
    /**
     * 内容数量
     */
    public int nCount;
    /**
     * 样式数量
     */
    public int nStyleCount;
    /**
     * 内容数据
     */
   public byte[] pData;


    @Override
    public String toString() {
        return "Tag_edit_data_copy{" +
                "nCount=" + nCount +
                ", nStyleCount=" + nStyleCount +
                ", pData=" + Arrays.toString(pData) +
                '}';
    }
}
