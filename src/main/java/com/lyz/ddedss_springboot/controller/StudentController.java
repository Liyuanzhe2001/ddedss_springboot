package com.lyz.ddedss_springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController extends BaseController {

    /**
     * 查询班级名
     *
     * @return {
     * code - 状态码
     * msg - 信息
     * classId - 班级id
     * className - 班级名
     * }
     */
    @GetMapping("/query_class_name")
    public String queryClassName() {
        return null;
    }

    /**
     * 通过班级id查询班级学生列表
     *
     * @param classId 班级id
     * @return {
     * code - 状态码
     * msg - 信息
     * students:[
     * {
     * id - 学生id
     * name - 姓名
     * sex - 性别
     * }
     * ],
     * }
     */
    @GetMapping("/query_student_list_by_class_id/{classId}")
    public String queryStudentListByClassId(@PathVariable("classId") Integer classId) {
        return null;
    }

    /**
     * 是否有课程评价通知
     *
     * @return {
     * code - 状态码
     * msg - 信息
     * haveOrNot - 有没有（0没有，1有）
     * }
     */
    @GetMapping("/have_evaluate_course_notice")
    public String haveEvaluateCourseNotice() {
        return null;
    }

}
