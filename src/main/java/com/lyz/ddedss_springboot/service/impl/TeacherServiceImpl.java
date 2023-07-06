package com.lyz.ddedss_springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyz.ddedss_springboot.entity.Student;
import com.lyz.ddedss_springboot.entity.Teacher;
import com.lyz.ddedss_springboot.mapper.TeacherMapper;
import com.lyz.ddedss_springboot.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Override
    public Boolean modifyName(Integer id, String name) {
        LambdaUpdateWrapper<Teacher> wrapper = new LambdaUpdateWrapper<Teacher>()
                .eq(Teacher::getId, id)
                .set(Teacher::getName, name);
        return update(wrapper);
    }
}
