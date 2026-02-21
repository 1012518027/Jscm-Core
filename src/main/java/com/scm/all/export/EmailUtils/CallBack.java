package com.scm.all.export.EmailUtils;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Store;

public class CallBack {
    /**
     * @param messages 消息结构体
     * @param folder 文件夹
     * @param store 指令
     * @throws Exception 异常反馈
     */
    public void data(Message[] messages, Folder folder, Store store) throws Exception{};
}
