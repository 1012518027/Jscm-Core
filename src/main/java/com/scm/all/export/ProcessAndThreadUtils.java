package com.scm.all.export;

import com.scm.all.pfunc.ModuleCallBack;
import com.scm.all.pfunc.NtQueryAllProcessInfoCallBackProcess;
import com.scm.all.pfunc.NtQueryAllProcessInfoCallBackThread;
import com.scm.all.pfunc.TagThreadInfo;
import com.scm.all.struct.*;
import org.apache.commons.lang3.StringUtils;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.scm.all.export.ModuleOperationUtilsJNI.systemIs32;
import static com.scm.all.export.ModuleOperationUtilsJNI.systemIs64;

/**
 * 进程线程_操作类
 */
public class ProcessAndThreadUtils {
    /**
     * 枚举进程信息
     * @return  返回集合
     */
    public static List<TagProcessenTry32>  processEnumInfo(){
        boolean flag;
        List<TagProcessenTry32> temp = new ArrayList<>();
        TagProcessenTry32 tagProcessenTry32 = new TagProcessenTry32();
        int hSnapshot = ModuleOperationUtilsJNI.CreateToolhelp32Snapshot(15,0);
        if(hSnapshot ==-1){
            return null;
        }
        flag =  ModuleOperationUtilsJNI.Process32FirstW(hSnapshot,tagProcessenTry32);
        while (flag){
            if( systemIs64()){
                String path = tagProcessenTry32.szExeFile;
                tagProcessenTry32.szExeFile = path.trim();
            }
            if(systemIs32()){
                String path = tagProcessenTry32.szExeFile;
                tagProcessenTry32.szExeFile = path.trim();
            }
            temp.add(tagProcessenTry32);
            tagProcessenTry32 = new TagProcessenTry32();
            flag = ModuleOperationUtilsJNI.Process32NextW(hSnapshot,tagProcessenTry32);
        }
        ModuleOperationUtilsJNI.CloseHandle(hSnapshot);
        return temp;
    }
    /**
     * 纯 Java 原生 API 获取当前进程 PID（无依赖、跨平台）
     * @return PID 字符串（非空，JVM 启动后必存在）
     */
    public static int getCurrentProcessPID() {
        // 核心原理：通过 ManagementFactory 获取运行时信息，格式为 "pid@hostname"
        String runtimeName = ManagementFactory.getRuntimeMXBean().getName();

        // 正则提取 PID（截取 @ 符号前的数字部分）
        Pattern pidPattern = Pattern.compile("(\\d+)@.*");
        Matcher matcher = pidPattern.matcher(runtimeName);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }

