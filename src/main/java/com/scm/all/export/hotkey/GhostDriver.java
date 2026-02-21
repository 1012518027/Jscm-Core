package com.scm.all.export.hotkey;


import com.scm.all.export.ModuleOperationUtilsJNI;
import com.scm.all.export.hotkey.install.InstallGhostDriver;

/**
 * 幽灵键鼠驱动
 * win7 以上部分系统无法使用64位
 */
public class GhostDriver extends InstallGhostDriver {
    /**
     * 获取设备列表
     * @return 非空表示设备序列号列表
     */
    public static String getDeviceListByModel(){
        return ModuleOperationUtilsJNI.GetDeviceListByModel();
    }

    /**
     * 选择设备
     * @param SerialNumber GetDeviceListByModel() 这个返回去掉分号
     * @return 1：表示成功
     */
    public static int selectDeviceBySerialNumber(String SerialNumber){
        return ModuleOperationUtilsJNI.SelectDeviceBySerialNumber(SerialNumber);
    }

    /**
     * 设备是否连接
     * @return 1：表示当前已连接设备
     */
    public static int isDeviceConnected(){
        return ModuleOperationUtilsJNI.IsDeviceConnected();
    }

    /**
     * 获取设备型号
     * @return 非空表示当前连接设备的型号
     */
    public static String getModel(){
        return ModuleOperationUtilsJNI.GetModel();
    }

    /**
     * 获取设备序列号
     * @return 非空表示当前连接设备的序列号
     */
    public static String getSerialNumber(){
        return ModuleOperationUtilsJNI.GetSerialNumber();
    }

    /**
     * 获取设备版本号
     * @return 非空表示当前连接设备的固件版本号
     */
    public static String getFirmwareVersion(){
        return ModuleOperationUtilsJNI.GetFirmwareVersion();
    }
    /**
     *  按下键
     * @param KeyName 键名 参考 GhostHashKeyCode.html
     * @return 1：表示按键成功
     */
    public static int pressKeyByName(String KeyName){
        return ModuleOperationUtilsJNI.PressKeyByName(KeyName);
    }
    /**
     *  按下键
     * @param KeyValue 键值 参考 GhostHashKeyCode.html
     * @return 1：表示按键成功
     */
    public static int pressKeyByValue(int KeyValue){
        return ModuleOperationUtilsJNI.PressKeyByValue(KeyValue);
    }

    /**
     * 释放键
     * @param KeyName 键值 参考 GhostHashKeyCode.html
     * @return 1：表示按键成功
     */
    public static int releaseKeyByName(String KeyName){
        return ModuleOperationUtilsJNI.ReleaseKeyByName(KeyName);
    }

    /**
     * 释放键
     * @param KeyValue 键值 参考 GhostHashKeyCode.html
     * @return 1：表示按键成功
     */
    public static int releaseKeyByValue(int KeyValue){
        return ModuleOperationUtilsJNI.ReleaseKeyByValue(KeyValue);
    }

    /**
     * 按下并释放键
     * @param KeyName 键值 参考 GhostHashKeyCode.html
     * @return 1：表示按键成功
     */
    public static int pressAndReleaseKeyByName(String KeyName){
        return ModuleOperationUtilsJNI.PressAndReleaseKeyByName(KeyName);
    }

    /**
     * 按下并释放键
     * @param KeyValue 键值 参考 GhostHashKeyCode.html
     * @return 1：表示按键成功
     */
    public static int pressAndReleaseKeyByValue(int KeyValue){
        return ModuleOperationUtilsJNI.PressAndReleaseKeyByValue(KeyValue);
    }

    /**
     * 键是否按下
     * @param KeyName 键值 参考 GhostHashKeyCode.html
     * @return 1：表示按键成功
     */
    public static int isKeyPressedByName(String KeyName){
        return ModuleOperationUtilsJNI.IsKeyPressedByName(KeyName);
    }

    /**
     * 键是否按下
     * @param KeyValue 键值 参考 GhostHashKeyCode.html
     * @return 1：表示按键成功
     */
    public static int isKeyPressedByValue(int KeyValue){
        return ModuleOperationUtilsJNI.IsKeyPressedByValue(KeyValue);
    }

    /**
     * 释放所有键盘按键
     * @return 1：表示成功
     */
    public static int releaseAllKey(){
        return ModuleOperationUtilsJNI.ReleaseAllKey();
    }

    /**
     * 输入字字符，不支持中文
     * @param Str 字符串型，要输入的字符串
     * @return 大于0：表示输入成功
     */
    public static int inputString(String Str){
        return ModuleOperationUtilsJNI.InputString(Str);
    }

    /**
     * 获取CapsLock（大写锁定）状态
     * @return 1：表示锁定（灯亮）
     */
    public static int getCapsLock(){
        return ModuleOperationUtilsJNI.GetCapsLock();
    }

    /**
     * 获取NumLock（数字键盘锁定）状态
     * @return 1：表示锁定（灯亮）
     */
    public static int getNumLock(){
        return ModuleOperationUtilsJNI.GetNumLock();
    }

    /**
     * 设置按键延时
     * @param MinDelay 整数类型，最小延时时间，单位毫秒，默认30
     * @param MaxDelay 整数类型，最大延时时间，单位毫秒，默认100
     * @return 1：表示成功
     */
    public static int setPressKeyDelay(int MinDelay,int MaxDelay){
        return ModuleOperationUtilsJNI.SetPressKeyDelay(MinDelay,MaxDelay);
    }

