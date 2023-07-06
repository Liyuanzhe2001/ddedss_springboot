package com.lyz.ddedss_springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyz.ddedss_springboot.entity.Exam;

import java.time.LocalDateTime;
import java.util.List;

public interface ExamService extends IService<Exam> {

    /**
     * 获取最新的考试id
     */
    public Integer getLatestId();

    /**
     * 获取时间段的考试
     */
    public List<Exam> getExam(LocalDateTime startTime, LocalDateTime endTime);

    /**
     * 新增考试
     */
    public Boolean addExam(Exam exam);

}
