package com.scm.x64.export;


import com.scm.all.export.ByteUtils;
import com.scm.all.export.ModuleOperationUtilsJNI;
import com.scm.all.struct.TagRegisTryGroupInfo;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 注册表操作工具类
 * Ps:本类操作系统注册表
 */
public class RegistryOperationUtilsX64 {
    /**
     * 打开注册表
     * RegistryGroupIsKeyValue("HKEY_LOCAL_MACHINE\\SOFTWARE\\Intel\\Bluetooth\\Metrics\\","Statistics");
     * @param registryGroup 注册表类   HKEY_LOCAL_MACHINE\SOFTWARE\Intel\Bluetooth\Metrics
     * @param IsCreate 是否创建注册表
     * @return 返回句柄
     */
    public static long openRegistryGroup(String registryGroup,boolean IsCreate){
        int rootHandle = -1;
        long rck = 0;
       String registryGroupValue = registryGroup.toUpperCase();
       if(registryGroupValue.length() > 0){
           int RegistryGroupValueIndex = StringUtils.indexOf(registryGroupValue,"\\",0);
           if(RegistryGroupValueIndex != -1){
               String RegistryGroupValueIndexLeft = StringUtils.substring(registryGroup,0,RegistryGroupValueIndex);
               if(StringUtils.equals(RegistryGroupValueIndexLeft,"HKEY_CLASSES_ROOT")){
                   rootHandle = 0;
               }
               if(StringUtils.equals(RegistryGroupValueIndexLeft,"HKEY_CURRENT_USER")){
                   rootHandle = 1;
               }
               if(StringUtils.equals(RegistryGroupValueIndexLeft,"HKEY_LOCAL_MACHINE")){
                   rootHandle = 2;
               }
               if(StringUtils.equals(RegistryGroupValueIndexLeft,"HKEY_USERS")){
                   rootHandle = 3;
               }
               if(StringUtils.equals(RegistryGroupValueIndexLeft,"HKEY_PERFORMANCE_DATA")){
                   rootHandle = 4;
               }
               if(StringUtils.equals(RegistryGroupValueIndexLeft,"HKEY_PERFORMANCE_TEXT")){
                   rootHandle = 5;
               }
               if(StringUtils.equals(RegistryGroupValueIndexLeft,"HKEY_PERFORMANCE_NLSTEXT")){
                   rootHandle = 6;
               }
               if(StringUtils.equals(RegistryGroupValueIndexLeft,"HKEY_CURRENT_CONFIG")){
                   rootHandle = 7;
               }
               if(StringUtils.equals(RegistryGroupValueIndexLeft,"HKEY_DYN_DATA")){
                   rootHandle = 8;
               }
               String RegistryGroupValueIndexRight = StringUtils.substring(registryGroupValue,RegistryGroupValueIndex+1,registryGroup.length());
               if(IsCreate){
                   // 因为java的长度不够 常量要在C++下实现API
                   rck = ModuleOperationUtilsJNI.MyOpenRegistryGroup(rootHandle,RegistryGroupValueIndexRight, 0,64);
               }else {
                   // 因为java的长度不够 常量要在C++下实现API
                   rck = ModuleOperationUtilsJNI.MyOpenRegistryGroup(rootHandle,RegistryGroupValueIndexRight, 1,64);
               }
               if(rck!=0){
                   return rck;
               }
           }else {
               System.err.println("注册表路径错误： " + registryGroupValue);
               return -1;
           }
       }
       return 0;
    }

    /**
     * 是否存在项
     *
     * @param KeyName 注册表头
     * @param ValueName 键值
     * @return 判断指定注册项名称(键名)是否存在(存在返回真,否则返回假)
     */
    public static boolean RegistryGroupIsKeyValue(String KeyName,String ValueName){
        long valueRegx = 0;
        byte[] pointerType = new byte[4];
        long openRegx = openRegistryGroup(KeyName,false);
        if(StringUtils.equals("",ValueName)){
           ModuleOperationUtilsJNI.RegCloseKey(openRegx);
            return openRegx != 0;
        }
        if(openRegx != 0){
            int length = ModuleOperationUtilsJNI.RegQueryValueExLengthW(openRegx,ValueName);
            byte[] pointerlpData = new byte[length];
            valueRegx = ModuleOperationUtilsJNI.RegQueryValueExW(openRegx,ValueName,0,pointerType,pointerlpData,length);
            ModuleOperationUtilsJNI.RegCloseKey(openRegx);
            return valueRegx==0;

        }else {
            System.err.println("注册表句柄有问题 openRegx 的值 正确返回值应该是 0");
        }
       ModuleOperationUtilsJNI.RegCloseKey(openRegx);
        return valueRegx==0;
    }

