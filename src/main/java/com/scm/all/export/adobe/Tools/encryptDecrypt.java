package com.scm.all.export.adobe.Tools;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

class encryptDecrypt {
    private Cipher mECipher;
    private Cipher mDCipher;

    private byte [] SALT = { 'A', 'd', 'o', 'b', 'e', ' ', 'P', 'h', 'o', 't', 'o', 's', 'h', 'o', 'p' };
    private final int ITERATION_COUNT = 1000;
    private final int KEY_LENGTH = 24;

    encryptDecrypt(String passPhrase) throws InvalidAlgorithmParameterException,
					     NoSuchPaddingException,
					     NoSuchAlgorithmException,
					     InvalidKeyException {

       	DESedeKeySpec keySpec = new DESedeKeySpec(PBKDF2.deriveKey( passPhrase.getBytes(), SALT, ITERATION_COUNT, KEY_LENGTH ));
       	Key key = new SecretKeySpec(keySpec.getKey(), "DESede");
        mECipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        mDCipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(new byte[8]);
        mECipher.init(Cipher.ENCRYPT_MODE, key, iv);
        mDCipher.init(Cipher.DECRYPT_MODE, key, iv);

    }

    public byte [] encrypt(byte [] inBytes) throws BadPaddingException,
						   IllegalBlockSizeException,
						   UnsupportedEncodingException {

	byte[] encryptedBytes = mECipher.doFinal(inBytes);
    	return encryptedBytes;

    }


    public byte [] decrypt(byte [] strBytes) throws BadPaddingException,
						    IllegalBlockSizeException,
						    UnsupportedEncodingException,
						    IOException {

    	byte[] utf8 = mDCipher.doFinal(strBytes);
    	return utf8;

    }

} /* 结束EncryptDecrypt.java */

