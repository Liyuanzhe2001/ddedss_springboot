package com.lyz.ddedss_springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyz.ddedss_springboot.entity.Lesson;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LessonMapper extends BaseMapper<Lesson> {

    public List<Integer> getTeacherSubjectIdByClassId(Integer classId);

}
