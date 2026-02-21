package com.scm.all.pfunc;

public class XEleEventCallBack {
    /**
     布局调整完成事件
     @param hEle 元素句柄
     @param nFlags 调整标识 AdjustLayout
     @param nAdjustNo 调整布局流水号
     @return true 表示已处理该事件，false 表示未处理
     */
    public boolean OnAdjustLayoutEnd (int hEle, long nFlags,int nAdjustNo){return false;}
    /**
     按钮点击事件
     @param hEle 按钮元素句柄
     @return true 表示已处理该事件，false 表示未处理
     */
    public boolean OnBtnClick (int hEle){return false;}
    /**
     按钮选中事件
     @param hEle 按钮元素句柄
     @param bCheck 是否选中
     @return true 表示已处理该事件，false 表示未处理
     */
    public boolean OnButtonCheck (int hEle,boolean bCheck){return false;}
    /**
     元素按键字符事件（参考 MSDN WM_CHAR）
     @param hEle 元素句柄
     @param wParam 按键消息参数
     @param lParam 按键消息参数
     @return true 表示已处理该事件，false 表示未处理
     */
    public boolean OnChar (int hEle, int wParam,int lParam){return false;}
    /**
     组合框下拉列表退出事件
     @param hEle 组合框元素句柄
     @return true 表示已处理该事件，false 表示未处理
     */
    public boolean OnComboBoxExitList (int hEle){return false;};
    /**
     组合框下拉列表项选择事件
     @param hEle 组合框元素句柄
     @param iItem 选中项索引
     @return true 表示已处理该事件，false 表示未处理
     */
    public boolean OnComboBoxSelect (int hEle, int iItem){return false;}
    /**
     组合框下拉列表项选择完成事件（编辑框内容已改变）
     @param hEle 组合框元素句柄
     @param iItem 选中项索引
     @return true 表示已处理该事件，false 表示未处理
     */
    public boolean OnComboBoxSelectEnd (int hEle, int iItem){return false;}
    /**
     日期时间元素内容改变事件
     @param hEle 日期时间元素句柄
     @return true 表示已处理该事件，false 表示未处理
     */
    public boolean OnDateTimeChange (int hEle){return false;}
    /**
     日期时间元素_弹出的月历卡片退出事件
     @param hEle 日期时间元素句柄
     @param hMonthCalWnd 月历卡片窗口句柄
     @param hMonthCal 月历元素句柄
     @return 返回结果，true 表示处理了事件，false 表示未处理
     */
    public boolean OnDateTimeExitMonthCal (int hEle, int hMonthCalWnd, int hMonthCal) { return false; }
    /**
     日期时间元素_弹出月历卡片事件
     @param hEle 日期时间元素句柄
     @param hMonthCalWnd 月历卡片窗口句柄
     @param hMonthCal 月历元素句柄
     @return 返回结果，true 表示处理了事件，false 表示未处理
     */
    public boolean OnDateTimePopupMonthCal (int hEle, int hMonthCalWnd, int hMonthCal) { return false; }
    /**
     元素_即将销毁事件
     @param hEle 元素句柄
     @return 返回结果，true 表示处理了事件，false 表示未处理
     */
    public boolean OnDestroy (int hEle) { return false; }
    /**
     元素_销毁完成事件
     @param hEle 元素句柄
     @return 返回结果，true 表示处理了事件，false 表示未处理
     */
    public boolean OnDestroyeEnd (int hEle) { return false; }
    /**
     元素_文件拖放事件
     @param hEle 元素句柄
     @param filesInfo 拖放的文件信息
     @return 返回结果，true 表示处理了事件，false 表示未处理
     */
    public boolean OnDropFiles (int hEle, String filesInfo) { return false; }
    /**
     编辑框_内容改变事件
     @param hEle 编辑框元素句柄
     @return 返回结果，true 表示处理了事件，false 表示未处理
     */
    public boolean OnEditChanged (int hEle) { return false; }
    /**
     编辑颜色_颜色改变事件
     @param hEle 编辑颜色元素句柄
     @param color 颜色值
     @return 返回结果，true 表示处理了事件，false 表示未处理
     */
    public boolean OnEditColorChange (int hEle, long color) { return false; }
    /**
     编辑框_绘制行事件
     @param hEle 编辑框元素句柄
     @param hDraw 图形绘制句柄
     @param iRow 行索引
     @return 返回结果，true 表示处理了事件，false 表示未处理
     */
    public boolean OnEditDrawRow (int hEle, long hDraw, int iRow) { return false; }
    /**
     编辑框_回车获取 TAB 对齐事件
     @param hEle 编辑框元素句柄
     @return 返回结果，true 表示处理了事件，false 表示未处理
     */
    public boolean OnEditEnterGetTabAlign (int hEle) { return false; }
    /**
     编辑框_插入符位置改变事件
     @param hEle 编辑框元素句柄
     @param iPos 位置索引
     @return 返回结果，true 表示处理了事件，false 表示未处理
     */
    public boolean OnEditPosChanged (int hEle, int iPos) { return false; }
    /**
     代码编辑框 - 断点位置改变事件
     @param hEle 元素句柄
     @param iRow 更改行开始位置索引
     @param nChangeRows 改变行数（正数添加行，负数删除行）
     @return 返回处理结果
     **/
    public boolean OnEditorBreakpointChanged (int hEle,int iRow, int nChangeRows){return false;}
    /**
     编辑框 - 设置内容事件
     @param hEle 元素句柄
     @return 返回处理结果
     **/
    public boolean OnEditSet (int hEle){return false;}
    /**
     编辑框 - 样式改变事件
     @param hEle 元素句柄
     @param iStyle 样式类型
     @return 返回处理结果
     **/
    public boolean OnEditStyleChanged (int hEle,int iStyle){return false;}
    /**
     编辑框 - 交换行事件
     @param hEle 元素句柄
     @param iRow 行索引
     @param bArrowUp 方向（向上箭头为 1，向下箭头为 0）
     @return 返回处理结果
     **/
    public boolean OnEditSwapRow (int hEle,int iRow,int bArrowUp){return false;}
    /**
     代码编辑框 - 自动匹配选择事件
     @param hEle 元素句柄
     @param iRow 行索引
     @param nRows 行数
     @return 返回处理结果
     **/
    public boolean OnEditorAutoMatchSelect (int hEle,int iRow, int nRows){return false;}
    /**
     代码编辑框 - 格式代码检测事件
     @param hEle 元素句柄
     @param iRow 行索引
     @param iCol 列索引
     @return 返回处理结果
     **/
    public boolean OnEditorFormatCodeTest (int hEle,int iRow, int iCol){return false;}
    /**
     代码编辑框 - 多行内容修改事件
     @param hEle 元素句柄
     @param iRow 开始行索引
     @param nRows 改变行数
     @return 返回处理结果
     **/
    public boolean OnEditChangeRows (int hEle,int iRow, int nRows){return false;}
    /**
     代码编辑框 - 移除断点事件
     @param hEle 元素句柄
     @param iRow 行索引
     @return 返回处理结果
     **/
    public boolean OnEditorRemoveBreakpoint (int hEle,int iRow){return false;}
    /**
     代码编辑框 - 设置断点事件
     @param hEle 元素句柄
     @param iRow 行索引
     @param bCheck 是否设置断点（true 设置，false 移除）
     @return 返回处理结果
     **/
    public boolean OnEditorSetBreakpoint (int hEle,int iRow, boolean bCheck){return false;}
    /**
     元素 - 处理过程事件
     @param hEle 元素句柄
     @param nEvent 事件类型
     @param wParam 事件参数
     @param lParam 事件参数
     @return 返回处理结果
     **/
    public boolean OnEventProc (int hEle,long nEvent, int wParam, int lParam){return false;}
    /**
     元素 - 按键按下事件
     @param hEle 元素句柄
     @param wParam 按键参数
     @param lParam 按键参数
     @return 返回处理结果
     **/
    public boolean OnKeyDown (int hEle, int wParam, int lParam){return false;}
    /**
     元素 - 按键弹起事件
     @param hEle 元素句柄
     @param wParam 按键参数
     @param lParam 按键参数
     @return 返回处理结果
     **/
    public boolean OnKeyUp (int hEle, int wParam, int lParam){return false;}
    /**
     元素 - 失去鼠标捕获事件
     @param hEle 元素句柄
     @return 返回处理结果
     **/
    public boolean OnKillCapture (int hEle){return false;}
    /**
     元素 - 失去焦点事件
     @param hEle 元素句柄
     @return 返回处理结果
     **/
    public boolean OnKillFocus (int hEle){return false;}
    /**
     元素 - 鼠标左键双击事件
     @param hEle 元素句柄
     @param flags 鼠标状态标志
     @param x 鼠标 X 坐标
     @param y 鼠标 Y 坐标
     @return 返回处理结果
     **/
    public boolean OnLButtonDBClick (int hEle, long flags, int x, int y){return false;}
    /**
     元素 - 鼠标左键按下事件
     @param hEle 元素句柄
     @param flags 鼠标状态标志
     @param x 鼠标 X 坐标
     @param y 鼠标 Y 坐标
     @return 返回处理结果
     **/
    public boolean OnLButtonDown (int hEle, long flags, int x, int y){return false;}
    /**
     元素 - 鼠标左键弹起事件
     @param hEle 元素句柄
     @param flags 鼠标状态标志
     @param x 鼠标 X 坐标
     @param y 鼠标 Y 坐标
     @return 返回处理结果
     **/
    public boolean OnLButtonUp (int hEle, long flags, int x, int y){return false;}
    /**
     列表 - 绘制项事件
     @param hEle 元素句柄
     @param hDraw 图形绘制句柄
     @param index 行索引
     @param iSubItem 子项索引（列索引）
     @param nState 项状态
     @param hLayout 布局元素
     @param hTemp 项模板
     @return 返回处理结果
     **/
    public boolean OnListDrawItem (int hEle,long hDraw ,int index,int iSubItem,int nState,int hLayout,int hTemp){return false;}
    /**
     列表 - 列表头项模板创建事件
     @param hEle 元素句柄
     @param index 项索引
     @param bSort 是否支持排序
     @param nSortType 排序方式（0 无效，1 升序，2 降序）
     @param iColumnAdapter 对应数据适配器中的列索引
     @param nState 状态
     @param hLayout 布局元素
     @param hTemp 项模板
     @return 返回处理结果
     **/
    public boolean OnListHeaderTemplateCreate (int hEle,int index,boolean bSort,int nSortType,int iColumnAdapter,int nState,int hLayout,int hTemp){return false;}
    /**
     列表 - 列表头项点击事件
     @param hEle 元素句柄
     @param iItem 项索引
     @return 返回处理结果
     **/
    public boolean OnListHeaderClick (int hEle,int iItem){return false;}
    /**
     列表 - 列表头绘制项事件
     @param hEle 元素句柄
     @param hDraw 图形绘制句柄
     @param index 项索引
     @param bSort 是否支持排序
     @param nSortType 排序方式（0 无效，1 升序，2 降序）
     @param iColumnAdapter 对应数据适配器中的列索引
     @param nState 状态
     @param hLayout 布局元素
     @param hTemp 项模板
     @return 返回处理结果
     **/
    public boolean OnListHeaderDrawItem (int hEle,long hDraw, int index,boolean bSort,int nSortType,int iColumnAdapter,int nState,int hLayout,int hTemp){return false;}
    /**
     列表 - 列表头项模板创建完成事件
     @param hEle 元素句柄
     @param index 项索引
     @param bSort 是否支持排序
     @param nSortType 排序方式（0 无效，1 升序，2 降序）
     @param iColumnAdapter 对应数据适配器中的列索引
     @param nState 状态
     @param hLayout 布局元素
     @param hTemp 项模板
     @return 返回处理结果
     **/
    public boolean OnListHeaderTemplateCreateEnd (int hEle,int index,boolean bSort,int nSortType,int iColumnAdapter,int nState,int hLayout,int hTemp){return false;}
    /**
     列表 - 列表头项模板销毁事件
     @param hEle 元素句柄
     @param index 项索引
     @param bSort 是否支持排序
     @param nSortType 排序方式（0 无效，1 升序，2 降序）
     @param iColumnAdapter 对应数据适配器中的列索引
     @param nState 状态
     @param hLayout 布局元素
     @param hTemp 项模板
     @return 返回处理结果
     **/
    public boolean OnListHeaderTemplateDestroy (int hEle,int index,boolean bSort,int nSortType,int iColumnAdapter,int nState,int hLayout,int hTemp){return false;}
    /**
     列表 - 列表头项宽度改变事件
     @param hEle 元素句柄
     @param iItem 项索引
     @param nWidth 新宽度
     @return 返回处理结果
     **/
    public boolean OnListHeaderItemWidthChange (int hEle,int iItem,int nWidth){return false;}
    /**
     列表 - 项选择事件
     @param hEle 元素句柄
     @param iItem 项索引
     @return 返回处理结果
     **/
    public boolean OnListSelect (int hEle,int iItem){return false;}
    /**
     列表 - 项模板创建事件
     @param hEle 元素句柄
     @param index 行索引
     @param iSubItem 子项索引（列索引）
     @param nState 项状态
     @param hLayout 布局元素
     @param hTemp 项模板
     @param nFlag 标识（0: 状态改变；1: 新模板实例；2: 旧模板复用）
     @return 返回处理结果
     **/
    public boolean OnListTemplateCreate (int hEle,int index,int iSubItem,int nState,int hLayout,int hTemp,int nFlag){return false;}
    /**
     * 列表元素-项模板创建完成事件
     * @param hEle 元素句柄
     * @param index 行索引
     * @param iSubItem 子项索引(列索引)
     * @param nState 状态
     * @param hLayout 布局元素句柄
     * @param hTemp 列表项模板句柄
     * @param nFlag 0:状态改变(复用); 1:新模板实例; 2:旧模板复用
     * @return 返回处理结果
     */
    public boolean OnListTemplateCreateEnd(int hEle, int index, int iSubItem, int nState, int hLayout, int hTemp, int nFlag) { return false; }

