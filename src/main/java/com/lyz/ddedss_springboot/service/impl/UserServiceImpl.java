package com.lyz.ddedss_springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyz.ddedss_springboot.entity.User;
import com.lyz.ddedss_springboot.mapper.UserMapper;
import com.lyz.ddedss_springboot.service.UserService;
import com.lyz.ddedss_springboot.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByNumber(Integer number) {
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
    public boolean judgeUserPassword(Integer roleId, String password) throws NoSuchAlgorithmException {
        // 通过roleId 获取 盐值
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<User>()
                .select(User::getSalt, User::getPassword)
                .eq(User::getRoleId, roleId);
        User user = getOne(lambdaQueryWrapper);

        return PasswordUtil.check(user.getPassword(), password, user.getSalt());
    }

    @Override
    public boolean modifyPasswordByRoleId(Integer roleId, String password) throws NoSuchAlgorithmException {
        // 获取salt
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<User>()
                .select(User::getSalt)
                .eq(User::getRoleId, roleId);
        User user = getOne(lambdaQueryWrapper);

        // 加密
        String encrypt = PasswordUtil.encrypt(password);
        password = PasswordUtil.encrypt(encrypt, user.getSalt());
        LambdaUpdateWrapper<User> set = new LambdaUpdateWrapper<User>()
                .eq(User::getRoleId, roleId)
                .set(User::getPassword, password);
        return update(set);
    }


}
