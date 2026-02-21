# JSCM框架完整API文档

## 框架概述

JSCM框架是一个基于Java JNA的Windows系统操作框架，提供了丰富的Windows API封装，包括窗口操作、进程线程管理、内存操作、注册表操作、热键驱动等功能。本框架集成了三大模块进行处理，参考了精易模块、超级模块、乐易模块的设计思想，为Java开发者提供了强大的Windows系统底层操作能力。

框架支持X86和X64平台，包含DD键鼠驱动、幽灵键鼠驱动等硬件抽象层功能，同时提供了完整的编码转换工具类，支持UTF-8、Unicode、ASCII等多种编码格式的相互转换。框架还集成了CapStone反汇编引擎，支持内存特征码搜索和反汇编分析功能。QQ群：128828632

## 环境配置

### 依赖配置

在使用本框架前，需要进行以下环境配置：

**开发环境配置示例：**

```java
PathFileJSCM.setDebugFile32("C:\\Users\\www10\\IdeaProjects\\scmJnaApi\\JscmFunction\\Jscm\\_int\\Jscm\\release\\win32\\linker\\");
PathFileJSCM.setDebugFile64("G:\\Users\\www10\\IdeaProjects\\scmJnaApi\\JscmFunction\\Jscm\\_int\\Jscm\\release\\x64\\linker\\");
PathFileJSCM.setIsDebug(true);
```

**生产环境配置示例：**

```java
PathFileJSCM.setDebugFile32(PathFileJSCM.getJarPath() + "demo\\\\x86\\\\");
PathFileJSCM.setDebugFile64(PathFileJSCM.getJarPath() + "demo\\\\x64\\\\");
PathFileJSCM.setIsDebug(false);
```

### 内存配置

如果出现内存不足错误，请在运行参数中配置：

```
-encoding utf-8 -charset utf-8 -XX:-UseGCOverheadLimit -Xms1T -Xmx1T -XX:MaxPermSize=1T
```

### 注意事项

如果出现"Can't find dependent libraries"错误，请将jar包中对应的系统版本号的jawt.dll文件放在java.exe的运行目录下，或者从java目录下直接复制到对应位置即可，因为界面库依赖了Swing库。

---

## 核心类说明

### ByteUtils 编码转换工具类

本类是全新的编码转换工具类，提供了各种数据类型与字节数组之间的转换功能，以及多种编码格式的字符串转换支持。

#### 数值类型转换方法

**intToBytesArray** - int类型转字节数组

```java
/**
 * 此方法是将int类型的数值转换成byte字节数组
 * @param value 整数
 * @return 返回字节数组
 */
public static byte[] intToBytesArray(int value)
```

**bytesArrayToInt** - 字节数组转int类型

```java
/**
 * 此方法是将byte字节数组转换成对应的int数值
 * @param bytes int字节数组
 * @return 返回对应的数值
 */
public static int bytesArrayToInt(byte[] bytes)
```

**longToBytesArray** - long类型转字节数组

```java
/**
 * 此方法是将long类型转换成byte字节数组
 * @param value 长整型
 * @return 返回字节数组
 */
public static byte[] longToBytesArray(long value)
```

**bytesArrayToLong1** - 字节数组转long类型

```java
/**
 * 此方法是将long类型的字节数组转换成对应的数值
 * @param bytes 数组
 * @return 返回对应的数值
 */
public static long bytesArrayToLong1(byte[] bytes)
```

**shortToBytesArray** - short类型转字节数组

```java
/**
 * 此方式是将short转换成对应的字节数组
 * @param value 短整型
 * @return 返回字节数组
 */
public static byte[] shortToBytesArray(short value)
```

**bytesArrayToShort1 / bytesArrayToShort2** - 字节数组转short类型

```java
/**
 * 此方法是将对应的字节数组转换成short类型数值
 * @param bytes 转short的字节数组
 * @return 返回short数值
 */
public static short bytesArrayToShort1(byte[] bytes)
public static short bytesArrayToShort2(byte[] bytes)
```

**doubleToBytesArray / bytesArrayToDouble1** - double类型转换

```java
/**
 * 此方法是将double类型转换成对应的字节数组
 * @param value double数值
 * @return 返回字节数组
 */
public static byte[] doubleToBytesArray(double value)

/**
 * 此方法是将字节数组转换成对应的类型数值
 * @param bytes 转double的字节数组
 * @return 返回对应数值
 */
public static double bytesArrayToDouble1(byte[] bytes)
```

**floatToBytesArray / bytesArrayToFloat1** - float类型转换

```java
/**
 * 此方法是将对应的float转换成对应的字节数组
 * @param value float类型数值
 * @return 返回字节数组
 */
public static byte[] floatToBytesArray(float value)

/**
 * 此方法是将对应的字节数组转换成类型数值
 * @param bytes 转float的字节数组
 * @return 返回对应数值
 */
public static float bytesArrayToFloat1(byte[] bytes)
```

**charToBytesArray / bytesArrayToChar** - char类型转换

```java
/**
 * 此方法是将char类型数值转换成字节数组
 * @param value char类型数值
 * @return 返回字节数组
 */
public static byte[] charToBytesArray(char value)

/**
 * 此方法是将字节数组转换成对应的类型数值
 * @param bytes 转char的字节数组
 * @return 返回对应数值
 */
public static char bytesArrayToChar(byte[] bytes)
```

#### 编码转换方法

**bytesArrayToUtf8WideString** - UTF-8字节数组转宽字符串

```java
/**
 * 常用
 * 字节数组转宽字符串 包含中文的
 * @param bs 宽字符utf8转UniCode数组
 * @return 返回字符串
 */
public static String bytesArrayToUtf8WideString(byte[] bs)
```

**charsArrayToUtf8WideString1 / charsArrayToUtf8WideString2** - 字符数组转宽字符串

```java
/**
 * 宽字符数组转宽字符串 包含中文的
 * @param bs 宽字符数组
 * @return 返回宽字符串
 */
public static String charsArrayToUtf8WideString1(char[] bs)

/**
 * 字符数组转宽字符串
 * @param chars 字符数组
 * @return 返回宽字符数组
 */
public static String charsArrayToUtf8WideString2(char[] chars)
```

**utf8WideStringToCharsArray** - UTF-8宽字符串转字符数组

```java
/**
 * utf8 字符串 转 宽字符数组
 * @param value 字符串值
 * @param isEnd 是否包含结束符号
 * @return 返回UTF8宽字符数组
 */
public static char[] utf8WideStringToCharsArray(String value, boolean isEnd)
```

**bytesArrayToAsciiWideString** - ASCII字节数组转字符串

```java
/**
 * 常用
 * Ascii字节数组 转换成字符串
 * @param bs ascii 字节数组
 * @return 返回字符串
 */
public static String bytesArrayToAsciiWideString(byte[] bs)
```

**asciiWideStringToBytesArray** - ASCII字符串转字节数组

```java
/**
 * Ascii符串 转 字节数组
 * @param value 字符串值
 * @param isEnd 是否包含结尾
 * @return 成功返回字节数组
 */
public static byte[] asciiWideStringToBytesArray(String value, boolean isEnd)
```

**bytesArrayUTF16ToWideString** - UTF-16字节数组转字符串

```java
/**
 * 字节数组转字符串（全参数重载，最灵活）
 * @param byteArray 待转换的字节数组（可为null或空）
 * @param filterNullChar 是否过滤Unicode空字符（\u0000）
 * @return 转换后的字符串（null或空数组返回空字符串）
 */
public static String bytesArrayToWideString(byte[] byteArray, boolean filterNullChar)
```

**utf8ToAsciiArray** - UTF-8转ASCII字节数组

```java
/**
 * utf8 转Ascii byte字节数组
 * @param text UTF8的常规内容
 * @return 返回字节数组
 */
public static byte[] utf8ToAsciiArray(String text)
```

**asciiArrayToUtf8** - ASCII字节数组转UTF-8

```java
/**
 * Ascii bytes 转 utf8
 * @param textBytes ASCII 字节编码
 * @return 返回文本
 */
public static String asciiArrayToUtf8(byte[] textBytes)
```

**utf8ToBytesArray / bytesArrayToUtf8** - UTF-8字节数组转换

```java
/**
 * UTF-8 转 byte字节数组
 * @param text UTF8的常规内容
 * @return 返回字节数组
 */
public static byte[] utf8ToBytesArray(String text)

/**
 * bytes 转 UTF-8
 * @param textBytes UTF-8 字节编码
 * @return 返回文本
 */
public static String bytesArrayToUtf8(byte[] textBytes)
```

**utf8ToUniCodeArray / uniCodeArrayToUtf8** - Unicode编码转换

```java
/**
 * utf8 转 UniCode
 * @param text UTF-8 文本
 * @return 返回UniCode 字节
 */
public static byte[] utf8ToUniCodeArray(String text)

/**
 * UniCode 转 utf8
 * @param textBytes UniCode 字节数组
 * @return 返回字符串
 */
public static String uniCodeArrayToUtf8(byte[] textBytes)
```

#### 进制转换方法

**intToHexString** - int转HEX字符串

```java
/**
 * 整型转HEX
 * @param value 类型数值
 * @return 返回HEX字符串
 */
public static String intToHexString(int value)
```

**longToHexString** - long转HEX字符串

```java
/**
 * 长整型转HEX
 * @param value 类型数值
 * @return 返回HEX字符串
 */
public static String longToHexString(long value)
```

**bytesToHexString** - 字节数组转HEX字符串

```java
/**
 * 字节数组转HEX
 * @param bytes 字节数组
 * @return 返回HEX字符串
 */
public static String bytesToHexString(byte[] bytes)
```

**hexStrToBytes** - HEX字符串转字节数组

```java
/**
 * Hex转有符号字节
 * @param inHex 字符串HEX
 * @return 返回字节数组
 */
public static byte[] hexStrToBytes(String inHex)
```

**hexStrToInt1 / hexStrToInt2** - HEX转十进制int

```java
/**
 * 十六进制 转 十进制
 * @param inHex 十六进制
 * @return 返回十进制
 */
public static int hexStrToInt1(String inHex)

/**
 * HexToInt 十六转十
 * @param content 十六进制文本
 * @return 返回十进制结果
 */
public static int hexStrToInt2(String content)
```

**hexToLong1 / hexToLong2 / hexToLong3** - HEX转long类型

```java
/**
 * HEX 转10进制   64位可用
 * @param hex 十六进制文本
 * @return 返回十进制长整型
 */
public static long hexToLong1(String hex)
public static long hexToLong2(String hex)

/**
 * 十六到十
 * @param lpData 字节数组
 * @return 返回64位的十进制
 */
public static long hexToInt3(byte[] lpData)
```

**intToHex** - 十进制转十六进制

```java
/**
 * IntToHex 十转十六
 * @param n 十进制数值
 * @return 返回十六进制字符串
 */
public static String intToHex(int n)
```

#### 字节数组操作方法

**indexOf** - 字节数组查找

```java
/**
 * 寻找字节数组
 * @param arr 被寻找的字节数组
 * @param key 欲寻找的字节数组
 * @param beginPosition 起始位置
 * @return 返回成功为位置 失败 -1
 */
public static int indexOf(byte[] arr, byte[] key, int beginPosition)
```

**indexOf** - 字符数组查找

```java
/**
 * 寻找字符数组
 * @param arr 被寻找的字符数组
 * @param key 欲寻找的字符数组
 * @param beginPosition 起始位置
 * @return 返回成功为位置 失败 -1
 */
public static int indexOf(char[] arr, char[] key, int beginPosition)
```

**subByteLeftArray** - 取字节数组左边

```java
/**
 * 取字节数组左边
 * @param arr 被取的字节数组
 * @param leftLen 取出长度
 * @param rightlen 取出长度
 * @return 返回中间的字节数组
 */
public static byte[] subByteLeftArray(byte[] arr, int leftLen, int rightlen)
```

**subByteArray** - 取字节数组中间

```java
/**
 * 取字节数组中间
 * @param arr 被取的字节数组
 * @param leftArray 左边的字节数组
 * @param rightArray 右边的字节数组
 * @return 返回中间的字节数组
 */
public static byte[] subByteArray(byte[] arr, byte[] leftArray, byte[] rightArray)
```

**equalsByteArray** - 字节数组匹配

```java
/**
 * 判断字节数组是否匹配
 * @param arr 预被匹配的字节数组
 * @param key 被匹配的字节数组
 * @return 成功匹配true 失败匹配false
 */
public static boolean equalsByteArray(byte[] arr, byte[] key)
```

**equalsCharArray** - 字符数组匹配

```java
/**
 * 判断字符数组是否匹配
 * @param arr 预被匹配的字符数组
 * @param key 被匹配的字符数组
 * @return 成功匹配true 失败匹配false
 */
public static boolean equalsCharArray(char[] arr, char[] key)
```

