package com.scm.all.export;



import com.scm.all.pfunc.KeyCodeCallBack;
import com.scm.all.pfunc.ResponseEventCallBack;
import com.scm.all.pfunc.ResponseEventCallBackRunnable;
import com.scm.all.pfunc.ResponseEventCallTowBackRunnable;
import com.scm.all.struct.TagHotKeyInfoThree;
import com.scm.all.struct.TagHotKeyInfoTow;
import com.scm.all.struct.TagINPUT;
import com.scm.all.struct.TagPoint;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.List;
import java.util.*;
import java.util.concurrent.*;


/**
 * 本类将存放系统API选项
 */
public class SystemUtils {
    public static ThreadPoolExecutor threadPoolExecutor =new ThreadPoolExecutor(3,20,0, TimeUnit.SECONDS,new LinkedBlockingQueue(100), Executors.defaultThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy());
    public static List<TagHotKeyInfoTow> hotkeyinfotowList = new ArrayList<>();
    public static List<TagHotKeyInfoThree> hotkeyinfothreeList = new ArrayList<>();
    /**
     * @return 返回桌面路径
     */
    public static String getDesktop(){
        File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
        return desktopDir.getAbsolutePath();
    }
    /**
     * 取自身启动目录() 完整
     * @return  返回结果
     */
    public static String getRunPathOne(){
        final String javaLibraryPath = System.getProperty("java.library.path");
        String jvmFolder = javaLibraryPath.substring(0, javaLibraryPath.indexOf(';'));
        return jvmFolder;
    }
    /**
     * getSystemPath 取System32系统目录
     * @return  返回结果
     */
    public static String System32Path(){
        char[] lpBuffer = new char[260];
        ModuleOperationUtilsJNI.GetSystemDirectoryW(lpBuffer,lpBuffer.length);
        String ret = new String(lpBuffer).trim();
        return ret;
    }
    /**
     * getSystemPath 取系统目录
     * @return  返回结果
     */
    public static String getSystemPath(){
        char[] lpBuffer = new char[260];
        ModuleOperationUtilsJNI.GetWindowsDirectoryW(lpBuffer,lpBuffer.length);
        String ret = new String(lpBuffer).trim();
        return ret;
    }
    /**
     * 弹出对话框
     * @param hWnd 窗口句柄
     * @param lpText 内容
     * @param lpCaption 标题
     * @param uType 窗体类型
     * 要指示消息框中显示的按钮，请指定以下值之一。
     * 0x00000002L  消息框包含三个按钮：Abort、Retry和Ignore。
     * 0x00000006L  消息框包含三个按钮：Cancel、Try Again、Continue。使用此消息框类型而不是 MB_ABORTRETRYIGNORE。
     * 0x00004000L  将帮助按钮添加到消息框。当用户单击“帮助”按钮或按 F1 时，系统会向所有者发送WM_HELP消息。
     * 0x00000000L  消息框包含一个按钮：OK。这是默认设置。
     * 0x00000001L  消息框包含两个按钮：OK和Cancel。
     * 0x00000005L  消息框包含两个按钮：重试和取消。
     * 0x00000004L  消息框包含两个按钮：Yes和No。
     * 0x00000003L  消息框包含三个按钮：Yes、No和Cancel。
     *要在消息框中显示图标，请指定以下值之一。
     * 0x00000030L  消息框中会出现一个感叹号图标。
     * 0x00000030L  消息框中会出现一个感叹号图标。
     * 0x00000040L  消息框中出现一个由圆圈 中的小写字母i组成的图标。
     * 0x00000040L  消息框中出现一个由圆圈 中的小写字母i组成的图标。
     * 0x00000020L  消息框中会出现一个问号图标。不再推荐使用问号消息图标，因为它不能清楚地表示特定类型的消息，并且因为将消息表述为问题可以适用于任何消息类型。此外，用户可能会将消息符号问号与帮助信息混淆。因此，不要在您的消息框中使用此问号消息符号。系统继续支持它的包含只是为了向后兼容。
     * 0x00000010L  停止标志图标出现在消息框中。
     * 0x00000010L  停止标志图标出现在消息框中。
     * 0x00000010L  停止标志图标出现在消息框中。
     * 要指示默认按钮，请指定以下值之一。
     * 0x00000000L  第一个按钮是默认按钮。MB_DEFBUTTON1是默认值，除非指定了 MB_DEFBUTTON2、MB_DEFBUTTON3或MB_DEFBUTTON4。
     * 0x00000100L  第二个按钮是默认按钮。
     * 0x00000200L  第三个按钮是默认按钮。
     * 0x00000300L  第四个按钮是默认按钮。
     * 要指示对话框的模式，请指定以下值之一。
     * 0x00000000L  在由hWnd参数标识的窗口中继续工作之前，用户必须响应消息框。但是，用户可以移动到其他线程的窗口并在这些窗口中工作。
     * 根据应用程序中窗口的层次结构，用户可能能够移动到线程内的其他窗口。消息框父级的所有子窗口都会自动禁用，但弹出窗口不会。
     *
     * 如果MB_SYSTEMMODAL和MB_TASKMODAL 均未指定，则MB_APPLMODAL为默认值。
     *
     * 0x00001000L  除了消息框具有WS_EX_TOPMOST样式外，与 MB_APPLMODAL 相同。使用系统模式消息框来通知用户需要立即关注的严重的、潜在的破坏性错误（例如，内存不足）。此标志对用户与hWnd关联的窗口以外的窗口进行交互的能力没有影响。
     * 0x00002000L  与MB_APPLMODAL相同，但如果hWnd参数为NULL ，则禁用属于当前线程的所有顶级窗口。当调用应用程序或库没有可用的窗口句柄但仍需要阻止输入到调用线程中的其他窗口而不挂起其他线程时，请使用此标志。
     *
     * 要指定其他选项，请使用以下一个或多个值。
     * 0x00020000L  与交互式窗口站的桌面相同。有关更多信息，请参阅窗口站。如果当前输入桌面不是默认桌面，MessageBox不会返回，直到用户切换到默认桌面。
     * 0x00080000L  文本是右对齐的。
     * 0x00100000L  在希伯来语和阿拉伯语系统上使用从右到左的阅读顺序显示消息和标题文本
     * 0x00010000L  消息框成为前台窗口。在内部，系统为消息框调用SetForegroundWindow函数。
     * 0x00040000L  消息框是用WS_EX_TOPMOST窗口样式创建的。
     * 0x00200000L  调用者是通知用户事件的服务。即使没有用户登录到计算机，该功能也会在当前活动桌面上显示一个消息框。终端服务：如果调用线程具有模拟令牌，则该函数会将消息框定向到模拟令牌中指定的会话。如果设置了此标志，则hWnd参数必须为NULL。这是为了使消息框可以出现在与hWnd对应的桌面之外的桌面上。有关使用此标志的安全注意事项的信息，请参阅交互式服务。特别要注意的是，此标志可以在锁定的桌面上生成交互式内容，因此应仅用于非常有限的一组场景，例如资源耗尽。
     * @return 返回结果 可以根据结果做判断
     */
    public static int messageBoxExW(int hWnd,String lpText,String lpCaption,int uType){
        return ModuleOperationUtilsJNI.MessageBoxExW(hWnd,lpText,lpCaption,uType,0);
    }
    /**
     * 监视热键  键代码参考 ：https://docs.microsoft.com/en-us/windows/win32/inputdev/virtual-key-codes
     * 目前仅支持单个按键监视
     * @param response 响应事件 实现接口 new ResponseEventCallBack()
     * @param keyCode 键代码
     * @param cycle 周期
     * @return 返回标识符
     */
    public static int listeningHotKeyOne(ResponseEventCallBack response, int keyCode, int cycle){
        if( cycle ==0){
            cycle=150;
        }
        if((byte)Math.abs(keyCode)<=0){
            return -1;
        }
        for(int j = 0 ;j<hotkeyinfotowList.size();j++){
            if(hotkeyinfotowList.get(j).keyCode ==keyCode && hotkeyinfotowList.get(j).cycle ==cycle && hotkeyinfotowList.get(j).response ==response){
                if(hotkeyinfotowList.get(j).logo !=0){
                    return hotkeyinfotowList.get(j).logo;
                }else {
                    hotkeyinfotowList.get(j).logo = j + 1000000;
                    hotkeyinfotowList.set(j,hotkeyinfotowList.get(j));
                    return hotkeyinfotowList.get(j).logo;
                }
            }
        }

        TagHotKeyInfoTow hot = new TagHotKeyInfoTow();
        hot.logo = hotkeyinfotowList.size() + 1000001;
        hot.cycle = cycle;
        hot.keyCode = keyCode;
        hot.response = response;
        hotkeyinfotowList.add(hot);
        if(hot.logo == 1000001){
            ResponseEventCallBackRunnable.cycle = hot.cycle;
            threadPoolExecutor.execute(new ResponseEventCallBackRunnable());
        }
        return hot.logo;
    }


