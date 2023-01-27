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
        if (e instanceof NoLoginException) {
            result.setMsg("用户未登录");
        } else if (e instanceof NumberOrPasswordException) {
            result.setMsg("账号或密码错误");
        } else if (e instanceof FailedSendEmailException) {
            result.setMsg("发送失败");
        } else if (e instanceof ClassNotFoundException) {
            result.setMsg("未找到该班级");
        } else if (e instanceof CreateStudentException) {
            result.setMsg("创建学生失败");
        } else if (e instanceof CreateUserException) {
            result.setMsg("创建用户失败");
        } else if (e instanceof ErrorVerificationCodeException) {
            result.setMsg("验证码错误");
        } else if (e instanceof ErrorNumberOrEmailException) {
            result.setMsg("学号/工号或邮箱错误");
        } else if (e instanceof FailedModifyPasswordException) {
            result.setMsg("修改密码失败");
        } else if (e instanceof ErrorPasswordException) {
            result.setMsg("密码错误");
        } else {
            result.setMsg("发生异常:" + e);
        }
        return result;
    }

    private void setSession(String key, Object value) {
        session.setAttribute(key, value);
    }

    public void setRoleIdAndUsername(Integer roleId, String username) {
        setSession("roleId", roleId);
        setSession("username", username);
    }

    public void setRoleId(Integer roleId) {
        setSession("roleId", roleId);
    }

    public void setUsername(String username) {
        setSession("username", username);
    }

    public Integer getRoleId() {
        return (Integer) session.getAttribute("roleId");
    }

    public String getUsername() {
        return (String) session.getAttribute("username");
    }

}
