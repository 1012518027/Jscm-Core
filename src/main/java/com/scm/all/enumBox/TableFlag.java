package com.scm.all.enumBox;

public enum TableFlag {
    /**
     * 别名：铺满
     * 铺满组合单元格
     */
    table_flag_full(0),

    /**
     * 别名：正常
     * 正常最小单元格
     */
    table_flag_none(1);

    private final int value;

    TableFlag(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TableFlag fromValue(int value) {
        for (TableFlag flag : values()) {
            if (flag.value == value) {
                return flag;
            }
        }
        return null;
    }
}