    /**
     * 设置输入字符串间隔时间
     * @param MinDelay 整数类型，最小延时时间，单位毫秒，默认60
     * @param MaxDelay 整数类型，最大延时时间，单位毫秒，默认200
     * @return 1：表示成功
     */
    public static int setInputStringIntervalTime(int MinDelay,int MaxDelay){
        return ModuleOperationUtilsJNI.SetInputStringIntervalTime(MinDelay,MaxDelay);
    }

    /**
     * 设置是否区分大小写
     * @param Discriminate 整数类型，1区分，0不区分，默认1
     * @return 1：表示成功
     */
    public static int setCaseSensitive(int Discriminate){
        return ModuleOperationUtilsJNI.SetCaseSensitive(Discriminate);
    }

    /**
     * 按下鼠标键
     * @param mButton 整数类型，鼠标键序号（1:左键 2:中键 3:右键）
     * @return 1：表示按键成功
     */
    public static int pressMouseButton(int mButton){
        return ModuleOperationUtilsJNI.PressMouseButton(mButton);
    }

    /**
     * 释放已按下的鼠标键
     * @param mButton 整数类型，鼠标键序号（1:左键 2:中键 3:右键）
     * @return 1：表示按键成功
     */
    public static int releaseMouseButton(int mButton){
        return ModuleOperationUtilsJNI.PressMouseButton(mButton);
    }

    /**
     * 按下并释放鼠标键
     * @param mButton 整数类型，鼠标键序号（1:左键 2:中键 3:右键）
     * @return 2：表示按键成功
     */
    public static int pressAndReleaseMouseButton(int mButton){
        return ModuleOperationUtilsJNI.PressAndReleaseMouseButton(mButton);
    }

    /**
     * 鼠标键是否按下
     * @param mButton 整数类型，鼠标键序号（1:左键 2:中键 3:右键）
     * @return 1：表示键已按下
     */
    public static int isMouseButtonPressed(int mButton){
        return ModuleOperationUtilsJNI.IsMouseButtonPressed(mButton);
    }

    /**
     * 释放所有鼠标按键
     * @return 1：表示成功
     */
    public static int releaseAllMouseButton(){
        return ModuleOperationUtilsJNI.ReleaseAllMouseButton();
    }

    /**
     * 移动鼠标到指定坐标
     * @param X 整数类型，屏幕的X坐标，取值范围为正整数
     * @param Y 整数类型，屏幕的Y坐标，取值范围为正整数
     * @return 大于0：表示移动成功
     */
    public static int moveMouseTo(int X,int Y){
        return ModuleOperationUtilsJNI.MoveMouseTo(X, Y);
    }

    /**
     * 相对移动鼠标
     * @param X 整数类型，水平移动距离，取值范围为-127~+127，正数为向右移动，负数为向左移动
     * @param Y 整数类型，垂直移动距离，取值范围为-127~+127，正数为向下移动，负数为向上移动
     * @return 1：表示移动成功
     */
    public static int moveMouseRelative(int X,int Y){
        return ModuleOperationUtilsJNI.MoveMouseRelative(X, Y);
    }

    /**
     * 移动鼠标滚轮
     * @param Z 整数类型，鼠标滚轮移动距离，取值范围为-127~+127，正数向上移动，负数向下移动
     * @return 1：表示移动成功
     */
    public static int moveMouseWheel(int Z){
        return ModuleOperationUtilsJNI.MoveMouseWheel(Z);
    }

    /**
     * 获取鼠标X坐标
     * @return 大于等于0：表示获取成功，高两字节为X值，低两字节为Y值
     */
    public static int getMouseX(){
        return ModuleOperationUtilsJNI.GetMouseX();
    }

    /**
     * 获取鼠标Y坐标
     * @return 大于等于0：表示获取成功，高两字节为X值，低两字节为Y值
     */
    public static int getMouseY(){
        return ModuleOperationUtilsJNI.GetMouseY();
    }

    /**
     * 设置鼠标位置
     * @param X 整数类型，屏幕的X坐标，取值范围为正整数
     * @param Y 整数类型，屏幕的Y坐标，取值范围为正整数
     * @return 大于0：表示设置位置成功
     */
    public static int setMousePosition(int X,int Y){
        return ModuleOperationUtilsJNI.SetMousePosition(X,Y);
    }

    /**
     * 设置鼠标按键延时
     * @param MinDelay 整数类型，最小延时时间，单位毫秒，默认30
     * @param MaxDelay 整数类型，最大延时时间，单位毫秒，默认100
     * @return 1：表示成功
     */
    public static int setPressMouseButtonDelay(int MinDelay,int MaxDelay){
        return ModuleOperationUtilsJNI.SetPressMouseButtonDelay(MinDelay,MaxDelay);
    }

    /**
     * 设置鼠标移动延时
     * @param MinDelay 整数类型，最小延时时间，单位毫秒，默认4
     * @param MaxDelay 整数类型，最大延时时间，单位毫秒，默认18
     * @return 1：表示成功
     */
    public static int setMouseMovementDelay(int MinDelay,int MaxDelay){
        return ModuleOperationUtilsJNI.SetMouseMovementDelay(MinDelay,MaxDelay);
    }

    /**
     * 设置鼠标移动速度
     * @param SpeedValue 整数类型，移动速度，取值范围1-10，其他值无效，默认7
     * @return 1：表示成功
     */
    public static int setMouseMovementSpeed(int SpeedValue){
        return ModuleOperationUtilsJNI.SetMouseMovementSpeed(SpeedValue);
    }
}
