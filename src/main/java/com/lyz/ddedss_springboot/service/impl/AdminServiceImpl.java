package com.lyz.ddedss_springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyz.ddedss_springboot.entity.Admin;
import com.lyz.ddedss_springboot.mapper.AdminMapper;
import com.lyz.ddedss_springboot.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
}
