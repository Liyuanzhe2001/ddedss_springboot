package com.lyz.ddedss_springboot.test;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lyz.ddedss_springboot.entity.UserTest;
import com.lyz.ddedss_springboot.mapper.UserTestMapper;
import com.lyz.ddedss_springboot.service.UserTestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DatabaseTest {

    @Autowired
    private UserTestMapper userTestMapper;

    @Autowired
    private UserTestService userTestService;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<UserTest> userList = userTestMapper.selectList(null);

        System.out.println(userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    public void testLambdaQuery(){
        LambdaQueryWrapper<UserTest> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        lambdaQueryWrapper = lambdaQueryWrapper.select(UserTest::getId, UserTest::getName);
        List<UserTest> userTests = userTestMapper.selectList(lambdaQueryWrapper);
        userTests.forEach(System.out::println);
    }

    @Test
    public void testMapper(){
    }

    @Test
    public void testIService(){
    }

    @Test
    public void testModel(){
        UserTest userTest = new UserTest();
    }

}
