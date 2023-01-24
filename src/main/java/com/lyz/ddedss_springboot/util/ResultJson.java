package com.lyz.ddedss_springboot.util;

import cn.hutool.json.JSONUtil;

import java.util.HashMap;
import java.util.Map;

public class ResultJson<T> {

    private int code;
    private String msg;
    private T data;
    private Map<String, Object> map;

    public ResultJson() {
        map = new HashMap<>();
    }

    public ResultJson(int code, String msg, T data) {
        map = new HashMap<>();
        map.put("code", code);
        map.put("msg", msg);
        map.put("data", data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return JSONUtil.toJsonStr(map);
    }

}
