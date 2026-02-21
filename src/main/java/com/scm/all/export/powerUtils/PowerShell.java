package com.scm.all.export.powerUtils;

/**
 * 系统自带脚本命令库
 */
public class PowerShell {
    /**
     * cmd下载命令
     * @param url 下载地址
     * @param pathFileName   存放的下载路径包含后缀名
     * @return -1代表失败 0代表成功
     */
    public static int  downLoad_Win(String url,String pathFileName){
//        int exitCode = -1;
//        try {
//            // 创建进程生成器
//            ProcessBuilder builder = new ProcessBuilder("cmd.exe ", "/c", "certutil -urlcache -split -f \""+url+"\" \""+pathFileName+"\"");
//            // 重定向错误输出流到标准输出流
//            builder.redirectErrorStream(true);
//            // 启动进程
//            Process process = builder.start();
//            // 等待进程结束
//            exitCode = process.waitFor();
//            System.out.println("Exit code: " + exitCode);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return exitCode;

        int exitCode = -1;
        try {
            // 创建进程生成器
            Process process = Runtime.getRuntime().exec("certutil -urlcache -split -f \""+url+"\" \""+pathFileName+"\"");
            exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("文件下载成功");
            } else {
                System.err.println("文件下载失败，错误代码：" + exitCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exitCode;
    }
    /**
     * powershell 下载命令  New-Object System.Net.WebClient
     * @param url 下载地址
     * @param pathFileName   存放的下载路径包含后缀名
     * @return -1代表失败 0代表成功
     */
    public static int  downLoadPowerShell_Win(String url,String pathFileName){
        int exitCode = -1;
        try {
            // 创建进程生成器
            Process process = Runtime.getRuntime().exec("powershell -ExecutionPolicy Bypass -Command \"(New-Object System.Net.WebClient).DownloadFile('"+url+"', '"+pathFileName+"')\"");
            exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("文件下载成功");
            } else {
                System.err.println("文件下载失败，错误代码：" + exitCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exitCode;
    }

    /**
     * 终端下载命令
     * @param url 下载地址
     * @param pathFileName   存放的下载路径包含后缀名
     * @return -1代表失败 0代表成功
     */
    public static int downLoad_Mac(String url, String pathFileName){
//        int exitCode = -1;
//        try {
//            // 创建进程生成器
//            ProcessBuilder builder = new ProcessBuilder("curl -o "+pathFileName+" [" + url + "]");
//            // 重定向错误输出流到标准输出流
//            builder.redirectErrorStream(true);
//            // 启动进程
//            Process process = builder.start();
//            // 等待进程结束
//            exitCode = process.waitFor();
//            System.out.println("Exit code: " + exitCode);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return exitCode;

        int exitCode = -1;
        try {
            // 创建进程生成器
            Process process = Runtime.getRuntime().exec("curl -o "+pathFileName+" [" + url + "]");
            exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("文件下载成功");
            } else {
                System.err.println("文件下载失败，错误代码：" + exitCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exitCode;
    }
}
