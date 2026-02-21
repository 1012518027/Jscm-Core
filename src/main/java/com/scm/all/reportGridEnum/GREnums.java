package com.scm.all.reportGridEnum;

/**
 * 锐浪报表枚举类集合
 * 包含报表引擎中使用的所有枚举类型定义
 */
public class GREnums {

    /**
     * 文件版本及修改日期
     * 内部供封装查看
     */
    public static class VersionAndDate {
        /** DLL文件版本 */
        public static final String FILE_VERSION = "6.8.23.1018";
        /** 锐浪产品版本 */
        public static final String PRODUCT_VERSION = "6.8.8.0";
        /** 控件最新修改发布日期 */
        public static final String RELEASE_DATE = "2023/11/10";
        /** 本火山库最新修改封装日期 */
        public static final String WRAPPER_DATE = "2022/11/10";
    }

    /**
     * 类CLSID和事件IID
     */
    public static class GRGuid {
        /** 报表CLSID */
        public static final String CLSID_REPORT = "{F9364159-6AED-4F9C-8BAF-D7C7ED6160A8}";
        /** 报表事件IID */
        public static final String IID_REPORT_EVENT = "{330F80F5-4568-4F70-BFCB-683D3B90FEBB}";
        /** 打印显示器CLSID */
        public static final String CLSID_PRINT_VIEWER = "{44CBB5DE-5AFB-4C3D-8F3F-0F70CA5372AD}";
        /** 打印显示器事件IID */
        public static final String IID_PRINT_VIEWER_EVENT = "{A9E920A1-C722-4A81-9FCF-4D5EBFFA21F4}";
        /** 查询显示器CLSID */
        public static final String CLSID_QUERY_VIEWER = "{1B5EA181-A38D-4F42-88B2-6AF74CF6D6C0}";
        /** 查询显示器事件IID */
        public static final String IID_QUERY_VIEWER_EVENT = "{2564DCE8-FEDB-4EB6-B7F9-5026F7F1041E}";
        /** 报表设计器CLSID */
        public static final String CLSID_DESIGNER = "{6EDD80CB-9F08-4C71-B406-479E5CB80FCE}";
        /** 报表设计器事件IID */
        public static final String IID_DESIGNER_EVENT = "{B89F92F4-5E58-413E-8E73-4A0B28B9E9BF}";
    }

    /**
     * 报表事件ID枚举
     * 事件就是 Grid++Report 在运行时各种报表对象在执行特定的任务时所发出的消息，通知操作的发生
     * 报表开发者通过对事件的响应处理，可以控制报表的行为，让 Grid++Report 完成特定的任务
     */
    public static class ReportEvent {
        /** Initialize，在报表生成开始时触发 */
        public static final int INITIALIZE = 1;
        /** FetchRecord，在报表生成时且以推模式向报表提供数据，报表主对象请求数据时触发 */
        public static final int FETCH_RECORD = 2;
        /** BeforePostRecord，在向报表中填入记录数据的过程中，每当提交一条记录之前触发本事件 */
        public static final int BEFORE_POST_RECORD = 3;
        /** BeforeSort，当要对记录集中的记录进行重新排序之前触发本事件 */
        public static final int BEFORE_SORT = 4;
        /** RuntimeError */
        public static final int RUNTIME_ERROR = 5;
        /** CellGetDisplayText，当单元格对象准备生成显示文字时触发 */
        public static final int CELL_GET_DISPLAY_TEXT = 20;
        /** FieldGetDisplayText，当字段对象准备生成其显示文字时触发 */
        public static final int FIELD_GET_DISPLAY_TEXT = 20;
        /** TextBoxGetDisplayText，当文字类型的部件框准备生成其显示文字时触发 */
        public static final int TEXTBOX_GET_DISPLAY_TEXT = 22;
        /** SectionFormat，当报表节在输出之前触发。包括查询显示与打印生成显示 */
        public static final int SECTION_FORMAT = 23;
        /** ControlCustomDraw，报表部件框自绘事件 */
        public static final int CONTROL_CUSTOM_DRAW = 24;
        /** ChartRequestData，当报表中的图表运行时，会触发本事件来请求加载图表数据 */
        public static final int CHART_REQUEST_DATA = 25;
        /** ProcessBegin，当报表准备对填入的数据进行分析处理之前触发本事件 */
        public static final int PROCESS_BEGIN = 30;
        /** ProcessEnd，当报表对填入的数据进行分析处理完成之后触发本事件 */
        public static final int PROCESS_END = 31;
        /** GroupBegin，在生成报表数据时，当定义了页分组，在开始生成明细网格关联的每一页时，每个页分组将触发一次本事件 */
        public static final int GROUP_BEGIN = 32;
        /** GroupEnd，在生成报表数据时，当定义了页分组，在结束生成明细网格关联的每一页时，每个页分组将触发一次本事件 */
        public static final int GROUP_END = 33;
        /** ProcessRecord，当报表生成时，会按顺序遍历每一条记录，每当遍历一条记录时触发本事件 */
        public static final int PROCESS_RECORD = 34;
        /** PageProcessRecord，当报表在生成打印数据时，会再次按顺序遍历每一条记录，每当遍历一条记录时触发本事件 */
        public static final int PAGE_PROCESS_RECORD = 35;
        /** PageStart，在开始生成每一页时，将触发一次本事件 */
        public static final int PAGE_START = 36;
        /** PageEnd，在结束生成每一页时，将触发一次本事件 */
        public static final int PAGE_END = 37;
        /** GeneratePagesBegin，在产生打印页面数据之前触发本事件 */
        public static final int GENERATE_PAGES_BEGIN = 38;
        /** GeneratePagesEnd，在产生打印页面数据完成后触发本事件 */
        public static final int GENERATE_PAGES_END = 39;
        /** PrintBegin，在开始打印报表之前，触发本事件 */
        public static final int PRINT_BEGIN = 40;
        /** PrintEnd，在结束打印报表之后，触发本事件 */
        public static final int PRINT_END = 41;
        /** PrintPage，在打印每一页报表页面时触发本事件 */
        public static final int PRINT_PAGE = 42;
        /** PrintAborted，当打印任务中途取消时触发本事件 */
        public static final int PRINT_ABORTED = 43;
        /** ExportBegin，在报表导出任务执行之前触发本事件 */
        public static final int EXPORT_BEGIN = 50;
        /** ExportEnd，在报表导出任务完成时触发本事件 */
        public static final int EXPORT_END = 51;
        /** ShowPreviewWnd，在显示默认打印预览窗口时触发本事件 */
        public static final int SHOW_PREVIEW_WND = 60;
        /** ShowDesignerWnd，预留事件接口 */
        public static final int SHOW_DESIGNER_WND = 61;
        /** HyperlinkClick，预留事件接口 */
        public static final int HYPERLINK_CLICK = 65;
    }

    /**
     * 查询显示器事件ID枚举
     */
    public static class QueryViewerEvent {
        /** Click，为了和系统事件区分，尾部加"事件"二字 */
        public static final int CLICK = -600;
        /** DblClick，为了和系统事件区分，尾部加"事件"二字 */
        public static final int DBL_CLICK = -601;
        /** KeyDown，为了和系统事件区分，尾部加"事件"二字 */
        public static final int KEY_DOWN = -602;
        /** KeyPress，为了和系统事件区分，尾部加"事件"二字 */
        public static final int KEY_PRESS = -603;
        /** KeyUp，为了和系统事件区分，尾部加"事件"二字 */
        public static final int KEY_UP = -604;
        /** MouseDown，在查询显示器上鼠标按钮按下时触发 */
        public static final int MOUSE_DOWN = -605;
        /** MouseMove */
        public static final int MOUSE_MOVE = -606;
        /** MouseUp，在查询显示器上鼠标按钮按下再释放时触发 */
        public static final int MOUSE_UP = -607;
        /** ControlClick */
        public static final int CONTROL_CLICK = 1;
        /** ControlDblClick */
        public static final int CONTROL_DBL_CLICK = 2;
        /** SectionClick */
        public static final int SECTION_CLICK = 3;
        /** SectionDblClick */
        public static final int SECTION_DBL_CLICK = 4;
        /** ContentCellClick */
        public static final int CONTENT_CELL_CLICK = 5;
        /** ContentCellDblClick */
        public static final int CONTENT_CELL_DBL_CLICK = 6;
        /** TitleCellClick */
        public static final int TITLE_CELL_CLICK = 7;
        /** TitleCellDblClick */
        public static final int TITLE_CELL_DBL_CLICK = 8;
        /** ColumnLayoutChange，当列的显示宽度或顺序发生改变时触发 */
        public static final int COLUMN_LAYOUT_CHANGE = 9;
        /** SelectionCellChange */
        public static final int SELECTION_CELL_CHANGE = 10;
        /** ChartClickSeries */
        public static final int CHART_CLICK_SERIES = 11;
        /** ChartDblClickSeries */
        public static final int CHART_DBL_CLICK_SERIES = 12;
        /** ChartClickLegend */
        public static final int CHART_CLICK_LEGEND = 13;
        /** ChartDblClickLegend */
        public static final int CHART_DBL_CLICK_LEGEND = 14;
        /** BatchFetchRecord，此事件目前仅提供给WEB报表插件 */
        public static final int BATCH_FETCH_RECORD = 15;
        /** StatusChange，当控件的查看状态发生改变时触发本事件 */
        public static final int STATUS_CHANGE = 22;
        /** CloseButtonClick */
        public static final int CLOSE_BUTTON_CLICK = 29;
        /** CustomButtonClick */
        public static final int CUSTOM_BUTTON_CLICK = 30;
        /** ToolbarCommandClick，5和6不会产生反馈事件 */
        public static final int TOOLBAR_COMMAND_CLICK = 31;
    }

    /**
     * 打印显示器事件ID枚举
     */
    public static class PrintViewerEvent {
        /** Click */
        public static final int CLICK = -600;
        /** DblClick */
        public static final int DBL_CLICK = -601;
        /** KeyDown */
        public static final int KEY_DOWN = -602;
        /** KeyPress */
        public static final int KEY_PRESS = -603;
        /** KeyUp */
        public static final int KEY_UP = -604;
        /** MouseDown */
        public static final int MOUSE_DOWN = -605;
        /** MouseMove */
        public static final int MOUSE_MOVE = -606;
        /** MouseUp */
        public static final int MOUSE_UP = -607;
        /** CurPageChange */
        public static final int CUR_PAGE_CHANGE = 21;
        /** StatusChange */
        public static final int STATUS_CHANGE = 22;
        /** RulerClick */
        public static final int RULER_CLICK = 23;
        /** RulerDblClick */
        public static final int RULER_DBL_CLICK = 24;
        /** PageClick */
        public static final int PAGE_CLICK = 27;
        /** PageDblClick */
        public static final int PAGE_DBL_CLICK = 28;
        /** CloseButtonClick */
        public static final int CLOSE_BUTTON_CLICK = 29;
        /** CustomButtonClick */
        public static final int CUSTOM_BUTTON_CLICK = 30;
        /** ToolbarCommandClick，5和6不会产生反馈事件 */
        public static final int TOOLBAR_COMMAND_CLICK = 31;
    }

    /**
     * 报表设计器事件ID枚举
     */
    public static class DesignerEvent {
        /** LayoutPanelContextMenu，当在报表布局窗口中执行打开关联弹出式菜单时触发本事件 */
        public static final int LAYOUT_PANEL_CONTEXT_MENU = 1;
        /** InspectorContextMenu，当属性侦测窗口的数据发生变化时触发本事件 */
        public static final int INSPECTOR_CONTEXT_MENU = 2;
        /** ExplorerContextMenu，当在对象浏览窗口中执行打开关联弹出式菜单时触发本事件 */
        public static final int EXPLORER_CONTEXT_MENU = 3;
        /** InspectorChange，当属性侦测窗口的数据发生变化时触发本事件 */
        public static final int INSPECTOR_CHANGE = 4;
        /** DataChange，当修改了报表定义时触发本事件 */
        public static final int DATA_CHANGE = 5;
        /** OpenRepor，当执行设计器工具栏中的打开按钮时触发本事件 */
        public static final int OPEN_REPORT = 6;
        /** SaveReport，当执行设计器工具栏中的保存按钮时触发本事件 */
        public static final int SAVE_REPORT = 7;
        /** BeforeDoAction，在报表执行每个任务动作前触发本事件 */
        public static final int BEFORE_DO_ACTION = 8;
        /** RequestData，当进入报表设计器的查询视图或预览视图时，会触发本事件来请求生成报表的数据 */
        public static final int REQUEST_DATA = 9;
    }