**byteMerger** - 字节数组合并

```java
/**
 * 字节数组合并
 * @param bt1 数组1
 * @param bt2 数组2
 * @return 返回合并字节
 */
public static byte[] byteMerger(byte[] bt1, byte[] bt2)
```

**byteToLeftIndexStart** - 字节数组截取

```java
/**
 * 字节起始位置开始
 * @param bytes 原字节
 * @param beginPosition 起始位置
 * @return 返回截取后的字节数组
 */
public static byte[] byteToLeftIndexStart(byte[] bytes, int beginPosition)
```

**charToLeftIndexStart** - 字符数组截取

```java
/**
 * 字符起始位置开始
 * @param bytes 原字符数组
 * @param beginPosition 起始位置
 * @return 返回截取后的字符数组
 */
public static char[] charToLeftIndexStart(char[] bytes, int beginPosition)
```

#### 特征码搜索方法

**hexToMd5BytesMatches** - 特征码匹配

```java
/**
 * 特征码搜索 根据??方式进行搜索
 * @param regx FF 22 ?? 55 00 ?? 2B
 * @param values 与匹配的字符串
 * @return 返回匹配结果
 */
public static boolean hexToMd5BytesMatches(String regx, String values)
```

**hexToMd5BytesIndex** - 特征码索引搜索

```java
/**
 * 特征码搜索 根据??方式进行搜索
 * @param regx FF 22 ?? 55 00 ?? 2B
 * @param values 与匹配的字符串
 * @return 返回匹配索引列表
 */
public static List<Integer> hexToMd5BytesIndex(String regx, String values)
```

**hexMd5ToBytesArrays** - 特征码转字节数组

```java
/**
 * 取模糊数据 FF 22 ?? 55 00 ?? 2B
 * @param regx 欲处理字符串
 * @return 返回字节数组
 */
public static byte[] hexMd5ToBytesArrays(String regx)
```

**byteToMd5Link / byteToMd5LinkOne** - 特征码索引匹配

```java
/**
 * 特征码索引匹配
 * @param data 源数据
 * @param md5HexStr 特征模糊数据
 * @param callBack 回调函数，返回索引
 */
public static void byteToMd5Link(byte[] data, String md5HexStr, ByteIndexCallBack callBack)
public static void byteToMd5LinkOne(byte[] data, String md5HexStr, ByteIndexCallBack callBack)
```

#### 字符串搜索算法

**sundayString / sundayStringIndex** - Sunday算法字符串搜索

```java
/**
 * Sunday算法字符串搜索
 * @param s 原文本
 * @param p 预搜索的文本
 * @return 返回匹配文本列表
 */
public static List<String> sundayString(String s, String p)

/**
 * Sunday算法返回索引位置
 * @param s 原文本
 * @param p 预搜索的文本
 * @return 返回索引位置列表
 */
public static List<Integer> sundayStringIndex(String s, String p)
```

**sundayByteIndex** - Sunday算法字节搜索

```java
/**
 * Sunday算法字节数组搜索
 * @param s 源字节数组
 * @param p 搜索的字节数组
 * @return 返回索引位置列表
 */
public static List<Integer> sundayByteIndex(byte[] s, byte[] p)
```

**bfString** - BF算法字符串搜索

```java
/**
 * BF搜索
 * @param str 原文本
 * @param sub 预搜索的文本
 * @return 返回索引位置
 */
public static int bfString(String str, String sub)
```

**kmpString** - KMP算法字符串搜索

```java
/**
 * KMP算法
 * @param str 原文本
 * @param sub 预搜索的文本
 * @param pos 起始位置
 * @return 返回索引位置
 */
public static int kmpString(String str, String sub, int pos)
```

#### 通用转换方法

**getObjectTypeToByte** - 通用类型转字节数组

```java
/**
 * 根据类型将对象转换为字节数组
 * @param intValue 要转换的值
 * @param type 字符串体现类型
 * @return 返回字节数组
 *
 * 支持的类型：
 * - "int" : 整数转字节
 * - "long" : 长整数转字节
 * - "StringUTF8None" : UTF8字符串去0符号
 * - "StringUniCodeNone" : Unicode字符串去0符号
 * - "StringAsciiNone" : ASCII字符串去0符号
 * - "StringUTF8" : UTF8字符串含0符号
 * - "StringUniCode" : Unicode字符串含0符号
 * - "StringAscii" : ASCII字符串含0符号
 * - "byte" : 字节数组直接返回
 * - "short" : 短整数转字节
 * - "float" : 浮点数转字节
 * - "double" : 双精度转字节
 * - "char" : 字符转字节
 */
public static byte[] getObjectTypeToByte(Object intValue, String type)
```

#### 文件操作方法

**convertFileToByteArray** - 文件转字节数组

```java
/**
 * 将文件转换为字节数组
 * @param file 要转换的文件
 * @return 包含文件内容的字节数组
 */
public static byte[] convertFileToByteArray(File file)
```

---

### WindowOperationUtils 窗口操作工具类

本类所有窗口操作都在此类中，包含了庞大的窗口操作类库。集成了三大模块进行处理，创建于2022年1月6日，参考了精易模块、超级模块、乐易模块的设计。

#### 窗口基础操作方法

**windowInvalidateRect** - 窗口重绘

```java
/**
 * 窗口重画
 * 该InvalidateRect函数添加一个矩形到指定窗口的更新区域
 * @param hWnd 窗口句柄
 * @param empty 是否更新
 * @return 返回结果
 */
public static boolean windowInvalidateRect(int hWnd, boolean empty)
```

**windowSetWindowLongW** - 窗口隐藏任务按钮

```java
/**
 * 窗口隐藏任务按钮
 * 更改指定窗口的属性
 * @param hWnd 窗口句柄
 * @return 返回结果
 */
public static long windowSetWindowLongW(int hWnd)
```

**windowGetDesktopWindow** - 获取屏幕窗口句柄

```java
/**
 * 窗口取屏幕窗口句柄
 * @return 返回屏幕句柄
 */
public static long windowGetDesktopWindow()
```

**windowSendMessageTimeoutW** - 窗口是否响应

```java
/**
 * 窗口是否响应
 * @param hWnd 窗口句柄
 * @return 返回结果
 */
public static boolean windowSendMessageTimeoutW(int hWnd)
```

**windowGetCaretPos** - 获取窗口光标坐标

```java
/**
 * 获取窗口光标坐标
 * @param pPoint 返回坐标位置
 * @return 返回结果
 */
public static boolean windowGetCaretPos(TagPoint pPoint)
```

**enumWindowClose** - 枚举循环关闭窗口

```java
/**
 * 枚举循环关闭窗口
 * @param lpClassName 窗口类目
 * @param lpWindowName 窗口标题
 */
public static void enumWindowClose(String lpClassName, String lpWindowName)
```

#### 坐标转换方法

**getClientPoint** - 获取客户端坐标

```java
/**
 * 获取指定窗口句柄中客户端句柄内鼠标坐标位置
 * @param hWnd 窗口句柄
 * @return TagPoint 返回该类
 */
public static TagPoint getClientPoint(int hWnd)
```

**ClientToScreenPos** - 客户端坐标转屏幕坐标

```java
/**
 * 父窗口客户区坐标 → 屏幕坐标
 * @param hParentWnd 父窗口句柄
 * @param clientX 客户区X坐标
 * @param clientY 客户区Y坐标
 * @return TagPoint 屏幕坐标
 */
public static TagPoint ClientToScreenPos(int hParentWnd, long clientX, long clientY)
```

**ScreenToClientPos** - 屏幕坐标转客户端坐标

```java
/**
 * 屏幕坐标 → 父窗口客户区坐标
 * @param hParentWnd 父窗口句柄
 * @param screenX 屏幕X坐标
 * @param screenY 屏幕Y坐标
 * @return TagPoint 客户区坐标
 */
public static TagPoint ScreenToClientPos(int hParentWnd, long screenX, long screenY)
```

#### 窗口嵌入方法

**windowEmbeddedDesktop** - 嵌入桌面

```java
/**
 * 嵌入桌面
 * @param hWnd 窗口句柄
 * @return 返回新窗口句柄
 */
public static boolean windowEmbeddedDesktop(int hWnd)
```

**WindowEmbeddedWindow** - 嵌入指定窗口

```java
/**
 * 嵌入指定句柄
 * @param hWnd 窗口句柄
 * @param childHwnd 被嵌入的句柄
 * @return 返回新窗口句柄
 */
public static boolean WindowEmbeddedWindow(int hWnd, int childHwnd)
```

**GetWorkerW** - 获取桌面WorkerW句柄

```java
/**
 * 获取WorkerW句柄
 * @return 返回桌面句柄
 */
public static int GetWorkerW()
```

**windowSetParent** - 窗口置父

```java
/**
 * 窗口置父
 * @param parentHWND 父窗口句柄
 * @param javaHWND 子窗口句柄
 * @return 返回句柄
 */
public static boolean windowSetParent(int parentHWND, int javaHWND)
```

#### 窗口位置方法

**SetChildScreenPos** - 设置子窗口位置

```java
/**
 * 设置子窗口在父窗口内/外
 * @param hParentWnd 父窗口句柄
 * @param hChildWnd 子窗口句柄
 * @param screenX 屏幕X坐标
 * @param screenY 屏幕Y坐标
 * @param width 宽
 * @param height 高
 */
public static void SetChildScreenPos(int hParentWnd, int hChildWnd, int screenX, int screenY, int width, int height)
```

**UpdateChildWindowPos** - 更新子窗口位置

```java
/**
 * 更新子窗口位置
 * @param hParent 父窗口句柄
 * @param hChild 子窗口句柄
 * @param right_Bottom_XOffset 右下X偏移
 * @param right_Bottom_YOffset 右下Y偏移
 */
public static void UpdateChildWindowPos(int hParent, int hChild, int right_Bottom_XOffset, int right_Bottom_YOffset)
```

**GetChildScreenPos** - 获取子窗口屏幕坐标

```java
/**
 * 获取子窗口当前屏幕坐标
 * @param hParentWnd 父窗口句柄
 * @param hChildWnd 子窗口句柄
 * @return TagPoint 屏幕坐标
 */
public static TagPoint GetChildScreenPos(int hParentWnd, int hChildWnd)
```

**SetChildPosAlignScreen** - 设置子窗口对齐屏幕

```java
/**
 * 屏幕坐标到客户区
 * @param hParentWnd 父窗口句柄
 * @param hChildWnd 子窗口句柄
 * @param targetScreenX 目标屏幕X坐标
 * @param targetScreenY 目标屏幕Y坐标
 * @param width 宽度
 * @param height 高度
 */
public static void SetChildPosAlignScreen(int hParentWnd, int hChildWnd, int targetScreenX, int targetScreenY, int width, int height)
```

**windowSetLocationSize** - 窗口置位置与大小

```java
/**
 * 窗口_置位置与大小
 * @param hWnd 窗口句柄
 * @param left 左边位置 可以为0
 * @param top 顶部位置 可以为0
 * @param newWidth 新宽度 可以为0
 * @param newHeight 新高度 可以为0
 * @return 返回逻辑值
 */
public static int windowSetLocationSize(int hWnd, int left, int top, int newWidth, int newHeight)
```

#### 窗口属性方法

**windowGetDlgCtrlID** - 获取窗口控件ID

```java
/**
 * 根据窗口取出窗口控件的ID,顶级窗口返回0
 * @param childHwnd 子窗口句柄
 * @return 返回控件ID
 */
public static int windowGetDlgCtrlID(int childHwnd)
```

**windowGetDlgItem** - 控件ID取窗口句柄

```java
/**
 * 控件ID取窗口句柄
 * @param ParentHwnd 上一级的窗口句柄
 * @param GetDlgCtrlID 控件ID
 * @return 返回句柄
 */
public static int windowGetDlgItem(int ParentHwnd, int GetDlgCtrlID)
```

**windowSetAlpha** - 窗口设置透明度

```java
/**
 * 窗口设置透明度
 * @param hWnd 窗口句柄
 * @param Alpha 透明度
 * @return 返回逻辑值
 */
public static boolean windowSetAlpha(int hWnd, int Alpha)
```

**windowActivation** - 窗口激活

```java
/**
 * 窗口激活
 * @param hWnd 窗口句柄
 * @return 返回结果
 */
public static int windowActivation(int hWnd)
```

**windowReduction** - 窗口还原

```java
/**
 * 窗口还原
 * @param hWnd 窗口句柄
 * @return 返回结果
 */
public static boolean windowReduction(int hWnd)
```

**windowTop** - 窗口置顶

