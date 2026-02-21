package com.scm.all.export.EmailUtils;

import com.sun.mail.imap.IMAPStore;
import org.apache.commons.lang3.StringUtils;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;

/**
 * javax.mail-1.6.2.jar  已经整合
 * 邮件处理工具类
 * 发件器：smtp
 * 收件器：pop imap pop3
 * //        发信协议只有 smtp
 * //        String host = "smtp.139.com";
 * //        int port= 465;
 * //        String userName ="xxxxx@139.com";
 * //        String passWord ="xxxxx";
 * //        配置发信服务器
 * //        EMailUtils.addProperties(host,port,"smtp",true,true,true,6000,5000,false);
 * ////        配置会话Session
 * //        Session session = EMailUtils.getSession(userName,passWord);
 * ////        配置头部信息
 * //        Message message = EMailUtils.getMessageCenText(session,userName,"xxxxx@qq.com",null,null,"xxxxx");
 * ////        拼接器
 * //        Multipart multipart = EMailUtils.getSpliceData();
 * //        multipart = EMailUtils.addMessageText(multipart,"");
 * //        multipart = EMailUtils.addMessageJson(multipart,"{\"code\":0,\"message\":\"SUCCESS\",\"data\":[{{\"code\":0,\"message\":\"SUCCESS\",\"data\":[{\"origin\":\"阿萨德\",\"explain\":\"Assad；\",\"from\":\"zh\",\"to\":\"en\",\"index\":0}]}\"origin\":\"阿萨德\",\"explain\":\"Assad；\",\"from\":\"zh\",\"to\":\"en\",\"index\":0}]}","66666");
 * ////        发送消息
 * //        EMailUtils.sendEmail(message,multipart);
 *
 *
 *
 * //        收件箱协议有pop imap
 *
 * //        枚举邮件箱的文件夹名
 * //        host = "imap.139.com";
 * //        port= 993;
 * //        配置发信服务器
 * //        EMailUtils.addProperties(host,port,"imap",true,true,true,6000,5000,false);
 * //        Session session = EMailUtils.getSession(userName,passWord);
 * //        String finalHost = host;
 * //        new Thread(new Runnable() {
 * //            @Override
 * //            public void run() {
 * //                while (true){
 * //                    synchronized (this){
 * //                        try {
 * //                            Thread.sleep(2000);
 * //                        } catch (InterruptedException e) {
 * //                            e.printStackTrace();
 * //                        }
 *                         //        获取文件夹下的内容
 * //                        EMailUtils.getEmailData(session, finalHost,userName,passWord,"imap","INBOX",new CallBack(){
 * //                            public void data(Message[] messages, Folder folder, Store store) throws Exception {
 * //                                for(Message message : messages){
 * //                                    if(StringUtils.equals(message.getSubject(),"xxxxx")){
 * //                                        InputStream inputStream = EMailUtils.downLoadFileInputStream(message);
 * //                        是否为已读
 * //                                        if(!EMailUtils.isReadMall(message)){
 * //                            标记为已读
 * //                                            EMailUtils.setReadMall(message,true);
 *                                         }
 * //                                        System.out.println(EMailUtils.getJson(inputStream));
 * //                        全部删除
 * //                                        EMailUtils.deleteFolder(message,folder);
 * //                                    }
 * //                                }
 * //                            }
 * //                        });
 * //                    }
 * //                }
 * //            }
 * //        }).start();
 */
public class EMailUtils {
    /**
     * 添加邮箱参数
     * @param host 服务器
     * @param port 端口
     * @param storeName 服务 pop smtp imap pop3
     * @param auth 是否加密
     * @param starttlsEnable 是否SSL
     * @param sslEnable  是否SSL
     * @param connectiontimeout 连接超时 5000
     * @param timeout 连接超时 5000
     * @param socketFactoryFallback 是否返回错误
     * @return Properties 属性
     */
    public static Properties addProperties(String host, int port,String storeName,boolean auth,boolean starttlsEnable,boolean sslEnable,int connectiontimeout,int timeout,boolean socketFactoryFallback){
        Properties props = new Properties();
        props.put("mail."+storeName+".host",host);
        props.put("mail."+storeName+".port",port);
        props.put("mail."+storeName+".auth",auth);
        props.put("mail."+storeName+".starttls.enable",starttlsEnable);
        props.put("mail."+storeName+".ssl.enable",sslEnable);
        props.put("mail."+storeName+".ssl.trust", host);
        props.put("mail."+storeName+".connectiontimeout",connectiontimeout);
        props.put("mail."+storeName+".timeout",timeout);
        props.put("mail."+storeName+".socketFactory.fallback",socketFactoryFallback);
        props.put("mail."+storeName+".protocol", storeName);
        return props;
    }


