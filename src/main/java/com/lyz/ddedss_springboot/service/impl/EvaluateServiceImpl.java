package com.lyz.ddedss_springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyz.ddedss_springboot.entity.Evaluate;
import com.lyz.ddedss_springboot.entity.TeacherSubject;
import com.lyz.ddedss_springboot.mapper.EvaluateMapper;
import com.lyz.ddedss_springboot.service.EvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EvaluateServiceImpl extends ServiceImpl<EvaluateMapper, Evaluate> implements EvaluateService {

    @Autowired
    private EvaluateMapper evaluateMapper;

    @Override
    public Integer getLatestTime() {
        return evaluateMapper.getLatestTime();
    }

    @Override
    public List<Integer> getIds(Integer teacherSubjectId) {
        LambdaQueryWrapper<Evaluate> lambdaQueryWrapper = new LambdaQueryWrapper<Evaluate>()
                .select(Evaluate::getId)
                .eq(Evaluate::getTeacherSubjectId, teacherSubjectId);
        List<Evaluate> evaluates = evaluateMapper.selectList(lambdaQueryWrapper);
        List<Integer> ids = new ArrayList<>();
        evaluates.forEach(evaluate -> {
            ids.add(evaluate.getId());
        });
        return ids;
    }

    @Override
    public Integer getId(Integer teacherSubjectId, Integer time) {
        LambdaQueryWrapper<Evaluate> lambdaQueryWrapper = new LambdaQueryWrapper<Evaluate>()
                .select(Evaluate::getId)
                .eq(Evaluate::getTeacherSubjectId, teacherSubjectId)
                .eq(Evaluate::getTime, time);
        return evaluateMapper.selectOne(lambdaQueryWrapper).getId();
    }
}
