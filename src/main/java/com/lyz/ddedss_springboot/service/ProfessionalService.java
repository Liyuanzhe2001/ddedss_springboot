package com.lyz.ddedss_springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyz.ddedss_springboot.entity.Professional;

public interface ProfessionalService extends IService<Professional> {

    /**
     * 修改姓名
     */
    public Boolean modifyName(Integer id,String name);

}
