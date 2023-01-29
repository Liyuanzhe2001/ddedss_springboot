package com.lyz.ddedss_springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyz.ddedss_springboot.entity.TeacherSubject;

import java.util.List;

public interface TeacherSubjectService extends IService<TeacherSubject> {

    /**
     * 通过teacher_id和subject_id获取id
     */
    public Integer getId(Integer teacherId, Integer subjectId);

}
