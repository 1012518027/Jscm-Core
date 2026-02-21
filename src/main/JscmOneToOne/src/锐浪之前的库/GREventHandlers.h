////////////////////////////////////////////////////////////////////////////////
//
//  锐浪报表 - 事件处理类示例
//  
//  说明: 继承三个事件基类并实现所有事件回调
//        使用时只需包含此文件并继承相应类即可
//
////////////////////////////////////////////////////////////////////////////////

#pragma once

#include "GR_OCX_Integrated.h"

////////////////////////////////////////////////////////////////////////////////
// 1. 报表主对象事件处理类
////////////////////////////////////////////////////////////////////////////////

class CGridppReportEventHandler : public CIGridppReportEvents
{
public:
    // 数据处理事件
    virtual void OnInitialize() override {
        // 报表初始化时触发
    }
    
    virtual void OnFetchRecord() override {
        // 取记录时触发
    }
    
    virtual void OnBeforePostRecord() override {
        // 提交记录前触发
    }
    
    virtual void OnBeforeSort(const wchar_t* SortFields) override {
        // 排序前触发，SortFields 为排序字段
    }
    
    virtual void OnRuntimeError(long ErrorID, const wchar_t* ErrorMsg) override {
        // 运行时错误，ErrorID 为错误码，ErrorMsg 为错误信息
    }

    // 记录遍历事件
    virtual void OnProcessBegin() override {
        // 处理开始
    }
    
    virtual void OnProcessEnd() override {
        // 处理结束
    }
    
    virtual void OnGroupBegin(void* pSender) override {
        // 分组开始
    }
    
    virtual void OnGroupEnd(void* pSender) override {
        // 分组结束
    }
    
    virtual void OnProcessRecord() override {
        // 处理每条记录
    }

    // 页面生成事件
    virtual void OnGeneratePagesBegin() override {
        // 开始生成页面
    }
    
    virtual void OnGeneratePagesEnd() override {
        // 结束生成页面
    }
    
    virtual void OnPageProcessRecord() override {
        // 页面记录处理
    }
    
    virtual void OnPageStart() override {
        // 页面开始
    }
    
    virtual void OnPageEnd() override {
        // 页面结束
    }

    // 显示格式化事件
    virtual void OnSectionFormat(void* pSender) override {
        // 节格式化
    }
    
    virtual void OnFieldGetDisplayText(void* pSender) override {
        // 字段获取显示文本
    }
    
    virtual void OnTextBoxGetDisplayText(void* pSender) override {
        // 文本框获取显示文本
    }

    // 自定义绘制事件
    virtual void OnControlCustomDraw(void* pSender, void* pGraphics) override {
        // 控件自定义绘制
    }

    // 图表事件
    virtual void OnChartRequestData(void* pSender) override {
        // 图表请求数据
    }

    // 打印事件
    virtual void OnPrintBegin() override {
        // 打印开始
    }
    
    virtual void OnPrintEnd() override {
        // 打印结束
    }
    
    virtual void OnPrintPage(long PageNo) override {
        // 打印页面，PageNo 为页码
    }
    
    virtual void OnPrintAborted() override {
        // 打印中止
    }

    // 导出事件
    virtual void OnExportBegin(void* pOptionObject) override {
        // 导出开始
    }
    
    virtual void OnExportEnd(void* pOptionObject) override {
        // 导出结束
    }

    // 窗口事件
    virtual void OnShowPreviewWnd(void* pPrintViewer) override {
        // 显示预览窗口
    }
    
    virtual void OnShowDesignerWnd(void* pDesigner) override {
        // 显示设计器窗口
    }

    // 超链接事件
    virtual void OnHyperlinkClick(void* pSender, const wchar_t* HyperlinkText, int FromPreviewPage) override {
        // 超链接点击，HyperlinkText 为链接文本
    }
};

////////////////////////////////////////////////////////////////////////////////
// 2. 查询显示器事件处理类
////////////////////////////////////////////////////////////////////////////////

class CGRDisplayViewerEventHandler : public CIGRDisplayViewerEvents
{
public:
    // 基础事件
    virtual void Click() override {
        // 点击
    }
    
    virtual void DblClick() override {
        // 双击
    }
    
    virtual void KeyDown(long Shift, longKey) override {
        // 键按下
    }
    
    virtual void KeyPress(char Key) override {
        // 键按下并释放
    }
    
    virtual void KeyUp(long Shift, long Key) override {
        // 键释放
    }
    
    virtual void MouseDown(long Button, long Shift, long xPos, long yPos) override {
        // 鼠标按下
    }
    
    virtual void MouseUp(long Button, long Shift, long xPos, long yPos) override {
        // 鼠标释放
    }
    
    virtual void MouseMove(long Shift, long xPos, long yPos) override {
        // 鼠标移动
    }

    // 部件框事件
    virtual void ControlClick(void* pSender) override {
        // 部件点击
    }
    
    virtual void ControlDblClick(void* pSender) override {
        // 部件双击
    }

