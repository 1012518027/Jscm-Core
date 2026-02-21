package com.scm.all.export.hotkey;

import com.scm.all.export.ByteUtils;
import com.scm.all.export.ModuleOperationUtilsJNI;
import com.scm.all.export.hotkey.install.InstallMdDriver;
import com.scm.all.struct.TagPoint;

public class MdDriver extends InstallMdDriver {
    /**
     * 延时指定时间
     * @param time 单位 ms
     * @return 返回结果
     */
    public static int m_Delay(int time){
        return ModuleOperationUtilsJNI.M_Delay(time);
    }

    /**
     * 在指定的最小值和最大值之间延时随机时间
     * @param Min_time 最小延时时间
     * @param Max_time 最大延时时间 （单位：ms）
     * @return 返回结果
     */
    public static int m_DelayRandom(int Min_time, int Max_time){
        return ModuleOperationUtilsJNI.M_DelayRandom(Min_time,Max_time);
    }

    /**
     * 在最小值、最大值之间取随机数
     * @param Min_V 参考
     * @param Max_V 参考
     * @return 参考
     */
    public static int m_RandDomNbr(int Min_V, int Max_V){
        return ModuleOperationUtilsJNI.M_RandDomNbr(Min_V,Max_V);
    }

    /**
     * 获取设备序列号
     * @return 设备序列号 buf(buf 由调用该 API 的脚本应用程序分配)
     */
    public static String m_GetDevSn(){
        return ModuleOperationUtilsJNI.M_GetDevSn();
    }

    /**
     * 读取当前绝对移动功能的状态
     * @return 1:打开 0:关闭 其它：失败
     */
    public static int m_GetAbsCfg(){
        return ModuleOperationUtilsJNI.M_GetAbsCfg();
    }

    /**
     * 设置绝对移动功能
     * @param e1d0 =1 打开; =0;关闭
     * @return 0: 成功；
     */
    public static int m_SetAbsCfg(int e1d0){
        return ModuleOperationUtilsJNI.M_SetAbsCfg(e1d0);
    }

    /**
     * 检查盒子是否是可修改盒子
     * @return 0: 可修改
     */
    public static int m_ChkSupportMdy(){
        return ModuleOperationUtilsJNI.M_ChkSupportMdy();
    }

    /**
     * 设置新 VID/PID;
     * 只支持可修改的单头、双头、无线双头。普通单头、双头不支持
     * @param mVid 主控端 Vid;
     *             不能是 C216 或 C217 或 FFFF;
     *  如果是 0，表示 mVid、mPid 不需要更改; mPid 的值将被忽略。
     * @param mPid 主控端 Pid;
     *             如果 mVid=0，该参数将被忽略;
     *  不能是 0000 或 FFFF
     * @param sVid 被控端 Vid;
     *             如果是单头，该参数将被忽略.
     *  不能是 C216 或 C217 或 FFFF;
     *  如果是 0，表示 sVid、sPid 不需要更改; sPid 的值将被忽略。
     * @param sPid 被控端 Pid;
     *             如果是单头，该参数将被忽略.
     *  如果 sVid=0，该参数将被忽略;
     *  不能是 0000 或 FFFF
     * @return 0: 成功；
     * -2: 该盒子不支持修改；
     * -10: mVID 不符合规则 -11: mPID 不符合规则
     * -20: sVID 不符合规则 -21: sPID 不符合规则
     * 其他: 修改失败
     */
    public static int m_SetNewVidPid(int mVid, int mPid, int sVid, int sPid){
        return ModuleOperationUtilsJNI.M_SetNewVidPid(mVid,mPid,sVid,sPid);
    }

    /**
     * 复位盒子的 VID/PID，恢复成出厂设置
     * 只支持可修改的单头、双头、无线双头。普通单头、双头不支持
     * @return 返回结果
     */
    public static int m_ResetVidPid (){
        return ModuleOperationUtilsJNI.M_ResetVidPid();
    }

