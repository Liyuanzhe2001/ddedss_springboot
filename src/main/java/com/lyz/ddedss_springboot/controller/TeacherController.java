package com.lyz.ddedss_springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
public class TeacherController extends BaseController {

    /**
     * 通过班级id查询教师课程列表
     *
     * @param classId 班级id
     * @return {
     * code - 状态码
     * msg - 信息
     * teachers:[
     * {
     * name - 姓名
     * subjectName - 科目
     * }
     * ]
     * }
     */
    @GetMapping("/query_teacher_list_by_class_id/{classId}")
    public String queryTeacherListByClassId(@PathVariable("classId") Integer classId) {
        return null;
    }

    /**
     * 通过教师id获取教的班级和科目
     *
     * @return {
     * code - 状态码
     * msg - 信息
     * classSubject:[
     * {
     * classId - 班级id
     * className - 班级名
     * peopleNum - 班级人数
     * subjectId - 课程id
     * subjectName - 课程名
     * haveFinish - 是否完成
     * }
     * ]
     * }
     */
    @GetMapping("/query_class_and_subject_by_teacher_id")
    public String queryClassAndSubjectByTeacherId() {
        return null;
    }


}