    /**
     * 列表元素-项模板销毁事件
     * @param hEle 元素句柄
     * @param index 行索引
     * @param iSubItem 子项索引(列索引)
     * @param nState 状态
     * @param hLayout 布局元素句柄
     * @param hTemp 列表项模板句柄
     * @param nFlag 0:正常销毁; 1:移动到缓存(不会被销毁,临时缓存备用,当需要时被复用)
     * @return 返回处理结果
     */
    public boolean OnListTemplateDestroy(int hEle, int index, int iSubItem, int nState, int hLayout, int hTemp, int nFlag) { return false; }

    /**
     * 列表框元素-项绘制事件
     * @param hEle 元素句柄
     * @param hDraw 图形绘制句柄
     * @param index 项索引
     * @param nHeight 项默认高度
     * @param nSelHeight 项选中时高度
     * @param nState 状态
     * @param hLayout 布局元素句柄
     * @param hTemp 列表项模板句柄
     * @return 返回处理结果
     */
    public boolean OnListBoxDrawItem(int hEle, long hDraw, int index, int nHeight, int nSelHeight, int nState, int hLayout, int hTemp) { return false; }

    /**
     * 列表框元素-项选择事件
     * @param hEle 元素句柄
     * @param iItem 项索引
     * @return 返回处理结果
     */
    public boolean OnListBoxSelect(int hEle, int iItem) { return false; }