    /**
     * 读取盒子的 VID/PID
     * @param IdIndex =1/2/3/4 分别对应 主控端 Vid, 主控端 Pid, 被控端 Vid, 被控
     * 端 Pid
     * @return -1, -2, -3：失败
     * 其他返回值为 IdIndex 指定的 Vid 或 Pid
     * 请注意：如果盒子修改了 VID/PID，而盒子还没有拔掉重新插上生效，
     * 此时盒子使用的还是修改前的 VID/PID。调用该接口将返回修改后的
     * VID/PID
     */
    public static int m_GetVidPid (int IdIndex){
        return ModuleOperationUtilsJNI.M_GetVidPid(IdIndex);
    }

    /**
     * 强制盒子重启，模拟手工插拔
     * @return  小于0 (不支持该功能或执行失败)，0 执行成功。
     * 请注意：版本≥9.1 的单双头盒子能支持该接口；无线双头盒子全都支
     * 持该接口。执行完以后，不能再继续操作，必须关闭端口，重新打开
     * 端口。不是所有机器都支持该功能，尤其是当盒子映射入虚拟机后，
     * 虚拟机大部分不支持该功能。
     */
    public static int m_ReEnum(){
        return ModuleOperationUtilsJNI.M_ReEnum();
    }

    /**
     * 检查盒子是否支持修改产品名称
     * @return 1: 可修改
     * 0: 不可修改
     * 其他: 错误
     */
    public static int m_ChkSupportProdStrMdy (){
        return ModuleOperationUtilsJNI.M_ChkSupportProdStrMdy();
    }

    /**
     * 读取产品名称
     * @param m0s1 0 表示主控端；1 表示被控端
     * @return 0 成功
     * 非 0 失败或者盒子不支持产品名称
     */
    public static String m_GetProdStr(int m0s1){
        return ModuleOperationUtilsJNI.M_GetProdStr(m0s1);
    }

    /**
     * 设置产品名称
     * 单双头盒子不超过 16 个、无线双头盒子不超过 14 个 ascii 的 char 数
     * 组, 支持 unicode 编码。
     * @param m0s1 0 表示主控端；1 表示被控端
     * @param ucp_Str 产品名称
     * @return 0 成功
     * 非 0 失败
     */
    public static int m_SetProdStr(int m0s1, String ucp_Str){
        return ModuleOperationUtilsJNI.M_SetProdStr(m0s1,ucp_Str);
    }

    /**
     * DLL 内部参数恢复默认值
     * @return 结果
     */
    public static int m_InitParam(){
        return ModuleOperationUtilsJNI.M_InitParam();
    }

    /**
     * 置 DLL 内部参数
     * @param ParamType 参数类型
     * @param Param1 最小值
     * @param Param2 最大值
     * @return
     *
     * ParamType 0 Param1 Param2 单击按键，按下和弹起之间的延时值（默认值是 50,80）
     * ParamType 2 Param1 Param2 多个按键，每个按键之间的延时值（默认值是 40,80）
     * ParamType 8 Param1 Param2 单击鼠标(左中右键)，按下和弹起之间的延时值（默认值是 50,80）
     * ParamType 10 Param1 Param2 多次单击鼠标(左中右键)，每次单击之间的延时值（默认值是 500,900）
     * ParamType 12 Param1 Param2 双击鼠标(左键)，两次单击之间的延时值（默认值是60,110）
     * ParamType 14 Param1 Param2 多次双击鼠标(左键)，每次双击之间的延时值（默认值是 500,900）
     * ParamType 20 Param1 Param2 鼠标移动轨迹由多条直线组成，每条直线移动之间的延时值（默认值是 10,20）
     */
    public static int m_SetParam (int ParamType, int Param1, int Param2){
        return ModuleOperationUtilsJNI.M_SetParam(ParamType,Param1,Param2);
    }

    /**
     * 写用户数据
     * 该接口仅支持可修改 VID/PID 的单双头盒子。
     * 使用该接口可以将脚本运行密钥等信息写入盒子中，这些信息将被加
     * 密压缩后存入盒子中，这些信息不可读取，只能验证。
     * @param ucp_UserData 数据
     * @return 0 成功
     */
    public static int m_SetUserData(byte[] ucp_UserData){
        return ModuleOperationUtilsJNI.M_SetUserData(ucp_UserData.length,ucp_UserData);
    }

