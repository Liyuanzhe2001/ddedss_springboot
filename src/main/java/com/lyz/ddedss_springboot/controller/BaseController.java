package com.lyz.ddedss_springboot.controller;

import com.lyz.ddedss_springboot.exception.*;
import com.lyz.ddedss_springboot.exception.ClassNotFoundException;
import com.lyz.ddedss_springboot.util.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * 控制层基类
 */
public class BaseController {

    /**
     * 操作成功的状态码
     */
    public static final int OK = 200;

    /**
     * 操作失败的状态码
     */
    public static final int ERR = -1;

    /**
     * session
     * <br>
     * - roleId  -  用户id（学生/教师/教育专家/管理员id）
     * <br>
     * - username  -  用户名（学生/教师/教育专家/管理员姓名）
     */
    @Autowired
    public HttpSession session;

    /**
     * 统一处理抛出的异常
     * 请求处理方法，这个方法的返回值就是需要传递给前端的数据
     * 自动将异常对象传递给此方法的参数列表上
     * 当项目中产生了异常，会被同意拦截到此方法中，这个方法此时就充当的是请求处理方法，方法的返回值直接给到前端
     */
    @ExceptionHandler({BaseException.class})
    public ResultJson<Void> handleException(Throwable e) {
        ResultJson<Void> result = new ResultJson<>(ERR, "");
        if (e instanceof BaseException) {
            result.setMsg(e.getMessage());
        }
        return result;
    }

    private void setSession(String key, Object value) {
        session.setAttribute(key, value);
    }

    public void setIdAndUsername(Integer userId, Integer roleId, String username) {
        setUserId(userId);
        setRoleId(roleId);
        setUsername(username);
    }

    public void setUserId(Integer userId) {
        setSession("userId", userId);
    }

    public void setRoleId(Integer roleId) {
        setSession("roleId", roleId);
    }

    public void setUsername(String username) {
        setSession("username", username);
    }

    public Integer getUserId() {
        return (Integer) session.getAttribute("userId");
    }

    public Integer getRoleId() {
        return (Integer) session.getAttribute("roleId");
    }

    public String getUsername() {
        return (String) session.getAttribute("username");
    }

}
