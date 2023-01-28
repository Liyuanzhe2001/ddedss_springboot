package com.lyz.ddedss_springboot.service.impl;

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
    public void test() {
        Page<Knowledge> page = new Page(2, 5);
        page = knowledgeMapper.selectPage(page, null);
        System.out.println(page.getTotal());
        page.getRecords().forEach(System.out::println);
        System.out.println(page.getCurrent());
        System.out.println(page.getSize());
    }
}
