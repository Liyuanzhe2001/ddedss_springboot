package com.lyz.ddedss_springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exam")
public class ExamController extends BaseController {

    /**
     *  获取学生考试列表
     * @param studentId 学生id
     * @return {
     *     code - 状态码
     *     msg - 信息
     *     exams:[
     *         {
     *             id - 考试id
     *             name - 考试名
     *         }
     *     ]
     * }
     */
    @GetMapping("/get_exam_list_by_student_id/{studentId}")
    public String getExamListByStudentId(@PathVariable("studentId") Integer studentId) {
        return null;
    }

}