    /**
     * 语言类型枚举
     * 指定当前应用的界面语言，各种语言对应的标识常数，可用十六进制表示
     */
    public static enum LanguageType {
        /** 简体中文 2052，0x0804 */
        SIMPLIFIED_CHINESE(2052),
        /** 繁体中文 1028，0x0404 */
        TRADITIONAL_CHINESE(1028),
        /** 英语 1033，0x0409 */
        ENGLISH(1033),
        /** 俄语 1049，0x0419 */
        RUSSIAN(1049),
        /** 日语 1041，0x0411 */
        JAPANESE(1041),
        /** 朝鲜语 1042，0x0412 */
        KOREAN(1042),
        /** 德语 1031，0x00407 */
        GERMAN(1031),
        /** 法语 1036，0x040c */
        FRENCH(1036),
        /** 意大利语 1040，0x0410 */
        ITALIAN(1040),
        /** 西班牙语 1034，0x040a */
        SPANISH(1034),
        /** 葡萄牙语 2070，0x0816 */
        PORTUGUESE(2070);

        private final int value;

        LanguageType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 报表显示模式枚举
     * 指定报表的输出显示模式
     */
    public static enum ReportDisplayMode {
        /** grrdmScreenView，正在查询显示器中报表处于自画过程中 */
        SCREEN_VIEW(1),
        /** grrdmPrintGenerate，报表处于打印显示器页面生成过程中 */
        PRINT_GENERATE(2),
        /** grrdmIdle，报表不处于任何生成显示过程中 */
        IDLE(3);

        private final int value;

