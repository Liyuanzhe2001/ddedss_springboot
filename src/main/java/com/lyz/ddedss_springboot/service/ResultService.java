package com.lyz.ddedss_springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyz.ddedss_springboot.entity.Exam;
import com.lyz.ddedss_springboot.entity.Result;

import java.util.List;

public interface ResultService extends IService<Result> {

    /**
     * 通过学生id获取考试id列表
     */
    public List<Integer> getExamId(Integer studentId);

    /**
     * 获取学生成绩
     */
    public List<Result> getResults(Integer examId, Integer studentId);

    /**
     * 获取班级考试平均分
     */
    public Double getAvgScore(Integer studentId,Integer examId);

}
