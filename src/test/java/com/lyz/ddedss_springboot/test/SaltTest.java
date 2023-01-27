package com.lyz.ddedss_springboot.test;

import cn.hutool.core.util.HexUtil;
import com.lyz.ddedss_springboot.util.PasswordUtil;
import org.junit.jupiter.api.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

public class SaltTest {

    @Test
    public void test() throws NoSuchAlgorithmException {
        // 加密(加密(密码) + salt)
        String salt = UUID.randomUUID().toString().replaceAll("-", "");
        String s1 = md5Hex("123123");
        s1 = md5Hex(s1, salt);

        boolean verify = verify("c92e03402b8c1abeb6815b749065a775", "123123", "b54f1f32ccad4b14b2cb9419a87a4c2f");

        System.out.println(salt);
        System.out.println(s1);
        System.out.println(verify);
    }

    /**
     * 加密
     */
    public static String md5Hex(String password, String... salt) throws NoSuchAlgorithmException {
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
    public static boolean verify(String realPassword, String password, String salt) throws NoSuchAlgorithmException {
        String s1 = md5Hex(password);
        s1 = md5Hex(s1, salt);
        return s1.equals(realPassword);
    }

    @Test
    public void utilTest() throws NoSuchAlgorithmException {
        boolean check = PasswordUtil.check("c92e03402b8c1abeb6815b749065a775", "123123", "b54f1f32ccad4b14b2cb9419a87a4c2f");
        System.out.println(check);
    }

}
