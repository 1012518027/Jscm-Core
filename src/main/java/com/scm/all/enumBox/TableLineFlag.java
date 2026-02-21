package com.scm.all.enumBox;

public enum TableLineFlag {
    table_line_flag_left(0x1),   ///<待补充
    table_line_flag_top(0x2),   ///<待补充
    table_line_flag_right(0x4),	 ///<待补充
    table_line_flag_bottom(0x8),   ///<待补充
    table_line_flag_left2(0x10),  ///<待补充
    table_line_flag_top2(0x20),	 ///<待补充
    table_line_flag_right2(0x40),	 ///<待补充
    table_line_flag_bottom2(0x80);	 ///<待补充

    private final int value;

    TableLineFlag(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TableLineFlag fromValue(int value) {
        for (TableLineFlag flag : values()) {
            if (flag.value == value) {
                return flag;
            }
        }
        return null;
    }
}
