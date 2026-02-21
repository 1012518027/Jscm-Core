package com.scm.all.export;

import com.scm.all.pfunc.JscmCallBack;
import com.scm.all.pfunc.SwingJFrameInfo;
import com.scm.all.pfunc.WindowEventCallBack;
import com.scm.all.pfunc.WindowSetGraduallyHideShowCallBack;
import com.scm.all.struct.TagPoint;
import com.scm.all.struct.TagRect;
import com.scm.all.struct.TagRectSize;
import com.scm.all.struct.TagWinInfo;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * 窗体操作工具类
 *  本类所有窗口操作都在此类中 可以根据需求进行处理 集成了三大模块进行处理
 *   此类包含窗口操作 、窗口句柄操作  包含庞大类库   创建于：2022年1月6日21:09:59
 *   参考精易模块、超级模块、乐易模块
 */
public class WindowOperationUtils {
    public static int EnumWindowsCallbackValue;
    public static List<TagWinInfo> temp = new ArrayList<>();

    /**
     * 窗口重画
     * 该InvalidateRect函数添加一个矩形到指定窗口的更新区域。更新区域表示必须重绘的窗口客户区部分。
     * @param hWnd 窗口句柄
     * @param empty 是否更新
     * @return 返回结果
     */
    public static boolean windowInvalidateRect(int hWnd, boolean empty){
        return ModuleOperationUtilsJNI.InvalidateRect(hWnd,null,empty);
    }

    /**
     * 窗口隐藏任务按钮
     *
     * 更改指定窗口的属性。该函数还将指定偏移量处的 32 位（长整型）值设置到额外的窗口内存中。
     * @param hWnd 窗口句柄
     * @return 返回结果
     */
    public static long windowSetWindowLongW(int hWnd){
        return ModuleOperationUtilsJNI.SetWindowLongW(hWnd,-8,ModuleOperationUtilsJNI.GetDesktopWindow());
    }



    /**
     * 窗口取屏幕窗口句柄
     * @return 返回屏幕句柄
     */
    public static long windowGetDesktopWindow(){
        return ModuleOperationUtilsJNI.GetDesktopWindow();
    }

    /**
     * 窗口是否响应
     * @param hWnd 窗口句柄
     * @return 返回结果
     */
    public static boolean windowSendMessageTimeoutW(int hWnd){
        byte[] pointerlpdwResult = new byte[4];
        int result = ModuleOperationUtilsJNI.SendMessageTimeoutW(hWnd,0,0,0,2,128,pointerlpdwResult);
        return result!=0;
    }

    /**
     * 获取窗口光标坐标
     * @param pPoint 返回坐标位置
     * @return 返回结果
     */
    public static boolean windowGetCaretPos(TagPoint pPoint){
        return ModuleOperationUtilsJNI.GetCursorPos(pPoint);
    }

    /**
     * 枚举循环关闭窗口
     * @param lpClassName 窗口类目
     * @param lpWindowName 窗口标题
     */
    public static void enumWindowClose(String lpClassName,String lpWindowName){
        while (ModuleOperationUtilsJNI.FindWindowW(lpClassName,lpWindowName)!=0){
            ModuleOperationUtilsJNI.PostMessageW(ModuleOperationUtilsJNI.FindWindowW(lpClassName,lpWindowName),16,null,null);
        }
    }

    /**
     * 获取指定窗口句柄中客户端句柄内鼠标坐标位置
     * @param hWnd 窗口句柄
     * @return TagPoint 返回该类
     */
    public static TagPoint getClientPoint(int hWnd){
        TagRect lpRect = new TagRect();
        ModuleOperationUtilsJNI.GetWindowRect(hWnd,lpRect);
        TagRect rect = new TagRect();
        ModuleOperationUtilsJNI.GetClientRect(hWnd,rect);
        TagPoint tagPoint = new TagPoint();
        ModuleOperationUtilsJNI.GetCursorPos(tagPoint);
        int x = (int) (tagPoint.x - lpRect.left - (lpRect.right - rect.right));
        int y = (int) (tagPoint.y - lpRect.top - (lpRect.bottom - rect.bottom));
        TagPoint tempPoint = new TagPoint();
        tempPoint.x = x;
        tempPoint.y = y;
        return tempPoint;
    }

    /**
     * 嵌入桌面
     * @param hWnd 窗口句柄
     * @return 返回新窗口句柄
     */
    public static boolean windowEmbeddedDesktop(int hWnd){
        int desktophWnd =  GetWorkerW();
        return windowSetParent(hWnd,desktophWnd);
    }

    /**
     * 嵌入指定句柄
     * @param hWnd 窗口句柄
     * @param childHwnd 被嵌入的句柄
     * @return 返回新窗口句柄
     */
    public static boolean WindowEmbeddedWindow(int hWnd,int childHwnd){
        return windowSetParent(hWnd,childHwnd);
    }

    /**
     * @return 返回桌面句柄
     */
    public static int GetWorkerW(){
        byte[] pointerlpdwResult = new byte[4];
        int windowHandle =ModuleOperationUtilsJNI.FindWindowW("Progman","Program Manager");
        ModuleOperationUtilsJNI.SendMessageTimeoutW(windowHandle,1324,0,0,0,1000,pointerlpdwResult);
        ModuleOperationUtilsJNI.EnumWindows(new JscmCallBack() {
            @Override
            public boolean callback(int hWnd, int data) {
                int defview = ModuleOperationUtilsJNI.FindWindowExW(hWnd,0,"SHELLDLL_DefView","");
                if(defview != 0 ){
                    EnumWindowsCallbackValue =  ModuleOperationUtilsJNI.FindWindowExW(0,hWnd,"WorkerW","");
                }
                return true;
            }
        },0);
        ModuleOperationUtilsJNI.ShowWindow(EnumWindowsCallbackValue,0);
        return windowHandle;
    }
    /**
     * 窗口置父
     * @param parentHWND 父窗口句柄
     * @param javaHWND 子窗口句柄
     * @return 返回句柄
     */
    public static boolean windowSetParent(int parentHWND,int javaHWND){
//        int hwnd = 0 ;
//        if (IsWindow(parentHWND) && IsWindow(javaHWND)) {
//            // 关键API：将Java窗口设置为C++窗口的子窗口
//            hwnd = ModuleOperationUtilsJNI.SetParent(javaHWND, parentHWND);
//
//            // 可选：设置Java窗口的样式（去除边框、置顶等）
//            ModuleOperationUtilsJNI.SetWindowLongW(javaHWND, GWL_STYLE.getValue(), WS_CHILD | WS_VISIBLE);
//            ModuleOperationUtilsJNI.SetWindowPos(javaHWND, -1, 0, 0, 0, 0, SWP_NOSIZE.getValue() | SWP_NOZORDER.getValue());
//            return hwnd;
//        }
        return ModuleOperationUtilsJNI.SetParentWithIndependentRender(javaHWND, parentHWND);
    }

