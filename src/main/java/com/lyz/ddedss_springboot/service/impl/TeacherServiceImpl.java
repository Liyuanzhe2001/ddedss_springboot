package com.lyz.ddedss_springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyz.ddedss_springboot.entity.Teacher;
import com.lyz.ddedss_springboot.mapper.TeacherMapper;
import com.lyz.ddedss_springboot.service.TeacherService;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
}
