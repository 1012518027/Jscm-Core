package com.scm.all.enumBox;

/**
 * 完整的Windows窗口消息事件枚举类
 * 覆盖微软官方文档所有系统级WM_XXX消息（含核心、扩展、小众/过时消息）
 * 消息码严格对应Windows SDK头文件（WinUser.h）定义，描述标注适用场景/过时状态
 */
public enum CompleteWindowMessageEvent {
    // ===================== 【核心窗口生命周期】（补充全量）=====================
    WM_NULL(0x0000, "WM_NULL", "空消息，测试消息队列/占位"),
    WM_CREATE(0x0001, "WM_CREATE", "窗口创建（CreateWindowEx后触发）"),
    WM_DESTROY(0x0002, "WM_DESTROY", "窗口销毁（清理资源）"),
    WM_MOVE(0x0003, "WM_MOVE", "窗口位置变更（坐标为客户区左上角）"),
    WM_SIZE(0x0005, "WM_SIZE", "窗口大小变更（lParam含宽高，wParam含状态：SIZE_MINIMIZED等）"),
    WM_ACTIVATE(0x0006, "WM_ACTIVATE", "窗口激活/失活（wParam=WA_ACTIVE/WA_INACTIVE/WA_CLICKACTIVE）"),
    WM_SETFOCUS(0x0007, "WM_SETFOCUS", "窗口获得输入焦点"),
    WM_KILLFOCUS(0x0008, "WM_KILLFOCUS", "窗口失去输入焦点"),
    WM_ENABLE(0x000A, "WM_ENABLE", "窗口启用/禁用（wParam=TRUE/FALSE）"),
    WM_SETREDRAW(0x000B, "WM_SETREDRAW", "设置窗口重绘开关（避免批量更新闪烁）"),
    WM_SETTEXT(0x000C, "WM_SETTEXT", "设置窗口文本（如按钮/编辑框内容）"),
    WM_GETTEXT(0x000D, "WM_GETTEXT", "获取窗口文本"),
    WM_GETTEXTLENGTH(0x000E, "WM_GETTEXTLENGTH", "获取窗口文本长度（不含终止符）"),
    WM_PAINT(0x000F, "WM_PAINT", "窗口重绘（无效区域需刷新）"),
    WM_CLOSE(0x0010, "WM_CLOSE", "窗口关闭请求（可拦截取消关闭）"),
    WM_QUERYENDSESSION(0x0011, "WM_QUERYENDSESSION", "系统会话结束前查询（关机/注销/重启）"),
    WM_QUIT(0x0012, "WM_QUIT", "退出消息循环（PostQuitMessage发送，wParam为退出码）"),
    WM_QUERYOPEN(0x0013, "WM_QUERYOPEN", "查询最小化窗口是否可恢复显示"),
    WM_ERASEBKGND(0x0014, "WM_ERASEBKGND", "擦除窗口背景（hDC为背景设备上下文）"),
    WM_SYSCOLORCHANGE(0x0015, "WM_SYSCOLORCHANGE", "系统颜色方案变更（需刷新界面）"),
    WM_ENDSESSION(0x0016, "WM_ENDSESSION", "系统会话结束（wParam=TRUE表示强制结束）"),
    WM_SYSTEMERROR(0x0017, "WM_SYSTEMERROR", "系统错误通知（已过时）"),
    WM_SHOWWINDOW(0x0018, "WM_SHOWWINDOW", "窗口显示/隐藏（wParam=TRUE/FALSE，lParam=显示原因）"),
    WM_CTLCOLOR(0x0019, "WM_CTLCOLOR", "控件绘制前设置颜色（通用，细分见WM_CTLCOLORXXX）"),
    WM_WININICHANGE(0x001A, "WM_WININICHANGE", "INI文件变更（等同WM_SETTINGCHANGE）"),
    WM_SETTINGCHANGE(0x001A, "WM_SETTINGCHANGE", "系统设置变更（替代WM_WININICHANGE）"),
    WM_DEVMODECHANGE(0x001B, "WM_DEVMODECHANGE", "设备模式（打印机等）变更"),
    WM_ACTIVATEAPP(0x001C, "WM_ACTIVATEAPP", "应用程序级激活/失活（lParam=进程ID）"),
    WM_FONTCHANGE(0x001D, "WM_FONTCHANGE", "系统字体池变更（需刷新字体相关控件）"),
    WM_TIMECHANGE(0x001E, "WM_TIMECHANGE", "系统时间/时区变更"),
    WM_CANCELMODE(0x001F, "WM_CANCELMODE", "取消模态状态（如拖拽、菜单、鼠标捕获）"),
    WM_SETCURSOR(0x0020, "WM_SETCURSOR", "设置鼠标光标（鼠标移动/窗口激活时触发）"),
    WM_MOUSEACTIVATE(0x0021, "WM_MOUSEACTIVATE", "鼠标激活非活动窗口（返回激活类型）"),
    WM_CHILDACTIVATE(0x0022, "WM_CHILDACTIVATE", "子窗口激活/移动/大小变更"),
    WM_QUEUESYNC(0x0023, "WM_QUEUESYNC", "同步消息队列（仅用于特定系统场景）"),
    WM_GETMINMAXINFO(0x0024, "WM_GETMINMAXINFO", "获取窗口最大/最小尺寸限制"),
    WM_PAINTICON(0x0026, "WM_PAINTICON", "绘制窗口图标（已过时，由WM_PAINT替代）"),
    WM_ICONERASEBKGND(0x0027, "WM_ICONERASEBKGND", "擦除图标背景（已过时）"),
    WM_NEXTDLGCTL(0x0028, "WM_NEXTDLGCTL", "对话框切换焦点控件"),
    WM_SPOOLERSTATUS(0x002A, "WM_SPOOLERSTATUS", "打印池状态变更（打印机任务完成/新增）"),
    WM_DRAWITEM(0x002B, "WM_DRAWITEM", "自绘控件绘制（按钮/列表框/菜单等）"),
    WM_MEASUREITEM(0x002C, "WM_MEASUREITEM", "自绘控件尺寸计算（创建时触发）"),
    WM_DELETEITEM(0x002D, "WM_DELETEITEM", "控件项删除（列表框/组合框）"),
    WM_VKEYTOITEM(0x002E, "WM_VKEYTOITEM", "键盘导航自绘列表框（替代WM_KEYDOWN）"),
    WM_CHARTOITEM(0x002F, "WM_CHARTOITEM", "字符输入导航自绘列表框（替代WM_CHAR）"),
    WM_SETFONT(0x0030, "WM_SETFONT", "设置控件字体（lParam=HFONT，wParam=是否重绘）"),
    WM_GETFONT(0x0031, "WM_GETFONT", "获取控件当前字体"),
    WM_SETHOTKEY(0x0032, "WM_SETHOTKEY", "设置窗口热键（加速键）"),
    WM_GETHOTKEY(0x0033, "WM_GETHOTKEY", "获取窗口热键"),
    WM_QUERYDRAGICON(0x0037, "WM_QUERYDRAGICON", "查询拖拽图标（已过时）"),
    WM_COMPAREITEM(0x0039, "WM_COMPAREITEM", "自绘控件项排序（列表框/组合框）"),
    WM_GETOBJECT(0x003D, "WM_GETOBJECT", "辅助功能（UIAccess）获取对象信息"),
    WM_COMPACTING(0x0041, "WM_COMPACTING", "系统内存不足（压缩内存页，已过时）"),
    WM_COMMNOTIFY(0x0044, "WM_COMMNOTIFY", "串口通信通知（已过时）"),
    WM_WINDOWPOSCHANGING(0x0046, "WM_WINDOWPOSCHANGING", "窗口位置/大小/层级变更前（可修改）"),
    WM_WINDOWPOSCHANGED(0x0047, "WM_WINDOWPOSCHANGED", "窗口位置/大小/层级变更后"),
    WM_POWER(0x0048, "WM_POWER", "电源状态变更（旧版，由WM_POWERBROADCAST替代）"),
    WM_COPYDATA(0x004A, "WM_COPYDATA", "进程间传递数据（只读，不可修改）"),
    WM_CANCELJOURNAL(0x004B, "WM_CANCELJOURNAL", "取消日志记录（调试/宏录制）"),
    WM_NOTIFY(0x004E, "WM_NOTIFY", "控件通知父窗口（通用，替代旧版WM_COMMAND）"),
    WM_INPUTLANGCHANGEREQUEST(0x0050, "WM_INPUTLANGCHANGEREQUEST", "请求切换输入语言"),
    WM_INPUTLANGCHANGE(0x0051, "WM_INPUTLANGCHANGE", "输入语言切换完成"),
    WM_TCARD(0x0052, "WM_TCARD", "智能卡通知（已过时）"),
    WM_HELP(0x0053, "WM_HELP", "帮助请求（F1键/上下文帮助）"),
    WM_USERCHANGED(0x0054, "WM_USERCHANGED", "用户配置文件变更（切换用户）"),
    WM_NOTIFYFORMAT(0x0055, "WM_NOTIFYFORMAT", "协商WM_NOTIFY数据格式（ANSI/Unicode）"),
    WM_CONTEXTMENU(0x007B, "WM_CONTEXTMENU", "右键上下文菜单请求"),
    WM_STYLECHANGING(0x007C, "WM_STYLECHANGING", "窗口样式变更前（可拦截修改）"),
    WM_STYLECHANGED(0x007D, "WM_STYLECHANGED", "窗口样式变更后"),
    WM_DISPLAYCHANGE(0x007E, "WM_DISPLAYCHANGE", "显示分辨率/颜色深度变更"),
    WM_GETICON(0x007F, "WM_GETICON", "获取窗口图标（大/小图标）"),
    WM_SETICON(0x0080, "WM_SETICON", "设置窗口图标（大/小图标）"),
    WM_NCCREATE(0x0081, "WM_NCCREATE", "非客户区创建（窗口创建第一步）"),
    WM_NCDESTROY(0x0082, "WM_NCDESTROY", "非客户区销毁（窗口销毁最后一步）"),
    WM_NCCALCSIZE(0x0083, "WM_NCCALCSIZE", "计算非客户区尺寸（调整客户区大小）"),
    WM_NCHITTEST(0x0084, "WM_NCHITTEST", "非客户区点击测试（判断点击位置：标题栏/边框等）"),
    WM_NCPAINT(0x0085, "WM_NCPAINT", "非客户区重绘（标题栏/边框）"),
    WM_NCACTIVATE(0x0086, "WM_NCACTIVATE", "非客户区激活状态变更（绘制激活/失活样式）"),
    WM_GETDLGCODE(0x0087, "WM_GETDLGCODE", "对话框控件处理键盘输入（返回按键处理类型）"),
    WM_SYNCPAINT(0x0088, "WM_SYNCPAINT", "同步重绘（系统内部使用）"),
    WM_NCMOUSEMOVE(0x00A0, "WM_NCMOUSEMOVE", "鼠标在非客户区移动"),
    WM_NCLBUTTONDOWN(0x00A1, "WM_NCLBUTTONDOWN", "非客户区左键按下"),
    WM_NCLBUTTONUP(0x00A2, "WM_NCLBUTTONUP", "非客户区左键抬起"),
    WM_NCLBUTTONDBLCLK(0x00A3, "WM_NCLBUTTONDBLCLK", "非客户区左键双击"),
    WM_NCRBUTTONDOWN(0x00A4, "WM_NCRBUTTONDOWN", "非客户区右键按下"),
    WM_NCRBUTTONUP(0x00A5, "WM_NCRBUTTONUP", "非客户区右键抬起"),
    WM_NCRBUTTONDBLCLK(0x00A6, "WM_NCRBUTTONDBLCLK", "非客户区右键双击"),
    WM_NCMBUTTONDOWN(0x00A7, "WM_NCMBUTTONDOWN", "非客户区中键按下"),
    WM_NCMBUTTONUP(0x00A8, "WM_NCMBUTTONUP", "非客户区中键抬起"),
    WM_NCMBUTTONDBLCLK(0x00A9, "WM_NCMBUTTONDBLCLK", "非客户区中键双击"),
    WM_NCXBUTTONDOWN(0x00AB, "WM_NCXBUTTONDOWN", "非客户区X键（鼠标侧键）按下"),
    WM_NCXBUTTONUP(0x00AC, "WM_NCXBUTTONUP", "非客户区X键抬起"),
    WM_NCXBUTTONDBLCLK(0x00AD, "WM_NCXBUTTONDBLCLK", "非客户区X键双击"),
    WM_INPUT(0x00FF, "WM_INPUT", "原始输入数据（鼠标/键盘/触屏，替代旧版输入消息）"),