    /**
     * 列表框元素-项模板创建事件
     * @param hEle 元素句柄
     * @param index 项索引
     * @param nHeight 项默认高度
     * @param nSelHeight 项选中时高度
     * @param nState 状态
     * @param hLayout 布局元素句柄
     * @param hTemp 列表项模板句柄
     * @param nFlag 0:状态改变; 1:新模板实例; 2:旧模板复用
     * @return 返回处理结果
     */
    public boolean OnListBoxTemplateCreate(int hEle, int index, int nHeight, int nSelHeight, int nState, int hLayout, int hTemp, int nFlag) { return false; }

    /**
     * 列表框元素-项模板创建完成事件
     * @param hEle 元素句柄
     * @param index 项索引
     * @param nHeight 项默认高度
     * @param nSelHeight 项选中时高度
     * @param nState 状态
     * @param hLayout 布局元素句柄
     * @param hTemp 列表项模板句柄
     * @param nFlag 0:状态改变(复用); 1:新模板实例; 2:旧模板复用
     * @return 返回处理结果
     */
    public boolean OnListBoxTemplateCreateEnd(int hEle, int index, int nHeight, int nSelHeight, int nState, int hLayout, int hTemp, int nFlag) { return false; }

    /**
     * 列表框元素-项模板销毁事件
     * @param hEle 元素句柄
     * @param index 项索引
     * @param nHeight 项默认高度
     * @param nSelHeight 项选中时高度
     * @param nState 状态
     * @param hLayout 布局元素句柄
     * @param hTemp 列表项模板句柄
     * @param nFlag 0:正常销毁; 1:移动到缓存(不会被销毁,临时缓存备用,当需要时被复用)
     * @return 返回处理结果
     */
    public boolean OnListBoxTemplateDestroy(int hEle, int index, int nHeight, int nSelHeight, int nState, int hLayout, int hTemp, int nFlag) { return false; }

