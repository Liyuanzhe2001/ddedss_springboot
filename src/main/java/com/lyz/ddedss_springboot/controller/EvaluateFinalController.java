package com.lyz.ddedss_springboot.controller;

import com.lyz.ddedss_springboot.dto.req.GradeTeacherReqDto;
import com.lyz.ddedss_springboot.util.ResultJson;
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
     */
    @PostMapping("/grade_teacher")
    public ResultJson<Void> gradeTeacher(List<GradeTeacherReqDto> reqDtos) {

        return null;
    }

}
