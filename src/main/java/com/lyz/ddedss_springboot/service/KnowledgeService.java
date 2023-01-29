package com.lyz.ddedss_springboot.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyz.ddedss_springboot.entity.Knowledge;

public interface KnowledgeService extends IService<Knowledge> {

    /**
     * 根据教师id获取发布的知识（分页）
     */
    public Page<Knowledge> queryKnowledgeList(Page<Knowledge> page, Integer teacherId);


}