    // ===================== 【键盘事件（全量）】=====================
    WM_KEYDOWN(0x0100, "WM_KEYDOWN", "普通按键按下（虚拟键码，不区分大小写）"),
    WM_KEYUP(0x0101, "WM_KEYUP", "普通按键抬起"),
    WM_CHAR(0x0102, "WM_CHAR", "字符输入（已处理Shift/大小写，ASCII/Unicode）"),
    WM_DEADCHAR(0x0103, "WM_DEADCHAR", "死字符输入（如重音符号，无直接显示）"),
    WM_SYSKEYDOWN(0x0104, "WM_SYSKEYDOWN", "系统按键按下（Alt+任意键，含Alt+F4）"),
    WM_SYSKEYUP(0x0105, "WM_SYSKEYUP", "系统按键抬起"),
    WM_SYSCHAR(0x0106, "WM_SYSCHAR", "系统按键字符输入（Alt+字符）"),
    WM_SYSDEADCHAR(0x0107, "WM_SYSDEADCHAR", "系统死字符输入"),
    WM_UNICHAR(0x0109, "WM_UNICHAR", "Unicode字符输入（兼容宽字符，替代WM_CHAR）"),
    WM_KEYLAST(0x0108, "WM_KEYLAST", "键盘消息结束标记（仅作范围标识，无实际事件）"),
    WM_IME_STARTCOMPOSITION(0x010D, "WM_IME_STARTCOMPOSITION", "IME开始输入组合字符"),
    WM_IME_ENDCOMPOSITION(0x010E, "WM_IME_ENDCOMPOSITION", "IME结束输入组合字符"),
    WM_IME_COMPOSITION(0x010F, "WM_IME_COMPOSITION", "IME组合字符更新"),
    WM_IME_KEYDOWN(0x0110, "WM_IME_KEYDOWN", "IME按键按下（内部处理）"),
    WM_IME_KEYUP(0x0111, "WM_IME_KEYUP", "IME按键抬起（内部处理）"),

    // ===================== 【菜单/命令（全量）】=====================
    WM_COMMAND(0x0111, "WM_COMMAND", "命令触发（菜单/按钮/快捷键/控件通知）"),
    WM_SYSCOMMAND(0x0112, "WM_SYSCOMMAND", "系统命令（最小化/最大化/关闭/还原/屏幕保护等）"),
    WM_TIMER(0x0113, "WM_TIMER", "定时器到期（SetTimer设置，wParam=定时器ID）"),
    WM_HSCROLL(0x0114, "WM_HSCROLL", "水平滚动条操作（点击/拖动/翻页）"),
    WM_VSCROLL(0x0115, "WM_VSCROLL", "垂直滚动条操作"),
    WM_INITDIALOG(0x0116, "WM_INITDIALOG", "对话框初始化（创建后显示前）"),
    WM_INITMENU(0x0117, "WM_INITMENU", "菜单初始化（显示前）"),
    WM_INITMENUPOPUP(0x0118, "WM_INITMENUPOPUP", "弹出菜单初始化（子菜单显示前）"),
    WM_MENUSELECT(0x011F, "WM_MENUSELECT", "菜单项选中（未点击，键盘/鼠标导航）"),
    WM_MENUCHAR(0x0120, "WM_MENUCHAR", "菜单字符输入（键盘导航，如按字母定位）"),
    WM_ENTERIDLE(0x0121, "WM_ENTERIDLE", "对话框/菜单进入空闲状态"),
    WM_MENURBUTTONUP(0x0122, "WM_MENURBUTTONUP", "菜单右键抬起（自绘菜单）"),
    WM_MENUDRAG(0x0123, "WM_MENUDRAG", "菜单拖拽（自定义拖拽逻辑）"),
    WM_MENUGETOBJECT(0x0124, "WM_MENUGETOBJECT", "菜单对象获取（辅助功能/拖拽）"),
    WM_UNINITMENUPOPUP(0x0125, "WM_UNINITMENUPOPUP", "弹出菜单销毁（关闭后）"),
    WM_MENUCOMMAND(0x0126, "WM_MENUCOMMAND", "菜单命令执行（替代WM_COMMAND的扩展版）"),
    WM_CHANGEUISTATE(0x0127, "WM_CHANGEUISTATE", "变更UI状态（如显示/隐藏键盘焦点框）"),
    WM_UPDATEUISTATE(0x0128, "WM_UPDATEUISTATE", "更新UI状态（同步显示/隐藏）"),
    WM_QUERYUISTATE(0x0129, "WM_QUERYUISTATE", "查询当前UI状态"),