    // ---------------------- 核心换算工具（2个函数搞定所有）----------------------
    /**
     * 父窗口客户区坐标 → 屏幕坐标（用于获取子窗口当前屏幕位置）
     * @param hParentWnd 父窗口句柄
     * @param clientX 客户区X坐标
     * @param clientY 客户区Y坐标
     * @return TagPoint
     */

    public static TagPoint ClientToScreenPos(int hParentWnd, long clientX, long clientY)
    {
        TagPoint pt = new TagPoint(clientX, clientY);
        ModuleOperationUtilsJNI.ClientToScreen(hParentWnd, pt);
        return pt;
    }

    /**
     * 屏幕坐标 → 父窗口客户区坐标（用于设置子窗口在父内/外的位置）
     * @param hParentWnd 父窗口句柄
     * @param screenX 屏幕X坐标
     * @param screenY 屏幕Y坐标
     * @return TagPoint
     */
    public static TagPoint ScreenToClientPos(int hParentWnd, long screenX, long screenY)
    {
        TagPoint pt = new TagPoint(screenX, screenY);
        ModuleOperationUtilsJNI.ScreenToClient(hParentWnd, pt);
        return pt;
    }


    // ---------------------- 精准设置子窗口位置（父内外通用）----------------------
    /**
     * 设置子窗口在父窗口内/外
     * @param hParentWnd 父窗口句柄
     * @param hChildWnd 子窗口句柄
     * @param screenX 屏幕X坐标
     * @param screenY 屏幕Y坐标
     * @param width 宽
     * @param height  高
     */

    public static void SetChildScreenPos(int hParentWnd, int hChildWnd, int screenX, int screenY, int width, int height)
    {
        SetChildToBottom(hParentWnd);
        if (!ModuleOperationUtilsJNI.IsWindow(hChildWnd)) return;

        boolean isEmbedded = ModuleOperationUtilsJNI.IsChild(hParentWnd, hChildWnd); // 判断是否嵌入
        if (isEmbedded)
        {
            // 嵌入模式：屏幕坐标 → 父客户区坐标
            TagPoint clientPos = ScreenToClientPos(hParentWnd, screenX, screenY);
            ModuleOperationUtilsJNI.MoveWindow(hChildWnd, (int)clientPos.x, (int)clientPos.y, width, height, true);
        }
        else
        {
            // 独立模式：直接用屏幕坐标
            ModuleOperationUtilsJNI.MoveWindow(hChildWnd, screenX, screenY, width, height, true);
        }
    }

    /**
     * @param hParent 父窗口句柄
     * @param hChild 子窗口句柄
     * @param right_Bottom_XOffset 右下X偏移
     * @param right_Bottom_YOffset 右下Y偏移
     */
    public static void UpdateChildWindowPos(int hParent,int hChild,int right_Bottom_XOffset,int right_Bottom_YOffset){
        ModuleOperationUtilsJNI.UpdateChildWindowPos(hParent,hChild,right_Bottom_XOffset,right_Bottom_YOffset);
    }

    /**
     * 返回swing窗口句柄
     * @param obj swing窗口组件
     * @return 窗口句柄
     */
    public static int GetSwingWindowHandle(Component obj){
        return ModuleOperationUtilsJNI.GetWindowHandle(obj);
    }


    /**
     * 将子窗口放到所有同级子控件的最底层
     * @param hChildWnd 子窗口句柄
     */
    public static void SetChildToBottom(int hChildWnd)
    {
        if (!ModuleOperationUtilsJNI.IsWindow(hChildWnd)){
            return;
        }

        // 核心：将子窗口放到所有同级子控件的最底层
        ModuleOperationUtilsJNI.SetWindowPos(
                hChildWnd,
                1,  // Z-Order：最底层（关键参数）
                0, 0, 0, 0,   // 位置/大小不变（用 0 即可）
                0x0002 | 0x0001 | 0x0010 // 仅调整层级，不改变其他属性
        );
    }

    /**
     * 获取子窗口当前屏幕
     * @param hParentWnd 父窗口句柄
     * @param hChildWnd 子窗口句柄
     * @return TagPoint
     */
    // ---------------------- 精准获取子窗口坐标（父内外通用）----------------------
// 功能：获取子窗口当前的屏幕坐标（无论是否嵌入，统一返回屏幕坐标）
    public static TagPoint GetChildScreenPos(int hParentWnd, int hChildWnd)
    {
        int screenX = 0;
        int screenY = 0;
        if (!ModuleOperationUtilsJNI.IsWindow(hChildWnd)){
            return null;
        }

        boolean isEmbedded = ModuleOperationUtilsJNI.IsChild(hParentWnd, hChildWnd);
        if (isEmbedded)
        {
            // 嵌入模式：父客户区坐标 → 屏幕坐标
            TagRect clientRect = new TagRect();
            ModuleOperationUtilsJNI.GetClientRect(hChildWnd, clientRect);
            TagPoint screenPos = ClientToScreenPos(hParentWnd, clientRect.left, clientRect.top);
            return screenPos;
        }
        else
        {
            // 独立模式：直接获取屏幕坐标
            TagRect screenRect = new TagRect();
            ModuleOperationUtilsJNI.GetWindowRect(hChildWnd, screenRect);
            screenX = screenRect.left;
            screenY = screenRect.top;
            return new TagPoint(screenX, screenY);
        }

    }

    /**
     * 屏幕坐标到客户区
     * @param hParentWnd 父窗口句柄
     * @param hChildWnd 子窗口句柄
     * @param targetScreenX 目标屏幕X坐标
     * @param targetScreenY 目标屏幕Y坐标
     * @param width 宽度
     * @param height 高度
     */
    public static void SetChildPosAlignScreen(int hParentWnd, int hChildWnd, int targetScreenX, int targetScreenY, int width, int height)
    {
        // 1. 简单校验句柄有效性
        if (!ModuleOperationUtilsJNI.IsWindow(hParentWnd) || !ModuleOperationUtilsJNI.IsWindow(hChildWnd))
            return;

        // 2. 关键换算：屏幕坐标 → 父窗口客户区坐标（一行搞定！）
        TagPoint targetPoint = new TagPoint( targetScreenX, targetScreenY);
        ModuleOperationUtilsJNI.ScreenToClient(hParentWnd, targetPoint); // 自动处理标题栏/边框偏移

        // 3. 直接设置子窗口位置（换算后的坐标可直接用）
        ModuleOperationUtilsJNI.MoveWindow(
                hChildWnd,
                (int)targetPoint.x,  // 换算后的父窗口内 X 坐标
                (int)targetPoint.y,  // 换算后的父窗口内 Y 坐标
                width,
                height,
                true  // 立即重绘
        );

    }


