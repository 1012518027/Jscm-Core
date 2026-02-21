package com.scm.all.pfunc.Nonvirtual;

/**
 * 窗体注册事件 完毕
 */
interface WindowEventCallBack_Nonvirtual {

    boolean OnWndMouseMove(int hWindow, long flags, int x, int y);
    boolean OnWndDrawWindow(int hWindow, long hDraw);
    boolean OnWndClose(int hWindow);
    boolean OnWndDestroy(int hWindow);
    boolean OnWndNCDestroy(int hWindow);
    boolean OnWndLButtonDown(int hWindow, long flags, int x, int y);
    boolean OnWndLButtonUp(int hWindow, long flags, int x, int y);
    boolean OnWndRButtonDown(int hWindow, long flags, int x, int y);
    boolean OnWndRButtonUp(int hWindow, long flags, int x, int y);
    boolean OnWndLButtonDBClick(int hWindow, long flags, int x, int y);
    boolean OnWndRButtonDBClick(int hWindow, long flags, int x, int y);
    boolean OnWndMouseHover(int hWindow, long flags, int x, int y);
    boolean OnWndMouseLeave(int hWindow);
    boolean OnWndMouseWheel(int hWindow, long flags, int x, int y);
    boolean OnWndCaptureChanged(int hWindow, int hWnd);
    boolean OnWndKeyDown(int hWindow, int wParam,int lParam);
    boolean OnWndKeyUp(int hWindow, int wParam,int lParam);
    boolean OnWndChar(int hWindow, int wParam,int lParam);
    boolean OnWndSize(int hWindow, long flags, int cx, int cy);
    boolean OnWndExitSizeMove(int hWindow);
    boolean OnWndTimer(int hWindow, long nIDEvent);
    boolean OnWndSetFocus(int hWindow);
    boolean OnWndKillFocus(int hWindow);
    boolean OnWndSetCursor(int hWindow, int wParam,int lParam);
    boolean OnDropFiles(int hWindow, String filesInfo);
    boolean OnWndTrayIcon(int hWindow, int wParam, int lParam);
    boolean OnWndOther(int hWindow, int wParam,int lParam);
    boolean OnWndProc(int hWindow,long message, int wParam,int lParam);
    boolean OnWndXCTimer(int hWindow,long nTimerID);
    boolean OnWndMenuPopup(int hWindow,int hMenu);
    boolean OnWndSetFocusEle(int hWindow, int hEle);
    boolean OnWndMenuPopupWnd(int hWindow, int hMenu,int hWindow2,int nParentID);
    boolean OnWndMenuSelect(int hWindow, int nID);
    boolean OnWndMenuDrawBackground(int hWindow, long hDraw, int hMenu,int hWindow2,int nParentID);
    boolean OnWndMenuSelectExit(int hWindow);
    boolean OnMenuDrawItemWindows(int hWindow1,long hDraw,int hMenu,int hWindow2 ,int nID,int nState,int hIcon,String pText);
    boolean OnWndFloatPane(int hWindow, int hFloatWnd, int hPane);
    boolean OnWndDrawWindowEnd(int hWindow, long hDraw);
    boolean OnWndDrawWindowDisplay(int hWindow);
    boolean OnWndDocPopup(int hFrameWindow, int hWindowDock,  int hPane);
    boolean OnWndFloatWndDrag(int hFrameWindow, int hFloatWnd, int[] hArray);
    boolean OnWndPaneShow(int hFrameWindow, int hPane, int bShow);
    boolean OnWndLayoutViewRect(int hFrameWindow, int width, int height);
    boolean OnWndMenuExit(int hWindow);

}
