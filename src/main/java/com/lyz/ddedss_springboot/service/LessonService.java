package com.lyz.ddedss_springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyz.ddedss_springboot.entity.Lesson;

import java.util.List;

public interface LessonService extends IService<Lesson> {

    /**
     * 通过班级id 查询教师科目id
     */
    public List<Integer> getTeacherSubjectId(Integer classId);

    /**
     * 获取班级的课程安排
     */
    public List<Lesson> getLessonList(Integer classId);

}
