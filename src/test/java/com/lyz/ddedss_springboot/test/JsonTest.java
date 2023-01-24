package com.lyz.ddedss_springboot.test;

import cn.hutool.json.JSONUtil;
import com.lyz.ddedss_springboot.entity.UserTest;
import com.lyz.ddedss_springboot.util.ResultJson;
import org.junit.jupiter.api.Test;

import javax.xml.transform.Result;

public class JsonTest {

    @Test
    public void test(){
        UserTest userTest = new UserTest();
        userTest.setId(1L);
        userTest.setName("zhangsan");
        userTest.setAge(10);
        userTest.setEmail("aaa.com");
        String json = JSONUtil.toJsonStr(userTest);
        System.out.println(json);
    }

    @Test
    public void testResultJson(){
        UserTest userTest = new UserTest();
        userTest.setId(1L);
        userTest.setName("zhangsan");
        userTest.setAge(10);
        userTest.setEmail("aaa.com");
        ResultJson<UserTest> result = new ResultJson<>(-1, "正常", userTest);
        System.out.println(result);
    }

}
