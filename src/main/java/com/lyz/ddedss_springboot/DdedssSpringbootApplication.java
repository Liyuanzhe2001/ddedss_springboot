package com.lyz.ddedss_springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.lyz.ddedss_springboot.mapper")
@SpringBootApplication
public class DdedssSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(DdedssSpringbootApplication.class, args);
    }

}
