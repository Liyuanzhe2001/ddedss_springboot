package com.lyz.ddedss_springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyz.ddedss_springboot.entity.Exam;
import com.lyz.ddedss_springboot.mapper.ExamMapper;
import com.lyz.ddedss_springboot.service.ExamService;
import org.springframework.stereotype.Service;

@Service
public class ExamServiceImpl extends ServiceImpl<ExamMapper, Exam> implements ExamService {
}