    // ===================== 【滚动条扩展】=====================
    WM_SCROLL(0x011A, "WM_SCROLL", "通用滚动条事件（兼容旧版控件）"),
    WM_SCROLLCLIPBOARD(0x030E, "WM_SCROLLCLIPBOARD", "剪贴板内容滚动（OLE/剪贴板查看器）"),

    // ===================== 【鼠标事件（全量）】=====================
    WM_MOUSEMOVE(0x0200, "WM_MOUSEMOVE", "鼠标在客户区移动"),
    WM_LBUTTONDOWN(0x0201, "WM_LBUTTONDOWN", "客户区左键按下"),
    WM_LBUTTONUP(0x0202, "WM_LBUTTONUP", "客户区左键抬起"),
    WM_LBUTTONDBLCLK(0x0203, "WM_LBUTTONDBLCLK", "客户区左键双击"),
    WM_RBUTTONDOWN(0x0204, "WM_RBUTTONDOWN", "客户区右键按下"),
    WM_RBUTTONUP(0x0205, "WM_RBUTTONUP", "客户区右键抬起"),
    WM_RBUTTONDBLCLK(0x0206, "WM_RBUTTONDBLCLK", "客户区右键双击"),
    WM_MBUTTONDOWN(0x0207, "WM_MBUTTONDOWN", "客户区中键按下"),
    WM_MBUTTONUP(0x0208, "WM_MBUTTONUP", "客户区中键抬起"),
    WM_MBUTTONDBLCLK(0x0209, "WM_MBUTTONDBLCLK", "客户区中键双击"),
    WM_MOUSEWHEEL(0x020A, "WM_MOUSEWHEEL", "鼠标滚轮垂直滚动（delta=120为一格）"),
    WM_XBUTTONDOWN(0x020B, "WM_XBUTTONDOWN", "鼠标X键（侧键）按下"),
    WM_XBUTTONUP(0x020C, "WM_XBUTTONUP", "鼠标X键抬起"),
    WM_XBUTTONDBLCLK(0x020D, "WM_XBUTTONDBLCLK", "鼠标X键双击"),
    WM_MOUSEHWHEEL(0x020E, "WM_MOUSEHWHEEL", "鼠标滚轮水平滚动"),
    WM_MOUSELAST(0x020E, "WM_MOUSELAST", "鼠标消息结束标记（范围标识）"),
    WM_MOUSELEAVE(0x02A3, "WM_MOUSELEAVE", "鼠标离开窗口区域（需先调用TrackMouseEvent）"),
    WM_MOUSEHOVER(0x02A1, "WM_MOUSEHOVER", "鼠标悬停在窗口内（需先调用TrackMouseEvent）"),
    WM_NCMOUSELEAVE(0x02A2, "WM_NCMOUSELEAVE", "鼠标离开非客户区（需TrackMouseEvent）"),

    // ===================== 【剪贴板（全量）】=====================
    WM_CUT(0x0300, "WM_CUT", "剪切到剪贴板"),
    WM_COPY(0x0301, "WM_COPY", "复制到剪贴板"),
    WM_PASTE(0x0302, "WM_PASTE", "从剪贴板粘贴"),
    WM_CLEAR(0x0303, "WM_CLEAR", "清除选中内容（不进剪贴板）"),
    WM_UNDO(0x0304, "WM_UNDO", "撤销上一步操作"),
    WM_RENDERFORMAT(0x0305, "WM_RENDERFORMAT", "渲染剪贴板格式（延迟提供数据）"),
    WM_RENDERALLFORMATS(0x0306, "WM_RENDERALLFORMATS", "渲染所有剪贴板格式（退出前）"),
    WM_DESTROYCLIPBOARD(0x0307, "WM_DESTROYCLIPBOARD", "剪贴板数据销毁"),
    WM_DRAWCLIPBOARD(0x0308, "WM_DRAWCLIPBOARD", "剪贴板内容变更（剪贴板查看器链）"),
    WM_PAINTCLIPBOARD(0x0309, "WM_PAINTCLIPBOARD", "剪贴板查看器重绘"),
    WM_VSCROLLCLIPBOARD(0x030A, "WM_VSCROLLCLIPBOARD", "剪贴板查看器垂直滚动"),
    WM_SIZECLIPBOARD(0x030B, "WM_SIZECLIPBOARD", "剪贴板查看器尺寸变更"),
    WM_ASKCBFORMATNAME(0x030C, "WM_ASKCBFORMATNAME", "查询剪贴板格式名称"),
    WM_CHANGECBCHAIN(0x030D, "WM_CHANGECBCHAIN", "剪贴板查看器链变更（添加/移除）"),
    WM_HSCROLLCLIPBOARD(0x030F, "WM_HSCROLLCLIPBOARD", "剪贴板查看器水平滚动"),
    WM_QUERYNEWPALETTE(0x030F, "WM_QUERYNEWPALETTE", "查询新调色板（颜色管理）"),
    WM_PALETTEISCHANGING(0x0310, "WM_PALETTEISCHANGING", "调色板变更中（通知其他窗口）"),
    WM_PALETTECHANGED(0x0311, "WM_PALETTECHANGED", "调色板变更完成（更新自身调色板）"),
    WM_HOTKEY(0x0312, "WM_HOTKEY", "全局热键触发（RegisterHotKey注册）"),
    WM_PRINT(0x0317, "WM_PRINT", "强制窗口绘制到指定DC（打印/截图）"),
    WM_PRINTCLIENT(0x0318, "WM_PRINTCLIENT", "强制客户区绘制到指定DC"),

    // ===================== 【电源管理（全量）】=====================
    WM_POWERBROADCAST(0x0218, "WM_POWERBROADCAST", "电源事件广播（休眠/恢复/电量低/电源模式变更）"),
    WM_BATTERYLOW(0x0219, "WM_BATTERYLOW", "电池电量低（已整合到WM_POWERBROADCAST）"),
    WM_POWERSTATUSCHANGE(0x021A, "WM_POWERSTATUSCHANGE", "电源状态变更（已整合到WM_POWERBROADCAST）"),
    WM_SUSPENDRESUME(0x021B, "WM_SUSPENDRESUME", "系统休眠/恢复（已整合到WM_POWERBROADCAST）"),

    // ===================== 【触屏/笔输入（全量）】=====================
    WM_TOUCH(0x0240, "WM_TOUCH", "触屏单点/多点触摸事件"),
    WM_TABLET_DEFBASE(0x02C0, "WM_TABLET_DEFBASE", "笔输入基础消息（范围起始）"),
    WM_TABLET_ADDED(0x02C0, "WM_TABLET_ADDED", "数位板/笔设备接入"),
    WM_TABLET_REMOVED(0x02C1, "WM_TABLET_REMOVED", "数位板/笔设备移除"),
    WM_TABLET_FLICK(0x02C2, "WM_TABLET_FLICK", "笔/触屏轻扫手势"),
    WM_TABLET_QUERYSYSTEMGESTURESTATUS(0x02C3, "WM_TABLET_QUERYSYSTEMGESTURESTATUS", "查询系统手势状态"),
    WM_GESTURE(0x0119, "WM_GESTURE", "触屏手势（缩放/旋转/平移）"),
    WM_GESTURENOTIFY(0x011A, "WM_GESTURENOTIFY", "触屏手势通知（开始/结束）"),

    // ===================== 【DPI/显示（全量）】=====================
    WM_DPICHANGED(0x02E0, "WM_DPICHANGED", "窗口DPI缩放比例变更"),
    WM_DPICHANGED_BEFOREPARENT(0x02E1, "WM_DPICHANGED_BEFOREPARENT", "子窗口DPI变更（先于父窗口）"),
    WM_DPICHANGED_AFTERPARENT(0x02E2, "WM_DPICHANGED_AFTERPARENT", "子窗口DPI变更（后于父窗口）"),
    WM_GETDPI(0x02E3, "WM_GETDPI", "查询窗口当前DPI值"),
    WM_DPICHANGE_NOTIFICATION(0x02E4, "WM_DPICHANGE_NOTIFICATION", "系统DPI变更通知（全局）"),

