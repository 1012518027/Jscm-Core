package com.scm.all.export;


import com.scm.all.struct.TagIntTable;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * 写配置项  通常用于ini配置读写
 */
public class IniConfigurationOperationUtils {

    /**
     * 写单配置项
     * @param filePath 配置项文件名默认是ini后缀
     * @param sectionName 节名称
     * @param ConfigurationItemName 配置项名称
     * @param dataValue 欲写入的值
     * @return 返回结果
     */
    public static int writeByte(String filePath,String sectionName,String ConfigurationItemName,String dataValue){
       File file  = new File(filePath);
       String isIni = StringUtils.substring(file.getPath(),filePath.length()-3);
        try {
            if(!StringUtils.equals(isIni,"ini")){
               System.err.println("后缀名必须是ini结尾");
               return -1;
            }else {
                if(!file.exists()){
                    file.createNewFile();//文件不存在 创建
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file, false);
                OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream, "UTF-8");
                BufferedWriter  bw = new BufferedWriter(outputStreamWriter);
                TagIntTable iniTable = new TagIntTable();
                iniTable.ConfigurationItemName = ConfigurationItemName;
                iniTable.dataValue = dataValue;
                iniTable.sectionName = sectionName;
                bw.write(iniTable.toString());
                bw.flush();
                fileOutputStream.flush();
                outputStreamWriter.flush();
                bw.close();
                fileOutputStream.close();
                outputStreamWriter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 读单配置项
     * @param filePath 配置项文件名默认是ini后缀
     * @param ConfigurationItemName 配置项名称
     * @return 返回读取的内容 自行转换
     */
    public static String readByte(String filePath,String ConfigurationItemName){
        File file  = new File(filePath);
        String isIni = StringUtils.substring(file.getPath(),filePath.length()-3);
        try {
            if(!StringUtils.equals(isIni,"ini")){
                System.err.println("后缀名必须是ini结尾");
                return "";
            }else {
                if(!file.exists()){
                    return "";
                }
                FileInputStream fileInputStream = new FileInputStream(file);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                String str = null;
                while((str = bufferedReader.readLine()) != null) {
                    int index = 0;
                    if((index = StringUtils.indexOf(str,ConfigurationItemName+"=",0))>-1){
                        return StringUtils.substring(str,(ConfigurationItemName+"=").length(),str.length());
                    }
                }
                bufferedReader.close();
                fileInputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 取单配置节名
     * @param filePath 配置项文件名默认是ini后缀
     * @param id 从 0开始  取哪个配置项名
     * @return 返回读取的内容 自行转换
     */
    public static String getSectionName(String filePath,int id){
        File file  = new File(filePath);
        String isIni = StringUtils.substring(file.getPath(),filePath.length()-3);
        try {
            if(!StringUtils.equals(isIni,"ini")){
                System.err.println("后缀名必须是ini结尾");
                return "";
            }else {
                if(!file.exists()){
                    return "";
                }
                FileInputStream fileInputStream = new FileInputStream(file);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                String str = null;
                int i = 0;
                while((str = bufferedReader.readLine()) != null) {
                    int index = 0;
                    if((index = StringUtils.indexOf(str,"=",0))>-1){
                        i++;
                        if(i==id){
                            i = 0;
                            return StringUtils.substring(str,0,index);
                        }
                    }
                }
                bufferedReader.close();
                fileInputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    //=======================================================================================================================================

    /**
     * 写配置项 支持多行  需要携带Jscm库
     * @param filePath 配置项文件名默认是ini后缀
     * @param sectionName 节名称
     * @param ConfigurationItemName 配置项名称
     * @param dataValue 欲写入的值
     * @return 返回结果
     */
    public static boolean api_writeByte(String filePath,String sectionName,String ConfigurationItemName,String dataValue){
        return ModuleOperationUtilsJNI.WritePrivateProfile(filePath, sectionName, ConfigurationItemName,dataValue);
    }
    /**
     * 读配置项   需要携带Jscm库
     * @param filePath 配置项文件名默认是ini后缀
     * @param sectionName 节名称
     * @param ConfigurationItemName 配置项名称
     * @return 返回读取的内容 自行转换
     */
    public static String api_readByte(String filePath,String sectionName,String ConfigurationItemName){

        return ModuleOperationUtilsJNI.ReadPrivateProfile(filePath,sectionName,ConfigurationItemName);
    }
    /**
     * 取所有配置节名集合
     * @param filePath 配置项文件名默认是ini后缀
     * @param sectionName 取配置项名称
     * @return 返回成员数
     */
    public static int getSectionNameList(String filePath,List<String> sectionName){
        File file  = new File(filePath);
        String isIni = StringUtils.substring(file.getPath(),filePath.length()-3);
        try {
            if(!StringUtils.equals(isIni,"ini")){
                System.err.println("后缀名必须是ini结尾");
                return -1;
            }else {
                if(!file.exists()){
                    return -1;
                }
                FileInputStream fileInputStream = new FileInputStream(file);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                String str = null;
                while((str = bufferedReader.readLine()) != null) {
                    int index = 0;
                    if((index = StringUtils.indexOf(str,"=",0))>-1){
                        sectionName.add(StringUtils.substring(str,0,index));
                    }
                }
                bufferedReader.close();
                fileInputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Ini配置写入 NIO快速写入配置
     *
     * @param fileName 文件名
     * @param KeyName 键值
     * @param ValueName 插入值
     */
    public static void nio_WriteByte(String fileName,String KeyName,String ValueName){
        FileChannel fileChannel =null;
        ByteBuffer byteBuffer =null;
        try {
            //追加文本
            fileChannel = FileChannel.open(Paths.get(fileName), StandardOpenOption.WRITE,StandardOpenOption.APPEND,StandardOpenOption.CREATE); // 以只读的方式打开一个文件 a.txt 的通道
            String temp = KeyName+"="+ValueName+"\r\n";
            byteBuffer = ByteBuffer.wrap(temp.getBytes());
            //将字符串以字节形式放入buffer中
            byteBuffer.put(temp.getBytes());

            //开始读取
            byteBuffer.flip();
            //从buffer中读取到文件
            fileChannel.write(byteBuffer);

        }catch (Exception e){e.printStackTrace();}finally {
            try {
                fileChannel.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
