package com.lyz.ddedss_springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyz.ddedss_springboot.entity.Lesson;
import com.lyz.ddedss_springboot.mapper.LessonMapper;
import com.lyz.ddedss_springboot.service.LessonService;
import org.springframework.stereotype.Service;

@Service
public class LessonServiceImpl extends ServiceImpl<LessonMapper, Lesson> implements LessonService {
}