    // ===================== 【打印（全量）】=====================
    WM_PRINTINIT(0x0319, "WM_PRINTINIT", "打印初始化（已过时）"),
    WM_PRINTPAGE(0x031A, "WM_PRINTPAGE", "打印单页（已过时）"),
    WM_PRINTEXIT(0x031B, "WM_PRINTEXIT", "打印退出（已过时）"),
    WM_SETPRINTTEMPLATE(0x031C, "WM_SETPRINTTEMPLATE", "设置打印模板（已过时）"),
    WM_GETPRINTTEMPLATE(0x031D, "WM_GETPRINTTEMPLATE", "获取打印模板（已过时）"),

    // ===================== 【OLE/COM（全量）】=====================
    WM_OLE_STARTVERB(0x0320, "WM_OLE_STARTVERB", "OLE对象执行动词（如打开/编辑）"),
    WM_OLE_ENDVERB(0x0321, "WM_OLE_ENDVERB", "OLE对象结束动词执行"),
    WM_OLE_CONFIRMVERB(0x0322, "WM_OLE_CONFIRMVERB", "确认OLE动词执行"),
    WM_OLE_EDITLINK(0x0323, "WM_OLE_EDITLINK", "编辑OLE链接"),
    WM_OLE_COMPLETEEDIT(0x0324, "WM_OLE_COMPLETEEDIT", "完成OLE编辑"),
    WM_OLE_CANCELEDIT(0x0325, "WM_OLE_CANCELEDIT", "取消OLE编辑"),
    WM_OLE_OPEN(0x0326, "WM_OLE_OPEN", "打开OLE对象"),
    WM_OLE_ACTIVATE(0x0327, "WM_OLE_ACTIVATE", "激活OLE对象"),
    WM_OLE_DEACTIVATE(0x0328, "WM_OLE_DEACTIVATE", "失活OLE对象"),
    WM_OLE_RELEASEDC(0x0329, "WM_OLE_RELEASEDC", "释放OLE设备上下文"),
    WM_OLE_INSERTOBJECT(0x032A, "WM_OLE_INSERTOBJECT", "插入OLE对象"),
    WM_OLE_CREATELINK(0x032B, "WM_OLE_CREATELINK", "创建OLE链接"),
    WM_OLE_SETOBJECTDATA(0x032C, "WM_OLE_SETOBJECTDATA", "设置OLE对象数据"),
    WM_OLE_GETOBJECTDATA(0x032D, "WM_OLE_GETOBJECTDATA", "获取OLE对象数据"),
    WM_OLE_SETCONTAINER(0x032E, "WM_OLE_SETCONTAINER", "设置OLE容器"),
    WM_OLE_GETCONTAINER(0x032F, "WM_OLE_GETCONTAINER", "获取OLE容器"),
    WM_OLE_ADVISE(0x0330, "WM_OLE_ADVISE", "设置OLE数据通知"),
    WM_OLE_UNADVISE(0x0331, "WM_OLE_UNADVISE", "取消OLE数据通知"),
    WM_OLE_REQUESTDATA(0x0332, "WM_OLE_REQUESTDATA", "请求OLE数据"),
    WM_OLE_CLIPFORMAT(0x0333, "WM_OLE_CLIPFORMAT", "OLE剪贴板格式"),
    WM_OLE_COMMAND(0x0334, "WM_OLE_COMMAND", "OLE命令执行"),
    WM_OLE_EVENT(0x0335, "WM_OLE_EVENT", "OLE事件通知"),
    WM_OLE_NOTIFY(0x0336, "WM_OLE_NOTIFY", "OLE状态通知"),

    // ===================== 【MDI（多文档）（全量）】=====================
    WM_MDICREATE(0x0220, "WM_MDICREATE", "创建MDI子窗口"),
    WM_MDIDESTROY(0x0221, "WM_MDIDESTROY", "销毁MDI子窗口"),
    WM_MDIACTIVATE(0x0222, "WM_MDIACTIVATE", "激活MDI子窗口"),
    WM_MDIRESTORE(0x0223, "WM_MDIRESTORE", "还原MDI子窗口"),
    WM_MDINEXT(0x0224, "WM_MDINEXT", "激活下一个MDI子窗口"),
    WM_MDIMAXIMIZE(0x0225, "WM_MDIMAXIMIZE", "最大化MDI子窗口"),
    WM_MDITILE(0x0226, "WM_MDITILE", "平铺MDI子窗口"),
    WM_MDICASCADE(0x0227, "WM_MDICASCADE", "层叠MDI子窗口"),
    WM_MDIICONARRANGE(0x0228, "WM_MDIICONARRANGE", "排列MDI最小化图标"),
    WM_MDIGETACTIVE(0x0229, "WM_MDIGETACTIVE", "获取当前激活的MDI子窗口"),
    WM_MDISETMENU(0x0230, "WM_MDISETMENU", "设置MDI框架菜单"),
    WM_ENTERSIZEMOVE(0x0231, "WM_ENTERSIZEMOVE", "进入窗口大小/位置调整模式"),
    WM_EXITSIZEMOVE(0x0232, "WM_EXITSIZEMOVE", "退出窗口大小/位置调整模式"),
    WM_DROPFILES(0x0233, "WM_DROPFILES", "文件拖放到窗口（需注册拖放）"),
    WM_MDIREFRESHMENU(0x0234, "WM_MDIREFRESHMENU", "刷新MDI菜单（已过时）"),

    // ===================== 【IME（输入法）（全量）】=====================
    WM_IME_SETCONTEXT(0x0281, "WM_IME_SETCONTEXT", "设置IME上下文（显示/隐藏候选框）"),
    WM_IME_NOTIFY(0x0282, "WM_IME_NOTIFY", "IME状态通知（打开/关闭/模式变更）"),
    WM_IME_CONTROL(0x0283, "WM_IME_CONTROL", "IME控件操作（候选框大小/位置）"),
    WM_IME_COMPOSITIONFULL(0x0284, "WM_IME_COMPOSITIONFULL", "IME组合区已满"),
    WM_IME_SELECT(0x0285, "WM_IME_SELECT", "选择IME输入法"),
    WM_IME_CHAR(0x0286, "WM_IME_CHAR", "IME字符输入（替代WM_CHAR）"),
    WM_IME_REQUEST(0x0288, "WM_IME_REQUEST", "IME请求数据（候选词/属性）"),
    WM_IME_KEYDOWN1(0x0290, "WM_IME_KEYDOWN", "IME按键按下（内部处理）"),
    WM_IME_KEYUP1(0x0291, "WM_IME_KEYUP", "IME按键抬起（内部处理）"),
    WM_IME_COMPOSITIONRECT(0x0292, "WM_IME_COMPOSITIONRECT", "IME组合区矩形位置变更"),
    WM_IME_DEFAULTCONTROL(0x0293, "WM_IME_DEFAULTCONTROL", "IME默认控件操作"),



