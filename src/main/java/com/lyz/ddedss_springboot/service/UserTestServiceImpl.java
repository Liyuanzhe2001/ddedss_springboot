package com.lyz.ddedss_springboot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyz.ddedss_springboot.entity.UserTest;
import com.lyz.ddedss_springboot.mapper.UserTestMapper;
import org.springframework.stereotype.Service;

@Service
public class UserTestServiceImpl extends ServiceImpl<UserTestMapper, UserTest> implements UserTestService {
}
