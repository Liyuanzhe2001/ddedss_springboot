package com.lyz.ddedss_springboot.controller;

import com.lyz.ddedss_springboot.exception.BaseException;
import com.lyz.ddedss_springboot.util.ResultJson;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 控制层基类
 */
public class BaseController {

    /**
     * 操作成功的状态码
     */
    public static final int OK = 200;

    /**
     * 统一处理抛出的异常
     * 请求处理方法，这个方法的返回值就是需要传递给前端的数据
     * 自动将异常对象传递给此方法的参数列表上
     * 当项目中产生了异常，会被同意拦截到此方法中，这个方法此时就充当的是请求处理方法，方法的返回值直接给到前端
     */
    @ExceptionHandler({BaseException.class})
    public String handleException(Throwable e) {
        ResultJson<Void> result = new ResultJson<>();
        if (e instanceof Exception) {
            result.setCode(4000);
            result.setMsg("发生xxx异常");
        }
        return result.toString();
    }

}
