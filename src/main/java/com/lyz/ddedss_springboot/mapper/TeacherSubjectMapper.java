package com.lyz.ddedss_springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyz.ddedss_springboot.entity.TeacherSubject;
import com.lyz.ddedss_springboot.vo.ClassAndSubjectVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeacherSubjectMapper extends BaseMapper<TeacherSubject> {

    /**
     * 通过教师id获取教的班级和科目
     */
    public List<ClassAndSubjectVO> getClassAndSubjectByTeacherId(Integer teacherId);

}