    /**
     * 窗口_置位置与大小
     * @param hWnd 窗口句柄
     * @param left 左边位置  可以为 0
     * @param top 顶部位置  可以为 0
     * @param newWidth 新宽度  可以为 0
     * @param newHeight 新高度 可以为 0
     * @return 返回逻辑值
     */
    public static int windowSetLocationSize(int hWnd,int left,int top,int newWidth,int newHeight){
        TagRect rect = new TagRect();
        ModuleOperationUtilsJNI.GetWindowRect(hWnd,rect);
        if(left == 0){
            left = rect.left;
        }
        if(top == 0){
            top = rect.top;
        }
        if(newWidth == 0){
            newWidth = rect.right- rect.left;
        }
        if(newHeight == 0){
            newHeight = rect.bottom - rect.top;
        }
        return ModuleOperationUtilsJNI.MoveWindow(hWnd,left,top,newWidth,newHeight,true);
    }

    /**
     * 根据窗口取出窗口控件的ID,顶级窗口返回 0
     * @param childHwnd 子窗口句柄
     * @return 返回控件ID
     */
    public static int windowGetDlgCtrlID(int childHwnd){
        return ModuleOperationUtilsJNI.GetDlgCtrlID(childHwnd);
    }

    /**
     * 控件ID取窗口句柄
     * @param ParentHwnd 上一级的窗口句柄
     * @param GetDlgCtrlID 控件ID  windowGetDlgCtrlID(int childHwnd)
     * @return 返回句柄
     */
    public static int windowGetDlgItem(int ParentHwnd,int GetDlgCtrlID){
        return ModuleOperationUtilsJNI.GetDlgItem(ParentHwnd,GetDlgCtrlID);
    }

    /**
     * 窗口设置透明度
     * @param hWnd 窗口句柄
     * @param Alpha 透明度
     * @return 返回逻辑值
     */
    public static boolean windowSetAlpha(int hWnd,int Alpha){
        int smtp = 0;
        ModuleOperationUtilsJNI.SetWindowLongW(hWnd,-20,524288);
        if(Alpha ==0){
            smtp = 1;
        }else {
            smtp = 3;
        }
        return ModuleOperationUtilsJNI.SetLayeredWindowAttributes(hWnd,2,Alpha,smtp);
    }

    /**
     * 窗口激活
     * @param hWnd 窗口句柄
     * @return 返回结果
     */
    public static int windowActivation(int hWnd){
        int currentThreadID = ModuleOperationUtilsJNI.GetCurrentThreadId();
        int targetThreadID = ModuleOperationUtilsJNI.GetWindowThreadId(hWnd);
        ModuleOperationUtilsJNI.AttachThreadInput(targetThreadID,currentThreadID,true);
        int result = ModuleOperationUtilsJNI.SetActiveWindow(hWnd);
        ModuleOperationUtilsJNI.AttachThreadInput(targetThreadID,currentThreadID,true);
        return result;
    }

    /**
     * 窗口还原
     * @param hWnd 窗口句柄
     * @return 返回结果
     */
    public static boolean windowReduction(int hWnd){
        return ModuleOperationUtilsJNI.OpenIcon(hWnd);
    }

    /**
     * 窗口置顶
     * @param hWnd 窗口句柄
     * @param IsActivation 参数二结果
     * @param uFlags 窗口方法 可叠加 参考
     * @return 返回结果
     *
     *
     *         参数 2 可选
     *         HWND_BOTTOM(HWND)1
     *         将窗口置于 Z 顺序的底部。如果hWnd参数标识了一个最顶层窗口，则该窗口将失去其最顶层状态并被放置在所有其他窗口的底部。
     *         HWND_NOTOPMOST(HWND)-2
     *         将窗口放置在所有非最顶层窗口的上方（即，在所有最顶层窗口的后面）。如果窗口已经是非最顶层窗口，则此标志无效。
     *         HWND_TOP(HWND)0
     *         将窗口置于 Z 顺序的顶部。
     *         HWND_TOPMOST(HWND)-1
     *         将窗口置于所有非最顶层窗口之上。窗口即使在停用时也保持其最高位置。
     *
     *
     *
     *
     *
     *         参数 3 可选 可以相加
     *
     * SWP_ASYNCWINDOWPOS
     * 0x4000
     * 如果调用线程和拥有窗口的线程连接到不同的输入队列，系统会将请求发布到拥有窗口的线程。这可以防止调用线程在其他线程处理请求时阻塞其执行。
     *
     * SWP_DEFERERASE
     * 0x2000
     * 防止生成WM_SYNCPAINT消息。
     *
     * SWP_DRAWFRAME
     * 0x0020
     * 在窗口周围绘制一个框架（在窗口的类描述中定义）。
     *
     * SWP_FRAMECHANGED
     * 0x0020
     * 应用使用SetWindowLong函数设置的新框架样式。向窗口发送WM_NCCALCSIZE消息，即使窗口大小没有改变。如果未指定此标志，则仅在更改窗口大小时发送WM_NCCALCSIZE。
     *
     * SWP_HIDEWINDOW
     * 0x0080
     * 隐藏窗口。
     *
     * SWP_NOACTIVATE
     * 0x0010
     * 不激活窗口。如果未设置此标志，则窗口被激活并移动到最顶层或非最顶层组的顶部（取决于hWndInsertAfter参数的设置）。
     *
     * SWP_NOCOPYBITS
     * 0x0100
     * 丢弃客户区的全部内容。如果未指定此标志，则在调整窗口大小或重新定位后，将保存客户区的有效内容并将其复制回客户区。
     *
     * SWP_NOMOVE
     * 0x0002
     * 保留当前位置（忽略X和Y参数）。
     *
     * SWP_NOOWNERZORDER
     * 0x0200
     * 不改变所有者窗口在 Z 顺序中的位置。
     *
     * SWP_NOREDRAW
     * 0x0008
     * 不重绘更改。如果设置了此标志，则不会发生任何类型的重新绘制。这适用于客户区、非客户区（包括标题栏和滚动条）以及由于窗口移动而未覆盖的父窗口的任何部分。设置此标志时，应用程序必须显式地使需要重绘的窗口和父窗口的任何部分无效或重绘。
     *
     * SWP_NOREPOSITION
     * 0x0200
     * 与SWP_NOOWNERZORDER标志相同。
     *
     * SWP_NOSENDCHANGING
     * 0x0400
     * 阻止窗口接收WM_WINDOWPOSCHANGING消息。
     *
     * SWP_NOSIZE
     * 0x0001
     * 保留当前大小（忽略cx和cy参数）。
     *
     * SWP_NOZORDER
     * 0x0004
     * 保留当前 Z 顺序（忽略hWndInsertAfter参数）。
     *
     * SWP_SHOWWINDOW
     * 0x0040
     * 显示窗口。
     */
    public static boolean windowTop(int hWnd,int IsActivation,int uFlags){
        return ModuleOperationUtilsJNI.SetWindowPos(hWnd,IsActivation,0,0,0,0,uFlags);
    }

