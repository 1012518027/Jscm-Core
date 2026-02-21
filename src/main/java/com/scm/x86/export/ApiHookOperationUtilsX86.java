package com.scm.x86.export;

import com.scm.all.export.ModuleOperationUtilsJNI;

/**
 * APIHook 该方法可以用，是因为JAVA他没有指针地址的概念，无法使用回调！
 * 先做记录后面再进行修复
 */
public class ApiHookOperationUtilsX86 {
    /**
     * 是否附加
     * @return 返回结果
     */
    public static boolean isSubjoin(){
        return ModuleOperationUtilsJNI.IsSubjoin();
    }
    /**
     * 取地址
     * @return 返回结果
     */
    public static int getAddress(){
        return ModuleOperationUtilsJNI.GetMainAddress(1);
    }
    /**
     * 暂停
     */
    public static void suspend(){
        ModuleOperationUtilsJNI.SetSuspend();
    }
    /**
     * 暂停
     */
    public static void go(){
        ModuleOperationUtilsJNI.SetContinue();
    }
    /**
     * 卸载
     */
    public static void unInstall(){
        ModuleOperationUtilsJNI.SetUnInstall();
    }

    /**
     * 安装
     * @param filename 模块名
     * @param address1 函数名
     * @param address2 回调名
     * @return 返回结果
     */
    public static boolean install(String filename,String address1,int address2){
        return ModuleOperationUtilsJNI.SetInstall(filename,address1,address2);
    }

}
