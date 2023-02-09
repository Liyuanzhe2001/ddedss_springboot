package com.lyz.ddedss_springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyz.ddedss_springboot.entity.Admin;
import com.lyz.ddedss_springboot.entity.Instructor;

import java.util.List;

public interface InstructorService extends IService<Instructor> {

    /**
     * 获取辅导员管理的班级ids
     */
    public List<Integer> getManagedClassIds(Integer teacherId);

    /**
     * 判断教师是否为班级辅导员
     */
    public boolean judgeTeacher(Integer teacherId, Integer classId);

}
