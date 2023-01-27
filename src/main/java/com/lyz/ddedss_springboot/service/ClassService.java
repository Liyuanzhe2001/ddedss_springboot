package com.lyz.ddedss_springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyz.ddedss_springboot.entity.Class_;

public interface ClassService extends IService<Class_> {

    public Integer getIdByName(String className);

}
