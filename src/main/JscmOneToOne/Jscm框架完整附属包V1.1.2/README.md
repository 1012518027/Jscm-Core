# 前言

> 开发语言：火山开发语言、JAVA
> 参考功能：C++、C、易语言、火山内置功能
>
> 实现原理：基于 JNI 接口  实现对C++的功能封装和实现
>
> 该类库所有功能接口免费对外开放学习使用，需要有一定的C++基础知识或其他win32API接口应用基础,
>
> 注意：<span style="color:#ff0000">所有的功能均有提供VIP类库，需要的可以自行购买或者找我购买。所有的API都是公开免费的，可以自行调用JNI接口。</span>
>
> 本库所用到JNI接口的都支持 X86 X64 也有一部分采用Java内置方法实现可以实现多平台使用，本类库<span style="color:#ff0000">强烈推荐在windows 平台上使用</span>。

# 版权申明

> 该项目由 鲤城区蜗牛猫软件开发工作室 研发，使用请注明。

# 官网以及下载地址

> https://www.snailcatmall.com
>
> 维护QQ群：128828632

# 视频教程地址

https://space.bilibili.com/29826047/channel/series

# 库文件文件夹说明

<table border="1">
  <tr>
    <th>文件夹路径</th>
    <th>文件夹说明</th>
  </tr>
  <tr>
    <td>Jscm框架完整附属包V1.1.2</td>
    <td>建议更改成英文目录名</td>
  </tr>
  <tr>
    <td>Jscm框架完整附属包V1.1.2/jscm-core-32</td>
    <td>32位JDK打包的核心库</td>
  </tr>
  <tr>
    <td>Jscm框架完整附属包V1.1.2/jscm-core-64</td>
    <td>64位JDK打包的核心库</td>
  </tr>
    <tr>
    <td>Jscm框架完整附属包V1.1.2/doc</td>
    <td>中文帮助文档</td>
  </tr>
  <tr>
    <td>Jscm框架完整附属包V1.1.2/samples</td>
    <td>模块基础案例</td>
  </tr>
  <tr>
    <td>Jscm框架完整附属包V1.1.2/x86</td>
    <td>存放所有32位的第三方库文件包	//支持按需导入</td>
  </tr>
  <tr>
    <td>Jscm框架完整附属包V1.1.2/x64</td>
    <td>存放所有64位的第三方库文件包	//支持按需导入</td>
  </tr>
</table>

# 第三方库描述

<table border="1">
    <tr>
    <th>工具类说明</th>
    <th>DLL依赖的类库</th>
    </tr>
    <tr>
    <td>全局核心库</th>
    <td>jscm</th>
    </tr>
    <tr>
    <td>幽灵键鼠驱动</th>
    <td>ghost_系统号</th>
    </tr>
    <tr>
    <td>DD键鼠驱动</th>
    <td>Jscm_snailcatmall_系统号</th>
    </tr>
    <tr>
    <td>MD键鼠驱动</th>
    <td>md_系统号</th>
    </tr>
    <tr>
    <td>炫彩界面库</th>
    <td>内置Lib版</th>
    </tr>
</table>


# 使用步骤流程

> <h3>下载核心包 jscm-core.zip</h3>
>
> <span style="color:#ff0000">注：本库没有加入maven仓库需要自行导入到jar</span>
>
> <h3>下载所需框架附属包.zip</h3>
>
> <span style="color:#ff0000">注：默认核心库都会附带，后面可根据需求移动</span>
>
> <h3>基础构建代码[调试环境配置]</h3>
>
> ```java
> package com;
> 
> import com.scm.all.export.PathFileJSCM;
> 
> class App {
> 
>     public static void main(String args[])throws Exception {
>         //必须绝对路径
>         PathFileJSCM.setDebugFile32("C:\\Users\\www10\\IdeaProjects\\scmJnaApi\\Application\\JscmOneToOne\\Jscm框架完整附属包V1.1.2\\x86\\");
>         PathFileJSCM.setDebugFile64("C:\\Users\\www10\\IdeaProjects\\scmJnaApi\\Application\\JscmOneToOne\\Jscm框架完整附属包V1.1.2\\x64\\");
>         PathFileJSCM.setIsDebug(true);
>     }
> }
> ```
>
> <h3>基础构建代码[开发环境配置]</h3>
>
> ```java
> package com;
> 
> import com.scm.all.export.PathFileJSCM;
> 
> class App {
> 
>     public static void main(String args[])throws Exception {
>         //可以采用相对路径  主要以jar的文件路径为准  文件夹下必须有x86 x64 文件夹，它会自动取找X86和X64
>         PathFileJSCM.setDebugFile32("..\\demo\\");
>         PathFileJSCM.setDebugFile64("..\\demo\\");
>         PathFileJSCM.setIsDebug(false);
>     }
> }
> ```

