package com.scm.all.export.adobe.Tools;
import javax.crypto.NoSuchPaddingException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
class Protocol {

    /** 详细输出标志 */
    public boolean mShowMessages = false;

    /** 事务类型的值 */
    private static final int ILLEGAL_TYPE = 0;
    private static final int ERRORSTRING_TYPE = 1;
    private static final int JAVASCRIPT_TYPE = 2;
    private static final int IMAGE_TYPE = 3;
    private static final int PROFILE_TYPE = 4;
	private static final int DATA_TYPE = 5;
    
    /** 当前的协议版本 */
    private static final int PROTOCOL_VERSION = 1;
    
    /** 头的长度，不包括实际长度字节
	or the communication status */
    private static final int PROTOCOL_LENGTH = 4 + 4 + 4;
    private static final int COMM_LENGTH = 4;
    private static final int NO_COMM_ERROR = 0;

    /** 每次发送消息时碰撞这个值，
	 *  回复消息将与ID匹配
	*/
    public int mTransactionID = 0;

    /** 消息被加密，用来加密和解密消息的助手类 */
    private encryptDecrypt mEncryptDecrypt = null;

    Protocol(String inPassword) throws UnknownHostException,
						IOException, 
						InvalidAlgorithmParameterException,
						NoSuchPaddingException, 
						NoSuchAlgorithmException, 
						InvalidKeyException {

	mEncryptDecrypt = new encryptDecrypt(inPassword);

    }
    
