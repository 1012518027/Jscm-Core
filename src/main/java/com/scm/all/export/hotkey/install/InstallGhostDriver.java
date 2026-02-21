package com.scm.all.export.hotkey.install;


import com.scm.all.export.ModuleOperationUtilsJNI;
import org.apache.commons.lang3.StringUtils;

import static com.scm.all.export.ModuleOperationUtilsJNI.tileDir;


/**
 * 初始化驱动文件
 * 该类是所有键鼠驱动 孵化起始时间：2022年3月6日14:04:50      结束日期：2022年3月6日22:01:10
 */
public class InstallGhostDriver {
    /**
     * @return 是 32位返回 true
     */
    private static boolean systemIs32(){
        String arch = System.getProperty("sun.arch.data.model");
        return StringUtils.equals(arch,"32");
    }
    /**
     * @return 是 64位返回 true
     */
    private static boolean systemIs64(){
        String arch = System.getProperty("sun.arch.data.model");
        return StringUtils.equals(arch,"64");
    }

    /**
     * TempPath 取Temp系统目录
     * @return  返回结果
     */
    private static String tempPath(){
        return System.getProperty("java.io.tmpdir");
    }


    /**
     * 安装驱动
     * @deprecated 该方法弃用不在生效
     * @return 返回结果
     */
    public static boolean installDriver(){
        if(systemIs64()){
            return ModuleOperationUtilsJNI.Install_Ghost_VIP(tileDir(),"ghost_x64");
        }else {
            return ModuleOperationUtilsJNI.Install_Ghost_VIP(tileDir(),"ghost_x86");
        }
    }

    /**
     * 卸载驱动
     * @deprecated 该方法弃用不在生效
     */
    public static void uninstallDriver(){
        ModuleOperationUtilsJNI.UnInstall_Ghost_VIP(tileDir());
    }

    /**
     *
     * 初始化驱动
     */
    public static void driverHandle(){
        if(systemIs64()){
            ModuleOperationUtilsJNI.Load_Ghost_Address(tileDir(),-64);
        }else {
            ModuleOperationUtilsJNI.Load_Ghost_Address(tileDir(),-86);
        }
    }


}
