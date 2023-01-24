package com.lyz.ddedss_springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyz.ddedss_springboot.entity.Result;
import com.lyz.ddedss_springboot.mapper.ResultMapper;
import com.lyz.ddedss_springboot.service.ResultService;
import org.springframework.stereotype.Service;

@Service
public class ResultServiceImpl extends ServiceImpl<ResultMapper, Result> implements ResultService {
}
