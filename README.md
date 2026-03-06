# JSCM 核心框架完全指南

# 主要更新网站：[https://gitee.com/SnailcatMall/projects](https://gitee.com/SnailcatMall/Jscm-Core-FrameWork)

## 框架概述

**JSCM（Java System Control Management）** 是一套基于 Java JNI（Java Native Interface）技术构建的 Windows 系统底层操作框架。该框架为 Java 开发者提供了强大的 Windows 操作系统底层能力访问接口，涵盖了窗口管理、进程线程控制、内存操作、注册表读写、硬件驱动模拟等核心功能。JSCM 框架的设计参考了精易模块、超级模块、乐易模块等成熟产品的架构思想，旨在为 Java 语言在 Windows 平台上的系统级开发提供一站式解决方案。

JSCM 框架的核心优势在于其对底层 Windows API 的完整封装和对硬件驱动的高效调用。通过 JNI 桥接技术，Java 开发者无需编写复杂的本地代码，即可直接调用底层的 Windows 系统功能。框架支持 X86（32位）和 X64（64位）两大平台，能够满足不同架构应用程序的开发需求。此外，框架还集成了 CapStone 反汇编引擎，支持内存特征码搜索和反汇编分析功能，配合 DD 键鼠驱动、幽灵键鼠驱动等硬件抽象层功能，使得自动化脚本、游戏辅助工具、窗口管理工具等开发变得更加简便快捷。

---

## 目录

