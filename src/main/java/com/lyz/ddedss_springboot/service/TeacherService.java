package com.lyz.ddedss_springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyz.ddedss_springboot.entity.Teacher;

public interface TeacherService extends IService<Teacher> {

    /**
     * 修改姓名
     */
    public Boolean modifyName(Integer id,String name);

}
