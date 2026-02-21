package com.scm.all.enumBox;

public enum RedrawWindow {
    RDW_INVALIDATE(0x0001),
    RDW_INTERNALPAINT(0x0002),
    RDW_ERASE(0x0004),

    RDW_VALIDATE(0x0008),
    RDW_NOINTERNALPAINT(0x0010),
    RDW_NOERASE(0x0020),

    RDW_NOCHILDREN(0x0040),
    RDW_ALLCHILDREN(0x0080),

    RDW_UPDATENOW(0x0100),
    RDW_ERASENOW(0x0200),

    RDW_FRAME(0x0400),
    RDW_NOFRAME(0x0800);
    private final int value;

    RedrawWindow(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static RedrawWindow fromValue(int value) {
        for (RedrawWindow type : values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }
}