    /**
     * 枚举注册表左侧注册项名称
     * @param KeyName 注册表
     * @param keyNameList 返回集合
     * @return 返回成员数
     */
    public static int selectRegistryGroupName(String KeyName, List<String> keyNameList){
        keyNameList.clear();
        long openRegx = openRegistryGroup(KeyName,false);
        if(openRegx == 0){
            return -1;
        }
        int index = 0;
        while (true){
            char[] pointerlpName = new char[260];
            if(ModuleOperationUtilsJNI.RegEnumKeyW(openRegx,index,pointerlpName,260)==0){
                index = index + 1;
                keyNameList.add(new String(pointerlpName).trim());
            }else {
                break;
            }
        }
       ModuleOperationUtilsJNI.RegCloseKey(openRegx);
        return keyNameList.size();
    }

    /**
     * 枚举注册表键名
     * @param RegistryGroup 注册项名
     * @param registrygroupinfos  注册表信息
     * @return 返回成员数
     */
    public static int selectRegistryGroupKeyName(String RegistryGroup, List<TagRegisTryGroupInfo> registrygroupinfos){
        byte[] lpcbMaxClassLen = new byte[4];
        registrygroupinfos.clear();
        long openRegx = openRegistryGroup(RegistryGroup,false);
        if(StringUtils.equals("",RegistryGroup)){
           ModuleOperationUtilsJNI.RegCloseKey(openRegx);
            return  0;
        }
        if(openRegx != 0){
            long result = ModuleOperationUtilsJNI.RegQueryInfoKeyW(openRegx,lpcbMaxClassLen,lpcbMaxClassLen.length);
            if(result==0){
                for(int i = -1; i< ByteUtils.bytesArrayToInt(lpcbMaxClassLen); i++){
                    // 这里主要是因为他需要变量承载所以导致API无法使用，如果将来可以实现的话     MyRegEnumValueW 返回值为键值的长度 针对二进制字节的长度
                    int len = ModuleOperationUtilsJNI.MyRegEnumValueWLength(openRegx,i);
                    byte[] lpType = new byte[4];
                    byte[] lpData = new byte[len];
                    int rev = ModuleOperationUtilsJNI.MyRegEnumValueW(openRegx,i,lpType,lpData,len);
                    if(rev!=-1){
                        String key = ByteUtils.bytesArrayToUtf8WideString(lpData);
                        TagRegisTryGroupInfo registrygroupinfo = new TagRegisTryGroupInfo();
                        registrygroupinfo.Keys = key;
                        registrygroupinfo.types = ByteUtils.bytesArrayToInt(lpType);
                        registrygroupinfo.typeName = selectRegistryGroupKeyNameTypeString(RegistryGroup,key);
                        if(ByteUtils.bytesArrayToInt(lpType)==11){
                            registrygroupinfo.values = "" + ByteUtils.hexToInt3(lpData);
                        }
                        if(ByteUtils.bytesArrayToInt(lpType)==4){

                            registrygroupinfo.values = "" +ByteUtils.hexToInt4(lpData);
                        }
                        if(ByteUtils.bytesArrayToInt(lpType)==3){
                            registrygroupinfo.values = "" +ByteUtils.bytesToHexString(lpData).substring(0,rev);
                        }
                        if(ByteUtils.bytesArrayToInt(lpType)!=11 && ByteUtils.bytesArrayToInt(lpType)!=4 && ByteUtils.bytesArrayToInt(lpType)!=3){
                            registrygroupinfo.values = "" + ByteUtils.bytesArrayToUtf8WideString(lpData);
                        }
                        registrygroupinfos.add(registrygroupinfo);
                    }
                }
            }
        }
       ModuleOperationUtilsJNI.RegCloseKey(openRegx);
        return registrygroupinfos.size();
    }
  /**
     * 获取注册表指定键名信息
     * @param RegistryGroup 注册项名  HKEY_CURRENT_USER\Control Panel\Desktop\Colors
     * @param KeyName 键名 ActiveBorder
     * @return 返回类型  -1 失败
     */
    public static int selectRegistryGroupKeyNameType(String RegistryGroup, String KeyName){
        byte[] lpcbMaxClassLenPointer = new byte[4];
        long openRegx = openRegistryGroup(RegistryGroup,false);
        if(StringUtils.equals("",RegistryGroup)){
           ModuleOperationUtilsJNI.RegCloseKey(openRegx);
            return  0;
        }
        if(openRegx != 0){
            long result = ModuleOperationUtilsJNI.RegQueryInfoKeyW(openRegx,lpcbMaxClassLenPointer,lpcbMaxClassLenPointer.length);
            if(result==0){
                for(int i =-1;i< ByteUtils.bytesArrayToInt(lpcbMaxClassLenPointer);i++){
                    // 这里主要是因为他需要变量承载所以导致API无法使用，如果将来可以实现的话
                    int len = ModuleOperationUtilsJNI.MyRegEnumValueWLength(openRegx,i);
                    byte[] lpType = new byte[4];
                    byte[] lpData = new byte[len];
                    int rev = ModuleOperationUtilsJNI.MyRegEnumValueW(openRegx,i,lpType,lpData,len);
                    if(rev!=-1){
                        if(StringUtils.equals(ByteUtils.bytesArrayToUtf8WideString(lpData),KeyName)){
                            int type = ByteUtils.bytesArrayToInt(lpType);
                           ModuleOperationUtilsJNI.RegCloseKey(openRegx);
                            return type;
                        }
                    }
                }
            }
        }
       ModuleOperationUtilsJNI.RegCloseKey(openRegx);
        return -1;
    }

