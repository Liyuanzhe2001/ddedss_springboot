package com.lyz.ddedss_springboot.util;

import java.util.UUID;

public class FinalData {

    public static final String TOKEN_KEY = UUID.randomUUID().toString().replaceAll("-", "");

}
