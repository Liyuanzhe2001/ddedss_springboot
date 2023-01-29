package com.lyz.ddedss_springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyz.ddedss_springboot.entity.Exam;

import java.util.List;

public interface ExamService extends IService<Exam> {

    /**
     * 获取最新的考试id
     */
    public Integer getLatestId();

}