    // 报表节事件
    virtual void SectionClick(void* pSender) override {
        // 节点击
    }
    
    virtual void SectionDblClick(void* pSender) override {
        // 节双击
    }

    // 单元格事件
    virtual void ContentCellClick(void* pSender) override {
        // 内容单元格点击
    }
    
    virtual void ContentCellDblClick(void* pSender) override {
        // 内容单元格双击
    }
    
    virtual void TitleCellClick(void* pSender) override {
        // 标题单元格点击
    }
    
    virtual void TitleCellDblClick(void* pSender) override {
        // 标题单元格双击
    }

    // 列事件
    virtual void ColumnLayoutChange() override {
        // 列布局改变
    }

    // 选中事件
    virtual void SelectionCellChange(long OldRow, long OldCol) override {
        // 选中单元格改变
    }

    // 图表事件
    virtual void ChartClickSeries(void* pSender, long SeriesIndex, long GroupIndex) override {
        // 图表系列点击
    }
    
    virtual void ChartDblClickSeries(void* pSender, long SeriesIndex, long GroupIndex) override {
        // 图表系列双击
    }
    
    virtual void ChartClickLegend(void* pSender, long SeriesIndex, long GroupIndex) override {
        // 图表图例点击
    }
    
    virtual void ChartDblClickLegend(void* pSender, long SeriesIndex, long GroupIndex) override {
        // 图表图例双击
    }

    // 数据事件
    virtual void BatchFetchRecord() override {
        // 批量获取记录
    }

    // 状态事件
    virtual long StatusChange() override {
        // 状态改变，返回 0
        return 0;
    }

    // 工具栏事件
    virtual void CloseButtonClick() override {
        // 关闭按钮点击
    }
    
    virtual void CustomButtonClick(long BtnID) override {
        // 自定义按钮点击，BtnID 为按钮ID
    }
    
    virtual void ToolbarCommandClick(long ControlType) override {
        // 工具栏命令点击
    }
};

////////////////////////////////////////////////////////////////////////////////
// 3. 打印显示器事件处理类
////////////////////////////////////////////////////////////////////////////////

class CGRPrintViewerEventHandler : public CIGRPrintViewerEvents
{
public:
    // 基础事件
    virtual void Click() override {
        // 点击
    }
    
    virtual void DblClick() override {
        // 双击
    }
    
    virtual void KeyDown(long Shift, long Key) override {
        // 键按下
    }
    
    virtual void KeyPress(char Key) override {
        // 键按下并释放
    }
    
    virtual void KeyUp(long Shift, long Key) override {
        // 键释放
    }
    
    virtual void MouseDown(long Button, long Shift, long xPos, long yPos) override {
        // 鼠标按下
    }
    
    virtual void MouseUp(long Button, long Shift, long xPos, long yPos) override {
        // 鼠标释放
    }
    
    virtual void MouseMove(long Shift, long xPos, long yPos) override {
        // 鼠标移动
    }

    // 页面事件
    virtual void CurPageChange(long NewCurPageNo) override {
        // 当前页改变，NewCurPageNo 为新页码
    }
    
    virtual long StatusChange() override {
        // 状态改变，返回 0
        return 0;
    }

    // 标尺事件
    virtual void RulerClick(int IsHorzRuler) override {
        // 标尺点击，IsHorzRuler 为是否水平标尺
    }
    
    virtual void RulerDblClick(int IsHorzRuler) override {
        // 标尺双击
    }

    // 页事件
    virtual void PageClick() override {
        // 页面点击
    }
    
    virtual void PageDblClick() override {
        // 页面双击
    }

    // 工具栏事件
    virtual void CloseButtonClick() override {
        // 关闭按钮点击
    }
    
    virtual void CustomButtonClick(long BtnID) override {
        // 自定义按钮点击
    }
    
    virtual void ToolbarCommandClick(long ControlType) override {
        // 工具栏命令点击
    }
};

////////////////////////////////////////////////////////////////////////////////
// 使用示例
////////////////////////////////////////////////////////////////////////////////
// 
// 1. 创建事件处理类实例
// CGridppReportEventHandler* pReportHandler = new CGridppReportEventHandler();
// CGRDisplayViewerEventHandler* pDisplayHandler = new CGRDisplayViewerEventHandler();
// CGRPrintViewerEventHandler* pPrintHandler = new CGRPrintViewerEventHandler();
//
// 2. 绑定事件
// CIGridppReport report;
// report.m_pDispatch = pDispatch;
// report.BindEvent(pReportHandler);
//
// CIGRDisplayViewer displayViewer;
// displayViewer.m_pDispatch = pDisplayDispatch;
// displayViewer.BindEvent(pDisplayHandler);
//
// CIGRPrintViewer printViewer;
// printViewer.m_pDispatch = pPrintDispatch;
// printViewer.BindEvent(pPrintHandler);
//
////////////////////////////////////////////////////////////////////////////////
