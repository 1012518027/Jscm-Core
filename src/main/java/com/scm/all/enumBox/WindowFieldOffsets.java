package com.scm.all.enumBox;

/**
 * 窗口字段偏移量枚举（对应 C++ 枚举 window_field_offsets_）
 */
public enum WindowFieldOffsets {
    /**
     *  GWL_WNDPROC
     */
    GWL_WNDPROC (-4),
    /**
     * GWL_HINSTANCE
     */
    GWL_HINSTANCE (-6),
    /**
     * GWL_HWNDPARENT
     */
    GWL_HWNDPARENT (-8),
    /**
     * GWL_STYLE
     */
    GWL_STYLE   (-16),
    /**
     * GWL_EXSTYLE
     */
    GWL_EXSTYLE (-20),
    /**
     * GWL_USERDATA
     */
    GWL_USERDATA  (-21),
    /**
     * GWL_ID
     */
    GWL_ID (-12);
    private final int value;

    WindowFieldOffsets(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static WindowFieldOffsets fromValue(int value) {
        for (WindowFieldOffsets type : values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }
}