```java
/**
 * 窗口置顶
 * @param hWnd 窗口句柄
 * @param IsActivation 参数二结果
 * @param uFlags 窗口方法 可叠加参考
 * @return 返回结果
 *
 * 参数2可选值：
 * - HWND_BOTTOM(1): 将窗口置于Z顺序底部
 * - HWND_NOTOPMOST(-2): 将窗口置于所有非最顶层窗口上方
 * - HWND_TOP(0): 将窗口置于Z顺序顶部
 * - HWND_TOPMOST(-1): 将窗口置于所有非最顶层窗口之上
 *
 * 参数3可选值（可相加）：
 * - SWP_ASYNCWINDOWPOS(0x4000): 异步设置窗口位置
 * - SWP_NOREDRAW(0x0008): 不重绘更改
 * - SWP_NOSIZE(0x0001): 保留当前大小
 * - SWP_NOMOVE(0x0002): 保留当前位置
 * - SWP_NOZORDER(0x0004): 保留当前Z顺序
 * - SWP_SHOWWINDOW(0x0040): 显示窗口
 * - SWP_HIDEWINDOW(0x0080): 隐藏窗口
 */
public static boolean windowTop(int hWnd, int IsActivation, int uFlags)
```

**windowClose** - 关闭窗口

```java
/**
 * 窗口关闭
 * @param hWnd 窗口句柄
 * @return 返回结果
 */
public static boolean windowClose(int hWnd)
```

**windowStringWidth** - 窗口取字符串宽度

```java
/**
 * 窗口取字符串宽度
 * @param hWnd 窗口句柄
 * @param string 字符串
 * @return 返回字符串像素点
 */
public static int windowStringWidth(int hWnd, String string)
```

**windowSetParent2** - 窗口置父（全新）

```java
/**
 * 窗口置父(全新的窗口置父)
 * @param ParentHwnd 父窗口句柄
 * @param childHwnd 子窗口句柄
 * @param x 坐标位置
 * @param y 坐标位置
 * @param childWidth 子窗口宽度
 * @param childHeight 子窗口高度
 * @return 返回结果
 */
public static int windowSetParent2(int ParentHwnd, int childHwnd, int x, int y, int childWidth, int childHeight)
```

#### 窗口查找方法

**windowGetHwnd** - 窗口取控件句柄

```java
/**
 * 窗口取控件句柄
 * @param ParentHwnd 父窗口句柄
 * @param childHwnd 子窗口句柄
 * @param windowClass 窗口类名
 * @param windowTitle 窗口标题
 * @return 返回控件句柄
 */
public static int windowGetHwnd(int ParentHwnd, int childHwnd, String windowClass, String windowTitle)
```

**windowGetPoint** - 窗口取坐标位置

```java
/**
 * 窗口取窗口坐标位置
 * @param hWnd 窗口句柄
 * @return 返回坐标tagPOINT
 */
public static TagPoint windowGetPoint(int hWnd)
```

**windowGetRectToSize** - 窗口取位置和大小

```java
/**
 * 窗口取位置和大小
 * @param hWnd 窗口句柄
 * @return 返回tagRECTSIZE
 */
public static TagRectSize windowGetRectToSize(int hWnd)
```

**windowGetcomponentXY** - 窗体取控件坐标

```java
/**
 * 窗体取控件坐标
 * @param hWnd 窗口句柄
 * @return 返回tagPOINT
 */
public static TagPoint windowGetcomponentXY(int hWnd)
```

**windowSetFocus** - 窗口置焦点

```java
/**
 * 窗口置焦点
 * @param hWnd 窗口句柄
 * @return 返回结果
 */
public static int windowSetFocus(int hWnd)
```

**windowGetTitle** - 窗口取标题

```java
/**
 * 窗口取标题
 * @param hWnd 窗口句柄
 * @return 返回标题
 */
public static String windowGetTitle(int hWnd)
```

**windowSetTitle** - 窗口设置标题

```java
/**
 * 窗口设置标题
 * @param hWnd 窗口句柄
 * @param title 设置的窗口标题
 * @return 返回结果
 */
public static int windowSetTitle(int hWnd, String title)
```

**windowGetClassName** - 窗口取类名

```java
/**
 * 窗口取类名
 * @param hWnd 窗口句柄
 * @return 返回字符串
 */
public static String windowGetClassName(int hWnd)
```

**windowSetState** - 窗口设置状态

```java
/**
 * 窗口设置状态
 * @param hWnd 窗口句柄
 * @param state 状态值
 * @return 返回结果
 *
 * state可选值：
 * - 0: 隐藏取消激活
 * - 1: 还原激活
 * - 2: 最小化激活
 * - 3: 最大化激活
 * - 4: 还原
 * - 6: 最小化取消激活
 * - 7: 最小化
 * - 9: 还原激活
 */
public static int windowSetState(int hWnd, int state)
```

**windowSetMaximize** - 窗口最大化

```java
/**
 * 窗口最大化
 * @param hWnd 窗口句柄
 * @param NoBorder 无边框
 */
public static void windowSetMaximize(int hWnd, boolean NoBorder)
```

**windowSetMinimize** - 窗口最小化

```java
/**
 * 窗口最小化
 * @param hWnd 窗口句柄
 * @return 结果
 */
public static int windowSetMinimize(int hWnd)
```

**windowSetRoundedCorners** - 窗口设置圆角

```java
/**
 * 窗口设置圆角化 控件
 * @param hWnd 窗口句柄
 * @param roundWidth 圆宽 默认50
 * @param roundHeight 圆高 默认50
 */
public static void windowSetRoundedCorners(int hWnd, int roundWidth, int roundHeight)
```

**windowGetDlgCtrlSize** - 窗口取控件大小

```java
/**
 * 窗口取控件大小
 * @param hWnd 窗口句柄
 * @return 返回结果 index[0]宽 index[1]高
 */
public static int[] windowGetDlgCtrlSize(int hWnd)
```

**windowGetRootHwnd** - 窗口取祖句柄

```java
/**
 * 窗口取祖句柄
 * @param hWnd 窗口句柄
 * @return 返回祖句柄
 */
public static int windowGetRootHwnd(int hWnd)
```

**windowGetFatherHwnd** - 窗口取父句柄

```java
/**
 * 窗口取父句柄
 * @param hWnd 窗口句柄
 * @return 返回父句柄
 */
public static int windowGetFatherHwnd(int hWnd)
```

**windowIsShow** - 窗口是否可见

```java
/**
 * 窗口是否可见
 * @param hWnd 窗口句柄
 * @return 返回结果
 */
public static int windowIsShow(int hWnd)
```

**windowIsShowHide** - 窗口显示隐藏

```java
/**
 * 窗口显示隐藏
 * @param hWnd 窗口句柄
 * @param isHide 是否隐藏 0隐藏 1显示
 * @return 返回结果
 */
public static int windowIsShowHide(int hWnd, int isHide)
```

#### 窗口高级操作方法

**windowSetAlpha** - 窗口置透明度（扩展）

```java
/**
 * 窗口置透明度
 * @param hWnd 窗口句柄
 * @param alpha 透明度
 * @param AlphaColor 透明色
 * @param isThrough 鼠标是否穿透
 * @return 返回结果
 */
public static boolean windowSetAlpha(int hWnd, int alpha, int AlphaColor, boolean isThrough)
```

**windowGetAlpha** - 窗口取透明度

```java
/**
 * 窗口取透明度
 * @param hWnd 窗口句柄
 * @return 返回透明度 失败-1
 */
public static int windowGetAlpha(int hWnd)
```

**windowSetThrough** - 窗口置穿透

```java
/**
 * 窗口置穿透
 * @param hWnd 窗口句柄
 * @param isThrough 鼠标是否穿透
 * @return 返回结果
 */
public static boolean windowSetThrough(int hWnd, boolean isThrough)
```

**windowAlwaysTop** - 窗口总在最近

```java
/**
 * 窗口总在最近
 * @param hWnd 窗口句柄
 * @param AlwaysTop 是否总在最前
 * @return 返回结果
 */
public static boolean windowAlwaysTop(int hWnd, boolean AlwaysTop)
```

**windowUnlock** - 窗口锁住解锁

```java
/**
 * 窗口锁住解锁
 * @param hWnd 窗口句柄
 * @param types 是否禁止
 * @return 返回结果
 */
public static int windowUnlock(int hWnd, boolean types)
```

**windowBanClose** - 窗口禁止关闭

```java
/**
 * 窗口禁止关闭
 * @param hWnd 窗口句柄
 * @param isClose 是否禁止关闭
 * @return 返回结果
 */
public static boolean windowBanClose(int hWnd, boolean isClose)
```

**windowSetGraduallyHideShow** - 窗口渐隐渐现

```java
/**
 * 窗口渐隐渐现
 * @param hWnd 窗口句柄
 * @param types 类型0逐渐隐藏 1逐渐显示
 * @param graduallyTimem 速度1-10为准 越小越快
 * @param callback 回调处理的事件
 * @return 返回结果
 */
public static boolean windowSetGraduallyHideShow(int hWnd, int types, int graduallyTimem, WindowSetGraduallyHideShowCallBack callback)
```

**windowGetScreenHWnd** - 窗口取屏幕句柄

```java
/**
 * 窗口_取屏幕句柄
 * @return 返回屏幕句柄
 */
public static long windowGetScreenHWnd()
```

**windowGetMinimize** - 窗体是否最小化

```java
/**
 * 窗体是否最小化
 * @param hWnd 窗口句柄
 * @return 返回结果
 */
public static boolean windowGetMinimize(int hWnd)
```

**windowGetMaximize** - 窗体是否最大化

```java
/**
 * 窗体是否最大化
 * @param hWnd 窗口句柄
 * @return 返回结果
 */
public static boolean windowGetMaximize(int hWnd)
```

**windowSetDlgItemText** - 窗体设置控件内容

```java
/**
 * 窗体设置控件内容
 * @param hWnd 控件句柄
 * @param title 内容
 * @return 返回结果
 */
public static int windowSetDlgItemText(int hWnd, String title)
```

**windowSetDlgItemState** - 窗体设置控件状态

```java
/**
 * 窗体设置控件状态
 * @param hWnd 控件句柄
 * @param showHide 显示隐藏
 * @return 返回结果
 */
public static boolean windowSetDlgItemState(int hWnd, boolean showHide)
```

**windowIsValid** - 窗体句柄是否有效

```java
/**
 * 窗体句柄是否有效
 * @param hWnd 窗体句柄
 * @return 返回结果
 */
public static boolean windowIsValid(int hWnd)
```

#### 窗口枚举方法

**constraintGetHwnd** - 暴力枚举句柄

```java
/**
 * 暴力枚举句柄
 * @param windowTtitle 窗口标题
 * @return 返回句柄
 */
public static int constraintGetHwnd(String windowTtitle)

/**
 * 暴力枚举句柄2
 * @param windowTtitle 窗口标题
 * @param windowClassName 窗口类名
 * @return 返回句柄
 */
public static int constraintGetHwnd(String windowTtitle, String windowClassName)
```

**SwingWindowsHwnd** - 获取Swing窗口句柄

```java
/**
 * 获取Swing窗口句柄
 * @param ClassName 窗口类名
 * @param WinTitle 窗口标题
 * @return 返回窗口句柄
 */
public static int SwingWindowsHwnd(String ClassName, String WinTitle)
```

**windowGetHwndOne / windowGetHwndTow** - 窗口取句柄

```java
/**
 * 窗口取句柄1
 * @param ProcessID 进程ID
 * @param ClassName 窗口类名
 * @param WinTitle 窗口标题
 * @return 返回句柄
 */
public static int windowGetHwndOne(int ProcessID, String ClassName, String WinTitle)

/**
 * 窗口取句柄2
 * @param ProcessID 进程ID
 * @param ClassName 窗口类名
 * @param WinTitle 窗口标题
 * @return 返回句柄
 */
public static int windowGetHwndTow(int ProcessID, String ClassName, String WinTitle)
```

**WindowEnum** - 枚举所有子窗口句柄

```java
/**
 * 枚举所有子窗口句柄
 * @param childWindow 返回窗口句柄列表
 * @return 返回成员数
 */
public static long WindowEnum(List<Integer> childWindow)
```

**WindowEnumChildWindowClassName** - 获取子窗口类名

```java
/**
 * 获取所有窗口句柄下的子窗口类名
 * @param childWindowClass 窗口类名列表
 * @return 返回成员数
 */
public static long WindowEnumChildWindowClassName(List<String> childWindowClass)
```

**WindowEnumChildWindowTitleName** - 获取子窗口标题

```java
/**
 * 获取所有窗口句柄下的子窗口标题
 * @param childWindowTitle 窗口标题列表
 * @return 返回成员数
 */
public static long WindowEnumChildWindowTitleName(List<String> childWindowTitle)
```

