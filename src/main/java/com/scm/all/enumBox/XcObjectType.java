package com.scm.all.enumBox;

/**
 * 炫彩对象类型枚举（对应 C++ 枚举 XC_OBJECT_TYPE）
 * 注：包含所有炫彩UI组件、布局、数据适配器、动画等对象类型定义，保留原始数值与语义说明
 */
public enum XcObjectType {
    /**
     * 别名：错误
     * 错误类型
     */
    XC_ERROR(-1),

    /**
     * 啥也不是
     */
    XC_NOTHING(0),

    /**
     * 别名：窗口
     * 基础窗口对象
     */
    XC_WINDOW(1),

    /**
     * 别名：模态窗口
     * 模态窗口对象（阻塞父窗口交互）
     */
    XC_MODALWINDOW(2),

    /**
     * 别名：框架窗口
     * 框架窗口对象
     */
    XC_FRAMEWND(3),

    /**
     * 别名：浮动窗口
     * 浮动窗口对象
     */
    XC_FLOATWND(4),

    /**
     * 别名：组合框下拉窗口
     * 组合框弹出下拉列表窗口（对应 comboBoxWindow_）
     */
    XC_COMBOBOXWINDOW(11),

    /**
     * 别名：菜单主窗口
     * 菜单主窗口（对应 popupMenuWindow_）
     */
    XC_POPUPMENUWINDOW(12),

    /**
     * 别名：菜单子窗口
     * 菜单子窗口（对应 popupMenuChildWindow_）
     */
    XC_POPUPMENUCHILDWINDOW(13),

    /**
     * 别名：可视对象
     * 基础可视对象
     */
    XC_OBJECT_UI(19),

    /**
     * 别名：窗口组件
     * 窗口组件对象
     */
    XC_WIDGET_UI(20),

    /**
     * 别名：基础元素
     * 基础UI元素
     */
    XC_ELE(21),

    /**
     * 别名：按钮
     * 按钮组件
     */
    XC_BUTTON(22),

    /**
     * 别名：富文本编辑框
     * 富文本编辑框（已废弃，请使用 XC_EDIT）
     */
    XC_RICHEDIT(23),

    /**
     * 别名：下拉组合框
     * 下拉组合框组件
     */
    XC_COMBOBOX(24),

    /**
     * 别名：滚动条
     * 滚动条组件
     */
    XC_SCROLLBAR(25),

    /**
     * 别名：滚动视图
     * 滚动视图组件
     */
    XC_SCROLLVIEW(26),

    /**
     * 别名：列表
     * 列表组件
     */
    XC_LIST(27),

    /**
     * 别名：列表框
     * 列表框组件
     */
    XC_LISTBOX(28),

    /**
     * 别名：列表视图
     * 列表视图组件（大图标模式）
     */
    XC_LISTVIEW(29),

    /**
     * 别名：列表树
     * 列表树组件
     */
    XC_TREE(30),

    /**
     * 别名：菜单条
     * 菜单条组件
     */
    XC_MENUBAR(31),

    /**
     * 别名：滑动条
     * 滑动条组件
     */
    XC_SLIDERBAR(32),

    /**
     * 别名：进度条
     * 进度条组件
     */
    XC_PROGRESSBAR(33),

    /**
     * 别名：工具条
     * 工具条组件
     */
    XC_TOOLBAR(34),

    /**
     * 别名：月历卡片
     * 月历卡片组件
     */
    XC_MONTHCAL(35),

    /**
     * 别名：日期时间
     * 日期时间选择组件
     */
    XC_DATETIME(36),

    /**
     * 别名：属性网格
     * 属性网格组件
     */
    XC_PROPERTYGRID(37),

    /**
     * 别名：颜色选择框
     * 颜色选择编辑框组件
     */
    XC_EDIT_COLOR(38),

    /**
     * 别名：设置编辑框
     * 设置编辑框组件
     */
    XC_EDIT_SET(39),

    /**
     * 别名：TAB条
     * TAB条组件
     */
    XC_TABBAR(40),

    /**
     * 别名：文本链接按钮
     * 文本链接按钮组件
     */
    XC_TEXTLINK(41),

    /**
     * 别名：窗格
     * 窗格组件
     */
    XC_PANE(42),

    /**
     * 别名：窗格分割条
     * 窗格拖动分割条组件
     */
    XC_PANE_SPLIT(43),

    /**
     * 别名：菜单条上按钮
     * 菜单条上的按钮组件
     */
    XC_MENUBAR_BUTTON(44),

    /**
     * 别名：编辑框
     * 基础编辑框组件
     */
    XC_EDIT(45),

    /**
     * 别名：代码编辑框
     * 代码编辑框组件
     */
    XC_EDITOR(46),

    /**
     * 别名：文件选择编辑框
     * 文件选择编辑框（对应 EditFile）
     */
    XC_EDIT_FILE(50),

    /**
     * 别名：文件夹选择编辑框
     * 文件夹选择编辑框（对应 EditFolder）
     */
    XC_EDIT_FOLDER(51),

    /**
     * 别名：列表头元素
     * 列表头元素组件
     */
    XC_LIST_HEADER(52),