    /**
     * 获取注册表指定键名信息
     * @param RegistryGroup 注册项名  HKEY_CURRENT_USER\Control Panel\Desktop\Colors
     * @param KeyName 键名 ActiveBorder
     * @return 返回类型字符串和说明 失败返回 ""
     */
    public static String selectRegistryGroupKeyNameTypeString(String RegistryGroup, String KeyName){
        int type = selectRegistryGroupKeyNameType(RegistryGroup,KeyName);
        if(type==0){
//            System.out.println("类型 ： " + type + "  字符串  " + "REG_NONE" + "   说明：  " + "没有定义的值类型");
            return "REG_NONE";
        }
        if(type==1){
//            System.out.println("类型 ： " + type + "  字符串  " + "REG_SZ" + "   说明：  " + "以空字符结尾的字符串。这将是 Unicode 或 ANSI 字符串，具体取决于您使用的是 Unicode 还是 ANSI 函数。");
            return "REG_SZ";
        }
        if(type==2){
//            System.out.println("类型 ： " + type + "  字符串  " + "REG_EXPAND_SZ" + "   说明：  " + "一个以空字符结尾的字符串，其中包含对环境变量的未扩展引用（例如，“%PATH%”）。它将是 Unicode 或 ANSI 字符串，具体取决于您使用的是 Unicode 还是 ANSI 函数。要扩展环境变量引用，请使用ExpandEnvironmentStrings函数。");
            return "REG_EXPAND_SZ";
        }
        if(type==3){
//            System.out.println("类型 ： " + type + "  字符串  " + "REG_BINARY" + "   说明：  " + "任何形式的二进制数据。");
            return "REG_BINARY";
        }
        if(type==4){
//            System.out.println("类型 ： " + type + "  字符串  " + "REG_DWORD | REG_DWORD_LITTLE_ENDIAN" + "   说明：  " + "一个 32 位数字。 | 小端格式的 32 位数字。Windows 旨在在小端计算机体系结构上运行。因此，该值在 Windows 头文件中定义为 REG_DWORD。");
            return "REG_DWORD | REG_DWORD_LITTLE_ENDIAN";
        }
        if(type==5){
//            System.out.println("类型 ： " + type + "  字符串  " + "REG_DWORD_BIG_ENDIAN" + "   说明：  " + "大端格式的 32 位数字。一些 UNIX 系统支持大端架构。");
            return "REG_DWORD_BIG_ENDIAN";
        }
        if(type==6){
//            System.out.println("类型 ： " + type + "  字符串  " + "REG_LINK" + "   说明：  " + "一个以空字符结尾的 Unicode 字符串，其中包含通过使用 REG_OPTION_CREATE_LINK调用RegCreateKeyEx函数创建的符号链接的目标路径。");
            return "REG_LINK";
        }
        if(type==7){
//            System.out.println("类型 ： " + type + "  字符串  " + "REG_MULTI_SZ" + "   说明：  " + "一系列以空字符结尾的字符串，以空字符串 (\\0) 结尾。以下是一个例子：字符串1 \\ 0 String2的\\ 0 STRING3 \\ 0 LastString \\ 0 \\ 0第一\\ 0终止第一字符串，第二个到最后\\ 0终止最后一个字符串，以及最终\\ 0终止序列。请注意，最终的终止符必须被计入字符串的长度。");
            return "REG_MULTI_SZ";
        }
        if(type==8){
//            System.out.println("类型 ： " + type + "  字符串  " + "REG_RESOURCE_LIST" + "   说明：  " + "无说明");
            return "REG_RESOURCE_LIST";
        }
        if(type==9){
//            System.out.println("类型 ： " + type + "  字符串  " + "REG_FULL_RESOURCE_DESCRIPTOR" + "   说明：  " + "无说明");
            return "REG_FULL_RESOURCE_DESCRIPTOR";
        }
        if(type==10){
//            System.out.println("类型 ： " + type + "  字符串  " + "REG_RESOURCE_REQUIREMENTS_LIST" + "   说明：  " + "无说明");
            return "REG_RESOURCE_REQUIREMENTS_LIST";
        }
        if(type==11){
//            System.out.println("类型 ： " + type + "  字符串  " + "REG_QWORD | REG_QWORD_LITTLE_ENDIAN" + "   说明：  " + "一个 64 位数字。 | 小端格式的 64 位数字。Windows 旨在在小端计算机体系结构上运行。因此，该值在 Windows 头文件中定义为 REG_QWORD。");
            return "REG_QWORD | REG_QWORD_LITTLE_ENDIAN";
        }
        return "";
    }