- [框架概述](#框架概述)
- [系统要求](#系统要求)
- [快速开始](#快速开始)
- [核心模块详解](#核心模块详解)
  - [ByteUtils 编码转换工具类](#byteutils-编码转换工具类)
  - [WindowOperationUtils 窗口操作工具类](#windowoperationutils-窗口操作工具类)
  - [SystemUtils 系统工具类](#systemutils-系统工具类)
  - [ProcessAndThreadUtils 进程线程工具类](#processandthreadutils-进程线程工具类)
  - [MemoryOperationUtilsX86/X64 内存操作工具类](#memoryoperationutilsx86x64-内存操作工具类)
  - [RegistryOperationUtilsX86/X64 注册表操作工具类](#registryoperationutilsx86x64-注册表操作工具类)
  - [键鼠驱动模块](#键鼠驱动模块)
  - [报表模块](#报表模块)
  - [回调接口](#回调接口)
- [使用案例](#使用案例)
- [常见问题](#常见问题)
- [技术支持](#技术支持)

---

## 系统要求

### 运行环境

JSCM 框架对运行环境有以下基本要求，确保这些条件满足是框架正常工作的前提。操作系统方面，框架支持 Windows 10、Windows 11 以及 Windows Server 2016 及以上版本。由于框架涉及大量的 Windows 本地 API 调用，因此仅支持在 Windows 操作系统上运行，Linux 和 macOS 平台无法使用。Java 运行时环境需要 JDK 1.8 或更高版本，建议使用 JDK 11 或 JDK 17 以获得最佳性能和稳定性。框架的 DLL 文件分为 32 位和 64 位两个版本，使用时需要注意 DLL 位数必须与 JDK 位数保持一致，否则会导致加载失败。

### 内存配置

在进行大规模内存操作（如内存搜索、特征码扫描）时，可能需要调整 Java 虚拟机的内存参数。建议在运行参数中添加以下配置，以确保框架在高负荷场景下的稳定运行。这些参数能够有效避免因内存不足导致的 OutOfMemoryError 问题，同时关闭 GC Overhead 限制以适应长时间运行的系统操作任务。

```bash
-encoding utf-8 -charset utf-8 -XX:-UseGCOverheadLimit -Xms1T -Xmx1T -XX:MaxPermSize=1T
```

### 依赖配置

在使用 JSCM 框架前，需要正确配置框架的 DLL 路径。框架通过 PathFileJSCM 类来管理 DLL 文件的加载路径，开发者需要在代码初始化阶段指定正确的路径。以下是开发环境和生产环境的配置示例，开发者应根据实际部署情况选择合适的配置方式。

**开发环境配置：**

```java
PathFileJSCM.setDebugFile32("C:\\Users\\www10\\IdeaProjects\\scmJnaApi\\JscmFunction\\Jscm\\_int\\Jscm\\release\\win32\\linker\\");
PathFileJSCM.setDebugFile64("G:\\Users\\www10\\IdeaProjects\\scmJnaApi\\JscmFunction\\Jscm\\_int\\Jscm\\release\\x64\\linker\\");
PathFileJSCM.setIsDebug(true);
```

**生产环境配置：**

```java
PathFileJSCM.setDebugFile32(PathFileJSCM.getJarPath() + "demo\\x86\\");
PathFileJSCM.setDebugFile64(PathFileJSCM.getJarPath() + "demo\\x64\\");
PathFileJSCM.setIsDebug(false);
```

---

## 快速开始

为了让开发者能够快速上手 JSCM 框架，本节提供一个完整的示例项目创建流程。从环境搭建到第一个功能调用，逐步引导开发者完成框架的集成和使用。

### 步骤一：创建 Maven 项目

首先创建一个标准的 Maven 项目，并在 pom.xml 文件中添加 JSCM 框架的依赖。如果框架尚未发布到 Maven 中央仓库，需要将框架的 JAR 包安装到本地仓库或搭建私有仓库。

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <groupId>com.scm</groupId>
    <artifactId>jscm-core-framework</artifactId>
    <version>1.2.8</version>
    <packaging>jar</packaging>
    
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
    </properties>
</project>
```

### 步骤二：初始化框架

在应用程序启动时，初始化 JSCM 框架并加载必要的 DLL 文件。初始化操作通常放在 main 方法或应用启动类的静态代码块中执行。

```java
import com.scm.all.export.PathFileJSCM;

public class App {
    static {
        // 初始化 JSCM 框架
        String jarPath = SystemUtils.runFilePath();
        PathFileJSCM.setDebugFile32(jarPath + "demo\\x86\\");
        PathFileJSCM.setDebugFile64(jarPath + "demo\\x64\\");
        PathFileJSCM.setIsDebug(true);
        System.out.println("JSCM 框架初始化完成");
    }
    
    public static void main(String[] args) {
        // 应用程序代码
    }
}
```

### 步骤三：调用框架功能

框架初始化完成后，即可调用各类工具类完成系统操作任务。以下示例展示了如何查找窗口并获取其信息，帮助开发者理解框架的基本使用流程。

```java
import com.scm.all.export.WindowOperationUtils;
import com.scm.all.export.SystemUtils;

public class QuickStartDemo {
    public static void main(String[] args) {
        try {
            // 获取桌面窗口句柄
            long desktopHwnd = WindowOperationUtils.windowGetDesktopWindow();
            System.out.println("桌面窗口句柄: " + desktopHwnd);
            
            // 查找指定窗口
            int notepadHwnd = WindowOperationUtils.windowGetHwndTow(0, "Notepad", "无标题 - 记事本");
            if (notepadHwnd != 0) {
                System.out.println("找到记事本窗口，句柄: " + notepadHwnd);
                
                // 获取窗口标题
                String title = WindowOperationUtils.windowGetTitle(notepadHwnd);
                System.out.println("窗口标题: " + title);
                
                // 获取窗口类名
                String className = WindowOperationUtils.windowGetClassName(notepadHwnd);
                System.out.println("窗口类名: " + className);
                
                // 获取窗口位置和大小
                int[] rect = WindowOperationUtils.windowGetRectToSize(notepadHwnd);
                System.out.println("窗口位置: x=" + rect[0] + ", y=" + rect[1] + 
                                  ", 宽度=" + rect[2] + ", 高度=" + rect[3]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

---

## 核心模块详解

JSCM 框架按照功能特性划分为多个核心模块，每个模块专注于特定的系统操作领域。以下是各模块的详细说明，包含完整的接口列表和使用示例。

### ByteUtils 编码转换工具类

ByteUtils 是 JSCM 框架中最基础的工具类之一，提供了各种数据类型与字节数组之间的转换功能。该类支持多种编码格式的相互转换，包括 UTF-8、Unicode、ASCII、UTF-16 等，是进行底层数据处理不可或缺的工具。在内存操作、网络通信、文件处理等场景中，字节数组与Java数据类型之间的转换极为频繁，ByteUtils 类极大简化了这些操作。

#### 数值类型转换

ByteUtils 提供了完善的基本数据类型与字节数组相互转换功能，覆盖了 int、long、short、double、float、char 等常用类型。所有转换方法都考虑了字节序问题，默认使用小端序（Little Endian）进行转换，这与 Windows 系统的字节序一致。

```java
import com.scm.all.export.ByteUtils;

// int 类型转换
int intValue = 12345678;
byte[] intBytes = ByteUtils.intToBytesArray(intValue);  // int 转 byte[]
int resultInt = ByteUtils.bytesArrayToInt(intBytes);    // byte[] 转 int

// long 类型转换
long longValue = 9876543210L;
byte[] longBytes = ByteUtils.longToBytesArray(longValue);  // long 转 byte[]
long resultLong = ByteUtils.bytesArrayToLong1(longBytes);  // byte[] 转 long

// short 类型转换
short shortValue = 32000;
byte[] shortBytes = ByteUtils.shortToBytesArray(shortValue);  // short 转 byte[]
short resultShort = ByteUtils.bytesArrayToShort1(shortBytes);  // byte[] 转 short

// double 类型转换
double doubleValue = 3.1415926;
byte[] doubleBytes = ByteUtils.doubleToBytesArray(doubleValue);  // double 转 byte[]
double resultDouble = ByteUtils.bytesArrayToDouble1(doubleBytes);  // byte[] 转 double

// float 类型转换
float floatValue = 3.14f;
byte[] floatBytes = ByteUtils.floatToBytesArray(floatValue);  // float 转 byte[]
float resultFloat = ByteUtils.bytesArrayToFloat1(floatBytes);  // byte[] 转 float
```

#### 编码转换方法

在处理 Windows 应用程序时，经常需要处理各种字符串编码格式。ByteUtils 提供了全面的编码转换支持，能够满足不同场景下的编码需求。这些方法在窗口标题获取、进程名称处理、内存数据解析等场景中广泛使用。

```java
// UTF-8 宽字符串转换（常用）
String utf8String = "Hello World 你好世界";
byte[] utf8Bytes = ByteUtils.utf8ToBytesArray(utf8String);
String resultUtf8 = ByteUtils.bytesArrayToUtf8(utf8Bytes);

// Unicode 编码转换
byte[] unicodeBytes = ByteUtils.utf8ToUniCodeArray(utf8String);
String resultUnicode = ByteUtils.uniCodeArrayToUtf8(unicodeBytes);

// UTF-16 宽字符串转换
byte[] utf16Bytes = "测试字符串".getBytes(java.nio.charset.StandardCharsets.UTF_16LE);
String resultUtf16 = ByteUtils.bytesArrayUTF16ToWideString(utf16Bytes, true);

// ASCII 宽字符串转换
byte[] asciiBytes = ByteUtils.asciiWideStringToBytesArray("Test String");
String resultAscii = ByteUtils.bytesArrayToAsciiWideString(asciiBytes);
```

#### 进制转换方法

进制转换在内存地址处理、特征码扫描、调试分析等场景中使用频繁。ByteUtils 提供了完整的进制转换功能，支持十进制、十六进制、二进制之间的相互转换。

```java
// 整数转十六进制字符串
int intValue = 255;
String hexString = ByteUtils.intToHexString(intValue);  // "FF"
String hexStringWithPrefix = ByteUtils.intToHex(intValue);  // "0xFF"

// 十六进制字符串转字节数组
String hexStr = "48 65 6C 6C 6F";
byte[] hexBytes = ByteUtils.hexStrToBytes(hexStr);  // 空格可分隔

// 字节数组转十六进制字符串
byte[] data = {0x48, 0x65, 0x6C, 0x6C, 0x6F};
String hexResult = ByteUtils.bytesToHexString(data);  // "48656C6C6F"

// 十六进制字符串转整数
String hexNum = "FF";
int decimalValue = ByteUtils.hexStrToInt1(hexNum);  // 255

// 长整型十六进制转换
String longHex = "FFFFFFFF";
long longValue = ByteUtils.hexToLong1(longHex);
```

#### 字节数组操作方法

字节数组操作是底层编程的基础，ByteUtils 提供了丰富的数组处理功能，包括数组查找、截取、合并、比较等操作。这些方法在数据解析、协议处理、文件操作等场景中非常实用。

```java
// 字节数组查找
byte[] mainArray = {0x01, 0x02, 0x03, 0x04, 0x05, 0x06};
byte[] searchArray = {0x03, 0x04};
int index = ByteUtils.indexOf(mainArray, searchArray, 0);  // 返回找到的位置

// 字节数组截取
byte[] leftArray = ByteUtils.subByteLeftArray(mainArray, 3);  // 取左边3个字节
byte[] midArray = ByteUtils.subByteArray(mainArray, 1, 3);     // 取中间字节

// 字节数组合并
byte[] array1 = {0x01, 0x02};
byte[] array2 = {0x03, 0x04};
byte[] mergedArray = ByteUtils.byteMerger(array1, array2);  // 合并两个数组

// 字节数组比较
boolean isEqual = ByteUtils.equalsByteArray(array1, array2);  // 比较是否相等

// 字符数组查找
char[] mainChars = {'A', 'B', 'C', 'D', 'E'};
char[] searchChars = {'C', 'D'};
int charIndex = ByteUtils.indexOf(mainChars, searchChars, 0);
```

#### 特征码搜索方法

特征码搜索是游戏辅助和软件安全分析中的核心技术，ByteUtils 提供了基于十六进制特征码的内存搜索功能。通过特征码定位，可以快速找到目标程序中的关键代码位置。

```java
// 特征码匹配
byte[] memoryData = {0x55, 0x8B, 0xEC, 0x83, 0xEC, 0x40, 0x53};
byte[] signature = ByteUtils.hexMd5ToBytesArrays("55 8B EC ?? 83 EC ?? 53");
// ? 表示任意字节

// 特征码索引搜索
int sigIndex = ByteUtils.hexToMd5BytesIndex(memoryData, signature);
if (sigIndex >= 0) {
    System.out.println("特征码位置偏移: " + sigIndex);
}

// 特征码匹配验证
boolean matched = ByteUtils.hexToMd5BytesMatches(memoryData, signature);
```

#### 字符串搜索算法

ByteUtils 实现了多种经典的字符串搜索算法，包括 Sunday 算法、BF（暴力匹配）算法、KMP 算法等。不同的算法在不同的数据规模和模式特点下表现各异，开发者可以根据实际需求选择合适的算法。

```java
String text = "ABABABCDABCDABCD";
String pattern = "ABCD";

// Sunday 算法（效率最高）
int sundayIndex = ByteUtils.sundayString(text, pattern);

// BF 算法（暴力匹配）
int bfIndex = ByteUtils.bfString(text, pattern);

// KMP 算法（预处理O(m)，匹配O(n)）
int kmpIndex = ByteUtils.kmpString(text, pattern);

// 字节数组搜索
byte[] byteText = {0x01, 0x02, 0x03, 0x04, 0x05};
byte[] bytePattern = {0x03, 0x04};
int byteIndex = ByteUtils.sundayByteIndex(byteText, bytePattern, 0);
```

---

### WindowOperationUtils 窗口操作工具类

WindowOperationUtils 是 JSCM 框架中功能最为庞大的模块之一，提供了全面的 Windows 窗口操作能力。该类封装了 Windows API 中的窗口管理功能，支持窗口查找、属性获取与设置、位置调整、样式修改等操作。无论是自动化测试工具、界面辅助工具还是游戏辅助软件，窗口操作都是核心功能之一。

#### 窗口基础操作

窗口基础操作涵盖了窗口句柄获取、窗口状态控制、窗口标题和类名管理等基本功能。通过这些接口，开发者可以获取系统中任意窗口的信息，并对其进行各种控制操作。

```java
import com.scm.all.export.WindowOperationUtils;
import com.scm.all.enumBox.WindowStyle;

// 获取桌面窗口句柄
long desktopHwnd = WindowOperationUtils.windowGetDesktopWindow();
System.out.println("桌面窗口句柄: " + desktopHwnd);

// 获取光标位置
int[] caretPos = WindowOperationUtils.windowGetCaretPos();
System.out.println("光标位置: x=" + caretPos[0] + ", y=" + caretPos[1]);

// 窗口是否响应（检测窗口是否响应消息）
boolean isResponsive = WindowOperationUtils.windowSendMessageTimeoutW(notepadHwnd);
System.out.println("窗口是否响应: " + isResponsive);

// 窗口重绘
WindowOperationUtils.windowInvalidateRect(notepadHwnd);

// 枚举循环关闭窗口
WindowOperationUtils.enumWindowClose("Notepad");
```

#### 窗口查找方法

窗口查找是窗口操作中最常用的功能之一，JSCM 提供了多种查找方式，包括精确查找、模糊查找、进程内查找等。这些方法能够满足不同场景下的窗口定位需求。

```java
// 精确查找窗口（进程ID + 类名 + 标题）
int hwnd1 = WindowOperationUtils.windowGetHwndTow(0, "Notepad", "无标题 - 记事本");

// 模糊查找窗口（只匹配类名）
int hwnd2 = WindowOperationUtils.windowGetHwndOne(0, "Notepad");

// 通过坐标查找窗口
int hwndAtPoint = WindowOperationUtils.windowGetPoint(100, 100);

// 暴力枚举句柄（遍历所有窗口）
int[] allHwnds = WindowOperationUtils.constraintGetHwnd();
for (int hwnd : allHwnds) {
    String title = WindowOperationUtils.windowGetTitle(hwnd);
    if (title != null && !title.isEmpty()) {
        System.out.println("窗口: " + title + " -> 句柄: " + hwnd);
    }
}

// 获取 Swing 窗口句柄
int swingHwnd = WindowOperationUtils.SwingWindowsHwnd();

// 枚举所有子窗口
int[] childHwnds = WindowOperationUtils.WindowEnum(notepadHwnd);
```

#### 窗口属性操作

窗口属性包括标题、类名、透明度、状态（最大、最小、正常）等。通过属性操作接口，可以获取和修改窗口的各种属性，实现窗口状态的动态控制。

```java
// 获取窗口标题
String title = WindowOperationUtils.windowGetTitle(notepadHwnd);
System.out.println("窗口标题: " + title);

// 设置窗口标题
WindowOperationUtils.windowSetTitle(notepadHwnd, "新标题");

// 获取窗口类名
String className = WindowOperationUtils.windowGetClassName(notepadHwnd);
System.out.println("窗口类名: " + className);

// 获取窗口控件ID
int ctrlId = WindowOperationUtils.windowGetDlgCtrlID(notepadHwnd);

// 控件ID取窗口句柄
int buttonHwnd = WindowOperationUtils.windowGetDlgItem(notepadHwnd, 1234);

// 设置窗口透明度（0-255，255为不透明）
WindowOperationUtils.windowSetAlpha(notepadHwnd, 200);

// 窗口激活
WindowOperationUtils.windowActivation(notepadHwnd);

// 窗口置顶
WindowOperationUtils.windowTop(notepadHwnd, true);

// 窗口最大化
WindowOperationUtils.windowSetMaximize(notepadHwnd);

// 窗口最小化
WindowOperationUtils.windowSetMinimize(notepadHwnd);

// 窗口还原
WindowOperationUtils.windowReduction(notepadHwnd);

// 关闭窗口
WindowOperationUtils.windowClose(notepadHwnd);

// 窗口置焦点
WindowOperationUtils.windowSetFocus(notepadHwnd);
```

#### 窗口位置操作

窗口位置操作允许开发者精确控制窗口的位置和大小。通过这些接口，可以实现窗口的移动、调整大小、对齐等功能，在自动化测试和界面布局调整等场景中非常有用。

```java
// 获取窗口位置和大小
int[] rect = WindowOperationUtils.windowGetRectToSize(notepadHwnd);
// rect[0]=x, rect[1]=y, rect[2]=width, rect[3]=height

// 设置窗口位置和大小
WindowOperationUtils.windowSetLocationSize(notepadHwnd, 100, 100, 800, 600);

// 获取子窗口屏幕坐标
int[] childPos = WindowOperationUtils.GetChildScreenPos(notepadHwnd);

// 设置子窗口位置
WindowOperationUtils.SetChildScreenPos(notepadHwnd, 50, 50);

// 坐标转换：客户端坐标转屏幕坐标
int[] screenPos = WindowOperationUtils.ClientToScreenPos(notepadHwnd, 100, 100);

// 坐标转换：屏幕坐标转客户端坐标
int[] clientPos = WindowOperationUtils.ScreenToClientPos(notepadHwnd, 500, 500);

// 获取客户端坐标
int[] clientPoint = WindowOperationUtils.getClientPoint(notepadHwnd);
```

#### 窗口嵌入技术

窗口嵌入是高级窗口操作技术，允许将一个窗口嵌入到另一个窗口中显示。JSCM 支持将窗口嵌入到桌面或其他窗口中，实现各种特殊的界面效果。

```java
// 嵌入桌面
WindowOperationUtils.windowEmbeddedDesktop(notepadHwnd);

// 嵌入指定窗口
int parentHwnd = 12345678;  // 父窗口句柄
WindowOperationUtils.WindowEmbeddedWindow(notepadHwnd, parentHwnd);

// 获取桌面 WorkerW 句柄
long workerW = WindowOperationUtils.GetWorkerW();

// 窗口置父
WindowOperationUtils.windowSetParent(notepadHwnd, parentHwnd);
```

#### 窗口枚举方法

窗口枚举用于遍历系统中所有窗口或指定窗口的子窗口。通过枚举操作，可以获取窗口列表、查找特定窗口、分析窗口结构等。

```java
// 枚举所有子窗口句柄
int[] childWindows = WindowOperationUtils.WindowEnum(parentHwnd);

// 获取子窗口类名
String childClassName = WindowOperationUtils.WindowEnumChildWindowClassName(parentHwnd, 0);

// 获取子窗口标题
String childTitle = WindowOperationUtils.WindowEnumChildWindowTitleName(parentHwnd, 0);

// 获取窗体控件坐标
int[] componentXY = WindowOperationUtils.windowGetcomponentXY(parentHwnd, "Button", "确定");
```

---

### SystemUtils 系统工具类

SystemUtils 提供了系统级别的工具功能，包括热键监视、全局钩子、剪贴板操作、消息框、系统路径获取等。这些功能是构建系统级应用的基础，在自动化脚本、监控工具、快捷键应用等开发中不可或缺。

#### 系统路径方法

系统路径方法用于获取各种系统和用户目录的路径，这些路径在文件操作、配置管理等场景中经常需要使用。

```java
import com.scm.all.export.SystemUtils;

// 获取桌面路径
String desktopPath = SystemUtils.getDesktop();
System.out.println("桌面路径: " + desktopPath);

// 获取自身启动目录
String runPath = SystemUtils.getRunPathOne();
System.out.println("启动目录: " + runPath);

// 获取 System32 系统目录
String system32Path = SystemUtils.System32Path();
System.out.println("System32路径: " + system32Path);

// 获取系统目录
String systemPath = SystemUtils.getSystemPath();
System.out.println("系统目录: " + systemPath);

// 返回 jar 运行目录
String jarPath = SystemUtils.runFilePath();
System.out.println("JAR目录: " + jarPath);
```

#### 消息框方法

消息框是 Windows 系统中最基本的用户交互方式，JSCM 提供了创建各种类型消息框的功能。

```java
// 弹出信息消息框
SystemUtils.messageBoxExW(0, "操作完成！", "提示", 
    com.scm.all.enumBox.MessageBoxFlag.MB_OK | 
    com.scm.all.enumBox.MessageBoxFlag.MB_ICONINFORMATION);

// 弹出确认消息框
int result = SystemUtils.messageBoxExW(0, "确定要执行此操作吗？", "确认",
    com.scm.all.enumBox.MessageBoxFlag.MB_YESNO | 
    com.scm.all.enumBox.MessageBoxFlag.MB_ICONQUESTION);

if (result == 6) {  // IDYES
    System.out.println("用户点击了确定");
} else {
    System.out.println("用户点击了取消");
}
```

#### 热键监视方法

热键监视允许应用程序捕获全局快捷键组合，实现后台快捷键响应功能。这在需要全局响应用户操作的场景中非常有用。

```java
import com.scm.all.export.SystemUtils;
import com.scm.all.pfunc.ResponseEventCallBack;

// 创建热键监视回调
ResponseEventCallBack hotkeyCallback = new ResponseEventCallBack() {
    @Override
    public void responseEvent(int id, int param) {
        System.out.println("检测到热键: ID=" + id + ", 参数=" + param);
    }
};

// 监视单个热键（Ctrl+Shift+A）
int hotkeyId = 1;
SystemUtils.listeningHotKeyOne(0, hotkeyId, 
    com.scm.all.enumBox.WindowFieldOffsets.MOD_CONTROL | 
    com.scm.all.enumBox.WindowFieldOffsets.MOD_SHIFT, 
    0x41, hotkeyCallback);

// 监视组合热键（Ctrl+Alt+Delete）
int hotkeyId2 = 2;
SystemUtils.listeningHotKeyTow(0, hotkeyId2,
    com.scm.all.enumBox.WindowFieldOffsets.MOD_CONTROL,
    com.scm.all.enumBox.WindowFieldOffsets.MOD_ALT,
    0x44, hotkeyCallback);

// 卸载热键
SystemUtils.removeListeningHotKeyOne(hotkeyId);
SystemUtils.removeListeningHotKeyTow(hotkeyId2);
```

#### 键盘钩子方法

全局键盘钩子能够捕获系统中所有的键盘输入事件，常用于键盘记录、快捷键拦截、输入模拟等场景。

```java
import com.scm.all.pfunc.KeyCodeCallBack;

// 创建键盘回调
KeyCodeCallBack keyCallback = new KeyCodeCallBack() {
    @Override
    public void keyEvent(int code, int wParam, int lParam) {
        if (code >= 0) {
            // wParam 包含虚拟键码
            String keyText = SystemUtils.keyCodeToStringOne(wParam);
            System.out.println("按键: " + keyText + ", 代码: " + wParam);
        }
    }
};

// 安装全局键盘钩子
int hookId = SystemUtils.setWindowsHookEx(keyCallback);

// 消息循环（需要持续运行）
// SystemUtils.messageRun();

// 卸载全局钩子
if (hookId != 0) {
    SystemUtils.unhookWindowsHookEx(hookId);
}
```

#### 鼠标操作方法

鼠标操作方法提供了模拟鼠标点击、移动、滚轮等行为的能力，是自动化工具的核心功能之一。

```java
// 格式化屏幕坐标
int[] formatted = SystemUtils.getScreenXY(100, 200);
System.out.println("格式化坐标: x=" + formatted[0] + ", y=" + formatted[1]);

// 获取屏幕坐标X
int mouseX = SystemUtils.mouseX();

// 模拟鼠标左键点击（绝对坐标）
SystemUtils.mouseClickOne(500, 500);

// 模拟鼠标左键点击（窗口内相对坐标）
int windowHwnd = 12345678;
SystemUtils.mouseClickTwo(windowHwnd, 100, 100);

// 后台模拟鼠标消息
com.scm.all.struct.TagINPUT[] inputs = new com.scm.all.struct.TagINPUT[1];
// 设置输入结构体
SystemUtils.sendInput(1, inputs);
```

#### 文件对话框方法

文件对话框方法封装了 Windows 原生的文件选择器，支持单文件选择、多文件选择、文件保存、文件夹选择等功能。

```java
// 文件选择器
String selectedFile = SystemUtils.OpenFileDialog(0, "所有文件(*.*)|*.*|文本文件(*.txt)|*.txt");
System.out.println("选择文件: " + selectedFile);

// 文件多选选择器
String[] selectedFiles = SystemUtils.OpenFilesDialog(0, "所有文件(*.*)|*.*");
for (String file : selectedFiles) {
    System.out.println("选择的文件: " + file);
}

// 文件保存选择器
String savePath = SystemUtils.SaveFileDialog(0, "所有文件(*.*)|*.*", "report.pdf");
System.out.println("保存路径: " + savePath);

// 文件夹选择器
String directory = SystemUtils.DirectoryDialog(0, "选择文件夹");
System.out.println("选择目录: " + directory);
```

---

### ProcessAndThreadUtils 进程线程工具类

ProcessAndThreadUtils 提供了进程和线程相关的操作功能，是进行进程管理、模块枚举、权限提升等操作的核心工具类。在系统监控、进程注入、权限分析等场景中，这些功能至关重要。

#### 进程操作方法

进程操作允许开发者枚举系统进程、获取进程信息、结束进程等。这些功能在进程管理工具、安全分析软件、任务管理器等应用中广泛使用。

```java
import com.scm.all.export.ProcessAndThreadUtils;
import com.scm.all.struct.TagProcessenTry32;
import java.util.List;

// 枚举系统进程
List<TagProcessenTry32> processes = ProcessAndThreadUtils.processEnumInfo();
for (TagProcessenTry32 process : processes) {
    System.out.println("进程: " + process.szExeFile + 
                       " PID: " + process.th32ProcessID +
                       " 父PID: " + process.th32ParentProcessID);
}

// 获取当前进程PID
int currentPid = ProcessAndThreadUtils.getCurrentProcessPID();
System.out.println("当前进程PID: " + currentPid);

// 进程PID取进程名
String processName = ProcessAndThreadUtils.pidGetProcessName(1234);
System.out.println("PID 1234 对应的进程名: " + processName);

// 进程名取PID
int pid = ProcessAndThreadUtils.processNameGetPid("notepad.exe");
System.out.println("notepad.exe 的PID: " + pid);

// 进程名是否存在
boolean exists = ProcessAndThreadUtils.processNameIsTrue("notepad.exe");
System.out.println("notepad.exe 是否存在: " + exists);

// 打开进程
int processHandle = ProcessAndThreadUtils.openProcess(pid);
System.out.println("进程句柄: " + processHandle);

// 结束进程
ProcessAndThreadUtils.overProcess(pid);
System.out.println("进程已结束");

// 关闭进程句柄
ProcessAndThreadUtils.closeHandle(processHandle);
```

#### 线程操作方法

线程操作提供了获取线程信息、结束线程、暂停线程等功能。这些功能在调试工具、性能分析、线程管理等应用中使用。

```java
// 进程名取线程ID
int[] threadIds = ProcessAndThreadUtils.processNameGetThreadTid("notepad.exe");
for (int tid : threadIds) {
    System.out.println("线程ID: " + tid);
}

// 线程ID取进程名
String threadProcessName = ProcessAndThreadUtils.threadTidGetProcessName(1234);
System.out.println("线程所属进程: " + threadProcessName);

// 线程ID取进程PID
int threadPid = ProcessAndThreadUtils.threadTidGetProcessPid(1234);
System.out.println("线程所属PID: " + threadPid);

// 打开线程
int threadHandle = ProcessAndThreadUtils.openThread(1234);
System.out.println("线程句柄: " + threadHandle);

// 结束线程
ProcessAndThreadUtils.overThread(1234);
System.out.println("线程已结束");

// 暂停线程
ProcessAndThreadUtils.threadSuspended(threadHandle);
System.out.println("线程已暂停");

// 关闭线程句柄
ProcessAndThreadUtils.closeHandle(threadHandle);
```

#### 权限操作方法

权限提升是系统安全中的重要概念，JSCM 提供了多种系统权限的提升功能。管理员权限可以执行一些需要更高权限的操作，如访问系统目录、修改注册表等。

```java
// 提升权限（备份权限）
boolean backupResult = ProcessAndThreadUtils.elevatePrivileges(3);  // SE_BACKUP_PRIVILEGE
System.out.println("备份权限提升: " + backupResult);

// 提升权限（恢复权限）
boolean restoreResult = ProcessAndThreadUtils.elevatePrivileges(4);  // SE_RESTORE_PRIVILEGE
System.out.println("恢复权限提升: " + restoreResult);

// 提升权限（关机权限）
boolean shutdownResult = ProcessAndThreadUtils.elevatePrivileges(5);  // SE_SHUTDOWN_PRIVILEGE
System.out.println("关机权限提升: " + shutdownResult);

// 提升权限（调试权限）
boolean debugResult = ProcessAndThreadUtils.elevatePrivileges(11);  // SE_DEBUG_PRIVILEGE
System.out.println("调试权限提升: " + debugResult);
```

#### 模块操作方法

模块枚举可以获取进程加载的所有动态链接库信息，对于分析进程、检查注入模块等场景非常有用。

```java
import com.scm.all.struct.TagModuleenTry32;
import java.util.List;

// 进程ID取进程模块（32位）
List<TagModuleenTry32> modulesX86 = ProcessAndThreadUtils.processPidToModuleX86(1234);
for (TagModuleenTry32 module : modulesX86) {
    System.out.println("模块: " + module.szExePath + " 句柄: " + module.hModule);
}

// 进程ID取进程模块（64位JDK下的32位模块）
List<TagModuleenTry32> modulesx86 = ProcessAndThreadUtils.processPidToModulex86(1234);

// 进程ID取进程模块（64位）
List<TagModuleenTry32> modulesX64 = ProcessAndThreadUtils.processPidToModuleX64(1234);
```

---

### MemoryOperationUtilsX86/X64 内存操作工具类

内存操作是 JSCM 框架中最核心也是最复杂的功能模块，分为 X86（32位）和 X64（64位）两个版本。内存操作允许程序读写其他进程的内存数据，进行内存搜索、特征码扫描等高级功能。在游戏辅助、内存分析、软件调试等领域，这些功能极为重要。

> **警告**：内存操作涉及系统底层功能，错误的操作可能导致目标进程崩溃或系统不稳定。请确保您具有足够的权限（管理员权限），并谨慎操作。

#### 内存属性操作

内存属性操作允许查询和修改内存区域的保护属性，如只读、读写、执行等。这对于绕过某些内存保护机制或分析内存布局非常有用。

```java
import com.scm.all.x86.export.MemoryOperationUtilsX86;
import com.scm.all.struct.TagPmemory_Basic_InfoRmationX86;

// 打开目标进程
int processHandle = ProcessAndThreadUtils.openProcess(1234);

// 查询内存属性
TagPmemory_Basic_InfoRmationX86 memInfo = MemoryOperationUtilsX86.selectMemoryAddressAttribute(processHandle, 0x00400000);
System.out.println("内存基址: 0x" + Long.toHexString(memInfo.BaseAddress));
System.out.println("内存大小: " + memInfo.RegionSize);
System.out.println("内存状态: " + memInfo.State);
System.out.println("内存保护: " + memInfo.Protect);

// 修改内存保护属性
boolean updateResult = MemoryOperationUtilsX86.updateMemoryAddressAttribute(processHandle, 
    0x00400000, 1024, 
    com.scm.all.enumBox.RedrawWindow.PAGE_READWRITE);
System.out.println("修改内存保护: " + updateResult);

// 判断内存地址是否有效
boolean isValid = MemoryOperationUtilsX86.memoryAddressIsValid(processHandle, 0x00400000);
System.out.println("内存地址有效: " + isValid);

// 判断是否为静态地址
boolean isStatic = MemoryOperationUtilsX86.memoryAddressIsStatic(processHandle, 0x00400000);
System.out.println("是否为静态地址: " + isStatic);

// 判断是否为基址
boolean isBase = MemoryOperationUtilsX86.memoryAddressIsBaseAddress(processHandle, 0x00400000);
System.out.println("是否为基址: " + isBase);
```

#### 内存读写操作

内存读写是内存操作的基础功能，支持读取和写入各种数据类型。JSCM 提供了基础读写和带偏移读写两种方式。

```java
// 内存读字节
byte[] readBytes = MemoryOperationUtilsX86.readProcessMemoryByte(processHandle, 0x00400000, 4);
int intValue = ByteUtils.bytesArrayToInt(readBytes);
System.out.println("读取整数: " + intValue);

// 内存读字节（带偏移）
int[] offsets = {0x100, 0x200, 0x10};
byte[] offsetBytes = MemoryOperationUtilsX86.readProcessMemoryByteOffSet(processHandle, 0x00401000, offsets, 4);
int offsetValue = ByteUtils.bytesArrayToInt(offsetBytes);
System.out.println("读取偏移值: " + offsetValue);

// 内存写字节
byte[] writeData = ByteUtils.intToBytesArray(9999);
boolean writeResult = MemoryOperationUtilsX86.writeProcessMemoryByte(processHandle, 0x00400000, 4, writeData);
System.out.println("写入内存: " + writeResult);

// 内存写字节（带偏移）
boolean offsetWriteResult = MemoryOperationUtilsX86.writeProcessMemoryByteOffSet(processHandle, 0x00401000, offsets, 4, writeData);
System.out.println("写入偏移内存: " + offsetWriteResult);

// 读取字符串
String readString = MemoryOperationUtilsX86.readProcessMemoryString(processHandle, 0x00403000, 100);
System.out.println("读取字符串: " + readString);

// 写入字符串
boolean writeStrResult = MemoryOperationUtilsX86.writeProcessMemoryString(processHandle, 0x00403000, "Hello World");
```

#### 内存搜索操作

内存搜索允许在目标进程内存中查找特定的数据模式，是特征码扫描的基础功能。

```java
import com.scm.all.pfunc.MemoryCallBack;

// 创建内存搜索回调
MemoryCallBack searchCallback = new MemoryCallBack() {
    @Override
    public void memoryCallback(int processId, long memoryAddress, byte[] data, int dataSize) {
        System.out.println("找到地址: 0x" + Long.toHexString(memoryAddress));
    }
};

// 线程模式内存搜索（搜索整数）
int searchValue = 1000;
MemoryOperationUtilsX86.threadSearchMemory(processHandle, searchValue, searchCallback);

// 线程模式特征码搜索
String signature = "55 8B EC ?? 83 EC ?? 53 56 57";
MemoryOperationUtilsX86.threadMd5SearchMemory(processHandle, signature, searchCallback);

// 内存范围搜索
// 在指定地址范围内搜索数据
long startAddress = 0x00400000L;
long endAddress = 0x01000000L;
```

#### 内存分配操作

内存分配允许在目标进程中分配和释放内存，常用于代码注入和远程线程创建等高级操作。

```java
// 申请内存
long allocatedAddr = MemoryOperationUtilsX86.virtualAllocEx(processHandle, 0, 4096, 
    com.scm.all.enumBox.RedrawWindow.MEM_COMMIT | 
    com.scm.all.enumBox.RedrawWindow.PAGE_READWRITE);
System.out.println("分配内存地址: 0x" + Long.toHexString(allocatedAddr));

// 释放内存
boolean freeResult = MemoryOperationUtilsX86.virtualFreeEx(processHandle, allocatedAddr, 0, 
    com.scm.all.enumBox.RedrawWindow.MEM_RELEASE);
System.out.println("释放内存: " + freeResult);
```

#### X64 版本差异

X64 版本的内存操作与 X86 版本接口基本一致，主要差异在于地址类型从 int 变为 long，以适应 64 位地址空间。

```java
import com.scm.all.x64.export.MemoryOperationUtilsX64;

// 打开64位进程
int processHandle64 = ProcessAndThreadUtils.openProcess(5678);

// 读取64位进程内存
long address64 = 0x00007FF6A1234000L;
byte[] readBytes64 = MemoryOperationUtilsX64.readProcessMemoryByte(processHandle64, address64, 8);
long value64 = ByteUtils.bytesArrayToLong1(readBytes64);
System.out.println("读取64位值: " + value64);

// 写入64位进程内存
byte[] writeData64 = ByteUtils.longToBytesArray(1234567890123L);
MemoryOperationUtilsX64.writeProcessMemoryByte(processHandle64, address64, 8, writeData64);
```

---

### RegistryOperationUtilsX86/X64 注册表操作工具类

注册表是 Windows 系统的核心数据库，存储了系统和应用程序的配置信息。RegistryOperationUtils 提供了对 Windows 注册表的完整操作能力，分为 X86 和 X64 两个版本。

> **注意**：注册表操作需要管理员权限，且错误的修改可能导致系统不稳定。请谨慎操作，并在操作前备份相关键值。

#### 注册表键操作

```java
import com.scm.all.x86.export.RegistryOperationUtilsX86;
import com.scm.all.struct.TagRegisTryGroupInfo;

// 创建注册表键
boolean createResult = RegistryOperationUtilsX86.createRegKey(
    com.scm.all.enumBox.ComboBoxState.HKEY_CURRENT_USER,
    "Software\\MyTestApp");
System.out.println("创建键: " + createResult);

// 删除注册表键
boolean deleteResult = RegistryOperationUtilsX86.deleteRegKey(
    com.scm.all.enumBox.ComboBoxState.HKEY_CURRENT_USER,
    "Software\\MyTestApp");
System.out.println("删除键: " + deleteResult);

// 判断键是否存在
boolean exists = RegistryOperationUtilsX86.isRegKeyExists(
    com.scm.all.enumBox.ComboBoxState.HKEY_CURRENT_USER,
    "Software\\Microsoft");
System.out.println("键存在: " + exists);

// 枚举子键
String[] subKeys = RegistryOperationUtilsX86.enumRegKey(
    com.scm.all.enumBox.ComboBoxState.HKEY_CURRENT_USER,
    "Software");
for (String key : subKeys) {
    System.out.println("子键: " + key);
}
```

#### 注册表值操作

```java
// 设置字符串值
RegistryOperationUtilsX86.setRegValue(
    com.scm.all.enumBox.ComboBoxState.HKEY_CURRENT_USER,
    "Software\\MyTestApp",
    "Username",
    "TestUser",
    com.scm.all.enumBox.AdapterDateType.REG_SZ);

// 设置整数值的示例代码
RegistryOperationUtilsX86.setRegValue(
    com.scm.all.enumBox.ComboBoxState.HKEY_CURRENT_USER,
    "Software\\MyTestApp",
    "Age",
    "25",
    com.scm.all.enumBox.AdapterDateType.REG_DWORD);

// 获取字符串值
String username = RegistryOperationUtilsX86.getRegValue(
    com.scm.all.enumBox.ComboBoxState.HKEY_CURRENT_USER,
    "Software\\MyTestApp",
    "Username");
System.out.println("用户名: " + username);

// 获取整数值
int age = Integer.parseInt(RegistryOperationUtilsX86.getRegValue(
    com.scm.all.enumBox.ComboBoxState.HKEY_CURRENT_USER,
    "Software\\MyTestApp",
    "Age"));
System.out.println("年龄: " + age);

// 删除值
RegistryOperationUtilsX86.deleteRegValue(
    com.scm.all.enumBox.ComboBoxState.HKEY_CURRENT_USER,
    "Software\\MyTestApp",
    "Username");
```

---

### 键鼠驱动模块

JSCM 框架集成了三种键鼠驱动：DD键鼠驱动、幽灵键鼠驱动和Md键鼠驱动。这些驱动提供了硬件级别的输入模拟能力，能够在游戏中实现更精准的自动化操作。

#### DD键鼠驱动

DD键鼠驱动是最常用的键鼠模拟驱动，提供了简单易用的鼠标和键盘模拟功能。

```java
import com.scm.all.export.hotkey.DdDriver;

// 初始化DD键鼠驱动
DdDriver.init();

// 鼠标操作
// 鼠标移动到绝对坐标
DdDriver.mov(500, 500);

// 鼠标相对移动
DdDriver.movR(100, 100);

// 鼠标点击（1=左键按下, 2=左键放开）
DdDriver.btn(1);
DdDriver.btn(2);

// 鼠标右键点击
DdDriver.btn(3);
DdDriver.btn(4);

// 鼠标滚轮（正数向前，负数向后）
DdDriver.whl(3);

// 键盘操作
// 按键按下
DdDriver.key(0x41, 1);  // A键按下

// 按键放开
DdDriver.key(0x41, 2);  // A键放开

// 直接输入字符串
DdDriver.str("Hello World 你好世界");
```

#### 幽灵键鼠驱动

幽灵键鼠驱动提供了更丰富的硬件模拟功能，支持设备选择、按键延时设置等高级特性。

```java
import com.scm.all.export.hotkey.GhostDriver;

// 初始化幽灵键鼠驱动
GhostDriver.init();

// 获取设备列表
String[] deviceList = GhostDriver.getDeviceListByModel("My Device");
System.out.println("可用设备: " + String.join(", ", deviceList));

// 选择设备
GhostDriver.selectDeviceBySerialNumber("SN123456789");

// 检查设备连接状态
boolean connected = GhostDriver.isDeviceConnected();
System.out.println("设备连接: " + connected);

// 获取设备信息
String model = GhostDriver.getModel();
String serialNumber = GhostDriver.getSerialNumber();
String firmwareVersion = GhostDriver.getFirmwareVersion();
System.out.println("型号: " + model + ", SN: " + serialNumber + ", 固件: " + firmwareVersion);

// 设置按键延时（毫秒）
GhostDriver.setPressKeyDelay(50);

// 键盘操作
// 按键按下（通过键名）
GhostDriver.pressKeyByName("A");

// 按键释放
GhostDriver.releaseKeyByName("A");

// 按下并释放
GhostDriver.pressAndReleaseKeyByName("Enter");

// 检查按键是否按下
boolean isPressed = GhostDriver.isKeyPressedByName("Space");
System.out.println("空格键按下: " + isPressed);

// 输入字符串
GhostDriver.inputString("Test String");

// 鼠标操作
// 鼠标移动到绝对坐标
GhostDriver.moveMouseTo(500, 500);

// 鼠标相对移动
GhostDriver.moveMouseRelative(100, 100);

// 鼠标按键按下
GhostDriver.pressMouseButton(1);  // 1=左键, 2=右键, 3=中键

// 鼠标按键释放
GhostDriver.releaseMouseButton(1);

// 鼠标滚轮
GhostDriver.moveMouseWheel(3);

// 获取鼠标坐标
int mouseX = GhostDriver.getMouseX();
int mouseY = GhostDriver.getMouseY();
System.out.println("鼠标位置: (" + mouseX + ", " + mouseY + ")");
```

#### 驱动安装

在使用键鼠驱动前，需要先安装驱动程序。JSCM 提供了自动安装功能。

```java
import com.scm.all.export.hotkey.install.InstallDDDriver;
import com.scm.all.export.hotkey.install.InstallGhostDriver;
import com.scm.all.export.hotkey.install.InstallMdDriver;

// 安装DD驱动
boolean ddInstallResult = InstallDDDriver.install("C:\\drivers\\ddmouse64.sys");
System.out.println("DD驱动安装: " + ddInstallResult);

// 安装幽灵驱动
boolean ghostInstallResult = InstallGhostDriver.install("C:\\drivers\\GhostMouse.sys");
System.out.println("幽灵驱动安装: " + ghostInstallResult);

// 安装Md驱动
boolean mdInstallResult = InstallMdDriver.install("C:\\drivers\\mdmouse64.sys");
System.out.println("Md驱动安装: " + mdInstallResult);
```

---

### 报表模块

JSCM 框架集成了锐浪报表（Grid++Report）功能，支持报表的设计、预览、打印和导出。通过该模块，开发者可以在 Java 应用中集成功能强大的报表功能。

```java
import com.scm.all.pfunc.CGridppReportEventHandler;

// 报表初始化配置
// 设置路径
PathFileJSCM.setDebugFile32("C:\\path\\to\\x86\\");
PathFileJSCM.setDebugFile64("C:\\path\\to\\x64\\");

// 创建报表事件处理回调
CGridppReportEventHandler reportHandler = new CGridppReportEventHandler() {
    @Override
    public void onInitialize() {
        System.out.println("报表初始化");
    }
    
    @Override
    public boolean onFetchRecord() {
        // 数据获取回调
        return true;
    }
    
    @Override
    public void onPrintStart() {
        System.out.println("打印开始");
    }
    
    @Override
    public void onPrintEnd() {
        System.out.println("打印结束");
    }
};

// 报表具体使用请参考锐浪报表官方文档
```

---

### 回调接口

JSCM 框架提供了多种回调接口，用于处理异步事件和系统通知。以下是主要回调接口的使用方法。

#### WindowEventCallBack 窗口事件回调

```java
import com.scm.all.pfunc.WindowEventCallBack;

WindowEventCallBack windowCallback = new WindowEventCallBack() {
    @Override
    public void mouseEvent(int hwnd, int msg, int wParam, int lParam) {
        System.out.println("鼠标事件 - HWND: " + hwnd + 
                           ", 消息: " + msg + 
                           ", 参数: " + wParam);
    }
    
    @Override
    public void keyBoardEvent(int hwnd, int msg, int wParam, int lParam) {
        System.out.println("键盘事件 - HWND: " + hwnd + 
                           ", 消息: " + msg + 
                           ", 键码: " + wParam);
    }
};
```

#### MemoryCallBack 内存搜索回调

```java
import com.scm.all.pfunc.MemoryCallBack;

MemoryCallBack memoryCallback = new MemoryCallBack() {
    @Override
    public void memoryCallback(int processId, long memoryAddress, byte[] data, int dataSize) {
        System.out.println("进程ID: " + processId);
        System.out.println("地址: 0x" + Long.toHexString(memoryAddress));
        System.out.println("数据大小: " + dataSize);
        System.out.println("数据: " + ByteUtils.bytesToHexString(data));
    }
};
```

#### KeyCodeCallBack 键盘回调

```java
import com.scm.all.pfunc.KeyCodeCallBack;

KeyCodeCallBack keyCallback = new KeyCodeCallBack() {
    @Override
    public void keyEvent(int code, int wParam, int lParam) {
if (code >= 0) {
            String keyText = SystemUtils.keyCodeToStringOne(wParam);
            System.out.println("按键: " + keyText + " (" + wParam + ")");
        }
    }
};
```

#### ResponseEventCallBack 热键回调

```java
import com.scm.all.pfunc.ResponseEventCallBack;

ResponseEventCallBack hotkeyCallback = new ResponseEventCallBack() {
    @Override
    public void responseEvent(int id, int param) {
        System.out.println("热键触发 - ID: " + id + ", 参数: " + param);
    }
};
```

---

### 炫彩GUI界面框架

JSCM 框架集成了炫彩GUI（XCGUI）界面库，这是一套功能强大的轻量级 GUI 开发框架。炫彩GUI提供了丰富的 Windows 桌面应用程序 UI 组件，具有高性能、高度可定制、无第三方依赖等特点。通过 JSCM 框架，开发者可以使用 Java 语言直接调用炫彩GUI的所有功能，创建现代化界面的 Windows 应用程序。

炫彩GUI界面框架基于 C++ 开发，通过 JNI 技术桥接到 Java 层。框架支持创建各种常见的 Windows UI 组件，包括窗口、按钮、编辑框、列表、树形控件、菜单等。与传统的 Swing/AWT 相比，炫彩GUI具有更低的资源占用和更高的渲染效率，特别适合开发对性能和界面效果有较高要求的应用程序。

#### 炫彩GUI对象类型

炫彩GUI框架定义了丰富的对象类型，涵盖了从基础窗口到高级UI组件的完整体系。开发者可以通过 XcObjectType 枚举类了解所有可用的对象类型，并根据需求选择合适的组件进行开发。

| 类型值 | 对象类型 | 说明 |
|-------|---------|------|
| XC_WINDOW | 基础窗口 | 最基础的窗口对象 |
| XC_MODALWINDOW | 模态窗口 | 阻塞父窗口交互的对话框 |
| XC_FRAMEWND | 框架窗口 | 带标题栏和边框的窗口 |
| XC_FLOATWND | 浮动窗口 | 可悬浮的窗口 |
| XC_ELE | 基础元素 | 所有UI元素的基类 |
| XC_BUTTON | 按钮 | 可点击的按钮组件 |
| XC_EDIT | 编辑框 | 文本输入组件 |
| XC_COMBOBOX | 下拉组合框 | 带下拉列表的组合框 |
| XC_SCROLLBAR | 滚动条 | 滚动导航组件 |
| XC_SCROLLVIEW | 滚动视图 | 支持滚动的内容视图 |
| XC_LIST | 列表 | 列表显示组件 |
| XC_LISTBOX | 列表框 | 列表框组件 |
| XC_LISTVIEW | 列表视图 | 大图标模式列表 |
| XC_TREE | 列表树 | 树形结构组件 |
| XC_MENUBAR | 菜单条 | 菜单栏组件 |
| XC_SLIDERBAR | 滑动条 | 滑动选择组件 |
| XC_PROGRESSBAR | 进度条 | 进度显示组件 |
| XC_TOOLBAR | 工具条 | 工具栏组件 |
| XC_MONTHCAL | 月历卡片 | 日历选择组件 |
| XC_DATETIME | 日期时间 | 日期时间选择组件 |
| XC_PROPERTYGRID | 属性网格 | 属性编辑器组件 |
| XC_TABBAR | TAB标签页 | 标签页切换组件 |
| XC_PANE | 窗格 | 可停靠面板组件 |
| XC_PANE_SPLIT | 窗格分割条 | 面板分隔组件 |
| XC_SHAPE_TEXT | 文本形状 | 绘制文本图形 |
| XC_SHAPE_PICTURE | 图片形状 | 绘制图片图形 |
| XC_SHAPE_RECT | 矩形形状 | 绘制矩形图形 |
| XC_SHAPE_ELLIPSE | 圆形形状 | 绘制椭圆图形 |
| XC_SHAPE_LINE | 直线形状 | 绘制直线图形 |

#### 炫彩GUI事件回调

炫彩GUI框架提供了完善的事件处理机制，通过 XEleEventCallBack 类可以捕获各种UI事件并进行相应处理。以下是常用的事件回调方法。

```java
import com.scm.all.pfunc.XEleEventCallBack;

// 创建炫彩GUI事件回调
XEleEventCallBack xcguiCallback = new XEleEventCallBack() {
    // 按钮点击事件
    @Override
    public boolean OnBtnClick(int hEle) {
        System.out.println("按钮被点击，句柄: " + hEle);
        return true;  // 返回true表示已处理该事件
    }
    
    // 按钮选中状态改变事件
    @Override
    public boolean OnButtonCheck(int hEle, boolean bCheck) {
        System.out.println("按钮选中状态改变: " + bCheck);
        return true;
    }
    
    // 编辑框内容改变事件
    @Override
    public boolean OnEditChanged(int hEle) {
        System.out.println("编辑框内容已改变");
        return true;
    }
    
    // 组合框选中项改变事件
    @Override
    public boolean OnComboBoxSelect(int hEle, int iItem) {
        System.out.println("选中项索引: " + iItem);
        return true;
    }
    
    // 元素销毁事件
    @Override
    public boolean OnDestroy(int hEle) {
        System.out.println("元素销毁，句柄: " + hEle);
        return true;
    }
    
    // 鼠标移动事件
    @Override
    public boolean OnMouseMove(int hEle, int flags, int pointX, int pointY) {
        System.out.println("鼠标移动到: (" + pointX + ", " + pointY + ")");
        return true;
    }
    
    // 鼠标左键按下事件
    @Override
    public boolean OnLButtonDown(int hEle, int flags, int pointX, int pointY) {
        System.out.println("鼠标左键按下: (" + pointX + ", " + pointY + ")");
        return true;
    }
    
    // 鼠标左键抬起事件
    @Override
    public boolean OnLButtonUp(int hEle, int flags, int pointX, int pointY) {
        System.out.println("鼠标左键抬起: (" + pointX + ", " + pointY + ")");
        return true;
    }
    
    // 元素获取焦点事件
    @Override
    public boolean OnSetFocus(int hEle) {
        System.out.println("元素获取焦点");
        return true;
    }
    
    // 元素失去焦点事件
    @Override
    public boolean OnKillFocus(int hEle) {
        System.out.println("元素失去焦点");
        return true;
    }
};
```

#### 炫彩GUI组件使用示例

以下示例展示了如何使用炫彩GUI框架创建基本的窗口和按钮组件。

```java
import com.scm.all.export.ModuleOperationUtilsJNI;
import com.scm.all.pfunc.XEleEventCallBack;

// 初始化框架
ModuleOperationUtilsJNI.initOutFile();

// 创建基础窗口
int hWindow = ModuleOperationUtilsJNI.XC_ShellCreateWindow(
    "JSCM 炫彩GUI示例",  // 窗口标题
    0, 0,  // x, y 坐标
    800, 600,  // 宽度, 高度
    0  // 窗口样式
);

// 创建按钮
int hButton = ModuleOperationUtilsJNI.XC_ButtonCreate(
    hWindow,  // 父窗口句柄
    "点击我",  // 按钮文本
    100, 100,  // x, y 坐标
    100, 30   // 宽度, 高度
);

// 设置按钮文本
ModuleOperationUtilsJNI.XC_SetWindowText(hButton, "确认");

// 获取按钮文本
String buttonText = ModuleOperationUtilsJNI.XC_GetWindowText(hButton);
System.out.println("按钮文本: " + buttonText);

// 设置元素属性
ModuleOperationUtilsJNI.XC_SetEleId(hButton, 1001);  // 设置ID

// 注册事件回调
ModuleOperationUtilsJNI.XC_EleRegisterEvent(hButton, xcguiCallback);

// 显示窗口
ModuleOperationUtilsJNI.XC_ShowWindow(hWindow);

// 消息循环（需要持续运行以处理UI事件）
// ModuleOperationUtilsJNI.XC_RunXCPopupMenu();

// 销毁窗口
ModuleOperationUtilsJNI.XC_DestroyWindow(hWindow);
```

#### 创建其他常用组件

炫彩GUI框架支持创建多种UI组件，以下是创建一些常用组件的示例。

```java
// 创建编辑框
int hEdit = ModuleOperationUtilsJNI.XC_EditCreate(
    hWindow,
    "请输入内容...",
    100, 150, 200, 30
);

// 设置编辑框只读
ModuleOperationUtilsJNI.XC_SetEleState(hEdit, 
    com.scm.all.enumBox.ElementStateFlag.XC_EDIT_STATE_READONLY, true);

// 创建滚动视图
int hScrollView = ModuleOperationUtilsJNI.XC_ScrollViewCreate(
    hWindow,
    100, 200, 300, 200
);

// 创建列表
int hList = ModuleOperationUtilsJNI.XC_ListCreate(
    hWindow,
    450, 100, 300, 400
);

// 添加列表项
ModuleOperationUtilsJNI.XC_ListAddItem(hList, "列表项1");
ModuleOperationUtilsJNI.XC_ListAddItem(hList, "列表项2");
ModuleOperationUtilsJNI.XC_ListAddItem(hList, "列表项3");

// 创建树形控件
int hTree = ModuleOperationUtilsJNI.XC_TreeCreate(
    hWindow,
    50, 50, 200, 300
);

// 添加树节点
int hNode1 = ModuleOperationUtilsJNI.XC_TreeAddItem(hTree, "根节点1");
int hNode2 = ModuleOperationUtilsJNI.XC_TreeAddItem(hTree, "根节点2");
ModuleOperationUtilsJNI.XC_TreeAddItem(hTree, "子节点1", hNode1);

// 创建进度条
int hProgressBar = ModuleOperationUtilsJNI.XC_ProgressBarCreate(
    hWindow,
    100, 450, 300, 20
);

// 设置进度值 (0-100)
ModuleOperationUtilsJNI.XC_ProgressBarSetPos(hProgressBar, 50);

// 创建滑动条
int hSlider = ModuleOperationUtilsJNI.XC_SliderBarCreate(
    hWindow,
    100, 480, 300, 30
);

// 设置滑动条范围和值
ModuleOperationUtilsJNI.XC_SliderBarSetRange(hSlider, 0, 100);
ModuleOperationUtilsJNI.XC_SliderBarSetPos(hSlider, 50);

// 创建组合框
int hComboBox = ModuleOperationUtilsJNI.XC_ComboBoxCreate(
    hWindow,
    100, 520, 200, 30
);

// 添加下拉项
ModuleOperationUtilsJNI.XC_ComboBoxAddItem(hComboBox, "选项1");
ModuleOperationUtilsJNI.XC_ComboBoxAddItem(hComboBox, "选项2");
ModuleOperationUtilsJNI.XC_ComboBoxAddItem(hComboBox, "选项3");

// 设置选中项
ModuleOperationUtilsJNI.XC_ComboBoxSetSelectItem(hComboBox, 0);
```

#### 炫彩GUI样式和状态

炫彩GUI提供了丰富的样式和状态管理功能，可以自定义组件的外观和行为。

```java
import com.scm.all.enumBox.*;

// 设置窗口样式
ModuleOperationUtilsJNI.XC_SetWindowStyle(hWindow, 
    WindowStyle.WS_CAPTION | WindowStyle.WS_SYSMENU | WindowStyle.WS_MINIMIZEBOX);

// 设置窗口透明
ModuleOperationUtilsJNI.XC_SetWindowAlpha(hWindow, 255);  // 0-255

// 设置窗口圆角
ModuleOperationUtilsJNI.XC_SetWindowRoundedCorners(hWindow, 10, 10);

// 设置元素背景颜色
ModuleOperationUtilsJNI.XC_SetEleBkColor(hButton, 0xFF0000);  // 红色

// 设置元素文本颜色
ModuleOperationUtilsJNI.XC_SetEleTextColor(hButton, 0xFFFFFF);  // 白色

// 设置元素状态
// 启用/禁用元素
ModuleOperationUtilsJNI.XC_SetEleState(hButton, 
    ElementStateFlag.XC_ELE_STATE_DISABLE, true);  // 禁用按钮

// 设置按钮选中状态
ModuleOperationUtilsJNI.XC_SetButtonCheck(hButton, true);

// 获取元素状态
boolean isChecked = ModuleOperationUtilsJNI.XC_GetButtonCheck(hButton);
System.out.println("按钮选中状态: " + isChecked);

// 设置元素位置和大小
ModuleOperationUtilsJNI.XC_SetEleRect(hButton, 100, 100, 120, 40);

// 获取元素位置和大小
int[] rect = ModuleOperationUtilsJNI.XC_GetEleRect(hButton);
System.out.println("元素位置: x=" + rect[0] + ", y=" + rect[1] + 
                   ", width=" + rect[2] + ", height=" + rect[3]);
```

#### 炫彩GUI布局管理

炫彩GUI支持灵活的布局管理，可以方便地实现响应式UI设计。

```java
// 创建布局框架
int hLayoutFrame = ModuleOperationUtilsJNI.XC_LayoutFrameCreate(hWindow);

// 添加布局元素
int hLayoutEle = ModuleOperationUtilsJNI.XC_EleLayoutCreate(hWindow);

// 设置布局对齐方式
ModuleOperationUtilsJNI.XC_SetLayoutEleAlign(hLayoutEle, 
    LayoutAlign.XC_LAYOUT_ALIGN_LEFT,  // 左对齐
    LayoutAlign.XC_LAYOUT_ALIGN_TOP);   // 顶对齐

// 设置布局间距
ModuleOperationUtilsJNI.XC_SetLayoutEleMargin(hLayoutEle, 10, 10, 10, 10);  // 左上右下

// 设置元素布局权重
ModuleOperationUtilsJNI.XC_SetLayoutEleSize(hLayoutEle, 100, 50, 
    LayoutSize.XC_LAYOUT_SIZE_TYPE_AUTO);  // 自动大小
```

#### 炫彩GUI绘图功能

炫彩GUI提供了强大的自绘功能，允许开发者自定义组件的绘制逻辑。

```java
// 创建形状文本
int hShapeText = ModuleOperationUtilsJNI.XC_ShapeTextCreate(
    hWindow,
    "显示的文本",
    50, 50, 200, 30
);

// 设置形状文本属性
ModuleOperationUtilsJNI.XC_SetShapeTextColor(hShapeText, 0x000000);  // 黑色
ModuleOperationUtilsJNI.XC_SetShapeTextFont(hShapeText, "宋体", 14);  // 字体和大小

// 创建形状图片
int hShapePicture = ModuleOperationUtilsJNI.XC_ShapePictureCreate(
    hWindow,
    "C:\\image.png",  // 图片路径
    50, 100, 200, 150
);

// 创建形状矩形
int hShapeRect = ModuleOperationUtilsJNI.XC_ShapeRectCreate(
    hWindow,
    50, 300, 200, 100
);

// 设置矩形边框颜色和宽度
ModuleOperationUtilsJNI.XC_SetShapeRectBorderColor(hShapeRect, 0xFF0000);
ModuleOperationUtilsJNI.XC_SetShapeRectBorderWidth(hShapeRect, 2);

// 设置矩形填充颜色
ModuleOperationUtilsJNI.XC_SetShapeRectFillColor(hShapeRect, 0x00FF00);

// 创建形状圆形
int hShapeEllipse = ModuleOperationUtilsJNI.XC_ShapeEllipseCreate(
    hWindow,
    300, 350, 100, 50
);
```

#### 炫彩GUI菜单功能

炫彩GUI支持创建各种类型的菜单，包括菜单条、弹出菜单等。

```java
// 创建菜单条
int hMenuBar = ModuleOperationUtilsJNI.XC_MenuBarCreate(hWindow);

// 添加菜单项
int hMenuFile = ModuleOperationUtilsJNI.XC_MenuBarAddMenu(hMenuBar, "文件");
int hMenuEdit = ModuleOperationUtilsJNI.XC_MenuBarAddMenu(hMenuBar, "编辑");
int hMenuHelp = ModuleOperationUtilsJNI.XC_MenuBarAddMenu(hMenuBar, "帮助");

// 添加子菜单项
ModuleOperationUtilsJNI.XC_MenuBarAddItem(hMenuFile, "新建", 1);
ModuleOperationUtilsJNI.XC_MenuBarAddItem(hMenuFile, "打开", 2);
ModuleOperationUtilsJNI.XC_MenuBarAddItem(hMenuFile, "-", 0);  // 分隔线
ModuleOperationUtilsJNI.XC_MenuBarAddItem(hMenuFile, "退出", 3);

// 创建弹出菜单
int hPopupMenu = ModuleOperationUtilsJNI.XC_PopupMenuCreate(hWindow);

// 添加弹出菜单项
ModuleOperationUtilsJNI.XC_PopupMenuAddItem(hPopupMenu, "复制", 1);
ModuleOperationUtilsJNI.XC_PopupMenuAddItem(hPopupMenu,2);

// 显示弹出 "粘贴", 菜单（在位置）
ModuleOperationUtilsJNI.XC_PopupMenu指定ShowMenu(hPopupMenu, hWindow, 100, 100);
```

---

## 使用案例

以下是 JSCM 框架在实际应用中的典型使用案例，涵盖了自动化工具、游戏辅助、系统监控等常见场景。

### 案例一：自动化测试工具

```java
public class AutomationDemo {
  public static void main(String[] args) throws Exception {
    // 初始化框架
    PathFileJSCM.setDebugFile32("demo\\x86\\");
    PathFileJSCM.setDebugFile64("demo\\x64\\");

    // 启动记事本
    Runtime.getRuntime().exec("notepad.exe");
    Thread.sleep(1000);

    // 查找记事本窗口
    int notepadHwnd = WindowOperationUtils.windowGetHwndTow(0, "Notepad", "无标题 - 记事本");
    if (notepadHwnd == 0) {
      System.out.println("未找到记事本窗口");
      return;
    }

    // 获取窗口位置
    int[] rect = WindowOperationUtils.windowGetRectToSize(notepadHwnd);
    System.out.println("窗口位置: " + Arrays.toString(rect));

    // 窗口置顶
    WindowOperationUtils.windowTop(notepadHwnd, true);

    // 窗口激活
    WindowOperationUtils.windowActivation(notepadHwnd);

    // 枚举子窗口（编辑框）
    int[] childHwnds = WindowOperationUtils.WindowEnum(notepadHwnd);
    for (int hwnd : childHwnds) {
      String className = WindowOperationUtils.windowGetClassName(hwnd);
      if ("Edit".equals(className)) {
        // 向编辑框发送文本
        // 具体实现需要使用SendMessage等API
        System.out.println("找到编辑框，句柄: " + hwnd);
        break;
      }
    }

    System.out.println("自动化测试完成");
  }
}
```

### 案例二：游戏辅助工具

```java
public class GameAssistDemo {
  public static void main(String[] args) throws Exception {
    // 初始化
    PathFileJSCM.setDebugFile32("demo\\x86\\");

    // 查找游戏进程
    int pid = ProcessAndThreadUtils.processNameGetPid("game.exe");
    if (pid == 0) {
      System.out.println("未找到游戏进程");
      return;
    }

    System.out.println("找到游戏进程，PID: " + pid);

    // 打开进程
    int handle = ProcessAndThreadUtils.openProcess(pid);
    if (handle == 0) {
      System.out.println("打开进程失败");
      return;
    }

    // 提升调试权限
    ProcessAndThreadUtils.elevatePrivileges(11);  // SE_DEBUG_PRIVILEGE

    // 读取游戏基址
    long baseAddress = 0x00400000L;

    // 读取血量（假设血量偏移为 0x1234）
    byte[] hpBytes = MemoryOperationUtilsX86.readProcessMemoryByte(handle, baseAddress + 0x1234, 4);
    int hp = ByteUtils.bytesArrayToInt(hpBytes);
    System.out.println("当前血量: " + hp);

    // 搜索角色坐标（需要先确定特征码）
    String coordSignature = "?? ?? ?? ?? 00 00 00 00";

    // 关闭句柄
    ProcessAndThreadUtils.closeHandle(handle);
  }
}
```

### 案例三：系统监控工具

```java
public class SystemMonitorDemo {
  public static void main(String[] args) {
    // 创建热键回调
    ResponseEventCallBack hotkeyCallback = new ResponseEventCallBack() {
      @Override
      public void responseEvent(int id, int param) {
        System.out.println("捕获到热键: " + id);

        // 显示所有窗口
        int[] hwnds = WindowOperationUtils.constraintGetHwnd();
        System.out.println("当前窗口数量: " + hwnds.length);

        for (int hwnd : hwnds) {
          String title = WindowOperationUtils.windowGetTitle(hwnd);
          if (title != null && !title.isEmpty() && title.length() < 50) {
            System.out.println("  - " + title);
          }
        }
      }
    };

    // 注册热键 Ctrl+Shift+M
    int monitorId = 100;
    SystemUtils.listeningHotKeyOne(0, monitorId,
            com.scm.all.enumBox.WindowFieldOffsets.MOD_CONTROL |
                    com.scm.all.enumBox.WindowFieldOffsets.MOD_SHIFT,
            0x4D, hotkeyCallback);  // M键

    System.out.println("系统监控已启动，按 Ctrl+Shift+M 查看窗口列表");
    System.out.println("按任意键退出...");

    try {
      System.in.read();
    } catch (Exception e) {
      e.printStackTrace();
    }

    // 卸载热键
    SystemUtils.removeListeningHotKeyOne(monitorId);
  }
}
```

---

## 常见问题

### 1. DLL 加载失败

**问题描述**：运行时出现 "Can't find dependent libraries" 错误。

**解决方案**：请将 JAR 包中对应的系统版本号的 jawt.dll 文件放在 java.exe 的运行目录下，或者从 Java 目录下直接复制到对应位置即可。因为界面库依赖了 Swing 库，需要确保 jawt.dll 正确加载。

### 2. 内存操作权限不足

**问题描述**：内存读写返回 false 或抛出访问异常。

**解决方案**：请以管理员权限运行应用程序。部分内存操作需要 SeDebugPrivilege 权限，可以使用 ProcessAndThreadUtils.elevatePrivileges(11) 提升权限。

### 3. 键鼠驱动无法使用

**问题描述**：DD 驱动或幽灵驱动初始化失败。

**解决方案**：请确保驱动程序已正确安装。可以使用 InstallDDDriver 或 InstallGhostDriver 类的方法安装驱动。部分驱动需要禁用驱动签名强制才能正常加载。

### 4. 32位与64位选择

**问题描述**：不确定应该使用 X86 还是 X64 版本的类。

**解决方案**：选择取决于目标进程的架构，而非 JDK 的架构。如果目标进程是 32 位程序，即使使用 64 位 JDK，也需要使用 X86 版本的类。如果目标进程是 64 位程序，则使用 X64 版本。

### 5. 中文乱码问题

**问题描述**：读取或写入的中文字符出现乱码。

**解决方案**：确保使用正确的编码方法。WindowOperationUtils 中的窗口标题获取方法默认使用 Unicode 编码。ByteUtils 提供了 bytesArrayToUtf8WideString 方法用于处理包含中文的 UTF-8 字符串。

---

## 技术支持

### 联系方式

- **官网地址**：https://www.snailcatmall.com
- **官方QQ群**：128828632
- **技术支持邮箱**：1012518027@qq.com
- **视频学习文档合集**：https://space.bilibili.com/29826047/lists?sid=7051754
- **炫彩界面库官方API文档**：http://8.138.59.98/xcgui/index.html

### 版本信息

- **框架版本**：V1.2.8
- **更新日期**：2026年2月

---

## 附录

### 结构体一览

JSCM 框架提供了丰富的结构体定义，用于封装 Windows API 的数据结构。主要结构体包括：

| 结构体名称 | 说明 |
|-----------|------|
| TagRect | 矩形结构（left, top, right, bottom） |
| TagPoint | 点坐标结构（x, y） |
| TagProcessenTry32 | 进程信息结构 |
| TagThreadEntry32 | 线程信息结构 |
| TagModuleenTry32 | 模块信息结构 |
| TagPmemory_Basic_InfoRmationX86/X64 | 内存信息结构 |
| TagINPUT | 输入事件结构 |
| TagHotKeyInfoTow | 热键信息结构 |

### 枚举类一览

框架提供了大量的枚举类，用于定义常量和选项。主要枚举类包括：

| 枚举类名称 | 说明 |
|-----------|------|
| WindowStyle | 窗口样式枚举 |
| MessageBoxFlag | 消息框标志 |
| WindowStateFlag | 窗口状态标志 |
| ButtonStateFlag | 按钮状态标志 |
| RedrawWindow | 内存保护标志 |
| ComboBoxState | 注册表根键 |

---

*文档最后更新时间：2026年3月*
