package com.lyz.ddedss_springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyz.ddedss_springboot.entity.Class_;
import com.lyz.ddedss_springboot.mapper.ClassMapper;
import com.lyz.ddedss_springboot.service.ClassService;
import org.springframework.stereotype.Service;

@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class_> implements ClassService {
}
