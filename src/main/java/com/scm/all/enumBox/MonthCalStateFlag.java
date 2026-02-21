package com.scm.all.enumBox;

/**
 * 月历卡片状态枚举（对应 C++ 枚举 monthCal_state_flag_）
 * group_monthCal_state_flag_ 月历卡片状态(monthCal_state_flag_)
 * 注：枚举值为独立位标志，支持整体状态与项级状态组合，复用 {@link ElementStateFlag} 常量确保一致性
 */
public enum MonthCalStateFlag {
    /**
     * 别名：鼠标离开
     * 鼠标离开月历卡片整体区域（复用元素状态的“鼠标离开”常量）
     */
    MONTH_CAL_STATE_FLAG_LEAVE(ElementStateFlag.ELEMENT_STATE_FLAG_LEAVE.getValue()),

    /**
     * 别名：项鼠标离开
     * 鼠标离开月历卡片的某一项（日期项）区域
     */
    MONTH_CAL_STATE_FLAG_ITEM_LEAVE(0x0080),

    /**
     * 别名：项鼠标停留
     * 鼠标停留于月历卡片的某一项（日期项）区域
     */
    MONTH_CAL_STATE_FLAG_ITEM_STAY(0x0100),

    /**
     * 别名：项鼠标按下
     * 鼠标在月历卡片的某一项（日期项）上按下（未释放）
     */
    MONTH_CAL_STATE_FLAG_ITEM_DOWN(0x0200),

    /**
     * 别名：项选择
     * 月历卡片的某一项（日期项）处于选中状态
     */
    MONTH_CAL_STATE_FLAG_ITEM_SELECT(0x0400),

    /**
     * 别名：项未选择
     * 月历卡片的某一项（日期项）处于未选中状态
     */
    MONTH_CAL_STATE_FLAG_ITEM_SELECT_NO(0x0800),

    /**
     * 别名：项今天
     * 月历卡片的某一项（日期项）是今天的日期
     */
    MONTH_CAL_STATE_FLAG_ITEM_TODAY(0x1000),

    /**
     * 别名：项上月
     * 月历卡片的某一项（日期项）是上月的日期（非当前显示月）
     */
    MONTH_CAL_STATE_FLAG_ITEM_LAST_MONTH(0x2000),

    /**
     * 别名：项当月
     * 月历卡片的某一项（日期项）是当前显示月的日期
     */
    MONTH_CAL_STATE_FLAG_ITEM_CUR_MONTH(0x4000),

    /**
     * 别名：项下月
     * 月历卡片的某一项（日期项）是下月的日期（非当前显示月）
     */
    MONTH_CAL_STATE_FLAG_ITEM_NEXT_MONTH(0x8000);

    // 月历卡片状态对应的位标志原始值（与 C++ 枚举完全一致）
    private final int value;

    /**
     * 构造函数：绑定枚举与对应的位标志值
     * @param value C++ 枚举对应的数值（或复用 ElementStateFlag 的常量值）
     */
    MonthCalStateFlag(int value) {
        this.value = value;
    }

    /**
     * 获取枚举对应的原始数值（用于调用 WINAPI 时传入）
     * @return 位标志的 int 类型值
     */
    public int getValue() {
        return value;
    }

    // ------------------- 位运算辅助方法（与前序枚举用法完全统一）-------------------
    /**
     * 组合当前状态与目标月历卡片状态（按位或运算）
     * 例：项停留 + 项今天 → MONTH_CAL_STATE_FLAG_ITEM_STAY.or(MONTH_CAL_STATE_FLAG_ITEM_TODAY)
     * @param other 要组合的月历卡片状态
     * @return 组合后的状态值（int 类型，可直接传入 WINAPI）
     */
    public int or(MonthCalStateFlag other) {
        return this.value | other.value;
    }

    /**
     * 组合多个月历卡片状态（支持可变参数，适配多状态组合场景）
     * @param flags 要组合的多个状态（可混合整体状态与项级状态）
     * @return 组合后的状态值（int 类型）
     */
    public static int combine(MonthCalStateFlag... flags) {
        int combined = 0;
        for (MonthCalStateFlag flag : flags) {
            combined |= flag.value;
        }
        return combined;
    }

    /**
     * 判断目标状态值中是否包含当前月历卡片状态（按位与运算）
     * @param state 要判断的状态值（通常是组合后的 int 值）
     * @return 是否包含当前状态（true=包含，false=不包含）
     */
    public boolean has(int state) {
        return (state & this.value) != 0;
    }

    /**
     * 扩展：组合当前月历卡片状态与元素状态（如项选中 + 元素启用）
     * @param elementState 元素状态枚举
     * @return 组合后的状态值（int 类型）
     */
    public int or(ElementStateFlag elementState) {
        return this.value | elementState.getValue();
    }
}
