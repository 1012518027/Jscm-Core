package com.scm.all.struct;

import java.util.Arrays;

public class TagEditor_color_ {
    // 对齐线 - 是否显示箭头
    public boolean bAlignLineArrow;

    // 展开收缩按钮, 是否缩进
    public boolean bAlignLineBtnIndent;

    // 填充缩进TAB背景
    public boolean bTabFillColor;

    // 是否使用彩虹线
    public boolean bAlignLineColor7;

    // 侧边栏 - 背景色1, 显示断点
    public long clrMargin1;

    // 侧边栏 - 背景色2, 显示行号
    public long clrMargin2;

    // 侧边栏 - 文本色 - 行号颜色
    public long clrMargin_text;

    // 侧边栏 - 断点色
    public long clrMargin_breakpoint;

    // 侧边栏 - 断点描边色
    public long clrMargin_breakpointBorder;

    // 侧边栏 - 调试位置箭头
    public long clrMargin_runRowArrow;

    // 侧边栏 - 当前行指示色,光标所在行
    public long clrMargin_curRow;

    // 侧边栏 - 错误指示色
    public long clrMargin_error;

    // 突出显示当前行颜色
    public long clrCurRowFull;

    // 匹配选择文本背景色
    public long clrMatchSel;

    // 彩虹线
    public long[] clrAlignLines = new long[7];

    // 对齐线 - 选择内容块
    public long clrAlignLineSel;

    // 函数分割线颜色
    public long clrFunSplitLine;

    // 缩进TAB
    public long clrIndentTab;

    // 缩进遇到空格
    public long clrIndentSpace;

    // 弹出提示窗口背景色
    public long clrTipsDlg;

    // 弹出提示窗口描边色
//    public long clrTipsDlgBorder;

    // 函数分割线-填充模式: 0:无, 1:线, 2:填充
//    public int funSplitLineMode;

    // 代码缩进模式(TAB)  自由缩进  固定缩进
    public int codeIndent;

    // 系统关键字  return, break, for
    public int styleSys;

    // 函数
    public int styleFunction;

    // 变量
    public int styleVar;

    // 基础数据类型  int, byte, char
    public int styleDataType;

    // 类  class
    public int styleClass;

    // 宏
    public int styleMacro;

    // 枚举
    public int styleEnum;

    // 数字
    public int styleNumber;

    // 字符串
    public int styleString;

    // 注释
    public int styleComment;

    // 标点符号
    public int StylePunctuation;

    @Override
    public String toString() {
        return "TagEditor_color_{" +
                "bAlignLineArrow=" + bAlignLineArrow +
                ", bAlignLineBtnIndent=" + bAlignLineBtnIndent +
                ", bTabFillColor=" + bTabFillColor +
                ", bAlignLineColor7=" + bAlignLineColor7 +
                ", clrMargin1=" + clrMargin1 +
                ", clrMargin2=" + clrMargin2 +
                ", clrMargin_text=" + clrMargin_text +
                ", clrMargin_breakpoint=" + clrMargin_breakpoint +
                ", clrMargin_breakpointBorder=" + clrMargin_breakpointBorder +
                ", clrMargin_runRowArrow=" + clrMargin_runRowArrow +
                ", clrMargin_curRow=" + clrMargin_curRow +
                ", clrMargin_error=" + clrMargin_error +
                ", clrCurRowFull=" + clrCurRowFull +
                ", clrMatchSel=" + clrMatchSel +
                ", clrAlignLines=" + Arrays.toString(clrAlignLines) +
                ", clrAlignLineSel=" + clrAlignLineSel +
                ", clrFunSplitLine=" + clrFunSplitLine +
                ", clrIndentTab=" + clrIndentTab +
                ", clrIndentSpace=" + clrIndentSpace +
                ", clrTipsDlg=" + clrTipsDlg +
                ", codeIndent=" + codeIndent +
                ", styleSys=" + styleSys +
                ", styleFunction=" + styleFunction +
                ", styleVar=" + styleVar +
                ", styleDataType=" + styleDataType +
                ", styleClass=" + styleClass +
                ", styleMacro=" + styleMacro +
                ", styleEnum=" + styleEnum +
                ", styleNumber=" + styleNumber +
                ", styleString=" + styleString +
                ", styleComment=" + styleComment +
                ", stylePunctuation=" + StylePunctuation +
                '}';
    }
}