    /**
     * 卸载监视热键
     * @param logo 标识符
     * @return 返回结果
     */
    public static boolean removeListeningHotKeyOne(int logo){
        Iterator iterator1 =  hotkeyinfotowList.iterator();
        while (iterator1.hasNext()){
            TagHotKeyInfoTow h = (TagHotKeyInfoTow)iterator1.next();
            if(h.logo == logo){
                threadPoolExecutor.shutdown();
                iterator1.remove();
                break;
            }
        }
        return true;
    }

    /**
     * 监视热键2  键代码参考 ：https://docs.microsoft.com/en-us/windows/win32/inputdev/virtual-key-codes
     * 支持组合按键监视 和单个监视
     * @param response 响应事件 实现接口 new ResponseEventCallBack()
     * @param keyCode 键代码
     * @param functionKey 1 Alt 2 Ctrl 4 shift 8 win
     * @param cycle 周期
     * @return 返回标识符
     */
    public static int listeningHotKeyTow(ResponseEventCallBack response,int keyCode,int functionKey,int cycle){
        if( cycle ==0){
            cycle=150;
        }
        if((byte)Math.abs(keyCode)<=0){
            return -1;
        }
        for(int j = 0 ;j<hotkeyinfothreeList.size();j++){
            if(hotkeyinfothreeList.get(j).keyCode ==keyCode && hotkeyinfothreeList.get(j).cycle ==cycle && hotkeyinfothreeList.get(j).response ==response && hotkeyinfothreeList.get(j).functionKey ==functionKey){
                if(hotkeyinfothreeList.get(j).logo !=0){
                    return hotkeyinfothreeList.get(j).logo;
                }else {
                    hotkeyinfothreeList.get(j).logo = j + 3000000;
                    hotkeyinfothreeList.set(j,hotkeyinfothreeList.get(j));
                    return hotkeyinfothreeList.get(j).logo;
                }
            }
        }
        TagHotKeyInfoThree hot = new TagHotKeyInfoThree();
        hot.logo = hotkeyinfotowList.size() + 3000001;
        hot.cycle = cycle;
        hot.keyCode = keyCode;
        hot.response = response;
        hot.functionKey =functionKey;
        hotkeyinfothreeList.add(hot);
        if(hot.logo == 3000001){
            ResponseEventCallTowBackRunnable.cycle = hot.cycle;
            threadPoolExecutor.execute(new ResponseEventCallTowBackRunnable());
        }
        return hot.logo;
    }
    /**
     * 卸载监视热键2
     * @param logo 标识符
     * @return 返回结果
     */
    public static boolean removeListeningHotKeyTow(int logo){
        Iterator iterator2 =  hotkeyinfothreeList.iterator();
        while (iterator2.hasNext()){
            TagHotKeyInfoThree h = (TagHotKeyInfoThree)iterator2.next();
            if(h.logo == logo){
                threadPoolExecutor.shutdown();
                iterator2.remove();
                break;
            }
        }
        return true;
    }


