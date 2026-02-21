package com.scm.all.enumBox;

public enum ChatFlag {
    /**
     * 别名：左侧
     * 左侧
     */
    chat_flag_left(0x1),

    /**
     * 别名：右侧
     * 右侧
     */
    chat_flag_right(0x2),

    /**
     * 别名：中间
     * 中间
     */
    chat_flag_center(0x4),

    /**
     * 别名：下一行显示气泡
     * 下一行显示气泡
     */
    chat_flag_next_row_bubble(0x8),

    /**
     * 别名：气泡行
     * 气泡(气泡行)
     */
    chat_flag_chat(0x10000);

    private final int value;

    ChatFlag(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ChatFlag fromValue(int value) {
        for (ChatFlag flag : values()) {
            if (flag.value == value) {
                return flag;
            }
        }
        return null;
    }
}
