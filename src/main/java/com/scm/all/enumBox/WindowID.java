package com.scm.all.enumBox;

/**
 * 窗口ID枚举
 */
public enum WindowID {
    /**
     * 窗口隐藏
     */
    SW_HIDE(0),
    /**
     * 窗口正常显示
     */
    SW_SHOWNORMAL(1),
    /**
     *  窗口最小化显示
     */
    SW_SHOWMINIMIZED(2),
    /**
     * 窗口最大化显示
     */
    SW_SHOWMAXIMIZED(3),
    /**
     * 窗口正常显示，但未激活
     */
    SW_SHOWNOACTIVATE(4),
    /**
     * 窗口正常显示，并激活
     */
    SW_SHOW(5),
    /**
     * 窗口最小化显示，但未激活
     */
    SW_MINIMIZE(6),
    /**
     * 窗口最小化显示，并激活
     */
    SW_SHOWMINNOACTIVE(7),
    /**
     * 窗口正常显示，但未激活
     */
    SW_SHOWNA(8),
    /**
     * 窗口正常显示，并激活
     */
    SW_RESTORE(9),
    /**
     * 窗口正常显示，并激活
     */
    SW_SHOWDEFAULT(10),
    /**
     * 窗口最小化显示，并激活
     */
    SW_FORCEMINIMIZE(11);


    private final int value;

    WindowID(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static WindowID fromValue(int value) {
        for (WindowID type : values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }
}