    /**
     * 全局键盘钩子
     * @param callBack 键盘钩子回调事件
     */
    public static void setWindowsHookEx(KeyCodeCallBack callBack){
        ModuleOperationUtilsJNI.SetWindowsHookEx(callBack);
    }

    /**
     * 卸载全局钩子
     * @return 返回卸载结果
     */
    public static boolean unhookWindowsHookEx(){
        return ModuleOperationUtilsJNI.UnhookWindowsHookEx();
    }

    /**
     * 消息循环事件 用于窗体循环的
     */
    public static void messageRun(){ModuleOperationUtilsJNI.MessageRun();}

    /**
     * 返回按键文本
     * @param wParam 键消息
     * @param vkCode 键代码
     * @return 返回键文本
     */
    public static String keyCodeToStringOne(int wParam,int vkCode){
        if (wParam == 0x100 || wParam == 0x104){
            String keyText = KeyEvent.getKeyText(vkCode);
            return keyText;
        }
        return null;
    }
    /**
     * 返回按键文本
     * @param wParam 键消息
     * @param vkCode 键代码
     * @return 返回键文本
     */
    public static String keyCodeToStringTow(int wParam,int vkCode){
        String keyText = KeyEvent.getKeyText(vkCode);
        return keyText;
    }

    /**
     * 置剪贴板内容
     * @param v 剪贴板内容
     */
    public static void setClipboardContents(String v){
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        // 封装文本内容
        Transferable trans = new StringSelection(v);
        // 把文本内容设置到系统剪贴板
        clipboard.setContents(trans, null);
    }
    /**
     * 取中间文本
     * @param allText  主要内容
     * @param index_x  左边
     * @param index_z  右边
     * @return  返回内容
     */
    public static String textCenter(String allText, String index_x, String index_z) {
        int index_head = allText.indexOf(index_x, 0);
        if (index_head >= 0) {
            index_head += index_x.length();
        }
        int index_end = allText.indexOf(index_z, index_head);
        if (index_head < 0 || index_end < 0) {
            return "";
        }
        String Addr = allText.substring(index_head, index_end);
        return Addr;
    }

