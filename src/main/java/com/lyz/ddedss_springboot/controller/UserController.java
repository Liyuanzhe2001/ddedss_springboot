package com.lyz.ddedss_springboot.controller;

import cn.hutool.jwt.JWTUtil;
import com.lyz.ddedss_springboot.dto.req.*;
import com.lyz.ddedss_springboot.dto.resp.AdminLoginRespDto;
import com.lyz.ddedss_springboot.dto.resp.LoginRespDto;
import com.lyz.ddedss_springboot.entity.Student;
import com.lyz.ddedss_springboot.entity.User;
import com.lyz.ddedss_springboot.exception.*;
import com.lyz.ddedss_springboot.service.*;
import com.lyz.ddedss_springboot.util.*;
import com.lyz.ddedss_springboot.vo.Password;
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
    private ProfessionalService professionalService;

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

        // 根据学号/工号获取用户信息
        User user = userService.getUserByNumber(number);
        if (user == null) {
            throw new ErrorNumberOrPasswordException("学号/工号或密码错误");
        }

        // 检查密码
        boolean check = PasswordUtil.check(user.getPassword(), password, user.getSalt());
        if (!check) {
            throw new ErrorNumberOrPasswordException("学号/工号或密码错误");
        }

        // 创建token
        Map<String, Object> tokenMap = new HashMap<String, Object>() {
            {
                put("number", number);
                put("password", user.getPassword());
            }
        };
        String token = JWTUtil.createToken(tokenMap, FinalData.TOKEN_KEY.getBytes());

        // 判断用户身份
        // 0 - student
        // 1 - teacher
        // 2 - professional
        // 3 - administrator
        Short identity = user.getIdentity();
        Integer roleId = user.getRoleId();

        String username = "管理员";
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
        }

        // 将roleId和用户名放入session
        setRoleIdAndUsername(roleId, username);

        // 创建返回结果对象
        LoginRespDto respDto = new LoginRespDto()
                .setUsername(username)
                .setIdentity(identity)
                .setToken(token);

        // 将token放入redis
        redis.opsForValue().set(String.valueOf(roleId), token);

        return new ResultJson<>(OK, "登陆成功", respDto);
    }

    /**
     * 发送验证码
     */
    @PostMapping("/sendVerificationCode/{email}")
    public ResultJson<Void> sendVerificationCode(@PathVariable("email") String email) {
        // 创建6位随机验证码
        String random = RandomUtil.createRandom(6);
        redis.opsForValue().set(email, random, 60, TimeUnit.SECONDS);

        // 邮箱发送验证码至用户邮箱
        try {
            emailUtil.sendSimpleMail(email, random);
        } catch (MessagingException e) {
            throw new FailedSendMessageException("验证码发送失败");
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
        if (!reqDto.getCode().equals("123123")) {
            throw new ErrorVerificationCodeException("验证码错误");
        }

        // 验证邀请码是否正确 通过验证码获取班级
        String classId = redis.opsForValue().get(reqDto.getInvite());
        // 邀请码不存在
        if (classId == null) {
            throw new InvitationCodeNotFoundException("未找到该邀请码");
        }

        // 创建学生
        Student student = new Student()
                .setName(reqDto.getUsername())
                .setSex(reqDto.getSex())
                .setClassId(Integer.parseInt(classId));

        // 保存注册信息
        boolean save = studentService.save(student);
        if (!save) {
            throw new FailedCreateStudentException("注册失败");
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
            throw new FailedCreateUserException("注册失败");
        }

        return new ResultJson<>(OK, "注册成功");
    }

    /**
     * 忘记密码
     */
    @PostMapping("/forgetPassword")
    public ResultJson<Void> forgetPassword(ForgetPasswordReqDto reqDto) throws NoSuchAlgorithmException {

        // 判断邮箱与学号/工号是否对应
        boolean flag = userService.judgeUserByNumberAndEmail(reqDto.getNumber(), reqDto.getEmail());
        if (!flag) {
            throw new ErrorNumberOrEmailException("学号/工号或邮箱错误");
        }

        // 判断验证码是否正确
        String code = redis.opsForValue().get(reqDto.getEmail());
        if (!reqDto.getCode().equals(code)) {
            throw new ErrorVerificationCodeException("验证码错误");
        }

        // 修改密码
        flag = userService.modifyPasswordByNumber(reqDto.getNumber(), reqDto.getPassword());
        if (!flag) {
            throw new FailedModifyPasswordException("修改密码失败");
        }

        return new ResultJson<>(OK, "修改成功");
    }

    /**
     * 判断密码是否正确
     */
    @PostMapping("/judgePassword")
    public ResultJson<Void> judgePassword(Password reqDto) throws NoSuchAlgorithmException {
        Integer roleId = getRoleId();
        boolean flag = userService.judgeUserPassword(roleId, reqDto.getPassword());
        if (!flag) {
            throw new ErrorPasswordException("密码错误");
        }
        return new ResultJson<>(OK, "密码正确");
    }

    /**
     * 修改密码
     */
    @PutMapping("/updatePassword")
    public ResultJson<Void> updatePassword(Password reqDto) throws NoSuchAlgorithmException {
        Integer roleId = getRoleId();
        boolean flag = userService.modifyPasswordByRoleId(roleId, reqDto.getPassword());
        if (!flag) {
            throw new FailedModifyPasswordException("修改密码失败");
        }
        return new ResultJson<>(OK, "修改成功");
    }

    /**
     * 管理员登录
     */
    @PostMapping("/adminLogin")
    public ResultJson<AdminLoginRespDto> AdminLogin(AdminLoginReqDto reqDto) throws NoSuchAlgorithmException {
        Integer number = reqDto.getNumber();
        String password = reqDto.getPassword();

        User user = userService.getAdminByNumber(number);
        String salt = user.getSalt();
        String correctPassword = user.getPassword();

        boolean check = PasswordUtil.check(correctPassword, password, salt);
        if (!check) {
            throw new ErrorNumberOrPasswordException("账号或密码错误");
        }

        // 创建token
        Map<String, Object> tokenMap = new HashMap<String, Object>() {
            {
                put("number", number);
                put("password", user.getPassword());
            }
        };
        String token = JWTUtil.createToken(tokenMap, FinalData.TOKEN_KEY.getBytes());

        Integer roleId = user.getRoleId();

        // roleId 放入session
        setRoleId(roleId);

        // 将token放入redis
        redis.opsForValue().set(String.valueOf(roleId), token);

        AdminLoginRespDto adminLoginRespDto = new AdminLoginRespDto(token);
        return new ResultJson<>(OK, "登陆成功", adminLoginRespDto);
    }

}