        ReportDisplayMode(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 计量单位枚举
     * 指定表示报表元素大小与位置的计量单位
     */
    public static enum MeasureUnit {
        /** grmuCm 以厘米为单位表示报表部件的尺寸与位置 */
        CENTIMETER(1),
        /** grmuInch 以英寸为单位表示报表部件的尺寸与位置 */
        INCH(2);

        private final int value;

        MeasureUnit(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 脚本语言枚举
     * 表示在报表中内置的脚本语言类型
     */
    public static enum ScriptType {
        /** grstJScript JScript 脚本语言 */
        JSCRIPT(1),
        /** grstVBScript VBScript 脚本语言 */
        VBSCRIPT(2);

        private final int value;

        ScriptType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 文档类型枚举
     * 指定报表模板的文档类型
     */
    public static enum DocumentType {
        /** grdtObject 按对象方式保存模板数据，此种格式为 Grid++Report 自有格式 */
        OBJECT_FORMAT(1),
        /** grdtJSON 按 JSON 格式保存报表模板数据 */
        JSON_FORMAT(2),
        /** grdtRegister 6.6.5.0 改变报表模板存储方式 */
        REGISTER_FORMAT(3);

        private final int value;

        DocumentType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 存储格式枚举
     * 表示报表模板数据的存储格式
     */
    public static enum StorageFormat {
        /** grsfText 文本格式 */
        TEXT(1),
        /** grsfBinary 二进制格式 */
        BINARY(2),
        /** grsfBinBase64 Base64编码格式 */
        BASE64(3);

        private final int value;

        StorageFormat(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 文本编码枚举
     * 指定文字的编码类型
     */
    public static enum TextEncodeMode {
        /** grtemAnsi ANSI编码 */
        ANSI(1),
        /** grtemUTF8 UTF-8编码，数据最前面有2个字节的标识数据 */
        UTF8(2),
        /** grtemUnicode Unicode编码 */
        UNICODE(3),
        /** grtemUTF8Pure UTF-8编码，数据最前面没有标识 */
        UTF8_PURE(4),
        /** grtemUTF8WithHead 此项同grtemUTF8 */
        UTF8_WITH_HEAD(5);

        private final int value;

        TextEncodeMode(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 内码表枚举
     * 指定报表内部进行文字编码转换的内码表
     */
    public static enum CodePage {
        /** default to ANSI code page */
        ANSI(0),
        /** default to OEM code page */
        OEM(1),
        /** default to MAC code page */
        MAC(2),
        /** current thread's ANSI code page */
        CURRENT_ANSI(3),
        /** ANSI/OEM - Japanese, Shift-JIS */
        JAPANESE(932),
        /** ANSI/OEM - Simplified Chinese (PRC, Singapore) */
        SIMPLIFIED_CHINESE(936),
        /** ANSI/OEM - Korean (Unified Hangeul Code) */
        KOREAN(949),
        /** ANSI/OEM - Traditional Chinese (Taiwan; Hong Kong SAR, PRC) */
        TRADITIONAL_CHINESE(950),
        /** utf-7 Unicode (UTF-7) */
        UTF_7(65000),
        /** utf-8 Unicode (UTF-8)【默认设置】 */
        UTF_8(65001);

        private final int value;

        CodePage(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 报表节类型枚举
     * 指定报表节的具体类型
     */
    public static enum SectionType {
        /** grstPageHeader */
        PAGE_HEADER(1),
        /** grstReportHeader */
        REPORT_HEADER(2),
        /** grstReportFooter */
        REPORT_FOOTER(3),
        /** grstPageFooter */
        PAGE_FOOTER(4),
        /** grstColumnTitle */
        COLUMN_TITLE(5),
        /** grstColumnContent */
        COLUMN_CONTENT(6),
        /** grstGroupHeader */
        GROUP_HEADER(7),
        /** grstGroupFooter */
        GROUP_FOOTER(8);

        private final int value;

        SectionType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 部件框类型枚举
     * 指定部件框的具体类型
     */
    public static enum ControlType {
        /** grctStaticBox */
        STATIC_BOX(1),
        /** grctShapeBox */
        SHAPE_BOX(2),
        /** grctSystemVarBox */
        SYSTEM_VAR_BOX(3),
        /** grctFieldBox */
        FIELD_BOX(4),
        /** grctSummaryBox */
        SUMMARY_BOX(5),
        /** grctRichTextBox */
        RICH_TEXT_BOX(6),
        /** grctPicture */
        PICTURE(7),
        /** grctMemoBox */
        MEMO_BOX(8),
        /** grctSubReport */
        SUB_REPORT(9),
        /** grctLine */
        LINE(10),
        /** grctChart */
        CHART(11),
        /** grctBarcode */
        BARCODE(12),
        /** grctFreeGrid */
        FREE_GRID(13);

        private final int value;

        ControlType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 锁定类别枚举
     * 说明：除1，2两项外，其它项按从弱到强的顺序锁定控制对象，后一项包括前一项的锁定权限
     */
    public static enum LockType {
        /** grltNone，指定对象没有锁定，可以全部权限进行设计 */
        NONE(0),
        /** grltInherited，指定对象的锁定控制来自父级对象，与父级对象保持一样的锁定控制 */
        INHERITED(1),
        /** grltObject，指定对象自身不能删除，其子对象不能删除与增加 */
        OBJECT(2),
        /** grltData，在锁定对象的基础上，再增加对象的数据相关的属性锁定控制 */
        DATA(3),
        /** grltDataAction，在锁定数据的基础上，再增加对报表生成行为相关的锁定控制 */
        DATA_ACTION(4),
        /** grltAll，对象完全不能编辑修改 */
        ALL(5);

        private final int value;

        LockType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 边框模式枚举
     * GRBorderStyles (组合值)，指定是否画部件框的上下左右边框线的组合方式
     */
    public static enum BorderStyle {
        /** grbsDrawLeft 部件框或明细网格显示左边边框线 */
        DRAW_LEFT(1),
        /** grbsDrawTop 部件框或明细网格显示上边边框线 */
        DRAW_TOP(2),
        /** grbsDrawRight 部件框或明细网格显示右边边框线 */
        DRAW_RIGHT(4),
        /** grbsDrawBottom 部件框或明细网格显示下边边框线 */
        DRAW_BOTTOM(8);

        private final int value;

        BorderStyle(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 锚定方式枚举
     * GRAnchorStyles (组合值)，指定部件框如何锚定到其容器的边缘
     */
    public static enum AnchorStyle {
        /** grasLeft 该部件框锚定到其容器的左边缘 */
        LEFT(1),
        /** grasTop 该部件框锚定到其容器的上边缘 */
        TOP(2),
        /** grasRight 该部件框锚定到其容器的右边缘 */
        RIGHT(4),
        /** grasBottom 该部件框锚定到其容器的下边缘 */
        BOTTOM(8);

        private final int value;

        AnchorStyle(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 停靠方式枚举
     * GRDockStyle 指定部件框停靠的位置和方式
     */
    public static enum DockStyle {
        /** grdsNone 该部件框未停靠 */
        NONE(0),
        /** grdsLeft 该部件框的左边缘停靠在父容器的左边缘 */
        LEFT(1),
        /** grdsTop 该部件框的上边缘停靠在父容器的顶端 */
        TOP(2),
        /** grdsRight 该部件框的右边缘停靠在父容器的右边缘 */
        RIGHT(3),
        /** grdsBottom 该部件框的下边缘停靠在父容器的底部 */
        BOTTOM(4),
        /** grdsFill 部件框的各个边缘分别停靠在父容器的各个边缘 */
        FILL(5);

        private final int value;

        DockStyle(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 伸展位移枚举
     * GRShiftMode 指定部件框在其上部的部件框自动伸展高度时在垂直方向进行平移的方式
     */
    public static enum ShiftMode {
        /** grsmNever 不进行位移 */
        NEVER(1),
        /** grsmAlways 总是进行位移 */
        ALWAYS(2),
        /** grsmWhenOverLapped 只有与上部部件框在垂直方向有重叠时才进行位移 */
        WHEN_OVERLAPPED(3);

        private final int value;

        ShiftMode(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 居中方式枚举
     * GRCenterStyle 指定用来在容器中居中对齐部件框的方式
     */
    public static enum CenterStyle {
        /** grcsNone 部件框在父容器中不居中对齐 */
        NONE(0),
        /** grcsHorizontal 部件框始终位于父容器水平方向的正中间 */
        HORIZONTAL(1),
        /** grcsVertical 部件框始终位于父容器垂直方向的正中间 */
        VERTICAL(2),
        /** grcsBoth 部件框始终位于父容器水平与垂直方向的正中间 */
        BOTH(3);

        private final int value;

        CenterStyle(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 对齐列方式枚举
     * GRAlignColumnStyle 指定用来对齐部件框到明细网格列方式
     */
    public static enum AlignColumnStyle {
        /** gracsLeft 对齐到列的左端 */
        LEFT(1),
        /** gracsRight 对齐到列的右端 */
        RIGHT(2),
        /** gracsBoth 对齐到列的左右两端，部件框宽度与列保持一致 */
        BOTH(3);

        private final int value;

        AlignColumnStyle(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 对齐列枚举
     * 对齐到明细网格的左右专用
     */
    public static enum AlignColumn {
        /** 用于部件框"对齐列"属性，可以指定对齐到明细网格的左端 */
        GRID_LEFT("(GridLeft)"),
        /** 用于部件框"对齐列"属性，可以指定对齐到明细网格的右端 */
        GRID_RIGHT("(GridRight)");

        private final String value;

        AlignColumn(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 背景模式枚举
     * GRBackStyle 指定部件再显示时用来填充其所占区域背景的方式
     */
    public static enum BackStyle {
        /** grbkNormal 部件框使用背景色填充他占据的矩形区域 */
        NORMAL(1),
        /** grbkTransparent 部件框背景透明，允许任何背景图像或其他部件框透过自身显示 */
        TRANSPARENT(2);

        private final int value;

        BackStyle(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 打印类别枚举
     * GRPrintType
     */
    public static enum PrintType {
        /** grptForm 表单固定数据，在套打输出内容时不输出 */
        FORM(1),
        /** grptContent 表单内容数据，在套打输出内容时输出 */
        CONTENT(2);

        private final int value;

        PrintType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 光标类型枚举
     * GRDisplayCursor 指定查询显示报表时，可供使用的鼠标光标类型
     */
    public static enum DisplayCursor {
        /** grdcDefault */
        DEFAULT(0),
        /** grdcArrow */
        ARROW(1),
        /** grdcMagnify */
        MAGNIFY(2),
        /** grdcFinger */
        FINGER(3),
        /** grdcAffirm */
        AFFIRM(4),
        /** grdcNegative */
        NEGATIVE(5);

        private final int value;

        DisplayCursor(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 文字方向枚举
     * GRTextOrientation 指定综合文字框的各种文字行向
     */
    public static enum TextOrientation {
        /** grtoDefault 默认方式，按从左到右，从上到下方式显示文字 */
        DEFAULT(0),
        /** grtoU2DL2R0 按从上到下，从左到右方式显示文字，文字不旋转 */
        TOP_TO_BOTTOM_LEFT_TO_RIGHT_0(5),
        /** grtoU2DR2L0 按从上到下，从右到左方式显示文字，文字不旋转 */
        TOP_TO_BOTTOM_RIGHT_TO_LEFT_0(9),
        /** grtoD2UL2R1 按从下到上，从左到右方式显示文字，文字旋转 */
        BOTTOM_TO_TOP_LEFT_TO_RIGHT_1(22),
        /** grtoU2DR2L1 按从上到下，从右到左方式显示文字，文字旋转 */
        TOP_TO_BOTTOM_RIGHT_TO_LEFT_1(25),
        /** grtoL2RD2U0 按从左到右，从下到上方式显示文字，适合用来进行脊背打印 */
        LEFT_TO_RIGHT_BOTTOM_TO_TOP_0(38),
        /** grtoL2RD2U1 按从左到右，从下到上方式显示文字，文字旋转 */
        LEFT_TO_RIGHT_BOTTOM_TO_TOP_1(54),
        /** grtoInvert 倒影方式 */
        INVERT(58);

        private final int value;

        TextOrientation(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 文字对齐方式枚举
     * GRTextAlign 指定文字在显示区域的输出对齐方式
     */
    public static enum TextAlign {
        /** grtaTopLeft 内容在垂直方向上顶部对齐，在水平方向上左边对齐 */
        TOP_LEFT(17),
        /** grtaTopCenter 内容在垂直方向上顶部对齐，在水平方向上居中对齐 */
        TOP_CENTER(18),
        /** grtaTopRight 内容在垂直方向上顶部对齐，在水平方向上右边对齐 */
        TOP_RIGHT(20),
        /** grtaMiddleLeft 内容在垂直方向上中间对齐，在水平方向上左边对齐 */
        MIDDLE_LEFT(33),
        /** grtaMiddleCenter 内容在垂直方向上中间对齐，在水平方向上居中对齐 */
        MIDDLE_CENTER(34),
        /** grtaMiddleRight 内容在垂直方向上中间对齐，在水平方向上右边对齐 */
        MIDDLE_RIGHT(36),
        /** grtaBottomLeft 内容在垂直方向上底边对齐，在水平方向上左边对齐 */
        BOTTOM_LEFT(65),
        /** grtaBottomCenter 内容在垂直方向上底边对齐，在水平方向上居中对齐 */
        BOTTOM_CENTER(66),
        /** grtaBottomRight 内容在垂直方向上底边对齐，在水平方向上右边对齐 */
        BOTTOM_RIGHT(68),
        /** grtaTopJustiy 内容在垂直方向上顶部对齐，在水平方向上两端分散对齐 */
        TOP_JUSTIFY(144),
        /** grtaMiddleJustiy 内容在垂直方向上中间对齐，在水平方向上两端分散对齐 */
        MIDDLE_JUSTIFY(160),
        /** grtaBottomJustiy 内容在垂直方向上底边对齐，在水平方向上两端分散对齐 */
        BOTTOM_JUSTIFY(192);

        private final int value;

        TextAlign(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 文本对齐方式枚举
     * GRStringAlignment 指定文本字符串相对于其布局矩形的对齐方式
     */
    public static enum StringAlignment {
        /** grsaNear 定文本靠近布局对齐。在左到右布局中，近端位置是左。在右到左布局中，近端位置是右 */
        NEAR(1),
        /** grsaCenter 指定文本在布局矩形中居中对齐 */
        CENTER(2),
        /** grsaFar 指定文本远离布局矩形的原点位置对齐。在左到右布局中，远端位置是右。在右到左布局中，远端位置是左 */
        FAR(3);

        private final int value;

        StringAlignment(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 字段类型枚举
     * GRFieldType 指定字段对象的具体类型
     */
    public static enum FieldType {
        /** grftString */
        STRING(1),
        /** grftInteger */
        INTEGER(2),
        /** grftFloat */
        FLOAT(3),
        /** grftCurrency */
        CURRENCY(4),
        /** grftBoolean */
        BOOLEAN(5),
        /** grftDateTime */
        DATE_TIME(6),
        /** grftBinary */
        BINARY(7);

        private final int value;

        FieldType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 参数类型枚举
     * GRParameterDataType 指示参数对象值的具体数据类型
     */
    public static enum ParameterType {
        /** grptString 字符串类型，可以为任意长度 */
        STRING(1),
        /** grptInteger 整数类型，可以设定格式化串 */
        INTEGER(2),
        /** grptFloat 浮点数类型，可以设定格式化串 */
        FLOAT(3),
        /** grptBoolean 逻辑型或布尔类型，可以真值与假值的显示文字 */
        BOOLEAN(5),
        /** grptDateTime 日期时间类型，可以设定格式化串 */
        DATE_TIME(6);

        private final int value;

        ParameterType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 标记类型枚举
     * GRPointMarkerStyle 指定数据项显示图案
     */
    public static enum PointMarkerStyle {
        /** grpmsNone */
        NONE(-1),
        /** grpmsSquare */
        SQUARE(0),
        /** grpmsSquareCheck */
        SQUARE_CHECK(1),
        /** grpmsSquareCross */
        SQUARE_CROSS(2),
        /** grpmsCircle */
        CIRCLE(3),
        /** grpmsCirclePoint */
        CIRCLE_POINT(4),
        /** grpmsCircleCross */
        CIRCLE_CROSS(5),
        /** grpmsDimond */
        DIAMOND(6),
        /** grpmsTriangle */
        TRIANGLE(7),
        /** grpmsCross */
        CROSS(8),
        /** grpmsCross4 */
        CROSS_4(9),
        /** grpmsStar4 */
        STAR_4(10),
        /** grpmsStar5 */
        STAR_5(11),
        /** grpmsStar6 */
        STAR_6(12),
        /** grpmsStar10 */
        STAR_10(13);

        private final int value;

        PointMarkerStyle(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 图形类型枚举
     * GRShapeType 指定图形部件框的具体类型
     */
    public static enum ShapeType {
        /** grsbtCircle */
        CIRCLE(1),
        /** grsbtEllipse */
        ELLIPSE(2),
        /** grsbtRectangle */
        RECTANGLE(3),
        /** grsbtRoundRect */
        ROUND_RECTANGLE(4),
        /** grsbtRoundSquare */
        ROUND_SQUARE(5),
        /** grsbtSquare */
        SQUARE(6),
        /** grsbtLine */
        LINE(7);

        private final int value;

        ShapeType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 画线笔类型枚举
     * GRPenStyle 画线条时，指定画线笔的类型
     */
    public static enum PenStyle {
        /** grpsSolid */
        SOLID(0),
        /** grpsDash */
        DASH(1),
        /** grpsDot */
        DOT(2),
        /** grpsDashDot */
        DASH_DOT(3),
        /** grpsDashDotDot */
        DASH_DOT_DOT(4),
        /** grpsClear 不画线，6.0虽取消，但仍可用 */
        CLEAR(5);

        private final int value;

        PenStyle(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 线条类型枚举
     * GRLineType 虽隐藏，仍可用
     */
    public static enum LineType {
        /** 左上至右下 */
        LEFT_TOP_TO_RIGHT_BOTTOM(0),
        /** 左下至右上 */
        LEFT_BOTTOM_TO_RIGHT_TOP(1),
        /** 左上至右上 */
        LEFT_TOP_TO_RIGHT_TOP(2),
        /** 左中至右中 */
        LEFT_MIDDLE_TO_RIGHT_MIDDLE(3),
        /** 左下至右下 */
        LEFT_BOTTOM_TO_RIGHT_BOTTOM(4),
        /** 左上至左下 */
        LEFT_TOP_TO_LEFT_BOTTOM(5),
        /** 上中至下中 */
        TOP_MIDDLE_TO_BOTTOM_MIDDLE(6),
        /** 右上至右下 */
        RIGHT_TOP_TO_RIGHT_BOTTOM(7);

        private final int value;

        LineType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 系统变量枚举
     * GRSystemVarType 指定系统变量部件框应用的系统变量类型
     */
    public static enum SystemVarType {
        /** grsvCurrentDateTime 计算机的当前日期时间 */
        CURRENT_DATE_TIME(1),
        /** grsvPageCount 总页数 */
        PAGE_COUNT(2),
        /** grsvPageNumber 当前页号 */
        PAGE_NUMBER(3),
        /** grsvRecordNo 明细记录的当前记录号，从1开始计数 */
        RECORD_NO(4),
        /** grsvRowNo 明细网格的当前行号，从1开始计数 */
        ROW_NO(8),
        /** roupOrderNo 分组项序号 */
        GROUP_ORDER_NO(9),
        /** grsvRecordCount 明细记录的记录数 */
        RECORD_COUNT(19);

        private final int value;

        SystemVarType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 分组变量枚举
     * SystemVarValue2 获取的分组变量
     */
    public static enum GroupVarType {
        /** grsvGroupNo，分组序号 */
        GROUP_NO(20),
        /** grsvGroupCount，分组数 */
        GROUP_COUNT(21),
        /** grsvGroupRowNo，分组项行号 */
        GROUP_ROW_NO(22),
        /** grsvGroupRowCount，分组项行数 */
        GROUP_ROW_COUNT(23),
        /** grsvGroupPageNo，分组项页号 */
        GROUP_PAGE_NO(24),
        /** grsvGroupPageCount，分组项页数 */
        GROUP_PAGE_COUNT(25);

        private final int value;

        GroupVarType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 分组保持同页方式枚举
     * GRGrpKpTogetherStyle 指定分组头行与本分组项其他行在打印输出时聚集在相同页的方式
     */
    public static enum GroupKeepTogetherStyle {
        /** grgktsNone 不要求分组头行与本分组项其他行打印输出聚集在相同页 */
        NONE(1),
        /** grgktsFirstDetail 要求分组头行与本分组项的第一个明细记录行打印输出在相同页 */
        FIRST_DETAIL(2),
        /** grgktsAll 要求分组头行与本分组项的其他所有行尽量打印输出在相同页 */
        ALL(3);

        private final int value;

        GroupKeepTogetherStyle(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 分组头对齐方式枚举
     * GROCGroupHeaderVAlign 指定占列式分组头在整个分组列区域的垂直方向上对齐位置
     */
    public static enum GroupHeaderVAlign {
        /** grbaTop 占列式分组头显示在整个分组列区域的顶部 */
        TOP(1),
        /** grbaBottom 占列式分组头显示在整个分组列区域的底部 */
        BOTTOM(2),
        /** grbaMiddle 占列式分组头显示在整个分组列区域的中部(垂直方向) */
        MIDDLE(3);

        private final int value;

        GroupHeaderVAlign(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 条码文字位置枚举
     * GRBarcodeCaptionPosition 表示条形码文字的输出位置
     */
    public static enum BarcodeCaptionPosition {
        /** grbcpNone 条形码的文字不显示 */
        NONE(1),
        /** grbcpAbove 条形码的文字显示在上端 */
        ABOVE(2),
        /** grbcpBelow 条形码的文字显示在下端 */
        BELOW(3);

        private final int value;

        BarcodeCaptionPosition(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 条形码方向枚举
     * GRBarcodeDirection 表示条形码图形的输出方向
     */
    public static enum BarcodeDirection {
        /** LeftToRight 从左到右 */
        LEFT_TO_RIGHT(1),
        /** RightToLeft 从右到左 */
        RIGHT_TO_LEFT(2),
        /** TopToBottom 从上到下 */
        TOP_TO_BOTTOM(3),
        /** BottomToTop 从下到上 */
        BOTTOM_TO_TOP(4);

        private final int value;

        BarcodeDirection(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 条码类型枚举
     * GRBarcodeType 指定条形码的各种类型
     */
    public static enum BarcodeType {
        CODE_25_INTLV(1),
        CODE_25_IND(2),
        CODE_25_MATRIX(3),
        CODE_39(4),
        CODE_39_X(5),
        CODE_128_A(6),
        CODE_128_B(7),
        CODE_128_C(8),
        CODE_93(9),
        CODE_93_X(10),
        CODE_MSI(11),
        CODE_POST_NET(12),
        CODE_CODEBAR(13),
        CODE_EAN_8(14),
        CODE_EAN_13(15),
        CODE_UPC_A(16),
        CODE_UPC_E0(17),
        CODE_UPC_E1(18),
        CODE_UPC_SUPP_2(19),
        CODE_UPC_SUPP_5(20),
        CODE_EAN_128_A(21),
        CODE_EAN_128_B(22),
        CODE_EAN_128_C(23),
        CODE_128_AUTO(24),
        CODE_EAN_128_AUTO(25),
        ITF_14(26),
        PHARMACODE_1_T(27),
        PHARMACODE_2_T(28),
        PDF_417(50),
        QR_CODE(51),
        DATA_MATRIX(52),
        GS1_DATA_MATRIX(53),
        GS1_QR_CODE(54);

        private final int value;

        BarcodeType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * DataM编码枚举
     * GRDtmxEncoding 指定二维码DataMatrix的数据编码方式
     */
    public static enum DtmxEncoding {
        /** grdtmxeAuto 由程序根据数据自动选择编码方式 */
        AUTO(2),
        /** grdtmxeAscii Ascii编码方式 */
        ASCII(3),
        /** grdtmxeC40 C40编码方式 */
        C40(4),
        /** grdtmxeText Text编码方式 */
        TEXT(5),
        /** grdtmxeX12 X12编码方式 */
        X12(6),
        /** grdtmxeEdifact Edifact编码方式 */
        EDIFACT(7),
        /** grdtmxeBase256 Base256编码方式 */
        BASE_256(8);

        private final int value;

        DtmxEncoding(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 换新页栏方式枚举
     * GRNewPageColumnStyle 在打印生成明细网格的分组头时，指定是否强制产生新页列的方式
     */
    public static enum NewPageColumnStyle {
        /** grncsNone 分组头在任何时候都不另起新页栏 */
        NONE(1),
        /** grncsBefore 分组头在打印输出之前另起新页栏进行输出 */
        BEFORE(2),
        /** grncsAfter 分组头在打印输出之后另起新页栏 */
        AFTER(3),
        /** grncsBeforeAfter 分组头在打印输出之前与之后都另起新页栏 */
        BEFORE_AFTER(4);

        private final int value;

        NewPageColumnStyle(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 换新页方式枚举
     * GRNewPageStyle 在打印生成报表节时，指定是否强制产生新页的方式
     */
    public static enum NewPageStyle {
        /** grnpsNone 报表节不强制产生新页 */
        NONE(1),
        /** grnpsBefore 报表节在打印输出之前要求产生新页，保证本节在新页中输出 */
        BEFORE(2),
        /** grnpsAfter 报表节在打印输出之后要求产生新页，保证本节之后的后续节在新页中输出 */
        AFTER(3),
        /** grnpsBeforeAfter 报表节在打印输出之前与之后要求产生新页 */
        BEFORE_AFTER(4);

        private final int value;

        NewPageStyle(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 页栏输出方向枚举
     * GRPageColumnDirection 在明细网格打印生成时，如果生成多栏报表，指定明细网格的各种行的在各页栏中的输出次序
     */
    public static enum PageColumnDirection {
        /** grpcdDownAcross 按从上到下，再从左到右的顺序在页栏中打印输出 */
        DOWN_ACROSS(1),
        /** grpcdAcrossDown 按从左到右，再从上到下的顺序在页栏中打印输出 */
        ACROSS_DOWN(2),
        /** grpcdDownAcrossEqual 按从上到下，再从左到右的顺序在页栏中打印输出，保持每栏输出基本一样多的数据 */
        DOWN_ACROSS_EQUAL(3);

        private final int value;

        PageColumnDirection(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 重复方式枚举
     * GRRepeatStyle 指定明细网格标题的重复输出方式
     */
    public static enum RepeatStyle {
        /** grrsNone 明细网格标题不重复输出 */
        NONE(1),
        /** grrsOnPage 明细网格标题在每页重复输出 */
        ON_PAGE(2),
        /** grrsOnPageColumn 明细网格标题在每个页栏重复输出 */
        ON_PAGE_COLUMN(4),
        /** grrsOnGroupHeader 明细网格标题在最内层的分组头之后重复输出 */
        ON_GROUP_HEADER(8),
        /** grrsOnGroupHeaderPage 明细网格标题在每页与最内层的分组头之后重复输出 */
        ON_GROUP_HEADER_PAGE(10);

        private final int value;

        RepeatStyle(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 周期类型枚举
     * GRPeriodType 表示按日期期间交叉的交叉表日期期间的类型
     */
    public static enum PeriodType {
        /** grptNone */
        NONE(0),
        /** grptDay */
        DAY(1),
        /** grptWeek */
        WEEK(2),
        /** grptThirdMonth */
        THIRD_MONTH(3),
        /** grptHalfMonth */
        HALF_MONTH(4),
        /** grptMonth */
        MONTH(5),
        /** grptQuarter */
        QUARTER(6),
        /** grptHalfYear */
        HALF_YEAR(7),
        /** grptYear */
        YEAR(8),
        /** grptCalendar */
        CALENDAR(9);

        private final int value;

        PeriodType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 图像对齐方式枚举
     * GRPictureAlignment 指定图像框中图像显示的对齐方式
     */
    public static enum PictureAlignment {
        /** grpaTopLeft 图像的左上角紧靠图像框的左上角显示 */
        TOP_LEFT(1),
        /** grpaTopRight 图像的右上角紧靠图像框的右上角显示 */
        TOP_RIGHT(2),
        /** grpaCenter 图像居中显示在图像框中 */
        CENTER(3),
        /** grpaBottomLeft 图像的左下角紧靠图像框的左下角显示 */
        BOTTOM_LEFT(4),
        /** grpaBottomRight 图像的右下角紧靠图像框的右下角显示 */
        BOTTOM_RIGHT(5);

        private final int value;

        PictureAlignment(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 图像旋转模式枚举
     * GRPictureRotateMode 指定图像显示时的旋转方式
     */
    public static enum PictureRotateMode {
        /** grprmNone */
        NONE(0),
        /** grprmLeft */
        LEFT(1),
        /** grprmRight */
        RIGHT(2),
        /** grprmFlip */
        FLIP(3),
        /** grprmMirror */
        MIRROR(4);

        private final int value;

        PictureRotateMode(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 图像缩放方式枚举
     * GRPictureSizeMode 指定图像框中图像的显示缩放方式
     */
    public static enum PictureSizeMode {
        /** grpsmClip 图像不进行缩放，按原始尺寸显示 */
        CLIP(1),
        /** grpsmStretch 图像伸缩到完全显示到图像框中 */
        STRETCH(2),
        /** grpsmZoom 根据图像框的大小图像保持宽高比例伸缩至某一方向完全铺满 */
        ZOOM(3),
        /** grpsmEnsureFullView 如果图像不能在图像框中完全显示，根据图像框的大小图像保持宽高比例伸缩至某一方向完全铺满 */
        ENSURE_FULL_VIEW(4),
        /** grpsmTile 平铺多次显示图像，铺满整个显示区域 */
        TILE(5);

        private final int value;

        PictureSizeMode(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 图像透明模式枚举
     * GRPictureTransparentMode 指定图像透明显示的方式
     */
    public static enum PictureTransparentMode {
        /** grptmNone */
        NONE(0),
        /** grptmOverlying */
        OVERLYING(1),
        /** grptmBackground */
        BACKGROUND(2);

        private final int value;

        PictureTransparentMode(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 图像类型枚举
     * GRPictureType 指定图像类型
     */
    public static enum PictureType {
        /** grptUnknown */
        UNKNOWN(0),
        /** grptBMP */
        BMP(1),
        /** grptGIF */
        GIF(2),
        /** grptJPEG */
        JPEG(3),
        /** grptPNG */
        PNG(4),
        /** grptICON */
        ICON(5),
        /** grptTIFF */
        TIFF(6),
        /** grptWMF */
        WMF(10);

        private final int value;

        PictureType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 打印策略枚举
     * GRColumnPrintAdaptMethod 打印生成时，明细网格列对输出页面的适应方法
     */
    public static enum ColumnPrintAdaptMethod {
        /** grcpamDiscard 在打印输出时，超出页面输出范围的列内容将被忽略掉 */
        DISCARD(1),
        /** grcpamWrap 在打印输出时，超出页面输出范围的列内容将另起新行输出显示 */
        WRAP(2),
        /** grcpamResizeToFit 在打印输出时，根据列的宽度按比列将所有要输出的列的总宽度调整到页面输出区域的宽度 */
        RESIZE_TO_FIT(3),
        /** grcpamShrinkToFit 在打印输出时，如果列的总宽度超出页面输出范围 */
        SHRINK_TO_FIT(4),
        /** grcpamToNewPage 在打印输出时，超出页面输出范围的列内容将另起新页输出显示，按先从上到下的顺序输出 */
        TO_NEW_PAGE(5),
        /** grcpamToNewPageEx 在打印输出时，超出页面输出范围的列内容将另起新页输出显示，按先从左到右的顺序输出 */
        TO_NEW_PAGE_EX(6),
        /** grcpamToNewPageRFC 在打印输出时，超出页面输出范围的列内容将另起新页输出显示 */
        TO_NEW_PAGE_RFC(7),
        /** grcpamToNewPageRFCEx 在打印输出时，超出页面输出范围的列内容将另起新页输出显示 */
        TO_NEW_PAGE_RFC_EX(8),
        /** grcpamWrapExcludeGroup 类同 grcpamWrap，但分组头尾不另起新行 */
        WRAP_EXCLUDE_GROUP(9);

        private final int value;

        ColumnPrintAdaptMethod(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 打印生成方式枚举
     * GRPrintGenerateStyle 指示打印页面的生成方式
     */
    public static enum PrintGenerateStyle {
        /** grpgsOnlyForm 只生成报表表单数据 */
        ONLY_FORM(1),
        /** grpgsOnlyContent 只生成报表内容数据 */
        ONLY_CONTENT(2),
        /** grpgsAll 生成报表所有数据 */
        ALL(3),
        /** grpgsPreviewAll 预览报表全部内容，但只打印出内容数据 */
        PREVIEW_ALL(4);

        private final int value;

        PrintGenerateStyle(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 打印页面类型枚举
     * GRPrintPageType 指定打印时输出页的类型
     */
    public static enum PrintPageType {
        /** grpptAllSelectionPages 输出选定页范围内的所有页 */
        ALL_SELECTION_PAGES(1),
        /** grpptOddSelectionPages 输出选定页范围内的奇数页 */
        ODD_SELECTION_PAGES(2),
        /** grpptEvenSelectionPages 输出选定页范围内的偶数页 */
        EVEN_SELECTION_PAGES(3);

        private final int value;

        PrintPageType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 打印页范围类型枚举
     * GRPrintRangeType 指定打印时选定页范围的类型
     */
    public static enum PrintRangeType {
        /** grprtAllPages 选定全部页 */
        ALL_PAGES(1),
        /** grprtCurrentPage 定当前页 */
        CURRENT_PAGE(2),
        /** grprtSelectionPages 指定的页范围 */
        SELECTION_PAGES(3);

        private final int value;

        PrintRangeType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 逐份打印方式枚举
     * GRCollateKind 逐份打印类型
     */
    public static enum CollateKind {
        /** Default 由打印机确定是否逐份打印 */
        DEFAULT(0),
        /** Collate */
        COLLATE(1),
        /** NotCollate */
        NOT_COLLATE(2);

        private final int value;

        CollateKind(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 共享打印参数枚举
     * GRSharePrintSetupOption (组合值) 指定各个共享全局打印参数的项目
     */
    public static enum SharePrintSetupOption {
        /** grspsoPaperMargin 页边距项目 */
        PAPER_MARGIN(1),
        /** grspsoPaperKind 纸张类型项目 */
        PAPER_KIND(2),
        /** grspsoPaperOrientation 纸张的打印方向项目 */
        PAPER_ORIENTATION(4),
        /** grspsoPaperSource 纸张的进纸来源项目 */
        PAPER_SOURCE(8),
        /** grspsoSelectedPrinter 当前选定的打印机项目 */
        SELECTED_PRINTER(16);

        private final int value;

        SharePrintSetupOption(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 每页版数枚举
     * GRSheetPages 指定打印输出时每页输出版数
     */
    public static enum SheetPages {
        /** grsp1Pages 指定版面数为1 */
        PAGES_1(1),
        /** grsp2Pages 指定版面数为2 */
        PAGES_2(2),
        /** grsp4Pages 指定版面数为4 */
        PAGES_4(3),
        /** grsp6Pages 指定版面数为6 */
        PAGES_6(4),
        /** grsp8Pages 指定版面数为8 */
        PAGES_8(5),
        /** grsp16Pages 指定版面数为16 */
        PAGES_16(6);

        private final int value;

        SheetPages(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 图表类型枚举
     * GRChartType 表示图表的类型
     */
    public static enum ChartType {
        /** grctBarChart 柱图 */
        BAR_CHART(1),
        /** grctPieChart 饼图 */
        PIE_CHART(2),
        /** grctLineChart 折线图 */
        LINE_CHART(3),
        /** grctStackedBarChart 叠加柱图 */
        STACKED_BAR_CHART(4),
        /** grctXYScatterChart 散列点图 */
        XY_SCATTER_CHART(5),
        /** grctXYLineChart 散列点连线图 */
        XY_LINE_CHART(6),
        /** grctCurveLineChart 连曲线图 */
        CURVE_LINE_CHART(7),
        /** grctXYCurveLineChart 散列点连曲线图 */
        XY_CURVE_LINE_CHART(8),
        /** grctBubble 气泡图 */
        BUBBLE(9),
        /** grctStackedBar100Chart 百分比柱状图 */
        STACKED_BAR_100_CHART(10),
        /** grctColumnChart 横向柱状图 */
        COLUMN_CHART(11),
        /** grctStackedColumnChart 横向叠加柱状图 */
        STACKED_COLUMN_CHART(12),
        /** grctStackedColumn100Chart 横向百分比柱状图 */
        STACKED_COLUMN_100_CHART(13);

        private final int value;

        ChartType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 统计函数枚举
     * GRSummaryFun 指定统计部件框应用的统计函数
     */
    public static enum SummaryFunction {
        /** grsfSum 统计某个字段的合计值 */
        SUM(1),
        /** grsfAvg 统计某个字段的平均值 */
        AVG(2),
        /** grsfCount 统计明细记录的个数 */
        COUNT(3),
        /** grsfMin 找出某个字段的最小值 */
        MIN(4),
        /** grsfMax 找出某个字段的最大值 */
        MAX(5),
        /** grsfVar 方差 */
        VAR(6),
        /** grsfVarP 总体方差 */
        VAR_P(7),
        /** grsfStdDev 标准偏差 */
        STD_DEV(8),
        /** grsfStdDevP 总体标准偏差 */
        STD_DEV_P(9),
        /** grsfAveDev 平均偏差 */
        AVE_DEV(10),
        /** grsfDevSq 偏差平方和 */
        DEV_SQ(11),
        /** grsfCountBlank 空值个数 */
        COUNT_BLANK(12),
        /** grsfCountA 非空值个数 */
        COUNT_A(13),
        /** grsfDistinct 非重复值个数 */
        DISTINCT(14),
        /** grsfAvgA 非空值平均 */
        AVG_A(15),
        /** grsfMinN 第N个最小值 */
        MIN_N(16),
        /** grsfMaxN 第N个最大值 */
        MAX_N(17),
        /** grsfStrMin 字符最小值 */
        STR_MIN(18),
        /** grsfStrMax 字符最大值 */
        STR_MAX(19),
        /** grsfVarA 非空方差 */
        VAR_A(20),
        /** grsfVarPA 非空总体方差 */
        VAR_PA(21),
        /** grsfStdDevA 非空标准偏差 */
        STD_DEV_A(22),
        /** grsfStdDevPA 非空总体标准偏差 */
        STD_DEV_PA(23),
        /** grsfAveDevA 非空平均偏差 */
        AVE_DEV_A(24),
        /** grsfDevSqA 非空偏差平方和 */
        DEV_SQ_A(25),
        /** grsfSumAbs 绝对值合计 */
        SUM_ABS(26),
        /** grsfSumAcc 累计 */
        SUM_ACC(27),
        /** grsfGroupSumAcc 组累计 */
        GROUP_SUM_ACC(28),
        /** grsfJoin 串接 */
        JOIN(29),
        /** grsfJoinUnique 非重复串接 */
        JOIN_UNIQUE(30);

        private final int value;

        SummaryFunction(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 系统图像枚举
     * GRSystemImage 指定报表引擎中的系统内建图像
     */
    public static enum SystemImage {
        /** grsiRadio3DUnchecked 值为-10，3D形式的无线按钮(Radio)非选中标志 */
        RADIO_3D_UNCHECKED(-10),
        /** grsiRadio3DChecked 值为-9，3D形式的无线按钮(Radio)选中标志 */
        RADIO_3D_CHECKED(-9),
        /** grsiArrowUp 值为-8，朝上箭头标志 */
        ARROW_UP(-8),
        /** grsiArrowDown 值为-7，朝下箭头标志 */
        ARROW_DOWN(-7),
        /** grsiNegative 值为-6，否定标志 */
        NEGATIVE(-6),
        /** grsiAffirm 值为-5，肯定标志 */
        AFFIRM(-5),
        /** grsi3DUnchecked 值为-4，3D形式的非选中标志 */
        THREE_D_UNCHECKED(-4),
        /** grsi3DChecked 值为-3，3D形式的选中标志 */
        THREE_D_CHECKED(-3),
        /** grsiUnchecked 值为-2，非选中标志 */
        UNCHECKED(-2),
        /** grsiChecked 值为-1，选中标志 */
        CHECKED(-1);

        private final int value;

        SystemImage(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 导出图像类型枚举
     * GRExportImageType 指定在导出图像文件时可用的各种图像格式
     */
    public static enum ExportImageType {
        /** greitBMP Bitmap 位图图像格式 */
        BMP(1),
        /** greitPNG PNG 图像格式 */
        PNG(2),
        /** greitJPEG JPEG 图像格式 */
        JPEG(3),
        /** GIF 图像格式，还未实现 */
        GIF(4),
        /** greitTIFF TIFF 图像格式 */
        TIFF(5);

        private final int value;

        ExportImageType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 导出类型枚举
     * GRExportType 指定数据导出的类型
     */
    public static enum ExportType {
        /** gretXLS 导出Excel文件 */
        XLS(1),
        /** gretTXT 导出文本文件 */
        TXT(2),
        /** gretHTM 导出Html超文本文件 */
        HTM(3),
        /** gretRTF 导出RTF文件 */
        RTF(4),
        /** gretPDF 导出PDF格式文件 */
        PDF(5),
        /** gretCSV 导出CSV格式文件 */
        CSV(6),
        /** gretIMG 导出图像文件，支持多种图像格式 */
        IMG(7);

        private final int value;

        ExportType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * PDF安全限制项枚举
     * GRPDFDocPermission 指定PDF文档的安全性限制类型
     */
    public static enum PDFDocPermission {
        /** grpdpPrint 是否允许打印 */
        PRINT(1),
        /** grpdpEditAll 是否允许完整修改文档 */
        EDIT_ALL(2),
        /** grpdpCopy 是否允许复制文档内容 */
        COPY(3),
        /** grpdpEdit 是否允许修改文档 */
        EDIT(4);

        private final int value;

        PDFDocPermission(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 打印旋转枚举
     * GRDrawRotation 目前仅用于打印输出时指定旋转角度
     */
    public static enum DrawRotation {
        /** grprRotate0 */
        ROTATE_0(0),
        /** grprRotate90 */
        ROTATE_90(1),
        /** grprRotate180 */
        ROTATE_180(2),
        /** grprRotate270 */
        ROTATE_270(3);

        private final int value;

        DrawRotation(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 双面打印方式枚举
     * GRDuplexKind 表示报表对双面打印的应用方式
     */
    public static enum DuplexKind {
        /** grdkDefault 打印机默认的双面打印设置 */
        DEFAULT(0),
        /** grdkSimplex 单面打印 */
        SIMPLEX(1),
        /** grdkVertical 双面垂直打印 */
        VERTICAL(2),
        /** grdkHorizontal 双面水平打印 */
        HORIZONTAL(3);

        private final int value;

        DuplexKind(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 纸张方向枚举
     * GRPaperOrientation 指定生成打印页面时在纸张中的输出方向
     */
    public static enum PaperOrientation {
        /** grpoDefault 应用打印机当前设置的纸张输出方向 */
        DEFAULT(0),
        /** grpoPortrait 纸张输出方向为纵向 */
        PORTRAIT(1),
        /** grpoLandscape 纸张输出方向为横向 */
        LANDSCAPE(2);

        private final int value;

        PaperOrientation(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 纸张来源枚举
     * GRPaperSourceKind 指定打印机的各种纸张来源方式
     */
    public static enum PaperSourceKind {
        /** grpskDefault 打印机默认 */
        DEFAULT(0),
        /** grpskUpper 打印机的上层纸盒 */
        UPPER(1),
        /** grpskLower 打印机的下层纸盒 */
        LOWER(2),
        /** grpskMiddle 打印机的中层纸盒 */
        MIDDLE(3),
        /** grpskManual 以手动方式送入的纸张 */
        MANUAL(4),
        /** grpskEnvelope 信封 */
        ENVELOPE(5),
        /** grpskManualFeed 以手动方式送入的信封 */
        MANUAL_FEED(6),
        /** grpskAutomaticFeed 自动送入的纸张 */
        AUTOMATIC_FEED(7),
        /** grpskTractorFeed 牵引器送纸 */
        TRACTOR_FEED(8),
        /** grpskSmallFormat 小纸盒 */
        SMALL_FORMAT(9),
        /** grpskLargeFormat 大纸盒 */
        LARGE_FORMAT(10),
        /** grpskLargeCapacity 打印机的大容量纸盒 */
        LARGE_CAPACITY(11),
        /** grpskCassette 卡式纸盒 */
        CASSETTE(14),
        /** grpskFormSource 打印机的默认送纸盒 */
        FORM_SOURCE(15),
        /** grpsCustom 打印机特定的纸张来源 */
        CUSTOM(256);

        private final int value;

        PaperSourceKind(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 标准纸张类型枚举
     * PaperSize GRPaperKind 参考Windows SDK中DEVMODE的成员dmPaperSize的说明
     */
    public static enum PaperKind {
        /** 默认纸张设置 */
        DEFAULT(255),
        /** 其中256代表自定义纸张，但定义为自定义纸张时，可以设置纸张宽度与长度属性指定打印纸张的大小 */
        CUSTOM(256),
        /** Letter 1 */
        LETTER(1),
        /** LetterSmall 2 */
        LETTER_SMALL(2),
        /** Tabloid 3 */
        TABLOID(3),
        /** Ledger 4 */
        LEDGER(4),
        /** Legal 5 */
        LEGAL(5),
        /** Statement 6 */
        STATEMENT(6),
        /** Executive 7 */
        EXECUTIVE(7),
        /** A3 8 */
        A3(8),
        /** A4 9 */
        A4(9),
        /** A4Small 10 */
        A4_SMALL(10),
        /** A5 11 */
        A5(11),
        /** A6 70 */
        A6(70),
        /** B4 12 */
        B4(12),
        /** B5 13 */
        B5(13),
        /** B6 88 */
        B6(88),
        /** Folio 14 */
        FOLIO(14),
        /** Quarto 15 */
        QUARTO(15),
        /** 10X14 16 */
        SIZE_10X14(16),
        /** 11X17 17 */
        SIZE_11X17(17),
        /** 12X11 90 */
        SIZE_12X11(90),
        /** Note 18 */
        NOTE(18),
        /** CSheet 24 */
        CSHEET(24),
        /** DSheet 25 */
        DSHEET(25),
        /** ESheet 26 */
        ESHEET(26),
        /** Envelope9 19 */
        ENVELOPE_9(19),
        /** Envelope10 20 */
        ENVELOPE_10(20),
        /** Envelope11 21 */
        ENVELOPE_11(21),
        /** Envelope12 22 */
        ENVELOPE_12(22),
        /** Envelope14 23 */
        ENVELOPE_14(23),
        /** EnvelopeC5 28 */
        ENVELOPE_C5(28),
        /** EnvelopeC3 29 */
        ENVELOPE_C3(29),
        /** EnvelopeC4 30 */
        ENVELOPE_C4(30),
        /** EnvelopeC6 31 */
        ENVELOPE_C6(31),
        /** EnvelopeC65 32 */
        ENVELOPE_C65(32),
        /** EnvelopeB4 33 */
        ENVELOPE_B4(33),
        /** EnvelopeB5 34 */
        ENVELOPE_B5(34),
        /** EnvelopeB6 35 */
        ENVELOPE_B6(35),
        /** EnvelopeDL 27 */
        ENVELOPE_DL(27),
        /** EnvelopeItaly 36 */
        ENVELOPE_ITALY(36),
        /** EnvelopeMonarch 37 */
        ENVELOPE_MONARCH(37),
        /** EnvelopePersonal 38 */
        ENVELOPE_PERSONAL(38),
        /** Fanfold 39 */
        FANSOLD(39),
        /** P16K 93 */
        P16K(93),
        /** P32K 94 */
        P32K(94);

        private final int value;

        PaperKind(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 鼠标键枚举
     * GRMouseButton 指定鼠标按钮
     */
    public static enum MouseButton {
        /** grmbLeft */
        LEFT(1),
        /** grmbRight */
        RIGHT(2),
        /** grmbMiddle */
        MIDDLE(3);

        private final int value;

        MouseButton(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 功能键枚举
     * GRShiftStates 在鼠标与键盘事件中，指定事件触发时鼠标与键盘的状态
     */
    public static enum ShiftStates {
        /** grssShift 指示键盘'Shift'键按下标志 */
        SHIFT(1),
        /** grssAlt 指示键盘'Alt'键按下标志 */
        ALT(2),
        /** grssCtrl 指示键盘'Ctrl'键按下标志 */
        CTRL(4),
        /** grssLeft 指示鼠标左键按下标志 */
        LEFT(8),
        /** grssRight 指示鼠标右键按下标志 */
        RIGHT(16),
        /** grssMiddle 指示鼠标中键按下标志 */
        MIDDLE(32),
        /** grssDouble 指示鼠标双击标志 */
        DOUBLE(64);

        private final int value;

        ShiftStates(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 显示器边框样式枚举
     * GRViewerBorderStyle 查询显示器和打印显示器公用
     */
    public static enum ViewerBorderStyle {
        /** 固定3D */
        FIXED_3D(1),
        /** 固定单边 */
        FIXED_SINGLE(2),
        /** 无 */
        NONE(3);

        private final int value;

        ViewerBorderStyle(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 查看模式枚举
     * GRPrintViewMode 在打印预览查看器中，指定查看模式
     */
    public static enum PrintViewMode {
        /** 打印显示器显示模式 连续页模式 */
        CONTINUOUS(1),
        /** 打印显示器显示模式 单页模式 */
        SINGLE_PAGE(2),
        /** 打印显示器显示模式 多页模式 */
        MULTI_PAGE(3);

        private final int value;

        PrintViewMode(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 预览编辑模式枚举
     * GRPreviewEditMode 指定报表预览时文字的即时编辑模式
     */
    public static enum PreviewEditMode {
        /** grpemDisable 不开启文字编辑功能 */
        DISABLE(1),
        /** grpemClickToEdit 单击文字进行即时编辑 */
        CLICK_TO_EDIT(2),
        /** grpemDblClickToEdit 双击文字进行即时编辑 */
        DBL_CLICK_TO_EDIT(3);

        private final int value;

        PreviewEditMode(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 打印显示器选项枚举
     * GRPrintViewerOption 指定打印显示器某个选项参数
     */
    public static enum PrintViewerOption {
        /** grpvoShowContentMenu 显示弹出式关联菜单 */
        SHOW_CONTENT_MENU(1),
        /** grpvoDblClickSwitchViewMode 鼠标双击切换查看方式 */
        DBL_CLICK_SWITCH_VIEW_MODE(2);

        private final int value;

        PrintViewerOption(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 查询显示器选项枚举
     * GRDisplayViewerOption 指定查询显示器某个选项参数
     */
    public static enum DisplayViewerOption {
        /** 查询显示器右键菜单 */
        SHOW_CONTENT_MENU(1);

        private final int value;

        DisplayViewerOption(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 打印显示器按钮枚举
     * GRToolControlType 指定打印显示器控件中工具栏上的按钮、编辑框与组合框的类型
     */
    public static enum PrintViewerButton {
        /** grtctSep 指定分隔空隙 */
        SEP(1),
        /** grtctPrint 指定打印设置按钮 */
        PRINT(2),
        /** grtctPageSetup 指定页面设置按钮 */
        PAGE_SETUP(3),
        /** grtctPrinterSetup 指定打印机设置按钮 */
        PRINTER_SETUP(4),
        /** grtctExport 指定数据导出按钮 */
        EXPORT(5),
        /** grtctMail 指定发送邮件按钮 */
        MAIL(6),
        /** grtctSaveDocument 指定保存为 Grid++Report 文档文件按钮 */
        SAVE_DOCUMENT(7),
        /** grtctContinuousPage 指定连续页查看模式按钮 */
        CONTINUOUS_PAGE(8),
        /** grtctSinglePage 指定单页查看模式按钮 */
        SINGLE_PAGE(9),
        /** grtctMultiPage 指定多页查看模式按钮 */
        MULTI_PAGE(10),
        /** grtctZoomIn 指定放大显示比例按钮 */
        ZOOM_IN(11),
        /** grtctZoomOut 指定缩小显示比例按钮 */
        ZOOM_OUT(12),
        /** grtctZoomPercent 指定显示百分比下拉列表组合框 */
        ZOOM_PERCENT(13),
        /** grtctPriorPage 指定前一页按钮 */
        PRIOR_PAGE(14),
        /** grtctNextPage 指定后一页按钮 */
        NEXT_PAGE(15),
        /** grtctFirstPage 指定首页按钮 */
        FIRST_PAGE(16),
        /** grtctLastPage 指定尾页按钮 */
        LAST_PAGE(17),
        /** grtctCurPageNo 指定当前显示页编辑框 */
        CUR_PAGE_NO(18),
        /** grtctClose 指定关闭按钮 */
        CLOSE(19),
        /** grtctClip 指定内容复制粘贴按钮组合 */
        CLIP(20),
        /** grtctRefresh 指定刷新按钮 */
        REFRESH(21),
        /** grtctBookmark 指定书签按钮 */
        BOOKMARK(22),
        /** grtctFindTextBox 指定查找文字筐 */
        FIND_TEXT_BOX(30),
        /** grtctFind 指定查找按钮 */
        FIND(31),
        /** grtctFindAgain 指定继续查找按钮 */
        FIND_AGAIN(32),
        /** grtctFindDlg 指定查找对话框按钮 */
        FIND_DLG(33),
        /** grtctExportXLS 指定导出 Excel 菜单项 */
        EXPORT_XLS(50),
        /** grtctExportTXT 指定导出 Text 菜单项 */
        EXPORT_TXT(51),
        /** grtctExportHTM 指定导出 html 菜单项 */
        EXPORT_HTM(52),
        /** grtctExportRTF 指定导出 RTF 菜单项 */
        EXPORT_RTF(53),
        /** grtctExportPDF 指定导出 PDF 菜单项 */
        EXPORT_PDF(54),
        /** grtctExportCSV 指定导出 CSV 菜单项 */
        EXPORT_CSV(55),
        /** grtctExportIMG 指定导出图像菜单项 */
        EXPORT_IMG(56),
        /** grtctExportXLSBtn 指定导出 Excel 按钮 */
        EXPORT_XLS_BTN(60),
        /** grtctExportPDFBtn 指定导出 PDF 按钮 */
        EXPORT_PDF_BTN(61),
        /** grtctMailXLS 指定导出 Excel 并发送Email菜单项 */
        MAIL_XLS(65),
        /** grtctMailTXT 指定导出 Text 并发送Email菜单项 */
        MAIL_TXT(66),
        /** grtctMailHTM 指定导出 html 并发送Email菜单项 */
        MAIL_HTM(67),
        /** grtctMailRTF 指定导出 RTF 并发送Email菜单项 */
        MAIL_RTF(68),
        /** grtctMailPDF 指定导出 PDF 并发送Email菜单项 */
        MAIL_PDF(69),
        /** grtctMailCSV 指定导出 CSV 并发送Email菜单项 */
        MAIL_CSV(70),
        /** grtctMailIMG 指定导出图像并发送Email菜单项 */
        MAIL_IMG(71),
        /** grtctMailSaveDocument 指定保存为 Grid++Report 文档并发送Email菜单项 */
        MAIL_SAVE_DOCUMENT(72);

        private final int value;

        PrintViewerButton(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 查询显示器按钮枚举
     * GRToolControlType2 指定报表查询显示器控件的工具栏中的各个按钮
     */
    public static enum QueryViewerButton {
        /** grdtctSep 指定分隔按钮 */
        SEP(1),
        /** grdtctPrint 指定打印设置按钮 */
        PRINT(2),
        /** grdtctPageSetup 指定打印页面设置按钮 */
        PAGE_SETUP(3),
        /** grdtctPrinterSetup 指定打印机设置按钮 */
        PRINTER_SETUP(4),
        /** grdtctExport 指定数据导出按钮 */
        EXPORT(5),
        /** grdtctMail 指定邮件发送按钮 */
        MAIL(6),
        /** grdtctSaveDocument 指定保存打印文档按钮 */
        SAVE_DOCUMENT(7),
        /** grdtctPriorPage 指定上一页按钮 */
        PRIOR_PAGE(14),
        /** grdtctNextPage 指定下一页按钮 */
        NEXT_PAGE(15),
        /** grdtctFirstPage 指定首页按钮 */
        FIRST_PAGE(16),
        /** grdtctLastPage 指定最后页按钮 */
        LAST_PAGE(17),
        /** grdtctCurPageNo 指定页号编辑框 */
        CUR_PAGE_NO(18),
        /** grdtctClose 指定关闭按钮 */
        CLOSE(19),
        /** grdtctClip 指定按钮 */
        CLIP(20),
        /** grdtctRefresh 指定刷新按钮 */
        REFRESH(21),
        /** grdtctDividePage 指定明细网格数据分页方式组合框与每页行数编辑框 */
        DIVIDE_PAGE(25),
        /** grdtctFindTextBox 指定查找编辑框 */
        FIND_TEXT_BOX(30),
        /** grdtctFind 指定查找按钮 */
        FIND(31),
        /** grdtctFindAgain 指定继续查找按钮 */
        FIND_AGAIN(32),
        /** grdtctFindDlg 指定查找对话框按钮 */
        FIND_DLG(33),
        /** grdtctPrintPreview 指定打印预览按钮 */
        PRINT_PREVIEW(40),
        /** grdtctPostLayout 指定打印提交布局选取框 */
        POST_LAYOUT(41),
        /** grdtctExportXLS 指定导出 Excel 菜单项 */
        EXPORT_XLS(50),
        /** grdtctExportTXT 指定导出文本菜单项 */
        EXPORT_TXT(51),
        /** grdtctExportHTM 指定导出 HTML 菜单项 */
        EXPORT_HTM(52),
        /** grdtctExportRTF 指定导出 RTF 菜单项 */
        EXPORT_RTF(53),
        /** grdtctExportPDF 指定导出 PDF 菜单项 */
        EXPORT_PDF(54),
        /** grdtctExportCSV 指定导出 CSV 菜单项 */
        EXPORT_CSV(55),
        /** grdtctExportIMG 指定导出图像菜单项 */
        EXPORT_IMG(56),
        /** grdtctExportXLSBtn 指定导出 Excel 按钮 */
        EXPORT_XLS_BTN(60),
        /** grdtctExportPDFBtn 指定导出 PDF 按钮 */
        EXPORT_PDF_BTN(61),
        /** grdtctMailXLS 指定导出 Excel 并发送Email菜单项 */
        MAIL_XLS(65),
        /** grdtctMailTXT 指定导出文本并发送Email菜单项 */
        MAIL_TXT(66),
        /** grdtctMailHTM 指定导出 HTML 并发送Email菜单项 */
        MAIL_HTM(67),
        /** grdtctMailRTF 指定导出 RTF 并发送Email菜单项 */
        MAIL_RTF(68),
        /** grdtctMailPDF 指定导出 PDF 并发送Email菜单项 */
        MAIL_PDF(69),
        /** grdtctMailCSV 指定导出 CSV 并发送Email菜单项 */
        MAIL_CSV(70),
        /** grdtctMailIMG 指定导出图像并发送Email菜单项 */
        MAIL_IMG(71),
        /** grdtctColumnVisible 指定打开列可见性设置对话框按钮 */
        COLUMN_VISIBLE(80);

        private final int value;

        QueryViewerButton(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 设计器窗口布局枚举
     * GRDesignerWindowLayout 表示报表设计器的报表布局窗口在整个控件中的布局位置
     */
    public static enum DesignerWindowLayout {
        /** grdwlLayoutAtLeft 设计区停靠在左边 */
        LAYOUT_AT_LEFT(1),
        /** grdwlLayoutAtRight 设计区停靠在右边 */
        LAYOUT_AT_RIGHT(2);

        private final int value;

        DesignerWindowLayout(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 设计器属性项枚举
     * GRPropertyCategory (用在"属性项"接口里是组合值，也可单一使用) 表示属性的组类别显示
     */
    public static enum PropertyCategory {
        /** grpcDesign 设计属性组 */
        DESIGN(1),
        /** grpcLayout 布局属性组 */
        LAYOUT(2),
        /** grpcAppearance 外观属性组 */
        APPEARANCE(4),
        /** grpcData 数据属性组 */
        DATA(8),
        /** grpcAction 行为属性组 */
        ACTION(16),
        /** grpcEvent 事件脚本属性组 */
        EVENT(32),
        /** grpcMisc 其它杂项属性组 */
        MISC(2048);

        private final int value;

        PropertyCategory(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 设计器视图枚举
     * GRDesignerViewMode (组合值) 指示设计器的各种运行视图
     */
    public static enum DesignerViewMode {
        /** grvmInNormalDesign 指示设计器的各种运行视图之普通视图 */
        IN_NORMAL_DESIGN(1),
        /** grvmInPageDesign 指示设计器的各种运行视图之页面视图 */
        IN_PAGE_DESIGN(2),
        /** grvmInPrint 指示设计器的各种运行视图之预览视图 */
        IN_PRINT(4),
        /** grvmInDisplay 指示设计器的各种运行视图之查询视图 */
        IN_DISPLAY(8),
        /** grvmInScript 这个貌似没用 */
        IN_SCRIPT(16);

        private final int value;

        DesignerViewMode(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 设计器视图模式枚举
     * GRDesignerViewStyle 指示设计器组合显示的视图模式
     */
    public static enum DesignerViewStyle {
        /** grvsOnlyNormal */
        ONLY_NORMAL(1),
        /** grvsOnlyPage */
        ONLY_PAGE(2),
        /** grvsNormalPage */
        NORMAL_PAGE(3),
        /** 仅预览无数据显示 */
        ONLY_PRINT(4),
        /** grvsNomalPrint */
        NORMAL_PRINT(5),
        /** grvsPagePrint */
        PAGE_PRINT(6),
        /** grvsNomalPagePrint */
        NORMAL_PAGE_PRINT(7),
        /** 仅查询无数据显示 */
        ONLY_DISPLAY(8),
        /** 原未公开组合，新增 */
        NORMAL_DISPLAY(9),
        /** 原未公开组合，新增 */
        PAGE_DISPLAY(10),
        /** 原未公开组合，新增 */
        NORMAL_PAGE_DISPLAY(11),
        /** 预览无数据显示 */
        PRINT_DISPLAY(12),
        /** grvsNomalPrintDisplay */
        NORMAL_PRINT_DISPLAY(13),
        /** grvsPagePrintDisplay */
        PAGE_PRINT_DISPLAY(14),
        /** grvsNomalPagePrintDisplay 全部 */
        NORMAL_PAGE_PRINT_DISPLAY(15);

        private final int value;

        DesignerViewStyle(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 设计器选项枚举
     * GRDesignerOption (在"选项"接口里是组合值，也可单一使用)，表示定制报表设计器行为的选项
     */
    public static enum DesignerOption {
        /** grdoShowRuler 指定是否显示标尺 */
        SHOW_RULER(1),
        /** grdoCanPopupMenu 指定是否允许打开右键关联菜单 */
        CAN_POPUP_MENU(2),
        /** grdoShowInspector 指定是否显示属性编辑窗口 */
        SHOW_INSPECTOR(4),
        /** grdoShowInspectorObjectPicker 指定是否显示属性编辑窗口的对象下拉选取框 */
        SHOW_INSPECTOR_OBJECT_PICKER(8),
        /** grdoShowExplorer 指定是否显示对象浏览窗口 */
        SHOW_EXPLORER(16),
        /** grdoShowToolbar 指定是否显示工具栏 */
        SHOW_TOOLBAR(32),
        /** grdoShowSectionLine 在报表进入页面视图时，指定是否显示节分隔线 */
        SHOW_SECTION_LINE(256),
        /** grdoShowMarginLine 在报表进入页面视图时，指定是否显示边距分隔线 */
        SHOW_MARGIN_LINE(512),
        /** grdoCanResizeSection 指定是否可以调整节的高度 */
        CAN_RESIZE_SECTION(1024),
        /** grdoCanChangeMargin 指定是否可以调整页面边距 */
        CAN_CHANGE_MARGIN(2048),
        /** grdoCanDeleteObject 指定是否可以删除对象 */
        CAN_DELETE_OBJECT(4096),
        /** grdoEnableQuickEdit 指定是否开启即时编辑功能 */
        ENABLE_QUICK_EDIT(8192),
        /** grdoAutoExpandReportTree 自动展开对象浏览窗口一级对象 */
        AUTO_EXPAND_REPORT_TREE(16384),
        /** grdoLockPropertyEditable 指定是否允许改变锁定属性 */
        LOCK_PROPERTY_EDITABLE(32768),
        /** grdoShowDTCollectionBtns 指定是否显示明细网格的工具栏上的几个打开集合对话框的按钮 */
        SHOW_DT_COLLECTION_BTNS(1048576),
        /** grdoCanAutoGenFields 指定是否允许根据明细查询SQL自动产生字段定义 */
        CAN_AUTO_GEN_FIELDS(2097152),
        /** grdoCanAutoGenColumns 指定是否允许根据字段定义自动产生列定义 */
        CAN_AUTO_GEN_COLUMNS(4194304),
        /** grdoCanAutoGenParameters 指定是否允许根据报表主对象的查询SQL自动产生参数定义 */
        CAN_AUTO_GEN_PARAMETERS(8388608),
        /** grdoShowDetailGridToolbar 指定是否显示明细网格的工具栏 */
        SHOW_DETAIL_GRID_TOOLBAR(16777216),
        /** grdoShowDetailGridColumnRuler 指定是否显示明细网格的列标尺 */
        SHOW_DETAIL_GRID_COLUMN_RULER(33554432),
        /** grdoCanModifyDBSettings 指定是否可以设置记录集的数据源连接串与查询SQL */
        CAN_MODIFY_DB_SETTINGS(67108864),
        /** grdoShowDBConnectionString 指定是否允许查看并编辑数据源连接串 */
        SHOW_DB_CONNECTION_STRING(134217728);

        private final int value;

        DesignerOption(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 选中对象类型枚举
     * GRSelectionObjectType 指定设计器当前选中的对象类型
     */
    public static enum SelectionObjectType {
        /** grdssReport 选中对象为报表主对象 */
        REPORT(1),
        /** grdssPageHeader 选中对象为页眉 */
        PAGE_HEADER(2),
        /** grdssPageFooter 选中对象为页脚 */
        PAGE_FOOTER(3),
        /** grdssReportHeader 选中对象为报表头 */
        REPORT_HEADER(4),
        /** grdssReportFooter 选中对象为报表尾 */
        REPORT_FOOTER(5),
        /** grdssControlSingle 选中对象为单个部件框 */
        CONTROL_SINGLE(6),
        /** grdssDetailGrid 选中对象为明细网格 */
        DETAIL_GRID(8),
        /** grdssColumn 选中对象为列 */
        COLUMN(9),
        /** grdssColumnContentCell 选中对象为内容格 */
        COLUMN_CONTENT_CELL(10),
        /** grdssColumnTitleCell 选中对象为标题格 */
        COLUMN_TITLE_CELL(11),
        /** grdssColumnContent 选中对象为明细内容行 */
        COLUMN_CONTENT(12),
        /** grdssColumnTitle 选中对象为标题行 */
        COLUMN_TITLE(13),
        /** grdssGroup 选中对象为分组 */
        GROUP(14),
        /** grdssGroupHeader 选中对象为分组头 */
        GROUP_HEADER(15),
        /** grdssGroupFooter 选中对象为分组尾 */
        GROUP_FOOTER(16),
        /** grdssRecordset 选中对象为记录集 */
        RECORD_SET(17),
        /** grdssField 选中对象为字段 */
        FIELD(18),
        /** grdssParameter 选中对象为参数 */
        PARAMETER(19),
        /** grdssCrossTab 选中对象为交叉表 */
        CROSS_TAB(20),
        /** grdssChartAxis 选中对象为图表轴 */
        CHART_AXIS(30),
        /** grdssChartSeries 选中对象为图表序列 */
        CHART_SERIES(31),
        /** grdssFreeGridColumn 选中对象为自由表格列 */
        FREE_GRID_COLUMN(51),
        /** grdssFreeGridRow 选中对象为自由表格行 */
        FREE_GRID_ROW(52),
        /** grdssFreeGridCell 选中对象为自由表格子格 */
        FREE_GRID_CELL(53);

        private final int value;

        SelectionObjectType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 设计器动作枚举
     * GRDesignerAction 指定执行报表设计器动作的类型
     */
    public static enum DesignerAction {
        /** grdaEditUndo 恢复操作 */
        EDIT_UNDO(101),
        /** grdaEditRedo 重复操作 */
        EDIT_REDO(102),
        /** grdaEditCut 剪切操作 */
        EDIT_CUT(103),
        /** grdaEditPaste 粘贴操作 */
        EDIT_PASTE(104),
        /** grdaEditCopy 复制操作 */
        EDIT_COPY(105),
        /** grdaEditDelete 删除当前选中的报表元素 */
        EDIT_DELETE(106),
        /** grdaEditSelectAll 全选操作 */
        EDIT_SELECT_ALL(107),
        /** grdaAutoSizeControl 根据文字框地显示文字自动调整框的大小 */
        AUTO_SIZE_CONTROL(201),
        /** grdaAutoGenColumn 根据记录集的字段定义，自动生成列的定义 */
        AUTO_GEN_COLUMN(202),
        /** grdaAutoGenField 根据明细网格的记录集数据源取数设置自动生成报表字段定义 */
        AUTO_GEN_FIELD(203),
        /** grdaSectionMoveUp 将当前选中报表头或报标尾的显示顺序前移 */
        SECTION_MOVE_UP(204),
        /** grdaSectionMoveDown 将当前选中报表头或报标尾的显示顺序后移 */
        SECTION_MOVE_DOWN(205),
        /** grdaAutoGenParameter 根据报表主对象上的参数数据源取数设置自动生成报表参数定义 */
        AUTO_GEN_PARAMETER(206),
        /** grdaAutoGenRecordsetField 自动生成数据集字段 */
        AUTO_GEN_RECORDSET_FIELD(207),
        /** grdaReportPaperSetting 打开页面设置对话框 */
        REPORT_PAPER_SETTING(301),
        /** grdaSetupDBQuery 打开明细网格的记录集数据源取数设置对话框 */
        SETUP_DB_QUERY(302),
        /** grdaReportParameterCollection 打开参数集合对话框 */
        REPORT_PARAMETER_COLLECTION(303),
        /** grdaReportGroupCollection 打开分组集合对话框 */
        REPORT_GROUP_COLLECTION(304),
        /** grdaReportColumnCollection 打开列集合对话框 */
        REPORT_COLUMN_COLLECTION(305),
        /** grdaReportFieldCollection 打开字段集合对话框 */
        REPORT_FIELD_COLLECTION(306),
        /** grdaReportColumnTitleLayout 打开列标题布局对话框 */
        REPORT_COLUMN_TITLE_LAYOUT(307),
        /** grdaBrowseScriptCode 打开报表脚本浏览窗口 */
        BROWSE_SCRIPT_CODE(308),
        /** grdaExitSubReportDesign 退出当前子报表设计，返回到其主表设计 */
        EXIT_SUB_REPORT_DESIGN(309),
        /** grdaSetupParamDBQuery 打开报表主对象的参数数据源取数设置对话框 */
        SETUP_PARAM_DB_QUERY(310),
        /** grdaSetupRecordsetDBQuery 打开数据集设置对话框 */
        SETUP_RECORDSET_DB_QUERY(311),
        /** grdaZoomSetting 缩放设置 */
        ZOOM_SETTING(313),
        /** grdaFreeGridCellMerge 自由表格合并单元格 */
        FREE_GRID_CELL_MERGE(351),
        /** grdaFreeGridCellSplit 自由表格拆分单元格 */
        FREE_GRID_CELL_SPLIT(352),
        /** grdaFreeGridColumnInsertLeft 自由表格左边插入列 */
        FREE_GRID_COLUMN_INSERT_LEFT(353),
        /** rdaFreeGridColumnInsertRight 自由表格右边插入列 */
        FREE_GRID_COLUMN_INSERT_RIGHT(354),
        /** grdaFreeGridColumnMoveLeft 自由表格列左移 */
        FREE_GRID_COLUMN_MOVE_LEFT(355),
        /** grdaFreeGridColumnMoveRight 自由表格列右移 */
        FREE_GRID_COLUMN_MOVE_RIGHT(356),
        /** rdaFreeGridColumnMoveTo 自由表格列移动至 */
        FREE_GRID_COLUMN_MOVE_TO(357),
        /** grdaFreeGridRowInsertUp 自由表格上面插入行 */
        FREE_GRID_ROW_INSERT_UP(358),
        /** grdaFreeGridRowInsertDown 自由表格下面插入行 */
        FREE_GRID_ROW_INSERT_DOWN(359),
        /** grdaFreeGridRowMoveUp 自由表格行向上移动 */
        FREE_GRID_ROW_MOVE_UP(360),
        /** grdaFreeGridRowMoveDown 自由表格行向下移动 */
        FREE_GRID_ROW_MOVE_DOWN(361),
        /** rdaFreeGridRowMoveTo 自由表格行移动至 */
        FREE_GRID_ROW_MOVE_TO(362),
        /** grdaFreeGridColumnDelete 自由表格删除列 */
        FREE_GRID_COLUMN_DELETE(363),
        /** grdaFreeGridRowDelete 自由表格删除行 */
        FREE_GRID_ROW_DELETE(364),
        /** grdaFreeGridRowAvgHeight 自由表格平均行高 */
        FREE_GRID_ROW_AVG_HEIGHT(365),
        /** grdaFreeGridColumnAvgWidth 自由表格平均列宽 */
        FREE_GRID_COLUMN_AVG_WIDTH(366),
        /** grdaFreeGridSectionAutoHeight 自由表格自动调整节高度 */
        FREE_GRID_SECTION_AUTO_HEIGHT(370),
        /** grdaInsertReportHeader 插入一个报表头 */
        INSERT_REPORT_HEADER(401),
        /** grdaInsertReportFooter 插入一个报表尾 */
        INSERT_REPORT_FOOTER(402),
        /** grdaInsertPageHeader 插入页眉 */
        INSERT_PAGE_HEADER(403),
        /** grdaInsertPageFooter 插入页脚 */
        INSERT_PAGE_FOOTER(404),
        /** grdaInsertDetailGrid 插入明细网格 */
        INSERT_DETAIL_GRID(405),
        /** grdaInsertGroup 插入一个分组 */
        INSERT_GROUP(406),
        /** grdaInsertField 插入字段 */
        INSERT_FIELD(407),
        /** grdaInsertColumn 插入列 */
        INSERT_COLUMN(408),
        /** grdaInsertParameter 插入参数 */
        INSERT_PARAMETER(409),
        /** grdaFormatAlignLeft 使选中的多个部件框左端对齐 */
        FORMAT_ALIGN_LEFT(501),
        /** grdaFormatAlignRight 使选中的多个部件框右端对齐 */
        FORMAT_ALIGN_RIGHT(502),
        /** grdaFormatAlignCenter 使选中的多个部件框水平居中对齐 */
        FORMAT_ALIGN_CENTER(503),
        /** grdaFormatAlignTop 使选中的多个部件框上端对齐 */
        FORMAT_ALIGN_TOP(504),
        /** grdaFormatAlignMiddle 使选中的多个部件框垂直居中对齐 */
        FORMAT_ALIGN_MIDDLE(505),
        /** grdaFormatAlignBottom 使选中的多个部件框底端对齐 */
        FORMAT_ALIGN_BOTTOM(506),
        /** grdaFormatAlignToGrid 使选中的部件框左上角对齐到设计格点 */
        FORMAT_ALIGN_TO_GRID(507),
        /** grdaFormatSizeSameWidth 使选中的多个部件框宽度相同 */
        FORMAT_SIZE_SAME_WIDTH(508),
        /** grdaFormatSizeSameHeight 使选中的多个部件框高度相同 */
        FORMAT_SIZE_SAME_HEIGHT(509),
        /** grdaFormatSizeSameBoth 使选中的多个部件框大小相同 */
        FORMAT_SIZE_SAME_BOTH(510),
        /** grdaFormatSizeToGrid 使选中的部件框四角伸缩到最近设计格点 */
        FORMAT_SIZE_TO_GRID(511),
        /** grdaFormatSpaceEquallyVertical 使选中的多个部件框垂直间距相等 */
        FORMAT_SPACE_EQUALLY_VERTICAL(512),
        /** grdaFormatSpaceIncreaseVertical 使选中的多个部件框垂直间距增大 */
        FORMAT_SPACE_INCREASE_VERTICAL(513),
        /** grdaFormatSpaceDecreaseVertical 使选中的多个部件框垂直间距减小 */
        FORMAT_SPACE_DECREASE_VERTICAL(514),
        /** grdaFormatSpaceEquallyHorizontal 使选中的多个部件框水平间距相等 */
        FORMAT_SPACE_EQUALLY_HORIZONTAL(515),
        /** grdaFormatSpaceIncreaseHorizontal 使选中的多个部件框水平间距增大 */
        FORMAT_SPACE_INCREASE_HORIZONTAL(516),
        /** grdaFormatSpaceDecreaseHorizontal 使选中的多个部件框水平间距减小 */
        FORMAT_SPACE_DECREASE_HORIZONTAL(517),
        /** grdaFormatSpaceRemoveHorizontal 去除选中的多个部件框的水平间距 */
        FORMAT_SPACE_REMOVE_HORIZONTAL(518),
        /** grdaFormatSpaceRemoveVertical 去除选中的多个部件框的垂直间距 */
        FORMAT_SPACE_REMOVE_VERTICAL(519),
        /** grdaFormatOrderBringToFron 将选中部件框的 Z 顺序置于最上层 */
        FORMAT_ORDER_BRING_TO_FRONT(520),
        /** grdaFormatOrderSendToBack 将选中部件框的 Z 顺序置于最下层 */
        FORMAT_ORDER_SEND_TO_BACK(521),
        /** grdaFormatFontStrikethrough 改变字体删除线 */
        FORMAT_FONT_STRIKETHROUGH(600),
        /** grdaFormatFontBold 使选中文字框的文字按粗体显示 */
        FORMAT_FONT_BOLD(601),
        /** grdaFormatFontItalic 使选中文字框的文字按斜体显示 */
        FORMAT_FONT_ITALIC(602),
        /** grdaFormatFontUnderline 使选中文字框的文字加下划线显示 */
        FORMAT_FONT_UNDERLINE(603),
        /** grdaFormatTextAlignTopLeft 使选中文字框的文字按左上对齐显示 */
        FORMAT_TEXT_ALIGN_TOP_LEFT(604),
        /** grdaFormatTextAlignTopCenter 使选中文字框的文字按中上对齐显示 */
        FORMAT_TEXT_ALIGN_TOP_CENTER(605),
        /** grdaFormatTextAlignTopRight 使选中文字框的文字按右上对齐显示 */
        FORMAT_TEXT_ALIGN_TOP_RIGHT(606),
        /** grdaFormatTextAlignMiddleLeft 使选中文字框的文字按左中对齐显示 */
        FORMAT_TEXT_ALIGN_MIDDLE_LEFT(607),
        /** grdaFormatTextAlignMiddleCenter 使选中文字框的文字按中中对齐显示 */
        FORMAT_TEXT_ALIGN_MIDDLE_CENTER(608),
        /** grdaFormatTextAlignMiddleRight 使选中文字框的文字按右中对齐显示 */
        FORMAT_TEXT_ALIGN_MIDDLE_RIGHT(609),
        /** grdaFormatTextAlignBottomLeft 使选中文字框的文字按左下对齐显示 */
        FORMAT_TEXT_ALIGN_BOTTOM_LEFT(610),
        /** grdaFormatTextAlignBottomCenter 使选中文字框的文字按中下对齐显示 */
        FORMAT_TEXT_ALIGN_BOTTOM_CENTER(611),
        /** grdaFormatTextAlignBottomRight 使选中文字框的文字按右下对齐显示 */
        FORMAT_TEXT_ALIGN_BOTTOM_RIGHT(612),
        /** grdaFormatTextOrientationDefault 设定选中文字框的文字方向为默认方式 */
        FORMAT_TEXT_ORIENTATION_DEFAULT(613),
        /** grdaFormatTextOrientationU2DL2R0 设定选中文字框的文字方向为从上到下，从左到右方式显示文字 */
        FORMAT_TEXT_ORIENTATION_U2DL2R0(614),
        /** grdaFormatTextOrientationD2UL2R1 设定选中文字框的文字方向为从下到上，从左到右方式显示文字 */
        FORMAT_TEXT_ORIENTATION_D2UL2R1(615),
        /** grdaFormatTextOrientationU2DR2L0 设定选中文字框的文字方向为从上到下，从右到左方式显示文字 */
        FORMAT_TEXT_ORIENTATION_U2DR2L0(616),
        /** grdaFormatTextOrientationU2DR2L1 设定选中文字框的文字方向为按从上到下，从右到左方式显示文字 */
        FORMAT_TEXT_ORIENTATION_U2DR2L1(617),
        /** grdaFormatTextOrientationInvert 设定选中文字框的文字方向为倒影方式 */
        FORMAT_TEXT_ORIENTATION_INVERT(618),
        /** grdaFormatTextOrientationL2RD2U0 文字方向左到右下到上脊背模式 */
        FORMAT_TEXT_ORIENTATION_L2RD2U0(619),
        /** grdaFormatTextOrientationL2RD2U1 文字方向左到右下到上且旋转 */
        FORMAT_TEXT_ORIENTATION_L2RD2U1(620),
        /** grdaFormatTextAlignLeft 文字左对齐 */
        FORMAT_TEXT_ALIGN_LEFT(621),
        /** grdaFormatTextAlignCenter 文字居中对齐 */
        FORMAT_TEXT_ALIGN_CENTER(622),
        /** grdaFormatTextAlignRight 文字右对齐 */
        FORMAT_TEXT_ALIGN_RIGHT(623),
        /** grdaFormatTextAlignJustify 文字两端对齐 */
        FORMAT_TEXT_ALIGN_JUSTIFY(624),
        /** grdaFormatTextAlignTop 文字顶端对齐 */
        FORMAT_TEXT_ALIGN_TOP(625),
        /** grdaFormatTextAlignMiddle 文字中间对齐 */
        FORMAT_TEXT_ALIGN_MIDDLE(626),
        /** grdaFormatTextAlignBottom 文字下端对齐 */
        FORMAT_TEXT_ALIGN_BOTTOM(627);

        private final int value;

        DesignerAction(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 报表类名枚举
     * Class类名文本，便于设计器属性栏操作枚举的接口
     */
    public static enum ReportClassName {
        /** IGridppReport */
        REPORT("Report"),
        /** DetailGrid */
        DETAIL_GRID("DetailGrid"),
        /** CrossTab */
        CROSS_TAB("CrossTab"),
        /** Column */
        COLUMN("Column"),
        /** Recordset */
        RECORDSET("Recordset"),
        /** Field */
        FIELD("Field"),
        /** Parameter */
        PARAMETER("Parameter"),
        /** Section 基类 节：页眉、页脚、报表节(报表头与报表尾)、列节(标题行与内容行)、分组节(分组头与分组尾) */
        SECTION("Section"),
        /** PageHeader */
        PAGE_HEADER("PageHeader"),
        /** PageFooter */
        PAGE_FOOTER("PageFooter"),
        /** ReportSection 基类 报表节：报表头、报表尾 */
        REPORT_SECTION("ReportSection"),
        /** ReportHeader 报表头 */
        REPORT_HEADER("ReportHeader"),
        /** ReportFooter 报表尾 */
        REPORT_FOOTER("ReportFooter"),
        /** ColumnSection 基类 列节 ColumnSection：标题行，内容行 */
        COLUMN_SECTION("ColumnSection"),
        /** ColumnTitle 标题行 */
        COLUMN_TITLE("ColumnTitle"),
        /** ColumnContent 内容行 */
        COLUMN_CONTENT("ColumnContent"),
        /** GroupSection 基类 分组节 GroupSection：分组(分组头、分组尾) */
        GROUP_SECTION("GroupSection"),
        /** Group 分组 */
        GROUP("Group"),
        /** GroupHeader 分组头 */
        GROUP_HEADER("GroupHeader"),
        /** GroupFooter 分组尾 */
        GROUP_FOOTER("GroupFooter"),
        /** CellBase 基类 单元格CellBase，包含各种单元格 */
        CELL_BASE("CellBase"),
        /** ColumnCell 基类 列格ColumnCell，包含：标题格、内容格 */
        COLUMN_CELL("ColumnCell"),
        /** ColumnTitleCell 标题格 */
        COLUMN_TITLE_CELL("ColumnTitleCell"),
        /** ColumnContentCell 内容格 */
        COLUMN_CONTENT_CELL("ColumnContentCell"),
        /** Control 基类 部件框 所有部件框 */
        CONTROL("Control"),
        /** TextBox 基类 部件框_文字框 */
        TEXT_BOX("TextBox"),
        /** StaticBox 静态文字框 */
        STATIC_BOX("StaticBox"),
        /** SystemVarBox 系统变量框 */
        SYSTEM_VAR_BOX("SystemVarBox"),
        /** FieldBox 字段框 */
        FIELD_BOX("FieldBox"),
        /** SummaryBox 统计框 */
        SUMMARY_BOX("SummaryBox"),
        /** MemoBox 综合文字框 */
        MEMO_BOX("MemoBox"),
        /** ShapeBox 图形框 */
        SHAPE_BOX("ShapeBox"),
        /** RichTextBox RTF文字框 */
        RICH_TEXT_BOX("RichTextBox"),
        /** PictureBox 图像框 */
        PICTURE_BOX("PictureBox"),
        /** SubReport 子报表框 */
        SUB_REPORT("SubReport"),
        /** Line 线段框 */
        LINE("Line"),
        /** Barcode 条码框 */
        BARCODE("Barcode"),
        /** Chart 图表框 */
        CHART("Chart"),
        /** ChartAxis 图表轴 */
        CHART_AXIS("ChartAxis"),
        /** ChartSeries 图表序列 */
        CHART_SERIES("ChartSeries"),
        /** FreeGrid 自由表格框 */
        FREE_GRID("FreeGrid"),
        /** FreeGridRow 自由表格行 */
        FREE_GRID_ROW("FreeGridRow"),
        /** FreeGridColumn 自由表格列 */
        FREE_GRID_COLUMN("FreeGridColumn"),
        /** FreeGridCell 自由表格子格 */
        FREE_GRID_CELL("FreeGridCell");

        private final String value;

        ReportClassName(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 报表类属性组枚举
     * Class类对象属性项里某一属性组文本，便于设计器属性栏操作枚举的部分接口
     */
    public static enum ReportClassPropertyGroup {
        BORDER("Border"),
        LOCATION("Location"),
        SIZE("Size"),
        PADDING("Padding"),
        ALIGN_DETAIL_COLUMN("AlignDetailColumn"),
        TEXT_FORMAT_GROUP("TextFormatGroup"),
        MONEY_LINE("MoneyLine"),
        DOC_FORMAT("DocFormat"),
        DESIGN_GRID("DesignGrid"),
        SHARE_PRINT_SETUP_OPTION("SharePrintSetupOption");

        private final String value;

        ReportClassPropertyGroup(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
