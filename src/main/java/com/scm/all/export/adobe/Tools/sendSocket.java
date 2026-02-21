package com.scm.all.export.adobe.Tools;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class sendSocket {
    private static Socket photoshopSocket = null;
    public static Socket connectPs()throws Exception{
        photoshopSocket = new Socket("0.0.0.0", 49494);
        return photoshopSocket;
    }

    protected static String send(String message, String password){
        DataOutputStream outputStream = null;
        DataInputStream inputStream = null;
        try {
            Protocol Protocol = new Protocol(password);
            outputStream = new DataOutputStream(photoshopSocket.getOutputStream());
            inputStream = new DataInputStream(photoshopSocket.getInputStream());
            byte[] bytesStr = Protocol.processJavaScript(message, outputStream, inputStream);
            byte[] newBytesStr = new byte[bytesStr.length];
            int j = 0;
            for (int i = 0; i < bytesStr.length; i++) {
                if (bytesStr[i] != 13) {
                    newBytesStr[j++] = bytesStr[i];
                } else {
                    newBytesStr[j++] = ' ';
                }
            }
            String returnString = new String(newBytesStr);
            return returnString;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
