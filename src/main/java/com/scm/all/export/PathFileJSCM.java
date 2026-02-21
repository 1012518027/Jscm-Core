package com.scm.all.export;

/**
 * 配置JSCM库
 */
public class PathFileJSCM {
    static {
        System.out.println("温馨提示:java.exe | javac.exe | javaw.exe 请设置成管理员 win10禁用驱动签名  购买联系：1012518027@qq.com  官方QQ群:128828632");
        System.out.println("温馨提示:请根据SDK版本如果有标注X64 和X86 请使用对应的SDK版本使用,否则无法使用!");
        System.out.println("关于Ascii 转UTF-8 取值常用的：ByteUtils.bytesArrayToAsciiWideString |  asciiWideStringToBytesArray  ");
        System.out.println("关于Unicode  转UTF-8 取值常用的：ByteUtils.bytesArrayToUtf8WideString | charsArrayToUtf8WideString1 |  charsArrayToUtf8WideString2 |  utf8WideStringToCharsArray");
        System.out.println("ByteUtils.indexOf  支持 字节 和 字符");
        System.out.println("ByteUtils.equalsByteArray | equalsCharArray  判断匹配");
        System.out.println("关于API的选用！尽量选用 U 码 例如 MessageBoxExW  尾部带  W  的就是U码 ");
        System.out.println("关于进制转换 也有相应的方案 ByteUtils hexToInt   intTo 可以根据API文档进行观看使用");
        System.out.println("学习圈子：https://pd.qq.com/s/bgsawr8he");
        System.out.println("官网地址：https://www.snailcatmall.com");
        System.out.println("视频学习文档合集：https://space.bilibili.com/29826047/lists?sid=7051754&spm_id_from=333.788.0.0");
        System.out.println("炫彩界面库官方API文档：http://8.138.59.98/xcgui/index.html");
        System.out.println("JCEF浏览器组件官方文档：https://github.com/jcefmaven/jcefbuild/releases");
        System.out.println("Java代码例子案例：https://pan.baidu.com/s/5nuqiuByVLOlAV4TfTIT25g");
        System.out.println("如果出现 java.lang.OutOfMemoryError: Java heap space 内存不足 配置运行时的VM 参数 ： -encoding utf-8 -charset utf-8 -XX:-UseGCOverheadLimit -Xms1T -Xmx1T  -XX:MaxPermSize=1T");
        System.out.println("如果出现 Can't find dependent libraries  请将jar包中对应的系统版本号的 jawt.dll 文件放在java.exe的运行目录下，或者从java目录下直接复制到这个位置下即可，因为界面库依赖了Swing库");
        System.out.println("======================================================");
        System.out.println("生产环境：PathFileJSCM.setDebugFile32(PathFileJSCM.getJarPath()+\"demo\\\\x86\\\\\");\n" +
                "        PathFileJSCM.setDebugFile64(PathFileJSCM.getJarPath()+\"demo\\\\x64\\\\\");\n" +
                "        PathFileJSCM.setIsDebug(false);");
        System.out.println("======================================================");
        System.out.println("开发环境：PathFileJSCM.setDebugFile32(\"C:\\\\Users\\\\www10\\\\IdeaProjects\\\\scmJnaApi\\\\Application\\\\JscmOneToOne\\\\Jscm框架完整附属包V1.1.2\\\\x86\\\\\");\n" +
                "        PathFileJSCM.setDebugFile64(\"C:\\\\Users\\\\www10\\\\IdeaProjects\\\\scmJnaApi\\\\Application\\\\JscmOneToOne\\\\Jscm框架完整附属包V1.1.2\\\\x64\\\\\");\n" +
                "        PathFileJSCM.setIsDebug(true);");
    }
    /**
     * 该方法位配置JSCM测试环境的位置 32位库
     */
    private static String debugFile32 ="C:\\Users\\www10\\IdeaProjects\\scmJnaApi\\JscmFunction\\Jscm\\_int\\Jscm\\release\\win32\\linker\\";
    /**
     * 该方法位配置JSCM测试环境的位置 64位库
     */
    private static String debugFile64 ="G:\\Users\\www10\\IdeaProjects\\scmJnaApi\\JscmFunction\\Jscm\\_int\\Jscm\\release\\x64\\linker\\";
    private static boolean isDebug;

    public static String getDebugFile32() {
        return debugFile32;
    }

    /**
     * 成对使用
     * @param debugFile32 文件夹
     */
    public static void setDebugFile32(String debugFile32) {
        PathFileJSCM.debugFile32 = debugFile32;
    }

    public static String getDebugFile64() {
        return debugFile64;
    }

    /**
     * 成对使用
     * @param debugFile64 文件夹
     */
    public static void setDebugFile64(String debugFile64) {
        PathFileJSCM.debugFile64 = debugFile64;
    }

    public static boolean isIsDebug() {
        return isDebug;
    }

    /**
     * 初始化调试环境，
     * @param fileDirX86 文件夹/x86 如果要配置就要双向都配置,单向不生效  必须填写  要嘛都为空  要嘛都要写   结尾必须斜杠
     * @param fileDirX64 文件夹/x64 如果要配置就要双向都配置,单向不生效   必须填写 要嘛都为空  要嘛都要写   结尾必须斜杠
     * @param isDebug 是否为调试环境
     */
    public static void initIsDebug(String fileDirX86,String fileDirX64,boolean isDebug){
        setDebugFile32(fileDirX86);
        setDebugFile64(fileDirX64);
        setIsDebug(isDebug);
    }
    public static void setIsDebug(boolean isDebug) {
        PathFileJSCM.isDebug = isDebug;
    }
    /**
     * 返回jar运行目录
     * @return 返回路径
     */
    public static String getJarPath(){
        return System.getProperty("user.dir")+"\\";
    }
}
