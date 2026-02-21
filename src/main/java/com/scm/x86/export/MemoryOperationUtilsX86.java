package com.scm.x86.export;//package com.x86.export;


import com.scm.all.export.ByteUtils;
import com.scm.all.export.IniConfigurationOperationUtils;
import com.scm.all.export.ModuleOperationUtilsJNI;
import com.scm.all.export.SystemUtils;
import com.scm.all.pfunc.ByteArraysToByte;
import com.scm.all.pfunc.ByteIndexCallBack;
import com.scm.all.pfunc.MemoryCallBack;
import com.scm.all.struct.TagPmemory_Basic_InfoRmationX86;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * 缺少远程内存申请和释放内存，后期补上
 */
public class MemoryOperationUtilsX86 {
    /**
     * 搜索的内容字节
     */
    public static byte[] saveHexValue;

    /**
     * selectMemoryAddressAttribute 查询内存属性
     * @param hProcess 进程句柄
     * @param address 内存地址 内存地址
     * @return  返回结果 逻辑型
     */
    public static TagPmemory_Basic_InfoRmationX86 selectMemoryAddressAttribute(int hProcess, int address){
        TagPmemory_Basic_InfoRmationX86 pmemory_basic_information = new TagPmemory_Basic_InfoRmationX86();
        ModuleOperationUtilsJNI.MyVirtualQueryEx(hProcess,address, pmemory_basic_information,32);
        return pmemory_basic_information;
    }
    /**
     * selectMemoryAddressAttribute 查询内存属性
     * @param hProcess 进程句柄
     * @param address 内存地址 内存地址
     * @param pmemory_basic_information 内存属性 new TagPmemory_Basic_InfoRmationX86()
     * @return  返回结果
     */
    public static int selectMemoryAddressAttribute(int hProcess, int address, TagPmemory_Basic_InfoRmationX86 pmemory_basic_information){
        int result = ModuleOperationUtilsJNI.MyVirtualQueryEx(hProcess,address, pmemory_basic_information,32);
        return result;
    }

    /**
     * 修改内存保护
     * @param hProcess  进程句柄
     * @param address 内存地址 内存地址
     * @param attribute 属性
     * @param dwSize 长度
     * @return  返回结果  返回原始保护值
     * attribute 参考
     * #停用高速缓存 = 512
     * #通知 = 256
     * #禁止操作 = 128
     * #允许读写 = 64
     * #允许执行与读取 = 32
     * #允许执行 = 16
     * #允许读写 = 4
     * #允许读取 = 2
     * #禁止访问 = 1
     */
    public static int updateMemoryAddressAttribute(int hProcess, int address,int dwSize,int attribute ){
        if(attribute==0){
            attribute = 64;
        }
        if(dwSize==0){
            dwSize = 5;
        }
        int result = ModuleOperationUtilsJNI.MyVirtualProtectEx(hProcess,address,dwSize,attribute);
        return result;
    }


    /**
     * 内存地址是否有效
     * @param  hProcess 进程ID
     * @param address 内存地址 内存地址
     * @return  返回结果
     */
    public static boolean memoryAddressIsValid(int hProcess,int address){
        boolean flag = false;
        TagPmemory_Basic_InfoRmationX86 pmemory_basic_information = MemoryOperationUtilsX86.selectMemoryAddressAttribute(hProcess,address);
        flag = pmemory_basic_information.Protect !=16 && pmemory_basic_information.Protect !=1 && pmemory_basic_information.Protect !=128;
        return flag;
    }



    /**
     * 是否为静态地址
     * @param  hProcess 进程句柄
     * @param address 内存地址 内存地址
     * @return  返回结果
     */
    public static boolean memoryAddressIsStatic(int hProcess,int address){
        boolean flag = false;
        TagPmemory_Basic_InfoRmationX86 pmemory_basic_information = MemoryOperationUtilsX86.selectMemoryAddressAttribute(hProcess,address);
        flag = pmemory_basic_information.lType == 16777216;
        return flag;
    }


    /**
     * 是否为基址
     * @param hProcess 进程句柄
     * @param address 内存地址 内存地址
     * @return  返回结果
     */
    public static boolean memoryAddressIsBaseAddress(int hProcess,int address){
        boolean flag = false;
        TagPmemory_Basic_InfoRmationX86 pmemory_basic_information = MemoryOperationUtilsX86.selectMemoryAddressAttribute(hProcess,address);
        flag = pmemory_basic_information.AllocationBase == 4194304 && pmemory_basic_information.Protect != 2 && pmemory_basic_information.lType != 16777216 && pmemory_basic_information.RegionSize > 4096;
        return flag;
    }


