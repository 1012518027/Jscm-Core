package com.scm.all.enumBox;

/**
 * Windows窗口样式常量定义，对应C语言中的窗口样式宏
 * 这些常量用于指定窗口的外观和行为特性
 */
public interface WindowsStyles {
    // 基础窗口样式
    long WS_OVERLAPPED       = 0x00000000L;
    long WS_POPUP            = 0x80000000L;
    long WS_CHILD            = 0x40000000L;
    long WS_MINIMIZE         = 0x20000000L;
    long WS_VISIBLE          = 0x10000000L;
    long WS_DISABLED         = 0x08000000L;
    long WS_CLIPSIBLINGS     = 0x04000000L;
    long WS_CLIPCHILDREN     = 0x02000000L;
    long WS_MAXIMIZE         = 0x01000000L;

    // 边框和标题栏样式
    long WS_CAPTION          = 0x00C00000L;     // WS_BORDER | WS_DLGFRAME 的组合
    long WS_BORDER           = 0x00800000L;
    long WS_DLGFRAME         = 0x00400000L;

    // 滚动条样式
    long WS_VSCROLL          = 0x00200000L;
    long WS_HSCROLL          = 0x00100000L;

    // 系统菜单和边框样式
    long WS_SYSMENU          = 0x00080000L;
    long WS_THICKFRAME       = 0x00040000L;
    long WS_GROUP            = 0x00020000L;
    long WS_TABSTOP          = 0x00010000L;

    // 最大化/最小化按钮样式
    long WS_MINIMIZEBOX      = 0x00020000L;
    long WS_MAXIMIZEBOX      = 0x00010000L;

    // 兼容别名
    long WS_TILED            = WS_OVERLAPPED;
    long WS_ICONIC           = WS_MINIMIZE;
    long WS_SIZEBOX          = WS_THICKFRAME;
    long WS_TILEDWINDOW      = 0x00CF0000L;     // WS_OVERLAPPEDWINDOW的等效值
}
