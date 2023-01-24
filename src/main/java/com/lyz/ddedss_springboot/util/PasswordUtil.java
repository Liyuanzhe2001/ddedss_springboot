package com.lyz.ddedss_springboot.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5 盐值加密
 * 加密 ( 加密 ( 密码 ) + salt )
 */
public class PasswordUtil {

    /**
     * 加密
     */
    public static String encrypt(String password, String... salt) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        if (salt.length != 0) {
            for (String s : salt) {
                password += s;
            }
        }
        byte[] bytes = md5.digest((password).getBytes());
        return new String(bytes);
    }

    /**
     * 校验密码
     */
    public static boolean check(String realPassword, String password, String salt) throws NoSuchAlgorithmException {
        String s1 = encrypt(password);
        s1 = encrypt(s1, salt);
        return s1.equals(realPassword);
    }

}
