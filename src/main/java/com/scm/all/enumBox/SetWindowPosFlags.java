package com.scm.all.enumBox;

/**
 * SetWindowPosFlags枚举（对应 C++ 枚举 setWindowPos_flags_）
 */
public enum SetWindowPosFlags {
    /**
     * 禁止调整窗口大小
     */
    SWP_NOSIZE (0x0001),
    /**
     * 禁止移动窗口
     */
   SWP_NOMOVE (0x0002),
    /**
     * 禁止改变窗口的层叠顺序
     */
   SWP_NOZORDER (0x0004),
    /**
     * 禁止重绘窗口
     */
   SWP_NOREDRAW (0x0008),
    /**
     * 禁止窗口活动
     */
   SWP_NOACTIVATE (0x0010),
    /**
     * 窗口大小改变
     */
   SWP_FRAMECHANGED (0x0020),  /* The frame changed: send WM_NCCALCSIZE */
    /**
     * 显示窗口
     */
   SWP_SHOWWINDOW (0x0040),
    /**
     * 隐藏窗口
     */
   SWP_HIDEWINDOW (0x0080),
    /**
     * 不复制位
     */
   SWP_NOCOPYBITS (0x0100),
    /**
     * 不复制窗口
     */
   SWP_NOOWNERZORDER (0x0200),  /* Don't do owner Z ordering */
    /**
     * 不发送更改消息
     */
   SWP_NOSENDCHANGING (0x0400);
    private final int value;

    SetWindowPosFlags(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SetWindowPosFlags fromValue(int value) {
        for (SetWindowPosFlags type : values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }
}
