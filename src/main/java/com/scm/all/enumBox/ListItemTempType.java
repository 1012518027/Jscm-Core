package com.scm.all.enumBox;

/**
 * 列表项模板类型枚举（对应 C++ 枚举 listItemTemp_type_）
 * Group_listItemTemp_type_ 列表项模板类型(listItemTemp_type_)
 * group_enum
 */
public enum ListItemTempType {
    /**
     * 别名：列表树
     * tree
     */
    listItemTemp_type_tree(0x01),

    /**
     * 别名：列表框
     * listBox
     */
    listItemTemp_type_listBox(0x02),

    /**
     * 别名：列表头
     * list 列表头
     */
    listItemTemp_type_list_head(0x04),

    /**
     * 别名：列表项
     * list 列表项
     */
    listItemTemp_type_list_item(0x08),

    /**
     * 别名：列表视图组
     * listView 列表视组
     */
    listItemTemp_type_listView_group(0x10),

    /**
     * 别名：列表视图项
     * listView 列表视项
     */
    listItemTemp_type_listView_item(0x20),

    /**
     * 别名：列表头和列表项
     * list (列表头)与(列表项)组合
     */
    listItemTemp_type_list(listItemTemp_type_list_head.getValue() | listItemTemp_type_list_item.getValue()),

    /**
     * 别名：列表视图组和项
     * listView (列表视组)与(列表视项)组合
     */
    listItemTemp_type_listView(listItemTemp_type_listView_group.getValue() | listItemTemp_type_listView_item.getValue());

    private final int value;

    ListItemTempType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ListItemTempType fromValue(int value) {
        for (ListItemTempType type : values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }
}