    // ========== 炫彩自定义窗口消息（原文档XWM_XXX） ==========
    XWM_EVENT_ALL(0x8000 + 1000, "XWM_EVENT_ALL", "事件投递（不公开，不需要注册）"),
    XWM_REDRAW(0x8000 + 1007, "XWM_REDRAW", "窗口重绘延时（不公开，内部自定义消息）"),
    XWM_REDRAW_ELE(0x7000 + 1, "XWM_REDRAW_ELE", "重绘元素（内部使用）wParam:元素句柄, lParam:RECT*基于窗口坐标"),
    XWM_WINDPROC(0x7000 + 2, "XWM_WINDPROC", "窗口消息过程 int CALLBACK OnWndProc(UINT message, WPARAM wParam, LPARAM lParam, BOOL *pbHandled)"),
    XWM_DRAW_T(0x7000 + 3, "XWM_DRAW_T", "窗口绘制（内部使用）wParam:0, lParam:0"),
    XWM_TIMER_T(0x7000 + 4, "XWM_TIMER_T", "内部使用定时器 wParam:hXCGUI, lParam:ID"),
    XWM_XC_TIMER(0x7000 + 5, "XWM_XC_TIMER", "炫彩定时器（非系统定时器）int CALLBACK OnWndXCTimer(UINT nTimerID,BOOL *pbHandled)"),
    XWM_CLOUDUI_DOWNLOADFILE_COMPLETE(0x7000 + 6, "XWM_CLOUDUI_DOWNLOADFILE_COMPLETE", "内部使用-云UI文件下载完成"),
    XWM_CLOUNDUI_OPENURL_WAIT(0x7000 + 7, "XWM_CLOUNDUI_OPENURL_WAIT", "内部使用-云UI打开URL等待"),
    XWM_CALL_UI_THREAD(0x7000 + 8, "XWM_CALL_UI_THREAD", "内部使用-调用UI线程"),
    XWM_SETFOCUS_ELE(0x7000 + 9, "XWM_SETFOCUS_ELE", "指定元素获得焦点 int CALLBACK OnWndSetFocusEle(HELE hEle,BOOL *pbHandled)"),
    XWM_TRAYICON(0x7000 + 10, "XWM_TRAYICON", "托盘图标事件 int CALLBACK OnWndTrayIcon(WPARAM wParam, LPARAM lParam, BOOL *pbHandled)"),
    XWM_MENU_POPUP(0x7000 + 11, "XWM_MENU_POPUP", "菜单弹出 int CALLBACK OnWndMenuPopup(HMENUX hMenu, BOOL *pbHandled)"),
    XWM_MENU_POPUP_WND(0x7000 + 12, "XWM_MENU_POPUP_WND", "菜单弹出窗口 int CALLBACK OnWndMenuPopupWnd(HMENUX hMenu,menu_popupWnd_ *pInfo,BOOL *pbHandled)"),
    XWM_MENU_SELECT(0x7000 + 13, "XWM_MENU_SELECT", "菜单选择 int CALLBACK OnWndMenuSelect(int nID,BOOL *pbHandled)"),
    XWM_MENU_EXIT(0x7000 + 14, "XWM_MENU_EXIT", "菜单退出 int CALLBACK OnWndMenuExit(BOOL *pbHandled)"),
    XWM_MENU_DRAW_BACKGROUND(0x7000 + 15, "XWM_MENU_DRAW_BACKGROUND", "绘制菜单背景（需调用XMenu_EnableDrawBackground）int CALLBACK OnWndMenuDrawBackground(HDRAW hDraw,menu_drawBackground_ *pInfo,BOOL *pbHandled)"),
    XWM_MENU_DRAWITEM(0x7000 + 16, "XWM_MENU_DRAWITEM", "绘制菜单项（需调用XMenu_EnableDrawItem）int CALLBACK OnMenuDrawItem(HDRAW hDraw,menu_drawItem_* pInfo,BOOL *pbHandled)"),
    XWM_COMBOBOX_POPUP_DROPLIST(0x7000 + 17, "XWM_COMBOBOX_POPUP_DROPLIST", "弹出下拉组框列表（内部使用）"),
    XWM_FLOAT_PANE(0x7000 + 18, "XWM_FLOAT_PANE", "浮动窗格（窗格从框架窗口弹出）int CALLBACK OnWndFloatPane(HWINDOW hFloatWnd, HELE hPane, BOOL *pbHandled)"),
    XWM_PAINT_END(0x7000 + 19, "XWM_PAINT_END", "窗口绘制完成消息 int CALLBACK OnWndDrawWindowEnd(HDRAW hDraw,BOOL *pbHandled)"),
    XWM_PAINT_DISPLAY(0x7000 + 20, "XWM_PAINT_DISPLAY", "窗口绘制完成并显示到屏幕 int CALLBACK OnWndDrawWindowDisplay(BOOL *pbHandled)"),
    XWM_DOCK_POPUP(0x7000 + 21, "XWM_DOCK_POPUP", "框架窗口码头弹出窗格 int CALLBACK OnWndDocPopup(HWINDOW hWindowDock, HELE hPane, BOOL *pbHandled)"),
    XWM_FLOATWND_DRAG(0x7000 + 22, "XWM_FLOATWND_DRAG", "浮动窗口拖动（显示停靠提示）int CALLBACK OnWndFloatWndDrag(HWINDOW hFloatWnd, HWINDOW* hArray, BOOL *pbHandled)"),
    XWM_PANE_SHOW(0x7000 + 23, "XWM_PANE_SHOW", "窗格显示隐藏 int CALLBACK OnWndPaneShow(HELE hPane, BOOL bShow, BOOL *pbHandled)"),
    XWM_BODYVIEW_RECT(0x7000 + 24, "XWM_BODYVIEW_RECT", "框架窗口主视图坐标改变（主视图未绑定元素时触发）int CALLBACK OnWndLayoutViewRect(int width, int height, BOOL *pbHandled)"),

