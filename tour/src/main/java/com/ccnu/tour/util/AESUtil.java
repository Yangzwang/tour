package com.ccnu.tour.util;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * AES加密解密字符串工具类
 * 概述：AES高级加密标准，是对称密钥加密中最流行的算法之一；
 *       工作模式包括：ECB、CBC、CTR、OFB、CFB；
 * 使用范围：该工具类仅支持CBC模式下的：
 *              填充：PKCS5PADDING
 *              数据块：128位
 *              密码（key）：16字节长度（例如：1234567890123456）
 *              偏移量（iv）：暂定和key一样
 *              输出：hex
 *              字符集：UTF-8
 * 使用方式：String encrypt = AESUtil.encrypt("wy");
 *           String decrypt = AESUtil.decrypt(encrypt);
 * 验证方式：http://tool.chacuo.net/cryptaes（在线AES加密解密）
 */
public class AESUtil {

/*    //SecretKey 负责保存对称密钥
    private static SecretKeySpec deskey;
    // Cipher负责完成加密或解密工作
    private static Cipher cipher;
    //
    private static IvParameterSpec ivParameterSpec;*/

    // 密码(key)
    private static final String password = "rihq8hd92qyr2328";
    // 偏移量
    private static final String ivParameter = "rihq8hd92qyr2328";
    // 使用CBC模式
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

    private static final String KEY_ALGORITHM = "AES";

    private final static Logger logger = LoggerFactory.getLogger(AESUtil.class);


    /**
     * 初始化参数
     * @return
     */
/*    private static void initParam(String password,String iv) {

        try {
            ivParameterSpec = new IvParameterSpec(iv.getBytes("UTF-8"));
            deskey =  new SecretKeySpec(password.getBytes("UTF-8"), KEY_ALGORITHM); // 转换为AES专用密钥
            cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            logger.warn("####[1] AESUtil initParam failed.", e);
        } catch (NoSuchPaddingException e) {
            logger.warn("####[2] AESUtil initParam failed.", e);
        } catch (UnsupportedEncodingException e) {
            logger.warn("####[3] AESUtil initParam failed.", e);
        }

    }*/


    /**
     * 对字符串加密
     *
     * @param str
     * @return
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws UnsupportedEncodingException
     */
    public static String doEncrypt(String str) {
        // initParam(password,ivParameter);
        String Darktext = null;
        try {

            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivParameter.getBytes("UTF-8"));

            SecretKeySpec deskey = new SecretKeySpec(password.getBytes("UTF-8"), KEY_ALGORITHM); // 转换为AES专用密钥

            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            // 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式
            cipher.init(Cipher.ENCRYPT_MODE, deskey, ivParameterSpec);

            byte[] src = str.getBytes("utf-8");

            // 加密，结果保存进cipherByte
            Darktext = parseByte2HexStr(cipher.doFinal(src));
        } catch (InvalidKeyException e) {
            logger.warn("####[1] AESUtil doEncrypt failed", e);
        } catch (BadPaddingException e) {
            logger.warn("####[2] AESUtil doEncrypt failed", e);
        } catch (UnsupportedEncodingException e) {
            logger.warn("####[3] AESUtil doEncrypt failed", e);
        } catch (IllegalBlockSizeException e) {
            logger.warn("####[4] AESUtil doEncrypt failed", e);
        } catch (InvalidAlgorithmParameterException e) {
            logger.warn("####[5] AESUtil doEncrypt failed", e);
        } catch (NoSuchPaddingException e) {
            logger.warn("####[6] AESUtil doEncrypt failed", e);
        } catch (NoSuchAlgorithmException e) {
            logger.warn("####[7] AESUtil doEncrypt failed", e);
        }
        return Darktext;
    }


    /**
     * 对字符串解密
     *
     * @param content
     * @return
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static String doDecrypt(String content) {
        // initParam(password,ivParameter);
        String Plaintext = null;

        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivParameter.getBytes("UTF-8"));

            SecretKeySpec deskey = new SecretKeySpec(password.getBytes("UTF-8"), KEY_ALGORITHM); // 转换为AES专用密钥

            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            // 根据密钥，对Cipher对象进行初始化，DECRYPT_MODE表示解密模式
            cipher.init(Cipher.DECRYPT_MODE, deskey, ivParameterSpec);

            byte[] bytes = cipher.doFinal(parseHexStr2Byte(content));

            Plaintext = new String(bytes);

        } catch (InvalidKeyException e) {
            logger.warn("####[1] AESUtil doDecrypt failed", e);
        } catch (BadPaddingException e) {
            logger.warn("####[2] AESUtil doDecrypt failed", e);
        } catch (IllegalBlockSizeException e) {
            logger.warn("####[3] AESUtil doDecrypt failed", e);
        } catch (InvalidAlgorithmParameterException e) {
            logger.warn("####[4] AESUtil doDecrypt failed", e);
        } catch (NoSuchPaddingException e) {
            logger.warn("####[5] AESUtil doDecrypt failed", e);
        } catch (UnsupportedEncodingException e) {
            logger.warn("####[6] AESUtil doDecrypt failed", e);
        } catch (NoSuchAlgorithmException e) {
            logger.warn("####[7] AESUtil doDecrypt failed", e);
        }

        return Plaintext;

    }


    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }


    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }


    /**
     * 对SdkUrl进行加密
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static String doEncryptOfSdkUrl(String url) {

        String Darktext = null;
        try {

            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivParameter.getBytes("UTF-8"));
            // 转换为AES专用密钥
            SecretKeySpec deskey = new SecretKeySpec(password.getBytes("UTF-8"), KEY_ALGORITHM);
            //"算法/模式/补码方式"
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            // 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式
            cipher.init(Cipher.ENCRYPT_MODE, deskey, ivParameterSpec);
            byte[] src = url.getBytes("utf-8");
            // 加密，结果保存进cipherByte(此处使用BASE64做转码功能)
            Darktext = new Base64().encodeToString(cipher.doFinal(src));

        } catch (InvalidKeyException e) {
            logger.warn("####[1] AESUtil doEncrypt failed", e);
        } catch (BadPaddingException e) {
            logger.warn("####[2] AESUtil doEncrypt failed", e);
        } catch (UnsupportedEncodingException e) {
            logger.warn("####[3] AESUtil doEncrypt failed", e);
        } catch (IllegalBlockSizeException e) {
            logger.warn("####[4] AESUtil doEncrypt failed", e);
        } catch (InvalidAlgorithmParameterException e) {
            logger.warn("####[5] AESUtil doEncrypt failed", e);
        } catch (NoSuchPaddingException e) {
            logger.warn("####[6] AESUtil doEncrypt failed", e);
        } catch (NoSuchAlgorithmException e) {
            logger.warn("####[7] AESUtil doEncrypt failed", e);
        }
        return Darktext;

    }

}