    /**
     * 列表视元素-自绘项事件
     * @param hEle 元素句柄
     * @param hDraw 图形绘制句柄
     * @param iGroup 组索引
     * @param item 项在组中位置索引
     * @param nState 状态
     * @param hLayout 布局元素句柄
     * @param hTemp 列表项模板句柄
     * @return 返回处理结果
     */
    public boolean OnListViewDrawItem(int hEle, long hDraw, int iGroup, int item, int nState, int hLayout, int hTemp) { return false; }

    /**
     * 列表视元素-组展开收缩事件
     * @param hEle 元素句柄
     * @param iGroup 组索引
     * @param bExpand 是否展开
     * @return 返回处理结果
     */
    public boolean OnListViewExpand(int hEle, int iGroup, boolean bExpand) { return false; }

    /**
     * 列表视元素-项选择事件
     * @param hEle 元素句柄
     * @param iGroup 组索引
     * @param iItem 项在组中位置索引
     * @return 返回处理结果
     */
    public boolean OnListViewSelect(int hEle, int iGroup, int iItem) { return false; }

    /**
     * 列表视元素-项模板创建事件
     * @param hEle 元素句柄
     * @param nFlag 0:状态改变(当前未使用); 1新模板实例; 2旧模板复用
     * @param iGroup 组索引
     * @param item 项在组中位置索引
     * @param nState 状态
     * @param hLayout 布局元素句柄
     * @param hTemp 列表项模板句柄
     * @return 返回处理结果
     */
    public boolean OnListViewTemplateCreate(int hEle, int nFlag, int iGroup, int item, int nState, int hLayout, int hTemp) { return false; }

