package com.lyz.ddedss_springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyz.ddedss_springboot.entity.TeacherSubject;
import com.lyz.ddedss_springboot.vo.ClassAndSubject;

import java.util.List;

public interface TeacherSubjectService extends IService<TeacherSubject> {

    /**
     * 通过teacher_id和subject_id获取id
     */
    public Integer getId(Integer teacherId, Integer subjectId);


    /**
     * 通过教师id获取教师科目列表
     */
    public List<TeacherSubject> getTeacherSubjects(Integer teacherId);


    /**
     * 通过教师id获取教的班级和科目
     */
    public List<ClassAndSubject> getClassAndSubject(Integer teacherId);

    /**
     * 根据课程id查询教师
     */
    public List<TeacherSubject> getTeachersBySubjectId(Integer subjectId);

    /**
     * 根据 教师名 模糊查询
     */
    public Page<TeacherSubject> getListLikeTeacherName(String likeInputValue, Page<TeacherSubject> page);

    /**
     * 获取所有id
     */
    public List<Integer> getAllIds();

}
