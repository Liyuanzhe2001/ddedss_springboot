package com.lyz.ddedss_springboot.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    /**
     * 登录
     *
     * @param number   学号/工号
     * @param password 密码
     * @return {
     * code - 状态码
     * msg - 信息
     * username - 用户名
     * token - token
     * }
     */
    @PostMapping("/login")
    public String login(Integer number, String password) {
        return null;
    }

    /**
     * 发送验证码
     *
     * @param email 邮箱
     * @return {
     * code - 状态码
     * msg - 信息
     * }
     */
    @PostMapping("/send_verification_code")
    public String sendVerificationCode(String email) {
        return null;
    }

    /**
     * 注册
     *
     * @param invite   邀请码
     * @param number   学号
     * @param username 姓名
     * @param password 密码
     * @param email    邮箱
     * @param code     验证码
     * @return {
     * code - 状态码
     * msg - 信息
     * }
     */
    @PostMapping("/register")
    public String register(String invite, Integer number, String username, String password, String email, String code) {
        return null;
    }

    /**
     * 忘记密码
     *
     * @param number   学号
     * @param email    邮箱
     * @param code     验证码
     * @param password 新密码
     * @return {
     * code - 状态码
     * msg - 信息
     * }
     */
    @PostMapping("/forget_password")
    public String forgetPassword(Integer number, String email, String code, String password) {
        return null;
    }

    /**
     * 判断密码是否正确
     * <p>
     * id: session 用户id
     *
     * @param password 旧密码
     * @return {
     * code - 状态码
     * msg - 信息
     * }
     */
    @PostMapping("/judge_password")
    public String judgePassword(String password) {
        return null;
    }

    /**
     * 修改密码
     *
     * @param password 新密码
     * @return {
     * code - 状态码
     * msg - 信息
     * }
     */
    @PutMapping("/update_password")
    public String updatePassword(String password) {
        return null;
    }

}
