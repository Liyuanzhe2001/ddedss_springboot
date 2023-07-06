package com.lyz.ddedss_springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyz.ddedss_springboot.entity.Class_;
import com.lyz.ddedss_springboot.entity.Student;
import com.lyz.ddedss_springboot.entity.User;
import com.lyz.ddedss_springboot.mapper.ClassMapper;
import com.lyz.ddedss_springboot.mapper.StudentMapper;
import com.lyz.ddedss_springboot.service.StudentService;
import jdk.internal.dynalink.support.ClassMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ClassMapper classMapper;

    @Override
    public Class_ getClassName(Integer studentId) {
        // 获取班级id
        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<Student>()
                .select(Student::getClassId)
                .eq(Student::getId, studentId);
        Integer classId = getOne(lambdaQueryWrapper).getClassId();

        // 通过班级id查询班级
        return classMapper.selectById(classId);
    }

    @Override
    public List<Student> getStudents(Integer classId) {
        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<Student>()
                .eq(Student::getClassId, classId);

        return studentMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public Boolean modifyName(Integer id, String name) {
        LambdaUpdateWrapper<Student> wrapper = new LambdaUpdateWrapper<Student>()
                .eq(Student::getId, id)
                .set(Student::getName, name);
        return update(wrapper);
    }
}
