package com.scm.all.export.adobe;

import com.scm.all.export.adobe.Tools.sendSocket;

import java.io.IOException;
import java.net.Socket;

/**
 * Adobe 操作工具类 支持大部分Adobe
 * 请配置环境：Adoble-首选项-增效工具-打开服务
 */
public class InstallAdobeJsx extends sendSocket {
    private static int password;

    /**
     * @return 获取服务密码
     */
    public static int getPassword() {
        return password;
    }

    /**
     * @param password 设置服务密码
     */
    public static void setPassword(int password) {
        InstallAdobeJsx.password = password;
    }

    private static Socket socket;
    public static Socket connect()throws Exception{
        socket = sendSocket.connectPs();
        return socket;
    }
    /**
     * 关闭端口
     */
    public static void quit(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送JSX脚本
     * @param JSXString 配置脚本
     * @param password 链接密码
     * @return 返回结果
     */
    public static String SendJsx(String JSXString,String password) {
       return sendSocket.send(JSXString,password);
    }
}
