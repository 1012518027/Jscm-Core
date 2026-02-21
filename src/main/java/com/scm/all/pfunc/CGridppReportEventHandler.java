package com.scm.all.pfunc;

/**
 * 报表回调  初始化的下面使用绑定事件可以加载到初始化
 */
public class CGridppReportEventHandler {
    // 数据处理事件

    public void onInitialize() {
        return;
    }

    public void onFetchRecord() {
        return;
    }

    public void onBeforePostRecord() {
        return;
    }

    public void onBeforeSort(String sortFields) {
        return;
    }

    public void onRuntimeError(int errorID, String errorMsg) {
        return;
    }


// 记录遍历事件

    public void onProcessBegin() {
        return;
    }

    public void onProcessEnd() {
        return;
    }

    public void onGroupBegin(int pSender) {
        return;
    }

    public void onGroupEnd(int pSender) {
        return;
    }

    public void onProcessRecord() {
        return;
    }


// 页面生成事件

    public void onGeneratePagesBegin() {
        return;
    }

    public void onGeneratePagesEnd() {
        return;
    }

    public void onPageProcessRecord() {
        return;
    }

    public void onPageStart() {
        return;
    }

    public void onPageEnd() {
        return;
    }


// 显示格式化事件

    public void onSectionFormat(int pSender) {
        return;
    }

    public void onFieldGetDisplayText(int pSender) {
        return;
    }

    public void onTextBoxGetDisplayText(int pSender) {
        return;
    }


// 自定义绘制事件

    public void onControlCustomDraw(int pSender, int pGraphics) {
        return;
    }


// 图表事件

    public void onChartRequestData(int pSender) {
        return;
    }


// 打印事件

    public void onPrintBegin() {
        return;
    }

    public void onPrintEnd() {
        return;
    }

    public void onPrintPage(long pageNo) {
        return;
    }

    public void onPrintAborted() {
        return;
    }


// 导出事件

    public void onExportBegin(int pOptionObject) {
        return;
    }

    public void onExportEnd(int pOptionObject) {
        return;
    }


// 窗口事件

    public void onShowPreviewWnd(int pPrintViewer) {
        return;
    }


    /**
     * 设计器窗口 禁止使用
     * @param pDesigner COMid
     */
    public void onShowDesignerWnd(int pDesigner) {
        return;
    }

    /**
     * 链接点击事件
     * @param pSender COMid
     * @param HyperlinkText 链接文本
     * @param FromPreviewPage 0:非预览窗口 1:预览窗口
     */
    public void onHyperlinkClick(int pSender,String HyperlinkText,int FromPreviewPage){return;}
}
