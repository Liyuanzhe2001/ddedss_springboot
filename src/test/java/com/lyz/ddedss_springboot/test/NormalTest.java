package com.lyz.ddedss_springboot.test;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class NormalTest {

    @Test
    public void test() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String format = simpleDateFormat.format(System.currentTimeMillis());
        System.out.println(format);
    }

    @Test
    public void test2() {
        LocalDateTime startTime = LocalDateTime.of(2020, 9, 1, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 9, 1, 0, 0);
        System.out.println(startTime);
        System.out.println(endTime);
    }

    @Test
    public void test3() {
        Short s = 0;
        System.out.println(s == (short) 0);
    }

    /**
     * 时间格式化
     */
    @Test
    public void timeFormat() {
        Long timeRemaining = 1234567L;
        Integer day = Math.toIntExact(timeRemaining / 60 / 60 / 24);
        Integer hour = Math.toIntExact(timeRemaining / 60 / 60 % 24);
        System.out.println(day);
        System.out.println(hour);
    }

    @Test
    public void compareTime() {
        DateTime startTime = DateUtil.parse("Tue Feb 21 00:00:00 CST 2023");
        DateTime endTime = DateUtil.parse("Tue Feb 28 00:00:00 CST 2023");
        DateTime nowTime = DateUtil.parse(DateUtil.now(), "yyyy-MM-dd");

        System.out.println(startTime);
        System.out.println(endTime);
        System.out.println(nowTime);

        // A.compareTo(B)
        // 如果 A 的时间更早，则返回 -1
        // 如果 A 的时间更晚，则返回1
        // 如果相同，返回0
        System.out.println(startTime.compareTo(endTime));
        System.out.println(startTime.compareTo(nowTime));
        System.out.println(endTime.compareTo(nowTime));
        System.out.println(startTime.compareTo(startTime));

//        System.out.println(DateUtil.parse(startTime.toDateStr(), "yyyy-MM-dd").toString());
    }

    @Test
    public void getYeas(){
        Calendar calendar=Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        System.out.println(year);
    }
}
