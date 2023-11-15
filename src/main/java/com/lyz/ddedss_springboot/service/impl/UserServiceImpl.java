package com.lyz.ddedss_springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyz.ddedss_springboot.entity.Student;
import com.lyz.ddedss_springboot.entity.User;
import com.lyz.ddedss_springboot.exception.ErrorNumberOrPasswordException;
import com.lyz.ddedss_springboot.exception.UserNotFoundExecption;
import com.lyz.ddedss_springboot.mapper.UserMapper;
import com.lyz.ddedss_springboot.service.UserService;
import com.lyz.ddedss_springboot.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByNumber(String number) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<User>()
                .eq(User::getNumber, number);

        return userMapper.selectOne(lambdaQueryWrapper);
    }

    @Override
    public boolean judgeUserByNumberAndEmail(Integer number, String email) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<User>()
                .eq(User::getNumber, number)
                .eq(User::getEmail, email);
        User user = userMapper.selectOne(lambdaQueryWrapper);
        return user != null;
    }

    @Override
    public boolean modifyPasswordByNumber(Integer number, String password) throws NoSuchAlgorithmException {
        // 密码加密
        // 1. 获取salt
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<User>()
                .select(User::getSalt)
                .eq(User::getNumber, number);
        User user = userMapper.selectOne(lambdaQueryWrapper);
        String salt = user.getSalt();
        // 2. 加密
        String encrypt = PasswordUtil.encrypt(password);
        password = PasswordUtil.encrypt(encrypt, salt);

        // 修改密码
        LambdaUpdateWrapper<User> set = new LambdaUpdateWrapper<User>()
                .eq(User::getNumber, number)
                .set(User::getPassword, password);

        return update(set);
    }

    @Override
    public boolean judgeUserPassword(Integer userId, String password) throws NoSuchAlgorithmException {
        // 通过roleId 获取 盐值
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<User>()
                .select(User::getSalt, User::getPassword)
                .eq(User::getId, userId);
        User user = getOne(lambdaQueryWrapper);

        return PasswordUtil.check(user.getPassword(), password, user.getSalt());
    }

    @Override
    public boolean modifyPasswordByRoleId(Integer userId, String password) throws NoSuchAlgorithmException {
        // 获取salt
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<User>()
                .select(User::getSalt)
                .eq(User::getId, userId);
        User user = getOne(lambdaQueryWrapper);

        // 加密
        String encrypt = PasswordUtil.encrypt(password);
        password = PasswordUtil.encrypt(encrypt, user.getSalt());
        LambdaUpdateWrapper<User> set = new LambdaUpdateWrapper<User>()
                .eq(User::getId, userId)
                .set(User::getPassword, password);
        return update(set);
    }

    @Override
    public User getAdminByNumber(Integer number) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<User>()
                .eq(User::getNumber, number)
                .eq(User::getIdentity, 3);
        List<User> users = userMapper.selectList(lambdaQueryWrapper);
        if (users.size() == 0) {
            throw new ErrorNumberOrPasswordException("账号或密码错误");
        }
        return users.get(0);
    }

    @Override
    public Page<User> queryAllUserLike(String like, Page<User> page) {
        Long pageNo = (page.getCurrent() - 1) * page.getSize();
        Long total = userMapper.queryAllUserNum(like);
        List<User> users = userMapper.queryAllUserLike(like, pageNo, page.getSize());
        page.setTotal(total);
        page.setRecords(users);
        return page;
    }

    @Override
    public User getNormalUser(Integer userId) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<User>()
                .ne(User::getIdentity, 3)
                .eq(User::getId, userId);
        List<User> users = userMapper.selectList(lambdaQueryWrapper);
        if (users.size() == 0) {
            throw new UserNotFoundExecption("未找到该用户");
        }
        return users.get(0);
    }

    @Override
    public Boolean modifyNumber(Integer id, String number) {
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<User>()
                .eq(User::getId, id)
                .set(User::getNumber, number);
        return update(wrapper);
    }

    @Override
    public Boolean modifyEmail(Integer id, String email) {
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<User>()
                .eq(User::getId, id)
                .set(User::getEmail, email);
        return update(wrapper);
    }

    @Override
    public Boolean deleteUserByStudentId(Integer studentId) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>()
                .eq(User::getIdentity, 0)
                .eq(User::getRoleId, studentId);
        return remove(wrapper);
    }
}