    /**
     * 获取内存地址数据长度
     * @param hProcess 进程句柄
     * @param address 内存地址 内存地址
     * @return  返回结果
     */
    public static int memoryAddressGetLength(int hProcess,int address){
        TagPmemory_Basic_InfoRmationX86 pmemory_basic_information = MemoryOperationUtilsX86.selectMemoryAddressAttribute(hProcess,address);
        int len = pmemory_basic_information.RegionSize + pmemory_basic_information.BaseAddress - address;
        return len;
    }

    /**
     * 内存读字节 返回文本HEX    可以通过字节工具类进行转换
     * @param hProcess 进程句柄
     * @param address 内存地址
     * @param dwSize 长度
     * @return  返回结果
     */
    public static byte[]  readProcessMemoryByte(int hProcess, int address,int dwSize){
        final byte[] buffer =new byte[dwSize];
        ModuleOperationUtilsJNI.MyReadProcessMemory(hProcess,address,dwSize,buffer);
        return buffer;
    }

    /**c
     * 内存读字节 返回字节 附带偏移   可以通过字节工具类进行转换
     * @param hProcess 进程句柄
     * @param address 内存地址
     * @param dwSize 长度
     * @param offset 偏移
     * @return  返回结果
     */
    public static byte[] readProcessMemoryByteOffSet(int hProcess, int address,int dwSize,int[] offset){
        for (int i =0;i<offset.length;i++){
            byte[] offsetInt  =  readProcessMemoryByte(hProcess,address,4);
            address = ByteUtils.bytesArrayToInt(offsetInt) + offset[i];
        }
        byte[] Buffer =new byte[dwSize];
        ModuleOperationUtilsJNI.MyReadProcessMemory(hProcess,address,dwSize,Buffer);
        return Buffer;
    }
    /**
     * 内存写字节 十六进制文本   可以通过字节工具类进行转换
     * @param hProcess 进程句柄
     * @param address 内存地址
     * @param dwSize 类型
     * @param lpBuffer 十六机制文本不带空格
     * @return  返回结果
     */
    public static int writeProcessMemoryByte(int hProcess,int address,int dwSize,byte[] lpBuffer){
        return ModuleOperationUtilsJNI.MyWriteProcessMemory(hProcess,address,dwSize,lpBuffer);
    }

    /**
     * 内存写字节 十六进制文本   附带偏移  可以通过字节工具类进行转换
     * @param hProcess 进程句柄
     * @param address 内存地址
     * @param dwSize 类型
     * @param offset 偏移十进制
     * @param lpBuffer 十六机制文本不带空格
     * @return  返回结果
     */
    public static int writeProcessMemoryByteOffSet(int hProcess,int address,int dwSize,byte[] lpBuffer,int[] offset){
        for (int i =0;i<offset.length;i++){
            byte[] offsetInt  = readProcessMemoryByte(hProcess,address,4);
            address = ByteUtils.bytesArrayToInt(offsetInt) + offset[i];
        }
        return ModuleOperationUtilsJNI.MyWriteProcessMemory(hProcess,address,dwSize,lpBuffer);
    }


