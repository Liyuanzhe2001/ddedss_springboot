package com.lyz.ddedss_springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyz.ddedss_springboot.entity.User;
import com.lyz.ddedss_springboot.mapper.UserMapper;
import com.lyz.ddedss_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByNumberAndPassword(Integer number, String password) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<User>()
                .eq(User::getNumber, number)
                .eq(User::getPassword, password);
        User user = userMapper.selectOne(lambdaQueryWrapper);
        return user;
    }
}
