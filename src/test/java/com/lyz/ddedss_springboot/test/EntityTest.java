package com.lyz.ddedss_springboot.test;

import com.lyz.ddedss_springboot.entity.User;
import com.lyz.ddedss_springboot.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EntityTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsertUser() {
        User user = new User();
        //1,123,"123123","aabbcc","123@qq.com",0,1
        user
                .setNumber(123L)
                .setPassword("123123")
                .setEmail("aaa@qq.com")
                .setIdentity((short) 0)
                .setRoleId(0)
                .setSalt("aaa");
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }

}
