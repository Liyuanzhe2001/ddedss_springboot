package com.lyz.ddedss_springboot.util;

import org.springframework.stereotype.Component;

import java.util.Random;

public class RandomUtil {

    public static String createRandom(int length) {
        String rel = "";
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < length; i++) {
            rel += random.nextInt(10);
        }
        return rel;
    }

}
