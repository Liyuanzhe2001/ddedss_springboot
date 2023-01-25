package com.lyz.ddedss_springboot.util;

import cn.hutool.json.JSONUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ResultJson<T> implements Serializable {

    private Integer code;
    private String msg = "";
    private T data;
    private Integer total = 0;

    public ResultJson(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultJson(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