    /**
     * 列表视元素-项模板创建完成事件
     * @param hEle 元素句柄
     * @param nFlag 0:状态改变(复用,当前未使用); 1:新模板实例; 2:旧模板复用
     * @param iGroup 组索引
     * @param item 项在组中位置索引
     * @param nState 状态
     * @param hLayout 布局元素句柄
     * @param hTemp 列表项模板句柄
     * @return 返回处理结果
     */
    public boolean OnListViewTemplateCreateEnd(int hEle, int nFlag, int iGroup, int item, int nState, int hLayout, int hTemp) { return false; }

    /**
     * 列表视元素-项模板销毁事件
     * @param hEle 元素句柄
     * @param nFlag 0:正常销毁; 1:移动到缓存列表(不会被销毁,临时缓存备用,当需要时被复用)
     * @param iGroup 组索引
     * @param item 项在组中位置索引
     * @param nState 状态
     * @param hLayout 布局元素句柄
     * @param hTemp 列表项模板句柄
     * @return 返回处理结果
     */
    public boolean OnListViewTemplateDestroy(int hEle, int nFlag, int iGroup, int item, int nState, int hLayout, int hTemp) { return false; }

    /**
     * 菜单背景自绘事件
     * @param hEle 元素句柄
     * @param hDraw 图形绘制句柄
     * @param hMenu 菜单句柄
     * @param hWindow 当前菜单项的窗口句柄
     * @param nParentID 父项ID
     * @return 返回处理结果
     */
    public boolean OnMenuDrawBackground(int hEle, long hDraw, int hMenu, int hWindow, int nParentID) { return false; }

    /**
     * 菜单项自绘事件
     * @param hEle 元素句柄
     * @param hDraw 图形绘制句柄
     * @param hMenu 菜单句柄
     * @param hWindow 当前菜单项的窗口句柄
     * @param nID 项ID
     * @param nState 状态
     * @param hIcon 菜单项图标句柄
     * @param pText 文本内容
     * @return 返回处理结果
     */
    public boolean OnMenuDrawItem(int hEle, long hDraw, int hMenu, int hWindow, int nID, int nState, int hIcon, String pText) { return false; }

    /**
     * 菜单退出事件
     * @param hEle 元素句柄
     * @return 返回处理结果
     */
    public boolean OnMenuExit(int hEle) { return false; }

    /**
     * 菜单弹出事件
     * @param hEle 元素句柄
     * @param hMenu 菜单句柄
     * @return 返回处理结果
     */
    public boolean OnMenuPopup(int hEle, int hMenu) { return false; }

    /**
     * 菜单弹出窗口事件
     * @param hEle 元素句柄
     * @param hMenu 菜单句柄
     * @param hWindow 窗口句柄
     * @param nParentID 父项ID
     * @return 返回处理结果
     */
    public boolean OnMenuPopupWnd(int hEle, int hMenu, int hWindow, int nParentID) { return false; }

    /**
     * 菜单项选择事件
     * @param hEle 元素句柄
     * @param nItem 菜单项id
     * @return 返回处理结果
     */
    public boolean OnMenuSelect(int hEle, int nItem) { return false; }

    /**
     * 月历元素日期改变事件
     * @param hEle 元素句柄
     * @return 返回处理结果
     */
    public boolean OnCalendarChange(int hEle) { return false; }

    /**
     * 元素鼠标悬停事件
     * @param hEle 元素句柄
     * @param nFlags 鼠标事件标志
     * @param x 鼠标X坐标
     * @param y 鼠标Y坐标
     * @return 返回处理结果
     */
    public boolean OnMouseHover(int hEle, long nFlags, int x, int y) { return false; }

