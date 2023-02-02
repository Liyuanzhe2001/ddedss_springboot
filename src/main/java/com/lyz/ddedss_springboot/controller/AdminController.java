package com.lyz.ddedss_springboot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyz.ddedss_springboot.dto.req.AddProfessionalReqDto;
import com.lyz.ddedss_springboot.dto.req.AddTeacherReqDto;
import com.lyz.ddedss_springboot.dto.req.QueryAllUserListReqDto;
import com.lyz.ddedss_springboot.dto.resp.QueryAllUserListRespDto;
import com.lyz.ddedss_springboot.entity.*;
import com.lyz.ddedss_springboot.exception.*;
import com.lyz.ddedss_springboot.service.*;
import com.lyz.ddedss_springboot.util.PasswordUtil;
import com.lyz.ddedss_springboot.util.ResultJson;
import com.lyz.ddedss_springboot.vo.Password;
import com.lyz.ddedss_springboot.vo.SubjectLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ProfessionalService professionalService;

    @Autowired
    private TeacherSubjectService teacherSubjectService;

    @Autowired
    private SubjectService subjectService;

    /**
     * 查询用户列表（除管理员）
     */
    @GetMapping("/queryAllUserList")
    public ResultJson<List<QueryAllUserListRespDto>> QueryAllUserList(QueryAllUserListReqDto reqDto) {
        // 模糊查询number
        Page<User> page = new Page<>(reqDto.getCurrentPage(), reqDto.getPageSize());
        page = userService.queryAllUserLike(reqDto.getSearchInput(), page);

        List<QueryAllUserListRespDto> respDtos = new ArrayList<>();
        for (User user : page.getRecords()) {
            QueryAllUserListRespDto respDto = new QueryAllUserListRespDto();

            // 获取用户名
            String name = "";
            switch (user.getIdentity()) {
                case 0:
                    name = studentService.getById(user.getRoleId()).getName();
                    break;
                case 1:
                    name = teacherService.getById(user.getRoleId()).getName();
                    break;
                case 2:
                    name = professionalService.getById(user.getRoleId()).getName();
                    break;
            }

            respDto.setUserId(user.getId())
                    .setUserNumber(user.getNumber())
                    .setUserName(name)
                    .setUserEmail(user.getEmail())
                    .setUserIdentity(user.getIdentity());

            respDtos.add(respDto);
        }
        return new ResultJson<>(OK, "查询成功", respDtos, page.getTotal());
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/deleteUser/{userId}")
    public ResultJson<Void> DeleteUser(@PathVariable("userId") Integer userId) {
        // 根据角色id，删除角色
        User user = userService.getById(userId);
        boolean flag = false;
        // 判断身份
        switch (user.getIdentity()) {
            case 0:
                flag = studentService.removeById(user.getRoleId());
                break;
            case 1:
                flag = teacherService.removeById(user.getRoleId());
                break;
            case 2:
                flag = professionalService.removeById(user.getRoleId());
                break;
        }
        if (!flag) {
            throw new FailedDeleteUserException("用户删除失败");
        }

        flag = userService.removeById(userId);
        if (!flag) {
            throw new FailedDeleteUserException("用户删除失败");
        }
        return new ResultJson<>(OK, "删除成功");
    }

    /**
     * 重置用户密码
     */
    @PutMapping("/initPassword/{userId}")
    public ResultJson<Void> initPassword(@PathVariable("userId") Integer userId) throws NoSuchAlgorithmException {
        // 获取用户
        User user = userService.getNormalUser(userId);

        // 获取盐值
        String salt = user.getSalt();
        // 密码加密
        String password = "12345678";
        password = PasswordUtil.encrypt(PasswordUtil.encrypt(password), salt);
        // 修改密码
        user.setPassword(password);
        boolean flag = userService.updateById(user);
        if (!flag) {
            throw new FailedModifyPasswordException("密码修改失败");
        }
        return new ResultJson<>(OK, "修改成功");
    }

    @PostMapping("/addTeacher")
    public ResultJson<Void> addTeacher(@RequestBody AddTeacherReqDto reqDto) throws NoSuchAlgorithmException {
        // 创建教师对象
        Teacher teacher = new Teacher()
                .setName(reqDto.getName())
                .setSex(reqDto.getSex());
        if (reqDto.getSubjectLevelList().size() == 0) {
            teacher.setIdentity((short) 0);
        } else {
            teacher.setIdentity((short) 1);
        }
        boolean flag = teacherService.save(teacher);
        if (!flag) {
            throw new FailedCreateTeacherException("创建教师失败");
        }

        // 创建用户
        User user = new User();
        // 盐值
        String salt = UUID.randomUUID().toString().replaceAll("-", "");
        // 密码加密
        String password = PasswordUtil.encrypt(PasswordUtil.encrypt("12345678"), salt);
        user.setNumber(reqDto.getNumber())
                .setPassword(password)
                .setSalt(salt)
                .setEmail(reqDto.getEmail())
                .setIdentity((short) 1)
                .setRoleId(teacher.getId());
        flag = userService.save(user);
        if (!flag) {
            throw new FailedCreateUserException("用户创建失败");
        }

        // 创建teacher_subject
        List<TeacherSubject> teacherSubjectList = new ArrayList<>();
        for (SubjectLevel subjectLevel : reqDto.getSubjectLevelList()) {
            TeacherSubject teacherSubject = new TeacherSubject();
            // 根据科目名称查找科目id
            Integer subjectId = subjectService.getId(subjectLevel.getSubjectName());
            // 如果科目不存在 则创建科目
            if (subjectId == -1) {
                Subject subject = new Subject()
                        .setName(subjectLevel.getSubjectName());
                flag = subjectService.save(subject);
                if (!flag) {
                    throw new FailedCreateSubjectException("科目创建失败");
                }
                subjectId = subject.getId();
            }
            teacherSubject
                    .setTeacherId(teacher.getId())
                    .setSubjectId(subjectId)
                    .setLevel(subjectLevel.getLevel());
            teacherSubjectList.add(teacherSubject);
        }

        flag = teacherSubjectService.saveOrUpdateBatch(teacherSubjectList);
        if (!flag) {
            throw new FailedCreateTeacherSubjectException("教师科目创建失败");
        }
        return new ResultJson<>(OK, "创建成功");
    }

    @PostMapping("/addProfessional")
    public ResultJson<Void> addProfessional(AddProfessionalReqDto reqDto) throws NoSuchAlgorithmException {
        // 创建教育专家
        Professional professional = new Professional()
                .setName(reqDto.getName());
        boolean flag = professionalService.save(professional);
        if (!flag) {
            throw new FailedCreateProfessionalException("教育专家创建失败");
        }

        // 创建用户
        User user = new User();
        // 盐值
        String salt = UUID.randomUUID().toString().replaceAll("-", "");
        // 密码加密
        String password = "12345678";
        password = PasswordUtil.encrypt(PasswordUtil.encrypt(password), salt);
        user.setNumber(reqDto.getNumber())
                .setPassword(password)
                .setSalt(salt)
                .setEmail(reqDto.getEmail())
                .setIdentity((short) 2)
                .setRoleId(professional.getId());
        flag = userService.save(user);
        if(!flag){
            throw new FailedCreateUserException("用户创建失败");
        }
        return new ResultJson<>(OK,"创建成功");
    }

}