**enumWindowInfoOne / enumWindowInfoTow** - 枚举窗口信息

```java
/**
 * 枚举窗口信息1
 * @return 窗口信息列表
 */
public static List<TagWinInfo> enumWindowInfoOne()

/**
 * 枚举窗口信息2
 * @param wininfo 窗口信息列表
 * @param isShow 可见性 默认真:所有可见窗口 假:所有窗口
 * @return 返回成员数
 */
public static int enumWindowInfoTow(List<TagWinInfo> wininfo, boolean isShow)
```

**classNameToHwnd** - 类名取窗口句柄

```java
/**
 * 类名取窗口句柄
 * @param className 类名
 * @return 返回句柄
 */
public static int classNameToHwnd(String className)
```

**processPidGetAllHwnd** - 进程取所有窗口

```java
/**
 * 进程取所有窗口
 * @param ProcessId 进程ID
 * @param allHwnd 窗口句柄列表
 * @return 返回成员数
 */
public static int processPidGetAllHwnd(int ProcessId, List<Integer> allHwnd)
```

**windowGetHwnd** - 取当前窗口句柄

```java
/**
 * 取当前窗口句柄
 * @return 返回句柄
 */
public static int windowGetHwnd()
```

**windowGetParentHwnd** - 取当前父窗口句柄

```java
/**
 * 取当前父窗口句柄
 * @return 返回父窗口句柄
 */
public static int windowGetParentHwnd()
```

#### Swing窗口嵌入方法

**SwingWindowEmbeddedWindow** - 嵌入Swing窗口

```java
/**
 * 嵌入Swing窗口
 * @param hWindow 炫彩窗口句柄
 * @param comp swing组件
 * @param x x坐标
 * @param y y坐标
 * @param width 宽度
 * @param height 高度
 * @return SwingJFrameInfo 必须设置为全局的静态SwingJFrameInfo信息
 */
public static SwingJFrameInfo SwingWindowEmbeddedWindow(int hWindow, Component comp, int x, int y, int width, int height)
```

**SwingWindowSizeMessage** - 控制Swing窗口位置

```java
/**
 * 回调控制Swing窗口位置
 * @param frame swing窗口信息
 */
public static void SwingWindowSizeMessage(SwingJFrameInfo frame)
```

**GetSwingWindowHandle** - 返回Swing窗口句柄

```java
/**
 * 返回swing窗口句柄
 * @param obj swing窗口组件
 * @return 窗口句柄
 */
public static int GetSwingWindowHandle(Component obj)
```

---

### SystemUtils 系统工具类

本类存放系统API操作，包含了热键监视、全局钩子、剪贴板操作、消息框等功能。

#### 系统路径方法

**getDesktop** - 获取桌面路径

```java
/**
 * @return 返回桌面路径
 */
public static String getDesktop()
```

**getRunPathOne** - 取自身启动目录

```java
/**
 * 取自身启动目录(完整)
 * @return 返回结果
 */
public static String getRunPathOne()
```

**System32Path** - 取System32系统目录

```java
/**
 * 取System32系统目录
 * @return 返回结果
 */
public static String System32Path()
```

**getSystemPath** - 取系统目录

```java
/**
 * 取系统目录
 * @return 返回结果
 */
public static String getSystemPath()
```

**runFilePath** - 返回jar运行目录

```java
/**
 * 返回jar运行目录
 * @return 返回路径
 */
public static String runFilePath()
```

#### 消息框方法

**messageBoxExW** - 弹出对话框

```java
/**
 * 弹出对话框
 * @param hWnd 窗口句柄
 * @param lpText 内容
 * @param lpCaption 标题
 * @param uType 窗体类型
 * @return 返回结果 可以根据结果做判断
 *
 * 按钮类型：
 * - 0x00000000L: OK（默认）
 * - 0x00000001L: OK和Cancel
 * - 0x00000004L: Yes和No
 * - 0x00000002L: Abort、Retry和Ignore
 * - 0x00000005L: 重试和取消
 *
 * 图标类型：
 * - 0x00000010L: 停止标志图标
 * - 0x00000020L: 问号图标
 * - 0x00000030L: 感叹号图标
 * - 0x00000040L: 信息图标
 */
public static int messageBoxExW(int hWnd, String lpText, String lpCaption, int uType)
```

#### 热键监视方法

**listeningHotKeyOne** - 监视热键（单个）

```java
/**
 * 监视热键 键代码参考微软虚拟键码文档
 * 目前仅支持单个按键监视
 * @param response 响应事件 实现接口new ResponseEventCallBack()
 * @param keyCode 键代码
 * @param cycle 周期
 * @return 返回标识符
 */
public static int listeningHotKeyOne(ResponseEventCallBack response, int keyCode, int cycle)
```

**removeListeningHotKeyOne** - 卸载监视热键

```java
/**
 * 卸载监视热键
 * @param logo 标识符
 * @return 返回结果
 */
public static boolean removeListeningHotKeyOne(int logo)
```

**listeningHotKeyTow** - 监视热键（组合键）

```java
/**
 * 监视热键2 支持组合按键监视和单个监视
 * @param response 响应事件 实现接口new ResponseEventCallBack()
 * @param keyCode 键代码
 * @param functionKey 1 Alt 2 Ctrl 4 shift 8 win
 * @param cycle 周期
 * @return 返回标识符
 */
public static int listeningHotKeyTow(ResponseEventCallBack response, int keyCode, int functionKey, int cycle)
```

**removeListeningHotKeyTow** - 卸载监视热键2

```java
/**
 * 卸载监视热键2
 * @param logo 标识符
 * @return 返回结果
 */
public static boolean removeListeningHotKeyTow(int logo)
```

#### 键盘钩子方法

**setWindowsHookEx** - 全局键盘钩子

```java
/**
 * 全局键盘钩子
 * @param callBack 键盘钩子回调事件
 */
public static void setWindowsHookEx(KeyCodeCallBack callBack)
```

**unhookWindowsHookEx** - 卸载全局钩子

```java
/**
 * 卸载全局钩子
 * @return 返回卸载结果
 */
public static boolean unhookWindowsHookEx()
```

**messageRun** - 消息循环事件

```java
/**
 * 消息循环事件 用于窗体循环的
 */
public static void messageRun()
```

**keyCodeToStringOne / keyCodeToStringTow** - 返回按键文本

```java
/**
 * 返回按键文本
 * @param wParam 键消息
 * @param vkCode 键代码
 * @return 返回键文本
 */
public static String keyCodeToStringOne(int wParam, int vkCode)
public static String keyCodeToStringTow(int wParam, int vkCode)
```

#### 剪贴板方法

**setClipboardContents** - 置剪贴板内容

```java
/**
 * 置剪贴板内容
 * @param v 剪贴板内容
 */
public static void setClipboardContents(String v)
```

#### 字符串操作方法

**textCenter** - 取中间文本

```java
/**
 * 取中间文本
 * @param allText 主要内容
 * @param index_x 左边
 * @param index_z 右边
 * @return 返回内容
 */
public static String textCenter(String allText, String index_x, String index_z)
```

#### 鼠标操作方法

**sendInput** - 后台模拟消息

```java
/**
 * 后台模拟消息
 * @param x 坐标
 * @param y 坐标
 * @param keyType 类型
 * @param valueType 事件
 * @return 结果
 */
public static int sendInput(int x, int y, int keyType, int valueType)
```

**mouseX** - 返回屏幕坐标X

```java
/**
 * 返回屏幕坐标X
 * @param index 坐标位置
 * @return 返回实际坐标位置
 */
public static int mouseX(int index)
```

**getScreenXY** - 格式化屏幕坐标

```java
/**
 * 格式化屏幕坐标
 * @param index 坐标位置
 * @param screenSize 屏幕宽或高
 * @return 返回实际坐标值
 */
public static int getScreenXY(int index, int screenSize)
```

**mouseClickOne** - 模拟鼠标左键

```java
/**
 * 模拟鼠标左键
 * @param x 坐标位置
 * @param y 坐标位置
 * @param w 屏幕宽
 * @param h 屏幕高
 */
public static void mouseClickOne(int x, int y, int w, int h)
```

**mouseClickTwo** - 模拟鼠标左键（窗口内）

```java
/**
 * 模拟鼠标左键
 * @param hwnd 窗口句柄
 * @param x 坐标位置
 * @param y 坐标位置
 */
public static void mouseClickTwo(int hwnd, int x, int y)
```

**getSendMessageXY** - 坐标消息

```java
/**
 * 坐标消息
 * @param x 坐标位置
 * @param y 坐标位置
 * @return 坐标消息
 */
public static int getSendMessageXY(int x, int y)
```

#### 游戏坐标转换方法

**getGameToServer** - 传奇游戏坐标转服务器坐标

```java
/**
 * 传奇游戏坐标转服务器坐标
 * @param point 坐标类
 * @return 返回坐标数组[X,Y]
 */
public static float[] getGameToServer(TagPoint point)
```

**getServerToGame** - 传奇服务器坐标转游戏坐标

```java
/**
 * 传奇服务器坐标转游戏坐标
 * @param point 坐标类
 * @return 返回坐标数组[X,Y]
 */
public static float[] getServerToGame(TagPoint point)
```

#### 定时任务方法

**timerExecutorOne** - 定时任务1

```java
/**
 * 定时任务1
 * @return ScheduledExecutorService
 */
public static ScheduledExecutorService timerExecutorOne()
```

**timerExecutorTwo** - 定时任务2

```java
/**
 * 定时任务2
 * @return Timer
 */
public static Timer timerExecutorTwo()
```

#### 工具方法

**macUID** - 随机值唯一序列号

```java
/**
 * 随机值唯一序列号
 * @return MAC地址UID
 */
public static String macUID()
```

**inputStreamToBase64** - 图片转Base64

```java
/**
 * 图片转Base64
 * @param is 图片输入流
 * @return Base64字符串
 */
public static String inputStreamToBase64(InputStream is)
```

**base64ToInputStream** - Base64转InputStream

```java
/**
 * Base64 转 InputStream
 * @param base64Data base64数据
 * @return InputStream
 */
public static InputStream base64ToInputStream(String base64Data)
```

**getFonts** - 返回系统所有的字体

```java
/**
 * 返回系统所有的字体
 * @return 返回系统字体列表
 */
public static List<String> getFonts()
```

#### 文件对话框方法

**OpenFileDialog** - 文件选择器

```java
/**
 * 文件选择器
 * @param filter 文件过滤器
 * @param hwnd 窗口句柄
 * @param title 标题
 * @param initialFilterIndex 初始过滤器索引
 * @param initialDirectory 初始目录
 * @param defaultExtension 默认扩展名
 * @param promptOnCreate 是否提示创建
 * @param disableLinkResolution 禁用链接解析
 * @param dontChangeDirectory 不改变目录
 * @return 文件路径
 */
public static String OpenFileDialog(String filter, int hwnd, String title, int initialFilterIndex, String initialDirectory, String defaultExtension, boolean promptOnCreate, boolean disableLinkResolution, boolean dontChangeDirectory)
```

**OpenFilesDialog** - 文件多选选择器

```java
/**
 * 文件多选选择器
 * @param filter 文件过滤器
 * @param hwnd 窗口句柄
 * @param title 标题
 * @param initialFilterIndex 初始过滤器索引
 * @param initialDirectory 初始目录
 * @param dontChangeDirectory 不改变目录
 * @return 文件路径数组
 */
public static String[] OpenFilesDialog(String filter, long hwnd, String title, int initialFilterIndex, String initialDirectory, boolean dontChangeDirectory)
```

**SaveFileDialog** - 文件保存选择器

```java
/**
 * 文件保存选择器
 * @param filter 文件过滤器
 * @param hwnd 窗口句柄
 * @param title 标题
 * @param initialFilterIndex 初始过滤器索引
 * @param initialDirectory 初始目录
 * @param defaultExtension 默认扩展名
 * @param promptOnOverwrite 是否提示覆盖
 * @param dontChangeDirectory 不改变目录
 * @return 文件路径
 */
public static String SaveFileDialog(String filter, long hwnd, String title, int initialFilterIndex, String initialDirectory, String defaultExtension, boolean promptOnOverwrite, boolean dontChangeDirectory)
```

**DirectoryDialog** - 文件夹选择器

```java
/**
 * 文件夹选择器
 * @param hwnd 窗口句柄
 * @param title 标题
 * @param buttonLabel 按钮标签
 * @return 文件夹路径
 */
public static String DirectoryDialog(long hwnd, String title, String buttonLabel)
```

#### 网络和资源方法

**readResourceToBytes** - 读取资源文件为字节数组

```java
/**
 * 读取资源文件为字节数组
 * @param resourcePath 资源路径
 * @return 字节数组
 */
public static byte[] readResourceToBytes(String resourcePath)
```

