package com.scm.all.enumBox;

/**
 * 弹出消息框标识枚举（对应 C++ 枚举 messageBox_flag_）
 * groupMessageBox 弹出消息框(messageBox_flag_)
 * group_enum
 */
public enum MessageBoxFlag {
    /**
     * 别名：其他
     * 其他
     */
    messageBox_flag_other(0x00),

    /**
     * 别名：确定按钮
     * 确定按钮
     */
    messageBox_flag_ok(0x01),

    /**
     * 别名：取消按钮
     * 取消按钮
     */
    messageBox_flag_cancel(0x02),

    /**
     * 别名：图标应用程序
     * 图标 应用程序  IDI_APPLICATION
     */
    messageBox_flag_icon_appicon(0x01000),

    /**
     * 别名：图标信息
     * 图标 信息     IDI_ASTERISK
     */
    messageBox_flag_icon_info(0x02000),

    /**
     * 别名：图标问询
     * 图标 问询/帮助/提问   IDI_QUESTION
     */
    messageBox_flag_icon_qustion(0x04000),

    /**
     * 别名：图标错误
     * 图标 错误/拒绝/禁止  IDI_ERROR
     */
    messageBox_flag_icon_error(0x08000),

    /**
     * 别名：图标警告
     * 图标 警告       IDI_WARNING
     */
    messageBox_flag_icon_warning(0x10000),

    /**
     * 别名：图标安全
     * 图标 盾牌/安全   IDI_SHIELD
     */
    messageBox_flag_icon_shield(0x20000);

    private final int value;

    MessageBoxFlag(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MessageBoxFlag fromValue(int value) {
        for (MessageBoxFlag flag : values()) {
            if (flag.value == value) {
                return flag;
            }
        }
        return null;
    }
}
