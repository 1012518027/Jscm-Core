package com.scm.all.pfunc;

/**
 * 窗体注册事件 完毕
 */
public class WindowEventCallBack {

    /**
     窗口_鼠标移动事件
     @param hWindow 窗口句柄
     @param flags 鼠标状态标志
     @param x 鼠标 X 坐标
     @param y 鼠标 Y 坐标
     @return 返回结果
     **/
    public boolean OnWndMouseMove (int hWindow, long flags, int x, int y){return false;}
    /**
     窗口_绘制事件
     @param hWindow 窗口句柄
     @param hDraw 图形绘制句柄
     @return 返回结果
     **/
    public boolean OnWndDrawWindow (int hWindow, long hDraw){return false;}
    /**
     窗口_关闭事件
     @param hWindow 窗口句柄
     @return 返回结果
     **/
    public boolean OnWndClose (int hWindow){return false;}
    /**
     窗口_销毁事件
     @param hWindow 窗口句柄
     @return 返回结果
     **/
    public boolean OnWndDestroy (int hWindow){return false;}
    /**
     窗口_非客户区销毁事件
     @param hWindow 窗口句柄
     @return 返回结果
     **/
    public boolean OnWndNCDestroy (int hWindow){return false;}
    /**
     窗口_鼠标左键按下事件
     @param hWindow 窗口句柄
     @param flags 鼠标状态标志
     @param x 鼠标 X 坐标
     @param y 鼠标 Y 坐标
     @return 返回结果
     **/
    public boolean OnWndLButtonDown (int hWindow, long flags, int x, int y){return false;}
    /**
     窗口_鼠标左键弹起事件
     @param hWindow 窗口句柄
     @param flags 鼠标状态标志
     @param x 鼠标 X 坐标
     @param y 鼠标 Y 坐标
     @return 返回结果
     **/
    public boolean OnWndLButtonUp (int hWindow, long flags, int x, int y){return false;}
    /**
     窗口_鼠标右键按下事件
     @param hWindow 窗口句柄
     @param flags 鼠标状态标志
     @param x 鼠标 X 坐标
     @param y 鼠标 Y 坐标
     @return 返回结果
     **/
    public boolean OnWndRButtonDown (int hWindow, long flags, int x, int y){return false;}
    /**
     窗口_鼠标右键弹起事件
     @param hWindow 窗口句柄
     @param flags 鼠标状态标志
     @param x 鼠标 X 坐标
     @param y 鼠标 Y 坐标
     @return 返回结果
     **/
    public boolean OnWndRButtonUp (int hWindow, long flags, int x, int y){return false;}
    /**
     窗口_鼠标左键双击事件
     @param hWindow 窗口句柄
     @param flags 鼠标状态标志
     @param x 鼠标 X 坐标
     @param y 鼠标 Y 坐标
     @return 返回结果
     **/
    public boolean OnWndLButtonDBClick (int hWindow, long flags, int x, int y){return false;}
    /**
     窗口_鼠标右键双击事件
     @param hWindow 窗口句柄
     @param flags 鼠标状态标志
     @param x 鼠标 X 坐标
     @param y 鼠标 Y 坐标
     @return 返回结果
     **/
    public boolean OnWndRButtonDBClick (int hWindow, long flags, int x, int y){return false;}
    /**
     窗口_鼠标悬停事件
     @param hWindow 窗口句柄
     @param flags 鼠标状态标志
     @param x 鼠标 X 坐标
     @param y 鼠标 Y 坐标
     @return 返回结果
     **/
    public boolean OnWndMouseHover (int hWindow, long flags, int x, int y){return false;}
    /**
     窗口鼠标离开消息
     @param hWindow 窗口句柄
     @return 处理结果，返回 false 表示未处理
     **/
    public boolean OnWndMouseLeave (int hWindow){return false;}
    /**
     窗口鼠标滚轮滚动消息
     @param hWindow 窗口句柄
     @param flags 消息参数，详见 MSDN WM_MOUSEWHEEL 的 wParam 说明
     @param x 鼠标 X 坐标
     @param y 鼠标 Y 坐标
     @return 处理结果，返回 false 表示未处理
     **/
    public boolean OnWndMouseWheel (int hWindow, long flags, int x, int y){return false;}
    /**
     窗口鼠标捕获改变消息
     @param hWindow 窗口句柄
     @param hWnd 获得鼠标捕获的窗口句柄
     @return 处理结果，返回 false 表示未处理
     **/
    public boolean OnWndCaptureChanged (int hWindow, int hWnd){return false;}
    /**
     窗口键盘按下消息
     @param hWindow 窗口句柄
     @param wParam 消息参数，详见 MSDN WM_KEYDOWN 的 wParam 说明
     @param lParam 消息参数，详见 MSDN WM_KEYDOWN 的 lParam 说明
     @return 处理结果，返回 false 表示未处理
     **/
    public boolean OnWndKeyDown (int hWindow, int wParam,int lParam){return false;}
    /**
     窗口键盘弹起消息
     @param hWindow 窗口句柄
     @param wParam 消息参数，详见 MSDN WM_KEYUP 的 wParam 说明
     @param lParam 消息参数，详见 MSDN WM_KEYUP 的 lParam 说明
     @return 处理结果，返回 false 表示未处理
     **/
    public boolean OnWndKeyUp (int hWindow, int wParam,int lParam){return false;}
    /**
     窗口字符输入消息
     @param hWindow 窗口句柄
     @param wParam 消息参数，详见 MSDN WM_CHAR 的 wParam 说明
     @param lParam 消息参数，详见 MSDN WM_CHAR 的 lParam 说明
     @return 处理结果，返回 false 表示未处理
     **/
    public boolean OnWndChar (int hWindow, int wParam,int lParam){return false;}
    /**
     窗口大小改变消息
     @param hWindow 窗口句柄
     @param flags 消息参数，详见 MSDN WM_SIZE 的 wParam 说明
     @param cx 窗口新宽度
     @param cy 窗口新高度
     @return 处理结果，返回 false 表示未处理
     **/
    public boolean OnWndSize (int hWindow, long flags, int cx, int cy){return false;}
    /**
     窗口退出移动或调整大小模式循环消息
     @param hWindow 窗口句柄
     @return 处理结果，返回 false 表示未处理
     **/
    public boolean OnWndExitSizeMove (int hWindow){return false;}
    /**
     窗口定时器消息
     @param hWindow 窗口句柄
     @param nIDEvent 定时器标识符
     @return 处理结果，返回 false 表示未处理
     **/
    public boolean OnWndTimer (int hWindow, long nIDEvent){return false;}
    /**
     窗口获得焦点消息
     @param hWindow 窗口句柄
     @return 处理结果，返回 false 表示未处理
     **/
    public boolean OnWndSetFocus (int hWindow){return false;}
    /**
     窗口失去焦点消息
     @param hWindow 窗口句柄
     @return 处理结果，返回 false 表示未处理
     **/
    public boolean OnWndKillFocus (int hWindow){return false;}
    /**
     窗口设置鼠标光标消息
     @param hWindow 窗口句柄
     @param wParam 消息参数，详见 MSDN WM_SETCURSOR 的 wParam 说明
     @param lParam 消息参数，详见 MSDN WM_SETCURSOR 的 lParam 说明
     @return 处理结果，返回 false 表示未处理
     **/
    public boolean OnWndSetCursor (int hWindow, int wParam,int lParam){return false;}
    /**
     拖动文件到窗口消息（需启用 XWnd_EnableDragFiles ()）
     @param hWindow 窗口句柄
     @param filesInfo 拖放文件信息
     @return 处理结果，返回 false 表示未处理
     **/
    public boolean OnDropFiles (int hWindow, String filesInfo){return false;}
    /**
     托盘图标事件
     @param hWindow 窗口句柄
     @param wParam 消息参数
     @param lParam 消息参数
     @return 处理结果，返回 false 表示未处理
     **/
    public boolean OnWndTrayIcon (int hWindow, int wParam, int lParam){return false;}
    /**
     其他 Windows 系统消息（包含自定义消息）
     @param hWindow 窗口句柄
     @param wParam 消息参数
     @param lParam 消息参数
     @return 处理结果，返回 false 表示未处理
     **/
    public boolean OnWndOther (int hWindow, int wParam,int lParam){return false;}
    /**
     窗口消息过程
     @param hWindow 窗口句柄
     @param message 消息类型
     @param wParam 消息参数
     @param lParam 消息参数
     @return 处理结果，返回 false 表示未处理
     **/
    public boolean OnWndProc (int hWindow,long message, int wParam,int lParam){return false;}
    /**
     炫彩定时器消息（非系统定时器，需注册 XWM_XC_TIMER 接收）
     @param hWindow 窗口句柄
     @param nTimerID 定时器 ID
     @return 处理结果，返回 false 表示未处理
     **/
    public boolean OnWndXCTimer (int hWindow,long nTimerID){return false;}
    /**
     菜单弹出消息
     @param hWindow 窗口句柄
     @param hMenu 菜单句柄
     @return 处理结果，返回 false 表示未处理
     **/
    public boolean OnWndMenuPopup (int hWindow,int hMenu){return false;}
    /**
     指定元素获得焦点消息
     @param hWindow 窗口句柄
     @param hEle 将获得焦点的元素句柄
     @return 处理结果，返回 false 表示未处理
     **/
    public boolean OnWndSetFocusEle (int hWindow, int hEle){return false;}
    /**
     菜单弹出窗口消息
     @param hWindow 窗口句柄
     @param hMenu 菜单句柄
     @param hWindow2 弹出窗口句柄
     @param nParentID 父项 ID
     @return 处理结果，返回 false 表示未处理
     **/
    public boolean OnWndMenuPopupWnd (int hWindow, int hMenu,int hWindow2,int nParentID){return false;}
    /**
     菜单选择消息
     @param hWindow 窗口句柄
     @param nID 菜单项 ID
     @return 处理结果，返回 false 表示未处理
     **/
    public boolean OnWndMenuSelect (int hWindow, int nID){return false;}
    /**
     绘制菜单背景消息（需启用 XMenu_EnableDrawBackground ()）
     @param hWindow 窗口句柄
     @param hDraw 图形绘制句柄
     @param hMenu 菜单句柄
     @param hWindow2 当前菜单项的窗口句柄
     @param nParentID 父项 ID
     @return 处理结果，返回 false 表示未处理
     **/
    public boolean OnWndMenuDrawBackground (int hWindow, long hDraw, int hMenu,int hWindow2,int nParentID){return false;}
    /**
     菜单选择退出消息
     @param hWindow 窗口句柄
     @return 处理结果，返回 false 表示未处理
     **/
    public boolean OnWndMenuSelectExit (int hWindow){return false;}
    /**
     绘制菜单项事件消息（需启用 XMenu_EnableDrawItem ()）
     @param hWindow1 窗口句柄
     @param hDraw 图形绘制句柄
     @param hMenu 菜单句柄
     @param hWindow2 当前菜单项的窗口句柄
     @param nID 菜单项 ID
     @param nState 菜单项状态，详见 menu_item_flag_宏定义
     @param hIcon 菜单项图标句柄
     @param pText 菜单项文本
     @return 处理结果，返回 false 表示未处理
     **/
    public boolean OnMenuDrawItemWindows (int hWindow1,long hDraw,int hMenu,int hWindow2 ,int nID,int nState,int hIcon,String pText){return false;}
    /**
     浮动窗格消息
     @param hWindow 窗口句柄
     @param hFloatWnd 浮动窗口句柄
     @param hPane 窗格句柄
     @return 处理结果，返回 false 表示未处理
     **/
    public boolean OnWndFloatPane (int hWindow, int hFloatWnd, int hPane){return false;}
    /**
     窗口绘制完成消息
     @param hWindow 窗口句柄
     @param hDraw 图形绘制句柄
     @return 处理结果，返回 false 表示未处理
     **/
    public boolean OnWndDrawWindowEnd (int hWindow, long hDraw){return false;}
    /**
     窗口绘制完成并显示到屏幕消息
     @param hWindow 窗口句柄
     @return 处理结果，返回 false 表示未处理
     **/
    public boolean OnWndDrawWindowDisplay (int hWindow){return false;}
    /**
     框架窗口码头弹出窗格消息
     @param hFrameWindow 框架窗口句柄
     @param hWindowDock 弹出窗格窗口句柄
     @param hPane 窗格句柄
     @return 处理结果，返回 false 表示未处理
     **/
    public boolean OnWndDocPopup (int hFrameWindow, int hWindowDock, int hPane){return false;}
    /**
     浮动窗口拖动消息
     @param hFrameWindow 框架窗口句柄
     @param hFloatWnd 拖动的浮动窗口句柄
     @param hArray 窗格停靠提示窗口句柄数组，6 个成员分别为：[0] 中间十字，[1] 左侧，[2] 顶部，[3] 右侧，[4] 底部，[5] 停靠位置预览
     @return 处理结果，返回 false 表示未处理
     **/
    public boolean OnWndFloatWndDrag (int hFrameWindow, int hFloatWnd, int [] hArray){return false;}
    /**
     窗格显示隐藏消息
     @param hFrameWindow 框架窗口句柄
     @param hPane 窗格句柄
     @param bShow 是否显示（1 显示，0 隐藏）
     @return 处理结果，返回 false 表示未处理
     **/
    public boolean OnWndPaneShow (int hFrameWindow, int hPane, int bShow){return false;}
    /**
     框架窗口主视图坐标改变消息
     @param hFrameWindow 框架窗口句柄
     @param width 主视图宽度
     @param height 主视图高度
     @return 处理结果，返回 false 表示未处理
     **/
    public boolean OnWndLayoutViewRect (int hFrameWindow, int width, int height){return false;}
    /**
     菜单退出消息
     @param hWindow 窗口句柄
     @return 处理结果，返回 false 表示未处理
     **/
    public boolean OnWndMenuExit (int hWindow){return false;}

}
