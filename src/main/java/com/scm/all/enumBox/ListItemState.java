package com.scm.all.enumBox;

/**
 * 列表项状态枚举（对应 C++ 枚举 list_item_state_）
 * 适用于(列表,列表框,列表视图)
 * groupListItemState 列表项状态(list_item_state_)
 * group_enum
 */
public enum ListItemState {
    /**
     * 别名：鼠标离开
     * 项鼠标离开状态
     */
    list_item_state_leave(0),

    /**
     * 别名：鼠标停留
     * 项鼠标停留状态
     */
    list_item_state_stay(1),

    /**
     * 别名：项选择
     * 项选择状态
     */
    list_item_state_select(2),

    /**
     * 别名：缓存的项
     * 缓存的项
     */
    list_item_state_cache(3);

    private final int value;

    ListItemState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ListItemState fromValue(int value) {
        for (ListItemState state : values()) {
            if (state.value == value) {
                return state;
            }
        }
        return null;
    }
}
