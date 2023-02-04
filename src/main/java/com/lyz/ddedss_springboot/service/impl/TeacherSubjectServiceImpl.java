package com.lyz.ddedss_springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyz.ddedss_springboot.entity.TeacherSubject;
import com.lyz.ddedss_springboot.mapper.TeacherSubjectMapper;
import com.lyz.ddedss_springboot.service.TeacherSubjectService;
import com.lyz.ddedss_springboot.vo.ClassAndSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherSubjectServiceImpl extends ServiceImpl<TeacherSubjectMapper, TeacherSubject> implements TeacherSubjectService {

    @Autowired
    private TeacherSubjectMapper teacherSubjectMapper;

    @Override
    public Integer getId(Integer teacherId, Integer subjectId) {
        LambdaQueryWrapper<TeacherSubject> lambdaQueryWrapper = new LambdaQueryWrapper<TeacherSubject>()
                .eq(TeacherSubject::getTeacherId, teacherId)
                .eq(TeacherSubject::getSubjectId, subjectId);
        List<TeacherSubject> teacherSubjectList = teacherSubjectMapper.selectList(lambdaQueryWrapper);
        // 不存在id，返回-1
        if(teacherSubjectList.size() == 0) {
            return -1;
        }
        return teacherSubjectList.get(0).getId();
    }

    @Override
    public List<TeacherSubject> getTeacherSubjects(Integer teacherId) {
        LambdaQueryWrapper<TeacherSubject> lambdaQueryWrapper = new LambdaQueryWrapper<TeacherSubject>()
                .eq(TeacherSubject::getTeacherId, teacherId);
        return teacherSubjectMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public List<ClassAndSubject> getClassAndSubject(Integer teacherId) {
        return teacherSubjectMapper.getClassAndSubjectByTeacherId(teacherId);
    }

    @Override
    public List<TeacherSubject> getTeachersBySubjectId(Integer subjectId) {
        return teacherSubjectMapper.getTeachersBySubjectId(subjectId);
    }

    @Override
    public Page<TeacherSubject> getListLikeTeacherName(String likeInputValue, Page<TeacherSubject> page) {
        Long pageSize =  page.getSize();
        Long pageNo = (page.getCurrent() - 1) * page.getSize();
        Long total = teacherSubjectMapper.getCountLikeTeacherName(likeInputValue);
        List<TeacherSubject> teacherSubjectList = teacherSubjectMapper.getListLikeTeacherName(likeInputValue, pageNo, pageSize);
        page.setTotal(total)
                .setRecords(teacherSubjectList);
        return page;
    }
}