**readUrlToBytes** - 读取URL为字节数组

```java
/**
 * 读取url为字节数组
 * @param urlAddress url地址
 * @return 字节数组
 */
public static byte[] readUrlToBytes(String urlAddress)
```

**convertUrlToBufferedImage** - URL转BufferedImage

```java
/**
 * 从网络URL读取图片并转换为BufferedImage
 * @param imageUrl 图片的网络URL
 * @return 转换后的BufferedImage
 */
public static BufferedImage convertUrlToBufferedImage(String imageUrl)
```

#### 图像处理方法

**multiplyBlendWithTransparency** - 带透明度的正片叠底

```java
/**
 * 带透明度的正片叠底：上层半透明时，能透出底层，同时保留Multiply的暗化效果
 * @param top 上层图像
 * @param bottom 底层图像
 * @return 混合后的BufferedImage
 */
public static BufferedImage multiplyBlendWithTransparency(BufferedImage top, BufferedImage bottom)
```

---

### ProcessAndThreadUtils 进程线程操作类

本类提供进程和线程相关的操作功能，包括进程枚举、进程打开与结束、线程管理、模块枚举、权限提升等。

#### 进程操作方法

**processEnumInfo** - 枚举进程信息

```java
/**
 * 枚举进程信息
 * @return 返回集合
 */
public static List<TagProcessenTry32> processEnumInfo()
```

**getCurrentProcessPID** - 获取当前进程PID

```java
/**
 * 纯Java原生API获取当前进程PID（无依赖、跨平台）
 * @return PID字符串（非空，JVM启动后必存在）
 */
public static int getCurrentProcessPID()
```

**pidGetProcessName** - 进程PID取进程名

```java
/**
 * 进程PID取进程名
 * @param th32ProcessID 进程ID
 * @return 返回进程名称
 */
public static String pidGetProcessName(int th32ProcessID)
```

**processNameGetPid** - 进程名取PID

```java
/**
 * 进程名取PID
 * @param ProcessName  @return 返回进程ID
 */
public进程名称
 * static int processNameGetPid(String ProcessName)
```

**openProcess** - 打开进程

```java
/**
 * 打开进程
 * @param dwProcessId 进程PID
 * @return 返回进程句柄
 */
public static int openProcess(int dwProcessId)
```

**closeHandle** - 关闭进程句柄

```java
/**
 * 关闭进程句柄
 * @param hObject 句柄
 * @return 返回逻辑值
 */
public static long closeHandle(int hObject)
```

**getCurrentProcess** - 打开当前进程句柄

```java
/**
 * 打开当前进程句柄
 * @return 返回值是当前进程的伪句柄-1
 */
public static int getCurrentProcess()
```

**overProcess** - 结束进程

```java
/**
 * 结束进程
 * @param dwProcessId 进程PID
 * @return 返回逻辑值
 */
public static int overProcess(int dwProcessId)
```

**processNameIsTrue** - 进程名是否存在

```java
/**
 * 进程名是否存在
 * @param ProcessName 进程名称
 * @return 返回逻辑值
 */
public static boolean processNameIsTrue(String ProcessName)
```

**processNameGetWindowHandle** - 进程名取窗口句柄

```java
/**
 * 进程名取窗口句柄
 * @param ProcessName 进程名
 * @return 返回句柄
 */
public static int processNameGetWindowHandle(String ProcessName)
```

**processNameGetThreadID** - 进程名取线程ID

```java
/**
 * 进程名取线程ID
 * @param ProcessName 进程名
 * @return 返回线程ID
 */
public static int processNameGetThreadID(String ProcessName)
```

**getCurrentProcessId** - 取自身进程ID

```java
/**
 * 取自身进程ID
 * @return 进程ID
 */
public static int getCurrentProcessId()
```

**processNameGetParentProcessID** - 进程名取父进程ID

```java
/**
 * 进程名取父进程ID
 * @param ProcessName 进程名称
 * @return 返回父进程ID
 */
public static int processNameGetParentProcessID(String ProcessName)
```

**processSuspended** - 暂停进程

```java
/**
 * 暂停进程
 * @param ProcessName 进程名称
 * @param flag 真为暂停 假为释放
 * @return 返回逻辑
 */
public static boolean processSuspended(String ProcessName, boolean flag)
```

#### 线程操作方法

**processNameGetThreadTid** - 进程名取线程ID

```java
/**
 * 进程名取线程ID
 * @param ProcessName 进程名
 * @return 返回线程ID
 */
public static int processNameGetThreadTid(String ProcessName)
```

**threadTidGetProcessName** - 线程ID取进程名

```java
/**
 * 线程ID取进程名
 * @param th32ThreadID 线程ID
 * @return 返回进程名
 */
public static String threadTidGetProcessName(int th32ThreadID)
```

**threadTidGetProcessPid** - 线程ID取进程ID

```java
/**
 * 线程ID取进程ID
 * @param th32ThreadID 线程ID
 * @return 返回进程ID
 */
public static int threadTidGetProcessPid(int th32ThreadID)
```

**openThread** - 打开线程

```java
/**
 * 打开线程ID
 * @param dwThreadId 线程ID
 * @return 返回线程句柄
 */
public static int openThread(int dwThreadId)
```

**getCurrentThreadId** - 返回自身线程ID

```java
/**
 * 返回自身线程ID
 * @return 线程ID
 */
public static int getCurrentThreadId()
```

**overThread** - 结束线程

```java
/**
 * 结束线程
 * @param dwThreadId 线程ID
 * @return 返回结果
 */
public static int overThread(int dwThreadId)
```

**threadSuspended** - 暂停线程

```java
/**
 * 暂停线程
 * @param dwThreadId 线程ID
 * @param flag 真为暂停 假为释放
 * @return 返回结果
 */
public static int threadSuspended(int dwThreadId, boolean flag)
```

#### 权限操作方法

**elevatePrivileges** - 提升权限

```java
/**
 * elevatePrivileges 提升权限
 * @param ProcessHandle 进程句柄
 * @param TokenIndex 权限类型
 * @return 返回逻辑值
 *
 * TokenIndex可选值：
 * - 1: SeBackupPrivilege 备份权限
 * - 2: SeRestorePrivilege 恢复权限
 * - 3: SeShutdownPrivilege 关机权限
 * - 4: SeDebugPrivilege 调试权限
 */
public static boolean elevatePrivileges(int ProcessHandle, int TokenIndex)
```

#### 模块操作方法

**processPidToModuleX86** - 进程ID取进程模块（32位）

```java
/**
 * 进程ID取进程模块 仅限32位使用 仅限32位JDK
 * @param th32ProcessID 进程ID
 * @param moduleCallBack 回调
 * @return 返回tagMODULEENTRY32集合
 */
public static List<TagModuleenTry32> processPidToModuleX86(int th32ProcessID, ModuleCallBack moduleCallBack)
```

**processPidToModulex86** - 进程ID取进程模块（64位JDK下的32位模块）

```java
/**
 * 进程ID取进程模块 64位下支持32位和64位使用 仅限64位JDK
 * @param hProcess 进程句柄
 * @param moduleCallBack 回调
 * @return 返回tagMODULEENTRYX86集合
 */
public static List<TagModuleenTryX86> processPidToModulex86(int hProcess, ModuleCallBack moduleCallBack)
```

**processPidToModuleX64** - 进程ID取进程模块（64位）

```java
/**
 * 进程ID取进程模块 64位下支持32位和64位使用 仅限64位JDK
 * @param hProcess 进程句柄
 * @param moduleCallBack 回调
 * @return 返回tagMODULEENTRYX64集合
 */
public static List<TagModuleenTryX64> processPidToModuleX64(int hProcess, ModuleCallBack moduleCallBack)
```

#### 高级枚举方法

**selectNtQueryAllProcessInfoWProcess** - 全局枚举进程信息

```java
/**
 * 全局枚举进程信息
 * @param callBack 回调进程线程信息数据
 * @return 返回逻辑值
 */
public static boolean selectNtQueryAllProcessInfoWProcess(NtQueryAllProcessInfoCallBackProcess callBack)
```

**selectNtQueryAllProcessInfoWThread** - 全局枚举线程信息

```java
/**
 * 全局枚举线程信息
 * @param callBack 回调进程线程信息数据
 * @return 返回逻辑值
 */
public static boolean selectNtQueryAllProcessInfoWThread(NtQueryAllProcessInfoCallBackThread callBack)
```

**selectThreadInfo** - 枚举线程X64

```java
/**
 * 枚举线程X64
 * @param tagThreadInfo 线程枚举回调
 */
public static void selectThreadInfo(TagThreadInfo tagThreadInfo)
```

---

### DdDriver DD键鼠驱动类

DD键鼠驱动提供了模拟鼠标和键盘操作的功能，适用于自动化脚本和游戏辅助开发。

#### 鼠标操作方法

**btn** - 鼠标点击

```java
/**
 * 鼠标点击
 * @param btnId 点击类型
 * @return 返回结果
 *
 * btnId可选值：
 * - 1: 左键按下
 * - 2: 左键放开
 * - 4: 右键按下
 * - 8: 右键放开
 * - 16: 中键按下
 * - 32: 中键放开
 * - 64: 4键按下
 * - 128: 4键放开
 * - 256: 5键按下
 * - 512: 5键放开
 */
public static int btn(int btnId)
```

**mov** - 鼠标绝对移动

```java
/**
 * 鼠标绝对移动
 * @param x 坐标轴X
 * @param y 坐标轴Y
 * @return 返回结果
 */
public static int mov(int x, int y)
```

**movR** - 鼠标相对移动

```java
/**
 * 模拟鼠标相对移动
 * @param dx 坐标轴X
 * @param dy 坐标轴Y
 * @return 返回结果
 */
public static int movR(int dx, int dy)
```

**whl** - 鼠标滚轮

```java
/**
 * 模拟鼠标滚轮
 * @param whl 滚动方向
 * @return 返回结果
 *
 * whl可选值：
 * - 1: 前滚
 * - 2: 后滚
 */
public static int whl(int whl)
```

#### 键盘操作方法

**key** - 键盘按键

```java
/**
 * 键盘按键
 * @param ddcode 虚拟键盘码表
 * @param flag 按键状态
 * @return 返回结果
 *
 * flag可选值：
 * - 1: 按下
 * - 2: 放开
 */
public static int key(int ddcode, int flag)
```

**str** - 直接输入字符串

```java
/**
 * 直接输入键盘上可见字符和空格 仅支持字符符号
 * @param str 虚拟键盘码表
 * @return 返回结果
 */
public static int str(String str)
```

---

### GhostDriver 幽灵键鼠驱动类

幽灵键鼠驱动提供了更丰富的硬件模拟功能，支持设备选择、按键延时设置等高级特性。注意：Win7以上部分系统无法使用64位。

#### 设备操作方法

**getDeviceListByModel** - 获取设备列表

```java
/**
 * 获取设备列表
 * @return 非空表示设备序列号列表
 */
public static String getDeviceListByModel()
```

**selectDeviceBySerialNumber** - 选择设备

```java
/**
 * 选择设备
 * @param SerialNumber GetDeviceListByModel()返回的去掉分号的字符串
 * @return 1表示成功
 */
public static int selectDeviceBySerialNumber(String SerialNumber)
```

**isDeviceConnected** - 设备是否连接

```java
/**
 * 设备是否连接
 * @return 1表示当前已连接设备
 */
public static int isDeviceConnected()
```

**getModel** - 获取设备型号

```java
/**
 * 获取设备型号
 * @return 非空表示当前连接设备的型号
 */
public static String getModel()
```

**getSerialNumber** - 获取设备序列号

```java
/**
 * 获取设备序列号
 * @return 非空表示当前连接设备的序列号
 */
public static String getSerialNumber()
```

**getFirmwareVersion** - 获取设备版本号

```java
/**
 * 获取设备版本号
 * @return 非空表示当前连接设备的固件版本号
 */
public static String getFirmwareVersion()
```

#### 键盘操作方法

**pressKeyByName** - 按下键（按名称）

```java
/**
 * 按下键
 * @param KeyName 键名 参考GhostHashKeyCode.html文档
 * @return 1表示按键成功
 */
public static int pressKeyByName(String KeyName)
```

**pressKeyByValue** - 按下键（按值）

```java
/**
 * 按下键
 * @param KeyValue 键值 参考GhostHashKeyCode.html文档
 * @return 1表示按键成功
 */
public static int pressKeyByValue(int KeyValue)
```

**releaseKeyByName** - 释放键（按名称）

```java
/**
 * 释放键
 * @param KeyName 键名 参考GhostHashKeyCode.html文档
 * @return 1表示按键成功
 */
public static int releaseKeyByName(String KeyName)
```

