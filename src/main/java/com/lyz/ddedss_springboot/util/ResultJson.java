package com.lyz.ddedss_springboot.util;


import java.io.Serializable;
import java.util.Map;

public class ResultJson<T> implements Serializable {

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 返回提示信息
     */
    private String msg = "";
    /**
     * 返回数据
     */
    private T data;
    /**
     * 总记录数
     */
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

    public ResultJson(Integer code, String msg, T data, Integer total) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.total = total;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