    /**
     * 返回jar运行目录
     * @return 返回路径
     */
    public static String runFilePath(){
        return System.getProperty("user.dir");
    }

    /**
     * 后台模拟消息
     * @param x 坐标
     * @param y 坐标
     * @param keyType 类型
     *                硬件输入：https://learn.microsoft.com/en-us/windows/desktop/api/winuser/ns-winuser-hardwareinput
     *                键盘输入：https://learn.microsoft.com/en-us/windows/desktop/api/winuser/ns-winuser-keybdinput
     *                键盘输入：https://learn.microsoft.com/en-us/windows/desktop/inputdev/keyboard-input
     *                鼠标输入：https://learn.microsoft.com/en-us/windows/desktop/api/winuser/ns-winuser-mouseinput
     * @param valueType 事件
     * @return 结果
     *
     *
     *
     */
    public static int sendInput(int x,int y,int keyType,int valueType){
        return ModuleOperationUtilsJNI.SendInput(x,y,keyType,valueType);
    }

    /**
     * 返回屏幕坐标 X
     * @param index 坐标位置
     * @return 返回实际坐标位置
     */
    public static int mouseX(int index){
        return index * (65535 / ModuleOperationUtilsJNI.GetSystemMetrics(0));
    }

    /**
     * 格式化屏幕坐标
     * @param index 坐标位置
     * @param screenSize 屏幕宽或高
     * @return 返回实际坐标值
     */
    public static int getScreenXY(int index,int  screenSize){
        return index * (65535/ screenSize);
    }

    /**
     * 模拟鼠标左键
     * @param x 坐标位置
     * @param y 坐标位置
     * @param w 屏幕宽
     * @param h 屏幕高
     */
    public static void mouseClickOne(int x,int y,int w,int h){
        SystemUtils.sendInput(x * (65535/ w),y * (65535/ h), TagINPUT.INPUT_MOUSE,0x8000| 0x0001 | 0x0002);
        SystemUtils.sendInput(x * (65535/ w),y * (65535/ h), TagINPUT.INPUT_MOUSE,0x8000| 0x0001 | 0x0004);
    }

