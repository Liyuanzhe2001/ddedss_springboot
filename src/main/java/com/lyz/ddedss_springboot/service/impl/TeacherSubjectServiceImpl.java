package com.lyz.ddedss_springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyz.ddedss_springboot.entity.TeacherSubject;
import com.lyz.ddedss_springboot.mapper.TeacherSubjectMapper;
import com.lyz.ddedss_springboot.service.TeacherSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherSubjectServiceImpl extends ServiceImpl<TeacherSubjectMapper, TeacherSubject> implements TeacherSubjectService {

    @Autowired
    private TeacherSubjectMapper teacherSubjectMapper;

}