    /**
     * 元素鼠标离开事件
     * @param hEle 元素句柄
     * @param hEleStay 将获得鼠标停留状态的元素句柄
     * @return 返回处理结果
     */
    public boolean OnMouseLeave(int hEle, int hEleStay) { return false; }

    /**
     * 元素鼠标移动事件
     * @param hEle 元素句柄
     * @param nFlags 鼠标事件标志
     * @param x 鼠标X坐标
     * @param y 鼠标Y坐标
     * @return 返回处理结果
     */
    public boolean OnMouseMove(int hEle, long nFlags, int x, int y) { return false; }

    /**
     * 元素鼠标进入事件
     * @param hEle 元素句柄
     * @return 返回处理结果
     */
    public boolean OnMouseStay(int hEle) { return false; }

    /**
     * 元素鼠标滚轮滚动事件
     * @param hEle 元素句柄
     * @param nFlags 鼠标事件标志
     * @param x 鼠标X坐标
     * @param y 鼠标Y坐标
     * @return 返回处理结果
     */
    public boolean OnMouseWheel(int hEle, long nFlags, int x, int y) { return false; }

    /**
     * 元素绘制事件
     * @param hEle 元素句柄
     * @param hDraw 图形绘制句柄
     * @return 返回处理结果
     */
    public boolean OnDraw(int hEle, long hDraw) { return false; }

    /**
     * 元素及子元素绘制完成事件
     * @param hEle 元素句柄
     * @param hDraw 图形绘制句柄
     * @return 返回处理结果
     */
    public boolean OnPaintEnd(int hEle, long hDraw) { return false; }

    /**
     * 滚动视图绘制事件
     * @param hEle 元素句柄
     * @param hDraw 图形绘制句柄
     * @return 返回处理结果
     */
    public boolean OnDrawScrollView(int hEle, long hDraw) { return false; }

    /**
     * 属性网格项调整坐标事件
     * @param hEle 元素句柄
     * @param nType 类型
     * @param nID 项ID
     * @param nDepth 深度
     * @param nNameColWidth 名称列宽度
     * @param bExpand 是否展开
     * @param bShow 是否可见
     * @return 返回处理结果
     */
    public boolean OnPGridItemAdjustCoordinate(int hEle, int nType, int nID, int nDepth, int nNameColWidth, int bExpand, int bShow) { return false; }