    /**
     * 参数说明：
     * dw_LenUserData: 数据长度(单位: 字节)，不能超过 256 字节
     * ucp_UserData: 数据
     * @param ucp_UserData 数据
     * @return 0 成功
     */
    public static int m_VerifyUserData(byte[] ucp_UserData){
        return ModuleOperationUtilsJNI.M_VerifyUserData(ucp_UserData.length,ucp_UserData);
    }

    /**
     * 单击(按下后立刻弹起)指定按键
     * @param HidKeyCode 键盘码
     * @param Nbr 按下次数
     * @return 结果
     * 注意 1：在 DLL 内部，M_KeyPress 是由 M_KeyDown + 50~80ms
     * 的随机延时 + M_KeyUp 组成。
     * 注意 2：在 DLL 内部，两次 M_KeyPress 单击之间的延时是
     * 150~600ms 随机延时。
     * 注意 3：如果需要自己控制按下和弹起之间的延时，可以在脚本应
     * 用程序里 M_KeyDown + 自定义延时 + M_KeyUp。也可以调用
     * M_SetParam 设置
     */
    public static int m_KeyPress(int HidKeyCode, int Nbr){
        return ModuleOperationUtilsJNI.M_KeyPress(HidKeyCode,Nbr);
    }

    /**
     * 按下指定按键不弹起，如果按下不弹起，可以和其他按键组成组合
     * 键
     * @param HidKeyCode 键盘码
     * @return 结果
     */
    public static int m_KeyDown(int HidKeyCode){
        return ModuleOperationUtilsJNI.M_KeyDown(HidKeyCode);
    }
    /**
     * 弹起指定按键
     * @param HidKeyCode 键盘码
     * @return 结果
     */
    public static int m_KeyUp(int HidKeyCode){
        return ModuleOperationUtilsJNI.M_KeyUp(HidKeyCode);
    }

    /**
     * 读取按键状态；返回值：0=弹起状态；1:=按下状态
     * 使用该接口，不允许手工操作键盘，否则该接口返回值有可能不正
     * 确
     * @param HidKeyCode 键盘码
     * @return 结果
     */
    public static int m_KeyState(int HidKeyCode){
        return ModuleOperationUtilsJNI.M_KeyState(HidKeyCode);
    }

    /**
     * 单击(按下后立刻弹起)指定按键
     * @param KeyCode Windows 标准键盘码
     * @param Nbr 按下次数
     * @return 结果
     */
    public static int m_KeyPress2(int KeyCode, int Nbr){
        return ModuleOperationUtilsJNI.M_KeyPress2(KeyCode,Nbr);
    }

    /**
     * 按下指定按键不弹起，如果按下不弹起，可以和其他按键组成组合
     * 键
     * @param KeyCode  Windows 标准键盘码
     * @return 结果
     */
    public static int m_KeyDown2(int KeyCode){
        return ModuleOperationUtilsJNI.M_KeyDown2(KeyCode);
    }

    /**
     * 弹起指定按键
     * @param KeyCode Windows 标准键盘码
     * @return 结果
     */
    public static int m_KeyUp2(int KeyCode){
        return ModuleOperationUtilsJNI.M_KeyUp2(KeyCode);
    }

    /**
     * 读取按键状态；返回值：0=弹起状态；1:=按下状态
     * @param KeyCode Windows 标准键盘码
     * @return 结果
     */
    public static int m_KeyState2(int KeyCode){
        return ModuleOperationUtilsJNI.M_KeyState2(KeyCode);
    }

    /**
     * 弹起所有按键。如果出现按键异常，也可以调用该接口恢复。
     * @return 结果
     */
    public static int m_ReleaseAllKey(){
        return ModuleOperationUtilsJNI.M_ReleaseAllKey();
    }

    /**
     * 读取小键盘 NumLock 灯的状态；返回值：0:灭；1:亮；-1: 失败
     * @return 结果
     */
    public static int m_NumLockLedState(){
        return ModuleOperationUtilsJNI.M_NumLockLedState();
    }