    /**
     * 刷新注册表
     * @param RegistryGroup 注册表项路径 左侧文件夹下的子项
     * @return 成功返回注册表
     */
    public static boolean refreshRegistryGroup(String RegistryGroup){
        long openRegx = openRegistryGroup(RegistryGroup,false);
        if(StringUtils.equals("",RegistryGroup)){
           ModuleOperationUtilsJNI.RegCloseKey(openRegx);
            return  false;
        }
        if(openRegx != 0){
            long result = ModuleOperationUtilsJNI.RegFlushKey(openRegx);
           ModuleOperationUtilsJNI.RegCloseKey(openRegx);
            return result ==0;
        }
        return false;
    }

    /**
     * 写注册表
     * @param RegistryGroup 注册表项
     * @param KeyName 键名
     * @param writeByte  键值
     * @param dwTyoe 类型
     * @return  返回逻辑值
     */
    public static boolean writeByte(String RegistryGroup,String KeyName,byte[] writeByte,int dwTyoe){
        long openRegx = openRegistryGroup(RegistryGroup,false);
        if(StringUtils.equals("",RegistryGroup)){
           ModuleOperationUtilsJNI.RegCloseKey(openRegx);
            return  false;
        }
        if(openRegx != 0){
            long flag = ModuleOperationUtilsJNI.RegSetValueExW(openRegx,KeyName,dwTyoe,writeByte,writeByte.length);
            if(flag ==0){
               ModuleOperationUtilsJNI.RegCloseKey(openRegx);
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

    /**
     * 读注册表
     * @param RegistryGroup 注册表项
     * @param KeyName 键名
     * @param types 类型
     * @return 返回字节 字节转换通过BytesUtils类
     */
    public static byte[] readByte(String RegistryGroup,String KeyName,int types){
        byte[] lpcbMaxClassLen = new byte[4];
        long openRegx = openRegistryGroup(RegistryGroup,false);
        if(StringUtils.equals("",RegistryGroup)){
           ModuleOperationUtilsJNI.RegCloseKey(openRegx);
            return  null;
        }
        if(openRegx != 0){
            long result =ModuleOperationUtilsJNI.RegQueryInfoKeyW(openRegx,lpcbMaxClassLen,lpcbMaxClassLen.length);
            if(result==0){
                for(int i =-1;i< ByteUtils.bytesArrayToInt(lpcbMaxClassLen);i++){
                    int len = ModuleOperationUtilsJNI.MyRegEnumValueWLength(openRegx,i);
                    byte[] lpType = new byte[4];
                    byte[] lpData = new byte[len];
                    int rev = ModuleOperationUtilsJNI.MyRegEnumValueW(openRegx,i,lpType,lpData,len);
                    if(rev !=-1){
                        String key = ByteUtils.bytesArrayToUtf8WideString(lpData);
                        if(StringUtils.equals(key,KeyName)){
                           ModuleOperationUtilsJNI.RegCloseKey(openRegx);
                            return lpData;
                        }
                    }
                }
            }
        }
       ModuleOperationUtilsJNI.RegCloseKey(openRegx);
        return null;
    }

    /**
     * 删除注册表项
     * @param RegistryGroup 注册表项路径
     * @param KeyName 注册表项
     * @return 返回结果
     */
    public static long deleteRegistryGroupKey(String RegistryGroup,String KeyName){
        long openRegx = openRegistryGroup(RegistryGroup,false);
        if(StringUtils.equals("",RegistryGroup)){
           ModuleOperationUtilsJNI.RegCloseKey(openRegx);
            return  -1;
        }
        if(openRegx != 0){
            return ModuleOperationUtilsJNI.RegDeleteKeyW(openRegx,KeyName);
        }
        return -1;
    }

    /**
     * 枚举删除注册表项值
     * @param RegistryGroup 注册表项路径 左侧文件夹下的子项
     * @return 返回结果
     */
    public static long enumDeleteRegistryGroupKey(String RegistryGroup){
        long result = 0;
        List<String> keyNameList = new ArrayList<>();
        long length = selectRegistryGroupName(RegistryGroup,keyNameList);
        if(length > 0){
            for (String keyName:keyNameList){
                if(RegistryGroupIsKeyValue(RegistryGroup,keyName)){
                    result = deleteRegistryGroupKey(RegistryGroup,keyName);
                }
            }
        }
        return result;
    }

    /**
     * 删除注册表键名
     * @param RegistryGroup 注册表项路径 左侧文件夹下的子项
     * @param KeyName 右侧的键值
     * @return 返回结果
     */
    public static long deleteRegistryValueName(String RegistryGroup,String KeyName){
        long openRegx = openRegistryGroup(RegistryGroup,false);
        if(StringUtils.equals("",RegistryGroup)){
           ModuleOperationUtilsJNI.RegCloseKey(openRegx);
            return  -1;
        }
        if(openRegx != 0){
            return ModuleOperationUtilsJNI.RegDeleteValueW(openRegx,KeyName);
        }
        return -1;
    }

    /**
     * 枚举删除注册表项键值
     * @param RegistryGroup 注册表项路径 左侧文件夹下的子项
     * @return 返回结果
     */
    public static long enumDeleteRegistryGroupValueNames(String RegistryGroup){
        long result = 0;
        List<TagRegisTryGroupInfo> preregistrations = new ArrayList<>();
        long length = selectRegistryGroupKeyName(RegistryGroup,preregistrations);
        if(length > 0){
            for (TagRegisTryGroupInfo preregistration :preregistrations){
                if(RegistryGroupIsKeyValue(RegistryGroup,preregistration.Keys)){
                    result = deleteRegistryValueName(RegistryGroup,preregistration.Keys);
                }
            }
        }
        return result;
    }


}
