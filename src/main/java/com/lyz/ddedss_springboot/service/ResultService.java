package com.lyz.ddedss_springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyz.ddedss_springboot.entity.Result;
import com.lyz.ddedss_springboot.vo.ExaminationResults;

import java.util.List;
import java.util.Map;

public interface ResultService extends IService<Result> {

    /**
     * 通过学生id获取考试id列表
     */
    public List<Integer> getExamId(Integer studentId);

    /**
     * 获取某个学生某次考试分数
     */
    public Result getResult(Integer studentId, Integer subjectId, Integer examId);

    /**
     * 获取多名学生成绩
     */
    public List<Result> getResults(Integer examId, Integer studentId);

    /**
     * 获取学生成绩情况List
     */
    public List<ExaminationResults> getExaminationResults(List<Integer> studentIds, Integer examId);

    /**
     * 获取班级考试平均分
     */
    public Double getAvgScore(Integer studentId, Integer examId);

    /**
     * 检查是否有学生还未打分
     */
    public boolean checkNoScore(Integer examId, Integer subjectId, Integer classId);

    /**
     * 修改学生分数
     */
    public void modifyStudentScore(Integer studentId, Integer examId, Integer subjectId, Integer score);

    /**
     * 获取某段时间科目平均成绩
     */
    public Map<Integer, Map<String, Double>> getAvgScoreByTime(Integer startYear, Integer endYear, Integer subjectId);

}