# 函数工具类说明表

<table border="1">
    <tr>
    <th>工具类名</th>
    <th>具体说明</th>
    </tr>
    <tr>
    <td>MemoryOperationUtilsX86/MemoryOperationUtilsX64</td>
    <td>内存操作类</td>
    </tr>
    <tr>
    <td>ApiHookOperationUtilsX86/ApiHookOperationUtilsX64</td>
    <td>APIHook操作类<b style="color:#ff0000">【虽然能用，但是目前不合适】</b></td>
    </tr>
    <tr>
    <td>RegistryOperationUtilsX86/RegistryOperationUtilsX64</td>
    <td>注册表操作类</td>
    </tr>
    <tr>
    <td>InstallAdobeJsx</td>
    <td>Adobe PhotoShop 通信接口</td>
    </tr>
    <tr>
    <td>CapStone</td>
    <td>CapStone 反汇编引擎<b style="color:#ff0000">【虽然能用，但是目前不支持循环】</b></td>
    </tr>
    <tr>
    <td>EMailUtils</td>
    <td>EMailUtils 收发信工具类</b></td>
    </tr>
    <tr>
    <td>DdDriver</td>
    <td>DD键鼠工具类<b style="color:#ff0000">【仅驱动，无需硬件】</b></td>
    </tr>
    <tr>
    <td>GhostDriver</td>
    <td>幽灵键鼠工具类<b style="color:#ff0000">【需要硬件驱动设备】</b></td>
    </tr>
    <tr>
    <td>MdDriver</td>
    <td>飞易来键鼠工具类<b style="color:#ff0000">【需要硬件驱动设备】</b></td>
    </tr>
    <tr>
    <td>PowerShell</td>
    <td>PowerShell cmd下载工具类</td>
    </tr>
    <tr>
    <td>CaptchaGenerator</td>
    <td>图形验证码生成工具类</td>
    </tr>
    <tr>
    <td>ByteUtils</td>
    <td>字节编码操作工具类</td>
    </tr>
    <tr>
    <td>IniConfigurationOperationUtils</td>
    <td>INI配置工具类</td>
    </tr>
    <tr>
    <td>ModuleOperationUtilsJNI</td>
    <td>JNI接口公开类<b style="color:#ff0000">【炫彩界面库的接口也在这里】</b></td>
    </tr>
    <tr>
    <td>PathFileJSCM</td>
    <td>初始化接口</td>
    </tr>
    <tr>
    <td>ProcessAndThreadUtils</td>
    <td>进程操作管理工具类</td>
    </tr>
    <tr>
    <td>SystemUtils</td>
    <td>系统操作工具类</td>
    </tr>
    <tr>
    <td>WindowOperationUtils</td>
    <td>窗口操作工具类</td>
    </tr></table>


# 炫彩界面库的基本用法案例

<h3>XML界面调用运行方案 [需要搭配炫语言的IDE界面设计]</h3>

界面库参考文档地址：http://8.138.59.98/xcgui/index.html  这个更详细

```java
        
//加载布局文件目录
ModuleOperationUtilsJNI.XC_AddFileSearchPath("C:\\Users\\www10\\IdeaProjects\\scmJnaApi\\src\\main\\JscmOneToOne\\Jscm框架完整附属包V1.1.2\\samples\\1\\layout");

//加载资源文件
ModuleOperationUtilsJNI.XC_LoadResource("resource.res");     
//加载布局文件，返回句柄
int hWindow = ModuleOperationUtilsJNI.XC_LoadLayout("main.xml",0,0); //加载布局文件

```
<h3>注册元素事件使用方法 <b style="color:#ff0000">只允许一个接口，不能内嵌式</b></h3>

