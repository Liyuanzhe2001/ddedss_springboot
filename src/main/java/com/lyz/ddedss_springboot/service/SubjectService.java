package com.lyz.ddedss_springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyz.ddedss_springboot.entity.Subject;

import java.util.List;

public interface SubjectService extends IService<Subject> {

    /**
     * 通过名字获取id
     */
    public Integer getId(String subjectName);

    /**
     * 获取所有科目
     */
    public List<Subject> getAllSubjectList();

}
