package com.lyz.ddedss_springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyz.ddedss_springboot.entity.Result;
import com.lyz.ddedss_springboot.mapper.ResultMapper;
import com.lyz.ddedss_springboot.mapper.StudentMapper;
import com.lyz.ddedss_springboot.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultServiceImpl extends ServiceImpl<ResultMapper, Result> implements ResultService {

    @Autowired
    private ResultMapper resultMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Integer> getExamId(Integer studentId) {
        return resultMapper.getExamIdByStudentId(studentId);
    }

    @Override
    public List<Result> getResults(Integer examId, Integer studentId) {
        LambdaQueryWrapper<Result> lambdaQueryWrapper = new LambdaQueryWrapper<Result>()
                .eq(Result::getExamId, examId)
                .eq(Result::getStudentId, studentId);
        return resultMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public Double getAvgScore(Integer studentId, Integer examId) {
        // 获取同班同学
        List<Integer> ids = studentMapper.getIds(studentId);

        // 获取总分
        Integer sumScore = resultMapper.getSumScore(ids, examId);

        return 1.0 * sumScore / ids.size();
    }
}