    /**
     * 读取 CapsLock 灯的状态；返回值：0:灭；1:亮；-1: 失败
     * @return 结果
     */
    public static int m_CapsLockLedState(){
        return ModuleOperationUtilsJNI.M_CapsLockLedState();
    }

    /**
     * 读取 ScrollLock 灯的状态；返回值：0:灭；1:亮；-1: 失败
     * @return 结果
     */
    public static int m_ScrollLockLedState(){
        return ModuleOperationUtilsJNI.M_ScrollLockLedState();
    }

    /**
     * 输入一串 ASCII 字符串，
     * @param InputStr ascii字符串  仅支持英文
     * @return 返回结果
     */
    public static int m_KeyInputString(String InputStr){
        return ModuleOperationUtilsJNI.M_KeyInputString(InputStr);
    }
    /**
     * 输入一串 GBK 字符串，
     * @param InputStr GBK字符串  仅支持英文
     * @return 返回结果
     */
    public static int m_KeyInputStringGBK( String InputStr){
        return ModuleOperationUtilsJNI.M_KeyInputStringGBK(InputStr);
    }
    /**
     * 输入一串 Unicode 字符串
     * @param InputStr Unicode字符串  仅支持英文
     * @return 返回结果
     */
    public static int m_KeyInputStringUnicode( String InputStr){
        return ModuleOperationUtilsJNI.M_KeyInputStringUnicode(InputStr);
    }
    /**
     * 左键单击(按下后立刻弹起)
     * @param Nbr 单击次数
     * @return 返回结果
     * 注意 1：在 DLL 内部，M_LeftClick 是由 M_LeftDown + 50~80ms
     * 的随机延时 + M_LeftUp 组成。
     * 如果需要自己控制按下和弹起之间的延时，可以在脚本应用程序里
     * M_LeftDown + 自定义延时 + M_LeftUp。
     * 注意 2：在 DLL内部，两次 M_LeftClick 之间的延时是 500~900ms 随
     * 机延时。
     */
    public static int m_LeftClick( int Nbr){
        return ModuleOperationUtilsJNI.M_LeftClick(Nbr);
    }

    /**
     * 左键双击
     * @param Nbr 单击次数
     * @return 返回结果
     * 注意 1：在 DLL 内部，M_LeftClick 是由 M_LeftDown + 50~80ms
     * 的随机延时 + M_LeftUp 组成。
     * 如果需要自己控制按下和弹起之间的延时，可以在脚本应用程序里
     * M_LeftDown + 自定义延时 + M_LeftUp。
     * 注意 2：在 DLL内部，两次 M_LeftClick 之间的延时是 500~900ms 随
     * 机延时。
     */
    public static int m_LeftDoubleClick( int Nbr){
        return ModuleOperationUtilsJNI.M_LeftDoubleClick(Nbr);
    }
    /**
     * 按下左键不弹起
     * @return 返回结果
     * 注意 1：在 DLL 内部，M_LeftClick 是由 M_LeftDown + 50~80ms
     * 的随机延时 + M_LeftUp 组成。
     * 如果需要自己控制按下和弹起之间的延时，可以在脚本应用程序里
     * M_LeftDown + 自定义延时 + M_LeftUp。
     * 注意 2：在 DLL内部，两次 M_LeftClick 之间的延时是 500~900ms 随
     * 机延时。
     */
    public static int m_LeftDown(){
        return ModuleOperationUtilsJNI.M_LeftDown();
    }
    /**
     * 弹起左键
     * @return 返回结果
     * 注意 1：在 DLL 内部，M_LeftClick 是由 M_LeftDown + 50~80ms
     * 的随机延时 + M_LeftUp 组成。
     * 如果需要自己控制按下和弹起之间的延时，可以在脚本应用程序里
     * M_LeftDown + 自定义延时 + M_LeftUp。
     * 注意 2：在 DLL内部，两次 M_LeftClick 之间的延时是 500~900ms 随
     * 机延时。
     */
    public static int m_LeftUp(){
        return ModuleOperationUtilsJNI.M_LeftUp();
    }

