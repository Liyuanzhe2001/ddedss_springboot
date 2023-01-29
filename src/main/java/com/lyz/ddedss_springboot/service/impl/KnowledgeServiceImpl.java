package com.lyz.ddedss_springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyz.ddedss_springboot.entity.Knowledge;
import com.lyz.ddedss_springboot.mapper.KnowledgeMapper;
import com.lyz.ddedss_springboot.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KnowledgeServiceImpl extends ServiceImpl<KnowledgeMapper, Knowledge> implements KnowledgeService {

    @Autowired
    private KnowledgeMapper knowledgeMapper;


    @Override
    public Page<Knowledge> queryKnowledgeList(Page<Knowledge> page, Integer teacherId) {
        LambdaQueryWrapper<Knowledge> lambdaQueryWrapper = new LambdaQueryWrapper<Knowledge>()
                .eq(Knowledge::getTeacherId, teacherId);
        page(page,lambdaQueryWrapper);
        return knowledgeMapper.selectPage(page, lambdaQueryWrapper);
    }
}
