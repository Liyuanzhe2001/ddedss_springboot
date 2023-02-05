package com.lyz.ddedss_springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyz.ddedss_springboot.entity.Exam;
import com.lyz.ddedss_springboot.entity.Result;
import com.lyz.ddedss_springboot.vo.ExaminationResults;

import java.util.List;

public interface ResultService extends IService<Result> {

    /**
     * 通过学生id获取考试id列表
     */
    public List<Integer> getExamId(Integer studentId);

    /**
     * 获取某个学生某次考试分数
     */
    public Result getResult(Integer studentId,Integer subjectId,Integer examId);

    /**
     * 获取多名学生成绩
     */
    public List<Result> getResults(Integer examId, Integer studentId);

    /**
     * 获取学生成绩情况List
     */
    public List<ExaminationResults> getExaminationResults(List<Integer> studentIds);

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

}