**releaseKeyByValue** - 释放键（按值）

```java
/**
 * 释放键
 * @param KeyValue 键值 参考GhostHashKeyCode.html文档
 * @return 1表示按键成功
 */
public static int releaseKeyByValue(int KeyValue)
```

**pressAndReleaseKeyByName** - 按下并释放键（按名称）

```java
/**
 * 按下并释放键
 * @param KeyName 键名 参考GhostHashKeyCode.html文档
 * @return 1表示按键成功
 */
public static int pressAndReleaseKeyByName(String KeyName)
```

**pressAndReleaseKeyByValue** - 按下并释放键（按值）

```java
/**
 * 按下并释放键
 * @param KeyValue 键值 参考GhostHashKeyCode.html文档
 * @return 1表示按键成功
 */
public static int pressAndReleaseKeyByValue(int KeyValue)
```

**isKeyPressedByName** - 键是否按下（按名称）

```java
/**
 * 键是否按下
 * @param KeyName 键名 参考GhostHashKeyCode.html文档
 * @return 1表示键已按下
 */
public static int isKeyPressedByName(String KeyName)
```

**isKeyPressedByValue** - 键是否按下（按值）

```java
/**
 * 键是否按下
 * @param KeyValue 键值 参考GhostHashKeyCode.html文档
 * @return 1表示键已按下
 */
public static int isKeyPressedByValue(int KeyValue)
```

**releaseAllKey** - 释放所有键盘按键

```java
/**
 * 释放所有键盘按键
 * @return 1表示成功
 */
public static int releaseAllKey()
```

**inputString** - 输入字符串

```java
/**
 * 输入字符串，不支持中文
 * @param Str 要输入的字符串
 * @return 大于0表示输入成功
 */
public static int inputString(String Str)
```

**getCapsLock** - 获取CapsLock状态

```java
/**
 * 获取CapsLock（大写锁定）状态
 * @return 1表示锁定（灯亮）
 */
public static int getCapsLock()
```

**getNumLock** - 获取NumLock状态

```java
/**
 * 获取NumLock（数字键盘锁定）状态
 * @return 1表示锁定（灯亮）
 */
public static int getNumLock()
```

**setPressKeyDelay** - 设置按键延时

```java
/**
 * 设置按键延时
 * @param MinDelay 最小延时时间，单位毫秒，默认30
 * @param MaxDelay 最大延时时间，单位毫秒，默认100
 * @return 1表示成功
 */
public static int setPressKeyDelay(int MinDelay, int MaxDelay)
```

**setInputStringIntervalTime** - 设置输入字符串间隔时间

```java
/**
 * 设置输入字符串间隔时间
 * @param MinDelay 最小延时时间，单位毫秒，默认60
 * @param MaxDelay 最大延时时间，单位毫秒，默认200
 * @return 1表示成功
 */
public static int setInputStringIntervalTime(int MinDelay, int MaxDelay)
```

**setCaseSensitive** - 设置是否区分大小写

```java
/**
 * 设置是否区分大小写
 * @param Discriminate 1区分，0不区分，默认1
 * @return 1表示成功
 */
public static int setCaseSensitive(int Discriminate)
```

#### 鼠标操作方法

**pressMouseButton** - 按下鼠标键

```java
/**
 * 按下鼠标键
 * @param mButton 鼠标键序号（1:左键 2:中键 3:右键）
 * @return 1表示按键成功
 */
public static int pressMouseButton(int mButton)
```

**releaseMouseButton** - 释放鼠标键

```java
/**
 * 释放已按下的鼠标键
 * @param mButton 鼠标键序号（1:左键 2:中键 3:右键）
 * @return 1表示按键成功
 */
public static int releaseMouseButton(int mButton)
```

**pressAndReleaseMouseButton** - 按下并释放鼠标键

```java
/**
 * 按下并释放鼠标键
 * @param mButton 鼠标键序号（1:左键 2:中键 3:右键）
 * @return 2表示按键成功
 */
public static int pressAndReleaseMouseButton(int mButton)
```

**isMouseButtonPressed** - 鼠标键是否按下

```java
/**
 * 鼠标键是否按下
 * @param mButton 鼠标键序号（1:左键 2:中键 3:右键）
 * @return 1表示键已按下
 */
public static int isMouseButtonPressed(int mButton)
```

**releaseAllMouseButton** - 释放所有鼠标按键

```java
/**
 * 释放所有鼠标按键
 * @return 1表示成功
 */
public static int releaseAllMouseButton()
```

**moveMouseTo** - 移动鼠标到指定坐标

```java
/**
 * 移动鼠标到指定坐标
 * @param X 屏幕的X坐标，取值范围为正整数
 * @param Y 屏幕的Y坐标，取值范围为正整数
 * @return 大于0表示移动成功
 */
public static int moveMouseTo(int X, int Y)
```

**moveMouseRelative** - 相对移动鼠标

```java
/**
 * 相对移动鼠标
 * @param X 水平移动距离，取值范围为-127~+127
 * @param Y 垂直移动距离，取值范围为-127~+127
 * @return 1表示移动成功
 */
public static int moveMouseRelative(int X, int Y)
```

**moveMouseWheel** - 移动鼠标滚轮

```java
/**
 * 移动鼠标滚轮
 * @param Z 鼠标滚轮移动距离，取值范围为-127~+127
 * @return 1表示移动成功
 */
public static int moveMouseWheel(int Z)
```

**getMouseX** - 获取鼠标X坐标

```java
/**
 * 获取鼠标X坐标
 * @return 大于等于0表示获取成功
 */
public static int getMouseX()
```

**getMouseY** - 获取鼠标Y坐标

```java
/**
 * 获取鼠标Y坐标
 * @return 大于等于0表示获取成功
 */
public static int getMouseY()
```

**setMousePosition** - 设置鼠标位置

```java
/**
 * 设置鼠标位置
 * @param X 屏幕的X坐标，取值范围为正整数
 * @param Y 屏幕的Y坐标，取值范围为正整数
 * @return 大于0表示设置位置成功
 */
public static int setMousePosition(int X, int Y)
```

**setPressMouseButtonDelay** - 设置鼠标按键延时

```java
/**
 * 设置鼠标按键延时
 * @param MinDelay 最小延时时间，单位毫秒，默认30
 * @param MaxDelay 最大延时时间，单位毫秒，默认100
 * @return 1表示成功
 */
public static int setPressMouseButtonDelay(int MinDelay, int MaxDelay)
```

**setMouseMovementDelay** - 设置鼠标移动延时

```java
/**
 * 设置鼠标移动延时
 * @param MinDelay 最小延时时间，单位毫秒，默认4
 * @param MaxDelay 最大延时时间，单位毫秒，默认18
 * @return 1表示成功
 */
public static int setMouseMovementDelay(int MinDelay, int MaxDelay)
```

**setMouseMovementSpeed** - 设置鼠标移动速度

```java
/**
 * 设置鼠标移动速度
 * @param SpeedValue 移动速度，取值范围1-10，其他值无效，默认7
 * @return 1表示成功
 */
public static int setMouseMovementSpeed(int SpeedValue)
```

---

### MemoryOperationUtilsX86 内存操作工具类（X86）

本类提供内存读写、内存搜索、内存属性查询等功能，仅限32位使用。

#### 内存属性方法

**selectMemoryAddressAttribute** - 查询内存属性

```java
/**
 * 查询内存属性
 * @param hProcess 进程句柄
 * @param address 内存地址
 * @return 返回TagPmemory_Basic_InfoRmationX86对象
 */
public static TagPmemory_Basic_InfoRmationX86 selectMemoryAddressAttribute(int hProcess, int address)

/**
 * 查询内存属性（重载版本）
 * @param hProcess 进程句柄
 * @param address 内存地址
 * @param pmemory_basic_information 内存属性对象
 * @return 返回查询结果
 */
public static int selectMemoryAddressAttribute(int hProcess, int address, TagPmemory_Basic_InfoRmationX86 pmemory_basic_information)
```

**updateMemoryAddressAttribute** - 修改内存保护

```java
/**
 * 修改内存保护
 * @param hProcess 进程句柄
 * @param address 内存地址
 * @param dwSize 长度
 * @param attribute 属性
 * @return 返回原始保护值
 *
 * attribute可选值：
 * - 1: 禁止访问
 * - 2: 允许读取
 * - 4: 允许读写
 * - 16: 允许执行
 * - 32: 允许执行与读取
 * - 64: 允许读写
 * - 128: 禁止操作
 * - 256: 通知
 * - 512: 停用高速缓存
 */
public static int updateMemoryAddressAttribute(int hProcess, int address, int dwSize, int attribute)
```

**memoryAddressIsValid** - 内存地址是否有效

```java
/**
 * 内存地址是否有效
 * @param hProcess 进程ID
 * @param address 内存地址
 * @return 返回结果
 */
public static boolean memoryAddressIsValid(int hProcess, int address)
```

**memoryAddressIsStatic** - 是否为静态地址

```java
/**
 * 是否为静态地址
 * @param hProcess 进程句柄
 * @param address 内存地址
 * @return 返回结果
 */
public static boolean memoryAddressIsStatic(int hProcess, int address)
```

**memoryAddressIsBaseAddress** - 是否为基址

```java
/**
 * 是否为基址
 * @param hProcess 进程句柄
 * @param address 内存地址
 * @return 返回结果
 */
public static boolean memoryAddressIsBaseAddress(int hProcess, int address)
```

**memoryAddressGetLength** - 获取内存地址数据长度

```java
/**
 * 获取内存地址数据长度
 * @param hProcess 进程句柄
 * @param address 内存地址
 * @return 返回长度
 */
public static int memoryAddressGetLength(int hProcess, int address)
```

#### 内存读写方法

**readProcessMemoryByte** - 内存读字节

```java
/**
 * 内存读字节 返回文本HEX
 * @param hProcess 进程句柄
 * @param address 内存地址
 * @param dwSize 长度
 * @return 返回字节数组
 */
public static byte[] readProcessMemoryByte(int hProcess, int address, int dwSize)
```

**readProcessMemoryByteOffSet** - 内存读字节（带偏移）

```java
/**
 * 内存读字节 返回字节 附带偏移
 * @param hProcess 进程句柄
 * @param address 内存地址
 * @param dwSize 长度
 * @param offset 偏移数组
 * @return 返回字节数组
 */
public static byte[] readProcessMemoryByteOffSet(int hProcess, int address, int dwSize, int[] offset)
```

**writeProcessMemoryByte** - 内存写字节

```java
/**
 * 内存写字节 十六进制文本
 * @param hProcess 进程句柄
 * @param address 内存地址
 * @param dwSize 类型
 * @param lpBuffer 十六进制文本不带空格
 * @return 返回结果
 */
public static int writeProcessMemoryByte(int hProcess, int address, int dwSize, byte[] lpBuffer)
```

**writeProcessMemoryByteOffSet** - 内存写字节（带偏移）

```java
/**
 * 内存写字节 附带偏移
 * @param hProcess 进程句柄
 * @param address 内存地址
 * @param dwSize 类型
 * @param offset 偏移十进制数组
 * @param lpBuffer 十六进制文本不带空格
 * @return 返回结果
 */
public static int writeProcessMemoryByteOffSet(int hProcess, int address, int dwSize, byte[] lpBuffer, int[] offset)
```

#### 内存搜索方法

**threadSearchMemory** - 线程模式内存搜索

```java
/**
 * 线程模式首次内存搜索
 * @param hProcess 进程句柄
 * @param HexData 内存字节集
 * @param model 搜索模式
 * @param memoryCallBack 回调数据
 * @return 返回结果
 */
public static int threadSearchMemory(int hProcess, byte[] HexData, int model, MemoryCallBack memoryCallBack)
```

**threadMd5SearchMemory** - 线程模式特征码搜索

```java
/**
 * 线程模式首次特征内存搜索
 * @param hProcess 进程句柄
 * @param HexData 特征码字符串
 * @param memoryCallBack 回调数据
 * @return 返回结果
 */
public static int threadMd5SearchMemory(int hProcess, String HexData, MemoryCallBack memoryCallBack)
```

#### INI文件操作方法

**readIniFile** - 读数据到Ini

```java
/**
 * 读数据到Ini
 * @param filePath 配置名 null返回当前类
 * @param hex 十六进制大写
 * @return 返回数据
 */
public static String readIniFile(String filePath, String hex)
```

**writeIniFile** - 写数据到Ini

```java
/**
 * 写数据到Ini
 * @param filePath 配置名
 * @param hex 十六进制大写
 * @param ten 十进制文本
 */
public static void writeIniFile(String filePath, String hex, String ten)
```

#### 内存分配方法

**virtualAllocEx** - 申请内存

