package com.scm.all.enumBox;

/**
 * 月历卡片按钮类型枚举（对应 C++ 枚举 monthCal_button_type_）
 * group_monthCal_button_type_ 月历元素上的按钮类型(monthCal_button_type_)
 * group_enum
 */
public enum MonthCalButtonType {
    /**
     * 别名：今天
     * 今天按钮
     */
    monthCal_button_type_today(0),

    /**
     * 别名：上一年
     * 上一年
     */
    monthCal_button_type_last_year(1),

    /**
     * 别名：下一年
     * 下一年
     */
    monthCal_button_type_next_year(2),

    /**
     * 别名：上一月
     * 上一月
     */
    monthCal_button_type_last_month(3),

    /**
     * 别名：下一月
     * 下一月
     */
    monthCal_button_type_next_month(4);

    private final int value;

    MonthCalButtonType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MonthCalButtonType fromValue(int value) {
        for (MonthCalButtonType type : values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }
}
