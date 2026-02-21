package com.scm.all.export.hotkey;


import com.scm.all.export.ModuleOperationUtilsJNI;
import com.scm.all.export.hotkey.install.InstallDDDriver;

/**
 * DD键鼠驱动
 * 孵化时间:2022年3月6日16:50:41
 */
public class DdDriver extends InstallDDDriver {
    /**
     * 鼠标点击
     * @param btnId 根据参数设定
     *
     * 1 =左键按下 ，2 =左键放开
     * 4 =右键按下 ，8 =右键放开
     * 16 =中键按下 ，32 =中键放开
     * 64 =4键按下 ，128 =4键放开
     * 256 =5键按下 ，512 =5键放开
     * @return 返回结果
     */
    public static int btn(int btnId){
        return ModuleOperationUtilsJNI.DDBtn(btnId);
    }

    /**
     *  鼠标绝对移动
     * @param x 坐标轴X
     * @param y 坐标轴Y
     * @return 返回结果
     */
    public static int mov(int x,int y){
        return ModuleOperationUtilsJNI.DDMov(x,y);
    }
    /**
     *  模拟鼠标相对移动
     * @param dx 坐标轴X
     * @param dy 坐标轴Y
     * @return 返回结果
     */
    public static int movR(int dx,int dy){
        return ModuleOperationUtilsJNI.DDMovR(dx,dy);
    }

    /**
     *   模拟鼠标滚轮
     * @param whl  1=前 , 2 = 后
     * @return 返回结果
     */
    public static int whl(int whl){
        return ModuleOperationUtilsJNI.DDWhl(whl);
    }

    /**
     *  键盘按键
     * @param ddcode 虚拟键盘码表
     * @param flag 1=按下，2=放开
     * @return 返回结果
     */
    public static int key(int ddcode,int flag){
        return ModuleOperationUtilsJNI.DDkey(ddcode,flag);
    }
    /**
     *  直接输入键盘上可见字符和空格   仅支持字符符号
     * @param str 虚拟键盘码表
     * @return 返回结果
     */
    public static int str(String str){
        return ModuleOperationUtilsJNI.DDstr(str);
    }

}
