package com.lyz.ddedss_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class DdedssSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(DdedssSpringbootApplication.class, args);
    }

    /**
     * 每日0:00检查是否有课程评价需要开始
     */
    @Scheduled(cron = "0 0 0 ? * *")
    public void searchCourseEvaluation() {
        System.out.println("定时任务");
    }
}
