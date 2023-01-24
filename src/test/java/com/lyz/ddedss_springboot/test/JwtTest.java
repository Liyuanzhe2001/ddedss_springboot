package com.lyz.ddedss_springboot.test;

import cn.hutool.crypto.KeyUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTHeader;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.lyz.ddedss_springboot.entity.User;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class JwtTest {

    @Test
    public void createToke() {
        HashMap<String, Object> map = new HashMap<String, Object>() {
            {
                put("id", 1234);
                put("password", "123123");
            }
        };

        String token = JWTUtil.createToken(map, "1234".getBytes());

        System.out.println(token);
    }

    @Test
    public void parseToken() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6IjEyMzEyMyIsImlkIjoxMjM0fQ.Gd8M507c2f4MqiNbBXRBGwvZOWNSMpzUmcD-ohF6Gos";
        JWT jwt = JWTUtil.parseToken(token);

        Object id = jwt.getPayload("id");
        Object password = jwt.getPayload("password");
        System.out.println(id);
        System.out.println(password);
    }

    @Test
    public void verify() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6IjEyMzEyMyIsImlkIjoxMjM0fQ.Gd8M507c2f4MqiNbBXRBGwvZOWNSMpzUmcD-ohF6Gos";

        boolean verify = JWTUtil.verify(token, "1234".getBytes());
        System.out.println(verify);
    }

}
