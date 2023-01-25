package com.lyz.ddedss_springboot.controller;

import com.lyz.ddedss_springboot.vo.EvaluateTeacherVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/evaluate_final")
public class EvaluateFinalController extends BaseController {

    /**
     * 给教师的课程评价
     *
     * @param teachers {
     *                 teachers:[
     *                 id - 教师id
     *                 name - 姓名
     *                 subjectId - 科目id
     *                 subjectName - 科目名称
     *                 evaluate - 结果（0差，1优）
     *                 ]
     *                 }
     * @return {
     * code - 状态码
     * msg - 信息
     * }
     */
    @PostMapping("/grade_teacher")
    public String gradeTeacher(List<EvaluateTeacherVO> teachers) {
        return null;
    }

}
