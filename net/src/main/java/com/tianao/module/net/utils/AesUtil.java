package com.tianao.module.net.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AesUtil {
    public static final String VIPARA = "1234567890123456";// "1269571569321021";
    public static final String bm = "UTF-8";


    /**
     * 字节数组转化为大写16进制字符串
     *
     * @param b
     * @return
     */
    private static String byte2HexStr(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            String s = Integer.toHexString(b[i] & 0xFF);
            if (s.length() == 1) {
                sb.append("0");
            }

            sb.append(s.toUpperCase());
        }

        return sb.toString();
    }

    /**
     * 16进制字符串转字节数组
     *
     * @param s
     * @return
     */
    private byte[] str2ByteArray(String s) {
        int byteArrayLength = s.length() / 2;
        byte[] b = new byte[byteArrayLength];
        for (int i = 0; i < byteArrayLength; i++) {
            byte b0 = (byte) Integer.valueOf(s.substring(i * 2, i * 2 + 2), 16)
                    .intValue();
            b[i] = b0;
        }

        return b;
    }


    private static final String transformation = "AES/CBC/PKCS5Padding";

    /**
     * AES 加密
     *
     * @param content  明文
     * @param password 生成秘钥的关键字
     * @return
     */
    public static String aesEncrypt(String content, String password, String iv) {
        try {
            IvParameterSpec zeroIv = new IvParameterSpec(iv.getBytes());
            SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
            byte[] encryptedData = cipher.doFinal(content.getBytes(bm));
            // return new String(encryptedData,bm);
            return Base64.encode(encryptedData);
//          return byte2HexStr(encryptedData);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    /**
     * AES 解密
     *
     * @param content  密文
     * @param password 生成秘钥的关键字
     * @return
     */

    public static String aesDecrypt(String content, String password, String iv) {
        try {
            byte[] byteMi = Base64.decode(content);
//          byte[] byteMi=  str2ByteArray(content);
            IvParameterSpec zeroIv = new IvParameterSpec(iv.getBytes());
            SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
            byte[] decryptedData = cipher.doFinal(byteMi);
            return new String(decryptedData);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