    public byte [] processJavaScript(String theInput, DataOutputStream out, DataInputStream in) {

	if (mShowMessages) {
	    System.err.println("开始处理JavaScript");
	}

	byte [] theOutput = null;

	try {

	    /* WRITE */
	    byte [] strBytes = theInput.getBytes("UTF-8");
	    byte [] allBytes = new byte[PROTOCOL_LENGTH + strBytes.length + 1];

	    int byteIndexer = 0;
	    
            allBytes[byteIndexer++] = (byte)(PROTOCOL_VERSION >>> 24);
            allBytes[byteIndexer++] = (byte)(PROTOCOL_VERSION >>> 16);
            allBytes[byteIndexer++] = (byte)(PROTOCOL_VERSION >>> 8);
            allBytes[byteIndexer++] = (byte)(PROTOCOL_VERSION);
            
            int messageID = mTransactionID++;
            
            allBytes[byteIndexer++] = (byte)(messageID >>> 24);
            allBytes[byteIndexer++] = (byte)(messageID >>> 16);
            allBytes[byteIndexer++] = (byte)(messageID >>> 8);
            allBytes[byteIndexer++] = (byte)(messageID);
            
            allBytes[byteIndexer++] = (byte)(JAVASCRIPT_TYPE >>> 24);
            allBytes[byteIndexer++] = (byte)(JAVASCRIPT_TYPE >>> 16);
            allBytes[byteIndexer++] = (byte)(JAVASCRIPT_TYPE >>> 8);
            allBytes[byteIndexer++] = (byte)(JAVASCRIPT_TYPE);
            
            for (int i = 0; i < strBytes.length; i++) {
            	allBytes[byteIndexer++] = strBytes[i];
            }

	    allBytes[byteIndexer++] = (byte)0x0a; // this is \n

	    if (mShowMessages) {
		System.err.println( "字节加密: " + allBytes.length );
		for (int i = 0; i < allBytes.length; i++) {
		    System.err.print( Integer.toString( ( allBytes[i] & 0xff ) + 0x100, 16).substring( 1 ) + " " );
		    if (i > 0 && (((i+1) % 8) == 0)) System.err.println();
		}
		System.err.println();
	    }

	    byte [] encryptedBytes = mEncryptDecrypt.encrypt(allBytes);
	    
	    if (mShowMessages) {
		System.err.println( "字节加密: " + encryptedBytes.length);
		for (int i = 0; i < encryptedBytes.length; i++) {
		    System.err.print( Integer.toString( ( encryptedBytes[i] & 0xff ) + 0x100, 16).substring( 1 ) + " " );
		    if (i > 0 && (((i+1) % 8) == 0)) System.err.println();
		}
		System.err.println();
	    }

	    // 我们不加密通信状态，所以我们增加了长度
	    int messageLength = COMM_LENGTH + encryptedBytes.length;

	    if (mShowMessages) {
		System.err.println("消息长度: " + messageLength);
	    }

	    out.writeInt(messageLength);
	    out.writeInt(NO_COMM_ERROR);
	    out.write(encryptedBytes, 0, encryptedBytes.length);

	    if (mShowMessages) {
		System.err.println("发送JavaScript到服务器");
	    }

	    /* READ */
	    int inLength = in.readInt();

	    if (mShowMessages) {
		System.err.println("阅读长度: " + inLength);
	    }

	    int inComStatus = in.readInt();

	    if (mShowMessages) {
		System.err.println("阅读com状态: " + inComStatus);
	    }

	    if (inComStatus != 0) {
		int inVersion = in.readInt();

		if (mShowMessages) {
		    System.err.println("阅读版: " + inVersion);
		}

		int inTransaction = in.readInt();

		if (mShowMessages) {
		    System.err.println("阅读ID: " + inTransaction);
		}

		int inType = in.readInt();

		if (mShowMessages) {
		    System.err.println("阅读类型: " + inType);
		}

		if (inType == JAVASCRIPT_TYPE || inType == ERRORSTRING_TYPE) {
		    inLength -= PROTOCOL_LENGTH;
		    theOutput = new byte[inLength + 1];
		    int rr = in.read(theOutput, 0, inLength);

		    if (mShowMessages) {
			for (int i = 0; i < theOutput.length; i++) {
			    System.err.print(theOutput[i] + " ");
			}
			System.err.println("\n读取字节: " + rr);
		    }

		} else {

		    System.err.println("未知类型: " + inType);
		    
		}

		int inAvailable = in.available();
		for (int i = 0; i < inAvailable; i++) {
		    int inByte = in.readByte();

		    if (mShowMessages) {
			System.err.println("阅读更多的字节: " + inByte);
		    }

		}

		if (mShowMessages) {
		    System.err.println("从服务器读取通信错误");
		}

		// 没有错误消息被加密
	    } else {
		inLength = inLength - 4;
		long to = System.currentTimeMillis() + 2000;
		while ((in.available() < inLength) && (System.currentTimeMillis() < to)) {
		    to = to + 1; // wait for the entire message
		}
    				
		byte[] messageBytes = new byte[inLength];
		int readThisMany = in.read(messageBytes, 0, inLength);

		if (mShowMessages) {
		    System.err.println("读取此加密消息: " + readThisMany);
		    for (int i = 0; i < messageBytes.length; i++) {
			System.err.print( Integer.toString( ( messageBytes[i] & 0xff ) + 0x100, 16).substring( 1 ) + " " );
			if (i > 0 && (((i+1) % 8) == 0)) System.err.println();
		    }
		    System.err.println();
		}

		byte [] decryptedBytes = mEncryptDecrypt.decrypt(messageBytes);

		if (mShowMessages) {
		    System.err.println("解密消息: " + decryptedBytes.length);
		    for (int i = 0; i < decryptedBytes.length; i++) {
			System.err.print( Integer.toString( ( decryptedBytes[i] & 0xff ) + 0x100, 16).substring( 1 ) + " " );
			if (i > 0 && (((i+1) % 8) == 0)) System.err.println();
		    }
		    System.err.println();
		}

    		int messageIndexer = 0;
    		int messageVersion = (decryptedBytes[messageIndexer++] << 24) 
		    + ((decryptedBytes[messageIndexer++] & 0xFF) << 16)
		    + ((decryptedBytes[messageIndexer++] & 0xFF) << 8)
		    + (decryptedBytes[messageIndexer++] & 0xFF);

		if (mShowMessages) {
		    System.err.println("阅读版: " + messageVersion);
		}

    		messageID = (decryptedBytes[messageIndexer++] << 24) 
		    + ((decryptedBytes[messageIndexer++] & 0xFF) << 16)
		    + ((decryptedBytes[messageIndexer++] & 0xFF) << 8)
		    + (decryptedBytes[messageIndexer++] & 0xFF);

		if (mShowMessages) {
		    System.err.println("阅读消息id: " + messageID);
		}

    		int messageType = (decryptedBytes[messageIndexer++] << 24) + 
		    + ((decryptedBytes[messageIndexer++] & 0xFF) << 16)
		    + ((decryptedBytes[messageIndexer++] & 0xFF) << 8)
		    + (decryptedBytes[messageIndexer++] & 0xFF);

		if (mShowMessages) {
		    System.err.println("阅读消息类型: " + messageType);
		}

		if (messageType == JAVASCRIPT_TYPE || messageType == ERRORSTRING_TYPE) {
		    theOutput = new byte[decryptedBytes.length - messageIndexer];
		    for (int i = 0; i < theOutput.length; i++) {
			theOutput[i] = decryptedBytes[messageIndexer++];
		    }
		} else {

		    System.err.println("我们不做其他消息类型: " + messageType);

		}
	    }
	}
	catch (Exception e) {
	    System.out.println("异常过程 JavaScript: " + e.toString());
	}
	return theOutput;
    }
}
