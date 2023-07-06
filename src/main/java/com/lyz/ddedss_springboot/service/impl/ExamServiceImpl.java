package com.lyz.ddedss_springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyz.ddedss_springboot.entity.Exam;
import com.lyz.ddedss_springboot.mapper.ExamMapper;
import com.lyz.ddedss_springboot.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExamServiceImpl extends ServiceImpl<ExamMapper, Exam> implements ExamService {

    @Autowired
    private ExamMapper examMapper;

    @Override
    public Integer getLatestId() {
        LambdaQueryWrapper<Exam> examLambdaQueryWrapper = new LambdaQueryWrapper<Exam>()
                .orderByDesc(Exam::getYear, Exam::getMonth);
        Exam exam = examMapper.selectList(examLambdaQueryWrapper).get(0);
        return exam.getId();
    }

    @Override
    public List<Exam> getExam(LocalDateTime startTime, LocalDateTime endTime) {
        LambdaQueryWrapper<Exam> lambdaQueryWrapper = new LambdaQueryWrapper<Exam>()
                .between(Exam::getCreateTime, startTime, endTime);
        return examMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public Boolean addExam(Exam exam) {
        int row = examMapper.insert(exam);
        return row > 0;
    }
}
