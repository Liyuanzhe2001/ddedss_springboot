package com.lyz.ddedss_springboot.test;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;

public class NormalTest {

    @Test
    public void test(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String format = simpleDateFormat.format(System.currentTimeMillis());
        System.out.println(format);
    }

}