    /**
     * 右键单击(按下后立刻弹起)
     * @param Nbr 单击次数
     * @return 返回结果
     */
    public static int m_RightClick( int Nbr){
        return ModuleOperationUtilsJNI.M_RightClick(Nbr);
    }


    /**
     * 按下右键不弹起
     * @return 返回结果
     */
    public static int m_RightDown(){
        return ModuleOperationUtilsJNI.M_RightDown();
    }

    /**
     * 弹起右键
     * @return 返回结果
     */
    public static int m_RightUp(){
        return ModuleOperationUtilsJNI.M_RightUp();
    }
    /**
     * 中键单击(按下后立刻弹起)
     * @param Nbr 单击次数
     * @return 返回结果
     */
    public static int m_MiddleClick( int Nbr){
        return ModuleOperationUtilsJNI.M_MiddleClick(Nbr);
    }

    /**
     * 按下中键不弹起
     * @return 返回结果
     */
    public static int m_MiddleDown(){
        return ModuleOperationUtilsJNI.M_MiddleDown();
    }

    /**
     * 弹起中键
     * @return 返回结果
     */
    public static int m_MiddleUp(){
        return ModuleOperationUtilsJNI.M_MiddleUp();
    }
    /**
     * 弹起鼠标的所有按键（包括左键、中键、右键）
     * @return 返回结果
     */
    public static int m_ReleaseAllMouse(){
        return ModuleOperationUtilsJNI.M_ReleaseAllMouse();
    }
    /**
     * 读取鼠标左中右键状态
     * @param MouseKeyCode 1=左键 2=右键 3=中键
     * @return 返回结果
     * 注意：只能读取盒子中鼠标的状态，读取不到实体鼠标的状态
     */
    public static int m_MouseKeyState( int MouseKeyCode){
        return ModuleOperationUtilsJNI.M_MouseKeyState(MouseKeyCode);
    }
    /**
     * 滚动鼠标滚轮
     * @param Nbr 滚动量, 为正,向上滚动；为负, 向下滚动;
     * @return 返回结果
     */
    public static int m_MouseWheel(int Nbr){
        return ModuleOperationUtilsJNI.M_MouseWheel(Nbr);
    }
    /**
     * 将鼠标移动到原点(0,0)
     * @return 返回结果
     */
    public static int m_ResetMousePos(){
        return ModuleOperationUtilsJNI.M_ResetMousePos();
    }
    /**
     * 从当前位置相对移动鼠标
     * @param x x 方向（横轴）的距离（正:向右; 负值:向左）
     * @param y : y 方向（纵轴）的距离（正:向下; 负值:向上）
     * @return 返回结果
     */
    public static int m_MoveR(int x, int y){
        return ModuleOperationUtilsJNI.M_MoveR(x,y);
    }
    /**
     * 一步到位从当前位置相对移动鼠标
     * @param x  x 方向的坐标; 取值范围(-32767, 32767)
     * @param y  y 方向的坐标。取值范围(-32767, 32767)
     * @return 返回结果
     * 注意：坐标原点(0, 0)在屏幕左上角
     * 鼠标移动是一步到位，没有移动轨迹！10.3 及以上版本文盒才支持
     */
    public static int m_MoveR_D(int x, int y){
        return ModuleOperationUtilsJNI.M_MoveR_D(x,y);
    }
    /**
     * 移动鼠标到指定坐标
     * 使用该接口请注意：该函数只适合完全由脚本应用程序控制鼠标移
     * 动的应用方式。！！
     * @param x  x 方向（横轴）的坐标;
     * @param y y 方向（纵轴）的坐标
     * @return 返回结果
     *
     * 注意：坐标原点(0, 0)在屏幕左上角
     * 注意：如果出现过将鼠标移动的距离超过屏幕大小，再次
     * M_MoveTo 可能会出现无法正确移动到指定坐标的问题！如果出现
     * 该问题，需调用 M_ResetMousePos 复位
     * 这个接口在第一次调用时，会先移动到原点。以后再调用这个接口，
     * dll 会记住每次调用的距离，然后计算需要相对移动的距离。如果
     * 在脚本应用程序运行中，有手工操作鼠标，那 dll 记住的坐标就不
     * 准确了。再调用这个接口移动鼠标，移动的坐标也就不准确。
     * 1、如果只是本机使用，请使用下面的 M_MoveTo2
     * 2、强烈建议新写脚本使用 M_MoveTo3 接口，不会再有以上困扰。
     * 3、鼠标移动是模拟曲线运动。
     */
    public static int m_MoveTo(int x, int y){
        return ModuleOperationUtilsJNI.M_MoveTo(x,y);
    }
    /**
     * 一步到位移动鼠标到指定坐标
     * @param x  x 方向的坐标; 取值范围(-32767, 32767)
     * @param y y 方向的坐标。取值范围(-32767, 32767)
     * @return 返回结果
     * 注意：坐标原点(0, 0)在屏幕左上角
     * 鼠标移动是一步到位，没有移动轨迹！10.3 及以上版本文盒才支持
     */
    public static int m_MoveTo_D(int x, int y){
        return ModuleOperationUtilsJNI.M_MoveTo_D(x,y);
    }
    /**
     * 读取当前鼠标所在坐标
     * 使用该接口请注意：该函数只适合完全由脚本应用程序控制鼠标移动的应用方式。！！
     * @param m_hdl 通信句柄
     * @return 返回结果
     *注意：该函数不是通过调用 windows 的 API 接口来获取被控机的
     * 鼠 标 当 前 坐 标 ， 而 是 通 过 监 测 脚 本 应 用 程 序 调 用
     * M_MoveTo/M_MoveTo3 来实时调整和记录当前坐标值，所以如果
     * 在调用 M_MoveTo/M_MoveTo3 之外，还手工调整被控机的鼠标，
     * 那调用该函数读取到的坐标值将不准确。
     * 注 意 ： 该 函 数 必 须 在 执 行 一 次 M_MoveTo/M_MoveTo3 或
     * M_ResetMousePos 函数后才能正确执行！
     * 注意：如果曾经出现过将鼠标移动的距离超过屏幕大小，这里读取
     * 到的坐标值有可能是不正确的！如果出现该问题，需调用
     * M_ResetMousePos 复位
     * 如果只是本机使用，请使用下面的 M_GetCurrMousePos2
     */
    public static TagPoint m_GetCurrMousePos(int m_hdl){
        TagPoint point = new TagPoint();
        byte[] x = new byte[4];
        byte[] y = new byte[4];
        int result = ModuleOperationUtilsJNI.M_GetCurrMousePos(x,y);
        point.x = ByteUtils.bytesArrayToInt(x);
        point.y = ByteUtils.bytesArrayToInt(y);
        return point;
    }
    /**
     * 读取当前鼠标 X 坐标值
     * @return 返回值为当前鼠标 X 坐标值。
     * 注意：该函数功能和上述 M_GetCurrMousePos 的功能重复，只是为了方便某些不支持指针的语言使用，如按键精灵。
     */
    public static int m_GetCurrMousePosX(){
        return ModuleOperationUtilsJNI.M_GetCurrMousePosX();
    }
    /**
     * 读取当前鼠标 X 坐标值
     * @return 返回值为当前鼠标 X 坐标值。
     * 注意：该函数功能和上述 M_GetCurrMousePos 的功能重复，只是为了方便某些不支持指针的语言使用，如按键精灵。
     */
    public static int m_GetCurrMousePosY(){
        return ModuleOperationUtilsJNI.M_GetCurrMousePosY();
    }


//    注意，以下接口仅适用主控机和被控机是同一台电脑的使用方式(单头盒子；双头盒子的两
//            个 USB 头都连接到同一台电脑)
//    以下接口 DLL 将调用 windows 操作系统的 API 来获取当前鼠标位置，计算坐标偏差后，
//    通过相对移动方式移动到目的坐标。DLL 将不记录鼠标移动的位置。