    /**
     * 窗口关闭
     * @param hWnd 窗口句柄
     * @return 返回结果
     */
    public static boolean windowClose(int hWnd){
        return ModuleOperationUtilsJNI.PostMessageW(hWnd,16,new byte[]{0},new byte[]{0});
    }

    /**
     * 窗口取字符串宽度
     * @param hWnd 窗口句柄
     * @param string 字符串
     * @return 返回字符串像素点
     */
    public static int windowStringWidth(int hWnd,String string){
        TagRect rect = new TagRect();
        ModuleOperationUtilsJNI.GetWindowRect(hWnd,rect);
        int dc = ModuleOperationUtilsJNI.GetDC(hWnd);
        ModuleOperationUtilsJNI.DrawTextW(dc,string,-1,rect,1024|32);
        ModuleOperationUtilsJNI.ReleaseDC(0,dc);
        return rect.right - rect.left;
    }

    /**
     * 窗口置父(全新的窗口置父)
     * @param ParentHwnd 父窗口句柄
     * @param childHwnd 子窗口句柄
     * @param x 坐标位置
     * @param y 坐标位置
     * @param childWidth 子窗口宽度
     * @param childHeight 子窗口高度
     * @return 返回结果
     */
    public static int windowSetParent2(int ParentHwnd,int childHwnd,int x,int y,int childWidth,int childHeight){
        TagRect rect = new TagRect();
        int hWndParentOld = 0 ;
        int dwStyle = 0;
        long dwStyleOld = 0;
        int dwNewStyle = 0;
        if(ParentHwnd==0){
            hWndParentOld = ModuleOperationUtilsJNI.GetPropW(childHwnd,"hwndparentold_tmp");
            dwStyle = ModuleOperationUtilsJNI.GetPropW(childHwnd,"styleold_tmp");
            ModuleOperationUtilsJNI.SetWindowLongW(childHwnd,-16,dwStyle);
            ModuleOperationUtilsJNI.SetParent(childHwnd,hWndParentOld);
            ModuleOperationUtilsJNI.RemovePropW(childHwnd,"hwndparentold_tmp");
            ModuleOperationUtilsJNI.RemovePropW(childHwnd,"styleold_tmp");
        }else {
            ModuleOperationUtilsJNI.SetParent(childHwnd,hWndParentOld);
            hWndParentOld = ModuleOperationUtilsJNI.GetParent(childHwnd);
            dwStyleOld = ModuleOperationUtilsJNI.GetWindowLongW(childHwnd,-16);
            dwStyle = ModuleOperationUtilsJNI.GetWindowLongW(childHwnd,-16);
            dwNewStyle = dwStyle & ~-2147483648;
            dwNewStyle = dwStyle | 1073741824;
            ModuleOperationUtilsJNI.SetPropW(childHwnd,"hwndparentold_tmp",hWndParentOld);
            ModuleOperationUtilsJNI.SetPropW(childHwnd,"styleold_tmp",dwStyle);
            if(dwStyle != dwNewStyle){
                ModuleOperationUtilsJNI.SetWindowLongW(childHwnd,-16,dwStyle);
            }
        }
        ModuleOperationUtilsJNI.GetWindowRect(childHwnd,rect);
        if(childWidth ==0){
            childWidth = rect.right - rect.left;
        }
        if(childHeight ==0){
            childHeight = rect.bottom - rect.top;
        }
        return ModuleOperationUtilsJNI.MoveWindow(childHwnd,x,y,childWidth,childHeight,true);
    }

    /**
     * 窗口取控件句柄
     * @param ParentHwnd 父窗口句柄
     * @param childHwnd 子窗口句柄
     * @param windowClass 窗口类名
     * @param windowTitle 窗口标题
     * @return 返回控件句柄
     */
    public static int windowGetHwnd(int ParentHwnd,int childHwnd,String windowClass,String windowTitle){
        return ModuleOperationUtilsJNI.FindWindowExW(childHwnd,childHwnd,windowClass,windowTitle);
    }

    /**
     * 窗口取窗口坐标位置
     * @param hWnd 窗口句柄
     * @return 返回坐标 tagPOINT
     */
    public static TagPoint windowGetPoint(int hWnd){
        TagPoint Sencer = new TagPoint();
        windowGetCaretPos(Sencer);
        TagRectSize rect = windowGetRectToSize(hWnd);
        TagPoint temp = new TagPoint();
        temp.x = Sencer.x- rect.left;
        temp.y = Sencer.y - rect.top;
        return temp;
    }

    /**
     * 窗口取位置和大小
     * @param hWnd 窗口句柄
     * @return 返回  tagRECTSIZE
     */
    public static TagRectSize windowGetRectToSize(int hWnd){
        TagRect rect = new TagRect();
        TagRectSize tempRect = new TagRectSize();
        ModuleOperationUtilsJNI.GetWindowRect(hWnd,rect);
        tempRect.left = rect.left;
        tempRect.top = rect.top;
        tempRect.width = rect.right - rect.left;
        tempRect.height = rect.bottom - rect.top;
        return tempRect;
    }

    /**
     * 窗体取控件坐标
     * @param hWnd 窗口句柄
     * @return 返回 tagPOINT
     */
    public static TagPoint windowGetcomponentXY(int hWnd){
        if(hWnd ==0){
            hWnd = ModuleOperationUtilsJNI.GetFocus();
        }
        TagPoint temp = new TagPoint();
        temp.y=0;
        temp.x=0;
        ModuleOperationUtilsJNI.ClientToScreen(hWnd,temp);
        return temp;
    }

    /**
     * 窗口置焦点
     * @param hWnd 窗口句柄
     * @return 返回结果
     */
    public static int windowSetFocus(int hWnd){
        return ModuleOperationUtilsJNI.SetForegroundWindow(hWnd);
    }

