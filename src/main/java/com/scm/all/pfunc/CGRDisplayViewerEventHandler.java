package com.scm.all.pfunc;

/**
 * 报表回调类  查询显示器启动以后可以绑定成功！
 */
public class CGRDisplayViewerEventHandler {
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


// 部件框事件

    public void controlClick(int pSender) {
        return;
    }

    public void controlDblClick(int pSender) {
        return;
    }


// 报表节事件

    public void sectionClick(int pSender) {
        return;
    }

    public void sectionDblClick(int pSender) {
        return;
    }


// 单元格事件

    public void contentCellClick(int pSender) {
        return;
    }

    public void contentCellDblClick(int pSender) {
        return;
    }

    public void titleCellClick(int pSender) {
        return;
    }

    public void titleCellDblClick(int pSender) {
        return;
    }


// 列事件

    public void columnLayoutChange() {
        return;
    }


// 选中事件

    public void selectionCellChange(long oldRow, long oldCol) {
        return;
    }


// 图表事件

    public void chartClickSeries(int pSender, long seriesIndex, long groupIndex) {
        return;
    }

    public void chartDblClickSeries(int pSender, long seriesIndex, long groupIndex) {
        return;
    }

    public void chartClickLegend(int pSender, long seriesIndex, long groupIndex) {
        return;
    }

    public void chartDblClickLegend(int pSender, long seriesIndex, long groupIndex) {
        return;
    }


// 数据事件

    public void batchFetchRecord() {
        return;
    }


// 状态事件

    public int statusChange() {
        return 0;
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
