package com.lyz.ddedss_springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyz.ddedss_springboot.entity.Class_;

import java.util.List;

public interface ClassService extends IService<Class_> {

    public Integer getIdByName(String className);

    /**
     * 获取所有班级
     */
    public List<Class_> getAllClassList();

    /**
     * 模糊查询所有班级
     */
    public Page<Class_> getAllLikeClassList(String like, Page<Class_> page);

    /**
     * 获取教师教的所有班级名
     */
    public List<Class_> getClasses(Integer teacherId);

}
