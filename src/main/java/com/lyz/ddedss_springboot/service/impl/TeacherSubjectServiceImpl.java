package com.lyz.ddedss_springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyz.ddedss_springboot.entity.TeacherSubject;
import com.lyz.ddedss_springboot.mapper.TeacherSubjectMapper;
import com.lyz.ddedss_springboot.service.TeacherSubjectService;
import org.springframework.stereotype.Service;

@Service
public class TeacherSubjectServiceImpl extends ServiceImpl<TeacherSubjectMapper, TeacherSubject> implements TeacherSubjectService {
}