    /**
     * 属性网格项销毁事件
     * @param hEle 元素句柄
     * @param nItemID 项ID
     * @return 返回处理结果
     */
    public boolean OnPGridItemDestroy(int hEle, int nItemID) { return false; }
    /**
     属性网格项展开 / 收缩事件
     @param hEle 元素句柄
     @param nItemID 项 ID
     @param bExpand 是否展开
     @return 是否处理了事件
     **/
    public boolean OnPGridItemExpand (int hEle, int nItemID, boolean bExpand) {
        return false;
    }
    /**
     属性网格项选择事件
     @param hEle 元素句柄
     @param nItemID 项 ID
     @return 是否处理了事件
     **/
    public boolean OnPGridItemSelect (int hEle, int nItemID) {
        return false;
    }
    /**
     属性网格项设置事件
     @param hEle 元素句柄
     @param nItemID 项 ID
     @return 是否处理了事件
     **/
    public boolean OnPGridItemSet (int hEle, int nItemID) {
        return false;
    }
    /**
     属性网格项值改变事件
     @param hEle 元素句柄
     @param nItemID 项 ID
     @return 是否处理了事件
     **/
    public boolean OnPGridValueChange (int hEle, int nItemID) {
        return false;
    }
    /**
     进度条进度改变事件
     @param hEle 元素句柄
     @param pos 当前进度位置
     @return 是否处理了事件
     **/
    public boolean OnProgressBarChange (int hEle, int pos) {
        return false;
    }
    /**
     鼠标右键按下事件
     @param hEle 元素句柄
     @param nFlags 鼠标状态标志（参考 MSDN WM_RBUTTONDOWN 消息 wParam 参数）
     @param x 鼠标 X 坐标
     @param y 鼠标 Y 坐标
     @return 是否处理了事件
     **/
    public boolean OnRButtonDown (int hEle, long nFlags, int x, int y) {
        return false;
    }
    /**
     鼠标右键弹起事件
     @param hEle 元素句柄
     @param nFlags 鼠标状态标志（参考 MSDN WM_RBUTTONUP 消息 wParam 参数）
     @param x 鼠标 X 坐标
     @param y 鼠标 Y 坐标
     @return 是否处理了事件
     **/
    public boolean OnRButtonUp (int hEle, long nFlags, int x, int y) {
        return false;
    }
    /**
     滚动条滚动事件
     @param hEle 元素句柄
     @param pos 当前滚动位置点
     @return 是否处理了事件
     **/
    public boolean OnSBarScroll (int hEle, int pos) {
        return false;
    }
    /**
     滚动视图水平滚动事件
     @param hEle 元素句柄
     @param pos 当前水平滚动位置
     @return 是否处理了事件
     **/
    public boolean OnScrollViewScrollH (int hEle, int pos) {
        return false;
    }
    /**
     滚动视图垂直滚动事件
     @param hEle 元素句柄
     @param pos 当前垂直滚动位置
     @return 是否处理了事件
     **/
    public boolean OnScrollViewScrollV (int hEle, int pos) {
        return false;
    }
    /**
     元素设置鼠标捕获事件
     @param hEle 元素句柄
     @return 是否处理了事件
     **/
    public boolean OnSetCapture (int hEle) {
        return false;
    }
    /**
     设置鼠标光标事件
     @param hEle 元素句柄
     @param wParam 系统消息参数（参考 MSDN WM_SETCURSOR 消息 wParam 参数）
     @param lParam 系统消息参数（参考 MSDN WM_SETCURSOR 消息 lParam 参数）
     @return 是否处理了事件
     **/
    public boolean OnSetCursor (int hEle, int wParam, int lParam) {
        return false;
    }
    /**
     元素获得焦点事件
     @param hEle 元素句柄
     @return 是否处理了事件
     **/
    public boolean OnSetFocus (int hEle) {
        return false;
    }
    /**
     元素设置字体事件
     @param hEle 元素句柄
     @return 是否处理了事件
     **/
    public boolean OnSetFont (int hEle) {
        return false;
    }
    /**
     元素显示 / 隐藏事件
     @param hEle 元素句柄
     @param bShow 是否显示
     @return 事件处理结果，true 表示已处理，false 表示未处理
     */
    public boolean OnShow (int hEle, boolean bShow) {
        return false;
    }
    /**
     元素大小改变事件
     @param hEle 元素句柄
     @param nFlags 调整布局标识位
     @param nAdjustNo 调整布局流水号
     @return 事件处理结果，true 表示已处理，false 表示未处理
     */
    public boolean OnSize (int hEle, long nFlags, int nAdjustNo) {
        return false;
    }
    /**
     滑动条元素滑块位置改变事件
     @param hEle 元素句柄
     @param pos 当前滑块位置
     @return 事件处理结果，true 表示已处理，false 表示未处理
     */
    public boolean OnSliderBarChange (int hEle, int pos) {
        return false;
    }
    /**
     TabBar 标签按钮删除事件
     @param hEle 元素句柄
     @param iItem 标签位置索引
     @return 事件处理结果，true 表示已处理，false 表示未处理
     */
    public boolean OnTabBarDelete (int hEle, int iItem) {
        return false;
    }
    /**
     TabBar 标签按钮选择改变事件
     @param hEle 元素句柄
     @param iItem 标签位置索引
     @return 事件处理结果，true 表示已处理，false 表示未处理
     */
    public boolean OnTabBarSelect (int hEle, int iItem) {
        return false;
    }
    /**
     元素工具提示弹出事件
     @param hEle 元素句柄
     @param hWindowTooltip 工具提示窗口句柄
     @param pText 工具提示内容
     @return 事件处理结果，true 表示已处理，false 表示未处理
     */
    public boolean OnTooltipPopup (int hEle, int hWindowTooltip, String pText) {
        return false;
    }
    /**
     列表树元素拖动项事件
     @param hEle 元素句柄
     @param nDragItem 拖动项 ID
     @param nDestItem 目标项 ID
     @param nType 停放相对目标位置 (0: 上，1: 下，3: 子项)
     @return 事件处理结果，true 表示已处理，false 表示未处理
     */
    public boolean OnTreeDragItem (int hEle, int nDragItem, int nDestItem, int nType) {
        return false;
    }
    /**
     列表树元素正在拖动项事件
     @param hEle 元素句柄
     @param nDragItem 拖动项 ID
     @param nDestItem 目标项 ID
     @param nType 停放相对目标位置 (0: 上，1: 下，3: 子项)
     @return 事件处理结果，true 表示已处理，false 表示未处理
     */
    public boolean OnTreeDragItemIng (int hEle, int nDragItem, int nDestItem, int nType) {
        return false;
    }
    /**
     列表树元素绘制项事件
     @param hEle 元素句柄
     @param hDraw 图形绘制句柄
     @param nID 项 ID
     @param nDepth 深度
     @param nHeight 项高度
     @param nSelHeight 项选中时高度
     @param bExpand 展开状态
     @param nState 项状态
     @param hLayout 布局元素句柄
     @param hTemp 项模板句柄
     @return 事件处理结果，true 表示已处理，false 表示未处理
     */
    public boolean OnTreeDrawItem (int hEle, long hDraw, int nID, int nDepth, int nHeight, int nSelHeight, boolean bExpand, int nState, int hLayout, int hTemp) {
        return false;
    }
    /**
     列表树元素项展开 / 收缩事件
     @param hEle 元素句柄
     @param id 项 ID
     @param bExpand 是否展开
     @return 事件处理结果，true 表示已处理，false 表示未处理
     */
    public boolean OnTreeExpand (int hEle, int id, int bExpand) {
        return false;
    }
    /**
     列表树元素项选择事件
     @param hEle 元素句柄
     @param nItemID 项 ID
     @return 事件处理结果，true 表示已处理，false 表示未处理
     */
    public boolean OnTreeSelect (int hEle, int nItemID) {
        return false;
    }
    /**
     列表树元素项模板创建事件
     @param hEle 元素句柄
     @param nFlag 标识 (0: 状态改变；1: 新模板实例；2: 旧模板复用)
     @param nID 项 ID
     @param nDepth 深度
     @param nHeight 项高度
     @param nSelHeight 项选中时高度
     @param bExpand 展开状态
     @param nState 项状态
     @param hLayout 布局元素句柄
     @param hTemp 项模板句柄
     @return 事件处理结果，true 表示已处理，false 表示未处理
     */
    public boolean OnTreeTemplateCreate (int hEle, int nFlag, int nID, int nDepth, int nHeight, int nSelHeight, boolean bExpand, int nState, int hLayout, int hTemp) {
        return false;
    }
    /**
     列表树元素项模板创建完成事件
     @param hEle 元素句柄
     @param nFlag 标识 (0: 状态改变 (复用); 1: 新模板实例；2: 旧模板复用)
     @param nID 项 ID
     @param nDepth 深度
     @param nHeight 项高度
     @param nSelHeight 项选中时高度
     @param bExpand 展开状态
     @param nState 项状态
     @param hLayout 布局元素句柄
     @param hTemp 项模板句柄
     @return 事件处理结果，true 表示已处理，false 表示未处理
     */
    public boolean OnTreeTemplateCreateEnd (int hEle, int nFlag, int nID, int nDepth, int nHeight, int nSelHeight, boolean bExpand, int nState, int hLayout, int hTemp) {
        return false;
    }
    /**
     列表树元素项模板销毁事件
     @param hEle 元素句柄
     @param nFlag 标识 (0: 正常销毁；1: 移动到缓存)
     @param nID 项 ID
     @param nDepth 深度
     @param nHeight 项高度
     @param nSelHeight 项选中时高度
     @param bExpand 展开状态
     @param nState 项状态
     @param hLayout 布局元素句柄
     @param hTemp 项模板句柄
     @return 事件处理结果，true 表示已处理，false 表示未处理
     */
    public boolean OnTreeTemplateDestroy (int hEle, int nFlag, int nID, int nDepth, int nHeight, int nSelHeight, boolean bExpand, int nState, int hLayout, int hTemp) {
        return false;
    }
    /**
     元素炫彩定时器事件
     @param hEle 元素句柄
     @param nTimerID 定时器 ID
     @return 事件处理结果，true 表示已处理，false 表示未处理
     */
    public boolean OnEleXCTimer (int hEle, long nTimerID) {
        return false;
    }
    /**
     自动事件处理函数
     @param hEle 元素句柄
     @param len 数据长度
     @param typeCode 类型码
     @param data 事件数据
     @return 事件处理结果，true 表示已处理，false 表示未处理
     */
    public boolean OnAutoEvent (int hEle, long len, long typeCode, byte [] data) {
        return false;
    }
    /**
     组合框下拉列表弹出事件
     @param hEle 元素句柄
     @param hWindow 下拉列表窗口句柄
     @param hListBox 列表框元素句柄
     @return 事件处理结果，true 表示已处理，false 表示未处理
     */
    public boolean OnComboBoxPopupList (int hEle, int hWindow, int hListBox) {
        return false;
    }
}
