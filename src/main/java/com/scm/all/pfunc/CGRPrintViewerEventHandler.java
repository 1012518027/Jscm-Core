package com.scm.all.pfunc;

/**
 * 表格打印显示回调
 */
public class CGRPrintViewerEventHandler {
    // 基础事件

    public void click() {
        return;
    }

    public void dblClick() {
        return;
    }

    public void keyDown(long shift, long key) {
        return;
    }

    public void keyPress(int key) {
        return;
    }

    public void keyUp(long shift, long key) {
        return;
    }

    public void mouseDown(long button, long shift, long xPos, long yPos) {
        return;
    }

    public void mouseUp(long button, long shift, long xPos, long yPos) {
        return;
    }

    public void mouseMove(long shift, long xPos, long yPos) {
        return;
    }


// 页面事件

    public void curPageChange(long newCurPageNo) {
        return;
    }

    public int statusChange() {
        return 0;
    }


// 标尺事件

    public void rulerClick(int isHorzRuler) {
        return;
    }

    public void rulerDblClick(int isHorzRuler) {
        return;
    }


// 页事件

    public void pageClick() {
        return;
    }

    public void pageDblClick() {
        return;
    }


// 工具栏事件

    public void closeButtonClick() {
        return;
    }

    public void customButtonClick(long btnID) {
        return;
    }

    public void toolbarCommandClick(long controlType) {
        return;
    }
}
