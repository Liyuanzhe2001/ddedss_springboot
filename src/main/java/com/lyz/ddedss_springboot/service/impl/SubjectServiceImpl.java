package com.lyz.ddedss_springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyz.ddedss_springboot.entity.Subject;
import com.lyz.ddedss_springboot.mapper.SubjectMapper;
import com.lyz.ddedss_springboot.service.SubjectService;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {
}