```java
            XEleEventCallBack xEleEventCallBack = new XEleEventCallBack(){
                
    public boolean OnAdjustLayoutEnd(int hEle, long nFlags,int nAdjustNo){return false;}
    public boolean OnBtnClick(int hEle){return false;}
    public boolean OnButtonCheck(int hEle,boolean bCheck){return false;}
    public boolean OnChar(int hEle, int wParam,int lParam){return false;}
    public boolean OnComboBoxExitList(int hEle){return false;};
    public boolean OnComboBoxSelect(int hEle, int iItem){return false;}
    public boolean OnComboBoxSelectEnd(int hEle, int iItem){return false;}
    public boolean OnDateTimeChange(int hEle){return false;}
    public boolean OnDateTimeExitMonthCal(int hEle,int hMonthCalWnd,int hMonthCal){return false;}
    public boolean OnDateTimePopupMonthCal(int hEle,int hMonthCalWnd,int hMonthCal){return false;}
    public boolean OnDestroy(int hEle){return false;}
    public boolean OnDestroyeEnd(int hEle){return false;}
    public boolean OnDropFiles(int hEle, String filesInfo){return false;}
    public boolean OnEditChanged(int hEle){return false;}
    public boolean OnEditColorChange(int hEle,long color){return false;}
    public boolean OnEditDrawRow(int hEle,long hDraw, int iRow){return false;}
    public boolean OnEditEnterGetTabAlign(int hEle){return false;}
    public boolean OnEditPosChanged(int hEle,int iPos){return false;}
    public boolean OnEditorBreakpointChanged(int hEle,int iRow, int nChangeRows){return false;}
    public boolean OnEditSet(int hEle){return false;}
    public boolean OnEditStyleChanged(int hEle,int iStyle){return false;}
    public boolean OnEditSwapRow(int hEle,int iRow,int bArrowUp){return false;}
    public boolean OnEditorAutoMatchSelect(int hEle,int iRow, int nRows){return false;}
    public boolean OnEditorFormatCodeTest(int hEle,int iRow, int iCol){return false;}
    public boolean OnEditChangeRows(int hEle,int iRow, int nRows){return false;}
    public boolean OnEditorRemoveBreakpoint(int hEle,int iRow){return false;}
    public boolean OnEditorSetBreakpoint(int hEle,int iRow, boolean bCheck){return false;}
    public boolean OnEventProc(int hEle,long nEvent, int wParam, int lParam){return false;}
    public boolean OnKeyDown(int hEle, int wParam, int lParam){return false;}
    public boolean OnKeyUp(int hEle, int wParam, int lParam){return false;}
    public boolean OnKillCapture(int hEle){return false;}
    public boolean OnKillFocus(int hEle){return false;}
    public boolean OnLButtonDBClick(int hEle, long flags, int x, int y){return false;}
    public boolean OnLButtonDown(int hEle, long flags, int x, int y){return false;}
    public boolean OnLButtonUp(int hEle, long flags, int x, int y){return false;}
    public boolean OnListDrawItem(int hEle,long hDraw ,int index,int iSubItem,int nState,int hLayout,int hTemp){return false;}
    public boolean OnListHeaderTemplateCreate(int hEle,int index,boolean bSort,int nSortType,int iColumnAdapter,int nState,int hLayout,int hTemp){return false;}
    public boolean OnListHeaderClick(int hEle,int iItem){return false;}
    public boolean OnListHeaderDrawItem(int hEle,long hDraw, int index,boolean bSort,int nSortType,int iColumnAdapter,int nState,int hLayout,int hTemp){return false;}
    public boolean OnListHeaderTemplateCreateEnd(int hEle,int index,boolean bSort,int nSortType,int iColumnAdapter,int nState,int hLayout,int hTemp){return false;}
    public boolean OnListHeaderTemplateDestroy(int hEle,int index,boolean bSort,int nSortType,int iColumnAdapter,int nState,int hLayout,int hTemp){return false;}
    public boolean OnListHeaderItemWidthChange(int hEle,int iItem,int nWidth){return false;}
    public boolean OnListSelect(int hEle,int iItem){return false;}
    public boolean OnListTemplateCreate(int hEle,int index,int iSubItem,int nState,int hLayout,int hTemp,int nFlag){return false;}
    public boolean OnListTemplateCreateEnd(int hEle,int index,int iSubItem,int nState,int hLayout,int hTemp,int nFlag){return false;}
    public boolean OnListTemplateDestroy(int hEle,int index,int iSubItem,int nState,int hLayout,int hTemp,int nFlag){return false;}
    public boolean OnListBoxDrawItem(int hEle,long hDraw,int index,int nHeight,int nSelHeight,int nState,int hLayout,int hTemp){return false;}
    public boolean OnListBoxSelect(int hEle,int iItem){return false;}
    public boolean OnListBoxTemplateCreate(int hEle,int index,int nHeight,int nSelHeight,int nState,int hLayout,int hTemp,int nFlag){return false;}
    public boolean OnListBoxTemplateCreateEnd(int hEle,int index,int nHeight,int nSelHeight,int nState,int hLayout,int hTemp,int nFlag){return false;}
    public boolean OnListBoxTemplateDestroy(int hEle,int index,int nHeight,int nSelHeight,int nState,int hLayout,int hTemp,int nFlag){return false;}
    public boolean OnListViewDrawItem(int hEle,long hDraw,int iGroup,int item,int nState,int hLayout,int hTemp){return false;}
    public boolean OnListViewExpand(int hEle,int iGroup,boolean bExpand){return false;}
    public boolean OnListViewSelect(int hEle,int iGroup,int iItem){return false;}
    public boolean OnListViewTemplateCreate(int hEle,int nFlag,int iGroup,int item,int nState,int hLayout,int hTemp){return false;}
    public boolean OnListViewTemplateDestroy(int hEle,int nFlag,int iGroup,int item,int nState,int hLayout,int hTemp){return false;}
    public boolean OnMenuDrawBackground(int hEle,long hDraw,int hMenu,int hWindow,int nParentID){return false;}
    public boolean OnMenuDrawItem(int hEle,long hDraw,int hMenu,int hWindow ,int nID,int nState,int hIcon,String pText){return false;}
    public boolean OnMenuExit(int hEle){return false;}
    public boolean OnMenuPopup(int hEle,int hMenu){return false;}
    public boolean OnMenuPopupWnd(int hEle,int hMenu,int hWindow,int nParentID){return false;}
    public boolean OnMenuSelect(int hEle,int nItem){return false;}
    public boolean OnCalendarChange(int hEle){return false;}
    public boolean OnMouseHover(int hEle,long nFlags, int x,int y){return false;}
    public boolean OnMouseLeave(int hEle,int hEleStay){return false;}
    public boolean OnMouseMove(int hEle,long nFlags, int x,int y){return false;}
    public boolean OnMouseStay(int hEle){return false;}
    public boolean OnMouseWheel(int hEle,long nFlags, int x,int y){return false;}
    public boolean OnDraw(int hEle,long hDraw){return false;}
    public boolean OnPaintEnd(int hEle,long hDraw){return false;}
    public boolean OnDrawScrollView(int hEle,long hDraw){return false;}
    public boolean OnPGridItemAdjustCoordinate(int hEle,int nType,int nID,int nDepth,int nNameColWidth,int bExpand,int bShow){return false;}
    public boolean OnPGridItemDestroy(int hEle,int nItemID){return false;}
    public boolean OnPGridItemExpand(int hEle,int nItemID, boolean bExpand){return false;}
    public boolean OnPGridItemSelect(int hEle,int nItemID){return false;}
    public boolean OnPGridItemSet(int hEle,int nItemID){return false;}
    public boolean OnPGridValueChange(int hEle,int nItemID){return false;}
    public boolean OnProgressBarChange(int hEle,int pos){return false;}
    public boolean OnRButtonDown(int hEle,long nFlags, int x,int y){return false;}
    public boolean OnRButtonUp(int hEle,long nFlags, int x,int y){return false;}
    public boolean OnSBarScroll(int hEle,int pos){return false;}
    public boolean OnScrollViewScrollH(int hEle,int pos){return false;}
    public boolean OnScrollViewScrollV(int hEle,int pos){return false;}
    public boolean OnSetCapture(int hEle){return false;}
    public boolean OnSetCursor(int hEle,int wParam,int lParam){return false;}
    public boolean OnSetFocus(int hEle){return false;}
    public boolean OnSetFont(int hEle){return false;}
    public boolean OnShow(int hEle,boolean bShow){return false;}
    public boolean OnSize(int hEle,long nFlags, int nAdjustNo){return false;}
    public boolean OnSliderBarChange(int hEle,int pos){return false;}
    public boolean OnTabBarDelete(int hEle,int iItem){return false;}
    public boolean OnTabBarSelect(int hEle,int iItem){return false;}
    public boolean OnTooltipPopup(int hEle,int hWindowTooltip, String pText){return false;}
    public boolean OnTreeDragItem(int hEle,int nDragItem,int nDestItem,int nType){return false;}
    public boolean OnTreeDragItemIng(int hEle,int nDragItem,int nDestItem,int nType){return false;}
    public boolean OnTreeDrawItem(int hEle,long hDraw,int nID,int nDepth,int nHeight,int nSelHeight,boolean bExpand,int nState,int hLayout,int hTemp){return false;}
    public boolean OnTreeExpand(int hEle,int id,int bExpand){return false;}
    public boolean OnTreeSelect(int hEle,int nItemID){return false;}
    public boolean OnTreeTemplateCreate(int hEle,int nFlag,int nID,int nDepth,int nHeight,int nSelHeight,boolean bExpand,int nState,int hLayout,int hTemp){return false;}
    public boolean OnTreeTemplateCreateEnd(int hEle,int nFlag,int nID,int nDepth,int nHeight,int nSelHeight,boolean bExpand,int nState,int hLayout,int hTemp){return false;}
    public boolean OnTreeTemplateDestroy(int hEle,int nFlag,int nID,int nDepth,int nHeight,int nSelHeight,boolean bExpand,int nState,int hLayout,int hTemp){return false;}
    public boolean OnEleXCTimer(int hEle,long nTimerID){return false;}
    public boolean OnAutoEvent(int hEle,long len,long typeCode,byte[] data){return false;}
                
            };
```



