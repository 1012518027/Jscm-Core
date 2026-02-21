package com.scm.all.export.hotkey.install;


import com.scm.all.export.ModuleOperationUtilsJNI;
import org.apache.commons.lang3.StringUtils;

import static com.scm.all.export.ModuleOperationUtilsJNI.tileDir;


/**
 * 初始化驱动文件
 * 该类是所有键鼠驱动 孵化起始时间：2022年3月6日14:04:50      结束日期：2022年3月6日22:01:10
 */
public class InstallMdDriver {
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
            return ModuleOperationUtilsJNI.Install_Md_VIP(tileDir(),"md_x64");
        }else {
            return ModuleOperationUtilsJNI.Install_Md_VIP(tileDir(),"md_x86");
        }
    }

    /**
     * 卸载驱动
     * @deprecated 该方法弃用不在生效
     */
    public static void uninstallDriver(){
        ModuleOperationUtilsJNI.UnInstall_Md_VIP(tileDir());
    }

    /**
     *初始化驱动
     *
     */
    public static void driverHandle(){
        if(systemIs64()){
            ModuleOperationUtilsJNI.Load_Md_Address(tileDir(),-64);
        }else {
            ModuleOperationUtilsJNI.Load_Md_Address(tileDir(),-86);
        }
    }
    /**
     * 打开默认 VID、PID 的端口获取句柄，脚本应用程序启动后只需打开
     * 一次端口就可以
     * @param Nbr 是端口号，无论是双头盒子还是单头盒子，都是从 1 开始，依次
     * 为 2/3/4...，最大 126
     */
    public static void m_Open(int Nbr){
        ModuleOperationUtilsJNI.M_Open(Nbr);
    }

    /**
     * 打开指定 VID、PID 的单头盒子或者双头盒子的主控端获取句柄
     * @param Vid 厂商标识
     * @param Pid 产品标识
     * 单头盒子的默认 VID PID 是(0xC216, 0x0301)
     * 双头盒子主控端的默认 VID PID 是(0xC216, 0x0102)
     * 无线双头盒子主控端的默认 VID PID 是(0xC217, 0x0102)
     * 无线双头盒子被控端的默认 VID PID 是(0xC217, 0x0209)
     */
    public static void m_Open_VidPid_(int Vid, int Pid){
        ModuleOperationUtilsJNI.M_Open_VidPid_(Vid,Pid);
    }

    /**
     * 扫描本机硬件列表，打开扫描到的第一个单头或者双头盒子
     */
    public static void m_ScanAndOpen(){
        ModuleOperationUtilsJNI.M_ScanAndOpen();
    }

    /**
     * 关闭端口；在脚本应用程序退出前再关闭端口
     * @return  0: 成功；!0: 失败
     */
    public static int M_Close(){
        return ModuleOperationUtilsJNI.M_Close();
    }
}
