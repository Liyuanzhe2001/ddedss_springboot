package com.lyz.ddedss_springboot.test;

import com.lyz.ddedss_springboot.entity.User;
import com.lyz.ddedss_springboot.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testMapperXml(){
//        List<User> all = userMapper.getAll();
//        all.forEach(System.out::println);
    }

}
