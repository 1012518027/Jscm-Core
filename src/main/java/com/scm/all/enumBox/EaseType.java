package com.scm.all.enumBox;

/**
 * 缓动类型枚举（对应 C++ 枚举 ease_type_）
 * group_ease_type_ 缓动类型(ease_type_)
 * group_enum
 */
public enum EaseType {
    /**
     * 别名：从慢到快
     * 从慢到快
     */
    easeIn,

    /**
     * 别名：从快到慢
     * 从快到慢
     */
    easeOut,

    /**
     * 别名：从慢到快再到慢
     * 从慢到快再到慢
     */
    easeInOut;
}