    /**
     * 窗口取标题
     * @param hWnd 窗口句柄
     * @return 返回标题
     */
    public static String windowGetTitle(int hWnd){
        int dwSize = ModuleOperationUtilsJNI.GetWindowTextLengthW(hWnd);
        if(dwSize==0){
            return "";
        }
        char[] lpString = new char[1025];
        ModuleOperationUtilsJNI.GetWindowTextW(hWnd,lpString,lpString.length);
        return new String(lpString).trim();
    }

    /**
     * 窗口设置标题
     * @param hWnd 窗口句柄
     * @param title 设置的窗口标题
     * @return 返回结果
     */
    public static int windowSetTitle(int hWnd,String title){
        return ModuleOperationUtilsJNI.SetWindowTextW(hWnd,title);
    }

    /**
     * 窗口取类名
     * @param hWnd 窗口句柄
     * @return 返回字符串
     */
    public static String windowGetClassName(int hWnd){
        char[] className = new char[1025];
        ModuleOperationUtilsJNI.GetClassNameW(hWnd,className,className.length);
        return new String(className).trim();
    }

    /**
     * 窗口设置状态
     * @param hWnd  窗口句柄
     * @param state 状态  0 隐藏取消激活 1 还原激活 2 最小化激活 3 最大化激活 4 还原 6 最小化取消激活 7 最小化 9 还原激活
     * @return 返回结果
     */
    public static int windowSetState(int hWnd,int  state){
        return ModuleOperationUtilsJNI.ShowWindow(hWnd,state);
    }

    /**
     * 窗口最大化
     * @param hWnd 窗口句柄
     * @param NoBorder 无边框
     */
    public static void windowSetMaximize(int hWnd,boolean  NoBorder){
        windowSetFocus(hWnd);
        if(NoBorder){
            windowSetLocationSize(hWnd,-2,-2,ModuleOperationUtilsJNI.GetSystemMetrics(62),ModuleOperationUtilsJNI.GetSystemMetrics(61));
        }else {
            ModuleOperationUtilsJNI.PostMessageW(hWnd,274,ByteUtils.intToBytesArray(61488),ByteUtils.intToBytesArray(0));
        }
    }

    /**
     * 窗口最小化
     * @param hWnd 窗口句柄
     * @return 结果
     */
    public static int windowSetMinimize(int hWnd){
        return ModuleOperationUtilsJNI.CloseWindow(hWnd);
    }

    /**
     * 窗口设置圆角化 控件
     * @param hWnd 窗口句柄
     * @param roundWidth 圆宽  默认 50
     * @param roundHeight 圆高  默认 50
     */
    public static void windowSetRoundedCorners(int hWnd,int roundWidth,int roundHeight){
        if(roundWidth==0){
            roundWidth = 50;
        }
        if(roundHeight==0){
            roundHeight = 50;
        }
        int[] sizel = windowGetDlgCtrlSize(hWnd);
        int rectHandle = ModuleOperationUtilsJNI.CreateRoundRectRgn(0,0,sizel[0],sizel[1],roundWidth,roundHeight);
        ModuleOperationUtilsJNI.SetWindowRgn(hWnd,rectHandle,true);
        ModuleOperationUtilsJNI.DeleteObject(rectHandle);
    }

    /**
     * 窗口取控件大小
     * @param hWnd 窗口句柄
     * @return 返回结果  index[0] 宽  index[1] 高
     */
    public static int[] windowGetDlgCtrlSize(int hWnd){
        TagRect rect = new TagRect();
        int[] index = new int[2];
        boolean isOk = ModuleOperationUtilsJNI.GetClientRect(hWnd,rect);
        index[0] = rect.right - rect.left;
        index[1] = rect.bottom - rect.top;
        return index;
    }

    /**
     * 窗口取祖句柄    取一个窗口的顶级句柄，如果提供的句柄已是顶级句柄，将直接返回。
     * @param hWnd 窗口句柄
     * @return 返回 祖句柄
     */
    public static int windowGetRootHwnd(int hWnd){
        return ModuleOperationUtilsJNI.GetAncestor(hWnd,3);
    }

    /**
     * 窗口取父句柄 判断一个窗口是否有父窗口，如有直接返回该窗口的像窗口句柄，否则返回0
     * @param hWnd 窗口句柄
     * @return 返回父句柄
     */
    public static int windowGetFatherHwnd(int hWnd){
        return ModuleOperationUtilsJNI.GetParent(hWnd);
    }

    /**
     * 窗口是否可见
     * @param hWnd  窗口句柄
     * @return 返回结果
     */
    public static int windowIsShow(int hWnd){
        return ModuleOperationUtilsJNI.IsWindowVisible(hWnd);
    }

    /**
     * 窗口显示隐藏
     * @param hWnd 窗口句柄
     * @param isHide 是否隐藏   0隐藏   1 显示
     *
     *               SW_HIDE
     * 0	隐藏窗口并激活另一个窗口。
     *
     * SW_SHOWNORMAL SW_NORMAL
     * 1	激活并显示一个窗口。如果窗口被最小化或最大化，系统会将其恢复到原来的大小和位置。应用程序应在第一次显示窗口时指定此标志。
     *
     * SW_SHOWMINIMIZED
     * 2	激活窗口并将其显示为最小化窗口。
     *
     * SW_SHOWMAXIMIZED
     * SW_MAXIMIZE
     * 3	激活窗口并将其显示为最大化窗口。
     *
     * SW_SHOWNOACTIVATE
     * 4	以最近的大小和位置显示窗口。这个值类似于SW_SHOWNORMAL，除了窗口没有被激活。
     *
     * SW_SHOW
     * 5	激活窗口并以其当前大小和位置显示它。
     *
     * SW_MINIMIZE
     * 6	最小化指定窗口并激活 Z 顺序中的下一个顶级窗口。
     *
     * SW_SHOWMINNOACTIVE
     * 7	将窗口显示为最小化窗口。这个值类似于SW_SHOWMINIMIZED，除了窗口没有被激活。
     *
     * SW_SHOWNA
     * 8	以当前大小和位置显示窗口。这个值类似于SW_SHOW，除了窗口没有被激活。
     *
     * SW_RESTORE
     * 9	激活并显示窗口。如果窗口被最小化或最大化，系统会将其恢复到原来的大小和位置。应用程序在恢复最小化窗口时应指定此标志。
     *
     * SW_SHOWDEFAULT
     * 10	根据启动应用程序的程序传递给CreateProcess函数的STARTUPINFO结构中指定的SW_值设置显示状态。
     *
     * SW_FORCEMINIMIZE
     * 11	最小化一个窗口，即使拥有该窗口的线程没有响应。只有在最小化来自不同线程的窗口时才应使用此标志。
     *
     * @return 返回结果
     */
    public static int windowIsShowHide(int hWnd,int isHide){
        return ModuleOperationUtilsJNI.ShowWindowAsync(hWnd,isHide);
    }