```java
/**
 * 申请内存
 * @param hProcess 进程句柄
 * @param lpBaseAddress 地址
 * @param dwSize 长度
 * @param flAllocationType 分配类型 4096|8192
 * @param flProtect 保护属性 4
 * @return 返回地址
 */
public static int virtualAllocEx(int hProcess, int lpBaseAddress, int dwSize, int flAllocationType, int flProtect)
```

**virtualFreeEx** - 释放内存

```java
/**
 * 释放内存
 * @param hProcess 进程句柄
 * @param lpBaseAddress 地址
 * @return 释放结果
 */
public static boolean virtualFreeEx(int hProcess, int lpBaseAddress)
```

---

## 枚举类说明

### WindowStyle 窗口样式枚举

窗口样式枚举定义了窗口的各种样式属性，包括标题栏、边框、居中、拖动、最大最小化按钮等功能。

```java
/**
 * 窗口样式枚举
 *
 * 可用样式值：
 * - window_style_nothing(0x0000): 什么也没有
 * - window_style_caption(0x0001): 标题栏
 * - window_style_border(0x0002): 边框
 * - window_style_center(0x0004): 窗口居中
 * - window_style_drag_border(0x0008): 拖动窗口边框
 * - window_style_drag_window(0x0010): 拖动窗口
 * - window_style_allow_maxWindow(0x0020): 允许窗口最大化
 * - window_style_icon(0x0040): 图标
 * - window_style_title(0x0080): 标题
 * - window_style_btn_min(0x0100): 控制按钮-最小化
 * - window_style_btn_max(0x0200): 控制按钮-最大化
 * - window_style_btn_close(0x0400): 控制按钮-关闭
 *
 * 复合样式：
 * - window_style_default: 默认样式（包含所有按钮）
 * - window_style_simple: 简单样式
 * - window_style_pop: 弹出窗口样式
 * - window_style_modal: 模态窗口样式
 * - window_style_modal_simple: 模态简单样式
 */
```

---

## 结构体说明

### TagRect 矩形结构

```java
/**
 * 矩形结构体
 *
 * 字段说明：
 * - left: 左边坐标
 * - top: 顶部坐标
 * - right: 右边坐标
 * - bottom: 底部坐标
 */
public class TagRect {
    public int left;
    public int top;
    public int right;
    public int bottom;
}
```

### TagPoint 点结构

```java
/**
 * 点坐标结构体
 *
 * 字段说明：
 * - x: X坐标
 * - y: Y坐标
 */
public class TagPoint {
    public long x;
    public long y;
}
```

### TagProcessenTry32 进程信息结构

```java
/**
 * 进程信息结构体
 *
 * 字段说明：
 * - th32ProcessID: 进程ID
 * - th32ParentProcessID: 父进程ID
 * - szExeFile: 可执行文件路径
 */
public class TagProcessenTry32 {
    public int th32ProcessID;
    public int th32ParentProcessID;
    public String szExeFile;
}
```

### TagThreadEntry32 线程信息结构

```java
/**
 * 线程信息结构体
 *
 * 字段说明：
 * - th32ThreadID: 线程ID
 * - th32OwnerProcessID: 所属进程ID
 */
public class TagThreadEntry32 {
    public int th32ThreadID;
    public int th32OwnerProcessID;
}
```

### TagModuleenTry32 模块信息结构（32位）

```java
/**
 * 模块信息结构体（32位）
 *
 * 字段说明：
 * - hModule: 模块句柄
 * - szExePath: 模块路径
 */
public class TagModuleenTry32 {
    public int hModule;
    public String szExePath;
}
```

### TagPmemory_Basic_InfoRmationX86 内存信息结构（X86）

```java
/**
 * 内存信息结构体（X86）
 *
 * 字段说明：
 * - BaseAddress: 基地址
 * - AllocationBase: 分配基址
 * - RegionSize: 区域大小
 * - Protect: 保护属性
 * - State: 状态
 * - lType: 类型
 */
public class TagPmemory_Basic_InfoRmationX86 {
    public int BaseAddress;
    public int AllocationBase;
    public int RegionSize;
    public int Protect;
    public int State;
    public int lType;
}
```

---

## 回调接口说明

### WindowEventCallBack 窗口事件回调

窗口事件回调接口用于处理各种窗口消息事件，包括鼠标、键盘、窗口状态变化等。

```java
/**
 * 窗口事件回调接口
 *
 * 可用回调方法：
 * - OnWndMouseMove: 窗口鼠标移动事件
 * - OnWndDrawWindow: 窗口绘制事件
 * - OnWndClose: 窗口关闭事件
 * - OnWndDestroy: 窗口销毁事件
 * - OnWndNCDestroy: 窗口非客户区销毁事件
 * - OnWndLButtonDown: 窗口鼠标左键按下事件
 * - OnWndLButtonUp: 窗口鼠标左键弹起事件
 * - OnWndRButtonDown: 窗口鼠标右键按下事件
 * - OnWndRButtonUp: 窗口鼠标右键弹起事件
 * - OnWndLButtonDBClick: 窗口鼠标左键双击事件
 * - OnWndRButtonDBClick: 窗口鼠标右键双击事件
 * - OnWndMouseHover: 窗口鼠标悬停事件
 * - OnWndMouseLeave: 窗口鼠标离开事件
 * - OnWndMouseWheel: 窗口鼠标滚轮事件
 * - OnWndKeyDown: 窗口键盘按下事件
 * - OnWndKeyUp: 窗口键盘弹起事件
 * - OnWndChar: 窗口字符输入事件
 * - OnWndSize: 窗口大小改变事件
 * - OnWndTimer: 窗口定时器事件
 * - OnWndSetFocus: 窗口获得焦点事件
 * - OnWndKillFocus: 窗口失去焦点事件
 * - OnDropFiles: 拖动文件到窗口事件
 * - OnWndXCTimer: 炫彩定时器消息
 */
public class WindowEventCallBack {
    public boolean OnWndMouseMove(int hWindow, long flags, int x, int y) { return false; }
    public boolean OnWndClose(int hWindow) { return false; }
    public boolean OnWndDestroy(int hWindow) { return false; }
    // ... 更多回调方法
}
```

### ResponseEventCallBack 响应事件回调

```java
/**
 * 响应事件回调接口
 * 用于热键监视等场景的事件响应
 */
public interface ResponseEventCallBack {
    /**
     * 响应事件回调方法
     * @return 处理结果
     */
    boolean response();
}
```

### KeyCodeCallBack 键盘回调

```java
/**
 * 键盘回调接口
 * 用于全局键盘钩子事件处理
 */
public interface KeyCodeCallBack {
    /**
     * 键盘事件回调方法
     * @param wParam 消息参数
     * @param vkCode 虚拟键码
     * @param scanCode 扫描码
     * @param flags 标志
     * @param time 时间戳
     * @param dwExtraInfo 额外信息
     * @return 处理结果
     */
    boolean callback(int wParam, int vkCode, int scanCode, int flags, int time, int dwExtraInfo);
}
```

### MemoryCallBack 内存回调

```java
/**
 * 内存回调接口
 * 用于内存搜索结果回调
 */
public interface MemoryCallBack {
    /**
     * 内存数据回调方法
     * @param memoryInfo 内存信息
     * @param isStatic 是否静态地址
     * @param address 地址
     * @param hexAddress 十六进制地址
     * @param protect 保护属性
     * @param type 类型
     * @return 处理结果
     */
    boolean data(TagPmemory_Basic_InfoRmationX86 memoryInfo, String isStatic, int address, String hexAddress, int protect, int type);
}
```

### ModuleCallBack 模块回调

```java
/**
 * 模块回调接口
 * 用于模块枚举结果回调
 */
public interface ModuleCallBack {
    /**
     * 模块数据回调方法
     * @param moduleInfo 模块信息
     */
    void data(TagModuleenTry32 moduleInfo);
}
```

---

## 使用示例

### 窗口操作示例

```java
// 获取桌面窗口句柄
long desktopHwnd = WindowOperationUtils.windowGetDesktopWindow();

// 查找窗口句柄
int targetHwnd = WindowOperationUtils.windowGetHwndTow(processId, "Button", "确定");

// 获取窗口标题
String title = WindowOperationUtils.windowGetTitle(targetHwnd);

// 设置窗口标题
WindowOperationUtils.windowSetTitle(targetHwnd, "新标题");

// 窗口置顶
WindowOperationUtils.windowAlwaysTop(targetHwnd, true);

// 窗口关闭
WindowOperationUtils.windowClose(targetHwnd);
```

### 进程操作示例

```java
// 枚举进程
List<TagProcessenTry32> processes = ProcessAndThreadUtils.processEnumInfo();
for (TagProcessenTry32 process : processes) {
    System.out.println(process.szExeFile + " : " + process.th32ProcessID);
}

// 进程名取PID
int pid = ProcessAndThreadUtils.processNameGetPid("notepad.exe");

// 打开进程
int handle = ProcessAndThreadUtils.openProcess(pid);

// 结束进程
ProcessAndThreadUtils.overProcess(pid);
```

### 内存操作示例

```java
// 打开进程
int handle = ProcessAndThreadUtils.openProcess(pid);

// 读取内存
byte[] data = MemoryOperationUtilsX86.readProcessMemoryByte(handle, address, 4);
int value = ByteUtils.bytesArrayToInt(data);

// 写入内存
byte[] writeData = ByteUtils.intToBytesArray(100);
MemoryOperationUtilsX86.writeProcessMemoryByte(handle, address, 4, writeData);

// 搜索内存
MemoryOperationUtilsX86.threadSearchMemory(handle, searchData, 0, new MemoryCallBack() {
    @Override
    public boolean data(TagPmemory_Basic_InfoRmationX86 info, String isStatic, int address, String hex, int protect, int type) {
        System.out.println("找到地址: " + hex);
        return false;
    }
});
```

### 热键监视示例

```java
// 监视单个热键
int id = SystemUtils.listeningHotKeyOne(new ResponseEventCallBack() {
    @Override
    public boolean response() {
        System.out.println("F9键被按下");
        return false;
    }
}, 0x78, 150); // 0x78是F9的虚拟键码

// 监视组合热键（Ctrl+Shift+A）
int id2 = SystemUtils.listeningHotKeyTow(new ResponseEventCallBack() {
    @Override
    public boolean response() {
        System.out.println("Ctrl+Shift+A被按下");
        return false;
    }
}, 0x41, 6, 150); // 0x41是A，6=2(Ctrl)+4(Shift)

// 卸载热键
SystemUtils.removeListeningHotKeyOne(id);
```

### DD键鼠驱动示例

```java
// 鼠标移动到指定坐标
DdDriver.mov(100, 100);

// 鼠标左键点击
DdDriver.btn(1); // 按下
DdDriver.btn(2); // 放开

// 鼠标滚轮
DdDriver.whl(1); // 向前滚动

// 键盘按键
DdDriver.key(0x41, 1); // 按下A键
DdDriver.key(0x41, 2); // 放开A键

// 直接输入字符串
DdDriver.str("Hello World");
```

---

## 注意事项

1. **驱动安装**：使用DD键鼠驱动或幽灵键鼠驱动前，需要先安装对应的驱动程序。

2. **权限问题**：部分操作需要管理员权限，如进程内存读写、远程线程创建等。

3. **平台兼容性**：部分功能仅支持32位或64位系统，请根据实际需求选择合适的类和方法。

4. **内存操作**：内存读写操作需要谨慎，错误的地址可能导致程序崩溃。

5. **编码转换**：框架提供了丰富的编码转换功能，使用时请注意选择正确的编码格式。

---

## 技术支持

- 官网地址：https://www.snailcatmall.com
- 官方QQ群：128828632
- 技术支持邮箱：1012518027@qq.com
- 视频学习文档合集：https://space.bilibili.com/29826047/lists?sid=7051754&spm_id_from=333.788.0.0
- 炫彩界面库官方API文档：http://8.138.59.98/xcgui/index.html

---

## ModuleOperationUtilsJNI 核心JNI接口类

本类是框架的核心JNI接口类，提供了所有的Windows原生API封装。

### 系统基础方法

**systemIs32 / systemIs64** - 判断系统位数

```java
/**
 * 判断是否为32位系统
 * @return 是32位返回true
 */
public static boolean systemIs32()

/**
 * 判断是否为64位系统
 * @return 是64位返回true
 */
public static boolean systemIs64()
```

**tileDir / initOutFile** - 路径和初始化方法

```java
/**
 * 返回运行文件夹路径
 */
public static String tileDir()

/**
 * 初始化从JAR包中复制DLL文件
 * @return 返回结果
 */
public static boolean initOutFile()
```

### INI文件操作方法

**WritePrivateProfile / ReadPrivateProfile** - INI文件读写