    /**
     * 从当前位置相对移动鼠标 2
     * @param x x 方向（横轴）的距离（正:向右; 负值:向左）
     * @param y  y 方向（纵轴）的距离（正:向下; 负值:向上）
     * @return 返回结果
     */
    public static int m_MoveR2(int x, int y){
        return ModuleOperationUtilsJNI.M_MoveR2(x,y);
    }
    /**
     * 一步到位从当前位置相对移动鼠标 2
     * @param x  x 方向的坐标; 取值范围(-32767, 32767)
     * @param y   y 方向的坐标。取值范围(-32767, 32767)
     * @return 返回结果
     * 注意：坐标原点(0, 0)在屏幕左上角
     * 鼠标移动是一步到位，没有移动轨迹！10.3 及以上版本文盒才支持
     */
    public static int m_MoveR2_D(int x, int y){
        return ModuleOperationUtilsJNI.M_MoveR2_D(x,y);
    }
    /**
     * 移动鼠标到指定坐标 2
     * @param x  x 方向（横轴）的坐标;
     * @param y   y 方向（横轴）的坐标;
     * @return 返回结果
     * 注意：坐标原点(0, 0)在屏幕左上角
     * 鼠标移动是模拟曲线运动。移动方法请看下述的“鼠标几个移动接
     * 口比较”
     */
    public static int m_MoveTo2(int x, int y){
        return ModuleOperationUtilsJNI.M_MoveTo2(x,y);
    }
    /**
     * 一步到位从当前位置相对移动鼠标 2
     * @param x  x 方向的坐标; 取值范围(-32767, 32767)
     * @param y   y 方向的坐标。取值范围(-32767, 32767)
     * @return 返回结果
     * 注意：坐标原点(0, 0)在屏幕左上角
     * 鼠标移动是一步到位，没有移动轨迹！10.3 及以上版本文盒才支持
     */
    public static int m_MoveTo2_D(int x, int y){
        return ModuleOperationUtilsJNI.M_MoveTo2_D(x,y);
    }
    /**
     * 读取当前鼠标所在坐标
     * @return 返回结果 tagPOINT
     * 该接口实际上是直接调用系统的 GetCursorPos ()
     * 脚本应用程序可以不用该接口，直接用系统的 GetCursorPos ()
     */
    public static TagPoint m_GetCurrMousePos2(){
        TagPoint point = new TagPoint();
        byte[] x = new byte[4];
        byte[] y = new byte[4];
        int result = ModuleOperationUtilsJNI.M_GetCurrMousePos2(x,y);
        point.x = ByteUtils.bytesArrayToInt(x);
        point.y = ByteUtils.bytesArrayToInt(y);
        return point;
    }
    /**
     * 输入被控机的屏幕分辨率
     * 返回值如果是-10，表示该盒子不支持绝对移动功能。返回 0 表示
     * 执行正确。可以用该接口判断盒子是否支持绝对移动功能
     * @param x 方向
     * @param y 方向
     * @return 返回结果 比如：屏幕分辨率是 1024×768，那么 x=1024，y=768
     */
    public static int m_ResolutionUsed(int x, int y){
        return ModuleOperationUtilsJNI.M_ResolutionUsed(x,y);
    }
    /**
     * 移动鼠标到指定坐标 3
     * @param x  x 方向（横轴）的坐标;
     * @param y   y 方向（横轴）的坐标;
     * @return 返回结果
     * 注意：坐标原点(0, 0)在屏幕左上角
     * 鼠标移动是模拟曲线运动。移动方法请看下述的“鼠标几个移动接
     * 口比较”
     */
    public static int m_MoveTo3(int x, int y){
        return ModuleOperationUtilsJNI.M_MoveTo3(x,y);
    }
    /**
     * 一步到位从当前位置相对移动鼠标 3
     * @param x  x 方向的坐标; 取值范围(-32767, 32767)
     * @param y   y 方向的坐标。取值范围(-32767, 32767)
     * @return 返回结果
     * 注意：坐标原点(0, 0)在屏幕左上角
     * 鼠标移动是一步到位，没有移动轨迹！10.3 及以上版本文盒才支持
     */
    public static int m_MoveTo3_D(int x, int y){
        return ModuleOperationUtilsJNI.M_MoveTo3_D(x,y);
    }

}