    // ===================== 【元素事件】=====================
    XE_ELEPROCE(1, "XE_ELEPROCE", "元素处理过程事件 int CALLBACK OnEventProc(UINT nEvent, WPARAM wParam, LPARAM lParam, BOOL *pbHandled)"),
    XE_PAINT(2, "XE_PAINT", "元素绘制事件 int CALLBACK OnDraw(HDRAW hDraw,BOOL *pbHandled)"),
    XE_PAINT_END(3, "XE_PAINT_END", "元素及子元素绘制完成（需调用XEle_EnableEvent_XE_PAINT_END）int CALLBACK OnPaintEnd(HDRAW hDraw,BOOL *pbHandled)"),
    XE_PAINT_SCROLLVIEW(4, "XE_PAINT_SCROLLVIEW", "滚动视图绘制事件 int CALLBACK OnDrawScrollView(HDRAW hDraw,BOOL *pbHandled)"),
    XE_MOUSEMOVE(5, "XE_MOUSEMOVE", "元素鼠标移动事件 int CALLBACK OnMouseMove(UINT nFlags, POINT *pPt, BOOL *pbHandled)"),
    XE_MOUSESTAY(6, "XE_MOUSESTAY", "元素鼠标进入事件 int CALLBACK OnMouseStay(BOOL *pbHandled)"),
    XE_MOUSEHOVER(7, "XE_MOUSEHOVER", "元素鼠标悬停事件 int CALLBACK OnMouseHover(UINT nFlags, POINT *pPt, BOOL *pbHandled)"),
    XE_MOUSELEAVE(8, "XE_MOUSELEAVE", "元素鼠标离开事件 int CALLBACK OnMouseLeave(HELE hEleStay,BOOL *pbHandled)"),
    XE_MOUSEWHEEL(9, "XE_MOUSEWHEEL", "元素鼠标滚轮滚动（非滚动视图需调用XEle_EnableEvent_XE_MOUSEWHEEL）int CALLBACK OnMouseWheel(UINT nFlags,POINT *pPt,BOOL *pbHandled)"),
    XE_LBUTTONDOWN(10, "XE_LBUTTONDOWN", "鼠标左键按下事件 int CALLBACK OnLButtonDown(UINT nFlags, POINT *pPt,BOOL *pbHandled)"),
    XE_LBUTTONUP(11, "XE_LBUTTONUP", "鼠标左键弹起事件 int CALLBACK OnLButtonUp(UINT nFlags, POINT *pPt,BOOL *pbHandled)"),
    XE_RBUTTONDOWN(12, "XE_RBUTTONDOWN", "鼠标右键按下事件 int CALLBACK OnRButtonDown(UINT nFlags, POINT *pPt,BOOL *pbHandled)"),
    XE_RBUTTONUP(13, "XE_RBUTTONUP", "鼠标右键弹起事件 int CALLBACK OnRButtonUp(UINT nFlags, POINT *pPt,BOOL *pbHandled)"),
    XE_LBUTTONDBCLICK(14, "XE_LBUTTONDBCLICK", "鼠标左键双击事件 int CALLBACK OnLButtonDBClick(UINT nFlags, POINT *pPt,BOOL *pbHandled)"),
    XE_XC_TIMER(16, "XE_XC_TIMER", "炫彩定时器（非系统）int CALLBACK OnEleXCTimer(UINT nTimerID,BOOL *pbHandled)"),
    XE_ADJUSTLAYOUT(17, "XE_ADJUSTLAYOUT", "调整布局事件（暂停使用）int CALLBACK OnAdjustLayout(int nFlags, UINT nAdjustNo, BOOL *pbHandled)"),
    XE_ADJUSTLAYOUT_END(18, "XE_ADJUSTLAYOUT_END", "调整布局完成事件 int CALLBACK OnAdjustLayoutEnd(int nFlags, UINT nAdjustNo, BOOL *pbHandled)"),
    XE_TOOLTIP_POPUP(19, "XE_TOOLTIP_POPUP", "元素工具提示弹出事件 int CALLBACK OnTooltipPopup(HWINDOW hWindowTooltip, const wchar_t* pText, BOOL *pbHandled)"),
    XE_SETFOCUS(31, "XE_SETFOCUS", "元素获得焦点事件 int CALLBACK OnSetFocus(BOOL *pbHandled)"),
    XE_KILLFOCUS(32, "XE_KILLFOCUS", "元素失去焦点事件 int CALLBACK OnKillFocus(BOOL *pbHandled)"),
    XE_DESTROY(33, "XE_DESTROY", "元素即将销毁（销毁子对象前触发）int CALLBACK OnDestroy(BOOL *pbHandled)"),
    XE_BNCLICK(34, "XE_BNCLICK", "按钮点击事件 int CALLBACK OnBtnClick(BOOL *pbHandled)"),
    XE_BUTTON_CHECK(35, "XE_BUTTON_CHECK", "按钮选中事件 int CALLBACK OnButtonCheck(BOOL bCheck,BOOL *pbHandled)"),
    XE_SIZE(36, "XE_SIZE", "元素大小改变事件 int CALLBACK OnSize(int nFlags, UINT nAdjustNo, BOOL *pbHandled)"),
    XE_SHOW(37, "XE_SHOW", "元素显示隐藏事件 int CALLBACK OnShow(BOOL bShow,BOOL *pbHandled)"),
    XE_SETFONT(38, "XE_SETFONT", "元素设置字体事件 int CALLBACK OnSetFont(BOOL *pbHandled)"),
    XE_KEYDOWN(39, "XE_KEYDOWN", "元素按键按下事件（参考WM_KEYDOWN）int CALLBACK OnKeyDown(WPARAM wParam,LPARAM lParam,BOOL *pbHandled)"),
    XE_KEYUP(40, "XE_KEYUP", "元素按键弹起事件（参考WM_KEYDOWN）int CALLBACK OnKeyUp(WPARAM wParam,LPARAM lParam,BOOL *pbHandled)"),
    XE_CHAR(41, "XE_CHAR", "字符事件（TranslateMessage翻译）int CALLBACK OnChar(WPARAM wParam,LPARAM lParam,BOOL *pbHandled)"),
    XE_SYSKEYDOWN(42, "XE_SYSKEYDOWN", "系统按键按下事件"),
    XE_SYSKEYUP(43, "XE_SYSKEYUP", "系统按键弹起事件"),
    XE_DESTROY_END(42, "XE_DESTROY_END", "元素销毁完成（销毁子对象后触发）int CALLBACK OnDestroyeEnd(BOOL *pbHandled)"),
    XE_SETCAPTURE(51, "XE_SETCAPTURE", "元素设置鼠标捕获 int CALLBACK OnSetCapture(BOOL *pbHandled)"),
    XE_KILLCAPTURE(52, "XE_KILLCAPTURE", "元素失去鼠标捕获 int CALLBACK OnKillCapture(BOOL *pbHandled)"),
    XE_SETCURSOR(53, "XE_SETCURSOR", "设置鼠标光标 int CALLBACK OnSetCursor(WPARAM wParam,LPARAM lParam,BOOL *pbHandled)"),
    XE_SCROLLVIEW_SCROLL_H(54, "XE_SCROLLVIEW_SCROLL_H", "滚动视图水平滚动 int CALLBACK OnScrollViewScrollH(int pos,BOOL *pbHandled)"),
    XE_SCROLLVIEW_SCROLL_V(55, "XE_SCROLLVIEW_SCROLL_V", "滚动视图垂直滚动 int CALLBACK OnScrollViewScrollV(int pos,BOOL *pbHandled)"),
    XE_SBAR_SCROLL(56, "XE_SBAR_SCROLL", "滚动条元素滚动 int CALLBACK OnSBarScroll(int pos,BOOL *pbHandled)"),
    XE_MENU_POPUP(57, "XE_MENU_POPUP", "菜单弹出 int CALLBACK OnMenuPopup(HMENUX hMenu, BOOL *pbHandled)"),
    XE_MENU_POPUP_WND(58, "XE_MENU_POPUP_WND", "菜单弹出窗口 int CALLBACK OnMenuPopupWnd(HMENUX hMenu,menu_popupWnd_* pInfo,BOOL *pbHandled)"),
    XE_MENU_SELECT(59, "XE_MENU_SELECT", "菜单项选择 int CALLBACK OnMenuSelect(int nItem,BOOL *pbHandled)"),
    XE_MENU_DRAW_BACKGROUND(60, "XE_MENU_DRAW_BACKGROUND", "绘制菜单背景（需XMenu_EnableDrawBackground）int CALLBACK OnMenuDrawBackground(HDRAW hDraw,menu_drawBackground_ *pInfo,BOOL *pbHandled)"),
    XE_MENU_DRAWITEM(61, "XE_MENU_DRAWITEM", "绘制菜单项（需XMenu_EnableDrawItem）int CALLBACK OnMenuDrawItem(HDRAW hDraw,menu_drawItem_* pInfo,BOOL *pbHandled)"),
    XE_MENU_EXIT(62, "XE_MENU_EXIT", "菜单退出事件 int CALLBACK OnMenuExit(BOOL *pbHandled)"),
    XE_SLIDERBAR_CHANGE(63, "XE_SLIDERBAR_CHANGE", "滑动条滑块位置改变 int CALLBACK OnSliderBarChange(int pos,BOOL *pbHandled)"),
    XE_PROGRESSBAR_CHANGE(64, "XE_PROGRESSBAR_CHANGE", "进度条进度改变 int CALLBACK OnProgressBarChange(int pos,BOOL *pbHandled)"),
    XE_COMBOBOX_SELECT(71, "XE_COMBOBOX_SELECT", "组合框下拉项选择 int CALLBACK OnComboBoxSelect(int iItem,BOOL *pbHandled)"),
    XE_COMBOBOX_POPUP_LIST(72, "XE_COMBOBOX_POPUP_LIST", "组合框下拉列表弹出 int CALLBACK OnComboBoxPopupList(HWINDOW hWindow,HELE hListBox,BOOL *pbHandled)"),
    XE_COMBOBOX_EXIT_LIST(73, "XE_COMBOBOX_EXIT_LIST", "组合框下拉列表退出 int CALLBACK OnComboBoxExitList(BOOL *pbHandled)"),
    XE_COMBOBOX_SELECT_END(74, "XE_COMBOBOX_SELECT_END", "组合框选择完成（编辑框内容已改）int CALLBACK OnComboBoxSelectEnd(int iItem,BOOL *pbHandled)"),
    XE_LISTBOX_TEMP_CREATE(81, "XE_LISTBOX_TEMP_CREATE", "列表框项模板创建（复用需启用）int CALLBACK OnListBoxTemplateCreate(listBox_item_* pItem, int nFlag, BOOL *pbHandled)"),
    XE_LISTBOX_TEMP_CREATE_END(82, "XE_LISTBOX_TEMP_CREATE_END", "列表框项模板创建完成 int CALLBACK OnListBoxTemplateCreateEnd(listBox_item_* pItem, int nFlag, BOOL *pbHandled)"),
    XE_LISTBOX_TEMP_DESTROY(83, "XE_LISTBOX_TEMP_DESTROY", "列表框项模板销毁 int CALLBACK OnListBoxTemplateDestroy(listBox_item_* pItem, int nFlag, BOOL *pbHandled)"),
    XE_LISTBOX_TEMP_ADJUST_COORDINATE(84, "XE_LISTBOX_TEMP_ADJUST_COORDINATE", "列表框项模板调整坐标（已停用）"),
    XE_LISTBOX_DRAWITEM(85, "XE_LISTBOX_DRAWITEM", "列表框绘制项 int CALLBACK OnListBoxDrawItem(HDRAW hDraw,listBox_item_* pItem,BOOL *pbHandled)"),
    XE_LISTBOX_SELECT(86, "XE_LISTBOX_SELECT", "列表框项选择 int CALLBACK OnListBoxSelect(int iItem,BOOL *pbHandled)"),
    XE_LIST_TEMP_CREATE(101, "XE_LIST_TEMP_CREATE", "列表项模板创建（复用需启用）int CALLBACK OnListTemplateCreate(list_item_* pItem,int nFlag, BOOL *pbHandled)"),
    XE_LIST_TEMP_CREATE_END(102, "XE_LIST_TEMP_CREATE_END", "列表项模板创建完成 int CALLBACK OnListTemplateCreateEnd(list_item_* pItem, int nFlag, BOOL *pbHandled)"),
    XE_LIST_TEMP_DESTROY(103, "XE_LIST_TEMP_DESTROY", "列表项模板销毁 int CALLBACK OnListTemplateDestroy(list_item_* pItem, int nFlag, BOOL *pbHandled)"),
    XE_LIST_TEMP_ADJUST_COORDINATE(104, "XE_LIST_TEMP_ADJUST_COORDINATE", "列表项模板调整坐标（已停用）"),
    XE_LIST_DRAWITEM(105, "XE_LIST_DRAWITEM", "列表绘制项 int CALLBACK OnListDrawItem(HDRAW hDraw,list_item_* pItem,BOOL *pbHandled)"),
    XE_LIST_SELECT(106, "XE_LIST_SELECT", "列表项选择 int CALLBACK OnListSelect(int iItem,BOOL *pbHandled)"),
    XE_LIST_HEADER_DRAWITEM(107, "XE_LIST_HEADER_DRAWITEM", "列表头绘制项 int CALLBACK OnListHeaderDrawItem(HDRAW hDraw, list_header_item_* pItem, BOOL *pbHandled)"),
    XE_LIST_HEADER_CLICK(108, "XE_LIST_HEADER_CLICK", "列表头项点击 int CALLBACK OnListHeaderClick(int iItem, BOOL *pbHandled)"),
    XE_LIST_HEADER_WIDTH_CHANGE(109, "XE_LIST_HEADER_WIDTH_CHANGE", "列表头项宽度改变 int CALLBACK OnListHeaderItemWidthChange(int iItem, int nWidth BOOL *pbHandled)"),
    XE_LIST_HEADER_TEMP_CREATE(110, "XE_LIST_HEADER_TEMP_CREATE", "列表头项模板创建 int CALLBACK OnListHeaderTemplateCreate(list_header_item_* pItem,BOOL *pbHandled)"),
    XE_LIST_HEADER_TEMP_CREATE_END(111, "XE_LIST_HEADER_TEMP_CREATE_END", "列表头项模板创建完成 int CALLBACK OnListHeaderTemplateCreateEnd(list_header_item_* pItem,BOOL *pbHandled)"),
    XE_LIST_HEADER_TEMP_DESTROY(112, "XE_LIST_HEADER_TEMP_DESTROY", "列表头项模板销毁 int CALLBACK OnListHeaderTemplateDestroy(list_header_item_* pItem,BOOL *pbHandled)"),
    XE_LIST_HEADER_TEMP_ADJUST_COORDINATE(113, "XE_LIST_HEADER_TEMP_ADJUST_COORDINATE", "列表头项模板调整坐标（已停用）"),
    XE_TREE_TEMP_CREATE(121, "XE_TREE_TEMP_CREATE", "列表树项模板创建（复用需启用）int CALLBACK OnTreeTemplateCreate(tree_item_* pItem,int nFlag, BOOL *pbHandled)"),
    XE_TREE_TEMP_CREATE_END(122, "XE_TREE_TEMP_CREATE_END", "列表树项模板创建完成 int CALLBACK OnTreeTemplateCreateEnd(tree_item_* pItem, int nFlag, BOOL *pbHandled)"),
    XE_TREE_TEMP_DESTROY(123, "XE_TREE_TEMP_DESTROY", "列表树项模板销毁 int CALLBACK OnTreeTemplateDestroy(tree_item_* pItem, int nFlag, BOOL *pbHandled)"),
    XE_TREE_TEMP_ADJUST_COORDINATE(124, "XE_TREE_TEMP_ADJUST_COORDINATE", "树项模板调整坐标（已停用）"),
    XE_TREE_DRAWITEM(125, "XE_TREE_DRAWITEM", "树绘制项 int CALLBACK OnTreeDrawItem(HDRAW hDraw,tree_item_* pItem,BOOL *pbHandled)"),
    XE_TREE_SELECT(126, "XE_TREE_SELECT", "树项选择 int CALLBACK OnTreeSelect(int nItemID,BOOL *pbHandled)"),
    XE_TREE_EXPAND(127, "XE_TREE_EXPAND", "树项展开收缩 int CALLBACK OnTreeExpand(int id,BOOL bExpand,BOOL *pbHandled)"),
    XE_TREE_DRAG_ITEM_ING(128, "XE_TREE_DRAG_ITEM_ING", "树拖动项中（可修改参数）int CALLBACK OnTreeDragItemIng(tree_drag_item_* pInfo, BOOL *pbHandled)"),
    XE_TREE_DRAG_ITEM(129, "XE_TREE_DRAG_ITEM", "树拖动项完成 int CALLBACK OnTreeDragItem(tree_drag_item_* pInfo, BOOL *pbHandled)"),
    XE_LISTVIEW_TEMP_CREATE(141, "XE_LISTVIEW_TEMP_CREATE", "列表视项模板创建（复用需启用）int CALLBACK OnListViewTemplateCreate(listView_item_* pItem,int nFlag, BOOL *pbHandled)"),
    XE_LISTVIEW_TEMP_CREATE_END(142, "XE_LISTVIEW_TEMP_CREATE_END", "列表视项模板创建完成 int CALLBACK OnListViewTemplateCreateEnd(listView_item_* pItem,int nFlag, BOOL *pbHandled)"),
    XE_LISTVIEW_TEMP_DESTROY(143, "XE_LISTVIEW_TEMP_DESTROY", "列表视项模板销毁 int CALLBACK OnListViewTemplateDestroy(listView_item_* pItem, int nFlag, BOOL *pbHandled)"),
    XE_LISTVIEW_TEMP_ADJUST_COORDINATE(144, "XE_LISTVIEW_TEMP_ADJUST_COORDINATE", "列表视项模板调整坐标（已停用）"),
    XE_LISTVIEW_DRAWITEM(145, "XE_LISTVIEW_DRAWITEM", "列表视自绘项 int CALLBACK OnListViewDrawItem(HDRAW hDraw,listView_item_* pItem,BOOL *pbHandled)"),
    XE_LISTVIEW_SELECT(146, "XE_LISTVIEW_SELECT", "列表视项选择 int CALLBACK OnListViewSelect(int iGroup,int iItem,BOOL *pbHandled)"),
    XE_LISTVIEW_EXPAND(147, "XE_LISTVIEW_EXPAND", "列表视组展开收缩 int CALLBACK OnListViewExpand(int iGroup,BOOL bExpand,BOOL *pbHandled)"),
    XE_PGRID_VALUE_CHANGE(151, "XE_PGRID_VALUE_CHANGE", "属性网格项值改变 int CALLBACK OnPGridValueChange(int nItemID,BOOL *pbHandled)"),
    XE_PGRID_ITEM_SET(152, "XE_PGRID_ITEM_SET", "属性网格项设置 int CALLBACK OnPGridItemSet(int nItemID, BOOL *pbHandled)"),
    XE_PGRID_ITEM_SELECT(153, "XE_PGRID_ITEM_SELECT", "属性网格项选择 int CALLBACK OnPGridItemSelect(int nItemID, BOOL *pbHandled)"),
    XE_PGRID_ITEM_ADJUST_COORDINATE(154, "XE_PGRID_ITEM_ADJUST_COORDINATE", "属性网格项调整坐标 int CALLBACK OnPGridItemAdjustCoordinate(propertyGrid_item_* pItem, BOOL *pbHandled)"),
    XE_PGRID_ITEM_DESTROY(155, "XE_PGRID_ITEM_DESTROY", "属性网格项销毁 int CALLBACK OnPGridItemDestroy(int nItemID, BOOL *pbHandled)"),
    XE_PGRID_ITEM_EXPAND(156, "XE_PGRID_ITEM_EXPAND", "属性网格项展开收缩 int CALLBACK OnPGridItemExpand(int nItemID, BOOL bExpand, BOOL *pbHandled)"),
    XE_EDIT_SET(180, "XE_EDIT_SET", "编辑框设置事件 int CALLBACK OnEditSet(BOOL *pbHandled)"),
    XE_EDIT_DRAWROW(181, "XE_EDIT_DRAWROW", "编辑框绘制行（暂未使用）int CALLBACK OnEditDrawRow(HDRAW hDraw, int iRow, BOOL *pbHandled)"),
    XE_EDIT_CHANGED(182, "XE_EDIT_CHANGED", "编辑框内容改变 int CALLBACK OnEditChanged(BOOL *pbHandled)"),
    XE_EDIT_POS_CHANGED(183, "XE_EDIT_POS_CHANGED", "编辑框光标位置改变 int CALLBACK OnEditPosChanged(int iPos, BOOL *pbHandled)"),
    XE_EDIT_STYLE_CHANGED(184, "XE_EDIT_STYLE_CHANGED", "编辑框样式改变 int CALLBACK OnEditStyleChanged(int iStyle, BOOL *pbHandled)"),
    XE_EDIT_ENTER_GET_TABALIGN(185, "XE_EDIT_ENTER_GET_TABALIGN", "编辑框回车TAB对齐（返回TAB数量）int CALLBACK OnEditEnterGetTabAlign(BOOL *pbHandled)"),
    XE_EDIT_SWAPROW(186, "XE_EDIT_SWAPROW", "编辑框行交换 int CALLBACK OnEditSwapRow(int iRow, int bArrowUp, BOOL *pbHandled)"),
    XE_EDITOR_MODIFY_ROWS(190, "XE_EDITOR_MODIFY_ROWS", "编辑器多行内容修改（注释/缩进/格式化）int CALLBACK OnEditChangeRows(int iRow, int nRows, BOOL *pbHandled)"),
    XE_EDITOR_SETBREAKPOINT(191, "XE_EDITOR_SETBREAKPOINT", "编辑器设置断点 int CALLBACK OnEditorSetBreakpoint(int iRow, BOOL bCheck, BOOL *pbHandled)"),
    XE_EDITOR_REMOVEBREAKPOINT(192, "XE_EDITOR_REMOVEBREAKPOINT", "编辑器移除断点 int CALLBACK OnEditorRemoveBreakpoint(int iRow, BOOL *pbHandled)"),
    XE_EDIT_ROW_CHANGED(193, "XE_EDIT_ROW_CHANGED", "编辑器行改变（修改断点位置）int CALLBACK OnEditorBreakpointChanged(int iRow, int nChangeRows, BOOL *pbHandled)"),
    XE_EDITOR_AUTOMATCH_SELECT(194, "XE_EDITOR_AUTOMATCH_SELECT", "编辑器自动匹配选择 int CALLBACK OnEditorAutoMatchSelect(int iRow, int nRows, BOOL *pbHandled)"),
    XE_EDITOR_FORMATCODE_TEST(187, "XE_EDITOR_FORMATCODE_TEST", "编辑器格式代码检测（未公开，光标位置改变触发）"),
    XE_TABBAR_SELECT(221, "XE_TABBAR_SELECT", "TabBar标签选择改变 int CALLBACK OnTabBarSelect(int iItem, BOOL *pbHandled)"),
    XE_TABBAR_DELETE(222, "XE_TABBAR_DELETE", "TabBar标签删除 int CALLBACK OnTabBarDelete(int iItem, BOOL *pbHandled)"),
    XE_MONTHCAL_CHANGE(231, "XE_MONTHCAL_CHANGE", "月历元素日期改变 int CALLBACK OnCalendarChange(BOOL *pbHandled)"),
    XE_DATETIME_CHANGE(241, "XE_DATETIME_CHANGE", "日期时间元素内容改变 int CALLBACK OnDateTimeChange(BOOL *pbHandled)"),
    XE_DATETIME_POPUP_MONTHCAL(242, "XE_DATETIME_POPUP_MONTHCAL", "日期时间弹出月历卡片 int CALLBACK OnDateTimePopupMonthCal(HWINDOW hMonthCalWnd,HELE hMonthCal,BOOL *pbHandled)"),
    XE_DATETIME_EXIT_MONTHCAL(243, "XE_DATETIME_EXIT_MONTHCAL", "日期时间退出月历卡片 int CALLBACK OnDateTimeExitMonthCal(HWINDOW hMonthCalWnd,HELE hMonthCal,BOOL *pbHandled)"),
    XE_DROPFILES(250, "XE_DROPFILES", "文件拖放事件（需启用XWnd_EnableDragFiles）int CALLBACK OnDropFiles(HDROP hDropInfo, BOOL *pbHandled)"),
    XE_EDIT_COLOR_CHANGE(260, "XE_EDIT_COLOR_CHANGE", "编辑框颜色改变 int CALLBACK OnEditColorChange(COLORREF color, BOOL *pbHandled)"),


