////////////////////////////////////////////////////////////////////////////////
//
//  锐浪报表(Rich Grid Report) - 事件模版头文件
//  
//  模块名称: 锐浪报表
//  版本: 1.0
//  日期: 2023-04-20
//  作者: XCGUI
//
//  说明: 本文件定义了 Grid++Report 报表引擎的所有事件接口
//        用于处理报表生成、打印、导出等各个阶段的事件回调
//        本文件为独立文件，不依赖其他头文件
//
////////////////////////////////////////////////////////////////////////////////

#ifndef _IGR_EVENTS_H_
#define _IGR_EVENTS_H_

//================================================================================
// 报表主对象事件类
// 包含报表生成过程中所有可处理的事件
//================================================================================

// 报表主对象事件类
class CIGridppReportEvents
{
public:
	// 数据处理事件
	virtual void OnInitialize() { }
	virtual void OnFetchRecord() { }
	virtual void OnBeforePostRecord() { }
	virtual void OnBeforeSort(const wchar_t* SortFields) { (void)SortFields; }
	virtual void OnRuntimeError(long ErrorID, const wchar_t* ErrorMsg) { (void)ErrorID; (void)ErrorMsg; }

	// 记录遍历事件
	virtual void OnProcessBegin() { }
	virtual void OnProcessEnd() { }
	virtual void OnGroupBegin(void* pSender) { (void)pSender; }
	virtual void OnGroupEnd(void* pSender) { (void)pSender; }
	virtual void OnProcessRecord() { }

	// 页面生成事件
	virtual void OnGeneratePagesBegin() { }
	virtual void OnGeneratePagesEnd() { }
	virtual void OnPageProcessRecord() { }
	virtual void OnPageStart() { }
	virtual void OnPageEnd() { }

	// 显示格式化事件
	virtual void OnSectionFormat(void* pSender) { (void)pSender; }
	virtual void OnFieldGetDisplayText(void* pSender) { (void)pSender; }
	virtual void OnTextBoxGetDisplayText(void* pSender) { (void)pSender; }

	// 自定义绘制事件
	virtual void OnControlCustomDraw(void* pSender, void* pGraphics) { (void)pSender; (void)pGraphics; }

	// 图表事件
	virtual void OnChartRequestData(void* pSender) { (void)pSender; }

	// 打印事件
	virtual void OnPrintBegin() { }
	virtual void OnPrintEnd() { }
	virtual void OnPrintPage(long PageNo) { (void)PageNo; }
	virtual void OnPrintAborted() { }

	// 导出事件
	virtual void OnExportBegin(void* pOptionObject) { (void)pOptionObject; }
	virtual void OnExportEnd(void* pOptionObject) { (void)pOptionObject; }

	// 窗口事件
	virtual void OnShowPreviewWnd(void* pPrintViewer) { (void)pPrintViewer; }
	virtual void OnShowDesignerWnd(void* pDesigner) { (void)pDesigner; }

	// 超链接事件
	virtual void OnHyperlinkClick(void* pSender, const wchar_t* HyperlinkText, int FromPreviewPage) { (void)pSender; (void)HyperlinkText; (void)FromPreviewPage; }

	virtual ~CIGridppReportEvents() { }
};

//================================================================================
// 报表查询显示器事件类
// 包含查询显示器交互过程中所有可处理的事件
//================================================================================

class CIGRDisplayViewerEvents
{
public:
	// 基础事件
	virtual void Click() { }
	virtual void DblClick() { }
	virtual void KeyDown(long Shift, long Key) { (void)Shift; (void)Key; }
	virtual void KeyPress(char Key) { (void)Key; }
	virtual void KeyUp(long Shift, long Key) { (void)Shift; (void)Key; }
	virtual void MouseDown(long Button, long Shift, long xPos, long yPos) { (void)Button; (void)Shift; (void)xPos; (void)yPos; }
	virtual void MouseUp(long Button, long Shift, long xPos, long yPos) { (void)Button; (void)Shift; (void)xPos; (void)yPos; }
	virtual void MouseMove(long Shift, long xPos, long yPos) { (void)Shift; (void)xPos; (void)yPos; }

