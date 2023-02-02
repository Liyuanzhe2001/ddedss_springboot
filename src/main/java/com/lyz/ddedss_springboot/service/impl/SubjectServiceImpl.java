package com.lyz.ddedss_springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyz.ddedss_springboot.entity.Subject;
import com.lyz.ddedss_springboot.mapper.SubjectMapper;
import com.lyz.ddedss_springboot.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public Integer getId(String subjectName) {
        LambdaQueryWrapper<Subject> lambdaQueryWrapper = new LambdaQueryWrapper<Subject>()
                .select(Subject::getId)
                .eq(Subject::getName, subjectName);
        List<Subject> subjectList = subjectMapper.selectList(lambdaQueryWrapper);

        // 如果不存在 返回-1
        if(subjectList.size() == 0){
            return -1;
        }

        return subjectList.get(0).getId();
    }
}
