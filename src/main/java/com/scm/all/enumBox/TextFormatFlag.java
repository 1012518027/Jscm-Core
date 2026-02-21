package com.scm.all.enumBox;

/**
 * 文本对齐枚举（对应 C++ 枚举 textFormatFlag_）
 * Group_textFormatFlag_ 文本对齐(textFormatFlag_)
 * group_enum
 */
public enum TextFormatFlag {
    /**
     * 别名：左对齐
     * 左对齐
     */
    textAlignFlag_left(0),

    /**
     * 别名：顶对齐
     * 垂直顶对齐
     */
    textAlignFlag_top(0),

    /**
     * 内部保留
     */
    textAlignFlag_left_top(0x4000),

    /**
     * 别名：水平居中
     * 水平居中
     */
    textAlignFlag_center(0x1),

    /**
     * 别名：右对齐
     * 右对齐.
     */
    textAlignFlag_right(0x2),

    /**
     * 别名：垂直居中
     * 垂直居中
     */
    textAlignFlag_vcenter(0x4),

    /**
     * 别名：底对齐
     * 垂直底对齐
     */
    textAlignFlag_bottom(0x8),

    /**
     * 别名：从右向左
     * 从右向左顺序显示文本
     */
    textFormatFlag_DirectionRightToLeft(0x10),

    /**
     * 别名：禁止换行
     * 禁止换行
     */
    textFormatFlag_NoWrap(0x20),

    /**
     * 别名：垂直显示
     * 垂直显示文本
     */
    textFormatFlag_DirectionVertical(0x40),

    /**
     * 别名：向外延伸
     * 允许部分字符延伸该字符串的布局矩形。默认情况下，将重新定位字符以避免任何延伸
     */
    textFormatFlag_NoFitBlackBox(0x80),

    /**
     * 别名：显示控制字符
     * 控制字符（如从左到右标记）随具有代表性的标志符号一起显示在输出中。
     */
    textFormatFlag_DisplayFormatControl(0x100),

    /**
     * 别名：禁止回退字体
     * 对于请求的字体中不支持的字符，禁用回退到可选字体。缺失的任何字符都用缺失标志符号的字体显示，通常是一个空的方块
     */
    textFormatFlag_NoFontFallback(0x200),

    /**
     * 别名：测量包含空格
     * 包括每一行结尾处的尾随空格。在默认情况下，MeasureString 方法返回的边框都将排除每一行结尾处的空格。设置此标记以便在测定时将空格包括进去
     */
    textFormatFlag_MeasureTrailingSpaces(0x400),

    /**
     * 别名：禁止超过行高
     * 如果内容显示高度不够一行,那么不显示
     */
    textFormatFlag_LineLimit(0x800),

    /**
     * 别名：禁止裁剪
     * 允许显示标志符号的伸出部分和延伸到边框外的未换行文本。在默认情况下，延伸到边框外侧的所有文本和标志符号部分都被剪裁
     */
    textFormatFlag_NoClip(0x1000),

    /**
     * 别名：以字符为单位去尾
     * 以字符为单位去尾
     */
    textTrimming_Character(0x40000),

    /**
     * 别名：以单词为单位去尾
     * 以单词为单位去尾
     */
    textTrimming_Word(0x80000),

    /**
     * 别名：以字符为单位去尾加省略号
     * 以字符为单位去尾,省略部分使用且略号表示
     */
    textTrimming_EllipsisCharacter(0x8000),

    /**
     * 别名：以单词为单位去尾加省略号
     * 以单词为单位去尾,省略部分使用省略号表示
     */
    textTrimming_EllipsisWord(0x10000),

    /**
     * 别名：省略中间部分
     * 略去字符串中间部分，保证字符的首尾都能够显示
     */
    textTrimming_EllipsisPath(0x20000);

    private final int value;

    TextFormatFlag(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TextFormatFlag fromValue(int value) {
        for (TextFormatFlag flag : values()) {
            if (flag.value == value) {
                return flag;
            }
        }
        return null;
    }
}