```java
/**
 * 写入INI文件
 * @param filePath 文件路径
 * @param sectionName 节名称
 * @param ConfigurationItemName 配置项名称
 * @param dataValue 数据值
 */
public static native boolean WritePrivateProfile(String filePath, String sectionName, String ConfigurationItemName, String dataValue)

/**
 * 读取INI文件
 * @param filePath 文件路径
 * @param sectionName 节名称
 * @param ConfigurationItemName 配置项名称
 */
public static native String ReadPrivateProfile(String filePath, String sectionName, String ConfigurationItemName)
```

### 进程线程操作方法

**CreateToolhelp32Snapshot** - 创建系统快照

```java
/**
 * 创建系统快照
 * @param dwFlags 快照标志
 * @param th32ProcessID 进程ID
 */
public static native int CreateToolhelp32Snapshot(int dwFlags, int th32ProcessID)
```

**Process32FirstW / Process32NextW** - 枚举进程

```java
public static native boolean Process32FirstW(int hSnapshot, TagProcessenTry32 pEntry32)
public static native boolean Process32NextW(int hSnapshot, TagProcessenTry32 pEntry32)
```

**OpenProcess / TerminateProcess** - 进程操作

```java
public static native int OpenProcess(int dwDesiredAccess, int bInheritHandle, int dwProcessId)
public static native int TerminateProcess(int hProcess, int uExitCode)
```

**Thread32First / Thread32Next** - 枚举线程

```java
public static native boolean Thread32First(int hSnapshot, TagThreadEntry32 pEntry32)
public static native boolean Thread32Next(int hSnapshot, TagThreadEntry32 pEntry32)
```

**OpenThread / SuspendThread / ResumeThread / TerminateThread** - 线程操作

```java
public static native int OpenThread(int dwDesiredAccess, int dwThreadId)
public static native int SuspendThread(int hThread)
public static native int ResumeThread(int hThread)
public static native int TerminateThread(int hThread, int dwExitCode)
```

**ZwSuspendProcess / ZwResumeProcess** - 进程暂停恢复

```java
public static native boolean ZwSuspendProcess(int hProcess)
public static native boolean ZwResumeProcess(int hProcess)
```

### 窗口操作方法

**GetDesktopWindow** - 获取桌面窗口

```java
public static native int GetDesktopWindow()
```

**GetWindow** - 获取窗口

```java
public static native int GetWindow(int hWnd, int uCmd)
```

**FindWindowW / FindWindowExW** - 查找窗口

```java
public static native int FindWindowW(String lpClassName, String lpWindowName)
public static native int FindWindowExW(int hWndParent, int hWndChildAfter, String lpszClass, String lpszWindow)
```

**ShowWindow** - 显示窗口

```java
public static native int ShowWindow(int hWnd, int nCmdShow)
```

**SetParent** - 设置父窗口

```java
public static native int SetParent(int hWndChild, int hWndNewParent)
```

**GetWindowRect / GetClientRect** - 获取矩形

```java
public static native int GetWindowRect(int hWnd, TagRect lpRect)
public static native boolean GetClientRect(int hWnd, TagRect rect)
```

**MoveWindow** - 移动窗口

```java
public static native int MoveWindow(int hWnd, int x, int y, int nWidth, int nHeight, boolean bRepaint)
```

**SetWindowPos** - 设置窗口位置

```java
public static native boolean SetWindowPos(int hWnd, int hWndInsertAfter, int x, int y, int cx, int cy, int uFlags)
```

**GetWindowTextW / SetWindowTextW** - 窗口标题操作

```java
public static native int GetWindowTextW(int hWnd, char[] lpString, int nMaxCount)
public static native int SetWindowTextW(int hWnd, String lpString)
```

**GetClassNameW** - 获取类名

```java
public static native int GetClassNameW(int hWnd, char[] lpClassName, int nMaxCount)
```

**GetParent / SetForegroundWindow** - 窗口层级操作

```java
public static native int GetParent(int hWnd)
public static native int SetForegroundWindow(int hWnd)
```

**GetFocus** - 获取焦点

```java
public static native int GetFocus()
```

**ClientToScreen / ScreenToClient** - 坐标转换

```java
public static native int ClientToScreen(int hWnd, TagPoint tagPOINT)
public static native boolean ScreenToClient(int hWnd, TagPoint lpPoint)
```

**GetCursorPos** - 获取鼠标位置

```java
public static native boolean GetCursorPos(TagPoint lpPoint)
```

**InvalidateRect** - 窗口重绘

```java
public static native boolean InvalidateRect(int hWnd, TagRect lpRect, boolean bErase)
```

**SetLayeredWindowAttributes** - 透明度设置

```java
public static native boolean SetLayeredWindowAttributes(int hwnd, int crKey, int bAlpha, int dwFlags)
```

**SetWindowLongW / GetWindowLongW** - 窗口扩展样式

```java
public static native long SetWindowLongW(int hWnd, int nIndex, long dwNewLong)
public static native int GetWindowLongW(int hWnd, int nIndex)
```

**GetAsyncKeyState** - 按键状态

```java
public static native int GetAsyncKeyState(int vkey)
```

### 消息发送方法

**SendMessageW / SendMessageWInfo** - 发送消息

```java
public static native int SendMessageW(int hWnd, int Msg, byte[] wParam, byte[] lParam)
public static native int SendMessageWInfo(int hWnd, int Msg, int wParam, int lParam)
```

**PostMessageW** - 投递消息

```java
public static native boolean PostMessageW(int hWnd, int Msg, byte[] wParam, byte[] lParam)
```

**SendMessageTimeoutW** - 超时消息

```java
public static native int SendMessageTimeoutW(int hWnd, int Msg, int wParam, int lParam, int fuFlags, int uTimeout, byte[] lpdwResult)
```

### 注册表操作方法

**MyOpenRegistryGroup** - 打开注册表

```java
public static native int MyOpenRegistryGroup(int RegistryGroup, String lpValueName, int IsCreate, int none)
```

**RegCloseKey** - 关闭注册表

```java
public static native int RegCloseKey(int hKey)
```

**RegQueryValueExW / RegSetValueExW** - 注册表值操作

```java
public static native int RegQueryValueExW(int hKey, String lpValueName, int lpReserved, byte[] lpType, byte[] lpData, int lpcbData)
public static native int RegSetValueExW(int hKey, String lpValueName, int dwType, byte[] lpData, int cbData)
```

**RegDeleteValueW / RegDeleteKeyW** - 删除注册表

```java
public static native int RegDeleteValueW(int hKey, String lpSubKey)
public static native int RegDeleteKeyW(int hKey, String lpSubKey)
```

**RegEnumKeyW** - 枚举注册表

```java
public static native int RegEnumKeyW(int hKey, int dwIndex, char[] lpName, int cchName)
```

**RegFlushKey** - 刷新注册表

```java
public static native int RegFlushKey(int hKey)
```

### 内存操作方法

**MyVirtualQueryEx** - 查询内存信息

```java
public static native int MyVirtualQueryEx(int hProcess, int lpAddress, TagPmemory_Basic_InfoRmationX86 pmemory_basic_information, int bit)
```

**MyVirtualProtectEx** - 修改内存保护

```java
public static native int MyVirtualProtectEx(int hProcess, int dwSize, int flNewProtect)
```

**MyReadProcessMemory / MyWriteProcessMemory** - 内存读写

```java
public static native int MyReadProcessMemory(int hProcess, int lpBaseAddress, int dwSize, byte[] Buffer)
public static native int MyWriteProcessMemory(int hProcess, int lpBaseAddress, int dwSize, byte[] lpBuffer)
```

**VirtualAllocEx / VirtualFreeEx** - 内存分配释放

```java
public static native int VirtualAllocEx(int hProcess, int lpBaseAddress, int dwSize, int flAllocationType, int flProtect)
public static native boolean VirtualFreeEx(int hProcess, int lpBaseAddress)
```

### 键鼠驱动方法

#### DD驱动

**Install_DD_VIP / UnInstall_DD_VIP** - 安装卸载

```java
public static native boolean Install_DD_VIP(String Path, String OutKey)
public static native boolean UnInstall_DD_VIP(String Path)
```

**Load_DD_Address / Init_DD** - 加载初始化

```java
public static native void Load_DD_Address(String Path, int bit)
public static native int Init_DD(int bit)
```

**DDBtn / DDMov / DDWhl / DDkey / DDstr** - DD操作

```java
public static native int DDBtn(int btn)
public static native int DDMov(int x, int y)
public static native int DDMovR(int dx, int dy)
public static native int DDWhl(int whl)
public static native int DDkey(int ddcode, int flag)
public static native int DDstr(String str)
```

#### Ghost驱动

**Install_Ghost_VIP / UnInstall_Ghost_VIP** - 安装卸载

```java
public static native boolean Install_Ghost_VIP(String Path, String OutKey)
public static native boolean UnInstall_Ghost_VIP(String Path)
```

**Load_Ghost_Address** - 加载地址

```java
public static native void Load_Ghost_Address(String Path, int bit)
```

#### Md驱动

**Install_Md_VIP / UnInstall_Md_VIP** - 安装卸载

```java
public static native boolean Install_Md_VIP(String Path, String OutKey)
public static native boolean UnInstall_Md_VIP(String Path)
```

**Load_Md_Address** - 加载地址

```java
public static native void Load_Md_Address(String Path, int bit)
```

**M_Open / M_Close** - 设备开关

```java
public static native void M_Open(int Nbr)
public static native int M_Close()
```

**M_Delay / M_DelayRandom** - 延时

```java
public static native int M_Delay(int time)
public static native int M_DelayRandom(int Min_time, int Max_time)
```

**键盘操作**

```java
public static native int M_KeyPress(int HidKeyCode, int Nbr)
public static native int M_KeyDown(int HidKeyCode)
public static native int M_KeyUp(int HidKeyCode)
public static native int M_KeyState(int HidKeyCode)
public static native int M_ReleaseAllKey()
public static native int M_KeyInputString(String InputStr)
public static native int M_KeyInputStringGBK(String InputStr)
public static native int M_KeyInputStringUnicode(String InputStr)
```

**鼠标操作**

```java
public static native int M_LeftClick(int Nbr)
public static native int M_LeftDoubleClick(int Nbr)
public static native int M_LeftDown()
public static native int M_LeftUp()
public static native int M_RightClick(int Nbr)
public static native int M_RightDown()
public static native int M_RightUp()
public static native int M_MiddleClick(int Nbr)
public static native int M_MiddleDown()
public static native int M_MiddleUp()
public static native int M_ReleaseAllMouse()
public static native int M_MouseKeyState(int MouseKeyCode)
public static native int M_MouseWheel(int Nbr)
public static native int M_MoveR(int x, int y)
public static native int M_MoveTo(int x, int y)
public static native int M_GetCurrMousePos(byte[] x, byte[] y)
```

### CapStone反汇编方法

**CapStone_cs_open** - 打开引擎

```java
public static native int CapStone_cs_open(int arch, int mode)
```

**CapStone_cs_option** - 设置选项

```java
public static native int CapStone_cs_option(int cs_opt_type, int lens)
```

**CapStone_cs_disasm** - 反汇编

```java
public static native int CapStone_cs_disasm(byte[] pOpCode, int pAddr, Tag_cs_insnCallBack tag_cs_insnCallBack)
```

**CapStone_cs_free** - 关闭引擎

```java
public static native void CapStone_cs_free()
```

### 炫彩界面库方法

**XInitXCGUI / XRunXCGUI / XExitXCGUI** - 基础方法

```java
public static native boolean XInitXCGUI(boolean bD2D)
public static native void XRunXCGUI()
public static native void XExitXCGUI()
```

**XWnd_Create** - 创建窗口

```java
public static native int XWnd_Create(int x, int y, int cx, int cy, String pTitle, int hWndParent, int XCStyle)
```

**XWnd_ShowWindow** - 显示窗口

```java
public static native void XWnd_ShowWindow(int hWindow, int nCmdShow)
```

**XWnd_RegEventC1** - 注册事件

```java
public static native boolean XWnd_RegEventC1(int hWindow, int nEvent, String windowEventCallBackName, Object windowEventCallBack)
```

### 其他方法

**SendInput** - 发送输入

```java
public static native int SendInput(int x, int y, int keyType, int valueType)
```

**MAKELPARAM** - 组合参数

```java
public static native int MAKELPARAM(int x, int y)
```

**GetSystemMetrics** - 系统度量

```java
public static native int GetSystemMetrics(int nIndex)
```

**GetDC / ReleaseDC** - 设备上下文

```java
public static native int GetDC(int hWnd)
public static native int ReleaseDC(int hWnd, int hDC)
```

---

## 版本信息

- 框架版本：V1.2.7
- 更新日期：2026年2月