    /**
     * 传奇游戏坐标转服务器坐标
     * @param point 坐标类
     * @return 返回坐标数组[X,Y]
     */
    public static float[] getGameToServer(TagPoint point)
    {
        float tempY = ((point.x * 100) + (point.y * 100)) / 0.707107f / 0.000976562f / 2f / 4096f;
        float tempX= ((point.x * 100) / 0.707107f / 0.000976562f + 134217728f) / 4096f - tempY;
        return new float[]{(float) Math.floor((tempX / 32f) + 0.5), (float) Math.floor((tempY / 32f) + 0.5)};
    }

    /**
     * 传奇服务器坐标转游戏坐标
     * @param point 坐标类
     * @return 返回坐标数组[X,Y]
     */
    public static float[] getServerToGame(TagPoint point)
    {
        float[] temp = new float[]{((float)point.x - 0.5f) * 32f, ((float)point.y - 0.5f) * 32f};
        float tempY = ((temp[1] + temp[0]) * 4096f - 134217728f) * 0.707107f * 0.000976562f;
        float tempX = ((temp[1] - temp[0]) * 4096f + 134217728f) * 0.707107f * 0.000976562f;
        return new float[]{(float) Math.floor(tempX / 100), (float) Math.floor(tempY / 100)};
    }
    /**
     * 模拟鼠标左键
     * @param hwnd 窗口句柄
     * @param x 坐标位置
     * @param y 坐标位置
     */
    public static void mouseClickTwo(int hwnd,int x,int y){
        ModuleOperationUtilsJNI.SendMessageWInfo(hwnd,0x0201, 0x0001, SystemUtils.getSendMessageXY(x,y));
        ModuleOperationUtilsJNI.SendMessageWInfo(hwnd,0x0202, 0x0001, SystemUtils.getSendMessageXY(x,y));
    }

    /**
     * 坐标消息
     * @param x 坐标位置
     * @param y 坐标位置
     * @return 坐标消息
     */
    public static int getSendMessageXY(int x,int y){
        return  ModuleOperationUtilsJNI.MAKELPARAM(x,y);
    }

    /**
     * 定时任务1
     * @return ScheduledExecutorService
     */
    public static ScheduledExecutorService timerExecutorOne(){
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        return executor;
    }
    /**
     * 定时任务2
     * @return Timer
     */
    public static Timer timerExecutorTwo(){
        Timer timer = new Timer();
        return timer;
    }

