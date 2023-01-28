package com.lyz.ddedss_springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyz.ddedss_springboot.entity.Class_;
import com.lyz.ddedss_springboot.entity.Student;

import java.util.List;

public interface StudentService extends IService<Student> {

    /**
     * 查询班级
     */
    public Class_ getClassName(Integer studentId);

    /**
     * 查询学生列表
     */
    public List<Student> getStudents(Integer classId);

}
