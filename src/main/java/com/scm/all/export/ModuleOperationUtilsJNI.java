package com.scm.all.export;

import com.scm.all.pfunc.*;
import com.scm.all.pfunc.Nonvirtual.*;
import com.scm.all.struct.*;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * 公开JNI接口方便用户进行调用
 */
public class ModuleOperationUtilsJNI {
    static {
        if (systemIs32()) {
            System.load(tileDir() + "Jscm.dll");
        }
        if (systemIs64()) {
            System.load(tileDir() + "Jscm.dll");
        }
    }

    /**
     * TempPath 取Temp系统目录
     *
     * @return 返回结果
     */
    private static String tempPath() {
        return System.getProperty("java.io.tmpdir");
    }

    /**
     * @return 是 32位返回 true
     */
    public static boolean systemIs32() {
        String arch = System.getProperty("sun.arch.data.model");
        return StringUtils.equals(arch, "32");
    }

    /**
     * @return 是 64位返回 true
     */
    public static boolean systemIs64() {
        String arch = System.getProperty("sun.arch.data.model");
        return StringUtils.equals(arch, "64");
    }

    /**
     * @return 返回运行文件夹
     */
    public static String tileDir() {
        if (!PathFileJSCM.isIsDebug()) {
            if (StringUtils.equals(PathFileJSCM.getDebugFile32(), "") || PathFileJSCM.getDebugFile32() == null || PathFileJSCM.getDebugFile32().length() == 0 && StringUtils.equals(PathFileJSCM.getDebugFile64(), "") || PathFileJSCM.getDebugFile64() == null || PathFileJSCM.getDebugFile64().length() == 0) {
                String a = System.getProperty("java.class.path");
                if (systemIs32()) {
                    return a.substring(0, a.lastIndexOf("\\")) + "\\x86\\";
                } else {
                    return a.substring(0, a.lastIndexOf("\\")) + "\\x64\\";
                }
            } else {
                if (systemIs32()) {
                    return PathFileJSCM.getDebugFile32();
                } else {
                    return PathFileJSCM.getDebugFile64();
                }
            }
        } else {
            if (StringUtils.equals(PathFileJSCM.getDebugFile32(), "") || PathFileJSCM.getDebugFile32() == null || PathFileJSCM.getDebugFile32().length() == 0 && StringUtils.equals(PathFileJSCM.getDebugFile64(), "") || PathFileJSCM.getDebugFile64() == null || PathFileJSCM.getDebugFile64().length() == 0) {
                if (systemIs32()) {
                    return tempPath() + "x86\\";
                } else {
                    return tempPath() + "x64\\";
                }
            } else {
                if (systemIs32()) {
                    return PathFileJSCM.getDebugFile32();
                } else {
                    return PathFileJSCM.getDebugFile64();
                }
            }
        }
    }
    /**
     * 仅获取java.exe所在的目录（bin目录）
     * @return bin目录路径（如：C:\Program Files\Java\dragonwell-8.19.20\bin）
     */
    private static String getJavaExeDir() {
        String javaExePath = getJavaExePath();
        return new File(javaExePath).getParentFile().getAbsolutePath();
    }
    /**
     * 获取当前JVM的java.exe完整路径（跨平台）
     * @return java.exe/java的绝对路径（如：C:\Program Files\Java\dragonwell-8.19.20\bin\java.exe）
     */
    private static String getJavaExePath() {
        // 1. 获取JRE安装目录（java.home）
        String javaHome = System.getProperty("java.home");
        // 2. 拼接bin目录 + java可执行文件（区分系统）
        File javaExeFile = new File(new File(javaHome, "bin"), "java.exe");
        // 3. 返回标准化绝对路径
        return javaExeFile.getAbsolutePath();
    }
    /**
     * 初始化从JAR包中复制DLL文件
     * @return 返回结果
     */
    public static boolean initOutFile() {
        try {
            java.nio.file.Path targetPath = Paths.get(tileDir()+"\\Jscm.dll");
            Files.createDirectories(targetPath.getParent());
            InputStream inputStream = null;
            if (systemIs32()) {
                inputStream = ModuleOperationUtilsJNI.class.getClassLoader().getResourceAsStream("init_/x86/Jscm.dll");

            }else {
                inputStream = ModuleOperationUtilsJNI.class.getClassLoader().getResourceAsStream("init_/x64/Jscm.dll");
            }

            if (inputStream == null) {
                return false;
            }

            Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    //====================================================================================================================================
    public static native boolean WritePrivateProfile(String filePath, String sectionName, String ConfigurationItemName, String dataValue);

    public static native String ReadPrivateProfile(String filePath, String sectionName, String ConfigurationItemName);

    public static native int CreateToolhelp32Snapshot(int dwFlags, int th32ProcessID);

    public static native boolean Process32FirstW(int hSnapshot, TagProcessenTry32 pEntry32);

    public static native boolean Process32NextW(int hSnapshot, TagProcessenTry32 pEntry32);

    public static native int CloseHandle(int hObject);

    public static native int OpenProcess(int dwDesiredAccess, int bInheritHandle, int dwProcessId);

    public static native int GetCurrentProcess();

    public static native int TerminateProcess(int hProcess, int uExitCode);

    public static native int GetWindow(int hWnd, int uCmd);

    public static native int GetDesktopWindow();

    public static native int IsWindowVisible(int hWnd);

    public static native int GetWindowThreadId(int hWnd); //线程ID

    public static native int GetWindowProcessId(int hWnd); //进程ID

    public static native int GetCurrentProcessId();

    public static native boolean ZwSuspendProcess(int hProcess);

    public static native boolean ZwResumeProcess(int hProcess);

    public static native boolean Thread32First(int hSnapshot, TagThreadEntry32 pEntry32);

    public static native boolean Thread32Next(int hSnapshot, TagThreadEntry32 pEntry32);

    public static native int OpenThread(int dwDesiredAccess, int dwThreadId);

    public static native int GetCurrentThreadId();

    public static native int GetExitCodeThread(int hThread); //返回 dwExitCode 指向变量以接收线程终止状态的指针。有关详细信息，请参阅备注。

    public static native int TerminateThread(int hThread, int dwExitCode);

    public static native int SuspendThread(int hThread);

    public static native int ResumeThread(int hThread);

    public static native int OpenProcessToken(int ProcessHandle, int DesiredAccess);//返回值是 TokenHandle

    public static native boolean LookupPrivilegeValueW(String lpSystemName, String lpName, TagLuid lpLuid);

    public static native boolean AdjustTokenPrivileges(int TokenHandle, boolean DisableAllPrivileges, TagTokenPrivileges NewState);

    public static native boolean Module32FirstW(int hSnapshot, TagModuleenTry32 pEntry32);

    public static native boolean Module32NextW(int hSnapshot, TagModuleenTry32 pEntry32);

    public static native int MyEnumProcessModulesEx(int hProcess);

    public static native int MyModuleFileNameExW(int len, char[] bydat, int none);

    public static native long MyModuleFileNameExW(int len, char[] bydat, long none);

    public static native int GetSystemDirectoryW(char[] lpBuffer, long dwSize);

    //====================================================================================================================================
    public static native int GetWindowsDirectoryW(char[] lpBuffer, int uSize);

    public static native int MessageBoxExW(int hWnd, String lpText, String lpCaption, int uType, int wLanguageId);

    public static native int GetAsyncKeyState(int vkey);

    public static native boolean InvalidateRect(int hWnd, TagRect lpRect, boolean bErase);

    public static native long SetWindowLongW(int hWnd, int nIndex, long dwNewLong);

    public static native int SendMessageTimeoutW(int hWnd, int Msg, int wParam, int lParam, int fuFlags, int uTimeout, byte[] lpdwResult);

    public static native boolean GetCursorPos(TagPoint lpPoint);

    public static native int FindWindowW(String lpClassName, String lpWindowName);

    public static native boolean PostMessageW(int hWnd, int Msg, byte[] wParam, byte[] lParam);

    public static native boolean EnumWindows(JscmCallBack callback, int lParam);

    public static native int FindWindowExW(int hWndParent, int hWndChildAfter, String lpszClass, String lpszWindow);

    public static native int ShowWindow(int hWnd, int nCmdShow);

    public static native int SetParent(int hWndChild, int hWndNewParent);

    public static native int GetWindowRect(int hWnd, TagRect lpRect);

    public static native boolean IsChild(int hWndParent,int hWndChild);
    public static native int MoveWindow(int hWnd, int x, int y, int nWidth, int nHeight, boolean bRepaint);

    public static native int GetDlgCtrlID(int hWnd);

    public static native int GetDlgItem(int hDlg, int nIDDlgItem);

    public static native boolean SetLayeredWindowAttributes(int hwnd, int crKey, int bAlpha, int dwFlags);

    public static native boolean AttachThreadInput(int idAttach, int idAttachTo, boolean fAttach);

    public static native int SetActiveWindow(int hWnd);

    public static native boolean OpenIcon(int hWnd);

    public static native boolean SetWindowPos(int hWnd, int hWndInsertAfter, int x, int y, int cx, int cy, int uFlags);

    public static native int GetDC(int hWnd);
    public static native int SelectObject(int hdc,int h);
    public static native int CreateCompatibleBitmap(int hdc, int cx, int cy);
    public static native int CreateCompatibleDC(int hdc);
    public static native int DrawTextW(int hdc, String lpchText, int cchText, TagRect lprc, int format);

    public static native int ReleaseDC(int hWnd, int hDC);

    public static native int GetPropW(int hWnd, String lpString);

    public static native int RemovePropW(int hWnd, String lpString);

    public static native int GetParent(int hWnd);

    public static native int GetWindowLongW(int hWnd, int nIndex);

    public static native int SetPropW(int hWnd, String lpString, int hData);

    public static native int GetFocus();

    public static native int ClientToScreen(int hWnd, TagPoint tagPOINT);

    public static native int SetForegroundWindow(int hWnd);

    public static native int GetWindowTextLengthW(int hWnd);

    public static native int GetWindowTextW(int hWnd, char[] lpString, int nMaxCount);

    public static native int SetWindowTextW(int hWnd, String lpString);

    public static native int GetClassNameW(int hWnd, char[] lpClassName, int nMaxCount);

    public static native int GetSystemMetrics(int nIndex);

    public static native int CloseWindow(int hWnd);

    public static native boolean GetClientRect(int hWnd, TagRect rect);

    public static native int CreateRoundRectRgn(int x1, int y1, int x2, int y2, int w, int h);

    public static native int SetWindowRgn(int hWnd, int hRgn, boolean bRedraw);

    public static native int DeleteObject(int ho);

    public static native int GetAncestor(int hwnd, int gaFlags);

    public static native int ShowWindowAsync(int hWnd, int nCmdShow);

    public static native int GetLayeredWindowAttributes(int hwnd, byte[] pbAlpha);

    public static native int EnableWindow(int hWnd, boolean bEnable);

    public static native int GetSystemMenu(int hWnd, int bRevert);

    public static native int EnableMenuItem(int hMenu, int uIDEnableItem, int uEnable);

    public static native int RedrawWindow(int hWnd, TagRect lpRect, int hrgnUpdate, int flags);

    public static native boolean IsIconic(int hWnd);

    public static native boolean IsZoomed(int hWnd);

    public static native int SendMessageW(int hWnd, int Msg, byte[] wParam, byte[] lParam);

    public static native int SendMessageWInfo(int hWnd, int Msg, int wParam, int lParam);

    public static native boolean ShowScrollBar(int hWnd, int wBar, boolean bShow);

    public static native boolean IsWindow(int hWnd);

    public static native int GetForegroundWindow();

    public static native String tenToHex(long s);

    public static native String tenToHex(int s);

    //====================================================================================================================================
    public static native boolean ScreenToClient(int hWnd, TagPoint lpPoint);
    public static native int MyOpenRegistryGroup(int RegistryGroup, String lpValueName, int IsCreate, int none);//参数最后一个为占位符

    public static native long MyOpenRegistryGroup(int RegistryGroup, String lpValueName, int IsCreate, long none);//参数最后一个为占位符

    public static native int RegCloseKey(int hKey);

    public static native int RegCloseKey(long hKey);

    public static native int RegQueryValueExLengthW(int hKey, String lpValueName);

    public static native int RegQueryValueExLengthW(long hKey, String lpValueName);

    public static native int RegQueryValueExW(int hKey, String lpValueName, int lpReserved, byte[] lpType, byte[] lpData, int lpcbData);

    public static native int RegQueryValueExW(long hKey, String lpValueName, int lpReserved, byte[] lpType, byte[] lpData, int lpcbData);

    public static native int RegEnumKeyW(int hKey, int dwIndex, char[] lpName, int cchName);

    public static native int RegEnumKeyW(long hKey, int dwIndex, char[] lpName, int cchName);

    public static native int RegQueryInfoKeyW(int hKey, byte[] lpcValues, int DwSize);//句柄 成员   类型 4

    public static native int RegQueryInfoKeyW(long hKey, byte[] lpcValues, int DwSize);//句柄 成员   类型 4

    public static native int MyRegEnumValueWLength(int hkey, int dwIndex);

    public static native int MyRegEnumValueWLength(long hkey, int dwIndex);

    public static native int MyRegEnumValueW(int hkey, int dwIndex, byte[] lpValueName, byte[] lpType, int lpData);

    public static native int MyRegEnumValueW(long hkey, int dwIndex, byte[] lpValueName, byte[] lpType, int lpData);

    public static native int RegFlushKey(int hKey);

    public static native int RegFlushKey(long hKey);

    public static native int RegSetValueExW(int hKey, String lpValueName, int dwType, byte[] lpData, int cbData);

    public static native int RegSetValueExW(long hKey, String lpValueName, int dwType, byte[] lpData, int cbData);

    public static native int RegDeleteValueW(int hKey, String lpSubKey);

    public static native int RegDeleteValueW(long hKey, String lpSubKey);

    public static native int RegDeleteKeyW(int hKey, String lpSubKey);

    public static native int RegDeleteKeyW(long hKey, String lpSubKey);

    //====================================================================================================================================
    public static native int MyVirtualQueryEx(int hProcess, int lpAddress, TagPmemory_Basic_InfoRmationX86 pmemory_basic_information, int bit);

    public static native int MyVirtualQueryEx(int hProcess, long lpAddress, TagPmemory_Basic_InfoRmationX64 pmemory_basic_information, int bit);

    public static native int MyVirtualProtectEx(int hProcess, int dwSize, int flNewProtect);

    public static native int MyVirtualProtectEx(int hProcess, long handle, long dwSize, int flNewProtect);

    public static native int MyReadProcessMemory(int hProcess, int lpBaseAddress, int dwSize, byte[] Buffer);

    public static native int MyReadProcessMemory(int hProcess, long lpBaseAddress, long dwSize, byte[] Buffer);

    public static native int MyWriteProcessMemory(int hProcess, int lpBaseAddress, int dwSize, byte[] lpBuffer);

    public static native int MyWriteProcessMemory(int hProcess, long lpBaseAddress, int DwSize, byte[] lpBuffer);

    public static native int VirtualAllocEx(int hProcess, int lpBaseAddress, int dwSize, int flAllocationType, int flProtect);

    public static native long VirtualAllocEx(int hProcess, long lpBaseAddress, int dwSize, int flAllocationType, int flProtect);

    public static native boolean VirtualFreeEx(int hProcess, int lpBaseAddress);

    public static native boolean VirtualFreeEx(int hProcess, long lpBaseAddress);

    //====================================================================================================================================
    public static native boolean Install_DD_VIP(String Path, String OutKey);

    public static native boolean UnInstall_DD_VIP(String Path);

    public static native void Load_DD_Address(String Path, int bit);

    public static native int Init_DD(int bit);

    public static native int DDBtn(int btn);

    public static native int DDMov(int x, int y);

    public static native int DDMovR(int dx, int dy);

    public static native int DDWhl(int whl);

    public static native int DDkey(int ddcode, int flag);

    public static native int DDstr(String str);

    //====================================================================================================================================
    public static native boolean Install_Ghost_VIP(String Path, String OutKey);

    public static native boolean UnInstall_Ghost_VIP(String Path);

    public static native void Load_Ghost_Address(String Path, int bit);

    public static native String GetDeviceListByModel();

    public static native int SelectDeviceBySerialNumber(String SerialNumber);

    public static native int IsDeviceConnected();

    public static native String GetModel();

    public static native String GetSerialNumber();

    public static native String GetFirmwareVersion();

    public static native int PressKeyByName(String KeyName);

    public static native int PressKeyByValue(int KeyValue);

    public static native int ReleaseKeyByName(String KeyName);

    public static native int ReleaseKeyByValue(int KeyValue);

    public static native int PressAndReleaseKeyByName(String KeyName);

    public static native int PressAndReleaseKeyByValue(int KeyValue);

    public static native int IsKeyPressedByName(String KeyName);

    public static native int IsKeyPressedByValue(int KeyValue);

    public static native int ReleaseAllKey();

    public static native int InputString(String Str);

    public static native int GetCapsLock();

    public static native int GetNumLock();

    public static native int SetPressKeyDelay(int MinDelay, int MaxDelay);

    public static native int SetInputStringIntervalTime(int MinDelay, int MaxDelay);

    public static native int SetCaseSensitive(int Discriminate);

    public static native int PressMouseButton(int mButton);

    public static native int ReleaseMouseButton(int mButton);

    public static native int PressAndReleaseMouseButton(int mButton);

    public static native int IsMouseButtonPressed(int mButton);

    public static native int ReleaseAllMouseButton();

    public static native int MoveMouseTo(int X, int Y);

    public static native int MoveMouseRelative(int X, int Y);

    public static native int MoveMouseWheel(int Z);

    public static native int GetMouseX();

    public static native int GetMouseY();

    public static native int SetMousePosition(int X, int Y);

    public static native int SetPressMouseButtonDelay(int MinDelay, int MaxDelay);

    public static native int SetMouseMovementDelay(int MinDelay, int MaxDelay);

    public static native int SetMouseMovementSpeed(int SpeedValue);

    //====================================================================================================================================
    public static native boolean Install_Md_VIP(String Path, String OutKey);

    public static native boolean UnInstall_Md_VIP(String Path);

    public static native void Load_Md_Address(String Path, int bit);

    public static native void M_Open(int Nbr);

    public static native void M_Open_VidPid_(int Vid, int Pid);

    public static native void M_ScanAndOpen();

    public static native int M_Close();

    public static native int M_Delay(int time);

    public static native int M_DelayRandom(int Min_time, int Max_time);

    public static native int M_RandDomNbr(int Min_V, int Max_V);

    public static native String M_GetDevSn();

    public static native int M_GetAbsCfg();

    public static native int M_SetAbsCfg(int e1d0);

    public static native int M_ChkSupportMdy();

    public static native int M_SetNewVidPid(int mVid, int mPid, int sVid, int sPid);

    public static native int M_ResetVidPid();

    public static native int M_GetVidPid(int IdIndex);

    public static native int M_ReEnum();

    public static native int M_ChkSupportProdStrMdy();

    public static native String M_GetProdStr(int m0s1);

    public static native int M_SetProdStr(int m0s1, String ucp_Str);

    public static native int M_InitParam();

    public static native int M_SetParam(int ParamType, int Param1, int Param2);

    public static native int M_SetUserData(int dw_LenUserData, byte[] ucp_UserData);

    public static native int M_VerifyUserData(int dw_LenUserData, byte[] ucp_UserData);

    public static native int M_KeyPress(int HidKeyCode, int Nbr);

    public static native int M_KeyDown(int HidKeyCode);

    public static native int M_KeyUp(int HidKeyCode);

    public static native int M_KeyState(int HidKeyCode);

    public static native int M_KeyPress2(int KeyCode, int Nbr);

    public static native int M_KeyDown2(int KeyCode);

    public static native int M_KeyUp2(int KeyCode);

    public static native int M_KeyState2(int KeyCode);

    public static native int M_ReleaseAllKey();

    public static native int M_NumLockLedState();

    public static native int M_CapsLockLedState();

    public static native int M_ScrollLockLedState();

    public static native int M_KeyInputString(String InputStr);//Ascii

    public static native int M_KeyInputStringGBK(String InputStr);//GBK

    public static native int M_KeyInputStringUnicode(String InputStr);//Unicode

    public static native int M_LeftClick(int Nbr);

    public static native int M_LeftDoubleClick(int Nbr);

    public static native int M_LeftDown();

    public static native int M_LeftUp();

    public static native int M_RightClick(int Nbr);

    public static native int M_RightDown();

    public static native int M_RightUp();

    public static native int M_MiddleClick(int Nbr);

    public static native int M_MiddleDown();

    public static native int M_MiddleUp();

    public static native int M_ReleaseAllMouse();

    public static native int M_MouseKeyState(int MouseKeyCode);

    public static native int M_MouseWheel(int Nbr);

    public static native int M_ResetMousePos();

    public static native int M_MoveR(int x, int y);

    public static native int M_MoveR_D(int x, int y);

    public static native int M_MoveTo(int x, int y);

    public static native int M_MoveTo_D(int x, int y);

    public static native int M_GetCurrMousePos(byte[] x, byte[] y);

    public static native int M_GetCurrMousePosX();

    public static native int M_GetCurrMousePosY();

    public static native int M_MoveR2(int x, int y);

    public static native int M_MoveR2_D(int x, int y);

    public static native int M_MoveTo2(int x, int y);

    public static native int M_MoveTo2_D(int x, int y);

    public static native int M_MoveTo3(int x, int y);

    public static native int M_MoveTo3_D(int x, int y);

    public static native int M_GetCurrMousePos2(byte[] x, byte[] y);

    public static native int M_ResolutionUsed(int x, int y);

    //    ==================================================================================================================
    public static native int CapStone_cs_option(int cs_opt_type, int lens);

    public static native int CapStone_cs_open(int arch, int mode);

    public static native int CapStone_cs_disasm(byte[] pOpCode, int pAddr, Tag_cs_insnCallBack tag_cs_insnCallBack);

    public static native int CapStone_cs_disasm(byte[] pOpCode, long pAddr, Tag_cs_insnCallBack tag_cs_insnCallBack);

    public static native void CapStone_cs_free();

    //    ==================================================================================================================
    public static native boolean NtQueryAllProcessInfoW_Process(NtQueryAllProcessInfoCallBackProcess callBack);

    public static native boolean NtQueryAllProcessInfoW_Thread(NtQueryAllProcessInfoCallBackThread callBack);

    //    ==================================================================================================================
    public static native int SendInput(int x, int y, int keyType, int valueType);

    public static native int MAKELPARAM(int x, int y);

    //    ==================================================================================================================
    public static native void SetWindowsHookEx(KeyCodeCallBack callBack);

    public static native boolean UnhookWindowsHookEx();

    public static native void MessageRun();

    //    ==================================================================================================================
    public static native boolean IsSubjoin();

    public static native int GetMainAddress(int none);

    public static native long GetMainAddress(long none);

    public static native void SetSuspend();

    public static native void SetContinue();

    public static native void SetUnInstall();

    public static native boolean SetInstall(String filename, String address1, int address2);

    public static native boolean SetInstall(String filename, String address1, long address2);
    public static native boolean SetParentWithIndependentRender(int parentHWND,int javaHWND);

    public static native void UpdateChildWindowPos(int hParent,int hChild,int right_Bottom_XOffset,int right_Bottom_YOffset);

    public static native String OpenFileDialog(
            String filter,
            int hwnd,
            String title,
            int initialFilterIndex,
            String initialDirectory,
            String defaultExtension,
            boolean promptOnCreate,
            boolean disableLinkResolution,
            boolean dontChangeDirectory
    );

    public static native String[] OpenFilesDialog(
            String filter,
            long hwnd,
            String title,
            int initialFilterIndex,
            String initialDirectory,
            boolean dontChangeDirectory
    );

    public static native String SaveFileDialog(
            String filter,
            long hwnd,
            String title,
            int initialFilterIndex,
            String initialDirectory,
            String defaultExtension,
            boolean promptOnOverwrite,
            boolean dontChangeDirectory
    );

    public static native String DirectoryDialog(
            long hwnd,
            String title,
            String buttonLabel
    );

    //    ==================================================================================================================

//    这是炫彩的开发文档
//    炫彩界面库  http://8.138.59.98/xcgui/index.html
    /**
     * 窗口_创建
     * @param x 坐标
     * @param y 坐标
     * @param cx 坐标宽度
     * @param cy 坐标高度
     * @param pTitle 标题
     * @param hWndParent=NULL
     * @param XCStyle=window_style_default
     * @return 返回结果
     **/
    public static native int XWnd_Create(int x, int y, int cx, int cy, String pTitle, int hWndParent, int XCStyle);
    /**
     * 窗口_调整布局扩展
     * @param hWindow 窗口句柄
     * @param nFlags=adjustLayout_self
     **/
    public static native void XWnd_AdjustLayoutEx(int hWindow, int nFlags);
    /**
     * 窗口_调整布局
     * @param hWindow 窗口句柄
     **/
    public static native void XWnd_AdjustLayout(int hWindow);
    /**
     * 炫彩_初始化
     * @param bD2D 是否开启
     * @return 返回结果
     **/
    public static native boolean XInitXCGUI(boolean bD2D);
    /**
     * 炫彩_运行
     **/
    public static native void XRunXCGUI();

    /**
     * 炫彩_退出
     **/
    public static native void XExitXCGUI();

    /**
     * 窗口_显示扩展
     * @param hWindow 窗口句柄
     * @param nCmdShow 参见MSDN
     *
     **/
    public static native void XWnd_ShowWindow(int hWindow, int nCmdShow);
    /**
     * 窗口_注册事件C1
     * @param hWindow 窗口句柄
     * @param nEvent 事件类型
     * @param windowEventCallBack 回调方法
     * @return 返回结果
     **/

    public static boolean XWnd_RegEventC1_(int hWindow, int nEvent,Object windowEventCallBack){
        return XWnd_RegEventC1(hWindow, nEvent,OverrideMethodUtils.getOverrideMethodNamesStr(windowEventCallBack.getClass()),windowEventCallBack);
    }
    /**
     * 窗口_注册事件C1
     * @param hWindow 窗口句柄
     * @param nEvent 事件类型
     * @param windowEventCallBackName 回调方法名称  这个是唯一值 防止串口回调覆盖
     * @param windowEventCallBack 回调方法
     * @return 返回结果
     **/
    public static native boolean XWnd_RegEventC1(int hWindow, int nEvent,String windowEventCallBackName, Object windowEventCallBack);

    /**
     * 元素_注册事件C1
     * @param hEle 元素句柄
     * @param nEvent 事件类型
     * @param xEleEventCallBack 回调方法
     * @return 返回结果
     **/
    public static boolean XEle_RegEventC1_(int hEle, int nEvent,Object xEleEventCallBack){
        return XEle_RegEventC1(hEle, nEvent,OverrideMethodUtils.getOverrideMethodNamesStr(xEleEventCallBack.getClass()),xEleEventCallBack);
    }
    /**
     * 元素_注册事件C1
     * @param hEle 元素句柄
     * @param nEvent 事件类型
     * @param xEleEventCallBackName 回调方法名称  这个是唯一值 防止串口回调覆盖
     * @param xEleEventCallBack 回调方法
     * @return 返回结果
     **/
    public static native boolean XEle_RegEventC1(int hEle, int nEvent,String xEleEventCallBackName, Object xEleEventCallBack);
    /**
     * 窗口_置定时器
     * @param hWindow 窗口句柄
     * @param nIDEvent 定时器ID
     * @param uElapse 延时毫秒
     * @return 返回结果
     **/
    public static native int XWnd_SetTimer(int hWindow, int nIDEvent, int uElapse);
    /**
     * 窗口_启用拖放文件
     * @param hWindow 窗口句柄
     * @param bEnable 是否启用
     * @return 返回结果
     **/
    public static native boolean XWnd_EnableDragFiles(int hWindow, boolean bEnable);

    /**
     * 按钮_创建
     * @param x 坐标
     * @param y 坐标
     * @param cx 坐标宽度
     * @param cy 坐标高度
     * @param pName 名称
     * @param hParent 父窗口句柄
     * @return 返回结果
     **/
    public static native int XBtn_Create(int x, int y, int cx, int cy, String pName, int hParent);
    /**
     * 按钮_置选中
     * @param hEle 元素句柄
     * @param bCheck 选中
     * @return 返回结果
     **/
    public static native boolean XBtn_SetCheck(int hEle, boolean bCheck);
    /**
     * 组合框_创建
     * @param x 坐标
     * @param y 坐标
     * @param cx 坐标宽度
     * @param cy 坐标高度
     * @param hParent 父窗口句柄
     * @return 返回结果
     **/
    public static native int XComboBox_Create(int x, int y, int cx, int cy, int hParent);
    /**
     * 组合框_添加项图片
     * @param hEle 元素句柄
     * @param hImage 图片句柄
     * @return 返回结果
     **/
    public static native int XComboBox_AddItemImage(int hEle, int hImage);
    /**
     * 组合框_添加项图片扩展
     * @param hEle 元素句柄
     * @param pName 名称
     * @param hImage 图片句柄
     * @return 返回结果
     **/
    public static native int XComboBox_AddItemImageEx(int hEle, String pName, int hImage);
    /**
     * 组合框_添加项文本
     * @param hEle 元素句柄
     * @param pText 文本内容
     * @return 返回结果
     **/
    public static native int XComboBox_AddItemText(int hEle, String pText);
    /**
     * 组合框_添加项文本扩展
     * @param hEle 元素句柄
     * @param pName 名称
     * @param pText 文本内容
     * @return 返回结果
     **/
    public static native int XComboBox_AddItemTextEx(int hEle, String pName, String pText);
    /**
     * 组合框_删除列全部
     * @param hEle 元素句柄
     **/
    public static native void XComboBox_DeleteColumnAll(int hEle);
    /**
     删除组合框中的指定项
     @param hEle 组合框元素句柄
     @param iItem 项索引
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XComboBox_DeleteItem (int hEle, int iItem);
    /**
     删除组合框中的所有项
     @param hEle 组合框元素句柄
     */
    public static native void XComboBox_DeleteItemAll (int hEle);
    /**
     从指定位置开始删除组合框中的多项
     @param hEle 组合框元素句柄
     @param iItem 起始项索引
     @param nCount 要删除的项数量
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XComboBox_DeleteItemEx (int hEle, int iItem, int nCount);
    /**
     设置组合框指定项的文本内容（根据字段名）
     @param hEle 组合框元素句柄
     @param iItem 项索引
     @param pName 字段名
     @param pText 文本内容
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XComboBox_SetItemTextEx (int hEle, int iItem, String pName, String pText);
    /**
     设置组合框指定项的文本内容（根据列索引）
     @param hEle 组合框元素句柄
     @param iItem 项索引
     @param iColumn 列索引
     @param pText 文本内容
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XComboBox_SetItemText (int hEle, int iItem, int iColumn, String pText);
    /**
     为组合框创建数据适配器
     @param hEle 组合框元素句柄
     @return 数据适配器句柄
     */
    public static native int XComboBox_CreateAdapter (int hEle);
    /**
     获取组合框绑定的数据适配器
     @param hEle 组合框元素句柄
     @return 数据适配器句柄
     */
    public static native int XComboBox_GetAdapter (int hEle);
    /**
     为组合框绑定数据适配器
     @param hEle 组合框元素句柄
     @param hAdapter 数据适配器句柄
     */
    public static native void XComboBox_BindAdapter (int hEle, int hAdapter);
    /**
     在组合框指定位置插入图片项（根据字段名）
     @param hEle 组合框元素句柄
     @param iItem 插入位置索引
     @param pName 字段名
     @param hImage 图片句柄
     @return 新插入项的索引
     */
    public static native int XComboBox_InsertItemImageEx (int hEle, int iItem, String pName, int hImage);
    /**
     在组合框指定位置插入图片项（默认列）
     @param hEle 组合框元素句柄
     @param iItem 插入位置索引
     @param hImage 图片句柄
     @return 新插入项的索引
     */
    public static native int XComboBox_InsertItemImage (int hEle, int iItem, int hImage);
    /**
     在组合框指定位置插入文本项（默认列）
     @param hEle 组合框元素句柄
     @param iItem 插入位置索引
     @param pValue 文本内容
     @return 新插入项的索引
     */
    public static native int XComboBox_InsertItemText (int hEle, int iItem, String pValue);
    /**
     在组合框指定位置插入文本项（根据字段名）
     @param hEle 组合框元素句柄
     @param iItem 插入位置索引
     @param pName 字段名
     @param pValue 文本内容
     @return 新插入项的索引
     */
    public static native int XComboBox_InsertItemTextEx (int hEle, int iItem, String pName, String pValue);
    /**
     创建日期时间元素
     @param x x 坐标
     @param y y 坐标
     @param cx 宽度
     @param cy 高度
     @param hParent 父对象句柄（窗口或元素）
     @return 日期时间元素句柄
     */
    public static native int XDateTime_Create (int x, int y, int cx, int cy, int hParent);
    /**
     设置日期时间元素的分割符为斜线或横线
     @param hEle 日期时间元素句柄
     @param bSlash true 为斜线，false 为横线
     */
    public static native void XDateTime_EnableSplitSlash (int hEle, boolean bSlash);
    /**
     销毁元素
     @param hEle 元素句柄
     */
    public static native void XEle_Destroy (int hEle);
    /**
     移除窗口的指定 C 方式事件函数
     @param hWindow 窗口句柄
     @param windowEventCallBackName 事件函数名称
     @param nEvent 事件类型
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XWnd_RemoveEventC (int hWindow,String windowEventCallBackName, int nEvent);
    /**
     移除元素的指定 C 方式事件函数
     @param hEle 元素句柄
     @param xEleEventCallBackName 事件函数名称
     @param nEvent 事件类型
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XEle_RemoveEventC (int hEle,String xEleEventCallBackName, int nEvent);
    /**
     创建编辑框元素
     @param x x 坐标
     @param y y 坐标
     @param cx 宽度
     @param cy 高度
     @param hParent 父对象句柄（窗口或元素）
     @return 编辑框元素句柄
     */
    public static native int XEdit_Create (int x, int y, int cx, int cy, int hParent);
    /**
     设置编辑框选择文本的背景颜色
     @param hEle 编辑框元素句柄
     @param color 颜色值（使用 RGBA 宏定义）
     */
    public static native void XEdit_SetSelectBkColor (int hEle, long color);
    /**
     设置编辑框默认文本的颜色
     @param hEle 编辑框元素句柄
     @param color 颜色值（使用 RGBA 宏定义）
     */
    public static native void XEdit_SetDefaultTextColor (int hEle, long color);
    /**
     设置编辑框插入符的颜色
     @param hEle 编辑框元素句柄
     @param color 颜色值（使用 RGBA 宏定义）
     */
    public static native void XEdit_SetCaretColor (int hEle, long color);
    /**
     创建列表框元素
     @param x x 坐标
     @param y y 坐标
     @param cx 宽度
     @param cy 高度
     @param hParent 父对象句柄（窗口或元素）
     @return 列表框元素句柄
     */
    public static native int XListBox_Create (int x, int y, int cx, int cy, int hParent);
    /**
     为列表框创建数据适配器
     @param hEle 列表框元素句柄
     @return 数据适配器句柄
     */
    public static native int XListBox_CreateAdapter (int hEle);
    /**
     创建列表框元素（使用内置项模板并自动创建数据适配器）
     @param x x 坐标
     @param y y 坐标
     @param cx 宽度
     @param cy 高度
     @param hParent 父对象句柄（窗口或元素）
     @return 列表框元素句柄
     */
    public static native int XListBox_CreateEx (int x, int y, int cx, int cy, int hParent);
    /**
     删除列表框的所有列
     @param hEle 列表框元素句柄
     */
    public static native void XListBox_DeleteColumnAll (int hEle);
    /**
     列表框_删除项
     @param hEle 元素句柄
     @param iItem 项索引
     @return 成功返回 TRUE, 否则返回 FALSE
     **/
    public static native boolean XListBox_DeleteItem (int hEle, int iItem);
    /**
     列表框_删除全部项
     @param hEle 元素句柄
     **/
    public static native void XListBox_DeleteItemAll (int hEle);
    /**
     列表框_删除项扩展
     @param hEle 元素句柄
     @param iItem 行起始索引
     @param nCount 删除行数量
     @return 成功返回 TRUE 否则返回 FALSE
     **/
    public static native boolean XListBox_DeleteItemEx (int hEle, int iItem, int nCount);
    /**
     列表框_添加项文本
     @param hEle 元素句柄
     @param pText 值
     @return 返回行索引值
     **/
    public static native int XListBox_AddItemText (int hEle, String pText);
    /**
     列表_创建
     @param x 元素 x 坐标
     @param y 元素 y 坐标
     @param cx 宽度
     @param cy 高度
     @param hParent 父为窗口句柄或元素句柄
     @return 元素句柄
     **/
    public static native int XList_Create (int x, int y, int cx, int cy, int hParent);
    /**
     列表_创建数据适配器
     @param hEle 元素句柄
     @param col_extend_count 列延伸 - 预计列表总列数
     @return 返回适配器句柄
     **/
    public static native int XList_CreateAdapter (int hEle, int col_extend_count);
    /**
     列表_列表头创建数据适配器
     @param hEle 元素句柄
     @return 返回适配器句柄
     **/
    public static native int XList_CreateAdapterHeader (int hEle);
    /**
     列表_创建数据适配器 2
     @param hEle 元素句柄
     @param col_extend_count 列延伸 - 预计列表总列数
     @return 如果成功返回 TRUE, 否则返回 FALSE
     **/
    public static native boolean XList_CreateAdapters (int hEle, int col_extend_count);
    /**
     列表_创建扩展
     @param x 元素 x 坐标
     @param y 元素 y 坐标
     @param cx 宽度
     @param cy 高度
     @param hParent 父为窗口句柄或元素句柄
     @param col_extend_count 列数量
     @return 元素句柄
     **/
    public static native int XList_CreateEx (int x, int y, int cx, int cy, int hParent, int col_extend_count);
    /**
     列表_删除列
     @param hEle 元素句柄
     @param iColumn 列索引
     @return 成功返回 TRUE 否则返回 FALSE
     **/
    public static native boolean XList_DeleteColumn (int hEle, int iColumn);
    /**
     列表_删除列全部
     @param hEle 元素句柄
     **/
    public static native void XList_DeleteColumnAll (int hEle);
    /**
     列表_删除列全部 AD
     @param hEle 元素句柄
     **/
    public static native void XList_DeleteColumnAll_AD (int hEle);
    /**
     列表_删除行
     @param hEle 元素句柄
     @param iRow 行索引
     @return 成功返回 TRUE 否则返回 FALSE
     **/
    public static native boolean XList_DeleteRow (int hEle, int iRow);
    /**
     列表_删除行全部
     @param hEle 元素句柄
     **/
    public static native void XList_DeleteRowAll (int hEle);
    /**
     列表_删除行扩展
     @param hEle 元素句柄
     @param iRow 行起始索引
     @param nCount 删除行数量
     @return 成功返回 TRUE 否则返回 FALSE
     **/
    public static native boolean XList_DeleteRowEx (int hEle, int iRow, int nCount);
    /**
     列表_添加列文本
     @param hEle 元素句柄
     @param nWidth 列宽度
     @param pName 字段称
     @param pText 文本
     @return 返回列索引
     **/
    public static native int XList_AddColumnText (int hEle, int nWidth, String pName, String pText);
    /**
     列表_添加列文本 2
     @param hEle 元素句柄
     @param nWidth 列宽度
     @param pText 标题文本
     @return 返回列索引
     **/
    public static native int XList_AddColumnText2 (int hEle, int nWidth, String pText);
    /**
     列表_添加行文本
     @param hEle 元素句柄
     @param pText 文本
     @return 返回行索引
     **/
    public static native int XList_AddRowText (int hEle, String pText);
    /**
     列表_添加行文本扩展
     @param hEle 元素句柄
     @param pName 字段名
     @param pText 文本
     @return 返回行索引
     **/
    public static native int XList_AddRowTextEx (int hEle, String pName, String pText);
    /**
     列表_插入行文本
     @param hEle 元素句柄
     @param iRow 插入位置行索引
     @param pValue 值
     @return 返回行索引
     **/
    public static native int XList_InsertRowText (int hEle, int iRow, String pValue);
    /**
     列表_插入行文本扩展
     @param hEle 元素句柄
     @param iRow 插入位置行索引
     @param pName 字段称
     @param pValue 值
     @return 返回行索引
     **/
    public static native int XList_InsertRowTextEx (int hEle, int iRow, String pName, String pValue);
    /**
     列表_置项文本
     @param hEle 元素句柄
     @param iRow 行索引
     @param iColumn 在数据适配器中的列索引
     @param pText 文本
     @return 成功返回 TRUE, 否则返回 FALSE
     **/
    public static native boolean XList_SetItemText (int hEle, int iRow, int iColumn, String pText);
    /**
     列表_置项文本扩展
     @param hEle 元素句柄
     @param iRow 行索引
     @param pName 字段称
     @param pText 文本
     @return 成功返回 TRUE, 否则返回 FALSE
     **/
    public static native boolean XList_SetItemTextEx (int hEle, int iRow, String pName, String pText);
    /**
     列表_刷新数据
     @param hEle 元素句柄
     **/
    public static native void XList_RefreshData (int hEle);
    /**
     元素_置用户数据 (字节数组)
     @param hEle 元素句柄
     @param nData 用户数据字节数组
     @return 成功返回 TRUE 否则返回 FALSE
     **/
    public static native int XEle_SetUserData (int hEle, byte [] nData);
    /**
     元素_取用户数据 (字节数组)
     @param hEle 元素句柄
     @param dwSize 要获取的字节大小
     @return 用户数据字节数组
     **/
    public static native byte [] XEle_GetUserData (int hEle, int dwSize);
    /**
     列表_取项数据 (字节数组)
     @param hEle 元素句柄
     @param iRow 行索引
     @param iColumn 列索引
     @param dwSize 要获取的字节大小
     @return 项数据字节数组
     **/
    public static native byte [] XList_GetItemData (int hEle, int iRow, int iColumn, int dwSize);
    /**
     列表_置项数据 (字节数组)
     @param hEle 元素句柄
     @param iRow 行索引
     @param iColumn 列索引
     @param data 项数据字节数组
     @return 成功返回 TRUE 否则返回 FALSE
     **/
    public static native int XList_SetItemData (int hEle, int iRow, int iColumn, byte [] data);
    /**
     列表框_刷新数据
     @param hEle 元素句柄
     **/
    public static native void XListBox_RefreshData (int hEle);
    /**
     元素_重绘
     @param hEle 元素句柄
     @param bImmediate 是否立即重绘
     **/
    public static native void XEle_Redraw (int hEle, boolean bImmediate);
    /**
     窗口_重绘
     @param hWindow 窗口句柄
     @param bUpdate 是否立即重绘
     **/
    public static native void XWnd_Redraw (int hWindow, boolean bUpdate);
    /**
     列表视_创建
     @param x x 坐标
     @param y y 坐标
     @param cx 宽度
     @param cy 高度
     @param hParent 父对象句柄
     @return 元素句柄
     **/
    public static native int XListView_Create (int x, int y, int cx, int cy, int hParent);
    /**
     列表视_创建扩展
     @param x x 坐标
     @param y y 坐标
     @param cx 宽度
     @param cy 高度
     @param hParent 父对象句柄
     @return 元素句柄
     **/
    public static native int XListView_CreateEx (int x, int y, int cx, int cy, int hParent);
    /**
     列表视_添加选择项
     @param hEle 元素句柄
     @param iGroup 组索引
     @param iItem 项索引
     @return 成功返回 TRUE 否则返回 FALSE
     **/
    public static native boolean XListView_AddSelectItem (int hEle, int iGroup, int iItem);
    /**
     列表视_绑定数据适配器
     @param hEle 元素句柄
     @param hAdapter 数据适配器句柄
     **/
    public static native void XListView_BindAdapter (int hEle, int hAdapter);
    /**
     列表视_取消选择项全部
     @param hEle 元素句柄
     **/
    public static native void XListView_CancelSelectAll (int hEle);
    /**
     列表视_创建数据适配器
     @param hEle 元素句柄
     @return 数据适配器句柄
     **/
    public static native int XListView_CreateAdapter (int hEle);
    /**
     列表视_删除全部
     @param hEle 元素句柄
     **/
    public static native void XListView_DeleteAll (int hEle);
    /**
     列表视_删除全部组
     @param hEle 元素句柄
     **/
    public static native void XListView_DeleteAllGroup (int hEle);
    /**
     列表视_删除全部项
     @param hEle 元素句柄
     **/
    public static native void XListView_DeleteAllItem (int hEle);
    /**
     列表视_组删除列
     @param hEle 元素句柄
     @param iColumn 列索引
     **/
    public static native void XListView_DeleteColumnGroup (int hEle, int iColumn);
    /**
     列表视_项删除列
     @param hEle 元素句柄
     @param iColumn 列索引
     **/
    public static native void XListView_DeleteColumnItem (int hEle, int iColumn);
    /**
     列表视_启用多选
     @param hEle 元素句柄
     @param bEnable 是否启用
     **/
    public static native void XListView_EnableMultiSel (int hEle, boolean bEnable);
    /**
     列表视_启用模板复用
     @param hEle 元素句柄
     @param bEnable 是否启用
     **/
    public static native void XListView_EnableTemplateReuse (int hEle, boolean bEnable);
    /**
     列表视_启用虚表
     @param hEle 元素句柄
     @param bEnable 是否启用
     **/
    public static native void XListView_EnableVirtualTable (int hEle, boolean bEnable);
    /**
     列表视_组添加项文本
     @param hEle 元素句柄
     @param pValue 值
     @param iPos 插入位置
     @return 项索引
     **/
    public static native int XListView_Group_AddItemText (int hEle, String pValue, int iPos);
    /**
     列表视_组添加项文本扩展
     @param hEle 元素句柄
     @param pName 字段名称
     @param pValue 值
     @param iPos 插入位置
     @return 项索引
     **/
    public static native int XListView_Group_AddItemTextEx (int hEle, String pName, String pValue, int iPos);
    /**
     列表视_刷新数据
     @param hEle 元素句柄
     **/
    public static native void XListView_RefreshData (int hEle);
    /**
     * 列表视_项_添加项文本
     * @param hEle 列表视图元素句柄
     * @param iGroup 组索引
     * @param pValue 文本值
     * @param iPos 插入位置, -1添加到末尾
     * @return 返回项索引
     */
    public static native int XListView_Item_AddItemText(int hEle, int iGroup, String pValue, int iPos);

    /**
     * 列表视_项_添加项文本扩展
     * @param hEle 列表视图元素句柄
     * @param iGroup 组索引
     * @param pName 字段名称
     * @param pValue 文本值
     * @param iPos 插入位置, -1添加到末尾
     * @return 返回项索引
     */
    public static native int XListView_Item_AddItemTextEx(int hEle, int iGroup, String pName, String pValue, int iPos);

    /**
     * 菜单_创建
     * @return 返回菜单句柄
     */
    public static native int XMenu_Create();

    /**
     * 菜单_关闭
     * @param hMenu 菜单句柄
     */
    public static native void XMenu_CloseMenu(int hMenu);

    /**
     * 菜单_销毁
     * @param hMenu 菜单句柄
     */
    public static native void XMenu_DestroyMenu(int hMenu);

    /**
     * 菜单_添加项
     * @param hMenu 菜单句柄
     * @param nID 项ID
     * @param pText 文本内容
     * @param parentId 父项ID
     * @param nFlags 标识，参考宏定义menu_item_flag_
     */
    public static native void XMenu_AddItem(int hMenu, int nID, String pText, int parentId, int nFlags);

    /**
     * 菜单_弹出
     * @param hMenu 菜单句柄
     * @param hParentWnd 父窗口句柄
     * @param x x坐标
     * @param y y坐标
     * @param hParentEle 父元素句柄，若不为NULL，hParentEle元素将接收菜单消息事件
     * @param nPosition 弹出位置，参考宏定义menu_popup_position_
     * @return 成功返回TRUE否则返回FALSE
     */
    public static native boolean XMenu_Popup(int hMenu, int hParentWnd, int x, int y, int hParentEle, int nPosition);

    /**
     * 菜单_置自动销毁
     * @param hMenu 菜单句柄
     * @param bAuto 是否自动销毁
     */
    public static native void XMenu_SetAutoDestroy(int hMenu, boolean bAuto);

    /**
     * 菜单条_添加按钮
     * @param hEle 菜单条元素句柄
     * @param pText 按钮文本内容
     * @return 返回菜单按钮索引
     */
    public static native int XMenuBar_AddButton(int hEle, String pText);

    /**
     * 菜单条_创建
     * @param x 元素x坐标
     * @param y 元素y坐标
     * @param cx 宽度
     * @param cy 高度
     * @param hParent 父句柄，窗口句柄或UI元素句柄
     * @return 返回菜单条元素句柄
     */
    public static native int XMenuBar_Create(int x, int y, int cx, int cy, int hParent);

    /**
     * 菜单条_删除按钮
     * @param hEle 菜单条元素句柄
     * @param nIndex 菜单条按钮索引
     * @return 成功返回TRUE否则返回FALSE
     */
    public static native boolean XMenuBar_DeleteButton(int hEle, int nIndex);

    /**
     * 菜单条_启用自动宽度
     * @param hEle 菜单条元素句柄
     * @param bEnable 是否启用自动宽度
     */
    public static native void XMenuBar_EnableAutoWidth(int hEle, boolean bEnable);

    /**
     * 菜单条_取菜单按钮
     * @param hEle 菜单条元素句柄
     * @param nIndex 菜单条上菜单按钮的索引
     * @return 返回按钮句柄
     */
    public static native int XMenuBar_GetButton(int hEle, int nIndex);

    /**
     * 菜单条_取菜单
     * @param hEle 菜单条元素句柄
     * @param nIndex 菜单条上菜单按钮的索引
     * @return 返回菜单句柄
     */
    public static native int XMenuBar_GetMenu(int hEle, int nIndex);

    /**
     * 菜单条_取选择项
     * @param hEle 菜单条元素句柄
     * @return 返回菜单条当前选择项索引
     */
    public static native int XMenuBar_GetSelect(int hEle);

    /**
     * 菜单条_置按钮高度
     * @param hEle 菜单条元素句柄
     * @param height 按钮高度
     */
    public static native void XMenuBar_SetButtonHeight(int hEle, int height);

    /**
     * 菜单_启用用户绘制项
     * @param hMenu 菜单句柄
     * @param bEnable 是否启用
     */
    public static native void XMenu_EnableDrawItem(int hMenu, boolean bEnable);

    /**
     * 菜单_启用用户绘制背景
     * @param hMenu 菜单句柄
     * @param bEnable 是否启用
     */
    public static native void XMenu_EnableDrawBackground(int hMenu, boolean bEnable);

    /**
     * 窗口组件_取整数属性
     * @param hXCGUI 窗口组件句柄
     * @return 返回整数属性值
     */
    public static native int XWidget_Getint(int hXCGUI);

    /**
     * 月历_创建
     * @param x 元素x坐标
     * @param y 元素y坐标
     * @param cx 宽度
     * @param cy 高度
     * @param hParent 父句柄，窗口句柄或UI元素句柄
     * @return 返回月历元素句柄
     */
    public static native int XMonthCal_Create(int x, int y, int cx, int cy, int hParent);

    /**
     * 月历_取选择日期
     * @param hEle 月历元素句柄
     * @param dwSize 数组大小
     * @param pnYear 返回年
     * @param pnMonth 返回月
     * @param pnDay 返回日
     */
    public static native void XMonthCal_GetSelDate(int hEle, int dwSize, byte[] pnYear, byte[] pnMonth, byte[] pnDay);

    /**
     * 月历_取当前日期
     * @param hEle 月历元素句柄
     * @param dwSize 数组大小
     * @param pnYear 返回年
     * @param pnMonth 返回月
     * @param pnDay 返回日
     */
    public static native void XMonthCal_GetToday(int hEle, int dwSize, byte[] pnYear, byte[] pnMonth, byte[] pnDay);

    /**
     * 月历_置文本颜色
     * @param hEle 月历元素句柄
     * @param nFlag 1:周六、周日文字颜色; 2:日期文字颜色; 其它周文字颜色使用元素自身颜色
     * @param color 颜色值，使用宏RGBA()
     */
    public static native void XMonthCal_SetTextColor(int hEle, int nFlag, long color);

    /**
     * 月历_置当前日期
     * @param hEle 月历元素句柄
     * @param nYear 年
     * @param nMonth 月
     * @param nDay 日
     */
    public static native void XMonthCal_SetToday(int hEle, int nYear, int nMonth, int nDay);

    /**
     * 元素_启用事件_XE_MOUSEWHEEL
     * @param hEle 元素句柄
     * @param bEnable 是否启用鼠标滚轮事件
     */
    public static native void XEle_EnableEvent_XE_MOUSEWHEEL(int hEle, boolean bEnable);
    /**
     启用或禁用元素绘制完成事件 (XE_PAINT_END)
     @param hEle 元素句柄
     @param bEnable 是否启用
     */
    public static native void XEle_EnableEvent_XE_PAINT_END (int hEle, boolean bEnable);
    /**
     设置元素的工具提示内容
     @param hEle 元素句柄
     @param pText 工具提示内容
     */
    public static native void XEle_SetToolTip (int hEle, String pText);
    /**
     获取背景对象的外间距
     @param hObj 背景对象句柄
     @param pMargin 接收返回的外间距结构体
     */
    public static native void XBkObj_GetMargin (int hObj, TagMarginSize_ pMargin);
    /**
     获取矩形背景对象的圆角大小
     @param hObj 背景对象句柄
     @param pRect 接收返回的圆角大小结构体
     */
    public static native void XBkObj_GetRectRoundAngle (int hObj, TagRect pRect);
    /**
     获取背景对象的文本内容
     @param hObj 背景对象句柄
     @param dwSize 缓冲区大小
     @return 背景对象的文本内容
     */
    public static native byte [] XBkObj_GetText (int hObj, int dwSize);
    /**
     弹出提示框
     @param pText 提示内容
     @param pTitle 提示框标题
     */
    public static native void XC_Alert (String pText, String pTitle);
    /**
     调用 UI 线程执行回调函数 (虚拟调用)
     @param ThreadUICallBack UI 线程回调函数
     @param data 用户自定义数据
     @param dwSize 数据大小
     @return 回调函数返回值
     */
    public static native int XC_CallUiThread (ThreadUICallBack ThreadUICallBack, byte [] data, int dwSize);
    /**
     调用 UI 线程执行回调函数 (非虚拟调用)
     @param ThreadUICallBack UI 线程回调函数
     @param data 用户自定义数据
     @param dwSize 数据大小
     @return 回调函数返回值
     */
    public static native int XC_CallUiThread (ThreadUICallBack_Nonvirtual ThreadUICallBack, byte [] data, int dwSize);
    /**
     组合两个矩形区域
     @param pDest 接收组合后的矩形区域
     @param pSrc1 源矩形区域 1
     @param pSrc2 源矩形区域 2
     */
    public static native void XC_CombineRect (TagRect pDest, TagRect pSrc1, TagRect pSrc2);
    /**
     打印调试信息到文件 (xcgui_debug.txt)
     @param pInfo 调试信息
     */
    public static native void XC_DebugToFileInfo (String pInfo);
    /**
     在 UI 库中释放内存 (32 位指针)
     @param p 内存首地址
     */
    public static native void XC_Free (int p);
    /**
     在 UI 库中释放内存 (64 位指针)
     @param p 内存首地址
     */
    public static native void XC_Free (long p);
    /**
     释放动态库模块 (32 位)
     @param hModule 动态库模块句柄
     @return 成功返回 true, 否则返回 false
     */
    public static native boolean XC_FreeLibrary (int hModule);
    /**
     释放动态库模块 (64 位)
     @param hModule 动态库模块句柄
     @return 成功返回 true, 否则返回 false
     */
    public static native boolean XC_FreeLibrary (long hModule);
    /**
     获取 32 位 D2D 工厂指针
     @return D2D 工厂指针
     */
    public static native int XC_GetD2dFactoryX86 ();
    /**
     获取 64 位 D2D 工厂指针
     @return D2D 工厂指针
     */
    public static native long XC_GetD2dFactoryX64 ();
    /**
     获取 32 位 DWrite 工厂指针
     @return DWrite 工厂指针
     */
    public static native int XC_GetDWriteFactoryX86 ();
    /**
     获取 64 位 DWrite 工厂指针
     @return DWrite 工厂指针
     */
    public static native long XC_GetDWriteFactoryX64 ();
    /**
     获取默认字体
     @return 默认字体句柄
     */
    public static native int XC_GetDefaultFont ();
    /**
     获取当前所使用的句柄总数量
     @return 句柄总数量
     */
    public static native int XC_GetHandleCount ();
    /**
     获取动态库中函数的地址 (32 位)
     @param hModule 动态库模块句柄
     @param lpProcName 函数名
     @return 函数地址
     */
    public static native int XC_GetProcAddress (int hModule, String lpProcName);
    /**
     获取动态库中函数的地址 (64 位)
     @param hModule 动态库模块句柄
     @param lpProcName 函数名
     @return 函数地址
     */
    public static native long XC_GetProcAddress (long hModule, String lpProcName);
    /**
     获取对象属性
     @param hXCGUI 对象句柄
     @param pName 属性名
     @param dwSize 缓冲区大小
     @return 属性值
     */
    public static native byte [] XC_GetProperty (int hXCGUI, String pName, int dwSize);
    /**
     获取文本显示矩形
     @param pString 字符串
     @param length 字符串长度
     @param hFontX 字体句柄
     @param nTextAlign 文本对齐方式
     @param width 最大宽度
     @param pOutSize 接收返回的显示大小
     */
    public static native void XC_GetTextShowRect (String pString, int length, int hFontX, int nTextAlign, int width, TagSize pOutSize);
    /**
     获取文本显示大小
     @param pString 字符串
     @param length 字符串长度
     @param hFontX 字体句柄
     @param pOutSize 接收返回的显示大小
     */
    public static native void XC_GetTextShowSize (String pString, int length, int hFontX, TagSize pOutSize);
    /**
     获取文本显示大小 (扩展)
     @param pString 字符串
     @param length 字符串长度
     @param hFontX 字体句柄
     @param nTextAlign 文本对齐方式
     @param pOutSize 接收返回的显示大小
     */
    public static native void XC_GetTextShowSizeEx (String pString, int length, int hFontX, int nTextAlign, TagSize pOutSize);
    /**
     获取文本绘制大小
     @param pString 字符串
     @param length 字符串长度
     @param hFontX 字体句柄
     @param pOutSize 接收返回的绘制大小
     */
    public static native void XC_GetTextSize (String pString, int length, int hFontX, TagSize pOutSize);
    /**
     获取 32 位 WIC 工厂指针
     @return WIC 工厂指针
     */
    public static native int XC_GetWicFactoryX86 ();
    /**
     获取 64 位 WIC 工厂指针
     @return WIC 工厂指针
     */
    public static native long XC_GetWicFactoryX64 ();
    /**
     初始化 LOGFONTW 结构体
     @param pFont LOGFONTW 结构体指针
     @param pName 字体名称
     @param size 字体大小
     @param bBold 是否为粗体
     @param bItalic 是否为斜体
     @param bUnderline 是否有下划线
     @param bStrikeOut 是否有删除线
     */
    public static native void XC_InitFont (TagLOGFONTW pFont, String pName, int size, boolean bBold, boolean bItalic, boolean bUnderline, boolean bStrikeOut);
    /**
     加载 32 位动态库并调用其中的 LoadDll () 函数
     @param pDllFileName DLL 文件名
     @return DLL 模块句柄
     */
    public static native int XC_LoadDllX86 (String pDllFileName);
    /**
     加载 64 位动态库并调用其中的 LoadDll () 函数
     @param pDllFileName DLL 文件名
     @return DLL 模块句柄
     */
    public static native long XC_LoadDllX64 (String pDllFileName);
    /**
     * 设置对象属性
     * @param hXCGUI 对象句柄
     * @param pName 属性名
     * @param pValue 属性值，如果是资源，需要加上"@"符号，例如: "@资源名"
     * @return 如果成功返回true，否则返回false
     */
    public static native boolean XC_SetProperty(int hXCGUI, String pName, String pValue);

    /**
     * 注册窗口类名
     * 当炫彩退出时，会自动注销类名; 假如类名没有注销，DLL卸载后，类名所指向的窗口过程地址失效，再次使用此类名，造成程序崩溃
     * @param pClassName 类名
     * @return 如果成功返回true，否则返回false
     */
    public static native boolean XC_RegisterWindowClassName(String pClassName);

    /**
     * 判断两个矩形是否相交及重叠
     * @param pRect1 矩形1
     * @param pRect2 矩形2
     * @return 如果两个矩形相交返回true，否则返回false
     */
    public static native boolean XC_RectInRect(TagRect pRect1, TagRect pRect2);

    /**
     * 显示/隐藏SVG边界
     * @param bShow 是否显示
     */
    public static native void XC_ShowSvgFrame(boolean bShow);

    /**
     * 显示/隐藏布局对象边界
     * @param bShow 是否显示
     */
    public static native void XC_ShowLayoutFrame(boolean bShow);

    /**
     * 启用/禁用资源监视器
     * @param bEnable 是否启用
     */
    public static native void XC_EnableResMonitor(boolean bEnable);

    /**
     * 设置布局边框颜色
     * @param color 颜色值，请使用宏: RGBA()
     */
    public static native void XC_SetLayoutFrameColor(long color);

    /**
     * 在32位环境中分配内存
     * @param size 大小，字节为单位
     * @return 内存首地址
     */
    public static native int XC_MallocX86(int size);

    /**
     * 在64位环境中分配内存
     * @param size 大小，字节为单位
     * @return 内存首地址
     */
    public static native long XC_MallocX64(int size);

    /**
     * 系统API ShellExecute的封装
     * @param hwnd 窗口句柄
     * @param lpOperation 操作类型
     * @param lpFile 文件名
     * @param lpParameters 参数
     * @param lpDirectory 目录
     * @param nShowCmd 显示方式
     * @return 执行成功会返回应用程序句柄
     */
    public static native int XC_Sys_ShellExecute(int hwnd, String lpOperation, String lpFile, String lpParameters, String lpDirectory, int nShowCmd);

    /**
     * 在32位环境下加载指定DLL
     * @param lpFileName DLL文件名
     * @return 返回动态库模块句柄
     */
    public static native int XC_LoadLibraryX86(String lpFileName);

    /**
     * 在64位环境下加载指定DLL
     * @param lpFileName DLL文件名
     * @return 返回动态库模块句柄
     */
    public static native long XC_LoadLibraryX64(String lpFileName);

    /**
     * 加载布局文件从zip压缩包中
     * @param pZipFileName ZIP压缩包文件名
     * @param pFileName 布局文件名
     * @param pPassword ZIP压缩包密码
     * @param hParent 父对象句柄，窗口句柄或UI元素句柄
     * @param hAttachWnd 附加到指定的窗口HWND，如果未指定忽略
     * @return 返回窗口句柄或元素句柄
     */
    public static native int XC_LoadLayoutZip(String pZipFileName, String pFileName, String pPassword, int hParent, int hAttachWnd);

    /**
     * 加载布局文件
     * @param pFileName 布局文件名
     * @param hParent 父对象句柄，窗口句柄或UI元素句柄
     * @param hAttachWnd 附加到指定的窗口HWND，如果未指定忽略
     * @return 返回窗口句柄或元素句柄
     */
    public static native int XC_LoadLayout(String pFileName, int hParent, int hAttachWnd);

    /**
     * 加载布局文件从内存zip压缩包中
     * @param data 内存块指针
     * @param length 内存块大小，字节为单位
     * @param pFileName 布局文件名
     * @param pPassword zip密码
     * @param hParent 父对象句柄，窗口句柄或UI元素句柄
     * @param hAttachWnd 附加窗口句柄，附加到指定的窗口，如果未指定忽略
     * @return 返回窗口句柄或元素句柄
     */
    public static native int XC_LoadLayoutZipMem(byte[] data, int length, String pFileName, String pPassword, int hParent, int hAttachWnd);

    /**
     * 加载样式文件从内存zip压缩包中
     * @param data 内存块指针
     * @param length 内存块大小，字节为单位
     * @param pFileName 文件名
     * @param pPassword zip密码
     * @return 成功返回true，否则返回false
     */
    public static native boolean XC_LoadStyleZipMem(byte[] data, int length, String pFileName, String pPassword);

    /**
     * 从RC资源中的ZIP包中加载样式文件，RC资源类型必须为:"RT_RCDATA"
     * @param id RC资源ID
     * @param pFileName 文件名
     * @param pPassword 密码
     * @param hModule 模块句柄
     * @return 如果成功返回true，否则返回false
     */
    public static native boolean XC_LoadStyleZipRes(int id, String pFileName, String pPassword, int hModule);
    /**
     * 从资源ZIP加载样式文件（RC资源类型必须为:"RT_RCDATA"）
     * @param id RC资源ID
     * @param pFileName 样式文件名
     * @param pPassword ZIP密码
     * @param hModule 模块句柄
     * @return 成功返回TRUE,否则返回FALSE
     */
    public static native boolean XC_LoadStyleZipRes(int id, String pFileName, String pPassword, long hModule);

    /**
     * 从字符串加载样式文件
     * @param pString 样式文件内容字符串
     * @param pFileName 样式文件名（用于打印错误和定位资源）
     * @return 成功返回TRUE,否则返回FALSE
     */
    public static native boolean XC_LoadStyleFromString(String pString, String pFileName);

    /**
     * 从内存ZIP加载资源文件
     * @param data ZIP数据内存块
     * @param length 数据长度（字节）
     * @param pFileName 资源文件名
     * @param pPassword ZIP密码
     * @return 成功返回TRUE,否则返回FALSE
     */
    public static native boolean XC_LoadResourceZipMem(byte[] data, int length, String pFileName, String pPassword);

    /**
     * 从资源ZIP加载资源文件（32位模块句柄）
     * @param id RC资源ID
     * @param pFileName 资源文件名
     * @param pPassword ZIP密码
     * @param hModule 模块句柄（int类型，32位）
     * @return 成功返回TRUE,否则返回FALSE
     */
    public static native boolean XC_LoadResourceZipRes(int id, String pFileName, String pPassword, int hModule);

    /**
     * 从资源ZIP加载资源文件（64位模块句柄）
     * @param id RC资源ID
     * @param pFileName 资源文件名
     * @param pPassword ZIP密码
     * @param hModule 模块句柄（long类型，64位）
     * @return 成功返回TRUE,否则返回FALSE
     */
    public static native boolean XC_LoadResourceZipRes(int id, String pFileName, String pPassword, long hModule);

    /**
     * 绑定int类型数据到炫彩对象
     * @param hXCGUI 炫彩对象句柄
     * @param data 要绑定的int数据
     */
    public static native void _XC_BindData(int hXCGUI, int data);

    /**
     * 绑定long类型数据到炫彩对象
     * @param hXCGUI 炫彩对象句柄
     * @param data 要绑定的long数据
     */
    public static native void _XC_BindData(int hXCGUI, long data);

    /**
     * 获取绑定到炫彩对象的int数据（32位平台）
     * @param hXCGUI 炫彩对象句柄
     * @return 绑定的int数据
     */
    public static native int _XC_GetBindDataX86(int hXCGUI);

    /**
     * 获取绑定到炫彩对象的long数据（64位平台）
     * @param hXCGUI 炫彩对象句柄
     * @return 绑定的long数据
     */
    public static native long _XC_GetBindDataX64(int hXCGUI);

    /**
     * 发送WM_QUIT消息退出消息循环
     * @param nExitCode 退出码
     */
    public static native void XC_PostQuitMessage(int nExitCode);

    /**
     * 线性缓动函数
     * @param pos 位置（0.0f - 1.0f）
     * @return 缓动计算结果
     */
    public static native float XEase_Linear(float pos);

    /**
     * 二次方曲线缓动函数
     * @param pos 位置（0.0f - 1.0f）
     * @param flag 缓动方式（ease_type_枚举）
     * @return 缓动计算结果
     */
    public static native float XEase_Quad(float pos, int flag);

    /**
     * 创建代码编辑框元素
     * @param x 元素x坐标
     * @param y 元素y坐标
     * @param cx 元素宽度
     * @param cy 元素高度
     * @param hParent 父对象句柄（窗口或元素）
     * @return 代码编辑框元素句柄
     */
    public static native int XEditor_Create(int x, int y, int cx, int cy, int hParent);

    /**
     * 获取代码编辑框的颜色信息
     * @param hEle 代码编辑框元素句柄
     * @param pInfo 用于接收颜色信息的结构体
     */
    public static native void XEditor_GetColor(int hEle, TagEditor_color_ pInfo);

    /**
     * 设置代码编辑框的颜色信息
     * @param hEle 代码编辑框元素句柄
     * @param pInfo 包含颜色信息的结构体
     */
    public static native void XEditor_SetColor(int hEle, TagEditor_color_ pInfo);

    /**
     * 获取代码编辑框的所有断点
     * @param hEle 代码编辑框元素句柄
     * @param aPoints 用于接收断点行号的数组
     * @param nCount 数组大小（数组成员数）
     * @return 实际获取的断点数量
     */
    public static native int XEditor_GetBreakpoints(int hEle, byte[] aPoints, int nCount);

    /**
     * 在代码编辑框中设置断点
     * @param hEle 代码编辑框元素句柄
     * @param iRow 行索引
     * @param bActivate 是否激活断点
     * @return 成功返回TRUE,否则返回FALSE
     */
    public static native boolean XEditor_SetBreakpoint(int hEle, int iRow, boolean bActivate);

    /**
     * 设置文件加载回调函数（虚函数版本）
     * @param pCallfunLoadFileCallBack 回调函数实例
     */
    public static native void XRes_SetLoadFileCallback(pCallfunLoadFileCallBack pCallfunLoadFileCallBack);

    /**
     * 设置文件加载回调函数（非虚函数版本）
     * @param pCallfunLoadFileCallBack 回调函数实例
     */
    public static native void XRes_SetLoadFileCallback(pCallfunLoadFileCallBack_Nonvirtual pCallfunLoadFileCallBack);

    /**
     * 获取SVG的宽度和高度
     * @param hSvg SVG句柄
     * @param pWidth 用于接收宽度的数组
     * @param pHeight 用于接收高度的数组
     */
    public static native void XSvg_GetSize(int hSvg, byte[] pWidth, byte[] pHeight);

    /**
     加载 SVG 文件（UTF8 文件）
     @param pFileName SVG 文件名
     @return SVG 句柄
     **/
    public static native int XSvg_LoadFile (String pFileName);
    /**
     获取 SVG 视图框
     @param hSvg SVG 句柄
     @param pViewBox 接收视图框坐标的结构体
     **/
    public static native void XSvg_GetViewBox (int hSvg, TagRect pViewBox);
    /**
     创建窗口（增强功能）
     @param dwExStyle 窗口扩展样式
     @param dwStyle 窗口样式
     @param lpClassName 窗口类名
     @param x 窗口左上角 X 坐标
     @param y 窗口左上角 Y 坐标
     @param cx 窗口宽度
     @param cy 窗口高度
     @param pTitle 窗口标题
     @param hWndParent 父窗口句柄
     @param XCStyle 炫彩窗口样式
     @return 炫彩窗口资源句柄
     **/
    public static native int XWnd_CreateEx (long dwExStyle, long dwStyle, String lpClassName, int x, int y, int cx, int cy, String pTitle, int hWndParent, int XCStyle);
    /**
     获取窗口绘制区域
     @param hWindow 窗口资源句柄
     @param pRcPaint 接收绘制区域坐标的结构体
     **/
    public static native void XWnd_GetDrawRect (int hWindow, TagRect pRcPaint);
    /**
     获取窗口边框大小
     @param hWindow 窗口句柄
     @param pBorder 接收边框大小的结构体
     **/
    public static native void XWnd_GetBorderSize (int hWindow, TagBorderSize pBorder);
    /**
     获取窗口拖动边框大小
     @param hWindow 窗口句柄
     @param pSize 接收拖动边框大小的结构体
     **/
    public static native void XWnd_GetDragBorderSize (int hWindow, TagBorderSize pSize);
    /**
     检测坐标点所在的子元素
     @param hWindow 窗口资源句柄
     @param pPoint 坐标点
     @return 子元素句柄
     **/
    public static native int XWnd_HitChildEle (int hWindow, TagPoint pPoint);
    /**
     获取插入符信息
     @param hWindow 窗口句柄
     @param pX 接收 X 坐标的数组
     @param pY 接收 Y 坐标的数组
     @param pWidth 接收宽度的数组
     @param pHeight 接收高度的数组
     @return 插入符元素句柄
     **/
    public static native int XWnd_GetCaretInfo (int hWindow, byte [] pX, byte [] pY, byte [] pWidth, byte [] pHeight);
    /**
     获取窗口阴影信息
     @param hWindow 窗口句柄
     @param pnSize 接收阴影大小的数组
     @param pnDepth 接收阴影深度（0-255）的数组
     @param pnAngeleSize 接收圆角阴影内收大小的数组
     @param pbRightAngle 接收是否强制直角的数组
     @param pColor 接收阴影颜色的数组
     **/
    public static native void XWnd_GetShadowInfo (int hWindow, byte [] pnSize, byte [] pnDepth, byte [] pnAngeleSize, byte [] pbRightAngle, byte [] pColor);
    /**
     设置窗口阴影信息
     @param hWindow 窗口句柄
     @param nSize 阴影大小
     @param nDepth 阴影深度（0-255）
     @param nAngeleSize 圆角阴影内收大小
     @param bRightAngle 是否强制直角（TRUE：直角，FALSE：圆角）
     @param color 阴影颜色（使用宏 RGBA ()）
     **/
    public static native void XWnd_SetShadowInfo (int hWindow, int nSize, int nDepth, int nAngeleSize, boolean bRightAngle, long color);
    /**
     获取窗口透明类型
     @param hWindow 窗口句柄
     @return 窗口透明类型
     **/
    public static native int XWnd_GetTransparentType (int hWindow);
    /**
     设置透明窗口的透明色
     @param hWindow 窗口句柄
     @param color 透明色
     **/
    public static native void XWnd_SetTransparentColor (int hWindow, long color);
    /**
     设置透明窗口类型
     @param hWindow 窗口句柄
     @param nType 窗口透明类型
     **/
    public static native void XWnd_SetTransparentType (int hWindow, int nType);
    /**
     创建基础元素
     @param x 元素 X 坐标
     @param y 元素 Y 坐标
     @param cx 元素宽度
     @param cy 元素高度
     @param hParent 父对象句柄（窗口句柄或元素句柄）
     @return 元素句柄
     **/
    public static native int XEle_Create (int x, int y, int cx, int cy, int hParent);
    /**
     发送事件给元素
     @param hEle 目标元素句柄
     @param nEvent 事件类型
     @param len 事件参数长度
     @param TypeCode 事件参数类型码
     @param data 事件参数数据
     @return 事件返回值
     **/
    public static native long XEle_SendEvent (int hEle, int nEvent, long len, long TypeCode, byte [] data);
    /**
     投递事件给元素
     @param hEle 元素句柄
     @param nEvent 事件类型
     @param len 事件参数长度
     @param TypeCode 事件参数类型码
     @param data 事件参数数据
     @return 成功返回 TRUE，否则返回 FALSE
     **/
    public static native boolean XEle_PostEvent (int hEle, int nEvent, long len, long TypeCode, byte [] data);
    /**
     启用或禁用元素背景画布（禁用则绘制在父画布之上）
     @param hEle 元素句柄
     @param bEnable 是否启用
     **/
    public static native void XEle_EnableCanvas (int hEle, boolean bEnable);
    /**
     检测坐标点所在的子元素（包含子元素的子元素）
     @param hEle 元素句柄
     @param pPt 坐标点
     @return 成功返回元素句柄，否则返回 NULL
     **/
    public static native int XEle_HitChildEle (int hEle, TagPoint pPt);
    /**
     获取元素坐标
     @param hEle 元素句柄
     @param pRect 接收元素坐标的结构体
     **/
    public static native void XEle_GetRect (int hEle, TagRect pRect);

    /**
     * 设置元素坐标
     * @param hEle 元素句柄
     * @param pRect 坐标
     * @param bRedraw 是否重绘
     * @param nFlags 调整布局标识位 @ref adjustLayout_
     * @param nAdjustNo 调整布局流水号
     * @return 如果返回0坐标没有改变,如果大小改变返回2(触发XE_SIZE), 否则返回1(仅改变left,top,没有改变大小)
     */
    public static native int XEle_SetRect(int hEle, TagRect pRect, boolean bRedraw, int nFlags, long nAdjustNo);

    /**
     * 获取元素逻辑坐标,包含滚动视图偏移
     * @param hEle 元素句柄
     * @param pRect 坐标
     */
    public static native void XEle_GetRectLogic(int hEle, TagRect pRect);

    /**
     * 获取元素客户区坐标
     * @param hEle 元素句柄
     * @param pRect 坐标
     */
    public static native void XEle_GetClientRect(int hEle, TagRect pRect);

    /**
     * 将窗口客户区坐标转换到元素客户区坐标
     * @param hEle 元素句柄
     * @param pRect 坐标
     */
    public static native void XEle_RectWndClientToEleClient(int hEle, TagRect pRect);

    /**
     * 将窗口客户区坐标点转换到元素客户区坐标点
     * @param hEle 元素句柄
     * @param point 坐标点
     */
    public static native void XEle_PointWndClientToEleClient(int hEle, TagPoint point);

    /**
     * 将元素客户区坐标转换到窗口客户区坐标
     * @param hEle 元素句柄
     * @param pRect 坐标
     */
    public static native void XEle_RectClientToWndClient(int hEle, TagRect pRect);

    /**
     * 将元素客户区坐标转换到窗口客户区DPI坐标
     * @param hEle 元素句柄
     * @param pRect 坐标
     */
    public static native void XEle_RectClientToWndClientDPI(int hEle, TagRect pRect);

    /**
     * 将元素客户区坐标点转换到窗口客户区坐标点
     * @param hEle 元素句柄
     * @param pPt 坐标点
     */
    public static native void XEle_PointClientToWndClient(int hEle, TagPoint pPt);

    /**
     * 将元素客户区坐标点转换到窗口客户区坐标点
     * @param hEle 元素句柄
     * @param pPt 坐标点
     */
    public static native void XEle_PointClientToWndClient(long hEle, TagPoint pPt);

    /**
     * 将元素客户区坐标点转换到窗口客户区DPI坐标点
     * @param hEle 元素句柄
     * @param pPt 坐标点
     */
    public static native void XEle_PointClientToWndClientDPI(int hEle, TagPoint pPt);

    /**
     * 获取元素基于窗口客户区的坐标
     * @param hEle 元素句柄
     * @param pRect 接收坐标
     */
    public static native void XEle_GetWndClientRect(int hEle, TagRect pRect);

    /**
     * 获取元素基于窗口客户区的DPI坐标
     * @param hEle 元素句柄
     * @param pRect 接收坐标
     */
    public static native void XEle_GetWndClientRectDPI(int hEle, TagRect pRect);

    /**
     * 重绘元素指定区域
     * @param hEle 元素句柄
     * @param pRect 相对于元素客户区的坐标
     * @param bImmediate 是否立即重绘
     */
    public static native void XEle_RedrawRect(int hEle, TagRect pRect, boolean bImmediate);

    /**
     * 获取元素内填充大小
     * @param hEle 元素句柄
     * @param padDingSize 接收内填充大小
     */
    public static native void XEle_GetPadding(int hEle, TagPadDingSize padDingSize);

    /**
     * 获取元素文本颜色
     * @param hEle 元素句柄
     * @return 文本颜色值
     */
    public static native long XEle_GetTextColor(int hEle);

    /**
     * 设置焦点边框颜色
     * @param hEle 元素句柄
     * @param color 颜色值, 请使用宏: RGBA()
     */
    public static native void XEle_SetFocusBorderColor(int hEle, long color);

    /**
     * 获取元素内容大小
     * @param hEle 元素句柄
     * @param bHorizon 水平或垂直, 布局属性交换依赖
     * @param cx 宽度
     * @param cy 高度
     * @param pSize 返回大小
     */
    public static native void XEle_GetContentSize(int hEle, boolean bHorizon, int cx, int cy, TagSize pSize);

    /**
     * 设置元素炫彩定时器
     * @param hEle 元素句柄
     * @param nIDEvent 事件ID
     * @param uElapse 延时毫秒
     * @return 如果成功返回TRUE,否则返回FALSE
     */
    public static native boolean XEle_SetXCTimer(int hEle, long nIDEvent, long uElapse);

    /**
     获取元素工具提示内容
     @param hEle 元素句柄
     @param dwSize 接收文本的缓冲区大小 (字节为单位)
     @return 工具提示内容字节数组
     */
    public static native byte [] XEle_GetToolTip (int hEle, int dwSize);
    /**
     创建图形绘制模块实例
     @param hWindow 窗口句柄
     @return 图形绘制模块实例句柄 (HDRAW)
     */
    public static native long XDraw_Create (int hWindow);
    /**
     创建图形绘制模块实例 (GDI)
     @param hWindow 窗口句柄
     @param hdc GDI 的 HDC 句柄
     @return 图形绘制模块实例句柄 (HDRAW)
     */
    public static native long XDraw_CreateGDI (int hWindow, long hdc);
    /**
     获取绘图坐标偏移量 (X 向左偏移为负数，向右偏移为正数)
     @param hDraw 图形绘制句柄
     @param pX 接收 X 轴偏移量的数组
     @param pY 接收 Y 轴偏移量的数组
     */
    public static native void XDraw_GetOffset (long hDraw, byte [] pX, byte [] pY);
    /**
     设置 GDI 背景模式
     @param hDraw 图形绘制句柄
     @param bTransparent 是否透明背景
     @return 先前的背景模式
     */
    public static native int XDraw_GDI_SetBkMode (long hDraw, boolean bTransparent);
    /**
     GDI 创建具有指定纯色的逻辑画刷
     @param hDraw 图形绘制句柄
     @param crColor 画刷颜色 (RGBA 值)
     @return 逻辑画刷句柄 (HBRUSH)
     */
    public static native long XDraw_GDI_CreateSolidBrush (long hDraw, long crColor);
    /**
     GDI 创建多边形区域
     @param hDraw 图形绘制句柄
     @param pPt POINT 坐标数组
     @param cPoints 坐标点数量
     @param fnPolyFillMode 多边形填充模式 (ALTERNATE/WINDING)
     @return 区域句柄 (HRGN)
     */
    public static native long XDraw_GDI_CreatePolygonRgn (long hDraw, TagPoint [] pPt, int cPoints, int fnPolyFillMode);
    /**
     GDI 绘制矩形 (使用当前画刷和画笔)
     @param hDraw 图形绘制句柄
     @param nLeftRect 左上角 X 坐标
     @param nTopRect 左上角 Y 坐标
     @param nRightRect 右下角 X 坐标
     @param nBottomRect 右下角 Y 坐标
     @return 绘制成功返回 true, 否则返回 false
     */
    public static native boolean XDraw_GDI_Rectangle (long hDraw, int nLeftRect, int nTopRect, int nRightRect, int nBottomRect);
    /**
     获取绑定的设备上下文 HDC 句柄
     @param hDraw 图形绘制句柄
     @return HDC 句柄
     */
    public static native long XDraw_GetHDC (long hDraw);
    /**
     绘制矩形边框
     @param hDraw 图形绘制句柄
     @param pRect 矩形区域坐标
     */
    public static native void XDraw_DrawRect (long hDraw, TagRect pRect);
    /**
     获取绘图当前使用的字体
     @param hDraw 图形绘制句柄
     @return 炫彩字体句柄 (HFONTX)
     */
    public static native int XDraw_GetFont (long hDraw);
    /**
     GDI 创建矩形区域
     @param hDraw 图形绘制句柄
     @param nLeftRect 左上角 X 坐标
     @param nTopRect 左上角 Y 坐标
     @param nRightRect 右下角 X 坐标
     @param nBottomRect 右下角 Y 坐标
     @return 区域句柄 (HRGN)
     */
    public static native long XDraw_GDI_CreateRectRgn (long hDraw, int nLeftRect, int nTopRect, int nRightRect, int nBottomRect);
    /**
     GDI 使用指定画刷填充区域
     @param hDraw 图形绘制句柄
     @param hrgn 区域句柄 (HRGN)
     @param hbr 画刷句柄 (HBRUSH)
     @return 填充成功返回 true, 否则返回 false
     */
    public static native boolean XDraw_GDI_FillRgn (long hDraw, long hrgn, long hbr);
    /**
     GDI 创建指定样式、宽度和颜色的逻辑笔
     @param hDraw 图形绘制句柄
     @param fnPenStyle 画笔样式 (PS_SOLID/PS_DASH 等)
     @param width 画笔宽度
     @param crColor 画笔颜色 (RGBA 值)
     @return 逻辑笔句柄 (HPEN)
     */
    public static native long XDraw_GDI_CreatePen (long hDraw, int fnPenStyle, int width, long crColor);
    /**
     GDI 绘制椭圆
     @param hDraw 图形绘制句柄
     @param pRect 椭圆的外接矩形坐标
     @return 绘制成功返回 true, 否则返回 false
     */
    public static native boolean XDraw_GDI_Ellipse (long hDraw, TagRect pRect);

    /**
     GDI 的 MoveToEx 函数，更新当前位置到指定点，并返回以前的位置
     @param hDraw 图形绘制句柄
     @param X 指定点的 X 坐标
     @param Y 指定点的 Y 坐标
     @param pPoint 接收以前的当前位置的 POINT 结构指针，如果为 null 则不返回原来的位置
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XDraw_GDI_MoveToEx (long hDraw, int X, int Y, TagPoint pPoint);
    /**
     GDI 的 Polyline 函数，绘制折线
     @param hDraw 图形绘制句柄
     @param pArrayPt POINT 数组，包含折线的顶点坐标
     @param arrayPtSize 数组大小（数组成员数）
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XDraw_GDI_Polyline (long hDraw, TagPoint [] pArrayPt, int arrayPtSize);
    /**
     获取窗口图标句柄
     @param hWindow 窗口句柄
     @return 返回图标句柄
     **/
    public static native int XWnd_GetIcon (int hWindow);
    /**
     GDI 的 BitBlt 函数，将位图从源设备上下文复制到目标设备上下文
     @param hDrawDest 目标图形绘制句柄
     @param nXDest 目标矩形的左上角 X 坐标
     @param nYDest 目标矩形的左上角 Y 坐标
     @param width 目标矩形的宽度
     @param nHeight 目标矩形的高度
     @param hdcSrc 源设备上下文句柄
     @param nXSrc 源矩形的左上角 X 坐标
     @param nYSrc 源矩形的左上角 Y 坐标
     @param dwRop 光栅操作码，详情见 MSDN
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XDraw_GDI_BitBlt (long hDrawDest, int nXDest, int nYDest, int width, int nHeight, long hdcSrc, int nXSrc, int nYSrc, long dwRop);
    /**
     渐变填充，从一种颜色过渡到另一种颜色
     @param hDraw 图形绘制句柄
     @param pRect 要填充的矩形坐标
     @param color1 开始颜色（使用宏 RGBA () 创建）
     @param color2 结束颜色（使用宏 RGBA () 创建）
     @param mode 填充模式：GRADIENT_FILL_RECT_H（水平填充）、GRADIENT_FILL_RECT_V（垂直填充）、GRADIENT_FILL_TRIANGLE（三角形）
     **/
    public static native void XDraw_GradientFill2 (long hDraw, TagRect pRect, long color1, long color2, int mode);
    /**
     在自绘事件函数中，用户手动调用绘制窗口，以便控制绘制顺序
     @param hWindow 窗口资源句柄
     @param hDraw 图形绘制句柄
     **/
    public static native void XWnd_DrawWindow (int hWindow, long hDraw);
    /**
     绘制曲线（D2D 暂时留空）
     @param hDraw 图形绘制句柄
     @param points 坐标点数组
     @param count 数组大小（数组成员数）
     @param tension 大于或等于 0.0F 的值，指定曲线的张力（D2D 忽略此参数）
     **/
    public static native void XDraw_DrawCurve (long hDraw, TagPoint [] points, int count, float tension);
    /**
     绘制曲线（D2D 暂时留空，使用浮点数坐标）
     @param hDraw 图形绘制句柄
     @param points 浮点数坐标点数组
     @param count 数组大小（数组成员数）
     @param tension 大于或等于 0.0F 的值，指定曲线的张力（D2D 忽略此参数）
     **/
    public static native void XDraw_DrawCurveF (long hDraw, TagPointF [] points, int count, float tension);
    /**
     在指定矩形内绘制文本
     @param hDraw 图形绘制句柄
     @param pString 要绘制的字符串
     @param nCount 字符串长度，填 - 1 则自动计算长度
     @param pRect 文本绘制的矩形区域坐标
     **/
    public static native void XDraw_DrawText (long hDraw, String pString, int nCount, TagRect pRect);

    /**
     绘制 SVG
     @param hDraw 图形绘制句柄
     @param hSvg SVG 句柄
     @param x 绘制起点 X 坐标
     @param y 绘制起点 Y 坐标
     **/
    public static native void XDraw_DrawSvg (long hDraw, int hSvg, int x, int y);
    /**
     列表增加列
     @param hEle 列表元素句柄
     @param width 列宽度
     @return 返回列索引
     **/
    public static native int XList_AddColumn (long hEle, int width);
    /**
     列表选择所有行
     @param hEle 列表元素句柄
     **/
    public static native void XList_SetSelectAll (long hEle);
    /**
     列表获取所有选择的行
     @param hEle 列表元素句柄
     @param len 数组大小（数组成员数）
     @param pArray 用于接收选择行索引的数组
     @return 返回实际接收的行数量
     **/
    private static native int XList_GetSelectAll (int hEle, int len, int [] pArray);
    /**
     列表滚动视图让指定行可见
     @param hEle 列表元素句柄
     @param iRow 行索引
     **/
    public static native void XList_VisibleRow (int hEle, int iRow);
    /**
     列表取消选择指定行
     @param hEle 列表元素句柄
     @param iRow 行索引
     @return 如果成功返回 true，否则返回 false
     **/
    public static native boolean XList_CancelSelectRow (int hEle, int iRow);
    /**
     创建项模板
     @param nType 模板类型 ListItemTempType
     @return 返回项模板句柄
     **/
    public static native int XTemp_Create (int nType);

    /**
     编辑框_获取数据（包含非文本内容）
     @param hEle 编辑框元素句柄
     @param tag_edit_data_copy_ 接收数据的结构
     @param dwSize 结构大小
     @param PstyleCall 样式回调
     @return 返回数据结构
     **/
    public static native int XEdit_GetData (int hEle, Tag_edit_data_copy tag_edit_data_copy_, int dwSize, PstyleCall PstyleCall);
    /**
     编辑框_获取数据（包含非文本内容，非虚拟样式回调重载）
     @param hEle 编辑框元素句柄
     @param tag_edit_data_copy_ 接收数据的结构
     @param dwSize 结构大小
     @param PstyleCall 非虚拟样式回调
     @return 返回数据结构
     **/
    public static native int XEdit_GetData (int hEle, Tag_edit_data_copy tag_edit_data_copy_, int dwSize, PstyleCall_Nonvirtual PstyleCall);
    /**
     编辑框_设置文本
     @param hEle 编辑框元素句柄
     @param pString 文本内容
     **/
    public static native void XEdit_SetText (int hEle, String pString);
    /**
     编辑框_添加样式
     @param hEle1 编辑框元素句柄
     @param hFont_image_Obj 字体 / 图片 / UI 对象句柄
     @param color 颜色值（请使用 RGBA 宏）
     @param bColor 是否使用颜色
     @return 返回样式索引
     **/
    public static native int XEdit_AddStyle (int hEle1, int hFont_image_Obj, long color, boolean bColor);
    /**
     编辑框_添加对象到样式
     @param hEle 编辑框元素句柄
     @param hObj 对象句柄（字体 / 图片 / UI 对象）
     @return 返回样式索引
     **/
    public static native int XEdit_AddObject (int hEle, int hObj);
    /**
     编辑框_添加数据
     @param hEle1 编辑框元素句柄
     @param hEle2 数据结构句柄
     @param styleTable 样式表数组
     @param nStyleCount 样式数量
     **/
    public static native void XEdit_AddData (int hEle1, int hEle2, int [] styleTable, int nStyleCount);
    /**
     编辑框_添加数据（扩展）
     @param hEle1 编辑框元素句柄
     @param hEle2 数据结构句柄
     @param styleTable 样式表数组
     @param nStyleCount 样式数量
     @param editDataCopy 数据复制结构
     **/
    public static native void XEdit_AddData2 (int hEle1, int hEle2, int [] styleTable, int nStyleCount, Tag_edit_data_copy editDataCopy);
    /**
     释放编辑框数据
     @param hEle 数据句柄
     **/
    public static native void XEdit_FreeData (int hEle);
    /**
     设置编辑框密码字符
     @param hEle 编辑框句柄
     @param ch 密码字符
     **/
    public static native void XEdit_SetPasswordCharacter (int hEle, char ch);
    /**
     启用编辑框密码模式
     @param hEle 编辑框句柄
     @param bEnable 是否启用
     **/
    public static native void XEdit_EnablePassword (int hEle, boolean bEnable);
    /**
     获取编辑框文本
     @param hEle 编辑框句柄
     @param pOut 接收文本的字节数组
     @param nOutlen 内存大小（字符为单位）
     @return 实际接收文本长度
     **/
    public static native int XEdit_GetText (int hEle, byte [] pOut, int nOutlen);
    /**
     获取编辑框临时文本
     @param hEle 编辑框句柄
     @param dwSize 临时缓存区大小
     @return 临时文本字节数组
     **/
    public static native byte [] XEdit_GetText_Temp (int hEle, int dwSize);
    /**
     获取编辑框指定位置字符
     @param hEle 编辑框句柄
     @param iRow 行索引
     @param iCol 列索引
     @return 字符
     **/
    public static native char XEdit_GetAt (int hEle, int iRow, int iCol);
    /**
     获取编辑框样式信息
     @param hEle 编辑框句柄
     @param iStyle 样式索引
     @param info 接收样式信息的结构体
     @return 是否成功
     **/
    public static native boolean XEdit_GetStyleInfo (long hEle, int iStyle, TagEditStyleInfo info);
    /**
     获取编辑框当前位置行列信息
     @param hEle 编辑框句柄
     @param iRow 接收行索引的字节数组
     @param iCol 接收列索引的字节数组
     **/
    public static native void XEdit_GetCurPosEx (int hEle, byte [] iRow, byte [] iCol);
    /**
     获取编辑框指定行列的坐标点
     @param hEle 编辑框句柄
     @param iRow 行索引
     @param iCol 列索引
     @param pOut 接收坐标点的结构体
     **/
    public static native void XEdit_GetPoint (int hEle, int iRow, int iCol, TagPoint pOut);
    /**
     将编辑框位置转换为行列
     @param hEle 编辑框句柄
     @param iPos 位置点
     @param pInfo 接收行列信息的结构体
     **/
    public static native void XEdit_PosToRowCol (int hEle, int iPos, TagPosition pInfo);
    /**
     将编辑框行列转换为位置
     @param hEle 编辑框句柄
     @param iRow 行索引
     @param iCol 列索引
     @return 位置点
     **/
    public static native int XEdit_RowColToPos (int hEle, int iRow, int iCol);
    /**
     启用编辑框多行模式
     @param hEle 编辑框句柄
     @param bEnable 是否启用
     **/
    public static native void XEdit_EnableMultiLine (int hEle, boolean bEnable);
    /**
     获取编辑框选择内容范围
     @param hEle 编辑框句柄
     @param pBegin 接收起始位置的结构体
     @param pEnd 接收结束位置的结构体
     @return 是否成功
     **/
    public static native boolean XEdit_GetSelectRange (int hEle, TagPosition pBegin, TagPosition pEnd);

    /**
     编辑框_获取可视行范围
     @param hEle 编辑框元素句柄
     @param piStart 返回起始行索引的字节数组
     @param piEnd 返回结束行索引的字节数组
     */
    public static native void XEdit_GetVisibleRowRange (int hEle, byte [] piStart, byte [] piEnd);
    /**
     列表视_获取所有选择的项 ID (回调方式)
     @param hEle 列表视图元素句柄
     @param pArrayCallBack 处理选择项 ID 的回调接口
     @param nArraySize 数组大小
     @return 返回接收的项数量
     */
    public static native int XListView_GetSelectAll (int hEle, pArrayCallBack pArrayCallBack, int nArraySize);
    /**
     列表视_获取所有选择的项 ID (非虚拟回调方式)
     @param hEle 列表视图元素句柄
     @param pArrayCallBack 处理选择项 ID 的非虚拟回调接口
     @param nArraySize 数组大小
     @return 返回接收的项数量
     */
    public static native int XListView_GetSelectAll (int hEle, pArrayCallBack_Nonvirtual pArrayCallBack, int nArraySize);
    /**
     列表视_获取项大小
     @param hEle 列表视图元素句柄
     @param pSize 接收返回大小的 TagSize 对象
     */
    public static native void XListView_GetItemSize (int hEle, TagSize pSize);
    /**
     列表视_设置项用户数据
     @param hEle 列表视图元素句柄
     @param iGroup 组索引
     @param iItem 项索引
     @param nData 用户数据的字节数组
     */
    public static native void XListView_SetItemUserData (int hEle, int iGroup, int iItem, byte [] nData);
    /**
     列表视_设置组用户数据
     @param hEle 列表视图元素句柄
     @param iGroup 组索引
     @param nData 用户数据的字节数组
     */
    public static native void XListView_SetGroupUserData (int hEle, int iGroup, byte [] nData);
    /**
     列表视_获取项用户数据
     @param hEle 列表视图元素句柄
     @param iGroup 组索引
     @param iItem 项索引
     @param dwSize 接收数据的缓冲区大小
     @return 返回用户数据的字节数组
     */
    public static native byte [] XListView_GetItemUserData (int hEle, int iGroup, int iItem, int dwSize);
    /**
     列表视_获取组用户数据
     @param hEle 列表视图元素句柄
     @param iGroup 组索引
     @param dwSize 接收数据的缓冲区大小
     @return 返回用户数据的字节数组
     */
    public static native byte [] XListView_GetGroupUserData (int hEle, int iGroup, int dwSize);
    /**
     列表视_组获取文本内容 (通过字段名)
     @param hEle 列表视图元素句柄
     @param iGroup 组索引
     @param pName 字段名称
     @param dwSize 接收文本的缓冲区大小
     @return 返回文本内容的字节数组
     */
    public static native byte [] XListView_Group_GetTextEx (int hEle, int iGroup, String pName, int dwSize);
    /**
     列表视_组获取文本内容 (通过列索引)
     @param hEle 列表视图元素句柄
     @param iGroup 组索引
     @param iColumn 列索引
     @param dwSize 接收文本的缓冲区大小
     @return 返回文本内容的字节数组
     */
    public static native byte [] XListView_Group_GetText (int hEle, int iGroup, int iColumn, int dwSize);
    /**
     列表视_项获取文本内容 (通过列索引)
     @param hEle 列表视图元素句柄
     @param iGroup 组索引
     @param iItem 项索引
     @param iColumn 列索引
     @param dwSize 接收文本的缓冲区大小
     @return 返回文本内容的字节数组
     */
    public static native byte [] XListView_Item_GetText (int hEle, int iGroup, int iItem, int iColumn, int dwSize);
    /**
     列表视_项获取文本内容 (通过字段名)
     @param hEle 列表视图元素句柄
     @param iGroup 组索引
     @param iItem 项索引
     @param pName 字段名称
     @param dwSize 接收文本的缓冲区大小
     @return 返回文本内容的字节数组
     */
    public static native byte [] XListView_Item_GetTextEx (int hEle, int iGroup, int iItem, String pName, int dwSize);
    /**
     列表框_设置项信息
     @param hEle 列表框元素句柄
     @param iItem 项索引
     @param pItem 项信息的 TagListBoxItemInfo 对象
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XListBox_SetItemInfo (int hEle, int iItem, TagListBoxItemInfo pItem);
    /**
     列表框_获取项信息 (回调方式)
     @param hEle 列表框元素句柄
     @param iItem 项索引
     @param pItemCall 处理项信息的回调接口
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XListBox_GetItemInfo (int hEle, int iItem, pItemCall pItemCall);
    /**
     列表框_获取项信息 (非虚拟回调方式)
     @param hEle 列表框元素句柄
     @param iItem 项索引
     @param pItemCall 处理项信息的非虚拟回调接口
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XListBox_GetItemInfo (int hEle, int iItem, pItemCall_Nonvirtual pItemCall);

    /**
     列表框_启用固定行高
     @param hEle 列表框元素句柄
     @param bEnable 是否启用固定行高
     **/
    public static native void XListBox_EnableFixedRowHeight (int hEle, boolean bEnable);
    /**
     列表框_取全部选择
     @param hEle 列表框元素句柄
     @param pArray 接收选择项索引的数组
     @param nArraySize 数组大小 (数组成员数)
     @return 返回接收的项数量
     **/
    public static native int XListBox_GetSelectAll (int hEle, byte [] pArray, int nArraySize);
    /**
     列表树_创建
     @param x 元素 x 坐标
     @param y 元素 y 坐标
     @param cx 元素宽度
     @param cy 元素高度
     @param hParent 父对象句柄（窗口或元素）
     @return 树元素句柄
     **/
    public static native int XTree_Create (int x, int y, int cx, int cy, int hParent);
    /**
     列表树_创建扩展
     使用内置项模板，自动创建数据适配器
     @param x 元素 x 坐标
     @param y 元素 y 坐标
     @param cx 元素宽度
     @param cy 元素高度
     @param hParent 父对象句柄（窗口或元素）
     @return 树元素句柄
     **/
    public static native int XTree_CreateEx (int x, int y, int cx, int cy, int hParent);
    /**
     托盘图标_置回调消息
     设置用户自定义的回调消息类型，触发事件后，系统会发送到此消息
     @param user_message 用户自定义消息
     **/
    public static native void XTrayIcon_SetCallbackMessage (int user_message);
    /**
     托盘图标_添加
     将图标添加到系统托盘
     @param hWindow 关联窗口句柄
     @param id 托盘图标唯一标识符
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XTrayIcon_Add (int hWindow, int id);
    /**
     托盘图标_置图标
     设置托盘图标
     @param hIcon 图标句柄
     **/
    public static native void XTrayIcon_SetIcon (int hIcon);
    /**
     托盘图标_置提示文本
     设置工具提示内容
     @param pTips 提示文本内容（长度不能超过 127 个字符）
     **/
    public static native void XTrayIcon_SetTips (String pTips);
    /**
     托盘图标_置弹出气泡
     设置弹出气泡信息
     @param pTitle 弹出气泡标题
     @param pText 弹出气泡内容
     @param hBalloonIcon 气泡图标
     @param flags 标识，可设置默认图标类型、禁用声音等
     **/
    public static native void XTrayIcon_SetPopupBalloon (String pTitle, String pText, int hBalloonIcon, int flags);
    /**
     字体_创建
     创建炫彩字体，当字体句柄与元素关联后，会自动释放
     @param size 字体大小，单位 (pt, 磅)
     @return 字体句柄
     **/
    public static native int XFont_Create (int size);
    /**
     字体_取信息
     获取字体信息
     @param hFontX 字体句柄
     @param pInfo 接收返回的字体信息的结构体
     @param dwSize 结构体大小
     **/
    public static native void XFont_GetFontInfo (int hFontX, TagFontInfo pInfo, int dwSize);
    /**
     字体_取 LOGFONTW
     获取字体 LOGFONTW 结构体
     @param hFontX 字体句柄
     @param hdc hdc 句柄
     @param pOut 接收返回信息的结构体
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XFont_GetLOGFONTW (int hFontX, long hdc, TagLOGFONTW pOut);
    /**
     字体_创建从 LOGFONT
     从 LOGFONTW 结构体创建炫彩字体
     @param pFontInfo 字体信息结构体
     @return 字体句柄
     **/
    public static native int XFont_CreateFromLOGFONTW (TagLOGFONTW pFontInfo);

    /**
     创建背景管理器
     @return 返回背景管理器句柄
      *背景_创建 ()
     */
    public static native int XBkM_Create ();
    /**
     获取对象最终类型
     @param hXCGUI 对象句柄
     @return 返回对象类型 XC_OBJECT_TYPE
      *炫彩对象_取类型 ()
     */
    public static native int XObj_GetType (int hXCGUI);
    /**
     获取对象基础类型
     @param hXCGUI 对象句柄
     @return 返回对象基础类型，包括 XC_ERROR, XC_WINDOW, XC_ELE, XC_SHAPE, XC_ADAPTER
      *炫彩对象_取基础类型 ()
     */
    public static native int XObj_GetTypeBase (int hXCGUI);
    /**
     获取对象扩展类型
     @param hXCGUI 对象句柄
     @return 返回对象扩展类型
      *炫彩对象_取类型扩展 ()
     */
    public static native int XObj_GetTypeEx (int hXCGUI);
    /**
     设置对象扩展类型，如果是按钮请使用按钮的增强接口 XBtn_SetTypeEx ()
     @param hXCGUI 对象句柄
     @param nType 扩展类型，参见宏定义
      *炫彩对象_置类型扩展 ()
     */
    public static native void XObj_SetTypeEx (int hXCGUI, int nType);
    /**
     设置 UI 对象样式
     @param hXCGUI 对象句柄
     @param nStyle 样式值
      *可视对象_置样式 ()
     */
    public static native void XUI_SetStyle (int hXCGUI, int nStyle);
    /**
     获取 UI 对象样式
     @param hXCGUI 对象句柄
     @return 返回 UI 对象样式
      *可视对象_取样式 ()
     */
    public static native int XUI_GetStyle (int hXCGUI);
    /**
     启用或禁用样式，并且覆盖内嵌子元素属性（例如：滚动视图上的滚动条，滚动条上的按钮）
     @param hXCGUI 对象句柄
     @param bEnable 是否启用
      *可视对象_启用 CSS ()
     */
    public static native void XUI_EnableCSS (int hXCGUI, boolean bEnable);
    /**
     启用或禁用样式，仅设置自身属性，不包含内嵌子元素属性（例如：滚动视图上的滚动条，滚动条上的按钮）
     @param hXCGUI 对象句柄
     @param bEnable 是否启用
      *可视对象_启用 CSS 扩展 ()
     */
    public static native void XUI_EnableCssEx (int hXCGUI, boolean bEnable);
    /**
     设置 CSS [套用样式] 名称
     @param hXCGUI 对象句柄
     @param pName 套用样式名称
      *可视对象_置 CSS 名称 ()
     */
    public static native void XUI_SetCssName (int hXCGUI, String pName);
    /**
     获取 CSS 样式名称
     @param hXCGUI 对象句柄
     @param dwSize 缓冲区大小
     @return 返回 CSS 样式名称的字节数组
      *可视对象_取 CSS 名称 ()
     */
    public static native byte [] XUI_GetCssName (int hXCGUI, int dwSize);
    /**
     判断 UI 对象是否显示
     @param hXCGUI 对象句柄
     @return 显示返回 true，否则返回 false
      *窗口组件_是否显示 ()
     */
    public static native boolean XWidget_IsShow (int hXCGUI);
    /**
     显示 / 隐藏 UI 对象
     @param hXCGUI 对象句柄
     @param bShow 是否显示
      *窗口组件_显示 ()
     */
    public static native void XWidget_Show (int hXCGUI, boolean bShow);
    /**
     该对象是否受布局控制
     @param hXCGUI 对象句柄
     @param bEnable 是否启用
      *窗口组件_启用布局控制 ()
     */
    public static native void XWidget_EnableLayoutControl (int hXCGUI, boolean bEnable);
    /**
     该对象是否受布局控制
     @param hXCGUI 对象句柄
     @return 如果受布局控制返回 true，否则返回 false
      *窗口组件_是否布局控制 ()
     */
    public static native boolean XWidget_IsLayoutControl (int hXCGUI);
    /**
     获取父元素句柄
     @param hXCGUI 对象句柄
     @return 返回父元素句柄
      *窗口组件_取父元素 ()
     */
    public static native int XWidget_GetParentEle (int hXCGUI);
    /**
     获取父对象句柄（父可能是元素或窗口）
     @param hXCGUI 对象句柄
     @return 返回父对象句柄
      *窗口组件_取父句柄 ()
     */
    public static native int XWidget_GetParent (int hXCGUI);
    /**
     设置元素 ID
     @param hXCGUI 对象句柄
     @param nID ID 值
      *窗口组件_置 ID ()
     */
    public static native void XWidget_SetID (int hXCGUI, int nID);
    /**
     获取元素 ID
     @param hXCGUI 对象句柄
     @return 返回元素 ID
      *窗口组件_取 ID ()
     */
    public static native int XWidget_GetID (int hXCGUI);
    /**
     设置元素 UID（全局唯一标识符）
     @param hXCGUI 对象句柄
     @param nUID UID 值
      *窗口组件_置 UID ()
     */
    public static native void XWidget_SetUID (int hXCGUI, int nUID);
    /**
     获取元素 UID（全局唯一标识符）
     @param hXCGUI 对象句柄
     @return 返回元素 UID
      *窗口组件_取 UID ()
     */
    public static native int XWidget_GetUID (int hXCGUI);

    /**
     设置窗口组件的名称
     @param hXCGUI 窗口组件句柄
     @param pName 窗口组件名称字符串
     */
    public static native void XWidget_SetName (int hXCGUI, String pName);
    /**
     获取窗口组件的名称
     @param hXCGUI 窗口组件句柄
     @param dwSize 接收名称的缓冲区大小
     @return 窗口组件名称的字节数组
     */
    public static native byte [] XWidget_GetName (int hXCGUI, int dwSize);
    /**
     启用或禁用窗口组件布局项的自动换行功能
     @param hXCGUI 窗口组件句柄
     @param bWrap 是否启用自动换行（true：启用，false：禁用）
     */
    public static native void XWidget_LayoutItem_EnableWrap (int hXCGUI, boolean bWrap);
    /**
     启用或禁用窗口组件布局项的属性交换功能（根据水平垂直布局变换交换宽度、高度等属性）
     @param hXCGUI 窗口组件句柄
     @param bEnable 是否启用属性交换（true：启用，false：禁用）
     */
    public static native void XWidget_LayoutItem_EnableSwap (int hXCGUI, boolean bEnable);
    /**
     启用或禁用窗口组件布局项的向反方向对齐（浮动）功能
     @param hXCGUI 窗口组件句柄
     @param bFloat 是否启用浮动（true：启用，false：禁用）
     */
    public static native void XWidget_LayoutItem_EnableFloat (int hXCGUI, boolean bFloat);
    /**
     设置窗口组件布局项的宽度类型和宽度值
     @param hXCGUI 窗口组件句柄
     @param nType 宽度类型（layout_size_枚举值：固定、填充父、自动、比例、百分比、禁用）
     @param nWidth 宽度值
     */
    public static native void XWidget_LayoutItem_SetWidth (int hXCGUI, int nType, int nWidth);
    /**
     设置窗口组件布局项的高度类型和高度值
     @param hXCGUI 窗口组件句柄
     @param nType 高度类型（layout_size_枚举值：固定、填充父、自动、比例、百分比、禁用）
     @param nHeight 高度值
     */
    public static native void XWidget_LayoutItem_SetHeight (int hXCGUI, int nType, int nHeight);
    /**
     获取窗口组件布局项的宽度类型和宽度值
     @param hXCGUI 窗口组件句柄
     @param pType 接收宽度类型的数组
     @param pWidth 接收宽度值的数组
     */
    public static native void XWidget_LayoutItem_GetWidth (int hXCGUI, int pType, byte [] pWidth);
    /**
     获取窗口组件布局项的高度类型和高度值
     @param hXCGUI 窗口组件句柄
     @param pType 接收高度类型的数组
     @param pHeight 接收高度值的数组
     */
    public static native void XWidget_LayoutItem_GetHeight (int hXCGUI, int pType, byte [] pHeight);
    /**
     设置窗口组件布局项的轴对齐方式
     @param hXCGUI 窗口组件句柄
     @param nAlign 对齐方式（layout_align_axis_枚举值：开始、居中、末尾）
     */
    public static native void XWidget_LayoutItem_SetAlign (int hXCGUI, int nAlign);
    /**
     设置窗口组件布局项的外间距
     @param hXCGUI 窗口组件句柄
     @param left 左边间距大小
     @param top 上边间距大小
     @param right 右边间距大小
     @param bottom 下边间距大小
     */
    public static native void XWidget_LayoutItem_SetMargin (int hXCGUI, int left, int top, int right, int bottom);
    /**
     获取窗口组件布局项的外间距
     @param hXCGUI 窗口组件句柄
     @param pMargin 接收外间距的 TagMarginSize_结构体
     */
    public static native void XWidget_LayoutItem_GetMargin (int hXCGUI, TagMarginSize_ pMargin);
    /**
     设置窗口组件布局项的最小大小（仅针对缩放有效：自动、填充父、比例、百分比）
     @param hXCGUI 窗口组件句柄
     @param width 最小宽度
     @param height 最小高度
     */
    public static native void XWidget_LayoutItem_SetMinSize (int hXCGUI, int width, int height);
    /**
     设置窗口组件布局项的相对位置（值大于等于 0 有效）
     @param hXCGUI 窗口组件句柄
     @param left 左边距离
     @param top 上边距离
     @param right 右边距离
     @param bottom 下边距离
     */
    public static native void XWidget_LayoutItem_SetPosition (int hXCGUI, int left, int top, int right, int bottom);
    /**
     创建窗口的扩展方法，支持更多参数配置
     @param dwExStyle 窗口扩展样式
     @param dwStyle 窗口样式
     @param lpClassName 窗口类名
     @param x 窗口左上角 x 坐标
     @param y 窗口左上角 y 坐标
     @param cx 窗口宽度
     @param cy 窗口高度
     @param pTitle 窗口标题
     @param hWndParent 父窗口句柄（HWND）
     @param XCStyle GUI 库窗口样式（window_style_枚举值）
     @return 炫彩窗口资源句柄（HWINDOW）
     */
    public static native int XWnd_CreateEx (int dwExStyle, int dwStyle, String lpClassName, int x, int y, int cx, int cy, String pTitle, int hWndParent, int XCStyle);
    /**
     将外部窗口附加到炫彩窗口系统
     @param hWnd 要附加的外部窗口句柄（HWND）
     @param XCStyle GUI 库窗口样式（window_style_枚举值）
     @return 炫彩窗口句柄（HWINDOW）
     */
    public static native int XWnd_Attach (int hWnd, int XCStyle);
    /**
     将子对象添加到窗口
     @param hWindow 窗口句柄
     @param hChild 要添加的子对象句柄
     @return 成功返回 true，失败返回 false
     */
    public static native boolean XWnd_AddChild (int hWindow, int hChild);
    /**
     将子对象插入到窗口的指定位置
     @param hWindow 窗口句柄
     @param hChild 要插入的子对象句柄
     @param index 插入位置索引
     @return 成功返回 true，失败返回 false
     */
    public static native boolean XWnd_InsertChild (int hWindow, int hChild, int index);
    /**
     重绘窗口的指定区域
     @param hWindow 窗口句柄
     @param pRect 需要重绘的区域坐标
     @param bUpdate 是否立即重绘（true：立即重绘，false：放入消息队列延迟重绘）
     */
    public static native void XWnd_RedrawRect (int hWindow, TagRect pRect, boolean bUpdate);

    /**
     设置窗口的焦点元素
     @param hWindow 窗口句柄
     @param hFocusEle 将获得焦点的元素句柄
     */
    public static native void XWnd_SetFocusEle (int hWindow, int hFocusEle);
    /**
     获得拥有输入焦点的元素
     @param hWindow 窗口资源句柄
     @return 元素句柄
     */
    public static native int XWnd_GetFocusEle (int hWindow);
    /**
     获取当前鼠标所停留的元素
     @param hWindow 窗口资源句柄
     @return 鼠标停留元素句柄
     */
    public static native int XWnd_GetStayEle (int hWindow);
    /**
     居中窗口
     @param hWindow 窗口资源句柄
     */
    public static native void XWnd_Center (int hWindow);
    /**
     居中窗口并指定大小
     @param hWindow 窗口资源句柄
     @param width 窗口宽度
     @param height 窗口高度
     */
    public static native void XWnd_CenterEx (int hWindow, int width, int height);
    /**
     设置窗口鼠标光标
     @param hWindow 窗口句柄
     @param hCursor 鼠标光标句柄
     */
    public static native void XWnd_SetCursor (int hWindow, int hCursor);
    /**
     获取窗口的鼠标光标
     @param hWindow 窗口句柄
     @return 鼠标光标句柄
     */
    public static native int XWnd_GetCursor (int hWindow);
    /**
     获取窗口的某个整数属性（文档中未明确，需结合上下文确认）
     @param hWindow 窗口句柄
     @return 窗口的整数属性值
     */
    public static native int XWnd_Getint (int hWindow);
    /**
     启用拖动窗口边框
     @param hWindow 窗口句柄
     @param bEnable 是否启用
     */
    public static native void XWnd_EnableDragBorder (int hWindow, boolean bEnable);
    /**
     启用拖动窗口
     @param hWindow 窗口句柄
     @param bEnable 是否启用
     */
    public static native void XWnd_EnableDragWindow (int hWindow, boolean bEnable);
    /**
     启用拖动窗口标题栏
     @param hWindow 窗口句柄
     @param bEnable 是否启用
     */
    public static native void XWnd_EnableDragCaption (int hWindow, boolean bEnable);
    /**
     设置是否绘制窗口背景
     @param hWindow 窗口句柄
     @param bEnable 是否启用
     */
    public static native void XWnd_EnableDrawBk (int hWindow, boolean bEnable);
    /**
     设置当鼠标左键按下是否获得焦点
     @param hWindow 窗口句柄
     @param bEnable 是否启用
     */
    public static native void XWnd_EnableAutoFocus (int hWindow, boolean bEnable);
    /**
     允许窗口最大化
     @param hWindow 窗口句柄
     @param bEnable 是否启用
     */
    public static native void XWnd_EnableMaxWindow (int hWindow, boolean bEnable);
    /**
     限制窗口最小和最大尺寸
     @param hWindow 窗口句柄
     @param bEnable 是否启用
     */
    public static native void XWnd_EnableLimitWindowSize (int hWindow, boolean bEnable);
    /**
     启用布局功能
     @param hWindow 窗口句柄
     @param bEnable 是否启用
     */
    public static native void XWnd_EnableLayout (int hWindow, boolean bEnable);
    /**
     启用布局覆盖边框
     @param hWindow 窗口句柄
     @param bEnable 是否启用
     */
    public static native void XWnd_EnableLayoutOverlayBorder (int hWindow, boolean bEnable);
    /**
     显示布局边界
     @param hWindow 窗口句柄
     @param bEnable 是否启用
     */
    public static native void XWnd_ShowLayoutFrame (int hWindow, boolean bEnable);
    /**
     判断是否启用布局
     @param hWindow 窗口句柄
     @return 如果启用布局返回 TRUE，否则返回 FALSE
     */
    public static native boolean XWnd_IsEnableLayout (int hWindow);
    /**
     判断窗口是否最大化
     @param hWindow 窗口句柄
     @return 如果窗口最大化返回 TRUE，否则返回 FALSE
     */
    public static native boolean XWnd_IsMaxWindow (int hWindow);
    /**
     判断窗口边框是否可拖动
     @param hWindow 窗口句柄
     @return 如果窗口边框可拖动返回 true，否则返回 false
     */
    public static native boolean XWnd_IsDragBorder (int hWindow);
    /**
     判断窗口内容区是否可拖动
     @param hWindow 窗口句柄
     @return 如果窗口内容区可拖动返回 true，否则返回 false
     */
    public static native boolean XWnd_IsDragWindow (int hWindow);
    /**
     判断窗口标题是否可拖动
     @param hWindow 窗口句柄
     @return 如果窗口标题可拖动返回 true，否则返回 false
     */
    public static native boolean XWnd_IsDragCaption (int hWindow);
    /**
     设置窗口的鼠标捕获元素
     @param hWindow 窗口句柄
     @param hEle 将获得鼠标捕获的元素句柄
     */
    public static native void XWnd_SetCaptureEle (int hWindow, int hEle);
    /**
     获取当前窗口的鼠标捕获元素
     @param hWindow 窗口句柄
     @return 鼠标捕获的元素句柄
     */
    public static native int XWnd_GetCaptureEle (int hWindow);
    /**
     显示或隐藏窗口
     @param hWindow 窗口句柄
     @param bShow 是否显示窗口：true 为显示，false 为隐藏
     */
    public static native void XWnd_Show (int hWindow, boolean bShow);
    /**
     设置窗口的系统光标句柄
     @param hWindow 窗口句柄
     @param hCursor 系统光标句柄
     @return 返回先前的光标句柄
     */
    public static native int XWnd_SetCursorSys (int hWindow, int hCursor);
    /**
     设置窗口的字体
     @param hWindow 窗口句柄
     @param hFontx 炫彩字体句柄
     */
    public static native void XWnd_SetFont (int hWindow, int hFontx);
    /**
     设置窗口的文本颜色
     @param hWindow 窗口句柄
     @param color 颜色值，使用宏 RGBA () 生成
     */
    public static native void XWnd_SetTextColor (int hWindow, long color);
    /**
     获取窗口的文本颜色
     @param hWindow 窗口句柄
     @return 返回文本颜色值
     */
    public static native long XWnd_GetTextColor (int hWindow);
    /**
     获取窗口的文本颜色（优先从资源中获取）
     @param hWindow 窗口句柄
     @return 返回文本颜色值
     */
    public static native long XWnd_GetTextColorEx (int hWindow);
    /**
     设置窗口的 ID
     @param hWindow 窗口句柄
     @param nID 窗口 ID 值
     */
    public static native void XWnd_SetID (int hWindow, int nID);
    /**
     获取窗口的 ID
     @param hWindow 窗口句柄
     @return 返回窗口 ID 值
     */
    public static native int XWnd_GetID (int hWindow);
    /**
     设置窗口的名称
     @param hWindow 窗口句柄
     @param pName 窗口名称字符串
     */
    public static native void XWnd_SetName (int hWindow, String pName);
    /**
     获取窗口的名称
     @param hWindow 窗口句柄
     @param dwSize 接收名称的缓冲区大小
     @return 返回窗口名称的字节数组
     */
    public static native byte [] XWnd_GetName (int hWindow, int dwSize);
    /**
     设置窗口的边大小
     @param hWindow 窗口句柄
     @param left 窗口左边大小
     @param top 窗口上边大小
     @param right 窗口右边大小
     @param bottom 窗口底部大小
     */
    public static native void XWnd_SetBorderSize (int hWindow, int left, int top, int right, int bottom);
    /**
     设置窗口的内填充大小
     @param hWindow 窗口句柄
     @param left 左边填充大小
     @param top 上边填充大小
     @param right 右边填充大小
     @param bottom 下边填充大小
     */
    public static native void XWnd_SetPadding (int hWindow, int left, int top, int right, int bottom);
    /**
     获取窗口的内填充大小
     @param hWindow 窗口句柄
     @param pPadding 用于接收内填充大小的结构体
     */
    public static native void XWnd_GetPadding (int hWindow, TagPadDingSize pPadding);
    /**
     设置窗口的拖动边框大小
     @param hWindow 窗口句柄
     @param left 左边拖动边框大小
     @param top 上边拖动边框大小
     @param right 右边拖动边框大小
     @param bottom 底边拖动边框大小
     */
    public static native void XWnd_SetDragBorderSize (int hWindow, int left, int top, int right, int bottom);
    /**
     设置窗口标题内容（图标、标题、控制按钮）的外间距
     @param hWindow 窗口句柄
     @param left 左边间距
     @param top 上边间距
     @param right 右边间距
     @param bottom 下边间距
     */
    public static native void XWnd_SetCaptionMargin (int hWindow, int left, int top, int right, int bottom);

    /**
     设置窗口的最小宽度和高度
     @param hWindow 窗口句柄
     @param width 最小宽度
     @param height 最小高度
     **/
    public static native void XWnd_SetMinimumSize (int hWindow, int width, int height);
    /**
     获取窗口当前层子对象数量（不包含子元素的子元素）
     @param hWindow 窗口句柄
     @return 子对象数量
     **/
    public static native int XWnd_GetChildCount (int hWindow);
    /**
     通过索引获取窗口当前层的子对象
     @param hWindow 窗口句柄
     @param index 元素索引
     @return 对象句柄
     **/
    public static native int XWnd_GetChildByIndex (int hWindow, int index);
    /**
     通过 ID 获取窗口当前层的子对象（ID 必须大于 0）
     @param hWindow 窗口句柄
     @param nID 对象 ID
     @return 对象句柄
     **/
    public static native int XWnd_GetChildByID (int hWindow, int nID);
    /**
     获取窗口子对象通过对象 ID（如果对象不在该窗口上无效）
     @param hWindow 窗口句柄
     @param nID 对象 ID
     @return 对象句柄
     **/
    public static native int XWnd_GetChild (int hWindow, int nID);
    /**
     设置当前窗口 DPI，默认 DPI 为 96
     @param hWindow 窗口句柄
     @param nDPI DPI 值
     **/
    public static native void XWnd_SetDPI (int hWindow, int nDPI);
    /**
     获取当前窗口所在显示器 DPI
     @param hWindow 窗口句柄
     @return 窗口 DPI
     **/
    public static native int XWnd_GetDPI (int hWindow);
    /**
     设置炫彩窗口上的图标（非系统图标）
     @param hWindow 窗口句柄
     @param hImage 图标句柄
     **/
    public static native void XWnd_SetIcon (int hWindow, int hImage);
    /**
     设置炫彩窗口标题栏文本（非系统窗口标题）
     @param hWindow 窗口句柄
     @param pTitle 标题文本
     **/
    public static native void XWnd_SetTitle (int hWindow, String pTitle);
    /**
     设置窗口标题颜色
     @param hWindow 窗口句柄
     @param color 颜色值（请使用宏：RGBA ()）
     **/
    public static native void XWnd_SetTitleColor (int hWindow, long color);
    /**
     获取窗口控制按钮
     @param hWindow 窗口句柄
     @param nFlag 按钮类型（如 window_style_btn_min、window_style_btn_max、window_style_btn_close）
     @return 按钮句柄
     **/
    public static native int XWnd_GetButton (int hWindow, int nFlag);
    /**
     获取窗口标题文本
     @param hWindow 窗口句柄
     @param dwSize 缓冲区大小（字符为单位）
     @return 标题文本字节数组
     **/
    public static native byte [] XWnd_GetTitle (int hWindow, int dwSize);
    /**
     获取窗口标题颜色
     @param hWindow 窗口句柄
     @return 颜色值
     **/
    public static native long XWnd_GetTitleColor (int hWindow);
    /**
     关闭窗口，发送 WM_CLOSE 消息，拦截该消息可阻止窗口关闭
     @param hWindow 窗口句柄
     **/
    public static native void XWnd_CloseWindow (int hWindow);
    /**
     立即销毁窗口，函数返回时窗口已销毁
     @param hWindow 窗口句柄
     **/
    public static native void XWnd_DestroyWindow (int hWindow);
    /**
     创建插入符（基于元素坐标）
     @param hWindow 窗口句柄
     @param hEle 元素句柄
     @param x x 坐标
     @param y y 坐标
     @param width 宽度
     @param height 高度
     **/
    public static native void XWnd_CreateCaret (int hWindow, int hEle, int x, int y, int width, int height);

    /**
     * 设置窗口插入符位置和大小
     * @param hWindow 窗口句柄
     * @param x 插入符X坐标
     * @param y 插入符Y坐标
     * @param width 插入符宽度
     * @param height 插入符高度
     * @param bUpdate 是否立即更新UI
     */
    public static native void XWnd_SetCaretPos(int hWindow, int x, int y, int width, int height, boolean bUpdate);

    /**
     * 设置窗口插入符颜色
     * @param hWindow 窗口句柄
     * @param color 颜色值（使用RGBA宏生成）
     */
    public static native void XWnd_SetCaretColor(int hWindow, long color);

    /**
     * 显示或隐藏窗口插入符
     * @param hWindow 窗口句柄
     * @param bShow 是否显示插入符
     */
    public static native void XWnd_ShowCaret(int hWindow, boolean bShow);

    /**
     * 获取插入符元素句柄和信息
     * @param hWindow 窗口句柄
     * @return 插入符元素句柄
     */
    public static native int XWnd_GetCaretint(int hWindow);

    /**
     * 销毁窗口插入符
     * @param hWindow 窗口句柄
     */
    public static native void XWnd_DestroyCaret(int hWindow);

    /**
     * 获取窗口客户区坐标；如果是阴影窗口，不包含阴影部分
     * @param hWindow 窗口句柄
     * @param pRect 接收返回的客户区坐标
     * @return 成功返回true，否则返回false
     */
    public static native boolean XWnd_GetClientRect(int hWindow, TagRect pRect);

    /**
     * 获取窗口body区域坐标
     * @param hWindow 窗口句柄
     * @param pRect 接收返回的body区域坐标
     */
    public static native void XWnd_GetBodyRect(int hWindow, TagRect pRect);

    /**
     * 获取窗口布局区域坐标
     * @param hWindow 窗口句柄
     * @param pRect 接收返回的布局区域坐标
     */
    public static native void XWnd_GetLayoutRect(int hWindow, TagRect pRect);

    /**
     * 移动窗口到指定位置
     * @param hWindow 窗口句柄
     * @param x 窗口左上角X坐标
     * @param y 窗口左上角Y坐标
     */
    public static native void XWnd_SetPosition(int hWindow, int x, int y);

    /**
     * 获取鼠标光标位置（已做DPI适配）
     * @param hWindow 窗口句柄
     * @param pPt 接收返回的鼠标光标坐标
     * @return 成功返回true，否则返回false
     */
    public static native boolean XWnd_GetCursorPos(int hWindow, TagPoint pPt);

    /**
     * 将窗口客户区坐标转换为屏幕坐标（已做DPI适配）
     * @param hWindow 窗口句柄
     * @param pPt 要转换的坐标点，转换后的值仍存储在此对象中
     * @return 成功返回true，否则返回false
     */
    public static native boolean XWnd_ClientToScreen(int hWindow, TagPoint pPt);

    /**
     * 将屏幕坐标转换为窗口客户区坐标（已做DPI适配）
     * @param hWindow 窗口句柄
     * @param pPt 要转换的坐标点，转换后的值仍存储在此对象中
     * @return 成功返回true，否则返回false
     */
    public static native boolean XWnd_ScreenToClient(int hWindow, TagPoint pPt);

    /**
     * 将窗口客户区坐标转换为DPI缩放后的坐标
     * @param hWindow 窗口句柄
     * @param pRect 要转换的坐标区域，转换后的值仍存储在此对象中
     */
    public static native void XWnd_RectToDPI(int hWindow, TagRect pRect);

    /**
     * 将窗口客户区坐标点转换为DPI缩放后的坐标
     * @param hWindow 窗口句柄
     * @param pPt 要转换的坐标点，转换后的值仍存储在此对象中
     */
    public static native void XWnd_PointToDPI(int hWindow, TagPoint pPt);

    /**
     * 获取窗口坐标
     * @param hWindow 窗口句柄
     * @param pRect 接收返回的窗口坐标
     */
    public static native void XWnd_GetRect(int hWindow, TagRect pRect);

    /**
     * 设置窗口坐标
     * @param hWindow 窗口句柄
     * @param pRect 要设置的窗口坐标
     */
    public static native void XWnd_SetRect(int hWindow, TagRect pRect);

    /**
     * 设置窗口置顶或取消置顶
     * @param hWindow 窗口句柄
     * @param bTop true置顶，false取消置顶
     */
    public static native void XWnd_SetTop(int hWindow, boolean bTop);

    /**
     窗口_最大化或还原
     @param hWindow 窗口句柄
     @param bMaximize 是否最大化
     **/
    public static native void XWnd_MaxWindow (int hWindow, boolean bMaximize);
    /**
     窗口_置窗口位置 (封装系统 API SetWindowPos, 内部做了 DPI 适配)
     @param hWindow 窗口句柄
     @param hWndInsertAfter 前面窗口
     @param X X 坐标
     @param Y Y 坐标
     @param cx 宽度
     @param cy 高度
     @param uFlags 窗口大小调整和定位标志，详情见 MSDN
     @return 如果成功返回 TRUE, 否则返回 FALSE
     **/
    public static native boolean XWnd_SetWindowPos (int hWindow, int hWndInsertAfter, int X, int Y, int cx, int cy, long uFlags);
    /**
     窗口_置系统定时器
     @param hWindow 窗口句柄
     @param nIDEvent 定时器 ID
     @param uElapse 间隔值，单位毫秒
     @return 参见 MSDN
     **/
    public static native long XWnd_SetTimer (int hWindow, long nIDEvent, long uElapse);
    /**
     窗口_关闭系统定时器
     @param hWindow 窗口句柄
     @param nIDEvent 定时器 ID
     @return 参见 MSDN
     **/
    public static native boolean XWnd_KillTimer (int hWindow, long nIDEvent);
    /**
     窗口_置炫彩定时器 (非系统定时器)
     @param hWindow 窗口句柄
     @param nIDEvent 定时器 ID
     @param uElapse 间隔值，单位毫秒
     @return 成功返回 TRUE, 否则返回 FALSE
     **/
    public static native boolean XWnd_SetXCTimer (int hWindow, long nIDEvent, long uElapse);
    /**
     窗口_关闭炫彩定时器
     @param hWindow 窗口句柄
     @param nIDEvent 定时器 ID
     @return 成功返回 TRUE, 否则返回 FALSE
     **/
    public static native boolean XWnd_KillXCTimer (int hWindow, long nIDEvent);
    /**
     窗口_添加背景内容边框
     @param hWindow 窗口句柄
     @param nState 组合状态 参见文档: API 接口 组合状态
     @param color 颜色值，请使用宏: RGBA ()
     @param width 线宽
     **/
    public static native void XWnd_AddBkBorder (int hWindow, int nState, long color, int width);
    /**
     窗口_添加背景内容填充
     @param hWindow 窗口句柄
     @param nState 组合状态 参见文档: API 接口 组合状态
     @param color 颜色值，请使用宏: RGBA ()
     **/
    public static native void XWnd_AddBkFill (int hWindow, int nState, long color);
    /**
     窗口_添加背景内容图片
     @param hWindow 窗口句柄
     @param nState 组合状态 参见文档: API 接口 组合状态
     @param hImage 图片句柄
     **/
    public static native void XWnd_AddBkImage (int hWindow, int nState, int hImage);
    /**
     窗口_置背景内容
     @param hWindow 窗口句柄
     @param pText 背景内容字符串
     @return 返回设置的背景对象数量
     **/
    public static native int XWnd_SetBkInfo (int hWindow, String pText);
    /**
     窗口_取背景内容数量
     @param hWindow 窗口句柄
     @return 返回背景内容数量
     **/
    public static native int XWnd_GetBkInfoCount (int hWindow);
    /**
     窗口_清空背景内容；如果背景没有内容，将使用系统默认内容，以便保证背景正确
     @param hWindow 窗口句柄
     **/
    public static native void XWnd_ClearBkInfo (int hWindow);
    /**
     窗口_取背景管理器
     @param hWindow 窗口句柄
     @return 背景管理器句柄
     **/
    public static native int XWnd_GetBkManager (int hWindow);
    /**
     窗口_取背景管理器 (优先从资源中获取)
     @param hWindow 窗口句柄
     @return 背景管理器句柄
     **/
    public static native int XWnd_GetBkManagerEx (int hWindow);
    /**
     窗口_置背景管理器
     @param hWindow 窗口句柄
     @param hBkInfoM 背景管理器句柄
     **/
    public static native void XWnd_SetBkMagager (int hWindow, int hBkInfoM);
    /**
     窗口_置透明窗口的透明度 (设置后调用重绘窗口 API 来更新)
     @param hWindow 窗口句柄
     @param alpha 窗口透明度，范围 0-255 之间，0 透明，255 不透明
     **/
    public static native void XWnd_SetTransparentAlpha (int hWindow, byte alpha);

    /**
     创建模态窗口；当模态窗口关闭时，会自动销毁模态窗口资源句柄
     @param nWidth 宽度
     @param nHeight 高度
     @param pTitle 窗口标题内容
     @param hWndParent 父窗口句柄
     @param XCStyle GUI 库窗口样式
     @return 模态窗口句柄
     **/
    public static native int XModalWnd_Create (int nWidth, int nHeight, String pTitle, int hWndParent, int XCStyle);
    /**
     创建模态窗口，增强功能
     @param dwExStyle 窗口扩展样式
     @param dwStyle 窗口样式
     @param lpClassName 窗口类名
     @param x 窗口左上角 x 坐标
     @param y 窗口左上角 y 坐标
     @param cx 窗口宽度
     @param cy 窗口高度
     @param pTitle 窗口名
     @param hWndParent 父窗口
     @param XCStyle GUI 库窗口样式
     @return GUI 库窗口资源句柄
     **/
    public static native int XModalWnd_CreateEx (int dwExStyle, int dwStyle, String lpClassName, int x, int y, int cx, int cy, String pTitle, int hWndParent, int XCStyle);
    /**
     模态窗口_附加窗口
     @param hWnd 要附加的外部窗口句柄
     @param XCStyle GUI 库窗口样式
     @return 返回窗口句柄
     **/
    public static native int XModalWnd_Attach (int hWnd, int XCStyle);
    /**
     是否自动关闭窗口，当窗口失去焦点时
     @param hWindow 模态窗口句柄
     @param bEnable 开启开关
     **/
    public static native void XModalWnd_EnableAutoClose (int hWindow, boolean bEnable);
    /**
     当用户按 ESC 键时自动关闭模态窗口
     @param hWindow 模态窗口句柄
     @param bEnable 是否启用
     **/
    public static native void XModalWnd_EnableEscClose (int hWindow, boolean bEnable);
    /**
     启动显示模态窗口，当窗口关闭时返回
     @param hWindow 模态窗口句柄
     @return messageBox_flag_ok: 点击确定按钮退出.messageBox_flag_cancel: 点击取消按钮退出.messageBox_flag_other: 其他方式退出
     **/
    public static native int XModalWnd_DoModal (int hWindow);
    /**
     结束模态窗口
     @param hWindow 窗口句柄
     @param nResult XModalWnd_DoModal () 返回值
     **/
    public static native void XModalWnd_EndModal (int hWindow, int nResult);
    /**
     创建框架窗口
     @param x 窗口左上角 x 坐标
     @param y 窗口左上角 y 坐标
     @param cx 窗口宽度
     @param cy 窗口高度
     @param pTitle 窗口标题
     @param hWndParent 父窗口
     @param XCStyle GUI 库窗口样式
     @return GUI 库窗口资源句柄
     **/
    public static native int XFrameWnd_Create (int x, int y, int cx, int cy, String pTitle, int hWndParent, int XCStyle);
    /**
     创建框架窗口，增强功能
     @param dwExStyle 窗口扩展样式
     @param dwStyle 窗口样式
     @param lpClassName 窗口类名
     @param x 窗口左上角 x 坐标
     @param y 窗口左上角 y 坐标
     @param cx 窗口宽度
     @param cy 窗口高度
     @param pTitle 窗口名
     @param hWndParent 父窗口
     @param XCStyle GUI 库窗口样式
     @return GUI 库窗口资源句柄
     **/
    public static native int XFrameWnd_CreateEx (int dwExStyle, int dwStyle, String lpClassName, int x, int y, int cx, int cy, String pTitle, int hWndParent, int XCStyle);
    /**
     框架窗口_附加窗口
     @param hWnd 要附加的外部窗口句柄
     @param XCStyle GUI 库窗口样式
     @return 返回窗口句柄
     **/
    public static native int XFrameWnd_Attach (int hWnd, int XCStyle);
    /**
     用来布局窗格的区域坐标，不包含码头
     @param hWindow 窗口句柄
     @param pRect 返回坐标
     **/
    public static native void XFrameWnd_GetLayoutAreaRect (int hWindow, TagRect pRect);
    /**
     获取框架窗口主视图区域坐标
     @param hWindow 窗口句柄
     @param pRect 返回坐标
     **/
    public static native void XFrameWnd_GetViewRect (int hWindow, TagRect pRect);
    /**
     设置主视图元素
     @param hWindow 窗口句柄
     @param hEle 元素句柄
     **/
    public static native void XFrameWnd_SetView (int hWindow, int hEle);
    /**
     设置窗格分隔条颜色
     @param hWindow 窗口句柄
     @param color 颜色值，请使用宏: RGBA ()
     **/
    public static native void XFrameWnd_SetPaneSplitBarColor (int hWindow, long color);

    /**
     设置框架窗口窗格分隔条宽度
     @param hWindow 框架窗口句柄
     @param nWidth 分隔条宽度
     **/
    public static native void XFrameWnd_SetPaneSplitBarWidth (int hWindow, int nWidth);
    /**
     获取框架窗口窗格分隔条宽度
     @param hWindow 框架窗口句柄
     @return 返回分隔条宽度
     **/
    public static native int XFrameWnd_GetPaneSplitBarWidth (int hWindow);
    /**
     设置框架窗口窗格组 TabBar 高度
     @param hWindow 框架窗口句柄
     @param nHeight TabBar 高度
     **/
    public static native void XFrameWnd_SetTabBarHeight (int hWindow, int nHeight);
    /**
     获取拖动浮动窗格停留位置标识
     @param hWindow 框架窗口句柄
     @return 返回框架窗口单元格类型标识 FrameWndCellType
     **/
    public static native int XFrameWnd_GetDragFloatWndTopFlag (int hWindow);
    /**
     设置框架窗口窗格区域布局的外间距
     @param hWindow 框架窗口句柄
     @param left 左边间隔
     @param top 上边间隔
     @param right 右边间隔
     @param bottom 下边间隔
     **/
    public static native void XFrameWnd_SetLayoutMargin (int hWindow, int left, int top, int right, int bottom);
    /**
     获取框架窗口停靠码头元素
     @param hWindow 框架窗口句柄
     @param number 码头编号 (1: 左侧码头，2: 顶部码头，3: 右侧码头，4: 底部码头)
     @return 返回码头元素句柄，类型为基础元素
     **/
    public static native int XFrameWnd_GetDock (int hWindow, int number);
    /**
     保存布局信息到文件
     @param hWindow 框架窗口句柄
     @param pFileName 文件名，如果文件名为空，将使用默认文件名 frameWnd_layout.xml
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XFrameWnd_SaveLayoutToFile (int hWindow, String pFileName);
    /**
     加载布局信息文件
     @param hWindow 框架窗口句柄
     @param aPaneList 窗格句柄数组
     @param nEleCount 窗格数量
     @param pFileName 文件名，如果文件名为空，将使用默认文件名 frameWnd_layout.xml
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XFrameWnd_LoadLayoutFile (int hWindow, byte [] aPaneList, int nEleCount, String pFileName);
    /**
     添加窗格到框架窗口
     @param hWindow 框架窗口句柄
     @param hPaneDest 目标窗格句柄
     @param hPaneNew 要添加的窗格句柄
     @param align 对齐方式 PaneAlign
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XFrameWnd_AddPane (int hWindow, int hPaneDest, int hPaneNew, int align);
    /**
     合并窗格
     @param hWindow 框架窗口句柄
     @param hPaneDest 目标窗格句柄
     @param hPaneNew 要合并的窗格句柄
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XFrameWnd_MergePane (int hWindow, int hPaneDest, int hPaneNew);
    /**
     添加带图标的菜单项
     @param hMenu 菜单句柄
     @param nID 项 ID
     @param pText 文本内容
     @param nParentID 父项 ID
     @param hImage 菜单项图标句柄
     @param nFlags 项标识 MenuItemFlag
     **/
    public static native void XMenu_AddItemIcon (int hMenu, int nID, String pText, int nParentID, int hImage, int nFlags);
    /**
     插入菜单项
     @param hMenu 菜单句柄
     @param nID 项 ID
     @param pText 文本内容
     @param nFlags 项标识 MenuItemFlag
     @param insertID 插入位置 ID
     **/
    public static native void XMenu_InsertItem (int hMenu, int nID, String pText, int nFlags, int insertID);
    /**
     插入带图标的菜单项
     @param hMenu 菜单句柄
     @param nID 项 ID
     @param pText 文本内容
     @param hIcon 菜单项图标句柄
     @param nFlags 项标识 MenuItemFlag
     @param insertID 插入位置 ID
     **/
    public static native void XMenu_InsertItemIcon (int hMenu, int nID, String pText, int hIcon, int nFlags, int insertID);
    /**
     获取第一个子项
     @param hMenu 菜单句柄
     @param nID 父项 ID
     @return 返回子项 ID，失败返回 XC_ID_ERROR
     **/
    public static native int XMenu_GetFirstChildItem (int hMenu, int nID);
    /**
     获取末尾子项
     @param hMenu 菜单句柄
     @param nID 父项 ID
     @return 返回末尾子项 ID，失败返回 XC_ID_ERROR
     **/
    public static native int XMenu_GetEndChildItem (int hMenu, int nID);
    /**
     获取上一个兄弟项
     @param hMenu 菜单句柄
     @param nID 项 ID
     @return 返回上一个兄弟项 ID，失败返回 XC_ID_ERROR
     **/
    public static native int XMenu_GetPrevSiblingItem (int hMenu, int nID);
    /**
     获取下一个兄弟项
     @param hMenu 菜单句柄
     @param nID 项 ID
     @return 返回下一个兄弟项 ID
     **/
    public static native int XMenu_GetNextSiblingItem (int hMenu, int nID);
    /**
     获取父项
     @param hMenu 菜单句柄
     @param nID 项 ID
     @return 返回父项 ID，错误返回 - 1
     **/
    public static native int XMenu_GetParentItem (int hMenu, int nID);
    /**
     获取菜单所属菜单条
     @param hMenu 菜单句柄
     @return 返回菜单条句柄
     **/
    public static native int XMenu_GetMenuBar (int hMenu);
    /**
     设置菜单背景图片
     @param hMenu 菜单句柄
     @param hImage 图片句柄
     **/
    public static native void XMenu_SetBkImage (int hMenu, int hImage);

    /**
     设置菜单项文本
     @param hMenu 菜单句柄
     @param nID 项 ID
     @param pText 文本内容
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XMenu_SetItemText (int hMenu, int nID, String pText);
    /**
     获取菜单项文本
     @param hMenu 菜单句柄
     @param nID 项 ID
     @param dwSize 缓冲区大小
     @return 文本内容的字节数组
     */
    public static native byte [] XMenu_GetItemText (int hMenu, int nID, int dwSize);
    /**
     获取菜单项文本长度
     @param hMenu 菜单句柄
     @param nID 项 ID
     @return 文本长度
     */
    public static native int XMenu_GetItemTextLength (int hMenu, int nID);
    /**
     设置菜单项图标
     @param hMenu 菜单句柄
     @param nID 项 ID
     @param hIcon 图标句柄
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XMenu_SetItemIcon (int hMenu, int nID, int hIcon);
    /**
     设置菜单项标识
     @param hMenu 菜单句柄
     @param nID 项 ID
     @param uFlags 标识，参见宏定义 menu_item_flag_
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XMenu_SetItemFlags (int hMenu, int nID, int uFlags);
    /**
     设置项高度
     @param hMenu 菜单句柄
     @param height 高度
     */
    public static native void XMenu_SetItemHeight (int hMenu, int height);
    /**
     获取项高度
     @param hMenu 菜单句柄
     @return 项高度
     */
    public static native int XMenu_GetItemHeight (int hMenu);
    /**
     设置项宽度（此宽度为文本显示区域宽度，不包含侧边条和与文本间隔）
     @param hMenu 菜单句柄
     @param nID 项 ID
     @param nWidth 指定文本区域宽度
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XMenu_SetItemWidth (int hMenu, int nID, int nWidth);
    /**
     设置菜单边框颜色
     @param hMenu 菜单句柄
     @param crColor 颜色值，使用宏 RGBA ()
     */
    public static native void XMenu_SetBorderColor (int hMenu, long crColor);
    /**
     设置弹出菜单窗口边框大小
     @param hMenu 菜单句柄
     @param nLeft 左边大小
     @param nTop 上边大小
     @param nRight 右边大小
     @param nBottom 下边大小
     */
    public static native void XMenu_SetBorderSize (int hMenu, int nLeft, int nTop, int nRight, int nBottom);
    /**
     获取左侧区域宽度
     @param hMenu 菜单句柄
     @return 左侧区域宽度
     */
    public static native int XMenu_GetLeftWidth (int hMenu);
    /**
     获取菜单项文本左间隔
     @param hMenu 菜单句柄
     @return 菜单项文本左间隔大小
     */
    public static native int XMenu_GetLeftSpaceText (int hMenu);
    /**
     获取菜单项数量（包含子菜单项）
     @param hMenu 菜单句柄
     @return 菜单项数量
     */
    public static native int XMenu_GetItemCount (int hMenu);
    /**
     设置菜单项勾选状态
     @param hMenu 菜单句柄
     @param nID 菜单项 ID
     @param bCheck 勾选为 true，非勾选为 false
     @return 如果勾选返回 true，否则返回 false
     */
    public static native boolean XMenu_SetItemCheck (int hMenu, int nID, boolean bCheck);
    /**
     判断菜单项是否勾选
     @param hMenu 菜单句柄
     @param nID 菜单项 ID
     @return 如果勾选返回 true，否则返回 false
     */
    public static native boolean XMenu_IsItemCheck (int hMenu, int nID);
    /**
     启用布局盒子的水平布局
     @param hLayoutBox 窗口或布局元素或布局框架句柄
     @param bEnable 是否启用
     */
    public static native void XLayoutBox_EnableHorizon (int hLayoutBox, boolean bEnable);
    /**
     启用布局盒子的自动换行
     @param hLayoutBox 窗口或布局元素或布局框架句柄
     @param bEnable 是否启用
     */
    public static native void XLayoutBox_EnableAutoWrap (int hLayoutBox, boolean bEnable);
    /**
     启用布局盒子的溢出隐藏
     @param hLayoutBox 窗口或布局元素或布局框架句柄
     @param bEnable 是否启用
     */
    public static native void XLayoutBox_EnableOverflowHide (int hLayoutBox, boolean bEnable);
    /**
     设置布局盒子的水平对齐方式
     @param hLayoutBox 窗口或布局元素或布局框架句柄
     @param nAlign 对齐方式，参见宏定义 layout_align_
     */
    public static native void XLayoutBox_SetAlignH (int hLayoutBox, int nAlign);
    /**
     设置布局盒子的垂直对齐方式
     @param hLayoutBox 窗口或布局元素或布局框架句柄
     @param nAlign 对齐方式，参见宏定义 layout_align_
     */
    public static native void XLayoutBox_SetAlignV (int hLayoutBox, int nAlign);

    /**
     布局盒子_置对齐基线
     @param hLayoutBox 布局盒子句柄（窗口或布局元素或布局框架句柄）
     @param nAlign 对齐方式
     **/
    public static native void XLayoutBox_SetAlignBaseline (int hLayoutBox, int nAlign);
    /**
     布局盒子_置间距
     @param hLayoutBox 布局盒子句柄
     @param nSpace 项间距大小
     **/
    public static native void XLayoutBox_SetSpace (int hLayoutBox, int nSpace);
    /**
     布局盒子_置行距
     @param hLayoutBox 布局盒子句柄
     @param nSpace 行间距大小
     **/
    public static native void XLayoutBox_SetSpaceRow (int hLayoutBox, int nSpace);
    /**
     元素_置宽度
     @param hEle 元素句柄
     @param nWidth 宽度
     **/
    public static native void XEle_SetWidth (int hEle, int nWidth);
    /**
     元素_置高度
     @param hEle 元素句柄
     @param nHeight 高度
     **/
    public static native void XEle_SetHeight (int hEle, int nHeight);
    /**
     元素_取宽度
     @param hEle 元素句柄
     @return 元素宽度
     **/
    public static native int XEle_GetWidth (int hEle);
    /**
     元素_取高度
     @param hEle 元素句柄
     @return 元素高度
     **/
    public static native int XEle_GetHeight (int hEle);
    /**
     元素_取光标
     @param hEle 元素句柄
     @return 鼠标光标句柄
     **/
    public static native int XEle_GetCursor (int hEle);
    /**
     元素_置光标
     @param hEle 元素句柄
     @param hCursor 鼠标光标句柄
     **/
    public static native void XEle_SetCursor (int hEle, int hCursor);
    /**
     元素_置光标
     @param hEle 元素句柄
     @param hCursor 鼠标光标句柄
     **/
    public static native void XEle_SetCursor (int hEle, long hCursor);
    /**
     元素_添加子对象
     @param hEle 元素句柄
     @param hChild 要添加的子元素句柄或形状对象句柄
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XEle_AddChild (int hEle, int hChild);
    /**
     元素_插入子对象到指定位置
     @param hEle 元素句柄
     @param hChild 要插入的元素句柄或形状对象句柄
     @param index 插入位置索引
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XEle_InsertChild (int hEle, int hChild, int index);
    /**
     元素_置坐标扩展
     @param hEle 元素句柄
     @param x X 坐标
     @param y Y 坐标
     @param cx 宽度
     @param cy 高度
     @param bRedraw 是否重绘
     @param nFlags 调整布局标识位
     @param nAdjustNo 调整布局流水号
     @return 如果坐标未改变返回 0，如果大小改变返回 2（触发 XE_SIZE），否则返回 1
     **/
    public static native int XEle_SetRectEx (int hEle, int x, int y, int cx, int cy, boolean bRedraw, int nFlags, long nAdjustNo);
    /**
     元素_置逻辑坐标
     @param hEle 元素句柄
     @param pRect 逻辑坐标
     @param bRedraw 是否重绘
     @param nFlags 调整布局标识位
     @param nAdjustNo 调整布局流水号
     @return 如果坐标未改变返回 0，如果大小改变返回 2（触发 XE_SIZE），否则返回 1
     **/
    public static native int XEle_SetRectLogic (int hEle, TagRect pRect, boolean bRedraw, int nFlags, long nAdjustNo);
    /**
     元素_置位置
     @param hEle 元素句柄
     @param x X 坐标
     @param y Y 坐标
     @param bRedraw 是否重绘
     @param nFlags 调整布局标识位
     @param nAdjustNo 调整布局流水号
     @return 如果坐标未改变返回 0，如果大小改变返回 2（触发 XE_SIZE），否则返回 1
     **/
    public static native int XEle_SetPosition (int hEle, int x, int y, boolean bRedraw, int nFlags, long nAdjustNo);
    /**
     元素_置位置逻辑
     @param hEle 元素句柄
     @param x 逻辑 X 坐标
     @param y 逻辑 Y 坐标
     @param bRedraw 是否重绘
     @param nFlags 调整布局标识位
     @param nAdjustNo 调整布局流水号
     @return 如果坐标未改变返回 0，如果大小改变返回 2（触发 XE_SIZE），否则返回 1
     **/
    public static native int XEle_SetPositionLogic (int hEle, int x, int y, boolean bRedraw, int nFlags, long nAdjustNo);
    /**
     元素_取位置
     @param hEle 元素句柄
     @param pOutX 接收 X 坐标的数组
     @param pOutY 接收 Y 坐标的数组
     **/
    public static native void XEle_GetPosition (int hEle, byte [] pOutX, byte [] pOutY);
    /**
     元素_置大小
     @param hEle 元素句柄
     @param nWidth 宽度
     @param nHeight 高度
     @param bRedraw 是否重绘
     @param nFlags 调整布局标识位
     @param nAdjustNo 调整布局流水号
     @return 如果坐标未改变返回 0，如果大小改变返回 2（触发 XE_SIZE），否则返回 1
     **/
    public static native int XEle_SetSize (int hEle, int nWidth, int nHeight, boolean bRedraw, int nFlags, long nAdjustNo);
    /**
     元素_取大小
     @param hEle 元素句柄
     @param pOutWidth 接收宽度的数组
     @param pOutHeight 接收高度的数组
     **/
    public static native void XEle_GetSize (int hEle, byte [] pOutWidth, byte [] pOutHeight);
    /**
     元素_是否绘制焦点
     @param hEle 元素句柄
     @return 绘制焦点返回 true，否则返回 false
     **/
    public static native boolean XEle_IsDrawFocus (int hEle);
    /**
     元素_是否启用
     @param hEle 元素句柄
     @return 启用状态返回 true，否则返回 false
     **/
    public static native boolean XEle_IsEnable (int hEle);

    /**
     判断元素是否启用焦点
     @param hEle 元素句柄
     @return 如果启用焦点返回 TRUE，否则返回 FALSE
     */
    public static native boolean XEle_IsEnableFocus (int hEle);
    /**
     判断元素是否启用鼠标穿透
     @param hEle 元素句柄
     @return 如果启用鼠标穿透返回 TRUE，否则返回 FALSE
     */
    public static native boolean XEle_IsMouseThrough (int hEle);
    /**
     判断元素背景是否透明
     @param hEle 元素句柄
     @return 如果背景透明返回 TRUE，否则返回 FALSE
     */
    public static native boolean XEle_IsBkTransparent (int hEle);
    /**
     判断是否启用了 XE_PAINT_END 事件
     @param hEle 元素句柄
     @return 如果启用返回 TRUE，否则返回 FALSE
     */
    public static native boolean XEle_IsEnableEvent_XE_PAINT_END (int hEle);
    /**
     判断元素是否接受 Tab 键输入
     @param hEle 元素句柄
     @return 接受 Tab 键输入返回 TRUE，否则返回 FALSE
     */
    public static native boolean XEle_IsKeyTab (int hEle);
    /**
     判断元素是否接受通过键盘切换焦点（方向键、TAB 键）
     @param hEle 元素句柄
     @return 接受切换焦点返回 TRUE，否则返回 FALSE
     */
    public static native boolean XEle_IsSwitchFocus (int hEle);
    /**
     判断是否启用鼠标滚动事件
     @param hEle 元素句柄
     @return 启用返回 TRUE，否则返回 FALSE
     */
    public static native boolean XEle_IsEnable_XE_MOUSEWHEEL (int hEle);
    /**
     判断 hChildEle 是否为 hEle 的子元素
     @param hEle 元素句柄
     @param hChildEle 子元素句柄
     @return 是子元素返回 TRUE，否则返回 FALSE
     */
    public static native boolean XEle_IsChildEle (int hEle, int hChildEle);
    /**
     判断是否启用画布
     @param hEle 元素句柄
     @return 启用画布返回 TRUE，否则返回 FALSE
     */
    public static native boolean XEle_IsEnableCanvas (int hEle);
    /**
     判断元素是否拥有焦点
     @param hEle 元素句柄
     @return 拥有焦点返回 TRUE，否则返回 FALSE
     */
    public static native boolean XEle_IsFocus (int hEle);
    /**
     判断该元素或其 i 元素是否拥有焦点
     @param hEle 元素句柄
     @return 拥有焦点返回 TRUE，否则返回 FALSE
     */
    public static native boolean XEle_IsFocusEx (int hEle);
    /**
     启用或禁用元素
     @param hEle 元素句柄
     @param bEnable 是否启用
     */
    public static native void XEle_Enable (int hEle, boolean bEnable);
    /**
     启用焦点
     @param hEle 元素句柄
     @param bEnable 是否启用
     */
    public static native void XEle_EnableFocus (int hEle, boolean bEnable);
    /**
     启用绘制焦点
     @param hEle 元素句柄
     @param bEnable 是否启用
     */
    public static native void XEle_EnableDrawFocus (int hEle, boolean bEnable);
    /**
     启用或禁用绘制默认边框
     @param hEle 元素句柄
     @param bEnable 是否启用
     */
    public static native void XEle_EnableDrawBorder (int hEle, boolean bEnable);
    /**
     启用背景透明
     @param hEle 元素句柄
     @param bEnable 是否启用
     */
    public static native void XEle_EnableBkTransparent (int hEle, boolean bEnable);
    /**
     启用鼠标穿透，如果启用，该元素不能接收到鼠标事件，但是他的子元素不受影响，仍然可以接收鼠标事件
     @param hEle 元素句柄
     @param bEnable 是否启用
     */
    public static native void XEle_EnableMouseThrough (int hEle, boolean bEnable);
    /**
     启用接收 Tab 输入
     @param hEle 元素句柄
     @param bEnable 是否启用
     */
    public static native void XEle_EnableKeyTab (int hEle, boolean bEnable);
    /**
     启用接受通过键盘切换焦点
     @param hEle 元素句柄
     @param bEnable 是否启用
     */
    public static native void XEle_EnableSwitchFocus (int hEle, boolean bEnable);
    /**
     移除元素，但不销毁
     @param hEle 元素句柄
     */
    public static native void XEle_Remove (int hEle);

    /**
     元素_置 Z 序 ()
     @param hEle 元素句柄
     @param index 位置索引
     @return 成功返回 TRUE 否则返回 FALSE
     **/
    public static native boolean XEle_SetZOrder (int hEle, int index);
    /**
     元素_置 Z 序扩展 ()
     @param hEle 元素句柄
     @param hDestEle 目标元素句柄
     @param nType Z 序位置类型
     @return 成功返回 TRUE 否则返回 FALSE
     **/
    public static native boolean XEle_SetZOrderEx (int hEle, int hDestEle, int nType);
    /**
     元素_取 Z 序 ()
     @param hEle 元素句柄
     @return 成功返回 Z 序索引值，失败返回 XC_ID_ERROR
     **/
    public static native int XEle_GetZOrder (int hEle);
    /**
     元素_启用置顶 ()
     @param hEle 元素句柄
     @param bTopmost 是否置顶
     @return 成功返回 TRUE 否则返回 FALSE
     **/
    public static native boolean XEle_EnableTopmost (int hEle, boolean bTopmost);
    /**
     元素_取子对象数量 ()
     @param hEle 元素句柄
     @return 返回子元素数量
     **/
    public static native int XEle_GetChildCount (int hEle);
    /**
     元素_取子对象从索引 ()
     @param hEle 元素句柄
     @param index 子对象索引
     @return 返回子对象句柄
     **/
    public static native int XEle_GetChildByIndex (int hEle, int index);
    /**
     元素_取子对象从 ID ()
     @param hEle 元素句柄
     @param nID 子对象 ID
     @return 返回子对象句柄
     **/
    public static native int XEle_GetChildByID (int hEle, int nID);
    /**
     元素_置边框大小 ()
     @param hEle 元素句柄
     @param left 左边大小
     @param top 上边大小
     @param right 右边大小
     @param bottom 下边大小
     **/
    public static native void XEle_SetBorderSize (int hEle, int left, int top, int right, int bottom);
    /**
     元素_取边框大小 ()
     @param hEle 元素句柄
     @param pBorder 接收边框大小的结构体
     **/
    public static native void XEle_GetBorderSize (int hEle, TagBorderSize pBorder);
    /**
     元素_置内填充大小 ()
     @param hEle 元素句柄
     @param left 左边内填充大小
     @param top 上边内填充大小
     @param right 右边内填充大小
     @param bottom 下边内填充大小
     **/
    public static native void XEle_SetPadding (int hEle, int left, int top, int right, int bottom);
    /**
     元素_置拖动边框 ()
     @param hEle 元素句柄
     @param nFlags 边框位置组合标志
     **/
    public static native void XEle_SetDragBorder (int hEle, int nFlags);
    /**
     元素_置拖动边框绑定元素 ()
     @param hEle 元素句柄
     @param nFlags 边框位置标识
     @param hBindEle 绑定的元素句柄
     @param nSpace 元素间隔大小
     **/
    public static native void XEle_SetDragBorderBindEle (int hEle, int nFlags, int hBindEle, int nSpace);
    /**
     元素_置最小大小 ()
     @param hEle 元素句柄
     @param nWidth 最小宽度
     @param nHeight 最小高度
     **/
    public static native void XEle_SetMinSize (int hEle, int nWidth, int nHeight);
    /**
     元素_置最大大小 ()
     @param hEle 元素句柄
     @param nWidth 最大宽度
     @param nHeight 最大高度
     **/
    public static native void XEle_SetMaxSize (int hEle, int nWidth, int nHeight);
    /**
     元素_置锁定滚动 ()
     @param hEle 元素句柄
     @param bHorizon 是否锁定水平滚动
     @param bVertical 是否锁定垂直滚动
     **/
    public static native void XEle_SetLockScroll (int hEle, boolean bHorizon, boolean bVertical);
    /**
     元素_置文本颜色 ()
     @param hEle 元素句柄
     @param color 颜色值，使用宏 RGBA ()
     **/
    public static native void XEle_SetTextColor (int hEle, long color);
    /**
     元素_取文本颜色扩展 ()
     @param hEle 元素句柄
     @return 文本颜色值，优先从资源中获取
     **/
    public static native long XEle_GetTextColorEx (int hEle);
    /**
     元素_取焦点边框颜色 ()
     @param hEle 元素句柄
     @return 返回焦点边框颜色值
     **/
    public static native long XEle_GetFocusBorderColor (int hEle);
    /**
     元素_置字体 ()
     @param hEle 元素句柄
     @param hFontx 炫彩字体句柄
     **/
    public static native void XEle_SetFont (int hEle, int hFontx);
    /**
     元素_取字体 ()
     @param hEle 元素句柄
     @return 返回炫彩字体句柄
     **/
    public static native int XEle_GetFont (int hEle);

    /**
     获取元素字体，优先从资源中获取
     @param hEle 元素句柄
     @return 炫彩字体句柄
     */
    public static native int XEle_GetFontEx (int hEle);
    /**
     设置元素透明度
     @param hEle 元素句柄
     @param alpha 透明度
     */
    public static native void XEle_SetAlpha (int hEle, byte alpha);
    /**
     获取元素透明度
     @param hEle 元素句柄
     @return 透明度
     */
    public static native byte XEle_GetAlpha (int hEle);
    /**
     添加元素背景边框
     @param hEle 元素句柄
     @param nState 组合状态
     @param color 颜色值（使用宏 RGBA () 生成）
     @param width 线宽
     */
    public static native void XEle_AddBkBorder (int hEle, int nState, long color, int width);
    /**
     添加元素背景填充
     @param hEle 元素句柄
     @param nState 组合状态
     @param color 颜色值（使用宏 RGBA () 生成）
     */
    public static native void XEle_AddBkFill (int hEle, int nState, long color);
    /**
     添加元素背景图片
     @param hEle 元素句柄
     @param nState 组合状态
     @param hImage 图片句柄
     */
    public static native void XEle_AddBkImage (int hEle, int nState, int hImage);
    /**
     设置元素背景内容
     @param hEle 元素句柄
     @param pText 背景内容字符串
     @return 设置的背景对象数量
     */
    public static native int XEle_SetBkInfo (int hEle, String pText);
    /**
     获取元素背景内容数量
     @param hEle 元素句柄
     @return 背景内容数量
     */
    public static native int XEle_GetBkInfoCount (int hEle);
    /**
     清空元素背景内容；如果背景没有内容，将使用系统默认内容，以便保证背景正确
     @param hEle 元素句柄
     */
    public static native void XEle_ClearBkInfo (int hEle);
    /**
     获取元素背景管理器
     @param hEle 元素句柄
     @return 背景管理器句柄
     */
    public static native int XEle_GetBkManager (int hEle);
    /**
     获取元素背景管理器，优先从资源中获取
     @param hEle 元素句柄
     @return 背景管理器句柄
     */
    public static native int XEle_GetBkManagerEx (int hEle);
    /**
     设置元素背景管理器
     @param hEle 元素句柄
     @param hBkInfoM 背景管理器句柄
     */
    public static native void XEle_SetBkManager (int hEle, int hBkInfoM);
    /**
     获取元素组合状态
     @param hEle 元素句柄
     @return 元素组合状态标志
     */
    public static native int XEle_GetStateFlags (int hEle);
    /**
     绘制元素焦点
     @param hEle 元素句柄
     @param hDraw 图形绘制句柄
     @param pRect 区域坐标
     @return 绘制成功返回 true，不需要绘制焦点返回 false
     */
    public static native boolean XEle_DrawFocus (int hEle, long hDraw, TagRect pRect);
    /**
     在自绘事件函数中，用户手动调用绘制元素，以便控制绘制顺序
     @param hEle 元素句柄
     @param hDraw 图形绘制句柄
     */
    public static native void XEle_DrawEle (int hEle, long hDraw);
    /**
     设置元素鼠标捕获
     @param hEle 元素句柄
     @param b true 设置，false 取消
     */
    public static native void XEle_SetCapture (int hEle, boolean b);
    /**
     启用或关闭元素透明通道，如果启用，将强制设置元素背景不透明，默认为启用，此功能是为了兼容 GDI 不支持透明通道问题
     @param hEle 元素句柄
     @param bEnable 是否启用
     */
    public static native void XEle_EnableTransparentChannel (int hEle, boolean bEnable);
    /**
     关闭元素炫彩定时器
     @param hEle 元素句柄
     @param nIDEvent 定时器 ID
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XEle_KillXCTimer (int hEle, long nIDEvent);
    /**
     设置元素工具提示内容及文本对齐方式
     @param hEle 元素句柄
     @param pText 工具提示内容
     @param nTextAlign 文本对齐方式
     */
    public static native void XEle_SetToolTipEx (int hEle, String pText, int nTextAlign);

    /**
     元素_弹出工具提示
     @param hEle 元素句柄
     @param x X 坐标
     @param y Y 坐标
     **/
    public static native void XEle_PopupToolTip (int hEle, int x, int y);
    /**
     元素_调整布局
     @param hEle 元素句柄
     @param nAdjustNo 调整布局流水号
     **/
    public static native void XEle_AdjustLayout (int hEle, long nAdjustNo);
    /**
     元素_调整布局扩展
     @param hEle 元素句柄
     @param nFlags 调整标识 @ref adjustLayout_
     @param nAdjustNo 调整布局流水号
     **/
    public static native void XEle_AdjustLayoutEx (int hEle, int nFlags, long nAdjustNo);
    /**
     布局_创建
     @param x 元素 x 坐标
     @param y 元素 y 坐标
     @param cx 宽度
     @param cy 高度
     @param hParent 父为窗口句柄或元素句柄
     @return 元素句柄
     **/
    public static native int XLayout_Create (int x, int y, int cx, int cy, int hParent);
    /**
     布局_创建扩展
     @param hParent 父为窗口句柄或元素句柄
     @return 元素句柄
     **/
    public static native int XLayout_CreateEx (int hParent);
    /**
     布局_启用
     @param hEle 元素句柄
     @param bEnable 是否启用
     **/
    public static native void XLayout_EnableLayout (int hEle, boolean bEnable);
    /**
     布局_是否启用
     @param hEle 元素句柄
     @return 如果启用布局返回 TRUE, 否则返回 FALSE
     **/
    public static native boolean XLayout_IsEnableLayout (int hEle);
    /**
     布局_显示布局边界
     @param hEle 元素句柄
     @param bEnable 是否显示
     **/
    public static native void XLayout_ShowLayoutFrame (int hEle, boolean bEnable);
    /**
     布局_取内宽度
     @param hEle 元素句柄
     @return 返回宽度
     **/
    public static native int XLayout_GetWidthIn (int hEle);
    /**
     布局_取内高度
     @param hEle 元素句柄
     @return 返回高度
     **/
    public static native int XLayout_GetHeightIn (int hEle);
    /**
     布局框架_创建
     @param x 元素 x 坐标
     @param y 元素 y 坐标
     @param cx 宽度
     @param cy 高度
     @param hParent 父为窗口句柄或元素句柄
     @return 元素句柄
     **/
    public static native int XLayoutFrame_Create (int x, int y, int cx, int cy, int hParent);
    /**
     布局框架_创建扩展
     @param hParent 父为窗口句柄或元素句柄
     @return 元素句柄
     **/
    public static native int XLayoutFrame_CreateEx (int hParent);
    /**
     布局框架_启用
     @param hEle 元素句柄
     @param bEnable 是否启用
     **/
    public static native void XLayoutFrame_EnableLayout (int hEle, boolean bEnable);
    /**
     布局框架_是否启用
     @param hEle 元素句柄
     @return 如果启用布局返回 TRUE, 否则返回 FALSE
     **/
    public static native boolean XLayoutFrame_IsEnableLayout (int hEle);
    /**
     布局框架_显示布局边界
     @param hEle 元素句柄
     @param bEnable 是否显示
     **/
    public static native void XLayoutFrame_ShowLayoutFrame (int hEle, boolean bEnable);
    /**
     布局框架_取内宽度
     @param hEle 元素句柄
     @return 返回宽度
     **/
    public static native int XLayoutFrame_GetWidthIn (int hEle);
    /**
     布局框架_取内高度
     @param hEle 元素句柄
     @return 返回高度
     **/
    public static native int XLayoutFrame_GetHeightIn (int hEle);
    /**
     滚动视_创建
     @param x 元素 x 坐标
     @param y 元素 y 坐标
     @param cx 宽度
     @param cy 高度
     @param hParent 父是窗口资源句柄或 UI 元素资源句柄。如果是窗口资源句柄将被添加到窗口，如果是元素资源句柄将被添加到元素
     @return 元素句柄
     **/
    public static native int XSView_Create (int x, int y, int cx, int cy, int hParent);
    /**
     滚动视_置视图大小
     @param hEle 元素句柄
     @param cx 宽度（此宽度不包含边框和内填充）
     @param cy 高度（此高度不包含边框和内填充）
     @return 如果内容改变返回 TRUE 否则返回 FALSE
     **/
    public static native boolean XSView_SetTotalSize (int hEle, int cx, int cy);
    /**
     滚动视_取视图大小
     @param hEle 元素句柄
     @param pSize 大小（不包含边框和内填充，用于接收返回的大小）
     **/
    public static native void XSView_GetTotalSize (int hEle, TagSize pSize);

    /**
     设置滚动视图的滚动单位大小
     @param hEle 滚动视图元素句柄
     @param nWidth 宽度
     @param nHeight 高度
     @return 如果内容改变返回 true，否则返回 false
     **/
    public static native boolean XSView_SetLineSize (int hEle, int nWidth, int nHeight);
    /**
     获取滚动视图的滚动单位大小
     @param hEle 滚动视图元素句柄
     @param pSize 接收返回大小的结构体
     **/
    public static native void XSView_GetLineSize (int hEle, TagSize pSize);
    /**
     设置滚动视图的滚动条大小
     @param hEle 滚动视图元素句柄
     @param size 滚动条大小
     **/
    public static native void XSView_SetScrollBarSize (int hEle, int size);
    /**
     获取滚动视图的视口原点 X 坐标
     @param hEle 滚动视图元素句柄
     @return 视口原点 X 坐标
     **/
    public static native int XSView_GetViewPosH (int hEle);
    /**
     获取滚动视图的视口原点 Y 坐标
     @param hEle 滚动视图元素句柄
     @return 视口原点 Y 坐标
     **/
    public static native int XSView_GetViewPosV (int hEle);
    /**
     获取滚动视图的视口宽度
     @param hEle 滚动视图元素句柄
     @return 视口宽度
     **/
    public static native int XSView_GetViewWidth (int hEle);
    /**
     获取滚动视图的视口高度
     @param hEle 滚动视图元素句柄
     @return 视口高度
     **/
    public static native int XSView_GetViewHeight (int hEle);
    /**
     获取滚动视图的视口坐标
     @param hEle 滚动视图元素句柄
     @param pRect 接收返回坐标的结构体
     **/
    public static native void XSView_GetViewRect (int hEle, TagRect pRect);
    /**
     获取滚动视图的水平滚动条句柄
     @param hEle 滚动视图元素句柄
     @return 水平滚动条句柄
     **/
    public static native int XSView_GetScrollBarH (int hEle);
    /**
     获取滚动视图的垂直滚动条句柄
     @param hEle 滚动视图元素句柄
     @return 垂直滚动条句柄
     **/
    public static native int XSView_GetScrollBarV (int hEle);
    /**
     水平滚动视图到指定位置点
     @param hEle 滚动视图元素句柄
     @param pos 位置点
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XSView_ScrollPosH (int hEle, int pos);
    /**
     垂直滚动视图到指定位置点
     @param hEle 滚动视图元素句柄
     @param pos 位置点
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XSView_ScrollPosV (int hEle, int pos);
    /**
     水平滚动视图到指定 X 坐标
     @param hEle 滚动视图元素句柄
     @param posX X 坐标
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XSView_ScrollPosXH (int hEle, int posX);
    /**
     垂直滚动视图到指定 Y 坐标
     @param hEle 滚动视图元素句柄
     @param posY Y 坐标
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XSView_ScrollPosYV (int hEle, int posY);
    /**
     显示或隐藏滚动视图的水平滚动条
     @param hEle 滚动视图元素句柄
     @param bShow 是否显示
     **/
    public static native void XSView_ShowSBarH (int hEle, boolean bShow);
    /**
     显示或隐藏滚动视图的垂直滚动条
     @param hEle 滚动视图元素句柄
     @param bShow 是否显示
     **/
    public static native void XSView_ShowSBarV (int hEle, boolean bShow);
    /**
     启用滚动视图的自动显示滚动条功能
     @param hEle 滚动视图元素句柄
     @param bEnable 是否启用
     **/
    public static native void XSView_EnableAutoShowScrollBar (int hEle, boolean bEnable);
    /**
     向左滚动视图一行
     @param hEle 滚动视图元素句柄
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XSView_ScrollLeftLine (int hEle);
    /**
     向右滚动视图一行
     @param hEle 滚动视图元素句柄
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XSView_ScrollRightLine (int hEle);
    /**
     向上滚动视图一行
     @param hEle 滚动视图元素句柄
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XSView_ScrollTopLine (int hEle);

    /**
     向下滚动
     @param hEle 元素句柄
     @return 返回如果成功返回 TRUE，否则相反
     */
    public static native boolean XSView_ScrollBottomLine (int hEle);
    /**
     水平滚动到左侧
     @param hEle 元素句柄
     @return 返回如果成功返回 TRUE，否则相反
     */
    public static native boolean XSView_ScrollLeft (int hEle);
    /**
     水平滚动到右侧
     @param hEle 元素句柄
     @return 返回如果成功返回 TRUE，否则相反
     */
    public static native boolean XSView_ScrollRight (int hEle);
    /**
     垂直滚动到顶部
     @param hEle 元素句柄
     @return 返回如果成功返回 TRUE，否则相反
     */
    public static native boolean XSView_ScrollTop (int hEle);
    /**
     垂直滚动到底部
     @param hEle 元素句柄
     @return 返回如果成功返回 TRUE，否则相反
     */
    public static native boolean XSView_ScrollBottom (int hEle);
    /**
     对当前文本内容处理，将符号后面的一个字符加上下划线
     @param hEle 元素句柄
     @param bEnable 是否启用
     */
    public static native void XBtn_EnableHotkeyPrefix (int hEle, boolean bEnable);
    /**
     是否选中状态
     @param hEle 元素句柄
     @return 返回成功返回 TRUE 否则返回 FALSE
     */
    public static native boolean XBtn_IsCheck (int hEle);
    /**
     设置按钮状态
     @param hEle 元素句柄
     @param nState 按钮状态见宏定义
     */
    public static native void XBtn_SetState (int hEle, int nState);
    /**
     获取按钮状态
     @param hEle 元素句柄
     @return 返回按钮状态（common_state3_类型）
     */
    public static native int XBtn_GetState (int hEle);
    /**
     获取按钮状态
     @param hEle 元素句柄
     @return 返回按钮状态（button_state_类型）
     */
    public static native int XBtn_GetStateEx (int hEle);
    /**
     设置按钮类型并自动修改样式和文本对齐方式
     @param hEle 元素句柄
     @param nType 按钮类型，参见宏定义
     */
    public static native void XBtn_SetTypeEx (int hEle, int nType);
    /**
     设置组 ID
     @param hEle 元素句柄
     @param nID 组 ID
     */
    public static native void XBtn_SetGroupID (int hEle, int nID);
    /**
     获取组 ID
     @param hEle 元素句柄
     @return 返回组 ID
     */
    public static native int XBtn_GetGroupID (int hEle);
    /**
     设置绑定元素
     @param hEle 元素句柄
     @param hBindEle 将要绑定的元素句柄
     */
    public static native void XBtn_SetBindEle (int hEle, int hBindEle);
    /**
     获取绑定的元素
     @param hEle 元素句柄
     @return 返回绑定的元素句柄
     */
    public static native int XBtn_GetBindEle (int hEle);
    /**
     设置文本对齐方式
     @param hEle 元素句柄
     @param nFlags 对齐方式 @ref textFormatFlag_
     */
    public static native void XBtn_SetTextAlign (int hEle, int nFlags);
    /**
     获取文本对齐方式
     @param hEle 元素句柄
     @return 返回文本对齐方式 @ref textFormatFlag_
     */
    public static native int XBtn_GetTextAlign (int hEle);
    /**
     设置图标对齐
     @param hEle 元素句柄
     @param align 对齐方式 @ref button_icon_align_
     */
    public static native void XBtn_SetIconAlign (int hEle, int align);
    /**
     设置按钮文本坐标偏移量
     @param hEle 元素句柄
     @param x X 轴偏移量
     @param y Y 轴偏移量
     */
    public static native void XBtn_SetOffset (int hEle, int x, int y);
    /**
     设置按钮图标坐标偏移量
     @param hEle 元素句柄
     @param x X 轴偏移量
     @param y Y 轴偏移量
     */
    public static native void XBtn_SetOffsetIcon (int hEle, int x, int y);

    /**
     设置按钮图标与文本的间隔大小
     @param hEle 按钮元素句柄
     @param size 间隔大小
     **/
    public static native void XBtn_SetIconSpace (int hEle, int size);
    /**
     设置按钮文本内容
     @param hEle 按钮元素句柄
     @param pName 文本内容
     **/
    public static native void XBtn_SetText (int hEle, String pName);
    /**
     获取按钮文本内容
     @param hEle 按钮元素句柄
     @param dwSize 接收文本的缓冲区大小
     @return 返回文本内容的字节数组
     **/
    public static native byte [] XBtn_GetText (int hEle, int dwSize);
    /**
     设置按钮图标
     @param hEle 按钮元素句柄
     @param hImage 图标图片句柄
     **/
    public static native void XBtn_SetIcon (int hEle, int hImage);
    /**
     设置按钮禁用状态下的图标
     @param hEle 按钮元素句柄
     @param hImage 禁用状态图标图片句柄
     **/
    public static native void XBtn_SetIconDisable (int hEle, int hImage);
    /**
     获取按钮的图标
     @param hEle 按钮元素句柄
     @param nType 图标类型（0: 默认图标，1: 禁用状态图标）
     @return 返回图标图片句柄
     **/
    public static native int XBtn_GetIcon (int hEle, int nType);
    /**
     给按钮添加动画帧
     @param hEle 按钮元素句柄
     @param hImage 动画帧图片句柄
     @param uElapse 动画帧延时时间（单位：毫秒）
     **/
    public static native void XBtn_AddAnimationFrame (int hEle, int hImage, long uElapse);
    /**
     启用或关闭按钮的图片动画播放
     @param hEle 按钮元素句柄
     @param bEnable 是否启用动画（true: 启用，false: 关闭）
     @param bLoopPlay 是否循环播放动画
     **/
    public static native void XBtn_EnableAnimation (int hEle, boolean bEnable, boolean bLoopPlay);
    /**
     清空按钮的动画帧
     @param hEle 按钮元素句柄
     **/
    public static native void XBtn_ClearAnimation (int hEle);
    /**
     创建编辑框（扩展版）
     @param x 元素 x 坐标
     @param y 元素 y 坐标
     @param cx 编辑框宽度
     @param cy 编辑框高度
     @param type 编辑框类型
     @param hParent 父窗口或父元素句柄
     @return 返回编辑框元素句柄
     **/
    public static native int XEdit_CreateEx (int x, int y, int cx, int cy, int type, int hParent);
    /**
     启用或关闭编辑框的自动换行功能
     @param hEle 编辑框元素句柄
     @param bEnable 是否启用自动换行
     **/
    public static native void XEdit_EnableAutoWrap (int hEle, boolean bEnable);
    /**
     启用或关闭编辑框的只读模式
     @param hEle 编辑框元素句柄
     @param bEnable 是否启用只读模式
     **/
    public static native void XEdit_EnableReadOnly (int hEle, boolean bEnable);
    /**
     启用或关闭编辑框中 URL 文本的自动下划线功能
     @param hEle 编辑框元素句柄
     @param bEnable 是否启用
     **/
    public static native void XEdit_EnableUrlUnderline (int hEle, boolean bEnable);
    /**
     启用或关闭编辑框获得焦点时自动选择所有内容
     @param hEle 编辑框元素句柄
     @param bEnable 是否启用
     **/
    public static native void XEdit_EnableAutoSelAll (int hEle, boolean bEnable);
    /**
     启用或关闭编辑框失去焦点时自动取消选择内容
     @param hEle 编辑框元素句柄
     @param bEnable 是否启用
     **/
    public static native void XEdit_EnableAutoCancelSel (int hEle, boolean bEnable);
    /**
     判断编辑框是否处于只读模式
     @param hEle 编辑框元素句柄
     @return 处于只读模式返回 true, 否则返回 false
     **/
    public static native boolean XEdit_IsReadOnly (int hEle);
    /**
     判断编辑框是否为多行模式
     @param hEle 编辑框元素句柄
     @return 多行模式返回 true, 否则返回 false
     **/
    public static native boolean XEdit_IsMultiLine (int hEle);
    /**
     编辑框_是否密码模式
     @param hEle 编辑框元素句柄
     @return 是否为密码模式：true 是，false 否
     */
    public static native boolean XEdit_IsPassword (int hEle);
    /**
     编辑框_是否自动换行
     @param hEle 编辑框元素句柄
     @return 是否自动换行：true 是，false 否
     */
    public static native boolean XEdit_IsAutoWrap (int hEle);
    /**
     编辑框_是否为空
     @param hEle 编辑框元素句柄
     @return 内容是否为空：true 空，false 非空
     */
    public static native boolean XEdit_IsEmpty (int hEle);
    /**
     编辑框_是否在选择区域
     @param hEle 编辑框元素句柄
     @param iRow 行索引
     @param iCol 列索引
     @return 是否在选择区域内：true 是，false 否
     */
    public static native boolean XEdit_IsInSelect (int hEle, int iRow, int iCol);
    /**
     编辑框_取总行数
     @param hEle 编辑框元素句柄
     @return 返回总行数
     */
    public static native int XEdit_GetRowCount (int hEle);
    /**
     编辑框_取总行数扩展（包含自动换行数量）
     @param hEle 编辑框元素句柄
     @return 返回总行数
     */
    public static native int XEdit_GetRowCountEx (int hEle);
    /**
     编辑框_置默认文本（内容为空时显示）
     @param hEle 编辑框元素句柄
     @param pString 默认文本内容
     */
    public static native void XEdit_SetDefaultText (int hEle, String pString);
    /**
     编辑框_置文本对齐方式（单行模式有效）
     @param hEle 编辑框元素句柄
     @param align 文本对齐方式 @ref edit_textAlign_flag_
     */
    public static native void XEdit_SetTextAlign (int hEle, int align);
    /**
     编辑框_置后备字体（遇到中文字符时使用，解决不支持中文的字体问题）
     @param hEle 编辑框元素句柄
     @param hFont 后备字体句柄
     */
    public static native void XEdit_SetBackFont (int hEle, int hFont);
    /**
     编辑框_置 TAB 占空格数量
     @param hEle 编辑框元素句柄
     @param nSpace TAB 占空格的数量
     */
    public static native void XEdit_SetTabSpace (int hEle, int nSpace);
    /**
     编辑框_置空格显示大小
     @param hEle 编辑框元素句柄
     @param size 空格显示大小
     */
    public static native void XEdit_SetSpaceSize (int hEle, int size);
    /**
     编辑框_置字符间距大小
     @param hEle 编辑框元素句柄
     @param size 英文字符间距大小
     @param sizeZh 中文字符间距大小
     */
    public static native void XEdit_SetCharSpaceSize (int hEle, int size, int sizeZh);
    /**
     编辑框_置文本为整数值
     @param hEle 编辑框元素句柄
     @param nValue 整数值
     */
    public static native void XEdit_SetTextInt (int hEle, int nValue);
    /**
     编辑框_取指定行文本内容
     @param hEle 编辑框元素句柄
     @param iRow 行索引
     @param pOut 接收文本内容的内存缓冲区
     @param nOutlen 缓冲区大小（字符为单位）
     @return 返回实际接收的文本长度
     */
    public static native int XEdit_GetTextRow (int hEle, int iRow, byte [] pOut, int nOutlen);
    /**
     编辑框_取内容总长度（包含非文本内容）
     @param hEle 编辑框元素句柄
     @return 返回内容总长度
     */
    public static native int XEdit_GetLength (int hEle);
    /**
     编辑框_取指定行内容长度（包含非文本内容）
     @param hEle 编辑框元素句柄
     @param iRow 行索引
     @return 返回行内容长度
     */
    public static native int XEdit_GetLengthRow (int hEle, int iRow);
    /**
     编辑框_在指定行列插入文本
     @param hEle 编辑框元素句柄
     @param iRow 行索引
     @param iCol 列索引
     @param pString 插入的文本内容
     */
    public static native void XEdit_InsertText (int hEle, int iRow, int iCol, String pString);
    /**
     编辑框_在指定行列插入文本并设置样式
     @param hEle 编辑框元素句柄
     @param iRow 行索引
     @param iCol 列索引
     @param pString 插入的文本内容
     @param iStyle 样式索引
     */
    public static native void XEdit_InsertTextEx (int hEle, int iRow, int iCol, String pString, int iStyle);
    /**
     编辑框_在指定行列插入对象（UI 对象或图片等）
     @param hEle 编辑框元素句柄
     @param iRow 行索引
     @param iCol 列索引
     @param hObj 插入的对象句柄
     */
    public static native void XEdit_InsertObject (int hEle, int iRow, int iCol, int hObj);
    /**
     编辑框_在当前插入位置添加文本
     @param hEle 编辑框元素句柄
     @param pString 添加的文本内容
     */
    public static native void XEdit_AddText (int hEle, String pString);
    /**
     编辑框_添加文本模拟用户操作（自动刷新 UI，支持撤销 / 恢复）
     @param hEle 编辑框元素句柄
     @param pString 添加的文本内容
     */
    public static native void XEdit_AddTextUser (int hEle, String pString);
    /**
     编辑框_添加文本并指定样式
     @param hEle 编辑框元素句柄
     @param pString 添加的文本内容
     @param iStyle 样式索引
     */
    public static native void XEdit_AddTextEx (int hEle, String pString, int iStyle);
    /**
     编辑框_从样式添加对象（当样式为图片时有效）
     @param hEle 编辑框元素句柄
     @param iStyle 样式索引
     */
    public static native void XEdit_AddByStyle (int hEle, int iStyle);
    /**
     编辑框_添加样式扩展
     @param hEle 编辑框元素句柄
     @param fontName 字体名称
     @param fontSize 字体大小
     @param fontStyle 字体样式 @ref fontStyle_
     @param color 颜色值（使用宏 RGBA () 生成）
     @param bColor 是否使用该颜色
     @return 返回样式索引
     */
    public static native int XEdit_AddStyleEx (int hEle, String fontName, int fontSize, int fontStyle, long color, boolean bColor);
    /**
     修改编辑框的样式
     @param hEle 元素句柄
     @param iStyle 样式索引
     @param hFont 字体句柄
     @param color 颜色值，使用宏 RGBA ()
     @param bColor 是否使用颜色
     @return 如果成功返回 TRUE，否则返回 FALSE
     */
    public static native boolean XEdit_ModifyStyle (int hEle, int iStyle, int hFont, long color, boolean bColor);
    /**
     释放编辑框的样式
     @param hEle 元素句柄
     @param iStyle 样式索引
     @return 如果成功返回 TRUE，否则返回 FALSE
     */
    public static native boolean XEdit_ReleaseStyle (int hEle, int iStyle);
    /**
     设置编辑框当前样式
     @param hEle 元素句柄
     @param iStyle 样式索引
     */
    public static native void XEdit_SetCurStyle (int hEle, int iStyle);
    /**
     设置编辑框选择文本的样式
     @param hEle 元素句柄
     @param iStyle 样式索引
     */
    public static native void XEdit_SetSelectTextStyle (int hEle, int iStyle);
    /**
     设置编辑框插入符宽度
     @param hEle 元素句柄
     @param nWidth 宽度
     */
    public static native void XEdit_SetCaretWidth (int hEle, int nWidth);
    /**
     设置编辑框默认行高
     @param hEle 元素句柄
     @param nHeight 行高
     */
    public static native void XEdit_SetRowHeight (int hEle, int nHeight);
    /**
     设置编辑框指定行的高度
     @param hEle 元素句柄
     @param iRow 行索引
     @param nHeight 高度
     */
    public static native void XEdit_SetRowHeightEx (int hEle, int iRow, int nHeight);
    /**
     设置编辑框行间隔大小
     @param hEle 元素句柄
     @param nSpace 行间隔大小
     */
    public static native void XEdit_SetRowSpace (int hEle, int nSpace);
    /**
     设置编辑框当前位置
     @param hEle 元素句柄
     @param pos 位置
     @return 如果成功返回 TRUE，否则返回 FALSE
     */
    public static native boolean XEdit_SetCurPos (int hEle, int pos);
    /**
     获取编辑框当前位置
     @param hEle 元素句柄
     @return 当前位置
     */
    public static native int XEdit_GetCurPos (int hEle);
    /**
     设置编辑框当前位置（行、列）
     @param hEle 元素句柄
     @param iRow 行索引
     @param iCol 列索引
     */
    public static native void XEdit_SetCurPosEx (int hEle, int iRow, int iCol);
    /**
     获取编辑框当前行索引
     @param hEle 元素句柄
     @return 当前行索引
     */
    public static native int XEdit_GetCurRow (int hEle);
    /**
     获取编辑框当前列索引
     @param hEle 元素句柄
     @return 当前列索引
     */
    public static native int XEdit_GetCurCol (int hEle);
    /**
     将编辑框插入符移动到末尾
     @param hEle 元素句柄
     */
    public static native void XEdit_MoveEnd (int hEle);
    /**
     编辑框视图自动滚动到当前插入符位置
     @param hEle 元素句柄
     @return 如果成功返回 TRUE，否则返回 FALSE
     */
    public static native boolean XEdit_AutoScroll (int hEle);
    /**
     编辑框视图自动滚动到指定位置
     @param hEle 元素句柄
     @param iRow 行索引
     @param iCol 列索引
     @return 如果成功返回 TRUE，否则返回 FALSE
     */
    public static native boolean XEdit_AutoScrollEx (int hEle, int iRow, int iCol);
    /**
     选择编辑框所有内容
     @param hEle 元素句柄
     @return 如果成功返回 TRUE，否则返回 FALSE
     */
    public static native boolean XEdit_SelectAll (int hEle);
    /**
     取消编辑框选择内容
     @param hEle 元素句柄
     @return 如果成功返回 TRUE，否则返回 FALSE
     */
    public static native boolean XEdit_CancelSelect (int hEle);
    /**
     删除编辑框选择内容
     @param hEle 元素句柄
     @return 如果成功返回 TRUE，否则返回 FALSE
     */
    public static native boolean XEdit_DeleteSelect (int hEle);
    /**
     设置编辑框选择范围
     @param hEle 元素句柄
     @param iStartRow 起始行索引
     @param iStartCol 起始列索引
     @param iEndRow 结束行索引
     @param iEndCol 结束列索引
     @return 如果成功返回 TRUE，否则返回 FALSE
     */
    public static native boolean XEdit_SetSelect (int hEle, int iStartRow, int iStartCol, int iEndRow, int iEndCol);

    /**
     编辑框_取选择文本
     @param hEle 编辑框元素句柄
     @param pOut 接收选择文本的内存缓冲区（字节数组）
     @param nOutLen 内存缓冲区大小（字符为单位）
     @return 返回实际接收的文本长度
     **/
    public static native int XEdit_GetSelectText (int hEle, byte [] pOut, int nOutLen);
    /**
     编辑框_取选择文本长度
     @param hEle 编辑框元素句柄
     @return 返回选择文本的长度（不包括非文本内容）
     **/
    public static native int XEdit_GetSelectTextLength (int hEle);
    /**
     编辑框_删除指定范围内容
     @param hEle 编辑框元素句柄
     @param iStartRow 起始行索引
     @param iStartCol 起始列索引
     @param iEndRow 结束行索引
     @param iEndCol 结束列索引
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XEdit_Delete (int hEle, int iStartRow, int iStartCol, int iEndRow, int iEndCol);
    /**
     编辑框_删除指定行
     @param hEle 编辑框元素句柄
     @param iRow 行索引
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XEdit_DeleteRow (int hEle, int iRow);
    /**
     编辑框_剪贴板剪切
     @param hEle 编辑框元素句柄
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XEdit_ClipboardCut (int hEle);
    /**
     编辑框_剪贴板复制选择内容
     @param hEle 编辑框元素句柄
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XEdit_ClipboardCopy (int hEle);
    /**
     编辑框_剪贴板复制全部内容
     @param hEle 编辑框元素句柄
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XEdit_ClipboardCopyAll (int hEle);
    /**
     编辑框_剪贴板粘贴
     @param hEle 编辑框元素句柄
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XEdit_ClipboardPaste (int hEle);
    /**
     编辑框_撤销
     @param hEle 编辑框元素句柄
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XEdit_Undo (int hEle);
    /**
     编辑框_恢复 / 重做
     @param hEle 编辑框元素句柄
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XEdit_Redo (int hEle);
    /**
     编辑框_插入聊天气泡开始
     @param hEle 编辑框元素句柄
     @param hImageAvatar 头像图片句柄
     @param hImageBubble 气泡背景图片句柄
     @param nFlag 气泡标志（chat_flag_枚举值）
     **/
    public static native void XEdit_InsertChatBegin (int hEle, int hImageAvatar, int hImageBubble, int nFlag);
    /**
     编辑框_添加聊天气泡开始
     @param hEle 编辑框元素句柄
     @param hImageAvatar 头像图片句柄
     @param hImageBubble 气泡背景图片句柄
     @param nFlag 气泡标志（chat_flag_枚举值）
     **/
    public static native void XEdit_AddChatBegin (int hEle, int hImageAvatar, int hImageBubble, int nFlag);
    /**
     编辑框_添加聊天气泡结束
     @param hEle 编辑框元素句柄
     **/
    public static native void XEdit_AddChatEnd (int hEle);
    /**
     编辑框_置聊天气泡内容缩进
     @param hEle 编辑框元素句柄
     @param nIndentation 缩进值
     **/
    public static native void XEdit_SetChatIndentation (int hEle, int nIndentation);
    /**
     编辑框_置聊天气泡最大宽度
     @param hEle 编辑框元素句柄
     @param nWidth 最大宽度（值为 0 时代表不限制）
     **/
    public static native void XEdit_SetChatMaxWidth (int hEle, int nWidth);
    /**
     编辑框_取指定行气泡标识
     @param hEle 编辑框元素句柄
     @param iRow 行索引
     @return 返回行标识（chat_flag_枚举值）
     **/
    public static native int XEdit_GetChatFlags (int hEle, int iRow);
    /**
     组合框_置选择项
     @param hEle 组合框元素句柄
     @param iIndex 项索引
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XComboBox_SetSelItem (int hEle, int iIndex);
    /**
     组合框_置绑定数据适配器字段名
     @param hEle 组合框元素句柄
     @param pName 字段名
     **/
    public static native void XComboBox_SetBindName (int hEle, String pName);
    /**
     组合框_取下拉按钮坐标
     @param hEle 组合框元素句柄
     @param pRect 接收下拉按钮坐标的矩形对象
     **/
    public static native void XComboBox_GetButtonRect (int hEle, TagRect pRect);

    /**
     设置下拉组合框下拉按钮大小
     @param hEle 元素句柄
     @param size 按钮大小
     **/
    public static native void XComboBox_SetButtonSize (int hEle, int size);
    /**
     设置下拉组合框下拉列表高度
     @param hEle 元素句柄
     @param height 下拉列表高度
     **/
    public static native void XComboBox_SetDropHeight (int hEle, int height);
    /**
     获取下拉组合框下拉列表高度
     @param hEle 元素句柄
     @return 下拉列表高度
     **/
    public static native int XComboBox_GetDropHeight (int hEle);
    /**
     设置下拉组合框项模板文件
     @param hEle 元素句柄
     @param pXmlFile 项模板文件路径
     **/
    public static native void XComboBox_SetItemTemplateXML (int hEle, String pXmlFile);
    /**
     设置下拉组合框项模板从字符串
     @param hEle 元素句柄
     @param pStringXML 项模板字符串
     **/
    public static native void XComboBox_SetItemTemplateXMLFromString (int hEle, String pStringXML);
    /**
     设置下拉组合框项模板从内存
     @param hEle 元素句柄
     @param data 内存数据
     @param length 内存大小（字节）
     @return 设置成功返回 true，否则返回 false
     **/
    public static native boolean XComboBox_SetItemTemplateXMLFromMem (int hEle, byte [] data, int length);
    /**
     设置下拉组合框项模板从资源 ZIP
     @param hEle 元素句柄
     @param id RC 资源 ID
     @param pFileName 模板文件名
     @param pPassword ZIP 密码
     @param hModule 模块句柄
     @return 设置成功返回 true，否则返回 false
     **/
    public static native boolean XComboBox_SetItemTemplateXMLFromZipRes (int hEle, int id, String pFileName, String pPassword, long hModule);
    /**
     设置下拉组合框项模板
     @param hEle 元素句柄
     @param hTemp 项模板句柄
     @return 设置成功返回 true，否则返回 false
     **/
    public static native boolean XComboBox_SetItemTemplate (int hEle, int hTemp);
    /**
     获取下拉组合框项模板
     @param hEle 元素句柄
     @return 项模板句柄
     **/
    public static native int XComboBox_GetItemTemplate (int hEle);
    /**
     启用 / 禁用下拉组合框下拉按钮绘制
     @param hEle 元素句柄
     @param bEnable 是否启用绘制
     **/
    public static native void XComboBox_EnableDrawButton (int hEle, boolean bEnable);
    /**
     启用 / 禁用下拉组合框文本编辑
     @param hEle 元素句柄
     @param bEdit 是否启用编辑
     **/
    public static native void XComboBox_EnableEdit (int hEle, boolean bEdit);
    /**
     启用 / 禁用下拉组合框下拉列表高度固定
     @param hEle 元素句柄
     @param bEnable 是否启用高度固定
     **/
    public static native void XComboBox_EnableDropHeightFixed (int hEle, boolean bEnable);
    /**
     弹出下拉组合框下拉列表
     @param hEle 元素句柄
     **/
    public static native void XComboBox_PopupDropList (int hEle);
    /**
     获取下拉组合框选中项索引
     @param hEle 元素句柄
     @return 选中项索引
     **/
    public static native int XComboBox_GetSelItem (int hEle);
    /**
     获取下拉组合框状态
     @param hEle 元素句柄
     @return 状态（comboBox_state_枚举）
     **/
    public static native int XComboBox_GetState (int hEle);
    /**
     设置下拉组合框指定项图片
     @param hEle 元素句柄
     @param iItem 项索引
     @param iColumn 列索引
     @param hImage 图片句柄
     @return 设置成功返回 true，否则返回 false
     **/
    public static native boolean XComboBox_SetItemImage (int hEle, int iItem, int iColumn, int hImage);
    /**
     设置下拉组合框指定项图片（扩展）
     @param hEle 元素句柄
     @param iItem 项索引
     @param pName 字段名
     @param hImage 图片句柄
     @return 设置成功返回 true，否则返回 false
     **/
    public static native boolean XComboBox_SetItemImageEx (int hEle, int iItem, String pName, int hImage);
    /**
     设置下拉组合框指定项整数值
     @param hEle 元素句柄
     @param iItem 项索引
     @param iColumn 列索引
     @param nValue 整数值
     @return 设置成功返回 true，否则返回 false
     **/
    public static native boolean XComboBox_SetItemInt (int hEle, int iItem, int iColumn, int nValue);
    /**
     设置下拉组合框指定项整数值（扩展）
     @param hEle 元素句柄
     @param iItem 项索引
     @param pName 字段名
     @param nValue 整数值
     @return 设置成功返回 true，否则返回 false
     **/
    public static native boolean XComboBox_SetItemIntEx (int hEle, int iItem, String pName, int nValue);
    /**
     设置下拉组合框指定项浮点值
     @param hEle 元素句柄
     @param iItem 项索引
     @param iColumn 列索引
     @param fFloat 浮点值
     @return 设置成功返回 true，否则返回 false
     **/
    public static native boolean XComboBox_SetItemFloat (int hEle, int iItem, int iColumn, float fFloat);

    /**
     组合框_置项浮点值扩展
     @param hEle 组合框元素句柄
     @param iItem 项索引
     @param pName 字段名
     @param fFloat 浮点值
     @return 成功返回 TRUE，否则返回 FALSE
     */
    public static native boolean XComboBox_SetItemFloatEx (int hEle, int iItem, String pName, float fFloat);
    /**
     组合框_取项文本
     @param hEle 组合框元素句柄
     @param iItem 项索引
     @param iColumn 列索引
     @param dwSize 接收文本的内存大小（字符为单位）
     @return 返回文本内容的字节数组
     */
    public static native byte [] XComboBox_GetItemText (int hEle, int iItem, int iColumn, int dwSize);
    /**
     组合框_取项文本扩展
     @param hEle 组合框元素句柄
     @param iItem 项索引
     @param pName 字段名
     @param dwSize 接收文本的内存大小（字符为单位）
     @return 返回文本内容的字节数组
     */
    public static native byte [] XComboBox_GetItemTextEx (int hEle, int iItem, String pName, int dwSize);
    /**
     组合框_取项图片
     @param hEle 组合框元素句柄
     @param iItem 项索引
     @param iColumn 列索引
     @return 返回图片句柄
     */
    public static native int XComboBox_GetItemImage (int hEle, int iItem, int iColumn);
    /**
     组合框_取项图片扩展
     @param hEle 组合框元素句柄
     @param iItem 项索引
     @param pName 字段名
     @return 返回图片句柄
     */
    public static native int XComboBox_GetItemImageEx (int hEle, int iItem, String pName);
    /**
     组合框_取项整数值
     @param hEle 组合框元素句柄
     @param iItem 项索引
     @param iColumn 列索引
     @param pOutValue 接收整数值的缓冲区
     @param dwSize 缓冲区大小
     @return 成功返回 TRUE，否则返回 FALSE
     */
    public static native boolean XComboBox_GetItemInt (int hEle, int iItem, int iColumn, byte [] pOutValue, int dwSize);
    /**
     组合框_取项整数值扩展
     @param hEle 组合框元素句柄
     @param iItem 项索引
     @param pName 字段名
     @param pOutValue 接收整数值的缓冲区
     @param dwSize 缓冲区大小
     @return 成功返回 TRUE，否则返回 FALSE
     */
    public static native boolean XComboBox_GetItemIntEx (int hEle, int iItem, String pName, byte [] pOutValue, int dwSize);
    /**
     组合框_取项浮点值
     @param hEle 组合框元素句柄
     @param iItem 项索引
     @param iColumn 列索引
     @param pOutValue 接收浮点值的缓冲区
     @param dwSize 缓冲区大小
     @return 成功返回 TRUE，否则返回 FALSE
     */
    public static native boolean XComboBox_GetItemFloat (int hEle, int iItem, int iColumn, byte [] pOutValue, int dwSize);
    /**
     组合框_取项浮点值扩展
     @param hEle 组合框元素句柄
     @param iItem 项索引
     @param pName 字段名
     @param pOutValue 接收浮点值的缓冲区
     @param dwSize 缓冲区大小
     @return 成功返回 TRUE，否则返回 FALSE
     */
    public static native boolean XComboBox_GetItemFloatEx (int hEle, int iItem, String pName, byte [] pOutValue, int dwSize);
    /**
     组合框_取项数量
     @param hEle 组合框元素句柄
     @return 返回项数量
     */
    public static native int XComboBox_GetCount (int hEle);
    /**
     组合框_取列数量
     @param hEle 组合框元素句柄
     @return 返回列数量
     */
    public static native int XComboBox_GetCountColumn (int hEle);
    /**
     列表框_启用模板复用
     @param hEle 列表框元素句柄
     @param bEnable 是否启用模板复用
     */
    public static native void XListBox_EnableTemplateReuse (int hEle, boolean bEnable);
    /**
     列表框_启用虚表
     @param hEle 列表框元素句柄
     @param bEnable 是否启用虚表
     */
    public static native void XListBox_EnableVirtualTable (int hEle, boolean bEnable);
    /**
     列表框_置虚表行数
     @param hEle 列表框元素句柄
     @param nRowCount 虚表行数
     */
    public static native void XListBox_SetVirtualRowCount (int hEle, int nRowCount);
    /**
     列表框_置项背景绘制标志
     @param hEle 列表框元素句柄
     @param nFlags 项背景绘制标志（@ref list_drawItemBk_flag_）
     */
    public static native void XListBox_SetDrawItemBkFlags (int hEle, int nFlags);
    /**
     列表框_置分割线颜色
     @param hEle 列表框元素句柄
     @param color 分割线颜色（请使用宏：RGBA ()）
     */
    public static native void XListBox_SetSplitLineColor (int hEle, long color);
    /**
     列表框_置项数据
     @param hEle 列表框元素句柄
     @param iItem 项索引
     @param nUserData 用户绑定数据
     @return 成功返回 TRUE 否则返回 FALSE
     */
    public static native boolean XListBox_SetItemData (int hEle, int iItem, byte [] nUserData);
    /**
     列表框_取项数据
     @param hEle 列表框元素句柄
     @param iItem 项索引
     @param dwSize 接收返回的内存大小
     @return 返回用户绑定数据的字节数组
     */
    public static native byte [] XListBox_GetItemData (int hEle, int iItem, int dwSize);
    /**
     列表框_置项高度
     @param hEle 列表框元素句柄
     @param iItem 项索引
     @param nHeight 项默认高度
     @param nSelHeight 项选中时高度
     */
    public static native void XListBox_SetItemHeight (int hEle, int iItem, int nHeight, int nSelHeight);

    /**
     列表框_取项高度
     @param hEle 列表框元素句柄
     @param iItem 项索引
     @param pHeight 返回项高度
     @param pSelHeight 返回项选中时高度
     **/
    public static native void XListBox_GetItemHeight (int hEle, int iItem, byte [] pHeight, byte [] pSelHeight);
    /**
     列表框_置选择项
     @param hEle 列表框元素句柄
     @param iItem 项索引
     @return 成功返回 true 否则返回 false
     **/
    public static native boolean XListBox_SetSelectItem (int hEle, int iItem);
    /**
     列表框_取选择项
     @param hEle 列表框元素句柄
     @return 返回项索引
     **/
    public static native int XListBox_GetSelectItem (int hEle);
    /**
     列表框_添加选择项
     @param hEle 列表框元素句柄
     @param iItem 项索引
     @return 成功返回 true 否则返回 false
     **/
    public static native boolean XListBox_AddSelectItem (int hEle, int iItem);
    /**
     列表框_取消选择项
     @param hEle 列表框元素句柄
     @param iItem 项索引
     @return 成功返回 true 否则返回 false
     **/
    public static native boolean XListBox_CancelSelectItem (int hEle, int iItem);
    /**
     列表框_取消选择全部
     @param hEle 列表框元素句柄
     @return 如果之前有选择状态的项返回 true，此时可以更新 UI，否则返回 false
     **/
    public static native boolean XListBox_CancelSelectAll (int hEle);
    /**
     列表框_取选择项数量
     @param hEle 列表框元素句柄
     @return 返回选择项数量
     **/
    public static native int XListBox_GetSelectCount (int hEle);
    /**
     列表框_取鼠标停留项
     @param hEle 列表框元素句柄
     @return 返回鼠标所在项
     **/
    public static native int XListBox_GetItemMouseStay (int hEle);
    /**
     列表框_选择全部项
     @param hEle 列表框元素句柄
     @return 成功返回 true 否则返回 false
     **/
    public static native boolean XListBox_SelectAll (int hEle);
    /**
     列表框_显示指定项
     @param hEle 列表框元素句柄
     @param iItem 项索引
     **/
    public static native void XListBox_VisibleItem (int hEle, int iItem);
    /**
     列表框_取可视行范围
     @param hEle 列表框元素句柄
     @param piStart 开始行索引
     @param piEnd 结束行索引
     **/
    public static native void XListBox_GetVisibleRowRange (int hEle, byte [] piStart, byte [] piEnd);
    /**
     列表框_置项默认高度
     @param hEle 列表框元素句柄
     @param nHeight 项高度
     @param nSelHeight 选中项高度
     **/
    public static native void XListBox_SetItemHeightDefault (int hEle, int nHeight, int nSelHeight);
    /**
     列表框_取项默认高度
     @param hEle 列表框元素句柄
     @param pHeight 返回项高度
     @param pSelHeight 返回项选中时高度
     **/
    public static native void XListBox_GetItemHeightDefault (int hEle, byte [] pHeight, byte [] pSelHeight);
    /**
     列表框_取对象所在行
     @param hEle 列表框元素句柄
     @param hXCGUI 对象句柄（UI 元素句柄或形状对象句柄）
     @return 成功返回项索引，否则返回 XC_ID_ERROR
     **/
    public static native int XListBox_GetItemIndexFromint (int hEle, int hXCGUI);
    /**
     列表框_置行间距
     @param hEle 列表框元素句柄
     @param nSpace 行距大小
     **/
    public static native void XListBox_SetRowSpace (int hEle, int nSpace);
    /**
     列表框_取行间距
     @param hEle 列表框元素句柄
     @return 返回行间距大小
     **/
    public static native int XListBox_GetRowSpace (int hEle);
    /**
     列表框_测试点击项
     @param hEle 列表框元素句柄
     @param pPt 坐标点
     @return 返回项索引
     **/
    public static native int XListBox_HitTest (int hEle, TagPoint pPt);
    /**
     列表框_测试点击项扩展
     @param hEle 列表框元素句柄
     @param pPt 坐标点
     @return 项索引
     **/
    public static native int XListBox_HitTestOffset (int hEle, TagPoint pPt);
    /**
     列表框_置项模板文件
     @param hEle 列表框元素句柄
     @param pXmlFile 文件名
     @return 成功返回 true 否则返回 false
     **/
    public static native boolean XListBox_SetItemTemplateXML (int hEle, String pXmlFile);

    /**
     设置列表框的项模板
     @param hEle 列表框元素句柄
     @param hTemp 模板句柄
     @return 成功返回 TRUE，否则返回 FALSE
     */
    public static native boolean XListBox_SetItemTemplate (int hEle, int hTemp);
    /**
     获取列表框的项模板
     @param hEle 列表框元素句柄
     @return 返回项模板句柄
     */
    public static native int XListBox_GetItemTemplate (int hEle);
    /**
     从字符串设置列表框的项模板
     @param hEle 列表框元素句柄
     @param pStringXML XML 格式的模板字符串
     @return 成功返回 TRUE，否则返回 FALSE
     */
    public static native boolean XListBox_SetItemTemplateXMLFromString (int hEle, String pStringXML);
    /**
     从内存设置列表框的项模板
     @param hEle 列表框元素句柄
     @param data 内存中的模板数据
     @param length 内存数据的大小
     @return 成功返回 TRUE，否则返回 FALSE
     */
    public static native boolean XListBox_SetItemTemplateXMLFromMem (int hEle, byte [] data, int length);
    /**
     从资源 ZIP 文件中加载列表框的项模板
     @param hEle 列表框元素句柄
     @param id RC 资源 ID
     @param pFileName ZIP 中的模板文件名
     @param pPassword ZIP 文件密码
     @param hModule 模块句柄
     @return 成功返回 TRUE，否则返回 FALSE
     */
    public static native boolean XListBox_SetItemTemplateXMLFromZipRes (int hEle, int id, String pFileName, String pPassword, long hModule);
    /**
     通过模板项 ID 获取实例化的对象句柄
     @param hEle 列表框元素句柄
     @param iItem 项索引
     @param nTempItemID 模板项 ID
     @return 成功返回对象句柄，否则返回 NULL
     */
    public static native int XListBox_GetTemplateObject (int hEle, int iItem, int nTempItemID);
    /**
     启用列表框的多选功能
     @param hEle 列表框元素句柄
     @param bEnable 是否启用多选
     */
    public static native void XListBox_EnableMultiSel (int hEle, boolean bEnable);
    /**
     绑定数据适配器到列表框
     @param hEle 列表框元素句柄
     @param hAdapter 数据适配器句柄
     */
    public static native void XListBox_BindAdapter (int hEle, int hAdapter);
    /**
     获取列表框绑定的数据适配器
     @param hEle 列表框元素句柄
     @return 返回数据适配器句柄
     */
    public static native int XListBox_GetAdapter (int hEle);
    /**
     设置列表框拖动矩形的颜色和宽度
     @param hEle 列表框元素句柄
     @param color 颜色值
     @param width 线宽
     */
    public static native void XListBox_SetDragRectColor (int hEle, long color, int width);
    /**
     对列表框的内容进行排序
     @param hEle 列表框元素句柄
     @param iColumnAdapter 要排序的数据在数据适配器中的列索引
     @param bAscending 是否按升序排序
     */
    public static native void XListBox_Sort (int hEle, int iColumnAdapter, boolean bAscending);
    /**
     刷新列表框的指定项模板
     @param hEle 列表框元素句柄
     @param iItem 项索引
     */
    public static native void XListBox_RefreshItem (int hEle, int iItem);
    /**
     添加项到列表框，填充指定列数据
     @param hEle 列表框元素句柄
     @param pName 字段名称
     @param pText 项文本内容
     @return 返回项索引
     */
    public static native int XListBox_AddItemTextEx (int hEle, String pName, String pText);
    /**
     添加图片项到列表框，默认填充第一列
     @param hEle 列表框元素句柄
     @param hImage 图片句柄
     @return 返回项索引
     */
    public static native int XListBox_AddItemImage (int hEle, int hImage);
    /**
     添加图片项到列表框，填充指定列数据
     @param hEle 列表框元素句柄
     @param pName 字段名称
     @param hImage 图片句柄
     @return 返回项索引
     */
    public static native int XListBox_AddItemImageEx (int hEle, String pName, int hImage);
    /**
     插入项文本到列表框，默认填充第一列
     @param hEle 列表框元素句柄
     @param iItem 插入位置索引
     @param pValue 项文本内容
     @return 返回项索引
     */
    public static native int XListBox_InsertItemText (int hEle, int iItem, String pValue);
    /**
     插入项文本到列表框，填充指定列数据
     @param hEle 列表框元素句柄
     @param iItem 插入位置索引
     @param pName 字段名称
     @param pValue 项文本内容
     @return 返回项索引
     */
    public static native int XListBox_InsertItemTextEx (int hEle, int iItem, String pName, String pValue);
    /**
     插入图片项到列表框，默认填充第一列
     @param hEle 列表框元素句柄
     @param iItem 插入位置索引
     @param hImage 图片句柄
     @return 返回项索引
     */
    public static native int XListBox_InsertItemImage (int hEle, int iItem, int hImage);
    /**
     插入图片项到列表框，填充指定列数据
     @param hEle 列表框元素句柄
     @param iItem 插入位置索引
     @param pName 字段名称
     @param hImage 图片句柄
     @return 返回项索引
     */
    public static native int XListBox_InsertItemImageEx (int hEle, int iItem, String pName, int hImage);
    /**
     设置列表框指定项的文本内容
     @param hEle 列表框元素句柄
     @param iItem 项索引
     @param iColumn 列索引
     @param pText 文本内容
     @return 成功返回 TRUE，否则返回 FALSE
     */
    public static native boolean XListBox_SetItemText (int hEle, int iItem, int iColumn, String pText);

    /**
     设置列表框指定项的文本内容（根据字段名）
     @param hEle 列表框元素句柄
     @param iItem 项索引
     @param pName 数据适配器中的字段名
     @param pText 文本内容
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XListBox_SetItemTextEx (int hEle, int iItem, String pName, String pText);
    /**
     设置列表框指定项的图片（根据列索引）
     @param hEle 列表框元素句柄
     @param iItem 项索引
     @param iColumn 数据适配器中的列索引
     @param hImage 图片句柄
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XListBox_SetItemImage (int hEle, int iItem, int iColumn, int hImage);
    /**
     设置列表框指定项的图片（根据字段名）
     @param hEle 列表框元素句柄
     @param iItem 项索引
     @param pName 数据适配器中的字段名
     @param hImage 图片句柄
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XListBox_SetItemImageEx (int hEle, int iItem, String pName, int hImage);
    /**
     设置列表框指定项的整数值（根据列索引）
     @param hEle 列表框元素句柄
     @param iItem 项索引
     @param iColumn 数据适配器中的列索引
     @param nValue 整数值
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XListBox_SetItemInt (int hEle, int iItem, int iColumn, int nValue);
    /**
     设置列表框指定项的整数值（根据字段名）
     @param hEle 列表框元素句柄
     @param iItem 项索引
     @param pName 数据适配器中的字段名
     @param nValue 整数值
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XListBox_SetItemIntEx (int hEle, int iItem, String pName, int nValue);
    /**
     设置列表框指定项的浮点值（根据列索引）
     @param hEle 列表框元素句柄
     @param iItem 项索引
     @param iColumn 数据适配器中的列索引
     @param fFloat 浮点值
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XListBox_SetItemFloat (int hEle, int iItem, int iColumn, float fFloat);
    /**
     设置列表框指定项的浮点值（根据字段名）
     @param hEle 列表框元素句柄
     @param iItem 项索引
     @param pName 数据适配器中的字段名
     @param fFloat 浮点值
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XListBox_SetItemFloatEx (int hEle, int iItem, String pName, float fFloat);
    /**
     获取列表框指定项的文本内容（根据列索引）
     @param hEle 列表框元素句柄
     @param iItem 项索引
     @param iColumn 数据适配器中的列索引
     @param dwSize 接收文本的缓冲区大小
     @return 文本内容的字节数组
     */
    public static native byte [] XListBox_GetItemText (int hEle, int iItem, int iColumn, int dwSize);
    /**
     获取列表框指定项的文本内容（根据字段名）
     @param hEle 列表框元素句柄
     @param iItem 项索引
     @param pName 数据适配器中的字段名
     @param dwSize 接收文本的缓冲区大小
     @return 文本内容的字节数组
     */
    public static native byte [] XListBox_GetItemTextEx (int hEle, int iItem, String pName, int dwSize);
    /**
     获取列表框指定项的图片句柄（根据列索引）
     @param hEle 列表框元素句柄
     @param iItem 项索引
     @param iColumn 数据适配器中的列索引
     @return 图片句柄
     */
    public static native int XListBox_GetItemImage (int hEle, int iItem, int iColumn);
    /**
     获取列表框指定项的图片句柄（根据字段名）
     @param hEle 列表框元素句柄
     @param iItem 项索引
     @param pName 数据适配器中的字段名
     @return 图片句柄
     */
    public static native int XListBox_GetItemImageEx (int hEle, int iItem, String pName);
    /**
     获取列表框指定项的整数值（根据列索引）
     @param hEle 列表框元素句柄
     @param iItem 项索引
     @param iColumn 数据适配器中的列索引
     @param pOutValue 接收整数值的缓冲区
     @param dwSize 缓冲区大小
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XListBox_GetItemInt (int hEle, int iItem, int iColumn, byte [] pOutValue, int dwSize);
    /**
     获取列表框指定项的整数值（根据字段名）
     @param hEle 列表框元素句柄
     @param iItem 项索引
     @param pName 数据适配器中的字段名
     @param pOutValue 接收整数值的缓冲区
     @param dwSize 缓冲区大小
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XListBox_GetItemIntEx (int hEle, int iItem, String pName, byte [] pOutValue, int dwSize);
    /**
     获取列表框指定项的浮点值（根据列索引）
     @param hEle 列表框元素句柄
     @param iItem 项索引
     @param iColumn 数据适配器中的列索引
     @param pOutValue 接收浮点值的缓冲区
     @param dwSize 缓冲区大小
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XListBox_GetItemFloat (int hEle, int iItem, int iColumn, byte [] pOutValue, int dwSize);
    /**
     获取列表框指定项的浮点值（根据字段名）
     @param hEle 列表框元素句柄
     @param iItem 项索引
     @param pName 数据适配器中的字段名
     @param pOutValue 接收浮点值的缓冲区
     @param dwSize 缓冲区大小
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XListBox_GetItemFloatEx (int hEle, int iItem, String pName, byte [] pOutValue, int dwSize);
    /**
     获取列表框数据适配器中的行数量
     @param hEle 列表框元素句柄
     @return 行数量
     */
    public static native int XListBox_GetCount_AD (int hEle);
    /**
     获取列表框数据适配器中的列数量
     @param hEle 列表框元素句柄
     @return 列数量
     */
    public static native int XListBox_GetCountColumn_AD (int hEle);
    /**
     给列表添加列
     @param hEle 列表元素句柄
     @param width 列宽度
     @return 列索引
     */
    public static native int XList_AddColumn (int hEle, int width);
    /**
     在列表指定位置插入列
     @param hEle 列表元素句柄
     @param width 列宽度
     @param iCol 插入位置索引
     @return 插入位置索引
     */
    public static native int XList_InsertColumn (int hEle, int width, int iCol);
    /**
     启用 / 禁用列表的多选功能
     @param hEle 列表元素句柄
     @param bEnable true：启用，false：禁用
     */
    public static native void XList_EnableMultiSel (int hEle, boolean bEnable);

    /**
     启用拖动改变列宽度
     @param hEle 元素句柄
     @param bEnable 是否启用
     */
    public static native void XList_EnableDragChangeColumnWidth (int hEle, boolean bEnable);
    /**
     启用垂直滚动条顶部对齐
     @param hEle 元素句柄
     @param bTop 是否启用顶部对齐
     */
    public static native void XList_EnableVScrollBarTop (int hEle, boolean bTop);
    /**
     启用行背景铺满模式
     @param hEle 元素句柄
     @param bFull 是否启用铺满
     */
    public static native void XList_EnableRowBkFull (int hEle, boolean bFull);
    /**
     启用固定行高
     @param hEle 元素句柄
     @param bEnable 是否启用
     */
    public static native void XList_EnableFixedRowHeight (int hEle, boolean bEnable);
    /**
     启用模板复用
     @param hEle 元素句柄
     @param bEnable 是否启用
     */
    public static native void XList_EnableTemplateReuse (int hEle, boolean bEnable);
    /**
     启用虚表
     @param hEle 元素句柄
     @param bEnable 是否启用
     */
    public static native void XList_EnableVirtualTable (int hEle, boolean bEnable);
    /**
     置虚表行数
     @param hEle 元素句柄
     @param nRowCount 行数
     */
    public static native void XList_SetVirtualRowCount (int hEle, int nRowCount);
    /**
     设置排序属性
     @param hEle 元素句柄
     @param iColumn 列索引
     @param iColumnAdapter 需要排序的数据在数据适配器中的列索引
     @param bEnable 是否启用排序功能
     */
    public static native void XList_SetSort (int hEle, int iColumn, int iColumnAdapter, boolean bEnable);
    /**
     设置是否绘制指定状态下行的背景
     @param hEle 元素句柄
     @param style 标志位（@ref list_drawItemBk_flag_）
     */
    public static native void XList_SetDrawRowBkFlags (int hEle, int style);
    /**
     置分割线颜色
     @param hEle 元素句柄
     @param color 颜色值（请使用宏：RGBA ()）
     */
    public static native void XList_SetSplitLineColor (int hEle, long color);
    /**
     置列宽
     @param hEle 元素句柄
     @param iRow 列索引
     @param width 宽度
     */
    public static native void XList_SetColumnWidth (int hEle, int iRow, int width);
    /**
     置列最小宽度
     @param hEle 元素句柄
     @param iRow 列索引
     @param width 宽度
     */
    public static native void XList_SetColumnMinWidth (int hEle, int iRow, int width);
    /**
     置列宽度固定
     @param hEle 元素句柄
     @param iColumn 列索引
     @param bFixed 是否固定宽度
     */
    public static native void XList_SetColumnWidthFixed (int hEle, int iColumn, boolean bFixed);
    /**
     取列宽度
     @param hEle 元素句柄
     @param iColumn 列索引
     @return 返回指定列宽度
     */
    public static native int XList_GetColumnWidth (int hEle, int iColumn);
    /**
     取列数量
     @param hEle 元素句柄
     @return 返回列数量
     */
    public static native int XList_GetColumnCount (int hEle);
    /**
     置选择行
     @param hEle 元素句柄
     @param iRow 行索引
     @return 成功返回 TRUE 否则返回 FALSE
     */
    public static native boolean XList_SetSelectRow (int hEle, int iRow);
    /**
     取选择行
     @param hEle 元素句柄
     @return 返回行索引
     */
    public static native int XList_GetSelectRow (int hEle);
    /**
     取选择行数量
     @param hEle 元素句柄
     @return 返回选择行数量
     */
    public static native int XList_GetSelectRowCount (int hEle);
    /**
     添加选择行
     @param hEle 元素句柄
     @param iRow 行索引
     @return 成功返回 TRUE 否则返回 FALSE
     */
    public static native boolean XList_AddSelectRow (int hEle, int iRow);
    /**
     置选择全部
     @param hEle 元素句柄
     */
    public static native void XList_SetSelectAll (int hEle);

    /**
     获取列表全部选择的行
     @param hEle 列表元素句柄
     @param pArray 接收行索引的数组
     @param nArraySize 数组大小 (数组成员数)
     @return 返回接收的行数量
     **/
    public static native int XList_GetSelectAll (int hEle, byte [] pArray, int nArraySize);
    /**
     取消选择列表所有项
     @param hEle 列表元素句柄
     **/
    public static native void XList_CancelSelectAll (int hEle);
    /**
     获取列表头元素
     @param hEle 列表元素句柄
     @return 返回列表头元素句柄
     **/
    public static native int XList_GetHeaderHELE (int hEle);
    /**
     绑定列表数据适配器
     @param hEle 列表元素句柄
     @param hAdapter 数据适配器句柄
     **/
    public static native void XList_BindAdapter (int hEle, int hAdapter);
    /**
     列表头绑定数据适配器
     @param hEle 列表元素句柄
     @param hAdapter 数据适配器句柄
     **/
    public static native void XList_BindAdapterHeader (int hEle, int hAdapter);
    /**
     获取列表绑定的数据适配器
     @param hEle 列表元素句柄
     @return 返回数据适配器句柄
     **/
    public static native int XList_GetAdapter (int hEle);
    /**
     获取列表头绑定的数据适配器
     @param hEle 列表元素句柄
     @return 返回数据适配器句柄
     **/
    public static native int XList_GetAdapterHeader (int hEle);
    /**
     设置列表项模板文件
     @param hEle 列表元素句柄
     @param pXmlFile 模板文件名
     @return 成功返回 true，失败返回 false
     **/
    public static native boolean XList_SetItemTemplateXML (int hEle, String pXmlFile);
    /**
     从内存设置列表项模板
     @param hEle 列表元素句柄
     @param data 内存数据地址
     @param length 内存大小 (字节为单位)
     @return 成功返回 true，失败返回 false
     **/
    public static native boolean XList_SetItemTemplateXMLFromMem (int hEle, byte [] data, int length);
    /**
     从 ZIP 资源设置列表项模板
     @param hEle 列表元素句柄
     @param id RC 资源 ID
     @param pFileName 模板文件名
     @param pPassword ZIP 密码
     @param hModule 模块句柄
     @return 成功返回 true，失败返回 false
     **/
    public static native boolean XList_SetItemTemplateXMLFromZipRes (int hEle, int id, String pFileName, String pPassword, long hModule);
    /**
     从字符串设置列表项模板
     @param hEle 列表元素句柄
     @param pStringXML 模板字符串
     @return 成功返回 true，失败返回 false
     **/
    public static native boolean XList_SetItemTemplateXMLFromString (int hEle, String pStringXML);
    /**
     设置列表项模板
     @param hEle 列表元素句柄
     @param hTemp 模板句柄
     @return 成功返回 true，失败返回 false
     **/
    public static native boolean XList_SetItemTemplate (int hEle, int hTemp);
    /**
     获取列表项模板句柄
     @param hEle 列表元素句柄
     @return 返回模板句柄
     **/
    public static native int XList_GetItemTemplate (int hEle);
    /**
     获取列表头项模板句柄
     @param hEle 列表元素句柄
     @return 返回模板句柄
     **/
    public static native int XList_GetItemTemplateHeader (int hEle);
    /**
     通过模板项 ID 获取实例化模板项 ID 对应的对象句柄
     @param hEle 列表元素句柄
     @param iRow 行索引
     @param iColumn 列索引
     @param nTempItemID 模板项 ID
     @return 成功返回对象句柄，否则返回 null
     **/
    public static native int XList_GetTemplateObject (int hEle, int iRow, int iColumn, int nTempItemID);
    /**
     获取当前对象所在模板实例的行索引
     @param hEle 列表元素句柄
     @param hXCGUI 对象句柄
     @return 成功返回行索引，否则返回 XC_ID_ERROR
     **/
    public static native int XList_GetRowIndexFromHXCGUI (int hEle, int hXCGUI);
    /**
     列表头通过模板项 ID 获取实例化模板项 ID 对应的对象句柄
     @param hEle 列表元素句柄
     @param iColumn 列表头列索引
     @param nTempItemID 模板项 ID
     @return 成功返回对象句柄，否则返回 null
     **/
    public static native int XList_GetHeaderTemplateObject (int hEle, int iColumn, int nTempItemID);
    /**
     列表头获取当前对象所在模板实例的列索引
     @param hEle 列表元素句柄
     @param hXCGUI 对象句柄
     @return 成功返回列索引，否则返回 XC_ID_ERROR
     **/
    public static native int XList_GetHeaderColumnIndexFromHXCGUI (int hEle, int hXCGUI);
    /**
     设置列表头高度
     @param hEle 列表元素句柄
     @param height 高度
     **/
    public static native void XList_SetHeaderHeight (int hEle, int height);
    /**
     获取列表头高度
     @param hEle 列表元素句柄
     @return 返回列表头高度
     **/
    public static native int XList_GetHeaderHeight (int hEle);

    /**
     获取列表当前可见行范围
     @param hEle 列表元素句柄
     @param piStart 接收可见起始行索引的数组
     @param piEnd 接收可见结束行索引的数组
     */
    public static native void XList_GetVisibleRowRange (int hEle, byte [] piStart, byte [] piEnd);
    /**
     设置列表行默认高度
     @param hEle 列表元素句柄
     @param nHeight 默认行高度
     @param nSelHeight 默认行选中时高度
     */
    public static native void XList_SetRowHeightDefault (int hEle, int nHeight, int nSelHeight);
    /**
     获取列表行默认高度
     @param hEle 列表元素句柄
     @param pHeight 接收默认行高度的数组
     @param pSelHeight 接收默认行选中时高度的数组
     */
    public static native void XList_GetRowHeightDefault (int hEle, byte [] pHeight, byte [] pSelHeight);
    /**
     设置列表指定行高度
     @param hEle 列表元素句柄
     @param iRow 行索引
     @param nHeight 指定行高度
     @param nSelHeight 指定行选中时高度
     */
    public static native void XList_SetRowHeight (int hEle, int iRow, int nHeight, int nSelHeight);
    /**
     获取列表指定行高度
     @param hEle 列表元素句柄
     @param iRow 行索引
     @param pHeight 接收指定行高度的数组
     @param pSelHeight 接收指定行选中时高度的数组
     */
    public static native void XList_GetRowHeight (int hEle, int iRow, byte [] pHeight, byte [] pSelHeight);
    /**
     设置列表行间距大小
     @param hEle 列表元素句柄
     @param nSpace 行间距大小
     */
    public static native void XList_SetRowSpace (int hEle, int nSpace);
    /**
     获取列表行间距大小
     @param hEle 列表元素句柄
     @return 返回行间距大小
     */
    public static native int XList_GetRowSpace (int hEle);
    /**
     锁定列表列 - 设置左侧锁定列分界列索引
     @param hEle 列表元素句柄
     @param iColumn 列索引，-1 代表不锁定
     */
    public static native void XList_SetLockColumnLeft (int hEle, int iColumn);
    /**
     锁定列表列 - 设置右侧锁定列分界列索引
     @param hEle 列表元素句柄
     @param iColumn 列索引，-1 代表不锁定
     */
    public static native void XList_SetLockColumnRight (int hEle, int iColumn);
    /**
     设置列表是否锁定末尾行
     @param hEle 列表元素句柄
     @param bLock 是否锁定末尾行
     */
    public static native void XList_SetLockRowBottom (int hEle, boolean bLock);
    /**
     设置列表锁定行底部是否重叠
     @param hEle 列表元素句柄
     @param bOverlap 是否重叠
     */
    public static native void XList_SetLockRowBottomOverlap (int hEle, boolean bOverlap);
    /**
     设置列表拖动矩形颜色
     @param hEle 列表元素句柄
     @param color 颜色值，使用宏 RGBA () 生成
     @param width 线宽度
     */
    public static native void XList_SetDragRectColor (int hEle, long color, int width);
    /**
     检测列表坐标点所在项
     @param hEle 列表元素句柄
     @param pPt 坐标点对象
     @param piRow 接收行索引的数组
     @param piColumn 接收列索引的数组
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XList_HitTest (int hEle, TagPoint pPt, byte [] piRow, byte [] piColumn);
    /**
     检测列表坐标点所在项 - 自动添加滚动视图偏移量
     @param hEle 列表元素句柄
     @param pPt 坐标点对象
     @param piRow 接收行索引的数组
     @param piColumn 接收列索引的数组
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XList_HitTestOffset (int hEle, TagPoint pPt, byte [] piRow, byte [] piColumn);

    /**
     刷新列表指定行模板，以便更新数据到模板 (如果当前项可见)
     @param hEle 列表元素句柄
     @param iRow 行索引
     **/
    public static native void XList_RefreshRow (int hEle, int iRow);
    /**
     刷新列表头数据
     @param hEle 列表元素句柄
     **/
    public static native void XList_RefreshDataHeader (int hEle);
    /**
     简化接口，为列表添加图片列 (首次添加数据时需先创建数据适配器)
     @param hEle 列表元素句柄
     @param nWidth 列宽度
     @param hImage 图片句柄
     @return 返回列索引
     **/
    public static native int XList_AddColumnImage2 (int hEle, int nWidth, int hImage);
    /**
     为列表添加图片列并指定字段名 (首次添加数据时需先创建数据适配器)
     @param hEle 列表元素句柄
     @param nWidth 列宽度
     @param pName 字段名
     @param hImage 图片句柄
     @return 返回列索引
     **/
    public static native int XList_AddColumnImage (int hEle, int nWidth, String pName, int hImage);
    /**
     为列表添加图片行 (首次添加数据时需先创建数据适配器)
     @param hEle 列表元素句柄
     @param hImage 图片句柄
     @return 返回行索引
     **/
    public static native int XList_AddRowImage (int hEle, int hImage);
    /**
     为列表添加图片行并指定字段名 (首次添加数据时需先创建数据适配器)
     @param hEle 列表元素句柄
     @param pName 字段名
     @param hImage 图片句柄
     @return 返回行索引
     **/
    public static native int XList_AddRowImageEx (int hEle, String pName, int hImage);
    /**
     在列表指定位置插入图片行 (首次添加数据时需先创建数据适配器)
     @param hEle 列表元素句柄
     @param iRow 插入位置行索引
     @param hImage 图片句柄
     @return 返回行索引
     **/
    public static native int XList_InsertRowImage (int hEle, int iRow, int hImage);
    /**
     在列表指定位置插入图片行并指定字段名 (首次添加数据时需先创建数据适配器)
     @param hEle 列表元素句柄
     @param iRow 插入位置行索引
     @param pName 字段名
     @param hImage 图片句柄
     @return 返回行索引
     **/
    public static native int XList_InsertRowImageEx (int hEle, int iRow, String pName, int hImage);
    /**
     设置列表项图片 (通过列索引)
     @param hEle 列表元素句柄
     @param iRow 行索引
     @param iColumn 列索引
     @param hImage 图片句柄
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XList_SetItemImage (int hEle, int iRow, int iColumn, int hImage);
    /**
     设置列表项图片 (通过字段名)
     @param hEle 列表元素句柄
     @param iRow 行索引
     @param pName 字段名
     @param hImage 图片句柄
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XList_SetItemImageEx (int hEle, int iRow, String pName, int hImage);
    /**
     设置列表项整数值 (通过列索引)
     @param hEle 列表元素句柄
     @param iRow 行索引
     @param iColumn 列索引
     @param nValue 整数值
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XList_SetItemInt (int hEle, int iRow, int iColumn, int nValue);
    /**
     设置列表项整数值 (通过字段名)
     @param hEle 列表元素句柄
     @param iRow 行索引
     @param pName 字段名
     @param nValue 整数值
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XList_SetItemIntEx (int hEle, int iRow, String pName, int nValue);
    /**
     设置列表项浮点值 (通过列索引)
     @param hEle 列表元素句柄
     @param iRow 行索引
     @param iColumn 列索引
     @param fFloat 浮点值
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XList_SetItemFloat (int hEle, int iRow, int iColumn, float fFloat);
    /**
     设置列表项浮点值 (通过字段名)
     @param hEle 列表元素句柄
     @param iRow 行索引
     @param pName 字段名
     @param fFloat 浮点值
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XList_SetItemFloatEx (int hEle, int iRow, String pName, float fFloat);

    /**
     列表_取项文本 ()
     @param hEle 列表元素句柄
     @param iRow 行索引
     @param iColumn 列索引
     @param dwSize 接收文本的内存大小 (字符为单位)
     @return 返回文本内容字节数组
     **/
    public static native byte [] XList_GetItemText (int hEle, int iRow, int iColumn,int dwSize);
    /**
     列表_取项文本扩展 ()
     @param hEle 列表元素句柄
     @param iRow 行索引
     @param pName 字段名称
     @param dwSize 接收文本的内存大小 (字符为单位)
     @return 返回文本内容字节数组
     **/
    public static native byte [] XList_GetItemTextEx (int hEle, int iRow, String pName,int dwSize);
    /**
     列表_取项图片 ()
     @param hEle 列表元素句柄
     @param iRow 行索引
     @param iColumn 列索引
     @return 返回图片句柄
     **/
    public static native int XList_GetItemImage (int hEle, int iRow, int iColumn);
    /**
     列表_取项图片扩展 ()
     @param hEle 列表元素句柄
     @param iRow 行索引
     @param pName 字段名称
     @return 返回图片句柄
     **/
    public static native int XList_GetItemImageEx (int hEle, int iRow, String pName);
    /**
     列表_取项整数值 ()
     @param hEle 列表元素句柄
     @param iRow 行索引
     @param iColumn 列索引
     @param pOutValue 接收返回整数值的字节数组
     @return 成功返回 TRUE, 否则返回 FALSE
     **/
    public static native boolean XList_GetItemInt (int hEle, int iRow, int iColumn, byte [] pOutValue);
    /**
     列表_取项整数值扩展 ()
     @param hEle 列表元素句柄
     @param iRow 行索引
     @param pName 字段名称
     @param pOutValue 接收返回整数值的字节数组
     @return 成功返回 TRUE, 否则返回 FALSE
     **/
    public static native boolean XList_GetItemIntEx (int hEle, int iRow, String pName, byte [] pOutValue);
    /**
     列表_取项浮点值 ()
     @param hEle 列表元素句柄
     @param iRow 行索引
     @param iColumn 列索引
     @param pOutValue 接收返回浮点值的字节数组
     @return 成功返回 TRUE, 否则返回 FALSE
     **/
    public static native boolean XList_GetItemFloat (int hEle, int iRow, int iColumn, byte [] pOutValue);
    /**
     列表_取项浮点值扩展 ()
     @param hEle 列表元素句柄
     @param iRow 行索引
     @param pName 字段名称
     @param pOutValue 接收返回浮点值的字节数组
     @return 成功返回 TRUE, 否则返回 FALSE
     **/
    public static native boolean XList_GetItemFloatEx (int hEle, int iRow, String pName, byte [] pOutValue);
    /**
     列表_取行数量 AD ()
     @param hEle 列表元素句柄
     @return 返回行数量
     **/
    public static native int XList_GetCount_AD (int hEle);
    /**
     列表_取列数量 AD ()
     @param hEle 列表元素句柄
     @return 返回列数量
     **/
    public static native int XList_GetCountColumn_AD (int hEle);
    /**
     列表视_取数据适配器 ()
     @param hEle 列表视图元素句柄
     @return 返回数据适配器句柄
     **/
    public static native int XListView_GetAdapter (int hEle);
    /**
     列表视_置项模板文件 ()
     @param hEle 列表视图元素句柄
     @param pXmlFile 项模板文件名
     @return 成功返回 TRUE 否则返回 FALSE
     **/
    public static native boolean XListView_SetItemTemplateXML (int hEle, String pXmlFile);
    /**
     列表视_置项模板从字符串 ()
     @param hEle 列表视图元素句柄
     @param pStringXML 项模板字符串
     @return 成功返回 TRUE 否则返回 FALSE
     **/
    public static native boolean XListView_SetItemTemplateXMLFromString (int hEle, String pStringXML);
    /**
     列表视_置项模板从内存 ()
     @param hEle 列表视图元素句柄
     @param data 项模板内存数据
     @param length 内存数据大小 (字节为单位)
     @return 成功返回 TRUE 否则返回 FALSE
     **/
    public static native boolean XListView_SetItemTemplateXMLFromMem (int hEle, byte [] data, int length);
    /**
     列表视_置项模板从资源 ZIP ()
     @param hEle 列表视图元素句柄
     @param id RC 资源 ID
     @param pFileName 项模板文件名
     @param pPassword ZIP 密码
     @param hModule 模块句柄
     @return 成功返回 TRUE 否则返回 FALSE
     **/
    public static native boolean XListView_SetItemTemplateXMLFromZipRes (int hEle, int id, String pFileName, String pPassword, long hModule);
    /**
     列表视_置项模板 ()
     @param hEle 列表视图元素句柄
     @param hTemp 项模板句柄
     @return 如果成功返回 TRUE, 否则返回 FALSE
     **/
    public static native boolean XListView_SetItemTemplate (int hEle, int hTemp);
    /**
     列表视_取项模板 ()
     @param hEle 列表视图元素句柄
     @return 返回项模板句柄
     **/
    public static native int XListView_GetItemTemplate (int hEle);
    /**
     列表视_取项模板组 ()
     @param hEle 列表视图元素句柄
     @return 返回项模板组句柄
     **/
    public static native int XListView_GetItemTemplateGroup (int hEle);
    /**
     列表视_取模板对象 ()
     @param hEle 列表视图元素句柄
     @param iGroup 组索引
     @param iItem 项索引
     @param nTempItemID 模板项 ID
     @return 成功返回对象句柄，否则返回 NULL
     **/
    public static native int XListView_GetTemplateObject (int hEle, int iGroup, int iItem, int nTempItemID);
    /**
     列表视_取模板对象组 ()
     @param hEle 列表视图元素句柄
     @param iGroup 组索引
     @param nTempItemID 模板项 ID
     @return 成功返回对象句柄，否则返回 NULL
     **/
    public static native int XListView_GetTemplateObjectGroup (int hEle, int iGroup, int nTempItemID);

    /**
     根据整数获取列表视项 ID
     @param hEle 列表视元素句柄
     @param hXCGUI 对象句柄
     @param piGroup 接收组索引的数组
     @param piItem 接收项索引的数组
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XListView_GetItemIDFromint (int hEle, int hXCGUI, byte [] piGroup, byte [] piItem);
    /**
     测试坐标点所在的列表视项
     @param hEle 列表视元素句柄
     @param pPt 坐标点
     @param pOutGroup 接收组索引的数组
     @param pOutItem 接收项索引的数组
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XListView_HitTest (int hEle, TagPoint pPt, byte [] pOutGroup, byte [] pOutItem);
    /**
     测试坐标点所在的列表视项，自动添加滚动视图偏移
     @param hEle 列表视元素句柄
     @param pPt 坐标点
     @param pOutGroup 接收组索引的数组
     @param pOutItem 接收项索引的数组
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XListView_HitTestOffset (int hEle, TagPoint pPt, byte [] pOutGroup, byte [] pOutItem);
    /**
     设置列表视虚拟项数量
     @param hEle 列表视元素句柄
     @param iGroup 组索引
     @param nCount 项数量
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XListView_SetVirtualItemCount (int hEle, int iGroup, int nCount);
    /**
     设置列表视项背景绘制标志
     @param hEle 列表视元素句柄
     @param nFlags 标志位 @ref list_drawItemBk_flag_
     */
    public static native void XListView_SetDrawItemBkFlags (int hEle, int nFlags);
    /**
     设置列表视选择项
     @param hEle 列表视元素句柄
     @param iGroup 组索引
     @param iItem 项索引
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XListView_SetSelectItem (int hEle, int iGroup, int iItem);
    /**
     获取列表视选择项
     @param hEle 列表视元素句柄
     @param piGroup 接收组索引的数组
     @param piItem 接收项索引的数组
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XListView_GetSelectItem (int hEle, byte [] piGroup, byte [] piItem);
    /**
     滚动视图让列表视指定项可见
     @param hEle 列表视元素句柄
     @param iGroup 组索引
     @param iItem 项索引
     */
    public static native void XListView_VisibleItem (int hEle, int iGroup, int iItem);
    /**
     获取列表视当前可见项范围
     @param hEle 列表视元素句柄
     @param piGroup1 接收可视开始组的数组
     @param piGroup2 接收可视结束组的数组
     @param piStartGroup 接收可视开始组的数组
     @param piStartItem 接收可视开始项的数组
     @param piEndGroup 接收可视结束组的数组
     @param piEndItem 接收可视结束项的数组
     */
    public static native void XListView_GetVisibleItemRange (int hEle, byte [] piGroup1, byte [] piGroup2, byte [] piStartGroup, byte [] piStartItem, byte [] piEndGroup, byte [] piEndItem);
    /**
     获取列表视选择项数量
     @param hEle 列表视元素句柄
     @return 返回选择项数量
     */
    public static native int XListView_GetSelectItemCount (int hEle);
    /**
     选择列表视所有项
     @param hEle 列表视元素句柄
     */
    public static native void XListView_SetSelectAll (int hEle);
    /**
     设置列表视列间隔大小
     @param hEle 列表视元素句柄
     @param space 间隔大小
     */
    public static native void XListView_SetColumnSpace (int hEle, int space);
    /**
     设置列表视行间隔大小
     @param hEle 列表视元素句柄
     @param space 间隔大小
     */
    public static native void XListView_SetRowSpace (int hEle, int space);
    /**
     设置列表视项大小
     @param hEle 列表视元素句柄
     @param width 宽度
     @param height 高度
     */
    public static native void XListView_SetItemSize (int hEle, int width, int height);
    /**
     设置列表视组高度
     @param hEle 列表视元素句柄
     @param height 高度
     */
    public static native void XListView_SetGroupHeight (int hEle, int height);
    /**
     获取列表视组高度
     @param hEle 列表视元素句柄
     @return 返回组高度
     */
    public static native int XListView_GetGroupHeight (int hEle);
    /**
     设置列表视拖动矩形颜色和宽度
     @param hEle 列表视元素句柄
     @param color 颜色值
     @param width 线宽
     */
    public static native void XListView_SetDragRectColor (int hEle, long color, int width);
    /**
     刷新列表视指定项
     @param hEle 列表视元素句柄
     @param iGroup 组索引
     @param iItem 项索引
     */
    public static native void XListView_RefreshItem (int hEle, int iGroup, int iItem);
    /**
     展开 / 折叠列表视组
     @param hEle 列表视元素句柄
     @param iGroup 组索引
     @param bExpand 是否展开
     @return 成功返回 true，失败或状态未改变返回 false
     */
    public static native boolean XListView_ExpandGroup (int hEle, int iGroup, boolean bExpand);

    /**
     列表视_组添加列
     @param hEle 列表视元素句柄
     @param pName 字段名称
     @return 返回列索引
     **/
    public static native int XListView_Group_AddColumn (int hEle, String pName);
    /**
     列表视_组添加项图片（默认填充第一列）
     @param hEle 列表视元素句柄
     @param hImage 图片句柄
     @param iPos 插入位置索引（-1 添加到末尾）
     @return 返回组索引
     **/
    public static native int XListView_Group_AddItemImage (int hEle, int hImage, int iPos);
    /**
     列表视_组添加项图片（填充指定列）
     @param hEle 列表视元素句柄
     @param pName 字段名称
     @param hImage 图片句柄
     @param iPos 插入位置索引（-1 添加到末尾）
     @return 返回组索引
     **/
    public static native int XListView_Group_AddItemImageEx (int hEle, String pName, int hImage, int iPos);
    /**
     列表视_组设置文本（通过列索引）
     @param hEle 列表视元素句柄
     @param iGroup 组索引
     @param iColumn 列索引
     @param pValue 文本值
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XListView_Group_SetText (int hEle, int iGroup, int iColumn, String pValue);
    /**
     列表视_组设置文本（通过字段名）
     @param hEle 列表视元素句柄
     @param iGroup 组索引
     @param pName 字段名称
     @param pValue 文本值
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XListView_Group_SetTextEx (int hEle, int iGroup, String pName, String pValue);
    /**
     列表视_组设置图片（通过列索引）
     @param hEle 列表视元素句柄
     @param iGroup 组索引
     @param iColumn 列索引
     @param hImage 图片句柄
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XListView_Group_SetImage (int hEle, int iGroup, int iColumn, int hImage);
    /**
     列表视_组设置图片（通过字段名）
     @param hEle 列表视元素句柄
     @param iGroup 组索引
     @param pName 字段名称
     @param hImage 图片句柄
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XListView_Group_SetImageEx (int hEle, int iGroup, String pName, int hImage);
    /**
     列表视_组获取图片（通过列索引）
     @param hEle 列表视元素句柄
     @param iGroup 组索引
     @param iColumn 列索引
     @return 返回图片句柄
     **/
    public static native int XListView_Group_GetImage (int hEle, int iGroup, int iColumn);
    /**
     列表视_组获取图片（通过字段名）
     @param hEle 列表视元素句柄
     @param iGroup 组索引
     @param pName 字段名称
     @return 返回图片句柄
     **/
    public static native int XListView_Group_GetImageEx (int hEle, int iGroup, String pName);
    /**
     列表视_组获取数量
     @param hEle 列表视元素句柄
     @return 返回组数量
     **/
    public static native int XListView_Group_GetCount (int hEle);
    /**
     列表视_项获取指定组的项数量
     @param hEle 列表视元素句柄
     @param iGroup 组索引
     @return 返回指定组的项数量
     **/
    public static native int XListView_Item_GetCount (int hEle, int iGroup);
    /**
     列表视_项添加列
     @param hEle 列表视元素句柄
     @param pName 字段名称
     @return 返回列索引
     **/
    public static native int XListView_Item_AddColumn (int hEle, String pName);
    /**
     列表视_项添加图片（默认填充第一列）
     @param hEle 列表视元素句柄
     @param iGroup 组索引
     @param hImage 图片句柄
     @param iPos 插入位置索引（-1 添加到末尾）
     @return 返回项索引
     **/
    public static native int XListView_Item_AddItemImage (int hEle, int iGroup, int hImage, int iPos);
    /**
     列表视_项添加图片（填充指定列）
     @param hEle 列表视元素句柄
     @param iGroup 组索引
     @param pName 字段名称
     @param hImage 图片句柄
     @param iPos 插入位置索引（-1 添加到末尾）
     @return 返回项索引
     **/
    public static native int XListView_Item_AddItemImageEx (int hEle, int iGroup, String pName, int hImage, int iPos);
    /**
     列表视_项设置文本（通过列索引）
     @param hEle 列表视元素句柄
     @param iGroup 组索引
     @param iItem 项索引
     @param iColumn 列索引
     @param pValue 文本值
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XListView_Item_SetText (int hEle, int iGroup, int iItem, int iColumn, String pValue);
    /**
     列表视_项设置文本（通过字段名）
     @param hEle 列表视元素句柄
     @param iGroup 组索引
     @param iItem 项索引
     @param pName 字段名称
     @param pValue 文本值
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XListView_Item_SetTextEx (int hEle, int iGroup, int iItem, String pName, String pValue);
    /**
     列表视_项设置图片（通过列索引）
     @param hEle 列表视元素句柄
     @param iGroup 组索引
     @param iItem 项索引
     @param iColumn 列索引
     @param hImage 图片句柄
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XListView_Item_SetImage (int hEle, int iGroup, int iItem, int iColumn, int hImage);
    /**
     列表视_项设置图片（通过字段名）
     @param hEle 列表视元素句柄
     @param iGroup 组索引
     @param iItem 项索引
     @param pName 字段名称
     @param hImage 图片句柄
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XListView_Item_SetImageEx (int hEle, int iGroup, int iItem, String pName, int hImage);
    /**
     列表视_组删除项
     @param hEle 列表视元素句柄
     @param iGroup 组索引
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XListView_Group_DeleteItem (int hEle, int iGroup);
    /**
     列表视_组删除所有子项
     @param hEle 列表视元素句柄
     @param iGroup 组索引
     **/
    public static native void XListView_Group_DeleteAllChildItem (int hEle, int iGroup);

    /**
     * 删除列表视指定组中的指定项
     * @param hEle 列表视元素句柄
     * @param iGroup 组索引
     * @param iItem 项索引
     * @return 成功返回true，否则返回false
     */
    public static native boolean XListView_Item_DeleteItem(int hEle, int iGroup, int iItem);

    /**
     * 通过列索引获取列表视指定项的图片句柄
     * @param hEle 列表视元素句柄
     * @param iGroup 组索引
     * @param iItem 项索引
     * @param iColumn 列索引
     * @return 返回图片句柄，失败返回0
     */
    public static native int XListView_Item_GetImage(int hEle, int iGroup, int iItem, int iColumn);

    /**
     * 通过字段名获取列表视指定项的图片句柄
     * @param hEle 列表视元素句柄
     * @param iGroup 组索引
     * @param iItem 项索引
     * @param pName 字段名
     * @return 返回图片句柄，失败返回0
     */
    public static native int XListView_Item_GetImageEx(int hEle, int iGroup, int iItem, String pName);

    /**
     * 创建窗格元素
     * @param pName 窗格标题
     * @param nWidth 宽度
     * @param nHeight 高度
     * @param hFrameWnd 框架窗口句柄（可为0）
     * @return 返回窗格元素句柄，失败返回0
     */
    public static native int XPane_Create(String pName, int nWidth, int nHeight, int hFrameWnd);

    /**
     * 设置窗格视图元素
     * @param hEle 窗格元素句柄
     * @param hView 视图元素句柄
     */
    public static native void XPane_SetView(int hEle, int hView);

    /**
     * 设置窗格标题文本
     * @param hEle 窗格元素句柄
     * @param pTitle 标题文本的字节数组
     */
    public static native void XPane_SetTitle(int hEle, byte[] pTitle);

    /**
     * 获取窗格标题文本
     * @param hEle 窗格元素句柄
     * @param dwSize 接收缓冲区大小
     * @return 返回标题文本的字节数组，失败返回null
     */
    public static native byte[] XPane_GetTitle(int hEle, int dwSize);

    /**
     * 设置窗格标题栏高度
     * @param hEle 窗格元素句柄
     * @param nHeight 高度
     */
    public static native void XPane_SetCaptionHeight(int hEle, int nHeight);

    /**
     * 获取窗格标题栏高度
     * @param hEle 窗格元素句柄
     * @return 返回标题栏高度
     */
    public static native int XPane_GetCaptionHeight(int hEle);

    /**
     * 判断窗格是否显示
     * @param hEle 窗格元素句柄
     * @return 显示返回true，否则返回false
     */
    public static native boolean XPane_IsShowPane(int hEle);

    /**
     * 判断窗格是否为组激活状态（当为窗格组成员时有效）
     * @param hEle 窗格元素句柄
     * @return 是窗格组显示项返回true，否则返回false
     */
    public static native boolean XPane_IsGroupActivate(int hEle);

    /**
     * 设置窗格大小
     * @param hEle 窗格元素句柄
     * @param nWidth 宽度
     * @param nHeight 高度
     */
    public static native void XPane_SetSize(int hEle, int nWidth, int nHeight);

    /**
     * 获取窗格停靠状态
     * @param hEle 窗格元素句柄
     * @return 返回状态标识（0:任意, 1:锁定, 2:停靠码头, 3:浮动窗格）
     */
    public static native int XPane_GetState(int hEle);

    /**
     * 获取窗格视图坐标
     * @param hEle 窗格元素句柄
     * @param pRect 接收返回的坐标值
     */
    public static native void XPane_GetViewRect(int hEle, TagRect pRect);

    /**
     * 隐藏窗格
     * @param hEle 窗格元素句柄
     * @param bGroupDelay 当为窗格组成员时，是否延迟处理窗格组成员激活的切换
     */
    public static native void XPane_HidePane(int hEle, boolean bGroupDelay);

    /**
     * 显示窗格
     * @param hEle 窗格元素句柄
     * @param bGroupActivate 如果是窗格组成员，是否将当前窗格切换为显示状态
     */
    public static native void XPane_ShowPane(int hEle, boolean bGroupActivate);

    /**
     * 将窗格停靠到码头
     * @param hEle 窗格元素句柄
     */
    public static native void XPane_DockPane(int hEle);

    /**
     * 锁定窗格
     * @param hEle 窗格元素句柄
     */
    public static native void XPane_LockPane(int hEle);

    /**
     * 浮动窗格
     * @param hEle 窗格元素句柄
     */
    public static native void XPane_FloatPane(int hEle);

    /**
     窗格_绘制
     @param hEle 窗格元素句柄
     @param hDraw 图形绘制句柄
     **/
    public static native void XPane_DrawPane (int hEle, long hDraw);
    /**
     窗格_置选中
     @param hEle 窗格元素句柄
     @return 如果为窗格组显示项返回 TRUE, 否则返回 FALSE
     **/
    public static native boolean XPane_SetSelect (int hEle);
    /**
     窗格_取 TabBar
     @param hEle 窗格元素句柄
     @return 若是有返回 tabBar 句柄，否则返回空
     **/
    public static native int XPane_GetTabBar (int hEle);
    /**
     窗格_取分割条
     @param hEle 窗格元素句柄
     @return 若是有返回分割条句柄，否则返回空
     **/
    public static native int XPane_GetSplitBar (int hEle);
    /**
     窗格_取控制按钮
     @param hEle 窗格元素句柄
     @param number 控制按钮编号: 1: 菜单按钮，2: 停靠码头按钮，3: 关闭按钮
     @return 返回按钮句柄
     **/
    public static native int XPane_GetButton (int hEle, int number);
    /**
     窗格_显示控制按钮
     @param hPane 窗格元素句柄
     @param bShow 是否显示
     **/
    public static native void XPane_ShowButton (int hPane, boolean bShow);
    /**
     进度条_创建
     @param x 元素 x 坐标
     @param y 元素 y 坐标
     @param cx 宽度
     @param cy 高度
     @param hParent 父为窗口句柄或元素句柄
     @return 元素句柄
     **/
    public static native int XProgBar_Create (int x, int y, int cx, int cy, int hParent);
    /**
     进度条_启用进度文本
     @param hEle 进度条元素句柄
     @param bShow 是否启用
     **/
    public static native void XProgBar_EnableShowText (int hEle, boolean bShow);
    /**
     进度条_启用缩放
     @param hEle 进度条元素句柄
     @param bStretch 是否缩放进度贴图为当前进度区域
     **/
    public static native void XProgBar_EnableStretch (int hEle, boolean bStretch);
    /**
     进度条_置范围
     @param hEle 进度条元素句柄
     @param range 范围
     **/
    public static native void XProgBar_SetRange (int hEle, int range);
    /**
     进度条_取范围
     @param hEle 进度条元素句柄
     @return 返回范围
     **/
    public static native int XProgBar_GetRange (int hEle);
    /**
     进度条_置进度图片
     @param hEle 进度条元素句柄
     @param hImage 图片句柄
     **/
    public static native void XProgBar_SetImageLoad (int hEle, int hImage);
    /**
     进度条_置进度颜色
     @param hEle 进度条元素句柄
     @param color 颜色值，请使用宏: RGBA ()
     **/
    public static native void XProgBar_SetColorLoad (int hEle, long color);
    /**
     进度条_置进度
     @param hEle 进度条元素句柄
     @param pos 位置点
     **/
    public static native void XProgBar_SetPos (int hEle, int pos);
    /**
     进度条_取进度
     @param hEle 进度条元素句柄
     @return 返回当前位置点
     **/
    public static native int XProgBar_GetPos (int hEle);
    /**
     进度条_启用水平
     @param hEle 进度条元素句柄
     @param bHorizon 是否水平或垂直
     **/
    public static native void XProgBar_EnableHorizon (int hEle, boolean bHorizon);
    /**
     滚动条_创建
     @param x 元素 x 坐标
     @param y 元素 y 坐标
     @param cx 宽度
     @param cy 高度
     @param hParent 父为窗口句柄或元素句柄
     @return 元素句柄
     **/
    public static native int XSBar_Create (int x, int y, int cx, int cy, int hParent);
    /**
     滚动条_置范围
     @param hEle 滚动条元素句柄
     @param range 滚动范围
     **/
    public static native void XSBar_SetRange (int hEle, int range);
    /**
     滚动条_取范围
     @param hEle 滚动条元素句柄
     @return 返回滚动范围
     **/
    public static native int XSBar_GetRange (int hEle);

    /**
     显示隐藏滚动条上下按钮
     @param hEle 滚动条元素句柄
     @param bShow 是否显示
     */
    public static native void XSBar_ShowButton (int hEle, boolean bShow);
    /**
     设置滑块长度
     @param hEle 滚动条元素句柄
     @param length 滑块长度
     */
    public static native void XSBar_SetSliderLength (int hEle, int length);
    /**
     设置滑块最小长度
     @param hEle 滚动条元素句柄
     @param minLength 滑块最小长度
     */
    public static native void XSBar_SetSliderMinLength (int hEle, int minLength);
    /**
     设置滑块两端的间隔大小
     @param hEle 滚动条元素句柄
     @param nPadding 滑块两端的间隔大小
     */
    public static native void XSBar_SetSliderPadding (int hEle, int nPadding);
    /**
     设置水平或者垂直
     @param hEle 滚动条元素句柄
     @param bHorizon TRUE 为水平，FALSE 为垂直
     @return 如果改变返回 TRUE 否则返回 FALSE
     */
    public static native boolean XSBar_EnableHorizon (int hEle, boolean bHorizon);
    /**
     获取滑块最大长度
     @param hEle 滚动条元素句柄
     @return 滑块最大长度
     */
    public static native int XSBar_GetSliderMaxLength (int hEle);
    /**
     向上滚动
     @param hEle 滚动条元素句柄
     @return 如果成功返回 TRUE，否则返回 FALSE
     */
    public static native boolean XSBar_ScrollUp (int hEle);
    /**
     向下滚动
     @param hEle 滚动条元素句柄
     @return 如果成功返回 TRUE，否则返回 FALSE
     */
    public static native boolean XSBar_ScrollDown (int hEle);
    /**
     滚动到顶部
     @param hEle 滚动条元素句柄
     @return 如果成功返回 TRUE，否则返回 FALSE
     */
    public static native boolean XSBar_ScrollTop (int hEle);
    /**
     滚动到底部
     @param hEle 滚动条元素句柄
     @return 如果成功返回 TRUE，否则返回 FALSE
     */
    public static native boolean XSBar_ScrollBottom (int hEle);
    /**
     滚动到指定位置点，触发事件 XE_SBAR_SCROLL
     @param hEle 滚动条元素句柄
     @param pos 位置点
     @return 如果成功返回 TRUE，否则返回 FALSE
     */
    public static native boolean XSBar_ScrollPos (int hEle, int pos);
    /**
     获取上按钮
     @param hEle 滚动条元素句柄
     @return 返回上按钮句柄
     */
    public static native int XSBar_GetButtonUp (int hEle);
    /**
     获取下按钮
     @param hEle 滚动条元素句柄
     @return 返回下按钮句柄
     */
    public static native int XSBar_GetButtonDown (int hEle);
    /**
     获取滑动按钮
     @param hEle 滚动条元素句柄
     @return 返回滑动按钮句柄
     */
    public static native int XSBar_GetButtonSlider (int hEle);
    /**
     创建滑动条元素
     @param x 元素 x 坐标
     @param y 元素 y 坐标
     @param cx 元素宽度
     @param cy 元素高度
     @param hParent 父对象句柄（窗口句柄或 UI 元素句柄）
     @return 返回滑动条元素句柄
     */
    public static native int XSliderBar_Create (int x, int y, int cx, int cy, int hParent);
    /**
     设置滑动范围
     @param hEle 滑动条元素句柄
     @param range 滑动范围
     */
    public static native void XSliderBar_SetRange (int hEle, int range);
    /**
     获取滚动范围
     @param hEle 滑动条元素句柄
     @return 返回滚动范围
     */
    public static native int XSliderBar_GetRange (int hEle);
    /**
     设置进度贴图
     @param hEle 滑动条元素句柄
     @param hImage 图片句柄
     */
    public static native void XSliderBar_SetImageLoad (int hEle, int hImage);
    /**
     设置滑块按钮宽度
     @param hEle 滑动条元素句柄
     @param width 滑块按钮宽度
     */
    public static native void XSliderBar_SetButtonWidth (int hEle, int width);
    /**
     设置滑块按钮高度
     @param hEle 滑动条元素句柄
     @param height 滑块按钮高度
     */
    public static native void XSliderBar_SetButtonHeight (int hEle, int height);

    /**
     滑动条_置当前位置
     @param hEle 滑动条元素句柄
     @param pos 当前进度点
     */
    public static native void XSliderBar_SetPos (int hEle, int pos);
    /**
     滑动条_取当前位置
     @param hEle 滑动条元素句柄
     @return 返回当前进度点
     */
    public static native int XSliderBar_GetPos (int hEle);
    /**
     滑动条_取滑块
     @param hEle 滑动条元素句柄
     @return 按钮句柄
     */
    public static native int XSliderBar_GetButton (int hEle);
    /**
     滑动条_启用水平
     @param hEle 滑动条元素句柄
     @param bHorizon 是否水平
     */
    public static native void XSliderBar_EnableHorizon (int hEle, boolean bHorizon);
    /**
     TAB 条_创建
     @param x 元素 x 坐标
     @param y 元素 y 坐标
     @param cx 宽度
     @param cy 高度
     @param hParent 父为窗口句柄或元素句柄
     @return 元素句柄
     */
    public static native int XTabBar_Create (int x, int y, int cx, int cy, int hParent);
    /**
     TAB 条_添加标签
     @param hEle 元素句柄
     @param pName 标签文本内容
     @return 标签索引
     */
    public static native int XTabBar_AddLabel (int hEle, String pName);
    /**
     TAB 条_插入标签
     @param hEle 元素句柄
     @param index 插入位置
     @param pName 标签文本内容
     @return 标签索引
     */
    public static native int XTabBar_InsertLabel (int hEle, int index, String pName);
    /**
     TAB 条_移动标签
     @param hEle 元素句柄
     @param iSrc 源位置索引
     @param iDest 目标位置索引
     @return 成功返回 TRUE 否则 FALSE
     */
    public static native boolean XTabBar_MoveLabel (int hEle, int iSrc, int iDest);
    /**
     TAB 条_删除标签
     @param hEle 元素句柄
     @param index 位置索引
     @return 成功返回 TRUE 否则 FALSE
     */
    public static native boolean XTabBar_DeleteLabel (int hEle, int index);
    /**
     TAB 条_删除全部
     @param hEle 元素句柄
     */
    public static native void XTabBar_DeleteLabelAll (int hEle);
    /**
     TAB 条_取标签
     @param hEle 元素句柄
     @param index 位置索引
     @return 按钮句柄
     */
    public static native int XTabBar_GetLabel (int hEle, int index);
    /**
     TAB 条_取标签上的关闭按钮
     @param hEle 元素句柄
     @param index 位置索引
     @return 按钮句柄
     */
    public static native int XTabBar_GetLabelClose (int hEle, int index);
    /**
     TAB 条_取左滚动按钮
     @param hEle 元素句柄
     @return 返回按钮句柄
     */
    public static native int XTabBar_GetButtonLeft (int hEle);
    /**
     TAB 条_取右滚动按钮
     @param hEle 元素句柄
     @return 返回按钮句柄
     */
    public static native int XTabBar_GetButtonRight (int hEle);
    /**
     TAB 条_取下拉菜单按钮句柄
     @param hEle 元素句柄
     @return 返回按钮句柄
     */
    public static native int XTabBar_GetButtonDropMenu (int hEle);
    /**
     TAB 条_取当前选择
     @param hEle 元素句柄
     @return 标签位置索引
     */
    public static native int XTabBar_GetSelect (int hEle);
    /**
     TAB 条_取间隔
     @param hEle 元素句柄
     @return 标签间隔大小
     */
    public static native int XTabBar_GetLabelSpacing (int hEle);
    /**
     TAB 条_取标签数量
     @param hEle 元素句柄
     @return 标签项数量
     */
    public static native int XTabBar_GetLabelCount (int hEle);
    /**
     TAB 条_取标签位置索引
     @param hEle 元素句柄
     @param hLabel 标签按钮句柄
     @return 成功返回索引值，否则返回 XC_ID_ERROR
     */
    public static native int XTabBar_GetindexByEle (int hEle, int hLabel);
    /**
     TAB 条_置间隔
     @param hEle 元素句柄
     @param spacing 标签间隔大小
     */
    public static native void XTabBar_SetLabelSpacing (int hEle, int spacing);
    /**
     TAB 条_置选择
     @param hEle 元素句柄
     @param index 标签位置索引
     */
    public static native void XTabBar_SetSelect (int hEle, int index);
    /**
     TAB 条_左滚动
     @param hEle 元素句柄
     */
    public static native void XTabBar_SetUp (int hEle);
    /**
     TAB 条_右滚动
     @param hEle 元素句柄
     */
    public static native void XTabBar_SetDown (int hEle);
    /**
     TAB 条_启用平铺
     @param hEle 元素句柄
     @param bTile 是否启用
     */
    public static native void XTabBar_EnableTile (int hEle, boolean bTile);

    /**
     启用下拉菜单按钮
     @param hEle TabBar 元素句柄
     @param bEnable 是否启用
     */
    public static native void XTabBar_EnableDropMenu (int hEle, boolean bEnable);
    /**
     启用关闭标签功能
     @param hEle TabBar 元素句柄
     @param bEnable 是否启用
     */
    public static native void XTabBar_EnableClose (int hEle, boolean bEnable);
    /**
     设置关闭按钮大小
     @param hEle TabBar 元素句柄
     @param pSize 大小值，宽度和高度可以为 - 1，代表使用默认值
     */
    public static native void XTabBar_SetCloseSize (int hEle, TagSize pSize);
    /**
     设置翻滚按钮大小
     @param hEle TabBar 元素句柄
     @param pSize 大小值，宽度和高度可以为 - 1，代表使用默认值
     */
    public static native void XTabBar_SetTurnButtonSize (int hEle, TagSize pSize);
    /**
     设置指定标签为固定宽度
     @param hEle TabBar 元素句柄
     @param index 标签索引
     @param nWidth 标签宽度，-1 则自动计算宽度
     */
    public static native void XTabBar_SetLabelWidth (int hEle, int index, int nWidth);
    /**
     显示或隐藏指定标签
     @param hEle TabBar 元素句柄
     @param index 标签索引
     @param bShow 是否显示
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XTabBar_ShowLabel (int hEle, int index, boolean bShow);
    /**
     创建静态文本链接元素
     @param x 元素 x 坐标
     @param y 元素 y 坐标
     @param cx 宽度
     @param cy 高度
     @param pName 文本内容
     @param hParent 父为窗口句柄或元素句柄
     @return 文本链接元素句柄
     */
    public static native int XTextLink_Create (int x, int y, int cx, int cy, String pName, int hParent);
    /**
     启用下划线（鼠标离开状态）
     @param hEle 文本链接元素句柄
     @param bEnable 是否启用
     */
    public static native void XTextLink_EnableUnderlineLeave (int hEle, boolean bEnable);
    /**
     启用下划线（鼠标停留状态）
     @param hEle 文本链接元素句柄
     @param bEnable 是否启用
     */
    public static native void XTextLink_EnableUnderlineStay (int hEle, boolean bEnable);
    /**
     设置文本颜色（鼠标停留状态）
     @param hEle 文本链接元素句柄
     @param color 颜色值，使用 RGBA 宏定义
     */
    public static native void XTextLink_SetTextColorStay (int hEle, long color);
    /**
     设置下划线颜色（鼠标离开状态）
     @param hEle 文本链接元素句柄
     @param color 颜色值，使用 RGBA 宏定义
     */
    public static native void XTextLink_SetUnderlineColorLeave (int hEle, long color);
    /**
     设置下划线颜色（鼠标停留状态）
     @param hEle 文本链接元素句柄
     @param color 颜色值，使用 RGBA 宏定义
     */
    public static native void XTextLink_SetUnderlineColorStay (int hEle, long color);
    /**
     创建工具条元素；如果指定了父为窗口，默认调用 XWnd_AddToolBar () 函数，将工具条添加到窗口非客户区
     @param x 元素 x 坐标
     @param y 元素 y 坐标
     @param cx 宽度
     @param cy 高度
     @param hParent 父是窗口资源句柄或 UI 元素资源句柄
     @return 工具条元素句柄
     */
    public static native int XToolBar_Create (int x, int y, int cx, int cy, int hParent);
    /**
     插入元素到工具条
     @param hEle 工具条元素句柄
     @param hNewEle 将要插入的元素
     @param index 插入位置索引，-1 插入到末尾
     @return 返回插入位置索引
     */
    public static native int XToolBar_InsertEle (int hEle, int hNewEle, int index);
    /**
     插入分隔符到工具条
     @param hEle 工具条元素句柄
     @param index 插入位置索引，-1 插入到末尾
     @param color 分隔符颜色，使用 RGBA 宏定义
     @return 返回插入位置索引
     */
    public static native int XToolBar_InsertSeparator (int hEle, int index, long color);
    /**
     启用下拉菜单，显示隐藏的项
     @param hEle 工具条元素句柄
     @param bEnable 是否启用
     */
    public static native void XToolBar_EnableButtonMenu (int hEle, boolean bEnable);
    /**
     获取工具条上指定元素
     @param hEle 工具条元素句柄
     @param index 索引值
     @return 返回元素句柄
     */
    public static native int XToolBar_GetEle (int hEle, int index);
    /**
     获取左滚动按钮
     @param hEle 工具条元素句柄
     @return 返回左滚动按钮句柄
     */
    public static native int XToolBar_GetButtonLeft (int hEle);
    /**
     获取右滚动按钮
     @param hEle 工具条元素句柄
     @return 返回右滚动按钮句柄
     */
    public static native int XToolBar_GetButtonRight (int hEle);

    /**
     获取菜单按钮
     @param hEle 元素句柄
     @return 返回菜单按钮句柄
     **/
    public static native int XToolBar_GetButtonMenu (int hEle);
    /**
     设置对象之间的间距
     @param hEle 元素句柄
     @param nSize 间距大小
     **/
    public static native void XToolBar_SetSpace (int hEle, int nSize);
    /**
     删除元素，并且销毁
     @param hEle 元素句柄
     @param index 索引值
     **/
    public static native void XToolBar_DeleteEle (int hEle, int index);
    /**
     删除所有元素，并且销毁
     @param hEle 元素句柄
     **/
    public static native void XToolBar_DeleteAllEle (int hEle);
    /**
     启用拖动项功能
     @param hEle 元素句柄
     @param bEnable 是否启用
     **/
    public static native void XTree_EnableDragItem (int hEle, boolean bEnable);
    /**
     启用或禁用显示项的连接线
     @param hEle 元素句柄
     @param bEnable 是否启用
     @param bSolid 实线或虚线；TRUE: 实线，FALSE: 虚线
     **/
    public static native void XTree_EnableConnectLine (int hEle, boolean bEnable, boolean bSolid);
    /**
     启动或关闭默认展开功能，如果开启新插入的项将自动展开
     @param hEle 元素句柄
     @param bEnable 是否启用
     **/
    public static native void XTree_EnableExpand (int hEle, boolean bEnable);
    /**
     启用模板复用
     @param hEle 元素句柄
     @param bEnable 是否启用
     **/
    public static native void XTree_EnableTemplateReuse (int hEle, boolean bEnable);
    /**
     设置项连接线颜色
     @param hEle 元素句柄
     @param color 颜色值，请使用宏: RGBA ()
     **/
    public static native void XTree_SetConnectLineColor (int hEle, long color);
    /**
     设置展开按钮占用空间大小
     @param hEle 元素句柄
     @param nWidth 宽度
     @param nHeight 高度
     **/
    public static native void XTree_SetExpandButtonSize (int hEle, int nWidth, int nHeight);
    /**
     设置连线绘制长度，展开按钮与项内容之间的连线
     @param hEle 元素句柄
     @param nLength 连线绘制长度
     **/
    public static native void XTree_SetConnectLineLength (int hEle, int nLength);
    /**
     设置拖动项插入位置颜色提示
     @param hEle 元素句柄
     @param color 颜色值
     **/
    public static native void XTree_SetDragInsertPositionColor (int hEle, long color);
    /**
     设置项模板文件
     @param hEle 元素句柄
     @param pXmlFile 文件名
     @return 成功返回 TRUE 否则返回 FALSE
     **/
    public static native boolean XTree_SetItemTemplateXML (int hEle, String pXmlFile);
    /**
     设置项模板文件，项选中状态
     @param hEle 元素句柄
     @param pXmlFile 文件名
     @return 成功返回 TRUE 否则返回 FALSE
     **/
    public static native boolean XTree_SetItemTemplateXMLSel (int hEle, String pXmlFile);
    /**
     设置列表项模板
     @param hEle 元素句柄
     @param hTemp 模板句柄
     @return 如果成功返回 TRUE，否则返回 FALSE
     **/
    public static native boolean XTree_SetItemTemplate (int hEle, int hTemp);
    /**
     设置列表项模板，项选中状态
     @param hEle 元素句柄
     @param hTemp 模板句柄
     @return 如果成功返回 TRUE，否则返回 FALSE
     **/
    public static native boolean XTree_SetItemTemplateSel (int hEle, int hTemp);
    /**
     设置项模板文件
     @param hEle 元素句柄
     @param pStringXML 字符串指针
     @return 成功返回 TRUE 否则返回 FALSE
     **/
    public static native boolean XTree_SetItemTemplateXMLFromString (int hEle, String pStringXML);
    /**
     设置项模板文件，项选中状态
     @param hEle 元素句柄
     @param pStringXML 字符串指针
     @return 成功返回 TRUE 否则返回 FALSE
     **/
    public static native boolean XTree_SetItemTemplateXMLSelFromString (int hEle, String pStringXML);
    /**
     设置项模板文件从内存
     @param hEle 元素句柄
     @param data 内存地址
     @param length 内存大小，字节为单位
     @return 成功返回 TRUE 否则返回 FALSE
     **/
    public static native boolean XTree_SetItemTemplateXMLFromMem (int hEle, byte [] data, int length);
    /**
     设置项模板文件从资源 ZIP，RC 资源类型必须为:"RT_RCDATA"
     @param hEle 元素句柄
     @param id RC 资源 ID
     @param pFileName 项模板文件名
     @param pPassword zip 密码
     @param hModule 模块句柄
     @return 如果成功返回 TRUE，否则返回 FALSE
     **/
    public static native boolean XTree_SetItemTemplateXMLFromZipRes (int hEle, int id, String pFileName, String pPassword, long hModule);

    /**
     获取列表树的项模板句柄
     @param hEle 列表树元素句柄
     @return 返回项模板句柄
     */
    public static native int XTree_GetItemTemplate (int hEle);
    /**
     设置是否绘制指定状态下项的背景
     @param hEle 列表树元素句柄
     @param nFlags 标志位 ListDrawItemBkFlag
     */
    public static native void XTree_SetDrawItemBkFlags (int hEle, int nFlags);
    /**
     设置列表树的分割线颜色
     @param hEle 列表树元素句柄
     @param color 颜色值，使用宏 RGBA () 生成
     */
    public static native void XTree_SetSplitLineColor (int hEle, long color);
    /**
     设置列表树项的用户数据
     @param hEle 列表树元素句柄
     @param nID 项 ID
     @param nUserData 用户数据的字节数组
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XTree_SetItemData (int hEle, int nID, byte [] nUserData);
    /**
     获取列表树项的用户数据
     @param hEle 列表树元素句柄
     @param nID 项 ID
     @param dwSize 要获取的数据大小（字节）
     @return 返回用户数据的字节数组
     */
    public static native byte [] XTree_GetItemData (int hEle, int nID, int dwSize);
    /**
     设置列表树的选择项
     @param hEle 列表树元素句柄
     @param nID 项 ID
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XTree_SetSelectItem (int hEle, int nID);
    /**
     获取列表树的选择项 ID
     @param hEle 列表树元素句柄
     @return 返回选择项的 ID
     */
    public static native int XTree_GetSelectItem (int hEle);
    /**
     滚动视图让列表树指定项可见
     @param hEle 列表树元素句柄
     @param nID 项 ID
     */
    public static native void XTree_VisibleItem (int hEle, int nID);
    /**
     判断列表树项是否展开
     @param hEle 列表树元素句柄
     @param nID 项 ID
     @return 展开返回 true，否则返回 false
     */
    public static native boolean XTree_IsExpand (int hEle, int nID);
    /**
     展开或折叠列表树项
     @param hEle 列表树元素句柄
     @param nID 项 ID
     @param bExpand 是否展开（true: 展开，false: 折叠）
     @return 成功返回 true，失败或状态未改变返回 false
     */
    public static native boolean XTree_ExpandItem (int hEle, int nID, boolean bExpand);
    /**
     展开或折叠列表树项的所有子项
     @param hEle 列表树元素句柄
     @param nID 项 ID
     @param bExpand 是否展开（true: 展开，false: 折叠）
     @return 成功返回 true，失败返回 false
     */
    public static native boolean XTree_ExpandAllChildItem (int hEle, int nID, boolean bExpand);
    /**
     检测坐标点所在的列表树项
     @param hEle 列表树元素句柄
     @param pPt 坐标点
     @return 返回项 ID，失败返回 XC_ID_ERROR
     */
    public static native int XTree_HitTest (int hEle, TagPoint pPt);
    /**
     检测坐标点所在的列表树项，自动添加滚动视图偏移坐标
     @param hEle 列表树元素句柄
     @param pPt 坐标点
     @return 返回项 ID，失败返回 XC_ID_ERROR
     */
    public static native int XTree_HitTestOffset (int hEle, TagPoint pPt);
    /**
     获取列表树项的第一个子项
     @param hEle 列表树元素句柄
     @param nID 项 ID
     @return 返回第一个子项的 ID，失败返回 XC_ID_ERROR
     */
    public static native int XTree_GetFirstChildItem (int hEle, int nID);
    /**
     获取列表树项的末尾子项
     @param hEle 列表树元素句柄
     @param nID 项 ID
     @return 返回末尾子项的 ID，失败返回 XC_ID_ERROR
     */
    public static native int XTree_GetEndChildItem (int hEle, int nID);
    /**
     获取列表树项的上一个兄弟项
     @param hEle 列表树元素句柄
     @param nID 项 ID
     @return 返回上一个兄弟项的 ID，失败返回 XC_ID_ERROR
     */
    public static native int XTree_GetPrevSiblingItem (int hEle, int nID);
    /**
     获取列表树项的下一个兄弟项
     @param hEle 列表树元素句柄
     @param nID 项 ID
     @return 返回下一个兄弟项的 ID
     */
    public static native int XTree_GetNextSiblingItem (int hEle, int nID);
    /**
     获取列表树项的父项
     @param hEle 列表树元素句柄
     @param nID 项 ID
     @return 返回父项的 ID，错误返回 - 1
     */
    public static native int XTree_GetParentItem (int hEle, int nID);
    /**
     创建列表树的数据适配器，从绑定的项模板扫描字段名初始化
     @param hEle 列表树元素句柄
     @return 返回数据适配器句柄
     */
    public static native int XTree_CreateAdapter (int hEle);

    /**
     列表树_绑定数据适配器
     @param hEle 列表树元素句柄
     @param hAdapter 数据适配器句柄 (XAdTree)
     **/
    public static native void XTree_BindAdapter (int hEle, int hAdapter);
    /**
     列表树_取数据适配器
     @param hEle 列表树元素句柄
     @return 返回绑定的数据适配器句柄
     **/
    public static native int XTree_GetAdapter (int hEle);
    /**
     列表树_刷新数据
     @param hEle 列表树元素句柄
     **/
    public static native void XTree_RefreshData (int hEle);
    /**
     列表树_刷新指定项
     @param hEle 列表树元素句柄
     @param nID 项 ID
     **/
    public static native void XTree_RefreshItem (int hEle, int nID);
    /**
     列表树_置缩进
     @param hEle 列表树元素句柄
     @param nWidth 缩进宽度
     **/
    public static native void XTree_SetIndentation (int hEle, int nWidth);
    /**
     列表树_取缩进
     @param hEle 列表树元素句柄
     @return 返回缩进值大小
     **/
    public static native int XTree_GetIndentation (int hEle);
    /**
     列表树_置项默认高度
     @param hEle 列表树元素句柄
     @param nHeight 项默认高度
     @param nSelHeight 项选中时高度
     **/
    public static native void XTree_SetItemHeightDefault (int hEle, int nHeight, int nSelHeight);
    /**
     列表树_取项默认高度
     @param hEle 列表树元素句柄
     @param pHeight 接收返回的项默认高度
     @param pSelHeight 接收返回的项选中时高度
     **/
    public static native void XTree_GetItemHeightDefault (int hEle, byte [] pHeight, byte [] pSelHeight);
    /**
     列表树_置项高度
     @param hEle 列表树元素句柄
     @param nID 项 ID
     @param nHeight 项高度
     @param nSelHeight 项选中时高度
     **/
    public static native void XTree_SetItemHeight (int hEle, int nID, int nHeight, int nSelHeight);
    /**
     列表树_取项高度
     @param hEle 列表树元素句柄
     @param nID 项 ID
     @param pHeight 接收返回的项高度
     @param pSelHeight 接收返回的项选中时高度
     **/
    public static native void XTree_GetItemHeight (int hEle, int nID, byte [] pHeight, byte [] pSelHeight);
    /**
     列表树_置行间距
     @param hEle 列表树元素句柄
     @param nSpace 行间距大小
     **/
    public static native void XTree_SetRowSpace (int hEle, int nSpace);
    /**
     列表树_取行间距
     @param hEle 列表树元素句柄
     @return 返回行间距大小
     **/
    public static native int XTree_GetRowSpace (int hEle);
    /**
     列表树_移动项
     @param hEle 列表树元素句柄
     @param nMoveItem 要移动的项 ID
     @param nDestItem 目标项 ID（参照位置）
     @param nFlag 位置标志：0 - 目标前面，1 - 目标后面，2 - 目标子项首，3 - 目标子项尾
     @return 成功返回 TRUE，否则返回 FALSE
     **/
    public static native boolean XTree_MoveItem (int hEle, int nMoveItem, int nDestItem, int nFlag);
    /**
     列表树_取模板对象
     @param hEle 列表树元素句柄
     @param nID 树项 ID
     @param nTempItemID 模板项 ID
     @return 返回实例化模板项 ID 对应的对象句柄
     **/
    public static native int XTree_GetTemplateObject (int hEle, int nID, int nTempItemID);
    /**
     列表树_取对象所在项 ID
     @param hEle 列表树元素句柄
     @param hXCGUI 对象句柄（UI 元素句柄或形状对象句柄）
     @return 成功返回项 ID，否则返回 XC_ID_ERROR
     **/
    public static native int XTree_GetItemIDFromint (int hEle, int hXCGUI);
    /**
     列表树_插入项文本
     @param hEle 列表树元素句柄
     @param pValue 文本值
     @param nParentID 父 ID（插入到目标位置时可忽略）
     @param insertID 插入位置 ID
     @return 返回项 ID 值
     **/
    public static native int XTree_InsertItemText (int hEle, String pValue, int nParentID, int insertID);
    /**
     列表树_插入项文本扩展
     @param hEle 列表树元素句柄
     @param pName 字段名称
     @param pValue 文本值
     @param nParentID 父 ID（插入到目标位置时可忽略）
     @param insertID 插入位置 ID
     @return 返回项 ID 值
     **/
    public static native int XTree_InsertItemTextEx (int hEle, String pName, String pValue, int nParentID, int insertID);
    /**
     列表树_插入项图片
     @param hEle 列表树元素句柄
     @param hImage 图片句柄
     @param nParentID 父 ID（插入到目标位置时可忽略）
     @param insertID 插入位置 ID
     @return 返回项 ID 值
     **/
    public static native int XTree_InsertItemImage (int hEle, int hImage, int nParentID, int insertID);
    /**
     列表树_插入项图片扩展
     @param hEle 列表树元素句柄
     @param pName 字段名称
     @param hImage 图片句柄
     @param nParentID 父 ID（插入到目标位置时可忽略）
     @param insertID 插入位置 ID
     @return 返回项 ID 值
     **/
    public static native int XTree_InsertItemImageEx (int hEle, String pName, int hImage, int nParentID, int insertID);

    /**
     列表树_取项数量
     @param hEle 列表树元素句柄
     @return 返回项数量
     **/
    public static native int XTree_GetCount (int hEle);
    /**
     列表树_取列数量
     @param hEle 列表树元素句柄
     @return 返回列数量
     **/
    public static native int XTree_GetCountColumn (int hEle);
    /**
     列表树_置项文本
     @param hEle 列表树元素句柄
     @param nID 项 ID
     @param iColumn 列索引
     @param pValue 值
     @return 成功返回 TRUE 否则返回 FALSE
     **/
    public static native boolean XTree_SetItemText (int hEle, int nID, int iColumn, String pValue);
    /**
     列表树_置项文本扩展
     @param hEle 列表树元素句柄
     @param nID 项 ID
     @param pName 字段名称
     @param pValue 值
     @return 成功返回 TRUE 否则返回 FALSE
     **/
    public static native boolean XTree_SetItemTextEx (int hEle, int nID, String pName, String pValue);
    /**
     列表树_置项图片
     @param hEle 列表树元素句柄
     @param nID 项 ID
     @param iColumn 列索引
     @param hImage 图片句柄
     @return 成功返回 TRUE 否则返回 FALSE
     **/
    public static native boolean XTree_SetItemImage (int hEle, int nID, int iColumn, int hImage);
    /**
     列表树_置项图片扩展
     @param hEle 列表树元素句柄
     @param nID 项 ID
     @param pName 字段名称
     @param hImage 图片句柄
     @return 成功返回 TRUE 否则返回 FALSE
     **/
    public static native boolean XTree_SetItemImageEx (int hEle, int nID, String pName, int hImage);
    /**
     列表树_取项文本
     @param hEle 列表树元素句柄
     @param nID 项 ID
     @param iColumn 列索引
     @param dwSize 接收文本的字节数组大小
     @return 返回文本内容的字节数组
     **/
    public static native byte [] XTree_GetItemText (int hEle, int nID, int iColumn, int dwSize);
    /**
     列表树_取项文本扩展
     @param hEle 列表树元素句柄
     @param nID 项 ID
     @param pName 字段名称
     @param dwSize 接收文本的字节数组大小
     @return 返回文本内容的字节数组
     **/
    public static native byte [] XTree_GetItemTextEx (int hEle, int nID, String pName, int dwSize);
    /**
     列表树_取项图片
     @param hEle 列表树元素句柄
     @param nID 项 ID
     @param iColumn 列索引
     @return 返回图片句柄
     **/
    public static native int XTree_GetItemImage (int hEle, int nID, int iColumn);
    /**
     列表树_取项图片扩展
     @param hEle 列表树元素句柄
     @param nID 项 ID
     @param pName 字段名称
     @return 返回图片句柄
     **/
    public static native int XTree_GetItemImageEx (int hEle, int nID, String pName);
    /**
     列表树_删除项
     @param hEle 列表树元素句柄
     @param nID 项 ID
     @return 成功返回 TRUE 否则返回 FALSE
     **/
    public static native boolean XTree_DeleteItem (int hEle, int nID);
    /**
     列表树_删除全部项
     @param hEle 列表树元素句柄
     **/
    public static native void XTree_DeleteItemAll (int hEle);
    /**
     列表树_删除列全部
     @param hEle 列表树元素句柄
     **/
    public static native void XTree_DeleteColumnAll (int hEle);
    /**
     日期_置样式
     @param hEle 日期时间元素句柄
     @param nStyle 样式，0 为日期元素，1 为时间元素
     **/
    public static native void XDateTime_SetStyle (int hEle, int nStyle);
    /**
     日期_取样式
     @param hEle 日期时间元素句柄
     @return 元素样式
     **/
    public static native int XDateTime_GetStyle (int hEle);
    /**
     日期_取内部按钮
     @param hEle 日期时间元素句柄
     @param nType 按钮类型，0: 日历下拉按钮，1: 上箭头按钮，2: 下箭头按钮
     @return 元素样式
     **/
    public static native int XDateTime_GetButton (int hEle, int nType);
    /**
     日期_取选择日期背景颜色
     @param hEle 日期时间元素句柄
     @return 返回颜色值
     **/
    public static native long XDateTime_GetSelBkColor (int hEle);
    /**
     日期_置选择日期背景颜色
     @param hEle 日期时间元素句柄
     @param crSelectBk 文字被选中背景色，颜色值，请使用宏: RGBA ()
     **/
    public static native void XDateTime_SetSelBkColor (int hEle, long crSelectBk);
    /**
     日期_取当前日期
     @param hEle 日期时间元素句柄
     @param pnYear 年.[OUT]
     @param pnMonth 月.[OUT]
     @param pnDay 日.[OUT]
     @param dwSize 接收数据的字节数组大小
     **/
    public static native void XDateTime_GetDate (int hEle, byte [] pnYear, byte [] pnMonth, byte [] pnDay, int dwSize);
    /**
     日期_置当前日期
     @param hEle 日期时间元素句柄
     @param nYear 年
     @param nMonth 月
     @param nDay 日
     **/
    public static native void XDateTime_SetDate (int hEle, int nYear, int nMonth, int nDay);

    /**
     日期时间_取当前时间
     @param hEle 元素句柄
     @param pnHour 时 (输出参数)
     @param pnMinute 分 (输出参数)
     @param pnSecond 秒 (输出参数)
     @param dwSize 数组大小
     **/
    public static native void XDateTime_GetTime (int hEle, byte [] pnHour, byte [] pnMinute, byte [] pnSecond, int dwSize);
    /**
     日期时间_置当前时间
     @param hEle 元素句柄
     @param nHour 时
     @param nMinute 分
     @param nSecond 秒
     **/
    public static native void XDateTime_SetTime (int hEle, int nHour, int nMinute, int nSecond);
    /**
     日期时间_弹出
     @param hEle 元素句柄
     **/
    public static native void XDateTime_Popup (int hEle);
    /**
     月历_取内部按钮
     @param hEle 元素句柄
     @param nType 按钮类型
     @return 返回按钮元素句柄
     **/
    public static native int XMonthCal_GetButton (int hEle, int nType);
    /**
     形状_移除
     @param hShape 形状对象句柄
     **/
    public static native void XShape_RemoveShape (int hShape);
    /**
     形状_取 Z 序
     @param hShape 形状对象句柄
     @return 成功返回索引值，否则返回 XC_ID_ERROR
     **/
    public static native int XShape_GetZOrder (int hShape);
    /**
     形状_重绘
     @param hShape 形状对象句柄
     **/
    public static native void XShape_Redraw (int hShape);
    /**
     形状_取宽度
     @param hShape 形状对象句柄
     @return 返回内容宽度
     **/
    public static native int XShape_GetWidth (int hShape);
    /**
     形状_取高度
     @param hShape 形状对象句柄
     @return 返回内容高度
     **/
    public static native int XShape_GetHeight (int hShape);
    /**
     形状_置位置
     @param hShape 形状对象句柄
     @param x X 坐标
     @param y Y 坐标
     **/
    public static native void XShape_SetPosition (int hShape, int x, int y);
    /**
     形状_取位置
     @param hShape 形状对象句柄
     @param pOutX 返回 X 坐标 (输出参数)
     @param pOutY 返回 Y 坐标 (输出参数)
     **/
    public static native void XShape_GetPosition (int hShape, byte [] pOutX, byte [] pOutY);
    /**
     形状_置大小
     @param hShape 形状对象句柄
     @param nWidth 宽度
     @param nHeight 高度
     **/
    public static native void XShape_SetSize (int hShape, int nWidth, int nHeight);
    /**
     形状_取大小
     @param hShape 形状对象句柄
     @param pOutWidth 返回宽度 (输出参数)
     @param pOutHeight 返回高度 (输出参数)
     **/
    public static native void XShape_GetSize (int hShape, byte [] pOutWidth, byte [] pOutHeight);
    /**
     形状_置透明度
     @param hShape 形状对象句柄
     @param alpha 透明度
     **/
    public static native void XShape_SetAlpha (int hShape, byte alpha);
    /**
     形状_取透明度
     @param hShape 形状对象句柄
     @return 返回透明度
     **/
    public static native byte XShape_GetAlpha (int hShape);
    /**
     形状_取坐标
     @param hShape 形状对象句柄
     @param pRect 接收返回坐标
     **/
    public static native void XShape_GetRect (int hShape, TagRect pRect);
    /**
     形状_置坐标
     @param hShape 形状对象句柄
     @param pRect 坐标
     **/
    public static native void XShape_SetRect (int hShape, TagRect pRect);
    /**
     形状_置逻辑坐标
     @param hShape 形状对象句柄
     @param pRect 逻辑坐标 (包含滚动视图偏移)
     @param bRedraw 是否重绘
     @return 如果成功返回 TRUE，否则返回 FALSE
     **/
    public static native boolean XShape_SetRectLogic (int hShape, TagRect pRect, boolean bRedraw);
    /**
     形状_取逻辑坐标
     @param hShape 形状对象句柄
     @param pRect 逻辑坐标 (包含滚动视图偏移)
     **/
    public static native void XShape_GetRectLogic (int hShape, TagRect pRect);
    /**
     形状_取基于窗口客户区坐标
     @param hShape 形状对象句柄
     @param pRect 基于窗口客户区的坐标
     **/
    public static native void XShape_GetWndClientRect (int hShape, TagRect pRect);

    /**
     获取形状对象的内容大小
     @param hShape 形状对象句柄
     @param pSize 接收返回的内容大小
     **/
    public static native void XShape_GetContentSize (int hShape, TagSize pSize);
    /**
     显示或隐藏形状对象的布局边界
     @param hShape 形状对象句柄
     @param bShow 是否显示布局边界
     **/
    public static native void XShape_ShowLayout (int hShape, boolean bShow);
    /**
     调整形状对象的布局
     @param hShape 形状对象句柄
     **/
    public static native void XShape_AdjustLayout (int hShape);
    /**
     销毁形状对象
     @param hShape 形状对象句柄
     **/
    public static native void XShape_Destroy (int hShape);
    /**
     创建形状对象文本
     @param x 元素 x 坐标
     @param y 元素 y 坐标
     @param cx 宽度
     @param cy 高度
     @param pName 文本内容
     @param hParent 父对象句柄
     @return 返回文本块句柄
     **/
    public static native int XShapeText_Create (int x, int y, int cx, int cy, String pName, int hParent);
    /**
     设置形状对象文本的内容
     @param hTextBlock 形状对象文本句柄
     @param pName 文本内容
     **/
    public static native void XShapeText_SetText (int hTextBlock, String pName);
    /**
     获取形状对象文本的内容
     @param hTextBlock 形状对象文本句柄
     @param dwSize 缓冲区大小，字节为单位
     @return 返回文本内容的字节数组
     **/
    public static native byte [] XShapeText_GetText (int hTextBlock, int dwSize);
    /**
     获取形状对象文本的长度
     @param hTextBlock 形状对象文本句柄
     @return 返回文本长度
     **/
    public static native int XShapeText_GetTextLength (int hTextBlock);
    /**
     设置形状对象文本的字体
     @param hTextBlock 形状对象文本句柄
     @param hFontx 炫彩字体句柄
     **/
    public static native void XShapeText_SetFont (int hTextBlock, int hFontx);
    /**
     获取形状对象文本的字体
     @param hTextBlock 形状对象文本句柄
     @return 返回炫彩字体句柄
     **/
    public static native int XShapeText_GetFont (int hTextBlock);
    /**
     设置形状对象文本的颜色
     @param hTextBlock 形状对象文本句柄
     @param color 颜色值，使用宏 RGBA () 生成
     **/
    public static native void XShapeText_SetTextColor (int hTextBlock, long color);
    /**
     获取形状对象文本的颜色
     @param hTextBlock 形状对象文本句柄
     @return 返回颜色值
     **/
    public static native long XShapeText_GetTextColor (int hTextBlock);
    /**
     设置形状对象文本的对齐方式
     @param hTextBlock 形状对象文本句柄
     @param align 文本对齐标识
     **/
    public static native void XShapeText_SetTextAlign (int hTextBlock, int align);
    /**
     设置形状对象文本的内容偏移
     @param hTextBlock 形状对象文本句柄
     @param x X 轴偏移量
     @param y Y 轴偏移量
     **/
    public static native void XShapeText_SetOffset (int hTextBlock, int x, int y);
    /**
     创建形状对象图片
     @param x 元素 x 坐标
     @param y 元素 y 坐标
     @param cx 宽度
     @param cy 高度
     @param hParent 父对象句柄
     @return 返回图片对象句柄
     **/
    public static native int XShapePic_Create (int x, int y, int cx, int cy, int hParent);
    /**
     设置形状对象图片
     @param hShape 形状对象句柄
     @param hImage 图片句柄
     **/
    public static native void XShapePic_SetImage (int hShape, int hImage);
    /**
     获取形状对象图片句柄
     @param hShape 形状对象句柄
     @return 返回图片句柄
     **/
    public static native int XShapePic_GetImage (int hShape);
    /**
     创建形状对象 GIF
     @param x 元素 x 坐标
     @param y 元素 y 坐标
     @param cx 宽度
     @param cy 高度
     @param hParent 父对象句柄
     @return 返回形状对象 GIF 句柄
     **/
    public static native int XShapeGif_Create (int x, int y, int cx, int cy, int hParent);
    /**
     设置形状对象 GIF 图片
     @param hShape 形状对象句柄
     @param hImage 图片句柄
     **/
    public static native void XShapeGif_SetImage (int hShape, int hImage);
    /**
     获取形状对象 GIF 图片句柄
     @param hShape 形状对象句柄
     @return 返回图片句柄
     **/
    public static native int XShapeGif_GetImage (int hShape);

    /**
     创建矩形形状对象
     @param x X 坐标
     @param y Y 坐标
     @param cx 宽度
     @param cy 高度
     @param hParent 父对象句柄
     @return 返回矩形形状对象句柄
     */
    public static native int XShapeRect_Create (int x, int y, int cx, int cy, int hParent);
    /**
     设置矩形形状对象的边框颜色
     @param hShape 矩形形状对象句柄
     @param color 边框颜色值，使用 RGBA 宏生成
     */
    public static native void XShapeRect_SetBorderColor (int hShape, long color);
    /**
     设置矩形形状对象的填充颜色
     @param hShape 矩形形状对象句柄
     @param color 填充颜色值，使用 RGBA 宏生成
     */
    public static native void XShapeRect_SetFillColor (int hShape, long color);
    /**
     设置矩形形状对象的圆角大小
     @param hShape 矩形形状对象句柄
     @param nWidth 圆角宽度
     @param nHeight 圆角高度
     */
    public static native void XShapeRect_SetRoundAngle (int hShape, int nWidth, int nHeight);
    /**
     获取矩形形状对象的圆角大小
     @param hShape 矩形形状对象句柄
     @param pWidth 接收圆角宽度的缓冲区
     @param pHeight 接收圆角高度的缓冲区
     */
    public static native void XShapeRect_GetRoundAngle (int hShape, byte [] pWidth, byte [] pHeight);
    /**
     启用或禁用绘制矩形形状对象的边框
     @param hShape 矩形形状对象句柄
     @param bEnable 是否启用，true 为启用，false 为禁用
     */
    public static native void XShapeRect_EnableBorder (int hShape, boolean bEnable);
    /**
     启用或禁用填充矩形形状对象
     @param hShape 矩形形状对象句柄
     @param bEnable 是否启用，true 为启用，false 为禁用
     */
    public static native void XShapeRect_EnableFill (int hShape, boolean bEnable);
    /**
     启用或禁用矩形形状对象的圆角
     @param hShape 矩形形状对象句柄
     @param bEnable 是否启用，true 为启用，false 为禁用
     */
    public static native void XShapeRect_EnableRoundAngle (int hShape, boolean bEnable);
    /**
     创建椭圆形状对象
     @param x X 坐标
     @param y Y 坐标
     @param cx 宽度
     @param cy 高度
     @param hParent 父对象句柄
     @return 返回椭圆形状对象句柄
     */
    public static native int XShapeEllipse_Create (int x, int y, int cx, int cy, int hParent);
    /**
     设置椭圆形状对象的边框颜色
     @param hShape 椭圆形状对象句柄
     @param color 边框颜色值，使用 RGBA 宏生成
     */
    public static native void XShapeEllipse_SetBorderColor (int hShape, long color);
    /**
     设置椭圆形状对象的填充颜色
     @param hShape 椭圆形状对象句柄
     @param color 填充颜色值，使用 RGBA 宏生成
     */
    public static native void XShapeEllipse_SetFillColor (int hShape, long color);
    /**
     启用或禁用绘制椭圆形状对象的边框
     @param hShape 椭圆形状对象句柄
     @param bEnable 是否启用，true 为启用，false 为禁用
     */
    public static native void XShapeEllipse_EnableBorder (int hShape, boolean bEnable);
    /**
     启用或禁用填充椭圆形状对象
     @param hShape 椭圆形状对象句柄
     @param bEnable 是否启用，true 为启用，false 为禁用
     */
    public static native void XShapeEllipse_EnableFill (int hShape, boolean bEnable);
    /**
     创建组框形状对象
     @param x X 坐标
     @param y Y 坐标
     @param cx 宽度
     @param cy 高度
     @param pName 组框名称文本
     @param hParent 父对象句柄
     @return 返回组框形状对象句柄
     */
    public static native int XShapeGroupBox_Create (int x, int y, int cx, int cy, String pName, int hParent);
    /**
     设置组框形状对象的边框颜色
     @param hShape 组框形状对象句柄
     @param color 边框颜色值，使用 RGBA 宏生成
     */
    public static native void XShapeGroupBox_SetBorderColor (int hShape, long color);
    /**
     设置组框形状对象的文本颜色
     @param hShape 组框形状对象句柄
     @param color 文本颜色值，使用 RGBA 宏生成
     */
    public static native void XShapeGroupBox_SetTextColor (int hShape, long color);
    /**
     设置组框形状对象的字体
     @param hShape 组框形状对象句柄
     @param hFontX 炫彩字体句柄
     */
    public static native void XShapeGroupBox_SetFontX (int hShape, int hFontX);
    /**
     设置组框形状对象的文本偏移量
     @param hShape 组框形状对象句柄
     @param offsetX 水平偏移
     @param offsetY 垂直偏移
     */
    public static native void XShapeGroupBox_SetTextOffset (int hShape, int offsetX, int offsetY);
    /**
     设置组框形状对象的圆角大小
     @param hShape 组框形状对象句柄
     @param nWidth 圆角宽度
     @param nHeight 圆角高度
     */
    public static native void XShapeGroupBox_SetRoundAngle (int hShape, int nWidth, int nHeight);

    /**
     形状组框_置文本
     @param hShape 形状对象句柄
     @param pText 文本内容
     **/
    public static native void XShapeGroupBox_SetText (int hShape, String pText);
    /**
     形状组框_取文本
     @param hShape 形状对象句柄
     @param dwSize 接收文本内存块长度（字符为单位）
     @return 返回文本内容的字节数组
     **/
    public static native byte [] XShapeGroupBox_GetText (int hShape, int dwSize);
    /**
     形状组框_取文本偏移
     @param hShape 形状对象句柄
     @param pOffsetX 接收 X 坐标偏移量的字节数组
     @param pOffsetY 接收 Y 坐标偏移量的字节数组
     **/
    public static native void XShapeGroupBox_GetTextOffset (int hShape, byte [] pOffsetX, byte [] pOffsetY);
    /**
     形状组框_取圆角大小
     @param hShape 形状对象句柄
     @param pWidth 接收圆角宽度的字节数组
     @param pHeight 接收圆角高度的字节数组
     **/
    public static native void XShapeGroupBox_GetRoundAngle (int hShape, byte [] pWidth, byte [] pHeight);
    /**
     形状组框_启用圆角
     @param hShape 形状对象句柄
     @param bEnable 是否启用
     **/
    public static native void XShapeGroupBox_EnableRoundAngle (int hShape, boolean bEnable);
    /**
     表格_创建
     @param x 元素 x 坐标
     @param y 元素 y 坐标
     @param cx 宽度
     @param cy 高度
     @param hParent 父为窗口句柄或元素句柄
     @return 返回表格句柄
     **/
    public static native int XTable_Create (int x, int y, int cx, int cy, int hParent);
    /**
     表格_重置
     @param hShape 形状对象句柄
     @param nRow 行数量
     @param nCol 列数量
     **/
    public static native void XTable_Reset (int hShape, int nRow, int nCol);
    /**
     表格_清空
     @param hShape 形状对象句柄
     **/
    public static native void XTable_Clear (int hShape);
    /**
     表格_组合行
     @param hShape 形状对象句柄
     @param iRow 行索引
     @param iCol 列索引
     @param count 数量
     **/
    public static native void XTable_ComboRow (int hShape, int iRow, int iCol, int count);
    /**
     表格_组合列
     @param hShape 形状对象句柄
     @param iRow 行索引
     @param iCol 列索引
     @param count 数量
     **/
    public static native void XTable_ComboCol (int hShape, int iRow, int iCol, int count);
    /**
     表格_置列宽
     @param hShape 形状对象句柄
     @param iCol 列索引
     @param width 宽度
     **/
    public static native void XTable_SetColWidth (int hShape, int iCol, int width);
    /**
     表格_置行高
     @param hShape 形状对象句柄
     @param iRow 行索引
     @param height 高度
     **/
    public static native void XTable_SetRowHeight (int hShape, int iRow, int height);
    /**
     表格_置边颜色
     @param hShape 形状对象句柄
     @param color 颜色
     **/
    public static native void XTable_SetBorderColor (int hShape, long color);
    /**
     表格_置文本颜色
     @param hShape 形状对象句柄
     @param color 颜色
     **/
    public static native void XTable_SetTextColor (int hShape, long color);
    /**
     表格_置字体
     @param hShape 形状对象句柄
     @param hFont 字体句柄
     **/
    public static native void XTable_SetFont (int hShape, int hFont);
    /**
     表格_置项内填充
     @param hShape 形状对象句柄
     @param leftSize 内填充大小
     @param topSize 内填充大小
     @param rightSize 内填充大小
     @param bottomSize 内填充大小
     **/
    public static native void XTable_SetItemPadding (int hShape, int leftSize, int topSize, int rightSize, int bottomSize);
    /**
     表格_置项文本
     @param hShape 形状对象句柄
     @param iRow 行索引
     @param iCol 列索引
     @param pText 文本
     **/
    public static native void XTable_SetItemText (int hShape, int iRow, int iCol, String pText);
    /**
     表格_置项文本扩展
     @param hShape 形状对象句柄
     @param iRow 行索引
     @param iCol 列索引
     @param pText 文本
     @param textColor 文本颜色
     @param bkColor 背景颜色
     @param bTextColor 是否使用文本颜色
     @param bBkColor 是否使用背景颜色
     @param hFont 字体
     **/
    public static native void XTable_SetItemTextEx (int hShape, int iRow, int iCol, String pText, long textColor, long bkColor, boolean bTextColor, boolean bBkColor, int hFont);
    /**
     表格_置项字体
     @param hShape 形状对象句柄
     @param iRow 行索引
     @param iCol 列索引
     @param hFont 字体句柄
     **/
    public static native void XTable_SetItemFont (int hShape, int iRow, int iCol, int hFont);
    /**
     表格_置项文本对齐
     @param hShape 形状对象句柄
     @param iRow 行索引
     @param iCol 列索引
     @param nAlign 对齐方式
     **/
    public static native void XTable_SetItemTextAlign (int hShape, int iRow, int iCol, int nAlign);
    /**
     表格_置项文本色
     @param hShape 形状对象句柄
     @param iRow 行索引
     @param iCol 列索引
     @param color 颜色
     @param bColor 是否使用
     **/
    public static native void XTable_SetItemTextColor (int hShape, int iRow, int iCol, long color, boolean bColor);
    /**
     表格_置项背景色
     @param hShape 形状对象句柄
     @param iRow 行索引
     @param iCol 列索引
     @param color 颜色
     @param bColor 是否使用
     **/
    public static native void XTable_SetItemBkColor (int hShape, int iRow, int iCol, long color, boolean bColor);

    /**
     表格_置项线
     @param hShape 形状对象句柄
     @param iRow1 行索引 1
     @param iCol1 列索引 1
     @param iRow2 行索引 2
     @param iCol2 列索引 2
     @param nFlag 标识
     @param color 颜色值，请使用宏: RGBA ()
     */
    public static native void XTable_SetItemLine (int hShape, int iRow1, int iCol1, int iRow2, int iCol2, int nFlag, long color);
    /**
     表格_置项标识
     @param hShape 形状对象句柄
     @param iRow 行索引
     @param iCol 列索引
     @param flag 标识 @ref table_flag_
     */
    public static native void XTable_SetItemFlag (int hShape, int iRow, int iCol, int flag);
    /**
     表格_取项文本
     @param hShape 形状对象句柄
     @param iRow 行索引
     @param iCol 列索引
     @param dwSize 缓冲区大小
     @return 返回字符串指针
     */
    public static native byte [] XTable_GetItemText (int hShape, int iRow, int iCol, int dwSize);
    /**
     表格_取项坐标
     @param hShape 形状对象句柄
     @param iRow 行索引
     @param iCol 列索引
     @param pRect 接收返回坐标
     @return 成功返回 TRUE, 否则返回 FALSE
     */
    public static native boolean XTable_GetItemRect (int hShape, int iRow, int iCol, TagRect pRect);
    /**
     数据适配器_增加引用计数 ()
     @param hAdapter 数据适配器句柄
     @return 返回当前引用计数
     */
    public static native int XAd_AddRef (int hAdapter);
    /**
     数据适配器_释放引用计数 ()
     @param hAdapter 数据适配器句柄
     @return 返回当前引用计数
     */
    public static native int XAd_Release (int hAdapter);
    /**
     数据适配器_取引用计数 ()
     @param hAdapter 数据适配器句柄
     @return 返回当前引用计数
     */
    public static native int XAd_GetRefCount (int hAdapter);
    /**
     数据适配器_销毁 ()
     @param hAdapter 数据适配器句柄
     */
    public static native void XAd_Destroy (int hAdapter);
    /**
     数据适配器_启用自动销毁 ()
     @param hAdapter 数据适配器句柄
     @param bEnable 是否启用
     */
    public static native void XAd_EnableAutoDestroy (int hAdapter, boolean bEnable);
    /**
     数据适配器列表视_创建 ()
     @return 返回数据适配器句柄
     */
    public static native int XAdListView_Create ();
    /**
     数据适配器列表视_组添加列 ()
     @param hAdapter 数据适配器句柄
     @param pName 字段名称
     @return 返回列索引
     */
    public static native int XAdListView_Group_AddColumn (int hAdapter, String pName);
    /**
     数据适配器列表视_添加组文本 ()
     @param hAdapter 数据适配器句柄
     @param pValue 值
     @param iPos 插入位置索引，-1 添加到末尾
     @return 返回组索引
     */
    public static native int XAdListView_Group_AddItemText (int hAdapter, String pValue, int iPos);
    /**
     数据适配器列表视_添加组文本扩展 ()
     @param hAdapter 数据适配器句柄
     @param pName 字段名称
     @param pValue 值
     @param iPos 插入位置，-1 添加到末尾
     @return 返回组索引
     */
    public static native int XAdListView_Group_AddItemTextEx (int hAdapter, String pName, String pValue, int iPos);
    /**
     数据适配器列表视_添加组图片 ()
     @param hAdapter 数据适配器句柄
     @param hImage 图片句柄
     @param iPos 插入位置，-1 添加到末尾
     @return 返回组索引
     */
    public static native int XAdListView_Group_AddItemImage (int hAdapter, int hImage, int iPos);
    /**
     数据适配器列表视_添加组图片扩展 ()
     @param hAdapter 数据适配器句柄
     @param pName 字段名称
     @param hImage 图片句柄
     @param iPos 插入位置，-1 添加到末尾
     @return 返回组索引
     */
    public static native int XAdListView_Group_AddItemImageEx (int hAdapter, String pName, int hImage, int iPos);
    /**
     数据适配器列表视_组设置文本 ()
     @param hAdapter 数据适配器句柄
     @param iGroup 组索引
     @param iColumn 列索引
     @param pValue 值
     @return 成功返回 TRUE 否则返回 FALSE
     */
    public static native boolean XAdListView_Group_SetText (int hAdapter, int iGroup, int iColumn, String pValue);
    /**
     数据适配器列表视_组设置文本扩展 ()
     @param hAdapter 数据适配器句柄
     @param iGroup 组索引
     @param pName 字段名
     @param pValue 值
     @return 成功返回 TRUE 否则返回 FALSE
     */
    public static native boolean XAdListView_Group_SetTextEx (int hAdapter, int iGroup, String pName, String pValue);
    /**
     数据适配器列表视_组设置图片 ()
     @param hAdapter 数据适配器句柄
     @param iGroup 组索引
     @param iColumn 列索引
     @param hImage 图片句柄
     @return 成功返回 TRUE 否则返回 FALSE
     */
    public static native boolean XAdListView_Group_SetImage (int hAdapter, int iGroup, int iColumn, int hImage);
    /**
     数据适配器列表视_组设置图片扩展 ()
     @param hAdapter 数据适配器句柄
     @param iGroup 组索引
     @param pName 字段名
     @param hImage 图片句柄
     @return 成功返回 TRUE 否则返回 FALSE
     */
    public static native boolean XAdListView_Group_SetImageEx (int hAdapter, int iGroup, String pName, int hImage);

    /**
     数据适配器列表视_组获取文本
     @param hAdapter 数据适配器句柄
     @param iGroup 组索引
     @param iColumn 列索引
     @param dwSize 输出缓冲区大小
     @return 返回文本内容的字节数组
     */
    public static native byte [] XAdListView_Group_GetText (int hAdapter, int iGroup, int iColumn, int dwSize);
    /**
     数据适配器列表视_组根据字段名获取文本
     @param hAdapter 数据适配器句柄
     @param iGroup 组索引
     @param pName 字段名称
     @param dwSize 输出缓冲区大小
     @return 返回文本内容的字节数组
     */
    public static native byte [] XAdListView_Group_GetTextEx (int hAdapter, int iGroup, String pName, int dwSize);
    /**
     数据适配器列表视_组获取图片句柄
     @param hAdapter 数据适配器句柄
     @param iGroup 组索引
     @param iColumn 列索引
     @return 返回图片句柄
     */
    public static native int XAdListView_Group_GetImage (int hAdapter, int iGroup, int iColumn);
    /**
     数据适配器列表视_组根据字段名获取图片句柄
     @param hAdapter 数据适配器句柄
     @param iGroup 组索引
     @param pName 字段名称
     @return 返回图片句柄
     */
    public static native int XAdListView_Group_GetImageEx (int hAdapter, int iGroup, String pName);
    /**
     数据适配器列表视_项添加列
     @param hAdapter 数据适配器句柄
     @param pName 字段名称
     @return 返回列索引
     */
    public static native int XAdListView_Item_AddColumn (int hAdapter, String pName);
    /**
     数据适配器列表视_获取组数量
     @param hAdapter 数据适配器句柄
     @return 返回组数量
     */
    public static native int XAdListView_Group_GetCount (int hAdapter);
    /**
     数据适配器列表视_获取指定组中的项数量
     @param hAdapter 数据适配器句柄
     @param iGroup 组索引
     @return 返回指定组中的项数量，失败返回 XC_ID_ERROR
     */
    public static native int XAdListView_Item_GetCount (int hAdapter, int iGroup);
    /**
     数据适配器列表视_项添加文本到第一列
     @param hAdapter 数据适配器句柄
     @param iGroup 组索引
     @param pValue 文本值
     @param iPos 插入位置，-1 添加到末尾
     @return 返回项索引
     */
    public static native int XAdListView_Item_AddItemText (int hAdapter, int iGroup, String pValue, int iPos);
    /**
     数据适配器列表视_项根据字段名添加文本
     @param hAdapter 数据适配器句柄
     @param iGroup 组索引
     @param pName 字段名称
     @param pValue 文本值
     @param iPos 插入位置，-1 添加到末尾
     @return 返回项索引
     */
    public static native int XAdListView_Item_AddItemTextEx (int hAdapter, int iGroup, String pName, String pValue, int iPos);
    /**
     数据适配器列表视_项添加图片到第一列
     @param hAdapter 数据适配器句柄
     @param iGroup 组索引
     @param hImage 图片句柄
     @param iPos 插入位置，-1 添加到末尾
     @return 返回项索引
     */
    public static native int XAdListView_Item_AddItemImage (int hAdapter, int iGroup, int hImage, int iPos);
    /**
     数据适配器列表视_项根据字段名添加图片
     @param hAdapter 数据适配器句柄
     @param iGroup 组索引
     @param pName 字段名称
     @param hImage 图片句柄
     @param iPos 插入位置，-1 添加到末尾
     @return 返回项索引
     */
    public static native int XAdListView_Item_AddItemImageEx (int hAdapter, int iGroup, String pName, int hImage, int iPos);
    /**
     数据适配器列表视_组删除
     @param hAdapter 数据适配器句柄
     @param iGroup 组索引
     @return 成功返回 TRUE，否则返回 FALSE
     */
    public static native boolean XAdListView_Group_DeleteItem (int hAdapter, int iGroup);
    /**
     数据适配器列表视_组删除全部子项
     @param hAdapter 数据适配器句柄
     @param iGroup 组索引
     */
    public static native void XAdListView_Group_DeleteAllChildItem (int hAdapter, int iGroup);
    /**
     数据适配器列表视_项删除
     @param hAdapter 数据适配器句柄
     @param iGroup 组索引
     @param iItem 项索引
     @return 成功返回 TRUE，否则返回 FALSE
     */
    public static native boolean XAdListView_Item_DeleteItem (int hAdapter, int iGroup, int iItem);
    /**
     数据适配器列表视_删除全部（组、项、列）
     @param hAdapter 数据适配器句柄
     */
    public static native void XAdListView_DeleteAll (int hAdapter);
    /**
     数据适配器列表视_删除全部组
     @param hAdapter 数据适配器句柄
     */
    public static native void XAdListView_DeleteAllGroup (int hAdapter);
    /**
     数据适配器列表视_删除全部项
     @param hAdapter 数据适配器句柄
     */
    public static native void XAdListView_DeleteAllItem (int hAdapter);
    /**
     数据适配器列表视_组删除列
     @param hAdapter 数据适配器句柄
     @param iColumn 列索引
     */
    public static native void XAdListView_DeleteColumnGroup (int hAdapter, int iColumn);
    /**
     数据适配器列表视_项删除列
     @param hAdapter 数据适配器句柄
     @param iColumn 列索引
     */
    public static native void XAdListView_DeleteColumnItem (int hAdapter, int iColumn);

    /**
     获取列表视项的文本内容
     @param hAdapter 列表视数据适配器句柄
     @param iGroup 组索引
     @param iItem 项索引
     @param iColumn 列索引
     @param dwSize 缓冲区大小
     @return 返回文本内容的字节数组
     **/
    public static native byte [] XAdListView_Item_GetText (int hAdapter, int iGroup, int iItem, int iColumn, int dwSize);
    /**
     通过字段名获取列表视项的文本内容
     @param hAdapter 列表视数据适配器句柄
     @param iGroup 组索引
     @param iItem 项索引
     @param pName 字段名
     @param dwSize 缓冲区大小
     @return 返回文本内容的字节数组
     **/
    public static native byte [] XAdListView_Item_GetTextEx (int hAdapter, int iGroup, int iItem, String pName, int dwSize);
    /**
     获取列表视项的图片句柄
     @param hAdapter 列表视数据适配器句柄
     @param iGroup 组索引
     @param iItem 项索引
     @param iColumn 列索引
     @return 返回图片句柄
     **/
    public static native int XAdListView_Item_GetImage (int hAdapter, int iGroup, int iItem, int iColumn);
    /**
     通过字段名获取列表视项的图片句柄
     @param hAdapter 列表视数据适配器句柄
     @param iGroup 组索引
     @param iItem 项索引
     @param pName 字段名
     @return 返回图片句柄
     **/
    public static native int XAdListView_Item_GetImageEx (int hAdapter, int iGroup, int iItem, String pName);
    /**
     设置列表视项的文本内容
     @param hAdapter 列表视数据适配器句柄
     @param iGroup 组索引
     @param iItem 项索引
     @param iColumn 列索引
     @param pValue 文本内容
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XAdListView_Item_SetText (int hAdapter, int iGroup, int iItem, int iColumn, String pValue);
    /**
     通过字段名设置列表视项的文本内容
     @param hAdapter 列表视数据适配器句柄
     @param iGroup 组索引
     @param iItem 项索引
     @param pName 字段名
     @param pValue 文本内容
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XAdListView_Item_SetTextEx (int hAdapter, int iGroup, int iItem, String pName, String pValue);
    /**
     设置列表视项的图片
     @param hAdapter 列表视数据适配器句柄
     @param iGroup 组索引
     @param iItem 项索引
     @param iColumn 列索引
     @param hImage 图片句柄
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XAdListView_Item_SetImage (int hAdapter, int iGroup, int iItem, int iColumn, int hImage);
    /**
     通过字段名设置列表视项的图片
     @param hAdapter 列表视数据适配器句柄
     @param iGroup 组索引
     @param iItem 项索引
     @param pName 字段名
     @param hImage 图片句柄
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XAdListView_Item_SetImageEx (int hAdapter, int iGroup, int iItem, String pName, int hImage);
    /**
     创建表格数据适配器
     @return 返回表格数据适配器句柄
     **/
    public static native int XAdTable_Create ();
    /**
     对表格数据进行排序
     @param hAdapter 表格数据适配器句柄
     @param iColumn 要排序的列索引
     @param bAscending 是否升序排序
     **/
    public static native void XAdTable_Sort (int hAdapter, int iColumn, boolean bAscending);
    /**
     获取表格项的数据类型
     @param hAdapter 表格数据适配器句柄
     @param iRow 行索引
     @param iColumn 列索引
     @return 返回数据类型 @ref adapter_date_type_
     **/
    public static native int XAdTable_GetItemDataType (int hAdapter, int iRow, int iColumn);
    /**
     通过字段名获取表格项的数据类型
     @param hAdapter 表格数据适配器句柄
     @param iRow 行索引
     @param pName 字段名
     @return 返回数据类型 @ref adapter_date_type_
     **/
    public static native int XAdTable_GetItemDataTypeEx (int hAdapter, int iRow, String pName);
    /**
     添加表格列
     @param hAdapter 表格数据适配器句柄
     @param pName 字段名
     @return 返回列索引，失败返回 - 1
     **/
    public static native int XAdTable_AddColumn (int hAdapter, String pName);
    /**
     设置表格列
     @param hAdapter 表格数据适配器句柄
     @param pColName 列名，多个列名用逗号分开
     @return 返回列数量
     **/
    public static native int XAdTable_SetColumn (int hAdapter, String pColName);
    /**
     添加表格行文本，默认填充到第一列
     @param hAdapter 表格数据适配器句柄
     @param pValue 文本内容
     @return 返回行索引值
     **/
    public static native int XAdTable_AddRowText (int hAdapter, String pValue);
    /**
     通过字段名添加表格行文本
     @param hAdapter 表格数据适配器句柄
     @param pName 字段名
     @param pValue 文本内容
     @return 返回行索引
     **/
    public static native int XAdTable_AddRowTextEx (int hAdapter, String pName, String pValue);
    /**
     添加表格行图片，默认填充到第一列
     @param hAdapter 表格数据适配器句柄
     @param hImage 图片句柄
     @return 返回行索引值
     **/
    public static native int XAdTable_AddRowImage (int hAdapter, int hImage);
    /**
     通过字段名添加表格行图片
     @param hAdapter 表格数据适配器句柄
     @param pName 字段名
     @param hImage 图片句柄
     @return 成功返回行索引，否则返回 - 1
     **/
    public static native int XAdTable_AddRowImageEx (int hAdapter, String pName, int hImage);
    /**
     插入表格行文本，填充到第一列
     @param hAdapter 表格数据适配器句柄
     @param iRow 插入位置行索引
     @param pValue 文本内容
     @return 返回行索引
     **/
    public static native int XAdTable_InsertRowText (int hAdapter, int iRow, String pValue);
    /**
     通过字段名插入表格行文本
     @param hAdapter 表格数据适配器句柄
     @param iRow 插入位置行索引
     @param pName 字段名
     @param pValue 文本内容
     @return 返回行索引
     **/
    public static native int XAdTable_InsertRowTextEx (int hAdapter, int iRow, String pName, String pValue);
    /**
     插入表格行图片，填充到第一列
     @param hAdapter 表格数据适配器句柄
     @param iRow 插入位置行索引
     @param hImage 图片句柄
     @return 返回行索引
     **/
    public static native int XAdTable_InsertRowImage (int hAdapter, int iRow, int hImage);
    /**
     通过字段名插入表格行图片
     @param hAdapter 表格数据适配器句柄
     @param iRow 插入位置行索引
     @param pName 字段名
     @param hImage 图片句柄
     @return 返回行索引
     **/
    public static native int XAdTable_InsertRowImageEx (int hAdapter, int iRow, String pName, int hImage);
    /**
     设置数据适配器表项文本
     @param hAdapter 数据适配器句柄
     @param iRow 行索引
     @param iColumn 列索引
     @param pValue 文本值
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XAdTable_SetItemText (int hAdapter, int iRow, int iColumn, String pValue);
    /**
     通过字段名设置数据适配器表项文本
     @param hAdapter 数据适配器句柄
     @param iRow 行索引
     @param pName 字段名
     @param pValue 文本值
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XAdTable_SetItemTextEx (int hAdapter, int iRow, String pName, String pValue);
    /**
     设置数据适配器表项整数值
     @param hAdapter 数据适配器句柄
     @param iRow 行索引
     @param iColumn 列索引
     @param nValue 整数值
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XAdTable_SetItemInt (int hAdapter, int iRow, int iColumn, int nValue);
    /**
     通过字段名设置数据适配器表项整数值
     @param hAdapter 数据适配器句柄
     @param iRow 行索引
     @param pName 字段名
     @param nValue 整数值
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XAdTable_SetItemIntEx (int hAdapter, int iRow, String pName, int nValue);
    /**
     设置数据适配器表项浮点值
     @param hAdapter 数据适配器句柄
     @param iRow 行索引
     @param iColumn 列索引
     @param nValue 浮点值
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XAdTable_SetItemFloat (int hAdapter, int iRow, int iColumn, float nValue);
    /**
     通过字段名设置数据适配器表项浮点值
     @param hAdapter 数据适配器句柄
     @param iRow 行索引
     @param pName 字段名
     @param nValue 浮点值
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XAdTable_SetItemFloatEx (int hAdapter, int iRow, String pName, float nValue);
    /**
     设置数据适配器表项图片
     @param hAdapter 数据适配器句柄
     @param iRow 行索引
     @param iColumn 列索引
     @param hImage 图片句柄
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XAdTable_SetItemImage (int hAdapter, int iRow, int iColumn, int hImage);
    /**
     通过字段名设置数据适配器表项图片
     @param hAdapter 数据适配器句柄
     @param iRow 行索引
     @param pName 字段名
     @param hImage 图片句柄
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XAdTable_SetItemImageEx (int hAdapter, int iRow, String pName, int hImage);
    /**
     删除数据适配器表指定行
     @param hAdapter 数据适配器句柄
     @param iRow 行索引
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XAdTable_DeleteRow (int hAdapter, int iRow);
    /**
     从指定行开始删除多行数据
     @param hAdapter 数据适配器句柄
     @param iRow 起始行索引
     @param nCount 删除行数
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XAdTable_DeleteRowEx (int hAdapter, int iRow, int nCount);
    /**
     删除数据适配器表所有行
     @param hAdapter 数据适配器句柄
     **/
    public static native void XAdTable_DeleteRowAll (int hAdapter);
    /**
     删除数据适配器表所有列
     @param hAdapter 数据适配器句柄
     **/
    public static native void XAdTable_DeleteColumnAll (int hAdapter);
    /**
     获取数据适配器表行数量
     @param hAdapter 数据适配器句柄
     @return 返回行数量
     **/
    public static native int XAdTable_GetCountRow (int hAdapter);
    /**
     获取数据适配器表列数量
     @param hAdapter 数据适配器句柄
     @return 返回列数量
     **/
    public static native int XAdTable_GetCountColumn (int hAdapter);
    /**
     获取数据适配器表项文本内容
     @param hAdapter 数据适配器句柄
     @param iRow 行索引
     @param iColumn 列索引
     @param dwSize 缓冲区大小
     @return 返回文本内容的字节数组
     **/
    public static native byte [] XAdTable_GetItemText (int hAdapter, int iRow, int iColumn, int dwSize);
    /**
     通过字段名获取数据适配器表项文本内容
     @param hAdapter 数据适配器句柄
     @param iRow 行索引
     @param pName 字段名
     @param dwSize 缓冲区大小
     @return 返回文本内容的字节数组
     **/
    public static native byte [] XAdTable_GetItemTextEx (int hAdapter, int iRow, String pName, int dwSize);
    /**
     获取数据适配器表项图片句柄
     @param hAdapter 数据适配器句柄
     @param iRow 行索引
     @param iColumn 列索引
     @return 返回图片句柄
     **/
    public static native int XAdTable_GetItemImage (int hAdapter, int iRow, int iColumn);
    /**
     通过字段名获取数据适配器表项图片句柄
     @param hAdapter 数据适配器句柄
     @param iRow 行索引
     @param pName 字段名
     @return 返回图片句柄
     **/
    public static native int XAdTable_GetItemImageEx (int hAdapter, int iRow, String pName);
    /**
     数据适配器表_取项整数值
     @param hAdapter 数据适配器句柄
     @param iRow 行索引
     @param iColumn 列索引
     @param pOutValue 接收返回整数值的字节数组
     @return 成功返回 TRUE，否则返回 FALSE
     **/
    public static native boolean XAdTable_GetItemInt (int hAdapter, int iRow, int iColumn, byte [] pOutValue);
    /**
     数据适配器表_取项整数值扩展（根据字段名）
     @param hAdapter 数据适配器句柄
     @param iRow 行索引
     @param pName 字段名
     @param pOutValue 接收返回整数值的字节数组
     @return 成功返回 TRUE，否则返回 FALSE
     **/
    public static native boolean XAdTable_GetItemIntEx (int hAdapter, int iRow, String pName, byte [] pOutValue);
    /**
     数据适配器表_取项浮点值
     @param hAdapter 数据适配器句柄
     @param iRow 行索引
     @param iColumn 列索引
     @param pOutValue 接收返回浮点值的字节数组
     @return 成功返回 TRUE，否则返回 FALSE
     **/
    public static native boolean XAdTable_GetItemFloat (int hAdapter, int iRow, int iColumn, byte [] pOutValue);
    /**
     数据适配器表_取项浮点值扩展（根据字段名）
     @param hAdapter 数据适配器句柄
     @param iRow 行索引
     @param pName 字段名
     @param pOutValue 接收返回浮点值的字节数组
     @return 成功返回 TRUE，否则返回 FALSE
     **/
    public static native boolean XAdTable_GetItemFloatEx (int hAdapter, int iRow, String pName, byte [] pOutValue);
    /**
     数据适配器 MAP_创建
     @return 返回数据适配器 MAP 句柄
     **/
    public static native int XAdMap_Create ();
    /**
     数据适配器 MAP_添加项文本
     @param hAdapter 数据适配器 MAP 句柄
     @param pName 字段名
     @param pValue 文本值
     @return 成功返回 TRUE，否则返回 FALSE
     **/
    public static native boolean XAdMap_AddItemText (int hAdapter, String pName, String pValue);
    /**
     数据适配器 MAP_添加项图片
     @param hAdapter 数据适配器 MAP 句柄
     @param pName 字段名
     @param hImage 图片句柄
     @return 成功返回 TRUE，否则返回 FALSE
     **/
    public static native boolean XAdMap_AddItemImage (int hAdapter, String pName, int hImage);
    /**
     数据适配器 MAP_删除项
     @param hAdapter 数据适配器 MAP 句柄
     @param pName 字段名
     @return 成功返回 TRUE，否则返回 FALSE
     **/
    public static native boolean XAdMap_DeleteItem (int hAdapter, String pName);
    /**
     数据适配器 MAP_取项数量
     @param hAdapter 数据适配器 MAP 句柄
     @return 返回项数量
     **/
    public static native int XAdMap_GetCount (int hAdapter);
    /**
     数据适配器 MAP_取项文本
     @param hAdapter 数据适配器 MAP 句柄
     @param pName 字段名
     @param dwSize 接收文本的缓冲区大小（字符为单位）
     @return 返回文本内容的字节数组
     **/
    public static native byte [] XAdMap_GetItemText (int hAdapter, String pName, int dwSize);
    /**
     数据适配器 MAP_取项图片
     @param hAdapter 数据适配器 MAP 句柄
     @param pName 字段名
     @return 返回图片句柄
     **/
    public static native int XAdMap_GetItemImage (int hAdapter, String pName);
    /**
     数据适配器 MAP_置项文本
     @param hAdapter 数据适配器 MAP 句柄
     @param pName 字段名
     @param pValue 文本值
     @return 成功返回 TRUE，否则返回 FALSE
     **/
    public static native boolean XAdMap_SetItemText (int hAdapter, String pName, String pValue);
    /**
     数据适配器 MAP_置项图片
     @param hAdapter 数据适配器 MAP 句柄
     @param pName 字段名
     @param hImage 图片句柄
     @return 成功返回 TRUE，否则返回 FALSE
     **/
    public static native boolean XAdMap_SetItemImage (int hAdapter, String pName, int hImage);
    /**
     数据适配器树_创建
     @return 返回数据适配器树句柄
     **/
    public static native int XAdTree_Create ();
    /**
     数据适配器树_添加列
     @param hAdapter 数据适配器树句柄
     @param pName 字段名
     @return 返回列索引
     **/
    public static native int XAdTree_AddColumn (int hAdapter, String pName);
    /**
     数据适配器树_置列
     @param hAdapter 数据适配器树句柄
     @param pColName 列名（多个列名用逗号分隔）
     @return 返回列数量
     **/
    public static native int XAdTree_SetColumn (int hAdapter, String pColName);
    /**
     数据适配器树_插入项文本
     @param hAdapter 数据适配器树句柄
     @param pValue 文本值
     @param nParentID 父项 ID（默认根节点）
     @param insertID 插入位置 ID（默认末尾）
     @return 返回项 ID
     **/
    public static native int XAdTree_InsertItemText (int hAdapter, String pValue, int nParentID, int insertID);

    /**
     数据适配器树_插入项文本扩展 (插入项，数据填充到指定列)
     @param hAdapter 数据适配器句柄
     @param pName 字段名称
     @param pValue 值
     @param nParentID 父 ID; 如果插入到目标位置，此参数忽略 (可填任意值)
     @param insertID 插入位置 ID
     @return 返回项 ID 值
     */
    public static native int XAdTree_InsertItemTextEx (int hAdapter, String pName, String pValue, int nParentID, int insertID);
    /**
     数据适配器树_插入项图片 (插入项，数据填充到第一列)
     @param hAdapter 数据适配器句柄
     @param hImage 图片句柄
     @param nParentID 父 ID; 如果插入到目标位置，此参数忽略 (可填任意值)
     @param insertID 插入位置 ID
     @return 返回项 ID 值
     */
    public static native int XAdTree_InsertItemImage (int hAdapter, int hImage, int nParentID, int insertID);
    /**
     数据适配器树_插入项图片扩展 (插入项，数据填充到指定列)
     @param hAdapter 数据适配器句柄
     @param pName 字段名称
     @param hImage 图片句柄
     @param nParentID 父 ID; 如果插入到目标位置，此参数忽略 (可填任意值)
     @param insertID 插入位置 ID
     @return 返回项 ID 值
     */
    public static native int XAdTree_InsertItemImageEx (int hAdapter, String pName, int hImage, int nParentID, int insertID);
    /**
     数据适配器树_取项数量 (获取项数量)
     @param hAdapter 数据适配器句柄
     @return 返回项数量
     */
    public static native int XAdTree_GetCount (int hAdapter);
    /**
     数据适配器树_取列数量 (获取列数量)
     @param hAdapter 数据适配器句柄
     @return 返回列数量
     */
    public static native int XAdTree_GetCountColumn (int hAdapter);
    /**
     数据适配器树_置项文本 (设置项数据)
     @param hAdapter 数据适配器句柄
     @param nID 项 ID
     @param iColumn 列索引
     @param pValue 值
     @return 成功返回 TRUE, 否则返回 FALSE
     */
    public static native boolean XAdTree_SetItemText (int hAdapter, int nID, int iColumn, String pValue);
    /**
     数据适配器树_置项文本扩展 (设置项文件内容)
     @param hAdapter 数据适配器句柄
     @param nID 项 ID
     @param pName 字段名称
     @param pValue 值
     @return 成功返回 TRUE, 否则返回 FALSE
     */
    public static native boolean XAdTree_SetItemTextEx (int hAdapter, int nID, String pName, String pValue);
    /**
     数据适配器树_置项图片 (设置项数据)
     @param hAdapter 数据适配器句柄
     @param nID 项 ID
     @param iColumn 列索引
     @param hImage 图片句柄
     @return 成功返回 TRUE, 否则返回 FALSE
     */
    public static native boolean XAdTree_SetItemImage (int hAdapter, int nID, int iColumn, int hImage);
    /**
     数据适配器树_置项图片扩展 (设置项内容)
     @param hAdapter 数据适配器句柄
     @param nID 项 ID
     @param pName 字段名称
     @param hImage 图片句柄
     @return 成功返回 TRUE, 否则返回 FALSE
     */
    public static native boolean XAdTree_SetItemImageEx (int hAdapter, int nID, String pName, int hImage);
    /**
     数据适配器树_取项文本 (获取项文本内容)
     @param hAdapter 数据适配器句柄
     @param nID 项 ID
     @param iColumn 列索引
     @param dwSize 输出缓冲区大小（字符为单位）
     @return 返回文本内容的字节数组
     */
    public static native byte [] XAdTree_GetItemText (int hAdapter, int nID, int iColumn, int dwSize);
    /**
     数据适配器树_取项文本扩展 (获取项文本内容)
     @param hAdapter 数据适配器句柄
     @param nID 项 ID
     @param pName 字段名称
     @param dwSize 输出缓冲区大小（字符为单位）
     @return 返回文本内容的字节数组
     */
    public static native byte [] XAdTree_GetItemTextEx (int hAdapter, int nID, String pName, int dwSize);
    /**
     数据适配器树_取项图片 (获取项内容)
     @param hAdapter 数据适配器句柄
     @param nID 项 ID
     @param iColumn 列索引
     @return 返回图片句柄
     */
    public static native int XAdTree_GetItemImage (int hAdapter, int nID, int iColumn);
    /**
     数据适配器树_取项图片扩展 (获取项内容)
     @param hAdapter 数据适配器句柄
     @param nID 项 ID
     @param pName 字段名称
     @return 返回图片句柄
     */
    public static native int XAdTree_GetItemImageEx (int hAdapter, int nID, String pName);
    /**
     数据适配器树_删除项
     @param hAdapter 数据适配器句柄
     @param nID 项 ID
     @return 成功返回 TRUE, 否则返回 FALSE
     */
    public static native boolean XAdTree_DeleteItem (int hAdapter, int nID);
    /**
     数据适配器树_删除项全部 (删除所有项)
     @param hAdapter 数据适配器句柄
     */
    public static native void XAdTree_DeleteItemAll (int hAdapter);

    /**
     删除所有列，并且清空数据
     @param hAdapter 数据适配器句柄
     */
    public static native void XAdTree_DeleteColumnAll (int hAdapter);
    /**
     销毁背景管理器
     @param hBkInfoM 背景管理器句柄
     */
    public static native void XBkM_Destroy (int hBkInfoM);
    /**
     废弃函数，保留为了兼容旧版；请使用 XBkM_SetInfo ()
     @param hBkInfoM 背景管理器句柄
     @param pText 背景内容字符串
     @return 设置的背景对象数量
     */
    public static native int XBkM_SetBkInfo (int hBkInfoM, String pText);
    /**
     设置背景内容
     @param hBkInfoM 背景管理器句柄
     @param pText 背景内容字符串
     @return 设置的背景对象数量
     */
    public static native int XBkM_SetInfo (int hBkInfoM, String pText);
    /**
     添加背景内容
     @param hBkInfoM 背景管理器句柄
     @param pText 背景内容字符串
     @return 添加的背景对象数量
     */
    public static native int XBkM_AddInfo (int hBkInfoM, String pText);
    /**
     添加背景内容边框
     @param hBkInfoM 背景管理器句柄
     @param nState 组合状态
     @param color 颜色值（请使用 RGBA 宏）
     @param width 线宽
     @param id 背景对象 ID（可忽略，填 0）
     */
    public static native void XBkM_AddBorder (int hBkInfoM, int nState, long color, int width, int id);
    /**
     添加背景内容填充
     @param hBkInfoM 背景管理器句柄
     @param nState 组合状态
     @param color 颜色值（请使用 RGBA 宏）
     @param id 背景对象 ID（可忽略，填 0）
     */
    public static native void XBkM_AddFill (int hBkInfoM, int nState, long color, int id);
    /**
     添加背景内容图片
     @param hBkInfoM 背景管理器句柄
     @param nState 组合状态
     @param hImage 图片句柄
     @param id 背景对象 ID（可忽略，填 0）
     */
    public static native void XBkM_AddImage (int hBkInfoM, int nState, int hImage, int id);
    /**
     获取背景内容数量
     @param hBkInfoM 背景管理器句柄
     @return 背景内容数量
     */
    public static native int XBkM_GetCount (int hBkInfoM);
    /**
     清空背景内容
     @param hBkInfoM 背景管理器句柄
     */
    public static native void XBkM_Clear (int hBkInfoM);
    /**
     绘制背景内容
     @param hBkInfoM 背景管理器句柄
     @param nState 组合状态
     @param hDraw 图形绘制句柄
     @param pRect 区域坐标
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XBkM_Draw (int hBkInfoM, int nState, long hDraw, TagRect pRect);
    /**
     绘制背景内容，设置条件
     @param hBkInfoM 背景管理器句柄
     @param nState 组合状态
     @param hDraw 图形绘制句柄
     @param pRect 区域坐标
     @param nStateEx 当 (nState) 中包含 (nStateEx) 中的一个或多个状态时有效
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XBkM_DrawEx (int hBkInfoM, int nState, long hDraw, TagRect pRect, int nStateEx);
    /**
     设置背景管理器是否自动销毁
     @param hBkInfoM 背景管理器句柄
     @param bEnable 是否启用自动销毁（true 启用，false 关闭）
     */
    public static native void XBkM_EnableAutoDestroy (int hBkInfoM, boolean bEnable);

    /**
     背景管理器 - 增加引用计数
     @param hBkInfoM 背景管理器句柄
     **/
    public static native void XBkM_AddRef (int hBkInfoM);
    /**
     背景管理器 - 释放引用计数，当引用计数为 0 时自动销毁
     @param hBkInfoM 背景管理器句柄
     **/
    public static native void XBkM_Release (int hBkInfoM);
    /**
     背景管理器 - 获取引用计数
     @param hBkInfoM 背景管理器句柄
     @return 返回当前引用计数
     **/
    public static native int XBkM_GetRefCount (int hBkInfoM);
    /**
     背景管理器 - 获取指定状态的文本颜色
     @param hBkInfoM 背景管理器句柄
     @param nState 组合状态（参见 API 文档组合状态说明）
     @param color 输出参数，接收返回的颜色值
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XBkM_GetStateTextColor (int hBkInfoM, int nState, byte [] color);
    /**
     背景管理器 - 根据 ID 获取背景对象
     @param hBkInfoM 背景管理器句柄
     @param id 背景对象 ID
     @return 返回背景对象句柄的字节数组表示
     **/
    public static native byte [] XBkM_GetObject (int hBkInfoM, int id);
    /**
     绘图 - 销毁图形绘制模块实例
     @param hDraw 图形绘制句柄
     **/
    public static native void XDraw_Destroy (long hDraw);
    /**
     绘图 - 设置坐标偏移量（X 向左偏移为负数，向右为正数；Y 向上偏移为负数，向下为正数）
     @param hDraw 图形绘制句柄
     @param x X 轴偏移量
     @param y Y 轴偏移量
     **/
    public static native void XDraw_SetOffset (long hDraw, int x, int y);
    /**
     绘图 - GDI - 选择裁剪区域（仅对 GDI 有效）
     @param hDraw 图形绘制句柄
     @param hRgn 区域句柄
     @return 返回区域复杂性（NULLREGION/SIMPLEREGION/COMPLEXREGION/ERROR）
     **/
    public static native int XDraw_GDI_SelectClipRgn (long hDraw, long hRgn);
    /**
     绘图 - GDI - 创建圆角矩形区域
     @param hDraw 图形绘制句柄
     @param nLeftRect 左上角 X 坐标
     @param nTopRect 左上角 Y 坐标
     @param nRightRect 右下角 X 坐标
     @param nBottomRect 右下角 Y 坐标
     @param nWidthEllipse 椭圆宽度（圆角宽度）
     @param nHeightEllipse 椭圆高度（圆角高度）
     @return 返回区域句柄
     **/
    public static native long XDraw_GDI_CreateRoundRectRgn (long hDraw, int nLeftRect, int nTopRect, int nRightRect, int nBottomRect, int nWidthEllipse, int nHeightEllipse);
    /**
     绘图 - GDI - 使用指定画刷填充区域
     @param hDraw 图形绘制句柄
     @param hrgn 区域句柄
     @param hbr 画刷句柄
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XDraw_GDI_FillRgn (long hDraw, long hrgn, int hbr);
    /**
     绘图 - GDI - 使用指定画刷绘制区域边框
     @param hDraw 图形绘制句柄
     @param hrgn 区域句柄
     @param hbr 画刷句柄
     @param width 边框宽度（垂直边）
     @param nHeight 边框高度（水平边）
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XDraw_GDI_FrameRgn (long hDraw, long hrgn, int hbr, int width, int nHeight);
    /**
     绘图 - GDI - 从当前位置绘制直线到指定点
     @param hDraw 图形绘制句柄
     @param nXEnd 直线结束点 X 坐标
     @param nYEnd 直线结束点 Y 坐标
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XDraw_GDI_LineTo (long hDraw, int nXEnd, int nYEnd);
    /**
     绘图 - GDI - 绘制图标（参见 MSDN DrawIconEx）
     @param hDraw 图形绘制句柄
     @param xLeft 图标左上角 X 坐标
     @param yTop 图标左上角 Y 坐标
     @param hIcon 要绘制的图标的句柄
     @param cxWidth 图标的宽度
     @param cyWidth 图标的高度
     @param istepIfAniCur 动画光标的帧索引，静态图标则为 0
     @param hbrFlickerFreeDraw 背景闪烁自由的画刷，通常为 null
     @param diFlags 绘制标志（DI_COMPAT/DI_DEFAULTSIZE/DI_IMAGE/DI_MASK/DI_NORMAL）
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XDraw_GDI_DrawIconEx (long hDraw, int xLeft, int yTop, int hIcon, int cxWidth, int cyWidth, long istepIfAniCur, int hbrFlickerFreeDraw, long diFlags);
    /**
     绘图 - GDI - 复制位图（参见 MSDN BitBlt）
     @param hDrawDest 目标图形绘制句柄
     @param nXDest 目标左上角 X 坐标
     @param nYDest 目标左上角 Y 坐标
     @param width 宽度
     @param nHeight 高度
     @param hdcSrc 源 HDC 句柄
     @param nXSrc 源左上角 X 坐标
     @param nYSrc 源左上角 Y 坐标
     @param dwRop 光栅操作码
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XDraw_GDI_BitBlt (long hDrawDest, int nXDest, int nYDest, int width, int nHeight, long hdcSrc, int nXSrc, int nYSrc, int dwRop);
    /**
     绘图 - GDI - 复制位图（源为图形绘制句柄）
     @param hDrawDest 目标图形绘制句柄
     @param nXDest 目标左上角 X 坐标
     @param nYDest 目标左上角 Y 坐标
     @param width 宽度
     @param nHeight 高度
     @param hDrawSrc 源图形绘制句柄
     @param nXSrc 源左上角 X 坐标
     @param nYSrc 源左上角 Y 坐标
     @param dwRop 光栅操作码
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XDraw_GDI_BitBlt2 (long hDrawDest, int nXDest, int nYDest, int width, int nHeight, long hDrawSrc, int nXSrc, int nYSrc, int dwRop);
    /**
     绘图 - GDI - 带透明度的位图混合（参见 MSDN AlphaBlend）
     @param hDraw 图形绘制句柄
     @param nXOriginDest 目标左上角 X 坐标
     @param nYOriginDest 目标左上角 Y 坐标
     @param nWidthDest 目标宽度
     @param nHeightDest 目标高度
     @param hdcSrc 源 HDC 句柄
     @param nXOriginSrc 源左上角 X 坐标
     @param nYOriginSrc 源左上角 Y 坐标
     @param nWidthSrc 源宽度
     @param nHeightSrc 源高度
     @param alpha 透明度
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XDraw_GDI_AlphaBlend (long hDraw, int nXOriginDest, int nYOriginDest, int nWidthDest, int nHeightDest, long hdcSrc, int nXOriginSrc, int nYOriginSrc, int nWidthSrc, int nHeightSrc, int alpha);
    /**
     绘图 - GDI - 设置指定坐标的像素颜色
     @param hDraw 图形绘制句柄
     @param X 像素 X 坐标
     @param Y 像素 Y 坐标
     @param crColor 颜色值（使用宏 RGBA () 生成）
     @return 返回设置后的 RGB 值，失败返回 - 1
     **/
    public static native long XDraw_GDI_SetPixel (long hDraw, int X, int Y, long crColor);
    /**
     绘图 - 获取 D2D 工厂（ID2D1Factory*）
     @param hDraw 图形绘制句柄
     @return 返回 D2D 工厂的字节数组表示
     **/
    public static native long XDraw_GetD2dFactory (long hDraw);
    /**
     绘图 - 获取 DWrite 工厂（IDWriteFactory*）
     @param hDraw 图形绘制句柄
     @return 返回 DWrite 工厂的字节数组表示
     **/
    public static native long XDraw_GetD2dWriteFactory (long hDraw);
    /**
     绘图 - 获取 WIC 工厂（IWICImagingFactory*）
     @param hDraw 图形绘制句柄
     @return 返回 WIC 工厂的字节数组表示
     **/
    public static native long XDraw_GetD2dWICFactory (long hDraw);
    /**
     绘图 - 获取 D2D 渲染目标（ID2D1RenderTarget*）
     @param hDraw 图形绘制句柄
     @return 返回 D2D 渲染目标的字节数组表示
     **/
    public static native long XDraw_GetD2dRenderTarget (long hDraw);
    /**
     绘图 - 获取 D2D 位图（ID2D1Bitmap*）
     @param hDraw 图形绘制句柄
     @param hImageFrame 图片帧句柄
     @return 返回 D2D 位图的字节数组表示
     **/
    public static native byte [] XDraw_GetD2dBitmap (long hDraw, int hImageFrame);
    /**
     绘图 - 设置 D2D 文本渲染模式
     @param hDraw 图形绘制句柄
     @param mode 渲染模式（XC_DWRITE_RENDERING_MODE 枚举）
     **/
    public static native void XDraw_SetD2dTextRenderingMode (long hDraw, int mode);
    /**
     绘图 - 设置文本渲染质量（GDI+）
     @param hDraw 图形绘制句柄
     @param nType 渲染质量类型（参见 GDI+ TextRenderingHint 定义）
     **/
    public static native void XDraw_SetTextRenderingHint (long hDraw, int nType);
    /**
     绘图 - D2D - 用指定颜色清理画布
     @param hDraw 图形绘制句柄
     @param color 清理颜色值（使用宏 RGBA () 生成）
     **/
    public static native void XDraw_D2D_Clear (long hDraw, long color);
    /**
     绘图 - 设置画刷颜色
     @param hDraw 图形绘制句柄
     @param color 颜色值（使用宏 RGBA () 生成）
     **/
    public static native void XDraw_SetBrushColor (long hDraw, long color);
    /**
     绘图 - 设置文本垂直显示
     @param hDraw 图形绘制句柄
     @param bVertical 是否垂直显示文本
     **/
    public static native void XDraw_SetTextVertical (long hDraw, boolean bVertical);
    /**
     绘图 - 设置文本对齐方式
     @param hDraw 图形绘制句柄
     @param nFlag 对齐标识（textFormatFlag_枚举）
     **/
    public static native void XDraw_SetTextAlign (long hDraw, int nFlag);
    /**
     绘图 - 设置字体
     @param hDraw 图形绘制句柄
     @param hFontx 炫彩字体句柄
     **/
    public static native void XDraw_SetFont (long hDraw, int hFontx);
    /**
     绘图 - 设置线宽
     @param hDraw 图形绘制句柄
     @param width 线宽
     **/
    public static native void XDraw_SetLineWidth (long hDraw, int width);
    /**
     绘图 - 设置线宽（浮点）
     @param hDraw 图形绘制句柄
     @param width 线宽
     **/
    public static native void XDraw_SetLineWidthF (long hDraw, float width);
    /**
     绘图 - 设置裁剪区域
     @param hDraw 图形绘制句柄
     @param pRect 裁剪区域坐标
     **/
    public static native void XDraw_SetClipRect (long hDraw, TagRect pRect);
    /**
     绘图 - 清除裁剪区域
     @param hDraw 图形绘制句柄
     **/
    public static native void XDraw_ClearClip (long hDraw);
    /**
     绘图 - 启用平滑模式
     @param hDraw 图形绘制句柄
     @param bEnable 是否启用平滑模式
     **/
    public static native void XDraw_EnableSmoothingMode (long hDraw, boolean bEnable);
    /**
     绘图 - 启用窗口透明判断（启用后调用 GDI + 函数时 alpha=255 自动修改为 254，解决透明通道异常）
     @param hDraw 图形绘制句柄
     @param bTransparent 是否启用
     **/
    public static native void XDraw_EnableWndTransparent (long hDraw, boolean bTransparent);
    /**
     绘图 - 填充矩形（使用当前画刷颜色）
     @param hDraw 图形绘制句柄
     @param pRect 矩形区域坐标
     **/
    public static native void XDraw_FillRect (long hDraw, TagRect pRect);
    /**
     绘图 - 填充矩形（浮点坐标，使用当前画刷颜色）
     @param hDraw 图形绘制句柄
     @param pRect 矩形区域坐标（浮点）
     **/
    public static native void XDraw_FillRectF (long hDraw, TagRectF pRect);
    /**
     绘图 - 用指定颜色填充矩形
     @param hDraw 图形绘制句柄
     @param pRect 矩形区域坐标
     @param color 填充颜色值（使用宏 RGBA () 生成）
     **/
    public static native void XDraw_FillRectColor (long hDraw, TagRect pRect, long color);
    /**
     绘制填充指定颜色的矩形（浮点坐标）
     @param hDraw 图形绘制句柄
     @param pRect 矩形区域（浮点坐标）
     @param color 颜色值，使用宏 RGBA () 生成
     */
    public static native void XDraw_FillRectColorF (long hDraw, TagRectF pRect, long color);
    /**
     绘制填充椭圆
     @param hDraw 图形绘制句柄
     @param pRect 椭圆外切矩形区域
     */
    public static native void XDraw_FillEllipse (long hDraw, TagRect pRect);
    /**
     绘制填充椭圆（浮点坐标）
     @param hDraw 图形绘制句柄
     @param pRect 椭圆外切矩形区域（浮点坐标）
     */
    public static native void XDraw_FillEllipseF (long hDraw, TagRectF pRect);
    /**
     绘制椭圆边框
     @param hDraw 图形绘制句柄
     @param pRect 椭圆外切矩形区域
     */
    public static native void XDraw_DrawEllipse (long hDraw, TagRect pRect);
    /**
     绘制椭圆边框（浮点坐标）
     @param hDraw 图形绘制句柄
     @param pRect 椭圆外切矩形区域（浮点坐标）
     */
    public static native void XDraw_DrawEllipseF (long hDraw, TagRectF pRect);
    /**
     绘制填充圆角矩形
     @param hDraw 图形绘制句柄
     @param pRect 矩形区域
     @param width 圆角宽度
     @param nHeight 圆角高度
     */
    public static native void XDraw_FillRoundRect (long hDraw, TagRect pRect, int width, int nHeight);
    /**
     绘制填充圆角矩形（浮点坐标）
     @param hDraw 图形绘制句柄
     @param pRect 矩形区域（浮点坐标）
     @param width 圆角宽度
     @param height 圆角高度
     */
    public static native void XDraw_FillRoundRectF (long hDraw, TagRectF pRect, float width, float height);
    /**
     绘制圆角矩形边框
     @param hDraw 图形绘制句柄
     @param pRect 矩形区域
     @param width 圆角宽度
     @param nHeight 圆角高度
     */
    public static native void XDraw_DrawRoundRect (long hDraw, TagRect pRect, int width, int nHeight);
    /**
     绘制圆角矩形边框（浮点坐标）
     @param hDraw 图形绘制句柄
     @param pRect 矩形区域（浮点坐标）
     @param width 圆角宽度
     @param height 圆角高度
     */
    public static native void XDraw_DrawRoundRectF (long hDraw, TagRectF pRect, float width, float height);
    /**
     绘制填充扩展圆角矩形（四个角分别指定圆角大小）
     @param hDraw 图形绘制句柄
     @param pRect 矩形区域
     @param leftTop 左上角圆角大小
     @param rightTop 右上角圆角大小
     @param rightBottom 右下角圆角大小
     @param leftBottom 左下角圆角大小
     */
    public static native void XDraw_FillRoundRectEx (long hDraw, TagRect pRect, int leftTop, int rightTop, int rightBottom, int leftBottom);
    /**
     绘制填充扩展圆角矩形（浮点坐标，四个角分别指定圆角大小）
     @param hDraw 图形绘制句柄
     @param pRect 矩形区域（浮点坐标）
     @param leftTop 左上角圆角大小
     @param rightTop 右上角圆角大小
     @param rightBottom 右下角圆角大小
     @param leftBottom 左下角圆角大小
     */
    public static native void XDraw_FillRoundRectExF (long hDraw, TagRectF pRect, float leftTop, float rightTop, float rightBottom, float leftBottom);
    /**
     绘制扩展圆角矩形边框（四个角分别指定圆角大小）
     @param hDraw 图形绘制句柄
     @param pRect 矩形区域
     @param leftTop 左上角圆角大小
     @param rightTop 右上角圆角大小
     @param rightBottom 右下角圆角大小
     @param leftBottom 左下角圆角大小
     */
    public static native void XDraw_DrawRoundRectEx (long hDraw, TagRect pRect, int leftTop, int rightTop, int rightBottom, int leftBottom);
    /**
     绘制扩展圆角矩形边框（浮点坐标，四个角分别指定圆角大小）
     @param hDraw 图形绘制句柄
     @param pRect 矩形区域（浮点坐标）
     @param leftTop 左上角圆角大小
     @param rightTop 右上角圆角大小
     @param rightBottom 右下角圆角大小
     @param leftBottom 左下角圆角大小
     */
    public static native void XDraw_DrawRoundRectExF (long hDraw, TagRectF pRect, float leftTop, float rightTop, float rightBottom, float leftBottom);
    /**
     绘制渐变填充（两种颜色，浮点坐标）
     @param hDraw 图形绘制句柄
     @param pRect 矩形区域（浮点坐标）
     @param color1 起始颜色
     @param color2 结束颜色
     @param mode 填充模式（GRADIENT_FILL_RECT_H/GRADIENT_FILL_RECT_V/GRADIENT_FILL_TRIANGLE）
     */
    public static native void XDraw_GradientFill2F (long hDraw, TagRectF pRect, long color1, long color2, int mode);
    /**
     绘制渐变填充（四种颜色）
     @param hDraw 图形绘制句柄
     @param pRect 矩形区域
     @param color1 颜色 1
     @param color2 颜色 2
     @param color3 颜色 3
     @param color4 颜色 4
     @param mode 填充模式（GRADIENT_FILL_RECT_H/GRADIENT_FILL_RECT_V/GRADIENT_FILL_TRIANGLE）
     */
    public static native void XDraw_GradientFill4 (long hDraw, TagRect pRect, long color1, long color2, long color3, long color4, int mode);
    /**
     绘制渐变填充（四种颜色，浮点坐标）
     @param hDraw 图形绘制句柄
     @param pRect 矩形区域（浮点坐标）
     @param color1 颜色 1
     @param color2 颜色 2
     @param color3 颜色 3
     @param color4 颜色 4
     @param mode 填充模式（GRADIENT_FILL_RECT_H/GRADIENT_FILL_RECT_V/GRADIENT_FILL_TRIANGLE）
     */
    public static native void XDraw_GradientFill4F (long hDraw, TagRectF pRect, long color1, long color2, long color3, long color4, int mode);
    /**
     绘制矩形边框（浮点坐标）
     @param hDraw 图形绘制句柄
     @param pRect 矩形区域（浮点坐标）
     */
    public static native void XDraw_DrawRectF (long hDraw, TagRectF pRect);
    /**
     绘制线条
     @param hDraw 图形绘制句柄
     @param x1 起始点 X 坐标
     @param y1 起始点 Y 坐标
     @param x2 结束点 X 坐标
     @param y2 结束点 Y 坐标
     */
    public static native void XDraw_DrawLine (long hDraw, int x1, int y1, int x2, int y2);
    /**
     绘制线条（浮点坐标）
     @param hDraw 图形绘制句柄
     @param x1 起始点 X 坐标
     @param y1 起始点 Y 坐标
     @param x2 结束点 X 坐标
     @param y2 结束点 Y 坐标
     */
    public static native void XDraw_DrawLineF (long hDraw, float x1, float y1, float x2, float y2);
    /**
     绘制圆弧
     @param hDraw 图形绘制句柄
     @param x 圆心 X 坐标
     @param y 圆心 Y 坐标
     @param width 宽度
     @param nHeight 高度
     @param startAngle 起始角度
     @param sweepAngle 绘制角度（从起始角度开始计算）
     */
    public static native void XDraw_DrawArc (long hDraw, int x, int y, int width, int nHeight, float startAngle, float sweepAngle);
    /**
     绘制圆弧（浮点坐标）
     @param hDraw 图形绘制句柄
     @param x 圆心 X 坐标
     @param y 圆心 Y 坐标
     @param width 宽度
     @param height 高度
     @param startAngle 起始角度
     @param sweepAngle 绘制角度（从起始角度开始计算）
     */
    public static native void XDraw_DrawArcF (long hDraw, float x, float y, float width, float height, float startAngle, float sweepAngle);
    /**
     绘制焦点矩形
     @param hDraw 图形绘制句柄
     @param pRect 矩形区域
     */
    public static native void XDraw_FocusRect (long hDraw, TagRect pRect);
    /**
     绘制焦点矩形（浮点坐标）
     @param hDraw 图形绘制句柄
     @param pRect 矩形区域（浮点坐标）
     */
    public static native void XDraw_FocusRectF (long hDraw, TagRectF pRect);
    /**
     绘制水平或垂直虚线
     @param hDraw 图形绘制句柄
     @param x1 起点 X 坐标
     @param y1 起点 Y 坐标
     @param x2 结束点 X 坐标
     @param y2 结束点 Y 坐标
     */
    public static native void XDraw_Dottedline (long hDraw, int x1, int y1, int x2, int y2);
    /**
     绘制水平或垂直虚线（浮点坐标）
     @param hDraw 图形绘制句柄
     @param x1 起点 X 坐标
     @param y1 起点 Y 坐标
     @param x2 结束点 X 坐标
     @param y2 结束点 Y 坐标
     */
    public static native void XDraw_DottedlineF (long hDraw, float x1, float y1, float x2, float y2);
    /**
     绘制多边形
     @param hDraw 图形绘制句柄
     @param points 顶点坐标数组
     @param nCount 顶点数量
     */
    public static native void XDraw_DrawPolygon (long hDraw, TagPoint [] points, int nCount);
    /**
     绘制浮点坐标的多边形边框
     @param hDraw 图形绘制句柄
     @param points 浮点坐标点数组
     @param nCount 点的数量
     **/
    public static native void XDraw_DrawPolygonF (long hDraw, TagPointF [] points, int nCount);
    /**
     填充整数坐标的多边形区域
     @param hDraw 图形绘制句柄
     @param points 整数坐标点数组
     @param nCount 点的数量
     **/
    public static native void XDraw_FillPolygon (long hDraw, TagPoint [] points, int nCount);
    /**
     填充浮点坐标的多边形区域
     @param hDraw 图形绘制句柄
     @param points 浮点坐标点数组
     @param nCount 点的数量
     **/
    public static native void XDraw_FillPolygonF (long hDraw, TagPointF [] points, int nCount);
    /**
     绘制图片（整数坐标）
     @param hDraw 图形绘制句柄
     @param hImageFrame 图片帧句柄
     @param x X 坐标
     @param y Y 坐标
     **/
    public static native void XDraw_Image (long hDraw, int hImageFrame, int x, int y);
    /**
     绘制图片（浮点坐标）
     @param hDraw 图形绘制句柄
     @param hImageFrame 图片帧句柄
     @param x X 坐标
     @param y Y 坐标
     **/
    public static native void XDraw_ImageF (long hDraw, int hImageFrame, float x, float y);
    /**
     绘制图片并指定宽高（整数坐标）
     @param hDraw 图形绘制句柄
     @param hImageFrame 图片帧句柄
     @param x X 坐标
     @param y Y 坐标
     @param width 宽度
     @param height 高度
     **/
    public static native void XDraw_ImageEx (long hDraw, int hImageFrame, int x, int y, int width, int height);
    /**
     绘制图片并指定宽高（浮点坐标）
     @param hDraw 图形绘制句柄
     @param hImageFrame 图片帧句柄
     @param x X 坐标
     @param y Y 坐标
     @param width 宽度
     @param height 高度
     **/
    public static native void XDraw_ImageExF (long hDraw, int hImageFrame, float x, float y, float width, float height);
    /**
     自适应绘制图片（九宫格模式，整数坐标）
     @param hDraw 图形绘制句柄
     @param hImageFrame 图片帧句柄
     @param pRect 目标区域坐标
     @param bOnlyBorder 是否只绘制边缘区域
     **/
    public static native void XDraw_ImageAdaptive (long hDraw, int hImageFrame, TagRect pRect, boolean bOnlyBorder);
    /**
     自适应绘制图片（九宫格模式，浮点坐标）
     @param hDraw 图形绘制句柄
     @param hImageFrame 图片帧句柄
     @param pRect 目标区域坐标（浮点）
     @param bOnlyBorder 是否只绘制边缘区域
     **/
    public static native void XDraw_ImageAdaptiveF (long hDraw, int hImageFrame, TagRectF pRect, boolean bOnlyBorder);
    /**
     平铺绘制图片（整数坐标）
     @param hDraw 图形绘制句柄
     @param hImageFrame 图片帧句柄
     @param pRect 目标区域坐标
     @param flag 平铺标志（0: 从左上角开始，1: 从左下角开始）
     **/
    public static native void XDraw_ImageTile (long hDraw, int hImageFrame, TagRect pRect, int flag);
    /**
     平铺绘制图片（浮点坐标）
     @param hDraw 图形绘制句柄
     @param hImageFrame 图片帧句柄
     @param pRect 目标区域坐标（浮点）
     @param flag 平铺标志（0: 从左上角开始，1: 从左下角开始）
     **/
    public static native void XDraw_ImageTileF (long hDraw, int hImageFrame, TagRectF pRect, int flag);
    /**
     增强版绘制图片（整数坐标，支持裁剪）
     @param hDraw 图形绘制句柄
     @param hImageFrame 图片帧句柄
     @param pRect 目标区域坐标
     @param bClip 是否裁剪
     **/
    public static native void XDraw_ImageSuper (long hDraw, int hImageFrame, TagRect pRect, boolean bClip);
    /**
     增强版绘制图片（浮点坐标，支持裁剪）
     @param hDraw 图形绘制句柄
     @param hImageFrame 图片帧句柄
     @param pRect 目标区域坐标（浮点）
     @param bClip 是否裁剪
     **/
    public static native void XDraw_ImageSuperF (long hDraw, int hImageFrame, TagRectF pRect, boolean bClip);
    /**
     增强版绘制图片（指定源和目标区域，整数坐标）
     @param hDraw 图形绘制句柄
     @param hImageFrame 图片帧句柄
     @param pRcDest 目标区域坐标
     @param pRcSrc 源区域坐标
     **/
    public static native void XDraw_ImageSuperEx (long hDraw, int hImageFrame, TagRect pRcDest, TagRect pRcSrc);
    /**
     增强版绘制图片（指定源和目标区域，浮点坐标）
     @param hDraw 图形绘制句柄
     @param hImageFrame 图片帧句柄
     @param pRcDest 目标区域坐标（浮点）
     @param pRcSrc 源区域坐标（浮点）
     **/
    public static native void XDraw_ImageSuperExF (long hDraw, int hImageFrame, TagRectF pRcDest, TagRectF pRcSrc);
    /**
     增强版带遮罩绘制图片（整数坐标）
     @param hDraw 图形绘制句柄
     @param hImageFrame 图片帧句柄
     @param hImageFrameMask 遮罩图片帧句柄
     @param pRect 目标区域坐标
     @param pRectMask 遮罩区域坐标
     @param bClip 是否裁剪
     **/
    public static native void XDraw_ImageSuperMask (long hDraw, int hImageFrame, int hImageFrameMask, TagRect pRect, TagRect pRectMask, boolean bClip);
    /**
     带遮罩绘制图片（整数坐标）
     @param hDraw 图形绘制句柄
     @param hImageFrame 图片帧句柄
     @param hImageFrameMask 遮罩图片帧句柄
     @param pRect 目标区域坐标
     @param x 遮罩 X 坐标
     @param y 遮罩 Y 坐标
     **/
    public static native void XDraw_ImageMask (long hDraw, int hImageFrame, int hImageFrameMask, TagRect pRect, int x, int y);
    /**
     使用矩形作为遮罩绘制图片
     @param hDraw 图形绘制句柄
     @param hImageFrame 图片帧句柄
     @param pRect 目标区域坐标
     @param pRcMask 遮罩矩形坐标
     @param pRcRoundAngle 遮罩圆角坐标
     **/
    public static native void XDraw_ImageMaskRect (long hDraw, int hImageFrame, TagRect pRect, TagRect pRcMask, TagRect pRcRoundAngle);
    /**
     使用圆形作为遮罩绘制图片
     @param hDraw 图形绘制句柄
     @param hImageFrame 图片帧句柄
     @param pRect 目标区域坐标
     @param pRcMask 遮罩圆形区域坐标
     **/
    public static native void XDraw_ImageMaskEllipse (long hDraw, int hImageFrame, TagRect pRect, TagRect pRcMask);
    /**
     绘制文本到指定浮点坐标区域
     @param hDraw 图形绘制句柄
     @param pString 文本内容
     @param nCount 文本长度（-1 自动计算）
     @param pRect 目标区域坐标（浮点）
     **/
    public static native void XDraw_DrawTextF (long hDraw, String pString, int nCount, TagRectF pRect);
    /**
     绘制带下划线的文本（整数坐标）
     @param hDraw 图形绘制句柄
     @param pString 文本内容
     @param nCount 文本长度（-1 自动计算）
     @param pRect 目标区域坐标
     @param colorLine 下划线颜色
     **/
    public static native void XDraw_DrawTextUnderline (long hDraw, String pString, int nCount, TagRect pRect, long colorLine);
    /**
     绘制带下划线的文本（浮点坐标）
     @param hDraw 图形绘制句柄
     @param pString 文本内容
     @param nCount 文本长度（-1 自动计算）
     @param pRect 目标区域坐标（浮点）
     @param colorLine 下划线颜色
     **/
    public static native void XDraw_DrawTextUnderlineF (long hDraw, String pString, int nCount, TagRectF pRect, long colorLine);
    /**
     输出文本（整数坐标）
     @param hDraw 图形绘制句柄
     @param xStart 起始 X 坐标
     @param yStart 起始 Y 坐标
     @param pString 文本内容
     @param cbString 文本长度
     **/
    public static native void XDraw_TextOut (long hDraw, int xStart, int yStart, String pString, int cbString);
    /**
     输出文本（浮点坐标）
     @param hDraw 图形绘制句柄
     @param xStart 起始 X 坐标（浮点）
     @param yStart 起始 Y 坐标（浮点）
     @param pString 文本内容
     @param cbString 文本长度
     **/
    public static native void XDraw_TextOutF (long hDraw, float xStart, float yStart, String pString, int cbString);
    /**
     在指定坐标绘制文本扩展
     @param hDraw 图形绘制句柄
     @param xStart X 坐标
     @param yStart Y 坐标
     @param pString 文本内容
     */
    public static native void XDraw_TextOutEx (long hDraw, int xStart, int yStart, String pString);
    /**
     在指定坐标绘制文本扩展 (Float 坐标)
     @param hDraw 图形绘制句柄
     @param xStart X 坐标 (Float)
     @param yStart Y 坐标 (Float)
     @param pString 文本内容
     */
    public static native void XDraw_TextOutExF (long hDraw, float xStart, float yStart, String pString);
    /**
     在指定坐标绘制 ANSI 文本
     @param hDraw 图形绘制句柄
     @param xStart X 坐标
     @param yStart Y 坐标
     @param pString ANSI 文本内容
     */
    public static native void XDraw_TextOutA (long hDraw, int xStart, int yStart, String pString);
    /**
     在指定坐标绘制 ANSI 文本 (Float 坐标)
     @param hDraw 图形绘制句柄
     @param xStart X 坐标 (Float)
     @param yStart Y 坐标 (Float)
     @param pString ANSI 文本内容
     */
    public static native void XDraw_TextOutAF (long hDraw, float xStart, float yStart, String pString);
    /**
     绘制 SVG 源
     @param hDraw 图形绘制句柄
     @param hSvg SVG 句柄
     */
    public static native void XDraw_DrawSvgSrc (long hDraw, int hSvg);
    /**
     绘制 SVG 扩展
     @param hDraw 图形绘制句柄
     @param hSvg SVG 句柄
     @param x X 坐标
     @param y Y 坐标
     @param nWidth 宽度
     @param nHeight 高度
     */
    public static native void XDraw_DrawSvgEx (long hDraw, int hSvg, int x, int y, int nWidth, int nHeight);
    /**
     绘制 SVG 大小
     @param hDraw 图形绘制句柄
     @param hSvg SVG 句柄
     @param nWidth 宽度
     @param nHeight 高度
     */
    public static native void XDraw_DrawSvgSize (long hDraw, int hSvg, int nWidth, int nHeight);
    /**
     创建炫彩字体扩展
     @param pName 字体名称
     @param size 字体大小 (pt)
     @param style 字体样式
     @return 字体句柄
     */
    public static native int XFont_CreateEx (String pName, int size, int style);
    /**
     从 HFONT 创建炫彩字体
     @param hFont HFONT 句柄
     @return 炫彩字体句柄
     */
    public static native int XFont_CreateFromHFONT (int hFont);
    /**
     从 Font 创建炫彩字体
     @param pFont Font 指针
     @return 炫彩字体句柄
     */
    public static native int XFont_CreateFromFont (int pFont);
    /**
     从 Font 创建炫彩字体
     @param pFont Font 指针
     @return 炫彩字体句柄
     */
    public static native int XFont_CreateFromFont (long pFont);
    /**
     从文件创建炫彩字体
     @param pFontFile 字体文件名
     @param size 字体大小
     @param style 字体样式
     @return 炫彩字体句柄
     */
    public static native int XFont_CreateFromFile (String pFontFile, int size, int style);
    /**
     从内存创建炫彩字体
     @param data 内存地址
     @param length 内存长度
     @param fontSize 字体大小
     @param style 字体样式
     @return 炫彩字体句柄
     */
    public static native int XFont_CreateFromMem (byte [] data, long length, int fontSize, int style);
    /**
     从资源创建炫彩字体
     @param id 资源 ID
     @param pType 资源类型
     @param fontSize 字体大小
     @param style 字体样式
     @param hModule 模块句柄
     @return 炫彩字体句柄
     */
    public static native int XFont_CreateFromRes (int id, String pType, int fontSize, int style, long hModule);
    /**
     从 ZIP 文件创建炫彩字体
     @param pZipFileName ZIP 文件名
     @param pFileName 字体文件名
     @param pPassword ZIP 密码
     @param fontSize 字体大小
     @param style 字体样式
     @return 炫彩字体句柄
     */
    public static native int XFont_CreateFromZip (String pZipFileName, String pFileName, String pPassword, int fontSize, int style);
    /**
     从内存 ZIP 创建炫彩字体
     @param data 内存块指针
     @param length 内存块大小
     @param pFileName 字体文件名
     @param pPassword ZIP 密码
     @param fontSize 字体大小
     @param style 字体样式
     @return 炫彩字体句柄
     */
    public static native int XFont_CreateFromZipMem (byte [] data, int length, String pFileName, String pPassword, int fontSize, int style);

    /**
     启用或关闭字体自动销毁功能
     当字体句柄与元素关联后，会自动释放
     @param hFontX 炫彩字体句柄
     @param bEnable 是否启用自动销毁：true - 启用，false - 关闭
     **/
    public static native void XFont_EnableAutoDestroy (int hFontX, boolean bEnable);
    /**
     设置字体的下划线和删除线样式（仅用于编辑框字体）
     因为编辑框不直接支持下划线字体，需通过此接口单独设置
     @param hFontX 炫彩字体句柄
     @param bUnderline 是否启用下划线：true - 启用，false - 关闭
     @param bStrikeout 是否启用删除线：true - 启用，false - 关闭
     **/
    public static native void XFont_SetUnderlineEdit (int hFontX, boolean bUnderline, boolean bStrikeout);
    /**
     获取字体的下划线和删除线设置（仅用于编辑框字体）
     @param hFontX 炫彩字体句柄
     @param bUnderline 输出参数：接收是否启用下划线，索引 0 为 1 表示 true，0 表示 false
     @param bStrikeout 输出参数：接收是否启用删除线，索引 0 为 1 表示 true，0 表示 false
     **/
    public static native void XFont_GetUnderlineEdit (int hFontX, byte [] bUnderline, byte [] bStrikeout);
    /**
     强制销毁炫彩字体
     谨慎使用，建议使用 XFont_Release () 释放
     @param hFontX 炫彩字体句柄
     **/
    public static native void XFont_Destroy (int hFontX);
    /**
     增加炫彩字体的引用计数
     @param hFontX 炫彩字体句柄
     **/
    public static native void XFont_AddRef (int hFontX);
    /**
     获取炫彩字体的当前引用计数
     @param hFontX 炫彩字体句柄
     @return 当前引用计数
     **/
    public static native int XFont_GetRefCount (int hFontX);
    /**
     释放炫彩字体的引用计数
     当引用计数为 0 时，自动销毁字体
     @param hFontX 炫彩字体句柄
     **/
    public static native void XFont_Release (int hFontX);
    /**
     从文件加载图片源
     @param pFileName 图片文件路径
     @return 图片源句柄，失败返回 0
     **/
    public static native int XImgSrc_LoadFile (String pFileName);
    /**
     从文件加载图片源的指定区域
     @param pFileName 图片文件路径
     @param x 区域左上角 X 坐标
     @param y 区域左上角 Y 坐标
     @param cx 区域宽度
     @param cy 区域高度
     @return 图片源句柄，失败返回 0
     **/
    public static native int XImgSrc_LoadFileRect (String pFileName, int x, int y, int cx, int cy);
    /**
     从资源加载图片源
     @param id 资源 ID
     @param pType 资源类型（如 rc 文件中定义的类型）
     @param hModule 模块句柄，从指定模块加载；如果为 0，从当前 EXE 加载
     @return 图片源句柄，失败返回 0
     **/
    public static native int XImgSrc_LoadRes (int id, String pType, long hModule);
    /**
     从 ZIP 压缩包加载图片源
     @param pZipFileName ZIP 压缩包文件路径
     @param pFileName ZIP 内的图片文件名
     @param pPassword ZIP 压缩包密码（可为 null 或空字符串）
     @return 图片源句柄，失败返回 0
     **/
    public static native int XImgSrc_LoadZip (String pZipFileName, String pFileName, String pPassword);
    /**
     从 ZIP 压缩包加载图片源的指定区域
     @param pZipFileName ZIP 压缩包文件路径
     @param pFileName ZIP 内的图片文件名
     @param pPassword ZIP 压缩包密码（可为 null 或空字符串）
     @param x 区域左上角 X 坐标
     @param y 区域左上角 Y 坐标
     @param cx 区域宽度
     @param cy 区域高度
     @return 图片源句柄，失败返回 0
     **/
    public static native int XImgSrc_LoadZipRect (String pZipFileName, String pFileName, String pPassword, int x, int y, int cx, int cy);
    /**
     从内存中的 ZIP 压缩包加载图片源
     @param data ZIP 压缩包的内存数据
     @param length 内存数据的大小（字节）
     @param pFileName ZIP 内的图片文件名
     @param pPassword ZIP 压缩包密码（可为 null 或空字符串）
     @return 图片源句柄，失败返回 0
     **/
    public static native int XImgSrc_LoadZipMem (byte [] data, int length, String pFileName, String pPassword);

    /**
     图片源_加载从内存
     @param pBuffer 图片缓冲区
     @param nSize 图片缓冲区大小
     @return 返回图片源句柄
     */
    public static native int XImgSrc_LoadMemory (byte [] pBuffer, int nSize);
    /**
     图片源_加载从内存指定区域
     @param pBuffer 图片缓冲区
     @param nSize 图片缓冲区大小
     @param x 区域 x 坐标
     @param y 区域 y 坐标
     @param cx 区域宽度
     @param cy 区域高度
     @return 返回图片源句柄
     */
    public static native int XImgSrc_LoadMemoryRect (byte [] pBuffer, int nSize, int x, int y, int cx, int cy);
    /**
     图片源_加载从 Image 对象（long 型）
     @param pImage GDI+ Image 对象指针
     @return 返回图片源句柄
     */
    public static native int XImgSrc_LoadFromImage (long pImage);
    /**
     图片源_加载从 Image 对象（int 型）
     @param pImage GDI+ Image 对象指针
     @return 返回图片源句柄
     */
    public static native int XImgSrc_LoadFromImage (int pImage);
    /**
     图片源_加载文件图标
     @param pFileName 文件名（EXE/DLL/ 图标文件）
     @return 返回图片源句柄
     */
    public static native int XImgSrc_LoadFromExtractIcon (String pFileName);
    /**
     图片源_加载从 HICON
     @param hIcon 图标句柄
     @return 返回图片源句柄
     */
    public static native int XImgSrc_LoadFromHICON (int hIcon);
    /**
     图片源_加载从 HBITMAP
     @param hBitmap 位图句柄
     @return 返回图片源句柄
     */
    public static native int XImgSrc_LoadFromHBITMAP (int hBitmap);
    /**
     图片源_启用自动销毁
     @param hImage 图片源句柄
     @param bEnable 是否启用自动销毁
     */
    public static native void XImgSrc_EnableAutoDestroy (int hImage, boolean bEnable);
    /**
     图片源_取宽度
     @param hImage 图片源句柄
     @return 返回图片宽度
     */
    public static native int XImgSrc_GetWidth (int hImage);
    /**
     图片源_取高度
     @param hImage 图片源句柄
     @return 返回图片高度
     */
    public static native int XImgSrc_GetHeight (int hImage);
    /**
     图片源_取文件名
     @param hImage 图片源句柄
     @param dwSize 接收文件名的缓冲区大小
     @return 返回图片文件名
     */
    public static native byte [] XImgSrc_GetFile (int hImage, int dwSize);
    /**
     图片源_增加引用计数
     @param hImage 图片源句柄
     */
    public static native void XImgSrc_AddRef (int hImage);
    /**
     图片源_释放引用计数
     @param hImage 图片源句柄
     */
    public static native void XImgSrc_Release (int hImage);
    /**
     图片源_取引用计数
     @param hImage 图片源句柄
     @return 返回引用计数
     */
    public static native int XImgSrc_GetRefCount (int hImage);
    /**
     图片源_销毁
     @param hImage 图片源句柄
     */
    public static native void XImgSrc_Destroy (int hImage);
    /**
     图片_加载从图片源
     @param hImageSrc 图片源句柄
     @return 返回图片句柄
     */
    public static native int XImage_LoadSrc (int hImageSrc);
    /**
     图片_加载从文件
     @param pFileName 图片文件名
     @return 返回图片句柄
     */
    public static native int XImage_LoadFile (String pFileName);

    /**
     加载图片从文件，自适应图片
     @param pFileName 图片文件
     @param leftSize 坐标
     @param topSize 坐标
     @param rightSize 坐标
     @param bottomSize 坐标
     @return 返回图片句柄
     */
    public static native int XImage_LoadFileAdaptive (String pFileName, int leftSize, int topSize, int rightSize, int bottomSize);
    /**
     加载图片，指定区位置及大小
     @param pFileName 图片文件
     @param x 坐标
     @param y 坐标
     @param cx 宽度
     @param cy 高度
     @return 返回图片句柄
     */
    public static native int XImage_LoadFileRect (String pFileName, int x, int y, int cx, int cy);
    /**
     加载图片从资源，自适应图片
     @param id 资源 ID
     @param pType 资源类型
     @param leftSize 坐标
     @param topSize 坐标
     @param rightSize 坐标
     @param bottomSize 坐标
     @param hModule 从指定模块加载，如果为空，从当前 EXE 加载
     @return 返回图片句柄
     */
    public static native int XImage_LoadResAdaptive (int id, String pType, int leftSize, int topSize, int rightSize, int bottomSize, long hModule);
    /**
     加载图片从资源
     @param id 资源 ID
     @param pType 资源类型
     @param hModule 从指定模块加载，如果为空，从当前 EXE 加载
     @return 返回图片句柄
     */
    public static native int XImage_LoadRes (int id, String pType, long hModule);
    /**
     加载图片从 ZIP 压缩包
     @param pZipFileName ZIP 压缩包文件名
     @param pFileName 图片文件名
     @param pPassword ZIP 压缩包密码
     @return 返回图片句柄
     */
    public static native int XImage_LoadZip (String pZipFileName, String pFileName, String pPassword);
    /**
     加载图片从 ZIP 压缩包，自适应图片
     @param pZipFileName ZIP 压缩包文件名
     @param pFileName 图片文件名
     @param pPassword ZIP 压缩包密码
     @param x1 坐标
     @param x2 坐标
     @param y1 坐标
     @param y2 坐标
     @return 返回图片句柄
     */
    public static native int XImage_LoadZipAdaptive (String pZipFileName, String pFileName, String pPassword, int x1, int x2, int y1, int y2);
    /**
     加载 ZIP 图片，指定区位置及大小
     @param pZipFileName ZIP 文件
     @param pFileName 图片名称
     @param pPassword 密码
     @param x 坐标
     @param y 坐标
     @param cx 宽度
     @param cy 高度
     @return 返回图片句柄
     */
    public static native int XImage_LoadZipRect (String pZipFileName, String pFileName, String pPassword, int x, int y, int cx, int cy);
    /**
     加载图片从内存 ZIP 压缩包
     @param data 内存块指针
     @param length 内存块大小，字节为单位
     @param pFileName 图片名称
     @param pPassword zip 压缩包密码
     @return 返回图片句柄
     */
    public static native int XImage_LoadZipMem (byte [] data, int length, String pFileName, String pPassword);
    /**
     加载图片从 RC 资源 zip 压缩包中
     @param id RC 资源 ID
     @param pFileName 图片名称
     @param pPassword zip 压缩包密码
     @param hModule 模块句柄
     @return 返回图片句柄
     */
    public static native int XImage_LoadZipRes (int id, String pFileName, String pPassword, long hModule);

    /**
     从内存加载流图片
     @param pBuffer 图片缓冲区字节数组
     @param nSize 图片缓冲区大小
     @return 返回图片句柄
     **/
    public static native int XImage_LoadMemory (byte [] pBuffer, int nSize);
    /**
     从内存加载流图片并指定区域位置及大小
     @param pBuffer 图片缓冲区字节数组
     @param nSize 图片缓冲区大小
     @param x 指定区域的 x 坐标
     @param y 指定区域的 y 坐标
     @param cx 指定区域的宽度
     @param cy 指定区域的高度
     @return 返回图片句柄
     **/
    public static native int XImage_LoadMemoryRect (byte [] pBuffer, int nSize, int x, int y, int cx, int cy);
    /**
     从内存加载流图片并设置自适应（九宫格）
     @param pBuffer 图片缓冲区字节数组
     @param nSize 图片缓冲区大小
     @param leftSize 九宫格左边界大小
     @param topSize 九宫格上边界大小
     @param rightSize 九宫格右边界大小
     @param bottomSize 九宫格下边界大小
     @return 返回图片句柄
     **/
    public static native int XImage_LoadMemoryAdaptive (byte [] pBuffer, int nSize, int leftSize, int topSize, int rightSize, int bottomSize);
    /**
     从 GDI + 的 Image 对象加载图片（64 位）
     @param pImage GDI + 的 Image 对象指针
     @return 返回图片句柄
     **/
    public static native int XImage_LoadFromImage (long pImage);
    /**
     从 GDI + 的 Image 对象加载图片（32 位）
     @param pImage GDI + 的 Image 对象指针
     @return 返回图片句柄
     **/
    public static native int XImage_LoadFromImage (int pImage);
    /**
     从 EXE/DLL/ 图标文件加载文件图标
     @param pFileName 文件名
     @return 返回图片句柄
     **/
    public static native int XImage_LoadFromExtractIcon (String pFileName);
    /**
     从现有的 HICON 句柄创建炫彩图片句柄
     @param hIcon HICON 图标句柄
     @return 返回图片句柄
     **/
    public static native int XImage_LoadFromHICON (int hIcon);
    /**
     从现有的 HBITMAP 句柄创建炫彩图片句柄
     @param hBitmap HBITMAP 位图句柄
     @return 返回图片句柄
     **/
    public static native int XImage_LoadFromHBITMAP (int hBitmap);
    /**
     判断是否为拉伸图片句柄
     @param hImage 图片句柄
     @return 如果是拉伸图片返回 true，否则返回 false
     **/
    public static native boolean XImage_IsStretch (int hImage);
    /**
     判断是否为自适应（九宫格）图片句柄
     @param hImage 图片句柄
     @return 如果是自适应图片返回 true，否则返回 false
     **/
    public static native boolean XImage_IsAdaptive (int hImage);

    /**
     判断图片是否为平铺图片
     @param hImage 图片句柄.
     @return 是否平铺
     */
    public static native boolean XImage_IsTile (int hImage);
    /**
     从 SVG 句柄加载图片
     @param hSvg SVG 句柄
     @return 返回图片句柄
     */
    public static native int XImage_LoadSvg (int hSvg);
    /**
     从 SVG 文件加载图片
     @param pFileName 文件名
     @return 图片句柄
     */
    public static native int XImage_LoadSvgFile (String pFileName);
    /**
     从多字节字符串 ANSI 加载 SVG
     @param pString 字符串指针
     @return 返回图片句柄
     */
    public static native int XImage_LoadSvgString (String pString);
    /**
     从 UNICODE 字符串加载 SVG
     @param pString 字符串指针
     @return 返回图片句柄
     */
    public static native int XImage_LoadSvgStringW (String pString);
    /**
     从 UTF8 字符串加载 SVG
     @param pString 字符串指针
     @return 返回图片句柄
     */
    public static native int XImage_LoadSvgStringUtf8 (String pString);
    /**
     获取 SVG 句柄
     @param hImage 图片句柄
     @return 返回 SVG 句柄
     */
    public static native int XImage_GetSvg (int hImage);
    /**
     设置图片绘制类型
     @param hImage 图片句柄.
     @param nType 图片绘制类型.
     @return 如果成功返回 TRUE, 否则相反.
     */
    public static native boolean XImage_SetDrawType (int hImage, int nType);
    /**
     设置图片自适应 (九宫格)
     @param hImage 图片句柄.
     @param leftSize 坐标.
     @param topSize 坐标.
     @param rightSize 坐标.
     @param bottomSize 坐标.
     @return 如果成功返回 TRUE, 否则相反.
     */
    public static native boolean XImage_SetDrawTypeAdaptive (int hImage, int leftSize, int topSize, int rightSize, int bottomSize);
    /**
     指定图片透明颜色。仅支持 GDI + 模式
     @param hImage 图片句柄.
     @param color 颜色值，请使用宏: RGBA ()
     */
    public static native void XImage_SetTranColor (int hImage, long color);
    /**
     指定图片透明颜色及透明度。仅支持 GDI + 模式
     @param hImage 图片句柄.
     @param color 颜色值，请使用宏: RGBA ()
     @param tranColor 透明色的透明度.
     */
    public static native void XImage_SetTranColorEx (int hImage, long color, byte tranColor);
    /**
     设置旋转角度.
     @param hImage 图片句柄.
     @param fAngle 选择角度.
     @return 返回先前角度.
     */
    public static native float XImage_SetRotateAngle (int hImage, float fAngle);
    /**
     设置图片等分数和索引
     @param hImage 图片句柄
     @param nCount 等分数量
     @param iIndex 索引
     */
    public static native void XImage_SetSplitEqual (int hImage, int nCount, int iIndex);
    /**
     启用缩放属性后有效，值大于 0 有效
     @param hImage 图片句柄
     @param width 宽度
     @param height 高度
     */
    public static native void XImage_SetScaleSize (int hImage, int width, int height);
    /**
     启用或关闭图片透明色。仅支持 GDI + 模式
     @param hImage 图片句柄.
     @param bEnable 启用 TRUE, 关闭 FALSE.
     */
    public static native void XImage_EnableTranColor (int hImage, boolean bEnable);
    /**
     启用或关闭自动销毁，当与 UI 元素关联时有效
     @param hImage 图片句柄.
     @param bEnable 启用自动销毁 TRUE, 关闭自动销毁 FALSE.
     */
    public static native void XImage_EnableAutoDestroy (int hImage, boolean bEnable);
    /**
     启用或关闭图片居中显示，默认属性图片有效。
     @param hImage 图片句柄.
     @param bCenter 是否居中显示.
     */
    public static native void XImage_EnableCenter (int hImage, boolean bCenter);

    /**
     判断图片是否居中显示
     @param hImage 图片句柄
     @return 如果居中显示返回 TRUE，否则返回 FALSE
     **/
    public static native boolean XImage_IsCenter (int hImage);
    /**
     获取图片绘制类型
     @param hImage 图片句柄
     @return 图片绘制类型
     **/
    public static native int XImage_GetDrawType (int hImage);
    /**
     获取图片宽度
     @param hImage 图片句柄
     @return 图片宽度
     **/
    public static native int XImage_GetWidth (int hImage);
    /**
     获取图片高度
     @param hImage 图片句柄
     @return 图片高度
     **/
    public static native int XImage_GetHeight (int hImage);
    /**
     获取图片源句柄
     @param hImage 图片句柄
     @return 图片源句柄
     **/
    public static native int XImage_GetImageSrc (int hImage);
    /**
     增加图片引用计数
     @param hImage 图片句柄
     **/
    public static native void XImage_AddRef (int hImage);
    /**
     释放图片引用计数，当引用计数为 0 时自动销毁
     @param hImage 图片句柄
     **/
    public static native void XImage_Release (int hImage);
    /**
     获取图片引用计数
     @param hImage 图片句柄
     @return 引用计数
     **/
    public static native int XImage_GetRefCount (int hImage);
    /**
     强制销毁图片，谨慎使用，建议使用 XImage_Release () 释放
     @param hImage 图片句柄
     **/
    public static native void XImage_Destroy (int hImage);
    /**
     将动画加入动画系统中运行
     @param hAnimationEx 动画序列或动画组句柄
     @param hRedrawObjectUI 当更新 UI 时重绘的 UI 层对象句柄（窗口句柄、元素句柄、形状句柄、SVG 句柄）
     **/
    public static native void XAnima_Run (int hAnimationEx, int hRedrawObjectUI);
    /**
     将动画从动画系统中移除，并自动销毁（如果启用自动销毁）
     @param hAnimationEx 动画序列或动画组句柄
     @param bEnd 是否立即执行到终点
     @return 操作是否成功
     **/
    public static native boolean XAnima_Release (int hAnimationEx, boolean bEnd);
    /**
     从动画系统中移除与指定 UI 对象关联的所有动画，并自动销毁（如果启用自动销毁）
     @param hObjectUI 指定 UI 对象句柄
     @param bEnd 是否立即执行到终点
     @return 释放的动画数量
     **/
    public static native int XAnima_ReleaseEx (int hObjectUI, boolean bEnd);
    /**
     创建按顺序执行的动画列表
     @param hObjectUI 绑定的 UI 对象句柄（窗口句柄、元素句柄、形状句柄、SVG 句柄）
     @param nLoopCount 动画循环次数，0 为无限循环
     @return 动画序列句柄
     **/
    public static native int XAnima_Create (int hObjectUI, int nLoopCount);
    /**
     移动到目标位置，默认以 UI 对象中心点为操作方式，避免出现坐标错位
     @param hSequence 动画序列句柄
     @param duration 持续时间
     @param x 终点位置 X（对象左上角坐标）
     @param y 终点位置 Y（对象左上角坐标）
     @param nLoopCount 动画循环次数，0 为无限循环
     @param ease_flag 缓动标识
     @param bGoBack 是否返回；当启用后：往返到起点（起点 终点 起点）
     @return 动画项句柄
     **/
    public static native int XAnima_Move (int hSequence, long duration, float x, float y, int nLoopCount, int ease_flag, boolean bGoBack);
    /**
     从指定位置移动到目标位置，默认以 UI 对象中心点为操作方式，避免出现坐标错位
     @param hSequence 动画序列句柄
     @param duration 持续时间
     @param from_x 起点位置 X（对象左上角坐标）
     @param from_y 起点位置 Y（对象左上角坐标）
     @param to_x 终点位置 X（对象左上角坐标）
     @param to_y 终点位置 Y（对象左上角坐标）
     @param nLoopCount 动画循环次数，0 为无限循环
     @param ease_flag 缓动标识
     @param bGoBack 是否返回；当启用后：往返到起点（起点 终点 起点）
     @return 动画项句柄
     **/
    public static native int XAnima_MoveEx (int hSequence, long duration, float from_x, float from_y, float to_x, float to_y, int nLoopCount, int ease_flag, boolean bGoBack);
    /**
     创建旋转动画项，旋转角度支持负数值以控制反向旋转
     @param hSequence 动画序列句柄
     @param duration 持续时间，单位毫秒
     @param angle 旋转角度（度），负值可控制反向旋转
     @param nLoopCount 动画循环次数，0 表示无限循环
     @param ease_flag 缓动标识，参考 ease_flag_枚举定义
     @param bGoBack 是否返回起点，启用后将往返运动：起点 终点 起点
     @return 返回动画项句柄
     */
    public static native int XAnima_Rotate (int hSequence, long duration, float angle, int nLoopCount, int ease_flag, boolean bGoBack);
    /**
     创建旋转动画项，指定起始和结束角度
     @param hSequence 动画序列句柄
     @param duration 持续时间，单位毫秒
     @param from 起始旋转角度（度）
     @param to 结束旋转角度（度）
     @param nLoopCount 动画循环次数，0 表示无限循环
     @param ease_flag 缓动标识，参考 ease_flag_枚举定义
     @param bGoBack 是否返回起点，启用后将往返运动：起点 终点 起点
     @return 返回动画项句柄
     */
    public static native int XAnima_RotateEx (int hSequence, long duration, float from, float to, int nLoopCount, int ease_flag, boolean bGoBack);
    /**
     创建缩放动画项，默认以 UI 对象中心进行缩放
     @param hSequence 动画序列句柄
     @param duration 持续时间，单位毫秒
     @param scaleX X 轴缩放比例
     @param scaleY Y 轴缩放比例
     @param nLoopCount 动画循环次数，0 表示无限循环
     @param ease_flag 缓动标识，参考 ease_flag_枚举定义
     @param bGoBack 是否返回起点，启用后将往返运动：起点 终点  起点
     @return 返回动画项句柄
     */
    public static native int XAnima_Scale (int hSequence, long duration, float scaleX, float scaleY, int nLoopCount, int ease_flag, boolean bGoBack);
    /**
     创建缩放大小动画项，修改 UI 对象的实际大小，默认向右延伸
     @param hSequence 动画序列句柄
     @param duration 持续时间，单位毫秒
     @param width 目标宽度
     @param height 目标高度
     @param nLoopCount 动画循环次数，0 表示无限循环
     @param ease_flag 缓动标识，参考 ease_flag_枚举定义
     @param bGoBack 是否返回起点，启用后将往返运动：起点 终点  起点
     @return 返回动画项句柄
     */
    public static native int XAnima_ScaleSize (int hSequence, long duration, float width, float height, int nLoopCount, int ease_flag, boolean bGoBack);
    /**
     设置动画序列的透明度变化
     @param hSequence 动画序列句柄
     @param duration 持续时间（毫秒）
     @param alpha 目标透明度（0-255）
     @param nLoopCount 动画循环次数，0 表示无限循环
     @param ease_flag 缓动效果标识，用于控制动画的速度变化
     @param bGoBack 是否在动画结束后返回起始状态（往返效果）
     @return 返回动画项句柄
     **/
    public static native int XAnima_Alpha (int hSequence, long duration, byte alpha, int nLoopCount, int ease_flag, boolean bGoBack);
    /**
     设置动画序列的透明度变化（扩展版，支持指定起始和结束透明度）
     @param hSequence 动画序列句柄
     @param duration 持续时间（毫秒）
     @param from_alpha 起始透明度（0-255）
     @param to_alpha 目标透明度（0-255）
     @param nLoopCount 动画循环次数，0 表示无限循环
     @param ease_flag 缓动效果标识，用于控制动画的速度变化
     @param bGoBack 是否在动画结束后返回起始状态（往返效果）
     @return 返回动画项句柄
     **/
    public static native int XAnima_AlphaEx (int hSequence, long duration, byte from_alpha, byte to_alpha, int nLoopCount, int ease_flag, boolean bGoBack);
    /**
     设置动画序列的颜色变化
     @param hSequence 动画序列句柄
     @param duration 持续时间（毫秒）
     @param color 目标颜色（格式：RGBA，例如：0xFFFFFFFF 表示白色）
     @param nLoopCount 动画循环次数，0 表示无限循环
     @param ease_flag 缓动效果标识，用于控制动画的速度变化
     @param bGoBack 是否在动画结束后返回起始状态（往返效果）
     @return 返回动画项句柄
     **/
    public static native int XAnima_Color (int hSequence, long duration, long color, int nLoopCount, int ease_flag, boolean bGoBack);
    /**
     设置动画序列的颜色变化（扩展版，支持指定起始和结束颜色）
     @param hSequence 动画序列句柄
     @param duration 持续时间（毫秒）
     @param from 起始颜色（格式：RGBA）
     @param to 目标颜色（格式：RGBA）
     @param nLoopCount 动画循环次数，0 表示无限循环
     @param ease_flag 缓动效果标识，用于控制动画的速度变化
     @param bGoBack 是否在动画结束后返回起始状态（往返效果）
     @return 返回动画项句柄
     **/
    public static native int XAnima_ColorEx (int hSequence, long duration, long from, long to, int nLoopCount, int ease_flag, boolean bGoBack);
    /**
     修改布局宽度属性的动画
     @param hSequence 动画序列句柄
     @param duration 持续时间
     @param nType 布局宽度类型（对应 layout_size_枚举）
     @param width 布局宽度
     @param nLoopCount 动画循环次数（0 表示无限循环）
     @param ease_flag 缓动标识（对应 ease_flag_枚举）
     @param bGoBack 是否返回（启用后往返到起点：起点  终点  起点）
     @return 返回动画项句柄
     **/
    public static native int XAnima_LayoutWidth (int hSequence, long duration, int nType, float width, int nLoopCount, int ease_flag, boolean bGoBack);
    /**
     修改布局高度属性的动画
     @param hSequence 动画序列句柄
     @param duration 持续时间
     @param nType 布局高度类型（对应 layout_size_枚举）
     @param height 布局高度
     @param nLoopCount 动画循环次数（0 表示无限循环）
     @param ease_flag 缓动标识（对应 ease_flag_枚举）
     @param bGoBack 是否返回（启用后往返到起点：起点  终点  起点）
     @return 返回动画项句柄
     **/
    public static native int XAnima_LayoutHeight (int hSequence, long duration, int nType, float height, int nLoopCount, int ease_flag, boolean bGoBack);
    /**
     修改布局宽度和高度的动画
     @param hSequence 动画序列句柄
     @param duration 持续时间
     @param nWidthType 布局宽度类型（对应 layout_size_枚举；layout_size_disable 表示禁用宽度动画）
     @param width 布局宽度
     @param nHeightType 布局高度类型（对应 layout_size_枚举；layout_size_disable 表示禁用高度动画）
     @param height 布局高度
     @param nLoopCount 动画循环次数（0 表示无限循环）
     @param ease_flag 缓动标识（对应 ease_flag_枚举）
     @param bGoBack 是否返回（启用后往返到起点：起点  终点  起点）
     @return 返回动画项句柄
     **/
    public static native int XAnima_LayoutSize (int hSequence, long duration, int nWidthType, float width, int nHeightType, float height, int nLoopCount, int ease_flag, boolean bGoBack);
    /**
     动画延迟
     @param hSequence 动画序列句柄
     @param duration 延迟持续时间
     @return 返回动画项句柄
     **/
    public static native int XAnima_Delay (int hSequence, float duration);

    /**
     创建一个延迟动画项，可以作为空动画在回调里处理自定义算法
     @param hSequence 动画序列句柄
     @param duration 持续时间
     @param nLoopCount 动画循环次数，0 表示无限循环
     @param ease_flag 缓动标识，参考 ease_flag_枚举
     @param bGoBack 是否返回：启用后将往返到起点（起点  终点  起点）
     @return 返回动画项句柄
     */
    public static native int XAnima_DelayEx (int hSequence, float duration, int nLoopCount, int ease_flag, boolean bGoBack);
    /**
     显示或隐藏 UI 对象的动画
     @param hSequence 动画序列句柄
     @param duration 持续时间
     @param bShow 是否显示 UI 对象
     @return 返回动画项句柄
     */
    public static native int XAnima_Show (int hSequence, float duration, boolean bShow);
    /**
     销毁 UI 对象的动画
     @param hSequence 动画序列句柄
     @param duration 持续时间
     @return 返回动画项句柄
     */
    public static native int XAnima_DestroyObjectUI (int hSequence, float duration);
    /**
     设置动画是否自动销毁
     @param hAnimationEx 动画序列或动画组句柄
     @param bEnable 是否启用自动销毁（TRUE：自动销毁，FALSE：手动销毁）
     */
    public static native void XAnima_EnableAutoDestroy (int hAnimationEx, boolean bEnable);
    /**
     获取动画关联的 UI 对象
     @param hAnimationEx 动画序列、动画组或动画项句柄
     @return 返回 UI 对象句柄
     */
    public static native int XAnima_GetObjectUI (int hAnimationEx);

    /**
     设置动画序列或动画组的回调函数
     @param hAnimationEx 动画序列或动画组句柄
     @param tagPFunAnimation 回调函数对象
     */
    public static void XAnima_SetCallback_(int hAnimationEx,TagPFunAnimation tagPFunAnimation){
        XAnima_SetCallback(hAnimationEx,OverrideMethodUtils.getOverrideMethodNamesStr(tagPFunAnimation.getClass()),tagPFunAnimation);
    }
    /**
     设置动画序列或动画组的回调函数
     @param hAnimationEx 动画序列或动画组句柄
     @param TagFunAnimationName 回调函数名称
     @param TagPFunAnimation 回调函数对象
     */
    public static native void XAnima_SetCallback(int hAnimationEx,String TagFunAnimationName,TagPFunAnimation TagPFunAnimation);
    /**
     设置动画序列或动画组的回调函数
     @param hAnimationEx 动画序列或动画组句柄
     @param tagPFunAnimation 回调函数对象
     */
    public static void XAnima_SetCallback_(int hAnimationEx,TagPFunAnimation_Nonvirtual tagPFunAnimation){
        XAnima_SetCallback(hAnimationEx,OverrideMethodUtils.getOverrideMethodNamesStr(tagPFunAnimation.getClass()),tagPFunAnimation);
    }
    /**
     设置动画序列或动画组的回调函数
     @param hAnimationEx 动画序列或动画组句柄
     @param TagFunAnimationName 回调函数名称
     @param TagPFunAnimation 回调函数对象
     */
    public static native void XAnima_SetCallback (int hAnimationEx, String TagFunAnimationName, TagPFunAnimation_Nonvirtual TagPFunAnimation);
    /**
     设置动画序列或动画组的用户数据
     @param hAnimationEx 动画序列或动画组句柄
     @param nUserData 用户数据字节数组
     **/
    public static native void XAnima_SetUserData (int hAnimationEx, byte [] nUserData);
    /**
     获取动画序列或动画组的用户数据
     @param hAnimationEx 动画序列或动画组句柄
     @param dwSize 要获取的用户数据大小
     @return 用户数据字节数组
     **/
    public static native byte [] XAnima_GetUserData (int hAnimationEx, int dwSize);
    /**
     停止动画序列或动画组
     @param hAnimationEx 动画序列或动画组句柄
     **/
    public static native void XAnima_Stop (int hAnimationEx);
    /**
     开始动画序列或动画组
     @param hAnimationEx 动画序列或动画组句柄
     **/
    public static native void XAnima_Start (int hAnimationEx);
    /**
     暂停动画序列或动画组（未实现功能）
     @param hAnimationEx 动画序列或动画组句柄
     **/
    public static native void XAnima_Pause (int hAnimationEx);

    /**
     创建动画同步组，当组中动画序列全部完成后重新开始；当遇到无限循环项时，直至其他序列完成后终止循环，避免无法到达终点
     @param nLoopCount 动画循环次数，0 表示无限循环
     @return 返回动画组句柄
     */
    public static native int XAnimaGroup_Create (int nLoopCount);
    /**
     将动画序列添加到动画组中
     @param hGroup 动画组句柄
     @param hSequence 动画序列句柄
     */
    public static native void XAnimaGroup_AddItem (int hGroup, int hSequence);
    /**
     启用或关闭动画项的自动销毁功能
     @param hAnimationItem 动画项句柄
     @param bEnable 是否启用自动销毁，true 为启用，false 为关闭
     */
    public static native void XAnimaItem_EnableAutoDestroy (int hAnimationItem, boolean bEnable);
    /**
     启用或关闭动画项完成后的自动释放功能，例如用于多个动画序列的渐近式延迟
     @param hAnimationItem 动画项句柄
     @param bEnable 是否启用完成释放，true 为启用，false 为关闭
     */
    public static native void XAnimaItem_EnableCompleteRelease (int hAnimationItem, boolean bEnable);
    /**
     动画项_置回调
     @param hAnimationItem 动画项句柄
     @param tagFunAnimationItem 回调函数实例
     **/
    public static void XAnimaItem_SetCallback_(int hAnimationItem,TagFunAnimationItem tagFunAnimationItem){
        XAnimaItem_SetCallback(hAnimationItem,OverrideMethodUtils.getOverrideMethodNamesStr(tagFunAnimationItem.getClass()),tagFunAnimationItem);
    }
    /**
     动画项_置回调
     @param hAnimationItem 动画项句柄
     @param TagFunAnimationItemName 回调函数名称标记
     @param TagFunAnimationItem 回调函数实例
     **/
    public static native void XAnimaItem_SetCallback (int hAnimationItem, String TagFunAnimationItemName, TagFunAnimationItem TagFunAnimationItem); /**
     设置动画项的回调函数
     @param hAnimationItem 动画项句柄
     @param tagFunAnimationItem 回调函数实例
     **/
    public static void XAnimaItem_SetCallback_(int hAnimationItem,TagFunAnimationItem_Nonvirtual tagFunAnimationItem){
        XAnimaItem_SetCallback(hAnimationItem,OverrideMethodUtils.getOverrideMethodNamesStr(tagFunAnimationItem.getClass()),tagFunAnimationItem);
    }
    /**
     设置动画项的回调函数
     @param hAnimationItem 动画项句柄
     @param TagFunAnimationItemName 回调函数名称
     @param TagFunAnimationItem 回调函数实例
     **/
    public static native void XAnimaItem_SetCallback (int hAnimationItem,String TagFunAnimationItemName ,TagFunAnimationItem_Nonvirtual TagFunAnimationItem);
    /**
     设置动画项的用户数据
     @param hAnimationItem 动画项句柄
     @param nUserData 用户数据的字节数组
     **/
    public static native void XAnimaItem_SetUserData (int hAnimationItem, byte [] nUserData);
    /**
     获取动画项的用户数据
     @param hAnimationItem 动画项句柄
     @param dwSize 要获取的用户数据大小
     @return 用户数据的字节数组
     **/
    public static native byte [] XAnimaItem_GetUserData (int hAnimationItem, int dwSize);
    /**
     设置旋转动画的中心点坐标
     @param hAnimationRotate 旋转动画项句柄
     @param x 中心点 X 坐标
     @param y 中心点 Y 坐标
     @param bOffset 是否相对于自身中心点偏移（TRUE 为偏移，FALSE 为绝对坐标）
     **/
    public static native void XAnimaRotate_SetCenter (int hAnimationRotate, float x, float y, boolean bOffset);
    /**
     设置缩放动画的延伸位置
     @param hAnimationScale 缩放动画项句柄
     @param position 位置标识（参考 animation_move_枚举）
     **/
    public static native void XAnimaScale_SetPosition (int hAnimationScale, int position);

    /**
     动画移动_置标识
     @param hAnimationMove 动画移动项句柄
     @param flags 移动标识
     */
    public static native void XAnimaMove_SetFlag (int hAnimationMove, int flags);
    /**
     托盘图标_重置
     重置托盘图标数据，仅在未添加到系统托盘状态下可调用
     */
    public static native void XTrayIcon_Reset ();
    /**
     托盘图标_删除
     从系统托盘删除托盘图标
     @return 如果成功返回 true，否则返回 false
     */
    public static native boolean XTrayIcon_Del ();
    /**
     托盘图标_修改
     修改系统托盘上的托盘图标
     @return 如果成功返回 true，否则返回 false
     */
    public static native boolean XTrayIcon_Modify ();
    /**
     托盘图标_置回调消息
     设置用户自定义的回调消息类型，触发托盘事件后系统会发送此消息
     @param user_message 用户自定义消息，界面库默认定义消息为 XWM_TRAYICON
     */
    public static native void XTrayIcon_SetCallbackMessage (long user_message);


    /**
     判断是否为形状对象
     @param hShape 形状对象句柄
     @return 如果是形状对象返回 TRUE，否则返回 FALSE
     **/
    public static native boolean XC_IsShape (int hShape);
    /**
     判断句柄是否拥有该类型
     @param hXCGUI 炫彩句柄
     @param nType 句柄类型
     @return 如果成功返回 TRUE 否则返回 FALSE
     **/
    public static native boolean XC_Isint (int hXCGUI, int nType);
    /**
     通过窗口 HWND 句柄获取 HWINDOW 句柄
     @param hWnd 窗口 HWND 句柄
     @return 返回 HWINDOW 句柄
     **/
    public static native int XC_hWindowFromHWnd (int hWnd);
    /**
     激活当前进程最上层窗口
     @return 成功返回 TRUE，否则返回 FALSE
     **/
    public static native boolean XC_SetActivateTopWindow ();

    /**
     判断元素是否从滚动视图元素扩展的新元素 (包含滚动视图元素本身)
     @param hEle 元素句柄
     @return 如果是滚动视图扩展元素返回 true，否则返回 false
     */
    public static native boolean XC_IsSViewExtend (int hEle);
    /**
     获取炫彩对象句柄的类型
     @param hXCGUI 炫彩对象句柄
     @return 返回对象类型 (@ref XC_OBJECT_TYPE)
     */
    public static native int XC_GetObjectType (int hXCGUI);
    /**
     通过 ID 获取对象句柄 (不包括窗口对象)
     @param hWindow 所属窗口句柄，不属于任何窗口填 0
     @param nID ID 值
     @return 成功返回对象句柄，否则返回 0
     */
    public static native int XC_GetObjectByID (int hWindow, int nID);
    /**
     通过 ID 名称获取对象句柄
     @param hWindow 所属窗口句柄，不属于任何窗口填 0
     @param pName ID 名称
     @return 成功返回对象句柄，否则返回 0
     */
    public static native int XC_GetObjectByIDName (int hWindow, String pName);
    /**
     通过 UID 获取对象句柄 (不包括窗口对象)
     @param nUID UID 值
     @return 成功返回对象句柄，否则返回 0
     */
    public static native int XC_GetObjectByUID (int nUID);

    /**
     炫彩_取对象从 UID 名称
     @param pName UID 名称
     @return 成功返回句柄，否则返回 NULL
     **/
    public static native int XC_GetObjectByUIDName (String pName);
    /**
     炫彩_取对象从名称
     @param pName name 名称
     @return 成功返回句柄，否则返回 NULL
     **/
    public static native int XC_GetObjectByName (String pName);
    /**
     炫彩_置绘制频率
     @param nMilliseconds 重绘最小时间间隔，单位毫秒
     **/
    public static native void XC_SetPaintFrequency (long nMilliseconds);
    /**
     炫彩_置文本炫彩质量
     @param nType 文本渲染质量类型 (参见 GDI+ TextRenderingHint 定义)
     **/
    public static native void XC_SetTextRenderingHint (int nType);
    /**
     炫彩_置 D2D 文本渲染模式
     @param mode 渲染模式 (参见 XC_DWRITE_RENDERING_MODE 枚举)
     **/
    public static native void XC_SetD2dTextRenderingMode (int mode);
    /**
     炫彩_置 D2D 文本抗锯齿模式
     @param mode 抗锯齿模式：0 - 系统默认；1-ClearType 抗锯齿；2 - 灰度抗锯齿；3 - 不抗锯齿
     **/
    public static native void XC_SetD2dTextAntialiasMode (int mode);

    /**
     启用或禁用 GDI 绘制文本，将影响 XDraw_TextOut、XDraw_TextOutEx、XDraw_TextOutA 等函数
     @param bEnable 是否启用 GDI 绘制文本
     **/
    public static native void XC_EnableGdiDrawText (boolean bEnable);
    /**
     启用或禁用 UI 自动重绘，修改 UI 后自动调用重绘函数更新 UI（如 XBtn_SetText 后自动调用 XEle_Redraw）
     @param bEnable 是否启用 UI 自动重绘
     **/
    public static native void XC_EnableAutoRedrawUI (boolean bEnable);
    /**
     启用或禁用错误消息框弹窗，可设置遇到严重错误时是否弹出消息提示框
     @param bEnable 是否启用错误消息框弹窗
     **/
    public static native void XC_EnableErrorMessageBox (boolean bEnable);
    /**
     启用 DPI 支持，可通过项目属性、清单文件、调用此函数或自己调用 DPI 函数等方式启用 DPI
     @param bEnable 是否启用 DPI
     @return 如果启用成功返回 true，否则返回 false
     **/
    public static native boolean XC_EnableDPI (boolean bEnable);

    /**
     启用或禁用自动 DPI 调整
     默认禁用；当启用后，创建窗口时自动检测 DPI 调整 UI 缩放，处理 DPI 改变消息；禁用后，当 DPI 改变，需要手动设置窗口 DPI
     @param bEnable 是否启用自动 DPI 调整
     **/
    public static native void XC_EnableAutoDPI (boolean bEnable);
    /**
     启用或禁用自动退出程序
     当检测到所有用户创建的窗口都关闭时，自动退出程序；可调用 XC_PostQuitMessage () 手动退出程序
     @param bEnable 是否启用自动退出程序
     **/
    public static native void XC_EnableAutoExitApp (boolean bEnable);
    /**
     启用或禁用系统非客户区样式
     当启用后拥有系统非客户区样式，替代模拟非客户区
     @param bEnable 是否启用系统非客户区样式
     **/
    public static native void XC_EnableWindowSysNc (boolean bEnable);
    /**
     判断是否启用了 D2D
     @return 如果启用 D2D 返回 true，否则返回 false
     **/
    public static native boolean XC_IsEnableD2D ();
    /**
     设置默认字体
     @param hFontX 炫彩字体句柄
     **/
    public static native void XC_SetDefaultFont (int hFontX);

    /**
     添加文件搜索路径，默认路径为 exe 目录和程序当前运行目录
     @param pPath 文件夹路径
     */
    public static native void XC_AddFileSearchPath (String pPath);
    /**
     初始化 LOGFONTW 结构体
     @param pFont LOGFONTW 结构体指针
     @param pName 字体名称
     @param size 字体大小
     @param bBold 是否为粗体
     @param bItalic 是否为斜体
     @param bUnderline 是否有下划线
     @param bStrikeOut 是否有删除线
     */
    public static native void XC_InitFont (TagLOGFONTW pFont, byte [] pName, int size, boolean bBold, boolean bItalic, boolean bUnderline, boolean bStrikeOut);
    /**
     炫彩_载入动态库
     @param lpFileName 动态库文件名
     @return 返回动态库模块句柄
     **/
    public static native long XC_LoadLibrary (String lpFileName);
    /**
     炫彩_加载 DLL
     @param pDllFileName DLL 文件名
     @return 返回 DLL 模块句柄
     **/
    public static native long XC_LoadDll (String pDllFileName);
    /**
     炫彩_加载布局文件从字符串
     @param pStringXML 布局文件的字符串指针
     @param hParent 父对象句柄（窗口句柄或 UI 元素句柄）
     @param hAttachWnd 附加到指定的窗口 HWND，如果未指定则忽略
     @return 返回窗口句柄或元素句柄
     **/
    public static native int XC_LoadLayoutFromString (String pStringXML, int hParent, int hAttachWnd);

    /**
     加载布局文件从 UTF8 内存字符串
     @param pStringXML UTF8 格式的布局文件内容字符串
     @param hParent 父对象句柄，可为窗口句柄或 UI 元素句柄
     @param hAttachWnd 要附加到的窗口 HWND 句柄，若未指定则忽略
     @return 成功返回窗口句柄或 UI 元素句柄，失败返回 NULL
     */
    public static native int XC_LoadLayoutFromStringUtf8 (String pStringXML, int hParent, int hAttachWnd);
    /**
     加载布局文件扩展
     @param pFileName 布局文件名
     @param pPrefixName 名称 (name) 前缀，可为空；为布局文件中所有 name 属性增加前缀，最终 name 属性值为：前缀 + name
     @param hParent 父对象句柄，可为窗口句柄或 UI 元素句柄
     @param hParentWnd 父窗口句柄 HWND，提供给第三方窗口使用
     @param hAttachWnd 要附加到的窗口 HWND 句柄，若未指定则忽略
     @return 成功返回窗口句柄或 UI 元素句柄，失败返回 NULL
     */
    public static native int XC_LoadLayoutEx (String pFileName, String pPrefixName, int hParent, int hParentWnd, int hAttachWnd);
    /**
     从 zip 压缩包中加载布局文件扩展
     @param pZipFileName zip 文件名
     @param pFileName zip 压缩包内的布局文件名
     @param pPassword zip 压缩包密码，若无密码则为 NULL
     @param pPrefixName 名称 (name) 前缀，可为空；为布局文件中所有 name 属性增加前缀，最终 name 属性值为：前缀 + name
     @param hParent 父对象句柄，可为窗口句柄或 UI 元素句柄
     @param hParentWnd 父窗口句柄 HWND，提供给第三方窗口使用
     @param hAttachWnd 要附加到的窗口 HWND 句柄，若未指定则忽略
     @return 成功返回窗口句柄或 UI 元素句柄，失败返回 NULL
     */
    public static native int XC_LoadLayoutZipEx (String pZipFileName, String pFileName, String pPassword, String pPrefixName, int hParent, int hParentWnd, int hAttachWnd);
    /**
     从内存 zip 压缩包中加载布局文件
     @param data 内存块指针，布局文件所在的内存压缩包数据
     @param length 内存块大小，字节为单位
     @param pFileName 布局文件名，zip 压缩包中的布局文件名
     @param pPassword zip 压缩包密码，如果没有密码则为 null
     @param pPrefixName 名称 (name) 前缀，可选参数；给当前布局文件中所有 name 属性增加前缀，那么 name 属性值为：前缀 + name
     @param hParent 父对象句柄，窗口句柄或 UI 元素句柄
     @param hParentWnd 父窗口句柄 HWND，提供给第三方窗口使用
     @param hAttachWnd 附加到指定的窗口 HWND，如果未指定则为 0
     @return 返回窗口句柄或元素句柄
     */
    public static native int XC_LoadLayoutZipMemEx (byte [] data, int length, String pFileName, String pPassword, String pPrefixName, int hParent, int hParentWnd, int hAttachWnd);
    /**
     从 RC 资源 zip 压缩包中加载布局文件，RC 资源类型必须为:"RT_RCDATA"
     @param id RC 资源 ID，资源在 RC 文件中的标识
     @param pFileName 布局文件名，zip 压缩包中的布局文件名
     @param pPassword zip 压缩包密码，如果没有密码则为 null
     @param pPrefixName 名称 (name) 前缀，可选参数；给当前布局文件中所有 name 属性增加前缀，那么 name 属性值为：前缀 + name
     @param hParent 父对象句柄，窗口句柄或 UI 元素句柄
     @param hParentWnd 父窗口句柄 HWND，提供给第三方窗口使用
     @param hAttachWnd 附加到指定的窗口 HWND，如果未指定则为 0
     @param hModule 模块句柄，从指定模块加载资源，例如 DLL、EXE；如果为空则从当前 EXE 加载
     @return 返回窗口句柄或元素句柄
     */
    public static native int XC_LoadLayoutZipResEx (int id, String pFileName, String pPassword, String pPrefixName, int hParent, int hParentWnd, int hAttachWnd, long hModule);
    /**
     加载布局文件从字符串扩展
     @param pStringXML 布局文件的字符串内容
     @param pPrefixName 名称 (name) 前缀，可选参数；给当前布局文件中所有 name 属性增加前缀，name 属性值变为：前缀 + name
     @param hParent 父对象句柄，窗口句柄或 UI 元素句柄
     @param hParentWnd 父窗口句柄 HWND，提供给第三方窗口使用
     @param hAttachWnd 附加到指定的窗口 HWND，如果未指定则忽略
     @return 返回窗口句柄或元素句柄
     */
    public static native int XC_LoadLayoutFromStringEx (String pStringXML, String pPrefixName, int hParent, int hParentWnd, int hAttachWnd);
    /**
     加载布局文件从 UTF8 字符串扩展
     @param pStringXML UTF8 编码的布局文件字符串内容
     @param pPrefixName 名称 (name) 前缀，可选参数；给当前布局文件中所有 name 属性增加前缀，name 属性值变为：前缀 + name
     @param hParent 父对象句柄，窗口句柄或 UI 元素句柄
     @param hParentWnd 父窗口句柄 HWND，提供给第三方窗口使用
     @param hAttachWnd 附加到指定的窗口 HWND，如果未指定则忽略
     @return 返回窗口句柄或元素句柄
     */
    public static native int XC_LoadLayoutFromStringUtf8Ex (String pStringXML, String pPrefixName, int hParent, int hParentWnd, int hAttachWnd);
    /**
     加载样式文件
     @param pFileName 样式文件名称
     @return 成功返回 true 否则返回 false
     **/
    public static native boolean XC_LoadStyle (String pFileName);
    /**
     加载样式文件从 zip 压缩包中
     @param pZipFile ZIP 文件名
     @param pFileName 文件名
     @param pPassword 密码
     @return 成功返回 true, 否则返回 false
     **/
    public static native boolean XC_LoadStyleZip (String pZipFile, String pFileName, String pPassword);

    /**
     从 UTF8 字符串加载样式文件
     @param pString 样式文件的 UTF8 字符串内容
     @param pFileName 样式文件名，用于打印错误文件和定位关联资源文件位置
     @return 如果成功返回 TRUE，否则返回 FALSE
     **/
    public static native boolean XC_LoadStyleFromStringUtf8 (String pString, String pFileName);
    /**
     从 Unicode 字符串加载样式文件
     @param pString 样式文件的 Unicode 字符串内容
     @param pFileName 样式文件名，用于打印错误文件和定位关联资源文件位置
     @return 如果成功返回 TRUE，否则返回 FALSE
     **/
    public static native boolean XC_LoadStyleFromStringW (String pString, String pFileName);
    /**
     加载资源文件
     @param pFileName 资源文件名
     @return 成功返回 TRUE 否则返回 FALSE
     **/
    public static native boolean XC_LoadResource (String pFileName);
    /**
     从 zip 压缩包中加载资源文件
     @param pZipFileName zip 文件名
     @param pFileName 资源文件名
     @param pPassword zip 压缩包密码
     @return 如果成功返回 TRUE，否则返回 FALSE
     **/
    public static native boolean XC_LoadResourceZip (String pZipFileName, String pFileName, String pPassword);

    /**
     加载资源文件从内存字符串
     @param pStringXML 字符串指针，指向待加载的资源文件内容
     @param pFileName 资源文件名，用于区分不同的资源文件，若名称重复则替换先前的资源文件
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XC_LoadResourceFromString (String pStringXML, String pFileName);
    /**
     加载资源文件从内存 UTF8 字符串
     @param pStringXML UTF8 编码的字符串指针，指向待加载的资源文件内容
     @param pFileName 资源文件名，用于区分不同的资源文件，若名称重复则替换先前的资源文件
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XC_LoadResourceFromStringUtf8 (String pStringXML, String pFileName);
    /**
     获取 D2D 工厂
     @return 返回 D2D 工厂相关的二进制数据（对应 ID2D1Factory*）
     */
    public static native byte [] XC_GetD2dFactory ();
    /**
     获取 WIC 工厂
     @return 返回 WIC 工厂相关的二进制数据（对应 IWICImagingFactory*）
     */
    public static native byte [] XC_GetWicFactory ();

    /**
     获取 DWrite 工厂，用于文本渲染相关操作
     @return 返回 DWrite 工厂的字节数组表示
     **/
    public static native byte [] XC_GetDWriteFactory ();
    /**
     设置炫彩对象的类型（扩展接口）
     @param hXCGUI 炫彩对象句柄
     @param nType 要设置的对象类型
     **/
    public static native void _XC_SetType (int hXCGUI, int nType);
    /**
     为炫彩对象添加类型（扩展开发接口）
     @param hXCGUI 炫彩对象句柄
     @param nType 要添加的对象类型
     **/
    public static native void _XC_AddType (int hXCGUI, int nType);
    /**
     为炫彩对象绑定数据（扩展开发接口）
     @param hXCGUI 炫彩对象句柄
     @param data 要绑定到对象的数据
     **/
    public static native void _XC_BindData (int hXCGUI, byte [] data);

    /**
     获取绑定到炫彩对象的数据
     @param hXCGUI 炫彩对象句柄
     @return 返回绑定的数据字节数组
     */
    public static native byte [] _XC_GetBindData (int hXCGUI);
    /**
     判断界面库是否已经初始化
     @return 如果界面库已初始化返回 true，否则返回 false
     */
    public static native boolean XC_IsInit ();
    /**
     设置全局窗口图标，所有未设置图标的窗口都将使用此默认图标
     @param hImage 图标句柄
     */
    public static native void XC_SetWindowIcon (int hImage);
    /**
     设置背景对象的外间距，外间距与对齐标识互相依赖
     @param hObj 背景对象句柄
     @param left 左边间距
     @param top 顶部间距
     @param right 右边间距
     @param bottom 底部间距
     */
    public static native void XBkObj_SetMargin (int hObj, int left, int top, int right, int bottom);

    /**
     背景对象_置对齐方式
     @param hObj 背景对象句柄
     @param nFlags 对齐方式，参考 bkObject_align_flag_枚举
     **/
    public static native void XBkObj_SetAlign (int hObj, int nFlags);
    /**
     背景对象_置图片
     @param hObj 背景对象句柄
     @param hImage 图片句柄
     **/
    public static native void XBkObj_SetImage (int hObj, int hImage);
    /**
     背景对象_置旋转角度
     @param hObj 背景对象句柄
     @param angle 旋转角度
     **/
    public static native void XBkObj_SetRotate (int hObj, float angle);
    /**
     背景对象_置填充颜色
     @param hObj 背景对象句柄
     @param color 颜色值，请使用宏：RGBA ()
     **/
    public static native void XBkObj_SetFillColor (int hObj, long color);

    /**
     设置背景对象的边框宽度
     @param hObj 背景对象句柄
     @param width 边框宽度
     **/
    public static native void XBkObj_SetBorderWidth (int hObj, int width);
    /**
     设置背景对象的边框颜色
     @param hObj 背景对象句柄
     @param color 颜色值，需使用宏 RGBA () 生成
     **/
    public static native void XBkObj_SetBorderColor (int hObj, long color);
    /**
     设置背景对象的矩形圆角大小
     @param hObj 背景对象句柄
     @param leftTop 左上角圆角大小
     @param leftBottom 左下角圆角大小
     @param rightTop 右上角圆角大小
     @param rightBottom 右下角圆角大小
     **/
    public static native void XBkObj_SetRectRoundAngle (int hObj, int leftTop, int leftBottom, int rightTop, int rightBottom);
    /**
     启用 / 禁用背景对象的填充功能
     @param hObj 背景对象句柄
     @param bEnable 是否启用填充：true 启用，false 禁用
     **/
    public static native void XBkObj_EnableFill (int hObj, boolean bEnable);

    /**
     背景对象_启用边框 ()
     @param hObj 背景对象句柄
     @param bEnable 是否启用绘制边框
     **/
    public static native void XBkObj_EnableBorder (int hObj, boolean bEnable);
    /**
     背景对象_置文本 ()
     @param hObj 背景对象句柄
     @param pText 文本字符串
     **/
    public static native void XBkObj_SetText (int hObj, String pText);
    /**
     背景对象_置字体 ()
     @param hObj 背景对象句柄
     @param hFont 字体句柄
     **/
    public static native void XBkObj_SetFont (int hObj, int hFont);
    /**
     背景对象_置文本对齐 ()
     @param hObj 背景对象句柄
     @param nAlign 文本对齐方式（参考 textFormatFlag_枚举）
     **/
    public static native void XBkObj_SetTextAlign (int hObj, int nAlign);

    /**
     背景对象_取对齐
     @param hObj 背景对象句柄
     @return 返回对齐标识 @ref bkObject_align_flag_
     **/
    public static native int XBkObj_GetAlign (int hObj);
    /**
     背景对象_取图片
     @param hObj 背景对象句柄
     @return 返回图片句柄
     **/
    public static native int XBkObj_GetImage (int hObj);
    /**
     背景对象_取旋转角度
     @param hObj 背景对象句柄
     @return 返回旋转角度
     **/
    public static native int XBkObj_GetRotate (int hObj);
    /**
     背景对象_取填充色
     @param hObj 背景对象句柄
     @return 返回填充色值
     **/
    public static native long XBkObj_GetFillColor (int hObj);
    /**
     背景对象_取边框色
     @param hObj 背景对象句柄
     @return 返回边框色值
     **/
    public static native long XBkObj_GetBorderColor (int hObj);

    /**
     背景对象_取边框宽度
     @param hObj 背景对象句柄
     @return 返回边框宽度
     **/
    public static native int XBkObj_GetBorderWidth (int hObj);
    /**
     背景对象_是否填充
     @param hObj 背景对象句柄
     @return 填充返回 TRUE，否则返回 FALSE
     **/
    public static native boolean XBkObj_IsFill (int hObj);
    /**
     背景对象_是否边框
     @param hObj 背景对象句柄
     @return 绘制边框返回 TRUE，否则返回 FALSE
     **/
    public static native boolean XBkObj_IsBorder (int hObj);
    /**
     背景对象_取字体
     @param hObj 背景对象句柄
     @return 返回字体句柄
     **/
    public static native int XBkObj_GetFont (int hObj);
    /**
     背景对象_取文本对齐
     @param hObj 背景对象句柄
     @return 返回文本对齐方式
     **/
    public static native int XBkObj_GetTextAlign (int hObj);


    /**
     三次方曲线（圆弧）缓动
     @param pos 位置，范围 0.0f - 1.0f
     @param flag 缓动方式，参考 ease_type_ 枚举
     @return 返回缓动计算结果
     **/
    public static native float XEase_Cubic (float pos, int flag);
    /**
     四次方曲线缓动
     @param pos 位置，范围 0.0f - 1.0f
     @param flag 缓动方式，参考 ease_type_ 枚举
     @return 返回缓动计算结果
     **/
    public static native float XEase_Quart (float pos, int flag);
    /**
     五次方曲线缓动
     @param pos 位置，范围 0.0f - 1.0f
     @param flag 缓动方式，参考 ease_type_ 枚举
     @return 返回缓动计算结果
     **/
    public static native float XEase_Quint (float pos, int flag);
    /**
     正弦曲线缓动（在末端变化）
     @param pos 位置，范围 0.0f - 1.0f
     @param flag 缓动方式，参考 ease_type_ 枚举
     @return 返回缓动计算结果
     **/
    public static native float XEase_Sine (float pos, int flag);
    /**
     突击曲线缓动（突然一下）
     @param pos 位置，范围 0.0f - 1.0f
     @param flag 缓动方式，参考 ease_type_ 枚举
     @return 返回缓动计算结果
     **/
    public static native float XEase_Expo (float pos, int flag);

    /**
     缓动_圆环（类似绕过圆环的缓动效果）
     @param pos 当前进度 (0.0f-1.0f)
     @param flag 缓动方式标识（对应 ease_flag_枚举）
     @return 返回缓动计算结果
     **/
    public static native float XEase_Circ (float pos, int flag);
    /**
     缓动_强力回弹
     @param pos 当前进度 (0.0f-1.0f)
     @param flag 缓动方式标识（对应 ease_flag_枚举）
     @return 返回缓动计算结果
     **/
    public static native float XEase_Elastic (float pos, int flag);
    /**
     缓动_回弹（比较缓慢的回弹效果）
     @param pos 当前进度 (0.0f-1.0f)
     @param flag 缓动方式标识（对应 ease_flag_枚举）
     @return 返回缓动计算结果
     **/
    public static native float XEase_Back (float pos, int flag);
    /**
     缓动_弹跳（模拟小球落地弹跳的效果）
     @param pos 当前进度 (0.0f-1.0f)
     @param flag 缓动方式标识（对应 ease_flag_枚举）
     @return 返回缓动计算结果
     **/
    public static native float XEase_Bounce (float pos, int flag);
    /**
     缓动_扩展（支持全部缓动类型）
     @param pos 当前进度 (0.0f-1.0f)
     @param flag 缓动方式标识（对应 ease_flag_枚举）
     @return 返回缓动计算结果
     **/
    public static native float XEase_Ex (float pos, int flag);

    /**
     判断指定行是否为空（可能包含空格和 TAB）
     @param hEle 代码编辑框元素句柄
     @param iRow 行索引
     @return 空行返回 true，否则返回 false
     */
    public static native boolean XEditor_IsEmptyRow (int hEle, int iRow);
    /**
     判断指定行是否设置了断点
     @param hEle 代码编辑框元素句柄
     @param iRow 行索引
     @return 有断点返回 true，否则返回 false
     */
    public static native boolean XEditor_IsBreakpoint (int hEle, int iRow);
    /**
     移除指定行的断点
     @param hEle 代码编辑框元素句柄
     @param iRow 行索引
     @return 移除成功返回 true，否则返回 false
     */
    public static native boolean XEditor_RemoveBreakpoint (int hEle, int iRow);
    /**
     清空代码编辑框中的所有断点
     @param hEle 代码编辑框元素句柄
     */
    public static native void XEditor_ClearBreakpoint (int hEle);
    /**
     设置当前运行行
     @param hEle 代码编辑框元素句柄
     @param iRow 行索引
     @return 设置成功返回 true，否则返回 false
     */
    public static native boolean XEditor_SetRunRow (int hEle, int iRow);

    /**
     代码编辑框_取断点数量 ()
     @param hEle 代码编辑框元素句柄
     @return 返回断点数量
     **/
    public static native int XEditor_GetBreakpointCount (int hEle);
    /**
     代码编辑框_置提示信息延迟 ()
     @param hEle 代码编辑框元素句柄
     @param nDelay 延迟值，单位毫秒
     **/
    public static native void XEditor_SetTipsDelay (int hEle, int nDelay);
    /**
     代码编辑框_置自动匹配选择模式 ()
     @param hEle 代码编辑框元素句柄
     @param mode 选择模式：0 - 回车选择，1 - 空格选择，3-tab 键选择
     **/
    public static native void XEditor_SetAutoMatchSelectMode (int hEle, int mode);
    /**
     代码编辑框_置自动匹配结果显示模式 ()
     @param hEle 代码编辑框元素句柄
     @param mode 显示模式：0 - 中英文 (上屏中文)，1 - 中英文 (上屏英文)，2 - 中文 (上屏中文)，3 - 英文 (上屏英文)
     **/
    public static native void XEditor_SetAutoMatchMode (int hEle, int mode);
    /**
     代码编辑框_设置当前行 ()
     @param hEle 代码编辑框元素句柄
     @param iRow 行索引（跳过收缩行）
     **/
    public static native void XEditor_SetCurRow (int hEle, int iRow);

    /**
     获取代码对齐线的缩进深度
     @param hEle 代码编辑框元素句柄
     @param iRow 行索引
     @return 返回代码对齐线的缩进深度
     */
    public static native int XEditor_GetDepth (int hEle, int iRow);
    /**
     获取实际的缩进深度
     若是自由缩进模式，返回实际 TAB 缩进深度；否则返回代码对齐线的缩进深度
     @param hEle 代码编辑框元素句柄
     @param iRow 行索引
     @return 返回实际的缩进深度
     */
    public static native int XEditor_GetDepthEx (int hEle, int iRow);
    /**
     转换行索引，跳过收缩行
     将收缩状态下的行索引转换为对应的展开行索引
     @param hEle 代码编辑框元素句柄
     @param iRow 行索引
     @return 返回转换后的展开行索引
     */
    public static native int XEditor_ToExpandRow (int hEle, int iRow);
    /**
     展开指定行周围的折叠内容
     将指定行所在的折叠内容展开，但保持行自身的展开状态不变
     @param hEle 代码编辑框元素句柄
     @param iRow 行索引
     */
    public static native void XEditor_ExpandEx (int hEle, int iRow);

    /**
     代码编辑框_展开全部行
     @param hEle 编辑器元素句柄
     @param bExpand 是否展开
     **/
    public static native void XEditor_ExpandAll (int hEle, boolean bExpand);
    /**
     代码编辑框_展开指定行
     @param hEle 编辑器元素句柄
     @param iRow 行索引
     @param bExpand 是否展开
     **/
    public static native void XEditor_Expand (int hEle, int iRow, boolean bExpand);
    /**
     代码编辑框_展开 / 收缩指定行（切换展开状态）
     @param hEle 编辑器元素句柄
     @param iRow 行索引
     **/
    public static native void XEditor_ExpandSwitch (int hEle, int iRow);

    /**
     如果已存在关键字则仅更新样式
     @param hEle 元素句柄
     @param pKey 关键字字符串
     @param iStyle 样式
     **/
    public static native void XEditor_AddKeyword (int hEle, String pKey, int iStyle);
    /**
     添加自动匹配字符串
     @param hEle 元素句柄
     @param pKey 字符串
     **/
    public static native void XEditor_AddConst (int hEle, String pKey);
    /**
     添加自动匹配函数
     @param hEle 元素句柄
     @param pKey 函数名字符串
     **/
    public static native void XEditor_AddFunction (int hEle, String pKey);

    /**
     代码编辑框_添加排除定义变量关键字
     <p>排除定义变量的关键字，用于排除定义变量，因为定义变量禁用自动匹配；此关键字不加入自动匹配，仅用于排除定义变量</p>
     @param hEle 代码编辑框元素句柄
     @param pKeyword 排除定义变量的关键字字符串
     */
    public static native void XEditor_AddExcludeDefVarKeyword (int hEle, String pKeyword);
    /**
     项模板_加载从文件
     <p>加载列表项模板文件</p>
     @param nType 模板类型（对应 listItemTemp_type_枚举）
     @param pFileName 列表项模板文件名
     @return 返回项模板句柄
     */
    public static native int XTemp_Load (int nType, String pFileName);
    /**
     项模板_加载从 ZIP
     <p>从ZIP压缩包中加载列表项模板</p>
     @param nType 模板类型（对应 listItemTemp_type_枚举）
     @param pZipFile ZIP 压缩包文件名
     @param pFileName 列表项模板文件名
     @param pPassword ZIP 压缩包密码
     @return 返回项模板句柄
     */
    public static native int XTemp_LoadZip (int nType, String pZipFile, String pFileName, String pPassword);
    /**
     项模板_加载从内存 ZIP () - 从内存中的 ZIP 压缩包加载列表项模板
     @param nType 模板类型，支持类型：列表树、列表框、列表头、列表项、列表视图组、列表视图项等
     @param data ZIP 压缩包的内存数据字节数组
     @param length 内存数据的大小 (字节为单位)
     @param pFileName 模板文件名
     @param pPassword ZIP 压缩包密码
     @return 返回项模板句柄 (HTEMP), 失败返回 NULL
     */
    public static native int XTemp_LoadZipMem (int nType, byte [] data, int length, String pFileName, String pPassword);
    /**
     项模板_加载从文件扩展 () - 从文件加载列表项模板，支持同时获取两个模板句柄
     @param nType 模板类型，支持类型：列表树、列表框、列表头、列表项、列表视图组、列表视图项等
     @param pFileName 模板文件名
     @param dwSize 输出数组的大小
     @param pOutTemp1 接收返回的模板句柄 1 (项模板)
     @param pOutTemp2 接收返回的模板句柄 2 (列表头模板或列表视组模板)
     @return 成功返回 TRUE, 否则返回 FALSE
     */
    public static native boolean XTemp_LoadEx (int nType, String pFileName, int dwSize, byte [] pOutTemp1, byte [] pOutTemp2);
    /**
     项模板_加载从 ZIP 扩展 () - 从 ZIP 压缩包加载列表项模板，支持同时获取两个模板句柄
     @param nType 模板类型，支持类型：列表树、列表框、列表头、列表项、列表视图组、列表视图项等
     @param pZipFile ZIP 压缩包文件名
     @param pFileName 模板文件名
     @param pPassword ZIP 压缩包密码
     @param dwSize 输出数组的大小
     @param pOutTemp1 接收返回的模板句柄 1 (项模板)
     @param pOutTemp2 接收返回的模板句柄 2 (列表头模板或列表视组模板)
     @return 成功返回 TRUE, 否则返回 FALSE
     */
    public static native boolean XTemp_LoadZipEx (int nType, String pZipFile, String pFileName, String pPassword, int dwSize, byte [] pOutTemp1, byte [] pOutTemp2);
    /**
     项模板_加载从内存 ZIP 扩展
     加载列表项模板从内存 zip 压缩包中
     @param nType 模板类型，支持类型组合：列表树、列表框、列表头、列表项、列表视图组、列表视图项等
     @param data 内存块字节数组
     @param length 内存块大小，字节为单位
     @param pFileName 模板文件名
     @param pPassword zip 压缩包密码
     @param dwSize 接收返回模板句柄的数组大小
     @param pOutTemp1 接收返回模板句柄 1：项模板
     @param pOutTemp2 接收返回模板句柄 2：列表头模板或列表视组模板
     @return 如果成功返回 true，否则返回 false
     */
    public static native boolean XTemp_LoadZipMemEx (int nType, byte [] data, int length, String pFileName, String pPassword, int dwSize, byte [] pOutTemp1, byte [] pOutTemp2);
    /**
     项模板_加载从资源 ZIP
     从 RC 资源 ZIP 加载项模板，RC 资源类型必须为:"RT_RCDATA"
     @param nType 模板类型，支持类型：列表树、列表框、列表头、列表项、列表视图组、列表视图项等
     @param id RC 资源 ID
     @param pFileName 模板文件名
     @param pPassword ZIP 压缩包密码
     @param hModule 模块句柄
     @return 返回项模板句柄
     */
    public static native int XTemp_LoadZipRes (int nType, int id, String pFileName, String pPassword, long hModule);
    /**
     项模板_加载从资源 ZIP 扩展
     从 RC 资源 ZIP 加载列表项模板的扩展版本，RC 资源类型必须为:"RT_RCDATA"
     @param nType 模板类型，支持类型组合：列表树、列表框、列表头、列表项、列表视图组、列表视图项等
     @param id RC 资源 ID
     @param pFileName 模板文件名
     @param pPassword ZIP 压缩包密码
     @param dwSize 接收返回模板句柄的数组大小
     @param pOutTemp1 接收返回模板句柄 1：项模板
     @param pOutTemp2 接收返回模板句柄 2：列表头模板或列表视组模板
     @param hModule 模块句柄
     @return 如果成功返回 true，否则返回 false
     */
    public static native boolean XTemp_LoadZipResEx (int nType, int id, String pFileName, String pPassword, int dwSize, byte [] pOutTemp1, byte [] pOutTemp2, long hModule);
    /**
     从内存字符串加载列表项模板
     @param nType 模板类型，取值范围见 listItemTemp_type_枚举
     @param pStringXML 模板的 XML 字符串内容
     @return 成功返回项模板句柄，失败返回 0
     */
    public static native int XTemp_LoadFromString (int nType, String pStringXML);
    /**
     从字符串加载列表项模板扩展，可同时返回两个模板句柄
     @param nType 模板类型，取值范围见 listItemTemp_type_枚举
     @param pStringXML 模板的 XML 字符串内容
     @param dwSize 预留参数
     @param pOutTemp1 用于接收返回的模板句柄 1（项模板）的字节数组
     @param pOutTemp2 用于接收返回的模板句柄 2（列表头模板或列表视组模板）的字节数组
     @return 成功返回 true，失败返回 false
     */
    public static native boolean XTemp_LoadFromStringEx (int nType, String pStringXML, int dwSize, byte [] pOutTemp1, byte [] pOutTemp2);
    /**
     从内存加载列表项模板
     @param nType 模板类型，取值范围见 listItemTemp_type_枚举
     @param data 模板的内存数据字节数组
     @param length 内存数据的长度（字节为单位）
     @return 成功返回项模板句柄，失败返回 0
     */
    public static native int XTemp_LoadFromMem (int nType, byte [] data, int length);

    /**
     项模板_加载从内存扩展
     @param nType 模板类型 @ref listItemTemp_type_
     @param data 内存块
     @param length 内存块大小（字节为单位）
     @param dwSize 参数（用户提供，文档未提及，保留）
     @param pOutTemp1 返回模板句柄 1，项模板
     @param pOutTemp2 返回模板句柄 2，列表头模板或列表视组模板
     @return 如果成功返回 TRUE，否则返回 FALSE
     **/
    public static native boolean XTemp_LoadFromMemEx (int nType, byte [] data, int length, int dwSize, byte [] pOutTemp1, byte [] pOutTemp2);
    /**
     项模板_取内置项模板
     @param nType 模板类型 @ref listItemTemp_type_
     @return 返回项模板句柄
     **/
    public static native int XTemp_Get (int nType);
    /**
     项模板_取类型
     @param hTemp 列表项模板句柄
     @return 返回模板类型 @ref listItemTemp_type_
     **/
    public static native int XTemp_GetType (int hTemp);
    /**
     项模板_销毁
     @param hTemp 项模板句柄
     @return 如果成功返回 TRUE，否则返回 FALSE
     **/
    public static native boolean XTemp_Destroy (int hTemp);

    /**
     项模板_克隆
     @param hTemp 项模板句柄
     @return 返回克隆的项模板句柄
     */
    public static native int XTemp_Clone (int hTemp);
    /**
     项模板_添加根节点
     @param hTemp 项模板句柄
     @param pNode 节点指针（int 类型）
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XTemp_AddNodeRoot (int hTemp, int pNode);
    /**
     项模板_添加根节点
     @param hTemp 项模板句柄
     @param pNode 节点指针（long 类型）
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XTemp_AddNodeRoot (int hTemp, long pNode);
    /**
     列表项模板_列表_插入节点
     @param hTemp 列表项模板句柄
     @param index 插入位置索引
     @param pNode 节点指针
     @return 如果成功返回 TRUE，否则返回 FALSE
     */
    public static native boolean XTemp_List_InsertNode (int hTemp, int index, int pNode);
    /**
     列表项模板_列表_插入节点
     @param hTemp 列表项模板句柄
     @param index 插入位置索引
     @param pNode 节点指针
     @return 如果成功返回 TRUE，否则返回 FALSE
     */
    public static native boolean XTemp_List_InsertNode (int hTemp, int index, long pNode);
    /**
     列表项模板_列表_删除节点
     @param hTemp 列表项模板句柄
     @param index 要删除的节点位置索引
     @return 如果成功返回 TRUE，否则返回 FALSE
     */
    public static native boolean XTemp_List_DeleteNode (int hTemp, int index);

    /**
     项模板_列表_取数量 () - 取子节点数量，仅当前层子节点
     @param hTemp 列表项模板句柄
     @return 子节点数量
     **/
    public static native int XTemp_List_GetCount (int hTemp);
    /**
     项模板_添加子节点 () - 添加子节点到父节点
     @param pParentNode 父节点指针
     @param pNode 节点指针 (32 位环境下的指针表示)
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XTemp_AddNode (int pParentNode, int pNode);
    /**
     项模板_添加子节点 () - 添加子节点到父节点
     @param pParentNode 父节点指针
     @param pNode 节点指针 (64 位环境下的指针表示)
     @return 成功返回 true，否则返回 false
     **/
    public static native boolean XTemp_AddNode (int pParentNode, long pNode);

    /**
     创建节点 (X86 架构)
     @param nType 对象类型
     @return 成功返回节点指针 (32 位), 否则返回 NULL
     **/
    public static native int XTemp_CreateNodeX86 (int nType);
    /**
     创建节点 (X64 架构)
     @param nType 对象类型
     @return 成功返回节点指针 (64 位), 否则返回 NULL
     **/
    public static native long XTemp_CreateNodeX64 (int nType);
    /**
     设置节点属性 (64 位节点指针)
     @param pNode 节点指针
     @param pName 属性名
     @param pAttr 属性值
     @return 成功返回 TRUE, 否则返回 FALSE
     **/
    public static native boolean XTemp_SetNodeAttribute (long pNode, String pName, String pAttr);
    /**
     设置节点属性 (32 位节点指针)
     @param pNode 节点指针
     @param pName 属性名
     @param pAttr 属性值
     @return 成功返回 TRUE, 否则返回 FALSE
     **/
    public static native boolean XTemp_SetNodeAttribute (int pNode, String pName, String pAttr);

    /**
     设置项模板节点属性扩展，支持通过 itemID 指定模板项
     @param pNode 节点指针
     @param itemID 模板项 ID
     @param pName 属性名
     @param pAttr 属性值
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XTemp_SetNodeAttributeEx (long pNode, int itemID, String pName, String pAttr);
    /**
     设置项模板节点属性扩展，支持通过 itemID 指定模板项（X86 版本）
     @param pNode 节点指针
     @param itemID 模板项 ID
     @param pName 属性名
     @param pAttr 属性值
     @return 成功返回 true，否则返回 false
     */
    public static native boolean XTemp_SetNodeAttributeEx (int pNode, int itemID, String pName, String pAttr);
    /**
     从项模板列表中获取指定索引的节点（X64 版本）
     @param hTemp 项模板句柄
     @param index 节点位置索引
     @return 节点指针
     */
    public static native long XTemp_List_GetNodeX64 (int hTemp, int index);
    /**
     从项模板列表中获取指定索引的节点（X86 版本）
     @param hTemp 项模板句柄
     @param index 节点位置索引
     @return 节点指针
     */
    public static native int XTemp_List_GetNodeX86 (int hTemp, int index);

    /**
     项模板_列表_移动列 - 将指定列移动到目标位置
     @param hTemp 列表项模板句柄
     @param iColSrc 源列索引
     @param iColDest 目标列索引
     @return 如果成功返回 TRUE，否则返回 FALSE
     */
    public static native boolean XTemp_List_MoveColumn (int hTemp, int iColSrc, int iColDest);
    /**
     项模板_取节点 - 根据 itemID 获取节点
     @param pNode 节点指针
     @param itemID 节点 ID
     @return 返回 itemID 对应的节点指针
     */
    public static native long XTemp_GetNode (long pNode, int itemID);
    /**
     项模板_取节点 - 根据 itemID 获取节点（重载，参数和返回值类型为 int）
     @param pNode 节点指针
     @param itemID 节点 ID
     @return 返回 itemID 对应的节点指针
     */
    public static native int XTemp_GetNode (int pNode, int itemID);
    /**
     项模板_克隆节点 - 克隆一个节点
     @param pNode 节点指针
     @return 返回克隆的节点指针
     */
    public static native long XTemp_CloneNode (long pNode);

    /**
     项模板_克隆节点
     @param pNode 节点指针
     @return 返回克隆的节点指针
     **/
    public static native int XTemp_CloneNode (int pNode);
    /**
     炫彩_消息框
     @param pTitle 消息框标题
     @param pText 消息框内容文本
     @param nFlags 消息框标识（messageBox_flag_）
     @param hWndParent 父窗口句柄
     @param XCStyle GUI 库窗口样式（window_style_）
     @return messageBox_flag_ok: 点击确定按钮退出，messageBox_flag_cancel: 点击取消按钮退出，messageBox_flag_other: 其他方式退出
     **/
    public static native int XC_MessageBox (String pTitle, String pText, int nFlags, int hWndParent, int XCStyle);
    /**
     炫彩消息框_创建
     @param pTitle 消息框标题
     @param pText 消息框内容文本
     @param nFlags 消息框标识（messageBox_flag_）
     @param hWndParent 父窗口句柄
     @param XCStyle GUI 库窗口样式（window_style_）
     @return 返回窗口句柄，需调用 XModalWnd_DoModal () 显示，根据其返回值判断用户操作
     **/
    public static native int XMsg_Create (String pTitle, String pText, int nFlags, int hWndParent, int XCStyle);

    /**
     创建扩展样式的模态消息框窗口
     @param dwExStyle 窗口扩展样式
     @param dwStyle 窗口样式
     @param lpClassName 窗口类名
     @param pTitle 消息框标题
     @param pText 消息框内容文本
     @param nFlags 消息框标识，参考 messageBox_flag_枚举
     @param hWndParent 父窗口句柄
     @param XCStyle GUI 库窗口样式，参考 window_style_枚举
     @return 返回窗口句柄，后续可调用 XModalWnd_DoModal () 显示并根据其返回值判断用户操作
     */
    public static native int XMsg_CreateEx (int dwExStyle, int dwStyle, String lpClassName, String pTitle, String pText, int nFlags, int hWndParent, int XCStyle);
    /**
     启用或关闭浮动窗口的标题栏内容（标题文本和关闭按钮）
     @param hWindow 浮动窗口句柄
     @param bEnable 是否启用标题栏内容
     @return 操作成功返回 true，否则返回 false
     */
    public static native boolean XFloatWnd_EnableCaptionContent (int hWindow, boolean bEnable);
    /**
     获取浮动窗口标题栏的形状文本对象句柄
     @param hWindow 浮动窗口句柄
     @return 返回形状文本对象句柄
     */
    public static native int XFloatWnd_GetCaptionShapeText (int hWindow);

    /**
     浮动窗口_取标题关闭按钮
     @param hWindow 浮动窗口句柄
     @return 标题关闭按钮句柄
     **/
    public static native int XFloatWnd_GetCaptionButtonClose (int hWindow);
    /**
     浮动窗口_置标题
     @param hWindow 浮动窗口句柄
     @param pTitle 标题文本
     **/
    public static native void XFloatWnd_SetTitle (int hWindow, String pTitle);
    /**
     浮动窗口_取标题
     @param hWindow 浮动窗口句柄
     @param dwSize 接收标题的缓冲区大小
     @return 标题文本的字节数组
     **/
    public static native byte [] XFloatWnd_GetTitle (int hWindow, int dwSize);

    /**
     资源_启用延迟加载（图片文件、列表项模板文件）
     @param bEnable 是否启用延迟加载
     **/
    public static native void XRes_EnableDelayLoad (boolean bEnable);
    /**
     资源_取 ID 值
     @param pName 资源 ID 名称
     @return 返回资源 ID 的整型值
     **/
    public static native int XRes_GetIDValue (String pName);
    /**
     资源_取图片
     @param pName 资源名称
     @return 返回图片句柄（整数类型）
     **/
    public static native int XRes_GetImage (String pName);

    /**
     从指定的资源文件中查找图片并获取其句柄
     @param pFileName 指定的资源文件名称
     @param pName 资源名称
     @return 返回图片句柄
     */
    public static native int XRes_GetImageEx (String pFileName, String pName);
    /**
     从资源中查找并获取颜色值
     @param pName 资源名称
     @return 返回颜色值
     */
    public static native long XRes_GetColor (String pName);
    /**
     从资源中查找并获取字体句柄
     @param pName 资源名称
     @return 返回字体句柄
     */
    public static native int XRes_GetFont (String pName);

    /**
     资源_取背景管理器 ()
     @param pName 资源名称
     @return 返回背景管理器句柄
     */
    public static native int XRes_GetBkM (String pName);
    /**
     形状线_创建 ()
     @param x1 起始点 X 坐标
     @param y1 起始点 Y 坐标
     @param x2 结束点 X 坐标
     @param y2 结束点 Y 坐标
     @param hParent 父对象句柄
     @return 返回形状线句柄
     */
    public static native int XShapeLine_Create (int x1, int y1, int x2, int y2, int hParent);
    /**
     形状线_置位置 ()
     @param hShape 形状对象句柄
     @param x1 起始点 X 坐标
     @param y1 起始点 Y 坐标
     @param x2 结束点 X 坐标
     @param y2 结束点 Y 坐标
     */
    public static native void XShapeLine_SetPosition (int hShape, int x1, int y1, int x2, int y2);

    /**
     设置直线形状对象的颜色
     @param hShape 形状对象句柄
     @param color 颜色值，请使用宏 RGBA ()
     **/
    public static native void XShapeLine_SetColor (int hShape, long color);
    /**
     从多字节字符串 ANSI 加载 SVG
     @param pString 字符串指针
     @return SVG 句柄
     **/
    public static native int XSvg_LoadString (String pString);

    /**
     SVG_加载从字符串 W ()
     @param pString UNICODE 字符串指针
     @return 返回 SVG 句柄
     **/
    public static native int XSvg_LoadStringW (String pString);
    /**
     SVG_加载从字符串 UTF8 ()
     @param pString UTF8 字符串指针
     @return 返回 SVG 句柄
     **/
    public static native int XSvg_LoadStringUtf8 (String pString);
    /**
     SVG_加载从 ZIP ()
     @param pZipFileName zip 文件名
     @param pFileName svg 文件名
     @param pPassword zip 密码
     @return 返回 SVG 句柄
     **/
    public static native int XSvg_LoadZip (String pZipFileName, String pFileName, String pPassword);

    /**
     SVG_加载从内存 ZIP
     从内存 zip 压缩包加载 SVG
     @param data 内存块指针
     @param length 内存块大小，字节为单位
     @param pFileName SVG 文件名
     @param pPassword zip 密码
     @return 返回 SVG 句柄
     **/
    public static native int XSvg_LoadZipMem (byte [] data, int length, String pFileName, String pPassword);
    /**
     SVG_加载从资源 ZIP
     从 RC 资源 ZIP 加载 SVG, RC 资源类型必须为:"RT_RCDATA"
     @param id RC 资源 ID
     @param pFileName SVG 文件名
     @param pPassword zip 密码
     @param hModule 模块句柄
     @return 返回 SVG 句柄
     **/
    public static native int XSvg_LoadZipRes (int id, String pFileName, String pPassword, long hModule);
    /**
     SVG_加载从资源
     从资源加载 SVG
     @param id 资源 ID
     @param pType 资源类型，在 rc 资源文件中可查看资源类型，例如：BITMAP, PNG; 参见 MSDN
     @param hModule 从指定模块加载，例如：DLL, EXE; 如果为 0, 从当前 EXE 加载
     @return 返回 SVG 句柄
     **/
    public static native int XSvg_LoadRes (int id, String pType, long hModule);
    /**
     SVG_置大小
     @param hSvg SVG 句柄
     @param nWidth 宽度，如果输入 0，那么还原初始宽度
     @param nHeight 高度，如果输入 0，那么还原初始高度
     **/
    public static native void XSvg_SetSize (int hSvg, int nWidth, int nHeight);
    /**
     SVG_取宽度
     @param hSvg SVG 句柄
     @return 返回宽度
     **/
    public static native int XSvg_GetWidth (int hSvg);
    /**
     SVG_取高度
     @param hSvg SVG 句柄
     @return 返回高度
     **/
    public static native int XSvg_GetHeight (int hSvg);
    /**
     SVG_置偏移
     @param hSvg SVG 句柄
     @param x x 轴偏移
     @param y y 轴偏移
     **/
    public static native void XSvg_SetPosition (int hSvg, int x, int y);
    /**
     SVG_取偏移
     @param hSvg SVG 句柄
     @param pX 接收返回 x 轴偏移的缓冲区
     @param pY 接收返回 y 轴偏移的缓冲区
     **/
    public static native void XSvg_GetPosition (int hSvg, byte [] pX, byte [] pY);
    /**
     SVG_置透明度
     @param hSvg SVG 句柄
     @param alpha 透明度
     **/
    public static native void XSvg_SetAlpha (int hSvg, byte alpha);
    /**
     SVG_取透明度
     @param hSvg SVG 句柄
     @return 返回透明度
     **/
    public static native byte XSvg_GetAlpha (int hSvg);
    /**
     SVG_置用户填充颜色
     @param hSvg SVG 句柄
     @param color 颜色值
     @param bEnable 是否启用用户填充颜色（覆盖默认样式）
     **/
    public static native void XSvg_SetUserFillColor (int hSvg, long color, boolean bEnable);
    /**
     SVG_置用户笔触颜色
     @param hSvg SVG 句柄
     @param color 笔触颜色值
     @param strokeWidth 笔触宽度
     @param bEnable 是否启用用户笔触颜色（覆盖默认样式）
     **/
    public static native void XSvg_SetUserStrokeColor (int hSvg, long color, float strokeWidth, boolean bEnable);
    /**
     SVG_取用户填充颜色
     @param hSvg SVG 句柄
     @param pColor 接收返回颜色值的缓冲区
     @return 如果成功获取返回 TRUE，否则返回 FALSE
     **/
    public static native boolean XSvg_GetUserFillColor (int hSvg, byte [] pColor);
    /**
     SVG_取用户笔触颜色
     @param hSvg SVG 句柄
     @param pColor 接收返回笔触颜色值的缓冲区
     @param pStrokeWidth 接收返回笔触宽度的缓冲区
     @return 如果成功获取返回 TRUE，否则返回 FALSE
     **/
    public static native boolean XSvg_GetUserStrokeColor (int hSvg, byte [] pColor, byte [] pStrokeWidth);
    /**
     SVG_置旋转角度
     @param hSvg SVG 句柄
     @param angle 旋转角度（默认以自身中心点旋转，当旋转角度为 0 时无效）
     **/
    public static native void XSvg_SetRotateAngle (int hSvg, float angle);
    /**
     SVG_取旋转角度
     @param hSvg SVG 句柄
     @return 返回旋转角度
     **/
    public static native float XSvg_GetRotateAngle (int hSvg);
    /**
     SVG_置旋转
     @param hSvg SVG 句柄
     @param angle 旋转角度
     @param x 旋转中心点 X 偏移
     @param y 旋转中心点 Y 偏移
     @param bCenter TRUE: 旋转中心点相对于自身中心偏移，FALSE: 使用绝对坐标
     **/
    public static native void XSvg_SetRotate (int hSvg, float angle, float x, float y, boolean bCenter);

    /**
     获取 SVG 的旋转信息，包括旋转角度、旋转中心点偏移以及是否相对于自身中心偏移
     @param hSvg SVG 句柄
     @param pAngle 接收返回的旋转角度的字节数组
     @param pX 接收返回的旋转中心点 X 轴偏移的字节数组
     @param pY 接收返回的旋转中心点 Y 轴偏移的字节数组
     @param pbCenter 接收返回的是否相对于自身中心偏移的字节数组（TRUE: 旋转中心点相对于自身中心偏移，FALSE: 使用绝对坐标）
     */
    public static native void XSvg_GetRotate (int hSvg, byte [] pAngle, byte [] pX, byte [] pY, byte [] pbCenter);
    /**
     SVG_显示 / 隐藏
     @param hSvg SVG 句柄
     @param bShow 是否显示
     */
    public static native void XSvg_Show (int hSvg, boolean bShow);
    /**
     SVG_启用自动销毁
     @param hSvg SVG 句柄
     @param bEnable 是否启用自动销毁
     */
    public static native void XSvg_EnableAutoDestroy (int hSvg, boolean bEnable);
    /**
     SVG_增加引用计数
     @param hSvg SVG 句柄
     */
    public static native void XSvg_AddRef (int hSvg);
    /**
     SVG_释放引用计数（当引用计数为 0 时自动销毁）
     @param hSvg SVG 句柄
     */
    public static native void XSvg_Release (int hSvg);
    /**
     SVG_取引用计数
     @param hSvg SVG 句柄
     @return 返回引用计数
     */
    public static native int XSvg_GetRefCount (int hSvg);
    /**
     SVG_强制销毁
     @param hSvg SVG 句柄
     */
    public static native void XSvg_Destroy (int hSvg);

    /**
     * 在指定窗口中弹出通知消息，使用基础元素作为面板，返回面板句柄以便操作
     * @param hWindow 窗口句柄
     * @param position 弹出位置 {@link TagPosition}：top-顶部，right-右侧等
     * @param pTitle 通知标题
     * @param pText 通知内容
     * @param hIcon 通知图标句柄
     * @param skin 外观类型 {@link TagNotifyMsgSkin}
     * @return 返回面板元素句柄
     */
    public static native int XNotifyMsg_WindowPopup(int hWindow, int position, String pTitle, String pText, int hIcon, int skin);

    /**
     * 在指定窗口中弹出通知消息（扩展版），可自定义宽度高度，使用基础元素作为面板，返回面板句柄以便操作
     * @param hWindow 窗口句柄
     * @param position 弹出位置 {@link TagPosition}：top-顶部，right-右侧等
     * @param pTitle 通知标题
     * @param pText 通知内容
     * @param hIcon 通知图标句柄
     * @param skin 外观类型 {@link TagNotifyMsgSkin}
     * @param bBtnClose 是否启用关闭按钮
     * @param bAutoClose 是否自动关闭
     * @param nWidth 自定义宽度，-1使用默认值
     * @param nHeight 自定义高度，-1使用默认值
     * @return 返回面板元素句柄
     */
    public static native int XNotifyMsg_WindowPopupEx(int hWindow, int position, String pTitle, String pText, int hIcon, int skin, boolean bBtnClose, boolean bAutoClose, int nWidth, int nHeight);

    /**
     * 弹出桌面通知消息（未实现，预留接口）
     * @param position 弹出位置 {@link TagPosition}
     * @param pTitle 通知标题
     * @param pText 通知内容
     * @param hIcon 通知图标句柄
     * @param skin 外观类型 {@link TagNotifyMsgSkin}
     * @return 返回窗口句柄
     */
    public static native int XNotifyMsg_Popup(int position, String pTitle, String pText, int hIcon, int skin);

    /**
     * 弹出桌面通知消息（未实现，预留接口，扩展版）
     * @param position 弹出位置 {@link TagPosition}
     * @param pTitle 通知标题
     * @param pText 通知内容
     * @param hIcon 通知图标句柄
     * @param skin 外观类型 {@link TagNotifyMsgSkin}
     * @param bBtnClose 是否启用关闭按钮
     * @param bAutoClose 是否自动关闭
     * @param nWidth 自定义宽度，-1使用默认值
     * @param nHeight 自定义高度，-1使用默认值
     * @return 返回窗口句柄
     */
    public static native int XNotifyMsg_PopupEx(int position, String pTitle, String pText, int hIcon, int skin, boolean bBtnClose, boolean bAutoClose, int nWidth, int nHeight);
    /**
     通知消息_置持续时间
     设置通知消息的持续时间（毫秒）
     @param hWindow 通知消息所属窗口句柄，若为桌面通知消息则未指定
     @param duration 持续时间（毫秒）
     */
    public static native void XNotifyMsg_SetDuration (int hWindow, long duration);
    /**
     通知消息_置标题高度
     设置通知消息的标题高度
     @param hWindow 通知消息所属窗口句柄
     @param nHeight 标题高度
     */
    public static native void XNotifyMsg_SetCaptionHeight (int hWindow, int nHeight);
    /**
     通知消息_置宽度
     设置通知消息的默认宽度
     @param hWindow 通知消息所属窗口句柄，若为桌面通知消息则未指定
     @param nWidth 通知消息的宽度
     */
    public static native void XNotifyMsg_SetWidth (int hWindow, int nWidth);
    /**
     通知消息_置间隔
     设置通知消息内部元素的间隔大小
     @param hWindow 通知消息所属窗口句柄，若为桌面通知消息则未指定
     @param nSpace 间隔大小
     */
    public static native void XNotifyMsg_SetSpace (int hWindow, int nSpace);
    /**
     通知消息_置边大小
     设置通知消息面板的边大小（左、上、右、下）
     @param hWindow 通知消息所属窗口句柄，若为桌面通知消息则未指定
     @param left 面板左边大小
     @param top 面板上边大小
     @param right 面板右边大小
     @param bottom 面板底边大小
     */
    public static native void XNotifyMsg_SetBorderSize (int hWindow, int left, int top, int right, int bottom);
    /**
     通知消息_置父边距
     设置通知消息与父对象的四边间隔
     @param hWindow 通知消息所属窗口句柄，若为桌面通知消息则未指定
     @param left 左侧间隔（未实现预留功能）
     @param top 顶部间隔
     @param right 右侧间隔
     @param bottom 底部间隔（未实现预留功能）
     */
    public static native void XNotifyMsg_SetParentMargin (int hWindow, int left, int top, int right, int bottom);

    /**
     判断是否为窗口句柄
     @param hWindow 窗口句柄
     @return 成功返回 TRUE，否则返回 FALSE
     */
    public static native boolean XC_IsHWINDOW (int hWindow);
    /**
     获取窗口句柄 (HWINDOW)
     @param hXCGUI 对象句柄
     @return 窗口句柄 (HWINDOW)
     */
    public static native int XWidget_GetHWINDOW (int hXCGUI);
    /**
     获取系统窗口句柄 (HWND)
     @param hXCGUI 对象句柄
     @return 系统窗口句柄 (HWND)
     */
    public static native int XWidget_GetHWND (int hXCGUI);
    /**
     获取窗口的系统窗口句柄 (HWND)
     @param hWindow 窗口句柄
     @return 系统窗口句柄 (HWND)
     */
    public static native int XWnd_GetHWND (int hWindow);
    /**
     * 炫彩_是否元素
     * @param hEle 元素句柄
     * @return 返回结果
     **/
    public static native boolean XC_IsHELE(int hEle);


    /**
     * 获取SWing窗口句柄
     * @param obj Swing组件创建
     * @return 返回 SWing窗口句柄
     */
    public static native int GetWindowHandle(Component obj);

    /**
     * 发送消息
     * @param hWindow 窗口句柄
     * @param msg 消息
     * @param wParam 参数
     * @param lParam 参数
     * @return 逻辑值
     */
    public static native boolean XC_SendMessage(int hWindow, long msg, long wParam, long lParam);

    /**
     * 投递消息
     * @param hWindow 窗口句柄
     * @param msg 消息
     * @param wParam 参数
     * @param lParam 参数
     * @return 逻辑值
     */
    public static native boolean XC_PostMessage(int hWindow, long msg, long wParam, long lParam);

    //================================================================================================================================


    /**
     * 高级反调试
     * @param hProcess 进程句柄
     */
    public static native void AdvancedBypassAntiDebug(int hProcess);

    /**
     * 释放进程的调试环境
     * @param hProcess 进程句柄
     * @param pid 进程ID
     * @return 逻辑值
     */
    public static native boolean CleanupProcessDebugEnv(int hProcess,int pid);




    public static native int GetThreadID(TagThreadInfo tagThreadInfo);


//    64位：1|2|4|8                   32位：1|2|4
//    64位：0=执行, 1=写入, 3=读写      32位： 1读    3写

    public static native int RunHardwareBreakpointX64(int pid,long address,int len,int dr7Type);
    public static native int CallBack_DataX64(int pid,BreakpointCallBackX64 callBack);


    public static native int RunHardwareBreakpointX86(int pid,int address,int len,int dr7Type);
    public static native int CallBack_DataX86(int pid,BreakpointCallBackX86 callBack);


    //====================================================    初始化报表类     ============================================================================





    // Java Native 方法声明转换结果
// 共转换 2322 个方法

    /**
     * 读对齐方式
     * @return 返回整数
     */
    public static native int report_Barcode_GetAlignment();
    /**
     * 写对齐方式
     * @param value 整数参数
     */
    public static native void report_Barcode_SetAlignment(int value);
    /**
     * 读条码类型
     * @return 返回整数
     */
    public static native int report_Barcode_GetBarcodeType();
    /**
     * 写条码类型
     * @param value 整数参数
     */
    public static native void report_Barcode_SetBarcodeType(int value);
    /**
     * 读文字对齐方式
     * @return 返回整数
     */
    public static native int report_Barcode_GetCaptionAlignment();
    /**
     * 写文字对齐方式
     * @param value 整数参数
     */
    public static native void report_Barcode_SetCaptionAlignment(int value);
    /**
     * 读文字位置
     * @return 返回整数
     */
    public static native int report_Barcode_GetCaptionPosition();
    /**
     * 写文字位置
     * @param value 整数参数
     */
    public static native void report_Barcode_SetCaptionPosition(int value);
    /**
     * 读输出方向
     * @return 返回整数
     */
    public static native int report_Barcode_GetDirection();
    /**
     * 写输出方向
     * @param value 整数参数
     */
    public static native void report_Barcode_SetDirection(int value);
    /**
     * 读DataMatrix编码
     * @return 返回整数
     */
    public static native int report_Barcode_GetDtmxEncoding();
    /**
     * 写DataMatrix编码
     * @param value 整数参数
     */
    public static native void report_Barcode_SetDtmxEncoding(int value);
    /**
     * 读DataMatrix矩阵维度
     * @return 返回整数
     */
    public static native int report_Barcode_GetDtmxMatrixSize();
    /**
     * 写DataMatrix矩阵维度
     * @param value 整数参数
     */
    public static native void report_Barcode_SetDtmxMatrixSize(int value);
    /**
     * 读宽窄条比例
     * @return 返回小数
     */
    public static native float report_Barcode_GetBarRatio();
    /**
     * 写宽窄条比例
     * @param value 小数参数
     */
    public static native void report_Barcode_SetBarRatio(float value);
    /**
     * 读条宽度
     * @return 返回小数
     */
    public static native float report_Barcode_GetBarWidth();
    /**
     * 写条宽度
     * @param value 小数参数
     */
    public static native void report_Barcode_SetBarWidth(float value);
    /**
     * 读条分辨率
     * @return 返回整数
     */
    public static native int report_Barcode_GetBarDPI();
    /**
     * 写条分辨率
     * @param value 整数参数
     */
    public static native void report_Barcode_SetBarDPI(int value);
    /**
     * 读数据校验
     * @return 返回布尔值
     */
    public static native boolean report_Barcode_GetCheckSum();
    /**
     * 写数据校验
     * @param value 布尔参数
     */
    public static native void report_Barcode_SetCheckSum(boolean value);
    /**
     * 读PDF417列数
     * @return 返回整数
     */
    public static native int report_Barcode_GetPDF417Columns();
    /**
     * 写PDF417列数
     * @param value 整数参数
     */
    public static native void report_Barcode_SetPDF417Columns(int value);
    /**
     * 读PDF417纠错等级
     * @return 返回整数
     */
    public static native int report_Barcode_GetPDF417ErrorLevel();
    /**
     * 写PDF417纠错等级
     * @param value 整数参数
     */
    public static native void report_Barcode_SetPDF417ErrorLevel(int value);
    /**
     * 读PDF417行数
     * @return 返回整数
     */
    public static native int report_Barcode_GetPDF417Rows();
    /**
     * 写PDF417行数
     * @param value 整数参数
     */
    public static native void report_Barcode_SetPDF417Rows(int value);
    /**
     * 读PDF417截短
     * @return 返回布尔值
     */
    public static native boolean report_Barcode_GetPDF417Simple();
    /**
     * 写PDF417截短
     * @param value 布尔参数
     */
    public static native void report_Barcode_SetPDF417Simple(boolean value);
    /**
     * 读QRCode掩模
     * @return 返回整数
     */
    public static native int report_Barcode_GetQRCodeMask();
    /**
     * 写QRCode掩模
     * @param value 整数参数
     */
    public static native void report_Barcode_SetQRCodeMask(int value);
    /**
     * 读QRCode版本
     * @return 返回整数
     */
    public static native int report_Barcode_GetQRCodeVersion();
    /**
     * 写QRCode版本
     * @param value 整数参数
     */
    public static native void report_Barcode_SetQRCodeVersion(int value);
    /**
     * 读QRCode纠错等级
     * @return 返回整数
     */
    public static native int report_Barcode_GetQRCodeErrorLevel();
    /**
     * 写QRCode纠错等级
     * @param value 整数参数
     */
    public static native void report_Barcode_SetQRCodeErrorLevel(int value);
    /**
     * 读显示文字
     * @return 返回文本
     */
    public static native String report_Barcode_GetDisplayText();
    /**
     * 写显示文字
     * @param value 文本参数
     */
    public static native void report_Barcode_SetDisplayText(String value);
    /**
     * 读文本
     * @return 返回文本
     */
    public static native String report_Barcode_GetText();
    /**
     * 写文本
     * @param value 文本参数
     */
    public static native void report_Barcode_SetText(String value);
    /**
     * 读BASE64编码文本
     * @return 返回文本
     */
    public static native String report_BinaryObject_GetAsBase64Text();
    /**
     * 读数据内存指针
     * @return 返回整数
     */
    public static native int report_BinaryObject_GetDataBuf();
    /**
     * 读数据尺寸
     * @return 返回整数
     */
    public static native int report_BinaryObject_GetDataSize();
    /**
     * 写数据尺寸
     * @param value 整数参数
     */
    public static native void report_BinaryObject_SetDataSize(int value);
    /**
     * 从BASE64编码载入
     * @param value 文本参数
     * @return 返回布尔值
     */
    public static native boolean report_BinaryObject_LoadFromBase64Text(String value);
    /**
     * 从字段载入
     * @param value 整数参数
     * @return 返回布尔值
     */
    public static native boolean report_BinaryObject_LoadFromField(int value);
    /**
     * 从文件载入
     * @param FileName 文本参数
     * @return 返回布尔值
     */
    public static native boolean report_BinaryObject_LoadFromFile(String FileName);
    /**
     * 从内存载入
     * @param DataPointer 整数参数
     * @param DataSize 整数参数
     */
    public static native void report_BinaryObject_LoadFromMemory(int DataPointer, int DataSize);
    /**
     * 保存到文件
     * @param FileName 文本参数
     * @return 返回布尔值
     */
    public static native boolean report_BinaryObject_SaveToFile(String FileName);
    /**
     * 保存到变量
     * @return 返回整数
     */
    public static native int report_BinaryObject_SaveToVariant();
    /**
     * 从变量载入
     * @param byteP 字节数组参数
     * @param dwSize 整数参数
     * @return 返回布尔值
     */
    public static native boolean report_BinaryObject_LoadFromVariant(byte[] byteP, int dwSize);
    /**
     * 读获取画笔
     * @return 返回整数
     */
    public static native int report_Border_GetPen();
    /**
     * 读边框模式
     * @return 返回整数
     */
    public static native int report_Border_GetStyles();
    /**
     * 写边框模式
     * @param value 整数参数
     */
    public static native void report_Border_SetStyles(int value);
    /**
     * 读获取内框画笔
     * @return 返回整数
     */
    public static native int report_Border_GetInnerPen();
    /**
     * 读内框模式
     * @return 返回整数
     */
    public static native int report_Border_GetInnerStyles();
    /**
     * 写内框模式
     * @param value 整数参数
     */
    public static native void report_Border_SetInnerStyles(int value);
    /**
     * 读内层缩进
     * @return 返回整数
     */
    public static native int report_Border_GetInnerIndent();
    /**
     * 写内层缩进
     * @param value 整数参数
     */
    public static native void report_Border_SetInnerIndent(int value);
    /**
     * 读阴影宽度
     * @return 返回整数
     */
    public static native int report_Border_GetShadowWidth();
    /**
     * 写阴影宽度
     * @param value 整数参数
     */
    public static native void report_Border_SetShadowWidth(int value);
    /**
     * 读阴影颜色
     * @return 返回整数
     */
    public static native int report_Border_GetShadowColor();
    /**
     * 写阴影颜色
     * @param value 整数参数
     */
    public static native void report_Border_SetShadowColor(int value);
    /**
     * 读显示阴影
     * @return 返回布尔值
     */
    public static native boolean report_Border_GetShadow();
    /**
     * 写显示阴影
     * @param value 布尔参数
     */
    public static native void report_Border_SetShadow(boolean value);
    /**
     * 读获取字体
     * @return 返回整数
     */
    public static native int report_CellBase_GetFont();
    /**
     * 读获取边框
     * @return 返回整数
     */
    public static native int report_CellBase_GetBorder();
    /**
     * 读获取文本格式
     * @return 返回整数
     */
    public static native int report_CellBase_GetTextFormat();
    /**
     * 读获取部件框集合
     * @return 返回整数
     */
    public static native int report_CellBase_GetControls();
    /**
     * 读背景色
     * @return 返回整数
     */
    public static native int report_CellBase_GetBackColor();
    /**
     * 写背景色
     * @param value 整数参数
     */
    public static native void report_CellBase_SetBackColor(int value);
    /**
     * 读前景色
     * @return 返回整数
     */
    public static native int report_CellBase_GetForeColor();
    /**
     * 写前景色
     * @param value 整数参数
     */
    public static native void report_CellBase_SetForeColor(int value);
    /**
     * 读边框自定义
     * @return 返回布尔值
     */
    public static native boolean report_CellBase_GetBorderCustom();
    /**
     * 写边框自定义
     * @param value 布尔参数
     */
    public static native void report_CellBase_SetBorderCustom(boolean value);
    /**
     * 读可伸展
     * @return 返回布尔值
     */
    public static native boolean report_CellBase_GetCanGrow();
    /**
     * 写可伸展
     * @param value 布尔参数
     */
    public static native void report_CellBase_SetCanGrow(boolean value);
    /**
     * 读可收缩
     * @return 返回布尔值
     */
    public static native boolean report_CellBase_GetCanShrink();
    /**
     * 写可收缩
     * @param value 布尔参数
     */
    public static native void report_CellBase_SetCanShrink(boolean value);
    /**
     * 读光标
     * @return 返回整数
     */
    public static native int report_CellBase_GetCursor();
    /**
     * 写光标
     * @param value 整数参数
     */
    public static native void report_CellBase_SetCursor(int value);
    /**
     * 读自由格
     * @return 返回布尔值
     */
    public static native boolean report_CellBase_GetFreeCell();
    /**
     * 写自由格
     * @param value 布尔参数
     */
    public static native void report_CellBase_SetFreeCell(boolean value);
    /**
     * 读显示金额标签
     * @return 返回布尔值
     */
    public static native boolean report_CellBase_GetShowMoneyLabel();
    /**
     * 写显示金额标签
     * @param value 布尔参数
     */
    public static native void report_CellBase_SetShowMoneyLabel(boolean value);
    /**
     * 读显示金额线
     * @return 返回布尔值
     */
    public static native boolean report_CellBase_GetShowMoneyLine();
    /**
     * 写显示金额线
     * @param value 布尔参数
     */
    public static native void report_CellBase_SetShowMoneyLine(boolean value);
    /**
     * 读左边距
     * @return 返回整数
     */
    public static native int report_CellBase_GetPaddingLeft();
    /**
     * 写左边距
     * @param value 整数参数
     */
    public static native void report_CellBase_SetPaddingLeft(int value);
    /**
     * 读右边距
     * @return 返回整数
     */
    public static native int report_CellBase_GetPaddingRight();
    /**
     * 写右边距
     * @param value 整数参数
     */
    public static native void report_CellBase_SetPaddingRight(int value);
    /**
     * 读上边距
     * @return 返回整数
     */
    public static native int report_CellBase_GetPaddingTop();
    /**
     * 写上边距
     * @param value 整数参数
     */
    public static native void report_CellBase_SetPaddingTop(int value);
    /**
     * 读下边距
     * @return 返回整数
     */
    public static native int report_CellBase_GetPaddingBottom();
    /**
     * 写下边距
     * @param value 整数参数
     */
    public static native void report_CellBase_SetPaddingBottom(int value);
    /**
     * 读打印类别
     * @return 返回整数
     */
    public static native int report_CellBase_GetPrintType();
    /**
     * 写打印类别
     * @param value 整数参数
     */
    public static native void report_CellBase_SetPrintType(int value);
    /**
     * 读按需缩小字体
     * @return 返回布尔值
     */
    public static native boolean report_CellBase_GetShrinkFontToFit();
    /**
     * 写按需缩小字体
     * @param value 布尔参数
     */
    public static native void report_CellBase_SetShrinkFontToFit(boolean value);
    /**
     * 读锁定
     * @return 返回整数
     */
    public static native int report_CellBase_GetLock();
    /**
     * 写锁定
     * @param value 整数参数
     */
    public static native void report_CellBase_SetLock(int value);
    /**
     * 读三维效果
     * @return 返回布尔值
     */
    public static native boolean report_Chart_GetChart3D();
    /**
     * 写三维效果
     * @param value 布尔参数
     */
    public static native void report_Chart_SetChart3D(boolean value);
    /**
     * 读组簇自动
     * @return 返回布尔值
     */
    public static native boolean report_Chart_GetGroupAuto();
    /**
     * 写组簇自动
     * @param value 布尔参数
     */
    public static native void report_Chart_SetGroupAuto(boolean value);
    /**
     * 读序列自动
     * @return 返回布尔值
     */
    public static native boolean report_Chart_GetSeriesAuto();
    /**
     * 写序列自动
     * @param value 布尔参数
     */
    public static native void report_Chart_SetSeriesAuto(boolean value);
    /**
     * 读单序列多色显示
     * @return 返回布尔值
     */
    public static native boolean report_Chart_GetSingleSeriesColored();
    /**
     * 写单序列多色显示
     * @param value 布尔参数
     */
    public static native void report_Chart_SetSingleSeriesColored(boolean value);
    /**
     * 读负数按0显示
     * @return 返回布尔值
     */
    public static native boolean report_Chart_GetNegativeAsZero();
    /**
     * 写负数按0显示
     * @param value 布尔参数
     */
    public static native void report_Chart_SetNegativeAsZero(boolean value);
    /**
     * 读图例底部显示
     * @return 返回布尔值
     */
    public static native boolean report_Chart_GetLegendAtBottom();
    /**
     * 写图例底部显示
     * @param value 布尔参数
     */
    public static native void report_Chart_SetLegendAtBottom(boolean value);
    /**
     * 读图例合计显示
     * @return 返回布尔值
     */
    public static native boolean report_Chart_GetLegendShowSum();
    /**
     * 写图例合计显示
     * @param value 布尔参数
     */
    public static native void report_Chart_SetLegendShowSum(boolean value);
    /**
     * 读图例中值可见性
     * @return 返回布尔值
     */
    public static native boolean report_Chart_GetLegendValueVisible();
    /**
     * 写图例中值可见性
     * @param value 布尔参数
     */
    public static native void report_Chart_SetLegendValueVisible(boolean value);
    /**
     * 读图例可见性
     * @return 返回布尔值
     */
    public static native boolean report_Chart_GetLegendVisible();
    /**
     * 写图例可见性
     * @param value 布尔参数
     */
    public static native void report_Chart_SetLegendVisible(boolean value);
    /**
     * 读柱图宽度百分比
     * @return 返回整数
     */
    public static native int report_Chart_GetBarWidthPercent();
    /**
     * 写柱图宽度百分比
     * @param value 整数参数
     */
    public static native void report_Chart_SetBarWidthPercent(int value);
    /**
     * 读填充颜色
     * @return 返回整数
     */
    public static native int report_Chart_GetFillColor();
    /**
     * 写填充颜色
     * @param value 整数参数
     */
    public static native void report_Chart_SetFillColor(int value);
    /**
     * 读自定义填充颜色数
     * @return 返回整数
     */
    public static native int report_Chart_GetFillColorCount();
    /**
     * 读组簇个数
     * @return 返回整数
     */
    public static native int report_Chart_GetGroupCount();
    /**
     * 写组簇个数
     * @param value 整数参数
     */
    public static native void report_Chart_SetGroupCount(int value);
    /**
     * 读图例栏数
     * @return 返回整数
     */
    public static native int report_Chart_GetLegendColumnCount();
    /**
     * 写图例栏数
     * @param value 整数参数
     */
    public static native void report_Chart_SetLegendColumnCount(int value);
    /**
     * 读序列个数
     * @return 返回整数
     */
    public static native int report_Chart_GetSeriesCount();
    /**
     * 写序列个数
     * @param value 整数参数
     */
    public static native void report_Chart_SetSeriesCount(int value);
    /**
     * 读气泡图比例
     * @return 返回小数
     */
    public static native float report_Chart_GetBubbleScalePerCm();
    /**
     * 写气泡图比例
     * @param value 小数参数
     */
    public static native void report_Chart_SetBubbleScalePerCm(float value);
    /**
     * 读组簇字段
     * @return 返回文本
     */
    public static native String report_Chart_GetGroupField();
    /**
     * 写组簇字段
     * @param value 文本参数
     */
    public static native void report_Chart_SetGroupField(String value);
    /**
     * 读图例合计标签
     * @return 返回文本
     */
    public static native String report_Chart_GetLegendSumLabel();
    /**
     * 写图例合计标签
     * @param value 文本参数
     */
    public static native void report_Chart_SetLegendSumLabel(String value);
    /**
     * 读序列字段
     * @return 返回文本
     */
    public static native String report_Chart_GetSeriesField();
    /**
     * 写序列字段
     * @param value 文本参数
     */
    public static native void report_Chart_SetSeriesField(String value);
    /**
     * 读标题
     * @return 返回文本
     */
    public static native String report_Chart_GetTitle();
    /**
     * 写标题
     * @param value 文本参数
     */
    public static native void report_Chart_SetTitle(String value);
    /**
     * 读获取标题字体
     * @return 返回整数
     */
    public static native int report_Chart_GetTitleFont();
    /**
     * 读获取数据值字体
     * @return 返回整数
     */
    public static native int report_Chart_GetValueFont();
    /**
     * 读获取数录集
     * @return 返回整数
     */
    public static native int report_Chart_GetRecordset();
    /**
     * 读获取序列集合
     * @return 返回整数
     */
    public static native int report_Chart_GetSeries();
    /**
     * 读获取X轴
     * @return 返回整数
     */
    public static native int report_Chart_GetXAxis();
    /**
     * 读获取Y轴
     * @return 返回整数
     */
    public static native int report_Chart_GetYAxis();
    /**
     * 读获取Y2轴
     * @return 返回整数
     */
    public static native int report_Chart_GetY2Axis();
    /**
     * 读图例光标
     * @return 返回整数
     */
    public static native int report_Chart_GetLegendCursor();
    /**
     * 写图例光标
     * @param value 整数参数
     */
    public static native void report_Chart_SetLegendCursor(int value);
    /**
     * 增加自定义填充颜色
     * @param value 整数参数
     */
    public static native void report_Chart_AddFillColor(int value);
    /**
     * 增加XY坐标值
     * @param Series 整数参数
     * @param value 小数参数
     * @param value2 小数参数
     */
    public static native void report_Chart_AddXYValue(int Series, float value, float value2);
    /**
     * 增加XYZ坐标值
     * @param Series 整数参数
     * @param value 小数参数
     * @param value2 小数参数
     * @param value3 小数参数
     */
    public static native void report_Chart_AddXYZValue(int Series, float value, float value2, float value3);
    /**
     * 清空自定义填充颜色
     */
    public static native void report_Chart_EmptyFillColors();
    /**
     * 清空全部值
     */
    public static native void report_Chart_EmptyValues();
    /**
     * 从XML载入数据
     * @param XMLData 文本参数
     * @param FirstSeries 布尔参数
     * @param AutoSeriesLabel 布尔参数
     * @param AutoGroupLabel 布尔参数
     * @return 返回布尔值
     */
    public static native boolean report_Chart_LoadDataFromXML(String XMLData, boolean FirstSeries, boolean AutoSeriesLabel, boolean AutoGroupLabel);
    /**
     * 从XML载入XY数据
     * @param XMLData 文本参数
     * @param AutoSeriesLabel 布尔参数
     * @return 返回布尔值
     */
    public static native boolean report_Chart_LoadXYDataFromXML(String XMLData, boolean AutoSeriesLabel);
    /**
     * 从XML载入XYZ数据
     * @param XMLData 文本参数
     * @param AutoSeriesLabel 布尔参数
     * @return 返回布尔值
     */
    public static native boolean report_Chart_LoadXYZDataFromXML(String XMLData, boolean AutoSeriesLabel);
    /**
     * 取组标签
     * @param Group 整数参数
     * @return 返回文本
     */
    public static native String report_Chart_GetGroupLabel(int Group);
    /**
     * 置组标签
     * @param Group 整数参数
     * @param LabelText 文本参数
     */
    public static native void report_Chart_SetGroupLabel(int Group, String LabelText);
    /**
     * 设置组标签
     * @param Group 整数参数
     * @param LabelText 文本参数
     */
    public static native void report_Chart_SetGroupLabel2(int Group, String LabelText);
    /**
     * 取序列标签
     * @param Series 整数参数
     * @return 返回文本
     */
    public static native String report_Chart_GetSeriesLabel(int Series);
    /**
     * 置序列标签
     * @param Series 整数参数
     * @param LabelText 文本参数
     */
    public static native void report_Chart_SetSeriesLabel(int Series, String LabelText);
    /**
     * 设置序列标签
     * @param Series 整数参数
     * @param LabelText 文本参数
     */
    public static native void report_Chart_SetSeriesLabel2(int Series, String LabelText);
    /**
     * 取值
     * @param Series 整数参数
     * @param Group 整数参数
     * @return 返回小数
     */
    public static native float report_Chart_GetValue(int Series, int Group);
    /**
     * 置值
     * @param Series 整数参数
     * @param Group 整数参数
     * @param value 小数参数
     */
    public static native void report_Chart_SetValue(int Series, int Group, float value);
    /**
     * 设置值
     * @param Series 整数参数
     * @param Group 整数参数
     * @param value 小数参数
     */
    public static native void report_Chart_SetValue2(int Series, int Group, float value);
    /**
     * 取值文本
     * @param Series 整数参数
     * @param Group 整数参数
     * @return 返回文本
     */
    public static native String report_Chart_GetValueText(int Series, int Group);
    /**
     * 置值文本
     * @param Series 整数参数
     * @param Group 整数参数
     * @param ValueText 文本参数
     */
    public static native void report_Chart_SetValueText(int Series, int Group, String ValueText);
    /**
     * 读标签
     * @return 返回文本
     */
    public static native String report_ChartAxis_GetLabel();
    /**
     * 写标签
     * @param value 文本参数
     */
    public static native void report_ChartAxis_SetLabel(String value);
    /**
     * 读刻度格式
     * @return 返回文本
     */
    public static native String report_ChartAxis_GetTextFormat();
    /**
     * 写刻度格式
     * @param value 文本参数
     */
    public static native void report_ChartAxis_SetTextFormat(String value);
    /**
     * 读坐标线可见性
     * @return 返回布尔值
     */
    public static native boolean report_ChartAxis_GetCoordLineVisible();
    /**
     * 写坐标线可见性
     * @param value 布尔参数
     */
    public static native void report_ChartAxis_SetCoordLineVisible(boolean value);
    /**
     * 读轴线可见性
     * @return 返回布尔值
     */
    public static native boolean report_ChartAxis_GetLineVisible();
    /**
     * 写轴线可见性
     * @param value 布尔参数
     */
    public static native void report_ChartAxis_SetLineVisible(boolean value);
    /**
     * 读文字可见性
     * @return 返回布尔值
     */
    public static native boolean report_ChartAxis_GetTextVisible();
    /**
     * 写文字可见性
     * @param value 布尔参数
     */
    public static native void report_ChartAxis_SetTextVisible(boolean value);
    /**
     * 读获取坐标线画笔
     * @return 返回整数
     */
    public static native int report_ChartAxis_GetCoordLinePen();
    /**
     * 读获取轴线画笔
     * @return 返回整数
     */
    public static native int report_ChartAxis_GetLinePen();
    /**
     * 读刻度个数
     * @return 返回整数
     */
    public static native int report_ChartAxis_GetScaleCount();
    /**
     * 读刻度步长
     * @return 返回小数
     */
    public static native float report_ChartAxis_GetSpace();
    /**
     * 写刻度步长
     * @param value 小数参数
     */
    public static native void report_ChartAxis_SetSpace(float value);
    /**
     * 读最大值
     * @return 返回小数
     */
    public static native float report_ChartAxis_GetMax();
    /**
     * 写最大值
     * @param value 小数参数
     */
    public static native void report_ChartAxis_SetMax(float value);
    /**
     * 读最小值
     * @return 返回小数
     */
    public static native float report_ChartAxis_GetMin();
    /**
     * 写最小值
     * @param value 小数参数
     */
    public static native void report_ChartAxis_SetMin(float value);
    /**
     * 读文字角度
     * @return 返回小数
     */
    public static native float report_ChartAxis_GetTextAngle();
    /**
     * 写文字角度
     * @param value 小数参数
     */
    public static native void report_ChartAxis_SetTextAngle(float value);
    /**
     * 读留起始空白程度
     * @return 返回整数
     */
    public static native int report_ChartAxis_GetMarginBeginWeight();
    /**
     * 写留起始空白程度
     * @param value 整数参数
     */
    public static native void report_ChartAxis_SetMarginBeginWeight(int value);
    /**
     * 读留结束空白程度
     * @return 返回整数
     */
    public static native int report_ChartAxis_GetMarginEndWeight();
    /**
     * 写留结束空白程度
     * @param value 整数参数
     */
    public static native void report_ChartAxis_SetMarginEndWeight(int value);
    /**
     * 读日期时间轴
     * @return 返回布尔值
     */
    public static native boolean report_ChartAxis_GetDateTimeAxis();
    /**
     * 写日期时间轴
     * @param value 布尔参数
     */
    public static native void report_ChartAxis_SetDateTimeAxis(boolean value);
    /**
     * 读最大值在刻度
     * @return 返回布尔值
     */
    public static native boolean report_ChartAxis_GetMaxAtScale();
    /**
     * 写最大值在刻度
     * @param value 布尔参数
     */
    public static native void report_ChartAxis_SetMaxAtScale(boolean value);
    /**
     * 增加自定义刻度
     * @param value 小数参数
     * @param DisplayText 文本参数
     */
    public static native void report_ChartAxis_AddCustomScale(float value, String DisplayText);
    /**
     * 增加自定义坐标线
     * @param value 小数参数
     * @param DisplayText 文本参数
     * @param LineWidth 小数参数
     * @param LineColor 整数参数
     * @param PenStyle 整数参数
     */
    public static native void report_ChartAxis_AddCustomCoordLine(float value, String DisplayText, float LineWidth, int LineColor, int PenStyle);
    /**
     * 清除自定义刻度
     */
    public static native void report_ChartAxis_EmptyCustomScale();
    /**
     * 清除自定义坐标线
     */
    public static native void report_ChartAxis_EmptyCustomCoordLine();
    /**
     * 取刻度文本
     * @param Scale 整数参数
     * @return 返回文本
     */
    public static native String report_ChartAxis_GetScaleText(int Scale);
    /**
     * 取刻度值
     * @param Scale 整数参数
     * @return 返回小数
     */
    public static native float report_ChartAxis_GetScaleValue(int Scale);
    /**
     * 置对象
     * @param value 整数参数
     */
    public static native void report_ChartSeries_SetObject(int value);
    /**
     * 读标签文本
     * @return 返回文本
     */
    public static native String report_ChartSeries_GetLabelText();
    /**
     * 写标签文本
     * @param value 文本参数
     */
    public static native void report_ChartSeries_SetLabelText(String value);
    /**
     * 读标签文本角度
     * @return 返回小数
     */
    public static native float report_ChartSeries_GetLabelTextAngle();
    /**
     * 写标签文本角度
     * @param value 小数参数
     */
    public static native void report_ChartSeries_SetLabelTextAngle(float value);
    /**
     * 读提示文本
     * @return 返回文本
     */
    public static native String report_ChartSeries_GetTooltipText();
    /**
     * 写提示文本
     * @param value 文本参数
     */
    public static native void report_ChartSeries_SetTooltipText(String value);
    /**
     * 读数据格式
     * @return 返回文本
     */
    public static native String report_ChartSeries_GetValueFormat();
    /**
     * 写数据格式
     * @param value 文本参数
     */
    public static native void report_ChartSeries_SetValueFormat(String value);
    /**
     * 读序列名称
     * @return 返回文本
     */
    public static native String report_ChartSeries_GetSeriesName();
    /**
     * 写序列名称
     * @param value 文本参数
     */
    public static native void report_ChartSeries_SetSeriesName(String value);
    /**
     * 读X值字段
     * @return 返回文本
     */
    public static native String report_ChartSeries_GetXValueField();
    /**
     * 写X值字段
     * @param value 文本参数
     */
    public static native void report_ChartSeries_SetXValueField(String value);
    /**
     * 读Y值字段
     * @return 返回文本
     */
    public static native String report_ChartSeries_GetYValueField();
    /**
     * 写Y值字段
     * @param value 文本参数
     */
    public static native void report_ChartSeries_SetYValueField(String value);
    /**
     * 读Z值字段
     * @return 返回文本
     */
    public static native String report_ChartSeries_GetZValueField();
    /**
     * 写Z值字段
     * @param value 文本参数
     */
    public static native void report_ChartSeries_SetZValueField(String value);
    /**
     * 读应用Y2座标轴
     * @return 返回布尔值
     */
    public static native boolean report_ChartSeries_GetByY2Axis();
    /**
     * 写应用Y2座标轴
     * @param value 布尔参数
     */
    public static native void report_ChartSeries_SetByY2Axis(boolean value);
    /**
     * 读填充颜色自动
     * @return 返回布尔值
     */
    public static native boolean report_ChartSeries_GetFillColorAuto();
    /**
     * 写填充颜色自动
     * @param value 布尔参数
     */
    public static native void report_ChartSeries_SetFillColorAuto(boolean value);
    /**
     * 读按组显示标签
     * @return 返回布尔值
     */
    public static native boolean report_ChartSeries_GetLabelAsGroup();
    /**
     * 写按组显示标签
     * @param value 布尔参数
     */
    public static native void report_ChartSeries_SetLabelAsGroup(boolean value);
    /**
     * 读标签在柱形中
     * @return 返回布尔值
     */
    public static native boolean report_ChartSeries_GetLabelInBar();
    /**
     * 写标签在柱形中
     * @param value 布尔参数
     */
    public static native void report_ChartSeries_SetLabelInBar(boolean value);
    /**
     * 读标记颜色自动
     * @return 返回布尔值
     */
    public static native boolean report_ChartSeries_GetMarkerColorAuto();
    /**
     * 写标记颜色自动
     * @param value 布尔参数
     */
    public static native void report_ChartSeries_SetMarkerColorAuto(boolean value);
    /**
     * 读获取图形画笔
     * @return 返回整数
     */
    public static native int report_ChartSeries_GetBorderPen();
    /**
     * 读获取标记画笔
     * @return 返回整数
     */
    public static native int report_ChartSeries_GetMarkerPen();
    /**
     * 读图表类型
     * @return 返回整数
     */
    public static native int report_ChartSeries_GetChartType();
    /**
     * 写图表类型
     * @param value 整数参数
     */
    public static native void report_ChartSeries_SetChartType(int value);
    /**
     * 读标记类型
     * @return 返回整数
     */
    public static native int report_ChartSeries_GetMarkerStyle();
    /**
     * 写标记类型
     * @param value 整数参数
     */
    public static native void report_ChartSeries_SetMarkerStyle(int value);
    /**
     * 读填充颜色
     * @return 返回整数
     */
    public static native int report_ChartSeries_GetFillColor();
    /**
     * 写填充颜色
     * @param value 整数参数
     */
    public static native void report_ChartSeries_SetFillColor(int value);
    /**
     * 读标记颜色
     * @return 返回整数
     */
    public static native int report_ChartSeries_GetMarkerColor();
    /**
     * 写标记颜色
     * @param value 整数参数
     */
    public static native void report_ChartSeries_SetMarkerColor(int value);
    /**
     * 读标记大小
     * @return 返回整数
     */
    public static native int report_ChartSeries_GetMarkerSize();
    /**
     * 写标记大小
     * @param value 整数参数
     */
    public static native void report_ChartSeries_SetMarkerSize(int value);
    /**
     * 数目
     * @return 返回整数
     */
    public static native int report_ChartSeriesCollection_GetCount();
    /**
     * 获取项目_变体型
     * @param value 整数参数
     * @return 返回整数
     */
    public static native int report_ChartSeriesCollection_GetItemVariant(int value);
    /**
     * 获取项目_文本型
     * @param value 文本参数
     * @return 返回整数
     */
    public static native int report_ChartSeriesCollection_GetItemString(String value);
    /**
     * 按名称获取项目
     * @param Name 文本参数
     * @return 返回整数
     */
    public static native int report_ChartSeriesCollection_GetItemByName(String Name);
    /**
     * 按序号获取项目
     * @param Index 整数参数
     * @return 返回整数
     */
    public static native int report_ChartSeriesCollection_GetItemBy(int Index);
    /**
     * 增加
     * @return 返回整数
     */
    public static native int report_ChartSeriesCollection_Add();
    /**
     * 改变项目顺序_整数
     * @param value 整数参数
     * @param Sequence 整数参数
     */
    public static native void report_ChartSeriesCollection_ChangeItemOrderInt(int value, int Sequence);
    /**
     * 改变项目顺序_文本型
     * @param value 文本参数
     * @param Sequence 整数参数
     */
    public static native void report_ChartSeriesCollection_ChangeItemOrderString(String value, int Sequence);
    /**
     * 改变项目顺序_名称
     * @param Name 文本参数
     * @param Sequence 整数参数
     */
    public static native void report_ChartSeriesCollection_ChangeItemOrderByName(String Name, int Sequence);
    /**
     * 改变项目顺序_序号
     * @param Index 整数参数
     * @param Sequence 整数参数
     */
    public static native void report_ChartSeriesCollection_ChangeItemOrderBy(int Index, int Sequence);
    /**
     * 按名称取序号
     * @param Name 文本参数
     * @return 返回整数
     */
    public static native int report_ChartSeriesCollection_ByName(String Name);
    /**
     * 获取指定项目
     * @param Index 整数参数
     * @return 返回整数
     */
    public static native int report_ChartSeriesCollection_ItemAt(int Index);
    /**
     * 删除项目_文本型
     * @param value 文本参数
     */
    public static native void report_ChartSeriesCollection_RemoveString(String value);
    /**
     * 删除项目_整数
     * @param value 整数参数
     */
    public static native void report_ChartSeriesCollection_RemoveInt(int value);
    /**
     * 按名称删除项目
     * @param Name 文本参数
     */
    public static native void report_ChartSeriesCollection_RemoveByName(String Name);
    /**
     * 按序号删除项目
     * @param Index 整数参数
     */
    public static native void report_ChartSeriesCollection_RemoveBy(int Index);
    /**
     * 全部删除
     */
    public static native void report_ChartSeriesCollection_RemoveAll();
    /**
     * 读获取父对象
     * @return 返回整数
     */
    public static native int report_Column_GetParent();
    /**
     * 写获取父对象
     * @param addressCOM 整数参数
     */
    public static native void report_Column_SetParent(int addressCOM);
    /**
     * 读获取内容格
     * @return 返回整数
     */
    public static native int report_Column_GetContentCell();
    /**
     * 写获取内容格
     * @param addressCOM 整数参数
     */
    public static native void report_Column_SetContentCell(int addressCOM);
    /**
     * 读名称
     * @return 返回文本
     */
    public static native String report_Column_GetName();
    /**
     * 写名称
     * @param value 文本参数
     */
    public static native void report_Column_SetName(String value);
    /**
     * 读可见性
     * @return 返回布尔值
     */
    public static native boolean report_Column_GetVisible();
    /**
     * 写可见性
     * @param value 布尔参数
     */
    public static native void report_Column_SetVisible(boolean value);
    /**
     * 读宽度固定
     * @return 返回布尔值
     */
    public static native boolean report_Column_GetFixedWidth();
    /**
     * 写宽度固定
     * @param value 布尔参数
     */
    public static native void report_Column_SetFixedWidth(boolean value);
    /**
     * 读宽度
     * @return 返回小数
     */
    public static native float report_Column_GetWidth();
    /**
     * 写宽度
     * @param value 小数参数
     */
    public static native void report_Column_SetWidth(float value);
    /**
     * 读锁定
     * @return 返回整数
     */
    public static native int report_Column_GetLock();
    /**
     * 写锁定
     * @param value 整数参数
     */
    public static native void report_Column_SetLock(int value);
    /**
     * 读获取列
     * @return 返回整数
     */
    public static native int report_ColumnCell_GetColumn();
    /**
     * 读名称
     * @return 返回文本
     */
    public static native String report_ColumnCell_GetName();
    /**
     * 写名称
     * @param Name 文本参数
     */
    public static native void report_ColumnCell_SetName(String Name);
    /**
     * 读调整行高
     * @return 返回布尔值
     */
    public static native boolean report_ColumnContent_GetAdjustRowHeight();
    /**
     * 写调整行高
     * @param value 布尔参数
     */
    public static native void report_ColumnContent_SetAdjustRowHeight(boolean value);
    /**
     * 读伸展到下行
     * @return 返回布尔值
     */
    public static native boolean report_ColumnContent_GetGrowToNextRow();
    /**
     * 写伸展到下行
     * @param value 布尔参数
     */
    public static native void report_ColumnContent_SetGrowToNextRow(boolean value);
    /**
     * 读每页行数包含分组
     * @return 返回布尔值
     */
    public static native boolean report_ColumnContent_GetRowsIncludeGroup();
    /**
     * 写每页行数包含分组
     * @param value 布尔参数
     */
    public static native void report_ColumnContent_SetRowsIncludeGroup(boolean value);
    /**
     * 读交替背景色
     * @return 返回整数
     */
    public static native int report_ColumnContent_GetAlternatingBackColor();
    /**
     * 写交替背景色
     * @param value 整数参数
     */
    public static native void report_ColumnContent_SetAlternatingBackColor(int value);
    /**
     * 读每页行数
     * @return 返回整数
     */
    public static native int report_ColumnContent_GetRowsPerPage();
    /**
     * 写每页行数
     * @param value 整数参数
     */
    public static native void report_ColumnContent_SetRowsPerPage(int value);
    /**
     * 读获取内容格集合
     * @return 返回整数
     */
    public static native int report_ColumnContent_GetContentCells();
    /**
     * 读获取父对象
     * @return 返回整数
     */
    public static native int report_ColumnContentCel_GetParent();
    /**
     * 读数据字段
     * @return 返回文本
     */
    public static native String report_ColumnContentCel_GetDataField();
    /**
     * 写数据字段
     * @param FieldName 文本参数
     */
    public static native void report_ColumnContentCel_SetDataField(String FieldName);
    /**
     * 读格式
     * @return 返回文本
     */
    public static native String report_ColumnContentCel_GetFormat();
    /**
     * 写格式
     * @param FormatText 文本参数
     */
    public static native void report_ColumnContentCel_SetFormat(String FormatText);
    /**
     * 数目
     * @return 返回整数
     */
    public static native int report_ColumnContentCells_GetCount();
    /**
     * 获取项目_变体型
     * @param value 整数参数
     * @return 返回整数
     */
    public static native int report_ColumnContentCells_GetItemVariant(int value);
    /**
     * 获取项目_文本型
     * @param NamePointer 文本参数
     * @return 返回整数
     */
    public static native int report_ColumnContentCells_GetItemString(String NamePointer);
    /**
     * 按名称获取项目
     * @param Name 文本参数
     * @return 返回整数
     */
    public static native int report_ColumnContentCells_GetItemByName(String Name);
    /**
     * 按序号获取项目
     * @param Index 整数参数
     * @return 返回整数
     */
    public static native int report_ColumnContentCells_GetItemBy(int Index);
    /**
     * 按名称取序号
     * @param Name 文本参数
     * @return 返回整数
     */
    public static native int report_ColumnContentCells_ByName(String Name);
    /**
     * 获取指定项目
     * @param Index 整数参数
     * @return 返回整数
     */
    public static native int report_ColumnContentCells_ItemAt(int Index);
    /**
     * 读光标
     * @return 返回整数
     */
    public static native int report_ColumnSection_GetCursor();
    /**
     * 写光标
     * @param value 整数参数
     */
    public static native void report_ColumnSection_SetCursor(int value);
    /**
     * 读获取明细网格
     * @return 返回整数
     */
    public static native int report_ColumnSection_GetDetailGrid();
    /**
     * 设置全部格背景色
     * @param BackgroundColor 整数参数
     */
    public static native void report_ColumnSection_SetCellsBackColor(int BackgroundColor);
    /**
     * 设置全部格前景色
     * @param ForegroundColor 整数参数
     */
    public static native void report_ColumnSection_SetCellsForeColor(int ForegroundColor);
    /**
     * 读在页组头前显示
     * @return 返回布尔值
     */
    public static native boolean report_ColumnTitle_GetBeforeHeaders();
    /**
     * 写在页组头前显示
     * @param value 布尔参数
     */
    public static native void report_ColumnTitle_SetBeforeHeaders(boolean value);
    /**
     * 读重复打印方式
     * @return 返回整数
     */
    public static native int report_ColumnTitle_GetRepeatStyle();
    /**
     * 写重复打印方式
     * @param value 整数参数
     */
    public static native void report_ColumnTitle_SetRepeatStyle(int value);
    /**
     * 取标题格集合
     * @return 返回整数
     */
    public static native int report_ColumnTitle_GetTitleCells();
    /**
     * 取父对象
     * @return 返回整数
     */
    public static native int report_ColumnTitleCell_GetParent();
    /**
     * 读组标题格
     * @return 返回布尔值
     */
    public static native boolean report_ColumnTitleCell_GetGroupTitle();
    /**
     * 读可见性
     * @return 返回布尔值
     */
    public static native boolean report_ColumnTitleCell_GetVisible();
    /**
     * 写可见性
     * @param value 布尔参数
     */
    public static native void report_ColumnTitleCell_SetVisible(boolean value);
    /**
     * 取下级标题格集合
     * @return 返回整数
     */
    public static native int report_ColumnTitleCell_GetSubTitles();
    /**
     * 取上级组标题格
     * @return 返回整数
     */
    public static native int report_ColumnTitleCell_GetSupCell();
    /**
     * 读文本
     * @return 返回文本
     */
    public static native String report_ColumnTitleCell_GetText();
    /**
     * 写文本
     * @param value 文本参数
     */
    public static native void report_ColumnTitleCell_SetText(String value);
    /**
     * 读高度
     * @return 返回小数
     */
    public static native float report_ColumnTitleCell_GetHeight();
    /**
     * 写高度
     * @param value 小数参数
     */
    public static native void report_ColumnTitleCell_SetHeight(float value);
    /**
     * 增加下级组标题格
     * @param name 文本参数
     * @param Caption 文本参数
     * @return 返回整数
     */
    public static native int report_ColumnTitleCell_AddSubGroupTitle(String name, String Caption);
    /**
     * 包含列整数
     * @param NameOrIndex 整数参数
     */
    public static native void report_ColumnTitleCell_EncloseColumnInt(int NameOrIndex);
    /**
     * 包含列文本
     * @param NameOrIndex 文本参数
     */
    public static native void report_ColumnTitleCell_EncloseColumnString(String NameOrIndex);
    /**
     * 包含指定列名
     * @param ColumnName 文本参数
     */
    public static native void report_ColumnTitleCell_EncloseColumnByName(String ColumnName);
    /**
     * 包含指定列号
     * @param ColumnIndex 整数参数
     */
    public static native void report_ColumnTitleCell_EncloseColumnByIndex(int ColumnIndex);
    /**
     * 数目
     * @return 返回整数
     */
    public static native int report_ColumnTitleCells_GetCount();
    /**
     * 获取项目_变体型
     * @param value 整数参数
     * @return 返回整数
     */
    public static native int report_ColumnTitleCells_GetItemVariant(int value);
    /**
     * 获取项目_文本型
     * @param NamePointer 文本参数
     * @return 返回整数
     */
    public static native int report_ColumnTitleCells_GetItemString(String NamePointer);
    /**
     * 按名称获取项目
     * @param Name 文本参数
     * @return 返回整数
     */
    public static native int report_ColumnTitleCells_GetItemByName(String Name);
    /**
     * 按序号获取项目
     * @param Index 整数参数
     * @return 返回整数
     */
    public static native int report_ColumnTitleCells_GetItemBy(int Index);
    /**
     * 增加组标题格
     * @param Name 文本参数
     * @param Title 文本参数
     * @return 返回整数
     */
    public static native int report_ColumnTitleCells_AddGroup(String Name, String Title);
    /**
     * 改变项目顺序_整数
     * @param value 整数参数
     * @param Sequence 整数参数
     */
    public static native void report_ColumnTitleCells_ChangeItemOrderInt(int value, int Sequence);
    /**
     * 改变项目顺序_文本型
     * @param NamePointer 文本参数
     * @param Sequence 整数参数
     */
    public static native void report_ColumnTitleCells_ChangeItemOrderString(String NamePointer, int Sequence);
    /**
     * 改变项目顺序_名称
     * @param Name 文本参数
     * @param Sequence 整数参数
     */
    public static native void report_ColumnTitleCells_ChangeItemOrderByName(String Name, int Sequence);
    /**
     * 改变项目顺序_序号
     * @param Index 整数参数
     * @param Sequence 整数参数
     */
    public static native void report_ColumnTitleCells_ChangeItemOrderBy(int Index, int Sequence);
    /**
     * 按名称取序号
     * @param Name 文本参数
     * @return 返回整数
     */
    public static native int report_ColumnTitleCells_ByName(String Name);
    /**
     * 获取指定项目
     * @param Index 整数参数
     * @return 返回整数
     */
    public static native int report_ColumnTitleCells_ItemAt(int Index);
    /**
     * 删除项目_文本型
     * @param NamePointer 文本参数
     */
    public static native void report_ColumnTitleCells_RemoveString(String NamePointer);
    /**
     * 删除项目_整数
     * @param value 整数参数
     */
    public static native void report_ColumnTitleCells_RemoveInt(int value);
    /**
     * 按名称删除项目
     * @param Name 文本参数
     */
    public static native void report_ColumnTitleCells_RemoveByName(String Name);
    /**
     * 按序号删除项目
     * @param Index 整数参数
     */
    public static native void report_ColumnTitleCells_RemoveBy(int Index);
    /**
     * 数目
     * @return 返回整数
     */
    public static native int report_Columns_GetCount();
    /**
     * 获取项目_变体型
     * @param value 整数参数
     * @return 返回整数
     */
    public static native int report_Columns_GetItemVariant(int value);
    /**
     * 获取项目_文本型
     * @param NamePointer 文本参数
     * @return 返回整数
     */
    public static native int report_Columns_GetItemString(String NamePointer);
    /**
     * 按名称获取项目
     * @param Name 文本参数
     * @return 返回整数
     */
    public static native int report_Columns_GetItemByName(String Name);
    /**
     * 按序号获取项目
     * @param Index 整数参数
     * @return 返回整数
     */
    public static native int report_Columns_GetItemBy(int Index);
    /**
     * 增加
     * @return 返回整数
     */
    public static native int report_Columns_Add();
    /**
     * 改变项目顺序_整数
     * @param value 整数参数
     * @param Sequence 整数参数
     */
    public static native void report_Columns_ChangeItemOrderInt(int value, int Sequence);
    /**
     * 改变项目顺序_文本型
     * @param NamePointer 文本参数
     * @param Sequence 整数参数
     */
    public static native void report_Columns_ChangeItemOrderString(String NamePointer, int Sequence);
    /**
     * 改变项目顺序_名称
     * @param Name 文本参数
     * @param Sequence 整数参数
     */
    public static native void report_Columns_ChangeItemOrderByName(String Name, int Sequence);
    /**
     * 改变项目顺序_序号
     * @param Index 整数参数
     * @param Sequence 整数参数
     */
    public static native void report_Columns_ChangeItemOrderBy(int Index, int Sequence);
    /**
     * 按名称取序号
     * @param Name 文本参数
     * @return 返回整数
     */
    public static native int report_Columns_ByName(String Name);
    /**
     * 获取指定项目
     * @param Index 整数参数
     * @return 返回整数
     */
    public static native int report_Columns_ItemAt(int Index);
    /**
     * 删除项目_文本型
     * @param NamePointer 文本参数
     */
    public static native void report_Columns_RemoveString(String NamePointer);
    /**
     * 删除项目_整数
     * @param value 整数参数
     */
    public static native void report_Columns_RemoveInt(int value);
    /**
     * 按名称删除项目
     * @param Name 文本参数
     */
    public static native void report_Columns_RemoveByName(String Name);
    /**
     * 按序号删除项目
     * @param Index 整数参数
     */
    public static native void report_Columns_RemoveBy(int Index);
    /**
     * 全部删除
     */
    public static native void report_Columns_RemoveAll();
    /**
     * 读名称
     * @return 返回文本
     */
    public static native String report_Control_GetName();
    /**
     * 写名称
     * @param Name 文本参数
     */
    public static native void report_Control_SetName(String Name);
    /**
     * 读获取父对象
     * @return 返回整数
     */
    public static native int report_Control_GetParent();
    /**
     * 读获取为文字框
     * @return 返回整数
     */
    public static native int report_Control_GetAsTextBox();
    /**
     * 读获取为图表框
     * @return 返回整数
     */
    public static native int report_Control_GetAsChart();
    /**
     * 读获取为静态文字框
     * @return 返回整数
     */
    public static native int report_Control_GetAsStaticBox();
    /**
     * 读获取为图形框
     * @return 返回整数
     */
    public static native int report_Control_GetAsShapeBox();
    /**
     * 读获取为系统变量框
     * @return 返回整数
     */
    public static native int report_Control_GetAsSystemVarBox();
    /**
     * 读获取为字段框
     * @return 返回整数
     */
    public static native int report_Control_GetAsFieldBox();
    /**
     * 读获取为统计框
     * @return 返回整数
     */
    public static native int report_Control_GetAsSummaryBox();
    /**
     * 读获取为图像框
     * @return 返回整数
     */
    public static native int report_Control_GetAsPictureBox();
    /**
     * 读获取为RTF文字框
     * @return 返回整数
     */
    public static native int report_Control_GetAsRichTextBox();
    /**
     * 读获取为综合文字框
     * @return 返回整数
     */
    public static native int report_Control_GetAsMemoBox();
    /**
     * 读获取为子报表框
     * @return 返回整数
     */
    public static native int report_Control_GetAsSubReport();
    /**
     * 读获取为线段框
     * @return 返回整数
     */
    public static native int report_Control_GetAsLine();
    /**
     * 读获取为条码框
     * @return 返回整数
     */
    public static native int report_Control_GetAsBarcode();
    /**
     * 读获取为自由表格
     * @return 返回整数
     */
    public static native int report_Control_GetAsFreeGrid();
    /**
     * 读获取边框
     * @return 返回整数
     */
    public static native int report_Control_GetBorder();
    /**
     * 读获取字体
     * @return 返回整数
     */
    public static native int report_Control_GetFont();
    /**
     * 读部件框类型
     * @return 返回整数
     */
    public static native int report_Control_GetControlType();
    /**
     * 读光标
     * @return 返回整数
     */
    public static native int report_Control_GetCursor();
    /**
     * 写光标
     * @param value 整数参数
     */
    public static native void report_Control_SetCursor(int value);
    /**
     * 读背景色
     * @return 返回整数
     */
    public static native int report_Control_GetBackColor();
    /**
     * 写背景色
     * @param value 整数参数
     */
    public static native void report_Control_SetBackColor(int value);
    /**
     * 读背景模式
     * @return 返回整数
     */
    public static native int report_Control_GetBackStyle();
    /**
     * 写背景模式
     * @param value 整数参数
     */
    public static native void report_Control_SetBackStyle(int value);
    /**
     * 读居中方式
     * @return 返回整数
     */
    public static native int report_Control_GetCenter();
    /**
     * 写居中方式
     * @param value 整数参数
     */
    public static native void report_Control_SetCenter(int value);
    /**
     * 读自定义绘制
     * @return 返回布尔值
     */
    public static native boolean report_Control_GetCustomDraw();
    /**
     * 写自定义绘制
     * @param value 布尔参数
     */
    public static native void report_Control_SetCustomDraw(boolean value);
    /**
     * 读自定义绘制脚本
     * @return 返回文本
     */
    public static native String report_Control_GetCustomDrawScript();
    /**
     * 写自定义绘制脚本
     * @param ScriptText 文本参数
     */
    public static native void report_Control_SetCustomDrawScript(String ScriptText);
    /**
     * 读停靠方式
     * @return 返回整数
     */
    public static native int report_Control_GetDock();
    /**
     * 写停靠方式
     * @param value 整数参数
     */
    public static native void report_Control_SetDock(int value);
    /**
     * 读前景色
     * @return 返回整数
     */
    public static native int report_Control_GetForeColor();
    /**
     * 写前景色
     * @param value 整数参数
     */
    public static native void report_Control_SetForeColor(int value);
    /**
     * 读左
     * @return 返回小数
     */
    public static native float report_Control_GetLeft();
    /**
     * 写左
     * @param value 小数参数
     */
    public static native void report_Control_SetLeft(float value);
    /**
     * 读上
     * @return 返回小数
     */
    public static native float report_Control_GetTop();
    /**
     * 写上
     * @param value 小数参数
     */
    public static native void report_Control_SetTop(float value);
    /**
     * 读宽度
     * @return 返回小数
     */
    public static native float report_Control_GetWidth();
    /**
     * 写宽度
     * @param value 小数参数
     */
    public static native void report_Control_SetWidth(float value);
    /**
     * 读高度
     * @return 返回小数
     */
    public static native float report_Control_GetHeight();
    /**
     * 写高度
     * @param value 小数参数
     */
    public static native void report_Control_SetHeight(float value);
    /**
     * 读位置锁定
     * @return 返回布尔值
     */
    public static native boolean report_Control_GetLocked();
    /**
     * 写位置锁定
     * @param value 布尔参数
     */
    public static native void report_Control_SetLocked(boolean value);
    /**
     * 读左边距
     * @return 返回整数
     */
    public static native int report_Control_GetPaddingLeft();
    /**
     * 写左边距
     * @param value 整数参数
     */
    public static native void report_Control_SetPaddingLeft(int value);
    /**
     * 读右边距
     * @return 返回整数
     */
    public static native int report_Control_GetPaddingRight();
    /**
     * 写右边距
     * @param value 整数参数
     */
    public static native void report_Control_SetPaddingRight(int value);
    /**
     * 读上边距
     * @return 返回整数
     */
    public static native int report_Control_GetPaddingTop();
    /**
     * 写上边距
     * @param value 整数参数
     */
    public static native void report_Control_SetPaddingTop(int value);
    /**
     * 读下边距
     * @return 返回整数
     */
    public static native int report_Control_GetPaddingBottom();
    /**
     * 写下边距
     * @param value 整数参数
     */
    public static native void report_Control_SetPaddingBottom(int value);
    /**
     * 读打印类别
     * @return 返回整数
     */
    public static native int report_Control_GetPrintType();
    /**
     * 写打印类别
     * @param value 整数参数
     */
    public static native void report_Control_SetPrintType(int value);
    /**
     * 读伸展位移
     * @return 返回整数
     */
    public static native int report_Control_GetShiftMode();
    /**
     * 写伸展位移
     * @param value 整数参数
     */
    public static native void report_Control_SetShiftMode(int value);
    /**
     * 读锚定
     * @return 返回整数
     */
    public static native int report_Control_GetAnchor();
    /**
     * 写锚定
     * @param value 整数参数
     */
    public static native void report_Control_SetAnchor(int value);
    /**
     * 读对齐列
     * @return 返回文本
     */
    public static native String report_Control_GetAlignColumn();
    /**
     * 写对齐列
     * @param ColumnName 文本参数
     */
    public static native void report_Control_SetAlignColumn(String ColumnName);
    /**
     * 读对齐列扩展
     * @return 返回文本
     */
    public static native String report_Control_GetAlignColumnEx();
    /**
     * 写对齐列扩展
     * @param ColumnName 文本参数
     */
    public static native void report_Control_SetAlignColumnEx(String ColumnName);
    /**
     * 读对齐列方式
     * @return 返回整数
     */
    public static native int report_Control_GetAlignColumnSide();
    /**
     * 写对齐列方式
     * @param value 整数参数
     */
    public static native void report_Control_SetAlignColumnSide(int value);
    /**
     * 读可见性
     * @return 返回布尔值
     */
    public static native boolean report_Control_GetVisible();
    /**
     * 写可见性
     * @param value 布尔参数
     */
    public static native void report_Control_SetVisible(boolean value);
    /**
     * 读标识
     * @return 返回文本
     */
    public static native String report_Control_GetTag();
    /**
     * 写标识
     * @param IDText 文本参数
     */
    public static native void report_Control_SetTag(String IDText);
    /**
     * 读锁定
     * @return 返回整数
     */
    public static native int report_Control_GetLock();
    /**
     * 写锁定
     * @param value 整数参数
     */
    public static native void report_Control_SetLock(int value);
    /**
     * 设置边界
     * @param value 小数参数
     * @param value2 小数参数
     * @param value3 小数参数
     * @param value4 小数参数
     */
    public static native void report_Control_SetBounds(float value, float value2, float value3, float value4);
    /**
     * 默认绘制
     */
    public static native void report_Control_DrawDefault();
    /**
     * 显示到前端
     */
    public static native void report_Control_BringToFront();
    /**
     * 显示到后端
     */
    public static native void report_Control_SendToBack();
    /**
     * 载入鼠标指针
     * @param FileName 文本参数
     */
    public static native void report_Control_LoadCursorFile(String FileName);
    /**
     * 读数目
     * @return 返回整数
     */
    public static native int report_Controls_GetCount();
    /**
     * 获取项目_文本型
     * @param value 文本参数
     * @return 返回整数
     */
    public static native int report_Controls_GetItemString(String value);
    /**
     * 获取项目_整数
     * @param value 整数参数
     * @return 返回整数
     */
    public static native int report_Controls_GetItemInt(int value);
    /**
     * 按名称获取项目
     * @param Name 文本参数
     * @return 返回整数
     */
    public static native int report_Controls_GetItemByName(String Name);
    /**
     * 按序号获取项目
     * @param Index 整数参数
     * @return 返回整数
     */
    public static native int report_Controls_GetItemBy(int Index);
    /**
     * 增加
     * @param value 整数参数
     * @return 返回整数
     */
    public static native int report_Controls_Add(int value);
    /**
     * 改变项目顺序_文本型
     * @param value 文本参数
     * @param Sequence 整数参数
     */
    public static native void report_Controls_ChangeItemOrderString(String value, int Sequence);
    /**
     * 改变项目顺序_整数
     * @param value 整数参数
     * @param Sequence 整数参数
     */
    public static native void report_Controls_ChangeItemOrderInt(int value, int Sequence);
    /**
     * 改变项目顺序_名称
     * @param Name 文本参数
     * @param Sequence 整数参数
     */
    public static native void report_Controls_ChangeItemOrderByName(String Name, int Sequence);
    /**
     * 改变项目顺序_序号
     * @param Index 整数参数
     * @param Sequence 整数参数
     */
    public static native void report_Controls_ChangeItemOrderBy(int Index, int Sequence);
    /**
     * 按名称取序号
     * @param Name 文本参数
     * @return 返回整数
     */
    public static native int report_Controls_ByName(String Name);
    /**
     * 获取指定项目
     * @param Index 整数参数
     * @return 返回整数
     */
    public static native int report_Controls_ItemAt(int Index);
    /**
     * 删除项目_文本型
     * @param value 文本参数
     */
    public static native void report_Controls_RemoveString(String value);
    /**
     * 删除项目_整数
     * @param value 整数参数
     */
    public static native void report_Controls_RemoveInt(int value);
    /**
     * 按名称删除项目
     * @param Name 文本参数
     */
    public static native void report_Controls_RemoveByName(String Name);
    /**
     * 按序号删除项目
     * @param Index 整数参数
     */
    public static native void report_Controls_RemoveBy(int Index);
    /**
     * 全部删除
     */
    public static native void report_Controls_RemoveAll();
    /**
     * 取数据集
     * @return 返回整数
     */
    public static native int report_CrossTab_GetRecordset();
    /**
     * 读项目列数
     * @return 返回整数
     */
    public static native int report_CrossTab_GetListCols();
    /**
     * 写项目列数
     * @param value 整数参数
     */
    public static native void report_CrossTab_SetListCols(int value);
    /**
     * 读横向交叉期间类型
     * @return 返回整数
     */
    public static native int report_CrossTab_GetHCrossPeriodType();
    /**
     * 写横向交叉期间类型
     * @param value 整数参数
     */
    public static native void report_CrossTab_SetHCrossPeriodType(int value);
    /**
     * 读开始日期参数
     * @return 返回文本
     */
    public static native String report_CrossTab_GetBeginDateParameter();
    /**
     * 写开始日期参数
     * @param value 文本参数
     */
    public static native void report_CrossTab_SetBeginDateParameter(String value);
    /**
     * 读结束日期参数
     * @return 返回文本
     */
    public static native String report_CrossTab_GetEndDateParameter();
    /**
     * 写结束日期参数
     * @param value 文本参数
     */
    public static native void report_CrossTab_SetEndDateParameter(String value);
    /**
     * 读横向交叉字段
     * @return 返回文本
     */
    public static native String report_CrossTab_GetHCrossFields();
    /**
     * 写横向交叉字段
     * @param value 文本参数
     */
    public static native void report_CrossTab_SetHCrossFields(String value);
    /**
     * 读纵向交叉字段
     * @return 返回文本
     */
    public static native String report_CrossTab_GetVCrossFields();
    /**
     * 写纵向交叉字段
     * @param value 文本参数
     */
    public static native void report_CrossTab_SetVCrossFields(String value);
    /**
     * 读禁止统计字段
     * @return 返回文本
     */
    public static native String report_CrossTab_GetDisabledSumFields();
    /**
     * 写禁止统计字段
     * @param value 文本参数
     */
    public static native void report_CrossTab_SetDisabledSumFields(String value);
    /**
     * 读横向求比率列
     * @return 返回文本
     */
    public static native String report_CrossTab_GetHPercentColumns();
    /**
     * 写横向求比率列
     * @param value 文本参数
     */
    public static native void report_CrossTab_SetHPercentColumns(String value);
    /**
     * 读纵向求比率列
     * @return 返回文本
     */
    public static native String report_CrossTab_GetVPercentColumns();
    /**
     * 写纵向求比率列
     * @param value 文本参数
     */
    public static native void report_CrossTab_SetVPercentColumns(String value);
    /**
     * 读横向合计排除列
     * @return 返回文本
     */
    public static native String report_CrossTab_GetTotalExcludeColumns();
    /**
     * 写横向合计排除列
     * @param value 文本参数
     */
    public static native void report_CrossTab_SetTotalExcludeColumns(String value);
    /**
     * 读横向合计求比率列
     * @return 返回文本
     */
    public static native String report_CrossTab_GetTotalHPercentColumns();
    /**
     * 写横向合计求比率列
     * @param value 文本参数
     */
    public static native void report_CrossTab_SetTotalHPercentColumns(String value);
    /**
     * 读纵向合计求比率列
     * @return 返回文本
     */
    public static native String report_CrossTab_GetTotalVPercentColumns();
    /**
     * 写纵向合计求比率列
     * @param value 文本参数
     */
    public static native void report_CrossTab_SetTotalVPercentColumns(String value);
    /**
     * 读百分比格式
     * @return 返回文本
     */
    public static native String report_CrossTab_GetPercentFormat();
    /**
     * 写百分比格式
     * @param value 文本参数
     */
    public static native void report_CrossTab_SetPercentFormat(String value);
    /**
     * 读分组自动求和
     * @return 返回布尔值
     */
    public static native boolean report_CrossTab_GetGroupAutoSum();
    /**
     * 写分组自动求和
     * @param value 布尔参数
     */
    public static native void report_CrossTab_SetGroupAutoSum(boolean value);
    /**
     * 读横向重排序
     * @return 返回布尔值
     */
    public static native boolean report_CrossTab_GetHResort();
    /**
     * 写横向重排序
     * @param value 布尔参数
     */
    public static native void report_CrossTab_SetHResort(boolean value);
    /**
     * 读横向排序升序
     * @return 返回布尔值
     */
    public static native boolean report_CrossTab_GetHSortAsc();
    /**
     * 写横向排序升序
     * @param value 布尔参数
     */
    public static native void report_CrossTab_SetHSortAsc(boolean value);
    /**
     * 读横向合计在前
     * @return 返回布尔值
     */
    public static native boolean report_CrossTab_GetHTotalAtFirst();
    /**
     * 写横向合计在前
     * @param value 布尔参数
     */
    public static native void report_CrossTab_SetHTotalAtFirst(boolean value);
    /**
     * 读纵向重排序
     * @return 返回布尔值
     */
    public static native boolean report_CrossTab_GetVResort();
    /**
     * 写纵向重排序
     * @param value 布尔参数
     */
    public static native void report_CrossTab_SetVResort(boolean value);
    /**
     * 读纵向排序升序
     * @return 返回布尔值
     */
    public static native boolean report_CrossTab_GetVSortAsc();
    /**
     * 写纵向排序升序
     * @param value 布尔参数
     */
    public static native void report_CrossTab_SetVSortAsc(boolean value);
    /**
     * 读小计列数
     * @return 返回整数
     */
    public static native int report_CrossTab_GetSubtotalCols();
    /**
     * 写小计列数
     * @param value 整数参数
     */
    public static native void report_CrossTab_SetSubtotalCols(int value);
    /**
     * 读合计列数
     * @return 返回整数
     */
    public static native int report_CrossTab_GetTotalCols();
    /**
     * 写合计列数
     * @param value 整数参数
     */
    public static native void report_CrossTab_SetTotalCols(int value);
    /**
     * 读锁定
     * @return 返回整数
     */
    public static native int report_CrossTab_GetLock();
    /**
     * 写锁定
     * @param value 整数参数
     */
    public static native void report_CrossTab_SetLock(int value);
    /**
     * 取交叉周期开始日期
     * @return 返回小数
     */
    public static native float report_CrossTab_GetCurPeriodBeginDate();
    /**
     * 取交叉周期结束日期
     * @return 返回小数
     */
    public static native float report_CrossTab_GetCurPeriodEndDate();
    /**
     * 开始横向交叉数据
     */
    public static native void report_CrossTab_HBeginAddItem();
    /**
     * 结束横向交叉数据
     */
    public static native void report_CrossTab_HEndAddItem();
    /**
     * 开始纵向交叉数据
     */
    public static native void report_CrossTab_VBeginAddItem();
    /**
     * 结束纵向交叉数据
     */
    public static native void report_CrossTab_VEndAddItem();
    /**
     * 读浮点数值
     * @return 返回小数
     */
    public static native float report_DateTime_GetAsFloat();
    /**
     * 写浮点数值
     * @param value 小数参数
     */
    public static native void report_DateTime_SetAsFloat(float value);
    /**
     * 读年
     * @return 返回整数
     */
    public static native int report_DateTime_GetYear();
    /**
     * 读月
     * @return 返回整数
     */
    public static native int report_DateTime_GetMonth();
    /**
     * 读日
     * @return 返回整数
     */
    public static native int report_DateTime_GetDay();
    /**
     * 读时
     * @return 返回整数
     */
    public static native int report_DateTime_GetHour();
    /**
     * 读分
     * @return 返回整数
     */
    public static native int report_DateTime_GetMinute();
    /**
     * 读秒
     * @return 返回整数
     */
    public static native int report_DateTime_GetSecond();
    /**
     * 读星期几
     * @return 返回整数
     */
    public static native int report_DateTime_GetDayOfWeek();
    /**
     * 读第几天
     * @return 返回整数
     */
    public static native int report_DateTime_GetDayOfYear();
    /**
     * 格式化
     * @param Format 文本参数
     * @return 返回文本
     */
    public static native String report_DateTime_Format(String Format);
    /**
     * 设定日期
     * @param value 整数参数
     * @param value2 整数参数
     * @param value3 整数参数
     */
    public static native void report_DateTime_ValueFromDate(int value, int value2, int value3);
    /**
     * 设定时间
     * @param value 整数参数
     * @param value2 整数参数
     * @param value3 整数参数
     * @param value4 整数参数
     * @param value5 整数参数
     * @param value6 整数参数
     */
    public static native void report_DateTime_ValueFromDateTime(int value, int value2, int value3, int value4, int value5, int value6);
    /**
     * 读锁定
     * @return 返回整数
     */
    public static native int report_DetailGrid_GetLock();
    /**
     * 写锁定
     * @param value 整数参数
     */
    public static native void report_DetailGrid_SetLock(int value);
    /**
     * 取字体
     * @return 返回整数
     */
    public static native int report_DetailGrid_GetFont();
    /**
     * 取边框
     * @return 返回整数
     */
    public static native int report_DetailGrid_GetBorder();
    /**
     * 取标题行
     * @return 返回整数
     */
    public static native int report_DetailGrid_GetColumnTitle();
    /**
     * 取内容行
     * @return 返回整数
     */
    public static native int report_DetailGrid_GetColumnContent();
    /**
     * 取列线画笔
     * @return 返回整数
     */
    public static native int report_DetailGrid_GetColLinePen();
    /**
     * 取行线画笔
     * @return 返回整数
     */
    public static native int report_DetailGrid_GetRowLinePen();
    /**
     * 取列集合
     * @return 返回整数
     */
    public static native int report_DetailGrid_GetColumns();
    /**
     * 取分组集合
     * @return 返回整数
     */
    public static native int report_DetailGrid_GetGroups();
    /**
     * 取数据集
     * @return 返回整数
     */
    public static native int report_DetailGrid_GetRecordset();
    /**
     * 取交叉表
     * @return 返回整数
     */
    public static native int report_DetailGrid_GetCrossTab();
    /**
     * 读边框打印类别
     * @return 返回整数
     */
    public static native int report_DetailGrid_GetBorderPrintType();
    /**
     * 写边框打印类别
     * @param value 整数参数
     */
    public static native void report_DetailGrid_SetBorderPrintType(int value);
    /**
     * 读行列线打印类别
     * @return 返回整数
     */
    public static native int report_DetailGrid_GetGridLinePrintType();
    /**
     * 写行列线打印类别
     * @param value 整数参数
     */
    public static native void report_DetailGrid_SetGridLinePrintType(int value);
    /**
     * 读显示行线
     * @return 返回布尔值
     */
    public static native boolean report_DetailGrid_GetShowRowLine();
    /**
     * 写显示行线
     * @param value 布尔参数
     */
    public static native void report_DetailGrid_SetShowRowLine(boolean value);
    /**
     * 读显示列线
     * @return 返回布尔值
     */
    public static native boolean report_DetailGrid_GetShowColLine();
    /**
     * 写显示列线
     * @param value 布尔参数
     */
    public static native void report_DetailGrid_SetShowColLine(boolean value);
    /**
     * 读居中显示
     * @return 返回布尔值
     */
    public static native boolean report_DetailGrid_GetCenterView();
    /**
     * 写居中显示
     * @param value 布尔参数
     */
    public static native void report_DetailGrid_SetCenterView(boolean value);
    /**
     * 读换新页
     * @return 返回整数
     */
    public static native int report_DetailGrid_GetNewPage();
    /**
     * 写换新页
     * @param value 整数参数
     */
    public static native void report_DetailGrid_SetNewPage(int value);
    /**
     * 读打印列宽适应内容
     * @return 返回布尔值
     */
    public static native boolean report_DetailGrid_GetPrintAdaptFitText();
    /**
     * 写打印列宽适应内容
     * @param value 布尔参数
     */
    public static native void report_DetailGrid_SetPrintAdaptFitText(boolean value);
    /**
     * 读锁定列数
     * @return 返回整数
     */
    public static native int report_DetailGrid_GetFixCols();
    /**
     * 写锁定列数
     * @param value 整数参数
     */
    public static native void report_DetailGrid_SetFixCols(int value);
    /**
     * 读打印锁定列重复间隔
     * @return 返回整数
     */
    public static native int report_DetailGrid_GetPrintAdaptRFCStep();
    /**
     * 写打印锁定列重复间隔
     * @param value 整数参数
     */
    public static native void report_DetailGrid_SetPrintAdaptRFCStep(int value);
    /**
     * 读伸展到页底
     * @return 返回布尔值
     */
    public static native boolean report_DetailGrid_GetGrowToBottom();
    /**
     * 写伸展到页底
     * @param value 布尔参数
     */
    public static native void report_DetailGrid_SetGrowToBottom(boolean value);
    /**
     * 读交叉表方式
     * @return 返回布尔值
     */
    public static native boolean report_DetailGrid_GetIsCrossTab();
    /**
     * 写交叉表方式
     * @param value 布尔参数
     */
    public static native void report_DetailGrid_SetIsCrossTab(boolean value);
    /**
     * 读页栏数
     * @return 返回整数
     */
    public static native int report_DetailGrid_GetPageColumnCount();
    /**
     * 写页栏数
     * @param value 整数参数
     */
    public static native void report_DetailGrid_SetPageColumnCount(int value);
    /**
     * 读页栏间隙
     * @return 返回小数
     */
    public static native float report_DetailGrid_GetPageColumnSpacing();
    /**
     * 写页栏间隙
     * @param value 小数参数
     */
    public static native void report_DetailGrid_SetPageColumnSpacing(float value);
    /**
     * 读页栏方向
     * @return 返回整数
     */
    public static native int report_DetailGrid_GetPageColumnDirection();
    /**
     * 写页栏方向
     * @param value 整数参数
     */
    public static native void report_DetailGrid_SetPageColumnDirection(int value);
    /**
     * 读分组禁止分栏
     * @return 返回布尔值
     */
    public static native boolean report_DetailGrid_GetPageColumnGroupNA();
    /**
     * 写分组禁止分栏
     * @param value 布尔参数
     */
    public static native void report_DetailGrid_SetPageColumnGroupNA(boolean value);
    /**
     * 读打印策略
     * @return 返回整数
     */
    public static native int report_DetailGrid_GetPrintAdaptMethod();
    /**
     * 写打印策略
     * @param value 整数参数
     */
    public static native void report_DetailGrid_SetPrintAdaptMethod(int value);
    /**
     * 读重复打印
     * @return 返回布尔值
     */
    public static native boolean report_DetailGrid_GetPrintAdaptRepeat();
    /**
     * 写重复打印
     * @param value 布尔参数
     */
    public static native void report_DetailGrid_SetPrintAdaptRepeat(boolean value);
    /**
     * 读横向分页一页显示
     * @return 返回布尔值
     */
    public static native boolean report_DetailGrid_GetPrintAdaptTryToOnePage();
    /**
     * 写横向分页一页显示
     * @param value 布尔参数
     */
    public static native void report_DetailGrid_SetPrintAdaptTryToOnePage(boolean value);
    /**
     * 读追加空白列
     * @return 返回布尔值
     */
    public static native boolean report_DetailGrid_GetAppendBlankCol();
    /**
     * 写追加空白列
     * @param value 布尔参数
     */
    public static native void report_DetailGrid_SetAppendBlankCol(boolean value);
    /**
     * 读追加空白列宽度
     * @return 返回小数
     */
    public static native float report_DetailGrid_GetAppendBlankColWidth();
    /**
     * 写追加空白列宽度
     * @param value 小数参数
     */
    public static native void report_DetailGrid_SetAppendBlankColWidth(float value);
    /**
     * 读追加空白行
     * @return 返回布尔值
     */
    public static native boolean report_DetailGrid_GetAppendBlankRow();
    /**
     * 写追加空白行
     * @param value 布尔参数
     */
    public static native void report_DetailGrid_SetAppendBlankRow(boolean value);
    /**
     * 读追加空白行在后
     * @return 返回布尔值
     */
    public static native boolean report_DetailGrid_GetAppendBlankRowAtLast();
    /**
     * 写追加空白行在后
     * @param value 布尔参数
     */
    public static native void report_DetailGrid_SetAppendBlankRowAtLast(boolean value);
    /**
     * 读背景色
     * @return 返回整数
     */
    public static native int report_DetailGrid_GetBackColor();
    /**
     * 写背景色
     * @param value 整数参数
     */
    public static native void report_DetailGrid_SetBackColor(int value);
    /**
     * 增加列
     * @param Name 文本参数
     * @param TitleText 文本参数
     * @param CloseField 文本参数
     * @param ColumnWidth 小数参数
     * @return 返回整数
     */
    public static native int report_DetailGrid_AddColumn(String Name, String TitleText, String CloseField, float ColumnWidth);
    /**
     * 增加组标题格
     * @param Name 文本参数
     * @param TitleText 文本参数
     * @return 返回整数
     */
    public static native int report_DetailGrid_AddGroupTitle(String Name, String TitleText);
    /**
     * 清空列
     */
    public static native void report_DetailGrid_ClearColumns();
    /**
     * 清空组标题格
     */
    public static native void report_DetailGrid_ClearGroupTitles();
    /**
     * 按序号获取列
     * @param Index 整数参数
     * @return 返回整数
     */
    public static native int report_DetailGrid_ColumnByShowOrder(int Index);
    /**
     * 查找组标题格
     * @param TitleName 文本参数
     * @return 返回整数
     */
    public static native int report_DetailGrid_FindGroupTitleCell(String TitleName);
    /**
     * 移动列至新位置
     * @param MoveColumnName 文本参数
     * @param value 文本参数
     * @param DisplaySequence 整数参数
     */
    public static native void report_DetailGrid_ColumnMoveTo(String MoveColumnName, String value, int DisplaySequence);
    /**
     * 移动列到最后_变体型
     * @param ColumnIDPointer 整数参数
     */
    public static native void report_DetailGrid_ColumnMoveToEndVariant(int ColumnIDPointer);
    /**
     * 移动列到最后_文本型
     * @param value 文本参数
     */
    public static native void report_DetailGrid_ColumnMoveToEndString(String value);
    /**
     * 按名称移动列到最后
     * @param ColumnName 文本参数
     */
    public static native void report_DetailGrid_ColumnMoveToEndByName(String ColumnName);
    /**
     * 按序号移动列到最后
     * @param ColumnIndex 整数参数
     */
    public static native void report_DetailGrid_ColumnMoveToEndBy(int ColumnIndex);
    /**
     * 开始新分组_变体型
     * @param GroupIDPointer 整数参数
     */
    public static native void report_DetailGrid_StartNewGroupVariant(int GroupIDPointer);
    /**
     * 开始新分组_文本型
     * @param value 文本参数
     */
    public static native void report_DetailGrid_StartNewGroupString(String value);
    /**
     * 按名称开始新分组
     * @param GroupName 文本参数
     */
    public static native void report_DetailGrid_StartNewGroupByName(String GroupName);
    /**
     * 按序号开始新分组
     * @param GroupIndex 整数参数
     */
    public static native void report_DetailGrid_StartNewGroupBy(int GroupIndex);
    /**
     * 读CSV分隔字符
     * @return 返回文本
     */
    public static native String report_E2CSVOption_GetDelimiterChar();
    /**
     * 写CSV分隔字符
     * @param value 文本参数
     */
    public static native void report_E2CSVOption_SetDelimiterChar(String value);
    /**
     * 读引号分割字符
     * @return 返回文本
     */
    public static native String report_E2CSVOption_GetQuoteText();
    /**
     * 写引号分割字符
     * @param value 文本参数
     */
    public static native void report_E2CSVOption_SetQuoteText(String value);
    /**
     * 读文字编码
     * @return 返回整数
     */
    public static native int report_E2CSVOption_GetTextEncode();
    /**
     * 写文字编码
     * @param value 整数参数
     */
    public static native void report_E2CSVOption_SetTextEncode(int value);
    /**
     * 读列与明细网格保持一致
     * @return 返回布尔值
     */
    public static native boolean report_E2CellOption_GetColumnAsDetailGrid();
    /**
     * 写列与明细网格保持一致
     * @param value 布尔参数
     */
    public static native void report_E2CellOption_SetColumnAsDetailGrid(boolean value);
    /**
     * 读输出页分隔行
     * @return 返回布尔值
     */
    public static native boolean report_E2CellOption_GetExportPageBreak();
    /**
     * 写输出页分隔行
     * @param value 布尔参数
     */
    public static native void report_E2CellOption_SetExportPageBreak(boolean value);
    /**
     * 读输出页眉与页脚
     * @return 返回布尔值
     */
    public static native boolean report_E2CellOption_GetExportPageHeaderFooter();
    /**
     * 写输出页眉与页脚
     * @param value 布尔参数
     */
    public static native void report_E2CellOption_SetExportPageHeaderFooter(boolean value);
    /**
     * 读只输出明细网格
     * @return 返回布尔值
     */
    public static native boolean report_E2CellOption_GetOnlyExportDetailGrid();
    /**
     * 写只输出明细网格
     * @param value 布尔参数
     */
    public static native void report_E2CellOption_SetOnlyExportDetailGrid(boolean value);
    /**
     * 读只导出纯文本数据
     * @return 返回布尔值
     */
    public static native boolean report_E2CellOption_GetOnlyExportPureText();
    /**
     * 写只导出纯文本数据
     * @param value 布尔参数
     */
    public static native void report_E2CellOption_SetOnlyExportPureText(boolean value);
    /**
     * 读与打印页面保持一致
     * @return 返回布尔值
     */
    public static native boolean report_E2CellOption_GetSameAsPrint();
    /**
     * 写与打印页面保持一致
     * @param value 布尔参数
     */
    public static native void report_E2CellOption_SetSameAsPrint(boolean value);
    /**
     * 读压缩空白行
     * @return 返回布尔值
     */
    public static native boolean report_E2CellOption_GetSupressEmptyLines();
    /**
     * 写压缩空白行
     * @param value 布尔参数
     */
    public static native void report_E2CellOption_SetSupressEmptyLines(boolean value);
    /**
     * 读图像路径
     * @return 返回文本
     */
    public static native String report_E2HTMOption_GetPicturePath();
    /**
     * 写图像路径
     * @param value 文本参数
     */
    public static native void report_E2HTMOption_SetPicturePath(String value);
    /**
     * 读多个页
     * @return 返回布尔值
     */
    public static native boolean report_E2HTMOption_GetMultiPage();
    /**
     * 写多个页
     * @param value 布尔参数
     */
    public static native void report_E2HTMOption_SetMultiPage(boolean value);
    /**
     * 读多个页
     * @return 返回布尔值
     */
    public static native boolean report_E2IMGOption_GetAllInOne();
    /**
     * 写多个页
     * @param value 布尔参数
     */
    public static native void report_E2IMGOption_SetAllInOne(boolean value);
    /**
     * 读绘制页面框
     * @return 返回布尔值
     */
    public static native boolean report_E2IMGOption_GetDrawPageBox();
    /**
     * 写绘制页面框
     * @param value 布尔参数
     */
    public static native void report_E2IMGOption_SetDrawPageBox(boolean value);
    /**
     * 读分辨率
     * @return 返回整数
     */
    public static native int report_E2IMGOption_GetDPI();
    /**
     * 写分辨率
     * @param value 整数参数
     */
    public static native void report_E2IMGOption_SetDPI(int value);
    /**
     * 读垂直间隙
     * @return 返回整数
     */
    public static native int report_E2IMGOption_GetVertGap();
    /**
     * 写垂直间隙
     * @param value 整数参数
     */
    public static native void report_E2IMGOption_SetVertGap(int value);
    /**
     * 读图像类型
     * @return 返回整数
     */
    public static native int report_E2IMGOption_GetImageType();
    /**
     * 写图像类型
     * @param value 整数参数
     */
    public static native void report_E2IMGOption_SetImageType(int value);
    /**
     * 读ANSI文字编码
     * @return 返回布尔值
     */
    public static native boolean report_E2PDFOption_GetAnsiTextMode();
    /**
     * 写ANSI文字编码
     * @param value 布尔参数
     */
    public static native void report_E2PDFOption_SetAnsiTextMode(boolean value);
    /**
     * 读压缩数据
     * @return 返回布尔值
     */
    public static native boolean report_E2PDFOption_GetCompressed();
    /**
     * 写压缩数据
     * @param value 布尔参数
     */
    public static native void report_E2PDFOption_SetCompressed(boolean value);
    /**
     * 读嵌入字体数据
     * @return 返回布尔值
     */
    public static native boolean report_E2PDFOption_GetFontEmbedding();
    /**
     * 写嵌入字体数据
     * @param value 布尔参数
     */
    public static native void report_E2PDFOption_SetFontEmbedding(boolean value);
    /**
     * 读作者
     * @return 返回文本
     */
    public static native String report_E2PDFOption_GetAuthor();
    /**
     * 写作者
     * @param value 文本参数
     */
    public static native void report_E2PDFOption_SetAuthor(String value);
    /**
     * 读创建程序
     * @return 返回文本
     */
    public static native String report_E2PDFOption_GetCreator();
    /**
     * 写创建程序
     * @param Text 文本参数
     */
    public static native void report_E2PDFOption_SetCreator(String Text);
    /**
     * 读关键字
     * @return 返回文本
     */
    public static native String report_E2PDFOption_GetKeywords();
    /**
     * 写关键字
     * @param Text 文本参数
     */
    public static native void report_E2PDFOption_SetKeywords(String Text);
    /**
     * 读拥有者密码
     * @return 返回文本
     */
    public static native String report_E2PDFOption_GetOwnerPassword();
    /**
     * 写拥有者密码
     * @param PasswordText 文本参数
     */
    public static native void report_E2PDFOption_SetOwnerPassword(String PasswordText);
    /**
     * 读用户密码
     * @return 返回文本
     */
    public static native String report_E2PDFOption_GetUserPassword();
    /**
     * 写用户密码
     * @param PasswordText 文本参数
     */
    public static native void report_E2PDFOption_SetUserPassword(String PasswordText);
    /**
     * 读制作程序
     * @return 返回文本
     */
    public static native String report_E2PDFOption_GetProducer();
    /**
     * 写制作程序
     * @param Text 文本参数
     */
    public static native void report_E2PDFOption_SetProducer(String Text);
    /**
     * 读主题
     * @return 返回文本
     */
    public static native String report_E2PDFOption_GetSubject();
    /**
     * 写主题
     * @param TopicText 文本参数
     */
    public static native void report_E2PDFOption_SetSubject(String TopicText);
    /**
     * 设置安全性限制状态
     * @param Safe 整数参数
     * @param TrueFalseAllow 布尔参数
     */
    public static native void report_E2PDFOption_SetDocPermission(int Safe, boolean TrueFalseAllow);
    /**
     * 获取安全性限制状态
     * @param Safe 整数参数
     * @return 返回布尔值
     */
    public static native boolean report_E2PDFOption_GetDocPermission(int Safe);
    /**
     * 读作者
     * @return 返回文本
     */
    public static native String report_E2RTFOption_GetAuthor();
    /**
     * 写作者
     * @param value 文本参数
     */
    public static native void report_E2RTFOption_SetAuthor(String value);
    /**
     * 读创建程序
     * @return 返回文本
     */
    public static native String report_E2RTFOption_GetCreator();
    /**
     * 写创建程序
     * @param value 文本参数
     */
    public static native void report_E2RTFOption_SetCreator(String value);
    /**
     * 读用Tab字符对齐
     * @return 返回布尔值
     */
    public static native boolean report_E2XLSOption_GetUsingTabChar();
    /**
     * 写用Tab字符对齐
     * @param value 布尔参数
     */
    public static native void report_E2XLSOption_SetUsingTabChar(boolean value);
    /**
     * 读用文字编码
     * @return 返回整数
     */
    public static native int report_E2XLSOption_GetTextEncode();
    /**
     * 写文字编码
     * @param value 整数参数
     */
    public static native void report_E2XLSOption_SetTextEncode(int value);
    /**
     * 读导出xlsx格式
     * @return 返回布尔值
     */
    public static native boolean report_E2XLSOption_GetToXlsxFormat();
    /**
     * 写导出xlsx格式
     * @param value 布尔参数
     */
    public static native void report_E2XLSOption_SetToXlsxFormat(boolean value);
    /**
     * 读禁止标题行重复输出
     * @return 返回布尔值
     */
    public static native boolean report_E2XLSOption_GetColumnTitleForbidRepeat();
    /**
     * 写禁止标题行重复输出
     * @param value 布尔参数
     */
    public static native void report_E2XLSOption_SetColumnTitleForbidRepeat(boolean value);
    /**
     * 读增大行高适应
     * @return 返回布尔值
     */
    public static native boolean report_E2XLSOption_GetExpandRowHeight();
    /**
     * 写增大行高适应
     * @param value 布尔参数
     */
    public static native void report_E2XLSOption_SetExpandRowHeight(boolean value);
    /**
     * 读最小列宽
     * @return 返回小数
     */
    public static native float report_E2XLSOption_GetMinColumnWidth();
    /**
     * 写最小列宽
     * @param value 小数参数
     */
    public static native void report_E2XLSOption_SetMinColumnWidth(float value);
    /**
     * 读最小行高
     * @return 返回小数
     */
    public static native float report_E2XLSOption_GetMinRowHeight();
    /**
     * 写最小行高
     * @param value 小数参数
     */
    public static native void report_E2XLSOption_SetMinRowHeight(float value);
    /**
     * 读换新数据表行数
     * @return 返回整数
     */
    public static native int report_E2XLSOption_GetNewSheetRows();
    /**
     * 写换新数据表行数
     * @param value 整数参数
     */
    public static native void report_E2XLSOption_SetNewSheetRows(int value);
    /**
     * 读页面左边距
     * @return 返回小数
     */
    public static native float report_E2XLSOption_GetPageLeftMargin();
    /**
     * 写页面左边距
     * @param value 小数参数
     */
    public static native void report_E2XLSOption_SetPageLeftMargin(float value);
    /**
     * 读页面右边距
     * @return 返回小数
     */
    public static native float report_E2XLSOption_GetPageRightMargin();
    /**
     * 写页面右边距
     * @param value 小数参数
     */
    public static native void report_E2XLSOption_SetPageRightMargin(float value);
    /**
     * 读页面上边距
     * @return 返回小数
     */
    public static native float report_E2XLSOption_GetPageTopMargin();
    /**
     * 写页面上边距
     * @param value 小数参数
     */
    public static native void report_E2XLSOption_SetPageTopMargin(float value);
    /**
     * 读页面下边距
     * @return 返回小数
     */
    public static native float report_E2XLSOption_GetPageBottomMargin();
    /**
     * 写页面下边距
     * @param value 小数参数
     */
    public static native void report_E2XLSOption_SetPageBottomMargin(float value);
    /**
     * 读获取导出格基类
     * @return 返回整数
     */
    public static native int report_ExportOption_GetAsE2CellOption();
    /**
     * 读获取为CSV选项
     * @return 返回整数
     */
    public static native int report_ExportOption_GetAsE2CSVOption();
    /**
     * 读获取为HTM选项
     * @return 返回整数
     */
    public static native int report_ExportOption_GetAsE2HTMOption();
    /**
     * 读获取为IMG选项
     * @return 返回整数
     */
    public static native int report_ExportOption_GetAsE2IMGOption();
    /**
     * 读获取为PDF选项
     * @return 返回整数
     */
    public static native int report_ExportOption_GetAsE2PDFOption();
    /**
     * 读获取为RTF选项
     * @return 返回整数
     */
    public static native int report_ExportOption_GetAsE2RTFOption();
    /**
     * 读获取为TXT选项
     * @return 返回整数
     */
    public static native int report_ExportOption_GetAsE2TXTOption();
    /**
     * 读获取为XLS选项
     * @return 返回整数
     */
    public static native int report_ExportOption_GetAsE2XLSOption();
    /**
     * 读导出后不打开
     * @return 返回布尔值
     */
    public static native boolean report_ExportOption_GetAbortOpenFile();
    /**
     * 写导出后不打开
     * @param value 布尔参数
     */
    public static native void report_ExportOption_SetAbortOpenFile(boolean value);
    /**
     * 读不显示导出设置对话框
     * @return 返回布尔值
     */
    public static native boolean report_ExportOption_GetAbortShowOptionDlg();
    /**
     * 写不显示导出设置对话框
     * @param value 布尔参数
     */
    public static native void report_ExportOption_SetAbortShowOptionDlg(boolean value);
    /**
     * 读导出类型
     * @return 返回整数
     */
    public static native int report_ExportOption_GetExportType();
    /**
     * 写导出类型
     * @param value 整数参数
     */
    public static native void report_ExportOption_SetExportType(int value);
    /**
     * 读文件名
     * @return 返回文本
     */
    public static native String report_ExportOption_GetFileName();
    /**
     * 写文件名
     * @param FileName 文本参数
     */
    public static native void report_ExportOption_SetFileName(String FileName);
    /**
     * 读导出后发送邮件
     * @return 返回布尔值
     */
    public static native boolean report_ExportOption_GetMailExportFile();
    /**
     * 写导出后发送邮件
     * @param value 布尔参数
     */
    public static native void report_ExportOption_SetMailExportFile(boolean value);
    /**
     * 读邮件主题
     * @return 返回文本
     */
    public static native String report_ExportOption_GetMailSubject();
    /**
     * 写邮件主题
     * @param TopicText 文本参数
     */
    public static native void report_ExportOption_SetMailSubject(String TopicText);
    /**
     * 读邮件正文
     * @return 返回文本
     */
    public static native String report_ExportOption_GetMailText();
    /**
     * 写邮件正文
     * @param Text 文本参数
     */
    public static native void report_ExportOption_SetMailText(String Text);
    /**
     * 读邮件收件人
     * @return 返回文本
     */
    public static native String report_ExportOption_GetMailTo();
    /**
     * 写邮件收件人
     * @param Receive 文本参数
     */
    public static native void report_ExportOption_SetMailTo(String Receive);
    /**
     * 显示选项对话框
     * @return 返回布尔值
     */
    public static native boolean report_ExportOption_ShowOptionDlg();
    /**
     * 读名称
     * @return 返回文本
     */
    public static native String report_Field_GetName();
    /**
     * 写名称
     * @param value 文本参数
     */
    public static native void report_Field_SetName(String value);
    /**
     * 读显示文字
     * @return 返回文本
     */
    public static native String report_Field_GetDisplayText();
    /**
     * 写显示文字
     * @param value 文本参数
     */
    public static native void report_Field_SetDisplayText(String value);
    /**
     * 读逻辑值
     * @return 返回布尔值
     */
    public static native boolean report_Field_GetAsBoolean();
    /**
     * 写逻辑值
     * @param value 布尔参数
     */
    public static native void report_Field_SetAsBoolean(boolean value);
    /**
     * 读货币值
     * @return 返回小数
     */
    public static native float report_Field_GetAsCurrency();
    /**
     * 写货币值
     * @param value 小数参数
     */
    public static native void report_Field_SetAsCurrency(float value);
    /**
     * 读日期时间值
     * @return 返回小数
     */
    public static native float report_Field_GetAsDateTime();
    /**
     * 写日期时间值
     * @param value 小数参数
     */
    public static native void report_Field_SetAsDateTime(float value);
    /**
     * 读浮点数值
     * @return 返回小数
     */
    public static native float report_Field_GetAsFloat();
    /**
     * 写浮点数值
     * @param value 小数参数
     */
    public static native void report_Field_SetAsFloat(float value);
    /**
     * 读整数值
     * @return 返回整数
     */
    public static native int report_Field_GetAsInteger();
    /**
     * 写整数值
     * @param value 整数参数
     */
    public static native void report_Field_SetAsInteger(int value);
    /**
     * 读字符值
     * @return 返回文本
     */
    public static native String report_Field_GetAsString();
    /**
     * 写字符值
     * @param value 文本参数
     */
    public static native void report_Field_SetAsString(String value);
    /**
     * 读数据缓冲区
     * @return 返回整数
     */
    public static native int report_Field_GetDataBuffer();
    /**
     * 读数据尺寸
     * @return 返回整数
     */
    public static native int report_Field_GetDataSize();
    /**
     * 读数据源字段名
     * @return 返回文本
     */
    public static native String report_Field_GetDBFieldName();
    /**
     * 写数据源字段名
     * @param value 文本参数
     */
    public static native void report_Field_SetDBFieldName(String value);
    /**
     * 读类型
     * @return 返回整数
     */
    public static native int report_Field_GetFieldType();
    /**
     * 写类型
     * @param value 整数参数
     */
    public static native void report_Field_SetFieldType(int value);
    /**
     * 读格式
     * @return 返回文本
     */
    public static native String report_Field_GetFormat();
    /**
     * 写格式
     * @param value 文本参数
     */
    public static native void report_Field_SetFormat(String value);
    /**
     * 读截除右端空格
     * @return 返回布尔值
     */
    public static native boolean report_Field_GetRTrimBlankChars();
    /**
     * 写截除右端空格
     * @param value 布尔参数
     */
    public static native void report_Field_SetRTrimBlankChars(boolean value);
    /**
     * 读运行时数据源字段名
     * @return 返回文本
     */
    public static native String report_Field_GetRunningDBField();
    /**
     * 读是否空值
     * @return 返回布尔值
     */
    public static native boolean report_Field_GetIsNull();
    /**
     * 读取显示文字脚本
     * @return 返回文本
     */
    public static native String report_Field_GetDisplayTextScript();
    /**
     * 写取显示文字脚本
     * @param value 文本参数
     */
    public static native void report_Field_SetDisplayTextScript(String value);
    /**
     * 读长度
     * @return 返回整数
     */
    public static native int report_Field_GetLength();
    /**
     * 写长度
     * @param value 整数参数
     */
    public static native void report_Field_SetLength(int value);
    /**
     * 清空
     */
    public static native void report_Field_Clear();
    /**
     * 从二进制载入
     * @param value 整数参数
     */
    public static native void report_Field_LoadFromBinary(int value);
    /**
     * 从文件载入
     * @param FileName 文本参数
     */
    public static native void report_Field_LoadFromFile(String FileName);
    /**
     * 从内存载入
     * @param DataPointer 整数参数
     * @param DataSize 整数参数
     */
    public static native void report_Field_LoadFromMemory(int DataPointer, int DataSize);
    /**
     * 读值整数
     * @return 返回整数
     */
    public static native int report_Field_GetValueInt();
    /**
     * 读值小数
     * @return 返回小数
     */
    public static native float report_Field_GetValueFloat();
    /**
     * 读文本
     * @return 返回文本
     */
    public static native String report_Field_GetValueString();
    /**
     * 读值逻辑
     * @return 返回布尔值
     */
    public static native boolean report_Field_GetValueBool();
    /**
     * 写值整数
     * @param ObjectPointer 整数参数
     */
    public static native void report_Field_SetValueInt(int ObjectPointer);
    /**
     * 写值小数
     * @param ObjectPointer 小数参数
     */
    public static native void report_Field_SetValueFloat(float ObjectPointer);
    /**
     * 写值文本
     * @param ObjectPointer 文本参数
     */
    public static native void report_Field_SetValueString(String ObjectPointer);
    /**
     * 写值逻辑型
     * @param ObjectPointer 布尔参数
     */
    public static native void report_Field_SetValueBool(boolean ObjectPointer);
    /**
     * 写值日期
     * @param ObjectPointer 小数参数
     */
    public static native void report_Field_SetValueDateFloat(float ObjectPointer);
    /**
     * 写值字节集
     * @param ObjectPointer 字节数组参数
     * @param dwSize 整数参数
     */
    public static native void report_Field_SetValueBytes(byte[] ObjectPointer, int dwSize);
    /**
     * 读数据字段
     * @return 返回文本
     */
    public static native String report_FieldBox_GetDataField();
    /**
     * 写数据字段
     * @param FieldName 文本参数
     */
    public static native void report_FieldBox_SetDataField(String FieldName);
    /**
     * 读数目
     * @return 返回整数
     */
    public static native int report_Fields_GetCount();
    /**
     * 获取项目_整数
     * @param value 整数参数
     * @return 返回整数
     */
    public static native int report_Fields_GetItemInt(int value);
    /**
     * 获取项目_文本型
     * @param value 整数参数
     * @return 返回整数
     */
    public static native int report_Fields_GetItemString(int value);
    /**
     * 按名称获取项目
     * @param Name 文本参数
     * @return 返回整数
     */
    public static native int report_Fields_GetItemByName(String Name);
    /**
     * 按序号获取项目
     * @param Index 整数参数
     * @return 返回整数
     */
    public static native int report_Fields_GetItemBy(int Index);
    /**
     * 增加
     * @return 返回整数
     */
    public static native int report_Fields_Add();
    /**
     * 改变项目顺序_整数
     * @param value 整数参数
     * @param Sequence 整数参数
     */
    public static native void report_Fields_ChangeItemOrderInt(int value, int Sequence);
    /**
     * 改变项目顺序_文本型
     * @param value 文本参数
     * @param Sequence 整数参数
     */
    public static native void report_Fields_ChangeItemOrderString(String value, int Sequence);
    /**
     * 改变项目顺序_名称
     * @param Name 文本参数
     * @param Sequence 整数参数
     */
    public static native void report_Fields_ChangeItemOrderByName(String Name, int Sequence);
    /**
     * 改变项目顺序_序号
     * @param Index 整数参数
     * @param Sequence 整数参数
     */
    public static native void report_Fields_ChangeItemOrderBy(int Index, int Sequence);
    /**
     * 按名称取序号
     * @param Name 文本参数
     * @return 返回整数
     */
    public static native int report_Fields_ByName(String Name);
    /**
     * 获取指定项目
     * @param Index 整数参数
     * @return 返回整数
     */
    public static native int report_Fields_ItemAt(int Index);
    /**
     * 删除项目_文本型
     * @param value 文本参数
     */
    public static native void report_Fields_RemoveString(String value);
    /**
     * 删除项目_整数
     * @param value 整数参数
     */
    public static native void report_Fields_RemoveInt(int value);
    /**
     * 按名称删除项目
     * @param Name 文本参数
     */
    public static native void report_Fields_RemoveByName(String Name);
    /**
     * 按序号删除项目
     * @param Index 整数参数
     */
    public static native void report_Fields_RemoveBy(int Index);
    /**
     * 全部删除
     */
    public static native void report_Fields_RemoveAll();
    /**
     * 读名称
     * @return 返回文本
     */
    public static native String report_Font_GetName();
    /**
     * 写名称
     * @param value 文本参数
     */
    public static native void report_Font_SetName(String value);
    /**
     * 读粗体
     * @return 返回布尔值
     */
    public static native boolean report_Font_GetBold();
    /**
     * 写粗体
     * @param value 布尔参数
     */
    public static native void report_Font_SetBold(boolean value);
    /**
     * 读斜体
     * @return 返回布尔值
     */
    public static native boolean report_Font_GetItalic();
    /**
     * 写斜体
     * @param value 布尔参数
     */
    public static native void report_Font_SetItalic(boolean value);
    /**
     * 读删除线
     * @return 返回布尔值
     */
    public static native boolean report_Font_GetStrikethrough();
    /**
     * 写删除线
     * @param value 布尔参数
     */
    public static native void report_Font_SetStrikethrough(boolean value);
    /**
     * 读下划线
     * @return 返回布尔值
     */
    public static native boolean report_Font_GetUnderline();
    /**
     * 写下划线
     * @param value 布尔参数
     */
    public static native void report_Font_SetUnderline(boolean value);
    /**
     * 读获取OLE字体
     * @return 返回整数
     */
    public static native int report_Font_GetOleFont();
    /**
     * 写设置OLE字体
     * @param value 整数参数
     */
    public static native void report_Font_SetOleFont(int value);
    /**
     * 读字号
     * @return 返回小数
     */
    public static native float report_Font_GetPoint();
    /**
     * 写字号
     * @param value 小数参数
     */
    public static native void report_Font_SetPoint(float value);
    /**
     * 读粗细度
     * @return 返回整数
     */
    public static native int report_Font_GetWeight();
    /**
     * 写粗细度
     * @param value 整数参数
     */
    public static native void report_Font_SetWeight(int value);
    /**
     * 读字符集
     * @return 返回整数
     */
    public static native int report_Font_GetCharset();
    /**
     * 写字符集
     * @param value 整数参数
     */
    public static native void report_Font_SetCharset(int value);
    /**
     * 读获取引用OLE字体
     * @return 返回整数
     */
    public static native int report_Font_GetUsingOleFont();
    /**
     * 克隆
     * @return 返回整数
     */
    public static native int report_Font_Clone();
    /**
     * 读获取行线画笔
     * @return 返回整数
     */
    public static native int report_FreeGrid_GetRowLinePen();
    /**
     * 读获取列线画笔
     * @return 返回整数
     */
    public static native int report_FreeGrid_GetColLinePen();
    /**
     * 读行数
     * @return 返回整数
     */
    public static native int report_FreeGrid_GetRowCount();
    /**
     * 写行数
     * @param value 整数参数
     */
    public static native void report_FreeGrid_SetRowCount(int value);
    /**
     * 读列数
     * @return 返回整数
     */
    public static native int report_FreeGrid_GetColumnCount();
    /**
     * 写列数
     * @param value 整数参数
     */
    public static native void report_FreeGrid_SetColumnCount(int value);
    /**
     * 读表头行数
     * @return 返回整数
     */
    public static native int report_FreeGrid_GetTitleRows();
    /**
     * 写表头行数
     * @param value 整数参数
     */
    public static native void report_FreeGrid_SetTitleRows(int value);
    /**
     * 读表头每页重复
     * @return 返回布尔值
     */
    public static native boolean report_FreeGrid_GetTitleRepeat();
    /**
     * 写表头每页重复
     * @param value 布尔参数
     */
    public static native void report_FreeGrid_SetTitleRepeat(boolean value);
    /**
     * 读伸展到页底
     * @return 返回布尔值
     */
    public static native boolean report_FreeGrid_GetGrowToBottom();
    /**
     * 写伸展到页底
     * @param value 布尔参数
     */
    public static native void report_FreeGrid_SetGrowToBottom(boolean value);
    /**
     * 读显示行线
     * @return 返回布尔值
     */
    public static native boolean report_FreeGrid_GetShowRowLine();
    /**
     * 写显示行线
     * @param value 布尔参数
     */
    public static native void report_FreeGrid_SetShowRowLine(boolean value);
    /**
     * 读显示列线
     * @return 返回布尔值
     */
    public static native boolean report_FreeGrid_GetShowColLine();
    /**
     * 写显示列线
     * @param value 布尔参数
     */
    public static native void report_FreeGrid_SetShowColLine(boolean value);
    /**
     * 获取指定行
     * @param RowNumber 整数参数
     * @return 返回整数
     */
    public static native int report_FreeGrid_RowAt(int RowNumber);
    /**
     * 获取指定列
     * @param ColumnNumber 整数参数
     * @return 返回整数
     */
    public static native int report_FreeGrid_ColumnAt(int ColumnNumber);
    /**
     * 获取指定单元格
     * @param RowNumber 整数参数
     * @param ColumnNumber 整数参数
     * @return 返回整数
     */
    public static native int report_FreeGrid_CellAt(int RowNumber, int ColumnNumber);
    /**
     * 按数据名称获取单元格
     * @param DataName 文本参数
     * @return 返回整数
     */
    public static native int report_FreeGrid_CellByDataName(String DataName);
    /**
     * 读数据名称
     * @return 返回文本
     */
    public static native String report_FreeGridCell_GetDataName();
    /**
     * 写数据名称
     * @param value 文本参数
     */
    public static native void report_FreeGridCell_SetDataName(String value);
    /**
     * 读文本
     * @return 返回文本
     */
    public static native String report_FreeGridCell_GetText();
    /**
     * 写文本
     * @param value 文本参数
     */
    public static native void report_FreeGridCell_SetText(String value);
    /**
     * 读合并行数
     * @return 返回整数
     */
    public static native int report_FreeGridCell_GetRowSpan();
    /**
     * 写合并行数
     * @param value 整数参数
     */
    public static native void report_FreeGridCell_SetRowSpan(int value);
    /**
     * 读合并列数
     * @return 返回整数
     */
    public static native int report_FreeGridCell_GetColSpan();
    /**
     * 写合并列数
     * @param value 整数参数
     */
    public static native void report_FreeGridCell_SetColSpan(int value);
    /**
     * 读获取自由表格
     * @return 返回整数
     */
    public static native int report_FreeGridColumn_GetFreeGrid();
    /**
     * 读锁定
     * @return 返回布尔值
     */
    public static native boolean report_FreeGridColumn_GetLocked();
    /**
     * 写锁定
     * @param value 布尔参数
     */
    public static native void report_FreeGridColumn_SetLocked(boolean value);
    /**
     * 读可见性
     * @return 返回布尔值
     */
    public static native boolean report_FreeGridColumn_GetVisible();
    /**
     * 写可见性
     * @param value 布尔参数
     */
    public static native void report_FreeGridColumn_SetVisible(boolean value);
    /**
     * 读宽度
     * @return 返回小数
     */
    public static native float report_FreeGridColumn_GetWidth();
    /**
     * 写宽度
     * @param value 小数参数
     */
    public static native void report_FreeGridColumn_SetWidth(float value);
    /**
     * 读获取自由表格
     * @return 返回整数
     */
    public static native int report_FreeGridRow_GetFreeGrid();
    /**
     * 读锁定
     * @return 返回布尔值
     */
    public static native boolean report_FreeGridRow_GetLocked();
    /**
     * 写锁定
     * @param value 布尔参数
     */
    public static native void report_FreeGridRow_SetLocked(boolean value);
    /**
     * 读可见性
     * @return 返回布尔值
     */
    public static native boolean report_FreeGridRow_GetVisible();
    /**
     * 写可见性
     * @param value 布尔参数
     */
    public static native void report_FreeGridRow_SetVisible(boolean value);
    /**
     * 读高度
     * @return 返回小数
     */
    public static native float report_FreeGridRow_GetHeight();
    /**
     * 写高度
     * @param value 小数参数
     */
    public static native void report_FreeGridRow_SetHeight(float value);
    /**
     * 读保持同页
     * @return 返回布尔值
     */
    public static native boolean report_FreeGridRow_GetKeepTogether();
    /**
     * 写保持同页
     * @param value 布尔参数
     */
    public static native void report_FreeGridRow_SetKeepTogether(boolean value);
    /**
     * 读依据计量单位
     * @return 返回布尔值
     */
    public static native boolean report_Graphics_GetByUnit();
    /**
     * 写依据计量单位
     * @param ByUnit 布尔参数
     */
    public static native void report_Graphics_SetByUnit(boolean ByUnit);
    /**
     * 读左
     * @return 返回整数
     */
    public static native int report_Graphics_GetLeft();
    /**
     * 读上
     * @return 返回整数
     */
    public static native int report_Graphics_GetTop();
    /**
     * 读宽度
     * @return 返回整数
     */
    public static native int report_Graphics_GetWidth();
    /**
     * 读高度
     * @return 返回整数
     */
    public static native int report_Graphics_GetHeight();
    /**
     * 读左端位置
     * @return 返回整数
     */
    public static native int report_Graphics_GetLeftInPaper();
    /**
     * 读上端位置
     * @return 返回整数
     */
    public static native int report_Graphics_GetTopInPaper();
    /**
     * 读取内侧控制点X
     * @return 返回整数
     */
    public static native int report_Graphics_GetCCPInnerX();
    /**
     * 读取内侧控制点Y
     * @return 返回整数
     */
    public static native int report_Graphics_GetCCPInnerY();
    /**
     * 读取外侧控制点X
     * @return 返回整数
     */
    public static native int report_Graphics_GetCCPOuterX();
    /**
     * 读取外侧控制点Y
     * @return 返回整数
     */
    public static native int report_Graphics_GetCCPOuterY();
    /**
     * 绘制巴塞尔曲线
     * @param innerPointX 小数参数
     * @param innerPointY 小数参数
     * @param outerPointX 小数参数
     * @param outerPointY 小数参数
     * @param curveEndPointX 小数参数
     * @param curveEndPointY 小数参数
     */
    public static native void report_Graphics_CurveTo(float innerPointX, float innerPointY, float outerPointX, float outerPointY, float curveEndPointX, float curveEndPointY);
    /**
     * 取巴塞尔曲线控制点
     * @param frontPointX 小数参数
     * @param frontPointY 小数参数
     * @param middlePointX 小数参数
     * @param middlePointY 小数参数
     * @param backPointX 小数参数
     * @param backPointY 小数参数
     */
    public static native void report_Graphics_CalcCurveControlPoints(float frontPointX, float frontPointY, float middlePointX, float middlePointY, float backPointX, float backPointY);
    /**
     * 开始路径
     */
    public static native void report_Graphics_BeginPath();
    /**
     * 封闭路径
     */
    public static native void report_Graphics_ClosePath();
    /**
     * 结束路径
     */
    public static native void report_Graphics_EndPath();
    /**
     * 绘制路径
     */
    public static native void report_Graphics_StrokePath();
    /**
     * 填充路径
     */
    public static native void report_Graphics_FillPath();
    /**
     * 绘制并填充路径
     */
    public static native void report_Graphics_StrokeAndFillPath();
    /**
     * 读计算标签文本高度
     * @param textString 整数参数
     * @return 返回小数
     */
    public static native float report_Graphics_GetCalcLabelTextHeight(int textString);
    /**
     * 读计算标签文本宽度
     * @param textString 整数参数
     * @return 返回小数
     */
    public static native float report_Graphics_GetCalcLabelTextWidth(int textString);
    /**
     * 读计算文本输出个数
     * @param width 小数参数
     * @param height 小数参数
     * @param textString 整数参数
     * @param autoWrap 布尔参数
     * @return 返回整数
     */
    public static native int report_Graphics_GetCalcTextOutLen(float width, float height, int textString, boolean autoWrap);
    /**
     * 读计算格式文本输出宽度
     * @param Text 整数参数
     * @param value 整数参数
     * @return 返回小数
     */
    public static native float report_Graphics_GetCalcDrawFormatTextWidth(int Text, int value);
    /**
     * 读计算格式文本输出高度
     * @param textString 整数参数
     * @param width 小数参数
     * @param textFormatObject 整数参数
     * @return 返回小数
     */
    public static native float report_Graphics_GetCalcDrawFormatTextHeight(int textString, float width, int textFormatObject);
    /**
     * 读计算格式文本输出个数
     * @param textString 整数参数
     * @param width 小数参数
     * @param height 小数参数
     * @param textFormatObject 整数参数
     * @return 返回整数
     */
    public static native int report_Graphics_GetCalcDrawFormatTextOutLen(int textString, float width, float height, int textFormatObject);
    /**
     * 绘制标签文本
     * @param textString 整数参数
     * @param left 小数参数
     * @param top 小数参数
     */
    public static native void report_Graphics_DrawLabelText(int textString, float left, float top);
    /**
     * 绘制旋转文本
     * @param textString 整数参数
     * @param left 小数参数
     * @param top 小数参数
     * @param angle 小数参数
     */
    public static native void report_Graphics_DrawRotateText(int textString, float left, float top, float angle);
    /**
     * 绘制文本
     * @param textString 整数参数
     * @param left 小数参数
     * @param top 小数参数
     * @param width 小数参数
     * @param height 小数参数
     * @param value 整数参数
     * @param value2 布尔参数
     */
    public static native void report_Graphics_DrawText(int textString, float left, float top, float width, float height, int value, boolean value2);
    /**
     * 绘制格式文本
     * @param textString 整数参数
     * @param left 小数参数
     * @param top 小数参数
     * @param width 小数参数
     * @param height 小数参数
     * @param textFormatObject 整数参数
     */
    public static native void report_Graphics_DrawFormatText(int textString, float left, float top, float width, float height, int textFormatObject);
    /**
     * 绘制格式文本缩小适应
     * @param textString 整数参数
     * @param left 小数参数
     * @param top 小数参数
     * @param width 小数参数
     * @param height 小数参数
     * @param textFormatObject 整数参数
     */
    public static native void report_Graphics_DrawFormatTextShrinkToFit(int textString, float left, float top, float width, float height, int textFormatObject);
    /**
     * 绘制像素点
     * @param value 小数参数
     * @param value2 小数参数
     * @param value3 整数参数
     */
    public static native void report_Graphics_DrawPixel(float value, float value2, int value3);
    /**
     * 绘制标记点
     * @param value 整数参数
     * @param centerPointX 小数参数
     * @param centerPointY 小数参数
     * @param size 小数参数
     */
    public static native void report_Graphics_DrawPointMarker(int value, float centerPointX, float centerPointY, float size);
    /**
     * 绘制图片
     * @param reportPictureObject 整数参数
     * @param left 小数参数
     * @param top 小数参数
     * @param width 小数参数
     * @param height 小数参数
     * @param value 整数参数
     * @param value2 整数参数
     * @param value3 整数参数
     */
    public static native void report_Graphics_DrawPicture(int reportPictureObject, float left, float top, float width, float height, int value, int value2, int value3);
    /**
     * 画弧线
     * @param value 小数参数
     * @param value2 小数参数
     * @param value3 小数参数
     * @param startAngle 小数参数
     * @param endAngle 小数参数
     */
    public static native void report_Graphics_Arc(float value, float value2, float value3, float startAngle, float endAngle);
    /**
     * 画扇形
     * @param value 小数参数
     * @param value2 小数参数
     * @param value3 小数参数
     * @param startAngle 小数参数
     * @param endAngle 小数参数
     * @param backgroundFill 布尔参数
     */
    public static native void report_Graphics_Pie(float value, float value2, float value3, float startAngle, float endAngle, boolean backgroundFill);
    /**
     * 画椭圆
     * @param left 小数参数
     * @param top 小数参数
     * @param width 小数参数
     * @param height 小数参数
     * @param backgroundFill 整数参数
     */
    public static native void report_Graphics_Ellipse(float left, float top, float width, float height, int backgroundFill);
    /**
     * 画椭圆弧线
     * @param left 小数参数
     * @param top 小数参数
     * @param width 小数参数
     * @param height 小数参数
     * @param startAngle 小数参数
     * @param endAngle 小数参数
     */
    public static native void report_Graphics_EllipseArc(float left, float top, float width, float height, float startAngle, float endAngle);
    /**
     * 画椭圆扇形
     * @param left 小数参数
     * @param top 小数参数
     * @param width 小数参数
     * @param height 小数参数
     * @param startAngle 小数参数
     * @param endAngle 小数参数
     * @param backgroundFill 布尔参数
     */
    public static native void report_Graphics_EllipsePie(float left, float top, float width, float height, float startAngle, float endAngle, boolean backgroundFill);
    /**
     * 画矩形
     * @param left 小数参数
     * @param top 小数参数
     * @param width 小数参数
     * @param height 小数参数
     * @param backgroundFill 布尔参数
     */
    public static native void report_Graphics_Rectangle(float left, float top, float width, float height, boolean backgroundFill);
    /**
     * 画填充矩形
     * @param left 小数参数
     * @param top 小数参数
     * @param width 小数参数
     * @param height 小数参数
     * @param value 整数参数
     */
    public static native void report_Graphics_FillRect(float left, float top, float width, float height, int value);
    /**
     * 画圆角矩形
     * @param left 小数参数
     * @param top 小数参数
     * @param width 小数参数
     * @param height 小数参数
     * @param ellipseWidth 整数参数
     * @param ellipseHeight 整数参数
     * @param backgroundFill 布尔参数
     */
    public static native void report_Graphics_RoundRect(float left, float top, float width, float height, int ellipseWidth, int ellipseHeight, boolean backgroundFill);
    /**
     * 画笔起点
     * @param left 小数参数
     * @param top 小数参数
     */
    public static native void report_Graphics_MoveTo(float left, float top);
    /**
     * 画线到
     * @param left 小数参数
     * @param top 小数参数
     */
    public static native void report_Graphics_LineTo(float left, float top);
    /**
     * 剪裁矩形
     * @param left 小数参数
     * @param top 小数参数
     * @param width 小数参数
     * @param height 小数参数
     */
    public static native void report_Graphics_PushClipRect(float left, float top, float width, float height);
    /**
     * 剪裁恢复
     */
    public static native void report_Graphics_PopClipRect();
    /**
     * 字号改变
     * @param fontSize 小数参数
     */
    public static native void report_Graphics_FontPointChangeTo(float fontSize);
    /**
     * 字号恢复
     */
    public static native void report_Graphics_FontPointRestore();
    /**
     * 填充背景色改变
     * @param value 整数参数
     */
    public static native void report_Graphics_SelectFillColor(int value);
    /**
     * 填充背景色恢复
     */
    public static native void report_Graphics_RestoreFillColor();
    /**
     * 字体改变
     * @param fontObject 整数参数
     */
    public static native void report_Graphics_SelectFont(int fontObject);
    /**
     * 字体恢复
     */
    public static native void report_Graphics_RestoreFont();
    /**
     * 画笔改变
     * @param width 小数参数
     * @param color 整数参数
     * @param value 整数参数
     */
    public static native void report_Graphics_SelectPen(float width, int color, int value);
    /**
     * 画笔恢复
     */
    public static native void report_Graphics_RestorePen();
    /**
     * 文字色改变
     * @param value 整数参数
     */
    public static native void report_Graphics_SelectTextColor(int value);
    /**
     * 文字色恢复
     */
    public static native void report_Graphics_RestoreTextColor();
    /**
     * 读依据字段
     * @return 返回文本
     */
    public static native String report_Group_GetByFields();
    /**
     * 写依据字段
     * @param FieldName 文本参数
     */
    public static native void report_Group_SetByFields(String FieldName);
    /**
     * 读名称
     * @return 返回文本
     */
    public static native String report_Group_GetName();
    /**
     * 写名称
     * @param Name 文本参数
     */
    public static native void report_Group_SetName(String Name);
    /**
     * 读每页最大组数
     * @return 返回整数
     */
    public static native int report_Group_GetLimitsPerPage();
    /**
     * 写每页最大组数
     * @param value 整数参数
     */
    public static native void report_Group_SetLimitsPerPage(int value);
    /**
     * 读排序统计框
     * @return 返回文本
     */
    public static native String report_Group_GetSortSummaryBox();
    /**
     * 写排序统计框
     * @param Name 文本参数
     */
    public static native void report_Group_SetSortSummaryBox(String Name);
    /**
     * 读分组开始脚本
     * @return 返回文本
     */
    public static native String report_Group_GetGroupBeginScript();
    /**
     * 写分组开始脚本
     * @param Text 文本参数
     */
    public static native void report_Group_SetGroupBeginScript(String Text);
    /**
     * 读分组结束脚本
     * @return 返回文本
     */
    public static native String report_Group_GetGroupEndScript();
    /**
     * 写分组结束脚本
     * @param ScriptText 文本参数
     */
    public static native void report_Group_SetGroupEndScript(String ScriptText);
    /**
     * 读按页分组
     * @return 返回布尔值
     */
    public static native boolean report_Group_GetPageGroup();
    /**
     * 写按页分组
     * @param value 布尔参数
     */
    public static native void report_Group_SetPageGroup(boolean value);
    /**
     * 读排序升序
     * @return 返回布尔值
     */
    public static native boolean report_Group_GetSortAsc();
    /**
     * 写排序升序
     * @param value 布尔参数
     */
    public static native void report_Group_SetSortAsc(boolean value);
    /**
     * 读获取父对象
     * @return 返回整数
     */
    public static native int report_Group_GetParent();
    /**
     * 读获取分组头
     * @return 返回整数
     */
    public static native int report_Group_GetHeader();
    /**
     * 读获取分组尾
     * @return 返回整数
     */
    public static native int report_Group_GetFooter();
    /**
     * 读追加空白行排除
     * @return 返回布尔值
     */
    public static native boolean report_GroupFooter_GetAppendBlankRowExclude();
    /**
     * 写追加空白行排除
     * @param value 布尔参数
     */
    public static native void report_GroupFooter_SetAppendBlankRowExclude(boolean value);
    /**
     * 读打印在页底
     * @return 返回布尔值
     */
    public static native boolean report_GroupFooter_GetPrintAtBottom();
    /**
     * 写打印在页底
     * @param value 布尔参数
     */
    public static native void report_GroupFooter_SetPrintAtBottom(boolean value);
    /**
     * 读包括分组尾
     * @return 返回布尔值
     */
    public static native boolean report_GroupHeader_GetIncludeFooter();
    /**
     * 写包括分组尾
     * @param value 布尔参数
     */
    public static native void report_GroupHeader_SetIncludeFooter(boolean value);
    /**
     * 读占列式
     * @return 返回布尔值
     */
    public static native boolean report_GroupHeader_GetOccupyColumn();
    /**
     * 写占列式
     * @param value 布尔参数
     */
    public static native void report_GroupHeader_SetOccupyColumn(boolean value);
    /**
     * 读占据列
     * @return 返回文本
     */
    public static native String report_GroupHeader_GetOccupiedColumns();
    /**
     * 写占据列
     * @param ColumnName 文本参数
     */
    public static native void report_GroupHeader_SetOccupiedColumns(String ColumnName);
    /**
     * 读显示同列
     * @return 返回布尔值
     */
    public static native boolean report_GroupHeader_GetSameAsColumn();
    /**
     * 写显示同列
     * @param value 布尔参数
     */
    public static native void report_GroupHeader_SetSameAsColumn(boolean value);
    /**
     * 读保持分组同页
     * @return 返回整数
     */
    public static native int report_GroupHeader_GetGroupKeepTogether();
    /**
     * 写保持分组同页
     * @param value 整数参数
     */
    public static native void report_GroupHeader_SetGroupKeepTogether(int value);
    /**
     * 读垂直对齐方式
     * @return 返回整数
     */
    public static native int report_GroupHeader_GetVAlign();
    /**
     * 写垂直对齐方式
     * @param value 整数参数
     */
    public static native void report_GroupHeader_SetVAlign(int value);
    /**
     * 读获取分组
     * @return 返回整数
     */
    public static native int report_GroupSection_GetGroup();
    /**
     * 读横向分页锁定
     * @return 返回布尔值
     */
    public static native boolean report_GroupSection_GetHNewPageFixed();
    /**
     * 写横向分页锁定
     * @param value 布尔参数
     */
    public static native void report_GroupSection_SetHNewPageFixed(boolean value);
    /**
     * 读分组保持同页
     * @return 返回布尔值
     */
    public static native boolean report_GroupSection_GetKeepTogether();
    /**
     * 写分组保持同页
     * @param value 布尔参数
     */
    public static native void report_GroupSection_SetKeepTogether(boolean value);
    /**
     * 读打印输出边框
     * @return 返回布尔值
     */
    public static native boolean report_GroupSection_GetPrintGridBorder();
    /**
     * 写打印输出边框
     * @param value 布尔参数
     */
    public static native void report_GroupSection_SetPrintGridBorder(boolean value);
    /**
     * 读每页重复打印
     * @return 返回布尔值
     */
    public static native boolean report_GroupSection_GetRepeatOnPage();
    /**
     * 写每页重复打印
     * @param value 布尔参数
     */
    public static native void report_GroupSection_SetRepeatOnPage(boolean value);
    /**
     * 读换新页
     * @return 返回整数
     */
    public static native int report_GroupSection_GetNewPage();
    /**
     * 写换新页
     * @param value 整数参数
     */
    public static native void report_GroupSection_SetNewPage(int value);
    /**
     * 读换新页栏
     * @return 返回整数
     */
    public static native int report_GroupSection_GetNewPageColumn();
    /**
     * 写换新页栏
     * @param value 整数参数
     */
    public static native void report_GroupSection_SetNewPageColumn(int value);
    /**
     * 数目
     * @return 返回整数
     */
    public static native int report_Groups_GetCount();
    /**
     * 获取项目_整数
     * @param value 整数参数
     * @return 返回整数
     */
    public static native int report_Groups_GetItemInt(int value);
    /**
     * 获取项目_文本型
     * @param value 整数参数
     * @return 返回整数
     */
    public static native int report_Groups_GetItemString(int value);
    /**
     * 按名称获取项目
     * @param Name 整数参数
     * @return 返回整数
     */
    public static native int report_Groups_GetItemByName(int Name);
    /**
     * 按序号获取项目
     * @param Index 整数参数
     * @return 返回整数
     */
    public static native int report_Groups_GetItemBy(int Index);
    /**
     * 增加
     * @return 返回整数
     */
    public static native int report_Groups_Add();
    /**
     * 改变项目顺序_整数
     * @param value 整数参数
     * @param Sequence 整数参数
     */
    public static native void report_Groups_ChangeItemOrderInt(int value, int Sequence);
    /**
     * 改变项目顺序_文本型
     * @param value 文本参数
     * @param Sequence 整数参数
     */
    public static native void report_Groups_ChangeItemOrderString(String value, int Sequence);
    /**
     * 改变项目顺序_名称
     * @param Name 文本参数
     * @param Sequence 整数参数
     */
    public static native void report_Groups_ChangeItemOrderByName(String Name, int Sequence);
    /**
     * 按名称取序号
     * @param Name 整数参数
     * @return 返回整数
     */
    public static native int report_Groups_GetByName(int Name);
    /**
     * 获取指定项目
     * @param Index 整数参数
     * @return 返回整数
     */
    public static native int report_Groups_GetItemAt(int Index);
    /**
     * 删除项目_文本型
     * @param value 文本参数
     */
    public static native void report_Groups_RemoveString(String value);
    /**
     * 删除项目_整数
     * @param value 整数参数
     */
    public static native void report_Groups_RemoveInt(int value);
    /**
     * 按名称删除项目
     * @param Name 文本参数
     */
    public static native void report_Groups_RemoveByName(String Name);
    /**
     * 按序号删除项目
     * @param Index 整数参数
     */
    public static native void report_Groups_RemoveBy(int Index);
    /**
     * 全部删除
     */
    public static native void report_Groups_RemoveAll();
    /**
     * 读单位
     * @return 返回整数
     */
    public static native int report_IGRSharePrintSetupObject_GetUnit();
    /**
     * 写单位
     * @param value 整数参数
     */
    public static native void report_IGRSharePrintSetupObject_SetUnit(int value);
    /**
     * 读打印机名称
     * @return 返回文本
     */
    public static native String report_IGRSharePrintSetupObject_GetPrinterName();
    /**
     * 写打印机名称
     * @param value 文本参数
     */
    public static native void report_IGRSharePrintSetupObject_SetPrinterName(String value);
    /**
     * 读页面长度
     * @return 返回小数
     */
    public static native float report_IGRSharePrintSetupObject_GetPaperLength();
    /**
     * 写页面长度
     * @param value 小数参数
     */
    public static native void report_IGRSharePrintSetupObject_SetPaperLength(float value);
    /**
     * 读纸张宽度
     * @return 返回小数
     */
    public static native float report_IGRSharePrintSetupObject_GetPaperWidth();
    /**
     * 写纸张宽度
     * @param value 小数参数
     */
    public static native void report_IGRSharePrintSetupObject_SetPaperWidth(float value);
    /**
     * 读纸张名称
     * @return 返回文本
     */
    public static native String report_IGRSharePrintSetupObject_GetPaperName();
    /**
     * 写纸张名称
     * @param value 文本参数
     */
    public static native void report_IGRSharePrintSetupObject_SetPaperName(String value);
    /**
     * 读纸张规格
     * @return 返回整数
     */
    public static native int report_IGRSharePrintSetupObject_GetPaperSize();
    /**
     * 写纸张规格
     * @param value 整数参数
     */
    public static native void report_IGRSharePrintSetupObject_SetPaperSize(int value);
    /**
     * 读纸张方向
     * @return 返回整数
     */
    public static native int report_IGRSharePrintSetupObject_GetPaperOrientation();
    /**
     * 写纸张方向
     * @param value 整数参数
     */
    public static native void report_IGRSharePrintSetupObject_SetPaperOrientation(int value);
    /**
     * 读左边距
     * @return 返回小数
     */
    public static native float report_IGRSharePrintSetupObject_GetLeftMargin();
    /**
     * 写左边距
     * @param value 小数参数
     */
    public static native void report_IGRSharePrintSetupObject_SetLeftMargin(float value);
    /**
     * 读右边距
     * @return 返回小数
     */
    public static native float report_IGRSharePrintSetupObject_GetRightMargin();
    /**
     * 写右边距
     * @param value 小数参数
     */
    public static native void report_IGRSharePrintSetupObject_SetRightMargin(float value);
    /**
     * 读上边距
     * @return 返回小数
     */
    public static native float report_IGRSharePrintSetupObject_GetTopMargin();
    /**
     * 写上边距
     * @param value 小数参数
     */
    public static native void report_IGRSharePrintSetupObject_SetTopMargin(float value);
    /**
     * 读下边距
     * @return 返回小数
     */
    public static native float report_IGRSharePrintSetupObject_GetBottomMargin();
    /**
     * 写下边距
     * @param value 小数参数
     */
    public static native void report_IGRSharePrintSetupObject_SetBottomMargin(float value);
    /**
     * 读纸张来源
     * @return 返回整数
     */
    public static native int report_IGRSharePrintSetupObject_GetPaperSource();
    /**
     * 写纸张来源
     * @param value 整数参数
     */
    public static native void report_IGRSharePrintSetupObject_SetPaperSource(int value);
    /**
     * 打印机对话框
     * @return 返回布尔值
     */
    public static native boolean report_IGRSharePrintSetupObject_PrintDialog();
    /**
     * 打印机设置对话框
     * @return 返回布尔值
     */
    public static native boolean report_IGRSharePrintSetupObject_PrinterSetupDialog();
    /**
     * 页面设置对话框
     * @return 返回布尔值
     */
    public static native boolean report_IGRSharePrintSetupObject_PageSetupDialog();
    /**
     * 读数目
     * @return 返回整数
     */
    public static native int report_ImageList_GetCount();
    /**
     * 获取项目
     * @param Index 整数参数
     * @return 返回整数
     */
    public static native int report_ImageList_Item(int Index);
    /**
     * 增加
     * @param Image 整数参数
     * @return 返回整数
     */
    public static native int report_ImageList_Add(int Image);
    /**
     * 删除项目
     * @param Index 整数参数
     */
    public static native void report_ImageList_Remove(int Index);
    /**
     * 全部删除
     */
    public static native void report_ImageList_RemoveAll();
    /**
     * 从文件增加图片
     * @param FileName 文本参数
     * @return 返回整数
     */
    public static native int report_ImageList_AddFromFile(String FileName);
    /**
     * 从二进制增加图片
     * @param Binary 整数参数
     * @return 返回整数
     */
    public static native int report_ImageList_AddFromBinary(int Binary);
    /**
     * 从内存增加图片
     * @param Buffer 整数参数
     * @param BytesCount 整数参数
     * @return 返回整数
     */
    public static native int report_ImageList_AddFromMemory(int Buffer, int BytesCount);
    /**
     * 读获取画笔
     * @return 返回整数
     */
    public static native int report_Line_GetLinePen();
    /**
     * 读X1
     * @return 返回小数
     */
    public static native float report_Line_GetX1();
    /**
     * 写X1
     * @param value 小数参数
     */
    public static native void report_Line_SetX1(float value);
    /**
     * 读X2
     * @return 返回小数
     */
    public static native float report_Line_GetX2();
    /**
     * 写X2
     * @param value 小数参数
     */
    public static native void report_Line_SetX2(float value);
    /**
     * 读Y1
     * @return 返回小数
     */
    public static native float report_Line_GetY1();
    /**
     * 写Y1
     * @param value 小数参数
     */
    public static native void report_Line_SetY1(float value);
    /**
     * 读Y2
     * @return 返回小数
     */
    public static native float report_Line_GetY2();
    /**
     * 写Y2
     * @param value 小数参数
     */
    public static native void report_Line_SetY2(float value);
    /**
     * 读浮点数值
     * @return 返回小数
     */
    public static native float report_MemoBox_GetAsFloat();
    /**
     * 写浮点数值
     * @param value 小数参数
     */
    public static native void report_MemoBox_SetAsFloat(float value);
    /**
     * 读超出显示至
     * @return 返回文本
     */
    public static native String report_MemoBox_GetFlowTo();
    /**
     * 写超出显示至
     * @param Name 文本参数
     */
    public static native void report_MemoBox_SetFlowTo(String Name);
    /**
     * 读文本
     * @return 返回文本
     */
    public static native String report_MemoBox_GetText();
    /**
     * 写文本
     * @param Text 文本参数
     */
    public static native void report_MemoBox_SetText(String Text);
    /**
     * 读置对象
     * @return 返回整数
     */
    public static native int report_Object_GetCOMObject();
    /**
     * 写置对象
     * @param addressCOM 整数参数
     * @return 返回布尔值
     */
    public static native boolean report_ExportOption_SetCOMObject(int addressCOM);
    /**
     * 读标识
     * @return 返回文本
     */
    public static native String report_Object_GetTag();
    /**
     * 写标识
     * @param TopicText 文本参数
     */
    public static native void report_Object_SetTag(String TopicText);
    /**
     * 读名称
     * @return 返回文本
     */
    public static native String report_Parameter_GetName();
    /**
     * 写名称
     * @param Name 文本参数
     */
    public static native void report_Parameter_SetName(String Name);
    /**
     * 读显示文字
     * @return 返回文本
     */
    public static native String report_Parameter_GetDisplayText();
    /**
     * 写显示文字
     * @param Name 文本参数
     */
    public static native void report_Parameter_SetDisplayText(String Name);
    /**
     * 读逻辑值
     * @return 返回布尔值
     */
    public static native boolean report_Parameter_GetAsBoolean();
    /**
     * 写逻辑值
     * @param value 布尔参数
     */
    public static native void report_Parameter_SetAsBoolean(boolean value);
    /**
     * 读日期时间值
     * @return 返回小数
     */
    public static native float report_Parameter_GetAsDateTime();
    /**
     * 写日期时间值
     * @param value 小数参数
     */
    public static native void report_Parameter_SetAsDateTime(float value);
    /**
     * 读浮点数值
     * @return 返回小数
     */
    public static native float report_Parameter_GetAsFloat();
    /**
     * 写浮点数值
     * @param value 小数参数
     */
    public static native void report_Parameter_SetAsFloat(float value);
    /**
     * 读整数值
     * @return 返回整数
     */
    public static native int report_Parameter_GetAsInteger();
    /**
     * 写整数值
     * @param value 整数参数
     */
    public static native void report_Parameter_SetAsInteger(int value);
    /**
     * 读字符值
     * @return 返回文本
     */
    public static native String report_Parameter_GetAsString();
    /**
     * 写字符值
     * @param Text 文本参数
     */
    public static native void report_Parameter_SetAsString(String Text);
    /**
     * 读类型
     */
    public static native void report_Parameter_GetDataType();
    /**
     * 写类型
     * @param value 整数参数
     */
    public static native void report_Parameter_SetDataType(int value);
    /**
     * 读格式
     * @return 返回文本
     */
    public static native String report_Parameter_GetFormat();
    /**
     * 写格式
     * @param FormatText 文本参数
     */
    public static native void report_Parameter_SetFormat(String FormatText);
    /**
     * 读是否空值
     * @return 返回布尔值
     */
    public static native boolean report_Parameter_GetIsNull();
    /**
     * 清空
     */
    public static native void report_Parameter_Clear();
    /**
     * 读值整数
     * @return 返回整数
     */
    public static native int report_Parameter_GetValueInt();
    /**
     * 读值小数
     * @return 返回小数
     */
    public static native float report_Parameter_GetValueFloat();
    /**
     * 读文本
     * @return 返回文本
     */
    public static native String report_Parameter_GetValueString();
    /**
     * 读值逻辑
     * @return 返回布尔值
     */
    public static native boolean report_Parameter_GetValueBool();
    /**
     * 写值整数
     * @param ObjectPointer 整数参数
     */
    public static native void report_Parameter_SetValueInt(int ObjectPointer);
    /**
     * 写值小数
     * @param ObjectPointer 小数参数
     */
    public static native void report_Parameter_SetValueFloat(float ObjectPointer);
    /**
     * 写值文本
     * @param ObjectPointer 文本参数
     */
    public static native void report_Parameter_SetValueString(String ObjectPointer);
    /**
     * 写值逻辑型
     * @param ObjectPointer 布尔参数
     */
    public static native void report_Parameter_SetValueBool(boolean ObjectPointer);
    /**
     * 写值日期
     * @param ObjectPointer 小数参数
     */
    public static native void report_Parameter_SetValueDateFloat(float ObjectPointer);
    /**
     * 写值字节集
     * @param ObjectPointer 字节数组参数
     * @param dwSize 整数参数
     */
    public static native void report_Parameter_SetValueBytes(byte[] ObjectPointer, int dwSize);
    /**
     * 读数目
     * @return 返回整数
     */
    public static native int report_Parameters_GetCount();
    /**
     * 获取项目_整数
     * @param value 整数参数
     * @return 返回整数
     */
    public static native int report_Parameters_GetItemInt(int value);
    /**
     * 获取项目_文本型
     * @param value 整数参数
     * @return 返回整数
     */
    public static native int report_Parameters_GetItemString(int value);
    /**
     * 按名称获取项目
     * @param Name 文本参数
     * @return 返回整数
     */
    public static native int report_Parameters_GetItemByName(String Name);
    /**
     * 按序号获取项目
     * @param Index 整数参数
     * @return 返回整数
     */
    public static native int report_Parameters_GetItemBy(int Index);
    /**
     * 增加
     * @return 返回整数
     */
    public static native int report_Parameters_Add();
    /**
     * 改变项目顺序_整数
     * @param value 整数参数
     * @param Sequence 整数参数
     */
    public static native void report_Parameters_ChangeItemOrderInt(int value, int Sequence);
    /**
     * 改变项目顺序_文本型
     * @param value 文本参数
     * @param Sequence 整数参数
     */
    public static native void report_Parameters_ChangeItemOrderString(String value, int Sequence);
    /**
     * 改变项目顺序_名称
     * @param Name 文本参数
     * @param Sequence 整数参数
     */
    public static native void report_Parameters_ChangeItemOrderByName(String Name, int Sequence);
    /**
     * 改变项目顺序_序号
     * @param Index 整数参数
     * @param Sequence 整数参数
     */
    public static native void report_Parameters_ChangeItemOrderBy(int Index, int Sequence);
    /**
     * 按名称取序号
     * @param Name 文本参数
     * @return 返回整数
     */
    public static native int report_Parameters_ByName(String Name);
    /**
     * 获取指定项目
     * @param Index 整数参数
     * @return 返回整数
     */
    public static native int report_Parameters_ItemAt(int Index);
    /**
     * 删除项目_文本型
     * @param value 文本参数
     */
    public static native void report_Parameters_RemoveString(String value);
    /**
     * 删除项目_整数
     * @param value 整数参数
     */
    public static native void report_Parameters_RemoveInt(int value);
    /**
     * 按名称删除项目
     * @param Name 文本参数
     */
    public static native void report_Parameters_RemoveByName(String Name);
    /**
     * 按序号删除项目
     * @param Index 整数参数
     */
    public static native void report_Parameters_RemoveBy(int Index);
    /**
     * 全部删除
     */
    public static native void report_Parameters_RemoveAll();
    /**
     * 读颜色
     * @return 返回整数
     */
    public static native int report_Pen_GetColor();
    /**
     * 写颜色
     * @param value 整数参数
     */
    public static native void report_Pen_SetColor(int value);
    /**
     * 读宽度
     * @return 返回小数
     */
    public static native float report_Pen_GetWidth();
    /**
     * 写宽度
     * @param value 小数参数
     */
    public static native void report_Pen_SetWidth(float value);
    /**
     * 读类型
     * @return 返回整数
     */
    public static native int report_Pen_GetStyle();
    /**
     * 写类型
     * @param value 整数参数
     */
    public static native void report_Pen_SetStyle(int value);
    /**
     * 读高度
     * @return 返回整数
     */
    public static native int report_Picture_GetHeight();
    /**
     * 写高度
     * @param value 整数参数
     */
    public static native void report_Picture_SetHeight(int value);
    /**
     * 读宽度
     * @return 返回整数
     */
    public static native int report_Picture_GetWidth();
    /**
     * 写宽度
     * @param value 整数参数
     */
    public static native void report_Picture_SetWidth(int value);
    /**
     * 读类型
     * @return 返回整数
     */
    public static native int report_Picture_GetType();
    /**
     * 写类型
     * @param value 整数参数
     */
    public static native void report_Picture_SetType(int value);
    /**
     * 读图片
     * @return 返回整数
     */
    public static native int report_Picture_GetOlePicture();
    /**
     * 写图片
     * @param value 整数参数
     */
    public static native void report_Picture_SetOlePicture(int value);
    /**
     * 从文件载入图片
     * @param FileName 文本参数
     * @return 返回布尔值
     */
    public static native boolean report_Picture_LoadFromFile(String FileName);
    /**
     * 从二进制载入图片
     * @param value 整数参数
     * @return 返回布尔值
     */
    public static native boolean report_Picture_LoadFromBinary(int value);
    /**
     * 从字段载入图片
     * @param value 文本参数
     * @return 返回布尔值
     */
    public static native boolean report_Picture_LoadFromField(String value);
    /**
     * 从内存载入图片
     * @param DataPointer 整数参数
     * @param DataSize 整数参数
     */
    public static native void report_Picture_LoadFromMemory(int DataPointer, int DataSize);
    /**
     * 保存到文件
     * @param FileName 文本参数
     * @return 返回布尔值
     */
    public static native boolean report_Picture_SaveToFile(String FileName);
    /**
     * 渲染
     * @param ObjectHandle 整数参数
     * @param value 整数参数
     * @param value2 整数参数
     * @param Width 整数参数
     * @param Height 整数参数
     * @param value3 整数参数
     * @param value4 整数参数
     * @param BackgroundColor 整数参数
     * @param BackgroundFill 布尔参数
     * @param value5 整数参数
     * @param value6 整数参数
     */
    public static native void report_Picture_Render(int ObjectHandle, int value, int value2, int Width, int Height, int value3, int value4, int BackgroundColor, boolean BackgroundFill, int value5, int value6);
    /**
     * 读对齐方式
     * @return 返回整数
     */
    public static native int report_PictureBox_GetAlignment();
    /**
     * 写对齐方式
     * @param value 整数参数
     */
    public static native void report_PictureBox_SetAlignment(int value);
    /**
     * 读数据字段
     * @return 返回文本
     */
    public static native String report_PictureBox_GetDataField();
    /**
     * 写数据字段
     * @param FieldName 文本参数
     */
    public static native void report_PictureBox_SetDataField(String FieldName);
    /**
     * 读图像文件
     * @return 返回文本
     */
    public static native String report_PictureBox_GetImageFile();
    /**
     * 写图像文件
     * @param GraphFile 文本参数
     */
    public static native void report_PictureBox_SetImageFile(String GraphFile);
    /**
     * 读图像索引
     * @return 返回整数
     */
    public static native int report_PictureBox_GetImage();
    /**
     * 写图像索引
     * @param Index 整数参数
     */
    public static native void report_PictureBox_SetImage(int Index);
    /**
     * 读图片
     * @return 返回整数
     */
    public static native int report_PictureBox_GetPicture();
    /**
     * 写图片
     * @param value 整数参数
     */
    public static native void report_PictureBox_SetPicture(int value);
    /**
     * 读旋转模式
     * @return 返回整数
     */
    public static native int report_PictureBox_GetRotateMode();
    /**
     * 写旋转模式
     * @param value 整数参数
     */
    public static native void report_PictureBox_SetRotateMode(int value);
    /**
     * 读缩放模式
     * @return 返回整数
     */
    public static native int report_PictureBox_GetSizeMode();
    /**
     * 写缩放模式
     * @param value 整数参数
     */
    public static native void report_PictureBox_SetSizeMode(int value);
    /**
     * 读透明模式
     * @return 返回整数
     */
    public static native int report_PictureBox_GetTransparentMode();
    /**
     * 写透明模式
     * @param value 整数参数
     */
    public static native void report_PictureBox_SetTransparentMode(int value);
    /**
     * 关联系统图片
     * @param value 整数参数
     */
    public static native void report_PictureBox_AttachSystemImage(int value);
    /**
     * 从Bmp图片载入
     * @param value 整数参数
     */
    public static native void report_PictureBox_LoadBmpPictureCOM(int value);
    /**
     * 从报表图片载入
     * @param value 整数参数
     */
    public static native void report_PictureBox_LoadBmpPicture(int value);
    /**
     * 从二进制载入
     * @param value 整数参数
     */
    public static native void report_PictureBox_LoadFromBinary(int value);
    /**
     * 从文件载入
     * @param FileName 文本参数
     */
    public static native void report_PictureBox_LoadFromFile(String FileName);
    /**
     * 从内存载入
     * @param DataPointer 整数参数
     * @param DataSize 整数参数
     */
    public static native void report_PictureBox_LoadFromMemory(int DataPointer, int DataSize);
    /**
     * 读打印机名称
     * @return 返回文本
     */
    public static native String report_Printer_GetPrinterName();
    /**
     * 写打印机名称
     * @param value 文本参数
     */
    public static native void report_Printer_SetPrinterName(String value);
    /**
     * 读纸张名称
     * @return 返回文本
     */
    public static native String report_Printer_GetPaperName();
    /**
     * 写纸张名称
     * @param value 文本参数
     */
    public static native void report_Printer_SetPaperName(String value);
    /**
     * 读纸张规格
     * @return 返回整数
     */
    public static native int report_Printer_GetPaperSize();
    /**
     * 写纸张规格
     * @param value 整数参数
     */
    public static native void report_Printer_SetPaperSize(int value);
    /**
     * 读纸张方向
     * @return 返回整数
     */
    public static native int report_Printer_GetPaperOrientation();
    /**
     * 写纸张方向
     * @param value 整数参数
     */
    public static native void report_Printer_SetPaperOrientation(int value);
    /**
     * 读纸张长度
     * @return 返回小数
     */
    public static native float report_Printer_GetPaperLength();
    /**
     * 写纸张长度
     * @param value 小数参数
     */
    public static native void report_Printer_SetPaperLength(float value);
    /**
     * 读纸张宽度
     * @return 返回小数
     */
    public static native float report_Printer_GetPaperWidth();
    /**
     * 写纸张宽度
     * @param value 小数参数
     */
    public static native void report_Printer_SetPaperWidth(float value);
    /**
     * 读左边距
     * @return 返回小数
     */
    public static native float report_Printer_GetLeftMargin();
    /**
     * 写左边距
     * @param value 小数参数
     */
    public static native void report_Printer_SetLeftMargin(float value);
    /**
     * 读右边距
     * @return 返回小数
     */
    public static native float report_Printer_GetRightMargin();
    /**
     * 写右边距
     * @param value 小数参数
     */
    public static native void report_Printer_SetRightMargin(float value);
    /**
     * 读上边距
     * @return 返回小数
     */
    public static native float report_Printer_GetTopMargin();
    /**
     * 写上边距
     * @param value 小数参数
     */
    public static native void report_Printer_SetTopMargin(float value);
    /**
     * 读下边距
     * @return 返回小数
     */
    public static native float report_Printer_GetBottomMargin();
    /**
     * 写下边距
     * @param value 小数参数
     */
    public static native void report_Printer_SetBottomMargin(float value);
    /**
     * 读联机状态
     * @return 返回布尔值
     */
    public static native boolean report_Printer_GetOnline();
    /**
     * 读支持逐份打印
     * @return 返回布尔值
     */
    public static native boolean report_Printer_GetCanCollate();
    /**
     * 读逐份打印
     * @return 返回布尔值
     */
    public static native boolean report_Printer_GetCollate();
    /**
     * 写逐份打印
     * @param value 布尔参数
     */
    public static native void report_Printer_SetCollate(boolean value);
    /**
     * 读支持自定义纸张
     * @return 返回布尔值
     */
    public static native boolean report_Printer_GetSupportCustomPaper();
    /**
     * 读支持双面打印
     * @return 返回布尔值
     */
    public static native boolean report_Printer_GetCanDuplex();
    /**
     * 读双面打印
     * @return 返回整数
     */
    public static native int report_Printer_GetDuplex();
    /**
     * 写双面打印
     * @param value 整数参数
     */
    public static native void report_Printer_SetDuplex(int value);
    /**
     * 读打印份数
     * @return 返回整数
     */
    public static native int report_Printer_GetCopies();
    /**
     * 写打印份数
     * @param value 整数参数
     */
    public static native void report_Printer_SetCopies(int value);
    /**
     * 读当前页号
     * @return 返回整数
     */
    public static native int report_Printer_GetCurPageNo();
    /**
     * 写当前页号
     * @param value 整数参数
     */
    public static native void report_Printer_SetCurPageNo(int value);
    /**
     * 读页数
     * @return 返回整数
     */
    public static native int report_Printer_GetPageCount();
    /**
     * 写页数
     * @param value 整数参数
     */
    public static native void report_Printer_SetPageCount(int value);
    /**
     * 读纸张来源
     * @return 返回整数
     */
    public static native int report_Printer_GetPaperSource();
    /**
     * 写纸张来源
     * @param value 整数参数
     */
    public static native void report_Printer_SetPaperSource(int value);
    /**
     * 读本地保存偏移设置
     * @return 返回布尔值
     */
    public static native boolean report_Printer_GetPrintOffsetSaveToLocal();
    /**
     * 写本地保存偏移设置
     * @param value 布尔参数
     */
    public static native void report_Printer_SetPrintOffsetSaveToLocal(boolean value);
    /**
     * 读打印水平偏移
     * @return 返回小数
     */
    public static native float report_Printer_GetPrintOffsetX();
    /**
     * 写打印水平偏移
     * @param value 小数参数
     */
    public static native void report_Printer_SetPrintOffsetX(float value);
    /**
     * 读打印垂直偏移
     * @return 返回小数
     */
    public static native float report_Printer_GetPrintOffsetY();
    /**
     * 写打印垂直偏移
     * @param value 小数参数
     */
    public static native void report_Printer_SetPrintOffsetY(float value);
    /**
     * 读打印页类型
     * @return 返回整数
     */
    public static native int report_Printer_GetPrintPageType();
    /**
     * 写打印页类型
     * @param value 整数参数
     */
    public static native void report_Printer_SetPrintPageType(int value);
    /**
     * 读打印范围类型
     * @return 返回整数
     */
    public static native int report_Printer_GetPrintRangeType();
    /**
     * 写打印范围类型
     * @param value 整数参数
     */
    public static native void report_Printer_SetPrintRangeType(int value);
    /**
     * 读打印页范围
     * @return 返回文本
     */
    public static native String report_Printer_GetSelectionPrintPages();
    /**
     * 写打印页范围
     * @param value 文本参数
     */
    public static native void report_Printer_SetSelectionPrintPages(String value);
    /**
     * 读打印旋转
     * @return 返回整数
     */
    public static native int report_Printer_GetPrintRotation();
    /**
     * 写打印旋转
     * @param value 整数参数
     */
    public static native void report_Printer_SetPrintRotation(int value);
    /**
     * 读每页版数
     * @return 返回整数
     */
    public static native int report_Printer_GetSheetPages();
    /**
     * 写每页版数
     * @param value 整数参数
     */
    public static native void report_Printer_SetSheetPages(int value);
    /**
     * 读按纸张规格缩放
     * @return 返回整数
     */
    public static native int report_Printer_GetSheetPaperSize();
    /**
     * 写按纸张规格缩放
     * @param value 整数参数
     */
    public static native void report_Printer_SetSheetPaperSize(int value);
    /**
     * 读设计打印机名称
     * @return 返回文本
     */
    public static native String report_Printer_GetDesignPrinterName();
    /**
     * 写设计打印机名称
     * @param value 文本参数
     */
    public static native void report_Printer_SetDesignPrinterName(String value);
    /**
     * 读设计纸张名称
     * @return 返回文本
     */
    public static native String report_Printer_GetDesignPaperName();
    /**
     * 写设计纸张名称
     * @param value 文本参数
     */
    public static native void report_Printer_SetDesignPaperName(String value);
    /**
     * 读设计纸张规格
     * @return 返回整数
     */
    public static native int report_Printer_GetDesignPaperSize();
    /**
     * 写设计纸张规格
     * @param value 整数参数
     */
    public static native void report_Printer_SetDesignPaperSize(int value);
    /**
     * 读设计纸张方向
     * @return 返回整数
     */
    public static native int report_Printer_GetDesignPaperOrientation();
    /**
     * 写设计纸张方向
     * @param value 整数参数
     */
    public static native void report_Printer_SetDesignPaperOrientation(int value);
    /**
     * 读设计纸张长度
     * @return 返回小数
     */
    public static native float report_Printer_GetDesignPaperLength();
    /**
     * 写设计纸张长度
     * @param value 小数参数
     */
    public static native void report_Printer_SetDesignPaperLength(float value);
    /**
     * 读设计纸张宽度
     * @return 返回小数
     */
    public static native float report_Printer_GetDesignPaperWidth();
    /**
     * 写设计纸张宽度
     * @param value 小数参数
     */
    public static native void report_Printer_SetDesignPaperWidth(float value);
    /**
     * 读设计左边距
     * @return 返回小数
     */
    public static native float report_Printer_GetDesignLeftMargin();
    /**
     * 写设计左边距
     * @param value 小数参数
     */
    public static native void report_Printer_SetDesignLeftMargin(float value);
    /**
     * 读设计右边距
     * @return 返回小数
     */
    public static native float report_Printer_GetDesignRightMargin();
    /**
     * 写设计右边距
     * @param value 小数参数
     */
    public static native void report_Printer_SetDesignRightMargin(float value);
    /**
     * 读设计上边距
     * @return 返回小数
     */
    public static native float report_Printer_GetDesignTopMargin();
    /**
     * 写设计上边距
     * @param value 小数参数
     */
    public static native void report_Printer_SetDesignTopMargin(float value);
    /**
     * 读设计下边距
     * @return 返回小数
     */
    public static native float report_Printer_GetDesignBottomMargin();
    /**
     * 写设计下边距
     * @param value 小数参数
     */
    public static native void report_Printer_SetDesignBottomMargin(float value);
    /**
     * 打印设置对话框
     * @return 返回布尔值
     */
    public static native boolean report_Printer_PrintDialog();
    /**
     * 打印机设置对话框
     * @return 返回布尔值
     */
    public static native boolean report_Printer_PrinterSetupDialog();
    /**
     * 页面设置对话框
     * @return 返回布尔值
     */
    public static native boolean report_Printer_PageSetupDialog();
    /**
     * 取字段集合
     * @return 返回整数
     */
    public static native int report_Recordset_GetFields();
    /**
     * 读连接串
     * @return 返回文本
     */
    public static native String report_Recordset_GetConnectionString();
    /**
     * 写连接串
     * @param value 文本参数
     */
    public static native void report_Recordset_SetConnectionString(String value);
    /**
     * 读查询SQL
     * @return 返回文本
     */
    public static native String report_Recordset_GetQuerySQL();
    /**
     * 写查询SQL
     * @param value 文本参数
     */
    public static native void report_Recordset_SetQuerySQL(String value);
    /**
     * 读排序字段
     * @return 返回文本
     */
    public static native String report_Recordset_GetSortFields();
    /**
     * 写排序字段
     * @param value 文本参数
     */
    public static native void report_Recordset_SetSortFields(String value);
    /**
     * 读Xml表名
     * @return 返回文本
     */
    public static native String report_Recordset_GetXmlTableName();
    /**
     * 写Xml表名
     * @param value 文本参数
     */
    public static native void report_Recordset_SetXmlTableName(String value);
    /**
     * 按名称取字段
     * @param value 文本参数
     * @return 返回整数
     */
    public static native int report_Recordset_FieldByName(String value);
    /**
     * 按源名称取字段
     * @param value 文本参数
     * @return 返回整数
     */
    public static native int report_Recordset_FieldByDBName(String value);
    /**
     * 读提交记录前脚本
     * @return 返回文本
     */
    public static native String report_Recordset_GetBeforePostRecordScript();
    /**
     * 写提交记录前脚本
     * @param value 文本参数
     */
    public static native void report_Recordset_SetBeforePostRecordScript(String value);
    /**
     * 读取记录脚本
     * @return 返回文本
     */
    public static native String report_Recordset_GetFetchRecordScript();
    /**
     * 写取记录脚本
     * @param value 文本参数
     */
    public static native void report_Recordset_SetFetchRecordScript(String value);
    /**
     * 读处理记录脚本
     * @return 返回文本
     */
    public static native String report_Recordset_GetProcessRecordScript();
    /**
     * 写处理记录脚本
     * @param value 文本参数
     */
    public static native void report_Recordset_SetProcessRecordScript(String value);
    /**
     * 读页处理记录脚本
     * @return 返回文本
     */
    public static native String report_Recordset_GetPageProcessRecordScript();
    /**
     * 写页处理记录脚本
     * @param value 文本参数
     */
    public static native void report_Recordset_SetPageProcessRecordScript(String value);
    /**
     * 读可编辑
     * @return 返回布尔值
     */
    public static native boolean report_Recordset_GetEditable();
    /**
     * 写可编辑
     * @param value 布尔参数
     */
    public static native void report_Recordset_SetEditable(boolean value);
    /**
     * 读忽略数据查询
     * @return 返回布尔值
     */
    public static native boolean report_Recordset_GetSkipQuery();
    /**
     * 写忽略数据查询
     * @param value 布尔参数
     */
    public static native void report_Recordset_SetSkipQuery(boolean value);
    /**
     * 读排序按升序
     * @return 返回布尔值
     */
    public static native boolean report_Recordset_GetSortAsc();
    /**
     * 写排序按升序
     * @param value 布尔参数
     */
    public static native void report_Recordset_SetSortAsc(boolean value);
    /**
     * 读排序大小写敏感
     * @return 返回布尔值
     */
    public static native boolean report_Recordset_GetSortCaseSensitive();
    /**
     * 写排序大小写敏感
     * @param value 布尔参数
     */
    public static native void report_Recordset_SetSortCaseSensitive(boolean value);
    /**
     * 读记录数
     * @return 返回整数
     */
    public static native int report_Recordset_GetRecordCount();
    /**
     * 读记录号
     * @return 返回整数
     */
    public static native int report_Recordset_GetRecordNo();
    /**
     * 读锁定
     * @return 返回整数
     */
    public static native int report_Recordset_GetLock();
    /**
     * 写锁定
     * @param value 整数参数
     */
    public static native void report_Recordset_SetLock(int value);
    /**
     * 增加字段
     * @param FieldName 文本参数
     * @param value 整数参数
     * @return 返回整数
     */
    public static native int report_Recordset_AddField(String FieldName, int value);
    /**
     * 追加
     */
    public static native void report_Recordset_Append();
    /**
     * 提交
     */
    public static native void report_Recordset_Post();
    /**
     * 编辑
     */
    public static native void report_Recordset_Edit();
    /**
     * 取消
     */
    public static native void report_Recordset_Cancel();
    /**
     * 清空
     */
    public static native void report_Recordset_Empty();
    /**
     * 清空字段
     */
    public static native void report_Recordset_RemoveAllFields();
    /**
     * 重新排序
     * @param SortField 文本参数
     * @param TrueFalseSort 布尔参数
     * @param value 布尔参数
     * @return 返回布尔值
     */
    public static native boolean report_Recordset_Resort(String SortField, boolean TrueFalseSort, boolean value);
    /**
     * 第一条
     */
    public static native void report_Recordset_First();
    /**
     * 上一条
     */
    public static native void report_Recordset_Prior();
    /**
     * 下一条
     */
    public static native void report_Recordset_Next();
    /**
     * 最后一条
     */
    public static native void report_Recordset_Last();
    /**
     * 相对移动
     * @param Move 整数参数
     */
    public static native void report_Recordset_MoveBy(int Move);
    /**
     * 记录头
     * @return 返回布尔值
     */
    public static native boolean report_Recordset_Bof();
    /**
     * 记录尾
     * @return 返回布尔值
     */
    public static native boolean report_Recordset_Eof();
    /**
     * 移动到
     * @param Record 整数参数
     */
    public static native void report_Recordset_MoveTo(int Record);
    /**
     * 从文件载入数据
     * @param FileName 文本参数
     * @return 返回布尔值
     */
    public static native boolean report_Recordset_LoadData(String FileName);
    /**
     * 从XML载入数据
     * @param XMLTextData 文本参数
     * @return 返回布尔值
     */
    public static native boolean report_Recordset_LoadDataFromXML(String XMLTextData);
    /**
     * 保存数据到XML
     * @return 返回文本
     */
    public static native String report_Recordset_SaveDataToXML();
    /**
     * 复制记录
     */
    public static native void report_Recordset_Duplicate();
    /**
     * 筛选间隔
     * @param Interval 整数参数
     * @param value 布尔参数
     */
    public static native void report_Recordset_FilterByStep(int Interval, boolean value);
    /**
     * 筛选记录数
     * @param MaxCount 整数参数
     */
    public static native void report_Recordset_FilterByCount(int MaxCount);
    /**
     * 筛选开始
     */
    public static native void report_Recordset_FilterBegin();
    /**
     * 筛选结束
     */
    public static native void report_Recordset_FilterEnd();
    /**
     * 筛选选中
     */
    public static native void report_Recordset_FilterSelect();
    /**
     * 筛选取消
     */
    public static native void report_Recordset_FilterReset();
    /**
     * 追加空白行排除_取
     * @return 返回布尔值
     */
    public static native boolean report_ReportFooter_GetAppendBlankRowExclude();
    /**
     * 追加空白行排除_设
     * @param value 布尔参数
     */
    public static native void report_ReportFooter_SetAppendBlankRowExclude(boolean value);
    /**
     * 打印在页底_取
     * @return 返回布尔值
     */
    public static native boolean report_ReportFooter_GetPrintAtBottom();
    /**
     * 打印在页底_设
     * @param value 布尔参数
     */
    public static native void report_ReportFooter_SetPrintAtBottom(boolean value);
    /**
     * 读数目
     * @return 返回整数
     */
    public static native int report_ReportFooters_GetCount();
    /**
     * 获取项目_整数
     * @param value 整数参数
     * @return 返回整数
     */
    public static native int report_ReportFooters_GetItemInt(int value);
    /**
     * 获取项目_文本型
     * @param value 整数参数
     * @return 返回整数
     */
    public static native int report_ReportFooters_GetItemString(int value);
    /**
     * 按名称获取项目
     * @param Name 文本参数
     * @return 返回整数
     */
    public static native int report_ReportFooters_GetItemByName(String Name);
    /**
     * 按序号获取项目
     * @param Index 整数参数
     * @return 返回整数
     */
    public static native int report_ReportFooters_GetItemBy(int Index);
    /**
     * 增加
     * @return 返回整数
     */
    public static native int report_ReportFooters_Add();
    /**
     * 改变项目顺序_整数
     * @param value 整数参数
     * @param Sequence 整数参数
     */
    public static native void report_ReportFooters_ChangeItemOrderInt(int value, int Sequence);
    /**
     * 改变项目顺序_文本型
     * @param value 文本参数
     * @param Sequence 整数参数
     */
    public static native void report_ReportFooters_ChangeItemOrderString(String value, int Sequence);
    /**
     * 改变项目顺序_名称
     * @param Name 文本参数
     * @param Sequence 整数参数
     */
    public static native void report_ReportFooters_ChangeItemOrderByName(String Name, int Sequence);
    /**
     * 改变项目顺序_序号
     * @param Index 整数参数
     * @param Sequence 整数参数
     */
    public static native void report_ReportFooters_ChangeItemOrderBy(int Index, int Sequence);
    /**
     * 按名称取序号
     * @param Name 文本参数
     * @return 返回整数
     */
    public static native int report_ReportFooters_ByName(String Name);
    /**
     * 获取指定项目
     * @param Index 整数参数
     * @return 返回整数
     */
    public static native int report_ReportFooters_ItemAt(int Index);
    /**
     * 删除项目_文本型
     * @param value 文本参数
     */
    public static native void report_ReportFooters_RemoveString(String value);
    /**
     * 删除项目_整数
     * @param value 整数参数
     */
    public static native void report_ReportFooters_RemoveInt(int value);
    /**
     * 按名称删除项目
     * @param Name 文本参数
     */
    public static native void report_ReportFooters_RemoveByName(String Name);
    /**
     * 按序号删除项目
     * @param Index 整数参数
     */
    public static native void report_ReportFooters_RemoveBy(int Index);
    /**
     * 全部删除
     */
    public static native void report_ReportFooters_RemoveAll();
    /**
     * 读数目
     * @return 返回整数
     */
    public static native int report_ReportHeaders_GetCount();
    /**
     * 获取项目_整数
     * @param value 整数参数
     * @return 返回整数
     */
    public static native int report_ReportHeaders_GetItemInt(int value);
    /**
     * 获取项目_文本型
     * @param value 整数参数
     * @return 返回整数
     */
    public static native int report_ReportHeaders_GetItemString(int value);
    /**
     * 按名称获取项目
     * @param Name 文本参数
     * @return 返回整数
     */
    public static native int report_ReportHeaders_GetItemByName(String Name);
    /**
     * 按序号获取项目
     * @param Index 整数参数
     * @return 返回整数
     */
    public static native int report_ReportHeaders_GetItemBy(int Index);
    /**
     * 增加
     * @return 返回整数
     */
    public static native int report_ReportHeaders_Add();
    /**
     * 改变项目顺序_整数
     * @param value 整数参数
     * @param Sequence 整数参数
     */
    public static native void report_ReportHeaders_ChangeItemOrderInt(int value, int Sequence);
    /**
     * 改变项目顺序_文本型
     * @param value 文本参数
     * @param Sequence 整数参数
     */
    public static native void report_ReportHeaders_ChangeItemOrderString(String value, int Sequence);
    /**
     * 改变项目顺序_名称
     * @param Name 文本参数
     * @param Sequence 整数参数
     */
    public static native void report_ReportHeaders_ChangeItemOrderByName(String Name, int Sequence);
    /**
     * 改变项目顺序_序号
     * @param Index 整数参数
     * @param Sequence 整数参数
     */
    public static native void report_ReportHeaders_ChangeItemOrderBy(int Index, int Sequence);
    /**
     * 按名称取序号
     * @param Name 文本参数
     * @return 返回整数
     */
    public static native int report_ReportHeaders_ByName(String Name);
    /**
     * 获取指定项目
     * @param Index 整数参数
     * @return 返回整数
     */
    public static native int report_ReportHeaders_ItemAt(int Index);
    /**
     * 删除项目_文本型
     * @param value 文本参数
     */
    public static native void report_ReportHeaders_RemoveString(String value);
    /**
     * 删除项目_整数
     * @param value 整数参数
     */
    public static native void report_ReportHeaders_RemoveInt(int value);
    /**
     * 按名称删除项目
     * @param Name 文本参数
     */
    public static native void report_ReportHeaders_RemoveByName(String Name);
    /**
     * 按序号删除项目
     * @param Index 整数参数
     */
    public static native void report_ReportHeaders_RemoveBy(int Index);
    /**
     * 全部删除
     */
    public static native void report_ReportHeaders_RemoveAll();
    /**
     * 读跟随明细网格居中
     * @return 返回布尔值
     */
    public static native boolean report_ReportSection_GetCenterWithDetailGrid();
    /**
     * 写跟随明细网格居中
     * @param value 布尔参数
     */
    public static native void report_ReportSection_SetCenterWithDetailGrid(boolean value);
    /**
     * 读每页重复打印
     * @return 返回布尔值
     */
    public static native boolean report_ReportSection_GetRepeatOnPage();
    /**
     * 写每页重复打印
     * @param value 布尔参数
     */
    public static native void report_ReportSection_SetRepeatOnPage(boolean value);
    /**
     * 读换新页
     * @return 返回整数
     */
    public static native int report_ReportSection_GetNewPage();
    /**
     * 写换新页
     * @param value 整数参数
     */
    public static native void report_ReportSection_SetNewPage(int value);
    /**
     * 读数据字段
     * @return 返回文本
     */
    public static native String report_RichTextBox_GetDataField();
    /**
     * 写数据字段
     * @param FieldName 文本参数
     */
    public static native void report_RichTextBox_SetDataField(String FieldName);
    /**
     * 读可伸展
     * @return 返回布尔值
     */
    public static native boolean report_RichTextBox_GetCanGrow();
    /**
     * 写可伸展
     * @param value 布尔参数
     */
    public static native void report_RichTextBox_SetCanGrow(boolean value);
    /**
     * 读可收缩
     * @return 返回布尔值
     */
    public static native boolean report_RichTextBox_GetCanShrink();
    /**
     * 写可收缩
     * @param value 布尔参数
     */
    public static native void report_RichTextBox_SetCanShrink(boolean value);
    /**
     * 读RTF文本
     * @return 返回文本
     */
    public static native String report_RichTextBox_GetRTF();
    /**
     * 写RTF文本
     * @param RTFTextData 文本参数
     */
    public static native void report_RichTextBox_SetRTF(String RTFTextData);
    /**
     * 从文件载入
     * @param FileName 文本参数
     */
    public static native void report_RichTextBox_LoadFromFile(String FileName);
    /**
     * 从内存载入
     * @param DataPointer 整数参数
     * @param DataSize 整数参数
     */
    public static native void report_RichTextBox_LoadFromMemory(int DataPointer, int DataSize);
    /**
     * 读获取父对象
     * @return 返回整数
     */
    public static native int report_Section_GetParent();
    /**
     * 读获取字体
     * @return 返回整数
     */
    public static native int report_Section_GetFont();
    /**
     * 读获取部件框集合
     * @return 返回整数
     */
    public static native int report_Section_GetControls();
    /**
     * 读类型
     * @return 返回整数
     */
    public static native int report_Section_GetSectionType();
    /**
     * 读名称
     * @return 返回文本
     */
    public static native String report_Section_GetName();
    /**
     * 写名称
     * @param Name 文本参数
     */
    public static native void report_Section_SetName(String Name);
    /**
     * 读背景色
     * @return 返回整数
     */
    public static native int report_Section_GetBackColor();
    /**
     * 写背景色
     * @param value 整数参数
     */
    public static native void report_Section_SetBackColor(int value);
    /**
     * 读书签文本
     * @return 返回文本
     */
    public static native String report_Section_GetBookmarkText();
    /**
     * 写书签文本
     * @param Text 文本参数
     */
    public static native void report_Section_SetBookmarkText(String Text);
    /**
     * 读格式化脚本
     * @return 返回文本
     */
    public static native String report_Section_GetFormatScript();
    /**
     * 写格式化脚本
     * @param ScriptText 文本参数
     */
    public static native void report_Section_SetFormatScript(String ScriptText);
    /**
     * 读可伸展
     * @return 返回布尔值
     */
    public static native boolean report_Section_GetCanGrow();
    /**
     * 写可伸展
     * @param value 布尔参数
     */
    public static native void report_Section_SetCanGrow(boolean value);
    /**
     * 读可收缩
     * @return 返回布尔值
     */
    public static native boolean report_Section_GetCanShrink();
    /**
     * 写可收缩
     * @param value 布尔参数
     */
    public static native void report_Section_SetCanShrink(boolean value);
    /**
     * 读高度
     * @return 返回小数
     */
    public static native float report_Section_GetHeight();
    /**
     * 写高度
     * @param value 小数参数
     */
    public static native void report_Section_SetHeight(float value);
    /**
     * 读保持同页
     * @return 返回布尔值
     */
    public static native boolean report_Section_GetKeepTogether();
    /**
     * 写保持同页
     * @param value 布尔参数
     */
    public static native void report_Section_SetKeepTogether(boolean value);
    /**
     * 读可见性
     * @return 返回布尔值
     */
    public static native boolean report_Section_GetVisible();
    /**
     * 写可见性
     * @param value 布尔参数
     */
    public static native void report_Section_SetVisible(boolean value);
    /**
     * 读锁定
     * @return 返回整数
     */
    public static native int report_Section_GetLock();
    /**
     * 写锁定
     * @param value 整数参数
     */
    public static native void report_Section_SetLock(int value);
    /**
     * 读圆角椭圆宽度
     * @return 返回整数
     */
    public static native int report_ShapeBox_GetCornerDx();
    /**
     * 写圆角椭圆宽度
     * @param value 整数参数
     */
    public static native void report_ShapeBox_SetCornerDx(int value);
    /**
     * 读圆角椭圆高度
     * @return 返回整数
     */
    public static native int report_ShapeBox_GetCornerDy();
    /**
     * 写圆角椭圆高度
     * @param value 整数参数
     */
    public static native void report_ShapeBox_SetCornerDy(int value);
    /**
     * 读填充颜色
     * @return 返回整数
     */
    public static native int report_ShapeBox_GetFillColor();
    /**
     * 写填充颜色
     * @param value 整数参数
     */
    public static native void report_ShapeBox_SetFillColor(int value);
    /**
     * 读填充方式
     * @return 返回整数
     */
    public static native int report_ShapeBox_GetFillStyle();
    /**
     * 写填充方式
     * @param value 整数参数
     */
    public static native void report_ShapeBox_SetFillStyle(int value);
    /**
     * 读获取线画笔
     * @return 返回整数
     */
    public static native int report_ShapeBox_GetLinePen();
    /**
     * 读图形类型
     * @return 返回整数
     */
    public static native int report_ShapeBox_GetShapeType();
    /**
     * 写图形类型
     * @param value 整数参数
     */
    public static native void report_ShapeBox_SetShapeType(int value);
    /**
     * 读参数
     * @return 返回文本
     */
    public static native String report_StaticBox_GetParameter();
    /**
     * 写参数
     * @param Name 文本参数
     */
    public static native void report_StaticBox_SetParameter(String Name);
    /**
     * 读文本
     * @return 返回文本
     */
    public static native String report_StaticBox_GetText();
    /**
     * 写文本
     * @param Text 文本参数
     */
    public static native void report_StaticBox_SetText(String Text);
    /**
     * 读关联报表
     * @return 返回整数
     */
    public static native int report_SubReport_GetReport();
    /**
     * 写关联报表
     * @param value 整数参数
     */
    public static native void report_SubReport_SetReport(int value);
    /**
     * 读报表模板文件
     * @return 返回文本
     */
    public static native String report_SubReport_GetReportFile();
    /**
     * 写报表模板文件
     * @param FileName 文本参数
     */
    public static native void report_SubReport_SetReportFile(String FileName);
    /**
     * 读主从关系字段
     * @return 返回文本
     */
    public static native String report_SubReport_GetRelationFields();
    /**
     * 写主从关系字段
     * @param CloseField 文本参数
     */
    public static native void report_SubReport_SetRelationFields(String CloseField);
    /**
     * 读可伸展
     * @return 返回布尔值
     */
    public static native boolean report_SubReport_GetCanGrow();
    /**
     * 写可伸展
     * @param value 布尔参数
     */
    public static native void report_SubReport_SetCanGrow(boolean value);
    /**
     * 读可收缩
     * @return 返回布尔值
     */
    public static native boolean report_SubReport_GetCanShrink();
    /**
     * 写可收缩
     * @param value 布尔参数
     */
    public static native void report_SubReport_SetCanShrink(boolean value);
    /**
     * 读无数据隐藏
     * @return 返回布尔值
     */
    public static native boolean report_SubReport_GetHideOnRecordsetEmpty();
    /**
     * 写无数据隐藏
     * @param value 布尔参数
     */
    public static native void report_SubReport_SetHideOnRecordsetEmpty(boolean value);
    /**
     * 读主页面设置
     * @return 返回布尔值
     */
    public static native boolean report_SubReport_GetParentPageSettings();
    /**
     * 写主页面设置
     * @param value 布尔参数
     */
    public static native void report_SubReport_SetParentPageSettings(boolean value);
    /**
     * 读重置页号
     * @return 返回布尔值
     */
    public static native boolean report_SubReport_GetResetPageNumber();
    /**
     * 写重置页号
     * @param value 布尔参数
     */
    public static native void report_SubReport_SetResetPageNumber(boolean value);
    /**
     * 读导出到新工作表
     * @return 返回布尔值
     */
    public static native boolean report_SubReport_GetToNewExcelSheet();
    /**
     * 写导出到新工作表
     * @param value 布尔参数
     */
    public static native void report_SubReport_SetToNewExcelSheet(boolean value);
    /**
     * 读数据字段
     * @return 返回文本
     */
    public static native String report_SummaryBox_GetDataField();
    /**
     * 写数据字段
     * @param FieldName 文本参数
     */
    public static native void report_SummaryBox_SetDataField(String FieldName);
    /**
     * 读显示字段
     * @return 返回文本
     */
    public static native String report_SummaryBox_GetDisplayField();
    /**
     * 写显示字段
     * @param Text 文本参数
     */
    public static native void report_SummaryBox_SetDisplayField(String Text);
    /**
     * 读格式
     * @return 返回文本
     */
    public static native String report_SummaryBox_GetFormat();
    /**
     * 写格式
     * @param FormatText 文本参数
     */
    public static native void report_SummaryBox_SetFormat(String FormatText);
    /**
     * 读第几名
     * @return 返回整数
     */
    public static native int report_SummaryBox_GetRankNo();
    /**
     * 写第几名
     * @param value 整数参数
     */
    public static native void report_SummaryBox_SetRankNo(int value);
    /**
     * 读统计函数
     * @return 返回整数
     */
    public static native int report_SummaryBox_GetSummaryFun();
    /**
     * 写统计函数
     * @param value 整数参数
     */
    public static native void report_SummaryBox_SetSummaryFun(int value);
    /**
     * 读值
     * @return 返回小数
     */
    public static native float report_SummaryBox_GetValue();
    /**
     * 写值
     * @param value 小数参数
     */
    public static native void report_SummaryBox_SetValue(float value);
    /**
     * 读格式
     * @return 返回文本
     */
    public static native String report_SystemVarBox_GetFormat();
    /**
     * 写格式
     * @param FormatText 文本参数
     */
    public static native void report_SystemVarBox_SetFormat(String FormatText);
    /**
     * 读分组序号
     * @return 返回整数
     */
    public static native int report_SystemVarBox_GetGroup();
    /**
     * 写分组序号
     * @param value 整数参数
     */
    public static native void report_SystemVarBox_SetGroup(int value);
    /**
     * 读系统变量
     * @return 返回整数
     */
    public static native int report_SystemVarBox_GetSystemVar();
    /**
     * 写系统变量
     * @param value 整数参数
     */
    public static native void report_SystemVarBox_SetSystemVar(int value);
    /**
     * 读获取文本格式
     * @return 返回整数
     */
    public static native int report_TextBox_GetTextFormat();
    /**
     * 读显示金额标签
     * @return 返回布尔值
     */
    public static native boolean report_TextBox_GetShowMoneyLabel();
    /**
     * 写显示金额标签
     * @param value 布尔参数
     */
    public static native void report_TextBox_SetShowMoneyLabel(boolean value);
    /**
     * 读显示金额线
     * @return 返回布尔值
     */
    public static native boolean report_TextBox_GetShowMoneyLine();
    /**
     * 写显示金额线
     * @param value 布尔参数
     */
    public static native void report_TextBox_SetShowMoneyLine(boolean value);
    /**
     * 读显示文字
     * @return 返回文本
     */
    public static native String report_TextBox_GetDisplayText();
    /**
     * 写显示文字
     * @param DisplayText 文本参数
     */
    public static native void report_TextBox_SetDisplayText(String DisplayText);
    /**
     * 读可伸展
     * @return 返回布尔值
     */
    public static native boolean report_TextBox_GetCanGrow();
    /**
     * 写可伸展
     * @param value 布尔参数
     */
    public static native void report_TextBox_SetCanGrow(boolean value);
    /**
     * 读可收缩
     * @return 返回布尔值
     */
    public static native boolean report_TextBox_GetCanShrink();
    /**
     * 写可收缩
     * @param value 布尔参数
     */
    public static native void report_TextBox_SetCanShrink(boolean value);
    /**
     * 读显示文字脚本
     * @return 返回文本
     */
    public static native String report_TextBox_GetDisplayTextScript();
    /**
     * 写显示文字脚本
     * @param ScriptText 文本参数
     */
    public static native void report_TextBox_SetDisplayTextScript(String ScriptText);
    /**
     * 读按需缩小字体
     * @return 返回布尔值
     */
    public static native boolean report_TextBox_GetShrinkFontToFit();
    /**
     * 写按需缩小字体
     * @param value 布尔参数
     */
    public static native void report_TextBox_SetShrinkFontToFit(boolean value);
    /**
     * 读超长文字省略号
     * @return 返回布尔值
     */
    public static native boolean report_TextFormat_GetEndEllipsis();
    /**
     * 写超长文字省略号
     * @param value 布尔参数
     */
    public static native void report_TextFormat_SetEndEllipsis(boolean value);
    /**
     * 读支持HTML标签
     * @return 返回布尔值
     */
    public static native boolean report_TextFormat_GetHtmlTags();
    /**
     * 写支持HTML标签
     * @param value 布尔参数
     */
    public static native void report_TextFormat_SetHtmlTags(boolean value);
    /**
     * 读文字对齐方式
     * @return 返回整数
     */
    public static native int report_TextFormat_GetTextAlign();
    /**
     * 写文字对齐方式
     * @param value 整数参数
     */
    public static native void report_TextFormat_SetTextAlign(int value);
    /**
     * 读文字自动绕行
     * @return 返回布尔值
     */
    public static native boolean report_TextFormat_GetWordWrap();
    /**
     * 写文字自动绕行
     * @param value 布尔参数
     */
    public static native void report_TextFormat_SetWordWrap(boolean value);
    /**
     * 读字间距
     * @return 返回小数
     */
    public static native float report_TextFormat_GetCharacterSpacing();
    /**
     * 写字间距
     * @param value 小数参数
     */
    public static native void report_TextFormat_SetCharacterSpacing(float value);
    /**
     * 读行间距
     * @return 返回小数
     */
    public static native float report_TextFormat_GetLineSpacing();
    /**
     * 写行间距
     * @param value 小数参数
     */
    public static native void report_TextFormat_SetLineSpacing(float value);
    /**
     * 读首字缩进
     * @return 返回小数
     */
    public static native float report_TextFormat_GetFirstCharIndent();
    /**
     * 写首字缩进
     * @param value 小数参数
     */
    public static native void report_TextFormat_SetFirstCharIndent(float value);
    /**
     * 读文字宽度比例
     * @return 返回小数
     */
    public static native float report_TextFormat_GetFontWidthRatio();
    /**
     * 写文字宽度比例
     * @param value 小数参数
     */
    public static native void report_TextFormat_SetFontWidthRatio(float value);
    /**
     * 读段间距
     * @return 返回小数
     */
    public static native float report_TextFormat_GetParagraphSpacing();
    /**
     * 写段间距
     * @param value 小数参数
     */
    public static native void report_TextFormat_SetParagraphSpacing(float value);
    /**
     * 读文字角度
     * @return 返回小数
     */
    public static native float report_TextFormat_GetTextAngle();
    /**
     * 写文字角度
     * @param value 小数参数
     */
    public static native void report_TextFormat_SetTextAngle(float value);
    /**
     * 读文字方向
     * @return 返回整数
     */
    public static native int report_TextFormat_GetTextOrientation();
    /**
     * 写文字方向
     * @param value 整数参数
     */
    public static native void report_TextFormat_SetTextOrientation(int value);
    /**
     * 克隆
     * @return 返回整数
     */
    public static native int report_TextFormat_Clone();
    /**
     * 读禁止常规提示
     * @return 返回布尔值
     */
    public static native boolean report_Utility_GetDisableNormalMsg();
    /**
     * 写禁止常规提示
     * @param value 布尔参数
     */
    public static native void report_Utility_SetDisableNormalMsg(boolean value);
    /**
     * 读禁止异常提示
     * @return 返回布尔值
     */
    public static native boolean report_Utility_GetDisableExceptionMsg();
    /**
     * 写禁止异常提示
     * @param value 布尔参数
     */
    public static native void report_Utility_SetDisableExceptionMsg(boolean value);
    /**
     * 读禁止提示HTTP通讯失败
     * @return 返回布尔值
     */
    public static native boolean report_Utility_GetDisableHttpFailedMsg();
    /**
     * 写禁止提示HTTP通讯失败
     * @param value 布尔参数
     */
    public static native void report_Utility_SetDisableHttpFailedMsg(boolean value);
    /**
     * 读依据文本自动折行
     * @return 返回布尔值
     */
    public static native boolean report_Utility_GetTextWrapByWord();
    /**
     * 写依据文本自动折行
     * @param value 布尔参数
     */
    public static native void report_Utility_SetTextWrapByWord(boolean value);
    /**
     * 读文本折行两端分散对齐
     * @return 返回布尔值
     */
    public static native boolean report_Utility_GetTextWrapToJustify();
    /**
     * 写文本折行两端分散对齐
     * @param value 布尔参数
     */
    public static native void report_Utility_SetTextWrapToJustify(boolean value);
    /**
     * 读文本左边标点符号
     * @return 返回文本
     */
    public static native String report_Utility_GetTextLeftPunctuations();
    /**
     * 写文本左边标点符号
     * @param value 文本参数
     */
    public static native void report_Utility_SetTextLeftPunctuations(String value);
    /**
     * 读文本右边标点符号
     * @return 返回文本
     */
    public static native String report_Utility_GetTextRightPunctuations();
    /**
     * 写文本右边标点符号
     * @param value 文本参数
     */
    public static native void report_Utility_SetTextRightPunctuations(String value);
    /**
     * 读提示文本显示速度
     * @return 返回整数
     */
    public static native int report_Utility_GetTooltipHoverTime();
    /**
     * 写提示文本显示速度
     * @param value 整数参数
     */
    public static native void report_Utility_SetTooltipHoverTime(int value);
    /**
     * 读字体最小缩放比
     * @return 返回小数
     */
    public static native float report_Utility_GetShrinkFontMinWidthRatio();
    /**
     * 写字体最小缩放比
     * @param value 小数参数
     */
    public static native void report_Utility_SetShrinkFontMinWidthRatio(float value);
    /**
     * 取格式文本宽度
     * @param textString 文本参数
     * @param textFormatObject 整数参数
     * @param reportFontObject 整数参数
     * @return 返回整数
     */
    public static native int report_Utility_FormatTextWidth(String textString, int textFormatObject, int reportFontObject);
    /**
     * 取格式文本高度
     * @param textString 文本参数
     * @param width 整数参数
     * @param textFormatObject 整数参数
     * @param reportFontObject 整数参数
     * @return 返回整数
     */
    public static native int report_Utility_FormatTextHeight(String textString, int width, int textFormatObject, int reportFontObject);
    /**
     * 取格式文本输出个数
     * @param textString 文本参数
     * @param width 整数参数
     * @param height 整数参数
     * @param textFormatObject 整数参数
     * @param reportFontObject 整数参数
     * @return 返回整数
     */
    public static native int report_Utility_FormatTextOutLen(String textString, int width, int height, int textFormatObject, int reportFontObject);
    /**
     * 取RGB色值
     * @param red 整数参数
     * @param green 整数参数
     * @param blue 整数参数
     * @return 返回整数
     */
    public static native int report_Utility_ColorFromRGB(int red, int green, int blue);
    /**
     * 创建数据集
     * @return 返回整数
     */
    public static native int report_Utility_CreateRecordset();
    /**
     * 创建二进制数据
     * @return 返回整数
     */
    public static native int report_Utility_CreateBinaryObject();
    /**
     * 创建日期时间
     * @return 返回整数
     */
    public static native int report_Utility_CreateDateTime();
    /**
     * 创建图片
     * @return 返回整数
     */
    public static native int report_Utility_CreatePicture();
    /**
     * 创建文本格式
     * @return 返回整数
     */
    public static native int report_Utility_CreateTextFormat();
    /**
     * 禁止调试
     */
    public static native void report_Utility_DisableDebug();
    /**
     * 取打印机数量
     * @return 返回整数
     */
    public static native int report_Utility_GetPrinterCount();
    /**
     * 取打印机名称
     * @param index 整数参数
     * @return 返回文本
     */
    public static native String report_Utility_GetPrinterName(int index);
    /**
     * 取打印机纸张数量
     * @param printerName 整数参数
     * @return 返回整数
     */
    public static native int report_Utility_GetPrinterPaperCount(int printerName);
    /**
     * 取打印机纸张名称
     * @param paperIndex 整数参数
     * @return 返回文本
     */
    public static native String report_Utility_GetPrinterPaperName(int paperIndex);
    /**
     * "//GZ压缩"
     * @param value 整数参数
     */
    public static native void report_Utility_GZCompress(int value);
    /**
     * "//GZ解压缩"
     * @param value 字节数组参数
     * @param dwSize 整数参数
     */
    public static native void report_Utility_GZUncompress(byte[] value, int dwSize);
    /**
     * 数值格式化
     * @param value 小数参数
     * @param formatString 文本参数
     * @return 返回文本
     */
    public static native String report_Utility_NumberFormat(float value, String formatString);
    /**
     * 数字格式化为汉字大写
     * @param value 小数参数
     * @param decimalPlaces 整数参数
     * @return 返回文本
     */
    public static native String report_Utility_NumberFormatToBigHZ(float value, int decimalPlaces);
    /**
     * 数字格式化为汉字大写金额
     * @param value 小数参数
     * @return 返回文本
     */
    public static native String report_Utility_NumberFormatToBigHZAmount(float value);
    /**
     * 数值四舍五入
     * @param value 小数参数
     * @param decimalPlaces 整数参数
     * @return 返回小数
     */
    public static native float report_Utility_NumberRound45(float value, int decimalPlaces);
    /**
     * 数值四舍六入五留双
     * @param value 小数参数
     * @param decimalPlaces 整数参数
     * @return 返回小数
     */
    public static native float report_Utility_NumberRound465(float value, int decimalPlaces);
    /**
     * 设置金额标签文字
     * @param labelText 整数参数
     */
    public static native void report_Utility_SetMoneyLabelText(int labelText);
    /**
     * 设置皮肤
     * @param red 整数参数
     * @param green 整数参数
     * @param blue 整数参数
     * @param styleType 整数参数
     */
    public static native void report_Utility_SetSkin(int red, int green, int blue, int styleType);
    /**
     * 显示文件对话框
     * @return 返回文本
     */
    public static native String report_Utility_ShowFolderDialog();
    /**
     * 显示打开文件对话框
     * @param fileType 文本参数
     * @param extension 文本参数
     * @param fileName 文本参数
     * @return 返回文本
     */
    public static native String report_Utility_ShowOpenFileDialog(String fileType, String extension, String fileName);
    /**
     * 显示保存文件对话框
     * @param fileType 文本参数
     * @param extension 文本参数
     * @param fileName 文本参数
     * @return 返回文本
     */
    public static native String report_Utility_ShowSaveFileDialog(String fileType, String extension, String fileName);
    /**
     * 提示框
     * @param messageText 整数参数
     */
    public static native void report_Utility_Alert(int messageText);
    /**
     * 取巴塞尔曲线控制点
     * @param frontPointX 小数参数
     * @param frontPointY 小数参数
     * @param middlePointX 小数参数
     * @param middlePointY 小数参数
     * @param backPointX 小数参数
     * @param backPointY 小数参数
     */
    public static native void report_Utility_CalcCurveControlPoints(float frontPointX, float frontPointY, float middlePointX, float middlePointY, float backPointX, float backPointY);
    /**
     * 读取内侧控制点X
     * @return 返回小数
     */
    public static native float report_Utility_GetCCPInnerX();
    /**
     * 读取内侧控制点Y
     * @return 返回小数
     */
    public static native float report_Utility_GetCCPInnerY();
    /**
     * 读取外侧控制点X
     * @return 返回小数
     */
    public static native float report_Utility_GetCCPOuterX();
    /**
     * 读取外侧控制点Y
     * @return 返回小数
     */
    public static native float report_Utility_GetCCPOuterY();
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_Table_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_Table_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_Table_ThisInit();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_Table_Assign(int ID);
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_Table_IsEmpty();
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_ExportOption_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_ExportOption_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_ExportOption_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_ExportOption_IsEmpty();
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_E2CellOption_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_E2CellOption_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_E2CellOption_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_E2CellOption_IsEmpty();
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_E2XLSOption_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_E2XLSOption_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_E2XLSOption_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_E2XLSOption_IsEmpty();
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_E2TXTOption_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_E2TXTOption_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_E2TXTOption_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_E2TXTOption_IsEmpty();
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_E2HTMOption_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_E2HTMOption_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_E2HTMOption_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_E2HTMOption_IsEmpty();
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_E2CSVOption_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_E2CSVOption_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_E2CSVOption_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_E2CSVOption_IsEmpty();
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_E2RTFOption_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_E2RTFOption_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_E2RTFOption_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_E2RTFOption_IsEmpty();
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_E2PDFOption_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_E2PDFOption_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_E2PDFOption_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_E2PDFOption_IsEmpty();
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_E2IMGOption_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_E2IMGOption_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_E2IMGOption_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_E2IMGOption_IsEmpty();
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_Section_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_Section_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_Section_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_Section_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_Section_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_Object_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_Object_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_Object_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_Object_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_Object_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_Column_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_Column_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_Column_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_Column_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_Column_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_BinaryObject_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_BinaryObject_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_BinaryObject_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_BinaryObject_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_BinaryObject_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_Field_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_Field_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_Field_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_Field_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_Field_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_DateTime_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_DateTime_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_DateTime_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_DateTime_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_DateTime_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_Font_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_Font_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_Font_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_Font_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_Font_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_Pen_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_Pen_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_Pen_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_Pen_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_Pen_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_Picture_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_Picture_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_Picture_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_Picture_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_Picture_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_TextFormat_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_TextFormat_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_TextFormat_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_TextFormat_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_TextFormat_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_Border_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_Border_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_Border_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_Border_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_Border_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_TextBox_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_TextBox_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_TextBox_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_TextBox_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_TextBox_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_GroupSection_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_GroupSection_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_GroupSection_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_GroupSection_IsEmpty();
    /**
     * 对象值复制
     * @param ID1 整数参数
     * @return 返回整数
     */
    public static native int report_GroupSection_Assign(int ID1);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_ColumnSection_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_ColumnSection_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_ColumnSection_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_ColumnSection_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_ColumnSection_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_ReportSection_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_ReportSection_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_ReportSection_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_ReportSection_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_ReportSection_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_ColumnCell_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_ColumnCell_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_ColumnCell_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_ColumnCell_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_ColumnCell_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_CellBase_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_CellBase_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_CellBase_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_CellBase_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_CellBase_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_Control_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_Control_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_Control_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_Control_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_Control_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_Controls_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_Controls_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_Controls_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_Controls_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_Controls_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_Fields_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_Fields_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_Fields_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_Fields_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_Fields_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_Parameters_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_Parameters_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_Parameters_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_Parameters_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_Parameters_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_ReportHeaders_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_ReportHeaders_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_ReportHeaders_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_ReportHeaders_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_ReportHeaders_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_ReportFooters_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_ReportFooters_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_ReportFooters_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_ReportFooters_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_ReportFooters_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_Groups_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_Groups_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_Groups_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_Groups_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_Groups_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_ChartSeriesCollection_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_ChartSeriesCollection_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_ChartSeriesCollection_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_ChartSeriesCollection_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_ChartSeriesCollection_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_ColumnContentCells_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_ColumnContentCells_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_ColumnContentCells_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_ColumnContentCells_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_ColumnContentCells_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_Columns_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_Columns_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_Columns_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_Columns_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_Columns_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_ColumnTitleCells_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_ColumnTitleCells_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_ColumnTitleCells_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_ColumnTitleCells_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_ColumnTitleCells_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_ReportFooter_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_ReportFooter_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_ReportFooter_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_ReportFooter_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_ReportFooter_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_DetailGrid_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_DetailGrid_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_DetailGrid_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_DetailGrid_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_DetailGrid_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_Recordset_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_Recordset_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_Recordset_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_Recordset_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_Recordset_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_CrossTab_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_CrossTab_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_CrossTab_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_CrossTab_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_CrossTab_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_ColumnTitle_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_ColumnTitle_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_ColumnTitle_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_ColumnTitle_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_ColumnTitle_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_ColumnTitleCell_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_ColumnTitleCell_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_ColumnTitleCell_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_ColumnTitleCell_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_ColumnTitleCell_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_ColumnContent_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_ColumnContent_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_ColumnContent_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_ColumnContent_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_ColumnContent_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_ColumnContentCell_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_ColumnContentCell_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_ColumnContentCell_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_ColumnContentCell_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_ColumnContentCell_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_Group_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_Group_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_Group_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_Group_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_Group_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_GroupHeader_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_GroupHeader_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_GroupHeader_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_GroupHeader_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_GroupHeader_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_GroupFooter_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_GroupFooter_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_GroupFooter_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_GroupFooter_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_GroupFooter_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_Parameter_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_Parameter_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_Parameter_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_Parameter_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_Parameter_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_StaticBox_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_StaticBox_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_StaticBox_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_StaticBox_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_StaticBox_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_ShapeBox_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_ShapeBox_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_ShapeBox_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_ShapeBox_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_ShapeBox_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_SystemVarBox_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_SystemVarBox_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_SystemVarBox_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_SystemVarBox_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_SystemVarBox_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_FieldBox_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_FieldBox_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_FieldBox_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_FieldBox_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_FieldBox_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_SummaryBox_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_SummaryBox_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_SummaryBox_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_SummaryBox_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_SummaryBox_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_RichTextBox_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_RichTextBox_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_RichTextBox_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_RichTextBox_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_RichTextBox_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_PictureBox_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_PictureBox_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_PictureBox_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_PictureBox_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_PictureBox_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_MemoBox_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_MemoBox_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_MemoBox_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_MemoBox_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_MemoBox_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_SubReport_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_SubReport_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_SubReport_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_SubReport_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_SubReport_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_Line_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_Line_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_Line_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_Line_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_Line_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_Chart_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_Chart_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_Chart_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_Chart_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_Chart_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_ChartAxis_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_ChartAxis_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_ChartAxis_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_ChartAxis_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_ChartAxis_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_ChartSeries_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_ChartSeries_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_ChartSeries_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_ChartSeries_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_ChartSeries_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_Barcode_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_Barcode_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_Barcode_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_Barcode_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_Barcode_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_FreeGrid_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_FreeGrid_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_FreeGrid_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_FreeGrid_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_FreeGrid_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_FreeGridRow_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_FreeGridRow_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_FreeGridRow_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_FreeGridRow_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_FreeGridRow_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_FreeGridColumn_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_FreeGridColumn_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_FreeGridColumn_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_FreeGridColumn_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_FreeGridColumn_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_FreeGridCell_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_FreeGridCell_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_FreeGridCell_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_FreeGridCell_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_FreeGridCell_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_Printer_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_Printer_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_Printer_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_Printer_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_Printer_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_ImageList_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_ImageList_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_ImageList_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_ImageList_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_ImageList_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_IGRSharePrintSetupObject_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_IGRSharePrintSetupObject_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_IGRSharePrintSetupObject_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_IGRSharePrintSetupObject_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_IGRSharePrintSetupObject_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_TableSelectShow_SetComPointer(int COM_ID);
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_TableSelectShow_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_TableSelectShow_IsEmpty();
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_TablePrintShow_SetComPointer(int COM_ID);
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_TablePrintShow_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_TablePrintShow_IsEmpty();
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_Graphics_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_Graphics_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_Graphics_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_Graphics_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_Graphics_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_Utility_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_Utility_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_Utility_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_Utility_IsEmpty();
    /**
     * 对象值复制
     * @param ID 整数参数
     * @return 返回整数
     */
    public static native int report_Utility_Assign(int ID);
    /**
     * 加载COM对象
     * @param COM_ID 整数参数
     */
    public static native void report_TableDesignShow_SetComPointer(int COM_ID);
    /**
     * 返回COM对象
     * @return 返回整数
     */
    public static native int report_TableDesignShow_InitPointer();
    /**
     * 返回自身对象
     * @return 返回整数
     */
    public static native int report_TableDesignShow_ThisInit();
    /**
     * 是否为空
     * @return 返回布尔值
     */
    public static native boolean report_TableDesignShow_IsEmpty();
    /**
     * 初始化
     * @return 返回整数
     */
    public static native int report_Table_Init();
    /**
     * 释放
     */
    public static native void report_Table_Release();
    /**
     * 写标识
     * @param Tag 文本参数
     */
    public static native void report_Table_SetTag(String Tag);
    /**
     * 绑定事件
     * @param value 整数参数
     * @return 返回布尔值
     */
    public static native boolean report_Table_BindEvent(int value);
    /**
     * 读标识
     * @return 返回文本
     */
    public static native String report_Table_GetTag();
    /**
     * 读版本号
     * @return 返回文本
     */
    public static native String report_Table_GetVersion();
    /**
     * 读文档标题
     * @return 返回文本
     */
    public static native String report_Table_GetTitle();
    /**
     * 写文档标题
     * @param Title 文本参数
     */
    public static native void report_Table_SetTitle(String Title);
    /**
     * 写作者
     * @param Author 文本参数
     */
    public static native void report_Table_SetAuthor(String Author);
    /**
     * 读作者
     * @return 返回文本
     */
    public static native String report_Table_GetAuthor();
    /**
     * 读简介
     * @return 返回文本
     */
    public static native String report_Table_GetDescription();
    /**
     * 写简介
     * @param Description 文本参数
     */
    public static native void report_Table_SetDescription(String Description);
    /**
     * 读语言
     * @return 返回整数
     */
    public static native int report_Table_GetLanguage();
    /**
     * 写语言
     * @param Language 整数参数
     */
    public static native void report_Table_SetLanguage(int Language);
    /**
     * 读锁定
     * @return 返回整数
     */
    public static native int report_Table_GetLock();
    /**
     * 写锁定
     * @param Lock 整数参数
     */
    public static native void report_Table_SetLock(int Lock);
    /**
     * 读存储格式
     * @return 返回整数
     */
    public static native int report_Table_GetStorageFormat();
    /**
     * 写存储格式
     * @param StorageFormat 整数参数
     */
    public static native void report_Table_SetStorageFormat(int StorageFormat);
    /**
     * 读文本编码
     * @return 返回整数
     */
    public static native int report_Table_GetTextEncode();
    /**
     * 写文本编码
     * @param TextEncode 整数参数
     */
    public static native void report_Table_SetTextEncode(int TextEncode);
    /**
     * 读文档类型
     * @return 返回整数
     */
    public static native int report_Table_GetDocType();
    /**
     * 写文档类型
     * @param TextEncode 整数参数
     */
    public static native void report_Table_SetDocType(int TextEncode);
    /**
     * 读单位
     * @return 返回整数
     */
    public static native int report_Table_GetUnit();
    /**
     * 写单位
     * @param Unit 整数参数
     */
    public static native void report_Table_SetUnit(int Unit);
    /**
     * 读脚本类型
     * @return 返回整数
     */
    public static native int report_Table_GetScriptType();
    /**
     * 写脚本类型
     * @param ScriptType 整数参数
     */
    public static native void report_Table_SetScriptType(int ScriptType);
    /**
     * 读内码表
     * @return 返回整数
     */
    public static native int report_Table_GetCodePage();
    /**
     * 写内码表
     * @param CodePage 整数参数
     */
    public static native void report_Table_SetCodePage(int CodePage);
    /**
     * 读对齐到栅格点
     * @return 返回布尔值
     */
    public static native boolean report_Table_GetAlignToGrid();
    /**
     * 写对齐到栅格点
     * @param AlignToGrid 布尔参数
     */
    public static native void report_Table_SetAlignToGrid(boolean AlignToGrid);
    /**
     * 读显示栅格点
     * @return 返回布尔值
     */
    public static native boolean report_Table_GetShowGrid();
    /**
     * 写显示栅格点
     * @param ShowGrid 布尔参数
     */
    public static native void report_Table_SetShowGrid(boolean ShowGrid);
    /**
     * 读每单位栅格点列数
     * @return 返回整数
     */
    public static native int report_Table_GetGridColsPerUnit();
    /**
     * 写每单位栅格点列数
     * @param GridColsPerUnit 整数参数
     */
    public static native void report_Table_SetGridColsPerUnit(int GridColsPerUnit);
    /**
     * 读每单位栅格点行数
     * @return 返回整数
     */
    public static native int report_Table_GetGridRowsPerUnit();
    /**
     * 写每单位栅格点行数
     * @param GridRowsPerUnit 整数参数
     */
    public static native void report_Table_SetGridRowsPerUnit(int GridRowsPerUnit);
    /**
     * 读财务金额位数
     * @return 返回整数
     */
    public static native int report_Table_GetShowMoneyDigit();
    /**
     * 写财务金额位数
     * @param ShowMoneyDigit 整数参数
     */
    public static native void report_Table_SetShowMoneyDigit(int ShowMoneyDigit);
    /**
     * 读财务金额位总宽度
     * @return 返回整数
     */
    public static native int report_Table_GetShowMoneyWidth();
    /**
     * 写财务金额位总宽度
     * @param ShowMoneyWidth 整数参数
     */
    public static native void report_Table_SetShowMoneyWidth(int ShowMoneyWidth);
    /**
     * 读财务金额线颜色
     * @return 返回整数
     */
    public static native int report_Table_GetShowMoneyLineColor();
    /**
     * 写财务金额线颜色
     * @param ShowMoneyLineColor 整数参数
     */
    public static native void report_Table_SetShowMoneyLineColor(int ShowMoneyLineColor);
    /**
     * 读财务金额分隔线颜色
     * @return 返回整数
     */
    public static native int report_Table_GetShowMoneySepLineColor();
    /**
     * 写财务金额分隔线颜色
     * @param ShowMoneySepLineColor 整数参数
     */
    public static native void report_Table_SetShowMoneySepLineColor(int ShowMoneySepLineColor);
    /**
     * 读背景色
     * @return 返回整数
     */
    public static native int report_Table_GetBackColor();
    /**
     * 写背景色
     * @param BackColor 整数参数
     */
    public static native void report_Table_SetBackColor(int BackColor);
    /**
     * 读设计纸张名称
     * @return 返回文本
     */
    public static native String report_Table_GetDesignPaperName();
    /**
     * 写设计纸张名称
     * @param DesignPaperName 文本参数
     */
    public static native void report_Table_SetDesignPaperName(String DesignPaperName);
    /**
     * 读设计纸张规格
     * @return 返回整数
     */
    public static native int report_Table_GetDesignPaperSize();
    /**
     * 写设计纸张规格
     * @param DesignPaperSize 整数参数
     */
    public static native void report_Table_SetDesignPaperSize(int DesignPaperSize);
    /**
     * 读设计纸张方向
     * @return 返回整数
     */
    public static native int report_Table_GetDesignPaperOrientation();
    /**
     * 写设计纸张方向
     * @param DesignPaperOrientation 整数参数
     */
    public static native void report_Table_SetDesignPaperOrientation(int DesignPaperOrientation);
    /**
     * 读设计纸张长度
     * @return 返回整数
     */
    public static native int report_Table_GetDesignPaperLength();
    /**
     * 写设计纸张长度
     * @param DesignPaperLength 整数参数
     */
    public static native void report_Table_SetDesignPaperLength(int DesignPaperLength);
    /**
     * 读设计纸张宽度
     * @return 返回整数
     */
    public static native int report_Table_GetDesignPaperWidth();
    /**
     * 写设计纸张宽度
     * @param DesignPaperWidth 整数参数
     */
    public static native void report_Table_SetDesignPaperWidth(int DesignPaperWidth);
    /**
     * 读设计左边距
     * @return 返回整数
     */
    public static native int report_Table_GetDesignLeftMargin();
    /**
     * 写设计左边距
     * @param DesignLeftMargin 整数参数
     */
    public static native void report_Table_SetDesignLeftMargin(int DesignLeftMargin);
    /**
     * 读设计右边距
     * @return 返回整数
     */
    public static native int report_Table_GetDesignRightMargin();
    /**
     * 写设计右边距
     * @param DesignRightMargin 整数参数
     */
    public static native void report_Table_SetDesignRightMargin(int DesignRightMargin);
    /**
     * 读设计上边距
     * @return 返回整数
     */
    public static native int report_Table_GetDesignTopMargin();
    /**
     * 写设计上边距
     * @param DesignTopMargin 整数参数
     */
    public static native void report_Table_SetDesignTopMargin(int DesignTopMargin);
    /**
     * 读设计下边距
     * @return 返回整数
     */
    public static native int report_Table_GetDesignBottomMargin();
    /**
     * 写设计下边距
     * @param DesignBottomMargin 整数参数
     */
    public static native void report_Table_SetDesignBottomMargin(int DesignBottomMargin);
    /**
     * 读页分割数
     * @return 返回整数
     */
    public static native int report_Table_GetPageDivideCount();
    /**
     * 写页分割数
     * @param PageDivideCount 整数参数
     */
    public static native void report_Table_SetPageDivideCount(int PageDivideCount);
    /**
     * 读页分割间隙
     * @return 返回整数
     */
    public static native int report_Table_GetPageDivideSpacing();
    /**
     * 写页分割间隙
     * @param PageDivideSpacing 整数参数
     */
    public static native void report_Table_SetPageDivideSpacing(int PageDivideSpacing);
    /**
     * 读页分割线显示
     * @return 返回布尔值
     */
    public static native boolean report_Table_GetPageDivideLine();
    /**
     * 写页分割线显示
     * @param PageDivideLine 布尔参数
     */
    public static native void report_Table_SetPageDivideLine(boolean PageDivideLine);
    /**
     * 读按设计页面打印
     * @return 返回布尔值
     */
    public static native boolean report_Table_GetPrintAsDesignPaper();
    /**
     * 写按设计页面打印
     * @param PrintAsDesignPaper 布尔参数
     */
    public static native void report_Table_SetPrintAsDesignPaper(boolean PrintAsDesignPaper);
    /**
     * 读即打即停
     * @return 返回布尔值
     */
    public static native boolean report_Table_GetContinuePrint();
    /**
     * 写即打即停
     * @param ContinuePrint 布尔参数
     */
    public static native void report_Table_SetContinuePrint(boolean ContinuePrint);
    /**
     * 读单色打印
     * @return 返回布尔值
     */
    public static native boolean report_Table_GetMonoPrint();
    /**
     * 写单色打印
     * @param MonoPrint 布尔参数
     */
    public static native void report_Table_SetMonoPrint(boolean MonoPrint);
    /**
     * 读镜像左右页边距
     * @return 返回布尔值
     */
    public static native boolean report_Table_GetMirrorMargins();
    /**
     * 写镜像左右页边距
     * @param MirrorMargins 布尔参数
     */
    public static native void report_Table_SetMirrorMargins(boolean MirrorMargins);
    /**
     * 读获取背景图像
     * @return 返回整数
     */
    public static native int report_Table_GetBackImage();
    /**
     * 写设置背景图像
     * @param BackImage 整数参数
     */
    public static native void report_Table_SetBackImage(int BackImage);
    /**
     * 读背景图文件
     * @return 返回文本
     */
    public static native String report_Table_GetBackImageFile();
    /**
     * 写背景图文件
     * @param BackImageFile 文本参数
     */
    public static native void report_Table_SetBackImageFile(String BackImageFile);
    /**
     * 读背景图预览
     * @return 返回布尔值
     */
    public static native boolean report_Table_GetBackImagePreview();
    /**
     * 写背景图预览
     * @param BackImagePreview 布尔参数
     */
    public static native void report_Table_SetBackImagePreview(boolean BackImagePreview);
    /**
     * 读背景图打印
     * @return 返回布尔值
     */
    public static native boolean report_Table_GetBackImagePrint();
    /**
     * 写背景图打印
     * @param BackImagePrint 布尔参数
     */
    public static native void report_Table_SetBackImagePrint(boolean BackImagePrint);
    /**
     * 读获取水印图像
     * @return 返回整数
     */
    public static native int report_Table_GetWatermark();
    /**
     * 写设置水印图像
     * @param Watermark 整数参数
     */
    public static native void report_Table_SetWatermark(int Watermark);
    /**
     * 读水印图显示方式
     * @return 返回整数
     */
    public static native int report_Table_GetWatermarkAlignment();
    /**
     * 写水印图显示方式
     * @param WatermarkAlignment 整数参数
     */
    public static native void report_Table_SetWatermarkAlignment(int WatermarkAlignment);
    /**
     * 读水印图缩放方式
     * @return 返回整数
     */
    public static native int report_Table_GetWatermarkSizeMode();
    /**
     * 写水印图缩放方式
     * @param WatermarkSizeMode 整数参数
     */
    public static native void report_Table_SetWatermarkSizeMode(int WatermarkSizeMode);
    /**
     * 读参数化查询连接串
     * @return 返回文本
     */
    public static native String report_Table_GetConnectionString();
    /**
     * 写参数化查询连接串
     * @param ConnectionString 文本参数
     */
    public static native void report_Table_SetConnectionString(String ConnectionString);
    /**
     * 读参数化查询SQL
     * @return 返回文本
     */
    public static native String report_Table_GetQuerySQL();
    /**
     * 写参数化查询SQL
     * @param QuerySQL 文本参数
     */
    public static native void report_Table_SetQuerySQL(String QuerySQL);
    /**
     * 读忽略数据绑定
     * @return 返回布尔值
     */
    public static native boolean report_Table_GetSkipQuery();
    /**
     * 写忽略数据绑定
     * @param SkipQuery 布尔参数
     */
    public static native void report_Table_SetSkipQuery(boolean SkipQuery);
    /**
     * 读Xml表名
     * @return 返回文本
     */
    public static native String report_Table_GetXmlTableName();
    /**
     * 写Xml表名
     * @param XmlTableName 文本参数
     */
    public static native void report_Table_SetXmlTableName(String XmlTableName);
    /**
     * 读全局脚本
     * @return 返回文本
     */
    public static native String report_Table_GetGlobalScript();
    /**
     * 写全局脚本
     * @param GlobalScript 文本参数
     */
    public static native void report_Table_SetGlobalScript(String GlobalScript);
    /**
     * 读初始化脚本
     * @return 返回文本
     */
    public static native String report_Table_GetInitializeScript();
    /**
     * 写初始化脚本
     * @param InitializeScript 文本参数
     */
    public static native void report_Table_SetInitializeScript(String InitializeScript);
    /**
     * 读开始处理记录脚本
     * @return 返回文本
     */
    public static native String report_Table_GetProcessBeginScript();
    /**
     * 写开始处理记录脚本
     * @param ProcessBeginScript 文本参数
     */
    public static native void report_Table_SetProcessBeginScript(String ProcessBeginScript);
    /**
     * 读结束处理记录脚本
     * @return 返回文本
     */
    public static native String report_Table_GetProcessEndScript();
    /**
     * 写结束处理记录脚本
     * @param ProcessEndScript 文本参数
     */
    public static native void report_Table_SetProcessEndScript(String ProcessEndScript);
    /**
     * 读排序前脚本
     * @return 返回文本
     */
    public static native String report_Table_GetBeforeSortScript();
    /**
     * 写排序前脚本
     * @param BeforeSortScript 文本参数
     */
    public static native void report_Table_SetBeforeSortScript(String BeforeSortScript);
    /**
     * 读页开始脚本
     * @return 返回文本
     */
    public static native String report_Table_GetPageStartScript();
    /**
     * 写页开始脚本
     * @param PageStartScript 文本参数
     */
    public static native void report_Table_SetPageStartScript(String PageStartScript);
    /**
     * 读页结束脚本
     * @return 返回文本
     */
    public static native String report_Table_GetPageEndScript();
    /**
     * 写页结束脚本
     * @param PageEndScript 文本参数
     */
    public static native void report_Table_SetPageEndScript(String PageEndScript);
    /**
     * 读导出前脚本
     * @return 返回文本
     */
    public static native String report_Table_GetExportBeginScript();
    /**
     * 写导出前脚本
     * @param ExportBeginScript 文本参数
     */
    public static native void report_Table_SetExportBeginScript(String ExportBeginScript);
    /**
     * 读导出后脚本
     * @return 返回文本
     */
    public static native String report_Table_GetExportEndScript();
    /**
     * 写导出后脚本
     * @param ExportEndScript 文本参数
     */
    public static native void report_Table_SetExportEndScript(String ExportEndScript);
    /**
     * 读打印前脚本
     * @return 返回文本
     */
    public static native String report_Table_GetPrintBeginScript();
    /**
     * 写打印前脚本
     * @param PrintBeginScript 文本参数
     */
    public static native void report_Table_SetPrintBeginScript(String PrintBeginScript);
    /**
     * 读打印后脚本
     * @return 返回文本
     */
    public static native String report_Table_GetPrintEndScript();
    /**
     * 写打印后脚本
     * @param PrintEndScript 文本参数
     */
    public static native void report_Table_SetPrintEndScript(String PrintEndScript);
    /**
     * 读打印页脚本
     * @return 返回文本
     */
    public static native String report_Table_GetPrintPageScript();
    /**
     * 写打印页脚本
     * @param PrintPageScript 文本参数
     */
    public static native void report_Table_SetPrintPageScript(String PrintPageScript);
    /**
     * 读显示预览脚本
     * @return 返回文本
     */
    public static native String report_Table_GetShowPreviewWndScript();
    /**
     * 写显示预览脚本
     * @param ShowPreviewWndScript 文本参数
     */
    public static native void report_Table_SetShowPreviewWndScript(String ShowPreviewWndScript);
    /**
     * 读页面生成前脚本
     * @return 返回文本
     */
    public static native String report_Table_GetGeneratePagesBeginScript();
    /**
     * 写页面生成前脚本
     * @param GeneratePagesBeginScript 文本参数
     */
    public static native void report_Table_SetGeneratePagesBeginScript(String GeneratePagesBeginScript);
    /**
     * 读页面生成后脚本
     * @return 返回文本
     */
    public static native String report_Table_GetGeneratePagesEndScript();
    /**
     * 写页面生成后脚本
     * @param GeneratePagesEndScript 文本参数
     */
    public static native void report_Table_SetGeneratePagesEndScript(String GeneratePagesEndScript);
    /**
     * 读显示进度界面
     * @return 返回布尔值
     */
    public static native boolean report_Table_GetShowProgressUI();
    /**
     * 写显示进度界面
     * @param ShowProgressUI 布尔参数
     */
    public static native void report_Table_SetShowProgressUI(boolean ShowProgressUI);
    /**
     * 读最近加载的文件
     * @return 返回文本
     */
    public static native String report_Table_GetLatestLoadedFile();
    /**
     * 写最近加载的文件
     * @param LatestLoadedFile 文本参数
     */
    public static native void report_Table_SetLatestLoadedFile(String LatestLoadedFile);
    /**
     * 读运行中
     * @return 返回布尔值
     */
    public static native boolean report_Table_GetRunning();
    /**
     * 读是否空载
     * @return 返回布尔值
     */
    public static native boolean report_Table_GetIsBlank();
    /**
     * 读显示模式
     * @return 返回整数
     */
    public static native int report_Table_GetDisplayMode();
    /**
     * 读是否有浮动部件
     * @return 返回布尔值
     */
    public static native boolean report_Table_GetHasFloatControl();
    /**
     * 读是否首次生成
     * @return 返回布尔值
     */
    public static native boolean report_Table_GetFirstPass();
    /**
     * 读页面空白高度
     * @return 返回整数
     */
    public static native int report_Table_GetPageBlankHeight();
    /**
     * 关于
     */
    public static native void report_Table_About();
    /**
     * 注册
     * @param Registration 文本参数
     * @return 返回布尔值
     */
    public static native boolean report_Table_Register(String Registration);
    /**
     * 注册SnailCatMallTable
     * @return 返回布尔值
     */
    private static native boolean report_Table_SnailCatMallTable();
    /**
     * 取模板WEB注册状态
     * @return 返回整数
     */
    public static native int report_Table_GetWebRegisterStatus();
    /**
     * 查找第一个部件框
     * @return 返回整数
     */
    public static native int report_Table_FindFirstControl();
    /**
     * 查找下一个部件框
     * @return 返回整数
     */
    public static native int report_Table_FindNextControl();
    /**
     * 强制换新页
     */
    public static native void report_Table_ForceNewPage();
    /**
     * 从文件加载报表
     * @param FileName 文本参数
     * @return 返回布尔值
     */
    public static native boolean report_Table_LoadFromFile(String FileName);
    /**
     * 保存到文件
     * @param FileName 文本参数
     * @return 返回布尔值
     */
    public static native boolean report_Table_SaveToFile(String FileName);
    /**
     * 从字符串加载报表
     * @param DataText 文本参数
     * @return 返回布尔值
     */
    public static native boolean report_Table_LoadFromStr(String DataText);
    /**
     * 保存到字符串
     * @return 返回文本
     */
    public static native String report_Table_SaveToStr();
    /**
     * 从变量加载报表
     * @param byteP 字节数组参数
     * @param dwSize 整数参数
     * @return 返回布尔值
     */
    public static native boolean report_Table_LoadFromVariant(byte[] byteP, int dwSize);
    /**
     * 保存到变量
     * @return 返回整数
     */
    public static native int report_Table_SaveToVariant();
    /**
     * 从内存加载报表
     * @param DataPointer 整数参数
     * @param DataSize 整数参数
     * @return 返回布尔值
     */
    public static native boolean report_Table_LoadFromMemory(int DataPointer, int DataSize);
    /**
     * 保存到内存
     * @param DataPointer 整数参数
     * @param DataSize 整数参数
     * @return 返回布尔值
     */
    public static native boolean report_Table_SaveToMemory(int DataPointer, int DataSize);
    /**
     * 从文件加载背景图
     * @param FileName 文本参数
     */
    public static native void report_Table_LoadBackImageFromFile(String FileName);
    /**
     * 从内存加载背景图
     * @param DataPointer 整数参数
     * @param DataSize 整数参数
     */
    public static native void report_Table_LoadBackImageFromMemory(int DataPointer, int DataSize);
    /**
     * 从文件加载水印图
     * @param FileName 文本参数
     */
    public static native void report_Table_LoadWatermarkFromFile(String FileName);
    /**
     * 从内存加载水印图
     * @param DataPointer 整数参数
     * @param DataSize 整数参数
     */
    public static native void report_Table_LoadWatermarkFromMemory(int DataPointer, int DataSize);
    /**
     * 从XML载入数据
     * @param XMLTextData 文本参数
     * @return 返回布尔值
     */
    public static native boolean report_Table_LoadDataFromXML(String XMLTextData);
    /**
     * 加载数据准备
     */
    public static native void report_Table_PrepareLoadData();
    /**
     * 加载数据取消
     */
    public static native void report_Table_CancelLoadData();
    /**
     * 添加记录准备
     * @return 返回布尔值
     */
    public static native boolean report_Table_PrepareRecordset();
    /**
     * 添加记录取消
     */
    public static native void report_Table_CancelRecordset();
    /**
     * 准备导出
     * @param value 整数参数
     * @return 返回整数
     */
    public static native int report_Table_PrepareExport(int value);
    /**
     * 导出
     * @param OpenFile 布尔参数
     * @return 返回布尔值
     */
    public static native boolean report_Table_Export(boolean OpenFile);
    /**
     * 完成导出
     */
    public static native void report_Table_UnprepareExport();
    /**
     * 直接导出
     * @param value 整数参数
     * @param FileName 文本参数
     * @param value2 布尔参数
     * @param OpenFile 布尔参数
     * @return 返回布尔值
     */
    public static native boolean report_Table_ExportDirect(int value, String FileName, boolean value2, boolean OpenFile);
    /**
     * 中断导出
     */
    public static native void report_Table_AbortExport();
    /**
     * 打印文档数据
     * @param BinaryObject 整数参数
     * @return 返回布尔值
     */
    public static native boolean report_Table_PrintDocumentData(int BinaryObject);
    /**
     * 生成文档数据
     * @return 返回整数
     */
    public static native int report_Table_GenerateDocumentData();
    /**
     * 生成报表文档
     * @param FileName 文本参数
     */
    public static native void report_Table_GenerateDocumentFile(String FileName);
    /**
     * 打印
     * @param value 布尔参数
     */
    public static native void report_Table_Print(boolean value);
    /**
     * 打印扩展
     * @param value 整数参数
     * @param value2 布尔参数
     */
    public static native void report_Table_PrintEx(int value, boolean value2);
    /**
     * 打印预览
     * @param DisplayMode 布尔参数
     */
    public static native void report_Table_PrintPreview(boolean DisplayMode);
    /**
     * 打印预览扩展
     * @param value 整数参数
     * @param DisplayMode 布尔参数
     * @param value2 布尔参数
     */
    public static native void report_Table_PrintPreviewEx(int value, boolean DisplayMode, boolean value2);
    /**
     * 开始循环打印
     * @param value 整数参数
     * @param value2 布尔参数
     * @return 返回布尔值
     */
    public static native boolean report_Table_BeginLoopPrint(int value, boolean value2);
    /**
     * 循环打印
     */
    public static native void report_Table_LoopPrint();
    /**
     * 结束循环打印
     */
    public static native void report_Table_EndLoopPrint();
    /**
     * 中断打印
     */
    public static native void report_Table_AbortPrint();
    /**
     * 读数据加载
     * @return 返回布尔值
     */
    public static native boolean report_Table_GetDataLoaded();
    /**
     * 读获取父报表
     * @return 返回整数
     */
    public static native int report_Table_GetParentReport();
    /**
     * 读获取页眉
     * @return 返回整数
     */
    public static native int report_Table_GetPageHeader();
    /**
     * 读获取页脚
     * @return 返回整数
     */
    public static native int report_Table_GetPageFooter();
    /**
     * 读获取字体
     * @return 返回整数
     */
    public static native int report_Table_GetFont();
    /**
     * 获取报表头String
     * @param NameIndex 文本参数
     * @return 返回整数
     */
    public static native int report_Table_ReportHeaderString1(String NameIndex);
    /**
     * 获取报表头Int
     * @param NameIndex 整数参数
     * @return 返回整数
     */
    public static native int report_Table_ReportHeaderInt(int NameIndex);
    /**
     * 按名称取报表头
     * @param Name 文本参数
     * @return 返回整数
     */
    public static native int report_Table_ReportHeaderString(String Name);
    /**
     * 按序号取报表头
     * @param Index 整数参数
     * @return 返回整数
     */
    public static native int report_Table_ReportHeaderInt1(int Index);
    /**
     * 读获取报表头集合
     * @return 返回整数
     */
    public static native int report_Table_GetReportHeaders();
    /**
     * 获取报表尾String
     * @param NameIndex 文本参数
     * @return 返回整数
     */
    public static native int report_Table_ReportFooterString1(String NameIndex);
    /**
     * 获取报表尾Int
     * @param NameIndex 整数参数
     * @return 返回整数
     */
    public static native int report_Table_ReportFooterInt1(int NameIndex);
    /**
     * 按名称取报表尾
     * @param Name 文本参数
     * @return 返回整数
     */
    public static native int report_Table_ReportFooterString(String Name);
    /**
     * 按序号取报表尾
     * @param Index 整数参数
     * @return 返回整数
     */
    public static native int report_Table_ReportFooterInt(int Index);
    /**
     * 读获取报表尾集合
     * @return 返回整数
     */
    public static native int report_Table_GetReportFooters();
    /**
     * 读获取参数集合
     * @return 返回整数
     */
    public static native int report_Table_GetParameters();
    /**
     * 读获取浮动部件框集合
     * @return 返回整数
     */
    public static native int report_Table_GetFloatControls();
    /**
     * 读获取图像集合
     * @return 返回整数
     */
    public static native int report_Table_GetImageList();
    /**
     * 读获取触发事件报表
     * @return 返回整数
     */
    public static native int report_Table_GetFiringReport();
    /**
     * 读获取绘图接口
     * @return 返回整数
     */
    public static native int report_Table_GetGraphics();
    /**
     * 读获取功能函数
     * @return 返回整数
     */
    public static native int report_Table_GetUtility();
    /**
     * 读获取共享打印设置
     * @return 返回整数
     */
    public static native int report_Table_GetSharePrintSetupObject();
    /**
     * 读共享打印选项
     * @return 返回整数
     */
    public static native int report_Table_GetSharePrintSetupOptions();
    /**
     * 写共享打印选项
     * @param SharePrintSetupOptions 整数参数
     */
    public static native void report_Table_SetSharePrintSetupOptions(int SharePrintSetupOptions);
    /**
     * 取共享打印参数值
     * @param PrintParameter1 整数参数
     * @return 返回布尔值
     */
    public static native boolean report_Table_GetSharePrintSetupGetOptionEnabled(int PrintParameter1);
    /**
     * 置共享打印参数值
     * @param PrintParameter1 整数参数
     * @return 返回布尔值
     */
    public static native boolean report_Table_SetSharePrintSetupGetOptionEnabled(int PrintParameter1);
    /**
     * 共享打印选项全部开启
     */
    public static native void report_Table_SharePrintSetupEnableAllOptions();
    /**
     * 共享打印选项全部禁止
     */
    public static native void report_Table_SharePrintSetupDisableAllOptions();
    /**
     * 转换单位到像素
     * @param CalculateSingle 整数参数
     * @return 返回整数
     */
    public static native int report_Table_UnitToPixels(int CalculateSingle);
    /**
     * 转换像素到单位
     * @param value 整数参数
     * @return 返回整数
     */
    public static native int report_Table_PixelsToUnit(int value);
    /**
     * 获取系统变量值
     * @param value 整数参数
     * @return 返回整数
     */
    public static native int report_Table_SystemVarValue(int value);
    /**
     * 获取分组变量值
     * @param value 整数参数
     * @param GroupIndex 整数参数
     * @return 返回整数
     */
    public static native int report_Table_SystemVarValue2(int value, int GroupIndex);
    /**
     * 读获取明细网格
     * @return 返回整数
     */
    public static native int report_Table_GetDetailGrid();
    /**
     * 读获取运行时明细网格
     * @return 返回整数
     */
    public static native int report_Table_GetRunningDetailGrid();
    /**
     * 读获取打印机
     * @return 返回整数
     */
    public static native int report_Table_GetPrinter();
    /**
     * 插入页眉
     * @return 返回整数
     */
    public static native int report_Table_InsertPageHeader();
    /**
     * 插入页脚
     * @return 返回整数
     */
    public static native int report_Table_InsertPageFooter();
    /**
     * 插入报表头
     * @return 返回整数
     */
    public static native int report_Table_InsertReportHeader();
    /**
     * 插入报表尾
     * @return 返回整数
     */
    public static native int report_Table_InsertReportFooter();
    /**
     * 插入明细网格
     * @return 返回整数
     */
    public static native int report_Table_InsertDetailGrid();
    /**
     * 增加参数
     * @return 返回整数
     */
    public static native int report_Table_AddParameter();
    /**
     * 删除页眉
     */
    public static native void report_Table_DeletePageHeader();
    /**
     * 删除页脚
     */
    public static native void report_Table_DeletePageFooter();
    /**
     * 删除报表头String
     * @param NameIndex 文本参数
     */
    public static native void report_Table_DeleteReportHeaderString1(String NameIndex);
    /**
     * 删除报表头Int
     * @param NameIndex 整数参数
     */
    public static native void report_Table_DeleteReportHeaderInt(int NameIndex);
    /**
     * 按名称删除报表头
     * @param Name 文本参数
     */
    public static native void report_Table_DeleteReportHeaderString(String Name);
    /**
     * 按序号删除报表头
     * @param Index 整数参数
     */
    public static native void report_Table_DeleteReportHeader(int Index);
    /**
     * 删除报表尾String
     * @param NameIndex 文本参数
     */
    public static native void report_Table_DeleteReportFooterString1(String NameIndex);
    /**
     * 删除报表尾Int
     * @param NameIndex 整数参数
     */
    public static native void report_Table_DeleteReportFooterInt(int NameIndex);
    /**
     * 按名称删除报表尾
     * @param Name 文本参数
     */
    public static native void report_Table_DeleteReportFooterString(String Name);
    /**
     * 按序号删除报表尾
     * @param Index 文本参数
     */
    public static native void report_Table_DeleteReportFooter(String Index);
    /**
     * 删除明细网格
     */
    public static native void report_Table_DeleteDetailGrid();
    /**
     * 按名称取部件框
     * @param Name 文本参数
     * @return 返回整数
     */
    public static native int report_Table_ControlByName(String Name);
    /**
     * 按名称取列
     * @param ColumnName 文本参数
     * @return 返回整数
     */
    public static native int report_Table_ColumnByName(String ColumnName);
    /**
     * 按名称取字段
     * @param FieldName 文本参数
     * @return 返回整数
     */
    public static native int report_Table_FieldByName(String FieldName);
    /**
     * 按源名称取字段
     * @param FieldName 文本参数
     * @return 返回整数
     */
    public static native int report_Table_FieldByDBName(String FieldName);
    /**
     * 按名称取参数
     * @param Name 文本参数
     * @return 返回整数
     */
    public static native int report_Table_ParameterByName(String Name);
    /**
     * 清空
     */
    public static native void report_Table_Clear();
    /**
     * 初始化
     */
    public static native void report_TableSelectShow_InitTableSelectShowWindow();
    /**
     * 绑定事件
     * @param value CGRDisplayViewerEventHandler
     * @return 返回布尔值
     */
    public static native boolean report_TableSelectShow_BindEvent(CGRDisplayViewerEventHandler value);
    /**
     * 释放
     */
    public static native void report_TableSelectShow_Release();
    /**
     * 创建组件窗口
     * @param value 整数参数
     * @param value2 整数参数
     * @param cx 整数参数
     * @param cy 整数参数
     * @param hWindow 整数参数
     * @return 返回整数
     */
    public static native int report_TableSelectShow_CreateSelectShowWindow(int value, int value2, int cx, int cy, int hWindow);
    /**
     * 调试查询显示器组件位置
     * @param value 整数参数
     * @param value2 整数参数
     * @param cx 整数参数
     * @param cy 整数参数
     */
    public static native void report_TableSelectShow_UpdateSelectShowWindowSize(int value, int value2, int cx, int cy);
    /**
     * 置对象
     * @param value 整数参数
     */
    public static native void report_TableSelectShow_SetObject(int value);
    /**
     * 读边框样式
     * @return 返回整数
     */
    public static native int report_TableSelectShow_GetBorderStyle();
    /**
     * 写边框样式
     * @param value 整数参数
     */
    public static native void report_TableSelectShow_SetBorderStyle(int value);
    /**
     * 读左边距
     * @return 返回整数
     */
    public static native int report_TableSelectShow_GetLeftGap();
    /**
     * 写左边距
     * @param value 整数参数
     */
    public static native void report_TableSelectShow_SetLeftGap(int value);
    /**
     * 读上边距
     * @return 返回整数
     */
    public static native int report_TableSelectShow_GetTopGap();
    /**
     * 写上边距
     * @param value 整数参数
     */
    public static native void report_TableSelectShow_SetTopGap(int value);
    /**
     * 读右边距
     * @return 返回整数
     */
    public static native int report_TableSelectShow_GetRightGap();
    /**
     * 写右边距
     * @param value 整数参数
     */
    public static native void report_TableSelectShow_SetRightGap(int value);
    /**
     * 读下边距
     * @return 返回整数
     */
    public static native int report_TableSelectShow_GetBottomGap();
    /**
     * 写下边距
     * @param value 整数参数
     */
    public static native void report_TableSelectShow_SetBottomGap(int value);
    /**
     * 读关联报表
     * @return 返回整数
     */
    public static native int report_TableSelectShow_GetReport();
    /**
     * 写关联报表
     * @param value 整数参数
     */
    public static native void report_TableSelectShow_SetReport(int value);
    /**
     * 读显示工具栏
     * @return 返回布尔值
     */
    public static native boolean report_TableSelectShow_GetShowToolbar();
    /**
     * 写显示工具栏
     * @param value 布尔参数
     */
    public static native void report_TableSelectShow_SetShowToolbar(boolean value);
    /**
     * 读点击列可重排序
     * @return 返回布尔值
     */
    public static native boolean report_TableSelectShow_GetResortable();
    /**
     * 写点击列可重排序
     * @param value 布尔参数
     */
    public static native void report_TableSelectShow_SetResortable(boolean value);
    /**
     * 读重排序按升序
     * @return 返回布尔值
     */
    public static native boolean report_TableSelectShow_GetResortDefaultAsc();
    /**
     * 写重排序按升序
     * @param value 布尔参数
     */
    public static native void report_TableSelectShow_SetResortDefaultAsc(boolean value);
    /**
     * 读重排序区分大小写
     * @return 返回布尔值
     */
    public static native boolean report_TableSelectShow_GetResortCaseSensitive();
    /**
     * 写重排序区分大小写
     * @param value 布尔参数
     */
    public static native void report_TableSelectShow_SetResortCaseSensitive(boolean value);
    /**
     * 读排序列
     * @return 返回文本
     */
    public static native String report_TableSelectShow_GetSortColumns();
    /**
     * 写排序列
     * @param value 文本参数
     */
    public static native void report_TableSelectShow_SetSortColumns(String value);
    /**
     * 读空白区背景色
     * @return 返回整数
     */
    public static native int report_TableSelectShow_GetBackgroundColor();
    /**
     * 写空白区背景色
     * @param value 整数参数
     */
    public static native void report_TableSelectShow_SetBackgroundColor(int value);
    /**
     * 读报表居中显示
     * @return 返回布尔值
     */
    public static native boolean report_TableSelectShow_GetGridCenterView();
    /**
     * 写报表居中显示
     * @param value 布尔参数
     */
    public static native void report_TableSelectShow_SetGridCenterView(boolean value);
    /**
     * 读节缩放适应
     * @return 返回布尔值
     */
    public static native boolean report_TableSelectShow_GetResizeToFit();
    /**
     * 写节缩放适应
     * @param value 布尔参数
     */
    public static native void report_TableSelectShow_SetResizeToFit(boolean value);
    /**
     * 读网格可收缩适应
     * @return 返回布尔值
     */
    public static native boolean report_TableSelectShow_GetGridCanShrink();
    /**
     * 写网格可收缩适应
     * @param value 布尔参数
     */
    public static native void report_TableSelectShow_SetGridCanShrink(boolean value);
    /**
     * 读网格标题3D
     * @return 返回布尔值
     */
    public static native boolean report_TableSelectShow_GetGridTitle3D();
    /**
     * 写网格标题3D
     * @param value 布尔参数
     */
    public static native void report_TableSelectShow_SetGridTitle3D(boolean value);
    /**
     * 读列顺序可移动
     * @return 返回布尔值
     */
    public static native boolean report_TableSelectShow_GetColumnMove();
    /**
     * 写列顺序可移动
     * @param value 布尔参数
     */
    public static native void report_TableSelectShow_SetColumnMove(boolean value);
    /**
     * 读列宽可调整
     * @return 返回布尔值
     */
    public static native boolean report_TableSelectShow_GetColumnResize();
    /**
     * 写列宽可调整
     * @param value 布尔参数
     */
    public static native void report_TableSelectShow_SetColumnResize(boolean value);
    /**
     * 读始终显示水平滚动条
     * @return 返回布尔值
     */
    public static native boolean report_TableSelectShow_GetAlwaysShowHScrollBar();
    /**
     * 写始终显示水平滚动条
     * @param value 布尔参数
     */
    public static native void report_TableSelectShow_SetAlwaysShowHScrollBar(boolean value);
    /**
     * 读始终显示垂直滚动条
     * @return 返回布尔值
     */
    public static native boolean report_TableSelectShow_GetAlwaysShowVScrollBar();
    /**
     * 写始终显示垂直滚动条
     * @param value 布尔参数
     */
    public static native void report_TableSelectShow_SetAlwaysShowVScrollBar(boolean value);
    /**
     * 读水平滚动条位置
     * @return 返回整数
     */
    public static native int report_TableSelectShow_GetHScrollPos();
    /**
     * 写水平滚动条位置
     * @param value 整数参数
     */
    public static native void report_TableSelectShow_SetHScrollPos(int value);
    /**
     * 读垂直滚动条位置
     * @return 返回整数
     */
    public static native int report_TableSelectShow_GetVScrollPos();
    /**
     * 写垂直滚动条位置
     * @param value 整数参数
     */
    public static native void report_TableSelectShow_SetVScrollPos(int value);
    /**
     * 读整行选中
     * @return 返回布尔值
     */
    public static native boolean report_TableSelectShow_GetRowSelection();
    /**
     * 写整行选中
     * @param value 布尔参数
     */
    public static native void report_TableSelectShow_SetRowSelection(boolean value);
    /**
     * 读始终显示选中行
     * @return 返回布尔值
     */
    public static native boolean report_TableSelectShow_GetAlwaysShowSelection();
    /**
     * 写始终显示选中行
     * @param value 布尔参数
     */
    public static native void report_TableSelectShow_SetAlwaysShowSelection(boolean value);
    /**
     * 读总记录数
     * @return 返回整数
     */
    public static native int report_TableSelectShow_GetTotalRecord();
    /**
     * 写总记录数
     * @param value 整数参数
     */
    public static native void report_TableSelectShow_SetTotalRecord(int value);
    /**
     * 读分批获取记录
     * @return 返回布尔值
     */
    public static native boolean report_TableSelectShow_GetBatchGetRecord();
    /**
     * 写分批获取记录
     * @param value 布尔参数
     */
    public static native void report_TableSelectShow_SetBatchGetRecord(boolean value);
    /**
     * 读分批显示记录数
     * @return 返回整数
     */
    public static native int report_TableSelectShow_GetBatchWantRecords();
    /**
     * 写分批显示记录数
     * @param value 整数参数
     */
    public static native void report_TableSelectShow_SetBatchWantRecords(int value);
    /**
     * 读分批序号
     * @return 返回整数
     */
    public static native int report_TableSelectShow_GetBatchNo();
    /**
     * 读分批开始记录号
     * @return 返回整数
     */
    public static native int report_TableSelectShow_GetBatchStartRecNo();
    /**
     * 读分批完成
     * @return 返回布尔值
     */
    public static native boolean report_TableSelectShow_GetBatchFinished();
    /**
     * 写分批完成
     * @param value 布尔参数
     */
    public static native void report_TableSelectShow_SetBatchFinished(boolean value);
    /**
     * 读运行中
     * @return 返回布尔值
     */
    public static native boolean report_TableSelectShow_GetRunning();
    /**
     * 读行数
     * @return 返回整数
     */
    public static native int report_TableSelectShow_GetRowCount();
    /**
     * 读页数
     * @return 返回整数
     */
    public static native int report_TableSelectShow_GetPageCount();
    /**
     * 读分页方式
     * @return 返回整数
     */
    public static native int report_TableSelectShow_GetRowsPerPage();
    /**
     * 写分页方式
     * @param value 整数参数
     */
    public static native void report_TableSelectShow_SetRowsPerPage(int value);
    /**
     * 读当前页号
     * @return 返回整数
     */
    public static native int report_TableSelectShow_GetCurPageNo();
    /**
     * 写当前页号
     * @param value 整数参数
     */
    public static native void report_TableSelectShow_SetCurPageNo(int value);
    /**
     * 读可查找
     * @return 返回布尔值
     */
    public static native boolean report_TableSelectShow_GetSearchable();
    /**
     * 写可查找
     * @param value 布尔参数
     */
    public static native void report_TableSelectShow_SetSearchable(boolean value);
    /**
     * 读选中列名称
     * @return 返回文本
     */
    public static native String report_TableSelectShow_GetSelColumnName();
    /**
     * 写选中列名称
     * @param value 文本参数
     */
    public static native void report_TableSelectShow_SetSelColumnName(String value);
    /**
     * 读选中行号
     * @return 返回整数
     */
    public static native int report_TableSelectShow_GetSelRowNo();
    /**
     * 写选中行号
     * @param value 整数参数
     */
    public static native void report_TableSelectShow_SetSelRowNo(int value);
    /**
     * 读选中列号
     * @return 返回整数
     */
    public static native int report_TableSelectShow_GetSelColumnNo();
    /**
     * 写选中列号
     * @param value 整数参数
     */
    public static native void report_TableSelectShow_SetSelColumnNo(int value);
    /**
     * 读选中背景色
     * @return 返回整数
     */
    public static native int report_TableSelectShow_GetSelectionBackColor();
    /**
     * 写选中背景色
     * @param value 整数参数
     */
    public static native void report_TableSelectShow_SetSelectionBackColor(int value);
    /**
     * 读选中前景色
     * @return 返回整数
     */
    public static native int report_TableSelectShow_GetSelectionForeColor();
    /**
     * 写选中前景色
     * @param value 整数参数
     */
    public static native void report_TableSelectShow_SetSelectionForeColor(int value);
    /**
     * 读选中行跟随滚动
     * @return 返回布尔值
     */
    public static native boolean report_TableSelectShow_GetSelectionFollowScroll();
    /**
     * 写选中行跟随滚动
     * @param value 布尔参数
     */
    public static native void report_TableSelectShow_SetSelectionFollowScroll(boolean value);
    /**
     * 读显示报表头
     * @return 返回布尔值
     */
    public static native boolean report_TableSelectShow_GetShowHeader();
    /**
     * 写显示报表头
     * @param value 布尔参数
     */
    public static native void report_TableSelectShow_SetShowHeader(boolean value);
    /**
     * 读显示报表尾
     * @return 返回布尔值
     */
    public static native boolean report_TableSelectShow_GetShowFooter();
    /**
     * 写显示报表尾
     * @param value 布尔参数
     */
    public static native void report_TableSelectShow_SetShowFooter(boolean value);
    /**
     * 读显示页面分隔线
     * @return 返回布尔值
     */
    public static native boolean report_TableSelectShow_GetShowPreviewLine();
    /**
     * 写显示页面分隔线
     * @param value 布尔参数
     */
    public static native void report_TableSelectShow_SetShowPreviewLine(boolean value);
    /**
     * 读应用当前列布局
     * @return 返回布尔值
     */
    public static native boolean report_TableSelectShow_GetToPostColumnLayout();
    /**
     * 写应用当前列布局
     * @param value 布尔参数
     */
    public static native void report_TableSelectShow_SetToPostColumnLayout(boolean value);
    /**
     * 启动
     */
    public static native void report_TableSelectShow_Start();
    /**
     * 停止
     */
    public static native void report_TableSelectShow_Stop();
    /**
     * 刷新
     */
    public static native void report_TableSelectShow_Refresh();
    /**
     * 快速刷新
     */
    public static native void report_TableSelectShow_QuickRefresh();
    /**
     * 获取选项值
     * @param value 整数参数
     * @return 返回布尔值
     */
    public static native boolean report_TableSelectShow_GetOptionValue(int value);
    /**
     * 设置选项值
     * @param value 整数参数
     * @param value2 整数参数
     */
    public static native void report_TableSelectShow_SetOptionValue(int value, int value2);
    /**
     * 执行默认工具栏任务
     * @param value 布尔参数
     */
    public static native void report_TableSelectShow_DefaultToolbarCommand(boolean value);
    /**
     * 增加工具栏按钮
     * @param value 整数参数
     */
    public static native void report_TableSelectShow_AddToolbarControl(int value);
    /**
     * 增加工具栏自定义按钮
     * @param ButtonID 整数参数
     * @param GraphFileText 文本参数
     * @param value 文本参数
     */
    public static native void report_TableSelectShow_AddToolbarCustomButton(int ButtonID, String GraphFileText, String value);
    /**
     * 删除工具栏按钮
     * @param value 整数参数
     */
    public static native void report_TableSelectShow_RemoveToolbarControl(int value);
    /**
     * 清空工具栏按钮
     */
    public static native void report_TableSelectShow_ResetToolbarContent();
    /**
     * 更新工具栏
     */
    public static native void report_TableSelectShow_UpdateToolbar();
    /**
     * 复位工具栏按钮
     */
    public static native void report_TableSelectShow_ResetToolbar();
    /**
     * 执行工具栏任务
     * @param value 整数参数
     */
    public static native void report_TableSelectShow_DoToolbarCommand(int value);
    /**
     * 首页
     */
    public static native void report_TableSelectShow_FirstPage();
    /**
     * 下一页
     */
    public static native void report_TableSelectShow_NextPage();
    /**
     * 上一页
     */
    public static native void report_TableSelectShow_PriorPage();
    /**
     * 尾页
     */
    public static native void report_TableSelectShow_LastPage();
    /**
     * 获取列可见性
     * @param ColumnNameText 文本参数
     * @return 返回布尔值
     */
    public static native boolean report_TableSelectShow_GetColumnVisible(String ColumnNameText);
    /**
     * 设置列可见性
     * @param ColumnNameText 文本参数
     * @param value 布尔参数
     */
    public static native void report_TableSelectShow_SetColumnVisible(String ColumnNameText, boolean value);
    /**
     * 按位置获取部件框
     * @param value 整数参数
     * @param value2 整数参数
     * @return 返回整数
     */
    public static native int report_TableSelectShow_GetControlFromPos(int value, int value2);
    /**
     * 按位置获取报表节
     * @param value 整数参数
     * @param value2 整数参数
     * @return 返回整数
     */
    public static native int report_TableSelectShow_GetSectionFromPos(int value, int value2);
    /**
     * 按位置获取标题格
     * @param value 整数参数
     * @param value2 整数参数
     * @return 返回整数
     */
    public static native int report_TableSelectShow_GetTitleCellFromPos(int value, int value2);
    /**
     * 按位置获取内容格
     * @param value 整数参数
     * @param value2 整数参数
     * @return 返回整数
     */
    public static native int report_TableSelectShow_GetContentCellFromPos(int value, int value2);
    /**
     * 取选中行单元格文本
     * @param ColumnIndex 整数参数
     * @return 返回文本
     */
    public static native String report_TableSelectShow_GetSelRowCellText(int ColumnIndex);
    /**
     * 自动调整列宽适应
     */
    public static native void report_TableSelectShow_ResizeColumnToFit();
    /**
     * 自动调整列宽适应页面
     */
    public static native void report_TableSelectShow_ResizeColumnToFitPage();
    /**
     * 自动调整列宽适应文本
     */
    public static native void report_TableSelectShow_ResizeColumnToFitText();
    /**
     * 更新列视图
     */
    public static native void report_TableSelectShow_UpdateColumnView();
    /**
     * 更新选中格
     */
    public static native void report_TableSelectShow_UpdateSelCell();
    /**
     * 更新选中行
     */
    public static native void report_TableSelectShow_UpdateSelRow();
    /**
     * 更新部件框
     * @param ObjectPointer 整数参数
     */
    public static native void report_TableSelectShow_UpdateControl(int ObjectPointer);
    /**
     * 更新显示
     */
    public static native void report_TableSelectShow_UpdateViewer();
    /**
     * 更新锁定
     */
    public static native void report_TableSelectShow_LockUpdate();
    /**
     * 查找文字
     * @param Text 文本参数
     * @param value 布尔参数
     * @param Matching 布尔参数
     * @param Lookup 布尔参数
     * @param value2 布尔参数
     * @param DisplayDialog 布尔参数
     * @param value3 布尔参数
     * @return 返回布尔值
     */
    public static native boolean report_TableSelectShow_Search(String Text, boolean value, boolean Matching, boolean Lookup, boolean value2, boolean DisplayDialog, boolean value3);
    /**
     * 继续查找
     * @param value 布尔参数
     * @return 返回布尔值
     */
    public static native boolean report_TableSelectShow_SearchAgain(boolean value);
    /**
     * 显示列可视对话框
     */
    public static native void report_TableSelectShow_ShowColumnVisibleDlg();
    /**
     * 更新语言
     */
    public static native void report_TableSelectShow_UpdateLanguage();
    /**
     * 更新UI显示
     */
    public static native void report_TableSelectShow_UpdateUI();
    /**
     * 保存当前列布局
     */
    public static native void report_TableSelectShow_PostColumnLayout();
    /**
     * 创建组件窗口
     * @param value 整数参数
     * @param value2 整数参数
     * @param cx 整数参数
     * @param cy 整数参数
     * @param hWindow 整数参数
     * @return 返回整数
     */
    public static native int report_TablePrintShow_CreateSelectShowWindow(int value, int value2, int cx, int cy, int hWindow);
    /**
     * 调试查询显示器组件位置
     * @param value 整数参数
     * @param value2 整数参数
     * @param cx 整数参数
     * @param cy 整数参数
     */
    public static native void report_TablePrintShow_UpdateSelectShowWindowSize(int value, int value2, int cx, int cy);
    /**
     * 绑定事件
     * @param value CGRPrintViewerEventHandler
     * @return 返回布尔值
     */
    public static native boolean report_TablePrintShow_BindEvent(CGRPrintViewerEventHandler value);
    /**
     * 写置对象
     * @param COMObject 整数参数
     */
    public static native void report_TablePrintShow_SetCOMObject(int COMObject);
    /**
     * 读边框样式
     * @return 返回整数
     */
    public static native int report_TablePrintShow_GetBorderStyle();
    /**
     * 写边框样式
     * @param BorderStyle 整数参数
     */
    public static native void report_TablePrintShow_SetBorderStyle(int BorderStyle);
    /**
     * 读关联报表
     * @return 返回整数
     */
    public static native int report_TablePrintShow_GetReport();
    /**
     * 写关联报表
     * @param Report 整数参数
     */
    public static native void report_TablePrintShow_SetReport(int Report);
    /**
     * 读每行页数
     * @return 返回整数
     */
    public static native int report_TablePrintShow_GetColPages();
    /**
     * 写每行页数
     * @param ColPages 整数参数
     */
    public static native void report_TablePrintShow_SetColPages(int ColPages);
    /**
     * 读每列页数
     * @return 返回整数
     */
    public static native int report_TablePrintShow_GetRowPages();
    /**
     * 写每列页数
     * @param RowPages 整数参数
     */
    public static native void report_TablePrintShow_SetRowPages(int RowPages);
    /**
     * 读当前页号
     * @return 返回整数
     */
    public static native int report_TablePrintShow_GetCurPageNo();
    /**
     * 写当前页号
     * @param CurPageNo 整数参数
     */
    public static native void report_TablePrintShow_SetCurPageNo(int CurPageNo);
    /**
     * 读编辑模式
     * @return 返回整数
     */
    public static native int report_TablePrintShow_GetEditMode();
    /**
     * 写编辑模式
     * @param EditMode 整数参数
     */
    public static native void report_TablePrintShow_SetEditMode(int EditMode);
    /**
     * 读生成方式
     * @return 返回整数
     */
    public static native int report_TablePrintShow_GetGenerateStyle();
    /**
     * 写生成方式
     * @param GenerateStyle 整数参数
     */
    public static native void report_TablePrintShow_SetGenerateStyle(int GenerateStyle);
    /**
     * 读运行中
     * @return 返回布尔值
     */
    public static native boolean report_TablePrintShow_GetRunning();
    /**
     * 读显示书签
     * @return 返回布尔值
     */
    public static native boolean report_TablePrintShow_GetShowBookmark();
    /**
     * 写显示书签
     * @param ShowBookmark 布尔参数
     */
    public static native void report_TablePrintShow_SetShowBookmark(boolean ShowBookmark);
    /**
     * 读显示边距角线
     * @return 返回布尔值
     */
    public static native boolean report_TablePrintShow_GetShowMarginCorner();
    /**
     * 写显示边距角线
     * @param ShowMarginCorner 布尔参数
     */
    public static native void report_TablePrintShow_SetShowMarginCorner(boolean ShowMarginCorner);
    /**
     * 读显示打印对话框
     * @return 返回布尔值
     */
    public static native boolean report_TablePrintShow_GetShowPrintDlg();
    /**
     * 写显示打印对话框
     * @param ShowPrintDlg 布尔参数
     */
    public static native void report_TablePrintShow_SetShowPrintDlg(boolean ShowPrintDlg);
    /**
     * 读显示标尺
     * @return 返回布尔值
     */
    public static native boolean report_TablePrintShow_GetShowRuler();
    /**
     * 写显示标尺
     * @param ShowRuler 布尔参数
     */
    public static native void report_TablePrintShow_SetShowRuler(boolean ShowRuler);
    /**
     * 读显示工具栏
     * @return 返回布尔值
     */
    public static native boolean report_TablePrintShow_GetShowToolbar();
    /**
     * 写显示工具栏
     * @param ShowToolbar 布尔参数
     */
    public static native void report_TablePrintShow_SetShowToolbar(boolean ShowToolbar);
    /**
     * 读查看模式
     * @return 返回整数
     */
    public static native int report_TablePrintShow_GetViewMode();
    /**
     * 写查看模式
     * @param ViewMode 整数参数
     */
    public static native void report_TablePrintShow_SetViewMode(int ViewMode);
    /**
     * 读显示百分比
     * @return 返回整数
     */
    public static native int report_TablePrintShow_GetZoomPercent();
    /**
     * 写显示百分比
     * @param ZoomPercent 整数参数
     */
    public static native void report_TablePrintShow_SetZoomPercent(int ZoomPercent);
    /**
     * 启动
     */
    public static native void report_TablePrintShow_Start();
    /**
     * 停止
     */
    public static native void report_TablePrintShow_Stop();
    /**
     * 刷新
     */
    public static native void report_TablePrintShow_Refresh();
    /**
     * 快速刷新
     */
    public static native void report_TablePrintShow_QuickRefresh();
    /**
     * 写执行默认工具栏任务
     * @param DefaultToolbarCommand 布尔参数
     */
    public static native void report_TablePrintShow_SetDefaultToolbarCommand(boolean DefaultToolbarCommand);
    /**
     * 更新语言
     */
    public static native void report_TablePrintShow_UpdateLanguage();
    /**
     * 更新工具栏
     */
    public static native void report_TablePrintShow_UpdateToolbar();
    /**
     * 增加工具栏按钮
     * @param AddToolbarControl 整数参数
     */
    public static native void report_TablePrintShow_AddToolbarControl(int AddToolbarControl);
    /**
     * 增加工具栏自定义按钮
     * @param BtnID 整数参数
     * @param BtnFileName 文本参数
     * @param TooltipText 文本参数
     */
    public static native void report_TablePrintShow_AddToolbarCustomButton(int BtnID, String BtnFileName, String TooltipText);
    /**
     * 删除工具栏按钮
     * @param RemoveToolbarControl 整数参数
     */
    public static native void report_TablePrintShow_RemoveToolbarControl(int RemoveToolbarControl);
    /**
     * 清空工具栏按钮
     */
    public static native void report_TablePrintShow_ResetToolbarContent();
    /**
     * 复位工具栏按钮
     */
    public static native void report_TablePrintShow_ResetToolbar();
    /**
     * 执行工具栏任务
     * @param DoToolbarCommand 整数参数
     */
    public static native void report_TablePrintShow_DoToolbarCommand(int DoToolbarCommand);
    /**
     * 首页
     */
    public static native void report_TablePrintShow_FirstPage();
    /**
     * 下一页
     */
    public static native void report_TablePrintShow_NextPage();
    /**
     * 上一页
     */
    public static native void report_TablePrintShow_PriorPage();
    /**
     * 尾页
     */
    public static native void report_TablePrintShow_LastPage();
    /**
     * 加载报表文档
     * @param FileName 文本参数
     * @return 返回布尔值
     */
    public static native boolean report_TablePrintShow_LoadFromDocumentFile(String FileName);
    /**
     * 打印
     * @param ShowPrintDialog 布尔参数
     */
    public static native void report_TablePrintShow_Print(boolean ShowPrintDialog);
    /**
     * 缩放到适应高度
     */
    public static native void report_TablePrintShow_ZoomToHeight();
    /**
     * 缩放到适应宽度
     */
    public static native void report_TablePrintShow_ZoomToWidth();
    /**
     * 放大
     */
    public static native void report_TablePrintShow_ZoomIn();
    /**
     * 缩小
     */
    public static native void report_TablePrintShow_ZoomOut();
    /**
     * 缩放到完整显示
     */
    public static native void report_TablePrintShow_ZoomToFit();
    /**
     * 读获取选项值
     * @param Option 整数参数
     * @return 返回布尔值
     */
    public static native boolean report_TablePrintShow_GetOptionValue(int Option);
    /**
     * 写设置选项值
     * @param Option 整数参数
     * @param value 整数参数
     */
    public static native void report_TablePrintShow_SetOptionValue(int Option, int value);
    /**
     * 创建组件窗口
     * @param value 整数参数
     * @param value2 整数参数
     * @param cx 整数参数
     * @param cy 整数参数
     * @param hWindow 整数参数
     * @return 返回整数
     */
    public static native int report_TableDesignShow_CreateSelectShowWindow(int value, int value2, int cx, int cy, int hWindow);
    /**
     * 调试查询显示器组件位置
     * @param value 整数参数
     * @param value2 整数参数
     * @param cx 整数参数
     * @param cy 整数参数
     */
    public static native void report_TableDesignShow_UpdateSelectShowWindowSize(int value, int value2, int cx, int cy);
    /**
     * 绑定事件
     * @param value CGRDesignerEventHandler
     * @return 返回布尔值
     */
    public static native boolean report_TableDesignShow_BindEvent(CGRDesignerEventHandler value);
    /**
     * 读关联报表
     * @return 返回整数
     */
    public static native int report_TableDesignShow_GetReport();
    /**
     * 写关联报表
     * @param Report 整数参数
     */
    public static native void report_TableDesignShow_SetReport(int Report);
    /**
     * 读设计面板宽度同明细网格
     * @return 返回布尔值
     */
    public static native boolean report_TableDesignShow_GetAsDetailGridWidth();
    /**
     * 写设计面板宽度同明细网格
     * @param AsDetailGridWidth 布尔参数
     */
    public static native void report_TableDesignShow_SetAsDetailGridWidth(boolean AsDetailGridWidth);
    /**
     * 读按页面模式显示
     * @return 返回布尔值
     */
    public static native boolean report_TableDesignShow_GetAsFormUI();
    /**
     * 写按页面模式显示
     * @param AsFormUI 布尔参数
     */
    public static native void report_TableDesignShow_SetAsFormUI(boolean AsFormUI);
    /**
     * 读中文界面
     * @return 返回布尔值
     */
    public static native boolean report_TableDesignShow_GetAsNativeUI();
    /**
     * 写中文界面
     * @param AsNativeUI 布尔参数
     */
    public static native void report_TableDesignShow_SetAsNativeUI(boolean AsNativeUI);
    /**
     * 读查看报表时自动保存
     * @return 返回布尔值
     */
    public static native boolean report_TableDesignShow_GetAutoSaveOnView();
    /**
     * 写查看报表时自动保存
     * @param AutoSaveOnView 布尔参数
     */
    public static native void report_TableDesignShow_SetAutoSaveOnView(boolean AutoSaveOnView);
    /**
     * 读按默认方式执行动作
     * @return 返回布尔值
     */
    public static native boolean report_TableDesignShow_GetDefaultAction();
    /**
     * 写按默认方式执行动作
     * @param DefaultAction 布尔参数
     */
    public static native void report_TableDesignShow_SetDefaultAction(boolean DefaultAction);
    /**
     * 读部件框边框颜色
     * @return 返回整数
     */
    public static native int report_TableDesignShow_GetDesignEdgeColor();
    /**
     * 写部件框边框颜色
     * @param DesignEdgeColor 整数参数
     */
    public static native void report_TableDesignShow_SetDesignEdgeColor(int DesignEdgeColor);
    /**
     * 读栅格点颜色
     * @return 返回整数
     */
    public static native int report_TableDesignShow_GetDesignGridColor();
    /**
     * 写栅格点颜色
     * @param DesignGridColor 整数参数
     */
    public static native void report_TableDesignShow_SetDesignGridColor(int DesignGridColor);
    /**
     * 读设计中子报表名称
     * @return 返回文本
     */
    public static native String report_TableDesignShow_GetDesigningSubReportName();
    /**
     * 写设计中子报表名称
     * @param DesigningSubReportName 文本参数
     */
    public static native void report_TableDesignShow_SetDesigningSubReportName(String DesigningSubReportName);
    /**
     * 读改写标志
     * @return 返回布尔值
     */
    public static native boolean report_TableDesignShow_GetDirty();
    /**
     * 写改写标志
     * @param Dirty 布尔参数
     */
    public static native void report_TableDesignShow_SetDirty(boolean Dirty);
    /**
     * 读允许即时编辑
     * @return 返回布尔值
     */
    public static native boolean report_TableDesignShow_GetEnableQuickEdit();
    /**
     * 写允许即时编辑
     * @param EnableQuickEdit 布尔参数
     */
    public static native void report_TableDesignShow_SetEnableQuickEdit(boolean EnableQuickEdit);
    /**
     * 读是否空载
     * @return 返回布尔值
     */
    public static native boolean report_TableDesignShow_GetIsBlank();
    /**
     * 读是否插入部件状态
     * @return 返回布尔值
     */
    public static native boolean report_TableDesignShow_GetIsInsertingControl();
    /**
     * 读只允许改变布局
     * @return 返回布尔值
     */
    public static native boolean report_TableDesignShow_GetOnlyLayout();
    /**
     * 写只允许改变布局
     * @param OnlyLayout 布尔参数
     */
    public static native void report_TableDesignShow_SetOnlyLayout(boolean OnlyLayout);
    /**
     * 读选项
     * @return 返回整数
     */
    public static native int report_TableDesignShow_GetOptions();
    /**
     * 写选项
     * @param Options 整数参数
     */
    public static native void report_TableDesignShow_SetOptions(int Options);
    /**
     * 读属性项
     * @return 返回整数
     */
    public static native int report_TableDesignShow_GetPublished();
    /**
     * 写属性项
     * @param Published 整数参数
     */
    public static native void report_TableDesignShow_SetPublished(int Published);
    /**
     * 读显示对象浏览器区
     * @return 返回布尔值
     */
    public static native boolean report_TableDesignShow_GetShowExplorer();
    /**
     * 写显示对象浏览器区
     * @param ShowExplorer 布尔参数
     */
    public static native void report_TableDesignShow_SetShowExplorer(boolean ShowExplorer);
    /**
     * 读显示属性设置区
     * @return 返回布尔值
     */
    public static native boolean report_TableDesignShow_GetShowInspector();
    /**
     * 写显示属性设置区
     * @param ShowInspector 布尔参数
     */
    public static native void report_TableDesignShow_SetShowInspector(boolean ShowInspector);
    /**
     * 读显示工具栏
     * @return 返回布尔值
     */
    public static native boolean report_TableDesignShow_GetShowToolbar();
    /**
     * 写显示工具栏
     * @param ShowToolbar 布尔参数
     */
    public static native void report_TableDesignShow_SetShowToolbar(boolean ShowToolbar);
    /**
     * 读显示浮动节
     * @return 返回布尔值
     */
    public static native boolean report_TableDesignShow_GetViewFloatSection();
    /**
     * 写显示浮动节
     * @param ViewFloatSection 布尔参数
     */
    public static native void report_TableDesignShow_SetViewFloatSection(boolean ViewFloatSection);
    /**
     * 读当前视图
     * @return 返回整数
     */
    public static native int report_TableDesignShow_GetViewMode();
    /**
     * 写当前视图
     * @param ViewMode 整数参数
     */
    public static native void report_TableDesignShow_SetViewMode(int ViewMode);
    /**
     * 读视图组合模式
     * @return 返回整数
     */
    public static native int report_TableDesignShow_GetViewStyle();
    /**
     * 写视图组合模式
     * @param ViewStyle 整数参数
     */
    public static native void report_TableDesignShow_SetViewStyle(int ViewStyle);
    /**
     * 读窗口排列方式
     * @return 返回整数
     */
    public static native int report_TableDesignShow_GetWindowLayout();
    /**
     * 写窗口排列方式
     * @param WindowLayout 整数参数
     */
    public static native void report_TableDesignShow_SetWindowLayout(int WindowLayout);
    /**
     * 读缩放比例
     * @return 返回整数
     */
    public static native int report_TableDesignShow_GetZoomPercent();
    /**
     * 写缩放比例
     * @param ZoomPercent 整数参数
     */
    public static native void report_TableDesignShow_SetZoomPercent(int ZoomPercent);
    /**
     * 提交
     */
    public static native void report_TableDesignShow_Post();
    /**
     * 重新载入
     */
    public static native void report_TableDesignShow_Reload();
    /**
     * 读获取查询显示器
     * @return 返回整数
     */
    public static native int report_TableDesignShow_GetDisplayViewer();
    /**
     * 读获取打印显示器
     * @return 返回整数
     */
    public static native int report_TableDesignShow_GetPrintViewer();
    /**
     * 准备插入部件框
     * @param ControlType 整数参数
     */
    public static native void report_TableDesignShow_PrepareInsertControl(int ControlType);
    /**
     * 取消插入部件框
     */
    public static native void report_TableDesignShow_CancelInsertControl();
    /**
     * 开启全部选项
     */
    public static native void report_TableDesignShow_EnableAllOptions();
    /**
     * 禁止全部选项
     */
    public static native void report_TableDesignShow_DisableAllOptions();
    /**
     * 公开所有属性项
     */
    public static native void report_TableDesignShow_EnableAllPublished();
    /**
     * 隐藏所有属性项
     */
    public static native void report_TableDesignShow_DisableAllPublished();
    /**
     * 进入子报表设计
     * @param SubReportName 文本参数
     */
    public static native void report_TableDesignShow_EnterSubReport(String SubReportName);
    /**
     * 选中对象数量
     * @return 返回整数
     */
    public static native int report_TableDesignShow_GetObjectCount();
    /**
     * 选中对象类型
     * @return 返回整数
     */
    public static native int report_TableDesignShow_GetObjectType();
    /**
     * 选中对象字体名称
     * @return 返回文本
     */
    public static native String report_TableDesignShow_GetObjectFontName();
    /**
     * 选中对象字体字号
     * @return 返回整数
     */
    public static native int report_TableDesignShow_GetObjectFontPoint();
    /**
     * 选中对象字体粗体
     * @return 返回布尔值
     */
    public static native boolean report_TableDesignShow_GetObjectFontBold();
    /**
     * 选中对象字体斜体
     * @return 返回布尔值
     */
    public static native boolean report_TableDesignShow_GetObjectFontItalic();
    /**
     * 选中对象字体下划线
     * @return 返回布尔值
     */
    public static native boolean report_TableDesignShow_GetObjectFontUnderline();
    /**
     * 选中对象字体删除线
     * @return 返回布尔值
     */
    public static native boolean report_TableDesignShow_GetObjectFontStrikethrough();
    /**
     * 选中对象可否设置字体
     * @return 返回布尔值
     */
    public static native boolean report_TableDesignShow_GetIsSetObjectFontEnabled();
    /**
     * 选中对象可否设置背景色
     * @return 返回布尔值
     */
    public static native boolean report_TableDesignShow_GetIsSetObjectBackColorEnabled();
    /**
     * 选中对象可否设置前景色
     * @return 返回布尔值
     */
    public static native boolean report_TableDesignShow_GetIsSetObjectForeColorEnabled();
    /**
     * 选中对象名称
     * @param Index 整数参数
     * @return 返回文本
     */
    public static native String report_TableDesignShow_GetObjectName(int Index);
    /**
     * 选中对象文字对齐
     * @return 返回整数
     */
    public static native int report_TableDesignShow_GetObjectTextAlign();
    /**
     * 选中对象文字方向
     * @return 返回整数
     */
    public static native int report_TableDesignShow_GetObjectTextOrientation();
    /**
     * 取某选项状态
     * @param Option 整数参数
     * @return 返回布尔值
     */
    public static native boolean report_TableDesignShow_GetOptionValue(int Option);
    /**
     * 置某选项状态
     * @param Option 整数参数
     * @param value 布尔参数
     */
    public static native void report_TableDesignShow_SetOptionValue(int Option, boolean value);
    /**
     * 取某属性项状态
     * @param Published 整数参数
     * @return 返回布尔值
     */
    public static native boolean report_TableDesignShow_GetPublishedValue(int Published);
    /**
     * 置某属性项状态
     * @param Published 整数参数
     * @param value 布尔参数
     */
    public static native void report_TableDesignShow_SetPublishedValue(int Published, boolean value);
    /**
     * 动作可否可以执行
     * @param Action 整数参数
     * @return 返回布尔值
     */
    public static native boolean report_TableDesignShow_IsActionEnabled(int Action);
    /**
     * 当前可否插入部件框
     * @return 返回布尔值
     */
    public static native boolean report_TableDesignShow_IsInsertControlEnabled();
    /**
     * 执行动作
     * @param Action 整数参数
     */
    public static native void report_TableDesignShow_DoAction(int Action);
    /**
     * 显示类属性
     * @param ClassName 整数参数
     * @param IsShow 布尔参数
     */
    public static native void report_TableDesignShow_ShowClassProperty(int ClassName, boolean IsShow);
    /**
     * 显示类某属性
     * @param ClassName 整数参数
     * @param PropertyName 文本参数
     * @param IsShow 布尔参数
     */
    public static native void report_TableDesignShow_ShowOneProperty(int ClassName, String PropertyName, boolean IsShow);
    /**
     * 显示类某属性组
     * @param ClassName 文本参数
     * @param PropertyGroup 文本参数
     * @param IsShow 布尔参数
     */
    public static native void report_TableDesignShow_ShowOnePropertyGroup(String ClassName, String PropertyGroup, boolean IsShow);
    /**
     * 更新语言
     */
    public static native void report_TableDesignShow_UpdateLanguage();
    /**
     * 更新UI显示
     */
    public static native void report_TableDesignShow_UpdateUI();

















}