    /**
     * 窗口置透明度
     * @param hWnd 窗口句柄
     * @param alpha 透明度
     * @param AlphaColor 透明色
     * @param isThrough 鼠标是否穿透
     * @return 返回结果
     */
    public static boolean windowSetAlpha(int hWnd,int alpha,int AlphaColor,boolean isThrough){
        int tempValue = 0;
        int tempFun = 0;
        windowSetThrough(hWnd,isThrough);
        if(AlphaColor==0){
            tempFun = 2;
        }
        if(alpha==0){
            tempFun = 1;
        }else {
            tempFun = 3;
        }
        return ModuleOperationUtilsJNI.SetLayeredWindowAttributes(hWnd,AlphaColor,alpha,tempFun);
    }


    /**
     * 窗口取透明度
     * @param hWnd 窗口句柄
     * @return 返回透明度 失败 -1
     */
    public static int windowGetAlpha(int hWnd){
        byte[] pbAlpha= new byte[4];
        int dwStyle = ModuleOperationUtilsJNI.GetWindowLongW(hWnd,-20);
        if((dwStyle & 524288) !=0){
            ModuleOperationUtilsJNI.GetLayeredWindowAttributes(hWnd,pbAlpha);
        }else {
            System.err.println("没有修改过透明度");
            return -1;
        }
        return ByteUtils.bytesArrayToInt(pbAlpha) & 0xff;
    }

    /**
     * 窗口置穿透
     * @param hWnd 窗口句柄
     * @param isThrough 鼠标是否穿透
     * @return 返回结果
     */
    public static boolean windowSetThrough(int hWnd,boolean isThrough){
        int dwStyleEx = 0;
        int dwStyleExNew = 0;
        if(!isThrough){
            dwStyleEx = ModuleOperationUtilsJNI.GetPropW(hWnd,"transparent_tmp");
            ModuleOperationUtilsJNI.RemovePropW(hWnd,"transparent_tmp");
            ModuleOperationUtilsJNI.SetWindowLongW(hWnd,-20,dwStyleEx);
            return dwStyleEx == ModuleOperationUtilsJNI.GetWindowLongW(hWnd,-20);
        }
        dwStyleEx = ModuleOperationUtilsJNI.GetWindowLongW(hWnd,-20);
        if((dwStyleExNew&32)==0){
            dwStyleExNew = dwStyleExNew|32;
        }
        if((dwStyleExNew&524288)==0){
            dwStyleExNew = dwStyleExNew|524288;
        }
        ModuleOperationUtilsJNI.SetPropW(hWnd,"transparent_tmp",dwStyleEx);
        ModuleOperationUtilsJNI.SetWindowLongW(hWnd,-20,dwStyleExNew);
        dwStyleEx = ModuleOperationUtilsJNI.GetWindowLongW(hWnd,-20);
        return (dwStyleEx&32&524288)!=0;
    }

    /**
     * 窗口总在最近
     * @param hWnd 窗口句柄
     * @param AlwaysTop 是否总在最前
     * @return 返回结果
     */
    public static boolean windowAlwaysTop(int hWnd,boolean AlwaysTop){
        if(AlwaysTop){
            windowSetFocus(hWnd);
            return ModuleOperationUtilsJNI.SetWindowPos(hWnd,-1,0,0,0,0,1|2);
        }
        return ModuleOperationUtilsJNI.SetWindowPos(hWnd,-2,0,0,0,0,1|2);
    }

    /**
     * 窗口锁住解锁      在指定的窗口里允许或禁止所有鼠标及键盘输入
     * @param hWnd 窗口句柄
     * @param types 是否禁止
     * @return 返回结果
     */
    public static int windowUnlock(int hWnd,boolean types){
        return ModuleOperationUtilsJNI.EnableWindow(hWnd,types);
    }

    /**
     * 窗口禁止关闭
     * @param hWnd 窗口句柄
     * @param isClose 是否禁止关闭
     * @return 返回结果
     */
    public static boolean windowBanClose(int hWnd,boolean isClose){
        TagRect lpRect = new TagRect();
        int menuHandle = ModuleOperationUtilsJNI.GetSystemMenu(hWnd,0);
        if(menuHandle ==0){
            return false;
        }
        if(isClose){
            ModuleOperationUtilsJNI.EnableMenuItem(menuHandle,6,1025);
        }else {
            ModuleOperationUtilsJNI.EnableMenuItem(menuHandle,6,1024);
        }
        if(ModuleOperationUtilsJNI.GetWindowRect(hWnd,lpRect)==0){
            return false;
        }
        if(ModuleOperationUtilsJNI.RedrawWindow(0,lpRect,0,1|128|256)==0){
            return false;
        }
        return true;
    }