    /**
     * 返回会话
     * @param props 成员
     * @param user 邮箱账户
     * @param pass 邮箱密码
     * @return 返回会话管理
     */
    public static Session getSession(Properties props, String user, String pass){
        try {
            // 创建会话对象
            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, pass);
                }
            });
            return session;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 发件人头部
     * @param session getSession
     * @param user 发件人
     * @param addressee 收件人
     * @param copyAddressee 抄送收件人
     * @param BCC 密送收件人
     * @param title 主题
     * @return Message消息结构体
     */
    public static Message getMessageCenText(Session session,String user,String addressee,String copyAddressee,String BCC,String title){
        try {
            Message message = new MimeMessage(session);
            // 设置发件人
            message.setFrom(new InternetAddress(user));
            // 设置收件人
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(addressee));
            if(StringUtils.isNotEmpty(copyAddressee)){
                message.addRecipient(Message.RecipientType.CC, new InternetAddress(copyAddressee));
            }
            if(StringUtils.isNotEmpty(BCC)){
                message.addRecipient(Message.RecipientType.BCC, new InternetAddress(BCC));
            }
            // 设置邮件主题
            message.setSubject(title);
            return message;
        }catch (Exception e){e.printStackTrace();}
        return null;
    }

    /**
     * @return 拼接内容
     */
    public static Multipart getSpliceData(){
        return new MimeMultipart();
    }
    /**
     * 发送文本
     * @param multipart 拼接器
     * @param text text 文本
     * @return Message消息结构体
     */
    public static Multipart addMessageText(Multipart multipart,String text){
        try {
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(text);
            multipart.addBodyPart(textPart);
            return multipart;
        }catch (Exception e){e.printStackTrace();}
        return null;
    }

    /**
     * 发送HTML
     * @param multipart 拼接器
     * @param html html 代码
     * @return Message消息结构体
     */
    public static Multipart addMessageHTML(Multipart multipart,String html){
        try {
            // 创建HTML部分
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(html, "text/html; charset=utf-8");
            multipart.addBodyPart(htmlPart);
            return multipart;
        }catch (Exception e){e.printStackTrace();}
        return null;
    }
    /**
     * 发送Json
     * @param multipart 拼接器
     * @param json html 代码
     * @param FileName 文件名
     * @return Message消息结构体
     */
    public static Multipart addMessageJson(Multipart multipart,String json,String FileName){
        try {
            // 创建HTML部分
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(json, "application/json;charset=UTF-8");
            htmlPart.setFileName(FileName);
            multipart.addBodyPart(htmlPart);
            return multipart;
        }catch (Exception e){e.printStackTrace();}
        return null;
    }
    /**
     * 添加图片
     * @param multipart 拼接器
     * @param FilePath 文件地址
     * @param FileName 文件名
     * @return 返回拼接器
     */
    public static Multipart addMessageImage(Multipart multipart,String FilePath,String FileName){
        try {
            MimeBodyPart imagePart = new MimeBodyPart();
            File imageFile = new File(FilePath);
            imagePart.attachFile(imageFile);
            imagePart.setContentID("<image>");
            imagePart.setDisposition(MimeBodyPart.INLINE);
            imagePart.setFileName(FileName);
            multipart.addBodyPart(imagePart);
            return multipart;
        }catch (Exception e){e.printStackTrace();}
        return null;
    }
    /**
     * 添加视频
     * @param multipart 拼接器
     * @param FilePath 文件地址
     * @param FileName 文件名
     * @return 返回拼接器
     */
    public static Multipart addMessageVideo(Multipart multipart,String FilePath,String FileName){
        try {
            // 创建附件部分
            MimeBodyPart attachmentPart = new MimeBodyPart();
            DataSource source = new FileDataSource(FilePath);
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName(FileName);
            multipart.addBodyPart(attachmentPart);
        }catch (Exception e){e.printStackTrace();}
        return null;
    }

    /**
     * 添加文件
     * @param multipart 拼接器
     * @param FilePath 文件地址
     * @param FileName 文件名
     * @return 返回拼接器
     */
    public static Multipart addMessageFile(Multipart multipart,String FilePath,String FileName){
        try {
            // 创建附件部分
            MimeBodyPart attachmentPart = new MimeBodyPart();
            File attachmentFile = new File(FilePath);
            attachmentPart.attachFile(attachmentFile);
            attachmentPart.setFileName(FileName);
            multipart.addBodyPart(attachmentPart);
            return multipart;
        }catch (Exception e){e.printStackTrace();}
        return null;
    }

    /**
     * 发送邮件
     * @param message 消息体
     * @param multipart 链接器
     */
    public static void sendEmail(Message message,Multipart multipart){
        try {
            message.setContent(multipart);
            Transport.send(message);
        }catch (Exception e){e.printStackTrace();}
    }


    /**
     * 返回状态
     * @param session 会话
     * @param host 地址
     * @param user 账户
     * @param pass 密码
     * @param storeName 状态 pop3 imap pop
     * @return 状态
     */
    public static IMAPStore getStore(Session session,String host,String user, String pass,String storeName){
        try {
            HashMap IAM = new HashMap();
//            Map<String,String> IAM = new HashMap<>();
//带上IMAP ID信息，由key和value组成，例如name，version，vendor，support-email等。
            IAM.put("name","snailctmall");
            IAM.put("version","1.0.0");
            IAM.put("vendor","snailctmall");
            IAM.put("support-email",user);
            IMAPStore store = (IMAPStore) session.getStore(storeName);
            store.connect(host, user, pass);
            store.id(IAM);
            return store;
        }catch (Exception e) {
            return null;
        }
    }
    public static Store getStoreTow(Session session,String host,String user, String pass,String storeName){
        try {
            HashMap IAM = new HashMap();
//            Map<String,String> IAM = new HashMap<>();
//带上IMAP ID信息，由key和value组成，例如name，version，vendor，support-email等。
            IAM.put("name","snailctmall");
            IAM.put("version","1.0.0");
            IAM.put("vendor","snailctmall");
            IAM.put("support-email",user);
            Store store = (Store) session.getStore(storeName);
            store.connect(host, user, pass);

            return store;
        }catch (Exception e) {
            return null;
        }
    }
    public static Folder getFolderRead(IMAPStore store,String folderName){
        try {
            // 打开收件箱
            Folder folder = store.getFolder(folderName);
            folder.open(Folder.READ_ONLY);
            if (!folder.isOpen()) {
                return getFolderRead(store,folderName);
            }
            return folder;
        }catch (Exception e){e.printStackTrace();}
        return getFolderRead(store,folderName);
    }
    public static Folder getFolderWrite(IMAPStore store,String folderName){
        try {
            // 打开收件箱
            Folder folder = store.getFolder(folderName);
            folder.open(Folder.READ_WRITE);
            if (!folder.isOpen()) {
                return getFolderWrite(store,folderName);
            }
            return folder;
        }catch (Exception e){e.printStackTrace();}
        return getFolderWrite(store,folderName);
    }
    public static Folder getFolderRead(Store store,String folderName){
        try {
            // 打开收件箱
            Folder folder = store.getFolder(folderName);
            folder.open(Folder.READ_ONLY);
            if (!folder.isOpen()) {
                return getFolderRead(store,folderName);
            }
            return folder;
        }catch (Exception e){e.printStackTrace();}
        return getFolderRead(store,folderName);
    }
    public static Folder getFolderWrite(Store store,String folderName){
        try {
            // 打开收件箱
            Folder folder = store.getFolder(folderName);
            folder.open(Folder.READ_WRITE);
            if (!folder.isOpen()) {
                return getFolderWrite(store,folderName);
            }
            return folder;
        }catch (Exception e){e.printStackTrace();}
        return getFolderWrite(store,folderName);
    }
    /**
     * 接收邮件数据
     * @param folder 文件夹
     * @param store store
     * @param callBack 回调
     */
    public static void getEmailData(Folder folder,IMAPStore store,CallBack callBack){
        try {
            callBack.data(folder.getMessages(),folder,store);
        }catch (Exception e){e.printStackTrace();}
    }
    /**
     * 接收邮件数据
     * @param folder 文件夹
     * @param store store
     * @param callBack 回调
     */
    public static void getEmailData(Folder folder,Store store,CallBack callBack){
        try {
            callBack.data(folder.getMessages(),folder,store);
        }catch (Exception e){e.printStackTrace();}
    }
    /**
     * 返回结果
     * @param message 消息体
     * @param outFilePath 存放地址
     * @return 返回结果
     */
    public static boolean downLoadFile(Message message,String outFilePath){
        try {
            if (message.isMimeType("multipart/*")) {
                Multipart multipart = (Multipart) message.getContent();
                for (int i = 0; i < multipart.getCount(); i++) {
                    BodyPart bodyPart = multipart.getBodyPart(i);
                    // 检查是否为附件
                    if (Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition())) {
                        InputStream inputStream = bodyPart.getInputStream();
                        FileOutputStream outputStream = new FileOutputStream(outFilePath);

                        byte[] buffer = new byte[inputStream.available()];
                        int bytesRead;
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                        outputStream.close();
                        inputStream.close();
                        return true;
                    }
                }
            }else {
                return false;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 返回流
     * @param message 消息体
     * @return 返回IO流
     */
    public static InputStream downLoadFileInputStream(Message message){
        try {
            if (message.isMimeType("multipart/*")) {
                Multipart multipart = (Multipart) message.getContent();
                for (int i = 0; i < multipart.getCount(); i++) {
                    BodyPart bodyPart = multipart.getBodyPart(i);
                    // 检查是否为附件
                    if (Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition())) {
                        return bodyPart.getInputStream();
                    }
                }
            }else {
                return null;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 返回邮件内容
     * @param message 消息结构体
     * @return 返回内容
     */
    public static String getTextContent(Message message){
        try {
            String content = "";
            Object messageContent = message.getContent();
            if (messageContent instanceof String) {
                content = (String) messageContent;
            } else if (messageContent instanceof Multipart) {
                Multipart multipart = (Multipart) messageContent;
                BodyPart bodyPart = multipart.getBodyPart(0);
                content = bodyPart.getContent().toString();
            }
            return content;
        }catch (Exception e){e.printStackTrace();}
        return null;
    }

    /**
     * 返回json文本
     * @param inputStream 填入流
     * @return 返回文本
     */
    public static String getJson(InputStream inputStream) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            return stringBuilder.toString();
        }catch (Exception e){e.printStackTrace();}
        return null;
    }

    /**
     * 返回协议邮箱关键字
     * @param store store
     * @return 返回文件夹数组
     */
    public static Folder[] getFolder(IMAPStore store){
        try {
            return store.getDefaultFolder().list("*");
        }catch (Exception e){e.printStackTrace();}
        return null;
    }

    /**
     * 删除指定信件 注意自己加判断
     * @param message 消息体
     * @param folder 信件
     */
    public static void deleteFolder(Message message,Folder folder){
        try {
            message.setFlag(Flags.Flag.DELETED, true);
            folder.expunge();
        }catch (Exception e){e.printStackTrace();}
    }
    public static void deleteFolder(Message[] messages,int index,Folder folder){
        try {
            messages[index].setFlag(Flags.Flag.DELETED, true);
            folder.expunge();
        }catch (Exception e){e.printStackTrace();}
    }

    /**
     * 移到到指定文件夹
     * @param message 当前邮件
     * @param folderPath 移到的文件夹
     * @param store 存储
     * @param folder 当前文件夹
     */
    public static void moveFolder(Message message,String folderPath,Store store,Folder folder){
       try{
           Folder destinationFolder = store.getFolder(folderPath);
           if (!destinationFolder.exists()) {
               destinationFolder.create(Folder.HOLDS_MESSAGES);
           }
           folder.copyMessages(new Message[]{message}, destinationFolder);
           message.setFlag(Flags.Flag.DELETED, true);
       }catch (Exception e){e.printStackTrace();}
    }
    /**
     * 移到到指定文件夹
     * @param messages 当前邮件
     * @param index 索引
     * @param folderPath 移到的文件夹
     * @param store 存储
     * @param folder 当前文件夹
     */
    public static void moveFolder(Message[] messages,int index,String folderPath,Store store,Folder folder){
        try{
            Folder destinationFolder = store.getFolder(folderPath);
            if (!destinationFolder.exists()) {
                destinationFolder.create(Folder.HOLDS_MESSAGES);
            }
            folder.copyMessages(new Message[]{messages[index]}, destinationFolder);
            messages[index].setFlag(Flags.Flag.DELETED, true);
        }catch (Exception e){e.printStackTrace();}
    }
    /**
     * 是否为已读
     * @param messages 消息结构体数组
     * @param index 索引
     * @return 真已读 加未标记
     */
    public static boolean isReadMall(Message[] messages,int index){
        try {
            boolean isRead = messages[index].isSet(Flags.Flag.SEEN);
            if (isRead) {
               return true;
            } else {
                return false;
            }
        }catch (Exception e){e.printStackTrace();}
        return false;
    }
    /**
     * 是否为已读
     * @param message 消息结构体
     * @return 真已读 加未标记
     */
    public static boolean isReadMall(Message message){
        try {
            boolean isRead = message.isSet(Flags.Flag.SEEN);
            if (isRead) {
                return true;
            } else {
                return false;
            }
        }catch (Exception e){e.printStackTrace();}
        return false;
    }
    /**
     * 标记为已读
     * @param messages 消息结构体数组
     * @param index 索引
     * @param flag 真为已读 假为未读
     */
    public static void setReadMall(Message[] messages,int index,boolean flag){
        try {
            messages[index].setFlag(Flags.Flag.SEEN, flag);;
        }catch (Exception e){e.printStackTrace();}
    }
    /**
     * 标记为已读
     * @param messages 消息结构体数组
     * @param flag 真为已读 假为未读
     */
    public static void setReadMall(Message messages,boolean flag){
        try {
            messages.setFlag(Flags.Flag.SEEN, flag);;
        }catch (Exception e){e.printStackTrace();}
    }

    /**
     * 是否连接成功 可用于心跳
     * @param session 会话Session
     * @param folderName pop pop3 imap smtp
     * @return 服务器跑通了就连接成功返回 true 失败返回false
     */
    public static boolean isConnect(Session session,String folderName){
        try {
            // 尝试连接SMTP服务器
            Transport transport = session.getTransport(folderName);
            transport.connect();

            // 关闭连接
            transport.close();
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 返回收件人邮箱
     * @param message 消息结构体
     * @return 返回收件人
     */
    public static Address[] getEmailRecipient(Message message){
       try {
           return message.getRecipients(Message.RecipientType.TO);
       }catch (Exception e){e.printStackTrace();}
       return null;
    }

    /**
     * 文件夹是否可读写
     * @param folder 文件夹
     * @return 返回是否可读写
     */
    public static boolean isReadOnly(Folder folder){
        try {
            folder.open(Folder.READ_WRITE);
            int mode = folder.getMode();
            return (mode & Folder.READ_WRITE) != 0;
        }catch (Exception e) {
            return false;
        }
    }

    /**
     * @param messages 集合排序
     */
    public static void listSort(Message[] messages){
        Arrays.sort(messages, (m1, m2) -> {
            try {
                return m2.getSentDate().compareTo(m1.getSentDate());
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return 0;
        });
    }

}
