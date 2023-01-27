package com.lyz.ddedss_springboot.controller;

import cn.hutool.jwt.JWTUtil;
import com.lyz.ddedss_springboot.dto.req.*;
import com.lyz.ddedss_springboot.dto.resp.LoginRespDto;
import com.lyz.ddedss_springboot.entity.Student;
import com.lyz.ddedss_springboot.entity.User;
import com.lyz.ddedss_springboot.exception.*;
import com.lyz.ddedss_springboot.service.*;
import com.lyz.ddedss_springboot.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private ClassService classService;

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private StringRedisTemplate redis;

    /**
     * 登录
     */
    @PostMapping("/login")
    public ResultJson<LoginRespDto> login(LoginReqDto loginReqDto) throws NoSuchAlgorithmException {
        Integer number = loginReqDto.getNumber();
        String password = loginReqDto.getPassword();

        User user = userService.getUserByNumber(number);

        if (user == null) {
            throw new NumberOrPasswordException();
        }

        boolean check = PasswordUtil.check(user.getPassword(), password, user.getSalt());

        if (!check) {
            throw new NumberOrPasswordException();
        }

        // create token
        // number password
        Map<String, Object> tokenMap = new HashMap<String, Object>() {
            {
                put("number", number);
                put("password", user.getPassword());
            }
        };
        String token = JWTUtil.createToken(tokenMap, FinalData.TOKEN_KEY.getBytes());

        // judge identity
        // 0 - student
        // 1 - teacher
        // 2 - professional
        // 3 - administrator
        Short identity = user.getIdentity();
        Integer roleId = user.getRoleId();

        String username = "";
        switch (identity) {
            case 0:
                username = studentService.getById(roleId).getName();
                break;
            case 1:
                username = teacherService.getById(roleId).getName();
                break;
            case 2:
                username = professionalService.getById(roleId).getName();
                break;
            case 3:
                username = administratorService.getById(roleId).getName();
                break;
        }

        // put roleId username into session
        setRoleIdAndUsername(roleId, username);

        LoginRespDto respDto = new LoginRespDto()
                .setUsername(username)
                .setIdentity(identity)
                .setToken(token);

        return new ResultJson<>(OK, "登陆成功", respDto);
    }

    /**
     * 发送验证码
     */
    @PostMapping("/send_verification_code/{email}")
    public ResultJson<Void> sendVerificationCode(@PathVariable("email") String email) {
        String random = RandomUtil.createRandom(6);

        redis.opsForValue().set(email, random, 60, TimeUnit.SECONDS);

        try {
            emailUtil.sendSimpleMail(email, random);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return new ResultJson<>(OK, "发送成功");
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public ResultJson<Void> register(RegisterReqDto reqDto) throws NoSuchAlgorithmException {

        // 邮箱验证码验证
        String code = redis.opsForValue().get(reqDto.getEmail());
        if (!reqDto.getCode().equals(code)) {
            throw new ErrorVerificationCodeException();
        }

        // 验证邀请码是否正确 通过验证码获取班级
        String className = redis.opsForValue().get(reqDto.getInvite());

        // 查询班级id
        Integer classId = classService.getIdByName(className);

        // 创建学生
        Student student = new Student()
                .setName(reqDto.getUsername())
                .setSex(reqDto.getSex())
                .setClassId(classId);
        boolean save = studentService.save(student);
        if (!save) {
            throw new CreateStudentException();
        }

        // 1. 密码加密
        String salt = UUID.randomUUID().toString().replaceAll("-", "");
        String password = PasswordUtil.encrypt(reqDto.getPassword());
        password = PasswordUtil.encrypt(password, salt);
        // 2. 创建用户
        User user = new User()
                .setNumber(reqDto.getNumber())
                .setPassword(password)
                .setSalt(salt)
                .setEmail(reqDto.getEmail())
                .setRoleId(student.getId())
                .setIdentity((short) 0);
        save = userService.save(user);
        if (!save) {
            throw new CreateUserException();
        }

        return new ResultJson<>(OK, "注册成功");
    }

    /**
     * 忘记密码
     */
    @PostMapping("/forget_password")
    public ResultJson<Void> forgetPassword(ForgetPasswordReqDto reqDto) throws NoSuchAlgorithmException {
        // 判断邮箱与学号/工号是否对应
        boolean flag = userService.judgeUserByNumberAndEmail(reqDto.getNumber(), reqDto.getEmail());
        if (!flag) {
            throw new ErrorNumberOrEmailException();
        }

        // 判断验证码是否正确
        String code = redis.opsForValue().get(reqDto.getEmail());
        if (!reqDto.getCode().equals(code)) {
            throw new ErrorVerificationCodeException();
        }

        // 修改密码
        flag = userService.modifyPasswordByNumber(reqDto.getNumber(), reqDto.getPassword());
        if (!flag) {
            throw new FailedModifyPasswordException();
        }

        return new ResultJson<>(OK, "修改成功");
    }

    /**
     * 判断密码是否正确
     */
    @PostMapping("/judge_password")
    public ResultJson<Void> judgePassword(JudgePasswordReqDto reqDto) throws NoSuchAlgorithmException {
        Integer roleId = getRoleId();
        boolean flag = userService.judgeUserPassword(roleId, reqDto.getPassword());
        if (!flag) {
            throw new ErrorPasswordException();
        }
        return new ResultJson<>(OK, "密码正确");
    }

    /**
     * 修改密码
     */
    @PutMapping("/update_password")
    public ResultJson<Void> updatePassword(UpdatePasswordReqDto reqDto) throws NoSuchAlgorithmException {
        setRoleId(5);
        Integer roleId = getRoleId();
        boolean flag = userService.modifyPasswordByRoleId(roleId, reqDto.getPassword());
        if (!flag) {
            throw new FailedModifyPasswordException();
        }
        return new ResultJson<>(OK, "修改成功");
    }

}
