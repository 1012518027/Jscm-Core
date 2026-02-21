package com.scm.all.export;


import com.scm.all.pfunc.ByteArraysToByte;
import com.scm.all.pfunc.ByteIndexCallBack;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 编码转换类
 * 本类是全新的编码转换工具类
 */
public class ByteUtils {
    /**
     * 此方法是将int类型的数值转换成byte字节数组
     * int 转 byte[]
     *
     * @param value 整数
     * @return 返回字节
     */
    public static byte[] intToBytesArray(int value) {
        byte[] data = ByteBuffer.allocate(4).putInt(value).array();
        byte[] temp = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[data.length - i - 1];
        }
        return temp;
    }
    /**
     * 字节数组转字符串（全参数重载，最灵活）
     *
     * @param byteArray    待转换的字节数组（可为 null 或空）
     * @param filterNullChar 是否过滤 Unicode 空字符（\u0000）
     * @return 转换后的字符串（null 或空数组返回空字符串）
     */
    public static String bytesArrayUTF16ToWideString(byte[] byteArray, boolean filterNullChar) {
        // 1. 空值校验：字节数组为 null 或长度为 0，返回空字符串
        if (byteArray == null || byteArray.length == 0) {
            return "";
        }
        // 3. 核心转换：通过指定编码解析字节数组
        String result = new String(byteArray, StandardCharsets.UTF_16LE);

        // 4. 空字符过滤（可选）
        if (filterNullChar) {
            result = result.replace("\u0000", "");
        }

        return result;
    }


    /**
     * 此方法是将byte字节数组转换成对应的int
     * bytes 转 int
     *
     * @param bytes int字节数组
     * @return 返回对应的数值
     */
    public static int bytesArrayToInt(byte[] bytes) {
        byte[] temp = new byte[4];
        for (int i = 0; i < bytes.length; i++) {
            temp[i] = bytes[bytes.length - i - 1];
        }
        ByteBuffer buffer = ByteBuffer.allocate(temp.length).put(temp);
        buffer.flip();  //一定要先执行flip再get，否则会报错
        return buffer.getInt();
    }

    /**
     * 此方法是将long类型转换成byte字节数组
     * long 转 byte[]
     *
     * @param value 长整型
     * @return 返回字节
     */
    public static byte[] longToBytesArray(long value) {
        byte[] data = ByteBuffer.allocate(8).putLong(value).array();
        byte[] temp = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[data.length - i - 1];
        }
        return temp;
    }

    /**
     * 此方法是将long类型的字节数组转换成对应的数值
     * bytes 转 long
     *
     * @param bytes 数组
     * @return 返回对应的数值
     */
    public static long bytesArrayToLong1(byte[] bytes) {
        byte[] temp = new byte[8];
        for (int i = 0; i < bytes.length; i++) {
            temp[i] = bytes[bytes.length - i - 1];
        }
        ByteBuffer buffer = ByteBuffer.allocate(temp.length).put(temp);
        buffer.flip();  //一定要先执行flip再get，否则会报错
        return buffer.getLong();
    }

    /**
     * 此方式是将short转换成对应的字节数组
     * short 转 byte[]
     *
     * @param value 短整型
     * @return 返回字节数组
     */
    public static byte[] shortToBytesArray(short value) {
        byte[] data = ByteBuffer.allocate(2).putShort(value).array();
        byte[] temp = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[data.length - i - 1];
        }
        return temp;
    }

    /**
     * 此方法是将对应的字节数组转换成short类型数值
     *
     * @param bytes 转 short
     * @return 返回数值
     */
    public static short bytesArrayToShort1(byte[] bytes) {
        byte[] temp = new byte[2];
        for (int i = 0; i < bytes.length; i++) {
            temp[i] = bytes[bytes.length - i - 1];
        }
        ByteBuffer buffer = ByteBuffer.allocate(temp.length).put(temp);
        buffer.flip();  //一定要先执行flip再get，否则会报错
        short tval = buffer.getShort();
        buffer = null;
        return tval;
    }

    /**
     * 此方法是将对应的字节数组转换成short类型数值
     *
     * @param bytes 转 short
     * @return 返回数值
     */
    public static short bytesArrayToShort2(byte[] bytes) {
        return ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getShort();
    }


    /**
     * 此方法是将double类型转换成对应的字节数组
     *
     * @param value double 数值
     *              double 转 byte[]
     * @return 返回字节数组
     */
    public static byte[] doubleToBytesArray(double value) {
        byte[] data = ByteBuffer.allocate(8).putDouble(value).array();
        byte[] temp = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[data.length - i - 1];
        }
        return temp;
    }

    /**
     * 此方法是将字节数组转换成对应的类型数值
     *
     * @param bytes 转 double
     * @return 返回对应数值
     */
    public static double bytesArrayToDouble1(byte[] bytes) {
        byte[] temp = new byte[8];
        for (int i = 0; i < bytes.length; i++) {
            temp[i] = bytes[bytes.length - i - 1];
        }
        ByteBuffer buffer = ByteBuffer.allocate(temp.length).put(temp);
        buffer.flip();  //一定要先执行flip再get，否则会报错
        return buffer.getDouble();
    }

    /**
     * 此方法是将对应的float转换成对应的字节数组
     *
     * @param value 类型数值
     *              float 转 byte[]
     * @return 返回字节数组
     */
    public static byte[] floatToBytesArray(float value) {
        byte[] data = ByteBuffer.allocate(4).putFloat(value).array();
        byte[] temp = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[data.length - i - 1];
        }
        return temp;
    }

    /**
     * 此方法是将对应的字节数组转换成类型数值
     *
     * @param bytes 转 float
     * @return 返回对应数值
     */
    public static float bytesArrayToFloat1(byte[] bytes) {
        byte[] temp = new byte[4];
        for (int i = 0; i < bytes.length; i++) {
            temp[i] = bytes[bytes.length - i - 1];
        }
        ByteBuffer buffer = ByteBuffer.allocate(temp.length).put(temp);
        buffer.flip();  //一定要先执行flip再get，否则会报错
        return buffer.getFloat();
    }


    /**
     * 此方法是将char类型数值转换成字节数组
     *
     * @param value 类型数值
     *              char 转 byte[]
     * @return 返回字节
     */
    public static byte[] charToBytesArray(char value) {
        return ByteBuffer.allocate(2).putChar(value).array();
    }

    /**
     * 此方法是将字节数组转换成对应的类型数值
     *
     * @param bytes 转 char
     * @return 返回对应数值
     */
    public static char bytesArrayToChar(byte[] bytes) {
        if (bytes.length > 0) {
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length).put(bytes);
            buffer.flip();  //一定要先执行flip再get，否则会报错
            return buffer.getChar();
        } else {
            return (char) 0;
        }

    }


    //===========================================================================================================

    /**
     * 常用
     * 字节数组转宽字符串 包含中文的
     *
     * @param bs 宽字符 utf8ToUniCodeArray
     * @return 返回字符串
     */
    public static String bytesArrayToUtf8WideString(byte[] bs) {
        //必须先转换到UTF8字节
        byte[] bytes = uniCodeArrayToUtf8(bs).getBytes();
        byte[] tempBytes = new byte[bytes.length];
        byte[] end1 = new byte[]{0};
        int index1 = indexOf(bytes, end1, 0);
        int j = 0;
        try {
            if (index1 != -1) {
                for (int i = 0; i < index1; i++) {
                    if (bytes[i] != 0) {
                        tempBytes[j++] = bytes[i];
                    }
                }
                return new String(tempBytes, "UTF-8").trim();
            } else if (index1 == -1) {
                for (int i = 0; i < bytes.length; i++) {
                    if (bytes[i] != 0) {
                        tempBytes[j++] = bytes[i];
                    }
                }
                return new String(tempBytes, "UTF-8").trim();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    //===========================================================================================================

    /**
     *  此方法可以回转！
     *
     * */

    /**
     * 宽字符数组转宽字符串 包含中文的
     *
     * @param bs 宽字符   utf8WideStringToCharsArray
     * @return 返回宽字符串
     */
    public static String charsArrayToUtf8WideString1(char[] bs) {
        char[] bytes = bs;
        char[] tempBytes = new char[bytes.length];
        char[] end1 = new char[]{'\u0000'};
        int index1 = indexOf(bytes, end1, 0);
        int j = 0;
        try {
            if (index1 != -1) {
                for (int i = 0; i < index1; i++) {
                    if (bytes[i] != '\u0000') {
                        tempBytes[j++] = bytes[i];
                    }
                }
                return new String(tempBytes).trim();
            } else if (index1 == -1) {
                for (int i = 0; i < bytes.length; i++) {
                    if (bytes[i] != '\u0000') {
                        tempBytes[j++] = bytes[i];
                    }
                }
                return new String(tempBytes).trim();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 字符数组转宽字符串
     *
     * @param chars 字符数组
     * @return 返回宽字符数组
     */
    public static String charsArrayToUtf8WideString2(char[] chars) {
        char[] tempBytes = new char[chars.length];
        char[] end = new char[]{'\u0000', 'o', 'n', '\u0000'};
        int index = indexOf(chars, end, 0);
        int j = 0;
        try {
            if (index != -1) {
                for (int i = 0; i < index; i++) {
                    if (chars[i] != 0) {
                        tempBytes[j++] = chars[i];
                    }
                }
                return new String(tempBytes).trim();
            } else if (index == -1) {
                for (int i = 0; i < chars.length; i++) {
                    if (chars[i] != 0) {
                        tempBytes[j++] = chars[i];
                    }
                }
                return new String(tempBytes).trim();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * utf8 字符串 转 宽字符数组
     *
     * @param value charsArrayToUtf8WideString
     * @param isEnd 是否包含结束符号
     * @return 返回UTF8宽字符数组
     */
    public static char[] utf8WideStringToCharsArray(String value, boolean isEnd) {
        char[] tempBytes = value.toCharArray();
        char[] bs = new char[tempBytes.length * 2 + 3];
        int j = 0;
        for (int i = 0; i < tempBytes.length; i++) {
            bs[j++] = tempBytes[i];
        }
        if (isEnd == true) {
            bs[bs.length - 4] = '\u0000';
            bs[bs.length - 3] = 'o';
            bs[bs.length - 2] = 'n';
            bs[bs.length - 1] = '\u0000';
        }
        return bs;
    }


    //===================================    ===========================================

    /**
     * 常用
     * Ascii字节数组 转换成字符串
     *
     * @param bs ascii 字节数组
     * @return 返回字符串
     */
    public static String bytesArrayToAsciiWideString(byte[] bs) {
        //必须先转换到ASCII字节
        byte[] bytes = asciiArrayToUtf8(bs).getBytes();
        byte[] tempBytes = new byte[bytes.length];
        // ascii特征编码
        byte[] end1 = new byte[]{0};
        int index1 = indexOf(bytes, end1, 0);
        int j = 0;
        try {
            if (index1 != -1) {
                for (int i = 0; i < index1; i++) {
                    if (bytes[i] != 0) {
                        tempBytes[j++] = bytes[i];
                    }
                }
                return new String(tempBytes, "UTF-8").trim();
            } else if (index1 == -1) {
                for (int i = 0; i < bytes.length; i++) {
                    if (bytes[i] != 0) {
                        tempBytes[j++] = bytes[i];
                    }
                }
                return new String(tempBytes, "UTF-8").trim();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * Ascii符串 转 字节数组
     *
     * @param value bytesArrayToAsciiWideString
     * @param isEnd 是否包含结尾
     * @return 成功返回字节数组
     */
    public static byte[] asciiWideStringToBytesArray(String value, boolean isEnd) {
        byte[] tempBytes;
        try {
            tempBytes = value.getBytes("GB2312");
            byte[] bs = new byte[tempBytes.length + 3];
            int j = 0;
            for (int i = 0; i < tempBytes.length; i++) {
                bs[j++] = tempBytes[i];
            }
            if (isEnd == true) {
                bs[bs.length - 3] = 0;
                bs[bs.length - 2] = 0;
                bs[bs.length - 1] = -17;
            }
            return bs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    //===========================================================================================================

    /**
     * utf8 转Ascii byte字节数组
     *
     * @param text UTF8的常规内容
     * @return 返回字节
     */
    public static byte[] utf8ToAsciiArray(String text) {
        try {
            byte[] textBytes = text.getBytes("GB2312");
            byte[] data = new byte[textBytes.length + 1];
            for (int b = 0; b < textBytes.length; b++) {
                data[b] = textBytes[b];
            }
            data[textBytes.length] = 0;
            return data;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Ascii bytes 转 utf8
     *
     * @param textBytes ASCII 字节编码
     * @return 返回文本
     */
    public static String asciiArrayToUtf8(byte[] textBytes) {
        try {
            byte[] data = new byte[textBytes.length - 1];
            for (int b = 0; b < textBytes.length - 1; b++) {
                data[b] = textBytes[b];
            }
            return new String(data, "GB2312");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * UTF-8 转 byte字节数组
     *
     * @param text UTF8的常规内容
     * @return 返回字节
     */
    public static byte[] utf8ToBytesArray(String text) {
        try {
            byte[] textBytes = text.getBytes("UTF-8");
            byte[] data = new byte[textBytes.length + 1];
            for (int b = 0; b < textBytes.length; b++) {
                data[b] = textBytes[b];
            }
            data[textBytes.length] = 0;
            return data;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * bytes 转 UTF-8
     *
     * @param textBytes UTF-8  字节编码
     * @return 返回文本
     */
    public static String bytesArrayToUtf8(byte[] textBytes) {
        try {
            byte[] data = new byte[textBytes.length - 1];
            for (int b = 0; b < textBytes.length - 1; b++) {
                data[b] = textBytes[b];
            }
            return new String(data, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * utf8 转 UniCode
     *
     * @param text UTF-8 文本
     * @return 返回UniCode 字节
     */
    public static byte[] utf8ToUniCodeArray(String text) {
        try {
            byte[] textBytes = text.getBytes("UnicodeLittleUnmarked");
            byte[] data = new byte[textBytes.length + 2];
            for (int b = 0; b < textBytes.length; b++) {
                data[b] = textBytes[b];
            }
            data[textBytes.length] = 0;
            data[textBytes.length + 1] = 0;
            return data;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * UniCode 转 utf8
     *
     * @param textBytes UniCode 字节数组
     * @return 返回字符串
     */
    public static String uniCodeArrayToUtf8(byte[] textBytes) {
        try {
            byte[] data = new byte[textBytes.length - 2];
            for (int b = 0; b < textBytes.length - 2; b++) {
                data[b] = textBytes[b];
            }
            return new String(data, "UnicodeLittleUnmarked");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 字符串反取文本
     *
     * @param s 文本
     * @return 返回结果
     */
    public static String reverse2(String s) {
        char[] array = s.toCharArray();
        String reverse = "";
        for (int i = array.length - 1; i >= 0; i--) {
            reverse += array[i];
        }
        return reverse;
    }

    /**
     * @param intValue 任意值
     * @param type     字符串体现类型
     *                 ByteUtils.getObjectTypeToByte(1012518027,"int")
     *                 ByteUtils.getObjectTypeToByte(1012518027l,"long")
     *                 ByteUtils.getObjectTypeToByte("1012518027","StringUTF8None") 去0符号
     *                 ByteUtils.getObjectTypeToByte("1012518027","StringUniCodeNone") 去0符号
     *                 ByteUtils.getObjectTypeToByte("1012518027","StringAsciiNone") 去0符号
     *                 ByteUtils.getObjectTypeToByte("1012518027","StringUTF8") 含 0符号
     *                 ByteUtils.getObjectTypeToByte("1012518027","StringUniCode") 含 0符号
     *                 ByteUtils.getObjectTypeToByte("1012518027","StringAscii") 含 0符号
     *                 ByteUtils.getObjectTypeToByte(byte[] 字节数组,"byte")
     *                 ByteUtils.getObjectTypeToByte(100,"short")
     *                 ByteUtils.getObjectTypeToByte(100,"float")
     *                 ByteUtils.getObjectTypeToByte(100,"double")
     *                 ByteUtils.getObjectTypeToByte(100,"char")
     * @return 返回字节
     */
    public static byte[] getObjectTypeToByte(Object intValue, String type) {
        if (StringUtils.equals("int", type)) {
            return intToBytesArray(Integer.parseInt((String) intValue));
        }
        if (StringUtils.equals("long", type)) {
            return longToBytesArray(Long.parseLong((String) intValue));
        }
        if (StringUtils.equals("StringUTF8", type)) {
            return utf8ToBytesArray((String) intValue);
        }
        if (StringUtils.equals("StringUniCode", type)) {
            return utf8ToUniCodeArray((String) intValue);
        }
        if (StringUtils.equals("StringAscii", type)) {
            return utf8ToAsciiArray((String) intValue);
        }
        if (StringUtils.equals("byte", type)) {
            return (byte[]) intValue;
        }
        if (StringUtils.equals("short", type)) {
            return shortToBytesArray(Short.parseShort((String) intValue));
        }
        if (StringUtils.equals("float", type)) {
            return floatToBytesArray(Float.parseFloat((String) intValue));
        }
        if (StringUtils.equals("double", type)) {
            return doubleToBytesArray(Double.parseDouble((String) intValue));
        }
        if (StringUtils.equals("char", type)) {
            return charToBytesArray((char) intValue);
        }
        return null;
    }

    /**
     * 特征码搜索 根据  ?? 方式进行搜索
     *
     * @param regx   FF 22 ?? 55 00 ?? 2B
     * @param values 与匹配的字符串
     * @return 返回匹配结果
     */
    public static boolean hexToMd5BytesMatches(String regx, String values) {
        //区分分割类型
        if (StringUtils.indexOf(regx.toUpperCase(), "??") > 0) {
            String pattern = regx.toUpperCase().replaceAll("\\?\\?", "([1-9-A-Z-a-z]{2})");
            Pattern r = Pattern.compile(pattern.toUpperCase());
            Matcher m = r.matcher(values.toUpperCase());
            return m.matches();
        }
        return false;
    }

    /**
     * 特征码搜索 根据  ?? 方式进行搜索
     *
     * @param regx   FF 22 ?? 55 00 ?? 2B
     * @param values 与匹配的字符串
     * @return 返回匹配结果
     */
    public static List<Integer> hexToMd5BytesIndex(String regx, String values) {
        List<Integer> temp = new LinkedList<>();
        temp.clear();
        //区分分割类型
        if (StringUtils.indexOf(regx.toUpperCase(), "??") > 0) {
            String pattern = regx.toUpperCase().replaceAll("\\?\\?", "([1-9-A-Z-a-z]{2})");
            Pattern r = Pattern.compile(pattern.toUpperCase());
            Matcher m = r.matcher(values.toUpperCase());
            while (m.find()) {
                temp.add(m.start());
            }
            return temp;
        }
        return temp;
    }

    /**
     * 取左边字节 开头 FF22
     *
     * @param data FF22??5500??2B
     * @return 返回分割字符串数组
     */
    public static String[] hexToMd5Split(String data) {
        return data.split("\\?\\?");
    }

    /**
     * 取模糊数据 FF 22 ?? 55 00 ?? 2B
     *
     * @param regx 欲处理字符串
     * @return 返回字节长度
     */
    public static byte[] hexMd5ToBytesArrays(String regx) {
        String temp = regx.replaceAll("\\?\\?", "00");
        return ByteUtils.hexStrToBytes(temp);
    }



    /**
     * @deprecated 早期的方案 sundayByteIndex
     * 特征码索引匹配
     * @param data      源数据
     * @param md5HexStr 特征模糊数据
     * @param callBack 这是回调，他会返回索引第一个字母
     */
    public static void byteToMd5LinkOne(byte[] data, String md5HexStr, ByteIndexCallBack callBack) {
        int len = hexMd5ToBytesArrays(md5HexStr).length;
        String[] st = hexToMd5Split(md5HexStr);
        List<Integer> sbi = sundayByteIndex(data, hexStrToBytes(st[0].toUpperCase()));
        Iterator var6 = sbi.iterator();
        while(var6.hasNext()) {
            int i = (Integer)var6.next();
            byte[] tempData = new byte[len];
            int j = i;

            for(int z = 0; j < data.length && z <= len - 1; ++z) {
                tempData[z] = data[j];
                ++j;
            }

            if (hexToMd5BytesMatches(md5HexStr.toUpperCase(), bytesToHexString(tempData))) {
                callBack.call(i);
            }
        }
    }

    /**
     * 特征码索引匹配
     * @param data      源数据
     * @param md5HexStr 特征模糊数据
     * @param callBack 这是回调，他会返回索引第一个字母
     */
    public static void byteToMd5Link(byte[] data, String md5HexStr, ByteIndexCallBack callBack) {
        String[] spStr = StringUtils.split(md5HexStr, "??");//分割特征
        int len = ByteUtils.hexMd5ToBytesArrays(md5HexStr).length;//取总长度
        if (spStr.length > 1) {
            if (data.length > len) {
                int index = 0;
                byte[] bp = ByteUtils.hexStrToBytes(spStr[0]);
                index = ByteUtils.indexOf(data, bp, index);
                if (index > -1) {
                    do {
                        byte[] temp = new byte[len];
                        for (int i = index, j = 0; i < index + len && j < len; i++, j++) {
                            if (data.length > index + len) {
                                temp[j] = data[i];
                            } else {
                                break;
                            }
                        }
                        if (hexToMd5BytesMatches(md5HexStr.toUpperCase(), ByteUtils.bytesToHexString(temp).toUpperCase())) {
                            callBack.call(index);
                        }
                        index = ByteUtils.indexOf(data, bp, index + 1);
                    } while (index > len);
                }

            }
        }
    }

    /**
     * 快速搜索条件
     *
     * @param data     源数据
     * @param dec 搜索的字段
     * @param callBack 回调
     */
    public static void byteToBytesArray(byte[] data, byte[] dec, ByteIndexCallBack callBack) {
        List<Integer> sbi = sundayByteIndex(data, dec);//获取在内存的首位置
        for (int i : sbi) {
            callBack.call(i);
        }
    }

    /**
     * 读取字节数组指定分段的数据段
     *
     * @param data          源数据，
     * @param HexData       需要匹配的长度
     * @param index         长度可以按照你的需求 常规是：1、 2 、 4 、8 、16
     * @param MemoryAddress 内存地址
     * @param callBack      回调 call区分32和64位
     */
    public static void bytesToReadIndexArray(byte[] data, byte[] HexData, int index, int MemoryAddress, ByteArraysToByte callBack) {
        int BytesIndex = 0;
        BytesIndex += index;
        do {
            if (BytesIndex == -1) {
                break;
            }
            callBack.call(ByteUtils.subByteLeftArray(data, BytesIndex, BytesIndex + index), (MemoryAddress + BytesIndex));
        } while ((BytesIndex = ByteUtils.indexOf(data, HexData, BytesIndex + index)) != -1);
    }

    /**
     * 读取字节数组指定分段的数据段
     *
     * @param data          源数据，
     * @param HexData       需要匹配的长度
     * @param index         长度可以按照你的需求 常规是：1、 2 、 4 、8 、16
     * @param MemoryAddress 内存地址
     * @param callBack      回调 call区分32和64位
     */
    public static void bytesToReadIndexArray(byte[] data, byte[] HexData, int index, long MemoryAddress, ByteArraysToByte callBack) {
        int BytesIndex = 0;
        BytesIndex += index;
        do {
            if (BytesIndex == -1) {
                break;
            }
            callBack.call(ByteUtils.subByteLeftArray(data, BytesIndex, BytesIndex + index), (MemoryAddress + BytesIndex));
        } while ((BytesIndex = ByteUtils.indexOf(data, HexData, BytesIndex + index)) != -1);
    }

    /**
     * Ps:说实话，这段是我网上找的，因为此项目多次开发维护大脑不够用了，经过多次的重构、重写、我已经彻底麻木了。
     * 寻找字节数组
     *
     * @param arr           被寻找的字节数组
     * @param key           欲寻找的字节数组
     * @param beginPosition 起始位置
     * @return 返回成功为位置 失败 -1
     */
    public static int indexOf(byte[] arr, byte[] key, int beginPosition) {
        if (arr == null || arr.length <= beginPosition) {
            return -1;
        }
        if (key == null || arr.length < key.length) {
            return -1;
        }
        int i, j = -1;
        for (i = beginPosition; i < arr.length; i++) {
            if (arr.length < key.length + i) {
                break;
            }
            for (j = 0; j < key.length; j++) {
                if (arr[i + j] != key[j])
                    break;
            }
            if (j == key.length) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Ps:说实话，这段是我网上找的，因为此项目多次开发维护大脑不够用了，经过多次的重构、重写、我已经彻底麻木了。
     * 寻找字符数组
     *
     * @param arr           被寻找的字符数组
     * @param key           欲寻找的字符数组
     * @param beginPosition 其实位置
     * @return 返回成功为位置 失败 -1
     */
    public static int indexOf(char[] arr, char[] key, int beginPosition) {
        if (arr == null || arr.length <= beginPosition) {
            return -1;
        }
        if (key == null || arr.length < key.length) {
            return -1;
        }
        int i, j = -1;
        for (i = beginPosition; i < arr.length; i++) {
            if (arr.length < key.length + i) {
                break;
            }
            for (j = 0; j < key.length; j++) {
                if (arr[i + j] != key[j])
                    break;
            }
            if (j == key.length) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 取字节数组左边
     *
     * @param arr      被取的字节数组
     * @param leftLen  取出长度
     * @param rightlen 取出长度
     * @return 返回中间的字节数组
     */
    public static byte[] subByteLeftArray(byte[] arr, int leftLen, int rightlen) {
        if (arr == null || arr.length <= 0) {
            return null;
        }
        byte[] temp = new byte[rightlen - leftLen];
        for (int i = leftLen, j = 0; i < rightlen; i++, j++) {
            if (i <= rightlen) {
                temp[j] = arr[i];
            }
        }
        return temp;
    }

    /**
     * 取字节数组中间
     *
     * @param arr        被取的字节数组
     * @param leftArray  左边的字节数组
     * @param rightArray 右边的字节数组
     * @return 返回中间的字节数组
     */
    public static byte[] subByteArray(byte[] arr, byte[] leftArray, byte[] rightArray) {

        if (arr == null || arr.length <= 0) {
            return null;
        }
        if (leftArray == null || leftArray.length <= 0) {
            return null;
        }
        if (rightArray == null || rightArray.length <= 0) {
            return null;
        }

        int leftLength = indexOf(arr, leftArray, 0) + leftArray.length;
        if (leftLength < 0) {
            return null;
        }
        int rightLength = indexOf(arr, rightArray, leftLength);
        if (rightLength < 0) {
            return null;
        }
        byte[] temp = new byte[rightLength - leftLength];
        for (int i = leftLength, j = 0; i < rightLength; i++, j++) {
            if (i <= rightLength) {
                temp[j] = arr[i];
            }
        }

        return temp;
    }

    /**
     * 判断字节数组是否匹配
     *
     * @param arr 预被匹配的字节数组
     * @param key 被匹配的字节数组
     * @return 成功匹配true  失败匹配false
     */
    public static boolean equalsByteArray(byte[] arr, byte[] key) {
        if (arr.length != key.length) {
            return false;
        }
        for (int i = arr.length - 1, j = key.length - 1; i > 0 && j > 0; i--, j--) {
            if (arr[i] != key[j]) {
                return false;
            }
        }
        if (indexOf(arr, key, 0) == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断字节数组是否匹配
     *
     * @param arr 预被匹配的字节数组
     * @param key 被匹配的字节数组
     * @return 成功匹配true  失败匹配false
     */
    public static boolean equalsCharArray(char[] arr, char[] key) {
        if (arr.length != key.length) {
            return false;
        }
        for (int i = arr.length - 1, j = key.length - 1; i > 0 && j > 0; i--, j--) {
            if (arr[i] != key[j]) {
                return false;
            }
        }
        if (indexOf(arr, key, 0) == 0) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 整型转HEX
     *
     * @param value 类型数值
     * @return 返回HEX字符串
     */
    public static String intToHexString(int value) {
        return Integer.toHexString(value);
    }

    /**
     * 长整型转HEX
     *
     * @param value 类型数值
     * @return 返回HEX字符串
     */
    public static String longToHexString(long value) {
        return Long.toHexString(value);
    }

    /**
     * 浮点型转HEX
     *
     * @param value 类型数值
     * @return 返回HEX字符串
     */
    public static String floatToHexString(long value) {
        return Float.toHexString(value);
    }

    /**
     * 双精度型转HEX
     *
     * @param value 类型数值
     * @return 返回HEX字符串
     */
    public static String doubleToHexString(double value) {
        return Double.toHexString(value);
    }

    /**
     * 短整数转HEX
     *
     * @param value 类型数值
     * @return 返回HEX字符串
     */
    public static String shortToHexString(double value) {
        return Double.toHexString(value);
    }

    /**
     * 字节数组转HEX
     *
     * @param bytes 字节数组
     * @return 返回HEX
     */
    public static String bytesToHexString(byte[] bytes) {
        String hex = new BigInteger(1, bytes).toString(16);
        return hex;
    }

    /**
     * Hex转有符号字节
     *
     * @param inHex 字符串HEX
     * @return 返回结果 返回结果
     */
    public static byte[] hexStrToBytes(String inHex) {
        int hexlen = inHex.length();
        byte[] result;
        if (hexlen % 2 == 1) {
            //奇数
            hexlen++;
            result = new byte[(hexlen / 2)];
            inHex = "0" + inHex;
        } else {
            //偶数
            result = new byte[(hexlen / 2)];
        }
        int j = 0;
        for (int i = 0; i < hexlen; i += 2) {
            result[j] = hexStrToByte(inHex.substring(i, i + 2));
            j++;
        }
        return result;
    }

    /**
     * 十六进制 转一个字节
     *
     * @param inHex 十六进制
     * @return
     */
    private static byte hexStrToByte(String inHex) {
        return (byte) Integer.parseInt(inHex, 16);
    }

    /**
     * 十六进制 转 十进制
     *
     * @param inHex 十六进制
     * @return 返回十进制
     */
    public static int hexStrToInt1(String inHex) {
        return Integer.valueOf(inHex, 16);
    }

    /**
     * 十六进制转短整数
     *
     * @param inHex 十六进制
     * @return 短整数
     */
    public static short hexToShort1(String inHex) {
        return Short.valueOf(inHex, 2);
    }

    /**
     * 字节数组转短整数
     *
     * @param bytes 字节数组
     * @return 短整数
     */
    public static short bytesArrayToShort(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length).put(bytes);
        buffer.flip();
        return buffer.getShort();
    }

    /**
     * 十六进制转长整数
     *
     * @param inHex 十六进制
     * @return 长整数
     */
    public static long hexToLong3(String inHex) {
        return Long.valueOf(inHex, 2);
    }

    /**
     * 字节数组转长整数
     *
     * @param bytes 字节数组
     * @return 长整数
     */
    public static long bytesArrayToLong2(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length).put(bytes);
        buffer.flip();
        return buffer.getLong();
    }

    /**
     * 字节数组转浮点数
     *
     * @param bytes 字节数组
     * @return 浮点数
     */
    public static float bytesArrayToFloat2(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length).put(bytes);
        buffer.flip();
        return buffer.getFloat();
    }

    /**
     * 字节数组转双精度
     *
     * @param bytes 字节数组
     * @return 双精度
     */
    public static double bytesArrayToDouble2(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length).put(bytes);
        buffer.flip();
        return buffer.getDouble();
    }

    /**
     * HexToInt 十六转十
     *
     * @param content 十六进制文本
     * @return 返回结果
     */
    public static int hexStrToInt2(String content) {
        int number = 0;
        Map<String, Integer> HexToIntmap = new HashMap<>();
        String[] HighLetter = {"A", "B", "C", "D", "E", "F"};
        for (int i = 0; i <= 9; i++) {
            HexToIntmap.put(i + "", i);
        }
        for (int j = 10; j < HighLetter.length + 10; j++) {
            HexToIntmap.put(HighLetter[j - 10], j);
        }
        String[] str = new String[content.length()];
        for (int i = 0; i < str.length; i++) {
            str[i] = content.substring(i, i + 1);
        }
        for (int i = 0; i < str.length; i++) {
            number += HexToIntmap.get(str[i]) * Math.pow(16, str.length - 1 - i);
        }
        HexToIntmap = null;
        return number;
    }

    /**
     * 十六到十
     *
     * @param lpData 字节数组
     * @return 返回64位的十进制
     */
    public static long hexToInt3(byte[] lpData) {
        return ByteUtils.bytesArrayToLong1(ByteUtils.hexStrToBytes(ByteUtils.bytesToHexString(lpData).substring(0, 16)));
    }

    /**
     * 十六到十
     *
     * @param lpData 字节数组
     * @return 返回32位的十进制
     */
    public static int hexToInt4(byte[] lpData) {
        return ByteUtils.bytesArrayToInt(ByteUtils.hexStrToBytes(ByteUtils.bytesToHexString(lpData).substring(0, 8)));
    }

    /**
     * IntToHex 十转十六
     *
     * @param n 返回结果
     * @return 返回结果
     */
    public static String intToHex(int n) {
        StringBuilder sb = new StringBuilder(8);
        String a;
        char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        while (n != 0) {
            sb = sb.append(b[n % 16]);
            n = n / 16;
        }
        a = sb.reverse().toString();
        sb = null;
        return a;
    }

    /**
     * HEX 转10进制   64位可用
     *
     * @param hex 十六进制文本
     * @return 返回十进制长整型
     */
    public static long hexToLong1(String hex) {
        return Long.parseLong(hex, 16);
    }

    /**
     * HEX 转10进制   64位可用
     *
     * @param hex 十六进制文本
     * @return 返回十进制长整型
     */
    public static long hexToLong2(String hex) {
        BigInteger bi = new BigInteger(hex, 16);
        return bi.longValue();
    }

    /**
     * 字节起始位置开始
     *
     * @param bytes         原字节
     * @param beginPosition 起始位置
     * @return 返回字节
     */
    public static byte[] byteToLeftIndexStart(byte[] bytes, int beginPosition) {
        if (bytes == null || bytes.length <= beginPosition) {
            return null;
        }
        byte[] temp = new byte[bytes.length];
        for (int i = beginPosition, j = 0; i < bytes.length; i++, j++) {
            temp[j] = bytes[i];
        }
        return temp;
    }

    /**
     * 字符起始位置开始
     *
     * @param bytes         原字节
     * @param beginPosition 起始位置
     * @return 返回字节
     */
    public static char[] charToLeftIndexStart(char[] bytes, int beginPosition) {
        if (bytes == null || bytes.length <= beginPosition) {
            return null;
        }
        char[] temp = new char[bytes.length];
        for (int i = beginPosition, j = 0; i < bytes.length; i++, j++) {
            temp[j] = bytes[i];
        }
        return temp;
    }

    /**
     * 字节数组合并
     *
     * @param bt1 数组1
     * @param bt2 数组2
     * @return 返回合并字节
     */
    public static byte[] byteMerger(byte[] bt1, byte[] bt2) {
        byte[] bt3 = new byte[bt1.length + bt2.length];
        System.arraycopy(bt1, 0, bt3, 0, bt1.length);
        System.arraycopy(bt2, 0, bt3, bt1.length, bt2.length);
        return bt3;
    }

    /**
     * 字节转字节
     * 四字节转二字节
     *
     * @param data 需要转换的字节 比如 62000000 转换以后 6200
     * @return 返回 4字节转2字节
     */
    public static byte[] byteArrayToUtf8ByteArrays(byte[] data) {
        byte[] temp = new byte[data.length];
        int j = 0;
        if (data.length % 2 == 0) {
            //取2 的倍数
            for (int i = 0; i < data.length - 1; i++) {
                if (i % 2 == 0) {
                    //我要固定保证左边和右边  不让它出现中间到右边的问题  因为是取2字节  要保证2的倍数   固定在2的倍数里面取
                    if (data[i] != 0 && data[i + 1] != 0 || data[i] != 0 && data[i + 1] == 0) {
                        temp[j++] = data[i];
                        temp[j++] = data[i + 1];
                    }
                }
            }
            return temp;
        } else {
            System.out.println("请返回正确的4 或 8 字节");
            return null;
        }
    }


    /**
     * 长整型转Hex
     *
     * @param value 10进制
     * @return 返回HEX
     */
    public static String longToByteHex(long value) {
        return ModuleOperationUtilsJNI.tenToHex(value);
    }

    /**
     * 整型转Hex
     *
     * @param value 10进制
     * @return 返回HEX
     */
    public static String intToByteHex(int value) {
        return ModuleOperationUtilsJNI.tenToHex(value);
    }

    //注意每次都是从后向前
    private static int contains(char[] str, char ch) {
        for (int i = str.length - 1; i >= 0; i--) {
            if (str[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    private static int contains(byte[] str, byte ch) {
        for (int i = str.length - 1; i >= 0; i--) {
            if (str[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * https://blog.csdn.net/futureflyme/article/details/72763966 原地址
     *
     * @param s 原文本
     * @param p 预搜索的文本
     * @return 返回结果文本
     */
    public static List<String> sundayString(String s, String p) {
        char[] sarray = s.toCharArray();
        char[] parray = p.toCharArray();
        int slen = s.length();
        int plen = p.length();
        List<String> saveIndex = new LinkedList<>();
        saveIndex.clear();
        int i = 0, j = 0;
        while (i <= slen - plen + j) {//这句话控制索引i,j的范围
            if (sarray[i] != parray[j]) {//假如主串的sarry[i]与模式串的parray[j]不相等
                if (i == slen - plen + j) {//
                    break;//假如主串的sarry[i]与模式串的parray[j]不相等,并且i=slen-plen+j,说明这已经
                    //是在和主串中最后可能相等的字符段比较了,并且不相等,说明后面就再也没有相等的了,所以
                    //跳出循环,结束匹配
                }
                //假如是主串的中间字段与模式串匹配，且结果不匹配
                //则就从模式串的最后面开始,(注意是从后向前)向前遍历,找出模式串的后一位在对应的母串的字符是否在子串中存在
                int pos = contains(parray, sarray[i + plen - j]);
                if (pos == -1) {//表示不存在
                    i = i + plen + 1 - j;
                    j = 0;
                } else {
                    i = i + plen - pos - j;
                    j = 0;
                }
            } else {//假如主串的sarry[i]与模式串的parray[j]相等,则继续下面的操作
                if (j == plen - 1) {//判断模式串的索引j是不是已经到达模式串的最后位置，
                    //j==plen-1证明在主串中已经找到一个模式串的位置,
                    //且目前主串尾部的索引为i,主串首部的索引为i-j,打印模式串匹配的第一个位置
                    saveIndex.add(s.substring((i - j), i + 1));
                    //然后主串右移一个位置,再和模式串的首字符比较,从而寻找下一个匹配的位置
                    i = i - j + 1;
                    j = 0;
                } else {
                    //假如模式串的索引j!=plen-1,说明模式串还没有匹配完,则i++,j++继续匹配,
                    i++;
                    j++;
                }
            }
        }
        return saveIndex;
    }

    /**
     * https://blog.csdn.net/futureflyme/article/details/72763966 原地址
     *
     * @param s 原文本
     * @param p 预搜索的文本
     * @return 返回索引位置
     */
    public static List<Integer> sundayStringIndex(String s, String p) {
        char[] sarray = s.toCharArray();
        char[] parray = p.toCharArray();
        int slen = s.length();
        int plen = p.length();
        List<Integer> saveIndex = new LinkedList<>();
        saveIndex.clear();
        int i = 0, j = 0;
        while (i <= slen - plen + j) {
            if (sarray[i] != parray[j]) {
                if (i == slen - plen + j) {
                    break;
                }
                int pos = contains(parray, sarray[i + plen - j]);
                if (pos == -1) {//表示不存在
                    i = i + plen + 1 - j;
                    j = 0;
                } else {
                    i = i + plen - pos - j;
                    j = 0;
                }
            } else {
                if (j == plen - 1) {
                    saveIndex.add((i - j));
                    i = i - j + 1;
                    j = 0;
                } else {
                    i++;
                    j++;
                }
            }
        }
        return saveIndex;
    }

    public static List<Integer> sundayByteIndex(byte[] s, byte[] p) {
        byte[] sarray = s;
        byte[] parray = p;
        int slen = s.length;
        int plen = p.length;
        List<Integer> saveIndex = new LinkedList<>();
        saveIndex.clear();
        int i = 0, j = 0;
        while (i <= slen - plen + j) {
            if (sarray[i] != parray[j]) {
                if (i == slen - plen + j) {
                    break;
                }
                int pos = contains(parray, sarray[i + plen - j]);
                if (pos == -1) {
                    i = i + plen + 1 - j;
                    j = 0;
                } else {
                    i = i + plen - pos - j;
                    j = 0;
                }
            } else {
                if (j == plen - 1) {
                    saveIndex.add((i - j));
                    i = i - j + 1;
                    j = 0;
                } else {
                    i++;
                    j++;
                }
            }
        }
        return saveIndex;
    }

    /**
     * BF搜索
     *
     * @param str 原文本
     * @param sub 预搜索的文本
     * @return 返回索引位置
     */
    public static int bfString(String str, String sub) {
        if (str == null || sub == null) return -1;
        int strLen = str.length();
        int subLen = sub.length();
        int i = 0;
        int j = 0;
        while (i < strLen && j < subLen) {
            if (str.charAt(i) == sub.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - j + 1; // i-j就是回退到i原来的位置  +1 就是i的下一位开始。同时恢复j的值
                j = 0;
            }
        }
        if (j >= subLen) {
            return i - j; //此时已经找到该下标
        }
        return -1; //没有找到
    }

    private static void getNext(int[] next, String sub) {
        next[0] = -1;
        next[1] = 0;
        int i = 2; // next数组的下标
        int k = 0;
        while (i < sub.length()) {
            //说明next数组没有遍历完
            if ((k == -1) || sub.charAt(k) == sub.charAt(i - 1)) { // 此时k等于-1说明走到了 sub的第一位前一位 所以就必须让k++；否则会越界
                next[i] = k + 1; // 由k的规律中可得
                i++;
                k++;
            } else {
                k = next[k];
            }
        }
    }

    /**
     * KPM算法
     *
     * @param str 原文本
     * @param sub 预搜索的文本
     * @param pos 起始位置
     * @return 返回索引位置
     */
    public static int kmpString(String str, String sub, int pos) {
        int i = pos;
        int j = 0;
        int strLen = str.length();
        int subLen = sub.length();
        int[] next = new int[subLen];
        getNext(next, sub);
        while (i < strLen && j < subLen) {
            if ((j == -1) || (sub.charAt(j) == str.charAt(i))) { // 当j等于-1的时候说明走到了 sub的第一位前一位，所以需要重新让j指向sub的0下标。
                i++;
                j++;
                // 满足条件向后走 并且进行匹配
            } else {
                j = next[j]; // 没有匹配上的情况下 j就进行回退到 next数组中的下标位置。
            }
        }
        if (j >= subLen) {
            return i - j;
        } else {
            return -1;
        }
    }


    /**
     * 取字节数组 两坐标位置的值
     * @param bytes 源字节数组
     * @param start 左边坐标
     * @param end   向右取出的长度
     * @return 返回坐标位置 + 长度的内容
     */
    public static byte[] subBytes(byte[] bytes, int start, int end) {
        byte[] temp = new byte[end];
        for (int i = start, j = 0; i < start + end && j < temp.length; i++, j++) {
            temp[j] = bytes[i];
        }
        return temp;
    }

    /**
     * 二维数组取出最大的值
     * @param array  二维数组
     * @return 返回最大的值
     */
    public static int findMaxNumberTwo(int[][] array) {
        int maxNumber = Integer.MIN_VALUE;
        for (int[] row : array) {
            for (int num : row) {
                if (num > maxNumber) {
                    maxNumber = num;
                }
            }
        }

        return maxNumber;
    }
    /**
     * 一维数组取出最大的值
     * @param array  一维数组
     * @return 返回最大的值
     */
    public static int findMaxNumberOne(int[] array) {
        int maxNumber = Integer.MIN_VALUE;
        for (int row : array) {
            if (row > maxNumber) {
                maxNumber = row;
            }
        }
        return maxNumber;
    }
    /**
     * 将文件转换为字节数组
     * @param file 要转换的文件
     * @return 包含文件内容的字节数组
     */
    public static byte[] convertFileToByteArray(File file)  {
       try {
           // 检查文件是否存在
           if (!file.exists()) {
               throw new FileNotFoundException("文件不存在: " + file.getAbsolutePath());
           }

           // 检查是否为文件（不是目录）
           if (!file.isFile()) {
               throw new IOException("不是一个文件: " + file.getAbsolutePath());
           }

           // 使用try-with-resources自动关闭流
           try (FileInputStream fis = new FileInputStream(file);
                ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

               byte[] buffer = new byte[1024];
               int bytesRead;

               // 读取文件内容并写入字节数组输出流
               while ((bytesRead = fis.read(buffer)) != -1) {
                   bos.write(buffer, 0, bytesRead);
               }

               // 将输出流转换为字节数组并返回
               return bos.toByteArray();
           }
       }catch (Exception e){e.printStackTrace();}
       return null;
    }
}