	// 部件框事件
	virtual void ControlClick(void* pSender) { (void)pSender; }
	virtual void ControlDblClick(void* pSender) { (void)pSender; }

	// 报表节事件
	virtual void SectionClick(void* pSender) { (void)pSender; }
	virtual void SectionDblClick(void* pSender) { (void)pSender; }

	// 单元格事件
	virtual void ContentCellClick(void* pSender) { (void)pSender; }
	virtual void ContentCellDblClick(void* pSender) { (void)pSender; }
	virtual void TitleCellClick(void* pSender) { (void)pSender; }
	virtual void TitleCellDblClick(void* pSender) { (void)pSender; }

	// 列事件
	virtual void ColumnLayoutChange() { }

	// 选中事件
	virtual void SelectionCellChange(long OldRow, long OldCol) { (void)OldRow; (void)OldCol; }

	// 图表事件
	virtual void ChartClickSeries(void* pSender, long SeriesIndex, long GroupIndex) { (void)pSender; (void)SeriesIndex; (void)GroupIndex; }
	virtual void ChartDblClickSeries(void* pSender, long SeriesIndex, long GroupIndex) { (void)pSender; (void)SeriesIndex; (void)GroupIndex; }
	virtual void ChartClickLegend(void* pSender, long SeriesIndex, long GroupIndex) { (void)pSender; (void)SeriesIndex; (void)GroupIndex; }
	virtual void ChartDblClickLegend(void* pSender, long SeriesIndex, long GroupIndex) { (void)pSender; (void)SeriesIndex; (void)GroupIndex; }

	// 数据事件
	virtual void BatchFetchRecord() { }

	// 状态事件
	virtual long StatusChange() { return 0; }

	// 工具栏事件
	virtual void CloseButtonClick() { }
	virtual void CustomButtonClick(long BtnID) { (void)BtnID; }
	virtual void ToolbarCommandClick(long ControlType) { (void)ControlType; }

	virtual ~CIGRDisplayViewerEvents() { }
};

//================================================================================
// 报表打印显示器事件类
// 包含打印显示器交互过程中所有可处理的事件
//================================================================================

class CIGRPrintViewerEvents
{
public:
	// 基础事件
	virtual void Click() { }
	virtual void DblClick() { }
	virtual void KeyDown(long Shift, long Key) { (void)Shift; (void)Key; }
	virtual void KeyPress(char Key) { (void)Key; }
	virtual void KeyUp(long Shift, long Key) { (void)Shift; (void)Key; }
	virtual void MouseDown(long Button, long Shift, long xPos, long yPos) { (void)Button; (void)Shift; (void)xPos; (void)yPos; }
	virtual void MouseUp(long Button, long Shift, long xPos, long yPos) { (void)Button; (void)Shift; (void)xPos; (void)yPos; }
	virtual void MouseMove(long Shift, long xPos, long yPos) { (void)Shift; (void)xPos; (void)yPos; }

	// 页面事件
	virtual void CurPageChange(long NewCurPageNo) { (void)NewCurPageNo; }
	virtual long StatusChange() { return 0; }

	// 标尺事件
	virtual void RulerClick(int IsHorzRuler) { (void)IsHorzRuler; }
	virtual void RulerDblClick(int IsHorzRuler) { (void)IsHorzRuler; }

	// 页事件
	virtual void PageClick() { }
	virtual void PageDblClick() { }

	// 工具栏事件
	virtual void CloseButtonClick() { }
	virtual void CustomButtonClick(long BtnID) { (void)BtnID; }
	virtual void ToolbarCommandClick(long ControlType) { (void)ControlType; }

	virtual ~CIGRPrintViewerEvents() { }
};

#endif // _IGR_EVENTS_H_