        // 极端情况（理论上不会触发）：通过系统属性兜底
        String pid = System.getProperty("java.vm.processid");
        return Integer.parseInt(pid != null ? pid : "unknown");
    }
    /**
     * 进程PID取进程名
     * @param th32ProcessID 进程ID
     * @return 返回进程名称
     */
    public static String pidGetProcessName(int th32ProcessID){
        for(TagProcessenTry32 tagProcessenTry32: processEnumInfo()){
            if(tagProcessenTry32.th32ProcessID==th32ProcessID){
                return tagProcessenTry32.szExeFile;
            }
        }
        return "";
    }
    /**
     * 进程名取PID
     * @param ProcessName 进程名称
     * @return 返回进程ID
     */
    public static int processNameGetPid(String ProcessName){
        for(TagProcessenTry32 tagProcessenTry32: processEnumInfo()){
            if(StringUtils.equals(tagProcessenTry32.szExeFile.toUpperCase().trim(),ProcessName.toUpperCase().trim())){
                return tagProcessenTry32.th32ProcessID;
            }
        }
        return -1;
    }
    /**
     * 打开进程
     * @param dwProcessId 进程PID
     * @return 返回进程句柄
     */
    public static int openProcess(int dwProcessId){
        int ret = ModuleOperationUtilsJNI.OpenProcess(2035711,0,dwProcessId);
        return ret;
    }
    /**
     * 关闭进程句柄
     * @param hObject 句柄
     * @return 返回逻辑值
     */
    public static long closeHandle(int hObject){
        return ModuleOperationUtilsJNI.CloseHandle(hObject);
    }
    /**
     * 打开当前进程句柄
     * @return 返回值是当前进程的伪句柄。 -1
     */
    public static int getCurrentProcess (){
        return ModuleOperationUtilsJNI.GetCurrentProcess();
    }
    /**
     * 结束进程
     * @param dwProcessId 进程PID
     * @return 返回逻辑值
     */
    public static int overProcess(int dwProcessId){
        if(dwProcessId ==0){
            dwProcessId = ModuleOperationUtilsJNI.GetCurrentProcess();
        }
        int openHandle = ModuleOperationUtilsJNI.OpenProcess(1,0,dwProcessId);
        int ret = ModuleOperationUtilsJNI.TerminateProcess(openHandle,0);
        return ret;
    }
    /**
     * 进程名是否存在
     * @param ProcessName 进程名称
     * @return 返回逻辑值
     */
    public static boolean processNameIsTrue(String ProcessName){
        for(TagProcessenTry32 tagProcessenTry32: processEnumInfo()){
            if(StringUtils.equals(tagProcessenTry32.szExeFile.toUpperCase().trim(),ProcessName.toUpperCase().trim())){
                return true;
            }
        }
        return false;
    }
    /**
     * 进程名取窗口句柄
     * @param ProcessName 进程名
     * @return 返回句柄
     */
    public static int processNameGetWindowHandle(String ProcessName){
        int tempHandle = ModuleOperationUtilsJNI.GetWindow(ModuleOperationUtilsJNI.GetDesktopWindow(), 5);
        TagMyWindwInfo myWindowInfo = new TagMyWindwInfo();
        while (tempHandle !=0){
            if(ModuleOperationUtilsJNI.IsWindowVisible(tempHandle)==1){
                myWindowInfo.th32ThreadID = ModuleOperationUtilsJNI.GetWindowThreadId(tempHandle);
                myWindowInfo.th32ProcessID = ModuleOperationUtilsJNI.GetWindowProcessId(tempHandle);
                if(processNameGetPid(ProcessName)==myWindowInfo.th32ProcessID){
                    myWindowInfo.tempHander = tempHandle;
                    return myWindowInfo.tempHander;
                }
            }
            tempHandle = ModuleOperationUtilsJNI.GetWindow(tempHandle,2);
        }
        return myWindowInfo.tempHander;
    }

    /**
     * 进程名取线程ID
     * @param ProcessName 进程名
     * @return 返回句柄
     */
    public static int processNameGetThreadID(String ProcessName){
        int tempHandle = ModuleOperationUtilsJNI.GetWindow(ModuleOperationUtilsJNI.GetDesktopWindow(), 5);
        TagMyWindwInfo myWindowInfo = new TagMyWindwInfo();
        while (tempHandle !=0){
            if(ModuleOperationUtilsJNI.IsWindowVisible(tempHandle)==1){
                myWindowInfo.th32ThreadID = ModuleOperationUtilsJNI.GetWindowThreadId(tempHandle);
                myWindowInfo.th32ProcessID = ModuleOperationUtilsJNI.GetWindowProcessId(tempHandle);
                if(processNameGetPid(ProcessName)==myWindowInfo.th32ProcessID){
                    myWindowInfo.tempHander = tempHandle;
                    return myWindowInfo.th32ThreadID;
                }
            }
            tempHandle = ModuleOperationUtilsJNI.GetWindow(tempHandle,2);
        }
        return myWindowInfo.th32ThreadID;
    }
    /**
     * @return 取自身进程ID
     */
    public static int getCurrentProcessId(){
        return ModuleOperationUtilsJNI.GetCurrentProcessId();
    }
    /**
     * 进程名取父进程ID
     * @param ProcessName 进程名称
     * @return 返回父进程ID
     */
    public static int processNameGetParentProcessID(String ProcessName){
        for(TagProcessenTry32 tagProcessenTry32: processEnumInfo()){
            if(StringUtils.equals(tagProcessenTry32.szExeFile.toUpperCase().trim(),ProcessName.toUpperCase().trim())){
                return tagProcessenTry32.th32ParentProcessID;
            }
        }
        return -1;
    }
    /**
     * 暂停进程
     * @param ProcessName 进程名称
     * @param flag 真为暂停  假为释放
     * @return 返回逻辑
     */
    public static boolean processSuspended(String ProcessName,boolean flag){
        int hprocess = openProcess(processNameGetPid(ProcessName));
        if(flag){
            ModuleOperationUtilsJNI.ZwSuspendProcess(hprocess);
        }else {
            ModuleOperationUtilsJNI.ZwResumeProcess(hprocess);
        }
        overProcess(hprocess);
        return true;
    }

    /**
     * 进程名取线程ID
     * @param ProcessName 进程名
     * @return 返回线程ID
     */
    public static int processNameGetThreadTid(String ProcessName){
        TagThreadEntry32 threadentry32 = new TagThreadEntry32();
        boolean flag;
        int th32ProcessID = processNameGetPid(ProcessName);
        int hSnapshot = ModuleOperationUtilsJNI.CreateToolhelp32Snapshot(4,th32ProcessID);
        if(hSnapshot == 0){
            return 0;
        }
        flag = ModuleOperationUtilsJNI.Thread32First(hSnapshot,threadentry32);
        while (flag){
            if(threadentry32.th32OwnerProcessID==th32ProcessID){
                break;
            }
            flag = ModuleOperationUtilsJNI.Thread32Next(hSnapshot,threadentry32);
        }
        ModuleOperationUtilsJNI.CloseHandle(hSnapshot);
        return threadentry32.th32ThreadID;
    }
    /**
     * 线程ID取进程名
     * @param th32ThreadID 线程ID
     * @return 返回进程名
     */
    public static String threadTidGetProcessName(int th32ThreadID){
        TagThreadEntry32 threadentry32 = new TagThreadEntry32();
        boolean flag;
        int hSnapshot = ModuleOperationUtilsJNI.CreateToolhelp32Snapshot(4,0);
        if(hSnapshot == 0){
            return "";
        }
        flag = ModuleOperationUtilsJNI.Thread32First(hSnapshot,threadentry32);
        while (flag){
            if(threadentry32.th32ThreadID==th32ThreadID){
                break;
            }
            flag = ModuleOperationUtilsJNI.Thread32Next(hSnapshot,threadentry32);
        }
        ModuleOperationUtilsJNI.CloseHandle(hSnapshot);
        return pidGetProcessName(threadentry32.th32OwnerProcessID);
    }
    /**
     * 线程ID取进程ID
     * @param th32ThreadID 线程ID
     * @return 返回进程名
     */
    public static int threadTidGetProcessPid(int th32ThreadID){
        TagThreadEntry32 threadentry32 = new TagThreadEntry32();
        boolean flag;
        int hSnapshot = ModuleOperationUtilsJNI.CreateToolhelp32Snapshot(4,0);
        if(hSnapshot == 0){
            return -1;
        }
        flag = ModuleOperationUtilsJNI.Thread32First(hSnapshot,threadentry32);
        while (flag){
            if(threadentry32.th32ThreadID==th32ThreadID){
                break;
            }
            flag = ModuleOperationUtilsJNI.Thread32Next(hSnapshot,threadentry32);
        }
        ModuleOperationUtilsJNI.CloseHandle(hSnapshot);
        return threadentry32.th32OwnerProcessID;
    }
    /**
     * 打开线程ID
     * @param dwThreadId 线程ID
     * @return 返回线程句柄
     */
    public static int openThread(int dwThreadId){
        return ModuleOperationUtilsJNI.OpenThread(2032639,dwThreadId);
    }
    /**
     * @return 返回自身线程ID
     */
    public static int getCurrentThreadId(){
        return ModuleOperationUtilsJNI.GetCurrentThreadId();
    }
    /**
     * 结束线程
     * @param dwThreadId 线程ID
     * @return 返回结果
     */
    public static int overThread(int dwThreadId){
        int threadId = openThread(dwThreadId);
        int lpExitCode = ModuleOperationUtilsJNI.GetExitCodeThread(threadId);
        int ret = ModuleOperationUtilsJNI.TerminateThread(threadId,lpExitCode);
        ModuleOperationUtilsJNI.CloseHandle(threadId);
        return ret;
    }
    /**
     * 暂停线程
     * @param dwThreadId 线程ID
     * @param flag 真为暂停  假为释放
     * @return 返回结果
     */
    public static int threadSuspended(int dwThreadId,boolean flag){
        int threadId = openThread(dwThreadId);
        if(flag){
            int ret =  ModuleOperationUtilsJNI.SuspendThread(threadId);
            closeHandle(threadId);
            return ret;
        }else {
            int ret = ModuleOperationUtilsJNI.ResumeThread(threadId);
            closeHandle(threadId);
            return ret;
        }
    }
    /**
     * elevatePrivileges 提升权限
     * @param ProcessHandle  进程句柄
     *                         备份                  启动                  关机                   调试
     * @param TokenIndex  SeBackupPrivilege   SeRestorePrivilege   SeShutdownPrivilege   SeDebugPrivilege
     * @return 返回逻辑值
     */
    public static boolean elevatePrivileges(int ProcessHandle,int TokenIndex){
        boolean result;
        String TokenName = "";
        TagTokenPrivileges token_privileges = new TagTokenPrivileges();
        TagLuid luid = new TagLuid();
        int TokenHandle = ModuleOperationUtilsJNI.OpenProcessToken(ProcessHandle,8|32);
        switch (TokenIndex){
            case 1:TokenName = "SeBackupPrivilege";break;
            case 2:TokenName = "SeRestorePrivilege";break;
            case 3:TokenName = "SeShutdownPrivilege";break;
            case 4:TokenName = "SeDebugPrivilege";break;
        }
        ModuleOperationUtilsJNI.LookupPrivilegeValueW("",TokenName,luid);
        token_privileges.PrivilegeCount = 1;
        token_privileges.LowPart = luid.LowPart;
        token_privileges.HighPart = luid.HighPart;
        token_privileges.Attributes = 2;
        result = ModuleOperationUtilsJNI.AdjustTokenPrivileges(TokenHandle,false,token_privileges);
        return result;
    }
    /**
     * 进程ID取进程模块  仅限32位使用  仅限32位JDK
     * @param th32ProcessID 进程ID
     * @param moduleCallBack 回调
     * @return 返回tagMODULEENTRY32 集合
     */
    public static  List<TagModuleenTry32> processPidToModuleX86(int th32ProcessID, ModuleCallBack moduleCallBack){
        List<TagModuleenTry32> tempList = new ArrayList<>();
        if(systemIs32()==true){
            boolean flag ;
            TagModuleenTry32 moduleentry32 = new TagModuleenTry32();
            int hSnapshot = ModuleOperationUtilsJNI.CreateToolhelp32Snapshot(8,th32ProcessID);
            if(hSnapshot ==-1){
                return tempList;
            }
            flag = ModuleOperationUtilsJNI.Module32FirstW(hSnapshot,moduleentry32);
            while (flag){
                tempList.add(moduleentry32);
                if(moduleCallBack!=null){
                    moduleCallBack.data(moduleentry32);
                }
                moduleentry32 = new TagModuleenTry32();
                flag = ModuleOperationUtilsJNI.Module32NextW(hSnapshot,moduleentry32);
            }
            closeHandle(hSnapshot);
            return tempList;
        }
        if(systemIs64()==true){
            System.err.println("请使用 processPidToModuleX64(int th32ProcessID, List<tagMODULEENTRYX64> tagMODULEENTRYX64)");
        }
        return tempList;
    }
    /**
     * 进程ID取进程模块   64位下支持 32位和64位使用  仅限64位JDK   需要jscm库
     * @param hProcess 进程句柄
     * @param moduleCallBack 回调
     * @return 返回成员数 tagMODULEENTRYX64 集合
     */
    public static  List<TagModuleenTryX86> processPidToModulex86(int hProcess, ModuleCallBack moduleCallBack){
        List<TagModuleenTryX86> temp =new ArrayList<>();
        if(systemIs32()==true){
            TagModuleenTryX86 moduleenTryX86 = new TagModuleenTryX86();
            int sizes = ModuleOperationUtilsJNI.MyEnumProcessModulesEx(hProcess);
            char[] bydat = new char[1024];
            for(int i = 0;i<sizes;i++){
                moduleenTryX86.hModule = ModuleOperationUtilsJNI.MyModuleFileNameExW(i,bydat,32);
                moduleenTryX86.szExePath = new String(bydat).trim();
                temp.add(moduleenTryX86);
                if(moduleCallBack!=null){
                    moduleCallBack.data(moduleenTryX86);
                }
                moduleenTryX86 = new TagModuleenTryX86();
            }
            return temp;
        }
        if(systemIs64()==true){
            System.err.println("请使用 processPidToModuleX64(int hProcess, ModuleCallBack moduleCallBack)");
        }
        return temp;
    }
    /**
     * 进程ID取进程模块   64位下支持 32位和64位使用  仅限64位JDK   需要jscm库
     * @param hProcess 进程句柄
     * @param moduleCallBack 回调
     * @return 返回成员数 tagMODULEENTRYX64 集合
     */
    public static  List<TagModuleenTryX64> processPidToModuleX64(int hProcess, ModuleCallBack moduleCallBack){
        List<TagModuleenTryX64> temp =new ArrayList<>();
        if(systemIs64()==true){
            TagModuleenTryX64 moduleentry64 = new TagModuleenTryX64();
            int sizes = ModuleOperationUtilsJNI.MyEnumProcessModulesEx(hProcess);
            char[] bydat = new char[1024];
            for(int i = 0;i<sizes;i++){
                moduleentry64.hModule = ModuleOperationUtilsJNI.MyModuleFileNameExW(i,bydat,64L);
                moduleentry64.szExePath = new String(bydat).trim();
                temp.add(moduleentry64);
                if(moduleCallBack!=null){
                    moduleCallBack.data(moduleentry64);
                }
                moduleentry64 = new TagModuleenTryX64();
            }
            return temp;
        }
        if(systemIs32()==true){
            System.err.println("请使用 processPidToModulex86(int hProcess, ModuleCallBack moduleCallBack)");
        }
        return temp;
    }

    /**
     * 全局枚举进程信息
     * @param callBack 回调进程线程信息数据
     * @return 返回逻辑值
     */
    public static boolean selectNtQueryAllProcessInfoWProcess(NtQueryAllProcessInfoCallBackProcess callBack){
        return ModuleOperationUtilsJNI.NtQueryAllProcessInfoW_Process(callBack);
    }



    /**
     * 全局枚举线程信息
     * @param callBack 回调进程线程信息数据
     * @return 返回逻辑值
     */
    public static boolean selectNtQueryAllProcessInfoWThread(NtQueryAllProcessInfoCallBackThread callBack){
        return ModuleOperationUtilsJNI.NtQueryAllProcessInfoW_Thread(callBack);
    }

    /**
     * 枚举线程X64
     * @param tagThreadInfo 线程枚举回调
     */
    public static void selectThreadInfo(TagThreadInfo tagThreadInfo){
        ModuleOperationUtilsJNI.GetThreadID(tagThreadInfo);
    }



}
