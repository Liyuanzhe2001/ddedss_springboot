package com.lyz.ddedss_springboot.controller;

import cn.hutool.jwt.JWTUtil;
import com.lyz.ddedss_springboot.dto.req.ForgetPasswordReqDto;
import com.lyz.ddedss_springboot.dto.req.JudgePasswordReqDto;
import com.lyz.ddedss_springboot.dto.req.LoginReqDto;
import com.lyz.ddedss_springboot.dto.req.UpdatePasswordReqDto;
import com.lyz.ddedss_springboot.dto.resp.LoginRespDto;
import com.lyz.ddedss_springboot.entity.User;
import com.lyz.ddedss_springboot.exception.NumberOrPasswordException;
import com.lyz.ddedss_springboot.service.*;
import com.lyz.ddedss_springboot.util.FinalData;
import com.lyz.ddedss_springboot.util.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private ProfessionalService professionalService;

    /**
     * 登录
     */
    @PostMapping("/login")
    public ResultJson<LoginRespDto> login(LoginReqDto loginReqDto) {
        Integer number = loginReqDto.getNumber();
        String password = loginReqDto.getPassword();
        User user = userService.getUserByNumberAndPassword(number, password);

        if (user == null) {
            throw new NumberOrPasswordException();
        }

        Map<String, Object> tokenMap = new HashMap<String, Object>() {
            {
                put("number", number);
                put("password", password);
            }
        };

        Short identity = user.getIdentity();
        Integer roleId = user.getRoleId();

        String name = "";
        switch (identity) {
            case 0:
                name = studentService.getById(roleId).getName();
                break;
            case 1:
                name = teacherService.getById(roleId).getName();
                break;
            case 2:
                name = professionalService.getById(roleId).getName();
                break;
            case 3:
                name = administratorService.getById(roleId).getName();
                break;
        }

        String token = JWTUtil.createToken(tokenMap, FinalData.TOKEN_KEY.getBytes());

        LoginRespDto respDto = new LoginRespDto().setName(name).setToken(token);

        return new ResultJson<>(OK, "登陆成功", respDto);
    }

    /**
     * 发送验证码
     */
    @PostMapping("/send_verification_code/{email}")
    public ResultJson<Void> sendVerificationCode(@PathVariable("email") String email) {
        return null;
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public ResultJson<Void> register() {
        return null;
    }

    /**
     * 忘记密码
     */
    @PostMapping("/forget_password")
    public ResultJson<Void> forgetPassword(ForgetPasswordReqDto reqDto) {
        return null;
    }

    /**
     * 判断密码是否正确
     */
    @PostMapping("/judge_password")
    public ResultJson<Void> judgePassword(JudgePasswordReqDto reqDto) {
        return null;
    }

    /**
     * 修改密码
     */
    @PutMapping("/update_password")
    public ResultJson<Void> updatePassword(UpdatePasswordReqDto reqDto) {
        return null;
    }

}
