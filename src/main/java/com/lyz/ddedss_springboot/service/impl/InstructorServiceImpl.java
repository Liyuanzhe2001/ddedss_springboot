package com.lyz.ddedss_springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyz.ddedss_springboot.entity.Instructor;
import com.lyz.ddedss_springboot.mapper.InstructorMapper;
import com.lyz.ddedss_springboot.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstructorServiceImpl extends ServiceImpl<InstructorMapper, Instructor> implements InstructorService {

    @Autowired
    private InstructorMapper instructorMapper;

    @Override
    public List<Integer> getManagedClassIds(Integer teacherId) {
        LambdaQueryWrapper<Instructor> lambdaQueryWrapper = new LambdaQueryWrapper<Instructor>()
                .eq(Instructor::getTeacherId, teacherId);
        List<Instructor> instructors = instructorMapper.selectList(lambdaQueryWrapper);
        List<Integer> list = new ArrayList<>();
        for (Instructor instructor : instructors) {
            list.add(instructor.getClassId());
        }
        return list;
    }
}