    /**
     * 窗口渐隐渐现
     * @param hWnd 窗口句柄
     * @param types 类型 0 逐渐隐藏   1逐渐显示
     * @param graduallyTimem 可控 默认3 1-10为准 越小越快
     * @param callback  回调处理的事件  逐渐过程完毕后执行回调处理  new windowSetGraduallyHideShowCallBack()  回调内部返回 true
     * @return 返回结果
     */
    public static boolean windowSetGraduallyHideShow(int hWnd, int types, int graduallyTimem, WindowSetGraduallyHideShowCallBack callback) {
        try {
            if (graduallyTimem == 0) {
                graduallyTimem = 3;
            }

            if (types == 1) {
                for (int i = 0; i < 255; i++) {
                    windowSetAlpha(hWnd, i);
                    Thread.sleep(graduallyTimem);
                }
            } else {
                for (int i = 255; i > 0; i--) {
                    windowSetAlpha(hWnd, i);
                    Thread.sleep(graduallyTimem);
                }
            }
            return callback.callback();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 窗口_取屏幕句柄
     * @return  返回屏幕句柄
     */
    public static long windowGetScreenHWnd(){
        return ModuleOperationUtilsJNI.GetDesktopWindow();
    }

    /**
     * 窗体是否最小化
     * @param hWnd 窗口句柄
     * @return 返回结果
     */
    public static boolean windowGetMinimize(int hWnd){
        return ModuleOperationUtilsJNI.IsIconic(hWnd);
    }

    /**
     * 窗体是否最大化
     * @param hWnd 窗口句柄
     * @return 返回结果
     */
    public static boolean windowGetMaximize(int hWnd){
        return ModuleOperationUtilsJNI.IsZoomed(hWnd);
    }

    /**
     * 窗体设置控件内容
     * @param hWnd 控件句柄
     * @param title 内容
     * @return 返回结果
     */
    public static int windowSetDlgItemText(int hWnd,String title){
        return ModuleOperationUtilsJNI.SendMessageW(hWnd,12,ByteUtils.intToBytesArray(0),ByteUtils.utf8ToUniCodeArray(title));
    }

    /**
     * 窗体设置控件状态
     * @param hWnd 控件句柄
     * @param showHide 显示隐藏
     * @return 返回结果
     */
    public static boolean windowSetDlgItemState(int hWnd,boolean showHide){
        return ModuleOperationUtilsJNI.ShowScrollBar(hWnd,2,showHide);
    }

    /**
     * 窗体句柄是否有效
     * @param hWnd 窗体句柄
     * @return 返回结果
     */
    public static boolean windowIsValid(int hWnd){
        return ModuleOperationUtilsJNI.IsWindow(hWnd);
    }

    /**
     * 暴力枚举句柄
     * @param windowTtitle 窗口标题
     * @return 返回句柄
     */
    public static int constraintGetHwnd(String windowTtitle){
        String saveTitle ="";
        int i = 0;
        if(i>=6000000){
            i = 0;
        }
        if(!StringUtils.equals(windowTtitle,"")){
            i = 0;
            saveTitle =  windowTtitle;
        }
        byte[] Name = new byte[1026];
        if(StringUtils.equals(windowTtitle,"")){

            return -1;
        }
        for(i = 0;i<6000000;i++){
            if(windowIsValid(i)){
                if(StringUtils.equals(windowGetTitle(i),saveTitle)){
                    return i;
                }
            }
        }
        return -1;
    }
    /**
     * 暴力枚举句柄2
     * @param windowTtitle 窗口标题
     * @param windowClassName 窗口类名
     * @return 返回句柄
     */
    public static int constraintGetHwnd(String windowTtitle,String windowClassName){
        if (StringUtils.equals(windowClassName,"")){
            windowClassName = "GlassWndClass-GlassWindowClass-2";
        }
        String saveTitle ="";
        int i = 0;
        if(i>=6000000){
            i = 0;
        }
        if(!StringUtils.equals(windowTtitle,"")){
            i = 0;
            saveTitle =  windowTtitle;
        }
        byte[] Name = new byte[1026];
        if(StringUtils.equals(windowTtitle,"")){

            return -1;
        }
        for(i = 0;i<6000000;i++){
            if(windowIsValid(i)){
                if(StringUtils.equals(windowGetTitle(i),saveTitle) && StringUtils.equals(windowGetClassName( i),windowClassName)){
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 获取Swing窗口句柄
     * @param ClassName 窗口类名
     * @param WinTitle 窗口标题
     * @return 返回窗口句柄
     */
    public static int SwingWindowsHwnd(String ClassName,String WinTitle){
        if (StringUtils.equals(ClassName,"")){
            ClassName ="SunAwtFrame";
        }
        if(ProcessAndThreadUtils.getCurrentProcessPID()== ProcessAndThreadUtils.getCurrentProcessId()){
            return WindowOperationUtils.windowGetHwndTow(ProcessAndThreadUtils.getCurrentProcessId(),ClassName , WinTitle);
        }else {
            return WindowOperationUtils.windowGetHwndTow(ProcessAndThreadUtils.getCurrentProcessPID(),ClassName , WinTitle);
        }
    }

    /**
     * 窗口取句柄1
     * @param ProcessID 进程ID
     * @param ClassName 窗口类名
     * @param WinTitle 窗口标题
     * @return 返回句柄
     */
    public static int windowGetHwndOne(int ProcessID,String ClassName,String WinTitle){
        if(ProcessID==-1 && StringUtils.equals(ClassName,"") && StringUtils.equals(WinTitle,"")){
            System.err.println("至少填写一个！参数");
            return -1;
        }
        List<TagWinInfo> wininfo = enumWindowInfoOne();
        for(TagWinInfo info:wininfo){
            if(ProcessID !=-1){
                if(info.th32ProcessID==ProcessID && StringUtils.equals(ClassName,info.className) && StringUtils.equals(WinTitle,info.titleName)){
                    return info.hwnd;
                }
            }
        }
        return -1;
    }
    /**
     * 窗口取句柄2
     * @param ProcessID 进程ID
     * @param ClassName 窗口类目
     * @param WinTitle 窗口标题
     * @return 返回句柄
     */
    public static int windowGetHwndTow(int ProcessID,String ClassName,String WinTitle){
        if(ProcessID==-1 && StringUtils.equals(ClassName,"") && StringUtils.equals(WinTitle,"")){
            System.err.println("至少填写一个！参数");
            return -1;
        }
        List<TagWinInfo> wininfo = new ArrayList<>();
        enumWindowInfoTow(wininfo,false);
        for(TagWinInfo info:wininfo){
            if(ProcessID!=-1){
                if(info.th32ProcessID==ProcessID && StringUtils.equals(ClassName,info.className) && StringUtils.equals(WinTitle,info.titleName)){
                    return info.hwnd;
                }
            }
        }
        return -1;
    }

    /**
     * 枚举所有子窗口句柄
     * @param childWindow 返回窗口句柄
     * @return 返回成员数
     */
    public static long WindowEnum(List<Integer> childWindow){
        childWindow.clear();
        int temp = 0 ;
        temp =ModuleOperationUtilsJNI.FindWindowExW(0,0,null,null);
        while (temp!=0){
            childWindow.add(temp);
            temp =ModuleOperationUtilsJNI.FindWindowExW(0,temp,null,null);
        }
        return childWindow.size();
    }

    /**
     * 获取所有窗口句柄下的子窗口类名
     * @param childWindowClass 窗口类目
     * @return 返回成员数
     */
    public static long WindowEnumChildWindowClassName( List<String> childWindowClass){
        List<Integer> childWindow = new ArrayList<>();
        childWindow.clear();
        childWindowClass.clear();
        WindowEnum(childWindow);
        Iterator<Integer> childWindowIter = childWindow.iterator();
        while (childWindowIter.hasNext()){
            int chindHwnd= childWindowIter.next();
            childWindowClass.add(windowGetClassName(chindHwnd));
        }
        return childWindowClass.size();
    }
    /**
     * 获取所有窗口句柄下的子窗口标题
     * @param childWindowTitle 窗口类目
     * @return 返回成员数
     */
    public static long WindowEnumChildWindowTitleName( List<String> childWindowTitle){
        List<Integer> childWindow = new ArrayList<>();
        childWindow.clear();
        childWindowTitle.clear();
        WindowEnum(childWindow);
        Iterator<Integer> childWindowIter = childWindow.iterator();
        while (childWindowIter.hasNext()){
            int chindHwnd= childWindowIter.next();
            childWindowTitle.add(windowGetTitle(chindHwnd));
        }
        return childWindowTitle.size();
    }
    /**
     * 枚举窗口信息1
     * @return 集合
     */
    public static List<TagWinInfo> enumWindowInfoOne(){
        ModuleOperationUtilsJNI.EnumWindows(new JscmCallBack() {
            @Override
            public boolean callback(int hWnd, int data) {
                if(windowIsValid(hWnd)==true){
                    TagWinInfo info = new TagWinInfo();
                    info.className = windowGetClassName(hWnd);
                    info.titleName = windowGetTitle(hWnd);
                    info.hwnd = hWnd;
                    info.threadID = ModuleOperationUtilsJNI.GetWindowThreadId(hWnd);
                    info.th32ProcessID =ModuleOperationUtilsJNI.GetWindowProcessId(hWnd);
                    temp.add(info);
                }
                return true;
            }
        },0);
        return temp;
    }

    /**
     * 枚举窗口信息2
     * @param wininfo 窗口信息
     * @param isShow 可见性  默认为真:所有可见窗口  假:所有窗口
     * @return 返回成员数
     */
    public static int enumWindowInfoTow(List<TagWinInfo> wininfo,boolean isShow){
        wininfo.clear();
        int tempHandle = ModuleOperationUtilsJNI.GetWindow(ModuleOperationUtilsJNI.GetDesktopWindow(),5);
        while (tempHandle != 0){
            if(!isShow || ModuleOperationUtilsJNI.IsWindowVisible(tempHandle)==1){
                TagWinInfo info = new TagWinInfo();
                info.className = windowGetClassName(tempHandle);
                info.titleName = windowGetTitle(tempHandle);
                info.hwnd = tempHandle;
                info.threadID = ModuleOperationUtilsJNI.GetWindowThreadId(tempHandle);
                info.th32ProcessID = ModuleOperationUtilsJNI.GetWindowProcessId(tempHandle);
                wininfo.add(info);
            }
            tempHandle = ModuleOperationUtilsJNI.GetWindow(tempHandle,2);
        }
        return wininfo.size();
    }

    /**
     * 类名取窗口句柄
     * @param className 类名
     * @return 返回句柄
     */
    public static int classNameToHwnd(String className){
        int tempHandle = ModuleOperationUtilsJNI.GetWindow(ModuleOperationUtilsJNI.GetDesktopWindow(),5);
        System.out.println(ModuleOperationUtilsJNI.GetDesktopWindow());
        while (tempHandle != 0){
            String classN = windowGetClassName(tempHandle);

            if(StringUtils.equals(classN,className)){
                return tempHandle;
            }
            tempHandle = ModuleOperationUtilsJNI.GetWindow(tempHandle,2);
        }
        return -1;
    }

    /**
     * 进程取所有窗口
     * @param ProcessId 进程ID
     * @param allHwnd 窗口句柄
     * @return 返回成员数
     */
    public static int processPidGetAllHwnd(int ProcessId,List<Integer> allHwnd){
        allHwnd.clear();

        int tempHandle = ModuleOperationUtilsJNI.GetWindow(ModuleOperationUtilsJNI.GetDesktopWindow(),5);
        while (tempHandle != 0){
            if(ModuleOperationUtilsJNI.GetWindowProcessId(tempHandle)==ProcessId){
                allHwnd.add(tempHandle);
            }
            tempHandle = ModuleOperationUtilsJNI.GetWindow(tempHandle,2);
        }
        return allHwnd.size();
    }

    /**
     * 取当前窗口句柄
     * @return 返回句柄
     */
    public static int windowGetHwnd(){
        return ModuleOperationUtilsJNI.GetForegroundWindow();
    }

    /**
     * 取当前父窗口句柄
     * @return 返回父窗口句柄
     */
    public static int windowGetParentHwnd(){
        return ModuleOperationUtilsJNI.GetAncestor(ModuleOperationUtilsJNI.GetForegroundWindow(),3);
    }
/*
参考例子
    frame = WindowOperationUtils.SwingWindowEmbeddedWindow(OpenWindow.OpenWindow_hWindow,new PieChart(),100,100,300,400);

    boolean s = XWnd_RegEventC1_(OpenWindow.OpenWindow_hWindow,XWM_XC_TIMER.getMessageCode(), new WindowEventCallBack() {
        @Override
        public boolean OnWndXCTimer(int hWindow, long nTimerID) {
            SwingWindowSizeMessage(frame);
            System.out.println("定时器" + frame.frameHwnd);
            return false;
        }
    });
                System.out.println(s);
                System.out.println(XWnd_SetXCTimer(OpenWindow.OpenWindow_hWindow, XWM_XC_TIMER.getMessageCode(), 500));
 */
    /**
     * 嵌入Swing窗口
     *
     * @param hWindow 炫彩窗口句柄
     * @param comp swing组件
     * @param x x坐标
     * @param y y坐标
     * @param width 宽度
     * @param height 高度
     * @return SwingJFrameInfo  必须设置为全局的静态SwingJFrameInfo信息
     */
    public static SwingJFrameInfo SwingWindowEmbeddedWindow(int hWindow,Component comp,int x,int y  , int width, int height){
        System.setProperty("sun.awt.noerasebackground", "true");
        System.setProperty("java.awt.headless", "false");
        SwingJFrameInfo info = new SwingJFrameInfo();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(3);
        frame.setLocationRelativeTo(null);
        frame.setSize(width, height);
        frame.setLocation(x, y);
        frame.setType(Window.Type.UTILITY);
        frame.setUndecorated(true);
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add(comp, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.CENTER);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
        frame.setFocusable(false);
        info.frameHwnd =  GetSwingWindowHandle(frame);
        info.x = frame.getX();
        info.y = frame.getY();
        info.width = frame.getWidth();
        info.height = frame.getHeight();
        info.frame = frame;
        WindowEmbeddedWindow(info.frameHwnd,ModuleOperationUtilsJNI.XWnd_GetHWND(hWindow));
        return info;
    }

    /**
     * 回调控制Swing窗口位置
     * @param frame swing窗口信息
     */
    public static void SwingWindowSizeMessage(SwingJFrameInfo  frame){
        windowSetLocationSize(frame.frameHwnd,frame.getX(),frame.getY(),frame.getWidth(),frame.getHeight());
    }
}
