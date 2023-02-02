package com.lyz.ddedss_springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyz.ddedss_springboot.entity.Class_;

import java.util.List;

public interface ClassService extends IService<Class_> {

    public Integer getIdByName(String className);

    /**
     * 获取所有班级
     */
    public List<Class_> getAllClassList();

}
