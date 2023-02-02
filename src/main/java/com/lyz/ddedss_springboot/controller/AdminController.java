package com.lyz.ddedss_springboot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyz.ddedss_springboot.dto.req.QueryAllUserListReqDto;
import com.lyz.ddedss_springboot.dto.resp.QueryAllUserListRespDto;
import com.lyz.ddedss_springboot.entity.User;
import com.lyz.ddedss_springboot.exception.FailedDeleteUserException;
import com.lyz.ddedss_springboot.exception.FailedModifyPasswordException;
import com.lyz.ddedss_springboot.service.ProfessionalService;
import com.lyz.ddedss_springboot.service.StudentService;
import com.lyz.ddedss_springboot.service.TeacherService;
import com.lyz.ddedss_springboot.service.UserService;
import com.lyz.ddedss_springboot.util.PasswordUtil;
import com.lyz.ddedss_springboot.util.ResultJson;
import com.lyz.ddedss_springboot.vo.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

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
    public ResultJson<Void> InitPassword(@PathVariable("userId") Integer userId) throws NoSuchAlgorithmException {
        // 获取用户
        User user = userService.getById(userId);
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

}
