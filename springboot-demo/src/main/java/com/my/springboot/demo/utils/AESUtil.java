package com.my.springboot.demo.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESUtil {

    // 密钥必须是 16 字节（128 位）
    private static final String KEY = "streamCompAESKey"; // 示例密钥，可替换为你的配置

    /**
     * 加密字符串
     */
    public static String encrypt(String plainText) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); // 模式：ECB + 填充
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encrypted = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    /**
     * 解密字符串
     */
    public static String decrypt(String cipherText) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decoded = Base64.getDecoder().decode(cipherText);
        byte[] decrypted = cipher.doFinal(decoded);
        return new String(decrypted);
    }

    public static void main(String[] args) throws Exception {
        String original = "enjing.guo_shanghai_dev";
        String encrypted = encrypt(original);
        String decrypted = decrypt(encrypted);

        System.out.println("原文: " + original);
        System.out.println("加密: " + encrypted);
        System.out.println("解密: " + decrypted);
    }
}
