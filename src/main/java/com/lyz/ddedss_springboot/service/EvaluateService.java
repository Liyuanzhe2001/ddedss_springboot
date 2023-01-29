package com.lyz.ddedss_springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyz.ddedss_springboot.entity.Evaluate;

public interface EvaluateService extends IService<Evaluate> {

    /**
     * 获取最新time
     */
    public Integer getLatestTime();

    /**
     * 根据teacher_subject_id和time获取id
     */
    public Integer getId(Integer teacherSubjectId, Integer time);

}