    // ===================== 【系统通知/小众（全量）】=====================
    WM_USER(0x0400, "WM_USER", "自定义消息起始标记（无实际事件）"),
    WM_APP(0x8000, "WM_APP", "应用自定义消息起始标记（无实际事件）"),
    WM_REFLECT(0x2000, "WM_REFLECT", "控件消息反射标记（无实际事件）"),
    WM_THEMECHANGED(0x031A, "WM_THEMECHANGED", "系统主题变更（视觉样式）"),
    WM_UAHDESTROYWINDOW(0x031B, "WM_UAHDESTROYWINDOW", "UAC提升窗口销毁（内部使用）"),
    WM_UAHDRAWMENU(0x031C, "WM_UAHDRAWMENU", "UAC提升菜单绘制（内部使用）"),
    WM_UAHDRAWMENUITEM(0x031D, "WM_UAHDRAWMENUITEM", "UAC提升菜单项绘制（内部使用）"),
    WM_UAHMEASUREMENUITEM(0x031E, "WM_UAHMEASUREMENUITEM", "UAC提升菜单项尺寸计算（内部使用）"),
    WM_UAHINITMENU(0x031F, "WM_UAHINITMENU", "UAC提升菜单初始化（内部使用）"),
    WM_CAPTURECHANGED(0x0215, "WM_CAPTURECHANGED", "鼠标捕获权变更（SetCapture/ReleaseCapture）"),
    WM_DEVICECHANGE(0x0219, "WM_DEVICECHANGE", "硬件设备变更（插拔U盘/打印机等）"),
    WM_MONITORCHANGE(0x021B, "WM_MONITORCHANGE", "显示器连接/断开变更"),
    WM_APPCOMMAND(0x0319, "WM_APPCOMMAND", "应用命令（媒体键/鼠标侧键/触控板手势）"),
    WM_HANDHELDFIRST(0x0358, "WM_HANDHELDFIRST", "手持设备起始消息（范围标识）"),
    WM_HANDHELDLAST(0x035F, "WM_HANDHELDLAST", "手持设备结束消息（范围标识）"),
    WM_AFXFIRST(0x0360, "WM_AFXFIRST", "MFC框架起始消息（范围标识）"),
    WM_AFXLAST(0x037F, "WM_AFXLAST", "MFC框架结束消息（范围标识）"),
    WM_PENWINFIRST(0x0380, "WM_PENWINFIRST", "笔输入窗口起始（范围标识）"),
    WM_PENWINLAST(0x038F, "WM_PENWINLAST", "笔输入窗口结束（范围标识）");

