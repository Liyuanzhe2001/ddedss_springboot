package com.lyz.ddedss_springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyz.ddedss_springboot.entity.Admin;
import com.lyz.ddedss_springboot.entity.Instructor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InstructorMapper extends BaseMapper<Instructor> {
}
