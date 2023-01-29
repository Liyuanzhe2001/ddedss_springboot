package com.lyz.ddedss_springboot.test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lyz.ddedss_springboot.entity.Subject;
import com.lyz.ddedss_springboot.entity.User;
import com.lyz.ddedss_springboot.mapper.ResultMapper;
import com.lyz.ddedss_springboot.mapper.SubjectMapper;
import com.lyz.ddedss_springboot.mapper.UserMapper;
import org.apache.ibatis.mapping.ResultMap;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SubjectMapper subjectMapper;


    @Autowired
    private ResultMapper resultMapper;

    @Test
    public void testMapperXml() {
//        List<User> all = userMapper.getAll();
//        all.forEach(System.out::println);
    }

    @Test
    public void testResultMapper() {
        List<Integer> ids = new ArrayList();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        ids.add(4);
        ids.add(5);
        ids.add(7);
        ids.add(10);
        Integer sumScore = resultMapper.getSumScore(ids,1);
        System.out.println(sumScore);
    }

    @Test
    public void testSelectNo(){
        LambdaQueryWrapper<Subject> lambdaQueryWrapper = new LambdaQueryWrapper<Subject>()
                .select(Subject::getId)
                .eq(Subject::getName, "abcde");
        Subject subject = subjectMapper.selectOne(lambdaQueryWrapper);
        System.out.println(subject.getId());
    }

}
