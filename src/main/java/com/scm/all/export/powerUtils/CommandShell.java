package com.scm.all.export.powerUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandShell {
    /**
     * 获取mac地址
     * @return 获取Mac地址
     */
    public static String GetMacInfo_Win(){
        int exitCode = -1;
        try {
            // 创建进程生成器
            Process process = Runtime.getRuntime().exec("netsh wlan show interfaces");
            // 读取命令输出流
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(),"gb2312"));
            String line;
            exitCode = process.waitFor();
            if (exitCode == 0) {
                while ((line = reader.readLine()) != null) {
                    String guidPattern = "GUID:(.*)";
                    Pattern pattern = Pattern.compile(guidPattern);
                    Matcher matcher = pattern.matcher(line.replaceAll(" ",""));
                    if (matcher.find()) {
                        return matcher.group(1);
                    }
                }
            } else {
                System.err.println("文件下载失败，错误代码：" + exitCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
