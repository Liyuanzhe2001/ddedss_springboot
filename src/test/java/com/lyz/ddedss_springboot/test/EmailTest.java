package com.lyz.ddedss_springboot.test;

import com.lyz.ddedss_springboot.util.EmailUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;

@SpringBootTest
public class EmailTest {

    @Autowired
    private EmailUtil emailUtil;

    @Test
    public void test() throws MessagingException {
        emailUtil.sendSimpleMail("619356147@qq.com", "123456");
    }


}
