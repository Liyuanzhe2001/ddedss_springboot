package com.lyz.ddedss_springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyz.ddedss_springboot.entity.Result;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ResultMapper extends BaseMapper<Result> {

    /**
     * 通过学生id获取考试id列表
     */
    public List<Integer> getExamIdByStudentId(Integer studentId);

    /**
     * 获取总分
     */
    public Integer getSumScore(List<Integer> studentIds, Integer examId);

}