    /**
     * 线程模式首次内存搜索
     * @param hProcess 进程句柄
     * @param HexData 内存字节集 ByteUtils.getObjectTypeToByte(1012518027,"int")
     * @param model 搜索模式
     * @param memoryCallBack 回调数据 返回最快的数据线
     * @return  返回结果
     */
    public static int threadSearchMemory(int hProcess, byte[] HexData,int model, MemoryCallBack memoryCallBack ){
        File file = new File(ModuleOperationUtilsJNI.tileDir()+ MemoryOperationUtilsX86.class.getSimpleName()+".ini");
        file.delete();
        int MemoryAddress = 0;
        int BytesIndex = 0;
        int temp = 0;
        if(ByteUtils.equalsByteArray(HexData,new byte[]{0})||ByteUtils.equalsByteArray(HexData,new byte[]{0,0})||ByteUtils.equalsByteArray(HexData,new byte[]{0,0,0,0})|| ByteUtils.equalsByteArray(HexData,new byte[]{0,0,0,0,0,0,0,0})){
            SystemUtils.messageBoxExW(0,"禁止输入0 非空长度进行搜索，因为 0 有上亿结果禁止使用！","温馨提示",0);
            return 0;
        }
        saveHexValue = HexData;
        final TagPmemory_Basic_InfoRmationX86 pmemory_basic_information = new TagPmemory_Basic_InfoRmationX86();
        while (selectMemoryAddressAttribute(hProcess,MemoryAddress,pmemory_basic_information) !=-1){
            if(pmemory_basic_information.State== 4096 && (pmemory_basic_information.lType == 16777216 ||  pmemory_basic_information.lType == 262144 || pmemory_basic_information.lType == 131072) && (pmemory_basic_information.Protect==64 || pmemory_basic_information.Protect==4)){
                updateMemoryAddressAttribute(hProcess,MemoryAddress,pmemory_basic_information.RegionSize,64);
                if(MemoryAddress > 0){
                    byte[] data = MemoryOperationUtilsX86.readProcessMemoryByte(hProcess,MemoryAddress,pmemory_basic_information.RegionSize);
                    if(data.length > 0 ){
                        if (model==0){
                            int finalMemoryAddress = MemoryAddress;
                            ByteUtils.byteToBytesArray(data,HexData,new ByteIndexCallBack(){
                                @Override
                                public void call(int index) {
                                    writeIniFile(null,Long.toHexString(finalMemoryAddress + index).toUpperCase(),""+(finalMemoryAddress + index));
                                    if(pmemory_basic_information.lType == 16777216){
                                        if(memoryCallBack!=null){
                                            boolean flag = memoryCallBack.data(selectMemoryAddressAttribute(hProcess,(finalMemoryAddress + index)),"√",(finalMemoryAddress + index),Long.toHexString(finalMemoryAddress + index),pmemory_basic_information.Protect,pmemory_basic_information.lType);
                                            while (flag){}
                                        }
                                    }else {
                                        if(memoryCallBack!=null){
                                            boolean flag = memoryCallBack.data(selectMemoryAddressAttribute(hProcess,(finalMemoryAddress + index)),"",(finalMemoryAddress + index),Long.toHexString(finalMemoryAddress + index),pmemory_basic_information.Protect,pmemory_basic_information.lType);
                                            while (flag){}
                                        }
                                    }
                                    super.call(index);
                                }
                            });
                        }
                        if(model==1){
                            BytesIndex = ByteUtils.indexOf(data,HexData,0);
                            do {
                                if(BytesIndex == -1){
                                    break;
                                }
                                writeIniFile(null,Long.toHexString(MemoryAddress + BytesIndex).toUpperCase(),""+(MemoryAddress + BytesIndex));
                                if(pmemory_basic_information.lType == 16777216){
                                    if(memoryCallBack!=null){
                                        boolean flag = memoryCallBack.data(selectMemoryAddressAttribute(hProcess,(MemoryAddress + BytesIndex)),"√",(MemoryAddress + BytesIndex),Long.toHexString(MemoryAddress + BytesIndex),pmemory_basic_information.Protect,pmemory_basic_information.lType);
                                        while (flag){}
                                    }
                                }else {
                                    if(memoryCallBack!=null){
                                        boolean flag = memoryCallBack.data(selectMemoryAddressAttribute(hProcess,(MemoryAddress + BytesIndex)),"",(MemoryAddress + BytesIndex),Long.toHexString(MemoryAddress + BytesIndex),pmemory_basic_information.Protect,pmemory_basic_information.lType);
                                        while (flag){}
                                    }
                                }

                            }while ((BytesIndex = ByteUtils.indexOf(data,HexData,BytesIndex + HexData.length))!=-1);
                        }
                        if(model==2){
                            ByteUtils.bytesToReadIndexArray(data,HexData,HexData.length,MemoryAddress,new ByteArraysToByte(){
                                @Override
                                public void call(byte[] b, int Address) {
                                    writeIniFile(null,Long.toHexString(Address).toUpperCase(),""+Address);
                                    if(pmemory_basic_information.lType == 16777216){
                                        if(memoryCallBack!=null){
                                            boolean flag = memoryCallBack.data(selectMemoryAddressAttribute(hProcess,Address),b,"√",Address,Long.toHexString(Address),pmemory_basic_information.Protect,pmemory_basic_information.lType);
                                            while (flag){}
                                        }
                                    }else {
                                        if(memoryCallBack!=null){
                                            boolean flag = memoryCallBack.data(selectMemoryAddressAttribute(hProcess,Address),b,"",Address,Long.toHexString(Address),pmemory_basic_information.Protect,pmemory_basic_information.lType);
                                            while (flag){}
                                        }
                                    }
                                }
                            });
                        }
                    }
                }else {
                    break;
                }
            }
            MemoryAddress = MemoryAddress + pmemory_basic_information.RegionSize;
            if(MemoryAddress!=temp){
                temp = MemoryAddress;
            } else if( MemoryAddress < 0 || MemoryAddress==temp){
                break;
            }
        }
        return 0;
    }


