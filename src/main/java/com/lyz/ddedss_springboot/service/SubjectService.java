package com.lyz.ddedss_springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyz.ddedss_springboot.entity.Subject;

public interface SubjectService extends IService<Subject> {

    /**
     * 通过名字获取id
     */
    public Integer getId(String subjectName);
}