    /**
     * 别名：布局元素
     * 布局元素组件
     */
    XC_ELE_LAYOUT(53),

    /**
     * 别名：布局框架
     * 布局框架（流式布局）
     */
    XC_LAYOUT_FRAME(54),

    /**
     * 别名：形状对象
     * 基础形状对象
     */
    XC_SHAPE(61),

    /**
     * 别名：形状对象文本
     * 文本形状对象
     */
    XC_SHAPE_TEXT(62),

    /**
     * 别名：形状对象图片
     * 图片形状对象
     */
    XC_SHAPE_PICTURE(63),

    /**
     * 别名：形状对象矩形
     * 矩形形状对象
     */
    XC_SHAPE_RECT(64),

    /**
     * 别名：形状对象圆形
     * 圆形形状对象
     */
    XC_SHAPE_ELLIPSE(65),

    /**
     * 别名：形状对象直线
     * 直线形状对象
     */
    XC_SHAPE_LINE(66),

    /**
     * 别名：形状对象组框
     * 组框形状对象
     */
    XC_SHAPE_GROUPBOX(67),

    /**
     * 别名：形状对象GIF
     * GIF形状对象
     */
    XC_SHAPE_GIF(68),

    /**
     * 别名：形状对象表格
     * 表格形状对象
     */
    XC_SHAPE_TABLE(69),

    /**
     * 别名：菜单
     * 菜单对象
     */
    XC_MENU(81),

    /**
     * 别名：图片源
     * 图片纹理（图片源、图片素材）
     */
    XC_IMAGE_TEXTURE(82),

    /**
     * 别名：绘图
     * 绘图操作对象
     */
    XC_HDRAW(83),

    /**
     * 别名：字体
     * 炫彩字体对象
     */
    XC_FONT(84),

    /**
     * 别名：图片帧
     * 图片帧（指定图片的渲染属性）
     */
    XC_IMAGE_FRAME(88),

    /**
     * 别名：SVG
     * SVG矢量图形对象
     */
    XC_SVG(89),

    /**
     * 布局对象（已废弃，对应 LayoutObject）
     */
    XC_LAYOUT_OBJECT(101),

    /**
     * 别名：数据适配器
     * 基础数据适配器（对应 Adapter）
     */
    XC_ADAPTER(102),

    /**
     * 别名：数据适配器表
     * 表格数据适配器（对应 AdapterTable）
     */
    XC_ADAPTER_TABLE(103),

    /**
     * 别名：数据适配器树
     * 树结构数据适配器（对应 AdapterTree）
     */
    XC_ADAPTER_TREE(104),

    /**
     * 别名：数据适配器列表视图
     * 列表视图数据适配器（对应 AdapterListView）
     */
    XC_ADAPTER_LISTVIEW(105),

    /**
     * 别名：数据适配器MAP
     * MAP结构数据适配器（对应 AdapterMap）
     */
    XC_ADAPTER_MAP(106),

    /**
     * 无实体对象，仅用于判断布局（内部使用）
     */
    XC_LAYOUT_LISTVIEW(111),

    /**
     * 无实体对象，仅用于判断布局（内部使用）
     */
    XC_LAYOUT_LIST(112),

    /**
     * 无实体对象，仅用于判断布局（内部使用）
     */
    XC_LAYOUT_OBJECT_GROUP(113),

    /**
     * 无实体对象，仅用于判断布局（内部使用）
     */
    XC_LAYOUT_OBJECT_ITEM(114),

    /**
     * 无实体对象，仅用于判断布局（内部使用）
     */
    XC_LAYOUT_PANEL(115),

    /**
     * 别名：背景管理器
     * 背景管理对象（对应 BKInfoM）
     */
    XC_BKINFOM(116),

    /**
     * 无实体对象，仅用于判断类型
     * 别名：布局盒子
     * 布局盒子（复合类型）
     */
    XC_LAYOUT_BOX(124),

    /**
     * 别名：动画序列
     * 动画序列对象
     */
    XC_ANIMATION_SEQUENCE(131),

    /**
     * 别名：动画组
     * 动画同步组对象
     */
    XC_ANIMATION_GROUP(132),

    /**
     * 别名：动画项
     * 单个动画项对象
     */
    XC_ANIMATION_ITEM(133);

    // 炫彩对象类型对应的原始数值（与 C++ 枚举完全一致）
    private final int value;

    /**
     * 构造函数：绑定枚举与对应的原始数值
     * @param value C++ 枚举对应的整数值
     */
    XcObjectType(int value) {
        this.value = value;
    }

    /**
     * 获取枚举对应的原始数值（用于调用 WINAPI 时传入/接收）
     * @return 炫彩对象类型的整数值
     */
    public int getValue() {
        return value;
    }

    /**
     * 根据原始数值反向获取枚举实例（用于解析 WINAPI 返回的整数值）
     * @param value 炫彩对象类型的整数值
     * @return 对应的枚举实例，无匹配值时返回 null
     */
    public static XcObjectType fromValue(int value) {
        for (XcObjectType type : values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }
}