    /**
     * 线程模式首次特征内存搜索
     * @param hProcess 进程句柄
     * @param HexData 内存字节集 ByteUtils.getObjectTypeToByte(1012518027,"int")
     * @param memoryCallBack 回调数据 返回最快的数据线
     * @return  返回结果
     */
    public static int threadMd5SearchMemory(int hProcess, String HexData, MemoryCallBack memoryCallBack ){
        File file = new File(ModuleOperationUtilsJNI.tileDir()+ MemoryOperationUtilsX86.class.getSimpleName()+".ini");
        file.delete();
        int MemoryAddress = 0;
        int temp = 0;
        if(StringUtils.equals(HexData,"")||HexData==null|| HexData.length()==0){
            SystemUtils.messageBoxExW(0,"禁止输入非空内容","温馨提示",0);
            return 0;
        }
        final TagPmemory_Basic_InfoRmationX86 pmemory_basic_information = new TagPmemory_Basic_InfoRmationX86();
        while (selectMemoryAddressAttribute(hProcess,MemoryAddress,pmemory_basic_information) !=-1){
            if(pmemory_basic_information.State== 4096 && (pmemory_basic_information.lType == 16777216 ||  pmemory_basic_information.lType == 262144 || pmemory_basic_information.lType == 131072) && (pmemory_basic_information.Protect==64 || pmemory_basic_information.Protect==4)){
                updateMemoryAddressAttribute(hProcess,MemoryAddress,pmemory_basic_information.RegionSize,64);
                if(MemoryAddress > 0){
                    byte[] data = MemoryOperationUtilsX86.readProcessMemoryByte(hProcess,MemoryAddress,pmemory_basic_information.RegionSize);
                    if(data.length > 0 ){
                        int finalMemoryAddress = MemoryAddress;
                        ByteUtils.byteToMd5Link(data,HexData,new ByteIndexCallBack(){
                            @Override
                            public void call(int index) {
                                writeIniFile(null,Long.toHexString(finalMemoryAddress + index).toUpperCase(),""+(finalMemoryAddress + index));
                                if(pmemory_basic_information.lType == 16777216){
                                    if(memoryCallBack!=null){
                                        boolean flag = memoryCallBack.data(selectMemoryAddressAttribute(hProcess,(finalMemoryAddress + index)),"√",(finalMemoryAddress + index),Long.toHexString(finalMemoryAddress + index),pmemory_basic_information.Protect,pmemory_basic_information.lType);
                                        while (flag){}
                                    }
                                }else {
                                    if(memoryCallBack!=null){
                                        boolean flag = memoryCallBack.data(selectMemoryAddressAttribute(hProcess,(finalMemoryAddress + index)),"",(finalMemoryAddress + index),Long.toHexString(finalMemoryAddress + index),pmemory_basic_information.Protect,pmemory_basic_information.lType);
                                        while (flag){}
                                    }
                                }
                            }
                        });
                    }
                }else {
                    break;
                }
            }
            MemoryAddress = MemoryAddress + pmemory_basic_information.RegionSize;
            if(MemoryAddress!=temp){
                temp = MemoryAddress;
            } else if( MemoryAddress < 0 || MemoryAddress==temp){
                break;
            }
        }
        return 0;
    }

    /**
     * 读数据到Ini
     * @param filePath 配置名 null返回当前类
     * @param hex 十六进制大写
     * @return 返回数据
     */
    public static String readIniFile(String filePath,String hex){
        if(StringUtils.equals(filePath,null)){
            return IniConfigurationOperationUtils.readByte(ModuleOperationUtilsJNI.tileDir() + MemoryOperationUtilsX86.class.getSimpleName() + ".ini",hex.toUpperCase());
        }else {
            return IniConfigurationOperationUtils.readByte(ModuleOperationUtilsJNI.tileDir() + filePath + ".ini",hex.toUpperCase());
        }
    }

    /**
     * 写数据到Ini
     * @param filePath 配置名
     * @param hex 十六进制大写
     * @param ten 十进制文本
     */
    public static void writeIniFile(String filePath,String hex,String ten){
        if(StringUtils.equals(filePath,null)){
            IniConfigurationOperationUtils.nio_WriteByte(ModuleOperationUtilsJNI.tileDir()+MemoryOperationUtilsX86.class.getSimpleName()+".ini",hex.toUpperCase(),ten);
        }else {
            IniConfigurationOperationUtils.nio_WriteByte(ModuleOperationUtilsJNI.tileDir()+filePath+".ini",hex.toUpperCase(),ten);
        }
    }


    /**
     * 申请内存
     * @param hProcess 进程句柄
     * @param lpBaseAddress 地址
     * @param dwSize 长度
     * @param flAllocationType 4096|8192
     * @param flProtect 4
     * @return 返回地址
     */
    public static int virtualAllocEx(int hProcess, int lpBaseAddress,int dwSize,int flAllocationType,int flProtect){
        return ModuleOperationUtilsJNI.VirtualAllocEx(hProcess,lpBaseAddress,dwSize,flAllocationType,flProtect);
    }
    /**
     * 释放内存
     * @param hProcess 进程句柄
     * @param lpBaseAddress 地址
     * @return 释放结果
     */
    public static boolean virtualFreeEx(int hProcess, int lpBaseAddress){
        return ModuleOperationUtilsJNI.VirtualFreeEx(hProcess,lpBaseAddress);
    }

}
