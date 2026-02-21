package com.scm.all.enumBox;

/**
 * 编辑框类型枚举（对应 C++ 枚举 edit_type_）
 * group_edit_macro 编辑框类型(edit_type_)
 * group_enum
 */
public enum EditType {
    /**
     * 别名：普通
     * 普通编辑框,   每行的高度相同
     */
    edit_type_none(0),

    /**
     * 别名：代码
     * 代码编辑框,   每行的高度相同,  功能继承普通编辑框
     */
    edit_type_editor(1),

    /**
     * 别名：富文本
     * 富文本编辑框,  每行的高度可能不同
     */
    edit_type_richedit(2),

    /**
     * 别名：聊天气泡
     * 聊天气泡,     每行的高度可能不同, 功能继承富文本编辑框
     */
    edit_type_chat(3),

    /**
     * 别名：代码表格
     * 代码表格,内部使用,  每行的高度相同, 未使用
     */
    edit_type_codeTable(4);

    private final int value;

    EditType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static EditType fromValue(int value) {
        for (EditType type : values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }
}
