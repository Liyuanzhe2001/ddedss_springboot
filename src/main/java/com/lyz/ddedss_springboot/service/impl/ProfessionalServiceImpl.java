package com.lyz.ddedss_springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyz.ddedss_springboot.entity.Professional;
import com.lyz.ddedss_springboot.mapper.ProfessionalMapper;
import com.lyz.ddedss_springboot.service.ProfessionalService;
import org.springframework.stereotype.Service;

@Service
public class ProfessionalServiceImpl extends ServiceImpl<ProfessionalMapper, Professional> implements ProfessionalService {
}
