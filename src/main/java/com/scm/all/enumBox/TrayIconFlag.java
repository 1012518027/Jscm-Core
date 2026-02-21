package com.scm.all.enumBox;

/**
 * 托盘图标标识枚举（对应 C++ 枚举 trayIcon_flag_）
 * group_trayIcon_flag_ 托盘图标标识(trayIcon_flag_)
 * group_enum
 */
public enum TrayIconFlag {
    /**
     * 别名：无图标
     * 无图标 NIIF_NONE
     */
    trayIcon_flag_icon_none(0x0),

    /**
     * 别名：信息图标
     * 信息图标 NIIF_INFO
     */
    trayIcon_flag_icon_info(0x1),

    /**
     * 别名：警告图标
     * 警告图标 NIIF_WARNING
     */
    trayIcon_flag_icon_warning(0x2),

    /**
     * 别名：错误图标
     * 错误图标 NIIF_ERROR
     */
    trayIcon_flag_icon_error(0x3),

    /**
     * 别名：用户图标
     * 用户指定的图标 NIIF_USER
     */
    trayIcon_flag_icon_user(0x4),

    /**
     * 别名：禁止播放声音
     * 禁止播放气泡声音 NIIF_NOSOUND
     */
    trayIcon_flag_nosound(0x10);

    private final int value;

    TrayIconFlag(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TrayIconFlag fromValue(int value) {
        for (TrayIconFlag flag : values()) {
            if (flag.value == value) {
                return flag;
            }
        }
        return null;
    }
}
