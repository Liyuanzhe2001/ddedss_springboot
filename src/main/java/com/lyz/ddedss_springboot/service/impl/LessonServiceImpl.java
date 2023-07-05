package com.lyz.ddedss_springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyz.ddedss_springboot.entity.Lesson;
import com.lyz.ddedss_springboot.entity.TeacherSubject;
import com.lyz.ddedss_springboot.mapper.LessonMapper;
import com.lyz.ddedss_springboot.mapper.TeacherSubjectMapper;
import com.lyz.ddedss_springboot.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LessonServiceImpl extends ServiceImpl<LessonMapper, Lesson> implements LessonService {

    @Autowired
    private LessonMapper lessonMapper;

    @Autowired
    private TeacherSubjectMapper teacherSubjectMapper;

    @Override
    public List<Integer> getTeacherSubjectId(Integer classId) {
        return lessonMapper.getTeacherSubjectIdByClassId(classId);
    }

    @Override
    public List<Lesson> getLessonList(Integer classId) {
        LambdaQueryWrapper<Lesson> lambdaQueryWrapper = new LambdaQueryWrapper<Lesson>()
                .select(Lesson::getWeekday, Lesson::getSection)
                .eq(Lesson::getClassId, classId);
        return lessonMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public List<Lesson> getLessonListByTeacherId(Integer teacherId) {
        LambdaQueryWrapper<TeacherSubject> teacherSubjectLambdaQueryWrapper = new LambdaQueryWrapper<TeacherSubject>()
                .select(TeacherSubject::getId)
                .eq(TeacherSubject::getTeacherId, teacherId);
        List<TeacherSubject> teacherSubjectList = teacherSubjectMapper.selectList(teacherSubjectLambdaQueryWrapper);
        List<Integer> teacherSubjectIds = new ArrayList<>();
        teacherSubjectList.forEach(teacherSubject -> {
            teacherSubjectIds.add(teacherSubject.getId());
        });
        LambdaQueryWrapper<Lesson> lambdaQueryWrapper = new LambdaQueryWrapper<Lesson>()
                .in(Lesson::getTeacherSubjectId, teacherSubjectIds);
        return lessonMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public Boolean deleteScheduleLesson(Integer classId, Short weekday, Short section) {
        LambdaQueryWrapper<Lesson> wrapper = new LambdaQueryWrapper<Lesson>()
                .eq(Lesson::getClassId, classId)
                .eq(Lesson::getWeekday, weekday)
                .eq(Lesson::getSection, section);
        return lessonMapper.delete(wrapper) > 0;
    }
}
