package com.lyz.ddedss_springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyz.ddedss_springboot.entity.TeacherSubject;
import com.lyz.ddedss_springboot.vo.ClassAndSubject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeacherSubjectMapper extends BaseMapper<TeacherSubject> {

    /**
     * 通过教师id获取教的班级和科目
     */
    public List<ClassAndSubject> getClassAndSubjectByTeacherId(Integer teacherId);

    /**
     * 根据课程id查询教师
     * 课程安排较少的有限
     * 课程安排数量相同，熟练程度高优先
     */
    public List<TeacherSubject> getTeachersBySubjectId(Integer subjectId);

    /**
     * 根据 教师名 模糊查询 条数
     */
    public Long getCountLikeTeacherName(String likeInputValue);

    /**
     * 根据 教师名 模糊查询
     */
    public List<TeacherSubject> getListLikeTeacherName(String likeInputValue, Long pageNo, Long pageSize);

}
