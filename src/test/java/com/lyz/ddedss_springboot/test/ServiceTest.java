package com.lyz.ddedss_springboot.test;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyz.ddedss_springboot.entity.Knowledge;
import com.lyz.ddedss_springboot.entity.Student;
import com.lyz.ddedss_springboot.mapper.KnowledgeMapper;
import com.lyz.ddedss_springboot.mapper.UserMapper;
import com.lyz.ddedss_springboot.service.KnowledgeService;
import com.lyz.ddedss_springboot.service.StudentService;
import com.lyz.ddedss_springboot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@SpringBootTest
public class ServiceTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private KnowledgeService knowledgeService;

    @Test
    public void testServiceMethods() {
        Student student = new Student()
                .setName("小名")
                .setSex((short) 0)
                .setClassId(1);
        boolean save = studentService.save(student);
        System.out.println(save);
        System.out.println(student);
    }

    @Test
    public void testUpdate() throws NoSuchAlgorithmException {
        userService.modifyPasswordByNumber(10, "123456");
    }

    @Test
    public void test_01() throws NoSuchAlgorithmException {
        boolean b = userService.judgeUserPassword(5, "1234");
        System.out.println(b);
    }

    @Test
    public void pageTest(){
        IPage<Knowledge> page = new Page(1, 5);
        knowledgeService.page(page);
        System.out.println(page.getTotal());

        page.getRecords().forEach(System.out::println);
//        knowledgeService.test();
    }

}
