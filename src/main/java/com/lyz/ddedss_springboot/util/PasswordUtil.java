package com.lyz.ddedss_springboot.util;

import cn.hutool.core.util.HexUtil;
import sun.security.util.Password;

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
        return new String(HexUtil.encodeHex(bytes));
    }

    /**
     * 校验密码
     */
    public static boolean check(String correctPassword, String password, String salt) throws NoSuchAlgorithmException {
        String s = encrypt(encrypt(password), salt);
        return s.equals(correctPassword);
    }

}