<h3>注册窗口事件 <b style="color:#ff0000">只允许一个接口，不能内嵌式</b></h3>

```java
        WindowEventCallBack windowEventCallBack = new WindowEventCallBack(){
           
    public boolean OnWndMouseMove(int hWindow, long flags, int x, int y){return false;}
    public boolean OnWndDrawWindow(int hWindow, long hDraw){return false;}
    public boolean OnWndClose(int hWindow){return false;}
    public boolean OnWndDestroy(int hWindow){return false;}
    public boolean OnWndNCDestroy(int hWindow){return false;}
    public boolean OnWndLButtonDown(int hWindow, long flags, int x, int y){return false;}
    public boolean OnWndLButtonUp(int hWindow, long flags, int x, int y){return false;}
    public boolean OnWndRButtonDown(int hWindow, long flags, int x, int y){return false;}
    public boolean OnWndRButtonUp(int hWindow, long flags, int x, int y){return false;}
    public boolean OnWndLButtonDBClick(int hWindow, long flags, int x, int y){return false;}
    public boolean OnWndRButtonDBClick(int hWindow, long flags, int x, int y){return false;}
    public boolean OnWndMouseHover(int hWindow, long flags, int x, int y){return false;}
    public boolean OnWndMouseLeave(int hWindow){return false;}
    public boolean OnWndMouseWheel(int hWindow, long flags, int x, int y){return false;}
    public boolean OnWndCaptureChanged(int hWindow, int hWnd){return false;}
    public boolean OnWndKeyDown(int hWindow, int wParam,int lParam){return false;}
    public boolean OnWndKeyUp(int hWindow, int wParam,int lParam){return false;}
    public boolean OnWndChar(int hWindow, int wParam,int lParam){return false;}
    public boolean OnWndSize(int hWindow, long flags, int cx, int cy){return false;}
    public boolean OnWndExitSizeMove(int hWindow){return false;}
    public boolean OnWndTimer(int hWindow, long nIDEvent){return false;}
    public boolean OnWndSetFocus(int hWindow){return false;}
    public boolean OnWndKillFocus(int hWindow){return false;}
    public boolean OnWndSetCursor(int hWindow, int wParam,int lParam){return false;}
    public boolean OnDropFiles(int hWindow, String filesInfo){return false;}
    public boolean OnWndTrayIcon(int hWindow, int wParam, int lParam){return false;}
    public boolean OnWndOther(int hWindow, int wParam,int lParam){return false;}
    public boolean OnWndProc(int hWindow,long message, int wParam,int lParam){return false;}
    public boolean OnWndXCTimer(int hWindow,long nTimerID){return false;}
    public boolean OnWndMenuPopup(int hWindow,int hMenu){return false;}
    public boolean OnWndSetFocusEle(int hWindow, int hEle){return false;}
    public boolean OnWndMenuPopupWnd(int hWindow, int hMenu,int hWindow2,int nParentID){return false;}
    public boolean OnWndMenuSelect(int hWindow, int nID){return false;}
    public boolean OnWndMenuDrawBackground(int hWindow, long hDraw, int hMenu,int hWindow2,int nParentID){return false;}
    public boolean OnWndMenuSelectExit(int hWindow){return false;}
    public boolean OnMenuDrawItemWindows(int hWindow1,long hDraw,int hMenu,int hWindow2 ,int nID,int nState,int hIcon,String pText){return false;}
    public boolean OnWndFloatPane(int hWindow, int hFloatWnd, int hPane){return false;}
    public boolean OnWndDrawWindowEnd(int hWindow, long hDraw){return false;}
    public boolean OnWndDrawWindowDisplay(int hWindow){return false;}
    public boolean OnWndDocPopup(int hFrameWindow, int hWindowDock,  int hPane){return false;}
    public boolean OnWndFloatWndDrag(int hFrameWindow, int hFloatWnd, int[] hArray){return false;}
    public boolean OnWndPaneShow(int hFrameWindow, int hPane, int bShow){return false;}
    public boolean OnWndLayoutViewRect(int hFrameWindow, int width, int height){return false;}

        };
```





