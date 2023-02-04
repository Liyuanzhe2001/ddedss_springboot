package com.lyz.ddedss_springboot.test;

import cn.hutool.json.JSONUtil;
import com.lyz.ddedss_springboot.entity.User;
import com.lyz.ddedss_springboot.util.ResultJson;
import org.junit.jupiter.api.Test;

public class JsonTest {

    @Test
    public void test(){
        User userTest = new User();
        userTest.setNumber(123L);
        userTest.setPassword("123123");
        userTest.setEmail("aaa.com");
        String json = JSONUtil.toJsonStr(userTest);
        System.out.println(json);
    }

    @Test
    public void testResultJson(){
        User userTest = new User();
        userTest.setNumber(123L);
        userTest.setPassword("123123");
        userTest.setEmail("aaa.com");
        ResultJson<User> result = new ResultJson<>(-1, "正常", userTest);
        System.out.println(result);
    }

}