    /**
     * 随机值唯一序列号
     * @return ID
     */
    public static String macUID(){
        StringBuilder macAddress = new StringBuilder();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                byte[] mac = networkInterface.getHardwareAddress();
                if (mac != null) {
                    for (int i = 0; i < mac.length; i++) {
//                        macAddress.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : "|"));
                        macAddress.append(String.format("%02X%s", mac[i], ""));
                    }
                }
            }
//            System.out.println("MacAddress: " + macAddress.toString());
            return macAddress.toString();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 图片转Base64
     * @param is 图片转base64
     * @return Base64
     */
    public static String inputStreamToBase64(InputStream is) {
        byte[] data = null;
        try {
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[100];
            int rc = 0;
            while ((rc = is.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            data = swapStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return Base64.getEncoder().encodeToString(data);
    }


    /**
     * Base64 转 InputStream
     * @param base64Data base64 转图片
     * @return InputStream
     */
    public static InputStream base64ToInputStream(String base64Data) {
        byte[] decodedBytes = Base64.getDecoder().decode(base64Data);
        return new ByteArrayInputStream(decodedBytes);
    }

    /**
     * 返回系统所有的字体
     * @return 返回系统字体
     */
    public static List<String> getFonts(){
        List<String> fontList = new ArrayList<>();
        fontList.clear();
        // 获取本地图形环境
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        // 获取系统中所有可用的字体家族名称
        String[] fontFamilies = ge.getAvailableFontFamilyNames();
        for (String fontFamily : fontFamilies) {
            fontList.add(fontFamily);
        }
        return fontList;
    }

    /**
     * 文件选择器
     * @param filter 文件过滤器
     * @param hwnd 窗口句柄
     * @param title 标题
     * @param initialFilterIndex 初始过滤器索引
     * @param initialDirectory 初始目录
     * @param defaultExtension 默认扩展名
     * @param promptOnCreate 是否提示创建
     * @param disableLinkResolution 禁用链接解析
     * @param dontChangeDirectory 不改变目录
     * @return 文件路径
     */
    public static String OpenFileDialog(
            String filter,
            int hwnd,
            String title,
            int initialFilterIndex,
            String initialDirectory,
            String defaultExtension,
            boolean promptOnCreate,
            boolean disableLinkResolution,
            boolean dontChangeDirectory
    ){
        return ModuleOperationUtilsJNI.OpenFileDialog(filter,hwnd,title,initialFilterIndex,initialDirectory,defaultExtension,promptOnCreate,disableLinkResolution,dontChangeDirectory);
    }

    /**
     * 文件选多选选择器
     * @param filter 文件过滤器
     * @param hwnd 窗口句柄
     * @param title 标题
     * @param initialFilterIndex 初始过滤器索引
     * @param initialDirectory 初始目录
     * @param dontChangeDirectory 不改变目录
     * @return 文件路径
     */
    public static String[] OpenFilesDialog(
            String filter,
            long hwnd,
            String title,
            int initialFilterIndex,
            String initialDirectory,
            boolean dontChangeDirectory
    ){
        return ModuleOperationUtilsJNI.OpenFilesDialog(filter,hwnd,title,initialFilterIndex,initialDirectory,dontChangeDirectory);
    }

    /**
     * 文件保存选择器
     * @param filter 文件过滤器
     * @param hwnd 窗口句柄
     * @param title 标题
     * @param initialFilterIndex 初始过滤器索引
     * @param initialDirectory 初始目录
     * @param defaultExtension 默认扩展名
     * @param promptOnOverwrite 是否提示覆盖
     * @param dontChangeDirectory 不改变目录
     * @return 文件路径
     */
    public static String SaveFileDialog(
            String filter,
            long hwnd,
            String title,
            int initialFilterIndex,
            String initialDirectory,
            String defaultExtension,
            boolean promptOnOverwrite,
            boolean dontChangeDirectory
    ){
        return ModuleOperationUtilsJNI.SaveFileDialog(filter,hwnd,title,initialFilterIndex,initialDirectory,defaultExtension,promptOnOverwrite,dontChangeDirectory);
    }

    /**
     * 文件夹选择器
     * @param hwnd 窗口句柄
     * @param title 标题
     * @param buttonLabel 按钮标签
     * @return 文件夹路径
     */
    public static String DirectoryDialog(
            long hwnd,
            String title,
            String buttonLabel
    ){
        return ModuleOperationUtilsJNI.DirectoryDialog(hwnd,title,buttonLabel);
    }

    /**
     * 读取资源文件为字节数组
     * @param resourcePath 资源路径
     * @return 字节数组
     *
     */
    public static byte[] readResourceToBytes(String resourcePath) {
        // 1. 获取资源输入流（优先使用ClassLoader，避免类路径问题）
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(resourcePath);
        if (inputStream == null) {
            return null;
        }

        // 2. 缓冲流读取，提升效率
        try (BufferedInputStream bis = new BufferedInputStream(inputStream);
             // 3. 字节数组输出流，无需预设大小，自动扩容
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024]; // 缓冲区大小，可根据资源大小调整（如4096）
            int readLength; // 每次实际读取的字节数

            // 4. 循环读取流内容到缓冲区，再写入字节数组输出流
            while ((readLength = bis.read(buffer)) != -1) {
                baos.write(buffer, 0, readLength);
            }

            // 5. 将ByteArrayOutputStream转换为byte[]
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取url为字节数组
     * @param urlAddress url地址
     * @return 字节数组
     */
    public static byte[] readUrlToBytes(String urlAddress) {
        // 1. 构建URL对象
        URL url = null;
        try {
            url = new URL(urlAddress);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        URLConnection connection = null;
        try {
            connection = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connection.setConnectTimeout(5000); // 连接超时5秒
        connection.setReadTimeout(10000);   // 读取超时10秒
        try (InputStream inputStream = connection.getInputStream()) {
            // 2. 缓冲流读取，提升效率
            try (BufferedInputStream bis = new BufferedInputStream(inputStream);
                 // 3. 字节数组输出流，无需预设大小，自动扩容
                 ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

                byte[] buffer = new byte[1024]; // 缓冲区大小，可根据资源大小调整（如4096）
                int readLength; // 每次实际读取的字节数

                // 4. 循环读取流内容到缓冲区，再写入字节数组输出流
                while ((readLength = bis.read(buffer)) != -1) {
                    baos.write(buffer, 0, readLength);
                }

                // 5. 将ByteArrayOutputStream转换为byte[]
                return baos.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (Exception e){e.printStackTrace();}
        return null;
    }

    /**
     * 从网络URL读取图片并转换为BufferedImage
     * @param imageUrl 图片的网络URL（如 https://xxx.com/xxx.jpg）
     * @return 转换后的BufferedImage
     *
     */
    public static BufferedImage convertUrlToBufferedImage(String imageUrl) {
        // 1. 构建URL对象
        URL url = null;
        try {
            url = new URL(imageUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        URLConnection connection = null;
        try {
            connection = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connection.setConnectTimeout(5000); // 连接超时5秒
        connection.setReadTimeout(10000);   // 读取超时10秒
        try (InputStream inputStream = connection.getInputStream()) {
            return ImageIO.read(inputStream);
        }catch (Exception e){e.printStackTrace();}
        return null;
    }

    /**
     * 带透明度的正片叠底：上层半透明时，能透出底层，同时保留 Multiply 的暗化效果
     * @param top   上
     * @param bottom  底
     * @return BufferedImage
     */
    public static BufferedImage multiplyBlendWithTransparency(BufferedImage top, BufferedImage bottom) {
        if (top == null || bottom == null) {
            throw new IllegalArgumentException("图像不能为空");
        }

        int w = Math.min(top.getWidth(), bottom.getWidth());
        int h = Math.min(top.getHeight(), bottom.getHeight());
        BufferedImage result = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int topRGB = top.getRGB(x, y);
                int bottomRGB = bottom.getRGB(x, y);

                int aTop = (topRGB >> 24) & 0xFF;
                int rTop = (topRGB >> 16) & 0xFF;
                int gTop = (topRGB >> 8) & 0xFF;
                int bTop = topRGB & 0xFF;

                int aBottom = (bottomRGB >> 24) & 0xFF;
                int rBottom = (bottomRGB >> 16) & 0xFF;
                int gBottom = (bottomRGB >> 8) & 0xFF;
                int bBottom = bottomRGB & 0xFF;

                // 如果上层完全透明，直接使用底层
                if (aTop == 0) {
                    result.setRGB(x, y, bottomRGB);
                    continue;
                }

                // 如果上层完全不透明，执行标准 Multiply
                if (aTop == 255) {
                    int r = (rBottom * rTop) / 255;
                    int g = (gBottom * gTop) / 255;
                    int b = (bBottom * bTop) / 255;
                    int a = Math.max(aTop, aBottom); // 或保留 aBottom
                    result.setRGB(x, y, (a << 24) | (r << 16) | (g << 8) | b);
                    continue;
                }

                // ★ 关键：半透明情况 ★
                // Step 1: 对 RGB 执行 Multiply（假设上层是不透明的）
                int rMul = (rBottom * rTop) / 255;
                int gMul = (gBottom * gTop) / 255;
                int bMul = (bBottom * bTop) / 255;

                // Step 2: 将 Multiply 结果与底层按 Alpha 混合（Over 操作）
                // 公式：result = top * α + bottom * (1 - α)
                float alpha = aTop / 255.0f;
                int r = (int) (rMul * alpha + rBottom * (1 - alpha));
                int g = (int) (gMul * alpha + gBottom * (1 - alpha));
                int b = (int) (bMul * alpha + bBottom * (1 - alpha));
                int a = Math.min(255, aTop + aBottom - (aTop * aBottom) / 255); // 标准 Alpha 合成

                r = Math.min(255, Math.max(0, r));
                g = Math.min(255, Math.max(0, g));
                b = Math.min(255, Math.max(0, b));

                result.setRGB(x, y, (a << 24) | (r << 16) | (g << 8) | b);
            }
        }
        return result;
    }

}

