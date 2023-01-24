package com.lyz.ddedss_springboot.test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lyz.ddedss_springboot.entity.User;
import com.lyz.ddedss_springboot.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DatabaseTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);

        System.out.println(userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    public void testLambdaQuery() {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper = lambdaQueryWrapper.select(User::getNumber, User::getPassword);
        List<User> userTests = userMapper.selectList(lambdaQueryWrapper);
        userTests.forEach(System.out::println);
    }

    @Test
    public void testMapper() {
    }

    @Test
    public void testIService() {
    }

    @Test
    public void testModel() {
    }

}
