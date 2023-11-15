package com.lyz.ddedss_springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyz.ddedss_springboot.entity.Class_;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassMapper extends BaseMapper<Class_> {

    public List<Class_> getClassNames(Integer teacherId);

    /**
     * 模糊查询所有班级
     */
    public List<Class_> getAllClassesLike(String className);

}
