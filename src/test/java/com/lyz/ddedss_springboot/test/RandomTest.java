package com.lyz.ddedss_springboot.test;

import com.lyz.ddedss_springboot.util.RandomUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RandomTest {

    @Test
    void test(){
        System.out.println(RandomUtil.createRandom(6));
    }

}