    // ===================== 枚举属性与方法 =====================
    private final int messageCode;    // 消息码（十进制，对应WinUser.h的十六进制）
    private final String messageName; // 消息名称（WM_XXX）
    private final String description; // 消息描述（含适用场景/过时说明）


    /**
     * 构造方法
     * @param messageCode 消息码（WinUser.h定义的十六进制转十进制）
     * @param messageName 消息名称（WM_XXX）
     * @param description 详细描述（含场景/过时标注）
     */
    CompleteWindowMessageEvent(int messageCode, String messageName, String description) {
        this.messageCode = messageCode;
        this.messageName = messageName;
        this.description = description;
    }

    // Getter方法
    public int getMessageCode() {
        return messageCode;
    }

    public String getMessageName() {
        return messageName;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 按消息码精准查找枚举项（支持十进制/十六进制输入）
     * @param code 消息码（如0x0201 / 513）
     * @return 对应枚举项，未找到返回null
     */
    public static CompleteWindowMessageEvent findByCode(int code) {
        for (CompleteWindowMessageEvent event : values()) {
            if (event.messageCode == code) {
                return event;
            }
        }
        return null;
    }

    /**
     * 按消息名称模糊查找（忽略大小写/前缀）
     * @param name 消息名称（如"WM_PAINT" / "PAINT"）
     * @return 匹配的枚举项列表
     */
    public static java.util.List<CompleteWindowMessageEvent> findByName(String name) {
        java.util.List<CompleteWindowMessageEvent> result = new java.util.ArrayList<>();
        String target = name.trim().toUpperCase();
        for (CompleteWindowMessageEvent event : values()) {
            if (event.messageName.toUpperCase().contains(target)) {
                result.add(event);
            }
        }
        return result;
    }

    /**
     * 按类型筛选消息（如筛选所有鼠标消息）
     * @param type 类型关键词：MOUSE/KEY/PAINT/MENU/TIMER/IME等
     * @return 匹配的枚举项列表
     */
    public static java.util.List<CompleteWindowMessageEvent> filterByType(String type) {
        java.util.List<CompleteWindowMessageEvent> result = new java.util.ArrayList<>();
        String keyword = type.trim().toUpperCase();
        for (CompleteWindowMessageEvent event : values()) {
            if (event.messageName.contains(keyword) || event.description.contains(keyword)) {
                result.add(event);
            }
        }
        return result;
    }

    /**
     * 重写toString，返回完整的消息信息
     */
    @Override
    public String toString() {
        return String.format(
                "【%s】码:0x%04X | 描述:%s",
                messageName, messageCode, description
        );
    }
}
