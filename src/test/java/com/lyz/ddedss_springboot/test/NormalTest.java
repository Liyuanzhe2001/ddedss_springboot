package com.lyz.ddedss_springboot.test;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class NormalTest {

    @Test
    public void test(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String format = simpleDateFormat.format(System.currentTimeMillis());
        System.out.println(format);
    }

    @Test
    public void test2(){
        LocalDateTime startTime = LocalDateTime.of(2020, 9, 1, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 9, 1, 0, 0);
        System.out.println(startTime);
        System.out.println(endTime);
    }

    @Test
    public void test3(){
        Short s = 0;
        System.out.println(s == (short)0);
    }

    /**
     * 时间格式化
     */
    @Test
    public void timeFormat(){
        Long timeRemaining = 1234567L;
        Integer day = Math.toIntExact(timeRemaining / 60 / 60 / 24);
        Integer hour = Math.toIntExact(timeRemaining / 60 / 60 % 24);
        System.out.println(day);
        System.out.println(hour);
    }
}
