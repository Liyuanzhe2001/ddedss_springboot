package com.lyz.ddedss_springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyz.ddedss_springboot.entity.Professional;
import com.lyz.ddedss_springboot.entity.Student;
import com.lyz.ddedss_springboot.mapper.ProfessionalMapper;
import com.lyz.ddedss_springboot.service.ProfessionalService;
import org.springframework.stereotype.Service;

@Service
public class ProfessionalServiceImpl extends ServiceImpl<ProfessionalMapper, Professional> implements ProfessionalService {
    @Override
    public Boolean modifyName(Integer id, String name) {
        LambdaUpdateWrapper<Professional> wrapper = new LambdaUpdateWrapper<Professional>()
                .eq(Professional::getId, id)
                .set(Professional::getName, name);
        return update(wrapper);
    }
}
